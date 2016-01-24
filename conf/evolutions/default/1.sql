# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table article (
  id                        bigint not null,
  title                     varchar(255),
  j_date                    timestamp,
  date                      timestamp,
  number_of_pages           integer,
  magazine_id               bigint,
  constraint pk_article primary key (id))
;

create table book (
  id                        bigint not null,
  title                     varchar(255),
  j_date                    timestamp,
  date                      timestamp,
  number_of_pages           integer,
  constraint pk_book primary key (id))
;

create table magazine (
  id                        bigint not null,
  title                     varchar(255),
  j_date                    timestamp,
  date                      timestamp,
  number_of_pages           integer,
  constraint pk_magazine primary key (id))
;

create table task (
  id                        bigint not null,
  title                     varchar(255),
  j_date                    timestamp,
  date                      timestamp,
  number_of_pages           integer,
  label                     varchar(255),
  constraint pk_task primary key (id))
;

create sequence article_seq;

create sequence book_seq;

create sequence magazine_seq;

create sequence task_seq;

alter table article add constraint fk_article_magazine_1 foreign key (magazine_id) references magazine (id);
create index ix_article_magazine_1 on article (magazine_id);



# --- !Downs

drop table if exists article cascade;

drop table if exists book cascade;

drop table if exists magazine cascade;

drop table if exists task cascade;

drop sequence if exists article_seq;

drop sequence if exists book_seq;

drop sequence if exists magazine_seq;

drop sequence if exists task_seq;

