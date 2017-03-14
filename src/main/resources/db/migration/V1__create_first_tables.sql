CREATE TABLE VeganFlags (
    id SERIAL PRIMARY KEY,
    meat BOOLEAN,
    egg BOOLEAN,
    milk BOOLEAN,
    honey BOOLEAN,
    gluten BOOLEAN,
    organic BOOLEAN
);

CREATE TABLE Certification (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    logo_url VARCHAR(100)
);

CREATE TABLE Company (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    logo_url VARCHAR(100),
    animal_tests BOOLEAN,
    animal_resources BOOLEAN,
    website VARCHAR(100)
);

CREATE TABLE CompanyCertification (
    id SERIAL PRIMARY KEY,
    company_id INTEGER NOT NULL REFERENCES Company (id),
    certification_id INTEGER NOT NULL REFERENCES Certification (id)
);

CREATE TABLE Ingredient (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    vegan_flags_id INTEGER REFERENCES VeganFlags (id)
);

CREATE TABLE Category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TYPE ProductType AS ENUM ('food', 'cleaning', 'hygiene');

CREATE TABLE Product (
    id SERIAL PRIMARY KEY,
    barcode VARCHAR(100) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    company_id INTEGER NOT NULL REFERENCES Company (id),
    package_image_url VARCHAR(100),
    type ProductType
);

CREATE TABLE ProductCertification (
    id SERIAL PRIMARY KEY,
    product_id INTEGER NOT NULL REFERENCES Product (id),
    certification_id INTEGER NOT NULL REFERENCES Certification (id)
);

CREATE TABLE ProductIngredient (
    id SERIAL PRIMARY KEY,
    product_id INTEGER NOT NULL REFERENCES Product (id),
    ingredient_id INTEGER NOT NULL REFERENCES Ingredient (id),
    vegan_flags_id INTEGER REFERENCES VeganFlags (id)
);

CREATE TABLE ProductCategory (
    id SERIAL PRIMARY KEY,
    product_id INTEGER NOT NULL REFERENCES Product (id),
    category_id INTEGER NOT NULL REFERENCES Category (id)
);

CREATE TABLE NutritionFact (
    id SERIAL PRIMARY KEY,
    product_id INTEGER NOT NULL REFERENCES Product (id),
    name VARCHAR(100),
    quantity NUMERIC,
    unity VARCHAR(10)
);