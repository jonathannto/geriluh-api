-- -----------------------------------------------------
-- Table `geriluhbd`.`tb_dish`
-- Insert all dishes of the menu
-- Author: Jonathan Nascimento
-- Date: 2024-12-07
-- -----------------------------------------------------

INSERT INTO `tb_dish` (`dish_nam_dsh`, `dish_prc_dsh`, `desc_dsh`, `cat_dsh`, `has_add_dsh`, `pic_dsh`) VALUES
-- Cuscuz
('Só Cuscuz', 4.00, 'Simples porção de cuscuz', 'Cuscuz', 'n', NULL),
('Cuscuz com manteiga', 5.00, 'Cuscuz acompanhado de manteiga', 'Cuscuz', 'n', NULL),
('Cuscuz com leite', 7.00, 'Cuscuz servido com leite', 'Cuscuz', 'n', NULL),
('Cuscuz com queijo', 8.00, 'Cuscuz servido com queijo', 'Cuscuz', 'n', NULL),
('Cuscuz com ovo e queijo', 9.00, 'Cuscuz servido com ovo e queijo', 'Cuscuz', 'n', NULL),
('Cuscuz com salsicha e queijo', 10.00, 'Cuscuz servido com salsicha e queijo', 'Cuscuz', 'n', NULL),
('Cuscuz com presunto e queijo', 11.00, 'Cuscuz servido com presunto e queijo', 'Cuscuz', 'n', NULL),
('Cuscuz com frango e queijo', 14.00, 'Cuscuz servido com frango e queijo', 'Cuscuz', 'n', NULL),
('Cuscuz com carne e queijo', 14.00, 'Cuscuz servido com carne e queijo', 'Cuscuz', 'n', NULL),
('Cuscuz com calabresa, cebola e queijo', 15.00, 'Cuscuz servido com calabresa, cebola e queijo', 'Cuscuz', 'n', NULL),
('Cuscuz combo presunto, frango, carne, ovo e queijo', 25.00, 'Combo especial com presunto, frango, carne, ovo e queijo', 'Cuscuz', 'n', NULL),

-- Tapioca
('Só tapioca', 3.00, 'Simples porção de tapioca', 'Tapioca', 'n', NULL),
('Tapioca com manteiga', 5.00, 'Tapioca acompanhada de manteiga', 'Tapioca', 'n', NULL),
('Tapioca com ovo', 6.50, 'Tapioca servida com ovo', 'Tapioca', 'n', NULL),
('Tapioca com queijo', 8.00, 'Tapioca servida com queijo', 'Tapioca', 'n', NULL),
('Tapioca com ovo e queijo', 10.00, 'Tapioca servida com ovo e queijo', 'Tapioca', 'n', NULL),
('Tapioca com presunto e queijo', 11.00, 'Tapioca servida com presunto e queijo', 'Tapioca', 'n', NULL),
('Tapioca com frango e queijo', 14.00, 'Tapioca servida com frango e queijo', 'Tapioca', 'n', NULL),
('Tapioca com carne e queijo', 14.00, 'Tapioca servida com carne e queijo', 'Tapioca', 'n', NULL),
('Tapioca com calabresa e cebola', 15.00, 'Tapioca servida com calabresa e cebola', 'Tapioca', 'n', NULL),

-- Panqueca
('Panqueca com queijo e catupiry', 10.00, 'Panqueca recheada com queijo e catupiry', 'Panqueca', 'n', NULL),
('Panqueca com presunto, queijo e catupiry', 12.00, 'Panqueca recheada com presunto, queijo e catupiry', 'Panqueca', 'n', NULL),
('Panqueca com carne, queijo e catupiry', 14.00, 'Panqueca recheada com carne, queijo e catupiry', 'Panqueca', 'n', NULL),
('Panqueca com frango, queijo e catupiry', 14.00, 'Panqueca recheada com frango, queijo e catupiry', 'Panqueca', 'n', NULL),
('Panqueca com calabresa, queijo e catupiry', 15.00, 'Panqueca recheada com calabresa, queijo e catupiry', 'Panqueca', 'n', NULL),

-- Omelete
('Só omelete', 8.00, 'Simples porção de omelete', 'Omelete', 'n', NULL),
('Omelete com queijo', 10.00, 'Omelete servido com queijo', 'Omelete', 'n', NULL),
('Omelete com presunto e queijo', 12.00, 'Omelete servido com presunto e queijo', 'Omelete', 'n', NULL),
('Omelete com frango e queijo', 15.00, 'Omelete servido com frango e queijo', 'Omelete', 'n', NULL),
('Omelete com carne e queijo', 15.00, 'Omelete servido com carne e queijo', 'Omelete', 'n', NULL),
('Omelete com calabresa, cebola e queijo', 16.00, 'Omelete servido com calabresa, cebola e queijo', 'Omelete', 'n', NULL),

-- Pão
('Pão com manteiga', 2.50, 'Pão servido com manteiga', 'Pão', 'n', NULL),
('Pão com ovo', 4.00, 'Pão servido com ovo', 'Pão', 'n', NULL),
('Pão com queijo', 5.00, 'Pão servido com queijo', 'Pão', 'n', NULL),
('Misto simples', 5.00, 'Pão recheado com presunto e queijo', 'Pão', 'n', NULL),
('Misto completo', 6.00, 'Pão recheado com presunto, queijo e manteiga', 'Pão', 'n', NULL),
('Pão com ovo e queijo', 8.00, 'Pão servido com ovo e queijo', 'Pão', 'n', NULL),
('Pão com carne e queijo', 10.00, 'Pão servido com carne e queijo', 'Pão', 'n', NULL),
('Pão com frango e queijo', 10.00, 'Pão servido com frango e queijo', 'Pão', 'n', NULL),
('Pão com calabresa e queijo', 10.00, 'Pão servido com calabresa e queijo', 'Pão', 'n', NULL),

-- Café
('Cafezinho', 1.00, 'Simples cafezinho', 'Café', 'n', NULL),
('Pingado', 2.00, 'Café com leite', 'Café', 'n', NULL),
('Café cremoso', 4.00, 'Café com textura cremosa', 'Café', 'n', NULL),
('Leite com toddy', 3.00, 'Leite servido com achocolatado Toddy', 'Café', 'n', NULL);