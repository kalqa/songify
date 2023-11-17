DROP TABLE IF EXISTS artist_albums CASCADE;

CREATE TABLE artist_albums
(
    album_id  BIGINT NOT NULL,
    artist_id BIGINT NOT NULL,
    CONSTRAINT pk_artist_albums PRIMARY KEY (album_id, artist_id)
);

ALTER TABLE artist_albums
    ADD CONSTRAINT fk_artalb_on_album FOREIGN KEY (album_id) REFERENCES album (id);

ALTER TABLE artist_albums
    ADD CONSTRAINT fk_artalb_on_artist FOREIGN KEY (artist_id) REFERENCES artist (id);

-- CREATE TABLE artist_albums
-- (
--     artist_id BIGSERIAL REFERENCES artist (id),
--     album_id  BIGSERIAL REFERENCES album (id),
--     PRIMARY KEY (artist_id, album_id)
-- );
