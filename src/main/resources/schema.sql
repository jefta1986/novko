
-- Drop all sequences

-- DROP SEQUENCE public.t_carts_id_seq;
-- DROP SEQUENCE public.t_categories_id_seq;
-- DROP SEQUENCE public.t_orders_id_seq;
-- DROP SEQUENCE public.t_products_id_seq;
-- DROP SEQUENCE public.t_subcategories_id_seq;
-- DROP SEQUENCE public.t_users_id_seq;



-- Drop all tables

DROP TABLE IF EXISTS public.t_carts CASCADE;

DROP TABLE IF EXISTS public.t_categories CASCADE;

--DROP TABLE IF EXISTS public.t_customers CASCADE;

DROP TABLE IF EXISTS public.t_images CASCADE;

DROP TABLE IF EXISTS public.t_orders CASCADE;

DROP TABLE IF EXISTS public.t_products CASCADE;

DROP TABLE IF EXISTS public.t_subcategories CASCADE;

--DROP TABLE IF EXISTS public.t_transactions CASCADE;

DROP TABLE IF EXISTS public.t_users CASCADE;


---- SEQUENCERS DDL
---- public.t_carts_id_seq definition
--
---- DROP SEQUENCE public.t_carts_id_seq;
--
--CREATE SEQUENCE public.t_carts_id_seq
--	INCREMENT BY 1
--	MINVALUE 1
--	MAXVALUE 9223372036854775807
--	START 1
--	CACHE 1
--	CYCLE;
--
--
---- public.t_categories_id_seq definition
--
---- DROP SEQUENCE public.t_categories_id_seq;
--
--CREATE SEQUENCE public.t_categories_id_seq
--	INCREMENT BY 1
--	MINVALUE 1
--	MAXVALUE 9223372036854775807
--	START 1
--	CACHE 1
--	CYCLE;
--
--
---- public.t_orders_id_seq definition
--
---- DROP SEQUENCE public.t_orders_id_seq;
--
--CREATE SEQUENCE public.t_orders_id_seq
--	INCREMENT BY 1
--	MINVALUE 1
--	MAXVALUE 9223372036854775807
--	START 1
--	CACHE 1
--	CYCLE;
--
--
---- public.t_products_id_seq definition
--
---- DROP SEQUENCE public.t_products_id_seq;
--
--CREATE SEQUENCE public.t_products_id_seq
--	INCREMENT BY 1
--	MINVALUE 1
--	MAXVALUE 9223372036854775807
--	START 1
--	CACHE 1
--	CYCLE;
--
--
---- public.t_subcategories_id_seq definition
--
---- DROP SEQUENCE public.t_subcategories_id_seq;
--
--CREATE SEQUENCE public.t_subcategories_id_seq
--	INCREMENT BY 1
--	MINVALUE 1
--	MAXVALUE 9223372036854775807
--	START 1
--	CACHE 1
--	CYCLE;
--
--
---- public.t_users_id_seq definition
--
---- DROP SEQUENCE public.t_users_id_seq;
--
--CREATE SEQUENCE public.t_users_id_seq
--	INCREMENT BY 1
--	MINVALUE 1
--	MAXVALUE 9223372036854775807
--	START 1
--	CACHE 1
--	CYCLE;
---- END SEQUENCES DDL




-- public.t_categories definition

-- Drop table

-- DROP TABLE public.t_categories;

CREATE TABLE public.t_categories (
	id bigserial NOT NULL,
	"name" varchar(255) NULL,
	"name_sr" varchar(255) NULL,
	CONSTRAINT t_categories_pkey PRIMARY KEY (id),
	CONSTRAINT uk_categories_name UNIQUE (name),
	CONSTRAINT uk_categories_name_sr UNIQUE (name_sr)
);


-- public.t_customers definition

-- Drop table

-- DROP TABLE public.t_customers;

--CREATE TABLE public.t_customers (
--	id bigserial NOT NULL,
--	email varchar(255) NULL,
--	"name" varchar(255) NULL,
--	phone_number varchar(255) NULL,
--	CONSTRAINT t_customers_pkey PRIMARY KEY (id),
--	CONSTRAINT uk_email UNIQUE (email)
--);


-- public.t_transactions definition

-- Drop table

-- DROP TABLE public.t_transactions;

--CREATE TABLE public.t_transactions (
--	id bigserial NOT NULL,
--	order_id int8 NULL,
--	product_amount_din int4 NULL,
--	product_amount_euro int4 NULL,
--	product_name varchar(255) NULL,
--	product_quantity int4 NULL,
--	product_code varchar(255) NULL,
--	rabat float8 NULL,
--	total_amount_din int4 NULL,
--	total_amount_euro int4 NULL,
--	CONSTRAINT t_transactions_pkey PRIMARY KEY (id)
--);


-- public.t_users definition

-- Drop table

