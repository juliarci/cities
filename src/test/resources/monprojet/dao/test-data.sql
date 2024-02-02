-- Initialisation spécifiques pour un jeu de test
INSERT INTO Country(id, code, name) VALUES
    (default,'IT', 'Italie'),
    (default,'FR', 'France'),
    (default,'ES', 'Espagne');

INSERT INTO City(country_id, id, name, population)
VALUES
    (3,default,'Rome', 2800000),
    (3,default,'Florence', 380000),
    (3,default,'Turin', 890000),
    (3,default,'Rimini', 150000);