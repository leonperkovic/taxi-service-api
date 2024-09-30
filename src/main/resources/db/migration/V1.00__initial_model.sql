create table taxi_driver (
    id UUID primary key,
    name varchar(64) not null
);

create table scheduled_ride (
    id UUID primary key,
    client_name varchar(64),
    pickup_time timestamp not null,
    pickup_location varchar(128) not null,
    destination varchar(128) not null,
    driver_id UUID not null,
    foreign key (driver_id) references taxi_driver(id)
);
