CREATE TABLE `admintable` (
  `id` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(250) DEFAULT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `theaterdb`.`admintable`
(`id`,
`username`,
`password`,
`firstName`,
`lastName`)
VALUES
(1,
'admin',
'8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918',
'Andrei',
'Baraian');

INSERT INTO `theaterdb`.`admintable`
(`id`,
`username`,
`password`,
`firstName`,
`lastName`)
VALUES
(2,
'admin1',
'25F43B1486AD95A1398E3EEB3D83BC4010015FCC9BEDB35B432E00298D5021F7',
'George',
'Baraian');

CREATE TABLE `cashiertable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(250) DEFAULT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

INSERT INTO `theaterdb`.`cashiertable`
(`id`,
`username`,
`password`,
`firstName`,
`lastName`)
VALUES
(1,
'dmitrea',
'52C36689038C38B0388B76B9260526E7687841B5E6A01733B11B0593680E2D60',
'Dan',
'Mitrea');

INSERT INTO `theaterdb`.`cashiertable`
(`id`,
`username`,
`password`,
`firstName`,
`lastName`)
VALUES
(2,
'mbirle',
'7207D814D6C962DB1E74C76E3F2A75D6D0F26F63546B89CF0DCC4C385BC21C08',
'Matei',
'Birle');

INSERT INTO `theaterdb`.`cashiertable`
(`id`,
`username`,
`password`,
`firstName`,
`lastName`)
VALUES
(3,
'vpeter',
'DCEF145481886A7F9D06E7AE0029DEFDFD52F248E852EAB1E7FDC771A12ECB53',
'Vlad',
'Peter');


CREATE TABLE `showtable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `genre` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `distributionList` varchar(300) DEFAULT NULL,
  `dateOfShow` varchar(45) DEFAULT NULL,
  `numberOfTickets` int(11) DEFAULT NULL,
  `remainingTickets` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `theaterdb`.`showtable`
(`id`,
`genre`,
`title`,
`distributionList`,
`dateOfShow`,
`numberOfTickets`,
`remainingTickets`)
VALUES
(1,
'Ballet',
'Swan Lake',
'Someone Someone Someone Someone',
'12/12/2017',
35,
35);

INSERT INTO `theaterdb`.`showtable`
(`id`,
`genre`,
`title`,
`distributionList`,
`dateOfShow`,
`numberOfTickets`,
`remainingTickets`)
VALUES
(2,
'Opera',
'Traviata',
'Someone Someone Someone Anyone',
'15/03/2018',
3,
3);

INSERT INTO `theaterdb`.`showtable`
(`id`,
`genre`,
`title`,
`distributionList`,
`dateOfShow`,
`numberOfTickets`,
`remainingTickets`)
VALUES
(3,
'Opera',
'Figaro',
'Anyone Anyone Someone Anyone',
'25/09/2018',
2,
2);

CREATE TABLE `tickettable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seatRow` int(11) DEFAULT NULL,
  `seatCol` int(11) DEFAULT NULL,
  `showId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

INSERT INTO `theaterdb`.`tickettable`
(`id`,
`seatRow`,
`seatCol`,
`showId`)
VALUES
(1,
0,
1,
1);

INSERT INTO `theaterdb`.`tickettable`
(`id`,
`seatRow`,
`seatCol`,
`showId`)
VALUES
(2,
0,
2,
1);

INSERT INTO `theaterdb`.`tickettable`
(`id`,
`seatRow`,
`seatCol`,
`showId`)
VALUES
(3,
0,
3,
1);

INSERT INTO `theaterdb`.`tickettable`
(`id`,
`seatRow`,
`seatCol`,
`showId`)
VALUES
(4,
0,
4,
1);

INSERT INTO `theaterdb`.`tickettable`
(`id`,
`seatRow`,
`seatCol`,
`showId`)
VALUES
(5,
0,
1,
3);