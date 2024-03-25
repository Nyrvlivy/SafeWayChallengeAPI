CREATE TABLE IF NOT EXISTS `companies`
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    name               VARCHAR(255)   NOT NULL,
    cnpj               CHAR(14)       NOT NULL UNIQUE,
    phone              VARCHAR(15),
    administration_fee DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS `accounts`
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    agency         VARCHAR(255)   NOT NULL,
    account_number VARCHAR(255)   NOT NULL UNIQUE,
    balance        DECIMAL(19, 4) NOT NULL DEFAULT 0.00,
    company_id     BIGINT UNIQUE,
    FOREIGN KEY (company_id) REFERENCES `companies` (id)
);

CREATE TABLE IF NOT EXISTS `customers`
(
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    cpf   CHAR(11)     NOT NULL UNIQUE,
    phone VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS `transactions`
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id       BIGINT                         NOT NULL,
    costumer_id      BIGINT                         NOT NULL,
    transaction_type ENUM ('DEPOSIT', 'WITHDRAWAL') NOT NULL,
    amount           DECIMAL(19, 4)                 NOT NULL,
    fee_amount       DECIMAL(19, 4)                 NOT NULL,
    transaction_date TIMESTAMP                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES `accounts` (id),
    FOREIGN KEY (costumer_id) REFERENCES `customers` (id)
);
