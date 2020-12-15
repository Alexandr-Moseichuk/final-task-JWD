USE `adlinker_db`;

INSERT INTO `user` (`id`,
                    `mail`,
                    `password`,
                    `role`,
                    `registration_date` ,
                    `status`)
VALUES (1,
        'admin@mail.ru',
        '21232F297A57A5A743894A0E4A801FC3', /* MD5 хэш пароля "admin" */
        0,
        '2020-12-07 12:00:00' ,
        1);