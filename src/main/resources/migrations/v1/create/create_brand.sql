create table Brand
(
    company_id integer not null,
    logo_url   varchar(2083),
    constraint pk_Brand primary key (company_id),
    constraint fk_Brand_Company foreign key (company_id) references Company (id) on delete cascade
)
