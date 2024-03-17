create table article (published_at date, cured_article_id uuid, feed_id uuid, id uuid not null, job_id uuid unique, full_content varchar(255), summary_content varchar(255), title varchar(255), url varchar(255), primary key (id));
create table bulletin (generation_completed_at date, generation_started_at date, published_at date, id uuid not null, headlines bytea, primary key (id));
create table cured_article (published_at date, bulletin_id uuid, id uuid not null, content varchar(255), title varchar(255), primary key (id));
create table cured_articles_tags (cured_article_id uuid not null, tag_id uuid not null, primary key (cured_article_id, tag_id));
create table feed (id uuid not null, title varchar(255), url varchar(255), primary key (id));
create table job (created_at date, finished_at date, state smallint check (state between 0 and 4), updated_at date, id uuid not null, primary key (id));
create table tag (id uuid not null, primary key (id));
alter table if exists article add constraint FK4uipg5rdpg6pw3taao04erajk foreign key (cured_article_id) references cured_article;
alter table if exists article add constraint FKqft6wk7f6tx6mvm9h6ssa4ea1 foreign key (feed_id) references feed;
alter table if exists article add constraint FKfi8ym4ydeiqty8vy249w7hy55 foreign key (job_id) references job;
alter table if exists cured_article add constraint FKganv6oqhbajktghfmbcui83je foreign key (bulletin_id) references bulletin;
alter table if exists cured_articles_tags add constraint FK61nwmp0ngr9si76jq9qnrhc99 foreign key (tag_id) references tag;
alter table if exists cured_articles_tags add constraint FKaty8wq2sy1f3uf61hm1i0wsfb foreign key (cured_article_id) references cured_article;
