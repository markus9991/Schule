DROP DATABASE IF EXISTS Buchverleih;
CREATE DATABASE IF NOT EXISTS Buchverleih;
USE Buchverleih;

 
CREATE TABLE Buch(
BuchId INT ,
Titel VARCHAR(50) NOT NULL,
Autor VARCHAR(40) NOT NULL,
Verlag VARCHAR(20) NOT NULL,
Genre VARCHAR(20) NOT NULL,
PRIMARY KEY(BuchId)
);

CREATE TABLE Kunde(
KundenNr int,
Vorname VARCHAR(20) NOT NULL,
Nachname VARCHAR(20) NOT NULL,
Straﬂe VARCHAR(20) NOT NULL,
Hausnummer int NOT NULL,
Ort VARCHAR(20) NOT NULL,
PLZ int NOT NULL,
Geschlecht char(1) NOT NULL,
PRIMARY KEY(KundenNR)
);

CREATE TABLE Verleih(
VerleihNr int AUTO_INCREMENT,
BuchId INT NOT NULL,
KundenNr INT NOT NULL,
von DATE NOT NULL,
bis DATE NOT NULL,
PRIMARY KEY(VerleihNr)
);

