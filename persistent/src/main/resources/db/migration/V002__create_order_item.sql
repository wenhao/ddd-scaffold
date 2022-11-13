CREATE TABLE `order_item`
(
    `id`           BIGINT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `order_id`     BIGINT         NOT NULL,
    `product_name` VARCHAR(255)   NOT NULL,
    `quantity`     BIGINT         NOT NULL,
    `price`        DECIMAL(12, 2) NOT NULL,
    `total_price`  DECIMAL(12, 2) NOT NULL,
    `created_at`   TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP
);
