CREATE TABLE photos
(
    id       SERIAL PRIMARY KEY NOT NULL,
    hash     uuid               NOT NULL,
    position integer            NOT NULL,
    type     varchar(255)       NOT NULL,
    name     varchar            NOT NULL,
    data     bytea              NOT NULL
);