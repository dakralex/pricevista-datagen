create table Article
(
    id                integer generated always as identity,
    brand_id          integer,
    name              varchar(100)   not null,
    description       clob,
    origin_country_id integer,
    article_unit_id   integer        not null,
    quantity          numeric(18, 4) not null,
    weightable        number(1)      not null,
    constraint pk_Article primary key (id),
    constraint uc_Article_Name_Brand unique (brand_id, name, article_unit_id, quantity),
    constraint fk_Article_Brand foreign key (brand_id) references Brand (company_id),
    constraint fk_Article_Country_origin foreign key (origin_country_id) references Country (id),
    constraint fk_Article_Unit foreign key (article_unit_id) references Article_Unit (id)
)
