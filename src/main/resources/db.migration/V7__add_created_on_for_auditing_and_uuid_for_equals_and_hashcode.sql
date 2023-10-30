CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

ALTER TABLE album
    ADD created_on timestamp(6) WITH TIME ZONE default now();

ALTER TABLE album
    ADD uuid UUID default uuid_generate_v4() NOT NULL UNIQUE;

ALTER TABLE artist
    ADD created_on timestamp(6) WITH TIME ZONE default now();

ALTER TABLE artist
    ADD uuid UUID default uuid_generate_v4() NOT NULL UNIQUE;

ALTER TABLE genre
    ADD created_on timestamp(6) WITH TIME ZONE default now();

ALTER TABLE genre
    ADD uuid UUID default uuid_generate_v4() NOT NULL UNIQUE;

ALTER TABLE song
    ADD created_on timestamp(6) WITH TIME ZONE default now();

ALTER TABLE song
    ADD uuid UUID default uuid_generate_v4() NOT NULL UNIQUE;
