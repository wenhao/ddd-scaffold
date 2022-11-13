CREATE TABLE `comment`
(
    `id`         BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `order_id`   BIGINT       NOT NULL,
    `title`      varchar(255) NOT NULL,
    `content`    varchar(255) NOT NULL,
    `created_at` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);
