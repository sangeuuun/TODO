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

## API 명세
| Command | Method | URL | Response |
| --- | --- | --- | --- |
| 할일카드 등록	| post	| todo | 201 | 
| 카드조회	| get	| /todo/{todoId} | 200 | 
| 카드 전체 조회	| get	| /todos | 200 | 
| 카드 수정	| put	| /todo/{todoId} | 200 | 
| 카드 삭제	| delete	| /todo/{todoId} | 204 | 
| 댓글 작성	| post	| /todo/{todoid}/comments | 201 | 
| 댓글 수정	| put	| /todo/{todoid}/comments/{commentid} | 200 | 
| 댓글 삭제	| delete	| /todo/{todoid}/comments/{commentid} | 204 | 

## ERD

<img width="263" alt="스크린샷 2024-05-24 오전 10 12 59" src="https://github.com/sangeuuun/TODO/assets/151465485/81f39134-8a97-47f5-a101-36ae99c7920b">
