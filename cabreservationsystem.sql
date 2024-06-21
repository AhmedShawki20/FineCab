-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 21, 2024 at 09:54 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cabreservationsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `car`
--

CREATE TABLE `car` (
  `id` int(11) NOT NULL,
  `type` varchar(20) DEFAULT NULL,
  `model` varchar(60) DEFAULT NULL,
  `version` varchar(5) DEFAULT NULL,
  `color` varchar(15) DEFAULT NULL,
  `body_type` varchar(50) DEFAULT NULL,
  `speed` varchar(20) DEFAULT NULL,
  `photo` varchar(130) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `car`
--

INSERT INTO `car` (`id`, `type`, `model`, `version`, `color`, `body_type`, `speed`, `photo`) VALUES
(1, 'Audi', 'A4 1.6', '2001', 'Grey', '4-doors-sedan', '190 km/h\r\n', 'https://www.cars-data.com/webp/audi/audi-a4_115_1.webp'),
(2, 'Chevrolet', 'Lacetti 1.4 Spirit', '2005', 'Red', '5-doors-hatchback', '175 km/h', 'https://www.cars-data.com/webp/chevrolet/chevrolet-lacetti_372_1.webp'),
(3, 'Hyundai', 'Sonata 2.0i ActiveVersion', '2005', 'Silver', '4-doors-sedan', '202 km/h', 'https://www.cars-data.com/webp/hyundai/hyundai-sonata_1042_1.webp'),
(4, 'Kia', 'Optima 2.0 CVVT Hybrid Plus Pack', '2012', 'White', '4-doors-sedan', '185 km/h', 'https://www.cars-data.com/webp/kia/kia-optima_1138_1.webp'),
(5, 'Lada', 'Kalina 1118 1.4', '2006', 'Blue', '4-doors-sedan', '165 km/h', 'https://www.cars-data.com/webp/lada/lada-kalina-1118_1176_1.webp'),
(6, 'Mazda', '3 Sedan 1.6 S-VT Touring', '2006', 'Gold', '4-doors-sedan', '185 km/h', 'https://www.cars-data.com/webp/mazda/mazda-3-sedan_1331_1.webp'),
(7, 'Mercedes', 'Benz CLS 350', '2010', 'Brown', '4-doors-sedan', '250 km/h', 'https://www.cars-data.com/webp/mercedes/mercedes-benz-cls-class_1497_10.webp'),
(8, 'MG', 'ZT 160', '2004', 'Black', '4-doors-sedan', '212 km/h', 'https://www.cars-data.com/webp/mg/mg-zt_1586_1.webp'),
(9, 'Opel', 'Astra GTC 1.4 Turbo 120hp Sport', '2012', 'Red', '3-doors-hatchback', '192 km/h', 'https://www.cars-data.com/webp/opel/opel-astra-gtc_1823_1.webp'),
(10, 'Nissan', 'Qashqai+2 1.6 Visia', '2010', 'Black', '5-doors-suv/crossover', '177 km/h', 'https://www.cars-data.com/webp/nissan/nissan-qashqai-2_1783_1.webp'),
(13, 'Lada', 'Samara', '1984', 'Blue', 'Sedan', '150 km/h', NULL),
(15, 'opel', 'Zafira', '2012', 'Green', 'MPV, 5 doors', '193 km/h', NULL),
(16, 'Mercedes', 'Benz GL-Class', '2008', 'Gray', 'SUV, 7-seets', '200 km/h', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `passwd` varchar(256) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `trips_id` int(11) DEFAULT NULL,
  `b_date` date DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `first_name`, `last_name`, `email`, `passwd`, `gender`, `trips_id`, `b_date`, `phone`) VALUES
(32, 'Ahmed', 'Shawki', 'ahmed@shawki.com', '15ba090ab0cc413c3c618affe2854962b98a0f7c13b5f247258642743fa78017', 'm', 105, '2000-12-01', '1099920033'),
(33, 'Ali', 'Sabry', 'ali@yahoo.com', '861982509ce255159a34c334ac711f7641b4c7ac181e076702daa7b07829afb2', 'm', NULL, '1999-08-01', '10222983801'),
(34, 'Samy', 'Eid', 'samy@eid.com', '38c196c570673a4883973938951e11cd64d6bb28dad2fa3b988116f76fd806c6', 'm', NULL, '1998-01-01', '1033458901');

-- --------------------------------------------------------

--
-- Table structure for table `driver`
--

CREATE TABLE `driver` (
  `id` int(11) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `b_date` date DEFAULT NULL,
  `rate_review` int(11) DEFAULT 0,
  `email` varchar(50) DEFAULT NULL,
  `passwd` varchar(256) DEFAULT NULL,
  `car_id` int(11) NOT NULL,
  `trips_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `driver`
--

INSERT INTO `driver` (`id`, `first_name`, `last_name`, `b_date`, `rate_review`, `email`, `passwd`, `car_id`, `trips_id`) VALUES
(1, 'Ahmed', 'Ahmed', '1990-09-10', 6, 'ahmed@example.xyz', '8f0ab6093bd36c4d1ca64788404cb1b6b04118bb29680fb566f5b57328fa7017', 3, NULL),
(2, 'Mohamed', 'Ali', '1992-08-08', 6, 'mohamed@example.xyz', 'c68ecb5132f012ceb9c74565e6d22ebb0fd48cba46a36ef7aab840c19eb697d9', 2, NULL),
(3, 'Hany', 'Emad', '1996-02-11', 5, 'hany@example.xyz', 'f094918c8bc030c999bbcbe6dea449173a5d71f791d2ef63b8c46011e48ad3d3', 5, NULL),
(4, 'Samy', 'Mohamed', '1998-03-07', 6, 'samy@example.xyz', '14843044773a770d2a42ecc783a13ac1de7ae18179939911fc67ca32b793a7d1', 9, NULL),
(5, 'Ayman', 'Khalad', '1991-09-09', 5, 'ayman@example.xyz', '5f35580c8996485178865f9429743cdc799d5a95f5243dcc8f365a3fe6e4c2be', 1, NULL),
(6, 'Hesham', 'Saad', '1991-01-20', 6, 'hesham@example.xyz', '2ec0e70d1c29b8357b320211837754dc639af5e33e0e1a0d54f732e6c055a6e6', 10, NULL),
(7, 'Ali', 'Ali', '1995-01-01', 5, 'ali@example.xyz', 'f741debc9f12f5137f1a479ee091a32349bd48f197de9291365c53b42226bc1f', 6, NULL),
(8, 'Nabil', 'Fawzy', '1997-04-13', 1, 'nabil@example.xyz', '223e771deec26cb771a16934343e28d91af15139ebdd9da32265bb07559c8105', 8, NULL),
(9, 'Youssef', 'Eid', '1993-12-03', 6, 'youssef@example.xyz', '3c391c3ce87a54af6172f73f042cf9c256a07764128ad2280ea4c1c35a83bc69', 4, NULL),
(10, 'Ziad', 'Ziad', '1991-09-04', 5, 'ziad@example.xyz', '12a62cf1d5b75abe8ca8b48f112b5cf1e7add8ccf2a1ca193ebba669a2862f9b', 7, NULL),
(11, 'Marwan', 'Ahmed', '1999-01-10', NULL, 'm.ahmed@m.com', '356fff47631a7b029e2d96e7cfbaf68267560d4c13e76b9691e3076effcf4159', 13, 105),
(13, 'Essa', 'Ahmed', '1999-12-23', NULL, 'essa@a.com', 'f65bdf830526bf1691ba9560ea926dcea07e9267b5bfef1820b27994f5d9521f', 15, NULL),
(14, 'Ahmed', 'Tarek', '1991-10-09', NULL, 'a@tarek.com', '27ec954b726fb0c4a275cf9871bec448838b723793e0e34cd09f06746e993cf2', 16, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `driver_phone`
--

CREATE TABLE `driver_phone` (
  `phone_id` int(11) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `driver_phone`
--

INSERT INTO `driver_phone` (`phone_id`, `phone`) VALUES
(1, '96101708089'),
(8, '962358645235'),
(9, '96370348927'),
(5, '96325684752'),
(3, '966087046489'),
(6, '97305654975'),
(7, '94856215578'),
(2, '985865862569'),
(4, '964258258855'),
(8, '98215482365'),
(1, '985865864569'),
(3, '985865865669'),
(10, '985865811669'),
(7, '985567865669'),
(6, '985999865669'),
(2, '985911865669'),
(10, '985231865669'),
(11, '11233422990'),
(11, '18776129808'),
(13, '122222222'),
(14, '557763229'),
(14, '1223374646');

-- --------------------------------------------------------

--
-- Table structure for table `rate`
--

CREATE TABLE `rate` (
  `id` int(11) NOT NULL,
  `stars` int(11) DEFAULT NULL,
  `review` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rate`
--

INSERT INTO `rate` (`id`, `stars`, `review`) VALUES
(1, 0, 'No Rate Yet'),
(2, 1, 'Bad'),
(3, 2, 'Not Bad'),
(4, 3, 'Good'),
(5, 4, 'Very Good'),
(6, 5, 'Excellent');

-- --------------------------------------------------------

--
-- Table structure for table `salt_customer`
--

CREATE TABLE `salt_customer` (
  `customer_value` varchar(16) NOT NULL,
  `customer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `salt_customer`
--

INSERT INTO `salt_customer` (`customer_value`, `customer_id`) VALUES
('KixENc7ZAUP2E9SS', 32),
('uM1fHdU1uy9VAXQp', 33),
('ipZIezHDevKaxCvU', 34);

-- --------------------------------------------------------

--
-- Table structure for table `salt_driver`
--

CREATE TABLE `salt_driver` (
  `driver_value` varchar(16) NOT NULL,
  `driver_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `salt_driver`
--

INSERT INTO `salt_driver` (`driver_value`, `driver_id`) VALUES
('Opms223mKA0sq1sX', 1),
('H7dF3lPmS9tZ8aQW', 2),
('B4uK7xJwR2oL1pVz', 3),
('M6rN2gTqY5sA3eFu', 4),
('Q9zV0hXkL4wP6bMj', 5),
('E2mJ1yRxD5nT8vZp', 6),
('W3oF6qLkG8bH7rNv', 7),
('K0zD4jXyN5tV2mWp', 8),
('C1uT7pLkR3oJ5eXs', 9),
('Z6wF8qHnY2vL3mXp', 10),
('P7dJ0xQwK9rM4zTl', 11),
('FreOPgvSWjgqqk9D', 13),
('PvPzsfVb2qCJZUCl', 14);

-- --------------------------------------------------------

--
-- Table structure for table `trips`
--

CREATE TABLE `trips` (
  `id` int(11) NOT NULL,
  `destination` varchar(50) DEFAULT NULL,
  `where_to` varchar(50) DEFAULT NULL,
  `status_trip` varchar(30) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `driver_id` int(11) DEFAULT NULL,
  `price` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `trips`
--

INSERT INTO `trips` (`id`, `destination`, `where_to`, `status_trip`, `customer_id`, `driver_id`, `price`) VALUES
(105, 'Aswan', 'Qena', 'Waiting', 32, 11, '700');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `fk_trips_customer` (`trips_id`);

--
-- Indexes for table `driver`
--
ALTER TABLE `driver`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `fk_cars` (`car_id`),
  ADD KEY `fk_rate` (`rate_review`),
  ADD KEY `fk_trips_driver` (`trips_id`);

--
-- Indexes for table `driver_phone`
--
ALTER TABLE `driver_phone`
  ADD KEY `fk_multi_phones` (`phone_id`);

--
-- Indexes for table `rate`
--
ALTER TABLE `rate`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `salt_customer`
--
ALTER TABLE `salt_customer`
  ADD UNIQUE KEY `fk_customer_salt` (`customer_id`);

--
-- Indexes for table `salt_driver`
--
ALTER TABLE `salt_driver`
  ADD KEY `fk_driver_salt` (`driver_id`);

--
-- Indexes for table `trips`
--
ALTER TABLE `trips`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `car`
--
ALTER TABLE `car`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `driver`
--
ALTER TABLE `driver`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `rate`
--
ALTER TABLE `rate`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `trips`
--
ALTER TABLE `trips`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=106;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `fk_trips_customer` FOREIGN KEY (`trips_id`) REFERENCES `trips` (`id`);

--
-- Constraints for table `driver`
--
ALTER TABLE `driver`
  ADD CONSTRAINT `fk_cars` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_rate` FOREIGN KEY (`rate_review`) REFERENCES `rate` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_trips_driver` FOREIGN KEY (`trips_id`) REFERENCES `trips` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `driver_phone`
--
ALTER TABLE `driver_phone`
  ADD CONSTRAINT `fk_multi_phones` FOREIGN KEY (`phone_id`) REFERENCES `driver` (`id`);

--
-- Constraints for table `salt_customer`
--
ALTER TABLE `salt_customer`
  ADD CONSTRAINT `fk_customer_salt` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `salt_driver`
--
ALTER TABLE `salt_driver`
  ADD CONSTRAINT `fk_driver_salt` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
