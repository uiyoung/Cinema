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