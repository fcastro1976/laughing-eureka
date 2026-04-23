ALTER TABLE inclusive_content_audio
    ADD COLUMN file_id UUID;

ALTER TABLE inclusive_content_audio
    DROP COLUMN audio_data;
