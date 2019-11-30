create table usr
(
    id          bigserial   not null primary key,
    first_name  varchar(50) not null,
    second_name varchar(50) not null,
    password    varchar(50) not null,
    email       varchar(50) not null,
    active      boolean     not null
);

alter table usr add constraint unq_email_usr unique (email);

create table user_roles
(
    user_id bigint not null,
    role_id bigint not null
);

create table roles
(
    id   bigserial   not null primary key,
    name varchar(20) not null
);

create table products
(
    id           bigserial     not null primary key,
    description  varchar(2048) not null,
    price        integer       not null,
    details_id   bigint,
    warehouse_id bigint,
    photo        varchar(2048),
    name         varchar(255)  not null
);

create table category
(
  id bigserial not null  primary key,
  title varchar(50) not null unique,
  parent_id int8 null
);

ALTER TABLE user_roles ADD CONSTRAINT user_role_ROLES FOREIGN KEY (user_id) REFERENCES usr ON DELETE RESTRICT;
ALTER TABLE user_roles ADD CONSTRAINT role_id_USER_ROLES FOREIGN KEY (user_id) REFERENCES usr ON DELETE RESTRICT;