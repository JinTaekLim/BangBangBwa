INSERT INTO members
SELECT 1, 'GOOGLE', 'googleTestSnsId', 'bbb@gamil.com', 'https://as2.ftcdn.net/v2/jpg/02/50/30/59/1000_F_250305943_sDC6la1N1fDl3bLgfLxOkQwItIodsdMb.jpg',
       'BangBangBwa', 'MEMBER', null, now(), 'SYSTEM', now(), 'SYSTEM'
;

INSERT INTO follow
SELECT 1, 1, 2, now(), now(), 'SYSTEM', now(), 'SYSTEM'
;

INSERT INTO follow
SELECT 2, 2, 1, now(), now(), 'SYSTEM', now(), 'SYSTEM'
;
INSERT INTO streamers (today_comment, self_introduction, image_url, name)
VALUES ('1', '1', '1', '1');
INSERT INTO streamers (today_comment, self_introduction, image_url, name)
VALUES ('2', '2', '2', '2');
INSERT INTO streamers (today_comment, self_introduction, image_url, name)
VALUES ('3', '3', '3', '3');
INSERT INTO streamers (today_comment, self_introduction, image_url, name)
VALUES ('4', '4', '4', '4');

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
