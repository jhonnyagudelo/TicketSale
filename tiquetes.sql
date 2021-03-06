PGDMP                         x            tiquetes    11.5    12.3 [    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    76452    tiquetes    DATABASE     �   CREATE DATABASE tiquetes WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Colombia.1252' LC_CTYPE = 'Spanish_Colombia.1252';
    DROP DATABASE tiquetes;
                postgres    false            �           1247    77389    category    TYPE     L   CREATE TYPE public.category AS ENUM (
    'Control',
    'Administrador'
);
    DROP TYPE public.category;
       public          postgres    false            �            1255    76453    _print(integer)    FUNCTION     �  CREATE FUNCTION public._print(id integer, OUT passenger integer, OUT company character varying, OUT name character varying, OUT license character varying, OUT buy date, OUT hour time without time zone) RETURNS SETOF record
    LANGUAGE sql
    AS $$
            SELECT 
            t_k.passenger
            ,t_k.company
            ,d_t.name AS DESTINATION
            ,v.license
            ,t_k.buy
            ,t_k.hour
                FROM tickets t_k
                    INNER JOIN ticket_details t_d
                        ON t_k.ticket_id = t_d.ticket
                    INNER JOIN vehicles v
                        ON v.vehicle_id = t_k.vehicle
                    INNER JOIN destinations d_t
                        ON d_t.destination_id = t_d.destination
                WHERE TRUE
                    AND v.internal_number = id
                    -- AND CURRENT_TIMESTAMP::TIMESTAMP <= t_k.created_at
                    ORDER BY t_k.ticket_id LIMIT 1;
        $$;
 �   DROP FUNCTION public._print(id integer, OUT passenger integer, OUT company character varying, OUT name character varying, OUT license character varying, OUT buy date, OUT hour time without time zone);
       public          postgres    false            �            1255    228141 	   _ticket()    FUNCTION       CREATE FUNCTION public._ticket(OUT buy character varying, OUT hour time without time zone, OUT company integer, OUT license character varying, OUT name character varying, OUT ticket_id integer, OUT conduce integer) RETURNS SETOF record
    LANGUAGE sql
    AS $$
SELECT  
        to_char(t_k.buy, 'dd/mm/yyyy'),
        t_k.hour,
        c.nit,
        v_h.license,
        d_t.name,
        t_k.ticket_id,
        t_k.conduce
FROM tickets t_k
    INNER JOIN vehicles v_h ON v_h.vehicle_id = t_k.vehicle
    INNER JOIN companies c ON v_h.company = c.company_id
    INNER JOIN destinations d_t ON t_k.destination = d_t.destination_id
WHERE TRUE
    AND CURRENT_DATE::TIMESTAMP <= t_k.created_at
ORDER BY t_k.ticket_id DESC
LIMIT 1;
$$;
 �   DROP FUNCTION public._ticket(OUT buy character varying, OUT hour time without time zone, OUT company integer, OUT license character varying, OUT name character varying, OUT ticket_id integer, OUT conduce integer);
       public          postgres    false            �            1255    77396    dateticket()    FUNCTION       CREATE FUNCTION public.dateticket(OUT company character varying, OUT destiny character varying) RETURNS SETOF record
    LANGUAGE sql
    AS $$
            SELECT
                c.name
                ,d_t.name
                    FROM tickets t_k
                        INNER JOIN vehicles v_h
                            ON v_h.vehicle_id = t_k.vehicle
                        INNER JOIN companies c
                            ON v_h.company = c.company_id
                        INNER JOIN destinations d_t
                            ON t_k.destination = d_t.destination_id
                    WHERE TRUE
                        AND CURRENT_DATE::TIMESTAMP <= t_k.created_at
                        ORDER BY t_k.ticket_id DESC LIMIT 1;
        $$;
 _   DROP FUNCTION public.dateticket(OUT company character varying, OUT destiny character varying);
       public          postgres    false            �            1255    77490    function_auditoria()    FUNCTION     �  CREATE FUNCTION public.function_auditoria() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
            DECLARE
               v_old_data TEXT;
               v_new_data TEXT;
            BEGIN
                IF(TG_OP = 'DELETE') THEN
                 v_old_data := ROW(OLD.*);
                    INSERT INTO auditoria_tickets(name_table, operation, old_data, new_data)
                        VALUES (TG_TABLE_NAME, TG_OP, v_old_data, NULL );
                    RETURN OLD;
                ELSEIF (TG_OP = 'UPDATE') THEN
                v_old_data = ROW(OLD.*);
                v_new_data = ROW(NEW.*);
                    INSERT INTO auditoria_tickets(name_table, operation, old_data, new_data)
                        VALUES (TG_TABLE_NAME, TG_OP, v_old_data, v_new_data );
                    RETURN NEW;
                ELSEIF (TG_OP = 'INSERT') THEN
                v_new_data = ROW(NEW.*);
                   INSERT INTO  auditoria_tickets(name_table, operation, old_data, new_data)
                        VALUES (TG_TABLE_NAME, TG_OP, NULL, v_new_data );
                    RETURN NEW;
                END IF;
                RETURN NULL;
            END;
        $$;
 +   DROP FUNCTION public.function_auditoria();
       public          postgres    false            �            1255    76455    getinfo(integer)    FUNCTION     �  CREATE FUNCTION public.getinfo(id integer, OUT internal_number integer, OUT license character varying, OUT capacity integer, OUT company character varying, OUT active boolean) RETURNS SETOF record
    LANGUAGE sql
    AS $$
            SELECT 
                v_h.internal_number
                ,v_h.license
                ,v_h.capacity
                ,cp.name
                ,v_h.active
            FROM vehicles v_h
                INNER JOIN companies cp
                    ON v_h.company = cp.company_id
                WHERE TRUE 
                    AND v_h.internal_number = id
                    ORDER BY v_h.vehicle_id;
        $$;
 �   DROP FUNCTION public.getinfo(id integer, OUT internal_number integer, OUT license character varying, OUT capacity integer, OUT company character varying, OUT active boolean);
       public          postgres    false            �            1255    76456 
   getinfot()    FUNCTION     �  CREATE FUNCTION public.getinfot(OUT internal_number integer, OUT license character varying, OUT capacity integer, OUT company character varying, OUT active boolean) RETURNS SETOF record
    LANGUAGE sql
    AS $$
            SELECT 
                v_h.internal_number
                ,v_h.license
                ,v_h.capacity
                ,cp.name
                ,v_h.active
            FROM vehicles v_h
                INNER JOIN companies cp
                    ON v_h.company = cp.company_id
                WHERE TRUE 		ORDER BY cp.company_id,v_h.vehicle_id;
        $$;
 �   DROP FUNCTION public.getinfot(OUT internal_number integer, OUT license character varying, OUT capacity integer, OUT company character varying, OUT active boolean);
       public          postgres    false            �            1255    77587    sale()    FUNCTION     �  CREATE FUNCTION public.sale(OUT id integer, OUT passenger integer, OUT origin character varying, OUT destiny character varying, OUT license character varying, OUT quantity integer, OUT buy date) RETURNS SETOF record
    LANGUAGE sql
    AS $$
            SELECT
            t_k.ticket_id
            ,t_k.passenger
            ,o_g.name
            ,d_t.name
            ,v.license
            ,t_k.quantity
            ,t_k.buy
                FROM tickets t_k
                    INNER JOIN vehicles v
                        ON v.vehicle_id = t_k.vehicle
                    INNER JOIN destinations d_t
                        ON d_t.destination_id = t_k.destination
                    INNER JOIN origins o_g
                        ON t_k.origin = o_g.origin_id
                WHERE TRUE
                AND CURRENT_DATE::TIMESTAMP <= t_k.created_at
                    ORDER BY t_k.ticket_id;
        $$;
 �   DROP FUNCTION public.sale(OUT id integer, OUT passenger integer, OUT origin character varying, OUT destiny character varying, OUT license character varying, OUT quantity integer, OUT buy date);
       public          postgres    false            �            1255    228142 <   ticket(integer, integer, integer, integer, integer, integer)    FUNCTION     H  CREATE FUNCTION public.ticket(identification_card integer, bus integer, origen integer, amount integer, destiny integer, conduce integer) RETURNS void
    LANGUAGE plpgsql
    AS $$ BEGIN
INSERT INTO tickets(
        company,
        passenger,
        origin,
        vehicle,
        quantity,
        destination,
        conduce
    )
SELECT c_p.name,
    identification_card,
    origen,
    v_c.vehicle_id,
    amount,
    destiny,
    conduce
FROM vehicles v_c
    INNER JOIN companies c_p ON v_c.company = c_p.company_id
WHERE TRUE
    AND v_c.internal_number = bus;
END;
$$;
 �   DROP FUNCTION public.ticket(identification_card integer, bus integer, origen integer, amount integer, destiny integer, conduce integer);
       public          postgres    false            �            1255    77419 (   ticket_registration(date, date, integer)    FUNCTION     �  CREATE FUNCTION public.ticket_registration(date, date, integer, OUT internal_number integer, OUT name character varying, OUT identification_card integer, OUT total_tickets integer, OUT buy date) RETURNS SETOF record
    LANGUAGE sql
    AS $_$
            WITH _data(data_start, data_end, data_bus) AS (
                VALUES ($1, $2, $3)
            )
            , data_dia AS (
                SELECT 
                    v_h.internal_number
                    ,d_t.name
                    ,t_k.passenger
                    ,t_k.quantity
                    ,t_k.buy
                FROM _data d
                    INNER JOIN vehicles v_h
                        ON d.data_bus = v_h.internal_number
                    INNER JOIN tickets t_k
                        ON t_k.vehicle = v_h.vehicle_id
                    INNER JOIN destinations d_t
                        ON d_t.destination_id = t_k.destination
                WHERE TRUE
                    AND t_k.created_at::DATE BETWEEN d.data_start::DATE AND d.data_end::DATE
                    AND v_h.internal_number = d.data_bus
                    ORDER BY t_k.buy, d_t.name
                    
            )
                SELECT 
                    d_b.internal_number
                    ,d_b.name
                    ,d_b.passenger
                    ,d_b.quantity
                    ,d_b.buy
                FROM data_dia d_b
        $_$;
 �   DROP FUNCTION public.ticket_registration(date, date, integer, OUT internal_number integer, OUT name character varying, OUT identification_card integer, OUT total_tickets integer, OUT buy date);
       public          postgres    false            �            1255    77552    totalsale()    FUNCTION     ,  CREATE FUNCTION public.totalsale(OUT passenger integer, OUT origin character varying, OUT destiny character varying, OUT license character varying, OUT quantity integer, OUT buy date) RETURNS SETOF record
    LANGUAGE sql
    AS $$
            SELECT
            t_k.passenger
            ,o_g.name
            ,d_t.name
            ,v.license
            ,t_k.quantity
            ,t_k.buy
                FROM tickets t_k
                    INNER JOIN vehicles v
                        ON v.vehicle_id = t_k.vehicle
                    INNER JOIN destinations d_t
                        ON d_t.destination_id = t_k.destination
                    INNER JOIN origins o_g
                        ON t_k.origin = o_g.origin_id
                WHERE TRUE
                    ORDER BY t_k.ticket_id;
        $$;
 �   DROP FUNCTION public.totalsale(OUT passenger integer, OUT origin character varying, OUT destiny character varying, OUT license character varying, OUT quantity integer, OUT buy date);
       public          postgres    false            �            1255    77430 #   travel_history(date, date, integer)    FUNCTION     n  CREATE FUNCTION public.travel_history(date, date, integer, OUT internal_number integer, OUT name character varying, OUT total_tickets bigint, OUT buy date) RETURNS SETOF record
    LANGUAGE sql
    AS $_$
            WITH _data(data_start, data_end, data_bus) AS (
                VALUES ($1, $2, $3)
            )
            , data_dia AS (
                SELECT
                    v_h.internal_number
                    ,d_t.name
                    ,t_k.quantity
                    ,t_k.buy
                FROM _data d
                    INNER JOIN vehicles v_h
                        ON d.data_bus = v_h.internal_number
                    INNER JOIN tickets t_k
                        ON t_k.vehicle = v_h.vehicle_id
                    INNER JOIN destinations d_t
                        ON d_t.destination_id = t_k.destination
                WHERE TRUE
                    AND t_k.created_at::DATE BETWEEN d.data_start AND d.data_end
                    AND v_h.internal_number = d.data_bus
                    ORDER BY d_t.name, t_k.buy
            )
                SELECT
                    d_b.internal_number
                    ,d_b.name
                    ,SUM(d_b.quantity) total_tickets
                    ,d_b.buy
                FROM data_dia d_b
                GROUP BY d_b.name, d_b.internal_number,d_b.buy
                ORDER BY d_b.buy, d_b.name;
        $_$;
 �   DROP FUNCTION public.travel_history(date, date, integer, OUT internal_number integer, OUT name character varying, OUT total_tickets bigint, OUT buy date);
       public          postgres    false            �            1255    77585 %   travel_historyto(date, date, integer)    FUNCTION     �  CREATE FUNCTION public.travel_historyto(date, date, integer, OUT internal_number integer, OUT name character varying, OUT total_tickets bigint, OUT buy date) RETURNS SETOF record
    LANGUAGE sql
    AS $_$
            WITH _data(data_start, data_end, company) AS (
                VALUES ($1, $2, $3)
            )
           , data_dia AS (
                SELECT
                    v_h.internal_number
                    ,d_t.name
                    ,t_k.quantity
                    ,t_k.buy
                FROM _data d
                    INNER JOIN companies c
                        ON c.company_id = $3
                    LEFT JOIN vehicles v_h
                        ON c.company_id = v_h.company
                    INNER JOIN tickets t_k
                        ON  t_k.vehicle = v_h.vehicle_id
                    INNER JOIN destinations d_t
                        ON d_t.destination_id = t_k.destination
                WHERE TRUE
                    AND t_k.created_at::DATE BETWEEN d.data_start::DATE AND d.data_end::DATE
                    ORDER BY d_t.name, t_k.buy
            )
                SELECT
                    d_b.internal_number
                    ,d_b.name
                    ,SUM(d_b.quantity)as total_tickets
                    ,d_b.buy
                FROM data_dia d_b
                GROUP BY  d_b.name, d_b.internal_number, d_b.buy  
                ORDER BY d_b.buy, d_b.name;
        $_$;
 �   DROP FUNCTION public.travel_historyto(date, date, integer, OUT internal_number integer, OUT name character varying, OUT total_tickets bigint, OUT buy date);
       public          postgres    false            �            1255    77439    update_at_modified()    FUNCTION       CREATE FUNCTION public.update_at_modified() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
            BEGIN
                IF(TG_OP = "UPDATE") THEN 
                    NEW.updated_at = now();
                END IF;
                RETURN NEW;
            END;
        $$;
 +   DROP FUNCTION public.update_at_modified();
       public          postgres    false            �            1255    76459     update_details(integer, integer)    FUNCTION     I  CREATE FUNCTION public.update_details(destiny integer, pasajero integer) RETURNS void
    LANGUAGE plpgsql
    AS $$
            /*  
            * Author: Jhonny Stiven Agudelo Tenorio
            * Purpose: trigger tiempo
            * statement in PostgreSQL.
            */
            BEGIN 
            UPDATE ticket_details
                SET destination = destiny,
                    quantity = pasajero
                FROM tickets t_k 
                WHERE TRUE
                    AND CURRENT_DATE::TIMESTAMP <= t_k.created_at;
            END;
        $$;
 H   DROP FUNCTION public.update_details(destiny integer, pasajero integer);
       public          postgres    false            �            1255    76460    vehicle_d(integer)    FUNCTION     �  CREATE FUNCTION public.vehicle_d(integer, OUT company character varying, OUT quantity integer, OUT license character varying, OUT internal_number integer, OUT destiny character varying, OUT _date date, OUT hour time without time zone) RETURNS SETOF record
    LANGUAGE sql
    AS $_$
            /* empresa, pasajeros,placa,vehiculo,dia,hora*/
                SELECT 
                    c_n.name
                    ,t_d.quantity
                    ,v_c.license
                    ,v_c.internal_number
                    ,d_t.name
                    ,t_k.buy
                    ,t_k.hour
                    FROM destinations d_t
                        INNER JOIN ticket_details t_d
                            ON d_t.destination_id = t_d.destination
                        INNER JOIN tickets t_k
                            ON t_k.ticket_id = t_d.ticket
                        INNER JOIN vehicles v_c
                            ON t_k.vehicle = v_c.vehicle_id
                        INNER JOIN companies c_n
                            ON c_n.company_id = v_c.company
                    WHERE TRUE
                        AND v_c.internal_number = $1
                    ORDER BY t_k.ticket_id;
        $_$;
 �   DROP FUNCTION public.vehicle_d(integer, OUT company character varying, OUT quantity integer, OUT license character varying, OUT internal_number integer, OUT destiny character varying, OUT _date date, OUT hour time without time zone);
       public          postgres    false            �            1255    76461    vehicle_date()    FUNCTION     �  CREATE FUNCTION public.vehicle_date(OUT company character varying, OUT quantity integer, OUT license character varying, OUT internal_number integer, OUT destiny character varying, OUT _date date, OUT hour time without time zone) RETURNS SETOF record
    LANGUAGE sql
    AS $$
            /* empresa, pasajeros,placa,vehiculo,dia,hora*/
                SELECT 
                    c_n.name
                    ,t_d.quantity
                    ,v_c.license
                    ,v_c.internal_number
                    ,d_t.name
                    ,t_k.buy
                    ,t_k.hour
                    FROM destinations d_t
                        INNER JOIN ticket_details t_d
                            ON d_t.destination_id = t_d.destination
                        INNER JOIN tickets t_k
                            ON t_k.ticket_id = t_d.ticket
                        INNER JOIN vehicles v_c
                            ON t_k.vehicle = v_c.vehicle_id
                        INNER JOIN companies c_n
                            ON c_n.company_id = v_c.company
                    WHERE TRUE
                    ORDER BY t_k.ticket_id;
        $$;
 �   DROP FUNCTION public.vehicle_date(OUT company character varying, OUT quantity integer, OUT license character varying, OUT internal_number integer, OUT destiny character varying, OUT _date date, OUT hour time without time zone);
       public          postgres    false            �            1259    77540    auditoria_tickets    TABLE       CREATE TABLE public.auditoria_tickets (
    auditoria_id integer NOT NULL,
    name_table character varying(100) NOT NULL,
    operation character varying(100) NOT NULL,
    old_data text,
    new_data text,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
 %   DROP TABLE public.auditoria_tickets;
       public            postgres    false            �            1259    77538 "   auditoria_tickets_auditoria_id_seq    SEQUENCE     �   CREATE SEQUENCE public.auditoria_tickets_auditoria_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 9   DROP SEQUENCE public.auditoria_tickets_auditoria_id_seq;
       public          postgres    false    211            �           0    0 "   auditoria_tickets_auditoria_id_seq    SEQUENCE OWNED BY     i   ALTER SEQUENCE public.auditoria_tickets_auditoria_id_seq OWNED BY public.auditoria_tickets.auditoria_id;
          public          postgres    false    210            �            1259    76462 	   companies    TABLE     �  CREATE TABLE public.companies (
    company_id integer NOT NULL,
    name character varying(50) NOT NULL,
    nit integer NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    deleted_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT companies_nit_check CHECK (((nit >= 7) OR (nit <= 12)))
);
    DROP TABLE public.companies;
       public            postgres    false            �            1259    76469    companies_company_id_seq    SEQUENCE     �   CREATE SEQUENCE public.companies_company_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.companies_company_id_seq;
       public          postgres    false    196            �           0    0    companies_company_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.companies_company_id_seq OWNED BY public.companies.company_id;
          public          postgres    false    197            �            1259    76471    controls    TABLE     f  CREATE TABLE public.controls (
    control_id integer NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    deleted_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
    DROP TABLE public.controls;
       public            postgres    false            �            1259    76477    controls_control_id_seq    SEQUENCE     �   CREATE SEQUENCE public.controls_control_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.controls_control_id_seq;
       public          postgres    false    198            �           0    0    controls_control_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.controls_control_id_seq OWNED BY public.controls.control_id;
          public          postgres    false    199            �            1259    76479    destinations    TABLE     G  CREATE TABLE public.destinations (
    destination_id integer NOT NULL,
    name character varying(80) NOT NULL,
    destination_code integer NOT NULL,
    active boolean DEFAULT true,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
     DROP TABLE public.destinations;
       public            postgres    false            �            1259    76485    destinations_destination_id_seq    SEQUENCE     �   CREATE SEQUENCE public.destinations_destination_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.destinations_destination_id_seq;
       public          postgres    false    200            �           0    0    destinations_destination_id_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.destinations_destination_id_seq OWNED BY public.destinations.destination_id;
          public          postgres    false    201            �            1259    76487    origins    TABLE       CREATE TABLE public.origins (
    origin_id integer NOT NULL,
    name character varying(80) NOT NULL,
    active boolean DEFAULT true,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
    DROP TABLE public.origins;
       public            postgres    false            �            1259    76493    origins_origin_id_seq    SEQUENCE     �   CREATE SEQUENCE public.origins_origin_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.origins_origin_id_seq;
       public          postgres    false    202            �           0    0    origins_origin_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.origins_origin_id_seq OWNED BY public.origins.origin_id;
          public          postgres    false    203            �            1259    76503    tickets    TABLE     H  CREATE TABLE public.tickets (
    ticket_id integer NOT NULL,
    company character varying(50),
    passenger integer NOT NULL,
    origin integer NOT NULL,
    buy date DEFAULT CURRENT_DATE NOT NULL,
    hour time without time zone DEFAULT CURRENT_TIME NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    deleted_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    vehicle integer,
    quantity integer,
    destination integer NOT NULL,
    conduce integer NOT NULL
);
    DROP TABLE public.tickets;
       public            postgres    false            �            1259    76511    tickets_ticket_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tickets_ticket_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.tickets_ticket_id_seq;
       public          postgres    false    204            �           0    0    tickets_ticket_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.tickets_ticket_id_seq OWNED BY public.tickets.ticket_id;
          public          postgres    false    205            �            1259    76520    users    TABLE     z  CREATE TABLE public.users (
    user_id integer NOT NULL,
    names character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    password character varying NOT NULL,
    company integer NOT NULL,
    status boolean DEFAULT true,
    last_session character varying DEFAULT CURRENT_TIMESTAMP,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    deleted_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    username character varying(50) NOT NULL,
    type_category public.category NOT NULL,
    control integer
);
    DROP TABLE public.users;
       public            postgres    false    648            �            1259    76531    users_user_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.users_user_id_seq;
       public          postgres    false    206            �           0    0    users_user_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;
          public          postgres    false    207            �            1259    76533    vehicles    TABLE     }  CREATE TABLE public.vehicles (
    vehicle_id integer NOT NULL,
    internal_number integer NOT NULL,
    license character varying(6) NOT NULL,
    capacity integer NOT NULL,
    company integer NOT NULL,
    active boolean DEFAULT true,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
    DROP TABLE public.vehicles;
       public            postgres    false            �            1259    76539    vehicles_vehicle_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vehicles_vehicle_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.vehicles_vehicle_id_seq;
       public          postgres    false    208            �           0    0    vehicles_vehicle_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.vehicles_vehicle_id_seq OWNED BY public.vehicles.vehicle_id;
          public          postgres    false    209            �
           2604    77543    auditoria_tickets auditoria_id    DEFAULT     �   ALTER TABLE ONLY public.auditoria_tickets ALTER COLUMN auditoria_id SET DEFAULT nextval('public.auditoria_tickets_auditoria_id_seq'::regclass);
 M   ALTER TABLE public.auditoria_tickets ALTER COLUMN auditoria_id DROP DEFAULT;
       public          postgres    false    210    211    211            �
           2604    76541    companies company_id    DEFAULT     |   ALTER TABLE ONLY public.companies ALTER COLUMN company_id SET DEFAULT nextval('public.companies_company_id_seq'::regclass);
 C   ALTER TABLE public.companies ALTER COLUMN company_id DROP DEFAULT;
       public          postgres    false    197    196            �
           2604    76542    controls control_id    DEFAULT     z   ALTER TABLE ONLY public.controls ALTER COLUMN control_id SET DEFAULT nextval('public.controls_control_id_seq'::regclass);
 B   ALTER TABLE public.controls ALTER COLUMN control_id DROP DEFAULT;
       public          postgres    false    199    198            �
           2604    76543    destinations destination_id    DEFAULT     �   ALTER TABLE ONLY public.destinations ALTER COLUMN destination_id SET DEFAULT nextval('public.destinations_destination_id_seq'::regclass);
 J   ALTER TABLE public.destinations ALTER COLUMN destination_id DROP DEFAULT;
       public          postgres    false    201    200            �
           2604    76544    origins origin_id    DEFAULT     v   ALTER TABLE ONLY public.origins ALTER COLUMN origin_id SET DEFAULT nextval('public.origins_origin_id_seq'::regclass);
 @   ALTER TABLE public.origins ALTER COLUMN origin_id DROP DEFAULT;
       public          postgres    false    203    202            �
           2604    76546    tickets ticket_id    DEFAULT     v   ALTER TABLE ONLY public.tickets ALTER COLUMN ticket_id SET DEFAULT nextval('public.tickets_ticket_id_seq'::regclass);
 @   ALTER TABLE public.tickets ALTER COLUMN ticket_id DROP DEFAULT;
       public          postgres    false    205    204            �
           2604    76548    users user_id    DEFAULT     n   ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);
 <   ALTER TABLE public.users ALTER COLUMN user_id DROP DEFAULT;
       public          postgres    false    207    206            �
           2604    76549    vehicles vehicle_id    DEFAULT     z   ALTER TABLE ONLY public.vehicles ALTER COLUMN vehicle_id SET DEFAULT nextval('public.vehicles_vehicle_id_seq'::regclass);
 B   ALTER TABLE public.vehicles ALTER COLUMN vehicle_id DROP DEFAULT;
       public          postgres    false    209    208            �          0    77540    auditoria_tickets 
   TABLE DATA           p   COPY public.auditoria_tickets (auditoria_id, name_table, operation, old_data, new_data, created_at) FROM stdin;
    public          postgres    false    211   �       y          0    76462 	   companies 
   TABLE DATA           ^   COPY public.companies (company_id, name, nit, created_at, updated_at, deleted_at) FROM stdin;
    public          postgres    false    196   t�       {          0    76471    controls 
   TABLE DATA           ^   COPY public.controls (control_id, code, name, created_at, updated_at, deleted_at) FROM stdin;
    public          postgres    false    198   �       }          0    76479    destinations 
   TABLE DATA           n   COPY public.destinations (destination_id, name, destination_code, active, created_at, updated_at) FROM stdin;
    public          postgres    false    200   ��                 0    76487    origins 
   TABLE DATA           R   COPY public.origins (origin_id, name, active, created_at, updated_at) FROM stdin;
    public          postgres    false    202   >�       �          0    76503    tickets 
   TABLE DATA           �   COPY public.tickets (ticket_id, company, passenger, origin, buy, hour, created_at, updated_at, deleted_at, vehicle, quantity, destination, conduce) FROM stdin;
    public          postgres    false    204   ��       �          0    76520    users 
   TABLE DATA           �   COPY public.users (user_id, names, last_name, password, company, status, last_session, created_at, updated_at, deleted_at, username, type_category, control) FROM stdin;
    public          postgres    false    206   ��       �          0    76533    vehicles 
   TABLE DATA           {   COPY public.vehicles (vehicle_id, internal_number, license, capacity, company, active, created_at, updated_at) FROM stdin;
    public          postgres    false    208   p�       �           0    0 "   auditoria_tickets_auditoria_id_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('public.auditoria_tickets_auditoria_id_seq', 58, true);
          public          postgres    false    210            �           0    0    companies_company_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.companies_company_id_seq', 2, true);
          public          postgres    false    197            �           0    0    controls_control_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.controls_control_id_seq', 8, true);
          public          postgres    false    199            �           0    0    destinations_destination_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.destinations_destination_id_seq', 9, true);
          public          postgres    false    201            �           0    0    origins_origin_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.origins_origin_id_seq', 3, true);
          public          postgres    false    203            �           0    0    tickets_ticket_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.tickets_ticket_id_seq', 51, true);
          public          postgres    false    205            �           0    0    users_user_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.users_user_id_seq', 40, true);
          public          postgres    false    207            �           0    0    vehicles_vehicle_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.vehicles_vehicle_id_seq', 63, true);
          public          postgres    false    209            �
           2606    77549 (   auditoria_tickets auditoria_tickets_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public.auditoria_tickets
    ADD CONSTRAINT auditoria_tickets_pkey PRIMARY KEY (auditoria_id);
 R   ALTER TABLE ONLY public.auditoria_tickets DROP CONSTRAINT auditoria_tickets_pkey;
       public            postgres    false    211            �
           2606    76551    companies companies_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.companies
    ADD CONSTRAINT companies_pkey PRIMARY KEY (company_id);
 B   ALTER TABLE ONLY public.companies DROP CONSTRAINT companies_pkey;
       public            postgres    false    196            �
           2606    76553    controls controls_code_key 
   CONSTRAINT     U   ALTER TABLE ONLY public.controls
    ADD CONSTRAINT controls_code_key UNIQUE (code);
 D   ALTER TABLE ONLY public.controls DROP CONSTRAINT controls_code_key;
       public            postgres    false    198            �
           2606    76555    controls controls_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.controls
    ADD CONSTRAINT controls_pkey PRIMARY KEY (control_id);
 @   ALTER TABLE ONLY public.controls DROP CONSTRAINT controls_pkey;
       public            postgres    false    198            �
           2606    76557    destinations destinations_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.destinations
    ADD CONSTRAINT destinations_pkey PRIMARY KEY (destination_id);
 H   ALTER TABLE ONLY public.destinations DROP CONSTRAINT destinations_pkey;
       public            postgres    false    200            �
           2606    76559    origins origins_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.origins
    ADD CONSTRAINT origins_pkey PRIMARY KEY (origin_id);
 >   ALTER TABLE ONLY public.origins DROP CONSTRAINT origins_pkey;
       public            postgres    false    202            �
           2606    76563    tickets tickets_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_pkey PRIMARY KEY (ticket_id);
 >   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_pkey;
       public            postgres    false    204            �
           2606    84890    users unique_username 
   CONSTRAINT     T   ALTER TABLE ONLY public.users
    ADD CONSTRAINT unique_username UNIQUE (username);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT unique_username;
       public            postgres    false    206            �
           2606    76567    users users_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    206            �
           2606    77432    vehicles vehicles_license_key 
   CONSTRAINT     [   ALTER TABLE ONLY public.vehicles
    ADD CONSTRAINT vehicles_license_key UNIQUE (license);
 G   ALTER TABLE ONLY public.vehicles DROP CONSTRAINT vehicles_license_key;
       public            postgres    false    208            �
           2606    76571    vehicles vehicles_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.vehicles
    ADD CONSTRAINT vehicles_pkey PRIMARY KEY (vehicle_id);
 @   ALTER TABLE ONLY public.vehicles DROP CONSTRAINT vehicles_pkey;
       public            postgres    false    208            �
           1259    84888 
   u_username    INDEX     G   CREATE UNIQUE INDEX u_username ON public.users USING btree (username);
    DROP INDEX public.u_username;
       public            postgres    false    206            �
           2620    77491 "   tickets tbl_categorical_attributes    TRIGGER     �   CREATE TRIGGER tbl_categorical_attributes AFTER INSERT OR DELETE OR UPDATE ON public.tickets FOR EACH ROW EXECUTE PROCEDURE public.function_auditoria();
 ;   DROP TRIGGER tbl_categorical_attributes ON public.tickets;
       public          postgres    false    204    232            �
           2620    77440    vehicles update_bus    TRIGGER     �   CREATE TRIGGER update_bus BEFORE UPDATE ON public.vehicles FOR EACH ROW WHEN ((old.updated_at IS DISTINCT FROM new.updated_at)) EXECUTE PROCEDURE public.update_at_modified();
 ,   DROP TRIGGER update_bus ON public.vehicles;
       public          postgres    false    234    208    208            �
           2620    77591    tickets update_tickets    TRIGGER     �   CREATE TRIGGER update_tickets BEFORE UPDATE ON public.tickets FOR EACH ROW WHEN ((old.updated_at IS DISTINCT FROM new.updated_at)) EXECUTE PROCEDURE public.update_at_modified();
 /   DROP TRIGGER update_tickets ON public.tickets;
       public          postgres    false    204    234    204            �
           2620    77592    users update_tickets    TRIGGER     �   CREATE TRIGGER update_tickets BEFORE UPDATE ON public.users FOR EACH ROW WHEN ((old.updated_at IS DISTINCT FROM new.updated_at)) EXECUTE PROCEDURE public.update_at_modified();
 -   DROP TRIGGER update_tickets ON public.users;
       public          postgres    false    206    234    206            �
           2606    76613    tickets destiny_ticket    FK CONSTRAINT     �   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT destiny_ticket FOREIGN KEY (destination) REFERENCES public.destinations(destination_id);
 @   ALTER TABLE ONLY public.tickets DROP CONSTRAINT destiny_ticket;
       public          postgres    false    200    204    2790            �
           2606    84883    users fk_control_user    FK CONSTRAINT        ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_control_user FOREIGN KEY (control) REFERENCES public.controls(control_id);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT fk_control_user;
       public          postgres    false    206    198    2788            �
           2606    76583    tickets tickets_origin_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_origin_fkey FOREIGN KEY (origin) REFERENCES public.origins(origin_id);
 E   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_origin_fkey;
       public          postgres    false    2792    204    202            �
           2606    76588    tickets tickets_vehicle_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_vehicle_fkey FOREIGN KEY (vehicle) REFERENCES public.vehicles(vehicle_id);
 F   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_vehicle_fkey;
       public          postgres    false    208    204    2803            �
           2606    76603    users users_company_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_company_fkey FOREIGN KEY (company) REFERENCES public.companies(company_id);
 B   ALTER TABLE ONLY public.users DROP CONSTRAINT users_company_fkey;
       public          postgres    false    2784    196    206            �
           2606    76608    vehicles vehicles_company_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.vehicles
    ADD CONSTRAINT vehicles_company_fkey FOREIGN KEY (company) REFERENCES public.companies(company_id);
 H   ALTER TABLE ONLY public.vehicles DROP CONSTRAINT vehicles_company_fkey;
       public          postgres    false    2784    196    208            �   b  x�͚Ɏ�6E�ͯ���j�o�]/F{�M#6#�x����]U�Z�B��>���KRp���_�|�����W��z����_>~��ۗ�O_�?���㗇�  �X,��B��G>�83�6Lb%�?�m��q��S��ݻ�w�*�����M.F�`����7��|��_ lr�i�v��K 7TڸC�@��n��P��C�^[�a�$���i��$�
�Mz�$�<4����>����Vi��s@8N�c�䬵���k0��4���q�����G�ڸC�G˲��&��N#iN��)O����m��Ą&M���f��>���u����Û��Yp
�ӟrUh�r���*jLl�v�� ,�J	�`g�3�5����Pi�1h1pT�ۄ����Ě��a
���Q�)���0�a�ЂI�g࠽�po1w48�����8������b8��Z�Vc��<�5t�~7�7�)M�8���%�6��-T��2ڦH�� sjv�&	i�,�����6�ˌv�q3B<�`E�i}L���Ń#�8M��~��F�ʜV�`I�_!�,��Œ�C��;�>�h�v�}"�bNy*4m�_��z�\��j8�bi0��T�)��4�u��s�[�+8Q������(h5���n���F�����87�����H7�ù�����oq��8p�I�
�7���5�	��ʃ���8K�\�N���Rw��3µ�̓�[}8<C�� )KgE���GQ��:
�&��Z"��:u?��P�X ���I�%�e;���j�,�q�x>�Z+1���m�T�s�J; �)�N�\�/<���{�4�$z�r�����b�T���� ^OX� �fN���aa���b=��C�;�IP8�0cL3��FWaXJ�5ubS�j�`xL�K�Dv�c��Ԕ��k���P� �O<@t\���N�*4m�_\!*j5s �����ĥW%�W�i���
QQc��'��Dbg��Z�Q��RS�7�m���P�Ҁp[ }$��9")_��M��+DE��fD zN���v��ba}|f�i���
QQc�y}@��(�$�<����Rw�-�e��fP ���'�Ko
��W�i���
QQc���m"x��"7��E�*m�!�D��p3.ж��6
iOl1ϡh�S�F@C��;DE�j73m�OD�c��T�
M��W�����@��!�-U0 ��	\ ��RS~7�u��5��q�{�,��la��X"/}-�q�x��]�1܌�-.d���_�);/ڡ��b��t��f\�������6�����BӦ�����H3.p���η�fK��x��6TڸC\t�V��fV���P I޵�˃ӓ�6�d��q��l�V��fV����QzS���#,����RS�7�G"W�5F�Y����1)����)H��c�p\˗ڸC�ǿ�y��'�V��f``�=�NX3�\ROO,5�|����+�Te��fh�/��T�Z�i�i#)rٔgm��q��������y�m�i&��႕���S
v1���Rw�Ec9�\�Z#�!�D!m��=�E1	a���X[+5�{��X�R#�![b�	��T�\N�N�,5�}��1q�YYk�MƘ�N�,a      y   e   x�3�)J�+.��L�/Vp*MOT�s䴰4�4067��420��54�56T04�26�2�г0��0%S�ː�9??%�d�B@bNnfQ"�>c���g]� �O2B      {   �   x����
�0���S�Z�iZ;*���4�@�H�,>��;�n�ໃ#.v��3�KRЮ"Q5�I��j���:�$$N�M6*h��fc2�۴�^A��pa擉�l��Bc��f���su���.�h��
�0/1x���/ہt-E��C�^3�>n���      }   �   x���M
� ��x�^ ��'jvD0M�dz�ޟ����>�{��dp��_PÀr��@3�����Ά?'��Me˕�c�d;�3u��@\k�����gC�I�j_G#ߙ�6��zs�>UJ�:�v<���w� ՋJc|�l�3
!~ �}�         D   x�3�	�	u�,�420��54�56T04�24�24�33673��#�e���N�vcNgGO����qqq ��%�      �   �   x���Ij1E��)t��K�.��2��`H������Ĵ2��B���_%��0����a������;�D� P���@� ����r�\(�Ax9Ģ�Q�9\�16�\��u��jt�+Q����k��4�YM���>h6��EE����iCn*��j՛�.hQ�y�
��5���K�kˀF ���v���,u���1ݟ߆���~jn�{f7Z�@󗐫�K�1~����      �   �   x����j�0���S�$[V�������Q��^z�ۯ�v)�r������ﵔO8��t�T���9?(�0N9F=�y�<��ȏ�@��C�G������oF�|G�PK���N���8l{�X[����j|���r�
/�.%�Q'�#I��>������N4ղ�z!x�9 FP�m�ݝ�Ag{"�\����~��?t'����-��c� �Ly<      �   �  x���K�A���S�i�Ye�.�0È "������0(�j�������&`������W��8����Ƹ�0����ST,�{�)ۣ�ы�
uOP��H�g�,��*1h�U��wT3��@�;���`b���^����2���w�VRtX\,��O�5<I�<㺊旭�4YBp����\�aY�./?�2�k���c
���L�ZybZz������t�̷���Fz�,������Y�W�f�|�z�]Ҍ�|������*��c��Xs��קS�#�H&������IE���:�}1���$����|;�7����X��1ޭ:�8n(�⚋���Ej��mN�������M�L|�z+-y^s�8�Ŋ7��#�}c��WC��I��uY��%��b     