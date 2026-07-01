
CREATE TABLE audit_log(
    id BIGSERIAL PRIMARY KEY,
    wallet_id BIGINT,
    type VARCHAR(20),
    amount NUMERIC(19,2),
    balance_before NUMERIC(19,2),
    balance_after NUMERIC(19,2),
    created_at TIMESTAMP
);