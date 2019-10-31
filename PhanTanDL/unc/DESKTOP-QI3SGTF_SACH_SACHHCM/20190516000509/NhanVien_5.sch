drop Table [dbo].[NhanVien]
go
SET ANSI_PADDING OFF
go

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNV] [int] IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,
	[taiKhoan] [nvarchar](225) NULL,
	[matKhau] [nvarchar](225) NULL,
	[hoTen] [nvarchar](225) NULL,
	[diaChi] [nvarchar](225) NULL,
	[sdt] [nvarchar](225) NULL,
	[maCN] [int] NULL,
	[chucvu] [nvarchar](225) NULL,
	[rowguid] [uniqueidentifier] ROWGUIDCOL  NOT NULL
)

GO
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [MSmerge_df_rowguid_AE13C5ABD198482ABB916DA33BF621FB]  DEFAULT (newsequentialid()) FOR [rowguid]
GO
SET ANSI_NULLS ON

go

SET QUOTED_IDENTIFIER ON

go

ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
GO
