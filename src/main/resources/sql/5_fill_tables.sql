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
	'$2a$10$GzvU4CSmDNalRmzs6f070OMqN7.c6ykMit7G0rCj.p1DCuFeWkpey', /* MD5 хэш пароля "influencer" */
	2,
    '2021-01-01 00:01:15',
	1
),
(   6,
    'influencer3@mail.ru',
    '$2a$10$GzvU4CSmDNalRmzs6f070OMqN7.c6ykMit7G0rCj.p1DCuFeWkpey', /* MD5 хэш пароля "influencer" */
    2,
    '2021-01-01 12:01:15',
    0
),
(   7,
    'influencer4@mail.ru',
    '$2a$10$GzvU4CSmDNalRmzs6f070OMqN7.c6ykMit7G0rCj.p1DCuFeWkpey', /* MD5 хэш пароля "influencer" */
    2,
    '2021-01-01 12:01:15',
    0
);

INSERT INTO `campaign`
(`id`, `create_date`,  `begin_date`, `end_date`, `title`,
 `description`,
 `requirement`,
 `budget`)
VALUES
( 1,   '2020-12-07 12:00:00',     '2020-12-10 16:00:00',   '2020-12-10 18:00:00', 'BigBon',
 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus arcu nulla, elementum vel turpis a, scelerisque facilisis orci.',
 'Praesent semper magna at erat cursus, id mollis justo tempor. Phasellus posuere tristique mi, vel interdum lorem ornare tristique.',
 50),
( 2,   '2020-11-30 08:36:00',     '2020-11-30 14:00:00',    '2020-12-01 18:00:00', 'RAID',
 'Suspendisse nec vehicula augue. Aenean mattis, mi nec aliquet condimentum, eros nulla rutrum sem, ac iaculis nisi sem et nulla. Sed imperdiet scelerisque nibh eu rhoncus.',
 'Vestibulum vehicula efficitur magna. Mauris sodales metus ante, non imperdiet odio feugiat sit amet. Fusce mattis sodales laoreet. Donec semper dui ac mi maximus tincidunt. Aliquam at porta quam.',
 100),
( 3,   '2020-11-26 18:22:00',     '2020-12-31 15:01:00',    '2021-01-01 18:00:00', 'Analgin Renewal',
  'Praesent rutrum dui nec mauris euismod, ac rhoncus dui tempus. Suspendisse nulla velit, sollicitudin ut sapien sed, cursus tempor dui. Suspendisse potenti.',
  'Donec laoreet, risus faucibus iaculis condimentum, ipsum libero sollicitudin lorem, nec ornare elit eros sit amet arcu. In eget porta turpis.',
  165),
( 4,   '2020-11-30 08:36:00',     '2020-11-30 14:00:00',    '2020-12-01 18:00:00', 'KASPERSKIY',
  'Curabitur eget libero eu lacus interdum fringilla sed vitae velit. Suspendisse potenti. Nullam nec elementum justo.',
  'Nulla erat lorem, iaculis vitae orci id, suscipit auctor nibh. Praesent semper magna at erat cursus, id mollis justo tempor.',
  800),
( 5,   '2020-12-11 11:12:00',     '2020-11-30 14:00:00',    '2020-12-01 18:00:00', 'RAID',
  'Nunc dignissim posuere purus sed placerat. Nulla facilisi. Phasellus eros nunc, bibendum dictum dapibus non, pharetra eget enim.',
  'Phasellus volutpat lectus nisl. Nullam eu eros scelerisque turpis fermentum dignissim nec a lectus. Suspendisse rhoncus lacus at ligula imperdiet, ut vehicula ipsum varius.',
  100),
( 6,   '2020-11-29 13:04:00',     '2020-11-30 14:00:00',    '2020-12-01 18:00:00', 'RAID',
  'Suspendisse nec consequat libero. Vivamus lacus nisl, egestas sit amet posuere quis, porttitor ut enim. Maecenas id orci nisi.',
  'Morbi varius massa eget mollis semper. Proin molestie imperdiet tortor, vitae sodales leo ornare in. Vestibulum non vehicula diam, sed mattis massa.',
  100);

