INSERT INTO users (email, password, authorities, enabled)
VALUES
    ('bartek', '12345', '{ROLE_ADMIN, ROLE_USER}', true),
    ('john', '12345', '{ROLE_USER}', true);
