create table if not exists student(
    id bigint primary key auto_increment,
    name varchar(50) not null null,
    birthday datetime,
    gender char(1)
);

show databases ;
