ALTER TABLE album
    ADD version BIGINT NOT NULL default 0;

ALTER TABLE artist
    ADD version BIGINT NOT NULL default 0;

ALTER TABLE genre
    ADD version BIGINT NOT NULL default 0;

ALTER TABLE song
    ADD version BIGINT NOT NULL default 0;
