-- $Revision$
-- $Date$

INSERT INTO ofVersion (name, version) VALUES ('conferenceVote', 0);

-- Sequence: conference_vote_seq

-- DROP SEQUENCE conference_vote_seq;

CREATE SEQUENCE conference_vote_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE conference_vote_seq OWNER TO lcmadmin;


-- Sequence: conference_select_seq

-- DROP SEQUENCE conference_select_seq;

CREATE SEQUENCE conference_select_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE conference_select_seq OWNER TO lcmadmin;


-- Sequence: conference_option_seq

-- DROP SEQUENCE conference_option_seq;

CREATE SEQUENCE conference_option_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE conference_option_seq OWNER TO lcmadmin;


-- Sequence: conference_vote_record_seq

-- DROP SEQUENCE conference_vote_record_seq;

CREATE SEQUENCE conference_vote_record_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE conference_vote_record_seq OWNER TO lcmadmin;



-- Table: conference_vote

-- DROP TABLE conference_vote;

CREATE TABLE conference_vote
(
  id bigint NOT NULL DEFAULT nextval('conference_vote_seq'::regclass),
  title character varying(300) NOT NULL,
  title_remark character varying(900),
  effective_time bigint,
  conference_id bigint NOT NULL,
  begin_time timestamp without time zone,
  creator bigint NOT NULL,
  is_start boolean,
  is_close boolean,
  CONSTRAINT pk_conference_vote PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE conference_vote OWNER TO lcmadmin;


-- Table: conference_select

-- DROP TABLE conference_select;

CREATE TABLE conference_select
(
  id bigint NOT NULL DEFAULT nextval('conference_select_seq'::regclass),
  conference_vote_id bigint NOT NULL,
  "type" integer NOT NULL,
  select_title character varying(300) NOT NULL,
  select_remark character varying(900),
  select_num integer NOT NULL,
  CONSTRAINT pk_conference_select PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE conference_select OWNER TO lcmadmin;


-- Table: conference_option

-- DROP TABLE conference_option;

CREATE TABLE conference_option
(
  id bigint NOT NULL DEFAULT nextval('conference_option_seq'::regclass),
  conference_select_id bigint NOT NULL,
  option_name character varying(300) NOT NULL,
  CONSTRAINT pk_conference_option PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE conference_option OWNER TO lcmadmin;


-- Table: conference_vote_record

-- DROP TABLE conference_vote_record;

CREATE TABLE conference_vote_record
(
  id bigint NOT NULL DEFAULT nextval('conference_vote_record_seq'::regclass),
  conference_id bigint NOT NULL,
  conference_vote_id bigint NOT NULL,
  conference_select_id bigint NOT NULL,
  conference_option_id bigint NOT NULL,
  user_id bigint NOT NULL,
  vote_time timestamp without time zone NOT NULL,
  CONSTRAINT pk_conference_vote_record PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE conference_vote_record OWNER TO lcmadmin;
