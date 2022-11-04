drop table if exists trade;
drop table if exists purchase;
drop table if exists sale;
drop table if exists user;
drop table if exists product;

create table product
(
    product_id    bigint not null auto_increment,
    brand         varchar(255),
    name          varchar(255),
    category      varchar(255),
    img_url       varchar(255),
    model_number  varchar(255),
    release_price varchar(255),
    primary key (product_id)
);

create table purchase
(
    purchase_id      bigint not null auto_increment,
    bid_price        varchar(255),
    product_size     varchar(255),
    shipping_address varchar(255),
    trade_status     integer,
    user_id          bigint,
    id               bigint,
    primary key (purchase_id),
    constraint FK_purchase_product foreign key (id) references product (product_id)
);

create table sale
(
    sale_id        bigint not null auto_increment,
    bid_price      varchar(255),
    product_size   varchar(255),
    return_address varchar(255),
    trade_status   integer,
    user_id        bigint,
    id             bigint,
    primary key (sale_id),
    constraint FK_sale_product foreign key (id) references product (product_id)
);

create table trade
(
    id          bigint not null auto_increment,
    status      integer,
    purchase_id bigint,
    sale_id     bigint,
    primary key (id),
    constraint FK_trade_purchase foreign key (purchase_id) references purchase (purchase_id),
    constraint FK_trade_sale foreign key (sale_id) references sale (sale_id)

);

create table user
(
    id               bigint not null auto_increment,
    email            varchar(255),
    encoded_password varchar(255),
    phone            varchar(255),
    role             varchar(10),
    username         varchar(255),
    primary key (id)
);