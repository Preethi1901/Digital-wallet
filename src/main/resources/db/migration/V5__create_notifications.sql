CREATE TABLE notifications (

    id BIGSERIAL PRIMARY KEY,

    user_id BIGINT NOT NULL,

    message VARCHAR(500),

    status VARCHAR(20),

    created_at TIMESTAMP
);
