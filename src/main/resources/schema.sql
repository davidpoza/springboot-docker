create table article (published_at date, cured_article_id uuid, feed_id uuid, id uuid not null, full_content varchar(255), summary_content varchar(255), title varchar(255), url varchar(255), primary key (id));
create table bulletin (generation_completed_at date, generation_started_at date, published_at date, id uuid not null, primary key (id));
create table cured_article (published_at date, bulletin_id uuid, id uuid not null, content varchar(255), title varchar(255), primary key (id));
create table feed (id uuid not null, title varchar(255), url varchar(255), primary key (id));
alter table if exists article add constraint FK4uipg5rdpg6pw3taao04erajk foreign key (cured_article_id) references cured_article;
alter table if exists article add constraint FKqft6wk7f6tx6mvm9h6ssa4ea1 foreign key (feed_id) references feed;
alter table if exists cured_article add constraint FKganv6oqhbajktghfmbcui83je foreign key (bulletin_id) references bulletin;
