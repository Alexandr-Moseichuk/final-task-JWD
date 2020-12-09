USE `adlinker_db`;

INSERT INTO `user` (

	`login`,
	`password`,
    `mail`,
	`role`,
    `approved`
) VALUES (

	'user1',
	'EE11CBB19052E40B07AAC0CA060C23EE', /* MD5 хэш пароля "user" */
	'user1@mail.ru',
	1,
    true
), (

	'user2',
	'EE11CBB19052E40B07AAC0CA060C23EE', /* MD5 хэш пароля 'user' */
    'user2@mail.ru',
	2,
    true
);

INSERT INTO `campaign`
( `create_date`,  `begin_date`, `end_date`, `title`,
 `description`,
 `requirement`,
 `budget`)
VALUES
(    '2020-12-07 12:00:00',     '2020-12-10 16:00:00',   '2020-12-10 18:00:00', 'BigBon',
 'Рекламная кампания лапши быстрого приготовления',
 '50 тыс. просмотров, нативная интерграция',
 50),
(    '2020-11-30 08:36:00',     '2020-11-30 14:00:00',    '2020-12-01 18:00:00', 'RAID',
 'Реклама мобильной игры в стиле дарк фентези',
 '100 тыс. просмотров',
 100);

INSERT INTO `file`
( `path`,   `name`, `file_type`)
VALUES
(     '/somedir', 'text', '.txt'),
(     '/somedir2', 'photo' , '.png'),
(     '/somedir2', 'text', '.txt'),
(     '/somedir2', 'photo2', '.png'),
(     '/userphoto', 'user1', '.png'),
(     '/userphoto', 'user2', '.png');

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
(`user_id` , `title`, `link`, `views`)
VALUES
(1, 'VK', 'vk.com/user1', 100000),
(1, 'Twitch', 'twitch.tv/user1', 35000),
(2, 'YouTube', 'youtube.com/user2', 2000000),
(2, 'VK', 'vk.com/user2', 55000),
(2, 'Instagram', 'instagram.com/user2', 135000);


