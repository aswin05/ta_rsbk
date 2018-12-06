-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 06, 2018 at 05:57 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `speedcardb`
--

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `userId` int(3) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`userId`, `username`, `password`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `mobil`
--

CREATE TABLE `mobil` (
  `mobilId` int(3) NOT NULL,
  `tipe` varchar(20) NOT NULL,
  `merk` varchar(20) NOT NULL,
  `noplat` varchar(20) NOT NULL,
  `warna` varchar(20) NOT NULL,
  `tahun` varchar(4) NOT NULL,
  `nomesin` varchar(20) NOT NULL,
  `foto` text,
  `penyewaId` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mobil`
--

INSERT INTO `mobil` (`mobilId`, `tipe`, `merk`, `noplat`, `warna`, `tahun`, `nomesin`, `foto`, `penyewaId`) VALUES
(7, 'Gallardo', 'Lamborghini', 'S3029ZZ', 'putih', '2018', 'SJDF896CDFDG77', '/resources/images/9890.jpg', 102),
(8, 'Cayman', 'Porsche', 'S209TT', 'biru', '2017', 'JFGKJ9842965HK', '/resources/images/6293.jpg', NULL),
(10, 'Aventador', 'Lamborghini', 'K7089K', 'hijau', '2016', 'SJGGD823648HH', '', NULL),
(11, 'Model S', 'Tesla', 'P2029Y', 'Hitam', '2018', 'KJSDSF39538IUO', '/resources/images/8635.jpg', NULL),
(12, 'Model X', 'Tesla', 'L0982IU', 'Putih', '2018', 'SHGFG374524TT', '', NULL),
(13, 'Xenia', 'Daihatsu', 'H768KL', 'Hitam', '2015', 'SGJF82356239BS', '', 102),
(14, 'Xenia', 'Daihatsu', 'S8097PO', 'Merah', '2015', 'GFDGFJ8293659O', '', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `penyewa`
--

CREATE TABLE `penyewa` (
  `penyewaId` int(4) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `alamat` text NOT NULL,
  `notlp` varchar(15) NOT NULL,
  `noktp` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `penyewa`
--

INSERT INTO `penyewa` (`penyewaId`, `nama`, `alamat`, `notlp`, `noktp`) VALUES
(2, 'Sendy', 'Jakarta', '0996t784854', '38944293865566'),
(102, 'Rony', 'Jombang', '034982652396', '39850235629386'),
(123, 'Elfa', 'Semarang', '08129128310', '33090282456778');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`userId`);

--
-- Indexes for table `mobil`
--
ALTER TABLE `mobil`
  ADD PRIMARY KEY (`mobilId`),
  ADD KEY `penyewaId` (`penyewaId`);

--
-- Indexes for table `penyewa`
--
ALTER TABLE `penyewa`
  ADD PRIMARY KEY (`penyewaId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `userId` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `mobil`
--
ALTER TABLE `mobil`
  MODIFY `mobilId` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `mobil`
--
ALTER TABLE `mobil`
  ADD CONSTRAINT `mobil_ibfk_1` FOREIGN KEY (`penyewaId`) REFERENCES `penyewa` (`penyewaId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
