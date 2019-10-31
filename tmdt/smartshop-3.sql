-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 26, 2019 at 05:47 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.1.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `smartshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `cartitems`
--

CREATE TABLE `cartitems` (
  `idOrder` int(11) NOT NULL,
  `idProduct` int(11) NOT NULL,
  `soLuong` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cartitems`
--

INSERT INTO `cartitems` (`idOrder`, `idProduct`, `soLuong`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `id` int(11) NOT NULL,
  `idUser` int(11) DEFAULT NULL,
  `diaChi` varchar(255) DEFAULT NULL,
  `ngayLap` date DEFAULT NULL,
  `tongGia` varchar(255) DEFAULT NULL,
  `soLuong` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`id`, `idUser`, `diaChi`, `ngayLap`, `tongGia`, `soLuong`) VALUES
(1, 2, NULL, '2019-10-08', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `namePro` varchar(255) DEFAULT NULL,
  `hangSX` varchar(255) DEFAULT NULL,
  `namSX` int(11) DEFAULT NULL,
  `gia` varchar(255) DEFAULT NULL,
  `manHinh` varchar(255) DEFAULT NULL,
  `camera` varchar(255) DEFAULT NULL,
  `heDieuHanh` varchar(255) DEFAULT NULL,
  `RAM` varchar(45) DEFAULT NULL,
  `ROM` varchar(45) DEFAULT NULL,
  `PIN` varchar(45) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `tongSL` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `namePro`, `hangSX`, `namSX`, `gia`, `manHinh`, `camera`, `heDieuHanh`, `RAM`, `ROM`, `PIN`, `img`, `tongSL`) VALUES
