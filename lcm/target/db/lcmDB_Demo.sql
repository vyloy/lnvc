--
-- PostgreSQL database dump
--

-- Started on 2011-07-26 16:18:42

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 408 (class 2612 OID 16388)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: lcmadmin
--

CREATE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO lcmadmin;

SET search_path = public, pg_catalog;

--
-- TOC entry 19 (class 1255 OID 16389)
-- Dependencies: 408 6
-- Name: createconfno(integer, bigint, character varying, integer, integer); Type: FUNCTION; Schema: public; Owner: lcmadmin
--

CREATE FUNCTION createconfno(integer, bigint, character varying, integer, integer) RETURNS boolean
    LANGUAGE plpgsql
    AS $_$
DECLARE
	num alias for $1;	--瑕佸垱寤虹殑浼氳鍙风爜鏁扮洰
	c_id alias for $2;	--customer.id
	c_code alias for $3;	--customer.code
	c_code_length alias for $4;	--customer code length
	conf_no_length alias for $5;	--conference no length
	zero_str varchar;	
	index_str varchar;
	zero_length integer;
	
BEGIN
	for i in 1..num loop
		index_str := cast(i as varchar);
		zero_length := conf_no_length - c_code_length - char_length(index_str);
		zero_str := '';
		for j in 1..zero_length loop
			zero_str := '0' || zero_str;
		end loop;
		insert into sys_confno(no_code, no_customer_id, no_is_used) values(c_code||zero_str||index_str, c_id, false);
	end loop;
	return true;
END;$_$;


ALTER FUNCTION public.createconfno(integer, bigint, character varying, integer, integer) OWNER TO lcmadmin;

CREATE FUNCTION insert_sip_conf(integer, integer) RETURNS boolean
    LANGUAGE plpgsql
    AS $_$
DECLARE
	start_num alias for $1;	
	end_num alias for $2;	
	lccno varchar;
BEGIN
	for i in start_num..end_num loop
		lccno := cast(i as varchar);
		insert into sip_conf(name, secret) values(lccno, '1qaz@WSX');
	end loop;
	return true;
END;
$_$;


ALTER FUNCTION public.insert_sip_conf(integer, integer) OWNER TO lcmadmin;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1595 (class 1259 OID 16390)
-- Dependencies: 6
-- Name: acl_class; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE acl_class (
    id bigint NOT NULL,
    class character varying(100) NOT NULL
);


ALTER TABLE public.acl_class OWNER TO lcmadmin;

--
-- TOC entry 1596 (class 1259 OID 16393)
-- Dependencies: 6 1595
-- Name: acl_class_id_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE acl_class_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.acl_class_id_seq OWNER TO lcmadmin;

--
-- TOC entry 2105 (class 0 OID 0)
-- Dependencies: 1596
-- Name: acl_class_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: lcmadmin
--

ALTER SEQUENCE acl_class_id_seq OWNED BY acl_class.id;


--
-- TOC entry 1597 (class 1259 OID 16395)
-- Dependencies: 6
-- Name: acl_entry; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE acl_entry (
    id bigint NOT NULL,
    acl_object_identity bigint NOT NULL,
    ace_order integer NOT NULL,
    sid bigint NOT NULL,
    mask integer NOT NULL,
    granting boolean,
    audit_success boolean,
    audit_failure boolean NOT NULL
);


ALTER TABLE public.acl_entry OWNER TO lcmadmin;

--
-- TOC entry 1598 (class 1259 OID 16398)
-- Dependencies: 6 1597
-- Name: acl_entry_id_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE acl_entry_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.acl_entry_id_seq OWNER TO lcmadmin;

--
-- TOC entry 2106 (class 0 OID 0)
-- Dependencies: 1598
-- Name: acl_entry_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: lcmadmin
--

ALTER SEQUENCE acl_entry_id_seq OWNED BY acl_entry.id;


--
-- TOC entry 1599 (class 1259 OID 16400)
-- Dependencies: 6
-- Name: acl_object_identity; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE acl_object_identity (
    object_id_class bigint NOT NULL,
    object_id_identity bigint NOT NULL,
    parent_object bigint,
    owner_sid bigint NOT NULL,
    id bigint NOT NULL,
    entries_inheriting boolean
);


ALTER TABLE public.acl_object_identity OWNER TO lcmadmin;

--
-- TOC entry 1600 (class 1259 OID 16403)
-- Dependencies: 1599 6
-- Name: acl_object_identity_id_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE acl_object_identity_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.acl_object_identity_id_seq OWNER TO lcmadmin;

--
-- TOC entry 2107 (class 0 OID 0)
-- Dependencies: 1600
-- Name: acl_object_identity_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: lcmadmin
--

ALTER SEQUENCE acl_object_identity_id_seq OWNED BY acl_object_identity.id;


--
-- TOC entry 1601 (class 1259 OID 16405)
-- Dependencies: 6
-- Name: acl_sid; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE acl_sid (
    id bigint NOT NULL,
    sid character varying(100) NOT NULL,
    principal boolean
);


ALTER TABLE public.acl_sid OWNER TO lcmadmin;

--
-- TOC entry 1602 (class 1259 OID 16408)
-- Dependencies: 1601 6
-- Name: acl_sid_id_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE acl_sid_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.acl_sid_id_seq OWNER TO lcmadmin;

--
-- TOC entry 2108 (class 0 OID 0)
-- Dependencies: 1602
-- Name: acl_sid_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: lcmadmin
--

ALTER SEQUENCE acl_sid_id_seq OWNED BY acl_sid.id;


--
-- TOC entry 1603 (class 1259 OID 16410)
-- Dependencies: 6
-- Name: event_opt_log_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE event_opt_log_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.event_opt_log_seq OWNER TO lcmadmin;

--
-- TOC entry 1604 (class 1259 OID 16412)
-- Dependencies: 1938 6
-- Name: event_opt_log; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE event_opt_log (
    id bigint DEFAULT nextval('event_opt_log_seq'::regclass) NOT NULL,
    eventlogid bigint,
    optusername character(100),
    opttime date,
    optuserid bigint,
    monitorid bigint
);


ALTER TABLE public.event_opt_log OWNER TO lcmadmin;

--
-- TOC entry 1605 (class 1259 OID 16416)
-- Dependencies: 1939 1940 1941 1942 6
-- Name: extensions_conf; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE extensions_conf (
    id integer NOT NULL,
    context character varying(20) DEFAULT ''::character varying NOT NULL,
    exten character varying(128) DEFAULT ''::character varying NOT NULL,
    priority smallint DEFAULT 0 NOT NULL,
    app character varying(20) DEFAULT ''::character varying NOT NULL,
    appdata character varying(128)
);


ALTER TABLE public.extensions_conf OWNER TO lcmadmin;

--
-- TOC entry 1606 (class 1259 OID 16423)
-- Dependencies: 1605 6
-- Name: extensions_conf_id_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE extensions_conf_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.extensions_conf_id_seq OWNER TO lcmadmin;

--
-- TOC entry 2109 (class 0 OID 0)
-- Dependencies: 1606
-- Name: extensions_conf_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: lcmadmin
--

ALTER SEQUENCE extensions_conf_id_seq OWNED BY extensions_conf.id;


--
-- TOC entry 1607 (class 1259 OID 16425)
-- Dependencies: 6
-- Name: friend_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE friend_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.friend_seq OWNER TO lcmadmin;

--
-- TOC entry 1608 (class 1259 OID 16427)
-- Dependencies: 1944 6
-- Name: friend; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE friend (
    id bigint DEFAULT nextval('friend_seq'::regclass) NOT NULL,
    userid bigint,
    friendid bigint,
    nickname character varying(100)
);


ALTER TABLE public.friend OWNER TO lcmadmin;

