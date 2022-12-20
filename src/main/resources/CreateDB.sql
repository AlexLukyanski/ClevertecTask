CREATE DATABASE "TestTaskClevertec"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE TABLE public.product
(
    p_id integer NOT NULL,
    p_name character varying,
    p_price numeric,
    p_type character varying,
    p_discount boolean,
    PRIMARY KEY (p_id)
);

ALTER TABLE IF EXISTS public.product
    OWNER to postgres;

CREATE TABLE public.discount_card
(
    dc_id integer NOT NULL,
    dc_number integer,
    dc_amount integer,
    PRIMARY KEY (dc_id)
);

ALTER TABLE IF EXISTS public.discount_card
    OWNER to postgres;