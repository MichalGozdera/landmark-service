--liquibase formatted sql

-- changeset cokeman:23_06_2024
create table if not exists landmark
(
    landmarkId UUID   primary key,
    name varchar (255) not null,
    geometryType varchar (30),
    category varchar (50),
    loadTime timestamp,
    country varchar (50),
    metadata jsonb
);

-- changeset cokeman:11_09_2024
ALTER TABLE landmark ADD COLUMN geometry public.geometry(GEOMETRY,4326);

-- changeset cokeman:27_10_2024
ALTER TABLE landmark ADD COLUMN parent UUID;