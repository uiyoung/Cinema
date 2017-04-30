/*Cinema_DB*/
create database cinemaDB;

use cinemaDB;

show tables;

create table MEMBER_TB(
no int not null auto_increment,
id varchar(20) not null,
password varchar(200) not null,
name varchar(20) not null,
birthdate char(6) not null,
phone varchar(20) not null,
point int not null,
primary key(id),
key(no)
);

desc MEMBER_TB;

select * from MEMBER_TB;

insert into MEMBER_TB values (null, "zaekiro1","1234","member0","970717","01027839433",5000);

create table MOVIE_TB(
no int not null auto_increment primary key,
title varchar(60) not null,
genre varchar(20) not null,
releaseDate int,
runningTime int,
description varchar(900)
);

desc MOVIE_TB;

Insert into MOVIE_TB VALUES(null, "美女と野獣", "ロマンス", 170421, 130,"進歩的な考え方が原因で、閉鎖的な村人たちとなじめないことに悩む美女ベル（エマ・ワトソン）。ある日、彼女は野獣（ダン・スティーヴンス）と遭遇する。彼は魔女の呪いによって変身させられた王子で、魔女が置いていったバラの花びらが散ってしまう前に誰かを愛し、愛されなければ元の姿に戻ることができない身であった。その恐ろしい外見にたじろぎながらも、野獣に心惹（ひ）かれていくベル。一方の野獣は……。");
Insert into MOVIE_TB VALUES(null, "名探偵コナン　から紅の恋歌（ラブレター）","アニメ",170415,112,"百人一首で有名な皐月会主催の皐月杯の会見収録が行われていた大阪・日売テレビで、爆破事件が起きる。崩壊するビルに高校生探偵の服部平次とその幼なじみ・遠山和葉が取り残されるも、コナンが救い出す。犯行声明が出ないことに疑問を抱いて調べを進めるコナンと平次の前に、平次の婚約者だという百人一首高校生チャンピオンの大岡紅葉が現れる。");
Insert into MOVIE_TB VALUES(null, "LION／ライオン ～25年目のただいま～","ドラマ",170407,119,"インドのスラム街。5歳のサルーは、兄と遊んでいる最中に停車していた電車内に潜り込んで眠ってしまい、そのまま遠くの見知らぬ地へと運ばれて迷子になる。やがて彼は、オーストラリアへ養子に出され、その後25年が経過する。ポッカリと人生に穴があいているような感覚を抱いてきた彼は、それを埋めるためにも本当の自分の家を捜そうと決意。わずかな記憶を手掛かりに、Google Earth を駆使して捜索すると……。");


select * from movie_tb;