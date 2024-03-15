-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 15, 2024 at 12:02 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `scams_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `club`
--

CREATE TABLE `club` (
  `ClubID` char(4) NOT NULL,
  `Club_name` varchar(100) DEFAULT NULL,
  `Club_type` varchar(50) DEFAULT NULL,
  `Started_date` date DEFAULT NULL,
  `Club_description` varchar(300) DEFAULT NULL,
  `Club_logo_path` varchar(255) DEFAULT NULL,
  `AdvisorID` char(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `club`
--

INSERT INTO `club` (`ClubID`, `Club_name`, `Club_type`, `Started_date`, `Club_description`, `Club_logo_path`, `AdvisorID`) VALUES
('C001', 'Debating', 'Academic', '2022-11-10', 'Improving public speaking and critical thinking.', 'debateClub.jpg', 'A001'),
('C002', 'Football', 'Sport', '2022-12-15', 'Passionate about soccer? Join us!', 'Footballclub.jpg', 'A002'),
('C003', 'Art Club', 'Extra-Curricular', '2023-01-20', 'Express your creativity through art.', 'Artclub.jpg', 'A003'),
('C004', 'Science Club', 'Academic', '2023-02-05', 'Exploring the wonders of science.', 'Sciencelub.jpg', 'A004'),
('C005', 'Coding Club', 'Academic', '2023-03-08', 'Learn and master coding skills.', 'Coding.jpg', 'A005'),
('C006', 'Basketball', 'Sport', '2023-04-12', 'Dribble, shoot, score! Join our team.', 'basketball.jpg', 'A006'),
('C007', 'Drama Club', 'Extra-Curricular', '2023-05-18', 'Act, direct, and enjoy the stage.', 'dramaclub.jpg', 'A007'),
('C008', 'Music Club', 'Extra-Curricular', '2023-06-22', 'Discover and create musical magic.', 'Musicclub.jpg', 'A008'),
('C009', 'Photography Club', 'Extra-Curricular', '2023-07-30', 'Capture moments through a lens.', 'Photographyclub.jpg', 'A009'),
('C010', 'Math Club', 'Academic', '2023-08-31', 'Solving mathematical puzzles and challenges.', 'Mathclub.jpg', 'A010'),
('C011', 'Scouting', 'Extra-Curricular', '2023-11-02', 'Be Prepared', 'ScoutLogo.png', 'A011');

-- --------------------------------------------------------

--
-- Table structure for table `club_advisor`
--

CREATE TABLE `club_advisor` (
  `AdvisorID` char(4) NOT NULL,
  `First_name` varchar(100) DEFAULT NULL,
  `Last_name` varchar(100) DEFAULT NULL,
  `Gender` varchar(10) DEFAULT NULL,
  `Email` varchar(150) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `Username` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `club_advisor`
--

INSERT INTO `club_advisor` (`AdvisorID`, `First_name`, `Last_name`, `Gender`, `Email`, `DOB`, `Username`, `Password`) VALUES
('A001', 'Saman', 'Perera', 'Male', 'saman.perera@example.com', '2000-05-12', 'saman01', 'password01'),
('A002', 'Nadeesha', 'Fernando', 'Female', 'nadeesha.fernando@example.com', '2002-08-22', 'nadeesha02', 'password02'),
('A003', 'Rajitha', 'Silva', 'Male', 'rajitha.silva@example.com', '2001-03-15', 'rajitha03', 'password03'),
('A004', 'Dilini', 'Ratnayake', 'Female', 'dilini.ratnayake@example.com', '2003-11-10', 'dilini04', 'password04'),
('A005', 'Chathura', 'Fernando', 'Male', 'chathura.fernando@example.com', '2000-06-08', 'chathura05', 'password05'),
('A006', 'Nishadi', 'Gunaratne', 'Female', 'nishadi.gunaratne@example.com', '2002-02-20', 'nishadi06', 'password06'),
('A007', 'Lakmal', 'Wickramasinghe', 'Male', 'lakmal.wickrama@example.com', '2001-07-04', 'lakmal07', 'password07'),
('A008', 'Kavindu', 'Rajapakse', 'Male', 'kavindu.rajapakse@example.com', '2003-09-28', 'kavindu08', 'password08'),
('A009', 'Ishara', 'Fernando', 'Female', 'ishara.fernando@example.com', '2000-12-19', 'ishara09', 'password09'),
('A010', 'Dinesh', 'Perera', 'Male', 'dinesh.perera@example.com', '2001-05-30', 'dinesh10', 'password10'),
('A011', 'Lahiru', 'Theekshana', 'Male', 'lahiru.thee@huu.com', '2023-04-12', 'admin', 'admin'),
('A852', 'dvv', 'dvwsv', 'Male', 'dcsdc@gmail.com', '2023-11-15', 'dcwv', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `EventID` char(4) NOT NULL,
  `Event_name` varchar(150) DEFAULT NULL,
  `Event_date` date DEFAULT NULL,
  `Event_time` time DEFAULT NULL,
  `Event_description` varchar(300) DEFAULT NULL,
  `ClubID` char(4) DEFAULT NULL,
  `AdvisorID` char(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`EventID`, `Event_name`, `Event_date`, `Event_time`, `Event_description`, `ClubID`, `AdvisorID`) VALUES
('E001', 'Leadership Workshop', '2023-01-15', '14:00:00', 'Developing leadership skills for students.', 'C001', 'A001'),
('E002', 'Rugby Tournament', '2023-02-20', '09:00:00', 'Inter-school rugby competition.', 'C002', 'A002'),
('E003', 'Chess Championship', '2023-03-25', '10:30:00', 'Annual chess championship event.', 'C003', 'A003'),
('E004', 'Community Service', '2023-04-18', '08:00:00', 'Cleaning and beautifying the local area.', 'C004', 'A004'),
('E005', 'Rugby Training', '2023-05-10', '15:30:00', 'Intensive rugby training session.', 'C005', 'A005'),
('E006', 'Chess Workshop', '2023-06-12', '11:00:00', 'Learning advanced chess strategies.', 'C006', 'A006'),
('E007', 'Scout Camp', '2023-07-17', '11:00:00', 'Full-day camping and team-building.', 'C007', 'A007'),
('E008', 'Rugby Match', '2023-08-22', '16:00:00', 'Exciting inter-school rugby match.', 'C008', 'A008'),
('E009', 'Chess Tournament', '2023-09-28', '12:45:00', 'School-level chess tournament.', 'C009', 'A009'),
('E010', 'Scout Adventure', '2023-10-31', '11:00:00', 'Exploring nature and survival skills.', 'C010', 'A010');

-- --------------------------------------------------------

--
-- Table structure for table `event_registration`
--

CREATE TABLE `event_registration` (
  `StudentID` char(4) NOT NULL,
  `EventID` char(4) NOT NULL,
  `Attendance` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `event_registration`
--

INSERT INTO `event_registration` (`StudentID`, `EventID`, `Attendance`) VALUES
('S001', 'E001', NULL),
('S001', 'E002', NULL),
('S002', 'E002', NULL),
('S003', 'E002', NULL),
('S003', 'E003', NULL),
('S004', 'E002', NULL),
('S004', 'E004', NULL),
('S005', 'E001', NULL),
('S005', 'E005', NULL),
('S006', 'E001', NULL),
('S006', 'E006', NULL),
('S007', 'E001', NULL),
('S007', 'E003', NULL),
('S007', 'E007', NULL),
('S008', 'E003', NULL),
('S008', 'E008', NULL),
('S009', 'E003', NULL),
('S009', 'E009', NULL),
('S010', 'E010', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `StudentID` char(4) NOT NULL,
  `First_name` varchar(100) DEFAULT NULL,
  `Last_name` varchar(100) DEFAULT NULL,
  `Gender` varchar(10) DEFAULT NULL,
  `Email` varchar(150) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `User_name` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`StudentID`, `First_name`, `Last_name`, `Gender`, `Email`, `DOB`, `User_name`, `Password`) VALUES
('S001', 'Nimal', 'Siriwardhana', 'Male', 'nimal.siri@example.com', '2000-02-03', 'nimal01', 'password01'),
('S002', 'Kasuni', 'Perera', 'Female', 'kasuni.perera@example.com', '2002-07-15', 'kasuni02', 'password02'),
('S003', 'Ruwan', 'Fernando', 'Male', 'ruwan.fernando@example.com', '2001-01-25', 'ruwan03', 'password03'),
('S004', 'Dulani', 'Rajapakse', 'Female', 'dulani.rajapakse@example.com', '2003-10-08', 'dulani04', 'password04'),
('S005', 'Chamara', 'Wickramasinghe', 'Male', 'chamara.wickrama@example.com', '2000-04-18', 'chamara05', 'password05'),
('S006', 'Malith', 'Gunaratne', 'Male', 'malith.gunaratne@example.com', '2002-12-30', 'malith06', 'password06'),
('S007', 'Lakshika', 'Fernando', 'Female', 'lakshika.fernando@example.com', '2001-06-14', 'lakshika07', 'password07'),
('S008', 'Kamal', 'Silva', 'Male', 'kamal.silva@example.com', '2003-08-28', 'kamal08', 'password08'),
('S009', 'Udara', 'Ratnayake', 'Male', 'udara.ratnayake@example.com', '2000-11-09', 'udara09', 'password09'),
('S010', 'Tharushi', 'Perera', 'Female', 'tharushi.perera@example.com', '2001-04-20', 'tharushi10', 'password10'),
('S011', 'Bandi', 'Theekshana', 'Male', 'chulanshammi@gmail.com', '2023-11-17', 'user', 'user');

-- --------------------------------------------------------

--
-- Table structure for table `student_club`
--

CREATE TABLE `student_club` (
  `StudentID` char(4) NOT NULL,
  `ClubID` char(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student_club`
