create table car (
    id bigint not null auto_increment,
    brand varchar(255),
    manufacture_date timestamp,
    model varchar(255),
    vin varchar(255),
    primary key (id)
    );