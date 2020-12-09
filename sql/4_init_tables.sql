USE `adlinker_db`;

INSERT INTO `user` (`id`,
                     `login`,
                     `password`,
                     `mail`,
                     `role`,
                     `approved`)
VALUES (1,
        'admin',
        '21232F297A57A5A743894A0E4A801FC3', /* MD5 хэш пароля "admin" */
        'admin@mail.ru',
        0,
        true);