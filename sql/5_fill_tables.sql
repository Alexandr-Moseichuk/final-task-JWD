USE `adlinker_db`;

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

