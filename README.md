# 내일배움캠프 뉴스 피드 앱 만들기


## 개발 환경
- IntellJ IDEA Ultimate Edition 2024.3.1.1

- Java 17

- Spring boot 3.4.2

- Github

- mysql  Ver 8.0.41

- git 2.34.1


## 개발자 블로그

    https://strnetwork.tistory.com/

    https://velog.io/@mapleclover/

    velog.io/@seung103/

    https://velog.io/@younghwan314/

    https://seaking110.tistory.com/
    
### ERD

![image (2)](https://github.com/user-attachments/assets/b89fd291-4a38-4777-860e-0dda6ad410af)

![image (3)](https://github.com/user-attachments/assets/1b6ed701-409e-4485-a0f1-840711b24749)



### API 명세서

|기능|URL|request|response|COMMENT|정상응답|잘못된 응답|
|---|---|---|---|-------|------|-------|


## 환경 변수

    DB_USER={your DB Id)
    DB_PASSWORD=(your DB password)
    DB_URL=jdbc:mysql://{your DB URL)
    PORT={your port}


## 프로그램 사용법

    git clone https://github.com/PK7NewsFeed/NewsFeed.git

프로젝트를 복사해주세요.

    ./ScheduleProject/src/main/resources/.env
    
.db.env 환경 변수 내용을 추가를 하고 서버에 환경변수 설정을 해주신 다음에

    ./ScheduleProject/src/main/java/xyz/tomorrowlearncamp/newsfeed/NewsFeedApplication.java
    
NewsFeedApplication.java에 있는 main 을 실행시키면 됩니다.



