import base64
import io
import os
import subprocess
import tempfile
from pathlib import Path

import requests
from dotenv import load_dotenv
from fastapi import FastAPI, File, Form, HTTPException, UploadFile
from fastapi.responses import FileResponse
from google import genai
from openai import OpenAI
from PIL import Image

load_dotenv()

app = FastAPI(title="Inclusive Audio Lesson Generator", version="1.0")

LEGACY_AUDIO_MODE = os.getenv("AUDIO_GENERATION_MODE", "").lower()
TTS_MODE = os.getenv("TTS_MODE") or ("cloud" if LEGACY_AUDIO_MODE == "cloud" else "local")
IMAGE_DESCRIPTION_MODE = os.getenv("IMAGE_DESCRIPTION_MODE") or "cloud"
API_KEY = os.getenv("PROXY_API_KEY")
OLLAMA_URL = os.getenv("OLLAMA_URL", "http://ollama:11434")
OLLAMA_MODEL = os.getenv("OLLAMA_MODEL", "llava:7b")
PIPER_BIN = os.getenv("PIPER_BIN", "/usr/local/bin/piper")
PIPER_MODEL = os.getenv("PIPER_MODEL", "/voices/ru_RU-irina-medium.onnx")
ESPEAK_BIN = os.getenv("ESPEAK_BIN", "espeak-ng")

TTS_MODEL = "gpt-4o-mini-tts"
TTS_VOICE = "coral"
TTS_INSTRUCTIONS = "Говори как учитель, который читает лекцию. Говори чётко, спокойно и понятно."

gemini_client: genai.Client | None = None
openai_client: OpenAI | None = None


def ensure_cloud_clients() -> None:
    global gemini_client, openai_client
    if gemini_client and openai_client:
        return
    if not API_KEY:
        raise HTTPException(status_code=500, detail="PROXY_API_KEY is required for cloud mode")

    gemini_client = genai.Client(
        api_key=API_KEY,
        http_options={"base_url": "https://api.proxyapi.ru/google"},
    )
    openai_client = OpenAI(
        api_key=API_KEY,
        base_url="https://api.proxyapi.ru/openai/v1",
    )


def text_to_speech_cloud(text: str) -> Path:
    ensure_cloud_clients()
    assert openai_client is not None
    with tempfile.NamedTemporaryFile(delete=False, suffix=".mp3") as tmp_file:
        temp_path = Path(tmp_file.name)

    with openai_client.audio.speech.with_streaming_response.create(
        model=TTS_MODEL,
        voice=TTS_VOICE,
        input=text,
        instructions=TTS_INSTRUCTIONS,
    ) as response:
        response.stream_to_file(temp_path)

    return temp_path


def text_to_speech_local(text: str) -> Path:
    with tempfile.NamedTemporaryFile(delete=False, suffix=".wav") as wav_file:
        wav_path = Path(wav_file.name)
    with tempfile.NamedTemporaryFile(delete=False, suffix=".mp3") as mp3_file:
        mp3_path = Path(mp3_file.name)

    try:
        if Path(PIPER_BIN).exists() and Path(PIPER_MODEL).exists():
            subprocess.run(
                [PIPER_BIN, "--model", PIPER_MODEL, "--output_file", str(wav_path)],
                input=text.encode("utf-8"),
                check=True,
                stdout=subprocess.PIPE,
                stderr=subprocess.PIPE,
            )
        else:
            subprocess.run(
                [ESPEAK_BIN, "-v", "ru", "-w", str(wav_path), text],
                check=True,
                stdout=subprocess.PIPE,
                stderr=subprocess.PIPE,
            )
        subprocess.run(
            [
                "ffmpeg",
                "-y",
                "-i",
                str(wav_path),
                "-codec:a",
                "libmp3lame",
                "-qscale:a",
                "2",
                str(mp3_path),
            ],
            check=True,
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
        )
        return mp3_path
    except FileNotFoundError as exc:
        raise HTTPException(
            status_code=500,
            detail=f"Local audio binary missing: {exc.filename}",
        ) from exc
    except subprocess.CalledProcessError as exc:
        raise HTTPException(
            status_code=500,
            detail=f"Local TTS error: {exc.stderr.decode('utf-8', errors='ignore')}",
        ) from exc
    finally:
        if wav_path.exists():
            wav_path.unlink(missing_ok=True)


