DROP DATABASE IF EXISTS `test_adlinker_db`;

CREATE DATABASE `test_adlinker_db` DEFAULT CHARACTER SET utf8;

GRANT SELECT, INSERT, UPDATE, DELETE
    ON `test_adlinker_db`.*
    TO adlinker_user@localhost
    IDENTIFIED BY 'adlinker_password';

GRANT SELECT, INSERT, UPDATE, DELETE
    ON `test_adlinker_db`.*
    TO adlinker_user@'%'
    IDENTIFIED BY 'adlinker_password';

USE `test_adlinker_db`;

CREATE TABLE `user`
(
    `id`       INTEGER      NOT NULL AUTO_INCREMENT,
    `email`    VARCHAR(255) NOT NULL UNIQUE,
    `password` CHAR(60)     NOT NULL,
    /*
     * 0 - администратор (Role.ADMINISTRATOR)
     * 1 - рекламодатель (Role.ADVERTISER)
     * 2 - инфлюенсер (Role.INFLUENCER)
     * 3 - менеджер (Role.MANAGER)
     */
    `role`     TINYINT      NOT NULL CHECK (`role` IN (0, 1, 2, 3)),
    `registration_date`     TIMESTAMP     NOT NULL ,
    /*
     * 0 - на рассмотрении
     * 1 - одобрен
     * 2 - в архиве
     */
    `status` TINYINT      NOT NULL CHECK ( `status` IN (0, 1, 2)) ,
    PRIMARY KEY (`id`)
);

