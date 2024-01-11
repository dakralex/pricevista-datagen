create table Store_Category
(
    store_id          integer     not null,
    store_category_id varchar(50) not null,
    category_id       integer     not null,
    constraint pk_Store_Category primary key (store_id, store_category_id),
    constraint fk_Store_Category_Store foreign key (store_id) references Store (id),
    constraint fk_Store_Category_Category foreign key (category_id) references Category (id)
)
