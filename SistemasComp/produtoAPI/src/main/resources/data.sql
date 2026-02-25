create table produto
(
    id   int primary key,
    name varchar(100) not null,
    description varchar(200) not null,
    price numeric (18,2) not null,
    weight numeric (5,2) not null,
    unitId int not null
);