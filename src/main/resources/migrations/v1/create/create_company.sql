create table Company
(
    id         integer generated by default on null as identity,
    long_name  varchar(100),
    short_name varchar(100) not null,
    place_id   integer,
    constraint pk_Company primary key (id),
    constraint uc_Company_short_name unique (short_name),
    constraint fk_Company_Place foreign key (place_id) references Place (id)
)
