SET search_path TO public;
DROP EXTENSION IF EXISTS "uuid-ossp";

CREATE EXTENSION "uuid-ossp" SCHEMA public;

alter table subscriptions drop constraint FKhro52ohfqfbay9774bev0qinr;

drop table if exists subscriptions cascade;

drop table if exists users cascade;

create table subscriptions (
  group_id uuid DEFAULT uuid_generate_v4 () not null,
  key varchar(255),
  user_id uuid,
  primary key (group_id)
);

create table users (
  id uuid DEFAULT uuid_generate_v4 () not null ,
  facebook_id varchar(255),
  image_url varchar(255),
  complete_name varchar(255),
  primary key (id)
);

alter table users
  add constraint user_key unique (complete_name);

alter table users
  add constraint UK_jmubronqnn4q0cwe2egqsgvnl unique (facebook_id);

alter table subscriptions
  add constraint FKhro52ohfqfbay9774bev0qinr
  foreign key (user_id)
  references users;


insert into users (facebook_id, image_url, complete_name) values
  ('a605ff5c-bbb7-40bc-9cf8-a46abcaf0aac', null, 'Gauthier Robert' ),
  ('55c6949a-dba0-4130-a84d-ff4fe4e01a09', null, 'Pierre Olivier'),
  ('8a2cce31-0522-488d-afd8-45a25e1183a0', null, 'Francois Xavier Tio'),
  ('8dabfdc0-fc16-4d41-9cbc-3f75ddc4c155', null, 'Corentin Dehem' );