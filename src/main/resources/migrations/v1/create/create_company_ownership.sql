create table Company_Ownership
(
    owner_id integer not null,
    ownee_id integer not null,
    constraint pk_Company_Ownership primary key (owner_id, ownee_id),
    constraint fk_Company_Ownership_Owner foreign key (owner_id) references Company (company_id),
    constraint fk_Company_Ownership_Ownee foreign key (ownee_id) references Company (company_id)
);