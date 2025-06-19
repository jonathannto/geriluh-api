-- -----------------------------------------------------
-- Table geriluhbd.tb_order
-- Insert initial orders
-- Author: Jonathan Nascimento
-- Date: 2024-12-12
-- -----------------------------------------------------

-- CAIXA 1
-- Ordem 1
INSERT INTO tb_order (
    status_ord, date_ord, tab_num_ord, total_prc_ord, paymt_sts_ord,
    end_date_ord, notes_ord, id_csh_reg, id_usr, id_pay
) VALUES (
    'closed', '2024-12-03 08:15:00', 1, 150.00, 'paid',
    '2024-12-03 10:00:00', 'Café da manhã completo.', 1, 1, 1
);

-- Ordem 2
INSERT INTO tb_order (
    status_ord, date_ord, tab_num_ord, total_prc_ord, paymt_sts_ord,
    end_date_ord, notes_ord, id_csh_reg, id_usr, id_pay
) VALUES (
    'closed', '2024-12-03 12:30:00', 2, 200.58, 'paid',
    '2024-12-03 14:00:00', 'Almoço para duas pessoas.', 1, 1, 2
);

-- Ordem 3
INSERT INTO tb_order (
    status_ord, date_ord, tab_num_ord, total_prc_ord, paymt_sts_ord,
    end_date_ord, notes_ord, id_csh_reg, id_usr, id_pay
) VALUES (
    'closed', '2024-12-03 16:00:00', 3, 50.00, 'paid',
    '2024-12-03 16:45:00', 'Lanche da tarde.', 1, 1, 3
);

-- CAIXA 2
-- Ordem 1
INSERT INTO tb_order (
    status_ord, date_ord, tab_num_ord, total_prc_ord, paymt_sts_ord,
    end_date_ord, notes_ord, id_csh_reg, id_usr, id_pay
) VALUES (
    'closed', '2024-12-04 09:00:00', 1, 300.00, 'paid',
    '2024-12-04 12:00:00', 'Café da manhã para grupo.', 2, 1, 4
);

-- Ordem 2
INSERT INTO tb_order (
    status_ord, date_ord, tab_num_ord, total_prc_ord, paymt_sts_ord,
    end_date_ord, notes_ord, id_csh_reg, id_usr, id_pay
) VALUES (
    'closed', '2024-12-04 13:00:00', 2, 200.00, 'paid',
    '2024-12-04 15:00:00', 'Almoço executivo.', 2, 1, 5
);

-- Ordem 3
INSERT INTO tb_order (
    status_ord, date_ord, tab_num_ord, total_prc_ord, paymt_sts_ord,
    end_date_ord, notes_ord, id_csh_reg, id_usr, id_pay
) VALUES (
    'closed', '2024-12-04 15:30:00', 3, 100.00, 'paid',
    '2024-12-04 16:00:00', 'Lanche leve.', 2, 1, 6
);

-- CAIXA 3
-- Ordem 1
INSERT INTO tb_order (
    status_ord, date_ord, tab_num_ord, total_prc_ord, paymt_sts_ord,
    end_date_ord, notes_ord, id_csh_reg, id_usr, id_pay
) VALUES (
    'open', '2024-12-05 09:00:00', 1, 150.00, 'unpaid',
    NULL, 'Café da manhã grande.', 3, 1, 7
);

-- Ordem 2
INSERT INTO tb_order (
    status_ord, date_ord, tab_num_ord, total_prc_ord, paymt_sts_ord,
    end_date_ord, notes_ord, id_csh_reg, id_usr, id_pay
) VALUES (
    'open', '2024-12-05 11:30:00', 2, 204.99, 'unpaid',
    NULL, 'Almoço em andamento.', 3, 1, 8
);