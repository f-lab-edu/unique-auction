drop table if exists trade;
drop table if exists review;
drop table if exists purchase;
drop table if exists sale;
drop table if exists user;
drop table if exists product;

create table product
(
    product_id          bigint not null auto_increment,
    brand               varchar(255),
    name                varchar(255),
    category            varchar(255),
    img_url             varchar(255),
    model_number        varchar(255),
    release_price       varchar(255),
    created_by          varchar(255),
    created_date        timestamp,
    last_modified_by    varchar(255),
    last_modified_date  timestamp DEFAULT CURRENT_TIMESTAMP,
    primary key (product_id)
);

create table purchase
(
    purchase_id         bigint not null auto_increment,
    bid_price           varchar(255),
    product_size        varchar(255),
    shipping_address    varchar(255),
    trade_status        integer,
    user_id             bigint,
    product_id          bigint,
    created_by          varchar(255),
    created_date        timestamp DEFAULT CURRENT_TIMESTAMP,
    last_modified_by    varchar(255),
    last_modified_date  timestamp DEFAULT CURRENT_TIMESTAMP,
    primary key (purchase_id),
    constraint FK_purchase_product foreign key (product_id) references product (product_id)
);

create table sale
(
    sale_id             bigint not null auto_increment,
    bid_price           varchar(255),
    product_size        varchar(255),
    return_address      varchar(255),
    trade_status        integer,
    user_id             bigint,
    product_id          bigint,
    created_by          varchar(255),
    created_date        timestamp DEFAULT CURRENT_TIMESTAMP,
    last_modified_by    varchar(255),
    last_modified_date  timestamp DEFAULT CURRENT_TIMESTAMP,
    primary key (sale_id),
    constraint FK_sale_product foreign key (product_id) references product (product_id)
);

create table trade
(
    id                  bigint not null auto_increment,
    status              integer,
    purchase_id         bigint,
    sale_id             bigint,
    created_by          varchar(255),
    created_date        timestamp DEFAULT CURRENT_TIMESTAMP,
    last_modified_by    varchar(255),
    last_modified_date  timestamp DEFAULT CURRENT_TIMESTAMP,
    primary key (id),
    constraint FK_trade_purchase foreign key (purchase_id) references purchase (purchase_id),
    constraint FK_trade_sale foreign key (sale_id) references sale (sale_id)

);

create table user
(
    user_id             bigint not null auto_increment,
    email               varchar(255),
    encoded_password    varchar(255),
    phone               varchar(255),
    role                varchar(10),
    username            varchar(255),
    created_by          varchar(255),
    created_date        timestamp DEFAULT CURRENT_TIMESTAMP,
    last_modified_by    varchar(255),
    last_modified_date  timestamp DEFAULT CURRENT_TIMESTAMP,
    primary key (user_id)
);

create table review
(
    review_id   bigint not null auto_increment,
    score       integer,
    content     varchar(100),
    user_id     bigint,
    product_id  bigint,
    created_by          varchar(255),
    created_date        timestamp DEFAULT CURRENT_TIMESTAMP,
    last_modified_by    varchar(255),
    last_modified_date  timestamp DEFAULT CURRENT_TIMESTAMP,
    primary key (review_id),
    constraint FK_review_user foreign key (user_id) references user (user_id),
    constraint FK_review_product foreign key (product_id) references product (product_id)

);
