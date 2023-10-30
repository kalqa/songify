DROP TABLE IF EXISTS song CASCADE;

CREATE TABLE song
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    release_date timestamp(6) WITH TIME ZONE,
    duration     BIGINT,
    language     VARCHAR(255),
--     album_id     BIGSERIAL,
--     genre_id     BIGSERIAL,
    album_id     BIGSERIAL REFERENCES album (id),
    genre_id     BIGSERIAL REFERENCES genre (id)
);
--
-- ALTER TABLE song
--     ADD CONSTRAINT FK_SONG_ON_ALBUM FOREIGN KEY (album_id) REFERENCES album (id);
--
-- ALTER TABLE song
--     ADD CONSTRAINT FK_SONG_ON_GENRE FOREIGN KEY (genre_id) REFERENCES genre (id);

ALTER SEQUENCE song_id_seq INCREMENT 1;

