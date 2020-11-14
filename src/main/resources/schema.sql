-- Drop all tables

DROP TABLE IF EXISTS public.t_carts CASCADE;

DROP TABLE IF EXISTS public.t_categories CASCADE;

DROP TABLE IF EXISTS public.t_customers CASCADE;

DROP TABLE IF EXISTS public.t_images CASCADE;

DROP TABLE IF EXISTS public.t_orders CASCADE;

DROP TABLE IF EXISTS public.t_products CASCADE;

DROP TABLE IF EXISTS public.t_subcategories CASCADE;

DROP TABLE IF EXISTS public.t_transactions CASCADE;

DROP TABLE IF EXISTS public.t_users CASCADE;


-- public.t_categories definition

-- Drop table

-- DROP TABLE public.t_categories;

CREATE TABLE public.t_categories (
	id bigserial NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT t_categories_pkey PRIMARY KEY (id),
	CONSTRAINT uk_i7f3jyrhdxjtcmsx5ded5fwqd UNIQUE (name)
);


-- public.t_customers definition

-- Drop table

-- DROP TABLE public.t_customers;

CREATE TABLE public.t_customers (
	id bigserial NOT NULL,
	email varchar(255) NULL,
	"name" varchar(255) NULL,
	phone_number varchar(255) NULL,
	CONSTRAINT t_customers_pkey PRIMARY KEY (id),
	CONSTRAINT uk_hp9w0v2wywotvunwvpja17ib UNIQUE (email)
);


-- public.t_transactions definition

-- Drop table

-- DROP TABLE public.t_transactions;

CREATE TABLE public.t_transactions (
	id bigserial NOT NULL,
	order_id int8 NULL,
	product_amount_din int4 NULL,
	product_amount_euro int4 NULL,
	product_name varchar(255) NULL,
	product_quantity int4 NULL,
	product_code varchar(255) NULL,
	rabat float8 NULL,
	total_amount_din int4 NULL,
	total_amount_euro int4 NULL,
	CONSTRAINT t_transactions_pkey PRIMARY KEY (id)
);


-- public.t_users definition

-- Drop table

-- DROP TABLE public.t_users;

CREATE TABLE public.t_users (
	id bigserial NOT NULL,
	active bool NULL,
	code varchar(255) NULL,
	"language" varchar(255) NULL,
	"password" varchar(255) NULL,
	rabat float8 NULL,
	"role" varchar(255) NULL,
	username varchar(255) NULL,
	CONSTRAINT t_users_pkey PRIMARY KEY (id)
);


-- public.t_orders definition

-- Drop table

-- DROP TABLE public.t_orders;

CREATE TABLE public.t_orders (
	id bigserial NOT NULL,
	address varchar(255) NULL,
	city varchar(255) NULL,
	country varchar(255) NULL,
	description text NULL,
	"name" varchar(255) NULL,
	order_date timestamp NULL,
	phone_number varchar(255) NULL,
	postal_code varchar(255) NULL,
	quantity int4 NULL,
	status bool NULL,
	surname varchar(255) NULL,
	total_amount_din int4 NULL,
	total_amount_euro int4 NULL,
	user_id int8 NULL,
	CONSTRAINT t_orders_pkey PRIMARY KEY (id),
	CONSTRAINT fk9bpidc7dnv6aifph80x035hli FOREIGN KEY (user_id) REFERENCES t_users(id)
);


-- public.t_subcategories definition

-- Drop table

-- DROP TABLE public.t_subcategories;

CREATE TABLE public.t_subcategories (
	id bigserial NOT NULL,
	"name" varchar(255) NULL,
	categories_id int8 NULL,
	CONSTRAINT t_subcategories_pkey PRIMARY KEY (id),
	CONSTRAINT uk_jik768j2y55tebaaej4x4dg54 UNIQUE (name),
	CONSTRAINT fk90i0jhnb2sfh82e2ihcio9h78 FOREIGN KEY (categories_id) REFERENCES t_categories(id)
);


-- public.t_products definition

-- Drop table

-- DROP TABLE public.t_products;

CREATE TABLE public.t_products (
	id bigserial NOT NULL,
	amount_din int4 NULL,
	amount_euro int4 NULL,
	code varchar(255) NULL,
	brand varchar(255) NULL,
	description text NULL,
	enabled bool NULL,
	"name" varchar(255) NULL,
	quantity int4 NULL,
	subcategories_id int8 NULL,
	CONSTRAINT t_products_pkey PRIMARY KEY (id),
	CONSTRAINT uk_bijlumenpkpv8p6mu5a5ava8r UNIQUE (name),
	CONSTRAINT uk_sy0uso8bbqn31udr97crd6176 UNIQUE (code),
	CONSTRAINT fk2w360ta23xnvuguhchr21v51a FOREIGN KEY (subcategories_id) REFERENCES t_subcategories(id)
);


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
	CONSTRAINT fk39am6d0kuv4jahspovuripflk FOREIGN KEY (products_id) REFERENCES t_products(id),
	CONSTRAINT fknbkr10bopt6v6g5x1v0uexglf FOREIGN KEY (orders_id) REFERENCES t_orders(id)
);


-- public.t_images definition

-- Drop table

-- DROP TABLE public.t_images;

CREATE TABLE public.t_images (
	id bigserial NOT NULL,
	"data" bytea NULL,
	default_picture bool NULL,
	"name" varchar(255) NULL,
	"type" varchar(255) NULL,
	product_id int8 NULL,
	CONSTRAINT t_images_pkey PRIMARY KEY (id),
	CONSTRAINT fk7xyo46d5skrdxh3mmsywe9dli FOREIGN KEY (product_id) REFERENCES t_products(id)
);