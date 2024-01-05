create table Article
(
    id          integer generated always as identity,
    brand_id    integer,
    name        varchar(100)   not null,
    description clob,
    origin_id   integer,
    unit_id     integer        not null,
    quantity    numeric(18, 4) not null,
    weightable  number(1)      not null,
    constraint pk_Article primary key (id),
    constraint uc_Article_Name_Brand unique (brand_id, name),
    constraint uc_Article_Name_Quantity_Unit unique (name, unit_id, quantity),
    constraint fk_Article_Brand foreign key (brand_id) references Brand (id),
    constraint fk_Article_Place_origin foreign key (origin_id) references Place (id),
    constraint fk_Article_Measurement_Unit foreign key (unit_id) references Measurement_Unit (id)
)
