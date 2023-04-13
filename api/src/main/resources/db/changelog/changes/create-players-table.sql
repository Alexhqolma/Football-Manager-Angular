--liquibase formatted sql
--changeset <footballmanager>:<create-players-sequence-id>

CREATE TABLE IF NOT EXISTS public.players
(
    id bigint NOT NULL,
    name character varying(256) NOT NULL,
    surname character varying(256) NOT NULL,
    age int NOT NULL,
    careerstart date NOT NULL,
    price bigint NOT NULL,
    CONSTRAINT players_pk PRIMARY KEY (id)
);

--rollback DROP TABLE players;
