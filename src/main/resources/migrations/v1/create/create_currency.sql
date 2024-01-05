create table Currency
(
    id           integer      not null,
    alpha3       char(3)      not null,
    sub_exponent integer      not null,
    symbol       varchar(10)  not null,
    sub_symbol   varchar(10)  not null,
    name         varchar(100) not null,
    constraint pk_Currency primary key (id),
    constraint cc_Currency_id check (id between 1 and 999),
    constraint uc_Currency_alpha3 unique (alpha3),
    constraint uc_Currency_name unique (name)
)
