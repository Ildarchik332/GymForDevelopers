CREATE TABLE admin (
                       id uuid primary key,
                       name varchar not null,
                       phone_number varchar not null,
                       email varchar NOT NULL,
                       age int check ( age>0 ) not null,
                       birth_date timestamp not null
);