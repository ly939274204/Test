/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.1.26-rc-community : Database - url
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`url` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `url`;

/*Table structure for table `longurl` */

CREATE TABLE `longurl` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `longurl` varchar(100) NOT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Table structure for table `shorturl` */

CREATE TABLE `shorturl` (
  `su_id` int(11) NOT NULL AUTO_INCREMENT,
  `shorturl` varchar(100) NOT NULL,
  `u_id` int(11) NOT NULL,
  PRIMARY KEY (`su_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `shorturl_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `longurl` (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
