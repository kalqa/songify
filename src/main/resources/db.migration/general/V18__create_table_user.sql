CREATE TABLE users
(
    id          BIGSERIAL PRIMARY KEY,
    email       VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    authorities TEXT[],
    confirmation_token VARCHAR(255),
    enabled BOOLEAN NOT NULL
);
