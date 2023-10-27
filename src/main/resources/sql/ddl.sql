CREATE TABLE admin (
                       id uuid primary key,
                       name varchar not null,
                       phone_number varchar not null,
                       email varchar NOT NULL,
                       age int check ( age>0 ) not null,
                       birth_date date not null
);

CREATE TABLE note(
                     id int primary key generated by default as identity,
                     section varchar not null ,
                     advice varchar not null ,
                     date_of_creation date,
                     who_created varchar
);
CREATE TABLE person(
                       id bigint generated by default as identity primary key,
                       name varchar not null,
                       age int check ( age>0 ) not null,
                       birth_date date not null,
                       email varchar not null,
                       country varchar not null
);
CREATE TABLE question(
                         id bigint generated by default as identity PRIMARY KEY,
                         issue varchar not null,
                         section varchar not null,
                         who_asked varchar not null
);