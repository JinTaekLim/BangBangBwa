INSERT INTO members (sns_type, sns_id, email, nickname, role, created_id, created_at)
VALUES ('GOOGLE', '12345', 'user@example.com', 'nickname123', 'MEMBER', 'admin', NOW());
INSERT INTO members (sns_type, sns_id, email, nickname, role, created_id, created_at)
VALUES ('GOOGLE', '12345', 'user@example.com', 'nickname123', 'MEMBER', 'admin', NOW());
INSERT INTO members (sns_type, sns_id, email, nickname, role, created_id, created_at)
VALUES ('GOOGLE', '12345', 'user@example.com', 'nickname123', 'MEMBER', 'admin', NOW());
INSERT INTO members (sns_type, sns_id, email, nickname, role, created_id, created_at)
VALUES ('GOOGLE', '12345', 'user@example.com', 'nickname123', 'MEMBER', 'admin', NOW());
INSERT INTO members (sns_type, sns_id, email, nickname, role, created_id, created_at)
VALUES ('GOOGLE', '12345', 'user@example.com', 'nickname123', 'MEMBER', 'admin', NOW());

INSERT INTO follow
SELECT 1,
       1,
       2,
       now(),
       now(),
       'SYSTEM',
       now(),
       'SYSTEM'
;

INSERT INTO follow
SELECT 2,
       2,
       1,
       now(),
       now(),
       'SYSTEM',
       now(),
       'SYSTEM'
;

INSERT INTO streamers (member_id)
VALUES ('1');
INSERT INTO streamers (member_id)
VALUES ('2');
INSERT INTO streamers (member_id)
VALUES ('3');
INSERT INTO streamers (member_id)
VALUES ('4');

INSERT INTO tags (name, created_at, created_id)
VALUES ('1', now(), '1');
INSERT INTO tags (name, created_at, created_id)
VALUES ('2', now(), '2');
INSERT INTO tags (name, created_at, created_id)
VALUES ('3', now(), '3');
INSERT INTO tags (name, created_at, created_id)
VALUES ('4', now(), '4');

INSERT INTO platforms (name, image_url)
VALUES ('1', '1');
INSERT INTO platforms (name, image_url)
VALUES ('2', '2');
INSERT INTO platforms (name, image_url)
VALUES ('3', '3');

INSERT INTO streamers_tags (streamer_id, tag_id)
VALUES (1, 1);
INSERT INTO streamers_tags (streamer_id, tag_id)
VALUES (1, 2);
INSERT INTO streamers_tags (streamer_id, tag_id)
VALUES (2, 1);
INSERT INTO streamers_tags (streamer_id, tag_id)
VALUES (2, 2);
INSERT INTO streamers_tags (streamer_id, tag_id)
VALUES (3, 1);
INSERT INTO streamers_tags (streamer_id, tag_id)
VALUES (3, 2);

INSERT INTO streamers_platforms (streamer_id, platform_id, streamer_profile_url)
VALUES ('1', '1', '1');
INSERT INTO streamers_platforms (streamer_id, platform_id, streamer_profile_url)
VALUES ('1', '2', '2');
INSERT INTO streamers_platforms (streamer_id, platform_id, streamer_profile_url)
VALUES ('2', '1', '1');
INSERT INTO streamers_platforms (streamer_id, platform_id, streamer_profile_url)
VALUES ('3', '1', '1');
INSERT INTO streamers_platforms (streamer_id, platform_id, streamer_profile_url)
VALUES ('4', '2', '2');
