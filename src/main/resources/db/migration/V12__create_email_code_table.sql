CREATE TABLE email_code (
    id CHAR(36) NOT NULL PRIMARY KEY,
    code VARCHAR(10) NOT NULL,
    email VARCHAR(255) NOT NULL,
    device_id VARCHAR(255) NOT NULL,
    is_used BOOLEAN DEFAULT FALSE,
    is_expired BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_email_code_email (email),
    INDEX idx_email_code_device_id (device_id),
    INDEX idx_email_code_code (code),
    INDEX idx_email_code_is_used (is_used),
    INDEX idx_email_code_is_expired (is_expired)
);
