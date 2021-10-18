CREATE DATABASE IF NOT EXISTS `bookshop`;

USE `bookshop`;

CREATE TABLE publisher(
	idpublisher INT(10),
    name VARCHAR(45),
    location VARCHAR(45),
    PRIMARY KEY (idpublisher)
);

CREATE TABLE author(
	idauthor INT(10),
    firstname VARCHAR(45),
    lastname VARCHAR(45),
    biography VARCHAR(511),
    PRIMARY KEY (idauthor)
);

CREATE TABLE book(
	idbook INT(10),
    title VARCHAR(45),
    price float,
    category VARCHAR(45),
    publicationyear YEAR,
    numpages INT(11),
    _idpublisher INT(10),
    quantity INT(11),
    PRIMARY KEY(idbook),
    FOREIGN KEY (_idpublisher) REFERENCES publisher(idpublisher)
);

CREATE TABLE book_has_author(
	_idbook INT(10),
    _idauthor INT(10),
    PRIMARY KEY(_idbook, _idauthor),
    FOREIGN KEY (_idbook) REFERENCES book(idbook),
    FOREIGN KEY (_idauthor) REFERENCES author(idauthor)
);

CREATE TABLE employee(
	idemployee INT(10),
    companyname VARCHAR(45),
    PRIMARY KEY (idemployee, companyname)
);