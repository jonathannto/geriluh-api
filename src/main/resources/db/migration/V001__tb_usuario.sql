CREATE TABLE tb_user (
    id_usr INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Unique identifier for the user, primary key / Identificador único do usuário, chave primária',
    nam_usr VARCHAR(255) NOT NULL COMMENT 'Full name of the user / Nome completo do usuário',
    cpf_usr VARCHAR(11) NOT NULL COMMENT 'User\'s CPF / CPF do usuário',
    eml_usr VARCHAR(255) NOT NULL UNIQUE COMMENT 'User\'s email, unique / Email do usuário, único',
    ph_num_usr VARCHAR(20) COMMENT 'User\'s phone number / Telefone do usuário',
    str_add_usr VARCHAR(255) COMMENT 'User\'s street address / Rua do endereço do usuário',
    add_num_usr INT COMMENT 'User\'s address number / Número do endereço do usuário',
    city_usr VARCHAR(100) COMMENT 'User\'s city / Cidade do endereço do usuário',
    sta_usr VARCHAR(50) COMMENT 'User\'s state / Estado do endereço do usuário',
    zip_code_usr VARCHAR(20) COMMENT 'User\'s zip code / CEP do endereço do usuário',
    birth_date_usr DATE COMMENT 'User\'s date of birth / Data de nascimento do usuário',
    user_nam_usr VARCHAR(100) NOT NULL UNIQUE COMMENT 'Username, unique / Nome de usuário, único',
    pass_usr VARCHAR(255) NOT NULL COMMENT 'User\'s password / Senha do usuário',
    sec_quest_usr VARCHAR(255) COMMENT 'User\'s security question / Pergunta de segurança do usuário',
    sec_answ_usr VARCHAR(255) COMMENT 'User\'s security answer / Resposta da pergunta de segurança',
    user_type_usr VARCHAR(50) COMMENT 'User type (e.g., administrator, manager, attendant) / Tipo de usuário (ex.: administrador, gerente, atendente)',
    crt_at_dat_usr TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Record creation date, default is current date/time / Data de criação do registro, padrão é a data/hora atual',
    sts_usr VARCHAR(20) DEFAULT 'active' COMMENT 'User status, default is "active" / Status do usuário, padrão é "ativo"',
    pro_pic_user LONGBLOB COMMENT 'User\'s profile picture / Foto do perfil do usuário',
    addit_notes_usr TEXT COMMENT 'Additional notes about the user / Notas adicionais sobre o usuário'
);

