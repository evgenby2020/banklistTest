CREATE DATABASE  IF NOT EXISTS `banklist` ;
USE `banklist`;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userid` INT,
  `name` VARCHAR(45),
  `sureName` VARCHAR(45),
  PRIMARY KEY (`userid`)
);


LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES (1,'Ivan','Ivanov'),(2,'Vadim','Vadimov'),(3,'Igor','Letvinov'),(4,'Vasya','Ivanov'),(5,'Ivan','Petrov'),
(6,'Vadim','Lenin'),(7,'Alex','Sidorov'),(8,'Petr','Petrov'),(9,'Sergey','Sidorov'),(10,'Dima','Ilyin');
UNLOCK TABLES;


DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `accountid` INT,
  `account` INT,
  `userid` INT,
  PRIMARY KEY (`accountid`),
  FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
);

LOCK TABLES `account` WRITE;

INSERT INTO `account` VALUES (1,100,1),(2,200,2),(3,300,3),(4,400,4),(5,500,5),(6,600,6),(7,700,7),(8,800,8),(9,900,9),(10,1000,10);

UNLOCK TABLES;





