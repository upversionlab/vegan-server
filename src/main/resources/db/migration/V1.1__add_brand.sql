CREATE TABLE Brand (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    logo_url VARCHAR(100),
    animal_tests BOOLEAN,
    animal_resources BOOLEAN,
    website VARCHAR(100),
    company_id INTEGER REFERENCES Company (id)
);

CREATE TABLE BrandCertification (
    id SERIAL PRIMARY KEY,
    brand_id INTEGER NOT NULL REFERENCES Brand (id),
    certification_id INTEGER NOT NULL REFERENCES Certification (id)
);

ALTER TABLE Product DROP COLUMN company_id;
ALTER TABLE Product ADD COLUMN brand_id INTEGER NOT NULL REFERENCES Brand (id);
