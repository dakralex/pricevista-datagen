create table Country
(
    id     integer      not null,
    alpha2 char(2)      not null,
    alpha3 char(3)      not null,
    name   varchar(100) not null,
    constraint pk_Country primary key (id),
    constraint cc_Country_id check (id between 1 and 999),
    constraint uc_Country_alpha2 unique (alpha2),
    constraint uc_Country_alpha3 unique (alpha3),
    constraint uc_Country_name unique (name)
)
