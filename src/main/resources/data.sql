INSERT INTO members (sns_type, sns_id, email, profile, nickname, role, self_introduction,
                     created_id, created_at)
VALUES ('GOOGLE', '11111', 'user@example.com',
        'https://as2.ftcdn.net/v2/jpg/02/50/30/59/1000_F_250305943_sDC6la1N1fDl3bLgfLxOkQwItIodsdMb.jpg',
        'nickname111', 'MEMBER', '저는 테스트1입니다.', 'admin', NOW());
INSERT INTO members (sns_type, sns_id, email, profile, nickname, role, self_introduction,
                     created_id, created_at)
VALUES ('GOOGLE', '22222', 'user@example.com',
        'https://as2.ftcdn.net/v2/jpg/02/50/30/59/1000_F_250305943_sDC6la1N1fDl3bLgfLxOkQwItIodsdMb.jpg',
        'nickname222', 'MEMBER', '저는 테스트2입니다.', 'admin', NOW());
INSERT INTO members (sns_type, sns_id, email, profile, nickname, role, self_introduction,
                     created_id, created_at)
VALUES ('GOOGLE', '33333', 'user@example.com',
        'https://as2.ftcdn.net/v2/jpg/02/50/30/59/1000_F_250305943_sDC6la1N1fDl3bLgfLxOkQwItIodsdMb.jpg',
        'nickname333', 'MEMBER', '저는 테스트3입니다.', 'admin', NOW());
INSERT INTO members (sns_type, sns_id, email, profile, nickname, role, self_introduction,
                     created_id, created_at)
VALUES ('GOOGLE', '44444', 'user@example.com',
        'https://as2.ftcdn.net/v2/jpg/02/50/30/59/1000_F_250305943_sDC6la1N1fDl3bLgfLxOkQwItIodsdMb.jpg',
        'nickname444', 'MEMBER', '저는 테스트4입니다.', 'admin', NOW());
INSERT INTO members (sns_type, sns_id, email, profile, nickname, role, self_introduction,
                     created_id, created_at)
VALUES ('GOOGLE', '55555', 'user@example.com',
        'https://as2.ftcdn.net/v2/jpg/02/50/30/59/1000_F_250305943_sDC6la1N1fDl3bLgfLxOkQwItIodsdMb.jpg',
        'nickname555', 'MEMBER', '저는 테스트5입니다.', 'admin', NOW());

-- Members 데이터 생성 (1-100)
INSERT INTO members (sns_type, sns_id, email, profile, nickname, role, self_introduction, created_id, created_at)
SELECT
    ELT(1 + FLOOR(RAND() * 3), 'GOOGLE', 'KAKAO', 'NAVER') as sns_type,
    CAST(numbers.n AS CHAR) as sns_id,
    CONCAT('test', numbers.n, '@example.com') as email,
    CASE
        WHEN numbers.n <= 20 THEN CONCAT('https://example.com/profiles/streamer', numbers.n, '_pro.jpg')
        ELSE 'https://as2.ftcdn.net/v2/jpg/02/50/30/59/1000_F_250305943_sDC6la1N1fDl3bLgfLxOkQwItIodsdMb.jpg'
        END as profile,
    CASE
        WHEN numbers.n = 1 THEN '게임왕킹'
        WHEN numbers.n = 2 THEN '요리하는여행자'
        WHEN numbers.n = 3 THEN 'MusicMaster'
        WHEN numbers.n = 4 THEN '댄서루이'
        WHEN numbers.n = 5 THEN '공부하는곰돌이'
        ELSE CONCAT('스트리머', numbers.n)
        END as nickname,
    'MEMBER' as role,
    CASE
        WHEN numbers.n = 1 THEN '안녕하세요! 게임 전문 스트리머입니다.'
        WHEN numbers.n = 2 THEN '세계 각국의 요리를 선보입니다'
        WHEN numbers.n = 3 THEN '음악과 함께하는 즐거운 방송!'
        WHEN numbers.n = 4 THEN '춤추는 게 제일 좋아요'
        WHEN numbers.n = 5 THEN '함께 공부해요!'
        ELSE CONCAT('안녕하세요! 스트리머 ', numbers.n, '입니다.')
        END as self_introduction,
    'admin' as created_id,
    DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 90) DAY) as created_at
