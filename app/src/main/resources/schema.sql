create table urls(
id serial primary key not null,
name varchar(255),
createdAt timestamp,
CONSTRAINT urls PRIMARY KEY (id)
);
