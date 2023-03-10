PGDMP     &        
            {            OrquideaHotel    14.2    14.2 B    ^           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            _           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            `           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            a           1262    24717    OrquideaHotel    DATABASE     m   CREATE DATABASE "OrquideaHotel" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Ecuador.1252';
    DROP DATABASE "OrquideaHotel";
                postgres    false            ?            1259    24815    cliente    TABLE     e   CREATE TABLE public.cliente (
    id_cli integer NOT NULL,
    id_per integer,
    id_tip integer
);
    DROP TABLE public.cliente;
       public         heap    postgres    false            ?            1259    24766    cuenta    TABLE     ?   CREATE TABLE public.cuenta (
    id_cue integer NOT NULL,
    username_cue character varying(30),
    password_cue character varying(15)
);
    DROP TABLE public.cuenta;
       public         heap    postgres    false            ?            1259    24892    det_factura    TABLE     ?   CREATE TABLE public.det_factura (
    id_det integer NOT NULL,
    idencabezado_det integer,
    idservicio_det integer,
    idreserva_enc integer,
    observaciones_det character varying(100),
    costo_det double precision
);
    DROP TABLE public.det_factura;
       public         heap    postgres    false            ?            1259    24852    det_reserva    TABLE     ?   CREATE TABLE public.det_reserva (
    id_rha integer NOT NULL,
    idreserva_rha integer,
    idhabitacion_rha integer,
    idcliente_rha integer
);
    DROP TABLE public.det_reserva;
       public         heap    postgres    false            ?            1259    24771    empleado    TABLE     ?   CREATE TABLE public.empleado (
    id_emp integer NOT NULL,
    id_per integer,
    idcuenta_emp integer,
    idlabor_emp integer
);
    DROP TABLE public.empleado;
       public         heap    postgres    false            ?            1259    24872    enc_factura    TABLE     ?   CREATE TABLE public.enc_factura (
    id_enc integer NOT NULL,
    idcliente_enc integer,
    idempl_enc integer,
    fecha_enc date,
    total_enc double precision,
    estado_enc boolean
);
    DROP TABLE public.enc_factura;
       public         heap    postgres    false            ?            1259    24842    enc_reserva    TABLE     ?   CREATE TABLE public.enc_reserva (
    id_res integer NOT NULL,
    idcliente_res integer,
    fechaingreso_res date NOT NULL,
    fechasalida_res date NOT NULL,
    total_res double precision,
    estado_res boolean
);
    DROP TABLE public.enc_reserva;
       public         heap    postgres    false            ?            1259    24830 
   habitacion    TABLE     ?   CREATE TABLE public.habitacion (
    id_hab integer NOT NULL,
    idtipo_hab integer,
    numero_hab integer NOT NULL,
    estado_hab boolean
);
    DROP TABLE public.habitacion;
       public         heap    postgres    false            ?            1259    24761    labor    TABLE     ?   CREATE TABLE public.labor (
    id_lab integer NOT NULL,
    nombre_lab character varying(40),
    horaslaborales_lab integer,
    sueldo double precision
);
    DROP TABLE public.labor;
       public         heap    postgres    false            ?            1259    24742    persona    TABLE     ?  CREATE TABLE public.persona (
    id_per integer NOT NULL,
    numeroidentificacion_per character varying(20) NOT NULL,
    nombre_per character varying(50),
    apellido_per character varying(50),
    tipo_doc integer,
    direccion_per character varying(120),
    telefono_per character varying(15),
    email_per character varying(120),
    fecha_nac date,
    genero_per character varying(20)
);
    DROP TABLE public.persona;
       public         heap    postgres    false            ?            1259    24887    servicio    TABLE     ?   CREATE TABLE public.servicio (
    id_ser integer NOT NULL,
    nombre_ser character varying(50) NOT NULL,
    descripcion_ser character varying(200),
    precio_ser double precision
);
    DROP TABLE public.servicio;
       public         heap    postgres    false            ?            1259    24808    tipo_cliente    TABLE     q   CREATE TABLE public.tipo_cliente (
    id_tip integer NOT NULL,
    nombre_tip character varying(50) NOT NULL
);
     DROP TABLE public.tipo_cliente;
       public         heap    postgres    false            ?            1259    24718    tipo_doc    TABLE     m   CREATE TABLE public.tipo_doc (
    id_tip integer NOT NULL,
    nombre_doc character varying(20) NOT NULL
);
    DROP TABLE public.tipo_doc;
       public         heap    postgres    false            ?            1259    24791    tipo_habitacion    TABLE     ?   CREATE TABLE public.tipo_habitacion (
    id_tha integer NOT NULL,
    nombre_tha character varying NOT NULL,
    numerocamas_tha integer,
    capacidad_tha integer,
    precio_tha double precision
);
 #   DROP TABLE public.tipo_habitacion;
       public         heap    postgres    false            U          0    24815    cliente 
   TABLE DATA           9   COPY public.cliente (id_cli, id_per, id_tip) FROM stdin;
    public          postgres    false    216   |P       Q          0    24766    cuenta 
   TABLE DATA           D   COPY public.cuenta (id_cue, username_cue, password_cue) FROM stdin;
    public          postgres    false    212   ?P       [          0    24892    det_factura 
   TABLE DATA           |   COPY public.det_factura (id_det, idencabezado_det, idservicio_det, idreserva_enc, observaciones_det, costo_det) FROM stdin;
    public          postgres    false    222   ?P       X          0    24852    det_reserva 
   TABLE DATA           ]   COPY public.det_reserva (id_rha, idreserva_rha, idhabitacion_rha, idcliente_rha) FROM stdin;
    public          postgres    false    219   ?P       R          0    24771    empleado 
   TABLE DATA           M   COPY public.empleado (id_emp, id_per, idcuenta_emp, idlabor_emp) FROM stdin;
    public          postgres    false    213   ?P       Y          0    24872    enc_factura 
   TABLE DATA           j   COPY public.enc_factura (id_enc, idcliente_enc, idempl_enc, fecha_enc, total_enc, estado_enc) FROM stdin;
    public          postgres    false    220   Q       W          0    24842    enc_reserva 
   TABLE DATA           v   COPY public.enc_reserva (id_res, idcliente_res, fechaingreso_res, fechasalida_res, total_res, estado_res) FROM stdin;
    public          postgres    false    218   *Q       V          0    24830 
   habitacion 
   TABLE DATA           P   COPY public.habitacion (id_hab, idtipo_hab, numero_hab, estado_hab) FROM stdin;
    public          postgres    false    217   GQ       P          0    24761    labor 
   TABLE DATA           O   COPY public.labor (id_lab, nombre_lab, horaslaborales_lab, sueldo) FROM stdin;
    public          postgres    false    211   dQ       O          0    24742    persona 
   TABLE DATA           ?   COPY public.persona (id_per, numeroidentificacion_per, nombre_per, apellido_per, tipo_doc, direccion_per, telefono_per, email_per, fecha_nac, genero_per) FROM stdin;
    public          postgres    false    210   ?Q       Z          0    24887    servicio 
   TABLE DATA           S   COPY public.servicio (id_ser, nombre_ser, descripcion_ser, precio_ser) FROM stdin;
    public          postgres    false    221   ?Q       T          0    24808    tipo_cliente 
   TABLE DATA           :   COPY public.tipo_cliente (id_tip, nombre_tip) FROM stdin;
    public          postgres    false    215   ?Q       N          0    24718    tipo_doc 
   TABLE DATA           6   COPY public.tipo_doc (id_tip, nombre_doc) FROM stdin;
    public          postgres    false    209   ?Q       S          0    24791    tipo_habitacion 
   TABLE DATA           i   COPY public.tipo_habitacion (id_tha, nombre_tha, numerocamas_tha, capacidad_tha, precio_tha) FROM stdin;
    public          postgres    false    214   ?Q       ?           2606    24819    cliente cliente_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id_cli);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public            postgres    false    216            ?           2606    24770    cuenta cuenta_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT cuenta_pkey PRIMARY KEY (id_cue);
 <   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT cuenta_pkey;
       public            postgres    false    212            ?           2606    24896    det_factura det_factura_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.det_factura
    ADD CONSTRAINT det_factura_pkey PRIMARY KEY (id_det);
 F   ALTER TABLE ONLY public.det_factura DROP CONSTRAINT det_factura_pkey;
       public            postgres    false    222            ?           2606    24856    det_reserva det_reserva_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.det_reserva
    ADD CONSTRAINT det_reserva_pkey PRIMARY KEY (id_rha);
 F   ALTER TABLE ONLY public.det_reserva DROP CONSTRAINT det_reserva_pkey;
       public            postgres    false    219            ?           2606    24775    empleado empleado_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (id_emp);
 @   ALTER TABLE ONLY public.empleado DROP CONSTRAINT empleado_pkey;
       public            postgres    false    213            ?           2606    24876    enc_factura enc_factura_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.enc_factura
    ADD CONSTRAINT enc_factura_pkey PRIMARY KEY (id_enc);
 F   ALTER TABLE ONLY public.enc_factura DROP CONSTRAINT enc_factura_pkey;
       public            postgres    false    220            ?           2606    24846    enc_reserva enc_reserva_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.enc_reserva
    ADD CONSTRAINT enc_reserva_pkey PRIMARY KEY (id_res);
 F   ALTER TABLE ONLY public.enc_reserva DROP CONSTRAINT enc_reserva_pkey;
       public            postgres    false    218            ?           2606    24836 $   habitacion habitacion_numero_hab_key 
   CONSTRAINT     e   ALTER TABLE ONLY public.habitacion
    ADD CONSTRAINT habitacion_numero_hab_key UNIQUE (numero_hab);
 N   ALTER TABLE ONLY public.habitacion DROP CONSTRAINT habitacion_numero_hab_key;
       public            postgres    false    217            ?           2606    24834    habitacion habitacion_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.habitacion
    ADD CONSTRAINT habitacion_pkey PRIMARY KEY (id_hab);
 D   ALTER TABLE ONLY public.habitacion DROP CONSTRAINT habitacion_pkey;
       public            postgres    false    217            ?           2606    24765    labor labor_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.labor
    ADD CONSTRAINT labor_pkey PRIMARY KEY (id_lab);
 :   ALTER TABLE ONLY public.labor DROP CONSTRAINT labor_pkey;
       public            postgres    false    211            ?           2606    24748    persona persona_cedula_per_key 
   CONSTRAINT     m   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_cedula_per_key UNIQUE (numeroidentificacion_per);
 H   ALTER TABLE ONLY public.persona DROP CONSTRAINT persona_cedula_per_key;
       public            postgres    false    210            ?           2606    24750    persona persona_email_per_key 
   CONSTRAINT     ]   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_email_per_key UNIQUE (email_per);
 G   ALTER TABLE ONLY public.persona DROP CONSTRAINT persona_email_per_key;
       public            postgres    false    210            ?           2606    24746    persona persona_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id_per);
 >   ALTER TABLE ONLY public.persona DROP CONSTRAINT persona_pkey;
       public            postgres    false    210            ?           2606    24891    servicio servicio_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.servicio
    ADD CONSTRAINT servicio_pkey PRIMARY KEY (id_ser);
 @   ALTER TABLE ONLY public.servicio DROP CONSTRAINT servicio_pkey;
       public            postgres    false    221            ?           2606    24814 (   tipo_cliente tipo_cliente_nombre_tip_key 
   CONSTRAINT     i   ALTER TABLE ONLY public.tipo_cliente
    ADD CONSTRAINT tipo_cliente_nombre_tip_key UNIQUE (nombre_tip);
 R   ALTER TABLE ONLY public.tipo_cliente DROP CONSTRAINT tipo_cliente_nombre_tip_key;
       public            postgres    false    215            ?           2606    24812    tipo_cliente tipo_cliente_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.tipo_cliente
    ADD CONSTRAINT tipo_cliente_pkey PRIMARY KEY (id_tip);
 H   ALTER TABLE ONLY public.tipo_cliente DROP CONSTRAINT tipo_cliente_pkey;
       public            postgres    false    215            ?           2606    24722    tipo_doc tipo_doc_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.tipo_doc
    ADD CONSTRAINT tipo_doc_pkey PRIMARY KEY (id_tip);
 @   ALTER TABLE ONLY public.tipo_doc DROP CONSTRAINT tipo_doc_pkey;
       public            postgres    false    209            ?           2606    24797 $   tipo_habitacion tipo_habitacion_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.tipo_habitacion
    ADD CONSTRAINT tipo_habitacion_pkey PRIMARY KEY (id_tha);
 N   ALTER TABLE ONLY public.tipo_habitacion DROP CONSTRAINT tipo_habitacion_pkey;
       public            postgres    false    214            ?           2606    24847    enc_reserva fk_cliente    FK CONSTRAINT     ?   ALTER TABLE ONLY public.enc_reserva
    ADD CONSTRAINT fk_cliente FOREIGN KEY (idcliente_res) REFERENCES public.cliente(id_cli);
 @   ALTER TABLE ONLY public.enc_reserva DROP CONSTRAINT fk_cliente;
       public          postgres    false    216    218    3236            ?           2606    24877    enc_factura fk_cliente    FK CONSTRAINT     ?   ALTER TABLE ONLY public.enc_factura
    ADD CONSTRAINT fk_cliente FOREIGN KEY (idcliente_enc) REFERENCES public.cliente(id_cli);
 @   ALTER TABLE ONLY public.enc_factura DROP CONSTRAINT fk_cliente;
       public          postgres    false    220    3236    216            ?           2606    24781    empleado fk_cuenta    FK CONSTRAINT     {   ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT fk_cuenta FOREIGN KEY (idcuenta_emp) REFERENCES public.cuenta(id_cue);
 <   ALTER TABLE ONLY public.empleado DROP CONSTRAINT fk_cuenta;
       public          postgres    false    212    3226    213            ?           2606    24882    enc_factura fk_empleado    FK CONSTRAINT     ?   ALTER TABLE ONLY public.enc_factura
    ADD CONSTRAINT fk_empleado FOREIGN KEY (idempl_enc) REFERENCES public.empleado(id_emp);
 A   ALTER TABLE ONLY public.enc_factura DROP CONSTRAINT fk_empleado;
       public          postgres    false    3228    213    220            ?           2606    24897    det_factura fk_encabezado    FK CONSTRAINT     ?   ALTER TABLE ONLY public.det_factura
    ADD CONSTRAINT fk_encabezado FOREIGN KEY (idencabezado_det) REFERENCES public.enc_factura(id_enc);
 C   ALTER TABLE ONLY public.det_factura DROP CONSTRAINT fk_encabezado;
       public          postgres    false    3246    220    222            ?           2606    24857 !   det_reserva fk_encabezado_reserva    FK CONSTRAINT     ?   ALTER TABLE ONLY public.det_reserva
    ADD CONSTRAINT fk_encabezado_reserva FOREIGN KEY (idreserva_rha) REFERENCES public.enc_reserva(id_res);
 K   ALTER TABLE ONLY public.det_reserva DROP CONSTRAINT fk_encabezado_reserva;
       public          postgres    false    219    3242    218            ?           2606    24862    det_reserva fk_habitacion    FK CONSTRAINT     ?   ALTER TABLE ONLY public.det_reserva
    ADD CONSTRAINT fk_habitacion FOREIGN KEY (idhabitacion_rha) REFERENCES public.habitacion(id_hab);
 C   ALTER TABLE ONLY public.det_reserva DROP CONSTRAINT fk_habitacion;
       public          postgres    false    3240    219    217            ?           2606    24867    det_reserva fk_huespedes    FK CONSTRAINT     ?   ALTER TABLE ONLY public.det_reserva
    ADD CONSTRAINT fk_huespedes FOREIGN KEY (idcliente_rha) REFERENCES public.cliente(id_cli);
 B   ALTER TABLE ONLY public.det_reserva DROP CONSTRAINT fk_huespedes;
       public          postgres    false    216    219    3236            ?           2606    24786    empleado fk_labor    FK CONSTRAINT     x   ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT fk_labor FOREIGN KEY (idlabor_emp) REFERENCES public.labor(id_lab);
 ;   ALTER TABLE ONLY public.empleado DROP CONSTRAINT fk_labor;
       public          postgres    false    3224    211    213            ?           2606    24776    empleado fk_persona    FK CONSTRAINT     w   ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT fk_persona FOREIGN KEY (id_per) REFERENCES public.persona(id_per);
 =   ALTER TABLE ONLY public.empleado DROP CONSTRAINT fk_persona;
       public          postgres    false    210    213    3222            ?           2606    24820    cliente fk_persona    FK CONSTRAINT     v   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT fk_persona FOREIGN KEY (id_per) REFERENCES public.persona(id_per);
 <   ALTER TABLE ONLY public.cliente DROP CONSTRAINT fk_persona;
       public          postgres    false    210    216    3222            ?           2606    24907    det_factura fk_reserva    FK CONSTRAINT     ?   ALTER TABLE ONLY public.det_factura
    ADD CONSTRAINT fk_reserva FOREIGN KEY (idreserva_enc) REFERENCES public.enc_reserva(id_res);
 @   ALTER TABLE ONLY public.det_factura DROP CONSTRAINT fk_reserva;
       public          postgres    false    218    3242    222            ?           2606    24902    det_factura fk_servicio    FK CONSTRAINT     ?   ALTER TABLE ONLY public.det_factura
    ADD CONSTRAINT fk_servicio FOREIGN KEY (idservicio_det) REFERENCES public.servicio(id_ser);
 A   ALTER TABLE ONLY public.det_factura DROP CONSTRAINT fk_servicio;
       public          postgres    false    3248    222    221            ?           2606    24825    cliente fk_tipo    FK CONSTRAINT     x   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT fk_tipo FOREIGN KEY (id_tip) REFERENCES public.tipo_cliente(id_tip);
 9   ALTER TABLE ONLY public.cliente DROP CONSTRAINT fk_tipo;
       public          postgres    false    3234    216    215            ?           2606    24751    persona fk_tipo_doc    FK CONSTRAINT     z   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT fk_tipo_doc FOREIGN KEY (tipo_doc) REFERENCES public.tipo_doc(id_tip);
 =   ALTER TABLE ONLY public.persona DROP CONSTRAINT fk_tipo_doc;
       public          postgres    false    3216    209    210            ?           2606    24837    habitacion fk_tipo_habitacion    FK CONSTRAINT     ?   ALTER TABLE ONLY public.habitacion
    ADD CONSTRAINT fk_tipo_habitacion FOREIGN KEY (idtipo_hab) REFERENCES public.tipo_habitacion(id_tha);
 G   ALTER TABLE ONLY public.habitacion DROP CONSTRAINT fk_tipo_habitacion;
       public          postgres    false    217    214    3230            U      x?????? ? ?      Q      x?????? ? ?      [      x?????? ? ?      X      x?????? ? ?      R      x?????? ? ?      Y      x?????? ? ?      W      x?????? ? ?      V      x?????? ? ?      P      x?????? ? ?      O      x?????? ? ?      Z      x?????? ? ?      T      x?????? ? ?      N      x?????? ? ?      S      x?????? ? ?     