FROM (
         SELECT a.N + b.N * 10 + 1 as n
         FROM (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a,
              (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b
         ORDER BY n
             LIMIT 100
     ) numbers;

INSERT INTO platforms (name, image_url)
VALUES ('Twitch', 'https://example.com/images/twitch.png'),
       ('YouTube', 'https://example.com/images/youtube.png'),
       ('SOOP', 'https://example.com/images/afreeca.png'),
       ('Chzzk', 'https://example.com/images/chzzk.png');

INSERT INTO tags (name, created_at, created_id)
VALUES ('게임', now(), '1'),
       ('음악', now(), '1'),
       ('요리', now(), '1'),
       ('채팅', now(), '1'),
       ('스포츠', now(), '1'),
       ('미술', now(), '1'),
       ('Just Chatting', now(), '1'),
       ('댄스', now(), '1'),
       ('공부', now(), '1'),
       ('장군', now(), '1'),
       ('장조림', now(), '1'),
       ('장난', now(), '1'),
       ('장남', now(), '1'),
       ('고장난', now(), '1'),
       ('짐승', now(), '1'),
       ('BTS', now(), '1'),
       ('Javascript', now(), '1'),
       ('일상', now(), '1');

-- Members Tags 관계 생성 (각 멤버당 2-5개의 랜덤 태그)
INSERT INTO members_tags (member_id, tag_id)
SELECT DISTINCT
    m.id as member_id,
    1 + FLOOR(RAND() * 18) as tag_id
FROM members m
         CROSS JOIN (
    SELECT 1 as n UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5
) t
WHERE NOT EXISTS (
    SELECT 1
    FROM members_tags mt
    WHERE mt.member_id = m.id
      AND mt.tag_id = 1 + FLOOR(RAND() * 18)
)
    LIMIT 300;  -- 100명의 멤버에 대해 평균 2-3개의 태그가 할당되도록

-- Streamers 생성 (전체 회원의 약 70%를 스트리머로 설정)
INSERT INTO streamers (member_id)
SELECT n
FROM (
         SELECT a.N + b.N * 10 + 1 as n
         FROM (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a,
              (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6) b
         ORDER BY n
             LIMIT 70
     ) numbers;

-- Streamers Tags 관계 생성 (각 스트리머당 2-5개의 랜덤 태그)
INSERT INTO streamers_tags (streamer_id, tag_id)
SELECT DISTINCT
    s.id as streamer_id,
    1 + FLOOR(RAND() * 18) as tag_id
FROM streamers s
         CROSS JOIN (
    SELECT 1 as n UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5
) t
WHERE NOT EXISTS (
    SELECT 1
    FROM streamers_tags st
    WHERE st.streamer_id = s.id
      AND st.tag_id = 1 + FLOOR(RAND() * 18)
)
    LIMIT 200;  -- 각 스트리머당 평균 2-3개의 태그가 할당되도록

-- Streamers Platforms 관계 생성 (각 스트리머당 1-3개의 플랫폼)
INSERT INTO streamers_platforms (streamer_id, platform_id, streamer_profile_url)
SELECT DISTINCT
    s.id as streamer_id,
    p.platform_id,
    CASE p.platform_id
        WHEN 1 THEN CONCAT('https://twitch.tv/streamer', s.id)
        WHEN 2 THEN CONCAT('https://youtube.com/@streamer', s.id)
        WHEN 3 THEN CONCAT('https://soop.com/streamer', s.id)
        ELSE CONCAT('https://chzzk.naver.com/streamer', s.id)
        END as streamer_profile_url
FROM streamers s
         CROSS JOIN (
    SELECT 1 as platform_id UNION SELECT 2 UNION SELECT 3 UNION SELECT 4
) p
WHERE NOT EXISTS (
    SELECT 1
    FROM streamers_platforms sp
    WHERE sp.streamer_id = s.id
      AND sp.platform_id = p.platform_id
)
  AND RAND() < 0.5
    LIMIT 150;  -- 각 스트리머당 평균 1-3개의 플랫폼이 할당되도록

INSERT INTO follow
SELECT 1,
       1,
       2,
       now(),
       now(),
       'SYSTEM',
       now(),
       'SYSTEM';

INSERT INTO follow
SELECT 2,
       2,
       1,
       now(),
       now(),
       'SYSTEM',
       now(),
       'SYSTEM';

INSERT INTO posts
(member_id, post_type, status, title, content, created_at, created_id, updated_at, updated_id,
 deleted_at)
VALUES (1, 'MEMBER', 'DRAFT', '게시글 제목', '게시글 내용', NOW(), 'admin', NULL, NULL, NULL);

INSERT INTO comments
(post_id, member_id, content, created_at, created_id, updated_at, updated_id, deleted_at)
VALUES (1, 1, '댓글댓글댓글입니다.', NOW(), 'admin', NULL, NULL, NULL);