-- -----------------------------------------------------
-- Table geriluhbd.tb_payment_type
-- Insert payment types
-- Author: Jonathan Nascimento
-- Date: 2025-01-13
-- -----------------------------------------------------
INSERT INTO tb_payment_type (
    name_pay_tp,
    desc_pay_tp
) VALUES
    ('Cash', 'Physical money used for transactions.'),
    ('PIX', 'Instant payment system widely used in Brazil.'),
    ('Credit Card', 'Payment made using a credit card.'),
    ('Debit Card', 'Payment made using a debit card.');