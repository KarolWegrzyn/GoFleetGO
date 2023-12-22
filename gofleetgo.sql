-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 22, 2023 at 02:54 PM
-- Wersja serwera: 10.4.32-MariaDB
-- Wersja PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gofleetgo`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `admin`
--

CREATE TABLE `admin` (
  `Admin_ID` int(10) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `company`
--

CREATE TABLE `company` (
  `Company_ID` int(10) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Subscription_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `model`
--

CREATE TABLE `model` (
  `Model_ID` int(10) NOT NULL,
  `Brand` varchar(50) NOT NULL,
  `Engine` decimal(2,1) NOT NULL,
  `Fueal_capacity` decimal(4,2) NOT NULL,
  `Year_of_production` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `reservation`
--

CREATE TABLE `reservation` (
  `Reservation_ID` int(10) NOT NULL,
  `User_ID` int(10) NOT NULL,
  `Vechicle_ID` int(10) NOT NULL,
  `Start_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `review`
--

CREATE TABLE `review` (
  `Review_ID` int(10) NOT NULL,
  `User_ID` int(10) NOT NULL,
  `Vechicle_ID` int(10) NOT NULL,
  `Rating` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ride`
--

CREATE TABLE `ride` (
  `Ride_ID` int(10) NOT NULL,
  `User_ID` int(10) NOT NULL,
  `Vechicle_ID` int(10) NOT NULL,
  `Reservation_ID` int(10) NOT NULL,
  `Route_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `route`
--

CREATE TABLE `route` (
  `Route_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `subscription`
--

CREATE TABLE `subscription` (
  `Subscription_ID` int(10) NOT NULL,
  `Cost` decimal(10,2) NOT NULL,
  `Funds` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `subscription`
--

INSERT INTO `subscription` (`Subscription_ID`, `Cost`, `Funds`) VALUES
(0, 29.99, 1000.00),
(1, 59.99, 2000.00);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `transaction`
--

CREATE TABLE `transaction` (
  `Transaction_ID` int(10) NOT NULL,
  `User_ID` int(10) NOT NULL,
  `Vechicle_ID` int(10) NOT NULL,
  `Ride_ID` int(10) NOT NULL,
  `Date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `User_ID` int(10) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Company_ID` int(11) NOT NULL,
  `Email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `vechicle`
--

CREATE TABLE `vechicle` (
  `Vechicle_ID` int(10) NOT NULL,
  `Model_ID` int(10) NOT NULL,
  `Location` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`Admin_ID`);

--
-- Indeksy dla tabeli `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`Company_ID`),
  ADD KEY `Subscription_ID` (`Subscription_ID`);

--
-- Indeksy dla tabeli `model`
--
ALTER TABLE `model`
  ADD PRIMARY KEY (`Model_ID`);

--
-- Indeksy dla tabeli `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`Reservation_ID`),
  ADD KEY `User_ID` (`User_ID`),
  ADD KEY `Vechicle_ID` (`Vechicle_ID`);

--
-- Indeksy dla tabeli `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`Review_ID`),
  ADD KEY `User_ID` (`User_ID`);

--
-- Indeksy dla tabeli `ride`
--
ALTER TABLE `ride`
  ADD PRIMARY KEY (`Ride_ID`),
  ADD KEY `Vechicle_ID` (`Vechicle_ID`),
  ADD KEY `Reservation_ID` (`Reservation_ID`),
  ADD KEY `User_ID` (`User_ID`),
  ADD KEY `Route_ID` (`Route_ID`);

--
-- Indeksy dla tabeli `route`
--
ALTER TABLE `route`
  ADD PRIMARY KEY (`Route_ID`);

--
-- Indeksy dla tabeli `subscription`
--
ALTER TABLE `subscription`
  ADD PRIMARY KEY (`Subscription_ID`);

--
-- Indeksy dla tabeli `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`Transaction_ID`),
  ADD KEY `User_ID` (`User_ID`),
  ADD KEY `Vechicle_ID` (`Vechicle_ID`),
  ADD KEY `Ride_ID` (`Ride_ID`);

--
-- Indeksy dla tabeli `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`User_ID`),
  ADD KEY `Company_ID` (`Company_ID`);

--
-- Indeksy dla tabeli `vechicle`
--
ALTER TABLE `vechicle`
  ADD PRIMARY KEY (`Vechicle_ID`),
  ADD KEY `Model_ID` (`Model_ID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `company`
--
ALTER TABLE `company`
  ADD CONSTRAINT `company_ibfk_1` FOREIGN KEY (`Subscription_ID`) REFERENCES `subscription` (`Subscription_ID`);

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`),
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`Vechicle_ID`) REFERENCES `vechicle` (`Vechicle_ID`),
  ADD CONSTRAINT `reservation_ibfk_3` FOREIGN KEY (`Vechicle_ID`) REFERENCES `vechicle` (`Vechicle_ID`);

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `review_ibfk_1` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`);

--
-- Constraints for table `ride`
--
ALTER TABLE `ride`
  ADD CONSTRAINT `ride_ibfk_1` FOREIGN KEY (`Vechicle_ID`) REFERENCES `vechicle` (`Vechicle_ID`),
  ADD CONSTRAINT `ride_ibfk_2` FOREIGN KEY (`Reservation_ID`) REFERENCES `reservation` (`Reservation_ID`),
  ADD CONSTRAINT `ride_ibfk_3` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`),
  ADD CONSTRAINT `ride_ibfk_4` FOREIGN KEY (`Route_ID`) REFERENCES `route` (`Route_ID`);

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`),
  ADD CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`Vechicle_ID`) REFERENCES `vechicle` (`Vechicle_ID`),
  ADD CONSTRAINT `transaction_ibfk_3` FOREIGN KEY (`Ride_ID`) REFERENCES `ride` (`Ride_ID`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`Company_ID`) REFERENCES `company` (`Company_ID`);

--
-- Constraints for table `vechicle`
--
ALTER TABLE `vechicle`
  ADD CONSTRAINT `vechicle_ibfk_1` FOREIGN KEY (`Model_ID`) REFERENCES `model` (`Model_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
