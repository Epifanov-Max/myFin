CREATE TABLE if not exists `revenue_type`
(
    `id`        int         NOT NULL AUTO_INCREMENT,
    `type_name` varchar(50) NOT NULL,
    `note`      varchar(250) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE if not exists `revenue_record`
(
    `id`               bigint       NOT NULL AUTO_INCREMENT,
    `id_type`          bigint       NOT NULL,
    `amount`           float(12, 2) NOT NULL,
    `note`             varchar(255) NOT NULL,
    `input_time`       datetime     NOT NULL,
    `transaction_date` date DEFAULT NULL,
    PRIMARY KEY (`id`)
);