(1, 'Iphone 6S plus', 'Apple', 2015, '9999000', 'LED-backlit IPS LCD 5.5\'', '12MP - 5MP', 'IOS 12', '2GB', '32GB', '2750 mAh', 'iphone6splus.png', 50),
(2, 'Iphone 6', 'Apple', 2015, '3900000', 'LED-backlit IPS LCD, 4.7\", Retina HD', '8MP - 1.2MP', 'IOS 12', '1GB', '32GB', '1810mAh', 'iphone6.png', 50),
(3, 'Huawei P30', 'Huawei', 2018, '16900000', 'OLED, 6.1\", Full HD+', '40MP - 32MP', 'Android 9.0 (Pie)', '4GB', '128GB', '3650 mAh', 'HuaweiP30.png', 50),
(4, 'Iphone 5s', 'Apple', 2015, '1900000', 'LED-backlit IPS LCD, 4\", DVGA', '8MP - 1.2MP', 'IOS 10', '512MB', '64GB', '1560mAh', 'iphone5s.png', 50),
(5, 'Iphone 7', 'Apple', 2016, '11990000', 'LED-backlit IPS LCD, 4.7\", Retina HD', '12MP - 7MP', 'IOS 12', '2GB', '32GB', '1960 mAh', 'iphone7.png', 100),
(6, 'Iphone 7 Plus', 'Apple', 2016, '12990000', 'LED-backlit IPS LCD, 5.5\", Retina HD', '12MP - 7MP', 'IOS 12', '3GB', '32GB', '2900 mAh', 'iphone7plus.png', 50),
(7, 'Iphone 8', 'Apple', 2016, '18000000', 'LED-backlit IPS LCD, 4.7\", Retina HD', '12MP - 7MP', 'IOS 12', '2GB', '64GB', '1821 mAh', 'iphone8.png', 50),
(8, 'Iphone X', 'Apple', 2017, '23528000', 'OLED, 5.8\", Super Retina', '12MP - 7MP', 'IOS 12', '3GB', '64GB', '2716 mAh', 'iphonex.png', 50),
(9, 'Samsung S6', 'SamSung', 2012, '4000000', 'Super AMOLED, 5.1\", Quad HD (2K)', '16MP - 5MP', 'Android 6.0 (Marshmallow)', '3GB', '32GB', '2550mAh', 'samsungs6.png', 50),
(10, 'Samsung S7', 'SamSung', 2012, '6900000', 'Super AMOLED, 5.1\", Quad HD (2K)', '12MP-5MP', 'Android 6.0 (Marshmallow)', '4GB', '32GB', '3000 mAh', 'samsungs7.png', 100),
(11, 'Samsung M20', 'SamSung', 2019, '4990000', 'PLS TFT LCD, 6.3\", Full HD+', '13MP - 8MP', 'Android 8.1 (Oreo)', '3GB', '32GB', '5000 mAh', 'samsungm20.png', 50),
(12, 'Samsung A50', 'SamSung', 2019, '6990000', 'Super AMOLED, 6.4\", Full HD+', '25MP - 25MP', 'Android 9.0 (Pie)', '4GB', '64GB', '4000 mAh', 'samsunga50.png', 50),
(13, 'Samsung A9', 'SamSung', 2019, '8990000', 'Super AMOLED, 6.3\", Full HD+', '24MP - 24MP', 'Android 8.0 (Oreo)', '6GB', '128GB', '3800 mAh', 'samsunga9.png', 50),
(14, 'OPPO Find X', 'OPPO', 2018, '15990000', 'AMOLED, 6.42\", Full HD+', '25MP - 6MP', 'Android 8.1 (Oreo)', '8GB', '256GB', '3730 mAh', 'oppofindx.png', 50),
(15, 'OPPO R17 Pro', 'OPPO', 2018, '11990000', 'AMOLED, 6.4\", Full HD+', '25MP - 12MP', 'ColorOS 5.2 (Android 8.1)', '8GB', '128GB', '3700 mAh', 'OPPOR17Pro.png', 50),
(16, 'Xiaomi Mi 8', 'Xiaomi', 2018, '11990000', 'Super AMOLED, 6.21\", Full HD+', '20MP - 12MP', 'Android 8.1 (Oreo)', '6GB', '64GB', '3400 mAh', 'XiaomiMi8.png', 100),
(17, 'Vivo V11', 'VIVO', 2018, '7990000', 'Super AMOLED, 6.41\", Full HD+', '25MP - 12MP', 'Android 8.1 (Oreo)', '6GB', '128GB', '3400 mAh', 'VivoV11.png', 50),
(18, 'Huawei P30 Pro', 'Huawei', 2019, '22990000', 'OLED, 6.47\", Full HD+', '40MP - 32MP', 'Android 9.0 (Pie)', '8GB', '256GB', '4200 mAh', 'HuaweiP30Pro.png', 50),
(19, 'Huawei Mate 20 Pro', 'Huawei', 2018, '17990000', 'OLED, 6.39\", Quad HD+ (2K+)', '40MP - 20MP', 'Android 9.0 (Pie)', '6GB', '128GB', '4200 mAh', 'HuaweiMate20Pro.png', 50),
(20, 'Vivo V15', 'VIVO', 2018, '7990000', 'IPS LCD, 6.53\", Full HD+', '32MP - 12MP', 'Android 9.0 (Pie)', '6GB', '128GB', '4000 mAh', 'VivoV15.png', 50),
(21, 'Xiaomi Mi 8 Lite', 'Xiaomi', 2018, '6690000', 'IPS LCD, 6.26\", Full HD+', '24MP - 12MP', 'Android 8.1 (Oreo)', '6GB', '128GB', '3350 mAh', 'XiaomiMi8Lite.png', 50),
(22, 'Xiaomi Mi A2', 'Xiaomi', 2018, '5690000', 'IPS LCD, 5.99\", Full HD+', '20MP - 12MP', 'Android 8 Oreo (Android One)', '4GB', '64GB', '3010 mAh', 'XiaomiMiA2.png', 80),
(23, 'Xiaomi Redmi Note 6 Pro ', 'Xiaomi', 2018, '4490000', 'IPS LCD, 6.26\", Full HD+', '20MP - 12MP', 'Android 8.1 (Oreo)', '3GB', '32GB', '4000 mAh', 'XiaomiRedmiNote6Pro.png', 100),
(24, 'OPPO F9', 'OPPO', 2018, '8490000', 'LTPS LCD, 6.3\", Full HD+', '25MP - 16MP', 'ColorOS 5.2 (Android 8.1)', '6GB', '64GB', '3500 mAh', 'OPPOF9.png', 50);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `name`, `phone`, `email`, `role`) VALUES
(2, 'quynh', 'quynh', 'nguyen quynh', '033666999', 'quynh@gmail.com', 1),
(6, 'chinh', 'chinh123', 'chinh', '34235235', 'a@gmail.com', 0),
(7, 'quynh1', '123', 'quỳnh', '01217133', 'a@gmail.com', 1),
(8, 'quyet', '12345', 'haquyet', '3423123123', 'a1@gmail.com', 1),
(9, 'sa123', '12345', 'sasa', '34235a235', 'a123@gmail.com', 1),
(10, 'a12345', '123', 'a123', '342qwe35235', 'a3213@gmail.com', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cartitems`
--
ALTER TABLE `cartitems`
  ADD PRIMARY KEY (`idOrder`,`idProduct`),
  ADD KEY `fk_id_product_idx` (`idProduct`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_user_idx` (`idUser`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cartitems`
--
ALTER TABLE `cartitems`
  ADD CONSTRAINT `fk_id_order` FOREIGN KEY (`idOrder`) REFERENCES `order` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_id_product` FOREIGN KEY (`idProduct`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `fk_id_user` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
