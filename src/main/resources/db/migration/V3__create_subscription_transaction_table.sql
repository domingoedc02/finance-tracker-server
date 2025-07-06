CREATE TABLE subscription_transaction (
    id CHAR(36) NOT NULL PRIMARY KEY,
    user_id CHAR(36) NOT NULL,
    subscription ENUM('pro', 'free', 'trial') NOT NULL,
    tier ENUM('upgrade', 'downgrade') NOT NULL,
    amount DOUBLE NOT NULL,
    status ENUM('successful', 'denied', 'waiting') NOT NULL DEFAULT 'waiting',
    payment_method VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    INDEX idx_subscription_transaction_user_id (user_id),
    INDEX idx_subscription_transaction_status (status),
    INDEX idx_subscription_transaction_subscription (subscription)
);
