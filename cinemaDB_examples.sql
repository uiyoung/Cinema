/*examples*/
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


/* tokyo극장의  좌석번호, 날짜, 예매상태 불러오기 */
select seat_no, date, state 
from theater_tb join seat_tb 
where theater_tb.name="tokyo";

/* 201752일자의 tokyo극장, 14:00시의   좌석번호, 예매상태 불러오기  */
select seat_no, date, state
from theater_tb join seat_tb 
where theater_tb.name='tokyo' and date='201752' and time='14:00';

/* tokyo영화관의 201752일의 14:00시의 A01 좌석의 state를 y로 바꾸기 */
update seat_tb as s join theater_tb as t
on s.theater_no = t.no and t.name='tokyo' and s.date='201752' and s.time='14:00' and s.seat_no='A01'
set s.state='y';




/*Insert member info*/
INSERT INTO MEMBER_TB VALUES (NULL, "user", "1234", "kante", "970717", "01027839433", 500);

/*Insert Theater info*/
INSERT INTO `cinemadb`.`theater_tb` (`no`, `name`) VALUES ('1', 'tokyo');

/*Insert movie info*/
INSERT INTO MOVIE_TB VALUES(NULL, "美女と野獣", "ロマンス", 170421, 130,"進歩的な考え方が原因で、閉鎖的な村人たちとなじめないことに悩む美女ベル（エマ・ワトソン）。ある日、彼女は野獣（ダン・スティーヴンス）と遭遇する。彼は魔女の呪いによって変身させられた王子で、魔女が置いていったバラの花びらが散ってしまう前に誰かを愛し、愛されなければ元の姿に戻ることができない身であった。その恐ろしい外見にたじろぎながらも、野獣に心惹（ひ）かれていくベル。一方の野獣は……。",null,null,null);
INSERT INTO MOVIE_TB VALUES(NULL, "名探偵コナン　から紅の恋歌（ラブレター）","アニメ",170415,112,"百人一首で有名な皐月会主催の皐月杯の会見収録が行われていた大阪・日売テレビで、爆破事件が起きる。崩壊するビルに高校生探偵の服部平次とその幼なじみ・遠山和葉が取り残されるも、コナンが救い出す。犯行声明が出ないことに疑問を抱いて調べを進めるコナンと平次の前に、平次の婚約者だという百人一首高校生チャンピオンの大岡紅葉が現れる。",null,null,null);
INSERT INTO MOVIE_TB VALUES(NULL, "LION／ライオン ～25年目のただいま～","ドラマ",170407,119,"インドのスラム街。5歳のサルーは、兄と遊んでいる最中に停車していた電車内に潜り込んで眠ってしまい、そのまま遠くの見知らぬ地へと運ばれて迷子になる。やがて彼は、オーストラリアへ養子に出され、その後25年が経過する。ポッカリと人生に穴があいているような感覚を抱いてきた彼は、それを埋めるためにも本当の自分の家を捜そうと決意。わずかな記憶を手掛かりに、Google Earth を駆使して捜索すると……。",null,null,null);


/* 상영 일정 삽입 */
INSERT INTO schedule_tb2 values(1,'201752','14:00',1,1);	/*201752, 14:00에 미녀와야수를 tokyo영화관에서 상영한다*/
INSERT INTO schedule_tb2 values(2,'201752','19:00',1,1);
INSERT INTO schedule_tb2 values(3,'201752','22:00',1,1);
INSERT INTO schedule_tb2 values(4,'201752','19:00',1,2);
INSERT INTO schedule_tb2 values(5,'201753','22:00',3,1);


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