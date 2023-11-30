CREATE TABLE if not exists users (
    id serial primary key,
    tx_username varchar(54) unique not null,
    tx_password varchar(255) not null,
    dt_birthday date not null
);