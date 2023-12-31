create table Store_Category_Map
(
    store_id          integer     not null,
    store_category_id varchar(50) not null,
    category_id       integer     not null,
    constraint pk_Store_Category_Map primary key (store_id, store_category_id),
    constraint fk_Store_Category_Map_Store foreign key (store_id) references Store (store_id),
    constraint fk_Store_Category_Map_Category foreign key (category_id) references Category (category_id)
);