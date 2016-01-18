# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table book (
  id                        bigint not null,
  title                     varchar(255),
  date                      timestamp,
  constraint pk_book primary key (id))
;

create table task (
  id                        bigint not null,
  label                     varchar(255),
  constraint pk_task primary key (id))
;

create sequence book_seq;

create sequence task_seq;




# --- !Downs

drop table if exists book cascade;

drop table if exists task cascade;

drop sequence if exists book_seq;

drop sequence if exists task_seq;

