-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 10, 2016 at 07:32 PM
-- Server version: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spms`
--

-- --------------------------------------------------------

--
-- Table structure for table `capplics`
--

CREATE TABLE IF NOT EXISTS `capplics` (
  `id` int(6) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `emailID` varchar(30) DEFAULT NULL,
  `address` text,
  `phoneNo` varchar(15) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `medicalCert` varchar(150) DEFAULT NULL,
  `photo` varchar(150) DEFAULT NULL,
  `feeReceipt` varchar(150) DEFAULT NULL,
  `courseID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cmembers`
--

CREATE TABLE IF NOT EXISTS `cmembers` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `notifics` text,
  `type` int(11) NOT NULL COMMENT 'cmember-0 coord-1 eman-2'
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cmembers`
--

INSERT INTO `cmembers` (`id`, `name`, `notifics`, `type`) VALUES
(100, 'mkdadi', NULL, 2),
(101, 'Anirudh Gopu', NULL, 1),
(102, 'Muni', NULL, 0),
(103, 'Kumar', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE IF NOT EXISTS `courses` (
  `ID` varchar(10) NOT NULL,
  `Name` varchar(30) DEFAULT NULL,
  `coordinatorID` int(6) NOT NULL,
  `start` date NOT NULL,
  `duration` int(11) NOT NULL,
  `fee` int(11) NOT NULL,
  `students` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`ID`, `Name`, `coordinatorID`, `start`, `duration`, `fee`, `students`) VALUES
('SB_4_2016', 'Swim Beginners', 103, '2016-04-15', 6, 30000, '90005'),
('SS_2016', 'Summer Swimming', 101, '2016-05-01', 3, 10000, '90004');

-- --------------------------------------------------------

--
-- Table structure for table `discussions`
--

CREATE TABLE IF NOT EXISTS `discussions` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `discussion` text
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `discussions`
--

INSERT INTO `discussions` (`id`, `title`, `discussion`) VALUES
(1, 'Test Disc_1', 'Kumar:$$Hello Every one My name is Madhu Kumar Dadi.$$$Muni:$$Hi Dadi.$$$(Manager) mkdadi:$$Hi everyone'),
(2, 'Test Disc_2', 'Kumar:$$Hello Every one My name is Madhu Kumar Dadi.$$$Madhu:$$Hi Dadi.$$$(Manager) mkdadi:$$Hi Guys$$$(Manager) mkdadi:$$Whats Up??'),
(4, 'Water Cleanliness', '(Manager) mkdadi:$$Hi everyone$$$(Manager) mkdadi:$$I guess the water now is clear.\r\nHave a blast!!!'),
(5, 'Welcome Discussion', '(Manager) mkdadi:$$Hi Everyone, This is Madhu Kumar Dadi, Manager of SPMS$$$(Com. Member) mkdadi:$$Hi there!!$$$(Com. Member) mkdadi:$$Hey$$$(Manager) mkdadi:$$Hi All'),
(6, 'Hello', '(Manager) mkdadi:$$Simple Hello Test');

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE IF NOT EXISTS `events` (
  `ID` varchar(10) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `managerID` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `duration` int(11) NOT NULL,
  `fee` int(11) NOT NULL,
  `particiList` text,
  `type` varchar(3) DEFAULT '131'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`ID`, `Name`, `managerID`, `timestamp`, `duration`, `fee`, `particiList`, `type`) VALUES
('SM_2016', 'SWIM MEET', 100, '2016-04-23 09:30:00', 3, 1000, '3,6,8', '142');

-- --------------------------------------------------------

--
-- Table structure for table `form`
--

CREATE TABLE IF NOT EXISTS `form` (
  `id` int(30) NOT NULL,
  `cF1` varchar(30) NOT NULL,
  `cF2` varchar(30) NOT NULL,
  `cF3` varchar(30) NOT NULL,
  `cF4` varchar(30) NOT NULL,
  `cF5` varchar(30) NOT NULL,
  `cF6` varchar(30) NOT NULL,
  `cF7` varchar(30) NOT NULL,
  `cF8` varchar(30) NOT NULL,
  `cF9` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `form`
--

INSERT INTO `form` (`id`, `cF1`, `cF2`, `cF3`, `cF4`, `cF5`, `cF6`, `cF7`, `cF8`, `cF9`) VALUES
(1, 'Name', 'Date of Birth', 'Email ID', 'Address', 'Phone No', 'Photo', 'Birth Certificate', 'Medical Certificate', 'Fee Receipt');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `id` int(6) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `type` int(1) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=90006 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `password`, `type`) VALUES
(27, 'dnttht@9', 0),
(100, 'dnttht@9', 2),
(101, 'dnttht@9', 2),
(102, 'dnttht@9', 2),
(103, 'RXZRFHQTW', 2),
(1001, 'dnttht@9', 1),
(1002, 'NCUOHBZGN', 1),
(1003, 'MRVKTKKXB', 1),
(1004, 'VCLILNZBL', 1),
(90004, 'dnttht@9', 3),
(90005, 'dnttht@9', 3);

-- --------------------------------------------------------

--
-- Table structure for table `manager`
--

CREATE TABLE IF NOT EXISTS `manager` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `notifics` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `manager`
--

INSERT INTO `manager` (`id`, `name`, `notifics`) VALUES
(27, 'mkdadi', '8');

-- --------------------------------------------------------

--
-- Table structure for table `mapplics`
--

CREATE TABLE IF NOT EXISTS `mapplics` (
  `id` int(6) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `emailID` varchar(30) DEFAULT NULL,
  `phoneNo` varchar(15) DEFAULT NULL,
  `address` text,
  `dob` date DEFAULT NULL,
  `photo` varchar(150) DEFAULT NULL,
  `birthCert` varchar(150) DEFAULT NULL,
  `medicalCert` varchar(150) DEFAULT NULL,
  `feeReceipt` varchar(150) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mapplics`
--

INSERT INTO `mapplics` (`id`, `name`, `emailID`, `phoneNo`, `address`, `dob`, `photo`, `birthCert`, `medicalCert`, `feeReceipt`) VALUES
(18, 'Madhu Kumar Dadi', 'kumardadi@outlook.com', '8145801184', 'IIT KGP', '1996-11-27', '/spms/mapplications/Madhu Kumar Dadi_WallpaperStudio10-20752.jpg', '/spms/mapplications/Madhu Kumar Dadi_MyCert.cer', '/spms/mapplications/Madhu Kumar Dadi_SPMS-SA_SD.pdf', '/spms/mapplications/Madhu Kumar Dadi_Applets and Swing.ppt');

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE IF NOT EXISTS `member` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `emailID` varchar(30) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `notifics` text
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`id`, `name`, `emailID`, `password`, `notifics`) VALUES
(1001, 'Madhukumar', 'kumardadi@hotmail.com', 'dnttht@9', NULL),
(1002, 'Muni Lohith', 'lonidalalohith@gmail.com', 'NCUOHBZGN', NULL),
(1003, 'Madhu Kumar', 'kumardadi100@gmail.com', 'MRVKTKKXB', NULL),
(1004, 'karthik', 'karthikpriyatham1996@gmail.com', 'VCLILNZBL', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `notices`
--

CREATE TABLE IF NOT EXISTS `notices` (
  `text` text NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notices`
--

INSERT INTO `notices` (`text`, `date`) VALUES
('Warning:\r\n	This is to all those members who are you yet to submit their fee receipts, You will be unregistered from Slot using in SPMS. So, please submit it fast.\r\nThank You,\r\nManager.', '2016-04-13'),
('SWIM MEET:\n	This event will take place on 2016-04-23T15:00 for a duration of 3 hours and Participation fee is 1000 rupees. Applications are welcome for this event now, those who want to apply Click apply for event and please enter the following detail:\nEvent ID: SM_2016.\nFee receipt must be submitted during application. If not selected for the event money will be refunded.', '2016-04-23'),
('Summer:\n	This course is starting on 2016-05-01 for a duration of 3 months and Fee is 10000 rupees. Applications are welcome for this course now, those who want to apply please enter the following detail:\nCourse ID: S_2016\nFee receipt must be submitted during application. If not selected for the course money will be refunded.', '2016-05-01'),
('Event format change:\n	The format of the event, SM_2016, has been changed to Men 100m Back Stroke', '2016-04-23'),
('Swim Beginners:\n	This course is starting on 2016-04-15 for a duration of 6 months and Fee is 3000 rupees. Applications are welcome for this course now, those who want to apply please enter the following detail:\nCourse ID: SB_4_2016\nFee receipt must be submitted during application. If not selected for the course money will be refunded.', '2016-04-15'),
('Event format change:\n	The format of the event, SM_2016, has been changed to Women 200m Free Style', '2016-04-23'),
('', '2016-04-17');

-- --------------------------------------------------------

--
-- Table structure for table `notifs`
--

CREATE TABLE IF NOT EXISTS `notifs` (
  `id` int(6) NOT NULL,
  `text` text,
  `userID` int(6) DEFAULT NULL,
  `type` int(1) NOT NULL COMMENT 'Text-0, AddMember-1, AddStudent-2, addParticipant-3, addViewer-4'
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notifs`
--

INSERT INTO `notifs` (`id`, `text`, `userID`, `type`) VALUES
(8, NULL, 18, 1),
(13, NULL, 2, 4),
(15, 'Hi Students, Today there is no class.', NULL, 0),
(21, '90004 (Student):\nSir,\r\nI dont feel Like learning anything. please see if you are checking on every student.', NULL, 0),
(22, '1001 (Member):\nSir,\r\nWater in the pools area is not clear. Please take action.', NULL, 0),
(23, 'The slot on [Ljava.lang.String;@65520b3a for the slot 7 is booked by for an event. So, the slots have been cancelled please change your slots.\nManager.', NULL, 0),
(24, 'The slot on [Ljava.lang.String;@20c692a6 for the slot 7 is booked by for an event. So, the slots have been cancelled please change your slots.\nManager.', NULL, 0),
(25, 'The slot on [Ljava.lang.String;@75e13ae0 for the slot 7 is booked by for an event. So, the slots have been cancelled please change your slots.\nManager.', NULL, 0),
(26, 'The slot on [Ljava.lang.String;@75e13ae0 for the slot 8 is booked by for an event. So, the slots have been cancelled please change your slots.\nManager.', NULL, 0),
(27, 'The slot on [Ljava.lang.String;@1d85ec2d for the slot 7 is booked by for an event. So, the slots have been cancelled please change your slots.\nManager.', NULL, 0),
(28, 'The slot on [Ljava.lang.String;@1d85ec2d for the slot 8 is booked by for an event. So, the slots have been cancelled please change your slots.\nManager.', NULL, 0),
(29, 'The slot on [Ljava.lang.String;@6e722d0b for the slot 7 is booked by for an event. So, the slots have been cancelled please change your slots.\nManager.', NULL, 0),
(30, 'The slot on [Ljava.lang.String;@6e722d0b for the slot 8 is booked by for an event. So, the slots have been cancelled please change your slots.\nManager.', NULL, 0),
(31, 'slot 14-16 class today. be there guys.', NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `papplics`
--

CREATE TABLE IF NOT EXISTS `papplics` (
  `id` int(6) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `emailID` varchar(30) DEFAULT NULL,
  `address` text,
  `phoneNo` varchar(15) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `medicalCert` varchar(150) DEFAULT NULL,
  `photo` varchar(150) DEFAULT NULL,
  `feeReceipt` varchar(150) DEFAULT NULL,
  `eventID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `participants`
--

CREATE TABLE IF NOT EXISTS `participants` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `emailID` varchar(30) DEFAULT NULL,
  `eventID` varchar(10) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `participants`
--

INSERT INTO `participants` (`id`, `name`, `emailID`, `eventID`) VALUES
(3, 'MKumar', 'kumardadi100@gmail.com', 'SM_2016'),
(6, 'Madhukumar', 'kumardadi@hotmail.com', 'SM_2016'),
(8, 'KumarD', 'kumardadi.9@gmail.com', 'SM_2016');

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE IF NOT EXISTS `posts` (
  `id` int(6) NOT NULL,
  `type` int(1) DEFAULT NULL,
  `poster` varchar(30) DEFAULT NULL,
  `post` text,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`id`, `type`, `poster`, `post`, `timestamp`) VALUES
(30, 1, '(Com. Member) mkdadi', 'Check 1.2.3..', '2016-04-07 17:06:52'),
(31, 0, '(Com. Member) Anirudh Gopu', 'New to SPMS, Hi everyone!!!', '2016-04-07 06:15:28'),
(32, 0, 'Kumar', 'Hi everyone.!!! :)', '2016-04-07 06:59:47'),
(33, 0, 'Kumar', 'Hi! everyone', '2016-04-07 07:34:52'),
(34, 0, 'karthik', 'Hi Everyone!!', '2016-04-09 18:31:04'),
(35, 0, '(Manager) mkdadi', 'Hi good afternoon', '2016-04-10 12:19:47');

-- --------------------------------------------------------

--
-- Table structure for table `slots`
--

CREATE TABLE IF NOT EXISTS `slots` (
  `date` date NOT NULL,
  `hour` int(2) NOT NULL,
  `eventID` varchar(10) DEFAULT NULL,
  `bookingID` varchar(30) DEFAULT NULL,
  `memberList` text,
  `number` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `slots`
--

INSERT INTO `slots` (`date`, `hour`, `eventID`, `bookingID`, `memberList`, `number`) VALUES
('2016-04-10', 0, NULL, NULL, NULL, NULL),
('2016-04-10', 1, NULL, NULL, NULL, NULL),
('2016-04-10', 2, NULL, NULL, NULL, NULL),
('2016-04-10', 3, NULL, NULL, '1001,1001', 2),
('2016-04-10', 4, NULL, NULL, NULL, NULL),
('2016-04-10', 5, NULL, NULL, '1001', 1),
('2016-04-10', 6, NULL, NULL, NULL, NULL),
('2016-04-10', 7, NULL, NULL, NULL, NULL),
('2016-04-10', 8, NULL, NULL, NULL, NULL),
('2016-04-10', 9, NULL, NULL, NULL, NULL),
('2016-04-10', 10, NULL, NULL, NULL, NULL),
('2016-04-10', 11, NULL, NULL, NULL, NULL),
('2016-04-10', 12, NULL, NULL, NULL, NULL),
('2016-04-10', 13, NULL, NULL, NULL, NULL),
('2016-04-10', 14, NULL, NULL, NULL, NULL),
('2016-04-10', 15, NULL, NULL, NULL, NULL),
('2016-04-10', 16, NULL, NULL, NULL, NULL),
('2016-04-10', 17, NULL, NULL, NULL, NULL),
('2016-04-10', 18, NULL, NULL, NULL, NULL),
('2016-04-10', 19, NULL, NULL, NULL, NULL),
('2016-04-10', 20, NULL, NULL, NULL, NULL),
('2016-04-10', 21, NULL, NULL, '1001', 1),
('2016-04-10', 22, NULL, NULL, NULL, NULL),
('2016-04-10', 23, NULL, NULL, '1001', 1),
('2016-04-11', 0, NULL, NULL, NULL, NULL),
('2016-04-11', 1, NULL, NULL, '1001', 1),
('2016-04-11', 2, NULL, NULL, NULL, NULL),
('2016-04-11', 3, NULL, NULL, '1001', 1),
('2016-04-11', 4, NULL, NULL, NULL, NULL),
('2016-04-11', 5, NULL, NULL, '1001', 1),
('2016-04-11', 6, NULL, NULL, NULL, NULL),
('2016-04-11', 7, NULL, NULL, NULL, NULL),
('2016-04-11', 8, NULL, NULL, NULL, NULL),
('2016-04-11', 9, NULL, NULL, NULL, NULL),
('2016-04-11', 10, NULL, NULL, NULL, NULL),
('2016-04-11', 11, NULL, NULL, NULL, NULL),
('2016-04-11', 12, NULL, NULL, NULL, NULL),
('2016-04-11', 13, NULL, NULL, NULL, NULL),
('2016-04-11', 14, NULL, NULL, NULL, NULL),
('2016-04-11', 15, NULL, NULL, NULL, NULL),
('2016-04-11', 16, NULL, NULL, NULL, NULL),
('2016-04-11', 17, NULL, NULL, NULL, NULL),
('2016-04-11', 18, NULL, NULL, NULL, NULL),
('2016-04-11', 19, NULL, NULL, NULL, NULL),
('2016-04-11', 20, NULL, NULL, NULL, NULL),
('2016-04-11', 21, NULL, NULL, NULL, NULL),
('2016-04-11', 22, NULL, NULL, NULL, NULL),
('2016-04-11', 23, NULL, NULL, NULL, NULL),
('2016-04-12', 0, NULL, NULL, NULL, NULL),
('2016-04-12', 1, NULL, NULL, NULL, NULL),
('2016-04-12', 2, NULL, NULL, NULL, NULL),
('2016-04-12', 3, NULL, NULL, NULL, NULL),
('2016-04-12', 4, NULL, NULL, NULL, NULL),
('2016-04-12', 5, NULL, NULL, NULL, NULL),
('2016-04-12', 6, NULL, NULL, NULL, NULL),
('2016-04-12', 7, NULL, NULL, '1001', 1),
('2016-04-12', 8, NULL, NULL, NULL, NULL),
('2016-04-12', 9, NULL, NULL, NULL, NULL),
('2016-04-12', 10, NULL, NULL, NULL, NULL),
('2016-04-12', 11, NULL, NULL, NULL, NULL),
('2016-04-12', 12, NULL, '1001_12_4_2016', NULL, NULL),
('2016-04-12', 13, NULL, NULL, NULL, NULL),
('2016-04-12', 14, NULL, NULL, NULL, NULL),
('2016-04-12', 15, NULL, NULL, NULL, NULL),
('2016-04-12', 16, NULL, NULL, NULL, NULL),
('2016-04-12', 17, NULL, NULL, NULL, NULL),
('2016-04-12', 18, NULL, NULL, NULL, NULL),
('2016-04-12', 19, NULL, NULL, NULL, NULL),
('2016-04-12', 20, NULL, NULL, NULL, NULL),
('2016-04-12', 21, NULL, NULL, NULL, NULL),
('2016-04-12', 22, NULL, NULL, NULL, NULL),
('2016-04-12', 23, NULL, NULL, NULL, NULL),
('2016-04-13', 0, NULL, NULL, NULL, NULL),
('2016-04-13', 1, NULL, NULL, NULL, NULL),
('2016-04-13', 2, NULL, NULL, NULL, NULL),
('2016-04-13', 3, NULL, NULL, NULL, NULL),
('2016-04-13', 4, NULL, NULL, NULL, NULL),
('2016-04-13', 5, NULL, NULL, NULL, NULL),
('2016-04-13', 6, NULL, NULL, NULL, NULL),
('2016-04-13', 7, NULL, NULL, NULL, NULL),
('2016-04-13', 8, NULL, NULL, NULL, NULL),
('2016-04-13', 9, NULL, NULL, NULL, NULL),
('2016-04-13', 10, NULL, NULL, NULL, NULL),
('2016-04-13', 11, NULL, NULL, NULL, NULL),
('2016-04-13', 12, NULL, NULL, NULL, NULL),
('2016-04-13', 13, NULL, NULL, NULL, NULL),
('2016-04-13', 14, NULL, NULL, NULL, NULL),
('2016-04-13', 15, NULL, NULL, NULL, NULL),
('2016-04-13', 16, NULL, NULL, NULL, NULL),
('2016-04-13', 17, NULL, NULL, NULL, NULL),
('2016-04-13', 18, NULL, NULL, NULL, NULL),
('2016-04-13', 19, NULL, NULL, NULL, NULL),
('2016-04-13', 20, NULL, NULL, NULL, NULL),
('2016-04-13', 21, NULL, NULL, NULL, NULL),
('2016-04-13', 22, NULL, NULL, NULL, NULL),
('2016-04-13', 23, NULL, NULL, NULL, NULL),
('2016-04-14', 0, NULL, NULL, NULL, NULL),
('2016-04-14', 1, NULL, NULL, NULL, NULL),
('2016-04-14', 2, NULL, NULL, NULL, NULL),
('2016-04-14', 3, NULL, NULL, NULL, NULL),
('2016-04-14', 4, NULL, NULL, NULL, NULL),
('2016-04-14', 5, NULL, NULL, NULL, NULL),
('2016-04-14', 6, NULL, NULL, NULL, NULL),
('2016-04-14', 7, NULL, NULL, NULL, NULL),
('2016-04-14', 8, NULL, NULL, NULL, NULL),
('2016-04-14', 9, NULL, NULL, NULL, NULL),
('2016-04-14', 10, NULL, NULL, NULL, NULL),
('2016-04-14', 11, NULL, NULL, NULL, NULL),
('2016-04-14', 12, NULL, NULL, NULL, NULL),
('2016-04-14', 13, NULL, NULL, NULL, NULL),
('2016-04-14', 14, NULL, NULL, NULL, NULL),
('2016-04-14', 15, NULL, NULL, NULL, NULL),
('2016-04-14', 16, NULL, NULL, NULL, NULL),
('2016-04-14', 17, NULL, NULL, NULL, NULL),
('2016-04-14', 18, NULL, NULL, NULL, NULL),
('2016-04-14', 19, NULL, NULL, NULL, NULL),
('2016-04-14', 20, NULL, NULL, NULL, NULL),
('2016-04-14', 21, NULL, NULL, NULL, NULL),
('2016-04-14', 22, NULL, NULL, NULL, NULL),
('2016-04-14', 23, NULL, NULL, NULL, NULL),
('2016-04-15', 0, NULL, NULL, NULL, NULL),
('2016-04-15', 1, NULL, NULL, NULL, NULL),
('2016-04-15', 2, NULL, NULL, NULL, NULL),
('2016-04-15', 3, NULL, NULL, NULL, NULL),
('2016-04-15', 4, NULL, NULL, NULL, NULL),
('2016-04-15', 5, NULL, NULL, NULL, NULL),
('2016-04-15', 6, NULL, NULL, NULL, NULL),
('2016-04-15', 7, NULL, NULL, NULL, NULL),
('2016-04-15', 8, NULL, NULL, NULL, NULL),
('2016-04-15', 9, NULL, NULL, NULL, NULL),
('2016-04-15', 10, NULL, NULL, NULL, NULL),
('2016-04-15', 11, NULL, NULL, NULL, NULL),
('2016-04-15', 12, NULL, NULL, NULL, NULL),
('2016-04-15', 13, NULL, NULL, NULL, NULL),
('2016-04-15', 14, NULL, NULL, NULL, NULL),
('2016-04-15', 15, NULL, NULL, NULL, NULL),
('2016-04-15', 16, NULL, NULL, NULL, NULL),
('2016-04-15', 17, NULL, NULL, NULL, NULL),
('2016-04-15', 18, NULL, NULL, NULL, NULL),
('2016-04-15', 19, NULL, NULL, NULL, NULL),
('2016-04-15', 20, NULL, NULL, NULL, NULL),
('2016-04-15', 21, NULL, NULL, NULL, NULL),
('2016-04-15', 22, NULL, NULL, NULL, NULL),
('2016-04-15', 23, NULL, NULL, NULL, NULL),
('2016-04-16', 0, NULL, NULL, NULL, NULL),
('2016-04-16', 1, NULL, NULL, NULL, NULL),
('2016-04-16', 2, NULL, NULL, NULL, NULL),
('2016-04-16', 3, NULL, NULL, NULL, NULL),
('2016-04-16', 4, NULL, NULL, NULL, NULL),
('2016-04-16', 5, NULL, NULL, NULL, NULL),
('2016-04-16', 6, NULL, NULL, NULL, NULL),
('2016-04-16', 7, NULL, NULL, NULL, NULL),
('2016-04-16', 8, NULL, NULL, NULL, NULL),
('2016-04-16', 9, NULL, NULL, NULL, NULL),
('2016-04-16', 10, NULL, NULL, NULL, NULL),
('2016-04-16', 11, NULL, NULL, NULL, NULL),
('2016-04-16', 12, NULL, NULL, NULL, NULL),
('2016-04-16', 13, NULL, NULL, NULL, NULL),
('2016-04-16', 14, NULL, NULL, NULL, NULL),
('2016-04-16', 15, NULL, NULL, NULL, NULL),
('2016-04-16', 16, NULL, NULL, NULL, NULL),
('2016-04-16', 17, NULL, NULL, NULL, NULL),
('2016-04-16', 18, NULL, NULL, NULL, NULL),
('2016-04-16', 19, NULL, NULL, NULL, NULL),
('2016-04-16', 20, NULL, NULL, NULL, NULL),
('2016-04-16', 21, NULL, NULL, NULL, NULL),
('2016-04-16', 22, NULL, NULL, NULL, NULL),
('2016-04-16', 23, NULL, NULL, NULL, NULL),
('2016-04-17', 0, NULL, NULL, NULL, NULL),
('2016-04-17', 1, NULL, NULL, '1001', 1),
('2016-04-17', 2, NULL, NULL, NULL, NULL),
('2016-04-17', 3, NULL, NULL, NULL, NULL),
('2016-04-17', 4, NULL, NULL, NULL, NULL),
('2016-04-17', 5, NULL, NULL, NULL, NULL),
('2016-04-17', 6, NULL, NULL, NULL, NULL),
('2016-04-17', 7, NULL, NULL, NULL, NULL),
('2016-04-17', 8, NULL, NULL, NULL, NULL),
('2016-04-17', 9, NULL, NULL, NULL, NULL),
('2016-04-17', 10, NULL, NULL, NULL, NULL),
('2016-04-17', 11, NULL, NULL, NULL, NULL),
('2016-04-17', 12, NULL, NULL, NULL, NULL),
('2016-04-17', 13, NULL, NULL, NULL, NULL),
('2016-04-17', 14, NULL, NULL, NULL, NULL),
('2016-04-17', 15, NULL, NULL, NULL, NULL),
('2016-04-17', 16, NULL, NULL, '1001', 1),
('2016-04-17', 17, NULL, NULL, NULL, NULL),
('2016-04-17', 18, NULL, NULL, NULL, NULL),
('2016-04-17', 19, NULL, NULL, NULL, NULL),
('2016-04-17', 20, NULL, NULL, '1001', 1),
('2016-04-17', 21, NULL, NULL, NULL, NULL),
('2016-04-17', 22, NULL, NULL, '1001', 1),
('2016-04-17', 23, NULL, NULL, NULL, NULL),
('2016-04-18', 0, NULL, NULL, NULL, NULL),
('2016-04-18', 1, NULL, NULL, NULL, NULL),
('2016-04-18', 2, NULL, NULL, NULL, NULL),
('2016-04-18', 3, NULL, NULL, NULL, NULL),
('2016-04-18', 4, NULL, NULL, '1001', 1),
('2016-04-18', 5, NULL, NULL, NULL, NULL),
('2016-04-18', 6, NULL, NULL, NULL, NULL),
('2016-04-18', 7, NULL, NULL, NULL, NULL),
('2016-04-18', 8, NULL, NULL, NULL, NULL),
('2016-04-18', 9, NULL, NULL, NULL, NULL),
('2016-04-18', 10, NULL, NULL, NULL, NULL),
('2016-04-18', 11, NULL, NULL, NULL, NULL),
('2016-04-18', 12, NULL, NULL, NULL, NULL),
('2016-04-18', 13, NULL, NULL, NULL, NULL),
('2016-04-18', 14, NULL, NULL, NULL, NULL),
('2016-04-18', 15, NULL, NULL, NULL, NULL),
('2016-04-18', 16, NULL, NULL, NULL, NULL),
('2016-04-18', 17, NULL, NULL, NULL, NULL),
('2016-04-18', 18, NULL, NULL, NULL, NULL),
('2016-04-18', 19, NULL, NULL, NULL, NULL),
('2016-04-18', 20, NULL, NULL, NULL, NULL),
('2016-04-18', 21, NULL, NULL, NULL, NULL),
('2016-04-18', 22, NULL, NULL, NULL, NULL),
('2016-04-18', 23, NULL, NULL, NULL, NULL),
('2016-04-19', 0, NULL, NULL, NULL, NULL),
('2016-04-19', 1, NULL, NULL, NULL, NULL),
('2016-04-19', 2, NULL, NULL, NULL, NULL),
('2016-04-19', 3, NULL, NULL, NULL, NULL),
('2016-04-19', 4, NULL, NULL, NULL, NULL),
('2016-04-19', 5, NULL, NULL, NULL, NULL),
('2016-04-19', 6, NULL, NULL, NULL, NULL),
('2016-04-19', 7, NULL, NULL, NULL, NULL),
('2016-04-19', 8, NULL, NULL, NULL, NULL),
('2016-04-19', 9, NULL, NULL, NULL, NULL),
('2016-04-19', 10, NULL, NULL, NULL, NULL),
('2016-04-19', 11, NULL, NULL, NULL, NULL),
('2016-04-19', 12, NULL, NULL, NULL, NULL),
('2016-04-19', 13, NULL, NULL, NULL, NULL),
('2016-04-19', 14, NULL, NULL, NULL, NULL),
('2016-04-19', 15, NULL, NULL, NULL, NULL),
('2016-04-19', 16, NULL, NULL, NULL, NULL),
('2016-04-19', 17, NULL, NULL, NULL, NULL),
('2016-04-19', 18, NULL, NULL, NULL, NULL),
('2016-04-19', 19, NULL, NULL, NULL, NULL),
('2016-04-19', 20, NULL, NULL, NULL, NULL),
('2016-04-19', 21, NULL, NULL, NULL, NULL),
('2016-04-19', 22, NULL, NULL, NULL, NULL),
('2016-04-19', 23, NULL, NULL, NULL, NULL),
('2016-04-20', 0, NULL, NULL, NULL, NULL),
('2016-04-20', 1, NULL, NULL, NULL, NULL),
('2016-04-20', 2, NULL, NULL, NULL, NULL),
('2016-04-20', 3, NULL, NULL, NULL, NULL),
('2016-04-20', 4, NULL, NULL, NULL, NULL),
('2016-04-20', 5, NULL, NULL, NULL, NULL),
('2016-04-20', 6, NULL, NULL, NULL, NULL),
('2016-04-20', 7, NULL, NULL, NULL, NULL),
('2016-04-20', 8, NULL, NULL, NULL, NULL),
('2016-04-20', 9, NULL, NULL, NULL, NULL),
('2016-04-20', 10, NULL, NULL, NULL, NULL),
('2016-04-20', 11, NULL, NULL, NULL, NULL),
('2016-04-20', 12, NULL, NULL, NULL, NULL),
('2016-04-20', 13, NULL, NULL, NULL, NULL),
('2016-04-20', 14, NULL, NULL, NULL, NULL),
('2016-04-20', 15, NULL, NULL, NULL, NULL),
('2016-04-20', 16, NULL, NULL, NULL, NULL),
('2016-04-20', 17, NULL, NULL, NULL, NULL),
('2016-04-20', 18, NULL, NULL, NULL, NULL),
('2016-04-20', 19, NULL, NULL, NULL, NULL),
('2016-04-20', 20, NULL, NULL, NULL, NULL),
('2016-04-20', 21, NULL, NULL, NULL, NULL),
('2016-04-20', 22, NULL, NULL, NULL, NULL),
('2016-04-20', 23, NULL, NULL, NULL, NULL),
('2016-04-21', 0, NULL, NULL, NULL, NULL),
('2016-04-21', 1, NULL, NULL, NULL, NULL),
('2016-04-21', 2, NULL, NULL, NULL, NULL),
('2016-04-21', 3, NULL, NULL, NULL, NULL),
('2016-04-21', 4, NULL, NULL, NULL, NULL),
('2016-04-21', 5, NULL, NULL, NULL, NULL),
('2016-04-21', 6, NULL, NULL, NULL, NULL),
('2016-04-21', 7, NULL, NULL, NULL, NULL),
('2016-04-21', 8, NULL, NULL, NULL, NULL),
('2016-04-21', 9, NULL, NULL, NULL, NULL),
('2016-04-21', 10, NULL, NULL, NULL, NULL),
('2016-04-21', 11, NULL, NULL, NULL, NULL),
('2016-04-21', 12, NULL, NULL, NULL, NULL),
('2016-04-21', 13, NULL, NULL, NULL, NULL),
('2016-04-21', 14, NULL, NULL, NULL, NULL),
('2016-04-21', 15, NULL, NULL, NULL, NULL),
('2016-04-21', 16, NULL, NULL, NULL, NULL),
('2016-04-21', 17, NULL, NULL, NULL, NULL),
('2016-04-21', 18, NULL, NULL, NULL, NULL),
('2016-04-21', 19, NULL, NULL, NULL, NULL),
('2016-04-21', 20, NULL, NULL, NULL, NULL),
('2016-04-21', 21, NULL, NULL, NULL, NULL),
('2016-04-21', 22, NULL, NULL, NULL, NULL),
('2016-04-21', 23, NULL, NULL, NULL, NULL),
('2016-04-22', 0, NULL, NULL, NULL, NULL),
('2016-04-22', 1, NULL, NULL, NULL, NULL),
('2016-04-22', 2, NULL, NULL, NULL, NULL),
('2016-04-22', 3, NULL, NULL, NULL, NULL),
('2016-04-22', 4, NULL, NULL, NULL, NULL),
('2016-04-22', 5, NULL, NULL, NULL, NULL),
('2016-04-22', 6, NULL, NULL, NULL, NULL),
('2016-04-22', 7, NULL, NULL, NULL, NULL),
('2016-04-22', 8, NULL, NULL, NULL, NULL),
('2016-04-22', 9, NULL, NULL, NULL, NULL),
('2016-04-22', 10, NULL, NULL, NULL, NULL),
('2016-04-22', 11, NULL, NULL, NULL, NULL),
('2016-04-22', 12, NULL, NULL, NULL, NULL),
('2016-04-22', 13, NULL, NULL, NULL, NULL),
('2016-04-22', 14, NULL, NULL, NULL, NULL),
('2016-04-22', 15, NULL, NULL, NULL, NULL),
('2016-04-22', 16, NULL, NULL, NULL, NULL),
('2016-04-22', 17, NULL, NULL, NULL, NULL),
('2016-04-22', 18, NULL, NULL, NULL, NULL),
('2016-04-22', 19, NULL, NULL, NULL, NULL),
('2016-04-22', 20, NULL, NULL, NULL, NULL),
('2016-04-22', 21, NULL, NULL, NULL, NULL),
('2016-04-22', 22, NULL, NULL, NULL, NULL),
('2016-04-22', 23, NULL, NULL, NULL, NULL),
('2016-04-23', 0, NULL, NULL, NULL, NULL),
('2016-04-23', 1, NULL, NULL, NULL, NULL),
('2016-04-23', 2, NULL, NULL, NULL, NULL),
('2016-04-23', 3, NULL, NULL, NULL, NULL),
('2016-04-23', 4, NULL, NULL, NULL, NULL),
('2016-04-23', 5, NULL, NULL, NULL, NULL),
('2016-04-23', 6, NULL, NULL, NULL, NULL),
('2016-04-23', 7, NULL, NULL, NULL, NULL),
('2016-04-23', 8, NULL, NULL, NULL, NULL),
('2016-04-23', 9, NULL, NULL, NULL, NULL),
('2016-04-23', 10, NULL, NULL, NULL, NULL),
('2016-04-23', 11, NULL, NULL, NULL, NULL),
('2016-04-23', 12, NULL, NULL, NULL, NULL),
('2016-04-23', 13, NULL, NULL, NULL, NULL),
('2016-04-23', 14, NULL, NULL, NULL, NULL),
('2016-04-23', 15, NULL, NULL, NULL, NULL),
('2016-04-23', 16, NULL, NULL, NULL, NULL),
('2016-04-23', 17, NULL, NULL, NULL, NULL),
('2016-04-23', 18, NULL, NULL, NULL, NULL),
('2016-04-23', 19, NULL, NULL, NULL, NULL),
('2016-04-23', 20, NULL, NULL, NULL, NULL),
('2016-04-23', 21, NULL, NULL, NULL, NULL),
('2016-04-23', 22, NULL, NULL, NULL, NULL),
('2016-04-23', 23, NULL, NULL, NULL, NULL),
('2016-04-24', 0, NULL, NULL, NULL, NULL),
('2016-04-24', 1, NULL, NULL, NULL, NULL),
('2016-04-24', 2, NULL, NULL, NULL, NULL),
('2016-04-24', 3, NULL, NULL, NULL, NULL),
('2016-04-24', 4, NULL, NULL, NULL, NULL),
('2016-04-24', 5, NULL, NULL, NULL, NULL),
('2016-04-24', 6, NULL, NULL, NULL, NULL),
('2016-04-24', 7, NULL, NULL, '1001', 1),
('2016-04-24', 8, NULL, NULL, NULL, NULL),
('2016-04-24', 9, NULL, NULL, NULL, NULL),
('2016-04-24', 10, NULL, NULL, NULL, NULL),
('2016-04-24', 11, NULL, NULL, NULL, NULL),
('2016-04-24', 12, NULL, NULL, '1001', 1),
('2016-04-24', 13, NULL, NULL, NULL, NULL),
('2016-04-24', 14, NULL, NULL, NULL, NULL),
('2016-04-24', 15, NULL, NULL, '1001', 1),
('2016-04-24', 16, NULL, NULL, NULL, NULL),
('2016-04-24', 17, NULL, NULL, NULL, NULL),
('2016-04-24', 18, NULL, NULL, NULL, NULL),
('2016-04-24', 19, NULL, NULL, '1001', 1),
('2016-04-24', 20, NULL, NULL, NULL, NULL),
('2016-04-24', 21, NULL, NULL, '1001', 1),
('2016-04-24', 22, NULL, NULL, NULL, NULL),
('2016-04-24', 23, NULL, NULL, NULL, NULL),
('2016-04-25', 0, NULL, NULL, NULL, NULL),
('2016-04-25', 1, NULL, NULL, NULL, NULL),
('2016-04-25', 2, NULL, NULL, NULL, NULL),
('2016-04-25', 3, NULL, NULL, NULL, NULL),
('2016-04-25', 4, NULL, NULL, NULL, NULL),
('2016-04-25', 5, NULL, NULL, NULL, NULL),
('2016-04-25', 6, NULL, NULL, NULL, NULL),
('2016-04-25', 7, NULL, NULL, NULL, NULL),
('2016-04-25', 8, NULL, NULL, NULL, NULL),
('2016-04-25', 9, NULL, NULL, NULL, NULL),
('2016-04-25', 10, NULL, NULL, NULL, NULL),
('2016-04-25', 11, NULL, NULL, NULL, NULL),
('2016-04-25', 12, NULL, NULL, NULL, NULL),
('2016-04-25', 13, NULL, NULL, NULL, NULL),
('2016-04-25', 14, NULL, NULL, NULL, NULL),
('2016-04-25', 15, NULL, NULL, NULL, NULL),
('2016-04-25', 16, NULL, NULL, NULL, NULL),
('2016-04-25', 17, NULL, NULL, NULL, NULL),
('2016-04-25', 18, NULL, NULL, NULL, NULL),
('2016-04-25', 19, NULL, NULL, NULL, NULL),
('2016-04-25', 20, NULL, NULL, NULL, NULL),
('2016-04-25', 21, NULL, NULL, NULL, NULL),
('2016-04-25', 22, NULL, NULL, NULL, NULL),
('2016-04-25', 23, NULL, NULL, NULL, NULL),
('2016-04-26', 0, NULL, NULL, NULL, NULL),
('2016-04-26', 1, NULL, NULL, NULL, NULL),
('2016-04-26', 2, NULL, NULL, NULL, NULL),
('2016-04-26', 3, NULL, NULL, NULL, NULL),
('2016-04-26', 4, NULL, NULL, NULL, NULL),
('2016-04-26', 5, NULL, NULL, NULL, NULL),
('2016-04-26', 6, NULL, NULL, NULL, NULL),
('2016-04-26', 7, NULL, NULL, NULL, NULL),
('2016-04-26', 8, NULL, NULL, NULL, NULL),
('2016-04-26', 9, NULL, NULL, NULL, NULL),
('2016-04-26', 10, NULL, NULL, NULL, NULL),
('2016-04-26', 11, NULL, NULL, NULL, NULL),
('2016-04-26', 12, NULL, NULL, NULL, NULL),
('2016-04-26', 13, NULL, NULL, NULL, NULL),
('2016-04-26', 14, NULL, NULL, NULL, NULL),
('2016-04-26', 15, NULL, NULL, NULL, NULL),
('2016-04-26', 16, NULL, NULL, NULL, NULL),
('2016-04-26', 17, NULL, NULL, NULL, NULL),
('2016-04-26', 18, NULL, NULL, NULL, NULL),
('2016-04-26', 19, NULL, NULL, NULL, NULL),
('2016-04-26', 20, NULL, NULL, NULL, NULL),
('2016-04-26', 21, NULL, NULL, NULL, NULL),
('2016-04-26', 22, NULL, NULL, NULL, NULL),
('2016-04-26', 23, NULL, NULL, NULL, NULL),
('2016-04-27', 0, NULL, NULL, NULL, NULL),
('2016-04-27', 1, NULL, NULL, NULL, NULL),
('2016-04-27', 2, NULL, NULL, NULL, NULL),
('2016-04-27', 3, NULL, NULL, NULL, NULL),
('2016-04-27', 4, NULL, NULL, NULL, NULL),
('2016-04-27', 5, NULL, NULL, NULL, NULL),
('2016-04-27', 6, NULL, NULL, NULL, NULL),
('2016-04-27', 7, NULL, NULL, NULL, NULL),
('2016-04-27', 8, NULL, NULL, NULL, NULL),
('2016-04-27', 9, NULL, NULL, NULL, NULL),
('2016-04-27', 10, NULL, NULL, NULL, NULL),
('2016-04-27', 11, NULL, NULL, NULL, NULL),
('2016-04-27', 12, NULL, NULL, NULL, NULL),
('2016-04-27', 13, NULL, NULL, NULL, NULL),
('2016-04-27', 14, NULL, NULL, NULL, NULL),
('2016-04-27', 15, NULL, NULL, NULL, NULL),
('2016-04-27', 16, NULL, NULL, NULL, NULL),
('2016-04-27', 17, NULL, NULL, NULL, NULL),
('2016-04-27', 18, NULL, NULL, NULL, NULL),
('2016-04-27', 19, NULL, NULL, NULL, NULL),
('2016-04-27', 20, NULL, NULL, NULL, NULL),
('2016-04-27', 21, NULL, NULL, NULL, NULL),
('2016-04-27', 22, NULL, NULL, NULL, NULL),
('2016-04-27', 23, NULL, NULL, NULL, NULL),
('2016-04-28', 0, NULL, NULL, NULL, NULL),
('2016-04-28', 1, NULL, NULL, NULL, NULL),
('2016-04-28', 2, NULL, NULL, NULL, NULL),
('2016-04-28', 3, NULL, NULL, NULL, NULL),
('2016-04-28', 4, NULL, NULL, NULL, NULL),
('2016-04-28', 5, NULL, NULL, NULL, NULL),
('2016-04-28', 6, NULL, NULL, NULL, NULL),
('2016-04-28', 7, NULL, NULL, NULL, NULL),
('2016-04-28', 8, NULL, NULL, NULL, NULL),
('2016-04-28', 9, NULL, NULL, NULL, NULL),
('2016-04-28', 10, NULL, NULL, NULL, NULL),
('2016-04-28', 11, NULL, NULL, NULL, NULL),
('2016-04-28', 12, NULL, NULL, NULL, NULL),
('2016-04-28', 13, NULL, NULL, NULL, NULL),
('2016-04-28', 14, NULL, NULL, NULL, NULL),
('2016-04-28', 15, NULL, NULL, NULL, NULL),
('2016-04-28', 16, NULL, NULL, NULL, NULL),
('2016-04-28', 17, NULL, NULL, NULL, NULL),
('2016-04-28', 18, NULL, NULL, NULL, NULL),
('2016-04-28', 19, NULL, NULL, NULL, NULL),
('2016-04-28', 20, NULL, NULL, NULL, NULL),
('2016-04-28', 21, NULL, NULL, NULL, NULL),
('2016-04-28', 22, NULL, NULL, NULL, NULL),
('2016-04-28', 23, NULL, NULL, NULL, NULL),
('2016-04-29', 0, NULL, NULL, NULL, NULL),
('2016-04-29', 1, NULL, NULL, NULL, NULL),
('2016-04-29', 2, NULL, NULL, NULL, NULL),
('2016-04-29', 3, NULL, NULL, NULL, NULL),
('2016-04-29', 4, NULL, NULL, NULL, NULL),
('2016-04-29', 5, NULL, NULL, NULL, NULL),
('2016-04-29', 6, NULL, NULL, NULL, NULL),
('2016-04-29', 7, NULL, NULL, NULL, NULL),
('2016-04-29', 8, NULL, NULL, NULL, NULL),
('2016-04-29', 9, NULL, NULL, NULL, NULL),
('2016-04-29', 10, NULL, NULL, NULL, NULL),
('2016-04-29', 11, NULL, NULL, NULL, NULL),
('2016-04-29', 12, NULL, NULL, NULL, NULL),
('2016-04-29', 13, NULL, NULL, NULL, NULL),
('2016-04-29', 14, NULL, NULL, NULL, NULL),
('2016-04-29', 15, NULL, NULL, NULL, NULL),
('2016-04-29', 16, NULL, NULL, NULL, NULL),
('2016-04-29', 17, NULL, NULL, NULL, NULL),
('2016-04-29', 18, NULL, NULL, NULL, NULL),
('2016-04-29', 19, NULL, NULL, NULL, NULL),
('2016-04-29', 20, NULL, NULL, NULL, NULL),
('2016-04-29', 21, NULL, NULL, NULL, NULL),
('2016-04-29', 22, NULL, NULL, NULL, NULL),
('2016-04-29', 23, NULL, NULL, NULL, NULL),
('2016-04-30', 0, NULL, NULL, NULL, NULL),
('2016-04-30', 1, NULL, NULL, NULL, NULL),
('2016-04-30', 2, NULL, NULL, NULL, NULL),
('2016-04-30', 3, NULL, NULL, NULL, NULL),
('2016-04-30', 4, NULL, NULL, NULL, NULL),
('2016-04-30', 5, NULL, NULL, NULL, NULL),
('2016-04-30', 6, NULL, NULL, NULL, NULL),
('2016-04-30', 7, NULL, NULL, NULL, NULL),
('2016-04-30', 8, NULL, NULL, NULL, NULL),
('2016-04-30', 9, NULL, NULL, NULL, NULL),
('2016-04-30', 10, NULL, NULL, NULL, NULL),
('2016-04-30', 11, NULL, NULL, NULL, NULL),
('2016-04-30', 12, NULL, NULL, NULL, NULL),
('2016-04-30', 13, NULL, NULL, NULL, NULL),
('2016-04-30', 14, NULL, NULL, NULL, NULL),
('2016-04-30', 15, NULL, NULL, NULL, NULL),
('2016-04-30', 16, NULL, NULL, NULL, NULL),
('2016-04-30', 17, NULL, NULL, NULL, NULL),
('2016-04-30', 18, NULL, NULL, NULL, NULL),
('2016-04-30', 19, NULL, NULL, NULL, NULL),
('2016-04-30', 20, NULL, NULL, NULL, NULL),
('2016-04-30', 21, NULL, NULL, NULL, NULL),
('2016-04-30', 22, NULL, NULL, NULL, NULL),
('2016-04-30', 23, NULL, NULL, NULL, NULL),
('2016-05-01', 0, NULL, NULL, NULL, NULL),
('2016-05-01', 1, NULL, NULL, NULL, NULL),
('2016-05-01', 2, NULL, NULL, NULL, NULL),
('2016-05-01', 3, NULL, NULL, NULL, NULL),
('2016-05-01', 4, NULL, NULL, NULL, NULL),
('2016-05-01', 5, NULL, NULL, NULL, NULL),
('2016-05-01', 6, NULL, NULL, NULL, NULL),
('2016-05-01', 7, NULL, NULL, NULL, NULL),
('2016-05-01', 8, NULL, NULL, NULL, NULL),
('2016-05-01', 9, NULL, NULL, NULL, NULL),
('2016-05-01', 10, NULL, NULL, NULL, NULL),
('2016-05-01', 11, NULL, NULL, NULL, NULL),
('2016-05-01', 12, NULL, NULL, NULL, NULL),
('2016-05-01', 13, NULL, NULL, NULL, NULL),
('2016-05-01', 14, NULL, NULL, NULL, NULL),
('2016-05-01', 15, NULL, NULL, NULL, NULL),
('2016-05-01', 16, NULL, NULL, NULL, NULL),
('2016-05-01', 17, NULL, NULL, NULL, NULL),
('2016-05-01', 18, NULL, NULL, NULL, NULL),
('2016-05-01', 19, NULL, NULL, NULL, NULL),
('2016-05-01', 20, NULL, NULL, NULL, NULL),
('2016-05-01', 21, NULL, NULL, NULL, NULL),
('2016-05-01', 22, NULL, NULL, NULL, NULL),
('2016-05-01', 23, NULL, NULL, NULL, NULL),
('2016-05-02', 0, NULL, NULL, NULL, NULL),
('2016-05-02', 1, NULL, NULL, NULL, NULL),
('2016-05-02', 2, NULL, NULL, NULL, NULL),
('2016-05-02', 3, NULL, NULL, NULL, NULL),
('2016-05-02', 4, NULL, NULL, NULL, NULL),
('2016-05-02', 5, NULL, NULL, NULL, NULL),
('2016-05-02', 6, NULL, NULL, NULL, NULL),
('2016-05-02', 7, NULL, NULL, NULL, NULL),
('2016-05-02', 8, NULL, NULL, NULL, NULL),
('2016-05-02', 9, NULL, NULL, NULL, NULL),
('2016-05-02', 10, NULL, NULL, NULL, NULL),
('2016-05-02', 11, NULL, NULL, NULL, NULL),
('2016-05-02', 12, NULL, NULL, NULL, NULL),
('2016-05-02', 13, NULL, NULL, NULL, NULL),
('2016-05-02', 14, NULL, NULL, NULL, NULL),
('2016-05-02', 15, NULL, NULL, NULL, NULL),
('2016-05-02', 16, NULL, NULL, NULL, NULL),
('2016-05-02', 17, NULL, NULL, NULL, NULL),
('2016-05-02', 18, NULL, NULL, NULL, NULL),
('2016-05-02', 19, NULL, NULL, NULL, NULL),
('2016-05-02', 20, NULL, NULL, NULL, NULL),
('2016-05-02', 21, NULL, NULL, NULL, NULL),
('2016-05-02', 22, NULL, NULL, NULL, NULL),
('2016-05-02', 23, NULL, NULL, NULL, NULL),
('2016-05-03', 0, NULL, NULL, NULL, NULL),
('2016-05-03', 1, NULL, NULL, NULL, NULL),
('2016-05-03', 2, NULL, NULL, NULL, NULL),
('2016-05-03', 3, NULL, NULL, NULL, NULL),
('2016-05-03', 4, NULL, NULL, NULL, NULL),
('2016-05-03', 5, NULL, NULL, NULL, NULL),
('2016-05-03', 6, NULL, NULL, NULL, NULL),
('2016-05-03', 7, NULL, NULL, NULL, NULL),
('2016-05-03', 8, NULL, NULL, NULL, NULL),
('2016-05-03', 9, NULL, NULL, NULL, NULL),
('2016-05-03', 10, NULL, NULL, NULL, NULL),
('2016-05-03', 11, NULL, NULL, NULL, NULL),
('2016-05-03', 12, NULL, NULL, NULL, NULL),
('2016-05-03', 13, NULL, NULL, NULL, NULL),
('2016-05-03', 14, NULL, NULL, NULL, NULL),
('2016-05-03', 15, NULL, NULL, NULL, NULL),
('2016-05-03', 16, NULL, NULL, NULL, NULL),
('2016-05-03', 17, NULL, NULL, NULL, NULL),
('2016-05-03', 18, NULL, NULL, NULL, NULL),
('2016-05-03', 19, NULL, NULL, NULL, NULL),
('2016-05-03', 20, NULL, NULL, NULL, NULL),
('2016-05-03', 21, NULL, NULL, NULL, NULL),
('2016-05-03', 22, NULL, NULL, NULL, NULL),
('2016-05-03', 23, NULL, NULL, NULL, NULL),
('2016-05-04', 0, NULL, NULL, NULL, NULL),
('2016-05-04', 1, NULL, NULL, NULL, NULL),
('2016-05-04', 2, NULL, NULL, NULL, NULL),
('2016-05-04', 3, NULL, NULL, NULL, NULL),
('2016-05-04', 4, NULL, NULL, NULL, NULL),
('2016-05-04', 5, NULL, NULL, NULL, NULL),
('2016-05-04', 6, NULL, NULL, NULL, NULL),
('2016-05-04', 7, NULL, NULL, NULL, NULL),
('2016-05-04', 8, NULL, NULL, NULL, NULL),
('2016-05-04', 9, NULL, NULL, NULL, NULL),
('2016-05-04', 10, NULL, NULL, NULL, NULL),
('2016-05-04', 11, NULL, NULL, NULL, NULL),
('2016-05-04', 12, NULL, NULL, NULL, NULL),
('2016-05-04', 13, NULL, NULL, NULL, NULL),
('2016-05-04', 14, NULL, NULL, NULL, NULL),
('2016-05-04', 15, NULL, NULL, NULL, NULL),
('2016-05-04', 16, NULL, NULL, NULL, NULL),
('2016-05-04', 17, NULL, NULL, NULL, NULL),
('2016-05-04', 18, NULL, NULL, NULL, NULL),
('2016-05-04', 19, NULL, NULL, NULL, NULL),
('2016-05-04', 20, NULL, NULL, NULL, NULL),
('2016-05-04', 21, NULL, NULL, NULL, NULL),
('2016-05-04', 22, NULL, NULL, NULL, NULL),
('2016-05-04', 23, NULL, NULL, NULL, NULL),
('2016-05-05', 0, NULL, NULL, NULL, NULL),
('2016-05-05', 1, NULL, NULL, NULL, NULL),
('2016-05-05', 2, NULL, NULL, NULL, NULL),
('2016-05-05', 3, NULL, NULL, NULL, NULL),
('2016-05-05', 4, NULL, NULL, NULL, NULL),
('2016-05-05', 5, NULL, NULL, NULL, NULL),
('2016-05-05', 6, NULL, NULL, NULL, NULL),
('2016-05-05', 7, NULL, NULL, NULL, NULL),
('2016-05-05', 8, NULL, NULL, NULL, NULL),
('2016-05-05', 9, NULL, NULL, NULL, NULL),
('2016-05-05', 10, NULL, NULL, NULL, NULL),
('2016-05-05', 11, NULL, NULL, NULL, NULL),
('2016-05-05', 12, NULL, NULL, NULL, NULL),
('2016-05-05', 13, NULL, NULL, NULL, NULL),
('2016-05-05', 14, NULL, NULL, NULL, NULL),
('2016-05-05', 15, NULL, NULL, NULL, NULL),
('2016-05-05', 16, NULL, NULL, NULL, NULL),
('2016-05-05', 17, NULL, NULL, NULL, NULL),
('2016-05-05', 18, NULL, NULL, NULL, NULL),
('2016-05-05', 19, NULL, NULL, NULL, NULL),
('2016-05-05', 20, NULL, NULL, NULL, NULL),
('2016-05-05', 21, NULL, NULL, NULL, NULL),
('2016-05-05', 22, NULL, NULL, NULL, NULL),
('2016-05-05', 23, NULL, NULL, NULL, NULL),
('2016-05-06', 0, NULL, NULL, NULL, NULL),
('2016-05-06', 1, NULL, NULL, NULL, NULL),
('2016-05-06', 2, NULL, NULL, NULL, NULL),
('2016-05-06', 3, NULL, NULL, NULL, NULL),
('2016-05-06', 4, NULL, NULL, NULL, NULL),
('2016-05-06', 5, NULL, NULL, NULL, NULL),
('2016-05-06', 6, NULL, NULL, NULL, NULL),
('2016-05-06', 7, NULL, NULL, NULL, NULL),
('2016-05-06', 8, NULL, NULL, NULL, NULL),
('2016-05-06', 9, NULL, NULL, NULL, NULL),
('2016-05-06', 10, NULL, NULL, NULL, NULL),
('2016-05-06', 11, NULL, NULL, NULL, NULL),
('2016-05-06', 12, NULL, NULL, NULL, NULL),
('2016-05-06', 13, NULL, NULL, NULL, NULL),
('2016-05-06', 14, NULL, NULL, NULL, NULL),
('2016-05-06', 15, NULL, NULL, NULL, NULL),
('2016-05-06', 16, NULL, NULL, NULL, NULL),
('2016-05-06', 17, NULL, NULL, NULL, NULL),
('2016-05-06', 18, NULL, NULL, NULL, NULL),
('2016-05-06', 19, NULL, NULL, NULL, NULL),
('2016-05-06', 20, NULL, NULL, NULL, NULL),
('2016-05-06', 21, NULL, NULL, NULL, NULL),
('2016-05-06', 22, NULL, NULL, NULL, NULL),
('2016-05-06', 23, NULL, NULL, NULL, NULL),
('2016-05-07', 0, NULL, NULL, NULL, NULL),
('2016-05-07', 1, NULL, NULL, NULL, NULL),
('2016-05-07', 2, NULL, NULL, NULL, NULL),
('2016-05-07', 3, NULL, NULL, NULL, NULL),
('2016-05-07', 4, NULL, NULL, NULL, NULL),
('2016-05-07', 5, NULL, NULL, NULL, NULL),
('2016-05-07', 6, NULL, NULL, NULL, NULL),
('2016-05-07', 7, NULL, NULL, NULL, NULL),
('2016-05-07', 8, NULL, NULL, NULL, NULL),
('2016-05-07', 9, NULL, NULL, NULL, NULL),
('2016-05-07', 10, NULL, NULL, NULL, NULL),
('2016-05-07', 11, NULL, NULL, NULL, NULL),
('2016-05-07', 12, NULL, NULL, NULL, NULL),
('2016-05-07', 13, NULL, NULL, NULL, NULL),
('2016-05-07', 14, NULL, NULL, NULL, NULL),
('2016-05-07', 15, NULL, NULL, NULL, NULL),
('2016-05-07', 16, NULL, NULL, NULL, NULL),
('2016-05-07', 17, NULL, NULL, NULL, NULL),
('2016-05-07', 18, NULL, NULL, NULL, NULL),
('2016-05-07', 19, NULL, NULL, NULL, NULL),
('2016-05-07', 20, NULL, NULL, NULL, NULL),
('2016-05-07', 21, NULL, NULL, NULL, NULL),
('2016-05-07', 22, NULL, NULL, NULL, NULL),
('2016-05-07', 23, NULL, NULL, NULL, NULL),
('2016-05-08', 0, NULL, NULL, NULL, NULL),
('2016-05-08', 1, NULL, NULL, NULL, NULL),
('2016-05-08', 2, NULL, NULL, NULL, NULL),
('2016-05-08', 3, NULL, NULL, NULL, NULL),
('2016-05-08', 4, NULL, NULL, NULL, NULL),
('2016-05-08', 5, NULL, NULL, NULL, NULL),
('2016-05-08', 6, NULL, NULL, NULL, NULL),
('2016-05-08', 7, NULL, NULL, NULL, NULL),
('2016-05-08', 8, NULL, NULL, NULL, NULL),
('2016-05-08', 9, NULL, NULL, NULL, NULL),
('2016-05-08', 10, NULL, NULL, NULL, NULL),
('2016-05-08', 11, NULL, NULL, NULL, NULL),
('2016-05-08', 12, NULL, NULL, NULL, NULL),
('2016-05-08', 13, NULL, NULL, NULL, NULL),
('2016-05-08', 14, NULL, NULL, NULL, NULL),
('2016-05-08', 15, NULL, NULL, NULL, NULL),
('2016-05-08', 16, NULL, NULL, NULL, NULL),
('2016-05-08', 17, NULL, NULL, NULL, NULL),
('2016-05-08', 18, NULL, NULL, NULL, NULL),
('2016-05-08', 19, NULL, NULL, NULL, NULL),
('2016-05-08', 20, NULL, NULL, NULL, NULL),
('2016-05-08', 21, NULL, NULL, NULL, NULL),
('2016-05-08', 22, NULL, NULL, NULL, NULL),
('2016-05-08', 23, NULL, NULL, NULL, NULL),
('2016-05-09', 0, NULL, NULL, NULL, NULL),
('2016-05-09', 1, NULL, NULL, NULL, NULL),
('2016-05-09', 2, NULL, NULL, NULL, NULL),
('2016-05-09', 3, NULL, NULL, NULL, NULL),
('2016-05-09', 4, NULL, NULL, NULL, NULL),
('2016-05-09', 5, NULL, NULL, NULL, NULL),
('2016-05-09', 6, NULL, NULL, NULL, NULL),
('2016-05-09', 7, NULL, NULL, NULL, NULL),
('2016-05-09', 8, NULL, NULL, NULL, NULL),
('2016-05-09', 9, NULL, NULL, NULL, NULL),
('2016-05-09', 10, NULL, NULL, NULL, NULL),
('2016-05-09', 11, NULL, NULL, NULL, NULL),
('2016-05-09', 12, NULL, NULL, NULL, NULL),
('2016-05-09', 13, NULL, NULL, NULL, NULL),
('2016-05-09', 14, NULL, NULL, NULL, NULL),
('2016-05-09', 15, NULL, NULL, NULL, NULL),
('2016-05-09', 16, NULL, NULL, NULL, NULL),
('2016-05-09', 17, NULL, NULL, NULL, NULL),
('2016-05-09', 18, NULL, NULL, NULL, NULL),
('2016-05-09', 19, NULL, NULL, NULL, NULL),
('2016-05-09', 20, NULL, NULL, NULL, NULL),
('2016-05-09', 21, NULL, NULL, NULL, NULL),
('2016-05-09', 22, NULL, NULL, NULL, NULL),
('2016-05-09', 23, NULL, NULL, NULL, NULL),
('2016-05-10', 0, NULL, NULL, NULL, NULL),
('2016-05-10', 1, NULL, NULL, NULL, NULL),
('2016-05-10', 2, NULL, NULL, NULL, NULL),
('2016-05-10', 3, NULL, NULL, NULL, NULL),
('2016-05-10', 4, NULL, NULL, NULL, NULL),
('2016-05-10', 5, NULL, NULL, NULL, NULL),
('2016-05-10', 6, NULL, NULL, NULL, NULL),
('2016-05-10', 7, NULL, NULL, NULL, NULL),
('2016-05-10', 8, NULL, NULL, NULL, NULL),
('2016-05-10', 9, NULL, NULL, NULL, NULL),
('2016-05-10', 10, NULL, NULL, NULL, NULL),
('2016-05-10', 11, NULL, NULL, NULL, NULL),
('2016-05-10', 12, NULL, NULL, NULL, NULL),
('2016-05-10', 13, NULL, NULL, NULL, NULL),
('2016-05-10', 14, NULL, NULL, NULL, NULL),
('2016-05-10', 15, NULL, NULL, NULL, NULL),
('2016-05-10', 16, NULL, NULL, NULL, NULL),
('2016-05-10', 17, NULL, NULL, NULL, NULL),
('2016-05-10', 18, NULL, NULL, NULL, NULL),
('2016-05-10', 19, NULL, NULL, NULL, NULL),
('2016-05-10', 20, NULL, NULL, NULL, NULL),
('2016-05-10', 21, NULL, NULL, NULL, NULL),
('2016-05-10', 22, NULL, NULL, NULL, NULL),
('2016-05-10', 23, NULL, NULL, NULL, NULL),
('2016-05-11', 0, NULL, NULL, NULL, NULL),
('2016-05-11', 1, NULL, NULL, NULL, NULL),
('2016-05-11', 2, NULL, NULL, NULL, NULL),
('2016-05-11', 3, NULL, NULL, NULL, NULL),
('2016-05-11', 4, NULL, NULL, NULL, NULL),
('2016-05-11', 5, NULL, NULL, NULL, NULL),
('2016-05-11', 6, NULL, NULL, NULL, NULL),
('2016-05-11', 7, NULL, NULL, NULL, NULL),
('2016-05-11', 8, NULL, NULL, NULL, NULL),
('2016-05-11', 9, NULL, NULL, NULL, NULL),
('2016-05-11', 10, NULL, NULL, NULL, NULL),
('2016-05-11', 11, NULL, NULL, NULL, NULL),
('2016-05-11', 12, NULL, NULL, NULL, NULL),
('2016-05-11', 13, NULL, NULL, NULL, NULL),
('2016-05-11', 14, NULL, NULL, NULL, NULL),
('2016-05-11', 15, NULL, NULL, NULL, NULL),
('2016-05-11', 16, NULL, NULL, NULL, NULL),
('2016-05-11', 17, NULL, NULL, NULL, NULL),
('2016-05-11', 18, NULL, NULL, NULL, NULL),
('2016-05-11', 19, NULL, NULL, NULL, NULL),
('2016-05-11', 20, NULL, NULL, NULL, NULL),
('2016-05-11', 21, NULL, NULL, NULL, NULL),
('2016-05-11', 22, NULL, NULL, NULL, NULL),
('2016-05-11', 23, NULL, NULL, NULL, NULL),
('2016-05-12', 0, NULL, NULL, NULL, NULL),
('2016-05-12', 1, NULL, NULL, NULL, NULL),
('2016-05-12', 2, NULL, NULL, NULL, NULL),
('2016-05-12', 3, NULL, NULL, NULL, NULL),
('2016-05-12', 4, NULL, NULL, NULL, NULL),
('2016-05-12', 5, NULL, NULL, NULL, NULL),
('2016-05-12', 6, NULL, NULL, NULL, NULL),
('2016-05-12', 7, NULL, NULL, NULL, NULL),
('2016-05-12', 8, NULL, NULL, NULL, NULL),
('2016-05-12', 9, NULL, NULL, NULL, NULL),
('2016-05-12', 10, NULL, NULL, NULL, NULL),
('2016-05-12', 11, NULL, NULL, NULL, NULL),
('2016-05-12', 12, NULL, NULL, NULL, NULL),
('2016-05-12', 13, NULL, NULL, NULL, NULL),
('2016-05-12', 14, NULL, NULL, NULL, NULL),
('2016-05-12', 15, NULL, NULL, NULL, NULL),
('2016-05-12', 16, NULL, NULL, NULL, NULL),
('2016-05-12', 17, NULL, NULL, NULL, NULL),
('2016-05-12', 18, NULL, NULL, NULL, NULL),
('2016-05-12', 19, NULL, NULL, NULL, NULL),
('2016-05-12', 20, NULL, NULL, NULL, NULL),
('2016-05-12', 21, NULL, NULL, NULL, NULL),
('2016-05-12', 22, NULL, NULL, NULL, NULL),
('2016-05-12', 23, NULL, NULL, NULL, NULL),
('2016-05-13', 0, NULL, NULL, NULL, NULL),
('2016-05-13', 1, NULL, NULL, NULL, NULL),
('2016-05-13', 2, NULL, NULL, NULL, NULL),
('2016-05-13', 3, NULL, NULL, NULL, NULL),
('2016-05-13', 4, NULL, NULL, NULL, NULL),
('2016-05-13', 5, NULL, NULL, NULL, NULL),
('2016-05-13', 6, NULL, NULL, NULL, NULL),
('2016-05-13', 7, NULL, NULL, NULL, NULL),
('2016-05-13', 8, NULL, NULL, NULL, NULL),
('2016-05-13', 9, NULL, NULL, NULL, NULL),
('2016-05-13', 10, NULL, NULL, NULL, NULL),
('2016-05-13', 11, NULL, NULL, NULL, NULL),
('2016-05-13', 12, NULL, NULL, NULL, NULL),
('2016-05-13', 13, NULL, NULL, NULL, NULL),
('2016-05-13', 14, NULL, NULL, NULL, NULL),
('2016-05-13', 15, NULL, NULL, NULL, NULL),
('2016-05-13', 16, NULL, NULL, NULL, NULL),
('2016-05-13', 17, NULL, NULL, NULL, NULL),
('2016-05-13', 18, NULL, NULL, NULL, NULL),
('2016-05-13', 19, NULL, NULL, NULL, NULL),
('2016-05-13', 20, NULL, NULL, NULL, NULL),
('2016-05-13', 21, NULL, NULL, NULL, NULL),
('2016-05-13', 22, NULL, NULL, NULL, NULL),
('2016-05-13', 23, NULL, NULL, NULL, NULL),
('2016-05-14', 0, NULL, NULL, NULL, NULL),
('2016-05-14', 1, NULL, NULL, NULL, NULL),
('2016-05-14', 2, NULL, NULL, NULL, NULL),
('2016-05-14', 3, NULL, NULL, NULL, NULL),
('2016-05-14', 4, NULL, NULL, NULL, NULL),
('2016-05-14', 5, NULL, NULL, NULL, NULL),
('2016-05-14', 6, NULL, NULL, NULL, NULL),
('2016-05-14', 7, NULL, NULL, NULL, NULL),
('2016-05-14', 8, NULL, NULL, NULL, NULL),
('2016-05-14', 9, NULL, NULL, NULL, NULL),
('2016-05-14', 10, NULL, NULL, NULL, NULL),
('2016-05-14', 11, NULL, NULL, NULL, NULL),
('2016-05-14', 12, NULL, NULL, NULL, NULL),
('2016-05-14', 13, NULL, NULL, NULL, NULL),
('2016-05-14', 14, NULL, NULL, NULL, NULL),
('2016-05-14', 15, NULL, NULL, NULL, NULL),
('2016-05-14', 16, NULL, NULL, NULL, NULL),
('2016-05-14', 17, NULL, NULL, NULL, NULL),
('2016-05-14', 18, NULL, NULL, NULL, NULL),
('2016-05-14', 19, NULL, NULL, NULL, NULL),
('2016-05-14', 20, NULL, NULL, NULL, NULL),
('2016-05-14', 21, NULL, NULL, NULL, NULL),
('2016-05-14', 22, NULL, NULL, NULL, NULL),
('2016-05-14', 23, NULL, NULL, NULL, NULL),
('2016-05-15', 0, NULL, NULL, NULL, NULL),
('2016-05-15', 1, NULL, NULL, NULL, NULL),
('2016-05-15', 2, NULL, NULL, NULL, NULL),
('2016-05-15', 3, NULL, NULL, NULL, NULL),
('2016-05-15', 4, NULL, NULL, NULL, NULL),
('2016-05-15', 5, NULL, NULL, NULL, NULL),
('2016-05-15', 6, NULL, NULL, NULL, NULL),
('2016-05-15', 7, NULL, NULL, NULL, NULL),
('2016-05-15', 8, NULL, NULL, NULL, NULL),
('2016-05-15', 9, NULL, NULL, NULL, NULL),
('2016-05-15', 10, NULL, NULL, NULL, NULL),
('2016-05-15', 11, NULL, NULL, NULL, NULL),
('2016-05-15', 12, NULL, NULL, NULL, NULL),
('2016-05-15', 13, NULL, NULL, NULL, NULL),
('2016-05-15', 14, NULL, NULL, NULL, NULL),
('2016-05-15', 15, NULL, NULL, NULL, NULL),
('2016-05-15', 16, NULL, NULL, NULL, NULL),
('2016-05-15', 17, NULL, NULL, NULL, NULL),
('2016-05-15', 18, NULL, NULL, NULL, NULL),
('2016-05-15', 19, NULL, NULL, NULL, NULL),
('2016-05-15', 20, NULL, NULL, NULL, NULL),
('2016-05-15', 21, NULL, NULL, NULL, NULL),
('2016-05-15', 22, NULL, NULL, NULL, NULL),
('2016-05-15', 23, NULL, NULL, NULL, NULL),
('2016-05-16', 0, NULL, NULL, NULL, NULL),
('2016-05-16', 1, NULL, NULL, NULL, NULL),
('2016-05-16', 2, NULL, NULL, NULL, NULL),
('2016-05-16', 3, NULL, NULL, NULL, NULL),
('2016-05-16', 4, NULL, NULL, NULL, NULL),
('2016-05-16', 5, NULL, NULL, NULL, NULL),
('2016-05-16', 6, NULL, NULL, NULL, NULL),
('2016-05-16', 7, NULL, NULL, NULL, NULL),
('2016-05-16', 8, NULL, NULL, NULL, NULL),
('2016-05-16', 9, NULL, NULL, NULL, NULL),
('2016-05-16', 10, NULL, NULL, NULL, NULL),
('2016-05-16', 11, NULL, NULL, NULL, NULL),
('2016-05-16', 12, NULL, NULL, NULL, NULL),
('2016-05-16', 13, NULL, NULL, NULL, NULL),
('2016-05-16', 14, NULL, NULL, NULL, NULL),
('2016-05-16', 15, NULL, NULL, NULL, NULL),
('2016-05-16', 16, NULL, NULL, NULL, NULL),
('2016-05-16', 17, NULL, NULL, NULL, NULL),
('2016-05-16', 18, NULL, NULL, NULL, NULL),
('2016-05-16', 19, NULL, NULL, NULL, NULL),
('2016-05-16', 20, NULL, NULL, NULL, NULL),
('2016-05-16', 21, NULL, NULL, NULL, NULL),
('2016-05-16', 22, NULL, NULL, NULL, NULL),
('2016-05-16', 23, NULL, NULL, NULL, NULL),
('2016-05-17', 0, NULL, NULL, NULL, NULL),
('2016-05-17', 1, NULL, NULL, NULL, NULL),
('2016-05-17', 2, NULL, NULL, NULL, NULL),
('2016-05-17', 3, NULL, NULL, NULL, NULL),
('2016-05-17', 4, NULL, NULL, NULL, NULL),
('2016-05-17', 5, NULL, NULL, NULL, NULL),
('2016-05-17', 6, NULL, NULL, NULL, NULL),
('2016-05-17', 7, NULL, NULL, NULL, NULL),
('2016-05-17', 8, NULL, NULL, NULL, NULL),
('2016-05-17', 9, NULL, NULL, NULL, NULL),
('2016-05-17', 10, NULL, NULL, NULL, NULL),
('2016-05-17', 11, NULL, NULL, NULL, NULL),
('2016-05-17', 12, NULL, NULL, NULL, NULL),
('2016-05-17', 13, NULL, NULL, NULL, NULL),
('2016-05-17', 14, NULL, NULL, NULL, NULL),
('2016-05-17', 15, NULL, NULL, NULL, NULL),
('2016-05-17', 16, NULL, NULL, NULL, NULL),
('2016-05-17', 17, NULL, NULL, NULL, NULL),
('2016-05-17', 18, NULL, NULL, NULL, NULL),
('2016-05-17', 19, NULL, NULL, NULL, NULL),
('2016-05-17', 20, NULL, NULL, NULL, NULL),
('2016-05-17', 21, NULL, NULL, NULL, NULL),
('2016-05-17', 22, NULL, NULL, NULL, NULL),
('2016-05-17', 23, NULL, NULL, NULL, NULL),
('2016-05-18', 0, NULL, NULL, NULL, NULL),
('2016-05-18', 1, NULL, NULL, NULL, NULL),
('2016-05-18', 2, NULL, NULL, NULL, NULL),
('2016-05-18', 3, NULL, NULL, NULL, NULL),
('2016-05-18', 4, NULL, NULL, NULL, NULL),
('2016-05-18', 5, NULL, NULL, NULL, NULL),
('2016-05-18', 6, NULL, NULL, NULL, NULL),
('2016-05-18', 7, NULL, NULL, NULL, NULL),
('2016-05-18', 8, NULL, NULL, NULL, NULL),
('2016-05-18', 9, NULL, NULL, NULL, NULL),
('2016-05-18', 10, NULL, NULL, NULL, NULL),
('2016-05-18', 11, NULL, NULL, NULL, NULL),
('2016-05-18', 12, NULL, NULL, NULL, NULL),
('2016-05-18', 13, NULL, NULL, NULL, NULL),
('2016-05-18', 14, NULL, NULL, NULL, NULL),
('2016-05-18', 15, NULL, NULL, NULL, NULL),
('2016-05-18', 16, NULL, NULL, NULL, NULL),
('2016-05-18', 17, NULL, NULL, NULL, NULL),
('2016-05-18', 18, NULL, NULL, NULL, NULL),
('2016-05-18', 19, NULL, NULL, NULL, NULL),
('2016-05-18', 20, NULL, NULL, NULL, NULL),
('2016-05-18', 21, NULL, NULL, NULL, NULL),
('2016-05-18', 22, NULL, NULL, NULL, NULL),
('2016-05-18', 23, NULL, NULL, NULL, NULL),
('2016-05-19', 0, NULL, NULL, NULL, NULL),
('2016-05-19', 1, NULL, NULL, NULL, NULL),
('2016-05-19', 2, NULL, NULL, NULL, NULL),
('2016-05-19', 3, NULL, NULL, NULL, NULL),
('2016-05-19', 4, NULL, NULL, NULL, NULL),
('2016-05-19', 5, NULL, NULL, NULL, NULL),
('2016-05-19', 6, NULL, NULL, NULL, NULL),
('2016-05-19', 7, NULL, NULL, NULL, NULL),
('2016-05-19', 8, NULL, NULL, NULL, NULL),
('2016-05-19', 9, NULL, NULL, NULL, NULL),
('2016-05-19', 10, NULL, NULL, NULL, NULL),
('2016-05-19', 11, NULL, NULL, NULL, NULL),
('2016-05-19', 12, NULL, NULL, NULL, NULL),
('2016-05-19', 13, NULL, NULL, NULL, NULL),
('2016-05-19', 14, NULL, NULL, NULL, NULL),
('2016-05-19', 15, NULL, NULL, NULL, NULL),
('2016-05-19', 16, NULL, NULL, NULL, NULL),
('2016-05-19', 17, NULL, NULL, NULL, NULL),
('2016-05-19', 18, NULL, NULL, NULL, NULL),
('2016-05-19', 19, NULL, NULL, NULL, NULL),
('2016-05-19', 20, NULL, NULL, NULL, NULL),
('2016-05-19', 21, NULL, NULL, NULL, NULL),
('2016-05-19', 22, NULL, NULL, NULL, NULL),
('2016-05-19', 23, NULL, NULL, NULL, NULL),
('2016-05-20', 0, NULL, NULL, NULL, NULL),
('2016-05-20', 1, NULL, NULL, NULL, NULL),
('2016-05-20', 2, NULL, NULL, NULL, NULL),
('2016-05-20', 3, NULL, NULL, NULL, NULL),
('2016-05-20', 4, NULL, NULL, NULL, NULL),
('2016-05-20', 5, NULL, NULL, NULL, NULL),
('2016-05-20', 6, NULL, NULL, NULL, NULL),
('2016-05-20', 7, NULL, NULL, NULL, NULL),
('2016-05-20', 8, NULL, NULL, NULL, NULL),
('2016-05-20', 9, NULL, NULL, NULL, NULL),
('2016-05-20', 10, NULL, NULL, NULL, NULL),
('2016-05-20', 11, NULL, NULL, NULL, NULL),
('2016-05-20', 12, NULL, NULL, NULL, NULL),
('2016-05-20', 13, NULL, NULL, NULL, NULL),
('2016-05-20', 14, NULL, NULL, NULL, NULL),
('2016-05-20', 15, NULL, NULL, NULL, NULL),
('2016-05-20', 16, NULL, NULL, NULL, NULL),
('2016-05-20', 17, NULL, NULL, NULL, NULL),
('2016-05-20', 18, NULL, NULL, NULL, NULL),
('2016-05-20', 19, NULL, NULL, NULL, NULL),
('2016-05-20', 20, NULL, NULL, NULL, NULL),
('2016-05-20', 21, NULL, NULL, NULL, NULL),
('2016-05-20', 22, NULL, NULL, NULL, NULL),
('2016-05-20', 23, NULL, NULL, NULL, NULL),
('2016-05-21', 0, NULL, NULL, NULL, NULL),
('2016-05-21', 1, NULL, NULL, NULL, NULL),
('2016-05-21', 2, NULL, NULL, NULL, NULL),
('2016-05-21', 3, NULL, NULL, NULL, NULL),
('2016-05-21', 4, NULL, NULL, NULL, NULL),
('2016-05-21', 5, NULL, NULL, NULL, NULL),
('2016-05-21', 6, NULL, NULL, NULL, NULL),
('2016-05-21', 7, NULL, NULL, NULL, NULL),
('2016-05-21', 8, NULL, NULL, NULL, NULL),
('2016-05-21', 9, NULL, NULL, NULL, NULL),
('2016-05-21', 10, NULL, NULL, NULL, NULL),
('2016-05-21', 11, NULL, NULL, NULL, NULL),
('2016-05-21', 12, NULL, NULL, NULL, NULL),
('2016-05-21', 13, NULL, NULL, NULL, NULL),
('2016-05-21', 14, NULL, NULL, NULL, NULL),
('2016-05-21', 15, NULL, NULL, NULL, NULL),
('2016-05-21', 16, NULL, NULL, NULL, NULL),
('2016-05-21', 17, NULL, NULL, NULL, NULL),
('2016-05-21', 18, NULL, NULL, NULL, NULL),
('2016-05-21', 19, NULL, NULL, NULL, NULL),
('2016-05-21', 20, NULL, NULL, NULL, NULL),
('2016-05-21', 21, NULL, NULL, NULL, NULL),
('2016-05-21', 22, NULL, NULL, NULL, NULL),
('2016-05-21', 23, NULL, NULL, NULL, NULL),
('2016-05-22', 0, NULL, NULL, NULL, NULL),
('2016-05-22', 1, NULL, NULL, NULL, NULL),
('2016-05-22', 2, NULL, NULL, NULL, NULL),
('2016-05-22', 3, NULL, NULL, NULL, NULL),
('2016-05-22', 4, NULL, NULL, NULL, NULL),
('2016-05-22', 5, NULL, NULL, NULL, NULL),
('2016-05-22', 6, NULL, NULL, NULL, NULL),
('2016-05-22', 7, NULL, NULL, NULL, NULL),
('2016-05-22', 8, NULL, NULL, NULL, NULL),
('2016-05-22', 9, NULL, NULL, NULL, NULL),
('2016-05-22', 10, NULL, NULL, NULL, NULL),
('2016-05-22', 11, NULL, NULL, NULL, NULL),
('2016-05-22', 12, NULL, NULL, NULL, NULL),
('2016-05-22', 13, NULL, NULL, NULL, NULL),
('2016-05-22', 14, NULL, NULL, NULL, NULL),
('2016-05-22', 15, NULL, NULL, NULL, NULL),
('2016-05-22', 16, NULL, NULL, NULL, NULL),
('2016-05-22', 17, NULL, NULL, NULL, NULL),
('2016-05-22', 18, NULL, NULL, NULL, NULL),
('2016-05-22', 19, NULL, NULL, NULL, NULL),
('2016-05-22', 20, NULL, NULL, NULL, NULL),
('2016-05-22', 21, NULL, NULL, NULL, NULL),
('2016-05-22', 22, NULL, NULL, NULL, NULL),
('2016-05-22', 23, NULL, NULL, NULL, NULL),
('2016-05-23', 0, NULL, NULL, NULL, NULL),
('2016-05-23', 1, NULL, NULL, NULL, NULL),
('2016-05-23', 2, NULL, NULL, NULL, NULL),
('2016-05-23', 3, NULL, NULL, NULL, NULL),
('2016-05-23', 4, NULL, NULL, NULL, NULL),
('2016-05-23', 5, NULL, NULL, NULL, NULL),
('2016-05-23', 6, NULL, NULL, NULL, NULL),
('2016-05-23', 7, NULL, NULL, NULL, NULL),
('2016-05-23', 8, NULL, NULL, NULL, NULL),
('2016-05-23', 9, NULL, NULL, NULL, NULL),
('2016-05-23', 10, NULL, NULL, NULL, NULL),
('2016-05-23', 11, NULL, NULL, NULL, NULL),
('2016-05-23', 12, NULL, NULL, NULL, NULL),
('2016-05-23', 13, NULL, NULL, NULL, NULL),
('2016-05-23', 14, NULL, NULL, NULL, NULL),
('2016-05-23', 15, NULL, NULL, NULL, NULL),
('2016-05-23', 16, NULL, NULL, NULL, NULL),
('2016-05-23', 17, NULL, NULL, NULL, NULL),
('2016-05-23', 18, NULL, NULL, NULL, NULL),
('2016-05-23', 19, NULL, NULL, NULL, NULL),
('2016-05-23', 20, NULL, NULL, NULL, NULL),
('2016-05-23', 21, NULL, NULL, NULL, NULL),
('2016-05-23', 22, NULL, NULL, NULL, NULL),
('2016-05-23', 23, NULL, NULL, NULL, NULL),
('2016-05-24', 0, NULL, NULL, NULL, NULL),
('2016-05-24', 1, NULL, NULL, NULL, NULL),
('2016-05-24', 2, NULL, NULL, NULL, NULL),
('2016-05-24', 3, NULL, NULL, NULL, NULL),
('2016-05-24', 4, NULL, NULL, NULL, NULL),
('2016-05-24', 5, NULL, NULL, NULL, NULL),
('2016-05-24', 6, NULL, NULL, NULL, NULL),
('2016-05-24', 7, NULL, NULL, NULL, NULL),
('2016-05-24', 8, NULL, NULL, NULL, NULL),
('2016-05-24', 9, NULL, NULL, NULL, NULL),
('2016-05-24', 10, NULL, NULL, NULL, NULL),
('2016-05-24', 11, NULL, NULL, NULL, NULL),
('2016-05-24', 12, NULL, NULL, NULL, NULL),
('2016-05-24', 13, NULL, NULL, NULL, NULL),
('2016-05-24', 14, NULL, NULL, NULL, NULL),
('2016-05-24', 15, NULL, NULL, NULL, NULL),
('2016-05-24', 16, NULL, NULL, NULL, NULL),
('2016-05-24', 17, NULL, NULL, NULL, NULL),
('2016-05-24', 18, NULL, NULL, NULL, NULL),
('2016-05-24', 19, NULL, NULL, NULL, NULL),
('2016-05-24', 20, NULL, NULL, NULL, NULL),
('2016-05-24', 21, NULL, NULL, NULL, NULL),
('2016-05-24', 22, NULL, NULL, NULL, NULL),
('2016-05-24', 23, NULL, NULL, NULL, NULL),
('2016-05-25', 0, NULL, NULL, NULL, NULL),
('2016-05-25', 1, NULL, NULL, NULL, NULL),
('2016-05-25', 2, NULL, NULL, NULL, NULL),
('2016-05-25', 3, NULL, NULL, NULL, NULL),
('2016-05-25', 4, NULL, NULL, NULL, NULL),
('2016-05-25', 5, NULL, NULL, NULL, NULL),
('2016-05-25', 6, NULL, NULL, NULL, NULL),
('2016-05-25', 7, NULL, NULL, NULL, NULL),
('2016-05-25', 8, NULL, NULL, NULL, NULL),
('2016-05-25', 9, NULL, NULL, NULL, NULL),
('2016-05-25', 10, NULL, NULL, NULL, NULL),
('2016-05-25', 11, NULL, NULL, NULL, NULL),
('2016-05-25', 12, NULL, NULL, NULL, NULL),
('2016-05-25', 13, NULL, NULL, NULL, NULL),
('2016-05-25', 14, NULL, NULL, NULL, NULL),
('2016-05-25', 15, NULL, NULL, NULL, NULL),
('2016-05-25', 16, NULL, NULL, NULL, NULL),
('2016-05-25', 17, NULL, NULL, NULL, NULL),
('2016-05-25', 18, NULL, NULL, NULL, NULL),
('2016-05-25', 19, NULL, NULL, NULL, NULL),
('2016-05-25', 20, NULL, NULL, NULL, NULL),
('2016-05-25', 21, NULL, NULL, NULL, NULL),
('2016-05-25', 22, NULL, NULL, NULL, NULL),
('2016-05-25', 23, NULL, NULL, NULL, NULL),
('2016-05-26', 0, NULL, NULL, NULL, NULL),
('2016-05-26', 1, NULL, NULL, NULL, NULL),
('2016-05-26', 2, NULL, NULL, NULL, NULL),
('2016-05-26', 3, NULL, NULL, NULL, NULL),
('2016-05-26', 4, NULL, NULL, NULL, NULL),
('2016-05-26', 5, NULL, NULL, NULL, NULL),
('2016-05-26', 6, NULL, NULL, NULL, NULL),
('2016-05-26', 7, NULL, NULL, NULL, NULL),
('2016-05-26', 8, NULL, NULL, NULL, NULL),
('2016-05-26', 9, NULL, NULL, NULL, NULL),
('2016-05-26', 10, NULL, NULL, NULL, NULL),
('2016-05-26', 11, NULL, NULL, NULL, NULL),
('2016-05-26', 12, NULL, NULL, NULL, NULL),
('2016-05-26', 13, NULL, NULL, NULL, NULL),
('2016-05-26', 14, NULL, NULL, NULL, NULL),
('2016-05-26', 15, NULL, NULL, NULL, NULL),
('2016-05-26', 16, NULL, NULL, NULL, NULL),
('2016-05-26', 17, NULL, NULL, NULL, NULL),
('2016-05-26', 18, NULL, NULL, NULL, NULL),
('2016-05-26', 19, NULL, NULL, NULL, NULL),
('2016-05-26', 20, NULL, NULL, NULL, NULL),
('2016-05-26', 21, NULL, NULL, NULL, NULL),
('2016-05-26', 22, NULL, NULL, NULL, NULL),
('2016-05-26', 23, NULL, NULL, NULL, NULL),
('2016-05-27', 0, NULL, NULL, NULL, NULL),
('2016-05-27', 1, NULL, NULL, NULL, NULL),
('2016-05-27', 2, NULL, NULL, NULL, NULL),
('2016-05-27', 3, NULL, NULL, NULL, NULL),
('2016-05-27', 4, NULL, NULL, NULL, NULL),
('2016-05-27', 5, NULL, NULL, NULL, NULL),
('2016-05-27', 6, NULL, NULL, NULL, NULL),
('2016-05-27', 7, NULL, NULL, NULL, NULL),
('2016-05-27', 8, NULL, NULL, NULL, NULL),
('2016-05-27', 9, NULL, NULL, NULL, NULL),
('2016-05-27', 10, NULL, NULL, NULL, NULL),
('2016-05-27', 11, NULL, NULL, NULL, NULL),
('2016-05-27', 12, NULL, NULL, NULL, NULL),
('2016-05-27', 13, NULL, NULL, NULL, NULL),
('2016-05-27', 14, NULL, NULL, NULL, NULL),
('2016-05-27', 15, NULL, NULL, NULL, NULL),
('2016-05-27', 16, NULL, NULL, NULL, NULL),
('2016-05-27', 17, NULL, NULL, NULL, NULL),
('2016-05-27', 18, NULL, NULL, NULL, NULL),
('2016-05-27', 19, NULL, NULL, NULL, NULL),
('2016-05-27', 20, NULL, NULL, NULL, NULL),
('2016-05-27', 21, NULL, NULL, NULL, NULL),
('2016-05-27', 22, NULL, NULL, NULL, NULL),
('2016-05-27', 23, NULL, NULL, NULL, NULL),
('2016-05-28', 0, NULL, NULL, NULL, NULL),
('2016-05-28', 1, NULL, NULL, NULL, NULL),
('2016-05-28', 2, NULL, NULL, NULL, NULL),
('2016-05-28', 3, NULL, NULL, NULL, NULL),
('2016-05-28', 4, NULL, NULL, NULL, NULL),
('2016-05-28', 5, NULL, NULL, NULL, NULL),
('2016-05-28', 6, NULL, NULL, NULL, NULL),
('2016-05-28', 7, NULL, NULL, NULL, NULL),
('2016-05-28', 8, NULL, NULL, NULL, NULL),
('2016-05-28', 9, NULL, NULL, NULL, NULL),
('2016-05-28', 10, NULL, NULL, NULL, NULL),
('2016-05-28', 11, NULL, NULL, NULL, NULL),
('2016-05-28', 12, NULL, NULL, NULL, NULL),
('2016-05-28', 13, NULL, NULL, NULL, NULL),
('2016-05-28', 14, NULL, NULL, NULL, NULL),
('2016-05-28', 15, NULL, NULL, NULL, NULL),
('2016-05-28', 16, NULL, NULL, NULL, NULL),
('2016-05-28', 17, NULL, NULL, NULL, NULL),
('2016-05-28', 18, NULL, NULL, NULL, NULL),
('2016-05-28', 19, NULL, NULL, NULL, NULL),
('2016-05-28', 20, NULL, NULL, NULL, NULL),
('2016-05-28', 21, NULL, NULL, NULL, NULL),
('2016-05-28', 22, NULL, NULL, NULL, NULL),
('2016-05-28', 23, NULL, NULL, NULL, NULL),
('2016-05-29', 0, NULL, NULL, NULL, NULL),
('2016-05-29', 1, NULL, NULL, NULL, NULL),
('2016-05-29', 2, NULL, NULL, NULL, NULL),
('2016-05-29', 3, NULL, NULL, NULL, NULL),
('2016-05-29', 4, NULL, NULL, NULL, NULL),
('2016-05-29', 5, NULL, NULL, NULL, NULL),
('2016-05-29', 6, NULL, NULL, NULL, NULL),
('2016-05-29', 7, NULL, NULL, NULL, NULL),
('2016-05-29', 8, NULL, NULL, NULL, NULL),
('2016-05-29', 9, NULL, NULL, NULL, NULL),
('2016-05-29', 10, NULL, NULL, NULL, NULL),
('2016-05-29', 11, NULL, NULL, NULL, NULL),
('2016-05-29', 12, NULL, NULL, NULL, NULL),
('2016-05-29', 13, NULL, NULL, NULL, NULL),
('2016-05-29', 14, NULL, NULL, NULL, NULL),
('2016-05-29', 15, NULL, NULL, NULL, NULL),
('2016-05-29', 16, NULL, NULL, NULL, NULL),
('2016-05-29', 17, NULL, NULL, NULL, NULL),
('2016-05-29', 18, NULL, NULL, NULL, NULL),
('2016-05-29', 19, NULL, NULL, NULL, NULL),
('2016-05-29', 20, NULL, NULL, NULL, NULL),
('2016-05-29', 21, NULL, NULL, NULL, NULL),
('2016-05-29', 22, NULL, NULL, NULL, NULL),
('2016-05-29', 23, NULL, NULL, NULL, NULL);
INSERT INTO `slots` (`date`, `hour`, `eventID`, `bookingID`, `memberList`, `number`) VALUES
('2016-05-30', 0, NULL, NULL, NULL, NULL),
('2016-05-30', 1, NULL, NULL, NULL, NULL),
('2016-05-30', 2, NULL, NULL, NULL, NULL),
('2016-05-30', 3, NULL, NULL, NULL, NULL),
('2016-05-30', 4, NULL, NULL, NULL, NULL),
('2016-05-30', 5, NULL, NULL, NULL, NULL),
('2016-05-30', 6, NULL, NULL, NULL, NULL),
('2016-05-30', 7, NULL, NULL, NULL, NULL),
('2016-05-30', 8, NULL, NULL, NULL, NULL),
('2016-05-30', 9, NULL, NULL, NULL, NULL),
('2016-05-30', 10, NULL, NULL, NULL, NULL),
('2016-05-30', 11, NULL, NULL, NULL, NULL),
('2016-05-30', 12, NULL, NULL, NULL, NULL),
('2016-05-30', 13, NULL, NULL, NULL, NULL),
('2016-05-30', 14, NULL, NULL, NULL, NULL),
('2016-05-30', 15, NULL, NULL, NULL, NULL),
('2016-05-30', 16, NULL, NULL, NULL, NULL),
('2016-05-30', 17, NULL, NULL, NULL, NULL),
('2016-05-30', 18, NULL, NULL, NULL, NULL),
('2016-05-30', 19, NULL, NULL, NULL, NULL),
('2016-05-30', 20, NULL, NULL, NULL, NULL),
('2016-05-30', 21, NULL, NULL, NULL, NULL),
('2016-05-30', 22, NULL, NULL, NULL, NULL),
('2016-05-30', 23, NULL, NULL, NULL, NULL),
('2016-05-31', 0, NULL, NULL, NULL, NULL),
('2016-05-31', 1, NULL, NULL, NULL, NULL),
('2016-05-31', 2, NULL, NULL, NULL, NULL),
('2016-05-31', 3, NULL, NULL, NULL, NULL),
('2016-05-31', 4, NULL, NULL, NULL, NULL),
('2016-05-31', 5, NULL, NULL, NULL, NULL),
('2016-05-31', 6, NULL, NULL, NULL, NULL),
('2016-05-31', 7, NULL, NULL, NULL, NULL),
('2016-05-31', 8, NULL, NULL, NULL, NULL),
('2016-05-31', 9, NULL, NULL, NULL, NULL),
('2016-05-31', 10, NULL, NULL, NULL, NULL),
('2016-05-31', 11, NULL, NULL, NULL, NULL),
('2016-05-31', 12, NULL, NULL, NULL, NULL),
('2016-05-31', 13, NULL, NULL, NULL, NULL),
('2016-05-31', 14, NULL, NULL, NULL, NULL),
('2016-05-31', 15, NULL, NULL, NULL, NULL),
('2016-05-31', 16, NULL, NULL, NULL, NULL),
('2016-05-31', 17, NULL, NULL, NULL, NULL),
('2016-05-31', 18, NULL, NULL, NULL, NULL),
('2016-05-31', 19, NULL, NULL, NULL, NULL),
('2016-05-31', 20, NULL, NULL, NULL, NULL),
('2016-05-31', 21, NULL, NULL, NULL, NULL),
('2016-05-31', 22, NULL, NULL, NULL, NULL),
('2016-05-31', 23, NULL, NULL, NULL, NULL),
('2016-06-01', 0, NULL, NULL, NULL, NULL),
('2016-06-01', 1, NULL, NULL, NULL, NULL),
('2016-06-01', 2, NULL, NULL, NULL, NULL),
('2016-06-01', 3, NULL, NULL, NULL, NULL),
('2016-06-01', 4, NULL, NULL, NULL, NULL),
('2016-06-01', 5, NULL, NULL, NULL, NULL),
('2016-06-01', 6, NULL, NULL, NULL, NULL),
('2016-06-01', 7, NULL, NULL, NULL, NULL),
('2016-06-01', 8, NULL, NULL, NULL, NULL),
('2016-06-01', 9, NULL, NULL, NULL, NULL),
('2016-06-01', 10, NULL, NULL, NULL, NULL),
('2016-06-01', 11, NULL, NULL, NULL, NULL),
('2016-06-01', 12, NULL, NULL, NULL, NULL),
('2016-06-01', 13, NULL, NULL, NULL, NULL),
('2016-06-01', 14, NULL, NULL, NULL, NULL),
('2016-06-01', 15, NULL, NULL, NULL, NULL),
('2016-06-01', 16, NULL, NULL, NULL, NULL),
('2016-06-01', 17, NULL, NULL, NULL, NULL),
('2016-06-01', 18, NULL, NULL, NULL, NULL),
('2016-06-01', 19, NULL, NULL, NULL, NULL),
('2016-06-01', 20, NULL, NULL, NULL, NULL),
('2016-06-01', 21, NULL, NULL, NULL, NULL),
('2016-06-01', 22, NULL, NULL, NULL, NULL),
('2016-06-01', 23, NULL, NULL, NULL, NULL),
('2016-06-02', 0, NULL, NULL, NULL, NULL),
('2016-06-02', 1, NULL, NULL, NULL, NULL),
('2016-06-02', 2, NULL, NULL, NULL, NULL),
('2016-06-02', 3, NULL, NULL, NULL, NULL),
('2016-06-02', 4, NULL, NULL, NULL, NULL),
('2016-06-02', 5, NULL, NULL, NULL, NULL),
('2016-06-02', 6, NULL, NULL, NULL, NULL),
('2016-06-02', 7, NULL, NULL, NULL, NULL),
('2016-06-02', 8, NULL, NULL, NULL, NULL),
('2016-06-02', 9, NULL, NULL, NULL, NULL),
('2016-06-02', 10, NULL, NULL, NULL, NULL),
('2016-06-02', 11, NULL, NULL, NULL, NULL),
('2016-06-02', 12, NULL, NULL, NULL, NULL),
('2016-06-02', 13, NULL, NULL, NULL, NULL),
('2016-06-02', 14, NULL, NULL, NULL, NULL),
('2016-06-02', 15, NULL, NULL, NULL, NULL),
('2016-06-02', 16, NULL, NULL, NULL, NULL),
('2016-06-02', 17, NULL, NULL, NULL, NULL),
('2016-06-02', 18, NULL, NULL, NULL, NULL),
('2016-06-02', 19, NULL, NULL, NULL, NULL),
('2016-06-02', 20, NULL, NULL, NULL, NULL),
('2016-06-02', 21, NULL, NULL, NULL, NULL),
('2016-06-02', 22, NULL, NULL, NULL, NULL),
('2016-06-02', 23, NULL, NULL, NULL, NULL),
('2016-06-03', 0, NULL, NULL, NULL, NULL),
('2016-06-03', 1, NULL, NULL, NULL, NULL),
('2016-06-03', 2, NULL, NULL, NULL, NULL),
('2016-06-03', 3, NULL, NULL, NULL, NULL),
('2016-06-03', 4, NULL, NULL, NULL, NULL),
('2016-06-03', 5, NULL, NULL, NULL, NULL),
('2016-06-03', 6, NULL, NULL, NULL, NULL),
('2016-06-03', 7, NULL, NULL, NULL, NULL),
('2016-06-03', 8, NULL, NULL, NULL, NULL),
('2016-06-03', 9, NULL, NULL, NULL, NULL),
('2016-06-03', 10, NULL, NULL, NULL, NULL),
('2016-06-03', 11, NULL, NULL, NULL, NULL),
('2016-06-03', 12, NULL, NULL, NULL, NULL),
('2016-06-03', 13, NULL, NULL, NULL, NULL),
('2016-06-03', 14, NULL, NULL, NULL, NULL),
('2016-06-03', 15, NULL, NULL, NULL, NULL),
('2016-06-03', 16, NULL, NULL, NULL, NULL),
('2016-06-03', 17, NULL, NULL, NULL, NULL),
('2016-06-03', 18, NULL, NULL, NULL, NULL),
('2016-06-03', 19, NULL, NULL, NULL, NULL),
('2016-06-03', 20, NULL, NULL, NULL, NULL),
('2016-06-03', 21, NULL, NULL, NULL, NULL),
('2016-06-03', 22, NULL, NULL, NULL, NULL),
('2016-06-03', 23, NULL, NULL, NULL, NULL),
('2016-06-04', 0, NULL, NULL, NULL, NULL),
('2016-06-04', 1, NULL, NULL, NULL, NULL),
('2016-06-04', 2, NULL, NULL, NULL, NULL),
('2016-06-04', 3, NULL, NULL, NULL, NULL),
('2016-06-04', 4, NULL, NULL, NULL, NULL),
('2016-06-04', 5, NULL, NULL, NULL, NULL),
('2016-06-04', 6, NULL, NULL, NULL, NULL),
('2016-06-04', 7, NULL, NULL, NULL, NULL),
('2016-06-04', 8, NULL, NULL, NULL, NULL),
('2016-06-04', 9, NULL, NULL, NULL, NULL),
('2016-06-04', 10, NULL, NULL, NULL, NULL),
('2016-06-04', 11, NULL, NULL, NULL, NULL),
('2016-06-04', 12, NULL, NULL, NULL, NULL),
('2016-06-04', 13, NULL, NULL, NULL, NULL),
('2016-06-04', 14, NULL, NULL, NULL, NULL),
('2016-06-04', 15, NULL, NULL, NULL, NULL),
('2016-06-04', 16, NULL, NULL, NULL, NULL),
('2016-06-04', 17, NULL, NULL, NULL, NULL),
('2016-06-04', 18, NULL, NULL, NULL, NULL),
('2016-06-04', 19, NULL, NULL, NULL, NULL),
('2016-06-04', 20, NULL, NULL, NULL, NULL),
('2016-06-04', 21, NULL, NULL, NULL, NULL),
('2016-06-04', 22, NULL, NULL, NULL, NULL),
('2016-06-04', 23, NULL, NULL, NULL, NULL),
('2016-06-05', 0, NULL, NULL, NULL, NULL),
('2016-06-05', 1, NULL, NULL, NULL, NULL),
('2016-06-05', 2, NULL, NULL, NULL, NULL),
('2016-06-05', 3, NULL, NULL, NULL, NULL),
('2016-06-05', 4, NULL, NULL, NULL, NULL),
('2016-06-05', 5, NULL, NULL, NULL, NULL),
('2016-06-05', 6, NULL, NULL, NULL, NULL),
('2016-06-05', 7, NULL, NULL, NULL, NULL),
('2016-06-05', 8, NULL, NULL, NULL, NULL),
('2016-06-05', 9, NULL, NULL, NULL, NULL),
('2016-06-05', 10, NULL, NULL, NULL, NULL),
('2016-06-05', 11, NULL, NULL, NULL, NULL),
('2016-06-05', 12, NULL, NULL, NULL, NULL),
('2016-06-05', 13, NULL, NULL, NULL, NULL),
('2016-06-05', 14, NULL, NULL, NULL, NULL),
('2016-06-05', 15, NULL, NULL, NULL, NULL),
('2016-06-05', 16, NULL, NULL, NULL, NULL),
('2016-06-05', 17, NULL, NULL, NULL, NULL),
('2016-06-05', 18, NULL, NULL, NULL, NULL),
('2016-06-05', 19, NULL, NULL, NULL, NULL),
('2016-06-05', 20, NULL, NULL, NULL, NULL),
('2016-06-05', 21, NULL, NULL, NULL, NULL),
('2016-06-05', 22, NULL, NULL, NULL, NULL),
('2016-06-05', 23, NULL, NULL, NULL, NULL),
('2016-06-06', 0, NULL, NULL, NULL, NULL),
('2016-06-06', 1, NULL, NULL, NULL, NULL),
('2016-06-06', 2, NULL, NULL, NULL, NULL),
('2016-06-06', 3, NULL, NULL, NULL, NULL),
('2016-06-06', 4, NULL, NULL, NULL, NULL),
('2016-06-06', 5, NULL, NULL, NULL, NULL),
('2016-06-06', 6, NULL, NULL, NULL, NULL),
('2016-06-06', 7, NULL, NULL, NULL, NULL),
('2016-06-06', 8, NULL, NULL, NULL, NULL),
('2016-06-06', 9, NULL, NULL, NULL, NULL),
('2016-06-06', 10, NULL, NULL, NULL, NULL),
('2016-06-06', 11, NULL, NULL, NULL, NULL),
('2016-06-06', 12, NULL, NULL, NULL, NULL),
('2016-06-06', 13, NULL, NULL, NULL, NULL),
('2016-06-06', 14, NULL, NULL, NULL, NULL),
('2016-06-06', 15, NULL, NULL, NULL, NULL),
('2016-06-06', 16, NULL, NULL, NULL, NULL),
('2016-06-06', 17, NULL, NULL, NULL, NULL),
('2016-06-06', 18, NULL, NULL, NULL, NULL),
('2016-06-06', 19, NULL, NULL, NULL, NULL),
('2016-06-06', 20, NULL, NULL, NULL, NULL),
('2016-06-06', 21, NULL, NULL, NULL, NULL),
('2016-06-06', 22, NULL, NULL, NULL, NULL),
('2016-06-06', 23, NULL, NULL, NULL, NULL),
('2016-06-07', 0, NULL, NULL, NULL, NULL),
('2016-06-07', 1, NULL, NULL, NULL, NULL),
('2016-06-07', 2, NULL, NULL, NULL, NULL),
('2016-06-07', 3, NULL, NULL, NULL, NULL),
('2016-06-07', 4, NULL, NULL, NULL, NULL),
('2016-06-07', 5, NULL, NULL, NULL, NULL),
('2016-06-07', 6, NULL, NULL, NULL, NULL),
('2016-06-07', 7, NULL, NULL, NULL, NULL),
('2016-06-07', 8, NULL, NULL, NULL, NULL),
('2016-06-07', 9, NULL, NULL, NULL, NULL),
('2016-06-07', 10, NULL, NULL, NULL, NULL),
('2016-06-07', 11, NULL, NULL, NULL, NULL),
('2016-06-07', 12, NULL, NULL, NULL, NULL),
('2016-06-07', 13, NULL, NULL, NULL, NULL),
('2016-06-07', 14, NULL, NULL, NULL, NULL),
('2016-06-07', 15, NULL, NULL, NULL, NULL),
('2016-06-07', 16, NULL, NULL, NULL, NULL),
('2016-06-07', 17, NULL, NULL, NULL, NULL),
('2016-06-07', 18, NULL, NULL, NULL, NULL),
('2016-06-07', 19, NULL, NULL, NULL, NULL),
('2016-06-07', 20, NULL, NULL, NULL, NULL),
('2016-06-07', 21, NULL, NULL, NULL, NULL),
('2016-06-07', 22, NULL, NULL, NULL, NULL),
('2016-06-07', 23, NULL, NULL, NULL, NULL),
('2016-06-08', 0, NULL, NULL, NULL, NULL),
('2016-06-08', 1, NULL, NULL, NULL, NULL),
('2016-06-08', 2, NULL, NULL, NULL, NULL),
('2016-06-08', 3, NULL, NULL, NULL, NULL),
('2016-06-08', 4, NULL, NULL, NULL, NULL),
('2016-06-08', 5, NULL, NULL, NULL, NULL),
('2016-06-08', 6, NULL, NULL, NULL, NULL),
('2016-06-08', 7, NULL, NULL, NULL, NULL),
('2016-06-08', 8, NULL, NULL, NULL, NULL),
('2016-06-08', 9, NULL, NULL, NULL, NULL),
('2016-06-08', 10, NULL, NULL, NULL, NULL),
('2016-06-08', 11, NULL, NULL, NULL, NULL),
('2016-06-08', 12, NULL, NULL, NULL, NULL),
('2016-06-08', 13, NULL, NULL, NULL, NULL),
('2016-06-08', 14, NULL, NULL, NULL, NULL),
('2016-06-08', 15, NULL, NULL, NULL, NULL),
('2016-06-08', 16, NULL, NULL, NULL, NULL),
('2016-06-08', 17, NULL, NULL, NULL, NULL),
('2016-06-08', 18, NULL, NULL, NULL, NULL),
('2016-06-08', 19, NULL, NULL, NULL, NULL),
('2016-06-08', 20, NULL, NULL, NULL, NULL),
('2016-06-08', 21, NULL, NULL, NULL, NULL),
('2016-06-08', 22, NULL, NULL, NULL, NULL),
('2016-06-08', 23, NULL, NULL, NULL, NULL),
('2016-06-09', 0, NULL, NULL, NULL, NULL),
('2016-06-09', 1, NULL, NULL, NULL, NULL),
('2016-06-09', 2, NULL, NULL, NULL, NULL),
('2016-06-09', 3, NULL, NULL, NULL, NULL),
('2016-06-09', 4, NULL, NULL, NULL, NULL),
('2016-06-09', 5, NULL, NULL, NULL, NULL),
('2016-06-09', 6, NULL, NULL, NULL, NULL),
('2016-06-09', 7, NULL, NULL, NULL, NULL),
('2016-06-09', 8, NULL, NULL, NULL, NULL),
('2016-06-09', 9, NULL, NULL, NULL, NULL),
('2016-06-09', 10, NULL, NULL, NULL, NULL),
('2016-06-09', 11, NULL, NULL, NULL, NULL),
('2016-06-09', 12, NULL, NULL, NULL, NULL),
('2016-06-09', 13, NULL, NULL, NULL, NULL),
('2016-06-09', 14, NULL, NULL, NULL, NULL),
('2016-06-09', 15, NULL, NULL, NULL, NULL),
('2016-06-09', 16, NULL, NULL, NULL, NULL),
('2016-06-09', 17, NULL, NULL, NULL, NULL),
('2016-06-09', 18, NULL, NULL, NULL, NULL),
('2016-06-09', 19, NULL, NULL, NULL, NULL),
('2016-06-09', 20, NULL, NULL, NULL, NULL),
('2016-06-09', 21, NULL, NULL, NULL, NULL),
('2016-06-09', 22, NULL, NULL, NULL, NULL),
('2016-06-09', 23, NULL, NULL, NULL, NULL),
('2016-06-10', 0, NULL, NULL, NULL, NULL),
('2016-06-10', 1, NULL, NULL, NULL, NULL),
('2016-06-10', 2, NULL, NULL, NULL, NULL),
('2016-06-10', 3, NULL, NULL, NULL, NULL),
('2016-06-10', 4, NULL, NULL, NULL, NULL),
('2016-06-10', 5, NULL, NULL, NULL, NULL),
('2016-06-10', 6, NULL, NULL, NULL, NULL),
('2016-06-10', 7, NULL, NULL, NULL, NULL),
('2016-06-10', 8, NULL, NULL, NULL, NULL),
('2016-06-10', 9, NULL, NULL, NULL, NULL),
('2016-06-10', 10, NULL, NULL, NULL, NULL),
('2016-06-10', 11, NULL, NULL, NULL, NULL),
('2016-06-10', 12, NULL, NULL, NULL, NULL),
('2016-06-10', 13, NULL, NULL, NULL, NULL),
('2016-06-10', 14, NULL, NULL, NULL, NULL),
('2016-06-10', 15, NULL, NULL, NULL, NULL),
('2016-06-10', 16, NULL, NULL, NULL, NULL),
('2016-06-10', 17, NULL, NULL, NULL, NULL),
('2016-06-10', 18, NULL, NULL, NULL, NULL),
('2016-06-10', 19, NULL, NULL, NULL, NULL),
('2016-06-10', 20, NULL, NULL, NULL, NULL),
('2016-06-10', 21, NULL, NULL, NULL, NULL),
('2016-06-10', 22, NULL, NULL, NULL, NULL),
('2016-06-10', 23, NULL, NULL, NULL, NULL),
('2016-06-11', 0, NULL, NULL, NULL, NULL),
('2016-06-11', 1, NULL, NULL, NULL, NULL),
('2016-06-11', 2, NULL, NULL, NULL, NULL),
('2016-06-11', 3, NULL, NULL, NULL, NULL),
('2016-06-11', 4, NULL, NULL, NULL, NULL),
('2016-06-11', 5, NULL, NULL, NULL, NULL),
('2016-06-11', 6, NULL, NULL, NULL, NULL),
('2016-06-11', 7, NULL, NULL, NULL, NULL),
('2016-06-11', 8, NULL, NULL, NULL, NULL),
('2016-06-11', 9, NULL, NULL, NULL, NULL),
('2016-06-11', 10, NULL, NULL, NULL, NULL),
('2016-06-11', 11, NULL, NULL, NULL, NULL),
('2016-06-11', 12, NULL, NULL, NULL, NULL),
('2016-06-11', 13, NULL, NULL, NULL, NULL),
('2016-06-11', 14, NULL, NULL, NULL, NULL),
('2016-06-11', 15, NULL, NULL, NULL, NULL),
('2016-06-11', 16, NULL, NULL, NULL, NULL),
('2016-06-11', 17, NULL, NULL, NULL, NULL),
('2016-06-11', 18, NULL, NULL, NULL, NULL),
('2016-06-11', 19, NULL, NULL, NULL, NULL),
('2016-06-11', 20, NULL, NULL, NULL, NULL),
('2016-06-11', 21, NULL, NULL, NULL, NULL),
('2016-06-11', 22, NULL, NULL, NULL, NULL),
('2016-06-11', 23, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `spmsvalues`
--

CREATE TABLE IF NOT EXISTS `spmsvalues` (
  `id` int(11) NOT NULL,
  `start` int(11) NOT NULL,
  `end` int(11) NOT NULL,
  `memberFee` int(11) NOT NULL,
  `maxBookings` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `spmsvalues`
--

INSERT INTO `spmsvalues` (`id`, `start`, `end`, `memberFee`, `maxBookings`) VALUES
(1, 8, 18, 1500, 24);

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE IF NOT EXISTS `students` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `emailID` varchar(30) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `courseID` varchar(10) NOT NULL,
  `notifics` text
) ENGINE=InnoDB AUTO_INCREMENT=90006 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`id`, `name`, `emailID`, `password`, `courseID`, `notifics`) VALUES
(90004, 'Kumar', 'kumardadi@hotmail.com', 'dnttht@9', 'SS_2016', '31'),
(90005, 'Dadi', 'kumardadi100@gmail.com', 'dnttht@9', 'SB_4_2016', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `vapplics`
--

CREATE TABLE IF NOT EXISTS `vapplics` (
  `id` int(6) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `emailID` varchar(30) DEFAULT NULL,
  `address` text,
  `phoneNo` varchar(15) DEFAULT NULL,
  `ticketReceipt` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `viewers`
--

CREATE TABLE IF NOT EXISTS `viewers` (
  `id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `emailID` varchar(30) DEFAULT NULL,
  `eventID` varchar(10) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `viewers`
--

INSERT INTO `viewers` (`id`, `name`, `emailID`, `eventID`, `date`) VALUES
(1, 'MKDD', 'kumardadi100@gmail.com', 'SM_2016', '2016-04-23');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `capplics`
--
ALTER TABLE `capplics`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cmembers`
--
ALTER TABLE `cmembers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `discussions`
--
ALTER TABLE `discussions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`timestamp`);

--
-- Indexes for table `form`
--
ALTER TABLE `form`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mapplics`
--
ALTER TABLE `mapplics`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notifs`
--
ALTER TABLE `notifs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `papplics`
--
ALTER TABLE `papplics`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `participants`
--
ALTER TABLE `participants`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `slots`
--
ALTER TABLE `slots`
  ADD UNIQUE KEY `dup_check` (`date`,`hour`);

--
-- Indexes for table `spmsvalues`
--
ALTER TABLE `spmsvalues`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vapplics`
--
ALTER TABLE `vapplics`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `viewers`
--
ALTER TABLE `viewers`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `capplics`
--
ALTER TABLE `capplics`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `cmembers`
--
ALTER TABLE `cmembers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=104;
--
-- AUTO_INCREMENT for table `discussions`
--
ALTER TABLE `discussions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=90006;
--
-- AUTO_INCREMENT for table `mapplics`
--
ALTER TABLE `mapplics`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1005;
--
-- AUTO_INCREMENT for table `notifs`
--
ALTER TABLE `notifs`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT for table `papplics`
--
ALTER TABLE `papplics`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `participants`
--
ALTER TABLE `participants`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=90006;
--
-- AUTO_INCREMENT for table `vapplics`
--
ALTER TABLE `vapplics`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `viewers`
--
ALTER TABLE `viewers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
DELIMITER $$
--
-- Events
--
CREATE DEFINER=`mkdadi`@`localhost` EVENT `delPosts` ON SCHEDULE EVERY 1 DAY STARTS '2016-04-03 00:00:00' ON COMPLETION NOT PRESERVE ENABLE COMMENT 'Deletes older Posts' DO DELETE FROM posts WHERE timestamp < (NOW() - INTERVAL 3 DAY)$$

CREATE DEFINER=`root`@`localhost` EVENT `slotADDER` ON SCHEDULE EVERY 7 DAY STARTS '2016-04-03 00:00:01' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
	DECLARE iterDate DATE DEFAULT now();
    DECLARE i INT DEFAULT 0;
    label1: LOOP
        SET i=0;
        WHILE i < 24 DO
        	insert IGNORE into slots(date,hour) values (iterDate,i);
        	SET i=i+1;
        END WHILE;
        SET iterDate = DATE_ADD(iterDate, INTERVAL 1 DAY);
        IF iterDate < now() + INTERVAL 62 day THEN
            ITERATE label1;
        END IF;
        LEAVE label1;
    END LOOP label1;
END$$

CREATE DEFINER=`root`@`localhost` EVENT `slotDel` ON SCHEDULE EVERY 7 DAY STARTS '2016-04-03 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM slots WHERE date< DATE(NOW())$$

CREATE DEFINER=`mkdadi`@`localhost` EVENT `noticeDel` ON SCHEDULE EVERY 1 DAY STARTS '2016-04-05 00:00:01' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM notices WHERE date < NOW()$$

CREATE DEFINER=`mkdadi`@`localhost` EVENT `delEvent` ON SCHEDULE EVERY 1 HOUR STARTS '2016-04-06 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM `events` WHERE `timestamp` + INTERVAL `duration` DAY_HOUR < now()$$

CREATE DEFINER=`mkdadi`@`localhost` EVENT `delCourse` ON SCHEDULE EVERY 1 HOUR STARTS '2016-04-06 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM `courses` WHERE `start` < (NOW() - INTERVAL `duration` MONTH )$$

DELIMITER ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
