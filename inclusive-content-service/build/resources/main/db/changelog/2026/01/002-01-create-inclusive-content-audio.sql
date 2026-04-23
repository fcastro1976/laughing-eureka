CREATE TABLE inclusive_content_audio (
    id BIGSERIAL PRIMARY KEY,
    lesson_id BIGINT NOT NULL UNIQUE,
    audio_data BYTEA NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL
);
