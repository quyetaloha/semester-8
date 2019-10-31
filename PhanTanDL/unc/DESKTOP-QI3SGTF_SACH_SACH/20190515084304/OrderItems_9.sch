drop Table [dbo].[OrderItems]
go
SET ANSI_PADDING OFF
go

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderItems](
	[maHD] [int] NOT NULL,
	[maSach] [int] NOT NULL,
	[soLuong] [int] NULL,
	[rowguid] [uniqueidentifier] ROWGUIDCOL  NOT NULL
)

GO
ALTER TABLE [dbo].[OrderItems] ADD  CONSTRAINT [MSmerge_df_rowguid_9E6DD66DF09A43C2A1C2C96E7FC316CF]  DEFAULT (newsequentialid()) FOR [rowguid]
GO
SET ANSI_NULLS ON

go

SET QUOTED_IDENTIFIER ON

go

ALTER TABLE [dbo].[OrderItems] ADD  CONSTRAINT [PK_GiaoDich] PRIMARY KEY CLUSTERED 
(
	[maHD] ASC,
	[maSach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
GO
