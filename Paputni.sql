create database paputka;
use paputka;

create table Users (
	ID int auto_increment not null primary key,
	FirstName varchar(20),
	LastName varchar(20),
	Gender boolean,
	BirthDate date,
	Status varchar(200),
	Password varchar(64),
	EMail varchar(40) unique 
);

create table Avatars(
	ID int auto_increment not null primary key,
	UserID int,
	Image varchar(50),
	constraint foreign key (UserID) references Users(ID)
);

create table Tel(
	ID int auto_increment not null primary key,
	UserID int,
	PhoneNumber varchar(25),
	constraint foreign key (UserID) references Users(ID)
);

create table Ratings(
	ID int auto_increment not null primary key,
	FirstID int,
	SecondID int,
	Rating int,
	constraint foreign key (FirstID) references Users(ID),
	constraint foreign key (SecondID) references Users(ID)
);

create table Events(
	ID int auto_increment not null primary key,
	UserID int,
	Places int,
	Fee double,
	FromLongitude double,
	FromLatitude double,
	ToLongitude double,
	ToLatitude double,
	FromPlace varchar(30),
	ToPlace varchar(30),
	Type boolean,
	Validation boolean,
	constraint foreign key (UserID) references Users(ID)
);

create table Participants(
	ID int auto_increment not null primary key,
	UserID int,
	EventID int,
	constraint foreign key (UserID) references Users(ID),
	constraint foreign key (EventID) references Events(ID)
);

create table Comments(
	ID int auto_increment not null primary key,
	UserID int,
	EventID int,
	Comment varchar(400),
	Date datetime,
	constraint foreign key (UserID) references Users(ID),
	constraint foreign key (EventID) references Events(ID)
);

create table Dates(
	ID int auto_increment not null primary key,
	EventID int,
	Date datetime,
	constraint foreign key (EventID) references Events(ID)
);

create table Everyday(
	ID int auto_increment not null primary key,
	EventID int,
	Day int,
	StartTime time,
	constraint foreign key (EventID) references Events(ID)
);

create table WantToGo(
	ID int auto_increment not null primary key,
	UserID int,
	Title varchar(100),
	FromLongitude double,
	FromLatitude double,
	ToLongitude double,
	ToLatitude double,
	Type boolean,
	constraint foreign key (UserID) references Users(ID)
);

create table WantToGoDates(
	ID int auto_increment not null primary key,
	WantToGoID int,
	StartDate datetime,
	EndDate datetime,
	constraint foreign key (WantToGoID) references WantToGo(ID)
);

create table WantToGoEveryday(
	ID int auto_increment not null primary key,
	WantToGoID int,
	Day int,
	StartDate time,
	EndDate time,
	constraint foreign key (WantToGoID) references WantToGo(ID)
);

create table Requests(
	ID int auto_increment not null primary key,
	UserID int,
	EventID int,
	Text varchar(500),
	Acception boolean,
	Date datetime,
	constraint foreign key (UserID) references Users(ID),
	constraint foreign key (EventID) references Events(ID)
);

