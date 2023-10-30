DROP TABLE IF EXISTS album CASCADE;

CREATE TABLE album
(
    id           BIGSERIAL PRIMARY KEY,
    title        VARCHAR(255) NOT NULL UNIQUE,
    release_date timestamp(6) WITH TIME ZONE
);


ALTER SEQUENCE album_id_seq INCREMENT 1;
