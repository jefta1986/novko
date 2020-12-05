INSERT INTO t_users (active, code, "language", "password", rabat, "role", username) VALUES(true, 'u1', 'EN', '$2a$10$F04qVGaEQiNx3sgG5wzsJ.6DsB9ZeXzEp4NNeus5mdD5jVzDo7GCO', 0.5, 'ROLE_USER', 'user');
INSERT INTO t_users (active, code, "language", "password", rabat, "role", username) VALUES(true, 'u2', 'EN', '$2a$10$spdkmRlxgqy4BCKvlmEABuWqkXGx4vsUkG/f/obaG/YRiNGJf/8oO', 0.5, 'ROLE_ADMIN', 'admin');
INSERT INTO t_categories ("name_sr", "name") VALUES('obuca', 'shoes');
INSERT INTO t_categories ("name_sr", "name") VALUES('automobili', 'cars');
INSERT INTO t_subcategories ("name_sr", "name" ,categories_id) VALUES('patike', 'sneakers', 1);
INSERT INTO t_subcategories ("name_sr", "name" ,categories_id) VALUES('cizme', 'boots', 1);
INSERT INTO t_subcategories ("name_sr", "name" ,categories_id) VALUES('elektricni', 'electric', 2);
INSERT INTO t_subcategories ("name_sr", "name" ,categories_id) VALUES('benzin', 'benz', 2);
INSERT INTO t_subcategories ("name_sr", "name" ,categories_id) VALUES('dizel', 'diesel', 2);
INSERT INTO t_products (amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES(10000, 100, 'shoes1', 'nike', 'Opis proizvoda nike...' ,'Some description for product...', true, 'air max 2', 5, 1);
INSERT INTO t_products (amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES(10000, 100, 'shoes2', 'adidas', 'Opis proizvoda adidas...' ,'Some description for product...', true, 'gazelle', 5, 1);
INSERT INTO t_products (amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES(10000, 100, 'shoes3', 'puma', 'Opis proizvoda puma...' ,'Some description for product...', true, 'prism', 5, 1);
INSERT INTO t_products (amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES(10000, 100, 'shoes4', 'timberland', 'Opis proizvoda timberland...' ,'Some description for product...', true, 'kanadjanke', 5, 2);
INSERT INTO t_products (amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES(10000, 100, 'car1', 'tesla', 'Opis automobila tesla...' ,'Some description for car...', true, 'model y', 2, 3);
INSERT INTO t_products (amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES(10000, 100, 'car2', 'bmw', 'Opis automobila bmw...' ,'Some description for car...', true, 'm5', 2, 4);
INSERT INTO t_products (amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES(10000, 100, 'car3', 'mercedes', 'Opis automobila mercedes...' ,'Some description for car...', true, 'slk', 2, 4);
INSERT INTO t_products (amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES(10000, 100, 'car4', 'vw', 'Opis automobila vw...' ,'Some description for car...', true, 'golf', 2, 5);
INSERT INTO t_products (amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES(10000, 100, 'car5', 'ford', 'Opis automobila ford...' ,'Some description for car...', true, 'mustang', 2, 5);

