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
    `approved` BOOLEAN      NOT NULL ,
    PRIMARY KEY (`id`)
);

CREATE TABLE `campaign`
(
    `id`          INTEGER       NOT NULL AUTO_INCREMENT,
    `title`       VARCHAR(32)   NOT NULL,
    `create_date` DATETIME      NOT NULL,
    `begin_date`  DATETIME      NOT NULL,
    `end_date`    DATETIME      NOT NULL,
    `description` VARCHAR(1024) NOT NULL,
    `requirement` VARCHAR(1024) NOT NULL,
    `budget`      INTEGER       NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `file`
(
    `id`          INTEGER      NOT NULL AUTO_INCREMENT ,
    `path`        VARCHAR(128) NOT NULL,
    `name`        VARCHAR(32)  NOT NULL,
    `file_type`   VARCHAR(15)  NOT NULL ,
    PRIMARY KEY (`id`)
);

CREATE TABLE `user_campaign`
(
    `user_id`     INTEGER NOT NULL ,
    `campaign_id` INTEGER NOT NULL ,
    PRIMARY KEY (`user_id`, `campaign_id`) ,
    FOREIGN KEY (`user_id`)     REFERENCES `user`     (`id`) ,
    FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`id`)
);

CREATE TABLE  `user_info`
(
    `user_id`      INTEGER     NOT NULL UNIQUE ,
    `last_name`    VARCHAR(32) NOT NULL ,
    `first_name`   VARCHAR(32) NOT NULL ,
    `second_name`  VARCHAR(32) NOT NULL ,
    `description`  VARCHAR(512) ,
    `phone_number` VARCHAR(15) ,
    `photo_id`     INTEGER,
    PRIMARY KEY (`user_id`) ,
    FOREIGN KEY (`user_id`)  REFERENCES `user`(`id`) ,
    FOREIGN KEY (`photo_id`) REFERENCES `file`(`id`)
);

CREATE TABLE `social_link`
(
    `id`      INTEGER      NOT NULL AUTO_INCREMENT,
    `user_id` INTEGER      NOT NULL ,
    `title`   VARCHAR(16)  NOT NULL ,
    `link`    VARCHAR(128) NOT NULL ,
    `views`   INTEGER      NOT NULL ,
    PRIMARY KEY (`id`) ,
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
);



CREATE TABLE `manager_influencer`
(
    `manager_id`    INTEGER NOT NULL ,
    `influencer_id` INTEGER NOT NULL UNIQUE ,
    PRIMARY KEY (`manager_id`, `influencer_id`),
    FOREIGN KEY (`manager_id`)    REFERENCES `user`(`id`),
    FOREIGN KEY (`influencer_id`) REFERENCES `user`(`id`)
);

CREATE TABLE `campaign_file`
(
    `campaign_id` INTEGER NOT NULL ,
    `file_id`     INTEGER NOT NULL UNIQUE ,
    PRIMARY KEY (`campaign_id`, `file_id`) ,
    FOREIGN KEY (`campaign_id`) REFERENCES `campaign`(`id`) ,
    FOREIGN KEY (file_id)       REFERENCES `file`(`id`)
);

CREATE TABLE `registration_application`
(
    `user_id`      INTEGER      NOT NULL UNIQUE ,
    `date`         DATETIME     NOT NULL ,
    `comment`      VARCHAR(512) NOT NULL ,
    `mobile_phone` VARCHAR(15)  NOT NULL ,
    PRIMARY KEY (`user_id`) ,
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
);