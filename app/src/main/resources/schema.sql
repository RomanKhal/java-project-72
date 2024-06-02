

CREATE TABLE IF NOT EXISTS urls(
id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
name varchar(255),
createdAt timestamp,
CONSTRAINT pk_urls PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS url_checks (
id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
statusCode INT ,
title VARCHAR(255),
h1 VARCHAR(255),
description TEXT,
urlId BIGINT REFERENCES urls (id),
createdAt TIMESTAMP,
CONSTRAINT pk_url_checks PRIMARY KEY (id));

