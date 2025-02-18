# 내일배움캠프 뉴스 피드 앱 만들기


## 개발 환경
- IntellJ IDEA Ultimate Edition 2024.3.1.1

- Java 17

- Spring boot 3.4.2
  
- Swagger API docs

- Github

- mysql  Ver 8.0

- git 2.34.1


## 개발자 소개


### 개발자 소개 양식 및 맡은 역할
이름 :
간단 자기 소개 : (한줄)
맡은 역할 :


## 개발자 블로그

    https://strnetwork.tistory.com/

    https://velog.io/@mapleclover/

    velog.io/@seung103/

    https://velog.io/@younghwan314/

    https://seaking110.tistory.com/

## 개발 일정

### 기간 2025.02.14~2025.02.20

02.14 : SA 작성
02.17 : 기능 개발
02.18 : 중간 평가
02.19 : 최종 평가
02.20 : 최종 발표

## S.A

<details>
    <summary>와이어 프레임</summary>
    https://docs.google.com/presentation/d/1PKA2l98Q4vxL55CSmklj0DIe_OZg3hQ5GkSz-7gYjbY/edit#slide=id.g334bd328e72_0_3

![와이어프레임](https://github.com/user-attachments/assets/7a8e2b08-c52d-41b0-b3c6-83bfe2fe013a)

![와이어프레임 (1)](https://github.com/user-attachments/assets/bb72f6b9-03c8-41aa-83d0-beb95526d9e0)

![와이어프레임 (2)](https://github.com/user-attachments/assets/4a6fc8fa-6055-45d7-8fed-f270608350e1)

![와이어프레임 (3)](https://github.com/user-attachments/assets/8486dbe5-f883-45b5-8cf5-0867886c221c)

![와이어프레임 (4)](https://github.com/user-attachments/assets/7e9e5973-c31f-4fef-9ec3-0397bf10fd03)

![와이어프레임 (5)](https://github.com/user-attachments/assets/e1e52296-7f6e-4d09-be4b-9a2dc176f7a5)

![와이어프레임 (6)](https://github.com/user-attachments/assets/07f40608-2fb0-425b-aa3d-6d830066b39b)

![와이어프레임 (7)](https://github.com/user-attachments/assets/f569e00f-be50-44d1-9d28-eeb816af8046)

</details>

<details>
    <summary>ERD</summary>

![image (2)](https://github.com/user-attachments/assets/b89fd291-4a38-4777-860e-0dda6ad410af)

![image (3)](https://github.com/user-attachments/assets/1b6ed701-409e-4485-a0f1-840711b24749)

</details>

<details>
    <summary>API 명세서</summary>


<details>
    <summary>Auth API</summary>
|기능|URL|request|response|COMMENT|정상응답|잘못된 응답|
|---|---|---|---|-------|------|-------|
</details>

</details>

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



