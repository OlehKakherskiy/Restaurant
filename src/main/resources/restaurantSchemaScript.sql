SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `restaurant` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

CREATE TABLE IF NOT EXISTS `restaurant`.`Dish` (
  `DishID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `description` TEXT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `dishTypeID` INT(10) UNSIGNED NOT NULL,
  `technologicalTaskID` INT(10) UNSIGNED NULL DEFAULT NULL,
  `price` FLOAT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`DishID`),
  INDEX `fk_Course_CourseType1_idx` (`dishTypeID` ASC),
  INDEX `fk_Course_TechnologicalTask1_idx` (`technologicalTaskID` ASC),
  CONSTRAINT `fk_Course_CourseType1`
  FOREIGN KEY (`dishTypeID`)
  REFERENCES `restaurant`.`DishType` (`DishTypeID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_Course_TechnologicalTask1`
  FOREIGN KEY (`technologicalTaskID`)
  REFERENCES `restaurant`.`TechnologicalTask` (`technologicalTaskID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `restaurant`.`Ingredient` (
  `ingredientID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `productCount` SMALLINT(6) NOT NULL,
  `productID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`ingredientID`),
  INDEX `FK_Product_Ingredient` (`productID` ASC),
  CONSTRAINT `FK_Product_Ingredient`
  FOREIGN KEY (`productID`)
  REFERENCES `restaurant`.`Product` (`productID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `restaurant`.`mobileNumber` (
  `mobileNumber` VARCHAR(20) NOT NULL,
  `mobileNumberID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `UserID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`mobileNumberID`),
  INDEX `FK_User_mobileNumber` (`UserID` ASC),
  CONSTRAINT `FK_User_mobileNumber`
  FOREIGN KEY (`UserID`)
  REFERENCES `restaurant`.`User` (`UserID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `restaurant`.`Bill` (
  `OrderID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `UserID` INT(10) UNSIGNED NOT NULL,
  `waiterID` INT(10) UNSIGNED NULL DEFAULT NULL,
  `reservationID` INT(10) UNSIGNED NOT NULL,
  `orderStatus` ENUM('initiated','activated','processing','annuled','executed') NOT NULL,
  PRIMARY KEY (`OrderID`),
  INDEX `FK_Order_User` (`UserID` ASC),
  INDEX `FK_waiterID_User` (`waiterID` ASC),
  INDEX `fk_Order_Reservation1_idx` (`reservationID` ASC),
  CONSTRAINT `FK_Order_User`
  FOREIGN KEY (`UserID`)
  REFERENCES `restaurant`.`User` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_waiterID_User`
  FOREIGN KEY (`waiterID`)
  REFERENCES `restaurant`.`User` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE No Action,
  CONSTRAINT `fk_Order_Reservation`
  FOREIGN KEY (`reservationID`)
  REFERENCES `restaurant`.`Reservation` (`reservationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `restaurant`.`UserOrderPosition` (
  `orderPositionID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `dishCount` TINYINT(3) UNSIGNED NOT NULL,
  `DishID` INT(10) UNSIGNED NOT NULL,
  `OrderID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`orderPositionID`),
  INDEX `FK_OrderPosition_Course` (`DishID` ASC),
  INDEX `FK_OrderPosition_Order` (`OrderID` ASC),
  CONSTRAINT `FK_OrderPosition_Course`
  FOREIGN KEY (`DishID`)
  REFERENCES `restaurant`.`Dish` (`DishID`)
    ON DELETE RESTRICT
    ON UPDATE No Action,
  CONSTRAINT `FK_OrderPosition_Order`
  FOREIGN KEY (`OrderID`)
  REFERENCES `restaurant`.`Bill` (`OrderID`)
    ON DELETE No Action
    ON UPDATE RESTRICT)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `restaurant`.`Outgoing` (
  `ingredientCount` SMALLINT(6) NOT NULL,
  `ingredientID` INT(10) UNSIGNED NOT NULL,
  `outgoingID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `taskExecutionID` INT(11) NOT NULL,
  INDEX `FK_Outgoing_Ingredient` (`ingredientID` ASC),
  PRIMARY KEY (`outgoingID`),
  CONSTRAINT `FK_Outgoin_TaskExecution`
  FOREIGN KEY (`outgoingID`)
  REFERENCES `restaurant`.`TaskExecution` (`taskExecutionID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_Outgoing_Ingredient`
  FOREIGN KEY (`ingredientID`)
  REFERENCES `restaurant`.`Ingredient` (`ingredientID`)
    ON DELETE No Action
    ON UPDATE No Action)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `restaurant`.`Product` (
  `productID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `measureUnit` ENUM('gramm','thing','milliliter') NOT NULL,
  PRIMARY KEY (`productID`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `restaurant`.`Reservation` (
  `reservationID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `status` ENUM('busy','free','reserved') NOT NULL,
  `price` SMALLINT(6) NOT NULL,
  `startTimeReservation` DATETIME NULL DEFAULT NULL,
  `endTimeReservation` DATETIME NULL DEFAULT NULL,
  `tableID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`reservationID`),
  INDEX `FK_Table_Reservation` (`tableID` ASC),
  CONSTRAINT `FK_Table_Reservation`
  FOREIGN KEY (`tableID`)
  REFERENCES `restaurant`.`Table` (`tableID`)
    ON DELETE RESTRICT
    ON UPDATE No Action)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `restaurant`.`Table` (
  `tableID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `seatsNumber` TINYINT(4) NOT NULL,
  PRIMARY KEY (`tableID`),
  UNIQUE INDEX `tableID_UNIQUE` (`tableID` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `restaurant`.`TaskExecution` (
  `executionStatus` ENUM('initiated','in progress','ready for check','checked') NOT NULL,
  `mark` ENUM('1','2','3','4','5') NOT NULL,
  `taskExecutionID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `endTime` DATETIME NULL DEFAULT NULL,
  `startTime` DATETIME NOT NULL,
  `orderPositionID` INT(10) UNSIGNED NOT NULL,
  `UserID` INT(10) UNSIGNED NOT NULL,
  `technologicalTaskID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`taskExecutionID`),
  INDEX `FK_OrderPosition_TaskExecution` (`orderPositionID` ASC),
  INDEX `FK_User_TaskExecution` (`UserID` ASC),
  INDEX `FK_TechnologicalTask_TaskExecution` (`technologicalTaskID` ASC),
  UNIQUE INDEX `taskExecutionID_UNIQUE` (`taskExecutionID` ASC),
  CONSTRAINT `FK_OrderPosition_TaskExecution`
  FOREIGN KEY (`orderPositionID`)
  REFERENCES `restaurant`.`UserOrderPosition` (`orderPositionID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_User_TaskExecution`
  FOREIGN KEY (`UserID`)
  REFERENCES `restaurant`.`User` (`UserID`)
    ON DELETE Restrict
    ON UPDATE Cascade,
  CONSTRAINT `FK_TechnologicalTask_TaskExecution`
  FOREIGN KEY (`technologicalTaskID`)
  REFERENCES `restaurant`.`TechnologicalTask` (`technologicalTaskID`)
    ON DELETE No Action
    ON UPDATE No Action)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `restaurant`.`DependsOnTask` (
  `dependsOnID` INT(11) NOT NULL,
  `parentTaskID` INT(10) UNSIGNED NULL DEFAULT NULL,
  `childTaskID` INT(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`dependsOnID`),
  INDEX `FK_previousTo_TechnologicalTask` (`parentTaskID` ASC),
  CONSTRAINT `FK_previousTo_TechnologicalTask`
  FOREIGN KEY (`parentTaskID`)
  REFERENCES `restaurant`.`TechnologicalTask` (`technologicalTaskID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_nextTo_DependsOnTask`
  FOREIGN KEY (`parentTaskID`)
  REFERENCES `restaurant`.`TechnologicalTask` (`technologicalTaskID`)
    ON DELETE No Action
    ON UPDATE No Action)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `restaurant`.`TechnologicalTask` (
  `technologicalTaskID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `taskDescription` TEXT NOT NULL,
  `approximateTimeExecution` TIME NOT NULL,
  PRIMARY KEY (`technologicalTaskID`),
  UNIQUE INDEX `technologicalTaskID_UNIQUE` (`technologicalTaskID` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `restaurant`.`User` (
  `UserID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `surname` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `workMark` TINYINT(3) UNSIGNED NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `userType` ENUM('client','waiter','administrator','chief-kitchener','kitchener') NULL DEFAULT NULL,
  PRIMARY KEY (`UserID`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `restaurant`.`JoinTechnologicalTaskToIngredient` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `technologicalTaskID` INT(10) UNSIGNED NOT NULL,
  `ingredientID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_JoinTechnologicalTaskToIngredient_Ingredient` (`ingredientID` ASC),
  INDEX `FK_JoinTechnologicalTaskToIngredient_TechnologicalTask` (`technologicalTaskID` ASC),
  CONSTRAINT `FK_JoinTechnologicalTaskToIngredient_Ingredient`
  FOREIGN KEY (`ingredientID`)
  REFERENCES `restaurant`.`Ingredient` (`ingredientID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_JoinTechnologicalTaskToIngredient_TechnologicalTask`
  FOREIGN KEY (`technologicalTaskID`)
  REFERENCES `restaurant`.`TechnologicalTask` (`technologicalTaskID`)
    ON DELETE Restrict
    ON UPDATE Restrict)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `restaurant`.`DishType` (
  `DishTypeID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `typeName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`DishTypeID`),
  UNIQUE INDEX `CourseTypeID_UNIQUE` (`DishTypeID` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `restaurant`.`DishTypeHierarchy` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `parentTypeID` INT(10) UNSIGNED NULL DEFAULT NULL,
  `subtypeID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_CourseTypeHierarchy_CourseType1_idx` (`parentTypeID` ASC),
  INDEX `fk_DishTypeHierarchy_DishType1_idx` (`subtypeID` ASC),
  CONSTRAINT `fk_CourseTypeHierarchy_CourseType1`
  FOREIGN KEY (`parentTypeID`)
  REFERENCES `restaurant`.`DishType` (`DishTypeID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_DishTypeHierarchy_DishType1`
  FOREIGN KEY (`subtypeID`)
  REFERENCES `restaurant`.`DishType` (`DishTypeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_general_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
