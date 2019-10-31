drop Table [dbo].[Sach]
go
SET ANSI_PADDING OFF
go

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
	[rowguid] [uniqueidentifier] ROWGUIDCOL  NOT NULL
)

GO
ALTER TABLE [dbo].[Sach] ADD  CONSTRAINT [MSmerge_df_rowguid_202F65B3CB3348F88FA957D43F972FE9]  DEFAULT (newsequentialid()) FOR [rowguid]
GO
SET ANSI_NULLS ON

go

SET QUOTED_IDENTIFIER ON

go

ALTER TABLE [dbo].[Sach] ADD  CONSTRAINT [PK_MatHang_1] PRIMARY KEY CLUSTERED 
(
	[maSach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
GO
