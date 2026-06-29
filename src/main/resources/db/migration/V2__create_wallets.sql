CREATE TABLE wallets(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    balance NUMERIC(19,2) NOT NULL,
    currency VARCHAR(10),
    version BIGINT
);

CREATE INDEX idx_wallet_user
ON wallets(user_id);

