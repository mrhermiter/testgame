-- create database
CREATE DATABASE IF NOT EXISTS `test_db`;
CREATE USER 'usertest'@'localhost' IDENTIFIED BY 'usertest';
GRANT ALL ON `test_db`.* TO 'usertest'@'localhost';

-- create tables
CREATE TABLE IF NOT EXISTS `test_db`.`users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rating` int(11) NOT NULL,
  `damage` int(11) NOT NULL,
  `health` int(11) NOT NULL,
  `fight_health` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  `fightID` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `test_db`.`fights` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `test_db`.`fights_progress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fightID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `damage` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;