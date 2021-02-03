INSERT INTO gift_certificate_test VALUES (1, 'testName1', 'testDescription1', 10.0, 120.0, '2021-02-01 16:07:19.635000000', '2021-02-01 16:07:19.635000000');
INSERT INTO gift_certificate_test VALUES (2, 'testName2', 'testDescription2', 20.0, 220.0, '2021-02-01 12:07:19.635000000', '2021-02-01 12:07:19.635000000');
INSERT INTO gift_certificate_test VALUES (3, 'testName3', 'testDescription3', 30.0, 320.0, '2021-02-01 13:07:19.635000000', '2021-02-01 13:07:19.635000000');
INSERT INTO gift_certificate_test VALUES (4, 'testName4', 'testDescription4', 40.0, 420.0, '2021-02-01 14:07:19.635000000', '2021-02-01 14:07:19.635000000');

INSERT INTO tag VALUES (1, 'testTag1');
INSERT INTO tag VALUES (2, 'testTag2');
INSERT INTO tag VALUES (3, 'testTag3');
INSERT INTO tag VALUES (4, 'testTag4');

INSERT INTO tag_has_gift_certificate VALUES (1, 1);
INSERT INTO tag_has_gift_certificate VALUES (1, 2);
INSERT INTO tag_has_gift_certificate VALUES (1, 3);
INSERT INTO tag_has_gift_certificate VALUES (1, 4);
INSERT INTO tag_has_gift_certificate VALUES (2, 1);
INSERT INTO tag_has_gift_certificate VALUES (3, 1);
INSERT INTO tag_has_gift_certificate VALUES (4, 1);
INSERT INTO tag_has_gift_certificate VALUES (4, 2);