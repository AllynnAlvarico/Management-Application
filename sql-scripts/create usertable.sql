create table users (
	`id` INT UNSIGNED AUTO_INCREMENT NOT NULL,
    `userID` VARCHAR(36) UNIQUE NOT NULL,
    `username` VARCHAR(36) NOT NULL,
    `password` VARCHAR(36) NOT NULL,
    `created_at` DATETIME,
	`avatar` TEXT,
    PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;