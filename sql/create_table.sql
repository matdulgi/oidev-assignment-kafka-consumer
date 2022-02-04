-- MySQL Script generated by MySQL Workbench
-- Thu Feb  3 15:41:33 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`DISK_FREE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`DISK_FREE` (
  `disk_free` DOUBLE NULL,
  `disk_free_absolute_dev_shm` DOUBLE NULL,
  `disk_free_absolute_rootfs` DOUBLE NULL,
  `disk_free_percent_dev_shm` DOUBLE NULL,
  `disk_free_percent_rootfs` DOUBLE NULL,
  `RRD_id` INT NOT NULL,
  PRIMARY KEY (`RRD_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`DISK_FREE_DATA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`DISK_FREE_DATA` (
  `TYPE` VARCHAR(10) NULL,
  `ABSOLUTE_DATA` DOUBLE NULL,
  `DATA_SEQUENCE` INT NULL,
  `RRD_id` INT NOT NULL,
  `PERCENT_DATA` DOUBLE NULL,
  PRIMARY KEY (`RRD_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`HM`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`HM` (
  `id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`HM_CLUSTER_METRICS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`HM_CLUSTER_METRICS` (
  `HM_id` INT NOT NULL,
  `system_seq` INT NOT NULL,
  `process_seq` INT NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  `metric_name` VARCHAR(50) NOT NULL,
  `metric_value` VARCHAR(50) NULL,
  PRIMARY KEY (`HM_id`),
  CONSTRAINT `fk_CLUSTER_METRICS_HM1`
    FOREIGN KEY (`HM_id`)
    REFERENCES `mydb`.`HM` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`HM_FSNAMESYSTEM`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`HM_FSNAMESYSTEM` (
  `HM_id` INT NOT NULL,
  `system_seq` INT NOT NULL,
  `process_seq` INT NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  `metric_name` VARCHAR(50) NOT NULL,
  `metric_value` VARCHAR(50) NULL,
  PRIMARY KEY (`HM_id`),
  INDEX `fk_FSNAMESYSTEM_STATE_HM1_idx` (`HM_id` ASC) VISIBLE,
  CONSTRAINT `fk_FSNAMESYSTEM_STATE_HM1`
    FOREIGN KEY (`HM_id`)
    REFERENCES `mydb`.`HM` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`HM_HEAPMEMORY_USAGE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`HM_HEAPMEMORY_USAGE` (
  `HM_id` INT NOT NULL,
  `system_seq` INT NOT NULL,
  `process_seq` INT NOT NULL,
  `metric_name` VARCHAR(50) NOT NULL,
  `metric_value` VARCHAR(50) NULL,
  `timestamp` TIMESTAMP NOT NULL,
  PRIMARY KEY (`HM_id`),
  CONSTRAINT `fk_HEAPMEMORY_USAGE_HM1`
    FOREIGN KEY (`HM_id`)
    REFERENCES `mydb`.`HM` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`HM_JVM_METRICS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`HM_JVM_METRICS` (
  `HM_id` INT NOT NULL,
  `system_seq` INT NOT NULL,
  `process_seq` INT NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  `metric_name` VARCHAR(50) NOT NULL,
  `metric_value` VARCHAR(50) NULL,
  PRIMARY KEY (`HM_id`),
  INDEX `fk_JVM_METRICS_HM1_idx` (`HM_id` ASC) VISIBLE,
  CONSTRAINT `fk_JVM_METRICS_HM1`
    FOREIGN KEY (`HM_id`)
    REFERENCES `mydb`.`HM` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`HM_METRICSSYSTEM_STATS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`HM_METRICSSYSTEM_STATS` (
  `HM_id` INT NOT NULL,
  `system_seq` INT NOT NULL,
  `process_seq` INT NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  `metric_name` VARCHAR(50) NOT NULL,
  `metric_value` VARCHAR(50) NULL,
  PRIMARY KEY (`HM_id`),
  CONSTRAINT `fk_METRICSSYSTEM_STATS_HM1`
    FOREIGN KEY (`HM_id`)
    REFERENCES `mydb`.`HM` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`HM_NAMENODE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`HM_NAMENODE` (
  `HM_id` INT NOT NULL,
  `system_seq` INT NOT NULL,
  `process_seq` INT NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  `metric_name` VARCHAR(50) NOT NULL,
  `metric_value` VARCHAR(50) NULL,
  PRIMARY KEY (`HM_id`),
  INDEX `fk_NAMENODE_ACTIVITY_HM1_idx` (`HM_id` ASC) VISIBLE,
  CONSTRAINT `fk_NAMENODE_ACTIVITY_HM1`
    FOREIGN KEY (`HM_id`)
    REFERENCES `mydb`.`HM` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`HM_NODEMANAGER_METRICS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`HM_NODEMANAGER_METRICS` (
  `HM_id` INT NOT NULL,
  `system_seq` INT NOT NULL,
  `process_seq` INT NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  `metric_name` VARCHAR(50) NOT NULL,
  `metric_value` VARCHAR(50) NULL,
  PRIMARY KEY (`HM_id`),
  CONSTRAINT `fk_NODEMANAGER_METRICS_HM1`
    FOREIGN KEY (`HM_id`)
    REFERENCES `mydb`.`HM` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`HM_NODE_USAGE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`HM_NODE_USAGE` (
  `HM_id` INT NOT NULL,
  `system_seq` INT NOT NULL,
  `process_seq` INT NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  `metric_name` VARCHAR(50) NOT NULL,
  `metric_value` VARCHAR(50) NULL,
  PRIMARY KEY (`HM_id`),
  CONSTRAINT `fk_NODE_USAGE_HM1`
    FOREIGN KEY (`HM_id`)
    REFERENCES `mydb`.`HM` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`HM_QUEUE_METRICS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`HM_QUEUE_METRICS` (
  `HM_id` INT NOT NULL,
  `system_seq` INT NOT NULL,
  `process_seq` INT NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  `metric_name` VARCHAR(50) NOT NULL,
  `metric_value` VARCHAR(50) NULL,
  PRIMARY KEY (`HM_id`),
  CONSTRAINT `fk_QUEUE_METRICS_HM1`
    FOREIGN KEY (`HM_id`)
    REFERENCES `mydb`.`HM` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`HM_SHUFFLE_METRICS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`HM_SHUFFLE_METRICS` (
  `HM_id` INT NOT NULL,
  `system_seq` INT NOT NULL,
  `process_seq` INT NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  `metric_name` VARCHAR(50) NOT NULL,
  `metric_value` VARCHAR(50) NULL,
  PRIMARY KEY (`HM_id`),
  CONSTRAINT `fk_SHUFFLE_METRICS_HM1`
    FOREIGN KEY (`HM_id`)
    REFERENCES `mydb`.`HM` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`RRD`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`RRD` (
  `system_seq` INT NOT NULL,
  `process_seq` INT NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  `metric_name` VARCHAR(50) NOT NULL,
  `metric_value` VARCHAR(50) NULL)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`SM`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`SM` (
  `id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`SM_CLUSTER_STATUS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`SM_CLUSTER_STATUS` (
  `SM_id` INT NOT NULL,
  `system_seq` INT NOT NULL,
  `process_seq` INT NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  `metric_name` VARCHAR(50) NOT NULL,
  `metric_value` VARCHAR(50) NULL,
  PRIMARY KEY (`SM_id`),
  INDEX `fk_CLUSTER_STATUS_SM1_idx` (`SM_id` ASC) VISIBLE,
  CONSTRAINT `fk_CLUSTER_STATUS_SM1`
    FOREIGN KEY (`SM_id`)
    REFERENCES `mydb`.`SM` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`SM_NAMENODE_LIVENODE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`SM_NAMENODE_LIVENODE` (
  `system_seq` INT NOT NULL,
  `process_seq` INT NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  `SM_id` INT NOT NULL,
  `metric_name` VARCHAR(50) NOT NULL,
  `metric_value` VARCHAR(50) NULL,
  PRIMARY KEY (`SM_id`),
  CONSTRAINT `fk_NAMENODE_LIVENODE_SM1`
    FOREIGN KEY (`SM_id`)
    REFERENCES `mydb`.`SM` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`SM_NODEMANAGER_LIVE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`SM_NODEMANAGER_LIVE` (
  `SM_id` INT NOT NULL,
  `system_seq` INT NOT NULL,
  `process_seq` INT NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  `metric_name` VARCHAR(50) NOT NULL,
  `metric_value` VARCHAR(50) NULL,
  PRIMARY KEY (`SM_id`),
  INDEX `fk_NODEMANAGER_LIVE_SM1_idx` (`SM_id` ASC) VISIBLE,
  CONSTRAINT `fk_NODEMANAGER_LIVE_SM1`
    FOREIGN KEY (`SM_id`)
    REFERENCES `mydb`.`SM` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
