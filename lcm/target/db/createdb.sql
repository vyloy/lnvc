-- createdb -E utf8 -O lcmadmin testdb

DROP ROLE lcmadmin;

CREATE ROLE lcmadmin LOGIN
  ENCRYPTED PASSWORD 'md58f6e2eba864cc2e141abf01d21b034ad'
  SUPERUSER INHERIT CREATEDB CREATEROLE;

DROP DATABASE "lcmDB_Dev";

CREATE DATABASE "lcmDB_Dev"
  WITH OWNER = lcmadmin
       ENCODING = 'UTF8'
       CONNECTION LIMIT = -1;

