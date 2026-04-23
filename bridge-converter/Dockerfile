FROM python:3.11-slim

WORKDIR /app

RUN apt-get update && apt-get install -y --no-install-recommends \
    curl \
    ffmpeg \
    espeak-ng \
    && rm -rf /var/lib/apt/lists/*

# Install full Piper bundle (binary + shared libs).
RUN curl -L -o /tmp/piper.tar.gz https://github.com/rhasspy/piper/releases/download/v1.2.0/piper_amd64.tar.gz \
    && mkdir -p /opt/piper /tmp/piper-extract \
    && tar -xzf /tmp/piper.tar.gz -C /tmp/piper-extract \
    && cp -a /tmp/piper-extract/piper/. /opt/piper/ \
    && ln -sf /opt/piper/piper /usr/local/bin/piper \
    && rm -rf /tmp/piper.tar.gz /tmp/piper-extract

# Download Russian voice model (Irina).
RUN mkdir -p /voices \
    && curl -L -o /voices/ru_RU-irina-medium.onnx https://huggingface.co/rhasspy/piper-voices/resolve/main/ru/ru_RU/irina/medium/ru_RU-irina-medium.onnx \
    && curl -L -o /voices/ru_RU-irina-medium.onnx.json https://huggingface.co/rhasspy/piper-voices/resolve/main/ru/ru_RU/irina/medium/ru_RU-irina-medium.onnx.json

ENV LD_LIBRARY_PATH=/opt/piper:${LD_LIBRARY_PATH}
ENV PIPER_BIN=/usr/local/bin/piper
ENV PIPER_MODEL=/voices/ru_RU-irina-medium.onnx

COPY requirements.txt .
RUN pip install --no-cache-dir --upgrade pip && \
    pip install --no-cache-dir -r requirements.txt

COPY bridge-converter.py .

EXPOSE 8000

CMD ["uvicorn", "bridge-converter:app", "--host", "0.0.0.0", "--port", "8000"]
