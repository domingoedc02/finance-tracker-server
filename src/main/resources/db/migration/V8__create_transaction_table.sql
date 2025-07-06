CREATE TABLE transaction (
    id CHAR(36) NOT NULL PRIMARY KEY,
    user_id CHAR(36) NOT NULL,
    group_id CHAR(36),
    category VARCHAR(255) NOT NULL,
    subcategory VARCHAR(255),
    status VARCHAR(50) NOT NULL,
    amount DOUBLE NOT NULL,
    note TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    INDEX idx_transaction_user_id (user_id),
    INDEX idx_transaction_group_id (group_id),
    INDEX idx_transaction_category (category),
    INDEX idx_transaction_status (status),
    INDEX idx_transaction_created_at (created_at)
);
