DELETE FROM `users`;
DELETE FROM `files`;
DELETE FROM `shops`;

-- users test data
INSERT INTO `users`
    VALUES (1, 'testuser@gmail.com', '1YPsW4mdDQz7ctPvcwGDaTMgcU7eehkK9Y3QzLWOq6QSduudE44SNhlLb6JC',
    false, '01012341234', 'iamtest', '$2a$10$hkWoqKEJTBqadPUuv9W/FO/UOdxEBc2ekaHf6JcLOmhCOgT7hwbiq',
    CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), NULL, NULL, NULL);

-- files test data
INSERT INTO `files`
    VALUES (1, 'LOCAL/public/', 'pBhvKVzqU8vhYi76Kb8Xgm78xU1w54VaVI8JkWv4weXiuh0pkyFAnSm9g7gB.jpg',
    'jpg', '440x440.jpg', 'jpg', 'https://storage.apjung.xyz/LOCAL/public/pBhvKVzqU8vhYi76Kb8Xgm78xU1w54VaVI8JkWv4weXiuh0pkyFAnSm9g7gB.jpg',
    20705, 440, 440, FALSE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), NULL, 1, 1);

-- shops test data
INSERT INTO `shops`
    VALUES (1, 1, 'test shop',  'https://www.naver.com', '테스트 쇼핑몰입니다1', 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), NULL, 1, 1);

INSERT INTO `shops`
    VALUES (2, NULL, '무신sa TEST',  'https://www.naver.com', '테스트 쇼핑몰입니다2', 8, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), NULL, 1, 1);

INSERT INTO `shops`
    VALUES (3, NULL, 'test shop2',  'https://www.naver.com', '테스트 쇼핑몰입니다3', 8, 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), NULL, 1, 1);

INSERT INTO `shops`
    VALUES (4, NULL, '4XR test',  'https://www.naver.com', '테스트 쇼핑몰입니다4', 8, 6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), NULL, 1, 1);

INSERT INTO `shops`
    VALUES (5, NULL, '12 무신사 test shop',  'https://www.naver.com', '테스트 쇼핑몰입니다5', 6, 6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), NULL, 1, 1);