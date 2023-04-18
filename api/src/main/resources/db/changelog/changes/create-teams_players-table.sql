--liquibase formatted sql
--changeset <footballmanager>:<create-teams_players-sequence-id>

CREATE TABLE IF NOT EXISTS public.teams_players
(
    id bigint,
    team_id bigint NOT NULL,
    players_id bigint NOT NULL
);

--rollback DROP TABLE teams_players;
