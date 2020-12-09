USE `adlinker_db`;

INSERT INTO `user` (
    `id`,
	`login`,
	`password`,
    `mail`,
	`role`,
    `approved`
) VALUES (
2,
	'Influencer1',
	'EE11CBB19052E40B07AAC0CA060C23EE', /* MD5 хэш пароля "user" */
	'user1@mail.ru',
	2,
    true
), (
3,
	'Influencer2',
	'EE11CBB19052E40B07AAC0CA060C23EE', /* MD5 хэш пароля 'user' */
    'user2@mail.ru',
	2,
    true
), (4,
    'Advertiser',
    'EE11CBB19052E40B07AAC0CA060C23EE',
    'advert@mail.ru',
    1,
    true
), (5,
    'Manager',
    'EE11CBB19052E40B07AAC0CA060C23EE',
    'manager@gmail.com',
    3,
    true
), (6,
    'Influenc',
    'EE11CBB19052E40B07AAC0CA060C23EE',
    'inf@gmail.ru',
    2,
    false
), (7,
    'Infl',
    'EE11CBB19052E40B07AAC0CA060C23EE',
    'mail@mail.ru',
    2,
    false
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
(`id`, `path`,   `name`, `file_type`)
VALUES
(1,     '/somedir', 'text', '.txt'),
(2,     '/somedir2', 'photo' , '.png'),
(3,     '/somedir2', 'text', '.txt'),
(4,     '/somedir2', 'photo2', '.png'),
(5,     '/userphoto', 'user1', '.png'),
(6,     '/userphoto', 'user2', '.png');

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
 'Стример на платформе Twitch',
 375444568714,
 5),
(2,         'Иванов',     'Иван',      'Иванович',
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
(`user_id`, `date`, `comment`, `mobile_phone`)
VALUES
(6, '2020-12-09 22:45:23', 'Прошу рассмотреть заявку. Звоните в любое время', 375444658247),
(7, '2020-11-30 12:11:47', 'Перспективный стример на платформе youtube', 375296874596);

