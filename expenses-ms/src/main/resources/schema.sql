CREATE TABLE if not exists `expense_category`
(
    `id`      int         NOT NULL AUTO_INCREMENT,
    `name`    varchar(30) NOT NULL,
    `comment` varchar(70) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE if not exists `expense_type`
(
    `id`        int          NOT NULL AUTO_INCREMENT,
    `type_name` varchar(100) NOT NULL,
    `id_exp`    int          NOT NULL,
    PRIMARY KEY (`id`),
    KEY `id_exp` (`id_exp`),
    FOREIGN KEY (`id_exp`) REFERENCES `expense_category` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE if not exists `subject_type`
(
    `id`        int         NOT NULL AUTO_INCREMENT,
    `type_name` varchar(30) NOT NULL,
    `comment`   varchar(30) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE if not exists `subject_entity`
(
    `id`        bigint       NOT NULL AUTO_INCREMENT,
    `subj_name` varchar(255) NOT NULL,
    `address`   varchar(255) DEFAULT NULL,
    `note`      varchar(255) DEFAULT NULL,
    `id_type`   int          NOT NULL,
    PRIMARY KEY (`id`),
    KEY `id_type` (`id_type`),
    FOREIGN KEY (`id_type`) REFERENCES `subject_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE if not exists `subject_expense`
(
    `id_subject_type` bigint NOT NULL,
    `id_expense_type` bigint NOT NULL,
    PRIMARY KEY (`id_subject_type`, `id_expense_type`)
);



