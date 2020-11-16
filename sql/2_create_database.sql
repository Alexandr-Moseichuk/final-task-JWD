CREATE DATABASE `linker_db` DEFAULT CHARACTER SET utf8;

GRANT SELECT,INSERT,UPDATE,DELETE
ON `linker_db`.*
TO linker_user@localhost
IDENTIFIED BY 'linker_password';

GRANT SELECT,INSERT,UPDATE,DELETE
ON `linker_db`.*
TO linker_user@'%'
IDENTIFIED BY 'linker_password';