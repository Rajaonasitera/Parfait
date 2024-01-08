CREATE DATABASE dentiste1;
\c dentiste1

-- arina sa aloha
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

CREATE TABLE dent(
    idDent SERIAL PRIMARY KEY,
    libelle VARCHAR(50),
    numero INT,
    idPlace INT REFERENCES place(idPlace)
);

CREATE TABLE prix(
    idPrix SERIAL PRIMARY KEY,
    idPrestation INT REFERENCES prestation(idPrestation),
    idDent INT REFERENCES dent(idDent),
    prix DECIMAL
);

CREATE TABLE note(
    idNote SERIAL PRIMARY KEY,
    idDent INT REFERENCES dent(idDent),
    etat INT
);

-- 0 (Ajout) 
-- 1-3 tsy reparable (manale)
-- 4-7 (reparation)
-- 8-9 (nettoyage) 
-- 10 perfect

