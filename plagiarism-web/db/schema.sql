SET client_encoding = 'UTF8';

--Schema

CREATE SCHEMA plagiarism;

ALTER SCHEMA plagiarism OWNER TO plagiarism;

--Tables

CREATE TABLE plagiarism.comparisons (
    id bigint NOT NULL,
    source_submission_id bigint,
    target_submission_id bigint,
    status character varying(15),
    creation_date timestamp without time zone,
    processing_date timestamp without time zone,
    similarity numeric(19,2),
    plagiarism boolean DEFAULT false
);

ALTER TABLE plagiarism.comparisons OWNER TO plagiarism;

CREATE TABLE plagiarism.groups (
    id bigint NOT NULL,
    creation_date timestamp without time zone,
    name character varying(255),
    private_id character varying(255),
    parent_group_id bigint,
    user_id bigint
);

ALTER TABLE plagiarism.groups OWNER TO plagiarism;

CREATE TABLE plagiarism.submissions (
    id bigint NOT NULL,
    filename character varying(255),
    language character varying(10),
    original_filename character varying(255),
    private_id character varying(255),
    processing_date timestamp without time zone,
    submission_date timestamp without time zone,
    status character varying(15),
    tokens text,
    group_id bigint
);

ALTER TABLE plagiarism.submissions OWNER TO plagiarism;

CREATE TABLE plagiarism.users (
    id bigint NOT NULL,
    username character varying(30),
    password character varying(40),
    email character varying(255)
);

ALTER TABLE plagiarism.users OWNER TO plagiarism;

--Sequences

CREATE SEQUENCE plagiarism.seq_comparisons
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE plagiarism.seq_comparisons OWNER TO plagiarism;

CREATE SEQUENCE plagiarism.seq_groups
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE plagiarism.seq_groups OWNER TO plagiarism;

CREATE SEQUENCE plagiarism.seq_submissions
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE plagiarism.seq_submissions OWNER TO plagiarism;

CREATE SEQUENCE plagiarism.seq_users
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE plagiarism.seq_users OWNER TO plagiarism;

SELECT pg_catalog.setval('plagiarism.seq_comparisons', 1, false);

SELECT pg_catalog.setval('plagiarism.seq_groups', 1, false);

SELECT pg_catalog.setval('plagiarism.seq_submissions', 1, false);

SELECT pg_catalog.setval('plagiarism.seq_users', 2, true);

--Primary Keys

ALTER TABLE ONLY plagiarism.comparisons
    ADD CONSTRAINT pk_comparisons PRIMARY KEY (id);

ALTER TABLE ONLY plagiarism.groups
    ADD CONSTRAINT pk_groups PRIMARY KEY (id);

ALTER TABLE ONLY plagiarism.submissions
    ADD CONSTRAINT pk_submissions PRIMARY KEY (id);

ALTER TABLE ONLY plagiarism.users
    ADD CONSTRAINT pk_users PRIMARY KEY (id);
    
--Foreign Keys

ALTER TABLE ONLY plagiarism.comparisons
    ADD CONSTRAINT fk_comparisons_source_submission_id_submissions FOREIGN KEY (source_submission_id) REFERENCES plagiarism.submissions(id);

ALTER TABLE ONLY plagiarism.comparisons
    ADD CONSTRAINT fk_comparisons_target_submission_id_submissions FOREIGN KEY (target_submission_id) REFERENCES plagiarism.submissions(id);

ALTER TABLE ONLY plagiarism.groups
    ADD CONSTRAINT fk_groups_parent_group_id_groups FOREIGN KEY (parent_group_id) REFERENCES plagiarism.groups(id);

ALTER TABLE ONLY plagiarism.groups
    ADD CONSTRAINT fk_groups_user_id_users FOREIGN KEY (user_id) REFERENCES plagiarism.users(id);   

ALTER TABLE ONLY plagiarism.submissions
    ADD CONSTRAINT fk_submissions_group_id_groups FOREIGN KEY (group_id) REFERENCES plagiarism.groups(id);
    
--Unique Keys

ALTER TABLE ONLY plagiarism.comparisons
    ADD CONSTRAINT uk_comparisons_source_submission_id_target_submission_id UNIQUE (source_submission_id, target_submission_id);

ALTER TABLE ONLY plagiarism.groups
    ADD CONSTRAINT uk_groups_private_id_user_id UNIQUE (private_id, user_id);

CREATE UNIQUE INDEX uk_submissions_private_id_group_id
  ON plagiarism.submissions
  WHERE private_id IS NOT NULL;

ALTER TABLE ONLY plagiarism.users
    ADD CONSTRAINT uk_users_username UNIQUE (username);
    
--Indexes
    
CREATE INDEX idx_comparisons_status
   ON plagiarism.comparisons (status DESC NULLS LAST);
   
CREATE INDEX idx_groups_parent_group_id_user_id
   ON plagiarism.groups (user_id ASC NULLS FIRST, parent_group_id ASC NULLS FIRST);
    
CREATE INDEX idx_submissions_status
   ON plagiarism.submissions (status DESC NULLS LAST);
    
CREATE INDEX idx_users_username
   ON plagiarism.users (username ASC NULLS LAST);
   
--Users
    
INSERT INTO plagiarism.users values (1, 'huxley', '7c4a8d09ca3762af61e59520943dc26494f8941b', 'support@thehuxley.com');