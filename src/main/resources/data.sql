INSERT INTO t_users (active, code, "language", "password", rabat, "role", username) VALUES(true, 'u1', 'EN', '$2a$10$F04qVGaEQiNx3sgG5wzsJ.6DsB9ZeXzEp4NNeus5mdD5jVzDo7GCO', 0.5, 'ROLE_USER', 'user');
INSERT INTO t_users (active, code, "language", "password", rabat, "role", username) VALUES(true, 'u2', 'EN', '$2a$10$spdkmRlxgqy4BCKvlmEABuWqkXGx4vsUkG/f/obaG/YRiNGJf/8oO', 0.5, 'ROLE_ADMIN', 'admin');
INSERT INTO t_categories ("name") VALUES('kategorija');
INSERT INTO t_subcategories ("name", categories_id) VALUES('podkategorija', 1);
INSERT INTO t_products (amount_din, amount_euro, code, brand, description, enabled, "name", quantity, subcategories_id) VALUES(50999, 430, 'code1', 'stihl', 'Some description for product. The best product ever made!', true, 'proizvod1', 5, 1);
INSERT INTO t_products (amount_din, amount_euro, code, brand, description, enabled, "name", quantity, subcategories_id) VALUES(119000, 1000, 'code2', 'stihl', 'Some description for product. The best product ever made!', true, 'proizvod2', 12, 1);
INSERT INTO t_products (amount_din, amount_euro, code, brand, description, enabled, "name", quantity, subcategories_id) VALUES(14000, 120, 'code3', 'stihl', 'Some description for product. The best product ever made!', true, 'proizvod3', 2, 1);
INSERT INTO t_products (amount_din, amount_euro, code, brand, description, enabled, "name", quantity, subcategories_id) VALUES(600000, 600, 'code4', 'stihl', 'Some description for product. The best product ever made!', true, 'proizvod4', 4, 1);


--INSERT INTO t_carts (amount_din, amount_euro, quantity, orders_id, products_id) VALUES(0, 0, 0, 0, 0);
--INSERT INTO t_categories ("name") VALUES('');
--INSERT INTO t_customers (email, "name", phone_number) VALUES('', '', '');
--INSERT INTO t_images ("data", default_picture, "name", "type", product_id) VALUES(?, false, '', '', 0);
--INSERT INTO t_orders (address, city, country, description, "name", order_date, phone_number, postal_code, quantity, status, surname, total_amount_din, total_amount_euro, user_id) VALUES('', '', '', '', '', '', '', '', 0, false, '', 0, 0, 0);
--INSERT INTO t_products (amount_din, amount_euro, code, description, enabled, "name", quantity, subcategories_id) VALUES(0, 0, '', '', false, '', 0, 0);
--INSERT INTO t_subcategories ("name", categories_id) VALUES('', 0);
--INSERT INTO t_transactions (order_id, product_amount_din, product_amount_euro, product_name, product_quantity, product_code, rabat, total_amount_din, total_amount_euro) VALUES(0, 0, 0, '', 0, '', 0, 0, 0);
--INSERT INTO t_users (active, code, "language", "password", rabat, "role", username) VALUES(false, '', '', '', 0, '', '');
