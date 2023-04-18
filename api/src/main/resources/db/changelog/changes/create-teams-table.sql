--liquibase formatted sql
--changeset <footballmanager>:<create-teams-sequence-id>

CREATE TABLE IF NOT EXISTS public.teams
(
    id bigint NOT NULL,
    name character varying(256),
    surname character varying(256),
    funds numeric,
    city character varying(256),
    country character varying(256),
    CONSTRAINT teams_pk PRIMARY KEY (id)
);

--rollback DROP TABLE teams;
