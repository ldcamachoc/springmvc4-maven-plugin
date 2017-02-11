-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`students`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`students` ;

CREATE TABLE IF NOT EXISTS `mydb`.`students` (
  `idStudent` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(100) NOT NULL,
  `lastName` VARCHAR(100) NOT NULL,
  `student_adress` VARCHAR(255) NULL,
  `phone_number` VARCHAR(25) NULL,
  `email_adress` VARCHAR(45) NULL,
  PRIMARY KEY (`idStudent`),
  UNIQUE INDEX `idStudents_UNIQUE` (`idStudent` ASC),
  UNIQUE INDEX `email_adress_UNIQUE` (`email_adress` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`books`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`books` ;

CREATE TABLE IF NOT EXISTS `mydb`.`books` (
  `isbn` VARCHAR(14) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `date_of_publication` DATE NULL,
  PRIMARY KEY (`isbn`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`author`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`author` ;

CREATE TABLE IF NOT EXISTS `mydb`.`author` (
  `idAuthor` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(100) NOT NULL,
  `lastName` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idAuthor`),
  UNIQUE INDEX `idAuthor_UNIQUE` (`idAuthor` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`books_by_author`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`books_by_author` ;

CREATE TABLE IF NOT EXISTS `mydb`.`books_by_author` (
  `idAuthor` INT NOT NULL,
  `isbn` VARCHAR(14) NOT NULL,
  PRIMARY KEY (`idAuthor`, `isbn`),
  INDEX `FK_books_by_author_idx` (`isbn` ASC),
  CONSTRAINT `FK_books_by_author`
    FOREIGN KEY (`isbn`)
    REFERENCES `mydb`.`books` (`isbn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_author_of_books`
    FOREIGN KEY (`idAuthor`)
    REFERENCES `mydb`.`author` (`idAuthor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`category` ;

CREATE TABLE IF NOT EXISTS `mydb`.`category` (
  `idCategory` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`idCategory`),
  UNIQUE INDEX `idCategory_UNIQUE` (`idCategory` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`books_by_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`books_by_category` ;

CREATE TABLE IF NOT EXISTS `mydb`.`books_by_category` (
  `isbn` VARCHAR(14) NOT NULL,
  `idCategory` INT NOT NULL,
  PRIMARY KEY (`isbn`, `idCategory`),
  INDEX `FK_category_of_books_idx` (`idCategory` ASC),
  CONSTRAINT `FK_books_by_category`
    FOREIGN KEY (`isbn`)
    REFERENCES `mydb`.`books` (`isbn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_category_of_books`
    FOREIGN KEY (`idCategory`)
    REFERENCES `mydb`.`category` (`idCategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`books_out_on_loan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`books_out_on_loan` ;

CREATE TABLE IF NOT EXISTS `mydb`.`books_out_on_loan` (
  `idBookBorrowing` INT NOT NULL AUTO_INCREMENT,
  `borrowerStudent_id` INT NOT NULL,
  `isbn` VARCHAR(14) NOT NULL,
  `data_issued` DATETIME NULL,
  `data_due_for_return` DATETIME NULL,
  `data_returned` DATETIME NULL,
  `amount_of_fine` DECIMAL(10,2) NULL DEFAULT 0.0,
  PRIMARY KEY (`idBookBorrowing`),
  INDEX `FK_students_book_on_loan_idx` (`borrowerStudent_id` ASC),
  INDEX `FK_books_book_on_loan_idx` (`isbn` ASC),
  CONSTRAINT `FK_students_book_on_loan`
    FOREIGN KEY (`borrowerStudent_id`)
    REFERENCES `mydb`.`students` (`idStudent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_books_book_on_loan`
    FOREIGN KEY (`isbn`)
    REFERENCES `mydb`.`books` (`isbn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
