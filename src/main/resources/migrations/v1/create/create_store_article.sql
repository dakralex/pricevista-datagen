create table Store_Article
(
    store_id         integer     not null,
    store_article_id varchar(50) not null,
    article_id       integer     not null,
    since            timestamp   not null,
    constraint pk_Store_Article primary key (store_id, store_article_id),
    constraint fk_Store_Article_Store foreign key (store_id) references Store (id),
    constraint fk_Store_Article_Article_Variant foreign key (article_id) references Article (id)
)
