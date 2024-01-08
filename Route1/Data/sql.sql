CREATE DATABASE route;
\c route

CREATE TABLE route(
    idRoute SERIAL PRIMARY KEY,
    libelle VARCHAR(50),
    km DECIMAL
);

-- extremite sa ampivoany
CREATE TABLE place(
    idPlace SERIAL PRIMARY KEY,
    libelle VARCHAR(50)
);

CREATE TABLE prestation(
    idPrestation SERIAL PRIMARY KEY,
    libelle VARCHAR(50),
    plusEtat INT
);

CREATE TABLE etat_prestation(
    idEtat_prestation SERIAL PRIMARY KEY,
    idPrestation INT REFERENCES prestation(idPrestation),
    min INT,
    max INT
);

CREATE TABLE prioritaire(
    idPrioritaire SERIAL PRIMARY KEY,
    libelle VARCHAR(50),
    idPlace INT REFERENCES place(idPlace)
);

CREATE TABLE petiteRoute(
    idPetiteRoute SERIAL PRIMARY KEY,
    idRoute INT REFERENCES route(idRoute),
    libelle VARCHAR(50),
    numero INT,
    idPlace INT REFERENCES place(idPlace),
    km DECIMAL
);

CREATE TABLE prix(
    idPrix SERIAL PRIMARY KEY,
    idPrestation INT REFERENCES prestation(idPrestation),
    idPetiteRoute INT REFERENCES petiteRoute(idPetiteRoute),
    prix DECIMAL
);

-- 0 (Ajout) 
-- 1-3 tsy reparable (manale)
-- 4-7 (reparation)
-- 8-9 (nettoyage) 
-- 10 perfect

