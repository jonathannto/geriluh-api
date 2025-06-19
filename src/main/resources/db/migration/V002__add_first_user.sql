-- -----------------------------------------------------
-- Table geriluhbd.tb_user
-- Insert new user admin
-- Author: Jonathan Nascimento
-- Date: 2024-11-12
-- -----------------------------------------------------
INSERT INTO tb_user (
  nam_usr,
  cpf_usr,
  eml_usr,
  ph_num_usr,
  str_add_usr,
  add_num_usr,
  city_usr,
  sta_usr,
  zip_code_usr,
  birth_date_usr,
  user_nam_usr,
  pass_usr,
  sec_quest_usr,
  sec_answ_usr,
  user_type_usr,
  crt_at_dat_usr,
  sts_usr,
  addit_notes_usr
) VALUES (
  'Rafaela Jennifer Aparício',
  '02569481190',
  'rafaela-aparicio96@freitasprior.com.br',
  '81995300016',
  'São Vicente de Paulo',
  134,
  'Vitória de Santo Antão',
  'PE',
  '55606290',
  '1995-05-26',
  'rafaela.aparicio',
  'FtQrfX1dlF',
  'My first cat',
  'snow bell',
  'Admin',
  '2024-11-12 00:00:00',
  'Active',
  'First user admin privileges'
);