CREATE TABLE inclusive_content_image_description (
    id BIGSERIAL PRIMARY KEY,
    cache_key VARCHAR(512) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);
