-- Sequence: im_groupchat_owner_seq


-- DROP SEQUENCE im_groupchat_owner_seq;

CREATE SEQUENCE im_groupchat_owner_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 3
  CACHE 1;
ALTER TABLE im_groupchat_owner_seq OWNER TO lcmadmin;


-- Sequence: im_authority_seq

-- DROP SEQUENCE im_authority_seq;

CREATE SEQUENCE im_authority_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1
  CYCLE;
ALTER TABLE im_authority_seq OWNER TO lcmadmin;

-- Sequence: im_authority_type_seq

-- DROP SEQUENCE im_authority_type_seq;

CREATE SEQUENCE im_authority_type_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE im_authority_type_seq OWNER TO lcmadmin;


-- Sequence: im_groupchat_authority_seq

-- DROP SEQUENCE im_groupchat_authority_seq;

CREATE SEQUENCE im_groupchat_authority_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE im_groupchat_authority_seq OWNER TO lcmadmin;


-- Sequence: im_role_authority_seq

-- DROP SEQUENCE im_role_authority_seq;

CREATE SEQUENCE im_role_authority_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE im_role_authority_seq OWNER TO lcmadmin;


-- Sequence: im_role_seq

-- DROP SEQUENCE im_role_seq;

CREATE SEQUENCE im_role_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE im_role_seq OWNER TO lcmadmin;


-- Sequence: im_role_user_seq

-- DROP SEQUENCE im_role_user_seq;

CREATE SEQUENCE im_role_user_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE im_role_user_seq OWNER TO lcmadmin;



-- Table: im_authority

-- DROP TABLE im_authority;

CREATE TABLE im_authority
(
  id bigint NOT NULL DEFAULT nextval('im_authority_seq'::regclass),
  authority_type bigint NOT NULL,
  creator bigint,
  create_time date,
  mark character varying(100) NOT NULL,
  del integer NOT NULL DEFAULT 0,
  authority_name character varying(100) NOT NULL,
  CONSTRAINT pk_im_authority PRIMARY KEY (id),
  CONSTRAINT "unique" UNIQUE (mark)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE im_authority OWNER TO lcmadmin;


-- Table: im_authority_type

-- DROP TABLE im_authority_type;

CREATE TABLE im_authority_type
(
  id bigint NOT NULL DEFAULT nextval('im_authority_type_seq'::regclass),
  type_name character varying(300) NOT NULL,
  creator bigint,
  create_time date,
  CONSTRAINT im_authority_type_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE im_authority_type OWNER TO lcmadmin;
COMMENT ON TABLE im_authority_type IS '即时通讯界面权限类型';


-- Table: im_groupchat_authority

-- DROP TABLE im_groupchat_authority;

CREATE TABLE im_groupchat_authority
(
  id bigint NOT NULL DEFAULT nextval('im_groupchat_authority_seq'::regclass),
  creator bigint,
  create_time date,
  user_id bigint NOT NULL,
  im_authority_id bigint NOT NULL,
  groupchat_id bigint NOT NULL,
  CONSTRAINT pk_im_groupchat_authory PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE im_groupchat_authority OWNER TO lcmadmin;


-- Table: im_role

-- DROP TABLE im_role;

CREATE TABLE im_role
(
  id bigint NOT NULL DEFAULT nextval('im_role_seq'::regclass),
  role_name character varying(100) NOT NULL,
  creator bigint,
  create_time date,
  del smallint NOT NULL DEFAULT 0,
  CONSTRAINT pk_im_role PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE im_role OWNER TO lcmadmin;


-- Table: im_role_authority

-- DROP TABLE im_role_authority;

CREATE TABLE im_role_authority
(
  id bigint NOT NULL DEFAULT nextval('im_role_authority_seq'::regclass),
  creator bigint,
  create_time date,
  im_role_id bigint NOT NULL,
  im_authority_id bigint NOT NULL,
  CONSTRAINT pk_im_role_authority PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE im_role_authority OWNER TO lcmadmin;


-- Table: im_role_user

-- DROP TABLE im_role_user;

CREATE TABLE im_role_user
(
  id bigint NOT NULL DEFAULT nextval('im_role_user_seq'::regclass),
  creator bigint,
  create_time date,
  im_role_id bigint NOT NULL,
  im_user_id bigint NOT NULL,
  CONSTRAINT pk_im_role_user PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE im_role_user OWNER TO lcmadmin;


-- Table: im_groupchat_owner

-- DROP TABLE im_groupchat_owner;

CREATE TABLE im_groupchat_owner
(
  id bigint NOT NULL DEFAULT nextval('im_groupchat_owner_seq'::regclass),
  groupchat_id bigint NOT NULL,
  user_id bigint NOT NULL,
  CONSTRAINT pk_im_group_owner PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE im_groupchat_owner OWNER TO lcmadmin;


--INSERT INTO ofmucservice (serviceid,subdomain,description,ishidden) VALUES (2,'groupchat','群组',0);
INSERT INTO im_authority_type (type_name) VALUES ('groupchat');
insert into im_authority (authority_type,mark,authority_name) values ((select id from im_authority_type t where t.type_name='groupchat'),'SetGroupChatInfo','设置群组信息');
insert into im_authority (authority_type,mark,authority_name) values ((select id from im_authority_type t where t.type_name='groupchat'),'KickOneFromGroupChat','踢出群组成员');
insert into im_authority (authority_type,mark,authority_name) values ((select id from im_authority_type t where t.type_name='groupchat'),'ModifyGroupChatMemberAuthority','修改群组成员权限');
