CREATE TABLE if not exists `balance_record`
(
    `id`               bigint       NOT NULL AUTO_INCREMENT,
    `note`             varchar(255) DEFAULT NULL,
    `amount`           float(12, 2) NOT NULL,
    `input_time`       datetime     NOT NULL,
    `transaction_date` date         NOT NULL,
    PRIMARY KEY (`id`)
);
