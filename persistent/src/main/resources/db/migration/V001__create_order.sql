CREATE TABLE `order`
(
    `id`           BIGINT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `customer_id`  BIGINT         NOT NULL,
    `order_status` VARCHAR(255)   NOT NULL,
    `total_price`  DECIMAL(12, 2) NOT NULL,
    `created_at`   TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP
);
