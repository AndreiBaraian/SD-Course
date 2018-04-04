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
