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
CREATE THEATER_TB(
NO INT NOT NULL PRIMARY KEY,
capactiy INT NOT NULL,
NAME varcahr(20) NOT NULL
);

/*Schdule Table*/
CREATE TABLE SCEDULE_TB(	
