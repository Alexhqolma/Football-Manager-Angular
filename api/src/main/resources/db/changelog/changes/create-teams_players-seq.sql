--liquibase formatted sql
--changeset <footballmanager>:<create-teams_players-sequence-id>

CREATE SEQUENCE IF NOT EXISTS public.teams_players_id_seq INCREMENT 1 START 1 MINVALUE 1;

--rollback DROP SEQUENCE public.teams_players_id_seq;
