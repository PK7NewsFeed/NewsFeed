# Introduce PRIMARY KEY 7
## 개발자 소개
- 팀장 : 이은성
  - 프로젝트 후기 :
  - 맡은 역할 : 공통 예외 처리, 인증/인가, 로그인 필터, 발표/발표자료 준비
  - 블로그 : https://strnetwork.tistory.com/
  - Github : https://github.com/mixedsider
- 팀원 : 전승민
  - 프로젝트 후기 :
  - 맡은 역할 : 댓글 CRUD , 댓글 좋아요 기능
  - 블로그 : velog.io/@seung103/
  - Github : https://github.com/Seungmin-J
- 팀원 : 유은호
  - 프로젝트 후기 :
  - 맡은 역할 : 친구 신청, 친구 신청 목록 관리
  - 블로그 : https://velog.io/@mapleclover/
  - Github : https://github.com/mapleclover
- 팀원 : 윤현호
  - 프로젝트 후기 :
  - 맡은 역할 : 사용자 CRUD
  - 블로그 : https://seaking110.tistory.com/
  - Github : https://github.com/seaking110
- 팀원 : 전영환
  - 프로젝트 후기 :
  - 맡은 역할 : 게시글(피드) CRUD
  - 블로그 : https://velog.io/@younghwan314/
  - Github : https://github.com/younghwan314

    
## 제작 기간 2025.02.14~2025.02.20

- 02.14 : SA 작성

- 02.17 : 기능 개발

- 02.18 : 중간 평가, 추가 기능 추가

- 02.19 : 최종 평가

- 02.20 : 최종 발표

---