--

INSERT INTO `student_club` (`StudentID`, `ClubID`) VALUES
('S001', 'C001'),
('S002', 'C002'),
('S003', 'C003'),
('S004', 'C004'),
('S005', 'C005'),
('S006', 'C006'),
('S007', 'C007'),
('S008', 'C008'),
('S009', 'C009'),
('S010', 'C010'),
('S011', 'C001'),
('S011', 'C002'),
('S011', 'C006'),
('S011', 'C008'),
('S011', 'C009');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `club`
--
ALTER TABLE `club`
  ADD PRIMARY KEY (`ClubID`),
  ADD KEY `AdvisorID` (`AdvisorID`);

--
-- Indexes for table `club_advisor`
--
ALTER TABLE `club_advisor`
  ADD PRIMARY KEY (`AdvisorID`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`EventID`),
  ADD KEY `ClubID` (`ClubID`),
  ADD KEY `AdvisorID` (`AdvisorID`);

--
-- Indexes for table `event_registration`
--
ALTER TABLE `event_registration`
  ADD PRIMARY KEY (`StudentID`,`EventID`),
  ADD KEY `EventID` (`EventID`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`StudentID`);

--
-- Indexes for table `student_club`
--
ALTER TABLE `student_club`
  ADD PRIMARY KEY (`StudentID`,`ClubID`),
  ADD KEY `ClubID` (`ClubID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `club`
--
ALTER TABLE `club`
  ADD CONSTRAINT `club_ibfk_1` FOREIGN KEY (`AdvisorID`) REFERENCES `club_advisor` (`AdvisorID`);

--
-- Constraints for table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `event_ibfk_1` FOREIGN KEY (`ClubID`) REFERENCES `club` (`ClubID`),
  ADD CONSTRAINT `event_ibfk_2` FOREIGN KEY (`AdvisorID`) REFERENCES `club_advisor` (`AdvisorID`);

--
-- Constraints for table `event_registration`
--
ALTER TABLE `event_registration`
  ADD CONSTRAINT `event_registration_ibfk_1` FOREIGN KEY (`StudentID`) REFERENCES `student` (`StudentID`),
  ADD CONSTRAINT `event_registration_ibfk_2` FOREIGN KEY (`EventID`) REFERENCES `event` (`EventID`);

--
-- Constraints for table `student_club`
--
ALTER TABLE `student_club`
  ADD CONSTRAINT `student_club_ibfk_1` FOREIGN KEY (`StudentID`) REFERENCES `student` (`StudentID`),
  ADD CONSTRAINT `student_club_ibfk_2` FOREIGN KEY (`ClubID`) REFERENCES `club` (`ClubID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