--
-- TOC entry 1609 (class 1259 OID 16431)
-- Dependencies: 6
-- Name: lcc_monitor_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE lcc_monitor_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.lcc_monitor_seq OWNER TO lcmadmin;

--
-- TOC entry 1610 (class 1259 OID 16433)
-- Dependencies: 1945 6
-- Name: lcc_monitor; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE lcc_monitor (
    id bigint DEFAULT nextval('lcc_monitor_seq'::regclass) NOT NULL,
    cmsip character varying(50),
    cmsport character varying(20),
    username character varying(100),
    password character varying(100),
    nodename character varying(100),
    channelno character varying(50),
    isaudio character varying(10),
    volume character varying(10),
    autoconnect character varying(10),
    nickname character varying(100),
    customerid bigint,
    longitude numeric(20,15),
    latitude numeric(20,15),
    status integer,
    longitudesat numeric(20,15),
    latitudesat numeric(20,15),
    remoteport character varying(20),
    lccno character varying(15)
);


ALTER TABLE public.lcc_monitor OWNER TO lcmadmin;

--
-- TOC entry 1611 (class 1259 OID 16440)
-- Dependencies: 1946 6
-- Name: lcc_monitor2; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE lcc_monitor2 (
    cmsip character varying(50),
    cmsport character varying(20),
    username character varying(100),
    password character varying(100),
    nodename character varying(100),
    channelno character varying(50),
    isaudio character varying(10),
    id bigint DEFAULT nextval('lcc_monitor_seq'::regclass) NOT NULL,
    volume character varying(10),
    autoconnect character varying(10),
    nickname character varying(100),
    customerid bigint,
    longitude numeric,
    latitude numeric,
    status integer
);


ALTER TABLE public.lcc_monitor2 OWNER TO lcmadmin;

--
-- TOC entry 1612 (class 1259 OID 16447)
-- Dependencies: 6
-- Name: mcu_mixer_mixerid_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE mcu_mixer_mixerid_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.mcu_mixer_mixerid_seq OWNER TO lcmadmin;

--
-- TOC entry 1613 (class 1259 OID 16449)
-- Dependencies: 1947 6
-- Name: mcu_mixer; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE mcu_mixer (
    mixer_id bigint DEFAULT nextval('mcu_mixer_mixerid_seq'::regclass) NOT NULL,
    mixer_name character varying(30) NOT NULL,
    mixer_key character varying(20) NOT NULL,
    mixer_ip character varying(20) NOT NULL,
    mixer_server_id bigint
);


ALTER TABLE public.mcu_mixer OWNER TO lcmadmin;

--
-- TOC entry 1614 (class 1259 OID 16453)
-- Dependencies: 6
-- Name: mcu_server_serverid_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE mcu_server_serverid_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.mcu_server_serverid_seq OWNER TO lcmadmin;

--
-- TOC entry 1615 (class 1259 OID 16455)
-- Dependencies: 1948 1949 6
-- Name: mcu_server; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE mcu_server (
    server_id bigint DEFAULT nextval('mcu_server_serverid_seq'::regclass) NOT NULL,
    server_name character varying(20) NOT NULL,
    server_ip character varying(20) ,
    server_desc character varying(200),
    server_status integer DEFAULT 0 NOT NULL,
    server_url character varying(50),
    cs_ip character varying(20),
    cs_port integer,
    cs_username character varying(20),
    cs_user_passwd character varying(20),
    rpc_pass character varying(60)
);


ALTER TABLE public.mcu_server OWNER TO lcmadmin;

--
-- TOC entry 1616 (class 1259 OID 16460)
-- Dependencies: 6
-- Name: peopleinmonitor_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE peopleinmonitor_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.peopleinmonitor_seq OWNER TO lcmadmin;

--
-- TOC entry 1617 (class 1259 OID 16462)
-- Dependencies: 1950 6
-- Name: peopleinmonitor; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE peopleinmonitor (
    id bigint DEFAULT nextval('peopleinmonitor_seq'::regclass) NOT NULL,
    monitorid bigint,
    userid bigint
);


ALTER TABLE public.peopleinmonitor OWNER TO lcmadmin;

--
-- TOC entry 1618 (class 1259 OID 16466)
-- Dependencies: 1951 1952 1953 1954 1955 1956 1957 1958 1959 1960 1961 1962 1963 1964 1966 1967 1968 1969 1970 6
-- Name: sip_conf; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE sip_conf (
    id integer NOT NULL,
    name character varying(80) DEFAULT ''::character varying NOT NULL,
    accountcode character varying(20),
    amaflags character varying(7),
    callgroup character varying(10),
    callerid character varying(80) DEFAULT ''::character varying,
    canreinvite character varying(3) DEFAULT 'yes'::character varying,
    context character varying(80) DEFAULT 'default'::character varying,
    defaultip character varying(15),
    dtmfmode character varying(7),
    fromuser character varying(80),
    fromdomain character varying(80),
    host character varying(31) DEFAULT 'dynamic'::character varying,
    insecure character varying(4),
    language character varying(2),
    mailbox character varying(50),
    md5secret character varying(80),
    nat character varying(5) DEFAULT 'yes'::character varying,
    permit character varying(95),
    deny character varying(95),
    mask character varying(95),
    pickupgroup character varying(10),
    port character varying(5) DEFAULT '0'::character varying NOT NULL,
    qualify character varying(3),
    restrictcid character varying(1),
    rtptimeout character varying(3),
    rtpholdtimeout character varying(3),
    secret character varying(80),
    type character varying DEFAULT 'friend'::character varying,
    username character varying(80) DEFAULT ''::character varying NOT NULL,
    disallow character varying(100) DEFAULT 'all'::character varying,
    allow character varying(100) DEFAULT 'h264;alaw;ulaw;'::character varying,
    musiconhold character varying(100),
    regseconds bigint DEFAULT (0)::bigint NOT NULL,
    ipaddr character varying(15) DEFAULT '0.0.0.0'::character varying NOT NULL,
    regexten character varying(80) DEFAULT ''::character varying,
    cancallforward character varying(3) DEFAULT 'yes'::character varying,
    defaultuser character varying(80),
    fullcontact character varying(80),
    regserver character varying(80),
    useragent character varying(80),
    lastms character varying(80),
    non_expired boolean DEFAULT false,
    non_locked boolean DEFAULT false,
    mac character varying(100),
    pic character varying(100),
    priority smallint DEFAULT 5,
    monitorindex character varying(5) DEFAULT '0'::character varying NOT NULL,
    monitorport character varying(8) DEFAULT '0'::character varying,
    monitorserver character varying(20) DEFAULT '0'::character varying,
    revport character varying(5),
    sendport character varying(5),
    groupid character varying(8) NOT NULL DEFAULT '0'::character varying,
    department_id bigint,
    isuser integer DEFAULT 0,
    monitorname character varying(50),
    longitude numeric(20,15),
    latitude numeric(20,15),
    warning boolean NOT NULL DEFAULT false
);


ALTER TABLE public.sip_conf OWNER TO lcmadmin;

--
-- TOC entry 1619 (class 1259 OID 16489)
-- Dependencies: 1618 6
-- Name: sip_conf_id_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE sip_conf_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sip_conf_id_seq OWNER TO lcmadmin;

--
-- TOC entry 2110 (class 0 OID 0)
-- Dependencies: 1619
-- Name: sip_conf_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: lcmadmin
--

ALTER SEQUENCE sip_conf_id_seq OWNED BY sip_conf.id;


--
-- TOC entry 1620 (class 1259 OID 16491)
-- Dependencies: 6
-- Name: static_city_cityid_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE static_city_cityid_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.static_city_cityid_seq OWNER TO lcmadmin;

