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
