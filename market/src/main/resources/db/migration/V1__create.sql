drop table if exists users;
create table users (
    id                      bigserial primary key,
    username                varchar(50) not null unique,
    password                varchar(255) not null,
    email                   varchar(100) unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

drop table if exists roles;
create table roles (
    id                      bigserial primary key,
    name                    varchar(50) not null unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

drop table if exists users_roles;
create table users_roles (
    user_id                 bigint not null references users (id),
    role_id                 bigint not null references roles (id),
    primary key (user_id, role_id)
);

drop table if exists products;
create table products (
    id                      bigserial primary key,
    title                   varchar(100),
    category                varchar(100),
    price                   bigint,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

drop table if exists orders;
create table orders (
    id                      bigserial primary key,
    owner_id                bigint references users (id),
    price                   bigint,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

drop table if exists order_items;
create table order_items (
    id                      bigserial primary key,
    order_id                bigint references orders (id),
    product_id              bigint references products (id),
    quantity                int,
    price_per_product       bigint,
    price                   bigint,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);



