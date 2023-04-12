--liquibase formatted sql
--changeset <footballmanager>:<create-players-sequence-id>

CREATE SEQUENCE IF NOT EXISTS public.players_id_seq INCREMENT 1 START 1 MINVALUE 1;

--rollback DROP SEQUENCE public.players_id_seq;
