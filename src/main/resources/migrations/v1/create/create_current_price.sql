create table Current_Price
(
    store_id   integer   not null,
    article_id integer   not null,
    value      integer   not null,
    changed_at timestamp not null,
    updated_at timestamp not null,
    constraint pk_Current_Price primary key (store_id, article_id, value),
    constraint fk_Current_Price_Store foreign key (store_id) references Store (id),
    constraint fk_Current_Price_Article foreign key (article_id) references Article (id)
)
