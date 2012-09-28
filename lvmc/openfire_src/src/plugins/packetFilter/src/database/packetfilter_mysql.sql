CREATE TABLE ofPfRules (
   id           BIGINT          NOT NULL AUTO_INCREMENT,
   ruleorder    BIGINT      ,
   type         varchar(255)     ,
   tojid       varchar(255)    ,
   fromjid     varchar(255)    ,
   rulef         varchar(255)   ,
   disabled     boolean,
   log          boolean,
   description  varchar(255),
   sourcetype   varchar(255),
   desttype     varchar(255),
   PRIMARY KEY (id)
);
INSERT INTO ofVersion(name,version) values('packetfilter',2);