--
-- TOC entry 1621 (class 1259 OID 16493)
-- Dependencies: 1971 1972 6
-- Name: static_city; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE static_city (
    city_id bigint DEFAULT nextval('static_city_cityid_seq'::regclass) NOT NULL,
    city_name character varying(20) NOT NULL,
    city_code character varying(10) DEFAULT '0000'::character varying NOT NULL
);


ALTER TABLE public.static_city OWNER TO lcmadmin;

--
-- TOC entry 1622 (class 1259 OID 16498)
-- Dependencies: 6
-- Name: sys_billing_billingid_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE sys_billing_billingid_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sys_billing_billingid_seq OWNER TO lcmadmin;

--
-- TOC entry 1623 (class 1259 OID 16500)
-- Dependencies: 1973 6
-- Name: sys_billing; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE sys_billing (
    billing_id bigint DEFAULT nextval('sys_billing_billingid_seq'::regclass) NOT NULL,
    conf_id bigint,
    rates_id bigint,
    is_pay boolean,
    total_cost numeric,
    actual_cost numeric,
    customer_code character varying(15),
    customer_name character varying(50),
    conf_name character varying(50)
);


ALTER TABLE public.sys_billing OWNER TO lcmadmin;

--
-- TOC entry 1624 (class 1259 OID 16507)
-- Dependencies: 6
-- Name: sys_conference_confid_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE sys_conference_confid_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sys_conference_confid_seq OWNER TO lcmadmin;

--
-- TOC entry 1625 (class 1259 OID 16509)
-- Dependencies: 1974 1975 6
-- Name: sys_conference; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE sys_conference (
    conf_id bigint DEFAULT nextval('sys_conference_confid_seq'::regclass) NOT NULL,
    conf_subject character varying(30),
    conf_pass character varying(50),
    conf_allow_before bigint,
    conf_mode integer,
    conf_type integer,
    conf_member_count integer,
    conf_video_count integer,
    conf_host_id bigint,
    conf_desc character varying(300),
    conf_public integer,
    conf_required_login integer,
    conf_start_time timestamp without time zone,
    conf_end_time timestamp without time zone,
    conf_owner_id bigint,
    conf_customer_id bigint,
    conf_status integer,
    conf_mcu_mixerkey character varying(100),
    conf_mcu_medialayout integer,
    conf_mcu_mediaquality character varying(50),
    conf_uid character varying(100),
    status integer,
    call_no_id bigint,
    conf_media integer,
    media_operate integer DEFAULT 0,
    conf_no character varying(15),
    sip_id integer,
    ismix integer
);


ALTER TABLE public.sys_conference OWNER TO lcmadmin;

--
-- TOC entry 1626 (class 1259 OID 16517)
-- Dependencies: 6
-- Name: sys_confno_noid_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE sys_confno_noid_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sys_confno_noid_seq OWNER TO lcmadmin;

--
-- TOC entry 1627 (class 1259 OID 16519)
-- Dependencies: 1976 1977 6
-- Name: sys_confno; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE sys_confno (
    no_id bigint DEFAULT nextval('sys_confno_noid_seq'::regclass) NOT NULL,
    no_code character varying(15),
    no_customer_id bigint,
    no_is_used boolean DEFAULT false
);


ALTER TABLE public.sys_confno OWNER TO lcmadmin;

--
-- TOC entry 1628 (class 1259 OID 16524)
-- Dependencies: 6
-- Name: sys_cron_conference_confid_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE sys_cron_conference_confid_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sys_cron_conference_confid_seq OWNER TO lcmadmin;

--
-- TOC entry 1629 (class 1259 OID 16526)
-- Dependencies: 1978 6
-- Name: sys_cron_conference; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE sys_cron_conference (
    conf_id bigint DEFAULT nextval('sys_cron_conference_confid_seq'::regclass) NOT NULL,
    conf_subject character varying(30),
    conf_pass character varying(50),
    conf_allow_before bigint,
    conf_mode integer,
    conf_type integer,
    conf_member_count integer,
    conf_video_count integer,
    conf_host_id bigint,
    conf_desc character varying(300),
    conf_public integer,
    conf_required_login integer,
    conf_owner_id bigint,
    conf_customer_id bigint,
    conf_mcu_mixerkey character varying(100),
    conf_mcu_medialayout integer,
    conf_mcu_mediaquality character varying(50),
    status integer,
    call_no_id bigint,
    cron_type integer,
    cron_expression character varying(30),
    cron_length integer,
    cron_hours timestamp without time zone,
    cron_time character varying(5),
    conf_no character varying(15),
    conf_media integer,
    ismix integer
);


ALTER TABLE public.sys_cron_conference OWNER TO lcmadmin;

--
-- TOC entry 1630 (class 1259 OID 16533)
-- Dependencies: 6
-- Name: syscustomer_customerid_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE syscustomer_customerid_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.syscustomer_customerid_seq OWNER TO lcmadmin;

--
-- TOC entry 1631 (class 1259 OID 16535)
-- Dependencies: 1979 1980 1981 1982 1983 1984 1985 1986 1987 6
-- Name: sys_customer; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE sys_customer (
    customer_id bigint DEFAULT nextval('syscustomer_customerid_seq'::regclass) NOT NULL,
    customer_name character varying(20) NOT NULL,
    customer_desc character varying(200),
    mcu_server_id bigint,
    customer_admin bigint,
    customer_code character varying(20) NOT NULL,
    customer_status integer DEFAULT 0,
    customer_address character varying(30),
    customer_contact character varying(15),
    customer_phone character varying(20),
    customer_userlimit integer DEFAULT 0,
    customer_nolimit integer DEFAULT 0,
    customer_city_id bigint,
    rates_id bigint,
    status integer,
    customer_pernolimit integer DEFAULT 0,
    conf_people_limit integer DEFAULT 0,
    customer_imdnolimit integer DEFAULT 0,
    customer_appnolimit integer DEFAULT 0,
    customer_meetingnolimit integer DEFAULT 0
);


ALTER TABLE public.sys_customer OWNER TO lcmadmin;

--
-- TOC entry 1632 (class 1259 OID 16547)
-- Dependencies: 6
-- Name: sys_deparment_deparmentid_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE sys_deparment_deparmentid_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sys_deparment_deparmentid_seq OWNER TO lcmadmin;

--
-- TOC entry 1633 (class 1259 OID 16549)
-- Dependencies: 1988 1989 6
-- Name: sys_deparment; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE sys_deparment
(
  deparment_id bigint NOT NULL DEFAULT nextval('sys_deparment_deparmentid_seq'::regclass),
  deparment_name character varying(20) NOT NULL,
  deparment_customer_id bigint,
  deparment_parent bigint,
  deparment_isroot boolean DEFAULT false,
  status integer,
  deparment_desc character varying(300),
  code character varying(50),
  deparment_order integer,
  searchstr character varying(100)
);


ALTER TABLE public.sys_deparment OWNER TO lcmadmin;

--
-- TOC entry 1634 (class 1259 OID 16554)
-- Dependencies: 6
-- Name: sys_media_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE sys_media_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sys_media_seq OWNER TO lcmadmin;

--
-- TOC entry 1635 (class 1259 OID 16556)
-- Dependencies: 1990 6
-- Name: sys_media; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE sys_media (
    id bigint DEFAULT nextval('sys_media_seq'::regclass) NOT NULL,
    media_name character varying(200),
    media_path character varying(500),
    status integer,
    file_name character varying(200),
    customer_id integer,
    media_desc character varying(500),
    original_name character varying(200)
);


ALTER TABLE public.sys_media OWNER TO lcmadmin;

--
-- TOC entry 2111 (class 0 OID 0)
-- Dependencies: 1635
-- Name: TABLE sys_media; Type: COMMENT; Schema: public; Owner: lcmadmin
--

