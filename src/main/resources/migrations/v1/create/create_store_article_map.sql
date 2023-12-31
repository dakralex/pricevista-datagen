create table Store_Article_Map
(
    store_id         integer                  not null,
    store_article_id varchar(50)              not null,
    article_id       integer                  not null,
    variant_id       integer                  not null,
    since            timestamp with time zone not null,
    constraint pk_Store_Article_Map primary key (store_id, store_article_id),
    constraint fk_Store_Article_Map_Store foreign key (store_id) references Store (store_id),
    constraint fk_Store_Article_Map_Article_Variant foreign key (article_id, variant_id) references Article_Variant (article_id, variant_id)
);