USE `library_db`;

CREATE TABLE `user` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`login` VARCHAR(255) NOT NULL UNIQUE,
	`password` CHAR(32) NOT NULL,
	/*
	 * 0 - администратор (Role.ADMINISTRATOR)
	 * 1 - рекламодатель (Role.ADVERTISER)
	 * 2 - инфлюенсер (Role.INFLUENCER)
	 * 3 - менеджер (Role.MANAGER)
	 */
	`role` TINYINT NOT NULL CHECK (`role` IN (0, 1, 2, 3)),
	PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `campaign` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`create_date` DATE NOT NULL,
	`begin_date` DATE NOT NULL,
	`end_date` DATE NOT NULL,
	`description` VARCHAR(1024) NOT NULL,
	`requirement` VARCHAR(1024) NOT NULL,
	`budget` INTEGER NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;