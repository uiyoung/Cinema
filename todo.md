- [x] Model, View, Controller 패키지 나누기
- [ ] Bean->DTO, Mgr->DAO

### Reservation
- [x] 영화 선택
- [x] 날짜 선택
- [x] 영화관 선택

### Seats
- [ ] 좌석 여러개 예매 구현
- [ ] 인원수 만큼만 선택가능하게

### Payment
- [x] 결제 UI - 김성령
- [ ] 결제수단 고른 후 나오는 창에 취소 버튼 추가
- [ ] 결제를 해야 예매버튼을 누를 수 있게
- [ ] 비밀번호 텍스트필드 수정
- [ ] 포인트로 결제 하는 기능
- [ ] 결제 후 포인트 적립
- [ ] 스레드 이용해서 결제되는 것 같은 효과

### MyTicket
- [ ] 예약 정보 확인, 예약 취소
- [ ] 회원 정보 수정
- [ ] 회원 탈퇴 
- [ ] 비밀번호 변경   
=> 박우재

### new MainMenu
- [ ] 기존 MainMenu를 영화정보 보기로 바꾸고 새로운 메인메뉴를 만든다.   
로그인->메인메뉴(공지사항, 상영영화 정보, 마이티켓, 종료) flat style 버튼   
- [ ] 메인 공지사항, 현재 상영 영화 수    
=> 김민재

### Sign in
- [ ] id 중복확인
- [ ] id,pw 찾기
- [x] 로그인 후에 영화선택페이지 이동

### Admin App
- [ ] 영화 정보 입력 - movie_tb에 insert, update, delete
- [ ] 상영 schedule 입력 - schedule_tb에 insert, update, delete. 콤보박스 사용하여 날자는 201752 형식으로 입력.
- [ ] 매출 확인, 통계 그래프   
=> 신민정, 이민영

### Calendar
- [ ] 영화를 선택하면 달력에 그 영화가 상영하는 날짜만 선택가능하게 만들기 or 색이 바뀌기
- [ ] 날짜버튼 눌렀을 때 yyMMdd형식으로 날짜 리턴하기

### UI, Graphic Resource
- [ ] Reservations 디자인 수정 [참고](http://hangunsworld.com/blog/1775)
- [ ] Seats(available, sold, selected), screen  [참고](https://w3layouts.com/movie-ticket-booking-widget-flat-responsive-widget-template/)
- [ ] MainMenu SetBounds 정리
- [ ] Reservation 3번째 패널의 예매정보에 JLabel과 JTextField 사용 ex)영화제목:, 날짜:, 영화관:, 시간:, 인원:
- [ ] Reservation.java의 JList 목록 가운데정렬
- [ ] JButton 크기 동일하게
- [ ] 日本語
