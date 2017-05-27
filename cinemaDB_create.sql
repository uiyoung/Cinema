/*Cinema_DB*/
CREATE DATABASE cinemaDB;
USE cinemaDB;

/*admin table*/
CREATE TABLE admin(
	id VARCHAR(10) NOT NULL, 
	pw VARCHAR(10) NOT NULL
);

INSERT INTO admin VALUES ('admin','1234');


/*Member Table*/
CREATE TABLE `member_tb` (
	`no` INT(11) NOT NULL AUTO_INCREMENT,
	`id` VARCHAR(20) NOT NULL,
	`password` VARCHAR(200) NOT NULL,
	`name` VARCHAR(20) NOT NULL,
	`birthdate` CHAR(6) NOT NULL,
	`phone` VARCHAR(20) NOT NULL,
	`point` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `no` (`no`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

/*Movie Table*/
/*
CREATE TABLE `movie_tb` (
	`no` INT(11) NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(60) NOT NULL,
	`genre` VARCHAR(20) NOT NULL,
	`releaseDate` INT(11) NULL DEFAULT NULL,
	`runningTime` INT(11) NULL DEFAULT NULL,
	`description` VARCHAR(900) NULL DEFAULT NULL,
	`type` VARCHAR(20) NULL DEFAULT NULL,
	`director` VARCHAR(20) NULL DEFAULT NULL,
	`cast` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`no`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
*/
CREATE TABLE `movie_tb` (
	`no` INT(11) NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(60) NOT NULL,
	`genre` VARCHAR(20) NOT NULL,
	`releaseDate` VARCHAR(10) NULL DEFAULT NULL,
	`runningTime` VARCHAR(10) NULL DEFAULT NULL,
	`description` VARCHAR(900) NULL DEFAULT NULL,
	`type` VARCHAR(20) NULL DEFAULT NULL,
	`director` VARCHAR(20) NULL DEFAULT NULL,
	`cast` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`no`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=2
;



/*Theater Table*/
/*
CREATE TABLE `theater_tb` (
	`no` INT(11) NOT NULL,
	`capactiy` INT(11) NOT NULL,
	`name` VARCHAR(20) NOT NULL,
	PRIMARY KEY (`no`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
*/
CREATE TABLE `theater_tb` (
	`no` INT(11) NOT NULL,
	`name` VARCHAR(20) NOT NULL,
	`address` VARCHAR(50) NULL DEFAULT NULL,
	`phone` VARCHAR(50) NULL DEFAULT NULL,
	`capacity` INT(11) NULL DEFAULT NULL,
	`description` VARCHAR(100) NULL DEFAULT NULL,
	PRIMARY KEY (`no`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;


/*Schedule Table*/
/*
CREATE TABLE `schedule_tb` (
	`no` INT(11) NOT NULL,
	`date` VARCHAR(10) NOT NULL,
	`time` CHAR(5) NOT NULL,
	`movie_no` INT(11) NOT NULL,
	`theater_no` INT(11) NOT NULL,
	PRIMARY KEY (`no`),
	INDEX `FK__movie_tb` (`movie_no`),
	INDEX `FK__theater_tb` (`theater_no`),
	INDEX `date` (`date`),
	INDEX `time` (`time`),
	CONSTRAINT `FK_schedule_tb_movie_tb` FOREIGN KEY (`movie_no`) REFERENCES `movie_tb` (`no`),
	CONSTRAINT `FK_schedule_tb_theater_tb` FOREIGN KEY (`theater_no`) REFERENCES `theater_tb` (`no`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
*/

CREATE TABLE `schedule_tb2` (
	`no` INT(11) NOT NULL AUTO_INCREMENT,
	`date` VARCHAR(10) NULL DEFAULT NULL,
	`time` CHAR(5) NULL DEFAULT NULL,
	`movie_no` INT(11) NOT NULL,
	`theater_no` INT(11) NOT NULL,
	PRIMARY KEY (`no`),
	UNIQUE INDEX `date_time_theater_no` (`date`, `time`, `theater_no`),
	INDEX `date` (`date`),
	INDEX `time` (`time`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

/* Seat Table */
/*
CREATE TABLE `seat_tb` (
	`theater_no` INT(11) NOT NULL,
	`date` VARCHAR(10) NOT NULL,
	`time` CHAR(5) NOT NULL,
	`seat_no` VARCHAR(10) NOT NULL,
	`state` CHAR(3) NOT NULL DEFAULT 'n',
	INDEX `FK__schedule_tb2` (`theater_no`),
	INDEX `date` (`date`),
	INDEX `time` (`time`),
	CONSTRAINT `seat_tb_ibfk_1` FOREIGN KEY (`theater_no`) REFERENCES `theater_tb` (`no`),
	CONSTRAINT `seat_tb_ibfk_2` FOREIGN KEY (`date`) REFERENCES `schedule_tb2` (`date`),
	CONSTRAINT `seat_tb_ibfk_3` FOREIGN KEY (`time`) REFERENCES `schedule_tb2` (`time`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
*/

CREATE TABLE `seat_tb2` (
	`theater_no` INT(11) NOT NULL,
	`date` VARCHAR(10) NOT NULL,
	`time` CHAR(5) NOT NULL,
	`seat_no` VARCHAR(10) NOT NULL,
	`state` CHAR(3) NOT NULL DEFAULT 'n'
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;




/*Ticket Table*/
CREATE TABLE `ticket_tb` (
	`no` INT(11) NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(60) NOT NULL,
	`theater_name` VARCHAR(20) NOT NULL,
	`date` VARCHAR(10) NOT NULL,
	`time` VARCHAR(10) NOT NULL,
	`seat_no` VARCHAR(10) NOT NULL,
	`price` INT(11) NOT NULL,
	`user_id` VARCHAR(20) NOT NULL,
	PRIMARY KEY (`no`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;