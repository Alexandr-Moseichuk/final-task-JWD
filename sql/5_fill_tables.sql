USE `adlinker_db`;

INSERT INTO `user` (
    `id`,
    `mail`,
    `password`,
    `role`,
    `registration_date` ,
    `status`
) VALUES (
    2,
    'user2@mail.ru',
	'EE11CBB19052E40B07AAC0CA060C23EE', /* MD5 хэш пароля "user" */
	1,
    '2020-12-07 12:00:00',
	2
), (
    3,
    'user3@mail.ru',
    'EE11CBB19052E40B07AAC0CA060C23EE', /* MD5 хэш пароля "user" */
    1,
    '2020-12-07 12:00:00',
    2
), (4,
    'user4@mail.ru',
    'EE11CBB19052E40B07AAC0CA060C23EE', /* MD5 хэш пароля "user" */
    1,
    '2020-12-07 12:00:00',
    2
), (5,
    'user5@mail.ru',
    'EE11CBB19052E40B07AAC0CA060C23EE', /* MD5 хэш пароля "user" */
    1,
    '2020-12-07 12:00:00',
    2
), (6,
    'user6@mail.ru',
    'EE11CBB19052E40B07AAC0CA060C23EE', /* MD5 хэш пароля "user" */
    1,
    '2020-12-07 12:00:00',
    2
), (7,
    'user7@mail.ru',
    'EE11CBB19052E40B07AAC0CA060C23EE', /* MD5 хэш пароля "user" */
    1,
    '2020-12-07 12:00:00',
    2
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
(1,         1),
(1,         2),
(2,         2);

INSERT INTO `user_info`
(`user_id`, `last_name`, `first_name`, `second_name`,
 `description`,
 `phone_number`,
 `photo_id`)
 VALUES
 (1,         'Петров',    'Пётр',      'Петрович',
 'Главный администратор',
 375444568714,
 5),
(2,         'Петров',    'Пётр',      'Петрович',
 'Стример на платформе Twitch',
 375444568714,
 5),
(3,         'Иванов',     'Иван',      'Иванович',
 'Блоггер на платформе YouTube',
 375897584425,
 6);

INSERT INTO `social_link`
(`id`, `user_id` , `title`, `link`, `views`)
VALUES
(1,    1, 'VK', 'vk.com/user1', 100000),
(2,    1, 'Twitch', 'twitch.tv/user1', 35000),
(3,    2, 'YouTube', 'youtube.com/user2', 2000000),
(4,    2, 'VK', 'vk.com/user2', 55000),
(5,    2, 'Instagram', 'instagram.com/user2', 135000);

INSERT INTO `manager_influencer`
(`manager_id`, `influencer_id`)
VALUES
(5, 2),
(5, 3);

INSERT INTO `campaign_file`
(`campaign_id`, `file_id`)
VALUES
(1, 2),
(1, 1),
(2, 3),
(2, 5);

INSERT INTO `registration_application`
(`user_id`, `comment`)
VALUES
(6, 'Прошу рассмотреть заявку. Звоните в любое время'),
(7, 'Перспективный стример на платформе youtube');