-- DROP TABLE public.t_users;

CREATE TABLE public.t_users (
	id bigserial NOT NULL,
	active bool NULL,
	code varchar(255) NULL,
	firma varchar(255) NULL,
	grad varchar(255) NULL,
	"language" varchar(255) NULL,
	mb varchar(255) NULL,
	"password" varchar(255) NULL,
	pib varchar(255) NULL,
	rabat float8 NOT NULL,
	"role" varchar(255) NULL,
	ulica varchar(255) NULL,
	username varchar(255) NULL,
	CONSTRAINT t_users_pkey PRIMARY KEY (id)
);


-- public.t_orders definition

-- Drop table

-- DROP TABLE public.t_orders;

CREATE TABLE public.t_orders (
	id bigserial NOT NULL,
	order_date timestamp NULL,
	quantity int4 NULL,
	status bool NULL,
	total_amount_din int4 NULL,
	total_amount_euro int4 NULL,
	user_id int8 NULL,
	CONSTRAINT t_orders_pkey PRIMARY KEY (id),
	CONSTRAINT fk_orders_users FOREIGN KEY (user_id) REFERENCES t_users(id)
);


-- public.t_subcategories definition

-- Drop table

-- DROP TABLE public.t_subcategories;

CREATE TABLE public.t_subcategories (
	id bigserial NOT NULL,
	"name" varchar(255) NULL,
    "name_sr" varchar(255) NULL,
	categories_id int8 NULL,
	CONSTRAINT t_subcategories_pkey PRIMARY KEY (id),
	CONSTRAINT uk_subcategories_name UNIQUE (name),
	CONSTRAINT uk_subcategories_name_sr UNIQUE (name_sr),
	CONSTRAINT fk_subcategories_categories FOREIGN KEY (categories_id) REFERENCES t_categories(id)
);


-- public.t_products definition

-- Drop table

-- DROP TABLE public.t_products;
--CREATE TABLE public.t_products (
--	id bigserial NOT NULL DEFAULT nextval('t_products_id_seq'::regclass),
--	amount_din int4 NULL,
--	amount_euro int4 NULL,
--	brand varchar(255) NULL,
--	code varchar(255) NULL,
--	description text NULL,
--	description_sr text NULL,
--	enabled bool NULL,
--	name varchar(255) NULL,
--	quantity int4 NULL,
--	subcategories_id int8 NULL,
--	CONSTRAINT t_products_pkey PRIMARY KEY (id),
--	CONSTRAINT uk_bijlumenpkpv8p6mu5a5ava8r UNIQUE (name),
--	CONSTRAINT uk_sy0uso8bbqn31udr97crd6176 UNIQUE (code)
--);



CREATE TABLE public.t_products (
	id bigserial NOT NULL,
	amount_din int4 NULL,
	amount_euro int4 NULL,
	code varchar(255) NULL,
	brand varchar(100) NULL,
	description text NULL,
	description_sr text NULL,
	enabled bool NULL,
	"name" varchar(255) NULL,
	quantity int4 NULL,
	subcategories_id int8 NULL,
	CONSTRAINT t_products_pkey PRIMARY KEY (id),
	CONSTRAINT uk_products_name UNIQUE (name),
	CONSTRAINT uk_products_code UNIQUE (code),
	CONSTRAINT fk_product_subcategories FOREIGN KEY (subcategories_id) REFERENCES t_subcategories(id)
);


CREATE TABLE public.t_images (
	id int8 NOT NULL,
	image varchar(255) NULL
);


-- public.t_images foreign keys

ALTER TABLE public.t_images ADD CONSTRAINT fk_product_images FOREIGN KEY (id) REFERENCES t_products(id);


-- public.t_carts definition

-- Drop table

-- DROP TABLE public.t_carts;

CREATE TABLE public.t_carts (
	id bigserial NOT NULL,
	amount_din int4 NULL,
	amount_euro int4 NULL,
	quantity int4 NULL,
	orders_id int8 NULL,
	products_id int8 NULL,
	CONSTRAINT t_carts_pkey PRIMARY KEY (id),
	CONSTRAINT fk_carts_products FOREIGN KEY (products_id) REFERENCES t_products(id),
	CONSTRAINT fk_carts_orders FOREIGN KEY (orders_id) REFERENCES t_orders(id)
);


-- public.t_images definition

-- Drop table

-- DROP TABLE public.t_images;

--CREATE TABLE public.t_images (
--	id bigserial NOT NULL,
--	"data" bytea NULL,
--	default_picture bool NULL,
--	"name" varchar(255) NULL,
--	"type" varchar(255) NULL,
--	product_id int8 NULL,
--	CONSTRAINT t_images_pkey PRIMARY KEY (id),
--	CONSTRAINT fk7xyo46d5skrdxh3mmsywe9dli FOREIGN KEY (product_id) REFERENCES t_products(id)
--);