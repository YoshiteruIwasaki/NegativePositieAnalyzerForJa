# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table category (
  category_id               bigint auto_increment not null,
  title                     varchar(255) not null,
  link                      longtext,
  consumer_key              varchar(255),
  consumer_secret           varchar(255),
  access_token              varchar(255),
  access_token_secret       varchar(255),
  create_date               datetime not null,
  update_date               datetime not null,
  constraint pk_category primary key (category_id))
;

create table item (
  item_id                   bigint auto_increment not null,
  category_id               bigint not null,
  title                     varchar(255) not null,
  search_word               varchar(255),
  point                     double,
  description               longtext not null,
  link                      longtext not null,
  pub_date                  datetime,
  picture                   longtext,
  picture_source            varchar(255),
  news_item_title           varchar(255),
  news_item_snippet         longtext,
  news_item_url             longtext,
  news_item_source          varchar(255),
  create_date               datetime not null,
  update_date               datetime not null,
  constraint pk_item primary key (item_id))
;

create table log (
  log_id                    bigint auto_increment not null,
  item_id                   bigint not null,
  category_id               bigint not null,
  date                      datetime not null,
  ranking                   integer not null,
  create_date               datetime not null,
  update_date               datetime not null,
  constraint pk_log primary key (log_id))
;

create table ranking (
  ranking_id                bigint auto_increment not null,
  item_id                   bigint not null,
  category_id               bigint not null,
  total_count               integer not null,
  count_neutral             integer not null,
  count_negative            integer not null,
  count_positive            integer not null,
  date                      datetime not null,
  ranking                   integer not null,
  comparison                integer not null,
  create_date               datetime not null,
  update_date               datetime not null,
  constraint pk_ranking primary key (ranking_id))
;

create table tweet (
  tweet_id                  bigint auto_increment not null,
  category_id               bigint not null,
  item_id                   bigint,
  tweeter_id                bigint not null,
  search_word               varchar(255) not null,
  created_at                datetime,
  source                    varchar(255) not null,
  text                      longtext not null,
  point                     double,
  raw_point                 double,
  tweeter_user_id           bigint,
  tweeter_uset_name         varchar(255),
  tweeter_user_screen_name  varchar(255),
  tweeter_user_url          varchar(255),
  tweeter_user_image_url    varchar(255),
  create_date               datetime not null,
  update_date               datetime not null,
  constraint pk_tweet primary key (tweet_id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table category;

drop table item;

drop table log;

drop table ranking;

drop table tweet;

SET FOREIGN_KEY_CHECKS=1;

