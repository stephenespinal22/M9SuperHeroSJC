drop database if exists SuperHero;
Create database SuperHero;

Use SuperHero;

create table SuperPersons( 
superId int primary key auto_increment,
`name` varchar(50) not null, 
`description` varchar(255) not null,
isVillain boolean not null,
imagePath varchar(200)

);

create table Organizations( 
orgId int primary key auto_increment,
`name` varchar(50) not null, 
`description` varchar(255) not null,
contactInfo varchar(255) not null

);

create table Powers( 
powId int primary key auto_increment,
powerName varchar(50) not null

);

create table Locations( 
locationId int primary key auto_increment,
`name` varchar(255) not null,
`description` varchar(255) not null,
address varchar(255) not null,
longitude decimal(9,6),
latitude decimal(9,6)

);

create table Sightings( 
sightingId int primary key auto_increment,
`description` varchar(255) not null,
locationId int not null,
sightingDate varchar(50) not null,

FOREIGN KEY fk_Locations_locationId(locationId) REFERENCES Locations(locationId) 

);

CREATE TABLE SuperPersonPower(
superId INT NOT NULL,
powId INT NOT NULL,

SuperPersonPowerId INT PRIMARY KEY AUTO_INCREMENT NOT NULL,

FOREIGN KEY fk_SuperPersons_superId(superId) REFERENCES SuperPersons(superId),
FOREIGN KEY fk_Powers_powId(powId) REFERENCES Powers(powId)

);

CREATE TABLE SuperPersonSighting(
superId INT NOT NULL,
sightingId INT NOT NULL,

SuperPersonSightingId INT PRIMARY KEY AUTO_INCREMENT NOT NULL,

FOREIGN KEY fk_SuperPersons_superId(superId) REFERENCES SuperPersons(superId),
FOREIGN KEY fk_Sightings_sightingId(sightingId) REFERENCES Sightings(sightingId)

);

CREATE TABLE SuperPersonOrganization(
superId INT NOT NULL,
orgId INT NOT NULL,

spoId INT PRIMARY KEY AUTO_INCREMENT NOT NULL,

FOREIGN KEY fk_SuperPersons_superId(superId) REFERENCES SuperPersons(superId),
FOREIGN KEY fk_Organizations_orgId(orgId) REFERENCES Organizations(orgId)

);

