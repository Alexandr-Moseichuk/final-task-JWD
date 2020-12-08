USE `adlinker_db`;

CREATE TABLE `user`
(
    `id`       INTEGER      NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(255) NOT NULL UNIQUE,
    `password` CHAR(32)     NOT NULL,
    `mail`     varchar(255) NOT NULL UNIQUE,
    /*
     * 0 - администратор (Role.ADMINISTRATOR)
     * 1 - рекламодатель (Role.ADVERTISER)
     * 2 - инфлюенсер (Role.INFLUENCER)
     * 3 - менеджер (Role.MANAGER)
     */
    `role`     TINYINT      NOT NULL CHECK (`role` IN (0, 1, 2, 3)),
    PRIMARY KEY (`id`)
);

CREATE TABLE `campaign`
(
    `id`          INTEGER       NOT NULL AUTO_INCREMENT,
    `title`       VARCHAR(32)   NOT NULL,
    `create_date` DATE          NOT NULL,
    `begin_date`  DATE          NOT NULL,
    `end_date`    DATE          NOT NULL,
    `description` VARCHAR(1024) NOT NULL,
    `requirement` VARCHAR(1024) NOT NULL,
    `budget`      INTEGER       NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `user_campaign_m2m`
(
    `user_id`     INTEGER NOT NULL ,
    `campaign_id` INTEGER NOT NULL ,
    PRIMARY KEY (`user_id`, `campaign_id`) ,
    FOREIGN KEY (`user_id`)     REFERENCES `user`     (`id`) ,
    FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`id`)
);

CREATE TABLE `role`
(
    `id`   TINYINT     NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(32) NOT NULL UNIQUE ,
    FOREIGN KEY (`id`) REFERENCES `user` (`role`)
);

CREATE TABLE  `user_info`
(
    `user_id`      INTEGER     NOT NULL UNIQUE ,
    `last_name`    VARCHAR(32) NOT NULL ,
    `first_name`   VARCHAR(32) NOT NULL ,
    `second_name`  VARCHAR(32) NOT NULL ,
    `description`  VARCHAR(512) ,
    `phone_number` VARCHAR(15) ,
    `photo_url`    VARCHAR(2083),
    PRIMARY KEY (`user_id`) ,
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

CREATE TABLE `social_link`
(
    `user_id` INTEGER      NOT NULL UNIQUE ,
    `title`   VARCHAR(16)  NOT NULL ,
    `link`    VARCHAR(128) NOT NULL ,
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
);

CREATE TABLE `file`
(
    `campaign_id` INTEGER NOT NULL,
    `path`        VARCHAR NOT NULL,
    `full_name`   VARCHAR NOT NULL,
    FOREIGN KEY (`campaign_id`) REFERENCES `campaign`(`id`)
);

CREATE TABLE `manager_influencer`
(
    `manager_id`    INTEGER NOT NULL ,
    `influencer_id` INTEGER NOT NULL UNIQUE ,
    FOREIGN KEY (`manager_id`)    REFERENCES `user`(`id`),
    FOREIGN KEY (`influencer_id`) REFERENCES `user`(`id`),
);