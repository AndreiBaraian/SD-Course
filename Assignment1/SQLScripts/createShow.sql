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

