create table if not exists purchase (
    id serial primary key,
    product varchar(50) not null ,
    price NUMERIC not null
);

create table if not exists account (
    id serial primary key ,
    full_name varchar(20) not null ,
    amount numeric not null
);
