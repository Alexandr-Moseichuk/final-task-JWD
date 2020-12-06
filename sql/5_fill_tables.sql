INSERT INTO `users` (
	`identity`,
	`login`,
	`password`,
    `mail`
	`role`
) VALUES (
	2,
	"user1",
	"EE11CBB19052E40B07AAC0CA060C23EE", /* MD5 хэш пароля "user" */
	"user1@mail.ru",
	1
), (
	3,
	"user2",
	"EE11CBB19052E40B07AAC0CA060C23EE", /* MD5 хэш пароля "user" */
    "user2@mail.ru",
	2
);

INSERT INTO `campaign`
(`id`, `create_date`,  `begin_date`, `end_date`, `title`,
 `description`,
 `requirement`,
 `budget`)
VALUES
(1,    2020-12-07,     2020-12-10,   2020-12-10, "BigBon",
 "Рекламная кампания лапши быстрого приготовления",
 "50 тыс. просмотров, нативная интерграция",
 50),
(2,    2020-11-30,     2020-11-31,    2020-12-01, "RAID",
 "Реклама мобильной игры в стиле дарк фентези",
 "100 тыс. просмотров"
 100);

INSERT INTO `user_campaign_m2m`
(`user_id`, `campaign_id`)
VALUES
(1,         1),
(1,         2),
(2,         2);

INSERT INTO `role`
(`id`, `name`)
VALUES
(0,    "administrator"),
(1,    "advertiser"),
(2,    "influencer"),
(3,     "manager");

INSERT INTO `user_info`
(`id`, `user_id`, `last_name`, `first_name`, `second_name`,
 `description`,
 `phone_number`,
 `photo_url`)
 VALUES
(1,    1,         "Петров",     "Пётр",      "Петрович",
 "Стример на платформе Twitch",
 375444568714,
 "https://cdnimg.rg.ru/img/content/168/10/26/kotik_d_850_d_850.jpg"),
(2,    2,         "Иванов",     "Иван",      "Иванович",
 "Блоггер на платформе YouTube",
 375897584425,
 "https://m.buro247.ru/images/senina/max-baskakov-OzAeZPNsLXk-unsplash.jpg");

INSERT INTO `sotial_link`
(`user_info_id` , `link`)
VALUES
(1,  "vk.com/user1"),
(1, "twitch.tv/user1"),
(2, "youtube.com/user2"),
(2, "vk.com/user2"),
(2, "instagram.com/user2");

INSERT INTO `file`
(`campaign_id`, `path`,     `full_name`)
VALUES
(1,             "/somedir", "text.txt"),
(2,             "/somedir2", "photo.png"),
(2,             "/somedir2", "text.txt"),
(2,             "/somedir2", "photo2.png")
