/*
 * wstable 
 */
CREATE TABLE `wstable` (
  `id` CHAR(32) NOT NULL COMMENT 'uri+title+author md5编码id',
  `uri` VARCHAR(255) COMMENT 'uri',
  `title` VARCHAR(255) COMMENT '标题',
  `author` VARCHAR(255) COMMENT '作者',
  `time1` DATETIME COMMENT '时间',
  `description` TEXT COMMENT '描述',
  `content` TEXT COMMENT '内容',
  `images` TEXT COMMENT 'json uris',
  `view1` TEXT COMMENT 'view',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
select * from `wstable`;
DROP TABLE  `wstable`;
TRUNCATE TABLE `wstable`;
select count(*) from `wstable`

/*
 * user table
 */
CREATE TABLE `user` (
  `id` INT NOT NULL,
  `name` VARCHAR(255),
  `join_date` DATETIME,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
select * from `user`;
DROP TABLE  `user`;
TRUNCATE TABLE `user`;
select count(*) from `user`

/*
 * searchitem table
 */
CREATE TABLE `searchitem` (
  `id` CHAR(32) NOT NULL,
  `name` VARCHAR(255),
  `num` INT,
  `user_id` INT,
  PRIMARY KEY (`id`),
  FOREIGN KEY(`user_id`) REFERENCES user(`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
select * from `searchitem`;
DROP TABLE  `searchitem`;
TRUNCATE TABLE `searchitem`;
select count(*) from `searchitem`

ALTER TABLE `wstable` ENGINE=innodb;
ALTER TABLE `user` ENGINE=innodb;
ALTER TABLE `searchitem` ENGINE=innodb;