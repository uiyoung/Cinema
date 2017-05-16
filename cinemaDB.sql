/*Cinema_DB*/
CREATE DATABASE cinemaDB;

USE cinemaDB;

SHOW TABLES;

/*Member Table*/
CREATE TABLE MEMBER_TB(
NO INT NOT NULL AUTO_INCREMENT,
id VARCHAR(20) NOT NULL,
PASSWORD VARCHAR(200) NOT NULL,
NAME VARCHAR(20) NOT NULL,
birthdate CHAR(6) NOT NULL,
phone VARCHAR(20) NOT NULL,
POINT INT NOT NULL,
PRIMARY KEY(id),
KEY(NO)
);

DESC MEMBER_TB;

SELECT * FROM MEMBER_TB;

/*Insert member info*/
INSERT INTO MEMBER_TB VALUES (NULL, "zaeki", "1234", "member0", "970717", "01027839433", 5000);

/*Movie Table*/
CREATE TABLE MOVIE_TB(
NO INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(60) NOT NULL,
genre VARCHAR(20) NOT NULL,
releaseDate INT,
runningTime INT,
description VARCHAR(900)
);

DESC MOVIE_TB;

/*Insert movie info*/
INSERT INTO MOVIE_TB VALUES(NULL, "美女と野獣", "ロマンス", 170421, 130,"進歩的な考え方が原因で、閉鎖的な村人たちとなじめないことに悩む美女ベル（エマ・ワトソン）。ある日、彼女は野獣（ダン・スティーヴンス）と遭遇する。彼は魔女の呪いによって変身させられた王子で、魔女が置いていったバラの花びらが散ってしまう前に誰かを愛し、愛されなければ元の姿に戻ることができない身であった。その恐ろしい外見にたじろぎながらも、野獣に心惹（ひ）かれていくベル。一方の野獣は……。");
INSERT INTO MOVIE_TB VALUES(NULL, "名探偵コナン　から紅の恋歌（ラブレター）","アニメ",170415,112,"百人一首で有名な皐月会主催の皐月杯の会見収録が行われていた大阪・日売テレビで、爆破事件が起きる。崩壊するビルに高校生探偵の服部平次とその幼なじみ・遠山和葉が取り残されるも、コナンが救い出す。犯行声明が出ないことに疑問を抱いて調べを進めるコナンと平次の前に、平次の婚約者だという百人一首高校生チャンピオンの大岡紅葉が現れる。");
INSERT INTO MOVIE_TB VALUES(NULL, "LION／ライオン ～25年目のただいま～","ドラマ",170407,119,"インドのスラム街。5歳のサルーは、兄と遊んでいる最中に停車していた電車内に潜り込んで眠ってしまい、そのまま遠くの見知らぬ地へと運ばれて迷子になる。やがて彼は、オーストラリアへ養子に出され、その後25年が経過する。ポッカリと人生に穴があいているような感覚を抱いてきた彼は、それを埋めるためにも本当の自分の家を捜そうと決意。わずかな記憶を手掛かりに、Google Earth を駆使して捜索すると……。");

SELECT * FROM movie_tb;

/*Theater Table*/
CREATE TABLE `theater_tb` (
	`no` INT(11) NOT NULL,
	`capactiy` INT(11) NOT NULL,
	`name` VARCHAR(20) NOT NULL,
	PRIMARY KEY (`no`)
);

INSERT INTO THEATER_TB VALUES(1,50,'tokyo');
INSERT INTO THEATER_TB VALUES(2,50,'yokohama');
INSERT INTO THEATER_TB VALUES(3,50,'osaka');

select * from theater_tb;


/*Schdule Table*/
CREATE TABLE `schedule_tb` (
	`no` INT(11) NOT NULL,
	`date` CHAR(6) NOT NULL,
	`time` CHAR(5) NOT NULL,
	`movie_no` INT(11) NOT NULL,
	`theater_no` INT(11) NOT NULL,
	PRIMARY KEY (`no`),
	INDEX `FK__movie_tb` (`movie_no`),
	INDEX `FK__theater_tb` (`theater_no`),
	CONSTRAINT `FK__movie_tb` FOREIGN KEY (`movie_no`) REFERENCES `movie_tb` (`no`),
	CONSTRAINT `FK__theater_tb` FOREIGN KEY (`theater_no`) REFERENCES `theater_tb` (`no`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

/* 상영 일정 삽입 */
INSERT INTO schedule_tb values(1,'201752','14:00',1,1);
INSERT INTO schedule_tb values(2,'201752','19:00',1,2);
INSERT INTO schedule_tb values(3,'201753','22:00',3,1);

select * from SCHEDULE_TB;

/*170502에 영화를 상영하는 극장 찾기*/
select name from theater_tb where no = (select distinct movie_no from schedule_tb where date='170502');

/*170502에 미녀와야수를 상영하는 극장 번호*/
select theater_no from schedule_tb where movie_no = (select no from movie_tb where title='美女と野獣');

/*170502에 미녀와야수를 상영하는 극장 이름, 시간*/
select t.name, s.time
from movie_tb m left join schedule_tb s on m.no = s.movie_no right join theater_tb t on s.theater_no = t.no 
where m.title = '美女と野獣' and s.date = '170502';


select distinct movie_tb.title, schedule_tb.date
from movie_tb left join schedule_tb
on movie_tb.no = schedule_tb.movie_no;


/* SEAT table */
CREATE TABLE `seat_tb` (
	`theater_no` INT(11) NOT NULL,
	`seat_no` VARCHAR(10) NOT NULL,
	`state` CHAR(3) NOT NULL DEFAULT 'n',	/*예매 여부*/
	PRIMARY KEY (`seat_no`, `theater_no`),
	INDEX `FK__seat_theater_tb` (`theater_no`),
	CONSTRAINT `FK__seate_theater_tb` FOREIGN KEY (`theater_no`) REFERENCES `theater_tb` (`no`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

/* 좌석 삽입 */
INSERT INTO `cinemadb`.`seat_tb` (`theater_no`, `seat_no`) VALUES ('1', 'A01');

select * from seat_tb;

