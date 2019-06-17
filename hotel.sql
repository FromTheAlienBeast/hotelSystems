/*
SQLyog v10.2 
MySQL - 5.5.49 : Database - hotel
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hotel` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `hotel`;

/*Table structure for table `dinnertable` */

DROP TABLE IF EXISTS `dinnertable`;

CREATE TABLE `dinnertable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tableName` varchar(20) DEFAULT NULL,
  `tableStatus` int(11) DEFAULT '0',
  `orderDate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `dinnertable` */

insert  into `dinnertable`(`id`,`tableName`,`tableStatus`,`orderDate`) values (1,'纽约',0,NULL),(2,'丹麦',0,NULL),(3,'伦敦',0,NULL),(4,'巴黎',1,'2018-10-10 21:52:43'),(13,'吉林',1,'2018-50-16 17:10:731');

/*Table structure for table `food` */

DROP TABLE IF EXISTS `food`;

CREATE TABLE `food` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `foodName` varchar(20) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `mprice` double DEFAULT NULL,
  `remark` varchar(200) DEFAULT 'null',
  `img` varchar(100) DEFAULT 'null',
  `foodType_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `foodType_id` (`foodType_id`),
  CONSTRAINT `food_ibfk_1` FOREIGN KEY (`foodType_id`) REFERENCES `foodtype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `food` */

insert  into `food`(`id`,`foodName`,`price`,`mprice`,`remark`,`img`,`foodType_id`) values (1,'白灼虾',50,40,NULL,'app/style/images/baizhuoxia.jpg',13),(2,'白切鸡',50,40,NULL,NULL,13),(3,'烤乳猪',50,40,NULL,NULL,13),(4,'烧鹅',50,40,NULL,NULL,13),(5,'猪肉荷兰豆',50,40,NULL,NULL,13),(6,'黄埔炒蛋',50,40,NULL,NULL,13),(7,'狗肉煲',50,40,NULL,NULL,13),(8,'鲫鱼汤',50,40,NULL,NULL,13),(11,'酱猪蹄',50,40,NULL,NULL,15),(12,'烧汁豆腐',50,40,NULL,NULL,15),(13,'水煮鱼',50,40,NULL,NULL,15),(14,'鱼香肉丝',29,21,'','',15),(15,'冰糖湘莲',50,40,NULL,NULL,16),(16,'东安子鸡',50,40,NULL,NULL,16),(17,'剁椒鱼头',50,40,NULL,NULL,16),(18,'烧鸭蛋',50,40,NULL,NULL,16),(19,'锅头肉',50,40,NULL,NULL,17),(25,'火腿白菜',38,30,'','',17),(26,'青椒鸡蛋',28,20,'','',17);

/*Table structure for table `foodtype` */

DROP TABLE IF EXISTS `foodtype`;

CREATE TABLE `foodtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `foodtype` */

insert  into `foodtype`(`id`,`typeName`) values (13,'粤菜'),(15,'川菜'),(16,'湘菜'),(17,'东北菜');

/*Table structure for table `orderdetall` */

DROP TABLE IF EXISTS `orderdetall`;

CREATE TABLE `orderdetall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderid` int(11) DEFAULT NULL,
  `food_id` int(11) DEFAULT NULL,
  `foodCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `orderid` (`orderid`),
  KEY `food_id` (`food_id`),
  CONSTRAINT `orderdetall_ibfk_1` FOREIGN KEY (`orderid`) REFERENCES `orders` (`id`),
  CONSTRAINT `orderdetall_ibfk_2` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderdetall` */

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `table_id` int(11) DEFAULT NULL,
  `orderDate` varchar(20) DEFAULT 'null',
  `totalPrice` double DEFAULT NULL,
  `orderStatus` int(11) DEFAULT NULL,
  `numbers` int(11) DEFAULT NULL,
  `foodName` varchar(20) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `table_id` (`table_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`table_id`) REFERENCES `dinnertable` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`id`,`table_id`,`orderDate`,`totalPrice`,`orderStatus`,`numbers`,`foodName`,`price`) values (45,1,NULL,50,0,1,'白切鸡',50),(46,1,NULL,28,0,1,'青椒鸡蛋',28),(48,2,NULL,50,0,1,'烤乳猪',50),(49,2,NULL,50,0,1,'烤乳猪',50);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