INSERT INTO `file`
(`id`, `path`)
VALUES
(2,     'profile/kot-1.jpg'),
(3,     'profile/kot-2.jpeg'),
(4,     'profile/kot-3.jpg'),
(5,     'profile/kot-4.jpg'),
(6,     'profile/kot-5.jpg'),
(7,     ''),
(8,     '');

INSERT INTO `user_campaign`
(`user_id`, `campaign_id`)
VALUES
(3,         1),
(3,         2),
(3,         3);

INSERT INTO `user_info`
(`user_id`, `last_name`, `first_name`, `second_name`,
 `description`,
 `phone_number`,
 `photo_id`)
 VALUES
 (1,         'Петров',    'Пётр',      'Петрович',
 'Главный администратор',
 375444568714,
 2),
(2,         'Алексеев ',    'Май',      'Валерьянович',
 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce sit amet nibh consequat, facilisis metus eu, consectetur risus. Cras ut arcu a elit dapibus ultrices. Suspendisse scelerisque fringilla arcu eget efficitur. Pellentesque malesuada et arcu at congue. Nam consequat risus vitae aliquam aliquam.',
 375444568714,
 3),
(3,         'Копылов',     'Родион',      'Созонович',
 'Nulla porttitor quis nunc sit amet sollicitudin. Proin vitae neque posuere dui bibendum cursus. Praesent vitae elit porta, blandit lectus vel, dignissim lacus. Sed faucibus egestas mauris vel faucibus. Maecenas vitae est vitae leo gravida lacinia porttitor at neque.',
 375897584425,
 4),
 (4,         'Овчинников',     'Клемент',      'Денисович',
  'Nunc aliquet at odio vitae consequat. Etiam eu elit felis. In ultricies tortor vel semper scelerisque. Curabitur interdum lectus enim, sed accumsan diam accumsan quis.',
  375897584425,
  5),
 (5,         'Белозёров',     'Станислав',      'Федотович',
  'Nulla pulvinar lectus a ex semper, sed mollis justo vestibulum. Ut nec metus vitae ligula rhoncus sagittis. Quisque porta tincidunt libero, ut faucibus lacus ullamcorper in.',
  375897584425,
  6),
 (6,         'Белозёров',     'Станислав',      'Федотович',
  'Nulla pulvinar lectus a ex semper, sed mollis justo vestibulum. Ut nec metus vitae ligula rhoncus sagittis. Quisque porta tincidunt libero, ut faucibus lacus ullamcorper in.',
  375897584425,
  7),
 (7,         'Белозёров',     'Станислав',      'Федотович',
  'Nulla pulvinar lectus a ex semper, sed mollis justo vestibulum. Ut nec metus vitae ligula rhoncus sagittis. Quisque porta tincidunt libero, ut faucibus lacus ullamcorper in.',
  375897584425,
  8);

INSERT INTO `social_link`
(`id`, `user_id` , `title`, `link`, `views`)
VALUES
(1,    3, 'VK', 'vk.com/user1', 100000),
(2,    3, 'Twitch', 'twitch.tv/user1', 35000),
(3,    3, 'YouTube', 'youtube.com/user2', 2000000),
(4,    3, 'VK', 'vk.com/user2', 55000),
(5,    3, 'Instagram', 'instagram.com/user2', 135000);

INSERT INTO `manager_influencer`
(`manager_id`, `influencer_id`, `begin_date`, `end_date`)
VALUES
(4, 3, '2020-12-11 13:11:11', 0);

INSERT INTO `registration_application`
(`user_id`, `comment`, `date`)
VALUES
(6, 'Прошу рассмотреть заявку. Звоните в любое время', '2020-12-15 13:35:23'),
(7, 'Перспективный стример на платформе youtube', '2020-12-01 18:01:03');

