CREATE DATABASE `adlinker_db` DEFAULT CHARACTER SET utf8;

GRANT SELECT, INSERT, UPDATE, DELETE
    ON `adlinker_db`.*
    TO adlinker_user@localhost
    IDENTIFIED BY 'adlinker_password';

GRANT SELECT, INSERT, UPDATE, DELETE
    ON `adlinker_db`.*
    TO adlinker_user@'%'
    IDENTIFIED BY 'adlinker_password';