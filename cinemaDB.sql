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
INSERT INTO MEMBER_TB VALUES (NULL, "user", "1234", "캉테", "970717", "01027839433", 5000);


/*Movie Table*/
CREATE TABLE `movie_tb` (
	`no` INT(11) NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(60) NOT NULL,
	`genre` VARCHAR(20) NOT NULL,
	`releaseDate` INT(11) NULL DEFAULT NULL,
	`runningTime` INT(11) NULL DEFAULT NULL,
	`description` VARCHAR(900) NULL DEFAULT NULL,
	PRIMARY KEY (`no`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

/*Insert movie info*/
INSERT INTO MOVIE_TB VALUES(NULL, "美女と野獣", "ロマンス", 170421, 130,"進歩的な考え方が原因で、閉鎖的な村人たちとなじめないことに悩む美女ベル（エマ・ワトソン）。ある日、彼女は野獣（ダン・スティーヴンス）と遭遇する。彼は魔女の呪いによって変身させられた王子で、魔女が置いていったバラの花びらが散ってしまう前に誰かを愛し、愛されなければ元の姿に戻ることができない身であった。その恐ろしい外見にたじろぎながらも、野獣に心惹（ひ）かれていくベル。一方の野獣は……。");
INSERT INTO MOVIE_TB VALUES(NULL, "名探偵コナン　から紅の恋歌（ラブレター）","アニメ",170415,112,"百人一首で有名な皐月会主催の皐月杯の会見収録が行われていた大阪・日売テレビで、爆破事件が起きる。崩壊するビルに高校生探偵の服部平次とその幼なじみ・遠山和葉が取り残されるも、コナンが救い出す。犯行声明が出ないことに疑問を抱いて調べを進めるコナンと平次の前に、平次の婚約者だという百人一首高校生チャンピオンの大岡紅葉が現れる。");
INSERT INTO MOVIE_TB VALUES(NULL, "LION／ライオン ～25年目のただいま～","ドラマ",170407,119,"インドのスラム街。5歳のサルーは、兄と遊んでいる最中に停車していた電車内に潜り込んで眠ってしまい、そのまま遠くの見知らぬ地へと運ばれて迷子になる。やがて彼は、オーストラリアへ養子に出され、その後25年が経過する。ポッカリと人生に穴があいているような感覚を抱いてきた彼は、それを埋めるためにも本当の自分の家を捜そうと決意。わずかな記憶を手掛かりに、Google Earth を駆使して捜索すると……。");


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

/*exampes*/
/*201752에 영화를 상영하는 극장 찾기*/
select name from theater_tb where no = (select distinct movie_no from schedule_tb where date='201752');

/*201752에 미녀와야수를 상영하는 극장 번호*/
select theater_no from schedule_tb where movie_no = (select no from movie_tb where title='美女と野獣');

/*201752에 미녀와야수를 상영하는 극장 이름, 시간*/
select t.name, s.time
from movie_tb m left join schedule_tb s on m.no = s.movie_no right join theater_tb t on s.theater_no = t.no 
where m.title = '美女と野獣' and s.date = '201752';

/* 미녀와야수를 상영하고 있는 극장 불러오기 */
select t.name
from movie_tb m left join schedule_tb s on m.no = s.movie_no right join theater_tb t on s.theater_no = t.no 
where m.title = '美女と野獣';

/* 美女と野獣영화를 상영하는 tokyo 극장의 201752 일자의 상영시간을 불러오기 */
select time
from movie_tb m left join schedule_tb s on m.no = s.movie_no right join theater_tb t on s.theater_no = t.no 
where m.title = '美女と野獣' and t.name='tokyo' and s.date = '201752' ;


/* Seat Table */
CREATE TABLE `seat_tb` (
	`theater_no` INT(11) NOT NULL,
	`seat_no` VARCHAR(10) NOT NULL,
	`date` VARCHAR(10) NOT NULL,
	`time` CHAR(5) NOT NULL,
	`state` CHAR(3) NOT NULL DEFAULT 'n',
	PRIMARY KEY (`theater_no`, `seat_no`),
	INDEX `FK__schedule_tb` (`theater_no`),
	INDEX `date` (`date`),
	INDEX `time` (`time`),
	CONSTRAINT `seat_tb_ibfk_1` FOREIGN KEY (`theater_no`) REFERENCES `theater_tb` (`no`),
	CONSTRAINT `seat_tb_ibfk_2` FOREIGN KEY (`date`) REFERENCES `schedule_tb` (`date`),
	CONSTRAINT `seat_tb_ibfk_3` FOREIGN KEY (`time`) REFERENCES `schedule_tb` (`time`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;




/* 좌석정보 삽입 */
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

/* TODO: 201752 일자의 tokyo, 14:00 스케쥴의 영화관 좌석정보 불러오기 */

/* tokyo극장의  좌석번호, 날짜, 예매상태 불러오기 */
select seat_no, date, state 
from theater_tb join seat_tb 
where theater_tb.name="tokyo";

/* TODO : 201752일자의 14:00시 tokyo극장의  좌석번호, 예매상태 불러오기  */
select seat_no, date, state
from theater_tb join seat_tb 
where theater_tb.name='tokyo' and date='201752' and time='14:00';
