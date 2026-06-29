
CREATE TABLE idempotency_keys(
    id BIGSERIAL PRIMARY KEY,
    idempotency_key VARCHAR(255) UNIQUE,
    response VARCHAR(1000),
    created_at TIMESTAMP
);

