--liquibase formatted sql
--changeset <footballmanager>:<create-teams_players-sequence-id>

CREATE TABLE IF NOT EXISTS public.teams_players
(
    id bigint NOT NULL,
    CONSTRAINT teams_players_pk PRIMARY KEY (id)
);

--rollback DROP TABLE teams_players;
