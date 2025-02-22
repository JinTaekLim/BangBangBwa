# BangBangBwa 란?

> 사용자 간 맞춤 방송인/게시글 정보를 제공해 방송인에게는 특정 다수의 시청자에게 홍보의 기회를,
시청자에게는 새로운 방송인과 소통할 수 있는 기회를 제공하는 WEB 사이트입니다.

- 기간 : 2024.10 ~ 2024.12
- 구성 : FE 2, BE 3, Designer 1
- [기획/UI](https://www.figma.com/design/s0tc9LNMOzS30XVk683QcN/%5B-BBB-%5D?node-id=0-1&p=f&t=gTTeJfWMeYFeb27l-0)

## 기술 스택
- Backend
  - Java, Spring Boot, Spring Security, MyBatis, JWT, Swagger
- DB
  - MySQL, Redis
- DevOps / Infra
  - AWS EC2, Docker, GitHub Actions, AWS ALB

## 아키텍처
![BangBangBwa-Architecture drawio](https://github.com/user-attachments/assets/366d3b25-bc1e-4c58-8284-d9039b6b75c9)

## 게시글 플로우차트
![게시글 플로우차트 drawio](https://github.com/user-attachments/assets/e0c82770-55ed-4d25-97fe-aa1348b15698)


## 트러블슈팅

1. **Jenkins 기반 CI/CD 구동 중 메모리 부족으로 서버 다운 및 서버 응답 지연 발생**

   - CI/CD 툴을 Jenkins → GitHub Actions로 변경하여 서버 부담 감소
   - 초기 설정 기준, CI/CD 완료 시간 평균 30초 이상 시간 단축


2.  **SQL 로깅 중 특수 문자 포함 요청 처리 오류 발생**

    - 테스트 코드에서 객체의 값을 한국어, 영어, 특수 문자 등 모든 허용 가능한 데이터로 랜덤 입력하도록 개선
    - 제약 조건을 위반하지 않는 모든 요청에 대한 정상 동작을 보장


3.  **FixtureMonkey 사용을 위해 어노테이션을 강제로 사용해야하는 문제 발생**

    - 엔티티의 제약 조건을 어노테이션 대신 DB 메타데이터에서 읽어오도록 구현
    - 관리 포인트를 schema.sql 하나로 줄여 유지보수를 용이하게 함


4. **사용 가능한 닉네임이 감소할 수록 닉네임 랜덤 생성 반환 시간이 증가하는 문제 발생**

   - 기존에 API 호출마다 중복되지 않는 랜덤 닉네임을 생성하는 방식 -> 미리 랜덤 닉네임을 조합해 Redis에 저장하는 방식으로 변경
   - 반환 속도가 일정해지고 전체적인 반환 속도 감소


5. **S3 미디어 업로드 시간으로 인한 게시물 작성 시간 저하**

   - 미디어 업로드와 게시글 작성 API를 분리해 제공
   - 리액트의 비동기 방식을 활용해 게시글 작성 및 사용자가 경험하는 대기 시간 감소
