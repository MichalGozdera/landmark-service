--liquibase formatted sql

-- changeset cokeman:12_05_2025_01
CREATE TABLE IF NOT EXISTS administrative_area (
    uuid UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    level_id INTEGER,
    metadata JSONB,
    parent UUID
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
    country VARCHAR(100),
    level_order INTEGER
);

ALTER TABLE administrative_area
ADD CONSTRAINT fk_area_level
FOREIGN KEY (level_id) REFERENCES administrative_level (id);

-- changeset cokeman:13_05_2025
CREATE UNIQUE INDEX IF NOT EXISTS idx_level_name_country
ON administrative_level (name, country);

-- changeset cokeman:21_05_2025
CREATE TABLE IF NOT EXISTS geometry_simplified (
    id SERIAL PRIMARY KEY,
    line TEXT NOT NULL,
    area_id UUID NOT NULL,
    create_time TIMESTAMP,
    update_time TIMESTAMP
);

ALTER TABLE geometry_simplified
ADD CONSTRAINT fk_area_id
FOREIGN KEY (area_id) REFERENCES administrative_area (uuid);