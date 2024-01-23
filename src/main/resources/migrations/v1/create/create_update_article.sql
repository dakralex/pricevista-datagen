create or replace procedure update_article(
    p_article_id in integer,
    p_article_name in varchar2 default null,
    p_article_brand_name in varchar2 default null,
    p_article_description in clob default null
) as
    lv_brand_id integer := null;
begin
    if p_article_id is null then
        raise_application_error(-20000, 'The article id must not be null.');
    end if;

    if p_article_brand_name is not null then
        -- Check if the brand already exists
        begin
            select b.company_id
            into lv_brand_id
            from Brand b
                     left join Company c on b.company_id = c.id
            where lower(c.short_name) = lower(trim(p_article_brand_name));
        exception
            when no_data_found then lv_brand_id := null;
        end;

        if lv_brand_id is null then
            -- Insert new entry into Company and Brand
            insert into Company (short_name)
            values (p_article_brand_name)
            returning id into lv_brand_id;

            insert into Brand (company_id) values (lv_brand_id);
        end if;
    end if;

    -- Updates the Article accordingly for the things that are set
    update Article
    set name        = nvl(p_article_name, name),
        brand_id    = nvl(lv_brand_id, brand_id),
        description = nvl(p_article_description, description)
    where id = p_article_id;

    -- If nothing was updated, notify the user of the procedure
    if sql%rowcount = 0 then
        raise_application_error(-20000, 'Nothing was updated.');
    end if;
end;