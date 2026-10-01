INSERT INTO tb_user (
    id,
    first_name,
    last_name,
    email,
    password,
    role,
    created_at,
    updated_at
)
VALUES (
    gen_random_uuid(),
    'Admin',
    'Nexus',
    'admin@nexus.com',
    '$2a$10$xJ2jO4LO60aq9Lwk5kJ7Q.IneRdFPDGf5IRfM2YC5WQ1ZMsGOYUUe',
    'ADMIN',
    NOW(),
    NOW()
);