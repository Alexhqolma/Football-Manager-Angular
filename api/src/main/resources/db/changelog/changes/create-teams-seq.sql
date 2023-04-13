--liquibase formatted sql
--changeset <footballmanager>:<create-teams-sequence-id>

CREATE SEQUENCE IF NOT EXISTS public.teams_id_seq INCREMENT 1 START 1 MINVALUE 1;

--rollback DROP SEQUENCE public.teams_id_seq;