CREATE TABLE `campaign`
(
    `id`          INTEGER       NOT NULL AUTO_INCREMENT,
    `title`       VARCHAR(32)   NOT NULL,
    `create_date` TIMESTAMP      NOT NULL,
    `begin_date`  TIMESTAMP      NOT NULL,
    `end_date`    TIMESTAMP      NOT NULL,
    `description` VARCHAR(1024) NOT NULL,
    `requirement` VARCHAR(1024) NOT NULL,
    `budget`      INTEGER       NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `file`
(
    `id`          INTEGER      NOT NULL AUTO_INCREMENT ,
    `path`        VARCHAR(255) NOT NULL ,
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
    `manager_id`    INTEGER  NOT NULL ,
    `influencer_id` INTEGER  NOT NULL ,
    `begin_date`    TIMESTAMP NOT NULL ,
    `end_date`      TIMESTAMP DEFAULT 0,
    PRIMARY KEY (`manager_id`, `influencer_id`, `end_date`),
    FOREIGN KEY (`manager_id`)    REFERENCES `user`(`id`),
    FOREIGN KEY (`influencer_id`) REFERENCES `user`(`id`)
);

CREATE TABLE `campaign_file`
(
    `campaign_id` INTEGER NOT NULL ,
    `file_id`     INTEGER NOT NULL UNIQUE ,
    PRIMARY KEY (`campaign_id`, `file_id`) ,
    FOREIGN KEY (`campaign_id`) REFERENCES `campaign`(`id`) ,
    FOREIGN KEY (`file_id`)       REFERENCES `file`(`id`)
);

CREATE TABLE `registration_application`
(
    `user_id`      INTEGER      NOT NULL UNIQUE ,
    `comment`      VARCHAR(5096) NOT NULL ,
    `date`         TIMESTAMP     NOT NULL ,
    PRIMARY KEY (`user_id`) ,
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
);

INSERT INTO `user` (`id`,
                    `email`,
                    `password`,
                    `role`,
                    `registration_date` ,
                    `status`)
VALUES (1,
        'admin@mail.ru',
        '$2a$10$3MjeX6zc3P1oDZj7fXS/eeLZXFW9gbtuCjg0QH0E13l6i7x3Xd5C6', /*хэш пароля "admin" */
        0,
        '2020-12-07 12:00:00' ,
        1),
       (2,
        'advertiser@mail.ru',
        '$2a$10$ows3FN0gFEcbCoumz4Zx.um7BVzzhJwE/FfM2DwnJBH5f/lfDxcUK', /*хэш пароля "advertiser" */
        1,
        '2020-12-17 12:35:00' ,
        1),
       (3,
        'influencer@mail.ru',
        '$2a$10$GzvU4CSmDNalRmzs6f070OMqN7.c6ykMit7G0rCj.p1DCuFeWkpey', /*хэш пароля "influencer" */
        2,
        '2020-12-17 12:35:00' ,
        1),
       (4,
        'manager@mail.ru',
        '$2a$10$KxJ0MVN36owBtbQKYCEKq.AmjhS.q4IL7.yLflKes9pHAUNVZq/nm', /*хэш пароля "manager" */
        3,
        '2020-12-31 14:14:14' ,
        1);

INSERT INTO `user` (
    `id`,
    `email`,
    `password`,
    `role`,
    `registration_date` ,
    `status`
) VALUES
(   5,
    'influencer2@mail.ru',
    '$2y$12$zxhbiFdWbJNiVi2ij4kXuuv2iSb2iZteG4QcSqMiPCjFZPaQd264O', /* MD5 хэш пароля "influencer" */
    2,
    '2021-01-01 00:01:15',
    1
),
(   6,
    'influencer3@mail.ru',
    '$2y$12$zxhbiFdWbJNiVi2ij4kXuuv2iSb2iZteG4QcSqMiPCjFZPaQd264O', /* MD5 хэш пароля "influencer" */
    2,
    '2021-01-01 12:01:15',
    1
),
(   7,
    'influencer4@mail.ru',
    '$2y$12$zxhbiFdWbJNiVi2ij4kXuuv2iSb2iZteG4QcSqMiPCjFZPaQd264O', /* MD5 хэш пароля "influencer" */
    2,
    '2021-01-01 12:01:15',
    1
);

INSERT INTO `campaign`
(`id`, `create_date`,  `begin_date`, `end_date`, `title`,
 `description`,
 `requirement`,
 `budget`)
VALUES
( 1,   '2020-12-07 12:00:00',     '2020-12-10 16:00:00',   '2020-12-10 18:00:00', 'BigBon',
  'Рекламная кампания лапши быстрого приготовления',
  '50 тыс. просмотров, нативная интерграция',
  50),
( 2,   '2020-11-30 08:36:00',     '2020-11-30 14:00:00',    '2020-12-01 18:00:00', 'RAID',
  'Реклама мобильной игры в стиле дарк фентези',
  '100 тыс. просмотров',
  100),
( 3,   '2020-11-26 18:22:00',     '2020-12-31 15:01:00',    '2021-01-01 18:00:00', 'Analgin Renewal',
  'Реклама средства от головной боли',
  '200 тыс. просмотров',
  165),
( 4,   '2020-11-30 08:36:00',     '2020-11-30 14:00:00',    '2020-12-01 18:00:00', 'KASPERSKIY',
  'Акция Лаборатории Касперского для покупателей комплекта Kaspersky Total Security + Cyberpunk 2077. Реклама комплекта.',
  '500 тыс. просмотров. 5000 переходов по реферальной ссылке',
  800),
( 5,   '2020-12-11 11:12:00',     '2020-11-30 14:00:00',    '2020-12-01 18:00:00', 'RAID',
  'Реклама мобильной игры в стиле дарк фентези',
  '100 тыс. просмотров',
  100),
( 6,   '2020-11-29 13:04:00',     '2020-11-30 14:00:00',    '2020-12-01 18:00:00', 'RAID',
  'Реклама мобильной игры в стиле дарк фентези',
  '100 тыс. просмотров',
  100);

INSERT INTO `file`
(`id`, `path`)
VALUES
(1,     '/somedir/text.txt'),
(2,     '/somedir2/photo.png'),
(3,     '/somedir2/text.txt'),
(4,     '/somedir2/photo2.png'),
(5,     '/userphoto/user1.png'),
(6,     '/userphoto/user2.png');

INSERT INTO `user_campaign`
(`user_id`, `campaign_id`)
VALUES
(2,         1),
(2,         2),
(2,         3);

INSERT INTO `user_info`
(`user_id`, `last_name`, `first_name`, `second_name`,
 `description`,
 `phone_number`,
 `photo_id`)
VALUES
(1,         'Петров',    'Пётр',      'Петрович',
 'Главный администратор',
 375444568714,
 1),
(2,         'Алексеев ',    'Май',      'Валерьянович',
 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce sit amet nibh consequat, facilisis metus eu, consectetur risus. Cras ut arcu a elit dapibus ultrices. Suspendisse scelerisque fringilla arcu eget efficitur. Pellentesque malesuada et arcu at congue. Nam consequat risus vitae aliquam aliquam.',
 375444568714,
 1),
(3,         'Копылов',     'Родион',      'Созонович',
 'Nulla porttitor quis nunc sit amet sollicitudin. Proin vitae neque posuere dui bibendum cursus. Praesent vitae elit porta, blandit lectus vel, dignissim lacus. Sed faucibus egestas mauris vel faucibus. Maecenas vitae est vitae leo gravida lacinia porttitor at neque.',
 375897584425,
 1),
(4,         'Овчинников',     'Клемент',      'Денисович',
 'Nunc aliquet at odio vitae consequat. Etiam eu elit felis. In ultricies tortor vel semper scelerisque. Curabitur interdum lectus enim, sed accumsan diam accumsan quis.',
 375897584425,
 1),
(5,         'Белозёров',     'Станислав',      'Федотович',
 'Nulla pulvinar lectus a ex semper, sed mollis justo vestibulum. Ut nec metus vitae ligula rhoncus sagittis. Quisque porta tincidunt libero, ut faucibus lacus ullamcorper in.',
 375897584425,
 1),
(6,         'Белозёров',     'Станислав',      'Федотович',
 'Nulla pulvinar lectus a ex semper, sed mollis justo vestibulum. Ut nec metus vitae ligula rhoncus sagittis. Quisque porta tincidunt libero, ut faucibus lacus ullamcorper in.',
 375897584425,
 1),
(7,         'Белозёров',     'Станислав',      'Федотович',
 'Nulla pulvinar lectus a ex semper, sed mollis justo vestibulum. Ut nec metus vitae ligula rhoncus sagittis. Quisque porta tincidunt libero, ut faucibus lacus ullamcorper in.',
 375897584425,
 1);

INSERT INTO `social_link`
(`id`, `user_id` , `title`, `link`, `views`)
VALUES
(1,    1, 'VK', 'vk.com/user1', 100000),
(2,    1, 'Twitch', 'twitch.tv/user1', 35000),
(3,    2, 'YouTube', 'youtube.com/user2', 2000000),
(4,    2, 'VK', 'vk.com/user2', 55000),
(5,    2, 'Instagram', 'instagram.com/user2', 135000);

INSERT INTO `manager_influencer`
(`manager_id`, `influencer_id`, `begin_date`, `end_date`)
VALUES
(4, 3, '2020-12-11 13:11:11', 0);

INSERT INTO `campaign_file`
(`campaign_id`, `file_id`)
VALUES
(1, 2),
(1, 1),
(2, 3),
(2, 5);

INSERT INTO `registration_application`
(`user_id`, `comment`, `date`)
VALUES
(6, 'Прошу рассмотреть заявку. Звоните в любое время', '2020-12-15 13:35:23'),
(7, 'Перспективный стример на платформе youtube', '2020-12-01 18:01:03');

