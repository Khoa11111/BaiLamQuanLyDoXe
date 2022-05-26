-- Tạo database
create database QuanLyDoXe
go
use QuanLyDoXe
go
--1/ Tạo table
--Tạo table NhanVien
create table NhanVien(
	maNV varchar(10) not null primary key,
	tenNV varchar(50) not null,
	ngaysinh date not null,
	gioitinh varchar(10) not null,
	loaiNV varchar(10) not null,
	SDT varchar(11) not null,
	matkhauNV varchar(50) not null
)
go
--Tạo table TaiKhoan
create table TaiKhoan(
	tenTK varchar(50) not null primary key,
	matKhau varchar(30) not null
)
go
--Tạo table QuanLyVe
create table QuanLyVe(
	maVe varchar(10) not null primary key,
	loaiVe varchar(30) not null,
	giaVe money not null,
	trangThaiVe varchar(20) not null,
	SoLuong int not null
)
go
--Tạo table QuanLyXe
create table QuanLyXe(
	bienSoXe varchar(15) not null primary key,
	maVe varchar(10) not null,
	loaiXe varchar(30) not null,
	tenXe varchar(30) not null,
	mauXe varchar(30) not null,
	ngayVaoBai date not null,
	ngayRaBai date null
)
go
--Tạo table TroGiup
create table TroGiup(
	maTG varchar(10) not null primary key,
	Ten varchar(50) not null,
	Email varchar(30) not null,
	SDT varchar(11) not null,
	noiDung varchar(100) not null,
)
go
--2/Chèn dữ liệu
--Chèn dữ liệu vào bảng TaiKhoan
set dateformat dmy
insert into TaiKhoan
values ('sa','12345')
go
--Chèn dữ liệu vào bảng NhanVien
insert into NhanVien(maNV,tenNV,ngaysinh,gioitinh,loaiNV,SDT,matKhauNV)
values ('205131','Hoang Thi Phi','2/10/2002','nu','Nhan Vien','0968845331','Phi123'),
	   ('205123','Thai Thi Nhi','8/4/1998','nu','Nhan Vien','0899431732','Nhi334'),
	   ('205177','Tran Hung Dung','13/9/1995','nam','Quan Ly','0981445678','Dung977')
go
--Chèn dữ liệu vào bảng QuanLyVe
insert into QuanLyVe
values ('VN000001','Ve ngay',5000,'Tot',50),
	   ('VN000002','Ve thang',130000,'Tot',30),
	   ('VN000003','Ve thang',130000,'Bi hu',60)

--Chèn dữ liệu vào bảng QuanLyXe
go
set dateformat dmy insert into QuanLyXe
values ('K2309','VN000001','Xe may','Honda','Den','13/4/2022','14/4/2022'),
	   ('K2311','VN000002','Xe oto','Toyota','Trang','13/4/2022','14/4/2022'),
	   ('K2312','VN000002','Xe oto','Toyota','Trang','13/4/2022',null),
	   ('K2313','VN000001','Xe oto','Toyota','Trang','13/4/2022',null),
	   ('K2314','VN000003','Xe oto','Toyota','Trang','13/4/2022',null)