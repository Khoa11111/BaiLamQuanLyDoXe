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
	chucVu varchar(10) not null,
	SDT varchar(11) not null,
	tenTK varchar(50) not null
)
go
--Tạo table DiaChi
create table DiaChi(
	maNV varchar(10) not null primary key,
	XaPhuong varchar(30) not null,
	QuanHuyen varchar(30) null,
	TinhTP varchar(30) null,
)
go
--Tạo table TaiKhoan
create table TaiKhoan(
	tenTK varchar(50) not null primary key,
	matKhau varchar(30) not null
)
go
--Tạo table QuanLyXe
create table QuanLyXe(
	bienSoXe varchar(15) not null primary key,
	loaiXe varchar(30) not null,
	tenXe varchar(30) not null,
	mauXe varchar(30) not null
)
go
--Tạo table QuanLyVe
create table QuanLyVe(
	maVe varchar(10) not null primary key,
	loaiVe varchar(30) not null,
	giaVe money not null,
	trangThaiVe varchar(20) not null
)
go
--Tạo table QuanLyBaiXe
create table QuanLyBaiXe(
	maBanGhi varchar(10) not null primary key,
	bienSoXe varchar(15) not null,
	maVe varchar(10) not null,
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
--2/Thêm Foreign Key
--Thêm Foreỉgn key cho bảng DiaChi
alter table DiaChi
add constraint FK_maNV_DiaChi foreign key(maNV) references NhanVien(maNV)
go
--Thêm Foreỉgn key cho bảng NhanVien
alter table NhanVien
add constraint FK_tenTK_NhanVien foreign key(tenTK) references TaiKhoan(tenTK)
go
--Thêm Foreỉgn key cho bảng QuanLyBaiXe
alter table QuanLyBaiXe
add constraint FK_bienSoXe_QuanLyBaiXe foreign key(bienSoXe) references QuanLyXe(bienSoXe),
	constraint FK_maVe_QuanLyBaiXe foreign key(maVe) references QuanLyVe(maVe)
go
--3/Chèn dữ liệu
--Chèn dữ liệu vào bảng TaiKhoan
insert into TaiKhoan
values ('Phi123','khoa4431'),
	   ('Nhi334','khoa4431'),
	   ('Dung977','long3124')
go
--Chèn dữ liệu vào bảng NhanVien
set dateformat dmy
insert into NhanVien(maNV,tenNV,ngaysinh,gioitinh,chucVu,SDT,tenTK)
values ('205131','Hoang Thi Phi','2/10/2002','nu','Nhan Vien','0968845331','Phi123'),
	   ('205123','Thai Thi Nhi','8/4/1998','nu','Nhan Vien','0899431732','Nhi334'),
	   ('205177','Tran Hung Dung','13/9/1995','nam','Quan Ly','0981445678','Dung977')
go
--Chèn dữ liệu vào bảng QuanLyXe
insert into QuanLyXe
values ('K2309','Xe may','Honda','Den'),
	   ('K2311','Xe oto','Toyota','Trang')
go
--Chèn dữ liệu vào bảng QuanLyVe
insert into QuanLyVe
values ('VN000001','Ve ngay',5000,'Tot'),
	   ('VT000001','Ve thang',130000,'Tot'),
	   ('VT000002','Ve thang',130000,'Bi hu')
go
--Chèn dữ liệu vào bảng QuanLyBaiXe
insert into QuanLyBaiXe
values ('BG001','K2309','VN000001','10/3/2002','2022/5/4'),
	   ('BG002','K2311','VT000001','11/9/2003',null)