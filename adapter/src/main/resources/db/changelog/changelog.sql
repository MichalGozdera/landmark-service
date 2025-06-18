--liquibase formatted sql

-- changeset cokeman:12_05_2025_01
CREATE TABLE IF NOT EXISTS administrative_area (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    level_id INTEGER,
    parent INTEGER
);

CREATE TABLE IF NOT EXISTS country (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    create_time TIMESTAMP,
    update_time TIMESTAMP
);

-- changeset cokeman:12_05_2025_02
ALTER TABLE administrative_area
ADD COLUMN geometry public.geometry(geometry, 4326);

-- changeset cokeman:12_05_2025_03
CREATE TABLE IF NOT EXISTS administrative_level (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    country INTEGER NOT NULL,
    level_order INTEGER,
    CONSTRAINT fk_level_country FOREIGN KEY (country) REFERENCES country(id)
);

ALTER TABLE administrative_area
ADD CONSTRAINT fk_area_level
FOREIGN KEY (level_id) REFERENCES administrative_level (id);

CREATE UNIQUE INDEX IF NOT EXISTS idx_level_name_country
ON administrative_level (name, country);