def text_to_speech(text: str) -> Path:
    if TTS_MODE == "cloud":
        return text_to_speech_cloud(text)
    return text_to_speech_local(text)


def describe_image_cloud(image_bytes: bytes, mime_type: str, prompt: str) -> str:
    ensure_cloud_clients()
    assert gemini_client is not None
    response = gemini_client.models.generate_content(
        model="gemini-2.0-flash",
        contents=[
            genai.types.Part.from_bytes(data=image_bytes, mime_type=mime_type),
            prompt,
        ],
    )
    description = response.candidates[0].content.parts[0].text.strip()
    if not description:
        raise HTTPException(status_code=500, detail="Gemini returned empty description")
    return description


def describe_image_local(image_bytes: bytes, prompt: str) -> str:
    payload = {
        "model": OLLAMA_MODEL,
        "stream": False,
        "messages": [
            {
                "role": "user",
                "content": (
                    "Ты помощник для доступности контента. "
                    "Опиши изображение на русском языке для озвучивания урока. "
                    "Не давай советы по оптимизации, только описание изображения.\n\n"
                    f"{prompt}"
                ),
                "images": [base64.b64encode(image_bytes).decode("utf-8")],
            }
        ],
    }

    try:
        response = requests.post(
            f"{OLLAMA_URL}/api/chat",
            json=payload,
            timeout=120,
        )
        response.raise_for_status()
        data = response.json()
        description = (data.get("message", {}).get("content") or "").strip()
        if not description:
            raise HTTPException(status_code=500, detail="Ollama returned empty description")
        return description
    except requests.RequestException as exc:
        raise HTTPException(status_code=500, detail=f"Ollama error: {str(exc)}") from exc


def describe_image(image_bytes: bytes, mime_type: str, prompt: str) -> str:
    if IMAGE_DESCRIPTION_MODE == "cloud":
        return describe_image_cloud(image_bytes, mime_type, prompt)
    return describe_image_local(image_bytes, prompt)


def load_image_from_url(image_url: str) -> tuple[bytes, str]:
    try:
        response = requests.get(
            image_url,
            timeout=30,
            headers={
                "User-Agent": (
                    "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 "
                    "(KHTML, like Gecko) Chrome/122.0 Safari/537.36"
                ),
                "Accept": "image/avif,image/webp,image/apng,image/*,*/*;q=0.8",
            },
        )
        response.raise_for_status()
    except requests.RequestException as exc:
        raise HTTPException(status_code=400, detail=f"Image URL download error: {str(exc)}") from exc

    image_bytes = response.content
    if not image_bytes:
        raise HTTPException(status_code=400, detail="Image URL returned empty body")

    content_type = (response.headers.get("content-type") or "").split(";")[0].strip().lower()
    if content_type.startswith("image/"):
        if content_type in ("image/jpeg", "image/jpg"):
            return image_bytes, "image/jpeg"
        if content_type == "image/png":
            return image_bytes, "image/png"

    try:
        img = Image.open(io.BytesIO(image_bytes))
    except Exception as exc:
        raise HTTPException(status_code=400, detail=f"Image URL is not a valid image: {image_url}") from exc

    mime_type = "image/jpeg" if img.format == "JPEG" else "image/png"
    return image_bytes, mime_type


@app.post("/audio/from-text", response_class=FileResponse)
async def audio_from_text(text: str = Form(...)):
    if not text.strip():
        raise HTTPException(status_code=400, detail="Text cannot be empty")

    try:
        audio_path = text_to_speech(text)
        return FileResponse(audio_path, media_type="audio/mpeg", filename="lesson.mp3")
    except Exception as exc:
        raise HTTPException(status_code=500, detail=f"TTS error: {str(exc)}")


