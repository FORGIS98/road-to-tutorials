DROP TABLE client IF EXISTS;

CREATE TABLE client  (
    client_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    idParty VARCHAR(50),
    commercialBrand VARCHAR(50),
    contactMediumType VARCHAR(50),
    contactMediumValue VARCHAR(50),
    verified VARCHAR(50)
);