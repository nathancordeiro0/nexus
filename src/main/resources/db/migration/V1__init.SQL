CREATE SEQUENCE sq_order_number
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE public.tb_user (
    id uuid NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    "password" varchar(255) NOT NULL,
    "role" varchar(255) NOT NULL,
    created_at timestamptz(6) NOT NULL,
    updated_at timestamptz(6) NOT NULL,
    CONSTRAINT tb_user_email_key UNIQUE (email),
    CONSTRAINT tb_user_pkey PRIMARY KEY (id),
    CONSTRAINT tb_user_role_check CHECK (
        ((role)::text = ANY (
            (ARRAY[
                'CUSTOMER'::character varying,
                'PRODUCER'::character varying,
                'ADMIN'::character varying
            ])::text[]
        ))
    )
);

CREATE TABLE public.tb_event (
    id uuid NOT NULL,
    producer_id uuid NOT NULL,
    "name" varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    venue varchar(255) NOT NULL,
    status varchar(255) NOT NULL,
    event_date timestamptz(6) NOT NULL,
    created_at timestamptz(6) NOT NULL,
    updated_at timestamptz(6) NOT NULL,
    CONSTRAINT tb_event_pkey PRIMARY KEY (id),
    CONSTRAINT tb_event_status_check CHECK (
        ((status)::text = ANY (
            (ARRAY[
                'DRAFT'::character varying,
                'PUBLISHED'::character varying,
                'CANCELED'::character varying,
                'FINISHED'::character varying
            ])::text[]
        ))
    )
);

CREATE TABLE public.tb_ticket (
    id uuid NOT NULL,
    event_id uuid NOT NULL,
    "name" varchar(255) NOT NULL,
    price numeric(38, 2) NOT NULL,
    "type" varchar(255) NOT NULL,
    total_quantity int4 NOT NULL,
    sold_quantity int4 NULL DEFAULT 0,
    created_at timestamptz(6) NOT NULL,
    updated_at timestamptz(6) NOT NULL,
    CONSTRAINT tb_ticket_pkey PRIMARY KEY (id),
    CONSTRAINT tb_ticket_type_check CHECK (
        ((type)::text = ANY (
            (ARRAY[
                'GENERAL_ADMISSION'::character varying,
                'VIP'::character varying,
                'VIP_LOUNGE'::character varying
        ])::text[]
        ))
    )
);

CREATE TABLE public.tb_order (
    id uuid NOT NULL,
    customer_id uuid NULL,
    number int8 NOT NULL DEFAULT nextval('sq_order_number'),
    status varchar(255) NULL,
    created_at timestamptz(6) NOT NULL,
    updated_at timestamptz(6) NOT NULL,
    CONSTRAINT tb_order_order_number_key UNIQUE (number),
    CONSTRAINT tb_order_pkey PRIMARY KEY (id),
    CONSTRAINT tb_order_status_check CHECK (
        ((status)::text = ANY (
            (ARRAY[
                'PENDING'::character varying,
                'PAID'::character varying,
                'CANCELED'::character varying,
                'REFUNDED'::character varying
            ])::text[]
        ))
    )
);

CREATE TABLE public.tb_order_item (
    id uuid NOT NULL,
    order_id uuid NOT NULL,
    ticket_id uuid NOT NULL,
    quantity int4 NOT NULL,
    created_at timestamptz(6) NOT NULL,
    updated_at timestamptz(6) NOT NULL,
    CONSTRAINT tb_order_item_pkey PRIMARY KEY (id)
);

ALTER TABLE public.tb_event
    ADD CONSTRAINT fk_event_user
        FOREIGN KEY (producer_id)
            REFERENCES public.tb_user(id);

ALTER TABLE public.tb_ticket
    ADD CONSTRAINT fk_ticket_event
        FOREIGN KEY (event_id)
            REFERENCES public.tb_event(id);

ALTER TABLE public.tb_order
    ADD CONSTRAINT fk_order_user
        FOREIGN KEY (customer_id)
            REFERENCES public.tb_user(id);

ALTER TABLE public.tb_order_item
    ADD CONSTRAINT fk_order_item_ticket
        FOREIGN KEY (ticket_id)
            REFERENCES public.tb_ticket(id);