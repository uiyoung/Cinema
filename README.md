# Cinema

[cinema wiki](https://github.com/UiyoungSeo/Cinema/wiki)
### How to run Cinema
#### 1. git 주소 복사
`Clone or download->copy to clipboard`

#### 2. eclipse에서 import하기
`eclipse->file->import->Git->Projects from Git->Clone URI`   
URI에 아까 복사한 주소를 입력한다.   
next 쭉 누르다가 finish

#### 3. MySQL 커넥터 연결하기
`Project->Properties->Java Build Path->Libraries->Add External JARs`

#### 4. DB
예전의 cinemaDB 데이터베이스가 존재한다면, `drop database cinemaDB;` db드롭 후에 
[SQL파일](https://github.com/UiyoungSeo/Cinema/blob/master/cinemaDB_create.sql) 열어서 Execute All Queries


---


### [1. 영화정보 입력하기(관리자)](https://github.com/UiyoungSeo/Cinema/tree/master/CinemaManager)

이제 모든 데이터는 관리자프로그램을 이용해서 db에 집어넣습니다. 따라서 관리자 프로그램에서 순서대로 `①극장생성->②영화정보입력->③상영스케줄입력`을 해야 사용자가 예매프로그램에서 예매를 할 수 있습니다.     
`CinemaManger에서 views.login 패키지의 Login.java를 실행합니다.`
#### ①극장 생성
극장관리에서 추가버튼을 누르고 극장번호와 이름을 입력하세요. 극장번호에는 숫자만 넣을 수 있습니다. 나머지는 안써도 등록가능합니다.

#### ②영화정보 입력
영화관리에서 등록버튼을 누르고 사용자에게 보여질 영화에 대한 정보를 입력해주세요.    
지금은 영화제목이 아래와 같을 때만 *(CinemaClient의 images폴더안에 있는 이미지파일)* 예매 프로그램에서 포스터가 보입니다.     
[영화정보입력예시](https://github.com/UiyoungSeo/Cinema/blob/master/CinemaManager/InsertMovieExample.md)
- 美女と野獣
- 名探偵コナン　から紅の恋歌（ラブレター）
- ゴースト・イン・ザ・シェル
- グレートウォール
- LION／ライオン ～25年目のただいま



#### ③ 상영스케줄 입력
스케줄관리에서 등록버튼을 누르고 상영일, 시간, 영화제목, 극장을 선택해주세요. 이 조건에 맞는 스케줄만 사용자 프로그램에서 예매 할 수 있습니다.

---

### [2. 예매하기(사용자)](https://github.com/UiyoungSeo/Cinema/tree/master/CinemaClient)

관리자가 입력한 영화정보를 db에서 불러와 확인하고 영화, 극장, 날짜, 시간, 좌석을 선택하여 티켓을 예매할 수 있습니다.    
 `CinemaClient에서 views.login 패키지의 Login.java를 실행합니다.`   
- 영화예매 : `회원가입->로그인->메뉴->상영영화정보->영화선택->극장선택->날짜선택->시간선택->좌석선택->결재->완료`
- MyTicket: 예매정보확인, 예매취소
- MyAccount : 회원정보수정, 탈퇴
