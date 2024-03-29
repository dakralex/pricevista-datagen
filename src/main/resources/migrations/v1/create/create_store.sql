create table Store
(
    id          integer generated by default on null as identity,
    retailer_id integer not null,
    place_id    integer not null,
    currency_id integer not null,
    language_id integer not null,
    constraint pk_Store primary key (id),
    constraint fk_Store_Retailer foreign key (retailer_id) references Retailer (company_id),
    constraint fk_Store_Place foreign key (place_id) references Place (id),
    constraint fk_Store_Currency foreign key (currency_id) references Currency (id),
    constraint fk_Store_Language foreign key (language_id) references Language (id)
)
