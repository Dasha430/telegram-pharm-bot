create table if not exists location
(
    id int auto_increment
    primary key,
    locationName varchar(50) not null,
    constraint locationName
    unique (locationName)
    );

create table if not exists problem
(
    id int auto_increment
    primary key,
    from_id int not null,
    to_id int not null,
    constraint problem_ibfk_1
    foreign key (from_id) references location (id),
    constraint problem_ibfk_2
    foreign key (to_id) references location (id)
    );

create index from_id
	on problem (from_id);

create index to_id
	on problem (to_id);

create table if not exists route
(
    id int auto_increment
    primary key,
    from_id int not null,
    to_id int not null,
    cost int null,
    constraint route_ibfk_1
    foreign key (from_id) references location (id),
    constraint route_ibfk_2
    foreign key (to_id) references location (id)
    );

create index from_id
	on route (from_id);

create index to_id
	on route (to_id);

create table if not exists solution
(
    problem_id int not null
    primary key,
    cost int null,
    constraint solution_ibfk_1
    foreign key (problem_id) references problem (id)
    );



