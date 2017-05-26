/*Cinema_DB*/
CREATE DATABASE cinemaDB;
USE cinemaDB;

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

/*Insert member info*/
INSERT INTO MEMBER_TB VALUES (NULL, "user", "1234", "kante", "970717", "01027839433", 5000);


/*Movie Table*/
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


/*Insert movie info*/
INSERT INTO MOVIE_TB VALUES(NULL, "美女と野獣", "ロマンス", 170421, 130,"進歩的な考え方が原因で、閉鎖的な村人たちとなじめないことに悩む美女ベル（エマ・ワトソン）。ある日、彼女は野獣（ダン・スティーヴンス）と遭遇する。彼は魔女の呪いによって変身させられた王子で、魔女が置いていったバラの花びらが散ってしまう前に誰かを愛し、愛されなければ元の姿に戻ることができない身であった。その恐ろしい外見にたじろぎながらも、野獣に心惹（ひ）かれていくベル。一方の野獣は……。",null,null,null);
INSERT INTO MOVIE_TB VALUES(NULL, "名探偵コナン　から紅の恋歌（ラブレター）","アニメ",170415,112,"百人一首で有名な皐月会主催の皐月杯の会見収録が行われていた大阪・日売テレビで、爆破事件が起きる。崩壊するビルに高校生探偵の服部平次とその幼なじみ・遠山和葉が取り残されるも、コナンが救い出す。犯行声明が出ないことに疑問を抱いて調べを進めるコナンと平次の前に、平次の婚約者だという百人一首高校生チャンピオンの大岡紅葉が現れる。",null,null,null);
INSERT INTO MOVIE_TB VALUES(NULL, "LION／ライオン ～25年目のただいま～","ドラマ",170407,119,"インドのスラム街。5歳のサルーは、兄と遊んでいる最中に停車していた電車内に潜り込んで眠ってしまい、そのまま遠くの見知らぬ地へと運ばれて迷子になる。やがて彼は、オーストラリアへ養子に出され、その後25年が経過する。ポッカリと人生に穴があいているような感覚を抱いてきた彼は、それを埋めるためにも本当の自分の家を捜そうと決意。わずかな記憶を手掛かりに、Google Earth を駆使して捜索すると……。",null,null,null);

/*Theater Table*/
CREATE TABLE `theater_tb` (
	`no` INT(11) NOT NULL,
	`capactiy` INT(11) NOT NULL,
	`name` VARCHAR(20) NOT NULL,
	PRIMARY KEY (`no`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

/*Insert Theater values*/
INSERT INTO THEATER_TB VALUES(1,50,'tokyo');
INSERT INTO THEATER_TB VALUES(2,50,'yokohama');
INSERT INTO THEATER_TB VALUES(3,50,'osaka');


/*Schedule Table*/
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

/* 상영 일정 삽입 */
INSERT INTO schedule_tb values(1,'201752','14:00',1,1);	/*201752, 14:00에 미녀와야수를 tokyo영화관에서 상영한다*/
INSERT INTO schedule_tb values(2,'201752','19:00',1,1);
INSERT INTO schedule_tb values(3,'201752','22:00',1,1);
INSERT INTO schedule_tb values(4,'201752','19:00',1,2);
INSERT INTO schedule_tb values(5,'201753','22:00',3,1);

/* Seat Table */
CREATE TABLE `seat_tb` (
	`theater_no` INT(11) NOT NULL,
	`date` VARCHAR(10) NOT NULL,
	`time` CHAR(5) NOT NULL,
	`seat_no` VARCHAR(10) NOT NULL,
	`state` CHAR(3) NOT NULL DEFAULT 'n',
	PRIMARY KEY (`theater_no`, `seat_no`),
	INDEX `FK__schedule_tb` (`theater_no`),
	INDEX `date` (`date`),
	INDEX `time` (`time`),
	INDEX `seat_no` (`seat_no`),
	CONSTRAINT `seat_tb_ibfk_1` FOREIGN KEY (`theater_no`) REFERENCES `theater_tb` (`no`),
	CONSTRAINT `seat_tb_ibfk_2` FOREIGN KEY (`date`) REFERENCES `schedule_tb` (`date`),
	CONSTRAINT `seat_tb_ibfk_3` FOREIGN KEY (`time`) REFERENCES `schedule_tb` (`time`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

/* 좌석정보 입력*/
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'A01', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'A02', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'A03', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'A04', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'A05', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'A06', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'A07', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'A08', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'A09', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'A10', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'B01', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'B02', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'B03', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'B04', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'B05', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'B06', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'B07', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'B08', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'B09', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'B10', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'C01', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'C02', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'C03', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'C04', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'C05', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'C06', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'C07', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'C08', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'C09', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'C10', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'D01', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'D02', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'D03', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'D04', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'D05', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'D06', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'D07', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'D08', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'D09', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'D10', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'E01', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'E02', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'E03', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'E04', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'E05', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'E06', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'E07', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'E08', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'E09', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'E10', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'F01', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'F02', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'F03', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'F04', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'F05', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'F06', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'F07', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'F08', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'F09', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'F10', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'G01', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'G02', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'G03', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'G04', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'G05', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'G06', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'G07', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'G08', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'G09', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'G10', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'H01', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'H02', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'H03', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'H04', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'H05', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'H06', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'H07', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'H08', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'H09', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'H10', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'I01', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'I02', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'I03', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'I04', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'I05', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'I06', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'I07', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'I08', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'I09', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'I10', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'J01', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'J02', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'J03', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'J04', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'J05', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'J06', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'J07', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'J08', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'J09', '201752', '14:00');
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`, `date`, `time`) VALUES ('1', 'J10', '201752', '14:00');


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
AUTO_INCREMENT=6
;