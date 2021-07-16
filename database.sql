create database Assignment
go
use Assignment
go
create table student(
	masv nvarchar(50) not null,
	hoten nvarchar(50),
	email nvarchar(50),
	sdt nvarchar(15),
	gioiTinh bit,
	diachi nvarchar(50),
	hinh nvarchar(50),
	primary key(masv),
)
go
if OBJECT_ID('grade') is not null
	drop table grade
go
create table grade(
	id int not null identity,
	masv nvarchar(50) not null,
	tienganh float,
	tinhoc float,
	gdtc float,
	primary key (id),
	foreign key (masv) references student(masv)
)
go
create table users(
	username nvarchar(50) not null,
	pass_word nvarchar(50) not null,
	role nvarchar(50),
	primary key(username)
)
go

insert into grade values('SV001',9.6,10,9)
insert into grade values('SV002',9,8,9)
insert into grade values('SV003',8,7,7)
insert into grade values('SV004',8.9,8,9)