COMMENT ON TABLE sys_media IS '濯掍綋鍒楄〃';


--
-- TOC entry 2112 (class 0 OID 0)
-- Dependencies: 1635
-- Name: COLUMN sys_media.media_name; Type: COMMENT; Schema: public; Owner: lcmadmin
--

COMMENT ON COLUMN sys_media.media_name IS '濯掍綋鍚嶅瓧';


--
-- TOC entry 2113 (class 0 OID 0)
-- Dependencies: 1635
-- Name: COLUMN sys_media.media_path; Type: COMMENT; Schema: public; Owner: lcmadmin
--

COMMENT ON COLUMN sys_media.media_path IS '濯掍綋璺緞';


--
-- TOC entry 1636 (class 1259 OID 16563)
-- Dependencies: 6
-- Name: sys_nostatus_id_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE sys_nostatus_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sys_nostatus_id_seq OWNER TO lcmadmin;

--
-- TOC entry 1637 (class 1259 OID 16565)
-- Dependencies: 1991 6
-- Name: sys_nostatus; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE sys_nostatus (
    nostatus_id bigint DEFAULT nextval('sys_nostatus_id_seq'::regclass) NOT NULL,
    start_time timestamp without time zone,
    end_time timestamp without time zone,
    no_id bigint
);


ALTER TABLE public.sys_nostatus OWNER TO lcmadmin;

--
-- TOC entry 1638 (class 1259 OID 16569)
-- Dependencies: 6
-- Name: sys_operate_record_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE sys_operate_record_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sys_operate_record_seq OWNER TO lcmadmin;

--
-- TOC entry 1639 (class 1259 OID 16571)
-- Dependencies: 1992 6
-- Name: sys_operate_record; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE sys_operate_record (
    id bigint DEFAULT nextval('sys_operate_record_seq'::regclass) NOT NULL,
    optime timestamp without time zone,
    opdisc character varying(500),
    user_id bigint,
    user_name character varying(50)
);


ALTER TABLE public.sys_operate_record OWNER TO lcmadmin;

--
-- TOC entry 2114 (class 0 OID 0)
-- Dependencies: 1639
-- Name: TABLE sys_operate_record; Type: COMMENT; Schema: public; Owner: lcmadmin
--

COMMENT ON TABLE sys_operate_record IS '鏃ュ織璁板綍琛�';


--
-- TOC entry 2115 (class 0 OID 0)
-- Dependencies: 1639
-- Name: COLUMN sys_operate_record.optime; Type: COMMENT; Schema: public; Owner: lcmadmin
--

COMMENT ON COLUMN sys_operate_record.optime IS '鎿嶄綔鏃堕棿';


--
-- TOC entry 2116 (class 0 OID 0)
-- Dependencies: 1639
-- Name: COLUMN sys_operate_record.opdisc; Type: COMMENT; Schema: public; Owner: lcmadmin
--

COMMENT ON COLUMN sys_operate_record.opdisc IS '鎿嶄綔鎻忚堪';


--
-- TOC entry 2117 (class 0 OID 0)
-- Dependencies: 1639
-- Name: COLUMN sys_operate_record.user_id; Type: COMMENT; Schema: public; Owner: lcmadmin
--

COMMENT ON COLUMN sys_operate_record.user_id IS '鐢ㄦ埛ID';


--
-- TOC entry 2118 (class 0 OID 0)
-- Dependencies: 1639
-- Name: COLUMN sys_operate_record.user_name; Type: COMMENT; Schema: public; Owner: lcmadmin
--

COMMENT ON COLUMN sys_operate_record.user_name IS '鐢ㄦ埛鍚�';


--
-- TOC entry 1640 (class 1259 OID 16578)
-- Dependencies: 6
-- Name: sys_params_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE sys_params_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sys_params_seq OWNER TO lcmadmin;

--
-- TOC entry 1641 (class 1259 OID 16580)
-- Dependencies: 1993 6
-- Name: sys_params; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE sys_params (
    id bigint DEFAULT nextval('sys_params_seq'::regclass) NOT NULL,
    module character varying(100),
    key character varying(50),
    value character varying(200),
    descript character varying(200)
);


ALTER TABLE public.sys_params OWNER TO lcmadmin;

--
-- TOC entry 1642 (class 1259 OID 16587)
-- Dependencies: 6
-- Name: sys_rates_ratesid_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE sys_rates_ratesid_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sys_rates_ratesid_seq OWNER TO lcmadmin;

--
-- TOC entry 1643 (class 1259 OID 16589)
-- Dependencies: 1994 6
-- Name: sys_rates; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE sys_rates (
    rates_id bigint DEFAULT nextval('sys_rates_ratesid_seq'::regclass) NOT NULL,
    rates_name character varying(20),
    start_time timestamp without time zone,
    end_time timestamp without time zone,
    rates_type integer,
    rates_tarriff numeric,
    status integer,
    rates_status integer
);


ALTER TABLE public.sys_rates OWNER TO lcmadmin;

--
-- TOC entry 1644 (class 1259 OID 16596)
-- Dependencies: 6
-- Name: sysrole_roleid_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE sysrole_roleid_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sysrole_roleid_seq OWNER TO lcmadmin;

--
-- TOC entry 1645 (class 1259 OID 16598)
-- Dependencies: 1995 1996 1997 6
-- Name: sys_role; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE sys_role (
    role_id bigint DEFAULT nextval('sysrole_roleid_seq'::regclass) NOT NULL,
    role_name character varying(50) NOT NULL,
    role_desc character varying(200) NOT NULL,
    customer_id bigint NOT NULL,
    status integer DEFAULT 0,
    role_code character varying(30) DEFAULT ''::character varying NOT NULL
);


ALTER TABLE public.sys_role OWNER TO lcmadmin;

--
-- TOC entry 2119 (class 0 OID 0)
-- Dependencies: 1645
-- Name: COLUMN sys_role.status; Type: COMMENT; Schema: public; Owner: lcmadmin
--

COMMENT ON COLUMN sys_role.status IS '璁板綍鐘舵�侊紝鐢ㄤ簬鏍囪瘑璇ユ潯璁板綍鐨勭姸鎬侊紝-1涓哄凡鍒犻櫎锛�0涓烘柊寤猴紝1涓烘湁鏁堢姸鎬�';


--
-- TOC entry 1646 (class 1259 OID 16604)
-- Dependencies: 6 1645
-- Name: sys_role_role_id_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE sys_role_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sys_role_role_id_seq OWNER TO lcmadmin;

--
-- TOC entry 2120 (class 0 OID 0)
-- Dependencies: 1646
-- Name: sys_role_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: lcmadmin
--

ALTER SEQUENCE sys_role_role_id_seq OWNED BY sys_role.role_id;


--
-- TOC entry 1647 (class 1259 OID 16606)
-- Dependencies: 6
-- Name: sysuser_userid_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE sysuser_userid_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sysuser_userid_seq OWNER TO lcmadmin;

--
-- TOC entry 1648 (class 1259 OID 16608)
-- Dependencies: 1998 1999 2000 2001 6
-- Name: sys_user; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE sys_user
(
  user_id integer NOT NULL DEFAULT nextval('sysuser_userid_seq'::regclass),
  user_name character varying(20),
  real_name character varying(30),
  is_enabled boolean,
  "password" character varying(60),
  customer_id bigint DEFAULT 0,
  mobile character varying(20),
  phone character varying(15),
  email character varying(50),
  account_non_expired boolean,
  account_non_locked boolean,
  is_credentials_non_expired boolean DEFAULT true,
  user_type integer DEFAULT 0,
  lcc_account character varying(15),
  department_id bigint,
  status integer,
  sip_id integer,
  md5passwd character varying(50),
  user_position character varying(50),
  code character varying(20),
  sign character varying(200),
  gender character varying(10),
  mypicture character varying(100)
);


