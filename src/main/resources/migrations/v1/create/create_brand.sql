create table Brand
(
    id       integer not null,
    logo_url varchar(2083),
    constraint pk_Brand primary key (id),
    constraint fk_Brand_Company foreign key (id) references Company (id) on delete cascade
)
