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