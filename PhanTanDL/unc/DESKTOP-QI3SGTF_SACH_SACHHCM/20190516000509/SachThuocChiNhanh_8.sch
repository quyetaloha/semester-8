drop Table [dbo].[SachThuocChiNhanh]
go
SET ANSI_PADDING OFF
go

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SachThuocChiNhanh](
	[maCN] [int] NOT NULL,
	[maSach] [int] NOT NULL,
	[soLuong] [int] NOT NULL,
	[rowguid] [uniqueidentifier] ROWGUIDCOL  NOT NULL
)

GO
ALTER TABLE [dbo].[SachThuocChiNhanh] ADD  CONSTRAINT [MSmerge_df_rowguid_B2FB2BEC55314B22866D86262E09EADC]  DEFAULT (newsequentialid()) FOR [rowguid]
GO
SET ANSI_NULLS ON

go

SET QUOTED_IDENTIFIER ON

go

ALTER TABLE [dbo].[SachThuocChiNhanh] ADD  CONSTRAINT [PK_ChiNhanhMatHang] PRIMARY KEY CLUSTERED 
(
	[maCN] ASC,
	[maSach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
GO
