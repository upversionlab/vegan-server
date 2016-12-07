CREATE TABLE Users (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(40) NOT NULL,
    facebook_id VARCHAR(40),
    google_id   VARCHAR(40),
    email       VARCHAR(100),
    password    VARCHAR(40)
);

CREATE TABLE Types (
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(40) NOT NULL
);

CREATE TABLE Companies (
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(40) NOT NULL,
    logo            VARCHAR(100),
    animal_testing  BOOLEAN,
    animal_origin   BOOLEAN,
    website         VARCHAR(100),
    user_creator_id INTEGER REFERENCES Users (id) ON DELETE SET NULL
);

CREATE TABLE Products (
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(40) NOT NULL,
    vegan           BOOLEAN,
    product_label   VARCHAR(100),
    company_id      INTEGER REFERENCES Companies (id) ON DELETE RESTRICT,
    type_id         INTEGER REFERENCES Types (id) ON DELETE RESTRICT,
    user_creator_id INTEGER REFERENCES Users (id) ON DELETE SET NULL
);