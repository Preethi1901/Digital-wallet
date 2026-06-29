CREATE TABLE transactions(

 id BIGSERIAL PRIMARY KEY,

 sender_wallet_id BIGINT,

 receiver_wallet_id BIGINT,

 amount NUMERIC(19,2),

 status VARCHAR(20),

 created_at TIMESTAMP
);