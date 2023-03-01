PGDMP                          {            zfztqpco     13.9 (Ubuntu 13.9-1.pgdg20.04+1)    14.2 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    1668569    zfztqpco    DATABASE     ]   CREATE DATABASE zfztqpco WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.UTF-8';
    DROP DATABASE zfztqpco;
                zfztqpco    false            �           0    0    DATABASE zfztqpco    ACL     ;   REVOKE CONNECT,TEMPORARY ON DATABASE zfztqpco FROM PUBLIC;
                   zfztqpco    false    4258                        3079    17161 	   btree_gin 	   EXTENSION     =   CREATE EXTENSION IF NOT EXISTS btree_gin WITH SCHEMA public;
    DROP EXTENSION btree_gin;
                   false            �           0    0    EXTENSION btree_gin    COMMENT     R   COMMENT ON EXTENSION btree_gin IS 'support for indexing common datatypes in GIN';
                        false    15                        3079    17702 
   btree_gist 	   EXTENSION     >   CREATE EXTENSION IF NOT EXISTS btree_gist WITH SCHEMA public;
    DROP EXTENSION btree_gist;
                   false            �           0    0    EXTENSION btree_gist    COMMENT     T   COMMENT ON EXTENSION btree_gist IS 'support for indexing common datatypes in GiST';
                        false    19                        3079    16671    citext 	   EXTENSION     :   CREATE EXTENSION IF NOT EXISTS citext WITH SCHEMA public;
    DROP EXTENSION citext;
                   false            �           0    0    EXTENSION citext    COMMENT     S   COMMENT ON EXTENSION citext IS 'data type for case-insensitive character strings';
                        false    8                        3079    17599    cube 	   EXTENSION     8   CREATE EXTENSION IF NOT EXISTS cube WITH SCHEMA public;
    DROP EXTENSION cube;
                   false            �           0    0    EXTENSION cube    COMMENT     E   COMMENT ON EXTENSION cube IS 'data type for multidimensional cubes';
                        false    17                        3079    16384    dblink 	   EXTENSION     :   CREATE EXTENSION IF NOT EXISTS dblink WITH SCHEMA public;
    DROP EXTENSION dblink;
                   false            �           0    0    EXTENSION dblink    COMMENT     _   COMMENT ON EXTENSION dblink IS 'connect to other PostgreSQL databases from within a database';
                        false    2                        3079    17152    dict_int 	   EXTENSION     <   CREATE EXTENSION IF NOT EXISTS dict_int WITH SCHEMA public;
    DROP EXTENSION dict_int;
                   false            �           0    0    EXTENSION dict_int    COMMENT     Q   COMMENT ON EXTENSION dict_int IS 'text search dictionary template for integers';
                        false    14                        3079    18325 	   dict_xsyn 	   EXTENSION     =   CREATE EXTENSION IF NOT EXISTS dict_xsyn WITH SCHEMA public;
    DROP EXTENSION dict_xsyn;
                   false            �           0    0    EXTENSION dict_xsyn    COMMENT     e   COMMENT ON EXTENSION dict_xsyn IS 'text search dictionary template for extended synonym processing';
                        false    20                        3079    17686    earthdistance 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS earthdistance WITH SCHEMA public;
    DROP EXTENSION earthdistance;
                   false    17            �           0    0    EXTENSION earthdistance    COMMENT     f   COMMENT ON EXTENSION earthdistance IS 'calculate great-circle distances on the surface of the Earth';
                        false    18                        3079    16660    fuzzystrmatch 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS fuzzystrmatch WITH SCHEMA public;
    DROP EXTENSION fuzzystrmatch;
                   false            �           0    0    EXTENSION fuzzystrmatch    COMMENT     ]   COMMENT ON EXTENSION fuzzystrmatch IS 'determine similarities and distance between strings';
                        false    7                        3079    17025    hstore 	   EXTENSION     :   CREATE EXTENSION IF NOT EXISTS hstore WITH SCHEMA public;
    DROP EXTENSION hstore;
                   false            �           0    0    EXTENSION hstore    COMMENT     S   COMMENT ON EXTENSION hstore IS 'data type for storing sets of (key, value) pairs';
                        false    13                        3079    16903    intarray 	   EXTENSION     <   CREATE EXTENSION IF NOT EXISTS intarray WITH SCHEMA public;
    DROP EXTENSION intarray;
                   false            �           0    0    EXTENSION intarray    COMMENT     g   COMMENT ON EXTENSION intarray IS 'functions, operators, and index support for 1-D arrays of integers';
                        false    12                        3079    16444    ltree 	   EXTENSION     9   CREATE EXTENSION IF NOT EXISTS ltree WITH SCHEMA public;
    DROP EXTENSION ltree;
                   false            �           0    0    EXTENSION ltree    COMMENT     Q   COMMENT ON EXTENSION ltree IS 'data type for hierarchical tree-like structures';
                        false    4                        3079    18337    pg_stat_statements 	   EXTENSION     F   CREATE EXTENSION IF NOT EXISTS pg_stat_statements WITH SCHEMA public;
 #   DROP EXTENSION pg_stat_statements;
                   false            �           0    0    EXTENSION pg_stat_statements    COMMENT     u   COMMENT ON EXTENSION pg_stat_statements IS 'track planning and execution statistics of all SQL statements executed';
                        false    22                        3079    16824    pg_trgm 	   EXTENSION     ;   CREATE EXTENSION IF NOT EXISTS pg_trgm WITH SCHEMA public;
    DROP EXTENSION pg_trgm;
                   false            �           0    0    EXTENSION pg_trgm    COMMENT     e   COMMENT ON EXTENSION pg_trgm IS 'text similarity measurement and index searching based on trigrams';
                        false    11            
            3079    16787    pgcrypto 	   EXTENSION     <   CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;
    DROP EXTENSION pgcrypto;
                   false            �           0    0    EXTENSION pgcrypto    COMMENT     <   COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';
                        false    10                        3079    17597 
   pgrowlocks 	   EXTENSION     >   CREATE EXTENSION IF NOT EXISTS pgrowlocks WITH SCHEMA public;
    DROP EXTENSION pgrowlocks;
                   false            �           0    0    EXTENSION pgrowlocks    COMMENT     I   COMMENT ON EXTENSION pgrowlocks IS 'show row-level locking information';
                        false    16                        3079    16629    pgstattuple 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS pgstattuple WITH SCHEMA public;
    DROP EXTENSION pgstattuple;
                   false            �           0    0    EXTENSION pgstattuple    COMMENT     C   COMMENT ON EXTENSION pgstattuple IS 'show tuple-level statistics';
                        false    5                        3079    16639 	   tablefunc 	   EXTENSION     =   CREATE EXTENSION IF NOT EXISTS tablefunc WITH SCHEMA public;
    DROP EXTENSION tablefunc;
                   false            �           0    0    EXTENSION tablefunc    COMMENT     `   COMMENT ON EXTENSION tablefunc IS 'functions that manipulate whole tables, including crosstab';
                        false    6                        3079    18330    unaccent 	   EXTENSION     <   CREATE EXTENSION IF NOT EXISTS unaccent WITH SCHEMA public;
    DROP EXTENSION unaccent;
                   false            �           0    0    EXTENSION unaccent    COMMENT     P   COMMENT ON EXTENSION unaccent IS 'text search dictionary that removes accents';
                        false    21            	            3079    16776 	   uuid-ossp 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;
    DROP EXTENSION "uuid-ossp";
                   false            �           0    0    EXTENSION "uuid-ossp"    COMMENT     W   COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';
                        false    9                        3079    16430    xml2 	   EXTENSION     8   CREATE EXTENSION IF NOT EXISTS xml2 WITH SCHEMA public;
    DROP EXTENSION xml2;
                   false            �           0    0    EXTENSION xml2    COMMENT     8   COMMENT ON EXTENSION xml2 IS 'XPath querying and XSLT';
                        false    3            �            1259    1681412    cliente    TABLE     e   CREATE TABLE public.cliente (
    id_cli integer NOT NULL,
    id_per integer,
    id_tip integer
);
    DROP TABLE public.cliente;
       public         heap    zfztqpco    false            �            1259    1681410    cliente_id_cli_seq    SEQUENCE     �   CREATE SEQUENCE public.cliente_id_cli_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.cliente_id_cli_seq;
       public          zfztqpco    false    239            �           0    0    cliente_id_cli_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.cliente_id_cli_seq OWNED BY public.cliente.id_cli;
          public          zfztqpco    false    238            �            1259    1681389    cuenta    TABLE     �   CREATE TABLE public.cuenta (
    id_cue integer NOT NULL,
    id_emp integer,
    username_cue character varying(30),
    password_cue character varying(15)
);
    DROP TABLE public.cuenta;
       public         heap    zfztqpco    false            �            1259    1681387    cuenta_id_cue_seq    SEQUENCE     �   CREATE SEQUENCE public.cuenta_id_cue_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.cuenta_id_cue_seq;
       public          zfztqpco    false    235            �           0    0    cuenta_id_cue_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.cuenta_id_cue_seq OWNED BY public.cuenta.id_cue;
          public          zfztqpco    false    234            �            1259    1681518    det_factura    TABLE     �   CREATE TABLE public.det_factura (
    id_det integer NOT NULL,
    idencabezado_det integer,
    idservicio_det integer,
    idreserva_enc integer,
    observaciones_det character varying(100),
    costo_det double precision
);
    DROP TABLE public.det_factura;
       public         heap    zfztqpco    false            �            1259    1681516    det_factura_id_det_seq    SEQUENCE     �   CREATE SEQUENCE public.det_factura_id_det_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.det_factura_id_det_seq;
       public          zfztqpco    false    253            �           0    0    det_factura_id_det_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.det_factura_id_det_seq OWNED BY public.det_factura.id_det;
          public          zfztqpco    false    252            �            1259    1681469    det_reserva    TABLE     �   CREATE TABLE public.det_reserva (
    id_rha integer NOT NULL,
    idreserva_rha integer,
    idhabitacion_rha integer,
    idcliente_rha integer
);
    DROP TABLE public.det_reserva;
       public         heap    zfztqpco    false            �            1259    1681467    det_reserva_id_rha_seq    SEQUENCE     �   CREATE SEQUENCE public.det_reserva_id_rha_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.det_reserva_id_rha_seq;
       public          zfztqpco    false    247            �           0    0    det_reserva_id_rha_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.det_reserva_id_rha_seq OWNED BY public.det_reserva.id_rha;
          public          zfztqpco    false    246            �            1259    1681371    empleado    TABLE     k   CREATE TABLE public.empleado (
    id_emp integer NOT NULL,
    id_per integer,
    idlabor_emp integer
);
    DROP TABLE public.empleado;
       public         heap    zfztqpco    false            �            1259    1681369    empleado_id_emp_seq    SEQUENCE     �   CREATE SEQUENCE public.empleado_id_emp_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.empleado_id_emp_seq;
       public          zfztqpco    false    233            �           0    0    empleado_id_emp_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.empleado_id_emp_seq OWNED BY public.empleado.id_emp;
          public          zfztqpco    false    232            �            1259    1681492    enc_factura    TABLE     �   CREATE TABLE public.enc_factura (
    id_enc integer NOT NULL,
    idcliente_enc integer,
    idempl_enc integer,
    fecha_enc date,
    total_enc double precision,
    estado_enc boolean
);
    DROP TABLE public.enc_factura;
       public         heap    zfztqpco    false            �            1259    1681490    enc_factura_id_enc_seq    SEQUENCE     �   CREATE SEQUENCE public.enc_factura_id_enc_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.enc_factura_id_enc_seq;
       public          zfztqpco    false    249            �           0    0    enc_factura_id_enc_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.enc_factura_id_enc_seq OWNED BY public.enc_factura.id_enc;
          public          zfztqpco    false    248            �            1259    1681456    enc_reserva    TABLE     �   CREATE TABLE public.enc_reserva (
    id_res integer NOT NULL,
    idcliente_res integer,
    fechaingreso_res date NOT NULL,
    fechasalida_res date NOT NULL,
    total_res double precision,
    estado_res boolean
);
    DROP TABLE public.enc_reserva;
       public         heap    zfztqpco    false            �            1259    1681454    enc_reserva_id_res_seq    SEQUENCE     �   CREATE SEQUENCE public.enc_reserva_id_res_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.enc_reserva_id_res_seq;
       public          zfztqpco    false    245            �           0    0    enc_reserva_id_res_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.enc_reserva_id_res_seq OWNED BY public.enc_reserva.id_res;
          public          zfztqpco    false    244            �            1259    1681441 
   habitacion    TABLE     �   CREATE TABLE public.habitacion (
    id_hab integer NOT NULL,
    idtipo_hab integer,
    numero_hab integer NOT NULL,
    estado_hab boolean
);
    DROP TABLE public.habitacion;
       public         heap    zfztqpco    false            �            1259    1681439    habitacion_id_hab_seq    SEQUENCE     �   CREATE SEQUENCE public.habitacion_id_hab_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.habitacion_id_hab_seq;
       public          zfztqpco    false    243            �           0    0    habitacion_id_hab_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.habitacion_id_hab_seq OWNED BY public.habitacion.id_hab;
          public          zfztqpco    false    242            �            1259    1681363    labor    TABLE     �   CREATE TABLE public.labor (
    id_lab integer NOT NULL,
    nombre_lab character varying(40),
    horaslaborales_lab integer,
    sueldo double precision
);
    DROP TABLE public.labor;
       public         heap    zfztqpco    false            �            1259    1681361    labor_id_lab_seq    SEQUENCE     �   CREATE SEQUENCE public.labor_id_lab_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.labor_id_lab_seq;
       public          zfztqpco    false    231            �           0    0    labor_id_lab_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.labor_id_lab_seq OWNED BY public.labor.id_lab;
          public          zfztqpco    false    230            �            1259    1681346    persona    TABLE     �  CREATE TABLE public.persona (
    id_per integer NOT NULL,
    numeroidentificacion_per character varying(20) NOT NULL,
    nombre_per character varying(50),
    apellido_per character varying(50),
    tipo_doc integer,
    direccion_per character varying(120),
    telefono_per character varying(15),
    email_per character varying(120),
    genero_per character varying(20),
    fecha_nac date
);
    DROP TABLE public.persona;
       public         heap    zfztqpco    false            �            1259    1681344    persona_id_per_seq    SEQUENCE     �   CREATE SEQUENCE public.persona_id_per_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.persona_id_per_seq;
       public          zfztqpco    false    229            �           0    0    persona_id_per_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.persona_id_per_seq OWNED BY public.persona.id_per;
          public          zfztqpco    false    228            �            1259    1681510    servicio    TABLE     �   CREATE TABLE public.servicio (
    id_ser integer NOT NULL,
    nombre_ser character varying(50) NOT NULL,
    descripcion_ser character varying(200),
    precio_ser double precision
);
    DROP TABLE public.servicio;
       public         heap    zfztqpco    false            �            1259    1681508    servicio_id_ser_seq    SEQUENCE     �   CREATE SEQUENCE public.servicio_id_ser_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.servicio_id_ser_seq;
       public          zfztqpco    false    251            �           0    0    servicio_id_ser_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.servicio_id_ser_seq OWNED BY public.servicio.id_ser;
          public          zfztqpco    false    250            �            1259    1681402    tipo_cliente    TABLE     q   CREATE TABLE public.tipo_cliente (
    id_tip integer NOT NULL,
    nombre_tip character varying(50) NOT NULL
);
     DROP TABLE public.tipo_cliente;
       public         heap    zfztqpco    false            �            1259    1681400    tipo_cliente_id_tip_seq    SEQUENCE     �   CREATE SEQUENCE public.tipo_cliente_id_tip_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.tipo_cliente_id_tip_seq;
       public          zfztqpco    false    237            �           0    0    tipo_cliente_id_tip_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.tipo_cliente_id_tip_seq OWNED BY public.tipo_cliente.id_tip;
          public          zfztqpco    false    236            �            1259    1681338    tipo_doc    TABLE     m   CREATE TABLE public.tipo_doc (
    id_tip integer NOT NULL,
    nombre_doc character varying(20) NOT NULL
);
    DROP TABLE public.tipo_doc;
       public         heap    zfztqpco    false            �            1259    1681336    tipo_doc_id_tip_seq    SEQUENCE     �   CREATE SEQUENCE public.tipo_doc_id_tip_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.tipo_doc_id_tip_seq;
       public          zfztqpco    false    227            �           0    0    tipo_doc_id_tip_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.tipo_doc_id_tip_seq OWNED BY public.tipo_doc.id_tip;
          public          zfztqpco    false    226            �            1259    1681430    tipo_habitacion    TABLE     �   CREATE TABLE public.tipo_habitacion (
    id_tha integer NOT NULL,
    nombre_tha character varying NOT NULL,
    numerocamas_tha integer,
    capacidad_tha integer,
    precio_tha double precision
);
 #   DROP TABLE public.tipo_habitacion;
       public         heap    zfztqpco    false            �            1259    1681428    tipo_habitacion_id_tha_seq    SEQUENCE     �   CREATE SEQUENCE public.tipo_habitacion_id_tha_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.tipo_habitacion_id_tha_seq;
       public          zfztqpco    false    241            �           0    0    tipo_habitacion_id_tha_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.tipo_habitacion_id_tha_seq OWNED BY public.tipo_habitacion.id_tha;
          public          zfztqpco    false    240            �           2604    1681415    cliente id_cli    DEFAULT     p   ALTER TABLE ONLY public.cliente ALTER COLUMN id_cli SET DEFAULT nextval('public.cliente_id_cli_seq'::regclass);
 =   ALTER TABLE public.cliente ALTER COLUMN id_cli DROP DEFAULT;
       public          zfztqpco    false    238    239    239            �           2604    1681392    cuenta id_cue    DEFAULT     n   ALTER TABLE ONLY public.cuenta ALTER COLUMN id_cue SET DEFAULT nextval('public.cuenta_id_cue_seq'::regclass);
 <   ALTER TABLE public.cuenta ALTER COLUMN id_cue DROP DEFAULT;
       public          zfztqpco    false    235    234    235            �           2604    1681521    det_factura id_det    DEFAULT     x   ALTER TABLE ONLY public.det_factura ALTER COLUMN id_det SET DEFAULT nextval('public.det_factura_id_det_seq'::regclass);
 A   ALTER TABLE public.det_factura ALTER COLUMN id_det DROP DEFAULT;
       public          zfztqpco    false    252    253    253            �           2604    1681472    det_reserva id_rha    DEFAULT     x   ALTER TABLE ONLY public.det_reserva ALTER COLUMN id_rha SET DEFAULT nextval('public.det_reserva_id_rha_seq'::regclass);
 A   ALTER TABLE public.det_reserva ALTER COLUMN id_rha DROP DEFAULT;
       public          zfztqpco    false    247    246    247            �           2604    1681374    empleado id_emp    DEFAULT     r   ALTER TABLE ONLY public.empleado ALTER COLUMN id_emp SET DEFAULT nextval('public.empleado_id_emp_seq'::regclass);
 >   ALTER TABLE public.empleado ALTER COLUMN id_emp DROP DEFAULT;
       public          zfztqpco    false    232    233    233            �           2604    1681495    enc_factura id_enc    DEFAULT     x   ALTER TABLE ONLY public.enc_factura ALTER COLUMN id_enc SET DEFAULT nextval('public.enc_factura_id_enc_seq'::regclass);
 A   ALTER TABLE public.enc_factura ALTER COLUMN id_enc DROP DEFAULT;
       public          zfztqpco    false    248    249    249            �           2604    1681459    enc_reserva id_res    DEFAULT     x   ALTER TABLE ONLY public.enc_reserva ALTER COLUMN id_res SET DEFAULT nextval('public.enc_reserva_id_res_seq'::regclass);
 A   ALTER TABLE public.enc_reserva ALTER COLUMN id_res DROP DEFAULT;
       public          zfztqpco    false    244    245    245            �           2604    1681444    habitacion id_hab    DEFAULT     v   ALTER TABLE ONLY public.habitacion ALTER COLUMN id_hab SET DEFAULT nextval('public.habitacion_id_hab_seq'::regclass);
 @   ALTER TABLE public.habitacion ALTER COLUMN id_hab DROP DEFAULT;
       public          zfztqpco    false    243    242    243            �           2604    1681366    labor id_lab    DEFAULT     l   ALTER TABLE ONLY public.labor ALTER COLUMN id_lab SET DEFAULT nextval('public.labor_id_lab_seq'::regclass);
 ;   ALTER TABLE public.labor ALTER COLUMN id_lab DROP DEFAULT;
       public          zfztqpco    false    230    231    231            �           2604    1681349    persona id_per    DEFAULT     p   ALTER TABLE ONLY public.persona ALTER COLUMN id_per SET DEFAULT nextval('public.persona_id_per_seq'::regclass);
 =   ALTER TABLE public.persona ALTER COLUMN id_per DROP DEFAULT;
       public          zfztqpco    false    228    229    229            �           2604    1681513    servicio id_ser    DEFAULT     r   ALTER TABLE ONLY public.servicio ALTER COLUMN id_ser SET DEFAULT nextval('public.servicio_id_ser_seq'::regclass);
 >   ALTER TABLE public.servicio ALTER COLUMN id_ser DROP DEFAULT;
       public          zfztqpco    false    250    251    251            �           2604    1681405    tipo_cliente id_tip    DEFAULT     z   ALTER TABLE ONLY public.tipo_cliente ALTER COLUMN id_tip SET DEFAULT nextval('public.tipo_cliente_id_tip_seq'::regclass);
 B   ALTER TABLE public.tipo_cliente ALTER COLUMN id_tip DROP DEFAULT;
       public          zfztqpco    false    237    236    237            �           2604    1681341    tipo_doc id_tip    DEFAULT     r   ALTER TABLE ONLY public.tipo_doc ALTER COLUMN id_tip SET DEFAULT nextval('public.tipo_doc_id_tip_seq'::regclass);
 >   ALTER TABLE public.tipo_doc ALTER COLUMN id_tip DROP DEFAULT;
       public          zfztqpco    false    226    227    227            �           2604    1681433    tipo_habitacion id_tha    DEFAULT     �   ALTER TABLE ONLY public.tipo_habitacion ALTER COLUMN id_tha SET DEFAULT nextval('public.tipo_habitacion_id_tha_seq'::regclass);
 E   ALTER TABLE public.tipo_habitacion ALTER COLUMN id_tha DROP DEFAULT;
       public          zfztqpco    false    241    240    241            �          0    1681412    cliente 
   TABLE DATA           9   COPY public.cliente (id_cli, id_per, id_tip) FROM stdin;
    public          zfztqpco    false    239   q�       �          0    1681389    cuenta 
   TABLE DATA           L   COPY public.cuenta (id_cue, id_emp, username_cue, password_cue) FROM stdin;
    public          zfztqpco    false    235   ��       �          0    1681518    det_factura 
   TABLE DATA           |   COPY public.det_factura (id_det, idencabezado_det, idservicio_det, idreserva_enc, observaciones_det, costo_det) FROM stdin;
    public          zfztqpco    false    253   ��       �          0    1681469    det_reserva 
   TABLE DATA           ]   COPY public.det_reserva (id_rha, idreserva_rha, idhabitacion_rha, idcliente_rha) FROM stdin;
    public          zfztqpco    false    247   Ȳ       �          0    1681371    empleado 
   TABLE DATA           ?   COPY public.empleado (id_emp, id_per, idlabor_emp) FROM stdin;
    public          zfztqpco    false    233   �       �          0    1681492    enc_factura 
   TABLE DATA           j   COPY public.enc_factura (id_enc, idcliente_enc, idempl_enc, fecha_enc, total_enc, estado_enc) FROM stdin;
    public          zfztqpco    false    249   �       �          0    1681456    enc_reserva 
   TABLE DATA           v   COPY public.enc_reserva (id_res, idcliente_res, fechaingreso_res, fechasalida_res, total_res, estado_res) FROM stdin;
    public          zfztqpco    false    245   �       �          0    1681441 
   habitacion 
   TABLE DATA           P   COPY public.habitacion (id_hab, idtipo_hab, numero_hab, estado_hab) FROM stdin;
    public          zfztqpco    false    243   <�       �          0    1681363    labor 
   TABLE DATA           O   COPY public.labor (id_lab, nombre_lab, horaslaborales_lab, sueldo) FROM stdin;
    public          zfztqpco    false    231   j�       �          0    1681346    persona 
   TABLE DATA           �   COPY public.persona (id_per, numeroidentificacion_per, nombre_per, apellido_per, tipo_doc, direccion_per, telefono_per, email_per, genero_per, fecha_nac) FROM stdin;
    public          zfztqpco    false    229   ��       �          0    1681510    servicio 
   TABLE DATA           S   COPY public.servicio (id_ser, nombre_ser, descripcion_ser, precio_ser) FROM stdin;
    public          zfztqpco    false    251   ��       �          0    1681402    tipo_cliente 
   TABLE DATA           :   COPY public.tipo_cliente (id_tip, nombre_tip) FROM stdin;
    public          zfztqpco    false    237   ��       �          0    1681338    tipo_doc 
   TABLE DATA           6   COPY public.tipo_doc (id_tip, nombre_doc) FROM stdin;
    public          zfztqpco    false    227   ޳       �          0    1681430    tipo_habitacion 
   TABLE DATA           i   COPY public.tipo_habitacion (id_tha, nombre_tha, numerocamas_tha, capacidad_tha, precio_tha) FROM stdin;
    public          zfztqpco    false    241   ��       �           0    0    cliente_id_cli_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.cliente_id_cli_seq', 1, false);
          public          zfztqpco    false    238            �           0    0    cuenta_id_cue_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.cuenta_id_cue_seq', 1, false);
          public          zfztqpco    false    234            �           0    0    det_factura_id_det_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.det_factura_id_det_seq', 1, false);
          public          zfztqpco    false    252            �           0    0    det_reserva_id_rha_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.det_reserva_id_rha_seq', 1, false);
          public          zfztqpco    false    246            �           0    0    empleado_id_emp_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.empleado_id_emp_seq', 1, false);
          public          zfztqpco    false    232            �           0    0    enc_factura_id_enc_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.enc_factura_id_enc_seq', 1, false);
          public          zfztqpco    false    248            �           0    0    enc_reserva_id_res_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.enc_reserva_id_res_seq', 1, false);
          public          zfztqpco    false    244            �           0    0    habitacion_id_hab_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.habitacion_id_hab_seq', 4, true);
          public          zfztqpco    false    242            �           0    0    labor_id_lab_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.labor_id_lab_seq', 1, false);
          public          zfztqpco    false    230            �           0    0    persona_id_per_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.persona_id_per_seq', 1, false);
          public          zfztqpco    false    228            �           0    0    servicio_id_ser_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.servicio_id_ser_seq', 1, false);
          public          zfztqpco    false    250            �           0    0    tipo_cliente_id_tip_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.tipo_cliente_id_tip_seq', 1, false);
          public          zfztqpco    false    236            �           0    0    tipo_doc_id_tip_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.tipo_doc_id_tip_seq', 1, false);
          public          zfztqpco    false    226            �           0    0    tipo_habitacion_id_tha_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.tipo_habitacion_id_tha_seq', 4, true);
          public          zfztqpco    false    240            �           2606    1681417    cliente cliente_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id_cli);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public            zfztqpco    false    239            �           2606    1681394    cuenta cuenta_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT cuenta_pkey PRIMARY KEY (id_cue);
 <   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT cuenta_pkey;
       public            zfztqpco    false    235            �           2606    1681523    det_factura det_factura_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.det_factura
    ADD CONSTRAINT det_factura_pkey PRIMARY KEY (id_det);
 F   ALTER TABLE ONLY public.det_factura DROP CONSTRAINT det_factura_pkey;
       public            zfztqpco    false    253            �           2606    1681474    det_reserva det_reserva_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.det_reserva
    ADD CONSTRAINT det_reserva_pkey PRIMARY KEY (id_rha);
 F   ALTER TABLE ONLY public.det_reserva DROP CONSTRAINT det_reserva_pkey;
       public            zfztqpco    false    247            �           2606    1681376    empleado empleado_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (id_emp);
 @   ALTER TABLE ONLY public.empleado DROP CONSTRAINT empleado_pkey;
       public            zfztqpco    false    233            �           2606    1681497    enc_factura enc_factura_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.enc_factura
    ADD CONSTRAINT enc_factura_pkey PRIMARY KEY (id_enc);
 F   ALTER TABLE ONLY public.enc_factura DROP CONSTRAINT enc_factura_pkey;
       public            zfztqpco    false    249            �           2606    1681461    enc_reserva enc_reserva_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.enc_reserva
    ADD CONSTRAINT enc_reserva_pkey PRIMARY KEY (id_res);
 F   ALTER TABLE ONLY public.enc_reserva DROP CONSTRAINT enc_reserva_pkey;
       public            zfztqpco    false    245            �           2606    1681448 $   habitacion habitacion_numero_hab_key 
   CONSTRAINT     e   ALTER TABLE ONLY public.habitacion
    ADD CONSTRAINT habitacion_numero_hab_key UNIQUE (numero_hab);
 N   ALTER TABLE ONLY public.habitacion DROP CONSTRAINT habitacion_numero_hab_key;
       public            zfztqpco    false    243            �           2606    1681446    habitacion habitacion_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.habitacion
    ADD CONSTRAINT habitacion_pkey PRIMARY KEY (id_hab);
 D   ALTER TABLE ONLY public.habitacion DROP CONSTRAINT habitacion_pkey;
       public            zfztqpco    false    243            �           2606    1681368    labor labor_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.labor
    ADD CONSTRAINT labor_pkey PRIMARY KEY (id_lab);
 :   ALTER TABLE ONLY public.labor DROP CONSTRAINT labor_pkey;
       public            zfztqpco    false    231            �           2606    1681355    persona persona_email_per_key 
   CONSTRAINT     ]   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_email_per_key UNIQUE (email_per);
 G   ALTER TABLE ONLY public.persona DROP CONSTRAINT persona_email_per_key;
       public            zfztqpco    false    229            �           2606    1681353 ,   persona persona_numeroidentificacion_per_key 
   CONSTRAINT     {   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_numeroidentificacion_per_key UNIQUE (numeroidentificacion_per);
 V   ALTER TABLE ONLY public.persona DROP CONSTRAINT persona_numeroidentificacion_per_key;
       public            zfztqpco    false    229            �           2606    1681351    persona persona_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id_per);
 >   ALTER TABLE ONLY public.persona DROP CONSTRAINT persona_pkey;
       public            zfztqpco    false    229            �           2606    1681515    servicio servicio_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.servicio
    ADD CONSTRAINT servicio_pkey PRIMARY KEY (id_ser);
 @   ALTER TABLE ONLY public.servicio DROP CONSTRAINT servicio_pkey;
       public            zfztqpco    false    251            �           2606    1681409 (   tipo_cliente tipo_cliente_nombre_tip_key 
   CONSTRAINT     i   ALTER TABLE ONLY public.tipo_cliente
    ADD CONSTRAINT tipo_cliente_nombre_tip_key UNIQUE (nombre_tip);
 R   ALTER TABLE ONLY public.tipo_cliente DROP CONSTRAINT tipo_cliente_nombre_tip_key;
       public            zfztqpco    false    237            �           2606    1681407    tipo_cliente tipo_cliente_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.tipo_cliente
    ADD CONSTRAINT tipo_cliente_pkey PRIMARY KEY (id_tip);
 H   ALTER TABLE ONLY public.tipo_cliente DROP CONSTRAINT tipo_cliente_pkey;
       public            zfztqpco    false    237            �           2606    1681343    tipo_doc tipo_doc_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.tipo_doc
    ADD CONSTRAINT tipo_doc_pkey PRIMARY KEY (id_tip);
 @   ALTER TABLE ONLY public.tipo_doc DROP CONSTRAINT tipo_doc_pkey;
       public            zfztqpco    false    227            �           2606    1681438 $   tipo_habitacion tipo_habitacion_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.tipo_habitacion
    ADD CONSTRAINT tipo_habitacion_pkey PRIMARY KEY (id_tha);
 N   ALTER TABLE ONLY public.tipo_habitacion DROP CONSTRAINT tipo_habitacion_pkey;
       public            zfztqpco    false    241            �           2606    1681462    enc_reserva fk_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY public.enc_reserva
    ADD CONSTRAINT fk_cliente FOREIGN KEY (idcliente_res) REFERENCES public.cliente(id_cli);
 @   ALTER TABLE ONLY public.enc_reserva DROP CONSTRAINT fk_cliente;
       public          zfztqpco    false    245    239    4061            �           2606    1681498    enc_factura fk_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY public.enc_factura
    ADD CONSTRAINT fk_cliente FOREIGN KEY (idcliente_enc) REFERENCES public.cliente(id_cli);
 @   ALTER TABLE ONLY public.enc_factura DROP CONSTRAINT fk_cliente;
       public          zfztqpco    false    239    4061    249            �           2606    1681395    cuenta fk_empleado    FK CONSTRAINT     w   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT fk_empleado FOREIGN KEY (id_emp) REFERENCES public.empleado(id_emp);
 <   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT fk_empleado;
       public          zfztqpco    false    4053    235    233            �           2606    1681503    enc_factura fk_empleado    FK CONSTRAINT     �   ALTER TABLE ONLY public.enc_factura
    ADD CONSTRAINT fk_empleado FOREIGN KEY (idempl_enc) REFERENCES public.empleado(id_emp);
 A   ALTER TABLE ONLY public.enc_factura DROP CONSTRAINT fk_empleado;
       public          zfztqpco    false    4053    233    249            �           2606    1681524    det_factura fk_encabezado    FK CONSTRAINT     �   ALTER TABLE ONLY public.det_factura
    ADD CONSTRAINT fk_encabezado FOREIGN KEY (idencabezado_det) REFERENCES public.enc_factura(id_enc);
 C   ALTER TABLE ONLY public.det_factura DROP CONSTRAINT fk_encabezado;
       public          zfztqpco    false    253    4073    249            �           2606    1681475 !   det_reserva fk_encabezado_reserva    FK CONSTRAINT     �   ALTER TABLE ONLY public.det_reserva
    ADD CONSTRAINT fk_encabezado_reserva FOREIGN KEY (idreserva_rha) REFERENCES public.enc_reserva(id_res);
 K   ALTER TABLE ONLY public.det_reserva DROP CONSTRAINT fk_encabezado_reserva;
       public          zfztqpco    false    247    4069    245            �           2606    1681480    det_reserva fk_habitacion    FK CONSTRAINT     �   ALTER TABLE ONLY public.det_reserva
    ADD CONSTRAINT fk_habitacion FOREIGN KEY (idhabitacion_rha) REFERENCES public.habitacion(id_hab);
 C   ALTER TABLE ONLY public.det_reserva DROP CONSTRAINT fk_habitacion;
       public          zfztqpco    false    4067    243    247            �           2606    1681485    det_reserva fk_huespedes    FK CONSTRAINT     �   ALTER TABLE ONLY public.det_reserva
    ADD CONSTRAINT fk_huespedes FOREIGN KEY (idcliente_rha) REFERENCES public.cliente(id_cli);
 B   ALTER TABLE ONLY public.det_reserva DROP CONSTRAINT fk_huespedes;
       public          zfztqpco    false    4061    239    247            �           2606    1681382    empleado fk_labor    FK CONSTRAINT     x   ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT fk_labor FOREIGN KEY (idlabor_emp) REFERENCES public.labor(id_lab);
 ;   ALTER TABLE ONLY public.empleado DROP CONSTRAINT fk_labor;
       public          zfztqpco    false    233    231    4051            �           2606    1681377    empleado fk_persona    FK CONSTRAINT     w   ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT fk_persona FOREIGN KEY (id_per) REFERENCES public.persona(id_per);
 =   ALTER TABLE ONLY public.empleado DROP CONSTRAINT fk_persona;
       public          zfztqpco    false    4049    229    233            �           2606    1681418    cliente fk_persona    FK CONSTRAINT     v   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT fk_persona FOREIGN KEY (id_per) REFERENCES public.persona(id_per);
 <   ALTER TABLE ONLY public.cliente DROP CONSTRAINT fk_persona;
       public          zfztqpco    false    229    239    4049            �           2606    1681534    det_factura fk_reserva    FK CONSTRAINT     �   ALTER TABLE ONLY public.det_factura
    ADD CONSTRAINT fk_reserva FOREIGN KEY (idreserva_enc) REFERENCES public.enc_reserva(id_res);
 @   ALTER TABLE ONLY public.det_factura DROP CONSTRAINT fk_reserva;
       public          zfztqpco    false    4069    245    253            �           2606    1681529    det_factura fk_servicio    FK CONSTRAINT     �   ALTER TABLE ONLY public.det_factura
    ADD CONSTRAINT fk_servicio FOREIGN KEY (idservicio_det) REFERENCES public.servicio(id_ser);
 A   ALTER TABLE ONLY public.det_factura DROP CONSTRAINT fk_servicio;
       public          zfztqpco    false    4075    251    253            �           2606    1681423    cliente fk_tipo    FK CONSTRAINT     x   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT fk_tipo FOREIGN KEY (id_tip) REFERENCES public.tipo_cliente(id_tip);
 9   ALTER TABLE ONLY public.cliente DROP CONSTRAINT fk_tipo;
       public          zfztqpco    false    237    239    4059            �           2606    1681356    persona fk_tipo_doc    FK CONSTRAINT     z   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT fk_tipo_doc FOREIGN KEY (tipo_doc) REFERENCES public.tipo_doc(id_tip);
 =   ALTER TABLE ONLY public.persona DROP CONSTRAINT fk_tipo_doc;
       public          zfztqpco    false    229    4043    227            �           2606    1681449    habitacion fk_tipo_habitacion    FK CONSTRAINT     �   ALTER TABLE ONLY public.habitacion
    ADD CONSTRAINT fk_tipo_habitacion FOREIGN KEY (idtipo_hab) REFERENCES public.tipo_habitacion(id_tha);
 G   ALTER TABLE ONLY public.habitacion DROP CONSTRAINT fk_tipo_habitacion;
       public          zfztqpco    false    243    241    4063            �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x�3�4�440�L�2�4���=... 0��      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x�3�t,N�44� .4~� �sA     