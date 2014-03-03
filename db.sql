/*
SQLyog Enterprise - MySQL GUI v8.05 
MySQL - 5.5.16 : Database - scrappertest
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`scrappertest` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `scrappertest`;

/*Table structure for table `depart` */

DROP TABLE IF EXISTS `depart`;

CREATE TABLE `depart` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `CODE` varchar(100) NOT NULL,
  `COUNTRY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=latin1;

/*Data for the table `depart` */

insert  into `depart`(`ID`,`NAME`,`CODE`,`COUNTRY`) values (1,'Australia','Australia',NULL),(2,'Sydney','SYD',1),(3,'Adelaide','ADL',1),(4,'Gold Coast','OOL',1),(5,'Melbourne','MEL',1),(6,'Perth','PER',1),(7,'Darwin','DRW',1),(8,'Brunei','Brunei',NULL),(9,'Brunei','BWN',8),(10,'Cambodia','Cambodia',NULL),(11,'Phnom Penh','PNH',10),(12,'Siem Reap','REP',10),(13,'China','China',NULL),(14,'Kunming','KMG',13),(15,'Shanghai','PVG',13),(16,'Chongqing','CKG',13),(17,'Chengdu','CTU',13),(18,'Beijing','PEK',13),(19,'Guangzhou','CAN',13),(20,'Guilin','KWL',13),(21,'Hangzhou','HGH',13),(22,'Nanning','NNG',13),(23,'Shenzhen','SZX',13),(24,'Changsha','CSX',13),(25,'Wuhan','WUH',13),(26,'Xi\'an','XIY',13),(27,'Jinjiang','JJN',13),(28,'Hong Kong','Hong Kong',NULL),(29,'Hong Kong','HKG',28),(30,'India','India',NULL),(31,'Kochi','COK',30),(32,'Kolkata','CCU',30),(33,'Bangalor','BLR',30),(34,'Tiruchirappalli','TRZ',30),(35,'Chennai','MAA',30),(36,'Indonesia','Indonesia',NULL),(37,'Bali','DPS',36),(38,'Banda Aceh','BTJ',36),(39,'Bandung','BDO',36),(40,'Jakarta','CGK',36),(41,'Makassar','UPG',36),(42,'Padang','PDG',36),(43,'Palembang','PLM',36),(44,'Lombok','LOP',36),(45,'Pekanbaru','PKU',36),(46,'Solo','SOC',36),(47,'Surabaya','SUB',36),(48,'Yogyakarta','JOG',36),(49,'Balikpapan','BPN',36),(50,'Semarang','SRG',36),(51,'Manado','MDC',36),(52,'Medan – Kualanamu','KNO',36),(53,'Japan','Japan',NULL),(54,'Tokyo - Haneda','HND',53),(55,'Osaka - Kansai','KIX',53),(56,'Nagoya - Chubu','NGO',53),(57,'Laos','Laos',NULL),(58,'Vientiane','VTE',57),(59,'Macau','Macau',NULL),(60,'Macau','MFM',59),(61,'Malaysia','Malaysia',NULL),(62,'Alor Setar','AOR',61),(63,'Bintulu','BTU',61),(64,'Johor Bahru','JHB',61),(65,'Kota Bharu','KBR',61),(66,'Kota Kinabalu','BKI',61),(67,'Kuala Lumpur','KUL',61),(68,'Kuala Terengganu','TGG',61),(69,'Kuching','KCH',61),(70,'Labuan','LBU',61),(71,'Langkawi','LGK',61),(72,'Miri','MYY',61),(73,'Penang','PEN',61),(74,'Sandakan','SDK',61),(75,'Sibu','SBW',61),(76,'Tawau','TWU',61),(77,'Maldives','Maldives',NULL),(78,'Male','MLE',77),(79,'Myanmar','Myanmar',NULL),(80,'Yangoon','RGN',79),(81,'Mandalay','MDL',79),(82,'Nay Pyi Taw','NYT',79),(83,'Nepal','Nepal',NULL),(84,'Kathmandu','KTM',83),(85,'Philippines','Philippines',NULL),(86,'Kalibo','KLO',85),(87,'Davao','DVO',85),(88,'Puerto Princesa (Palawan)','PPS',85),(89,'Bacolod','BCD',85),(90,'Cagayan de Oro','CGY',85),(91,'Cebu','TAG',85),(92,'Iloilo','ILO',85),(93,'Tagbilaran','TAG',85),(94,'Manila','MNL',85),(95,'Tacloban','TAC',85),(96,'Saudi Arabia','Saudi Arabia',NULL),(97,'Jeddah','JED',96),(98,'Singapore','Singapore',NULL),(99,'Singapura','SIN',98),(100,'South Korea','South Korea',NULL),(101,'Seoul','ICN',100),(102,'Busan','PUS',100),(103,'Sri Lanka','Sri Lanka',NULL),(104,'Colombo','CMB',103),(105,'Taiwan','Taiwan',NULL),(106,'Taipei','TPE',105),(107,'Thailand','Thailand',NULL),(108,'Trang','TST',107),(109,'Phitsanulok','PHS',107),(110,'Khon Kaen','KKC',107),(111,'Chiang Mai','CNX',107),(112,'Chiang Rai','CEI',107),(113,'Hat Yai','HDY',107),(114,'Krabi','KBV',107),(115,'Nakhon Si Thammarat','NST',107),(116,'Narathiwat','NAW',107),(117,'Nakhon Phanom','KOP',107),(118,'Phuket','HKT',107),(119,'Surat Thani','URT',107),(120,'Ubon Ratchathani','UBP',107),(121,'Udon Thani','UTH',107),(122,'Bangkok – Don Mueang','DMK',107),(123,'Vietnam','Vietnam',NULL),(124,'Hanoi','HAN',123),(125,'Ho Chi Minh (Saigon)','SGN',123);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
