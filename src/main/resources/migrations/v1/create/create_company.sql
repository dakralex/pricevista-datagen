create table Company
(
    company_id   integer generated always as identity,
    company_name varchar(100) not null,
    locality     varchar(100),
    admin_area   varchar(100),
    country      char(3)      not null,
    constraint pk_Company primary key (company_id),
    constraint uc_Company_name unique (company_name)
);