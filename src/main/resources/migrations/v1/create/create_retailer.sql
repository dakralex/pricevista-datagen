create table Retailer
(
    retailer_id      integer not null,
    market_share     numeric(6, 3),
    annual_revenue   numeric(32, 4),
    profit_margin    numeric(6, 3),
    working_currency char(3) not null,
    constraint pk_Retailer primary key (retailer_id),
    constraint fk_Retailer_Company foreign key (retailer_id) references Company (company_id) on delete cascade
);