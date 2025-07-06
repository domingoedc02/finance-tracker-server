CREATE TABLE subscription (
    id CHAR(36) NOT NULL PRIMARY KEY,
    user_id CHAR(36) NOT NULL,
    status VARCHAR(50) NOT NULL,
    subscription ENUM('pro', 'free', 'trial') NOT NULL DEFAULT 'free',
    start_date TIMESTAMP NOT NULL,
    expire_date TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    INDEX idx_subscription_user_id (user_id),
    INDEX idx_subscription_status (status),
    INDEX idx_subscription_type (subscription)
);
