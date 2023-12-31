create table Brand
(
    brand_id     integer not null,
    product_line varchar(50),
    constraint pk_Brand primary key (brand_id),
    constraint fk_Brand_Company foreign key (brand_id) references Company (company_id) on delete cascade
);
