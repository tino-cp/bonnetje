CREATE DATABASE IF NOT EXISTS Bonnetje;
USE Bonnetje;

CREATE TABLE BTW (
    btw_perc TINYINT NOT NULL PRIMARY KEY,
    CHECK (btw_perc IN (9, 21))
);

CREATE TABLE Producten (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_naam VARCHAR(255) NOT NULL,
    prijs DECIMAL(10, 2) NOT NULL
);

CREATE TABLE PRODUCT_BTW (
    product_id INT NOT NULL,
    btw_perc TINYINT NOT NULL,
    PRIMARY KEY (product_id, btw_perc),
    FOREIGN KEY (product_id) REFERENCES Producten(product_id),
    FOREIGN KEY (btw_perc) REFERENCES BTW(btw_perc)
);

CREATE TABLE StaffelKorting (
    product_id INT NOT NULL,
    min_aantal INT NOT NULL,
    korting_perc DECIMAL(5, 2) NOT NULL,
    PRIMARY KEY (product_id, min_aantal),
    FOREIGN KEY (product_id) REFERENCES Producten(product_id)
);

INSERT INTO BTW (btw_perc) VALUES (9), (21);

INSERT INTO Producten (product_naam, prijs)
VALUES
    ('AH Biologische Havermout', 1.95),
    ('Unox Rookworst', 4.25),
    ('Calvé Pindakaas Crunchy', 3.99),
    ('Douwe Egberts Aroma Rood Koffiebonen', 7.99),
    ('Becel Original Margarine', 2.29),
    ('Grolsch Premium Pilsner 6-pack', 6.99),
    ('Boni Crispy Muesli', 2.79),
    ('FrieslandCampina Vifit Yoghurt Drink', 1.59),
    ('AH Huismerk Mineraalwater 6-pack', 2.49),
    ('Hak Groene Boontjes in Pot', 2.19),
    ('Jumbo Smac Smakelijke Ham', 3.75),
    ('Zonnatura Bio Groene Thee', 2.99);

INSERT INTO PRODUCT_BTW (product_id, btw_perc)
VALUES
    (1, 9),  -- AH Biologische Havermout
    (2, 21), -- Unox Rookworst
    (3, 9),  -- Calvé Pindakaas Crunchy
    (4, 9),  -- Douwe Egberts Aroma Rood Koffiebonen
    (5, 9),  -- Becel Original Margarine
    (6, 21), -- Grolsch Premium Pilsner 6-pack
    (7, 9),  -- Boni Crispy Muesli
    (8, 9),  -- FrieslandCampina Vifit Yoghurt Drink
    (9, 9),  -- AH Huismerk Mineraalwater 6-pack
    (10, 9), -- Hak Groene Boontjes in Pot
    (11, 21),-- Jumbo Smac Smakelijke Ham
    (12, 9); -- Zonnatura Bio Groene Thee

INSERT INTO StaffelKorting (product_id, min_aantal, korting_perc)
VALUES
    (1, 5, 10.00),  -- 10% korting bij 5x AH Biologische Havermout
    (4, 6, 8.00),   -- 8% korting bij 6x Douwe Egberts Koffiebonen
    (6, 12, 20.00), -- 20% korting bij 12x Grolsch Premium Pilsner
    (9, 15, 15.00), -- 15% korting bij 15x Mineraalwater 6-pack
    (11, 10, 25.00);-- 25% korting bij 10x Jumbo Smac Smakelijke Ham
