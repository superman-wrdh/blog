/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.8 : Database - db_blog3
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_blog3` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_blog3`;

/*Table structure for table `t_blog` */

DROP TABLE IF EXISTS `t_blog`;

CREATE TABLE `t_blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `summary` varchar(400) DEFAULT NULL,
  `releaseDate` datetime DEFAULT NULL,
  `clickHit` int(11) DEFAULT NULL,
  `replyHit` int(11) DEFAULT NULL,
  `content` text,
  `typeId` int(11) DEFAULT NULL,
  `keyWord` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `typeId` (`typeId`),
  CONSTRAINT `t_blog_ibfk_1` FOREIGN KEY (`typeId`) REFERENCES `t_blogtype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

/*Table structure for table `t_blogger` */

DROP TABLE IF EXISTS `t_blogger`;

CREATE TABLE `t_blogger` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `profile` text,
  `nickName` varchar(50) DEFAULT NULL,
  `sign` varchar(100) DEFAULT NULL,
  `imageName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `t_blogtype` */

DROP TABLE IF EXISTS `t_blogtype`;

CREATE TABLE `t_blogtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(30) DEFAULT NULL,
  `orderNo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Table structure for table `t_comment` */

DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userIp` varchar(50) DEFAULT NULL,
  `blogId` int(11) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `commentDate` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=366 DEFAULT CHARSET=utf8;

/*Table structure for table `t_link` */

DROP TABLE IF EXISTS `t_link`;

CREATE TABLE `t_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `linkName` varchar(100) DEFAULT NULL,
  `linkUrl` varchar(200) DEFAULT NULL,
  `orderNo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Table structure for table `t_loginrecord` */

DROP TABLE IF EXISTS `t_loginrecord`;

CREATE TABLE `t_loginrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(255) DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL,
  `ip` varchar(128) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Table structure for table `t_property` */

DROP TABLE IF EXISTS `t_property`;

CREATE TABLE `t_property` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(32) DEFAULT NULL,
  `value` varchar(32) DEFAULT NULL,
  `values` varchar(4096) DEFAULT NULL,
  `description` varbinary(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `t_searchword` */

DROP TABLE IF EXISTS `t_searchword`;

CREATE TABLE `t_searchword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(255) NOT NULL,
  `count` int(10) unsigned DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Table structure for table `t_vistor` */

DROP TABLE IF EXISTS `t_vistor`;

CREATE TABLE `t_vistor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `type` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
