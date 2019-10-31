USE [BookStore]
GO
/****** Object:  Table [dbo].[ChiNhanh]    Script Date: 4/3/2019 5:49:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiNhanh](
	[maCN] [int] IDENTITY(1,1) NOT NULL,
	[ten] [nvarchar](225) NULL,
	[diaChi] [nvarchar](225) NULL,
 CONSTRAINT [PK_ChiNhanh] PRIMARY KEY CLUSTERED 
(
	[maCN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 4/3/2019 5:49:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[maHD] [int] IDENTITY(1,1) NOT NULL,
	[maNV] [int] NULL,
	[maKH] [int] NOT NULL,
	[ngayLap] [date] NULL,
	[tongTien] [int] NULL,
	[kieuHD] [nvarchar](50) NULL,
 CONSTRAINT [PK_HoaDonOffline] PRIMARY KEY CLUSTERED 
(
	[maHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 4/3/2019 5:49:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKH] [int] NOT NULL,
	[tenKhachHang] [nvarchar](225) NULL,
	[maCN] [int] NOT NULL,
	[diaChi] [nvarchar](225) NULL,
	[sdt] [nvarchar](225) NULL,
 CONSTRAINT [PK_KhachHang] PRIMARY KEY CLUSTERED 
(
	[maKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 4/3/2019 5:49:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNV] [int] IDENTITY(1,1) NOT NULL,
	[taiKhoan] [nvarchar](225) NULL,
	[matKhau] [nvarchar](225) NULL,
	[hoTen] [nvarchar](225) NULL,
	[diaChi] [nvarchar](225) NULL,
	[sdt] [nvarchar](225) NULL,
	[maCN] [int] NULL,
	[chucvu] [nvarchar](225) NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[OrderItems]    Script Date: 4/3/2019 5:49:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderItems](
	[maHD] [int] NOT NULL,
	[maSach] [int] NOT NULL,
	[soLuong] [int] NULL,
 CONSTRAINT [PK_GiaoDich] PRIMARY KEY CLUSTERED 
(
	[maHD] ASC,
	[maSach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Sach]    Script Date: 4/3/2019 5:49:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sach](
	[maSach] [int] NOT NULL,
	[maLS] [int] NOT NULL,
	[tenSach] [nvarchar](225) NOT NULL,
	[nhaSanXuat] [nvarchar](225) NULL,
	[img] [nvarchar](225) NULL,
	[giaBan] [nvarchar](225) NULL,
	[tacGia] [nvarchar](225) NULL,
	[quocGia] [nvarchar](225) NULL,
	[nguoiBienDich] [nvarchar](225) NULL,
	[kichThuoc] [nvarchar](225) NULL,
	[moTa] [nvarchar](225) NULL,
	[ngayXuatBan] [nvarchar](225) NULL,
 CONSTRAINT [PK_MatHang_1] PRIMARY KEY CLUSTERED 
(
	[maSach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SachThuocChiNhanh]    Script Date: 4/3/2019 5:49:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SachThuocChiNhanh](
	[maCN] [int] NOT NULL,
	[maSach] [int] NOT NULL,
	[soLuong] [int] NOT NULL,
 CONSTRAINT [PK_ChiNhanhMatHang] PRIMARY KEY CLUSTERED 
(
	[maCN] ASC,
	[maSach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[TheLoaiSach]    Script Date: 4/3/2019 5:49:14 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TheLoaiSach](
	[maLs] [int] IDENTITY(1,1) NOT NULL,
	[tenLS] [nvarchar](225) NULL,
 CONSTRAINT [PK_DanhMuc] PRIMARY KEY CLUSTERED 
(
	[maLs] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[ChiNhanh] ON 

INSERT [dbo].[ChiNhanh] ([maCN], [ten], [diaChi]) VALUES (1, N'Hà Nội', N'Hà Đông, Hà Nội')
INSERT [dbo].[ChiNhanh] ([maCN], [ten], [diaChi]) VALUES (2, N'Hồ Chí Minh', N'TP. Hồ Chí Minh')
SET IDENTITY_INSERT [dbo].[ChiNhanh] OFF
SET IDENTITY_INSERT [dbo].[HoaDon] ON 

INSERT [dbo].[HoaDon] ([maHD], [maNV], [maKH], [ngayLap], [tongTien], [kieuHD]) VALUES (1, 1, 1, CAST(0x2D210B00 AS Date), 2000000, N'Offline')
INSERT [dbo].[HoaDon] ([maHD], [maNV], [maKH], [ngayLap], [tongTien], [kieuHD]) VALUES (2, 1, 2, CAST(0x1E400B00 AS Date), 2600000, N'Offline')
INSERT [dbo].[HoaDon] ([maHD], [maNV], [maKH], [ngayLap], [tongTien], [kieuHD]) VALUES (3, 1, 1, CAST(0xD83F0B00 AS Date), 1500000, N'Offline')
INSERT [dbo].[HoaDon] ([maHD], [maNV], [maKH], [ngayLap], [tongTien], [kieuHD]) VALUES (4, 1, 2, CAST(0xD83F0B00 AS Date), 200000, N'Online')
INSERT [dbo].[HoaDon] ([maHD], [maNV], [maKH], [ngayLap], [tongTien], [kieuHD]) VALUES (6, 4, 5, CAST(0x7B3F0B00 AS Date), 149980000, N'Offline')
INSERT [dbo].[HoaDon] ([maHD], [maNV], [maKH], [ngayLap], [tongTien], [kieuHD]) VALUES (8, 4, 8, CAST(0x7B3F0B00 AS Date), 101140000, N'Offline')
INSERT [dbo].[HoaDon] ([maHD], [maNV], [maKH], [ngayLap], [tongTien], [kieuHD]) VALUES (9, 4, 5, CAST(0x7B3F0B00 AS Date), 38990000, N'Offline')
INSERT [dbo].[HoaDon] ([maHD], [maNV], [maKH], [ngayLap], [tongTien], [kieuHD]) VALUES (10, 4, 5, CAST(0x7B3F0B00 AS Date), 24000000, N'Offline')
INSERT [dbo].[HoaDon] ([maHD], [maNV], [maKH], [ngayLap], [tongTien], [kieuHD]) VALUES (11, 2, 10, CAST(0x663F0B00 AS Date), 3000000, N'Offline')
INSERT [dbo].[HoaDon] ([maHD], [maNV], [maKH], [ngayLap], [tongTien], [kieuHD]) VALUES (28, NULL, 5, CAST(0x663F0B00 AS Date), 3000000, N'Online')
INSERT [dbo].[HoaDon] ([maHD], [maNV], [maKH], [ngayLap], [tongTien], [kieuHD]) VALUES (29, NULL, 142749809, CAST(0x7F3F0B00 AS Date), 24000000, N'Online')
INSERT [dbo].[HoaDon] ([maHD], [maNV], [maKH], [ngayLap], [tongTien], [kieuHD]) VALUES (30, NULL, 13456789, CAST(0x7F3F0B00 AS Date), 11480000, N'Online')
INSERT [dbo].[HoaDon] ([maHD], [maNV], [maKH], [ngayLap], [tongTien], [kieuHD]) VALUES (31, NULL, 142749809, CAST(0x7F3F0B00 AS Date), 24000000, N'Online')
INSERT [dbo].[HoaDon] ([maHD], [maNV], [maKH], [ngayLap], [tongTien], [kieuHD]) VALUES (32, 4, 1, CAST(0x7F3F0B00 AS Date), 48000000, N'Offline')
INSERT [dbo].[HoaDon] ([maHD], [maNV], [maKH], [ngayLap], [tongTien], [kieuHD]) VALUES (33, 11, 1, CAST(0x7F3F0B00 AS Date), 62990000, N'Offline')
SET IDENTITY_INSERT [dbo].[HoaDon] OFF
INSERT [dbo].[KhachHang] ([maKH], [tenKhachHang], [maCN], [diaChi], [sdt]) VALUES (1, N'Luong Van Chinh', 1, N'Hải Dương', N'0986505708')
INSERT [dbo].[KhachHang] ([maKH], [tenKhachHang], [maCN], [diaChi], [sdt]) VALUES (2, N'Nguyen van a', 1, N'Bắc Ninh', N'0985050461')
INSERT [dbo].[KhachHang] ([maKH], [tenKhachHang], [maCN], [diaChi], [sdt]) VALUES (3, N'Hoàng Xuân Quyết', 2, N'Đà Nẵng', N'09456275849')
INSERT [dbo].[KhachHang] ([maKH], [tenKhachHang], [maCN], [diaChi], [sdt]) VALUES (4, N'Nguyễn Ngọc Hải', 2, N'Bình Dương', N'03475937583')
INSERT [dbo].[KhachHang] ([maKH], [tenKhachHang], [maCN], [diaChi], [sdt]) VALUES (5, N'Phạm Huy Nam', 1, N'Bắc Ninh', N'46523416')
INSERT [dbo].[KhachHang] ([maKH], [tenKhachHang], [maCN], [diaChi], [sdt]) VALUES (6, N'Lê Đức Hà', 2, N'Sóc Trăng', N'0346274859')
INSERT [dbo].[KhachHang] ([maKH], [tenKhachHang], [maCN], [diaChi], [sdt]) VALUES (7, N'Trịnh Quang Nam', 2, N'Cần Thơ', N'0986374859')
INSERT [dbo].[KhachHang] ([maKH], [tenKhachHang], [maCN], [diaChi], [sdt]) VALUES (8, N'Phạm Huy Nam', 1, N'Bắc Ninh', N'46546316')
INSERT [dbo].[KhachHang] ([maKH], [tenKhachHang], [maCN], [diaChi], [sdt]) VALUES (9, N'Tom David', 2, N'Mỹ Tho', N'0573849596')
INSERT [dbo].[KhachHang] ([maKH], [tenKhachHang], [maCN], [diaChi], [sdt]) VALUES (10, N'Supachai', 2, N'Lào', N'0127483485')
INSERT [dbo].[KhachHang] ([maKH], [tenKhachHang], [maCN], [diaChi], [sdt]) VALUES (11, N'Phạm Văn Quang', 1, N'Nam Đinh', N'0128475937')
INSERT [dbo].[KhachHang] ([maKH], [tenKhachHang], [maCN], [diaChi], [sdt]) VALUES (13, N'Chính Lương', 1, N'Hà Nội, Việt Nam, Hà Nội, Việt Nam', N'86505708')
INSERT [dbo].[KhachHang] ([maKH], [tenKhachHang], [maCN], [diaChi], [sdt]) VALUES (14, N'Chính Lương', 1, N'Hà Nội, Việt Nam, Hà Nội, Việt Nam', N'86505708')
INSERT [dbo].[KhachHang] ([maKH], [tenKhachHang], [maCN], [diaChi], [sdt]) VALUES (15, N'Chính Lương', 1, N'Hà Nội, Việt Nam, Hà Nội, Việt Nam', N'86505708')
INSERT [dbo].[KhachHang] ([maKH], [tenKhachHang], [maCN], [diaChi], [sdt]) VALUES (13456789, N'Phạm Huy Nam', 1, N'Bắc Ninh', N'0564134610')
INSERT [dbo].[KhachHang] ([maKH], [tenKhachHang], [maCN], [diaChi], [sdt]) VALUES (142749809, N'Lương Văn Chính', 1, N'Hải Dương', N'086505708')
SET IDENTITY_INSERT [dbo].[NhanVien] ON 

INSERT [dbo].[NhanVien] ([maNV], [taiKhoan], [matKhau], [hoTen], [diaChi], [sdt], [maCN], [chucvu]) VALUES (1, N'nguyenhuuquynh', N'huuquynh', N'Nguyễn Hữu Quỳnh', N'Bắc Ninh', N'0123456789', 1, N'Quản lý')
INSERT [dbo].[NhanVien] ([maNV], [taiKhoan], [matKhau], [hoTen], [diaChi], [sdt], [maCN], [chucvu]) VALUES (2, N'tranvannuc', N'trannuc', N'Trần Văn Nực', N'Nam Định', N'0123467895', 1, N'Bán hàng')
INSERT [dbo].[NhanVien] ([maNV], [taiKhoan], [matKhau], [hoTen], [diaChi], [sdt], [maCN], [chucvu]) VALUES (3, N'danghuuhieu', N'danghieu', N'Đặng Huy Hiếu', N'Hà Nội', N'0164604613', 1, N'Bán hàng')
INSERT [dbo].[NhanVien] ([maNV], [taiKhoan], [matKhau], [hoTen], [diaChi], [sdt], [maCN], [chucvu]) VALUES (4, N'q', N'q', N'Hà Tiều Phu', N'Bắc Ninh', N'0546463216', 1, N'Bán hàng')
INSERT [dbo].[NhanVien] ([maNV], [taiKhoan], [matKhau], [hoTen], [diaChi], [sdt], [maCN], [chucvu]) VALUES (5, N'luongvanchinh', N'vanchinh', N'Lương Văn Chính', N'Hải Dương', N'0986505708', 2, N'Bán hàng')
INSERT [dbo].[NhanVien] ([maNV], [taiKhoan], [matKhau], [hoTen], [diaChi], [sdt], [maCN], [chucvu]) VALUES (6, N'hoangxuanquyet', N'quyetptit', N'Hoàng Xuân Quyết', N'Hà Nam', N'0123456789', 1, N'Quản lý')
INSERT [dbo].[NhanVien] ([maNV], [taiKhoan], [matKhau], [hoTen], [diaChi], [sdt], [maCN], [chucvu]) VALUES (8, N'luongchinh', N'chinh123', N'Lương Văn Chính', N'Hải Dương', N'0986050708', NULL, N'Quản lý server')
INSERT [dbo].[NhanVien] ([maNV], [taiKhoan], [matKhau], [hoTen], [diaChi], [sdt], [maCN], [chucvu]) VALUES (9, N'ongchoipro', N'1', N'Trinh Quang Nam', N'Nam Dinh', N'0123234234', 1, N'Bán hàng')
INSERT [dbo].[NhanVien] ([maNV], [taiKhoan], [matKhau], [hoTen], [diaChi], [sdt], [maCN], [chucvu]) VALUES (10, N'mrlao', N'1', N'hiepxongchet', N'lao', N'91224341', 2, N'Bán hàng')
INSERT [dbo].[NhanVien] ([maNV], [taiKhoan], [matKhau], [hoTen], [diaChi], [sdt], [maCN], [chucvu]) VALUES (11, N'a', N'a', N'a', N'a', N'1212535', 1, N'Bán hàng')
INSERT [dbo].[NhanVien] ([maNV], [taiKhoan], [matKhau], [hoTen], [diaChi], [sdt], [maCN], [chucvu]) VALUES (12, N'z', N'z', N'z', N'z', N'z', 1, N'Bán hàng')
SET IDENTITY_INSERT [dbo].[NhanVien] OFF
INSERT [dbo].[OrderItems] ([maHD], [maSach], [soLuong]) VALUES (1, 1000, 1)
INSERT [dbo].[OrderItems] ([maHD], [maSach], [soLuong]) VALUES (2, 1010, 2)
INSERT [dbo].[OrderItems] ([maHD], [maSach], [soLuong]) VALUES (3, 1005, 1)
INSERT [dbo].[OrderItems] ([maHD], [maSach], [soLuong]) VALUES (4, 1009, 1)
INSERT [dbo].[OrderItems] ([maHD], [maSach], [soLuong]) VALUES (6, 1015, 2)
INSERT [dbo].[OrderItems] ([maHD], [maSach], [soLuong]) VALUES (8, 1020, 1)
INSERT [dbo].[OrderItems] ([maHD], [maSach], [soLuong]) VALUES (9, 1025, 2)
INSERT [dbo].[OrderItems] ([maHD], [maSach], [soLuong]) VALUES (10, 1026, 1)
INSERT [dbo].[OrderItems] ([maHD], [maSach], [soLuong]) VALUES (11, 1014, 3)
INSERT [dbo].[OrderItems] ([maHD], [maSach], [soLuong]) VALUES (29, 1000, 1)
INSERT [dbo].[OrderItems] ([maHD], [maSach], [soLuong]) VALUES (30, 1020, 1)
INSERT [dbo].[OrderItems] ([maHD], [maSach], [soLuong]) VALUES (31, 1000, 1)
INSERT [dbo].[OrderItems] ([maHD], [maSach], [soLuong]) VALUES (32, 1000, 2)
INSERT [dbo].[OrderItems] ([maHD], [maSach], [soLuong]) VALUES (33, 1000, 1)
INSERT [dbo].[OrderItems] ([maHD], [maSach], [soLuong]) VALUES (33, 1001, 1)
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1000, 1, N'Iphone 8 Plus', N'Apple', N'iphone-xs-gold-400x400.jpg', N'24000000', N'	LED-backlit IPS LCD, 5.5"', N'IOS', N'12 PM', N'	Apple A11 Bionic 6 nhân', N'4 GB', N'2691 mAh')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1001, 1, N'IphoneXS Max', N'Apple', N'iphone-xs-max.jpg', N'38990000', N'OLED, 6.5"', N'IOS', N'12 PM', N'Apple A12 Bionic 6 nhân', N'4 GB', N'3174 mAh')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1002, 1, N'Iphone X ', N'Apple', N'iphone-x.jpg', N'22990000', N'OLED, 5.8"', N'IOS', N'8 PM', N'	Apple A11 Bionic 6 nhân', N'4 GB', N'2716 mAh')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1003, 1, N'Samsung Galaxy S10 ', N'Samsung', N'samsung-galaxy-s10.jpg', N'28990000', N'Dynamic AMOLED, 6.4"', N'Android 9.0', N'12 PM', N'Exynos 9820 8 nhân 64-bit', N'8 GB', N'4100 mAh')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1004, 1, N'Samsung Galaxy Note 9', N'Samsung', N'samsung-galaxy-note-9.jpg', N'24490000', N'Super AMOLED, 6.4"', N'Android 8.1', N'2 camera 12 MP', N'Exynos 9810 8 nhân 64-bit', N'8 GB', N'4000 mAh')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1005, 1, N'Samsung Galaxy S9+', N'Samsung', N'samsung-galaxy-s9-plus.jpg', N'19990000', N'Super AMOLED, 6.2"', N'Android 8.0', N'2 camera 12 MP', N'Exynos 9810 8 nhân 64-bit', N'6 GB', N'2500 mAh')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1006, 1, N'OPPO Find X', N'OPPO', N'oppo-find-x.jpg', N'19990000', N'AMOLED, 6.42"', N'Android 8.1', N'Chính 16 MP & Phụ 20 MP', N'	Snapdragon 845 8 nhân', N'8 GB', N'3730 mAh')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1007, 1, N'OPPO R17 Pro', N'OPPO', N'oppo-r17-pro.jpg', N'15990000', N'AMOLED, 6.4"', N'Android 8.1', N'Chính 12 MP & Phụ 20 MP', N'	Snapdragon 710 8 nhân 64-bit', N'8 GB', N'3700 mAh')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1008, 1, N'OPPO F11 Pro', N'OPPO', N'oppo-f11-pro.jpg', N'16900000', N'AMOLED, 6.4"', N'Android 8.1', N'Chính 12 MP & Phụ 20 MP', N'	Snapdragon 710 8 nhân 64-bit', N'8 GB', N'3730 mAh')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1009, 2, N'Macbook Air MREE2SA/A', N'Apple', N'macbookAir.jpg', N'30990000', N'13.3 inch, Retina', N'Mac OS', NULL, N'Intel Core i5 Coffee Lake, 1.60 GHz', N'8 GB, DDR3, 2133 MHz', N'PIN liền (Khoảng 10h)')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1010, 2, N'Asus A510UA i5 8250U', N'Asus', N'asus-a510ua-i5.jpg', N'13790000', N'15.6 inch, Full HD', N'	Windows 10 Home SL', NULL, N'Intel Core i5 Coffee Lake, 8250U, 1.60 GHz', N'4 GB, DDR4 (2 khe), 2400 MHz', N'PIN liền')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1011, 3, N'iPad Wifi Cellular', N'Apple', N'ipad-wifi-cellular.jpg', N'13490000', N'	LED backlit LCD, 9.7"', N'iOS 11.3', N'8 MP', N'Apple A10 Fusion, 2.34 GHz', N'2 GB', N'Lithium - Polymer (8600 mAh)')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1012, 3, N'Samsung Galaxy Tab A', N'Samsung', N'samsung-galaxy-tab-a.jpg', N'9490000', N'IPS LCD, 10.5"', N'Android 8.0', N'8 MP', N'CPU 8 nhân, 1.8 GHz', N'3 GB', N'7300 mAh')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1013, 2, N'Dell Inspiron 3576', N'Dell', N'dell-inspiron-3576.jpg', N'14690000', N'15.6 inch, Full HD (1920 x 1080)', N'Windows 10 Home SL', NULL, N'Intel Core i5 Kabylake Refresh, 8250U, 1.60 GHz', N'4 GB', N'Li-Ion 4 cell')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1014, 2, N' Asus VivoBook X507UF', N'Asus', N'asus-x507uf.jpg', N'14590000', N'15.6 inch, Full HD (1920 x 1080)', N'Windows 10 Home SL', NULL, N'Intel Core i5 Kabylake Refresh, 8250U, 1.60 GHz', N'4 GB', N'Pin liền')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1015, 2, N'HP Pavilion 15 cs1009TU', N'HP', N'hp-pavilion-15-cs1009tu.jpg', N'15390000', N'15.6 inch, Full HD (1920 x 1080)', N'Windows 10 Home SL', NULL, N'Intel Core i5 Coffee Lake, 8265U, 1.60 GHz', N'4 GB', N'Li-Ion 3 cell (4240mAh)
')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1016, 2, N'Lenovo IdeaPad 330 14IKBR', N'Lenovo', N'lenovo-ideapad-330.jpg', N'10290000', N'14 inch, Full HD (1920 x 1080)', N'	Windows 10 Home SL', NULL, N'Intel Core i3 Kabylake, 7020U, 2.30 GHz', N'4 GB', N'Li-Ion 2 cell')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1017, 2, N'Acer Aspire E5 476', N'Acer', N'acer-aspire-e5-476.jpg', N'9990000', N'14 inch, HD (1366 x 768)', N'Windows 10 Home SL', NULL, N'	Intel Core i3 Kabylake Refresh, 8130U, 2.20 GHz', N'4 GB', N'Li-Ion 4 cell')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1018, 2, N'Asus VivoBook A411UA ', N'Asus', N'asus-a411ua.jpg', N'11290000', N'14 inch, Full HD (1920 x 1080)', N'	Windows 10 Home SL', NULL, N'Intel Core i3 Kabylake Refresh, 8130U, 2.20 GHz', N'4 GB', N'Li-Ion 3 cell')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1019, 2, N'Dell Inspiron 5570', N'Dell', N'dell-inspiron-5570.jpg', N'17990000', N'15.6 inch, Full HD (1920 x 1080)', N'Windows 10 + Office 365 1 năm', NULL, N'	Intel Core i5 Kabylake Refresh, 8250U, 1.60 GHz', N'4 GB', N'Li-Ion 3 cell')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1020, 3, N'iPad Wifi 128 GB (2018)', N'Apple', N'ipad-6th-wifi-128-gb.jpg', N'11480000', N'	LED backlit LCD, 9.7"', N'iOS 11.3', N'8 MP', N'Apple A10 Fusion, 2.34 GHz', N'2 GB', N' 8600 mAh')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1021, 3, N'Samsung Galaxy Tab A6 10.1 Spen', N'Samsung', N'samsung-galaxy-tab-a6-101-spen.jpg', N'7940000', N'1920 x 1200 pixels', N'Android 6.0 (Marshmallow)', N'8 MP', N'	Exynos 7870 8 nhân', N'3 GB', N'7300 mAh')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1022, 3, N'Huawei Mediapad T5 ', N'Huawei', N'huawei-mediapad-t5.jpg', N'4990000', N'	IPS LCD Full HD, 10.1"', N'Android 8.0', N'5 MP', N'	Kirin 659, 1.7 GHz', N'3 GB', N'5100 mAh')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1023, 3, N'Huawei MediaPad T3', N'Huawei', N'huawei-mediapad-t3.jpg', N'4590000', N'	PLS LCD, 10', N'Android 7.0', N'5 MP', N'	Qualcomm MSM8917, 1.4 GHz', N'2 GB', N'4800 mAh')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1024, 3, N'Lenovo Tab 4', N'Lenovo', N'lenovo-tab-4.jpg', N'3690000', N'IPS LCD, 8"', N'Android 7.0', N'5 MP', N'	Qualcomm MSM8917, 1.4 GHz', N'2 GB', N'4850 mAh')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1025, 3, N'Lenovo Tab 7 Essential', N'Lenovo', N'lenovo-tab-7-essential.jpg', N'2590000', N'IPS LCD, 7"', N'Android 7.0', N'2 MP', N'MediaTek MT 8735D 4 nhân, 1.1 GHz', N'1 GB', N'3450 mAh')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1026, 3, N'Masstel Tab10 Plus', N'Masstel', N'masstel-tab10-plus.jpg', N'2290000', N'IPS LCD, 10.1"', N'Android Go 8.1', N'8 MP', N'	MTK 6580, 1.3 GHz', N'1 GB', N'5000 mAh')
INSERT [dbo].[Sach] ([maSach], [maLS], [tenSach], [nhaSanXuat], [img], [giaBan], [tacGia], [quocGia], [nguoiBienDich], [kichThuoc], [moTa], [ngayXuatBan]) VALUES (1027, 3, N'Masstel Tab8 Plus', N'Masstel', N'masstel-tab8-plus.jpg', N'1990000', N'IPS LCD, 8"
', N'Android Go 8.1', N'5 MP', N'MT8321, 1.3 GHz', N'1 GB', N'	4000 mAh')
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1000, 101)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1001, 100)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1002, 140)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1003, 75)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1004, 100)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1006, 100)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1007, 85)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1008, 100)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1009, 128)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1010, 54)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1011, 32)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1012, 81)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1013, 32)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1014, 12)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1015, 56)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1016, 37)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1017, 43)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1018, 42)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1019, 12)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1020, 9)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1021, 21)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1022, 85)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1023, 25)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1024, 67)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1025, 3)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1026, 34)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (1, 1027, 9)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1000, 70)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1001, 100)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1002, 90)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1003, 80)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1004, 2)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1005, 60)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1006, 90)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1007, 60)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1008, 100)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1009, 4)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1010, 67)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1011, 23)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1012, 26)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1013, 45)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1014, 23)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1015, 56)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1016, 7)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1017, 15)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1018, 16)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1019, 5)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1020, 7)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1021, 45)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1022, 47)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1023, 56)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1024, 36)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1025, 28)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1026, 23)
INSERT [dbo].[SachThuocChiNhanh] ([maCN], [maSach], [soLuong]) VALUES (2, 1027, 43)
SET IDENTITY_INSERT [dbo].[TheLoaiSach] ON 

INSERT [dbo].[TheLoaiSach] ([maLs], [tenLS]) VALUES (1, N'Smartphone')
INSERT [dbo].[TheLoaiSach] ([maLs], [tenLS]) VALUES (2, N'Laptop')
INSERT [dbo].[TheLoaiSach] ([maLs], [tenLS]) VALUES (3, N'Tablet')
SET IDENTITY_INSERT [dbo].[TheLoaiSach] OFF
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDonOffline_KhachHang] FOREIGN KEY([maKH])
REFERENCES [dbo].[KhachHang] ([maKH])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDonOffline_KhachHang]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDonOffline_NhanVien] FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDonOffline_NhanVien]
GO
ALTER TABLE [dbo].[KhachHang]  WITH CHECK ADD  CONSTRAINT [FK_KhachHang_ChiNhanh] FOREIGN KEY([maCN])
REFERENCES [dbo].[ChiNhanh] ([maCN])
GO
ALTER TABLE [dbo].[KhachHang] CHECK CONSTRAINT [FK_KhachHang_ChiNhanh]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_ChiNhanh1] FOREIGN KEY([maCN])
REFERENCES [dbo].[ChiNhanh] ([maCN])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_ChiNhanh1]
GO
ALTER TABLE [dbo].[OrderItems]  WITH CHECK ADD  CONSTRAINT [FK_GiaoDich_HoaDonOffline] FOREIGN KEY([maHD])
REFERENCES [dbo].[HoaDon] ([maHD])
GO
ALTER TABLE [dbo].[OrderItems] CHECK CONSTRAINT [FK_GiaoDich_HoaDonOffline]
GO
ALTER TABLE [dbo].[OrderItems]  WITH CHECK ADD  CONSTRAINT [FK_GiaoDich_MatHang] FOREIGN KEY([maSach])
REFERENCES [dbo].[Sach] ([maSach])
GO
ALTER TABLE [dbo].[OrderItems] CHECK CONSTRAINT [FK_GiaoDich_MatHang]
GO
ALTER TABLE [dbo].[Sach]  WITH CHECK ADD  CONSTRAINT [FK_MatHang_DanhMuc] FOREIGN KEY([maLS])
REFERENCES [dbo].[TheLoaiSach] ([maLs])
GO
ALTER TABLE [dbo].[Sach] CHECK CONSTRAINT [FK_MatHang_DanhMuc]
GO
ALTER TABLE [dbo].[SachThuocChiNhanh]  WITH CHECK ADD  CONSTRAINT [FK_ChiNhanhMatHang_ChiNhanh] FOREIGN KEY([maCN])
REFERENCES [dbo].[ChiNhanh] ([maCN])
GO
ALTER TABLE [dbo].[SachThuocChiNhanh] CHECK CONSTRAINT [FK_ChiNhanhMatHang_ChiNhanh]
GO
ALTER TABLE [dbo].[SachThuocChiNhanh]  WITH CHECK ADD  CONSTRAINT [FK_ChiNhanhMatHang_MatHang] FOREIGN KEY([maSach])
REFERENCES [dbo].[Sach] ([maSach])
GO
ALTER TABLE [dbo].[SachThuocChiNhanh] CHECK CONSTRAINT [FK_ChiNhanhMatHang_MatHang]
GO