@app.post("/audio/from-image", response_class=FileResponse)
async def audio_from_image(image: UploadFile = File(...)):
    if not image.content_type.startswith("image/"):
        raise HTTPException(status_code=400, detail="File must be an image (JPEG/PNG)")

    try:
        image_bytes = await image.read()
        img = Image.open(io.BytesIO(image_bytes))
        mime_type = "image/jpeg" if img.format == "JPEG" else "image/png"

        description = describe_image(
            image_bytes=image_bytes,
            mime_type=mime_type,
            prompt="Опиши это изображение подробно, как будто объясняешь слепому человеку.",
        )

        audio_path = text_to_speech(description)
        return FileResponse(audio_path, media_type="audio/mpeg", filename="image_lesson.mp3")
    except Exception as exc:
        raise HTTPException(status_code=500, detail=f"Gemini or TTS error: {str(exc)}")


@app.post("/image/describe")
async def image_describe(
    image: UploadFile | None = File(default=None),
    image_url: str | None = Form(default=None, alias="imageUrl"),
):
    if image is None and (image_url is None or not image_url.strip()):
        raise HTTPException(status_code=400, detail="Either image or imageUrl must be provided")

    if image is not None:
        if not image.content_type.startswith("image/"):
            raise HTTPException(status_code=400, detail="Image must be JPEG/PNG")
        try:
            image_bytes = await image.read()
            img = Image.open(io.BytesIO(image_bytes))
            mime_type = "image/jpeg" if img.format == "JPEG" else "image/png"
        except Exception as exc:
            raise HTTPException(status_code=400, detail="Uploaded image is invalid") from exc
    else:
        image_bytes, mime_type = load_image_from_url(image_url.strip())

    description = describe_image(
        image_bytes=image_bytes,
        mime_type=mime_type,
        prompt="Опиши это изображение кратко и понятно для включения в текст урока.",
    )
    return {"description": description}


@app.post("/audio/from-lesson", response_class=FileResponse)
async def audio_from_lesson(
    text: str = Form(...),
    image: list[UploadFile] = File(default=[]),
    image_url: list[str] = Form(default=[], alias="imageUrl"),
):
    lesson_prompt = text.strip()

    image_inputs: list[tuple[bytes, str]] = []
    for uploaded_image in image:
        if not (uploaded_image.content_type or "").startswith("image/"):
            raise HTTPException(status_code=400, detail="Image must be JPEG/PNG")
        try:
            image_bytes = await uploaded_image.read()
            img = Image.open(io.BytesIO(image_bytes))
            mime_type = "image/jpeg" if img.format == "JPEG" else "image/png"
        except Exception as exc:
            raise HTTPException(status_code=400, detail="Uploaded image is invalid") from exc
        image_inputs.append((image_bytes, mime_type))

    for raw_image_url in image_url:
        cleaned_url = raw_image_url.strip()
        if not cleaned_url:
            continue
        image_inputs.append(load_image_from_url(cleaned_url))

    if image_inputs:
        for index, (image_bytes, mime_type) in enumerate(image_inputs, start=1):
            try:
                image_desc = describe_image(
                    image_bytes=image_bytes,
                    mime_type=mime_type,
                    prompt="Опиши это изображение кратко, чтобы дополнить текстовый урок.",
                )
                marker = f"[[IMAGE_{index}]]"
                if marker in lesson_prompt:
                    lesson_prompt = lesson_prompt.replace(
                        marker,
                        image_desc,
                        1,
                    )
                else:
                    lesson_prompt += f"\n\n{image_desc}"
            except Exception as exc:
                raise HTTPException(status_code=500, detail=f"Image analysis error: {str(exc)}")

    if not lesson_prompt:
        raise HTTPException(status_code=400, detail="Either text or image must be provided")

    try:
        audio_path = text_to_speech(lesson_prompt)
        return FileResponse(audio_path, media_type="audio/mpeg", filename="combined_lesson.mp3")
    except Exception as exc:
        raise HTTPException(status_code=500, detail=f"TTS error: {str(exc)}")


@app.get("/health")
def health_check():
    return {
        "status": "OK",
        "service": "Inclusive Audio Lesson Generator",
        "tts_mode": TTS_MODE,
        "image_description_mode": IMAGE_DESCRIPTION_MODE,
    }
