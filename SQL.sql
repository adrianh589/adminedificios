create table if not exists roles
(
    id     int auto_increment
        primary key,
    nombre enum ('ROLE_ADMIN', 'ROLE_USER') not null
);

create table if not exists tipos_identificacion
(
    id     int auto_increment
        primary key,
    nombre varchar(45) not null
);

create table if not exists huespedes
(
    id                  int auto_increment
        primary key,
    tipo_identificacion int                   null,
    no_identificacion   varchar(100)          not null,
    nombre              varchar(45)           not null,
    apellido            varchar(45)           not null,
    dias_retraso        int         default 0 null,
    precio_mora         decimal(65) default 0 null,
    activo              tinyint(1)  default 1 null,
    constraint huespedes_tipos_identificacion_id_fk
        foreign key (tipo_identificacion) references tipos_identificacion (id)
);

create trigger desocupar_habitacion
    after update
    on huespedes
    for each row
BEGIN
        IF NEW.activo = 0 THEN
            UPDATE habitaciones SET id_huesped = NULL WHERE id_huesped = NEW.id;
        END IF;
    END;

create table if not exists torres
(
    id       int auto_increment
        primary key,
    torre_no int null
);

create table if not exists habitaciones
(
    id               int auto_increment
        primary key,
    id_huesped       int         null,
    id_torre         int         not null,
    no_habitacion    varchar(45) not null,
    costo_habitacion decimal(65) not null,
    constraint no_habitacion
        unique (no_habitacion, id_torre),
    constraint habitaciones_huespedes_id_fk
        foreign key (id_huesped) references huespedes (id),
    constraint habitaciones_torres_id_fk
        foreign key (id_torre) references torres (id)
            on delete cascade
);

create table if not exists usuarios
(
    id       int auto_increment
        primary key,
    nombre   varchar(45)  not null,
    password varchar(255) not null
);

create table if not exists usuarios_roles
(
    id      int auto_increment
        primary key,
    usuario int null,
    rol     int null,
    constraint usuarios_roles_ibfk_1
        foreign key (usuario) references usuarios (id),
    constraint usuarios_roles_ibfk_2
        foreign key (rol) references roles (id)
);

create index rol
    on usuarios_roles (rol);

create index usuario
    on usuarios_roles (usuario);

