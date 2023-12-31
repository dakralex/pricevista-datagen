create table Category
(
    category_id integer generated always as identity,
    name        varchar(50) not null,
    description clob,
    constraint pk_Category primary key (category_id),
    constraint uc_Category_name unique (name)
);