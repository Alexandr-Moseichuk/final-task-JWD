USE `adlinker_db`;

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
INSERT INTO `file` (`id`, `path`)
VALUES (1, 'profile/icon-user.svg');