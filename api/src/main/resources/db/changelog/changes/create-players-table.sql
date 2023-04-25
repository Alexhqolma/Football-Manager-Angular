--liquibase formatted sql
--changeset <footballmanager>:<create-players-sequence-id>

CREATE TABLE IF NOT EXISTS public.players
(
    id bigint NOT NULL,
    name character varying(256),
    surname character varying(256),
    age int,
    careerstart date,
    price numeric NOT NULL,
    team_id INT,
    CONSTRAINT players_pk PRIMARY KEY (id)
);

--rollback DROP TABLE players;
