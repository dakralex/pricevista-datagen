create or replace view article_search_view as
select a.id                    as id,
       nvl(c.short_name, null) as brand_name,
       a.name                  as name,
       ai.image_url            as image_url,
       a.description           as description,
       u.label                 as article_unit,
       a.quantity              as article_quantity,
       cu.alpha3               as price_currency,
       cp.price                as price,
       cs.short_name           as store_name
from Article a
         left outer join Brand b on a.brand_id = b.company_id
         left outer join Company c on b.company_id = c.id
         left outer join Article_Image ai on a.id = ai.article_id
         left join Article_Unit u on a.article_unit_id = u.id
         left join Current_Price cp on a.id = cp.article_id
         left join Store s on cp.store_id = s.id
         left join Currency cu on s.currency_id = cu.id
         left join Company cs on s.retailer_id = cs.id