ALTER TABLE public.sys_user OWNER TO lcmadmin;

--
-- TOC entry 2121 (class 0 OID 0)
-- Dependencies: 1648
-- Name: TABLE sys_user; Type: COMMENT; Schema: public; Owner: lcmadmin
--

COMMENT ON TABLE sys_user IS '绯荤粺鐢ㄦ埛琛�';


--
-- TOC entry 1649 (class 1259 OID 16615)
-- Dependencies: 1648 6
-- Name: sys_user_user_id_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE sys_user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sys_user_user_id_seq OWNER TO lcmadmin;

--
-- TOC entry 2122 (class 0 OID 0)
-- Dependencies: 1649
-- Name: sys_user_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: lcmadmin
--

ALTER SEQUENCE sys_user_user_id_seq OWNED BY sys_user.user_id;


--
-- TOC entry 1650 (class 1259 OID 16617)
-- Dependencies: 6
-- Name: sys_userinconf; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE sys_userinconf (
    user_id bigint NOT NULL,
    conf_id bigint NOT NULL
);


ALTER TABLE public.sys_userinconf OWNER TO lcmadmin;

--
-- TOC entry 1651 (class 1259 OID 16620)
-- Dependencies: 6
-- Name: sys_userincronconf; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE sys_userincronconf (
    user_id bigint NOT NULL,
    cron_conf_id bigint NOT NULL
);


ALTER TABLE public.sys_userincronconf OWNER TO lcmadmin;

--
-- TOC entry 1652 (class 1259 OID 16623)
-- Dependencies: 6
-- Name: sys_userindeparment; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE sys_userindeparment (
    user_id bigint NOT NULL,
    deparment_id bigint NOT NULL
);


ALTER TABLE public.sys_userindeparment OWNER TO lcmadmin;

