create table usr
(
    id          bigserial   not null primary key,
    first_name  varchar(50) not null,
    second_name varchar(50) not null,
    password    varchar(50) not null,
    email       varchar(50) not null,
    active      boolean     not null
);

alter table usr
    add constraint unq_email_usr unique (email);

create table user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    constraint pk_user_role
        primary key (user_id, role_id)
);

create table roles
(
    id   bigserial   not null primary key,
    name varchar(20) not null
);

create table products
(
    id          bigserial     not null primary key,
    description varchar(2048) not null,
    price       integer       not null,
    category_id bigint        not null,
    details_id  bigint,
    stock       int           not null,
    active      bool          not null,
    photo       varchar(2048),
    name        varchar(255)  not null
);

create table category
(
    id        bigserial   not null primary key,
    title     varchar(50) not null unique,
    parent_id bigint      null
);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_user_id_USR FOREIGN KEY (user_id) REFERENCES usr (id) ON DELETE RESTRICT
ALTER TABLE user_roles
    ADD CONSTRAINT fk_role_id_ROLES FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE RESTRICT;

ALTER TABLE products
    ADD CONSTRAINT fk_category_CATEGORY FOREIGN KEY (category_id) REFERENCES category ON DELETE RESTRICT;