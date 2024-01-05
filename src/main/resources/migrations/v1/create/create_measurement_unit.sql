create table Measurement_Unit
(
    id     integer generated by default on null as identity,
    symbol varchar(10) not null,
    name   varchar(50) not null,
    constraint pk_Measurement_Unit primary key (id),
    constraint uc_Measurement_Unit_symbol unique (symbol),
    constraint uc_Measurement_Unit_name unique (name)
)