
create table users
(

    id              bigserial primary key ,
    username        varchar(32),
    email           varchar(32),
    password        varchar(100),
    balance         numeric(8, 2),
    is_deprecated   boolean
);

create table roles
(
    id bigserial primary key,
    title varchar(36) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table users_roles
(
    user_id bigint not null references users(id),
    role_id bigint not null references roles(id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (user_id, role_id)
);

create table hashtags
(
    id              bigserial primary key ,
    title           varchar(255)
);



create table companies
(
    id              bigserial primary key,
    title           varchar(32),
    logo            varchar(16),
    user_id         bigint references users(id)
);

create table discounts
(
    id              bigserial primary key,
    amount          integer,
    end_dt          timestamp default CURRENT_TIMESTAMP
);

create table products
(
    id              bigserial primary key,
    title           varchar(255),
    price           numeric(8, 2),
    remains         bigint,
    specifications  varchar(255),
    score           int,
    company_id      bigint references companies(id),
    discount_id     bigint references discounts(id),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table comments
(
    id              bigserial primary key,
    comment_text    varchar(1000),
    score           int,
    user_id         bigint references users(id),
    product_id      bigint references products(id)
);

create table products_hashtags
(
    product_id      bigint not null references products(id),
    hashtag_id      bigint not null references hashtags(id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (product_id, hashtag_id)
);

insert into users (username, email, password, balance, is_deprecated)
values ('Sergey', 'sergey@mail.ru', '$2a$12$a.1ClOfpqbqjUTiZmE0IdeDe88XtvS3WOl8LrMQHY.EKKrl6hFING', 40000.00, false),
       ('Andrey', 'andrey@mail.ru', '$2a$12$a.1ClOfpqbqjUTiZmE0IdeDe88XtvS3WOl8LrMQHY.EKKrl6hFING', 70000.00, false),
       ('Ivan', 'ivan@mail.ru', '$2a$12$a.1ClOfpqbqjUTiZmE0IdeDe88XtvS3WOl8LrMQHY.EKKrl6hFING', 50000.00, false);

insert into roles (title)
values  ('ROLE_USER'),
        ('ROLE_ADMIN');

insert into users_roles(user_id, role_id)
values (1, 1),
       (2, 2),
       (1, 2),
       (3, 1);

insert into comments (comment_text, score)
values ('perfect', 5),
       ('good', 4),
       ('not bad', 3);

insert into companies (title, logo, user_id)
values ('x5group', '5', 1),
       ('magnit', 'mag', 2),
       ('diksi', 'xXx', 3);

insert into discounts (amount)
values (5),
       (10),
       (25);

insert into products (title, price, remains, specifications, score, company_id, discount_id)
values ('bread', 40.50, 20,'10x5x2', 5, 1, 1),
       ('milk', 80.70, 50, '20x10x10', 5, 2, 2),
       ('choco', 100.20, 10, '5x5x2', 4, 3, 3);



