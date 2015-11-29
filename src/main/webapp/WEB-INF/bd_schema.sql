SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `restaurant` ;
CREATE SCHEMA IF NOT EXISTS `restaurant` DEFAULT CHARACTER SET utf8 ;
USE `restaurant` ;

-- -----------------------------------------------------
-- Table `restaurant`.`CourseType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`CourseType` ;

CREATE TABLE IF NOT EXISTS `restaurant`.`CourseType` (
  `CourseTypeID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `typeName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CourseTypeID`),
  UNIQUE INDEX `CourseTypeID_UNIQUE` (`CourseTypeID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restaurant`.`TechnologicalTask`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`TechnologicalTask` ;

CREATE TABLE IF NOT EXISTS `restaurant`.`TechnologicalTask` (
  `technologicalTaskID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `taskDescription` TEXT NOT NULL DEFAULT 'опис відсутній',
  PRIMARY KEY (`technologicalTaskID`),
  UNIQUE INDEX `technologicalTaskID_UNIQUE` (`technologicalTaskID` ASC));


-- -----------------------------------------------------
-- Table `restaurant`.`Course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`Course` ;

CREATE TABLE IF NOT EXISTS `restaurant`.`Course` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `description` TEXT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `CourseTypeID` INT UNSIGNED NOT NULL,
  `TechnologicalTask_technologicalTaskID` INT UNSIGNED NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Course_CourseType1_idx` (`CourseTypeID` ASC),
  INDEX `fk_Course_TechnologicalTask1_idx` (`TechnologicalTask_technologicalTaskID` ASC),
  CONSTRAINT `fk_Course_CourseType1`
    FOREIGN KEY (`CourseTypeID`)
    REFERENCES `restaurant`.`CourseType` (`CourseTypeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Course_TechnologicalTask1`
    FOREIGN KEY (`TechnologicalTask_technologicalTaskID`)
    REFERENCES `restaurant`.`TechnologicalTask` (`technologicalTaskID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `restaurant`.`Product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`Product` ;

CREATE TABLE IF NOT EXISTS `restaurant`.`Product` (
  `productID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `measureUnit` ENUM('gramm','thing','milliliter') NOT NULL,
  PRIMARY KEY (`productID`));


-- -----------------------------------------------------
-- Table `restaurant`.`Ingredient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`Ingredient` ;

CREATE TABLE IF NOT EXISTS `restaurant`.`Ingredient` (
  `ingredientID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `productCount` SMALLINT NOT NULL,
  `productID` INT NOT NULL,
  PRIMARY KEY (`ingredientID`),
  INDEX `FK_Product_Ingredient` (`productID` ASC),
  CONSTRAINT `FK_Product_Ingredient`
    FOREIGN KEY (`productID`)
    REFERENCES `restaurant`.`Product` (`productID`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `restaurant`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`User` ;

CREATE TABLE IF NOT EXISTS `restaurant`.`User` (
  `UserID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `surname` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `workMark` TINYINT UNSIGNED NULL DEFAULT NULL,
  `password` VARCHAR(50) NOT NULL,
  `userType` ENUM('client','waiter','administrator','chief-kitchener','kitchener') NULL,
  PRIMARY KEY (`UserID`));


-- -----------------------------------------------------
-- Table `restaurant`.`Table`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`Table` ;

CREATE TABLE IF NOT EXISTS `restaurant`.`Table` (
  `tableID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `seatsNumber` TINYINT NOT NULL,
  PRIMARY KEY (`tableID`),
  UNIQUE INDEX `tableID_UNIQUE` (`tableID` ASC));


-- -----------------------------------------------------
-- Table `restaurant`.`Reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`Reservation` ;

CREATE TABLE IF NOT EXISTS `restaurant`.`Reservation` (
  `reservationID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `status` ENUM('busy','free') NOT NULL,
  `price` SMALLINT NOT NULL,
  `startTimeReservation` TIMESTAMP NOT NULL,
  `endTimeReservation` TIMESTAMP NOT NULL,
  `tableID` INT NOT NULL,
  `OrderID` INT NOT NULL,
  PRIMARY KEY (`reservationID`),
  INDEX `FK_Table_Reservation` (`tableID` ASC),
  INDEX `FK_Reservation_Order` (`OrderID` ASC),
  CONSTRAINT `FK_Table_Reservation`
    FOREIGN KEY (`tableID`)
    REFERENCES `restaurant`.`Table` (`tableID`)
    ON DELETE RESTRICT
    ON UPDATE No Action,
  CONSTRAINT `FK_Reservation_Order`
    FOREIGN KEY (`OrderID`)
    REFERENCES `restaurant`.`Order` (`OrderID`)
    ON DELETE No Action
    ON UPDATE No Action);


-- -----------------------------------------------------
-- Table `restaurant`.`Order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`Order` ;

CREATE TABLE IF NOT EXISTS `restaurant`.`Order` (
  `OrderID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `reservationID` INT NOT NULL,
  `UserID` INT NOT NULL,
  `waiterID` INT NOT NULL,
  PRIMARY KEY (`OrderID`),
  INDEX `FK_Order_Reservation` (`reservationID` ASC),
  INDEX `FK_Order_User` (`UserID` ASC),
  INDEX `FK_waiterID_User` (`waiterID` ASC),
  CONSTRAINT `FK_Order_Reservation`
    FOREIGN KEY (`reservationID`)
    REFERENCES `restaurant`.`Reservation` (`reservationID`)
    ON DELETE Restrict
    ON UPDATE Restrict,
  CONSTRAINT `FK_Order_User`
    FOREIGN KEY (`UserID`)
    REFERENCES `restaurant`.`User` (`UserID`)
    ON DELETE Restrict
    ON UPDATE Restrict,
  CONSTRAINT `FK_waiterID_User`
    FOREIGN KEY (`waiterID`)
    REFERENCES `restaurant`.`User` (`UserID`)
    ON DELETE No Action
    ON UPDATE No Action);


