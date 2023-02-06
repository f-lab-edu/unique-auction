
create table product
(
    product_id          bigint not null auto_increment,
    brand               varchar(255),
    name                varchar(255),
    category            varchar(255),
    img_url             varchar(255),
    model_number        varchar(255),
    release_price       varchar(255),
    created_date        timestamp DEFAULT CURRENT_TIMESTAMP,
    modified_date       timestamp DEFAULT CURRENT_TIMESTAMP,
    primary key (product_id)
);

create table trade
(
    id                  bigint not null auto_increment,
    publisher_id        bigint,
    buyer_id            bigint,
    seller_id           bigint,
    product_id          bigint,
    trade_status        integer,
    product_size        varchar(255),
    price               bigint,
    shipping_address    varchar(255),
    created_date        timestamp DEFAULT CURRENT_TIMESTAMP,
    modified_date       timestamp DEFAULT CURRENT_TIMESTAMP,
    primary key (id),
    constraint FK_trade_publisher foreign key (publisher_id) references user (user_id),
    constraint FK_trade_buyer foreign key (buyer_id) references user (user_id),
    constraint FK_trade_seller foreign key (seller_id) references user (user_id),
    constraint FK_trade_product foreign key (product_id) references product (product_id)
);

create table user
(
    user_id             bigint not null auto_increment,
    email               varchar(255),
    encoded_password    varchar(255),
    phone               varchar(255),
    role                varchar(10),
    username            varchar(255),
    version             integer(255),
    created_date        timestamp DEFAULT CURRENT_TIMESTAMP,
    modified_date       timestamp DEFAULT CURRENT_TIMESTAMP,
    primary key (user_id),
    UNIQUE KEY uk_email (email)
);

create table review
(
    review_id   bigint not null auto_increment,
    score       integer,
    content     varchar(100),
    user_id     bigint,
    product_id  bigint,
    created_date        timestamp DEFAULT CURRENT_TIMESTAMP,
    modified_date       timestamp DEFAULT CURRENT_TIMESTAMP,
    primary key (review_id),
    constraint FK_review_user foreign key (user_id) references user (user_id),
    constraint FK_review_product foreign key (product_id) references product (product_id)
);
