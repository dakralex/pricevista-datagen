create or replace procedure update_price_history(
    p_store_id in integer,
    p_article_id in integer,
    p_price in integer,
    p_changed_at in timestamp default current_timestamp
) as
    p_latest_price integer;
    p_updated_at   timestamp := current_timestamp;
begin
    select price
    into p_latest_price
    from Current_Price
    where store_id = p_store_id
      and article_id = p_article_id;

    if p_price <> p_latest_price or p_latest_price is null then
        update Current_Price
        set price      = p_price,
            changed_at = p_changed_at
        where store_id = p_store_id
          and article_id = p_article_id;

        if sql%rowcount = 0 then
            insert into Current_Price (store_id, article_id, price,
                                       changed_at, updated_at)
            values (p_store_id, p_article_id, p_price,
                    p_changed_at, p_updated_at);
        end if;

        update Recorded_Price
        set price      = p_price,
            changed_at = p_changed_at
        where store_id = p_store_id
          and article_id = p_article_id;

        if sql%rowcount = 0 then
            insert into Recorded_Price (store_id, article_id, changed_at, price)
            values (p_store_id, p_article_id, p_changed_at, p_price);
        end if;
    end if;
end;
