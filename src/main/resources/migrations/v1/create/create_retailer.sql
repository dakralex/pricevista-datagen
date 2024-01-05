create table Retailer
(
    id          integer not null,
    website_url varchar(2083),
    constraint pk_Retailer primary key (id),
    constraint fk_Retailer_Company foreign key (id) references Company (id) on delete cascade
)
