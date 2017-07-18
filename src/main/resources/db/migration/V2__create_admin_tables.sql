CREATE TABLE pending_products (
    id SERIAL PRIMARY KEY,
    upload_date VARCHAR(20) NOT NULL,
    barcode VARCHAR(100),
    ingredients VARCHAR(500),
    nutritional_facts VARCHAR(500)
);
