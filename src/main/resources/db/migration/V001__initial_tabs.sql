-- MySQL Script generated by MySQL Workbench
-- Thu Oct  3 21:15:55 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering
-- Author: Jonathan Nascimento

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema geriluhbd
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema geriluhbd
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS $$$raw_schema$$$ DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE $$$raw_schema$$$ ;

-- -----------------------------------------------------
-- Table `geriluhbd`.`flyway_schema_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS $$$raw_schema$$$.`flyway_schema_history` (
  `installed_rank` INT NOT NULL,
  `version` VARCHAR(50) NULL DEFAULT NULL,
  `description` VARCHAR(200) NOT NULL,
  `type` VARCHAR(20) NOT NULL,
  `script` VARCHAR(1000) NOT NULL,
  `checksum` INT NULL DEFAULT NULL,
  `installed_by` VARCHAR(100) NOT NULL,
  `installed_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` INT NOT NULL,
  `success` TINYINT(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  INDEX `flyway_schema_history_s_idx` (`success` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `geriluhbd`.`tb_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS $$$raw_schema$$$.`tb_user` (
  `id_usr` INT NOT NULL AUTO_INCREMENT COMMENT 'Unique identifier for the user, primary key / Identificador único do usuário, chave primária',
  `nam_usr` VARCHAR(255) NOT NULL COMMENT 'Full name of the user / Nome completo do usuário',
  `cpf_usr` VARCHAR(11) NOT NULL COMMENT 'User\'s CPF / CPF do usuário',
  `eml_usr` VARCHAR(255) NOT NULL COMMENT 'User\'s email, unique / Email do usuário, único',
  `ph_num_usr` VARCHAR(20) NULL DEFAULT NULL COMMENT 'User\'s phone number / Telefone do usuário',
  `str_add_usr` VARCHAR(255) NULL DEFAULT NULL COMMENT 'User\'s street address / Rua do endereço do usuário',
  `add_num_usr` INT NULL DEFAULT NULL COMMENT 'User\'s address number / Número do endereço do usuário',
  `city_usr` VARCHAR(100) NULL DEFAULT NULL COMMENT 'User\'s city / Cidade do endereço do usuário',
  `sta_usr` VARCHAR(50) NULL DEFAULT NULL COMMENT 'User\'s state / Estado do endereço do usuário',
  `zip_code_usr` VARCHAR(20) NULL DEFAULT NULL COMMENT 'User\'s zip code / CEP do endereço do usuário',
  `birth_date_usr` DATE NULL DEFAULT NULL COMMENT 'User\'s date of birth / Data de nascimento do usuário',
  `user_nam_usr` VARCHAR(100) NOT NULL COMMENT 'Username, unique / Nome de usuário, único',
  `pass_usr` VARCHAR(255) NOT NULL COMMENT 'User\'s password / Senha do usuário',
  `sec_quest_usr` VARCHAR(255) NULL DEFAULT NULL COMMENT 'User\'s security question / Pergunta de segurança do usuário',
  `sec_answ_usr` VARCHAR(255) NULL DEFAULT NULL COMMENT 'User\'s security answer / Resposta da pergunta de segurança',
  `user_type_usr` VARCHAR(50) NULL DEFAULT NULL COMMENT 'User type (e.g., administrator, manager, attendant) / Tipo de usuário (ex.: administrador, gerente, atendente)',
  `crt_at_dat_usr` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Record creation date, default is current date/time / Data de criação do registro, padrão é a data/hora atual',
  `sts_usr` VARCHAR(20) NULL DEFAULT 'active' COMMENT 'User status, default is \"active\" / Status do usuário, padrão é \"ativo\"',
  `pro_pic_user` LONGBLOB NULL DEFAULT NULL COMMENT 'User\'s profile picture / Foto do perfil do usuário',
  `addit_notes_usr` TEXT NULL DEFAULT NULL COMMENT 'Additional notes about the user / Notas adicionais sobre o usuário',
  PRIMARY KEY (`id_usr`),
  UNIQUE INDEX `eml_usr` (`eml_usr` ASC) VISIBLE,
  UNIQUE INDEX `user_nam_usr` (`user_nam_usr` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `geriluhbd`.`tb_cash_register`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS $$$raw_schema$$$.`tb_cash_register` (
  `id_csh_reg` INT NOT NULL AUTO_INCREMENT COMMENT ' Unique identifier for the cash register / Identificador único do caixa',
  `init_dt_csh_reg` DATETIME NOT NULL COMMENT ' Opening date and time of the cash register / Data e hora de abertura do caixa',
  `end_dt_csh_reg` DATETIME NULL COMMENT ' Closing date and time of the cash register / Data e hora de fechamento do caixa',
  `init_blc_csh_reg` DECIMAL(10,2) NOT NULL COMMENT ' Initial balance in the cash register / Saldo inicial no caixa ao abrir',
  `end_blc_csh_reg` DECIMAL(10,2) NULL COMMENT ' Balance at the closing of the cash register / Saldo no caixa ao fechar',
  `total_sale_csh_reg` DECIMAL(10,2) NULL COMMENT 'Total sales during the period / Total das vendas realizadas no período',
  `total_with_csh_reg` DECIMAL(10,2) NULL COMMENT ' Total cash withdrawals from the register /  Total de retiradas de dinheiro do caixa',
  `status_csh_reg` VARCHAR(20) NOT NULL DEFAULT 'open' COMMENT 'Status of the cash register (e.g., open, closed) / Status do caixa (ex.: aberto, fechado)',
  `notes_csh_reg` TEXT NULL COMMENT ' Additional notes about the cash register / Notas adicionais sobre o caixa',
  `id_usr` INT NOT NULL COMMENT 'ID of the user who opened the cash register / ID do usuário que abriu o caixa (referência para TB_USUARIO)',
  PRIMARY KEY (`id_csh_reg`, `id_usr`),
  INDEX `fk_tb_cash_register_tb_user_idx` (`id_usr` ASC) VISIBLE,
  CONSTRAINT `fk_tb_cash_register_tb_user`
    FOREIGN KEY (`id_usr`)
    REFERENCES $$$raw_schema$$$.`tb_user` (`id_usr`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `geriluhbd`.`tb_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS $$$raw_schema$$$.`tb_order` (
  `id_ord` INT NOT NULL AUTO_INCREMENT COMMENT ' Unique identifier for the order / Identificador único da comanda',
  `status_ord` VARCHAR(20) NULL COMMENT ' Status of the order (e.g., open, closed) / Status da comanda (ex.: aberta, fechada)',
  `date_ord` DATETIME NOT NULL COMMENT ' Date and time the order was created / Data e hora de criação da comanda',
  `tab_num_ord` INT NOT NULL COMMENT ' Table number associated with the order / Número da mesa associada à comanda ',
  `total_prc_ord` DECIMAL(10,2) NULL COMMENT ' Total price of the order / Preço total da comanda',
  `paymt_sts_ord` VARCHAR(20) NOT NULL DEFAULT 'unpaid' COMMENT ' Payment status of the order (e.g., paid, unpaid) / Status de pagamento da comanda (ex.: paga, não paga)',
  `end_date_ord` DATETIME NULL COMMENT '  Date and time the order was closed / Data e hora de fechamento da comanda',
  `notes_ord` TEXT NULL COMMENT ' Additional notes about the order / Notas adicionais sobre a comanda',
  `id_csh_reg` INT NOT NULL COMMENT '  Unique identifier for the cash register / Identificador único do caixa',
  `id_usr` INT NOT NULL COMMENT ' ID of the user who opened the cash register / ID do usuário que abriu o caixa (referência para TB_USUARIO)',
  PRIMARY KEY (`id_ord`, `id_csh_reg`, `id_usr`),
  INDEX `fk_tb_order_tb_cash_register1_idx` (`id_csh_reg` ASC, `id_usr` ASC) VISIBLE,
  CONSTRAINT `fk_tb_order_tb_cash_register1`
    FOREIGN KEY (`id_csh_reg` , `id_usr`)
    REFERENCES $$$raw_schema$$$.`tb_cash_register` (`id_csh_reg` , `id_usr`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `geriluhbd`.`tb_dish`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS $$$raw_schema$$$.`tb_dish` (
  `id_dsh` INT NOT NULL AUTO_INCREMENT COMMENT 'Unique identifier for the dish / Identificador único do lanche',
  `dish_nam_dsh` VARCHAR(255) NOT NULL COMMENT ' Name of the dish / Nome do lanche',
  `dish_prc_dsh` DECIMAL(10,2) NOT NULL COMMENT ' Price of the dish / Preço do lanche',
  `desc_dsh` TEXT NOT NULL COMMENT ' Description of the dish / Descrição do lanche',
  `cat_dsh` VARCHAR(100) NOT NULL COMMENT ' Category of the dish (e.g., burger, drink) / Categoria do lanche (ex.: hambúrguer, bebida)',
  `has_add_dsh` CHAR(1) NULL DEFAULT 'n' COMMENT ' Indicates if the dish has additional options / Indica se o lanche tem opções adicionais',
  `pic_dsh` LONGBLOB NULL COMMENT 'Picture of dish / Imagem do lanche',
  PRIMARY KEY (`id_dsh`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `geriluhbd`.`tb_dish_has_tb_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS $$$raw_schema$$$.`tb_dish_has_tb_order` (
  `id_dsh` INT NOT NULL,
  `id_ord` INT NOT NULL,
  `id_csh_reg` INT NOT NULL,
  `id_usr` INT NOT NULL,
  PRIMARY KEY (`id_dsh`, `id_ord`, `id_csh_reg`, `id_usr`),
  INDEX `fk_tb_dish_has_tb_order_tb_order1_idx` (`id_ord` ASC, `id_csh_reg` ASC, `id_usr` ASC) VISIBLE,
  INDEX `fk_tb_dish_has_tb_order_tb_dish1_idx` (`id_dsh` ASC) VISIBLE,
  CONSTRAINT `fk_tb_dish_has_tb_order_tb_dish1`
    FOREIGN KEY (`id_dsh`)
    REFERENCES $$$raw_schema$$$.`tb_dish` (`id_dsh`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_dish_has_tb_order_tb_order1`
    FOREIGN KEY (`id_ord` , `id_csh_reg` , `id_usr`)
    REFERENCES $$$raw_schema$$$.`tb_order` (`id_ord` , `id_csh_reg` , `id_usr`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `geriluhbd`.`tb_payment_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS $$$raw_schema$$$.`tb_payment_type` (
  `id_pay_tp` INT NOT NULL AUTO_INCREMENT COMMENT ' Unique identifier for the payment type / Identificador único do tipo de pagamento',
  `name_pay_tp` VARCHAR(50) NOT NULL COMMENT ' Name of the payment type / Nome do tipo de pagamento (e.g., Cash, Credit Card, Debit Card, PIX)',
  `desc_pay_tp` VARCHAR(45) NULL COMMENT ' Description of the payment type / Descrição do tipo de pagamento ',
  `total_pay_tp` DECIMAL(10,2) NOT NULL COMMENT 'Total at this payment type / Total neste meio de pagamento',
  PRIMARY KEY (`id_pay_tp`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `geriluhbd`.`tb_order_has_tb_payment_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS $$$raw_schema$$$.`tb_order_has_tb_payment_type` (
  `id_ord` INT NOT NULL,
  `id_csh_reg` INT NOT NULL,
  `id_usr` INT NOT NULL,
  `id_pay_tp` INT NOT NULL,
  PRIMARY KEY (`id_ord`, `id_csh_reg`, `id_usr`, `id_pay_tp`),
  INDEX `fk_tb_order_has_tb_payment_type_tb_payment_type1_idx` (`id_pay_tp` ASC) VISIBLE,
  INDEX `fk_tb_order_has_tb_payment_type_tb_order1_idx` (`id_ord` ASC, `id_csh_reg` ASC, `id_usr` ASC) VISIBLE,
  CONSTRAINT `fk_tb_order_has_tb_payment_type_tb_order1`
    FOREIGN KEY (`id_ord` , `id_csh_reg` , `id_usr`)
    REFERENCES $$$raw_schema$$$.`tb_order` (`id_ord` , `id_csh_reg` , `id_usr`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_order_has_tb_payment_type_tb_payment_type1`
    FOREIGN KEY (`id_pay_tp`)
    REFERENCES $$$raw_schema$$$.`tb_payment_type` (`id_pay_tp`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'tb_payment_type_id_pay_tp';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