--
-- TOC entry 1653 (class 1259 OID 16626)
-- Dependencies: 6
-- Name: sys_userinrole; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE sys_userinrole (
    role_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.sys_userinrole OWNER TO lcmadmin;

--
-- TOC entry 2123 (class 0 OID 0)
-- Dependencies: 1653
-- Name: TABLE sys_userinrole; Type: COMMENT; Schema: public; Owner: lcmadmin
--

COMMENT ON TABLE sys_userinrole IS '鐢ㄦ埛鍜岃鑹茶繛鎺ヨ〃';


--
-- TOC entry 1654 (class 1259 OID 16629)
-- Dependencies: 6
-- Name: sys_userstatus_statusid_seq; Type: SEQUENCE; Schema: public; Owner: lcmadmin
--

CREATE SEQUENCE sys_userstatus_statusid_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sys_userstatus_statusid_seq OWNER TO lcmadmin;

--
-- TOC entry 1655 (class 1259 OID 16631)
-- Dependencies: 2002 6
-- Name: sys_userstatus; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE sys_userstatus (
    userstatus_id bigint DEFAULT nextval('sys_userstatus_statusid_seq'::regclass) NOT NULL,
    end_time timestamp without time zone,
    start_time timestamp without time zone,
    user_id bigint
);


ALTER TABLE public.sys_userstatus OWNER TO lcmadmin;

--
-- TOC entry 1656 (class 1259 OID 16635)
-- Dependencies: 2003 2004 2005 6
-- Name: table_count; Type: TABLE; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE TABLE table_count (
    id integer NOT NULL,
    sipcount bigint DEFAULT 0 NOT NULL,
    extensionscount bigint DEFAULT 0 NOT NULL,
    logcount bigint DEFAULT 0 NOT NULL
);


ALTER TABLE public.table_count OWNER TO lcmadmin;

CREATE OR REPLACE VIEW openfireuser AS 
 SELECT sys_user.lcc_account AS username, sys_user.email, sys_user.real_name AS name, sys_user.md5passwd AS password
   FROM sys_user
  WHERE sys_user.status = 1;

ALTER TABLE openfireuser OWNER TO lcmadmin;

--
-- TOC entry 1934 (class 2604 OID 16641)
-- Dependencies: 1596 1595
-- Name: id; Type: DEFAULT; Schema: public; Owner: lcmadmin
--

ALTER TABLE acl_class ALTER COLUMN id SET DEFAULT nextval('acl_class_id_seq'::regclass);


--
-- TOC entry 1935 (class 2604 OID 16642)
-- Dependencies: 1598 1597
-- Name: id; Type: DEFAULT; Schema: public; Owner: lcmadmin
--

ALTER TABLE acl_entry ALTER COLUMN id SET DEFAULT nextval('acl_entry_id_seq'::regclass);


--
-- TOC entry 1936 (class 2604 OID 16643)
-- Dependencies: 1600 1599
-- Name: id; Type: DEFAULT; Schema: public; Owner: lcmadmin
--

ALTER TABLE acl_object_identity ALTER COLUMN id SET DEFAULT nextval('acl_object_identity_id_seq'::regclass);


--
-- TOC entry 1937 (class 2604 OID 16644)
-- Dependencies: 1602 1601
-- Name: id; Type: DEFAULT; Schema: public; Owner: lcmadmin
--

ALTER TABLE acl_sid ALTER COLUMN id SET DEFAULT nextval('acl_sid_id_seq'::regclass);


--
-- TOC entry 1943 (class 2604 OID 16645)
-- Dependencies: 1606 1605
-- Name: id; Type: DEFAULT; Schema: public; Owner: lcmadmin
--

ALTER TABLE extensions_conf ALTER COLUMN id SET DEFAULT nextval('extensions_conf_id_seq'::regclass);


--
-- TOC entry 1965 (class 2604 OID 16646)
-- Dependencies: 1619 1618
-- Name: id; Type: DEFAULT; Schema: public; Owner: lcmadmin
--

ALTER TABLE sip_conf ALTER COLUMN id SET DEFAULT nextval('sip_conf_id_seq'::regclass);


--
-- TOC entry 2017 (class 2606 OID 16648)
-- Dependencies: 1604 1604
-- Name: event_opt_log_pkey; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY event_opt_log
    ADD CONSTRAINT event_opt_log_pkey PRIMARY KEY (id);


--
-- TOC entry 2019 (class 2606 OID 16650)
-- Dependencies: 1605 1605
-- Name: extensions_conf_pkey; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY extensions_conf
    ADD CONSTRAINT extensions_conf_pkey PRIMARY KEY (id);


--
-- TOC entry 2021 (class 2606 OID 16652)
-- Dependencies: 1608 1608
-- Name: friend_pk; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY friend
    ADD CONSTRAINT friend_pk PRIMARY KEY (id);


--
-- TOC entry 2095 (class 2606 OID 16654)
-- Dependencies: 1656 1656
-- Name: id; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY table_count
    ADD CONSTRAINT id PRIMARY KEY (id);


--
-- TOC entry 2023 (class 2606 OID 16656)
-- Dependencies: 1610 1610
-- Name: lcc_monitor_pkey; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY lcc_monitor
    ADD CONSTRAINT lcc_monitor_pkey PRIMARY KEY (id);


--
-- TOC entry 2025 (class 2606 OID 16658)
-- Dependencies: 1611 1611
-- Name: lcc_monitor_pkey2; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY lcc_monitor2
    ADD CONSTRAINT lcc_monitor_pkey2 PRIMARY KEY (id);


--
-- TOC entry 2031 (class 2606 OID 16660)
-- Dependencies: 1617 1617
-- Name: peoplemonitor_pk; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY peopleinmonitor
    ADD CONSTRAINT peoplemonitor_pk PRIMARY KEY (id);


--
-- TOC entry 2007 (class 2606 OID 16662)
-- Dependencies: 1595 1595
-- Name: pk_acl_class_class; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY acl_class
    ADD CONSTRAINT pk_acl_class_class PRIMARY KEY (id);


--
-- TOC entry 2011 (class 2606 OID 16664)
-- Dependencies: 1597 1597
-- Name: pk_acl_entry_id; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY acl_entry
    ADD CONSTRAINT pk_acl_entry_id PRIMARY KEY (id);


--
-- TOC entry 2013 (class 2606 OID 16666)
-- Dependencies: 1599 1599
-- Name: pk_acl_object_identity_id; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY acl_object_identity
    ADD CONSTRAINT pk_acl_object_identity_id PRIMARY KEY (id);


--
-- TOC entry 2015 (class 2606 OID 16668)
-- Dependencies: 1601 1601
-- Name: pk_acl_sid_id; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY acl_sid
    ADD CONSTRAINT pk_acl_sid_id PRIMARY KEY (id);


--
-- TOC entry 2048 (class 2606 OID 16670)
-- Dependencies: 1631 1631
-- Name: pk_customer_customerid; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY sys_customer
    ADD CONSTRAINT pk_customer_customerid PRIMARY KEY (customer_id);


--
-- TOC entry 2027 (class 2606 OID 16672)
-- Dependencies: 1613 1613
-- Name: pk_mcu_mixer_mixerid; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY mcu_mixer
    ADD CONSTRAINT pk_mcu_mixer_mixerid PRIMARY KEY (mixer_id);


--
-- TOC entry 2029 (class 2606 OID 16674)
-- Dependencies: 1615 1615
-- Name: pk_mcu_server_serverid; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY mcu_server
    ADD CONSTRAINT pk_mcu_server_serverid PRIMARY KEY (server_id);


--
-- TOC entry 2033 (class 2606 OID 16676)
-- Dependencies: 1618 1618
-- Name: pk_sip_config; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY sip_conf
    ADD CONSTRAINT pk_sip_config PRIMARY KEY (id);


--
-- TOC entry 2035 (class 2606 OID 16678)
-- Dependencies: 1621 1621
-- Name: pk_static_city_cityid; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY static_city
    ADD CONSTRAINT pk_static_city_cityid PRIMARY KEY (city_id);


--
-- TOC entry 2037 (class 2606 OID 16680)
-- Dependencies: 1623 1623
-- Name: pk_sys_billing_billingid; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY sys_billing
    ADD CONSTRAINT pk_sys_billing_billingid PRIMARY KEY (billing_id);


--
-- TOC entry 2040 (class 2606 OID 16682)
-- Dependencies: 1625 1625
-- Name: pk_sys_conference_confid; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY sys_conference
    ADD CONSTRAINT pk_sys_conference_confid PRIMARY KEY (conf_id);


--
-- TOC entry 2043 (class 2606 OID 16684)
-- Dependencies: 1627 1627
-- Name: pk_sys_confno_noid; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY sys_confno
    ADD CONSTRAINT pk_sys_confno_noid PRIMARY KEY (no_id);


--
-- TOC entry 2046 (class 2606 OID 16686)
-- Dependencies: 1629 1629
-- Name: pk_sys_cron_conference_confid; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY sys_cron_conference
    ADD CONSTRAINT pk_sys_cron_conference_confid PRIMARY KEY (conf_id);


--
-- TOC entry 2052 (class 2606 OID 16688)
-- Dependencies: 1633 1633
-- Name: pk_sys_deparment_deparmentid; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY sys_deparment
    ADD CONSTRAINT pk_sys_deparment_deparmentid PRIMARY KEY (deparment_id);


--
-- TOC entry 2057 (class 2606 OID 16690)
-- Dependencies: 1635 1635
-- Name: pk_sys_media_id; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY sys_media
    ADD CONSTRAINT pk_sys_media_id PRIMARY KEY (id);


--
-- TOC entry 2059 (class 2606 OID 16692)
-- Dependencies: 1637 1637
-- Name: pk_sys_nostatus_id; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY sys_nostatus
    ADD CONSTRAINT pk_sys_nostatus_id PRIMARY KEY (nostatus_id);


--
-- TOC entry 2062 (class 2606 OID 16694)
-- Dependencies: 1639 1639
-- Name: pk_sys_operate_recordid; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY sys_operate_record
    ADD CONSTRAINT pk_sys_operate_recordid PRIMARY KEY (id);


--
-- TOC entry 2066 (class 2606 OID 16696)
-- Dependencies: 1643 1643
-- Name: pk_sys_rates_ratesid; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY sys_rates
    ADD CONSTRAINT pk_sys_rates_ratesid PRIMARY KEY (rates_id);


--
-- TOC entry 2070 (class 2606 OID 16698)
-- Dependencies: 1645 1645
-- Name: pk_sys_role_roleid; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY sys_role
    ADD CONSTRAINT pk_sys_role_roleid PRIMARY KEY (role_id);


--
-- TOC entry 2081 (class 2606 OID 16700)
-- Dependencies: 1650 1650 1650
-- Name: pk_sys_userinconf_userid_confid; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY sys_userinconf
    ADD CONSTRAINT pk_sys_userinconf_userid_confid PRIMARY KEY (user_id, conf_id);


--
-- TOC entry 2083 (class 2606 OID 16702)
-- Dependencies: 1651 1651 1651
-- Name: pk_sys_userincronconf_userid_confid; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY sys_userincronconf
    ADD CONSTRAINT pk_sys_userincronconf_userid_confid PRIMARY KEY (user_id, cron_conf_id);


--
-- TOC entry 2088 (class 2606 OID 16704)
-- Dependencies: 1653 1653 1653
-- Name: pk_sys_userinrole_userid_roleid; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY sys_userinrole
    ADD CONSTRAINT pk_sys_userinrole_userid_roleid PRIMARY KEY (role_id, user_id);


--
-- TOC entry 2075 (class 2606 OID 16706)
-- Dependencies: 1648 1648
-- Name: pk_sysuser_userid; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY sys_user
    ADD CONSTRAINT pk_sysuser_userid PRIMARY KEY (user_id);


--
-- TOC entry 2085 (class 2606 OID 16708)
-- Dependencies: 1652 1652 1652
-- Name: pk_userindeparment; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY sys_userindeparment
    ADD CONSTRAINT pk_userindeparment PRIMARY KEY (user_id, deparment_id);


--
-- TOC entry 2092 (class 2606 OID 16710)
-- Dependencies: 1655 1655
-- Name: pk_userstatus_statusid; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY sys_userstatus
    ADD CONSTRAINT pk_userstatus_statusid PRIMARY KEY (userstatus_id);


--
-- TOC entry 2064 (class 2606 OID 16712)
-- Dependencies: 1641 1641
-- Name: sys_params_pkey; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY sys_params
    ADD CONSTRAINT sys_params_pkey PRIMARY KEY (id);


--
-- TOC entry 2009 (class 2606 OID 16714)
-- Dependencies: 1595 1595
-- Name: uk_acl_class_class; Type: CONSTRAINT; Schema: public; Owner: lcmadmin; Tablespace: 
--

ALTER TABLE ONLY acl_class
    ADD CONSTRAINT uk_acl_class_class UNIQUE (class);


--
-- TOC entry 2073 (class 1259 OID 16715)
-- Dependencies: 1648
-- Name: index_sys_user_userid; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE UNIQUE INDEX index_sys_user_userid ON sys_user USING btree (user_id);


--
-- TOC entry 2086 (class 1259 OID 16716)
-- Dependencies: 1653 1653
-- Name: index_sys_userinrole_userid_roleid; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE UNIQUE INDEX index_sys_userinrole_userid_roleid ON sys_userinrole USING btree (role_id, user_id);


--
-- TOC entry 2038 (class 1259 OID 16717)
-- Dependencies: 1623
-- Name: sys_billing_index_confid; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE INDEX sys_billing_index_confid ON sys_billing USING btree (conf_id);


--
-- TOC entry 2041 (class 1259 OID 16718)
-- Dependencies: 1625
-- Name: sys_conference_index_confid; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE INDEX sys_conference_index_confid ON sys_conference USING btree (conf_id);


--
-- TOC entry 2044 (class 1259 OID 16719)
-- Dependencies: 1627
-- Name: sys_confno_index_noid; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE INDEX sys_confno_index_noid ON sys_confno USING btree (no_id);


--
-- TOC entry 2049 (class 1259 OID 16720)
-- Dependencies: 1631 1631
-- Name: sys_customer_index_code_status; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE INDEX sys_customer_index_code_status ON sys_customer USING btree (status, customer_code);


--
-- TOC entry 2050 (class 1259 OID 16721)
-- Dependencies: 1631
-- Name: sys_customer_mcu_index; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE INDEX sys_customer_mcu_index ON sys_customer USING btree (mcu_server_id);


--
-- TOC entry 2053 (class 1259 OID 16722)
-- Dependencies: 1633
-- Name: sys_deparment_index_customerid; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE INDEX sys_deparment_index_customerid ON sys_deparment USING btree (deparment_customer_id);


--
-- TOC entry 2054 (class 1259 OID 16723)
-- Dependencies: 1633 1633 1633
-- Name: sys_deparment_index_deptmsg; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE INDEX sys_deparment_index_deptmsg ON sys_deparment USING btree (deparment_customer_id, deparment_isroot, status);


--
-- TOC entry 2055 (class 1259 OID 16724)
-- Dependencies: 1633
-- Name: sys_deparment_index_id; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE INDEX sys_deparment_index_id ON sys_deparment USING btree (deparment_id);


--
-- TOC entry 2060 (class 1259 OID 16725)
-- Dependencies: 1637
-- Name: sys_nostatus_index_id; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE INDEX sys_nostatus_index_id ON sys_nostatus USING btree (no_id);


--
-- TOC entry 2067 (class 1259 OID 16726)
-- Dependencies: 1643
-- Name: sys_rates_index_name; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE INDEX sys_rates_index_name ON sys_rates USING btree (rates_name);


--
-- TOC entry 2068 (class 1259 OID 16727)
-- Dependencies: 1643 1643
-- Name: sys_rates_index_status; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE INDEX sys_rates_index_status ON sys_rates USING btree (status, rates_status);


--
-- TOC entry 2071 (class 1259 OID 16728)
-- Dependencies: 1645
-- Name: sys_role_index_customer_id; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE INDEX sys_role_index_customer_id ON sys_role USING btree (customer_id);


--
-- TOC entry 2072 (class 1259 OID 16729)
-- Dependencies: 1645
-- Name: sys_role_index_rolecode; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE INDEX sys_role_index_rolecode ON sys_role USING btree (role_code);


--
-- TOC entry 2076 (class 1259 OID 16730)
-- Dependencies: 1648 1648
-- Name: sys_user_index; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE INDEX sys_user_index ON sys_user USING btree (user_name, status);


--
-- TOC entry 2077 (class 1259 OID 16731)
-- Dependencies: 1648
-- Name: sys_user_index2; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE INDEX sys_user_index2 ON sys_user USING btree (user_id);


--
-- TOC entry 2078 (class 1259 OID 16732)
-- Dependencies: 1648 1648 1648 1648 1648
-- Name: sys_user_index_accountmsg; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE INDEX sys_user_index_accountmsg ON sys_user USING btree (is_enabled, account_non_expired, account_non_locked, user_type, status);


--
-- TOC entry 2079 (class 1259 OID 16733)
-- Dependencies: 1648
-- Name: sys_user_index_lccaccount; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE INDEX sys_user_index_lccaccount ON sys_user USING btree (lcc_account);


--
-- TOC entry 2089 (class 1259 OID 16734)
-- Dependencies: 1653
-- Name: sys_userinrole_index; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE INDEX sys_userinrole_index ON sys_userinrole USING btree (user_id);


--
-- TOC entry 2090 (class 1259 OID 16735)
-- Dependencies: 1653
-- Name: sys_userinrole_index_userid; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE INDEX sys_userinrole_index_userid ON sys_userinrole USING btree (user_id);


--
-- TOC entry 2093 (class 1259 OID 16736)
-- Dependencies: 1655
-- Name: sys_userstatus_index; Type: INDEX; Schema: public; Owner: lcmadmin; Tablespace: 
--

CREATE INDEX sys_userstatus_index ON sys_userstatus USING btree (user_id);


--
-- TOC entry 2096 (class 2606 OID 16737)
-- Dependencies: 1601 1597 2014
-- Name: acl_entry_sid; Type: FK CONSTRAINT; Schema: public; Owner: lcmadmin
--

ALTER TABLE ONLY acl_entry
    ADD CONSTRAINT acl_entry_sid FOREIGN KEY (sid) REFERENCES acl_sid(id);


--
-- TOC entry 2097 (class 2606 OID 16742)
-- Dependencies: 1599 1597 2012
-- Name: fk_acl_entry_acl_object_identity; Type: FK CONSTRAINT; Schema: public; Owner: lcmadmin
--

ALTER TABLE ONLY acl_entry
    ADD CONSTRAINT fk_acl_entry_acl_object_identity FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity(id);


--
-- TOC entry 2098 (class 2606 OID 16747)
-- Dependencies: 1595 2006 1599
-- Name: fk_object_id_class; Type: FK CONSTRAINT; Schema: public; Owner: lcmadmin
--

ALTER TABLE ONLY acl_object_identity
    ADD CONSTRAINT fk_object_id_class FOREIGN KEY (object_id_class) REFERENCES acl_class(id);


--
-- TOC entry 2099 (class 2606 OID 16752)
-- Dependencies: 1599 1601 2014
-- Name: fk_owner_sid; Type: FK CONSTRAINT; Schema: public; Owner: lcmadmin
--

ALTER TABLE ONLY acl_object_identity
    ADD CONSTRAINT fk_owner_sid FOREIGN KEY (owner_sid) REFERENCES acl_sid(id);


--
-- TOC entry 2104 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2011-07-26 16:18:43

--
-- PostgreSQL database dump complete
--








-- ------------------- 会议权限 --------------------------
-- Sequence: authority_seq

-- DROP SEQUENCE authority_seq;

CREATE SEQUENCE authority_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 99999999999999999
  START 1
  CACHE 1;
ALTER TABLE authority_seq OWNER TO lcmadmin;


-- Sequence: conf_role_authority_seq

-- DROP SEQUENCE conf_role_authority_seq;

CREATE SEQUENCE conf_role_authority_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE conf_role_authority_seq OWNER TO lcmadmin;


-- Sequence: conf_user_authority_seq

-- DROP SEQUENCE conf_user_authority_seq;

CREATE SEQUENCE conf_user_authority_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE conf_user_authority_seq OWNER TO lcmadmin;


-- Sequence: conf_user_role_seq

-- DROP SEQUENCE conf_user_role_seq;

CREATE SEQUENCE conf_user_role_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE conf_user_role_seq OWNER TO lcmadmin;


-- Sequence: conference_role_seq

-- DROP SEQUENCE conference_role_seq;

CREATE SEQUENCE conference_role_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE conference_role_seq OWNER TO lcmadmin;


-- Sequence: conference_seq

-- DROP SEQUENCE conference_seq;

CREATE SEQUENCE conference_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE conference_seq OWNER TO lcmadmin;


-- Sequence: conference_type_role_seq

-- DROP SEQUENCE conference_type_role_seq;

CREATE SEQUENCE conference_type_role_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE conference_type_role_seq OWNER TO lcmadmin;


-- Sequence: conference_type_seq

-- DROP SEQUENCE conference_type_seq;

CREATE SEQUENCE conference_type_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 99999999999999999
  START 1
  CACHE 1;
ALTER TABLE conference_type_seq OWNER TO lcmadmin;


-- Sequence: conference_user_seq

-- DROP SEQUENCE conference_user_seq;

CREATE SEQUENCE conference_user_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 999999999999999999
  START 1
  CACHE 1;
ALTER TABLE conference_user_seq OWNER TO lcmadmin;


-- Table: authority

-- DROP TABLE authority;

CREATE TABLE authority
(
  id bigint NOT NULL DEFAULT nextval('authority_seq'::regclass),
  authority_name character varying(100),
  mark character varying(100) NOT NULL,
  del integer NOT NULL DEFAULT 1,
  CONSTRAINT "PK_authority" PRIMARY KEY (id),
  CONSTRAINT unique_authority_mark UNIQUE (mark)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE authority OWNER TO lcmadmin;
COMMENT ON TABLE authority IS '权限表';





-- Table: conf_role_authority

-- DROP TABLE conf_role_authority;

CREATE TABLE conf_role_authority
(
  id bigint NOT NULL DEFAULT nextval('conf_role_authority_seq'::regclass),
  authority_id bigint NOT NULL,
  role_id bigint NOT NULL,
  CONSTRAINT pk_conf_role_authority PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE conf_role_authority OWNER TO lcmadmin;
COMMENT ON TABLE conf_role_authority IS '会议角色权限表';





-- Table: conf_user_authority

-- DROP TABLE conf_user_authority;

CREATE TABLE conf_user_authority
(
  id bigint NOT NULL DEFAULT nextval('conf_user_authority_seq'::regclass),
  conference_user_id bigint NOT NULL,
  authority_id bigint NOT NULL,
  CONSTRAINT pk_conf_user_authority PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE conf_user_authority OWNER TO lcmadmin;
COMMENT ON TABLE conf_user_authority IS '某一会议用户权限表';




-- Table: conf_user_role

-- DROP TABLE conf_user_role;

CREATE TABLE conf_user_role
(
  id bigint NOT NULL DEFAULT nextval('conf_user_role_seq'::regclass),
  conference_user_id bigint NOT NULL,
  role_id bigint NOT NULL,
  CONSTRAINT pk_conf_user_role PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE conf_user_role OWNER TO lcmadmin;
COMMENT ON TABLE conf_user_role IS '用户在某一个会议里面的角色';





-- Table: conference

-- DROP TABLE conference;

CREATE TABLE conference
(
  id bigint NOT NULL DEFAULT nextval('conference_seq'::regclass),
  conference_name character varying(100) NOT NULL,
  creator bigint NOT NULL,
  need_apply integer NOT NULL DEFAULT 0,
  default_role_id bigint DEFAULT 0,
  conference_type_id bigint DEFAULT 0,
  del integer NOT NULL DEFAULT 1,
  confno character varying(50) NOT NULL DEFAULT '0'::character varying,
  topic character varying(100),
  description character varying(250),
  password character varying(250),
  CONSTRAINT pk_conference PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE conference OWNER TO lcmadmin;
COMMENT ON TABLE conference IS '会议信息表';




-- Table: conference_role

-- DROP TABLE conference_role;

CREATE TABLE conference_role
(
  id bigint NOT NULL DEFAULT nextval('conference_role_seq'::regclass),
  role_name character varying(100) NOT NULL,
  del integer NOT NULL DEFAULT 1,
  CONSTRAINT pk_conference_role PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE conference_role OWNER TO lcmadmin;




-- Table: conference_type

-- DROP TABLE conference_type;

CREATE TABLE conference_type
(
  id bigint NOT NULL DEFAULT nextval('conference_type_seq'::regclass),
  type_name character varying(100) NOT NULL,
  del integer NOT NULL DEFAULT 1,
  CONSTRAINT pk_conftype_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE conference_type OWNER TO lcmadmin;
COMMENT ON TABLE conference_type IS '会议类型表';




-- Table: conference_type_role

-- DROP TABLE conference_type_role;

CREATE TABLE conference_type_role
(
  id bigint NOT NULL DEFAULT nextval('conference_type_role_seq'::regclass),
  conference_type_id bigint NOT NULL,
  conference_role_id bigint NOT NULL,
  CONSTRAINT pk_conference_type_role PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE conference_type_role OWNER TO lcmadmin;
COMMENT ON TABLE conference_type_role IS '会议类型角色表';




-- Table: conference_user

-- DROP TABLE conference_user;

CREATE TABLE conference_user
(
  id bigint NOT NULL DEFAULT nextval('conference_user_seq'::regclass),
  conference_id bigint NOT NULL,
  user_id bigint NOT NULL,
  CONSTRAINT pk_conference_user PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE conference_user OWNER TO lcmadmin;
COMMENT ON TABLE conference_user IS '某一个会议的用户关系表';


------------========= opensips 连接 lcmDB_Dev 所需的表 ==============start-------
------------创建location表----------
CREATE TABLE "location"
(
  id serial NOT NULL,
  username character varying(64) NOT NULL DEFAULT ''::character varying,
  "domain" character varying(64) NOT NULL DEFAULT ''::character varying,
  contact character varying(255) NOT NULL DEFAULT ''::character varying,
  received character varying(128) DEFAULT NULL::character varying,
  path character varying(128) DEFAULT NULL::character varying,
  expires timestamp without time zone NOT NULL DEFAULT '2020-05-28 21:32:15'::timestamp without time zone,
  q real NOT NULL DEFAULT 1.0,
  callid character varying(255) NOT NULL DEFAULT 'Default-Call-ID'::character varying,
  cseq integer NOT NULL DEFAULT 13,
  last_modified timestamp without time zone NOT NULL DEFAULT '1900-01-01 00:00:01'::timestamp without time zone,
  flags integer NOT NULL DEFAULT 0,
  cflags integer NOT NULL DEFAULT 0,
  user_agent character varying(255) NOT NULL DEFAULT ''::character varying,
  socket character varying(64) DEFAULT NULL::character varying,
  methods integer,
  sip_instance character varying(255) DEFAULT NULL::character varying,
  CONSTRAINT location_pkey PRIMARY KEY (id),
  CONSTRAINT location_account_contact_idx UNIQUE (username, domain, contact, callid)
);

---------- 创建version表------------
CREATE TABLE version
(
  table_name character varying(32) NOT NULL,
  table_version integer NOT NULL DEFAULT 0,
  CONSTRAINT version_t_name_idx UNIQUE (table_name)
);

----------- 向version表插入数据-----
insert into version values('subscriber',7);
insert into version values('location',1007);

---------subscriber视图-------------
CREATE OR REPLACE VIEW subscriber AS 
 SELECT sip_conf.id, sip_conf.name AS username, sip_conf.secret AS password, '' AS domain, '' AS email_address, '' AS hal, '' AS halb, '' AS rpid
   FROM sip_conf;

----------视频社区-------------------
CREATE SEQUENCE videoclip_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE videoclip_id_seq OWNER TO lcmadmin;

CREATE TABLE videoclip
(
  id bigint NOT NULL DEFAULT nextval('videoclip_id_seq'::regclass),
  videoclipurl character varying(2048),
  thumbnailurl character varying(2048),
  title character varying(100),
  description character varying(255),
  category character varying(50),
  CONSTRAINT pk_videoclip_id PRIMARY KEY (id)
);

------------========================================================end---------

