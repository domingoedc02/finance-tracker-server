CREATE TABLE user_accounts (
    id CHAR(36) NOT NULL PRIMARY KEY,
    user_id CHAR(36) NOT NULL,
    type ENUM('credit card', 'debit', 'bank account') NOT NULL,
    last_four_digits INTEGER,
    balance DOUBLE DEFAULT 0.0,
    card_network VARCHAR(100),
    expiration_date VARCHAR(10),
    credit_limit DOUBLE,
    credit_cut_off INTEGER,
    credit_due_days_after INTEGER,
    credit_fixed_due_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    INDEX idx_user_accounts_user_id (user_id),
    INDEX idx_user_accounts_type (type),
    INDEX idx_user_accounts_card_network (card_network)
);
