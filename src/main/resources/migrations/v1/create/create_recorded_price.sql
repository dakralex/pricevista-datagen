create table Recorded_Price
(
    store_id   integer   not null,
    article_id integer   not null,
    changed_at timestamp not null,
    price      integer   not null,
    constraint pk_Recorded_Price primary key (store_id, article_id, changed_at),
    constraint fk_Recorded_Price_Store foreign key (store_id) references Store (id) on delete cascade,
    constraint fk_Recorded_Price_Article foreign key (article_id) references Article (id) on delete cascade
)
