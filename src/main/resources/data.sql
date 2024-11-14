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
