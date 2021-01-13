USE `adlinker_db`;

INSERT INTO `user` (`id`,
                    `mail`,
                    `password`,
                    `role`,
                    `registration_date` ,
                    `status`)
VALUES (1,
        'admin@mail.ru',
        '$2a$10$3MjeX6zc3P1oDZj7fXS/eeLZXFW9gbtuCjg0QH0E13l6i7x3Xd5C6', /*хэш пароля "admin" */
        0,
        '2020-12-07 12:00:00' ,
        1);