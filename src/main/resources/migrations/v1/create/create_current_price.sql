create table Current_Price
(
    store_id   integer   not null,
    article_id integer   not null,
    price      integer   not null,
    changed_at timestamp not null,
    updated_at timestamp not null,
    constraint pk_Current_Price primary key (store_id, article_id, price),
    constraint fk_Current_Price_Store foreign key (store_id) references Store (id) on delete cascade,
    constraint fk_Current_Price_Article foreign key (article_id) references Article (id) on delete cascade
)
