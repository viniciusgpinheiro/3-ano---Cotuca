create table produto
(
    id   integer primary key,
    name varchar(100) not null,
    description varchar(200) not null,
    price numeric (18,2) not null,
    weight numeric (5,2) not null,
    unitId integer not null
);

create table unidade (
    unitId integer primary key,
    description varchar(200) not null
);
