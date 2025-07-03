DELETE FROM administrative_area;
DELETE FROM administrative_level;
DELETE FROM country;

INSERT INTO country (id, name) VALUES (1, 'Poland');
INSERT INTO administrative_level (id, name, country_id) VALUES (1, 'CITY', 1);
INSERT INTO administrative_area (id, name, level_id) VALUES (1, 'Test Area', 1);
