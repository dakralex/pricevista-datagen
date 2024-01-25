create or replace procedure update_article(
    p_article_id in integer,
    p_article_name in varchar default null,
    p_article_brand_name in varchar default null,
    p_article_description in clob default null,
    p_update_count out integer
) as
    lv_brand_id         integer := null;
    lv_current_brand_id integer := null;
    lv_brand_usages     integer := 0;
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

    if p_article_brand_name is not null then
        -- Find out the current brand id of the article
        begin
            select a.brand_id
            into lv_current_brand_id
            from Article a
            where a.id = p_article_id;
        exception
            when no_data_found then lv_current_brand_id := null;
        end;

        -- If the brand is not used by any article, delete the entry
        if lv_current_brand_id is not null and
           lv_current_brand_id <> lv_brand_id then
            select count(*)
            into lv_brand_usages
            from Article
            where brand_id = lv_current_brand_id;

            -- If the brand is not used by any other article, delete the entry
            if lv_brand_usages = 0 then
                delete Brand where company_id = lv_current_brand_id;
                delete Company where id = lv_current_brand_id;
            end if;
        end if;
    end if;

    -- Will always be at least 1, but it is only to have an output
    -- because of the course requirements
    p_update_count := sql%rowcount;
end;



