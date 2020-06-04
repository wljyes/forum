CREATE TABLE `collect`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` int NULL,
  `pid` int NULL,
  `collect_date` datetime NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `pid` int NULL,
  `uid` int NULL,
  `create_date` datetime NULL,
  `content` varchar(1000) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `follow`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` int NULL,
  `follower_id` int NULL,
  `follow_date` datetime NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `post`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` int NULL,
  `create_date` datetime NULL,
  `update_date` datetime NULL,
  `content` varchar(1000) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `User`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NULL,
  `password` varchar(255) NULL,
  PRIMARY KEY (`id`)
);

ALTER TABLE `collect` ADD CONSTRAINT `fk_collect_post_on_pid` FOREIGN KEY (`pid`) REFERENCES `post` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `collect` ADD CONSTRAINT `fk_collect_user_on_uid` FOREIGN KEY (`uid`) REFERENCES `User` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `comment` ADD CONSTRAINT `fk_comment_post_on_pid` FOREIGN KEY (`pid`) REFERENCES `post` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `comment` ADD CONSTRAINT `fk_comment_user_on_uid` FOREIGN KEY (`uid`) REFERENCES `User` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `follow` ADD CONSTRAINT `fk_follow_user_on_uid` FOREIGN KEY (`uid`) REFERENCES `User` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `follow` ADD CONSTRAINT `fk_follow_user_on_followid` FOREIGN KEY (`follow_id`) REFERENCES `User` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `post` ADD CONSTRAINT `fk_post_user_on_uid` FOREIGN KEY (`uid`) REFERENCES `User` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

