INSERT INTO t_users (active, code, firma, grad, "language", mb, "password", pib, rabat, "role", ulica, username) VALUES(true, 'u1', 'TICA MOTO' , '11420 Smederevska Palanka' , 'EN', '62908904' , '$2a$10$F04qVGaEQiNx3sgG5wzsJ.6DsB9ZeXzEp4NNeus5mdD5jVzDo7GCO', '107675136' , 0.5, 'ROLE_USER', 'Glibovac' , 'user@gmail.com');
INSERT INTO t_users (active, code, firma, grad, "language", mb, "password", pib, rabat, "role", ulica, username) VALUES(true, 'u2', 'TICA MOTO' , '11000 Beograd' , 'EN', '62908904' , '$2a$10$spdkmRlxgqy4BCKvlmEABuWqkXGx4vsUkG/f/obaG/YRiNGJf/8oO', '107675136' , 0.5, 'ROLE_ADMIN', 'Beograd', 'admin@gmail.com');
INSERT INTO t_users (active, code, firma, grad, "language", mb, "password", pib, rabat, "role", ulica, username) VALUES(true, 'user-code00001/2020', 'IT SERVICES' , '11300 Smederevo' , 'SR', '62908904' , '$2a$10$F04qVGaEQiNx3sgG5wzsJ.6DsB9ZeXzEp4NNeus5mdD5jVzDo7GCO', '107675136' , 0.5, 'ROLE_USER', 'Smederevo' , 'jefticivan0@gmail.com');
INSERT INTO t_users (active, code, firma, grad, "language", mb, "password", pib, rabat, "role", ulica, username) VALUES(true, 'user-code00002/2020', 'OUTSOURCE' , '11300 Smederevo' , 'EN', '123456' , '$2a$10$F04qVGaEQiNx3sgG5wzsJ.6DsB9ZeXzEp4NNeus5mdD5jVzDo7GCO', '107675136' , 0.3, 'ROLE_USER', 'Smederevo' , 'jefticivan@yahoo.com');
INSERT INTO t_categories ("name_sr", "name") VALUES('obuca', 'shoes');
INSERT INTO t_categories ("name_sr", "name") VALUES('automobili', 'cars');
INSERT INTO t_subcategories ("name_sr", "name" ,categories_id) VALUES('patike', 'sneakers', 1);
INSERT INTO t_subcategories ("name_sr", "name" ,categories_id) VALUES('cizme', 'boots', 1);
INSERT INTO t_subcategories ("name_sr", "name" ,categories_id) VALUES('elektricni', 'electric', 2);
INSERT INTO t_subcategories ("name_sr", "name" ,categories_id) VALUES('benzin', 'benz', 2);
INSERT INTO t_subcategories ("name_sr", "name" ,categories_id) VALUES('dizel', 'diesel', 2);
INSERT INTO t_products (created_date, amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES('2020-01-01 22:11:47.922', 14000, 120, 'shoes1', 'nike', 'Opis proizvoda nike...' ,'Some description for product...', true, 'air max 2 patike (best seller Nike product from 1990-today)', 25, 1);
INSERT INTO t_products (created_date, amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES('2020-11-17 22:11:47.922', 8000, 700, 'shoes2', 'adidas', 'Opis proizvoda adidas...' ,'Some description for product...', true, 'gazelle (best seller Adidas product from 1962-today)', 15, 1);
INSERT INTO t_products (created_date, amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES('2020-12-17 22:11:47.922', 6000, 50, 'shoes3', 'puma', 'Opis proizvoda puma...' ,'Some description for product...', true, 'prism', 7, 1);
INSERT INTO t_products (created_date, amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES('2020-12-15 22:11:47.922', 20000, 180, 'shoes4', 'timberland', 'Opis proizvoda timberland...' ,'Some description for product...', true, 'kanadjanke (best seller product from 1962-today)', 5, 2);
INSERT INTO t_products (created_date, amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES('2020-12-13 22:11:47.922', 5000, 40, 'shoes5', 'converse', 'Opis proizvoda converse...' ,'Some description for product...', true, 'all star (best seller product from 1962-today)', 5, 1);
INSERT INTO t_products (created_date, amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES('2020-12-17 22:11:47.922', 4000, 30, 'shoes6', 'reebok', 'Opis proizvoda reebok...' ,'Some description for product...', true, 'classic (best seller product from 1962-today)', 5, 1);
INSERT INTO t_products (created_date, amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES('2020-12-17 22:11:47.922', 16000, 150, 'shoes7', 'under armour', 'Opis proizvoda under armour...' ,'Some description for product...', true, 'hovr (best seller product from 1962-today)', 5, 1);
INSERT INTO t_products (created_date, amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES('2020-12-17 22:11:47.922', 10000, 100, 'shoes8', 'replay', 'Opis proizvoda replay...' ,'Some description for product...', true, 'replay-1 (best seller product from 1962-today)', 5, 1);
INSERT INTO t_products (created_date, amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES('2020-12-17 22:11:47.922', 10000, 100, 'shoes9', 'diesel', 'Opis proizvoda diesel...' ,'Some description for product...', true, 'diesel-1 (best seller product from 1962-today)', 5, 1);
INSERT INTO t_products (created_date, amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES('2020-12-17 22:11:47.922', 7000, 60, 'shoes10', 'new balance', 'Opis proizvoda new balance...' ,'Some description for product...', true, 'x Bodega (best seller product from 1962-today)', 5, 1);
INSERT INTO t_products (created_date, amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES('2021-12-17 22:11:47.922', 1000000, 100000, 'car1', 'tesla', 'Opis automobila tesla...' ,'Some description for car...', true, 'model y (best seller product from 1962-today)', 2, 3);
INSERT INTO t_products (created_date, amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES('2021-11-17 22:11:47.922', 500000, 50000, 'car2', 'bmw', 'Opis automobila bmw...' ,'Some description for car...', true, 'm5 (best seller product from 1962-today)', 2, 4);
INSERT INTO t_products (created_date, amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES('2020-12-17 22:11:47.922', 70000, 60000, 'car3', 'mercedes', 'Opis automobila mercedes...' ,'Some description for car...', true, 'slk (best seller product from 1962-today)', 2, 4);
INSERT INTO t_products (created_date, amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES('2021-12-15 22:11:47.922', 200000, 20000, 'car4', 'vw', 'Opis automobila vw...' ,'Some description for car...', true, 'golf (best seller product from 1962-today)', 2, 5);
INSERT INTO t_products (created_date, amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES('2020-12-17 22:11:47.922', 10000, 100, 'car5', 'ford', 'Opis automobila ford...' ,'Some description for car...', true, 'mustang (best seller product from 1962-today)', 2, 5);
INSERT INTO t_products (created_date, amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES('2020-12-17 22:11:47.922', 10000, 100, 'car6', 'opel', 'Opis automobila opel...' ,'Some description for car...', true, 'opel (best seller product from 1962-today)', 2, 5);
INSERT INTO t_products (created_date, amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES('2020-12-17 22:11:47.922', 10000, 100, 'car7', 'citroen', 'Opis automobila citroen...' ,'Some description for car...', true, 'citroen (best seller product from 1962-today)', 2, 5);
INSERT INTO t_products (created_date, amount_din, amount_euro, code, brand, description, description_sr, enabled, "name", quantity, subcategories_id) VALUES('2020-12-17 22:11:47.922', 10000, 100, 'car8', 'honda', 'Opis automobila honda...' ,'Some description for car...', true, 'honda (best seller product from 1962-today)', 2, 5);
--INSERT INTO t_images (id, image) VALUES(1, 'C:\images\1\airmax2.jpg');
--INSERT INTO t_images (id, image) VALUES(1, 'C:\images\1\drugaslika.jpg');
--INSERT INTO t_images (id, image) VALUES(1, 'C:\images\1\treca.jpg');
--INSERT INTO t_images (id, image) VALUES(1, 'C:\images\1\cetvrtasl.jpg');
--INSERT INTO t_images (id, image) VALUES(2, 'C:\images\2\adidas.jpg');
--INSERT INTO t_images (id, image) VALUES(2, 'C:\images\2\gazele.jpg');
INSERT INTO t_orders (order_date, status, total_amount_din, total_amount_euro, user_id) VALUES('2020-12-17 22:11:47.922', false, 50000, 500, 1);
INSERT INTO t_carts (amount_din, amount_euro, quantity, ukupno, pdv, orders_id, products_id) VALUES(20000, 200, 2, 30000, 18, 1, 1);
--INSERT INTO t_carts (amount_din, amount_euro, quantity, orders_id, products_id) VALUES(20000, 200, 2, 1, 2);
--INSERT INTO t_carts (amount_din, amount_euro, quantity, orders_id, products_id) VALUES(10000, 100, 1, 1, 3);
INSERT INTO t_orders (order_date, status, total_amount_din, total_amount_euro, user_id) VALUES('2021-12-17 22:11:47.922', true, 75000, 1000, 3);
INSERT INTO t_carts (amount_din, amount_euro, quantity, ukupno, pdv, orders_id, products_id) VALUES(15000, 135, 3, 45000, 18, 2, 2);
