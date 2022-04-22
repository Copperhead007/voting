//Servername : voter.database.windows.net
//What else is needed?
//Uses Transparent Data Encryption at Rest implements Bring Your Own Key Scenario
CREATE TABLE Voter (
id int NOT NULL IDENTITY(1,1),
name varchar(128) NOT NULL,
pass varchar(128) NOT NULL,
voted bit NOT NULL DEFAULT 0,
)
//Was this
CREATE TABLE Votes(
 Pres int DEFAULT 0,
 Senate int DEFAULT 0,
 House int DEFAULT 0
 )
 CREATE TABLE Candidates(
office varchar(128) NOT NULL,
party varchar (128) NOT NULL,
votes int DEFAULT 0)
//Now
CREATE TABLE presidential(
candname varchar(128) NOT NULL,
party varchar (128) NOT NULL,
votes int NOT NULL DEFAULT 0)
CREATE TABLE senate(
candname varchar(128) NOT NULL,
party varchar (128) NOT NULL,
votes int NOT NULL DEFAULT 0)
CREATE TABLE house(
candname varchar(128) NOT NULL,
party varchar (128) NOT NULL,
votes int NOT NULL DEFAULT 0)

 INSERT INTO Voter(name,pass)
 VALUES('Ctest','hooligan');
 INSERT INTO presidential(candname,party)
VALUES('Mark Joe','Democratic');
INSERT INTO presidential(candname,party)
VALUES('John Marsh','Democratic');
INSERT INTO presidential(candname,party)
VALUES('Kevin Shane','Republican');
INSERT INTO presidential(candname,party)
VALUES('David Warner','Republican');

INSERT INTO senate(candname,party)
VALUES('Gowtham Varu','Democratic');
INSERT INTO senate(candname,party)
VALUES('Mahinder Rana','Republican');

INSERT INTO house(candname,party)
VALUES('Kaif Malik','Democratic');
INSERT INTO house(candname,party)
VALUES('Jacob John','Republican');
 
CREATE VIEW details AS SELECT name,pass FROM Voter WHERE id < 3;
SELECT * FROM details;

CREATE VIEW presDetails AS SELECT candname,party,votes FROM presidential;
SELECT * FROM presDetails;

CREATE VIEW details2 AS SELECT name,pass,id,voted FROM Voter WHERE id < 3;
SELECT * FROM details2

//Shows  name|pass    | id | voted
//    1 Ctest|hooligan|  1 | 0
//    2 2Test|excited |  2 | 0 
//Used Microsoft SQL Server Management Software to Connect with Azure Cloud Hosted Server & Database
