create table Article
(
    article_id     integer generated always as identity,
    name           varchar(100) not null,
    description    clob,
    origin_country char(3),
    image_url      varchar(2083),
    brand_id       integer,
    category_id    integer,
    constraint pk_Article primary key (article_id),
    constraint uc_Article_Name_Brand unique (name, brand_id),
    constraint fk_Article_Brand foreign key (brand_id) references Brand (brand_id),
    constraint fk_Article_Category foreign key (category_id) references Category (category_id)
);