-- -----------------------------------------------------
-- Table `restaurant`.`JoinOrderToUser`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`JoinOrderToUser` ;

CREATE TABLE IF NOT EXISTS `restaurant`.`JoinOrderToUser` (
  `UserID` INT NOT NULL,
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `OrderID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_User` (`UserID` ASC),
  INDEX `FK_Order` (`OrderID` ASC),
  CONSTRAINT `FK_User`
    FOREIGN KEY (`UserID`)
    REFERENCES `restaurant`.`User` (`UserID`)
    ON DELETE CASCADE
    ON UPDATE No Action,
  CONSTRAINT `FK_Order`
    FOREIGN KEY (`OrderID`)
    REFERENCES `restaurant`.`Order` (`OrderID`)
    ON DELETE No Action
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `restaurant`.`mobileNumber`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`mobileNumber` ;

CREATE TABLE IF NOT EXISTS `restaurant`.`mobileNumber` (
  `mobileNumber` VARCHAR(20) NOT NULL,
  `mobileNumberID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `UserID` INT NOT NULL,
  PRIMARY KEY (`mobileNumberID`),
  INDEX `FK_User_mobileNumber` (`UserID` ASC),
  CONSTRAINT `FK_User_mobileNumber`
    FOREIGN KEY (`UserID`)
    REFERENCES `restaurant`.`User` (`UserID`)
    ON DELETE Restrict
    ON UPDATE Cascade);


-- -----------------------------------------------------
-- Table `restaurant`.`OrderPosition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`OrderPosition` ;

CREATE TABLE IF NOT EXISTS `restaurant`.`OrderPosition` (
  `orderPositionID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `courseCount` TINYINT NOT NULL,
  `ID` INT NOT NULL,
  `OrderID` INT NOT NULL,
  PRIMARY KEY (`orderPositionID`),
  INDEX `FK_OrderPosition_Course` (`ID` ASC),
  INDEX `FK_OrderPosition_Order` (`OrderID` ASC),
  CONSTRAINT `FK_OrderPosition_Course`
    FOREIGN KEY (`ID`)
    REFERENCES `restaurant`.`Course` (`ID`)
    ON DELETE No Action
    ON UPDATE No Action,
  CONSTRAINT `FK_OrderPosition_Order`
    FOREIGN KEY (`OrderID`)
    REFERENCES `restaurant`.`Order` (`OrderID`)
    ON DELETE No Action
    ON UPDATE No Action);


-- -----------------------------------------------------
-- Table `restaurant`.`TaskExecution`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`TaskExecution` ;

CREATE TABLE IF NOT EXISTS `restaurant`.`TaskExecution` (
  `executionStatus` ENUM('initiated','in progress','ready for check','checked') NOT NULL DEFAULT 'initiated',
  `mark` ENUM('1','2','3','4','5') NOT NULL,
  `taskExecutionID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `endTime` TIMESTAMP NOT NULL,
  `startTime` TIMESTAMP NOT NULL,
  `orderPositionID` INT NOT NULL,
  `UserID` INT NOT NULL,
  `technologicalTaskID` INT NOT NULL,
  PRIMARY KEY (`taskExecutionID`),
  INDEX `FK_OrderPosition_TaskExecution` (`orderPositionID` ASC),
  INDEX `FK_User_TaskExecution` (`UserID` ASC),
  INDEX `FK_TechnologicalTask_TaskExecution` (`technologicalTaskID` ASC),
  UNIQUE INDEX `taskExecutionID_UNIQUE` (`taskExecutionID` ASC),
  CONSTRAINT `FK_OrderPosition_TaskExecution`
    FOREIGN KEY (`orderPositionID`)
    REFERENCES `restaurant`.`OrderPosition` (`orderPositionID`)
    ON DELETE No Action
    ON UPDATE No Action,
  CONSTRAINT `FK_User_TaskExecution`
    FOREIGN KEY (`UserID`)
    REFERENCES `restaurant`.`User` (`UserID`)
    ON DELETE Restrict
    ON UPDATE Cascade,
  CONSTRAINT `FK_TechnologicalTask_TaskExecution`
    FOREIGN KEY (`technologicalTaskID`)
    REFERENCES `restaurant`.`TechnologicalTask` (`technologicalTaskID`)
    ON DELETE No Action
    ON UPDATE No Action);


-- -----------------------------------------------------
-- Table `restaurant`.`Outgoing`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`Outgoing` ;

CREATE TABLE IF NOT EXISTS `restaurant`.`Outgoing` (
  `ingredientCount` SMALLINT NOT NULL,
  `ingredientID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `outgoingID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `taskExecutionID` INT NOT NULL,
  INDEX `FK_Outgoing_Ingredient` (`ingredientID` ASC),
  PRIMARY KEY (`outgoingID`),
  CONSTRAINT `FK_Outgoin_TaskExecution`
    FOREIGN KEY (`outgoingID`)
    REFERENCES `restaurant`.`TaskExecution` (`taskExecutionID`)
    ON DELETE Restrict
    ON UPDATE Restrict,
  CONSTRAINT `FK_Outgoing_Ingredient`
    FOREIGN KEY (`ingredientID`)
    REFERENCES `restaurant`.`Ingredient` (`ingredientID`)
    ON DELETE No Action
    ON UPDATE No Action);


-- -----------------------------------------------------
-- Table `restaurant`.`DependsOnTask`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`DependsOnTask` ;

CREATE TABLE IF NOT EXISTS `restaurant`.`DependsOnTask` (
  `dependsOnID` INT NOT NULL,
  `technologicalTaskID` INT NULL,
  `technologicalTaskID` INT NULL,
  PRIMARY KEY (`dependsOnID`),
  INDEX `FK_previousTo_TechnologicalTask` (`technologicalTaskID` ASC),
  CONSTRAINT `FK_previousTo_TechnologicalTask`
    FOREIGN KEY (`technologicalTaskID`)
    REFERENCES `restaurant`.`TechnologicalTask` (`technologicalTaskID`)
    ON DELETE No Action
    ON UPDATE No Action,
  CONSTRAINT `FK_nextTo_DependsOnTask`
    FOREIGN KEY (`technologicalTaskID`)
    REFERENCES `restaurant`.`TechnologicalTask` (`technologicalTaskID`)
    ON DELETE No Action
    ON UPDATE No Action);


-- -----------------------------------------------------
-- Table `restaurant`.`JoinTechnologicalTaskToIngredient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`JoinTechnologicalTaskToIngredient` ;

CREATE TABLE IF NOT EXISTS `restaurant`.`JoinTechnologicalTaskToIngredient` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `technologicalTaskID` INT NOT NULL,
  `ingredientID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_JoinTechnologicalTaskToIngredient_Ingredient` (`ingredientID` ASC),
  INDEX `FK_JoinTechnologicalTaskToIngredient_TechnologicalTask` (`technologicalTaskID` ASC),
  CONSTRAINT `FK_JoinTechnologicalTaskToIngredient_Ingredient`
    FOREIGN KEY (`ingredientID`)
    REFERENCES `restaurant`.`Ingredient` (`ingredientID`)
    ON DELETE Restrict
    ON UPDATE Restrict,
  CONSTRAINT `FK_JoinTechnologicalTaskToIngredient_TechnologicalTask`
    FOREIGN KEY (`technologicalTaskID`)
    REFERENCES `restaurant`.`TechnologicalTask` (`technologicalTaskID`)
    ON DELETE Restrict
    ON UPDATE Restrict);


-- -----------------------------------------------------
-- Table `restaurant`.`CourseTypeHierarchy`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`CourseTypeHierarchy` ;

CREATE TABLE IF NOT EXISTS `restaurant`.`CourseTypeHierarchy` (
  `idCourseTypeHierarchy` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `subtypeID` INT UNSIGNED NOT NULL,
  `parentTypeID` INT UNSIGNED NOT NULL,
  `CourseType_CourseTypeID` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idCourseTypeHierarchy`),
  INDEX `fk_CourseSubtype_CourseType2_idx` (`parentTypeID` ASC),
  INDEX `fk_CourseTypeHierarchy_CourseType1_idx` (`CourseType_CourseTypeID` ASC),
  CONSTRAINT `fk_CourseSubtype_CourseType2`
    FOREIGN KEY (`parentTypeID`)
    REFERENCES `restaurant`.`CourseType` (`CourseTypeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CourseTypeHierarchy_CourseType1`
    FOREIGN KEY (`CourseType_CourseTypeID`)
    REFERENCES `restaurant`.`CourseType` (`CourseTypeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
