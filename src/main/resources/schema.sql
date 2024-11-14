DROP TABLE IF EXISTS pending_streamer;
DROP TABLE IF EXISTS admins;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS posts_private_members;
DROP TABLE IF EXISTS posts_public_members;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS tokens;
DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS banners;

CREATE TABLE members
(
    id         BIGINT AUTO_INCREMENT NOT NULL COMMENT '멤버_ID',
    sns_type   VARCHAR(100) NOT NULL COMMENT 'GOOGLE, KAKAO , NAVER',
    sns_id     VARCHAR(255) NOT NULL COMMENT 'SNS 고유 ID',
    email      VARCHAR(255) NOT NULL COMMENT 'SNS 로그인시 받아오는 이메일 값',
    profile    VARCHAR(255) NULL COMMENT 'storage URL',
    nickname   VARCHAR(255) NOT NULL COMMENT '닉네임(null시 랜덤값 생성)',
    role       VARCHAR(20)  NOT NULL COMMENT '회원 권한',
    deleted_at DATETIME NULL COMMENT '탈퇴일시(null)',
    created_at DATETIME     NOT NULL COMMENT '생성일시',
    created_id VARCHAR(255) NOT NULL COMMENT '생성자',
    updated_id VARCHAR(255) NULL COMMENT '수정자(null)',
    updated_at DATETIME NULL COMMENT '수정일시(null)',
    PRIMARY KEY (id)
);


CREATE TABLE tags
(
    id         BIGINT AUTO_INCREMENT NOT NULL COMMENT '태그_ID',
    name       VARCHAR(30)  NOT NULL COMMENT '태그명',
    created_at DATETIME     NOT NULL COMMENT '생성일시',
    created_id VARCHAR(255) NOT NULL COMMENT '생성자',
    updated_id VARCHAR(255) NULL COMMENT '수정자(null)',
    updated_at DATETIME NULL COMMENT '수정일시(null)',
    PRIMARY KEY (id)
);

CREATE TABLE tokens
(
    id              BIGINT AUTO_INCREMENT NOT NULL COMMENT '토큰_ID',
    member_id       BIGINT              NOT NULL COMMENT '멤버_ID',
    refresh_token   VARCHAR(255) UNIQUE NOT NULL COMMENT '리프레쉬_토큰',
    expiration_time DATETIME            NOT NULL COMMENT '만료_시간',
    PRIMARY KEY (id),
    FOREIGN KEY (member_id) REFERENCES members (id)
);

CREATE TABLE banners
(
    id       BIGINT AUTO_INCREMENT NOT NULL COMMENT '배너_ID',
    url      LONGTEXT   NOT NULL COMMENT '배너_이미지_URL',
    bg_color VARCHAR(8) NOT NULL COMMENT '배너_배경색',
    PRIMARY KEY (id)
);

CREATE TABLE posts
(
    id          BIGINT AUTO_INCREMENT   NOT NULL COMMENT '게시글_ID',
    member_id   BIGINT                  NOT NULL COMMENT '작성자_ID',
    post_type   VARCHAR(100)            NULL     COMMENT 'MEMBER, STREAMER',
    status      VARCHAR(100)            NOT NULL COMMENT 'DRAFT, PUBLISHED, DELETED',
    title       VARCHAR(100)                     COMMENT '제목',
    content     VARCHAR(4000)                    COMMENT '내용',
    created_at  DATETIME                NOT NULL COMMENT '생성 일시',
    created_id  VARCHAR(255)            NOT NULL COMMENT '생성자',
    updated_id  VARCHAR(255)            NULL     COMMENT '수정자(null)',
    updated_at  DATETIME                NULL     COMMENT '수정 일시(null)',
    deleted_at  DATETIME                NULL COMMENT '삭제 일시(null)',
    PRIMARY KEY (id),
    FOREIGN KEY (member_id) REFERENCES members (id)
);

CREATE TABLE posts_public_members
(
    id          BIGINT AUTO_INCREMENT   NOT NULL COMMENT '게시글 공개 멤버 ID',
    member_id   BIGINT                  NOT NULL COMMENT '멤버 ID',
    post_id     BIGINT                  NOT NULL COMMENT '게시물 ID',
    created_at  DATETIME                NOT NULL COMMENT '생성 일시',
    created_id  VARCHAR(255)            NOT NULL COMMENT '생성자',
    PRIMARY KEY (id),
    FOREIGN KEY (member_id) REFERENCES members (id),
    FOREIGN KEY (post_id) REFERENCES posts (id)
);

CREATE TABLE posts_private_members
(
    id          BIGINT AUTO_INCREMENT   NOT NULL COMMENT '게시글 비공개 멤버 ID',
    member_id   BIGINT                  NOT NULL COMMENT '멤버 ID',
    post_id     BIGINT                  NOT NULL COMMENT '게시물 ID',
    created_at  DATETIME                NOT NULL COMMENT '생성 일시',
    created_id  VARCHAR(255)            NOT NULL COMMENT '생성자',
    PRIMARY KEY (id),
    FOREIGN KEY (member_id) REFERENCES members (id),
    FOREIGN KEY (post_id) REFERENCES posts (id)
);

CREATE TABLE comments
(
    post_id         BIGINT              NOT NULL COMMENT '게시물_ID',
    member_id       BIGINT              NOT NULL COMMENT '작성자_ID',
    content         VARCHAR(500)        NOT NULL COMMENT '내용',
    created_at      DATETIME            NOT NULL COMMENT '생성 일시',
    created_id      VARCHAR(255)        NOT NULL COMMENT '생성자',
    updated_id      VARCHAR(255)        NULL COMMENT '수정자(null)',
    updated_at      DATETIME            NULL COMMENT '수정 일시(null)',
    deleted_at      DATETIME            NULL COMMENT '삭제 일시(null)',
    PRIMARY KEY (post_id, member_id),
    FOREIGN KEY (post_id) REFERENCES posts (id),
    FOREIGN KEY (member_id) REFERENCES members (id)
);

CREATE TABLE admins
(
    id       BIGINT AUTO_INCREMENT NOT NULL COMMENT '관리자_ID',
    PRIMARY KEY (id)
);


CREATE TABLE pending_streamer
(
    id       BIGINT AUTO_INCREMENT NOT NULL COMMENT '스트리머 승인 대기_ID',
    member_id       BIGINT              NOT NULL COMMENT '멤버_ID',
    admin_id       BIGINT              NULL COMMENT '승인자_ID',
    platformUrl     VARCHAR(255)       NOT NULL COMMENT '플랫폼 URL',
    status          ENUM('APPROVAL', 'PENDING', 'REJECTION') NOT NULL DEFAULT 'PENDING',
    created_at      DATETIME            NOT NULL COMMENT '생성 일시',
    created_id      VARCHAR(255)        NOT NULL COMMENT '생성자',
    updated_id      VARCHAR(255)        NULL COMMENT '수정자(null)',
    updated_at      DATETIME            NULL COMMENT '수정 일시(null)',
    PRIMARY KEY (id),
    FOREIGN KEY (admin_id) REFERENCES admins (id),
    FOREIGN KEY (member_id) REFERENCES members (id)
);