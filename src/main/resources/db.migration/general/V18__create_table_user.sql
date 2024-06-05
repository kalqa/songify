CREATE TABLE users
(
    id          BIGSERIAL PRIMARY KEY,
    email       VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    authorities TEXT[],
    enabled BOOLEAN NOT NULL
);
