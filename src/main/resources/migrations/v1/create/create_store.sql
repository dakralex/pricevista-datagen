create table Store
(
    store_id       integer generated always as identity,
    retailer_id    integer not null,
    street_address varchar(150),
    postal_code    varchar(15),
    locality       varchar(100),
    admin_area     varchar(100),
    country        char(3) not null,
    url_address    varchar(2083),
    constraint pk_Store primary key (store_id),
    constraint uc_Store_Retailer unique (store_id, retailer_id),
    constraint fk_Store_Retailer foreign key (retailer_id) references Retailer (retailer_id)
);