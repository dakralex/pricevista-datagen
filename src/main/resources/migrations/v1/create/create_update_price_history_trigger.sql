create or replace trigger update_price_history_trigger
    after insert or update
    on Current_Price
    for each row
begin
    insert into Recorded_Price (store_id, article_id, changed_at, price)
    values (:new.store_id, :new.article_id, :new.changed_at, :new.price);
end;