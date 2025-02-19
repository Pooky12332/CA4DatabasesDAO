-- Creating the database --
CREATE OR REPLACE DATABASE oopca4db;
USE `oopca4db`;

-- Creating the tables --
-- The ID has to be a primary key for it to auto increment
CREATE TABLE expense (
	`expenseID` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`title` tinytext NOT NULL,
	`category` tinytext NOT NULL,
	`amount` double NOT NULL,
	`dateIncurred` date
);

CREATE TABLE income (
	`incomeID` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`title` tinytext NOT NULL,
	`amount` double NOT NULL,
	`dateEarned` date
);

-- Placing in dummy values --
INSERT INTO `expense` (`expenseID`, `title`, `category`, `amount`, `dateIncurred`) VALUES
(1, "Groceries", "Shopping", 47.50, "2025-02-13"),
(2, "Spotify", "Subscription", 19.99, "2025-02-14"),
(3, "Netflix", "Subscription", 7.99, "2025-02-14"),
(4, "Dinner", "Disposable", 25.79, "2025-02-15");

INSERT INTO `income` (`incomeID`, `title`, `amount`, `dateEarned`) VALUES
(1, "Babysitting", 60.0, "2025-02-13"),
(2, "Work", 150.0, "2025-02-14"),
(3, "Gift", 50.0, "2025-02-15");