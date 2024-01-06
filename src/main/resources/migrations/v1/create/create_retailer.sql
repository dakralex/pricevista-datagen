create table Retailer
(
    company_id  integer not null,
    website_url varchar(2083),
    constraint pk_Retailer primary key (company_id),
    constraint fk_Retailer_Company foreign key (company_id) references Company (id) on delete cascade
)
