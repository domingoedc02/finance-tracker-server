CREATE TABLE goal (
    id CHAR(36) NOT NULL PRIMARY KEY,
    user_id CHAR(36) NOT NULL,
    name VARCHAR(255) NOT NULL,
    color VARCHAR(50),
    note TEXT,
    icon VARCHAR(255),
    target_amount DOUBLE NOT NULL,
    current_amount DOUBLE DEFAULT 0.0,
    start_date DATE NOT NULL,
    end_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    INDEX idx_goal_user_id (user_id),
    INDEX idx_goal_start_date (start_date),
    INDEX idx_goal_end_date (end_date)
);
