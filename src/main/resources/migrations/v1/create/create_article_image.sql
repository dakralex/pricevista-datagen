create table Article_Image
(
    article_id integer       not null,
    image_id   integer generated always as identity,
    image_url  varchar(2083) not null,
    constraint pk_Article_Image primary key (article_id, image_id),
    constraint uc_Article_Image_article_image unique (article_id, image_url)
)
