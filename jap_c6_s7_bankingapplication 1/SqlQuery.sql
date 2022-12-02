create database BankProject;
use BankProject;


select * from BankInfo;
select * from customer;
select * from account;
select * from accounttype;
select * from transaction;


drop table BankInfo;
drop table account;
drop table customer;
drop table accounttype;
drop table transaction;


create table BankInfo(IFSCCode bigint not null primary key,City varchar(20)
not null,BranchId varchar(20) unique not null,BranchName varchar(20) not null);


insert into  BankInfo values(10001,"Kavi nagar","idfc1234","idfcKAV");
insert into  BankInfo values(10002,"Rajnagar","idfc1235","idfcRAJ");
insert into  BankInfo values(10003,"RDC","idfc1236","idfcRDC");
insert into  BankInfo values(10004,"Shastri nagar","idfc1237","idfcSHA");


create table customer (cID int primary key auto_increment,
Name varchar(20) not null,
Age int not null check (Age>=18),
Address varchar(50) not null,
Aadhar int unique not null);


alter table customer auto_increment=100;


create table account(Account_no bigint primary key,
Account_type int not null,
Account_Open_Date varchar(20) not null,
 balance int not null,
 ifsc_Code varchar(10), cID int auto_increment,
constraint c1 foreign key(cID) references customer(cID),
constraint c4 foreign key(Account_type) references AccountType(Acc_type));

create table transaction(txnID int primary key auto_increment,
txnDate varchar(20) not null,
Account_no bigint, operation varchar(20) not null, amount int not null,
constraint c2 foreign key(Account_no) references account(Account_no));



create table AccountType(Acc_type int primary key, Description varchar(20));


insert into customer values (101,'Stefan',29,'Kanpur',852369147);
insert into customer values (null,'DAmon',21,'Mystic',1452369871);
insert into customer (Name,age,address,Aadhar) values ('Kai', 29, 'Paris',290865157);


insert into accountType values (1,'Saving');
insert into accountType values (2,'Payment');


insert into account values (123456,1,'10-02-2020',1000,10010,101);
insert into account values (12345,2,'05-01-2021',1050,10011,102);
insert into account (Account_no,Account_type,Account_open_date,balance, ifsc_code)
values(12371,2,'26-06-2021',1100,10012);

insert into transaction values (1001,'10-03-2021',123455,'credit',500);
insert into transaction values (1002,'12-01-2022',12345,'withdraw',400);