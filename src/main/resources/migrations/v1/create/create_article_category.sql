create table Article_Category
(
    article_id  integer not null,
    category_id integer not null,
    constraint pk_Article_Category primary key (article_id, category_id),
    constraint fk_Article_Category_Article foreign key (article_id) references Article (id),
    constraint fk_Article_Category_Category foreign key (category_id) references Category (id)
)
