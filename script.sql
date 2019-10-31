USE [master]
GO
/****** Object:  Database [SmartphoneShop]    Script Date: 3/21/2019 3:52:34 PM ******/
CREATE DATABASE [SmartphoneShop]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SmartphoneShop', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\SmartphoneShop.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'SmartphoneShop_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\SmartphoneShop_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [SmartphoneShop] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SmartphoneShop].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SmartphoneShop] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SmartphoneShop] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SmartphoneShop] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SmartphoneShop] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SmartphoneShop] SET ARITHABORT OFF 
GO
ALTER DATABASE [SmartphoneShop] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SmartphoneShop] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [SmartphoneShop] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SmartphoneShop] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SmartphoneShop] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SmartphoneShop] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SmartphoneShop] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SmartphoneShop] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SmartphoneShop] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SmartphoneShop] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SmartphoneShop] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SmartphoneShop] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SmartphoneShop] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SmartphoneShop] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SmartphoneShop] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SmartphoneShop] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SmartphoneShop] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SmartphoneShop] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SmartphoneShop] SET RECOVERY FULL 
GO
ALTER DATABASE [SmartphoneShop] SET  MULTI_USER 
GO
ALTER DATABASE [SmartphoneShop] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SmartphoneShop] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SmartphoneShop] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SmartphoneShop] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
EXEC sys.sp_db_vardecimal_storage_format N'SmartphoneShop', N'ON'
GO
USE [SmartphoneShop]
GO
/****** Object:  Table [dbo].[ChiNhanh]    Script Date: 3/21/2019 3:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiNhanh](
	[maCN] [int] IDENTITY(1,1) NOT NULL,
	[tenChiNhanh] [nvarchar](225) NULL,
	[diaChi] [nvarchar](225) NULL,
 CONSTRAINT [PK_ChiNhanh] PRIMARY KEY CLUSTERED 
(
	[maCN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[DanhMuc]    Script Date: 3/21/2019 3:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DanhMuc](
	[maDM] [int] IDENTITY(1,1) NOT NULL,
	[tenDM] [nvarchar](225) NULL,
 CONSTRAINT [PK_DanhMuc] PRIMARY KEY CLUSTERED 
(
	[maDM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[GiaoDich]    Script Date: 3/21/2019 3:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GiaoDich](
	[maHD] [int] NOT NULL,
	[maMH] [int] NOT NULL,
	[soLuong] [int] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HoaDonOffline]    Script Date: 3/21/2019 3:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonOffline](
	[maHD] [int] IDENTITY(1,1) NOT NULL,
	[maNV] [int] NOT NULL,
	[maKH] [int] NOT NULL,
	[ngayLap] [date] NULL,
	[total] [bigint] NULL,
 CONSTRAINT [PK_HoaDonOffline] PRIMARY KEY CLUSTERED 
(
	[maHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HoaDonWeb]    Script Date: 3/21/2019 3:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonWeb](
	[maHD] [int] IDENTITY(1,1) NOT NULL,
	[maKH] [int] NOT NULL,
	[ngayLap] [date] NULL,
	[total] [bigint] NULL,
 CONSTRAINT [PK_HoaDonWeb] PRIMARY KEY CLUSTERED 
(
	[maHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 3/21/2019 3:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKH] [int] IDENTITY(1,1) NOT NULL,
	[tenKH] [nvarchar](225) NULL,
	[maCN] [int] NOT NULL,
	[diaChi] [nvarchar](225) NULL,
	[phone] [nvarchar](225) NULL,
 CONSTRAINT [PK_KhachHang] PRIMARY KEY CLUSTERED 
(
	[maKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[MatHang]    Script Date: 3/21/2019 3:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MatHang](
	[maMH] [int] IDENTITY(1,1) NOT NULL,
	[maCN] [int] NOT NULL,
	[maDM] [int] NOT NULL,
	[tenMH] [nvarchar](225) NULL,
	[hangMH] [nvarchar](225) NULL,
	[img] [nvarchar](225) NULL,
	[giaMH] [nvarchar](225) NULL,
	[soLuong] [int] NULL,
	[manHinh] [nvarchar](225) NULL,
	[heDieuHanh] [nvarchar](225) NULL,
	[camera] [nvarchar](225) NULL,
	[cpu] [nvarchar](225) NULL,
	[ram] [nvarchar](225) NULL,
	[pin] [nvarchar](225) NULL,
 CONSTRAINT [PK_MatHang] PRIMARY KEY CLUSTERED 
(
	[maMH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 3/21/2019 3:52:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNV] [int] IDENTITY(1,1) NOT NULL,
	[userName] [nvarchar](225) NULL,
	[passWord] [nvarchar](225) NULL,
	[hoTen] [nvarchar](225) NULL,
	[diaChi] [nvarchar](225) NULL,
	[phone] [nvarchar](225) NULL,
	[maCN] [int] NULL,
	[chucVu] [nvarchar](225) NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[GiaoDich]  WITH CHECK ADD  CONSTRAINT [FK_GiaoDich_HoaDonOffline] FOREIGN KEY([maHD])
REFERENCES [dbo].[HoaDonOffline] ([maHD])
GO
ALTER TABLE [dbo].[GiaoDich] CHECK CONSTRAINT [FK_GiaoDich_HoaDonOffline]
GO
ALTER TABLE [dbo].[GiaoDich]  WITH CHECK ADD  CONSTRAINT [FK_GiaoDich_HoaDonWeb] FOREIGN KEY([maHD])
REFERENCES [dbo].[HoaDonWeb] ([maHD])
GO
ALTER TABLE [dbo].[GiaoDich] CHECK CONSTRAINT [FK_GiaoDich_HoaDonWeb]
GO
ALTER TABLE [dbo].[GiaoDich]  WITH CHECK ADD  CONSTRAINT [FK_GiaoDich_MatHang] FOREIGN KEY([maMH])
REFERENCES [dbo].[MatHang] ([maMH])
GO
ALTER TABLE [dbo].[GiaoDich] CHECK CONSTRAINT [FK_GiaoDich_MatHang]
GO
ALTER TABLE [dbo].[HoaDonOffline]  WITH CHECK ADD  CONSTRAINT [FK_HoaDonOffline_KhachHang] FOREIGN KEY([maKH])
REFERENCES [dbo].[KhachHang] ([maKH])
GO
ALTER TABLE [dbo].[HoaDonOffline] CHECK CONSTRAINT [FK_HoaDonOffline_KhachHang]
GO
ALTER TABLE [dbo].[HoaDonOffline]  WITH CHECK ADD  CONSTRAINT [FK_HoaDonOffline_NhanVien] FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[HoaDonOffline] CHECK CONSTRAINT [FK_HoaDonOffline_NhanVien]
GO
ALTER TABLE [dbo].[HoaDonWeb]  WITH CHECK ADD  CONSTRAINT [FK_HoaDonWeb_KhachHang] FOREIGN KEY([maKH])
REFERENCES [dbo].[KhachHang] ([maKH])
GO
ALTER TABLE [dbo].[HoaDonWeb] CHECK CONSTRAINT [FK_HoaDonWeb_KhachHang]
GO
ALTER TABLE [dbo].[KhachHang]  WITH CHECK ADD  CONSTRAINT [FK_KhachHang_ChiNhanh] FOREIGN KEY([maCN])
REFERENCES [dbo].[ChiNhanh] ([maCN])
GO
ALTER TABLE [dbo].[KhachHang] CHECK CONSTRAINT [FK_KhachHang_ChiNhanh]
GO
ALTER TABLE [dbo].[MatHang]  WITH CHECK ADD  CONSTRAINT [FK_MatHang_ChiNhanh] FOREIGN KEY([maCN])
REFERENCES [dbo].[ChiNhanh] ([maCN])
GO
ALTER TABLE [dbo].[MatHang] CHECK CONSTRAINT [FK_MatHang_ChiNhanh]
GO
ALTER TABLE [dbo].[MatHang]  WITH CHECK ADD  CONSTRAINT [FK_MatHang_DanhMuc] FOREIGN KEY([maDM])
REFERENCES [dbo].[DanhMuc] ([maDM])
GO
ALTER TABLE [dbo].[MatHang] CHECK CONSTRAINT [FK_MatHang_DanhMuc]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_ChiNhanh1] FOREIGN KEY([maCN])
REFERENCES [dbo].[ChiNhanh] ([maCN])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_ChiNhanh1]
GO
USE [master]
GO
ALTER DATABASE [SmartphoneShop] SET  READ_WRITE 
GO
