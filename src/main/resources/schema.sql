DROP TABLE IF EXISTS tokens;
DROP TABLE IF EXISTS members;

CREATE TABLE members
(
    id         BIGINT AUTO_INCREMENT NOT NULL COMMENT '멤버_ID',
    sns_type   VARCHAR(100) NOT NULL COMMENT 'GOOGLE, KAKAO , NAVER',
    sns_id     VARCHAR(255) NOT NULL COMMENT 'SNS 고유 ID',
    email      VARCHAR(255) NOT NULL COMMENT 'SNS 로그인시 받아오는 이메일 값',
    profile    VARCHAR(255) NULL COMMENT 'storage URL',
    nickname   VARCHAR(255) NOT NULL COMMENT '닉네임(null시 랜덤값 생성)',
    role       VARCHAR(20) NOT NULL COMMENT '회원 권한',
    deleted_at DATETIME NULL COMMENT '탈퇴일시(null)',
    created_at DATETIME     NOT NULL COMMENT '생성일시',
    created_id VARCHAR(255) NOT NULL COMMENT '생성자',
    updated_id VARCHAR(255) NULL COMMENT '수정자(null)',
    updated_at DATETIME NULL COMMENT '수정일시(null)',
    PRIMARY KEY (id)
);

CREATE TABLE tokens
(
    id              BIGINT AUTO_INCREMENT             NOT NULL COMMENT '토큰_ID',
    member_id       BIGINT                            NOT NULL COMMENT '멤버_ID',
    refresh_token   VARCHAR(255)            UNIQUE    NOT NULL COMMENT '리프레쉬_토큰',
    expiration_time DATETIME                          NOT NULL COMMENT '만료_시간',
    PRIMARY KEY (id),
    FOREIGN KEY (member_id) REFERENCES members(id)
);
