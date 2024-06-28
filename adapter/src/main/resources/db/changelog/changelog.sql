--liquibase formatted sql

-- changeset cokeman:23_06_2024
create schema if not exists Landmarks;

create table if not exists Landmark
(
    landmarkId UUID   primary key,
    name varchar (255) not null,
    geometryType varchar (30),
    category varchar (50),
    loadTime timestamp,
    country varchar (50)
);