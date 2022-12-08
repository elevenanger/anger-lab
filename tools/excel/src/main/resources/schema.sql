create sequence hibernate_sequence
    increment 1
    minvalue 1000
    start 1001
    cache 1;

create table if not exists test_data(
    id serial primary key,
    line_no varchar(50) not null,
    acc varchar(50) not null,
    rec_name varchar(50) not null,
    amount decimal not null,
    summary varchar(10) not null
);

select nextval('hibernate_sequence') ;

select * from test_data;