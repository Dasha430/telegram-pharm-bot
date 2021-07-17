create table forms(
    id   int not null primary key,
    name varchar not null
);

insert into forms (id, name) values (0, 'TABLET');
insert into forms(id, name) values (1, 'LIQUID');
insert into forms(id, name) values (2, 'CAPSULES');
insert into forms(id, name) values (3, 'TOPICAL');
insert into forms(id, name) values (4, 'INJECTION');
insert into forms(id, name) values (5, ' OTHER');


create table medicines (
    id          uuid not null primary key ,
    name        varchar not null,
    instruction text,
    form_id     int not null,

    constraint medicine_form_fk foreign key (form_id)
        references forms(id) on delete cascade
);


create table pharmacies(
    id              uuid not null primary key,
    name            varchar not null,
    address         varchar unique not null,
    address_geocode varchar,
    start_work_at   time not null,
    finish_work_at  time not null
);

create table medicine_in_pharmacy (
    medicine_id uuid not null,
    pharmacy_id uuid not null,
    price       numeric(6,2) not null,
    number      bigint not null,

    primary key (medicine_id, pharmacy_id),
    constraint medicine_in_pharmacy_medicine_fk foreign key (medicine_id)
                                  references medicines(id) on delete cascade,
    constraint medicine_in_pharmacy_pharmacy_fk foreign key (pharmacy_id)
                                  references pharmacies(id) on delete cascade
);

create table users(
    chat_id bigint not null primary key,
    current_address varchar,
    current_address_geocode varchar
);

create table searches (
    id serial primary key ,
    medicine_id uuid not null,
    user_id bigint not null,
    searched_at timestamp default now(),

    constraint search_medicine_fk foreign key (medicine_id)
                      references medicines(id),
    constraint search_user_fk foreign key (user_id)
                      references users(chat_id) on delete cascade
);



