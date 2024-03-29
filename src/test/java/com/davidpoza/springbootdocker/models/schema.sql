create sequence article_seq start with 1 increment by 50;
create sequence bulletin_seq start with 1 increment by 50;
create sequence cured_article_seq start with 1 increment by 50;
create sequence feed_seq start with 1 increment by 50;
create table article (published_at date, feed_id bigint, id bigint not null, full_content varchar(255), summary_content varchar(255), title varchar(255), url varchar(255), primary key (id));
create table bulletin (generation_completed_at date, generation_started_at date, published_at date, id bigint not null, primary key (id));
create table cured_article (published_at date, id bigint not null, content varchar(255), title varchar(255), primary key (id));
create table feed (id bigint not null, title varchar(255), url varchar(255), primary key (id));
alter table if exists article add constraint FKqft6wk7f6tx6mvm9h6ssa4ea1 foreign key (feed_id) references feed;
