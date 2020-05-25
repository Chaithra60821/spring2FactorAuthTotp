create table USER1(
    id int not null,
    username varchar_ignorecase(50) not null,
    password varchar_ignorecase(50) not null,
    active boolean not null,
    roles varchar_ignorecase(50) not null,
    secret varchar_ignorecase(50) not null
);