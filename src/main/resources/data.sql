INSERT INTO members (sns_type, sns_id, email, profile, nickname, role, self_introduction, created_id, created_at)
VALUES ('GOOGLE', '11111', 'user@example.com', 'https://as2.ftcdn.net/v2/jpg/02/50/30/59/1000_F_250305943_sDC6la1N1fDl3bLgfLxOkQwItIodsdMb.jpg', 'nickname111', 'MEMBER', '저는 테스트1입니다.', 'admin', NOW());
INSERT INTO members (sns_type, sns_id, email, profile, nickname, role, self_introduction, created_id, created_at)
VALUES ('GOOGLE', '22222', 'user@example.com', 'https://as2.ftcdn.net/v2/jpg/02/50/30/59/1000_F_250305943_sDC6la1N1fDl3bLgfLxOkQwItIodsdMb.jpg', 'nickname222', 'MEMBER', '저는 테스트2입니다.', 'admin', NOW());
INSERT INTO members (sns_type, sns_id, email, profile, nickname, role, self_introduction, created_id, created_at)
VALUES ('GOOGLE', '33333', 'user@example.com', 'https://as2.ftcdn.net/v2/jpg/02/50/30/59/1000_F_250305943_sDC6la1N1fDl3bLgfLxOkQwItIodsdMb.jpg', 'nickname333', 'MEMBER', '저는 테스트3입니다.', 'admin', NOW());
INSERT INTO members (sns_type, sns_id, email, profile, nickname, role, self_introduction, created_id, created_at)
VALUES ('GOOGLE', '44444', 'user@example.com', 'https://as2.ftcdn.net/v2/jpg/02/50/30/59/1000_F_250305943_sDC6la1N1fDl3bLgfLxOkQwItIodsdMb.jpg', 'nickname444', 'MEMBER', '저는 테스트4입니다.', 'admin', NOW());
INSERT INTO members (sns_type, sns_id, email, profile, nickname, role, self_introduction, created_id, created_at)
VALUES ('GOOGLE', '55555', 'user@example.com', 'https://as2.ftcdn.net/v2/jpg/02/50/30/59/1000_F_250305943_sDC6la1N1fDl3bLgfLxOkQwItIodsdMb.jpg', 'nickname555', 'MEMBER', '저는 테스트5입니다.', 'admin', NOW());

INSERT INTO streamers (member_id, today_comment, self_introduction, image_url, name)
VALUES ('1','1', '1', '1', '1');
INSERT INTO streamers (member_id, today_comment, self_introduction, image_url, name)
VALUES ('2','2', '2', '2', '2');
INSERT INTO streamers (member_id, today_comment, self_introduction, image_url, name)
VALUES ('3','3', '3', '3', '3');
INSERT INTO streamers (member_id, today_comment, self_introduction, image_url, name)
VALUES ('4','4', '4', '4', '4');

INSERT INTO follow SELECT 1, 1, 2, now(), now(), 'SYSTEM', now(), 'SYSTEM';

INSERT INTO follow SELECT 2, 2, 1, now(), now(), 'SYSTEM', now(), 'SYSTEM';

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
