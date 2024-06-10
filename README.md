# TODO
todo app backend server
<br>
<br>

## 구현 기능
- 할일카드 작성
- 선택한 할 일 조회
- 할 일 카드 목록 조회
- 선택한 할 일 수정
- 선택한 할 일 삭제
- 선택한 할 일 카드 완료
<br>

- 댓글 작성
- 댓글 수정
- 댓글 삭제
<br>

- 회원가입
- 로그인


## API 명세
| Command | Method | URL | Response |
| --- | --- | --- | --- |
| 할일카드 등록	| post	| todos | 201 | 
| 카드조회	| get	| /todos/{todoId} | 200 | 
| 카드 전체 조회	| get	| /todos | 200 | 
| 카드 수정	| put	| /todos/{todoId} | 200 | 
| 카드 삭제	| delete	| /todos/{todoId} | 204 | 
| 카드 상태 변경 | patch | /todos/{todoId}?status= | 200 |
| 댓글 작성	| post	| /todos/{todoid}/comments | 201 | 
| 댓글 수정	| put	| /todos/{todoid}/comments/{commentid} | 200 | 
| 댓글 삭제	| delete	| /todos/{todoid}/comments/{commentid} | 204 | 
| 회원가입	| post	| /signUp | 201 | 
| 로그인	| post	| /login | 200 | 

## ERD

<img width="592" alt="스크린샷 2024-06-10 오전 11 36 29" src="https://github.com/sangeuuun/TODO/assets/151465485/55c1536a-4eaf-460d-92c4-ae52bafdffdf">
