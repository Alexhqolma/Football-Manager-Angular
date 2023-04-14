--liquibase formatted sql
--changeset <footballmanager>:<create-teams-sequence-id>

CREATE TABLE IF NOT EXISTS public.teams
(
    id bigint NOT NULL,
    name character varying(256) NOT NULL,
    surname character varying(256) NOT NULL,
    funds numeric NOT NULL,
    city character varying(256) NOT NULL,
    country character varying(256) NOT NULL,
    CONSTRAINT teams_pk PRIMARY KEY (id)
);

--rollback DROP TABLE teams;
