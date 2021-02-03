CREATE TABLE gift_certificate (
id bigint NOT NULL AUTO_INCREMENT,
name varchar(45) COLLATE utf8_bin DEFAULT NULL,
description varchar(45) COLLATE utf8_bin DEFAULT NULL,
price double DEFAULT NULL,
duration double DEFAULT NULL,
create_date timestamp(5) NULL DEFAULT NULL,
last_update_date timestamp(5) NULL DEFAULT NULL,
PRIMARY KEY (id),
UNIQUE(id)
);

CREATE TABLE tag (
id bigint NOT NULL AUTO_INCREMENT,
name varchar(45) COLLATE utf8_bin NOT NULL,
PRIMARY KEY (id),
UNIQUE(id)
);

CREATE TABLE tag_has_gift_certificate (
tag_id bigint NOT NULL,
gift_certificate_id bigint NOT NULL,
PRIMARY KEY (tag_id,gift_certificate_id),
KEY fk_tag_has_gift_certificate_gift_certificate1_idx (gift_certificate_id),
KEY fk_tag_has_gift_certificate_tag_idx (tag_id),
CONSTRAINT fk_tag_has_gift_certificate_gift_certificate1 FOREIGN KEY (gift_certificate_id) REFERENCES gift_certificate (id),
CONSTRAINT fk_tag_has_gift_certificate_tag FOREIGN KEY (tag_id) REFERENCES tag (id)
);
