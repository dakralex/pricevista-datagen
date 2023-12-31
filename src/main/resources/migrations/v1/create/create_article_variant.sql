create table Article_Variant
(
    article_id integer        not null,
    variant_id integer generated always as identity,
    unit       varchar(32)    not null,
    quantity   numeric(18, 4) not null,
    weightable number(1)      not null, -- Whether the price description is a weightable (stored as number as OracleDB does not have booleans)
    constraint pk_Article_Variant primary key (article_id, variant_id),
    constraint fk_Article_Variant_Parent foreign key (article_id) references Article (article_id)
);