# 목차
1. [개요](#-개요)
2. [기능](##-기능)
3. [기술 스택](##-기술-스택)
4. [와이어프레임](##-S.A)
5. [ERD](##-S.A)
6. [API 명세서](##-S.A)
7. [설치 및 실행 방법](##-설치-및-실행-방법)

---

# 개요
<h2> 뉴스피드란? </h2>
친구들의 가장 최근에 업데이트된 게시물들을 볼 수 있는 페이지<br>
SNS의 주요 기능을 메인으로하는 REST API. <br>

---

## 기능

- **핵심 기능 1**: 뉴스피드 게시물 관리 및 정렬, 좋아요
- **핵심 기능 2**: 댓글, 대댓글 기능
- **핵심 기능 3**: 유저 프로필 관리 기능
- **핵심 기능 4**: 사용자 인증 (JWT)
- **핵심 기능 5**: 친구 관리 기능

---

## 기술 스택

- **프론트엔드**: Postman, Rest API
- **백엔드**: Spring boot 3.4.2
- **데이터베이스**: Mysql ver 8.0
- **기타**: GitHub, git 2.34.1, java 17

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
    <summary>Users 명세서</summary>

| API 명 | HTTP Method | Endpoint | 요청 방식 | 설명 | 응답 코드 | 응답 데이터 |
|--------|------------|----------|-----------|------|-----------|-------------|
| (본인) 유저 조회 | GET | /users/myInfo | JWT | (본인) 유저 프로필 정보 | 401: Unauthorized <br> 200 OK | `{ "id": 1, "userName": "윤현호", "email": "email@email.com", "gender": "남성", "dateOfBirth": "2000-10-10", "createdAt": "2025-01-25 00:00:00", "modifiedAt": "2025-01-25 00:00:00" }` |
| (다른 사람) 유저 조회 | GET | /users/{userId} | 요청 param | (다른 사람) 유저 프로필 정보 | 404: NOT FOUND <br> 200 OK | `{ "id": 1, "userName": "윤현호", "email": "email@email.com", "gender": "남성", "dateOfBirth": "2000-10-10", "createdAt": "2025-01-25 00:00:00", "modifiedAt": "2025-01-25 00:00:00" }` |
| 전체 유저 조회 | GET | /users | - | 유저 프로필 정보 리스트 | 200 OK | `[ { "id": 1, "userName": "윤현호", "email": "email@email.com", "gender": "남성", "dateOfBirth": "2000-10-10", "createdAt": "2025-01-25 00:00:00", "modifiedAt": "2025-01-25 00:00:00" }, ... ]` |
| (본인) 유저 프로필 수정 | PATCH | /users | JWT, body | (본인) 유저 프로필 수정 정보 | 401: Unauthorized <br> 200 OK | 요청 데이터: `{ "userName": "윤현호", "email": "email@email.com", "password": "qwer1234!", "gender": "남성", "birthDate": "2000-10-10" }` <br> 응답 데이터: `{ "id": 1, "userName": "윤현호", "email": "email@email.com", "gender": "남성", "birthDate": "2000-10-10", "createdAt": "2025-01-25 00:00:00", "modifiedAt": "2025-01-25 00:00:00" }` |
| (본인) 유저 비밀번호 수정 | PATCH | /users/password | JWT, body | - | 401: Unauthorized <br> 200 OK | `{ "oldPassword": "qwer1234!", "newPassword": "asdf5678$", "newPasswordCheck": "asdf5678$" }` |
| (본인) 유저 삭제 (회원 탈퇴) | POST | /users/delete | JWT, body | - | 401: Unauthorized <br> 200 OK | `{ "password": "Seojiwon123!" }` |

</details>

<details>
  <summary>Auth 명세서</summary>

| API 명 | HTTP Method | Endpoint | 요청 방식 | 설명 | 응답 코드 | 응답 데이터 |
|--------|------------|----------|-----------|------|-----------|-------------|
| 유저 등록 (회원가입) | POST | /auth/signup | RequestBody | 회원가입 | 400: 이메일 형식 유효성 위반 <br> 400: 필수 값 미입력 <br> 201: 정상등록 | 요청 데이터: `{ "username": "nameValue", "email": "email@email.com", "password": "passwordValue", "gender": "MALE" }` <br> 응답 데이터: `{ "id": 1, "username": "nameValue", "email": "email@email.com" }` |
| 로그인 | POST | /auth/login | RequestBody | 로그인 처리 | 400: 이메일 형식 유효성 위반 <br> 400: 필수 값 미입력 <br> 401: 아이디 및 비밀번호 불일치 <br> 404: 존재하지 않는 id <br> 200: 정상조회 | 요청 데이터: `{ "email": "email@email.com", "password": "passwordValue" }` <br> 응답 데이터: `{ "id": 1, "username": "nameValue", "email": "email@email.com" }` |
| 로그아웃 | POST | /auth/logout | JWT {Const.LOGIN_USER} | 로그아웃 처리 | 400: 이미 로그아웃된 유저 <br> 200: 정상조회 | `"success"` |

</details>

<details>
  <summary>NewsFeed/NewsFeedLikes 명세서</summary>

  | API 명 | HTTP Method | Endpoint | 요청 방식 | 설명 | 응답 코드 | 응답 데이터 |
|--------|------------|----------|-----------|------|-----------|-------------|
| 피드 등록 | POST | /posts | RequestBody | 피드 등록 | 400: 필수 값 미입력 <br> 401: 로그인 필요 <br> 200: 정상등록 | 요청 데이터: `{ "title": "titleValue", "content": "contentValue", "image_url": "image_url" }` <br> 응답 데이터: `{ "id": 1, "userName": "userNameValue", "title": "제목", "content": "내용", "image_url": "이미지주소", "createdAt": "2025-01-25 00:00:00", "modifiedAt": "2025-01-25 00:00:00" }` |
| 피드 전체 조회 | GET | /posts | 요청 Param | 등록된 피드 정보, 좋아요 누른 피드 정보 | 200: 정상조회 | `[ { "id": 1, "userName": "userNameValue", "title": "제목", "content": "내용", "image_url": "이미지주소", "createdAt": "2025-01-25 00:00:00", "modifiedAt": "2025-01-25 00:00:00" }, ... ]` |
| 피드 단건 조회 | GET | /posts/{postId} | 요청 Path | 피드 단건 정보 | 404: 존재하지 않는 id <br> 200: 정상조회 | `{ "id": 1, "userName": "userNameValue", "title": "제목", "content": "내용", "image_url": "이미지주소", "createdAt": "2025-01-25 00:00:00", "modifiedAt": "2025-01-25 00:00:00" }` |
| 피드 수정 | PATCH | /posts/{postId} | 요청 Path, body | 피드 수정 | 400: 필수 값 미입력 <br> 401: 로그인 필요 <br> 404: 존재하지 않는 id <br> 200: 정상수정 | 요청 데이터: `{ "title": "수정된 제목", "content": "수정된 내용", "image_url": "이미지주소" }` <br> 응답 데이터: `{ "id": 1, "userName": "userNameValue", "title": "수정된 제목", "content": "수정된 내용", "image_url": "이미지주소", "createdAt": "2025-01-25 00:00:00", "modifiedAt": "2025-01-25 00:00:00" }` |
| 피드 삭제 | DELETE | /posts/{postId} | 요청 Path, body | 피드 삭제 | 401: 로그인 필요 <br> 401: 올바르지 않은 password <br> 404: 존재하지 않는 id <br> 200: 정상삭제 | 요청 데이터: `{ "password": "passwordValue" }` |

| API 명 | HTTP Method | Endpoint | 요청 방식 | 설명 | 응답 코드 | 응답 데이터 |
|--------|------------|----------|-----------|------|-----------|-------------|
| 피드 좋아요 | POST | /posts/{postId}/like | 요청 Param | 피드 좋아요 등록 | 200: 정상등록 | 요청 데이터: `{ "postId": 1 }` <br> 응답 데이터: `{ "id": 1, "userName": "userNameValue", "title": "제목", "content": "내용", "image_url": "이미지주소", "createdAt": "2025-01-25 00:00:00" }` |

</details>

<details>
<summary>Comment 명세서</summary>

  | API 명 | HTTP Method | Endpoint | 요청 방식 | 설명 | 응답 코드 | 응답 데이터 |
|--------|------------|----------|-----------|------|-----------|-------------|
| 댓글 등록 | POST | /comments | RequestBody | 댓글 등록 | 400: 필수 값 미입력 <br> 200: 정상등록 | 요청 데이터: `{ "content": "댓글 내용", "userId": 1, "newsFeedId": 1, "depth": 0, "parentCommentId": 3 }` <br> 응답 데이터: `{ "id": 1, "content": "댓글 내용", "userId": 1, "newsFeedId": 1, "depth": 0, "parentCommentId": 3, "createdAt": "2025-01-25 00:00:00", "modifiedAt": "2025-01-25 00:00:00" }` |
| 댓글 조회 | GET | /comments | 요청 Param | 댓글 리스트 조회 | 200: 정상조회 | `[ { "id": 1, "content": "댓글 내용", "userId": 1, "newsFeedId": 1, "depth": 0, "parentCommentId": 3, "createdAt": "2025-01-25 00:00:00", "modifiedAt": "2025-01-25 00:00:00" }, ... ]` |
| 댓글 수정 | PUT | /comments/{commentId} | 요청 PathVariable, JWT, body | 댓글 수정 | 401: 권한 없음 <br> 404: 존재하지 않는 id <br> 200: 정상수정 | 요청 데이터: `{ "id": 1, "content": "댓글 수정", "userId": 1, "newsFeedId": 1, "depth": 0, "parentCommentId": 3 }` <br> 응답 데이터: `{ "id": 1, "content": "댓글 수정", "userId": 1, "newsFeedId": 1, "depth": 0, "parentCommentId": 3, "createdAt": "2025-01-25 00:00:00", "modifiedAt": "2025-01-25 00:00:00" }` |
| 댓글 삭제 | DELETE | /comments/{commentId} | 요청 PathVariable, JWT | 댓글 삭제 | 401: 권한 없음 <br> 404: 존재하지 않는 id <br> 200: 정상삭제 | - |

| API 명 | HTTP Method | Endpoint | 요청 방식 | 설명 | 응답 코드 | 응답 데이터 |
|--------|------------|----------|-----------|------|-----------|-------------|
| 댓글 좋아요 | POST | /comments/{commentId}/like | 요청 Body | 댓글 좋아요 등록 | 200: 정상등록 | 요청 데이터: `{ "userId": 1, "commentId": 2 }` |


</details>

<details>
  <summary>Friend 명세서</summary>

  | API 명 | HTTP Method | Endpoint | 요청 방식 | 설명 | 응답 코드 | 응답 데이터 |
|--------|------------|----------|-----------|------|-----------|-------------|
| 친구 신청 | POST | /friends | 요청 Param | 친구 신청 요청 | 404: 존재하지 않는 id <br> 200: 정상처리 | - |
| 친구 신청 조회 | GET | /friends | 요청 Param | 친구 신청 목록 및 친구 목록 조회 | 404: 유효성 위반 <br> 200: 정상조회 | `/friends?status=WAITING` (받은 요청 조회) <br> `/friends?status=ACCEPTED` (친구 목록 조회) <br> 응답 데이터: `[ { "id": 1, "username": "아무개", "email": "example@example.com" }, ... ]` |
| 친구 삭제 | DELETE | /friends | 요청 Param | 친구 삭제 | 404: 존재하지 않는 id <br> 200: 정상삭제 | - |

</details>

</details>



## 설치 및 실행 방법

```bash
git clone https://github.com/PK7NewsFeed/NewsFeed.git
cd your-repo
```
프로젝트를 복사해주세요.

    ./ScheduleProject/src/main/resources/.env

```
DB_URL=jdbc:mysql://{your_DB}
DB_USER={your_User}
DB_PASSWORD={your_Password}
PORT={your_Port}
```
  
  .env 파일에 위와 같이 환경 변수를 선언을 해주세요.


```bash
chmod u+x gradlew
./gradlew build
```
./ScheduleProject 위치에서 다음 명령어를 실행을 해주세요.

build success 가 나오면
./ScheduleProject/build/libs/{your_file_name}-SNAPSHOT.jar 실행시켜주시면 됩니다.

```bash
java -jar {your_file_name}-SNAPSHOT.jar
```



