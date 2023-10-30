DROP TABLE IF EXISTS artist CASCADE;

CREATE TABLE artist
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);


ALTER SEQUENCE artist_id_seq INCREMENT 1;
