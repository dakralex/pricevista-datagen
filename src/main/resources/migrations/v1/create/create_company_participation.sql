create table Company_Participation
(
    stakeholder_id integer not null,
    company_id     integer not null,
    constraint pk_Company_Participation primary key (stakeholder_id, company_id),
    constraint fk_Company_Participation_Stakeholder foreign key (stakeholder_id) references Company (id) on delete cascade,
    constraint fk_Company_Participation_Company foreign key (company_id) references Company (id) on delete cascade
)
