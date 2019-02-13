-- -- FOR clearing, drop all sequence and tables
--
-- BEGIN
--
--  --Bye Sequences!
--  FOR i IN (SELECT us.sequence_name
--              FROM USER_SEQUENCES us) LOOP
--    EXECUTE IMMEDIATE 'drop sequence '|| i.sequence_name ||'';
--  END LOOP;
--
--  --Bye Tables!
--  FOR i IN (SELECT ut.table_name
--              FROM USER_TABLES ut) LOOP
--    EXECUTE IMMEDIATE 'drop table '|| i.table_name ||' CASCADE CONSTRAINTS ';
--  END LOOP;
--
--  --Bye Views!
--  FOR i IN (SELECT uv.view_name
--              FROM USER_VIEWS uv) LOOP
--    EXECUTE IMMEDIATE 'drop view '|| i.view_name ||' CASCADE CONSTRAINTS ';
--  END LOOP;
-- END;

-- -- for empty recycle bin, it cannot be run with above code in a single statement; need to run it in a separate statement
-- PURGE RECYCLEBIN;

--------------------------------------------------------
--  DDL for Sequence ROLE_SEQ
--------------------------------------------------------

--------------------------------------------------------
--  DDL for DB Link IDOINT10G.ORA11G
--------------------------------------------------------

  CREATE DATABASE LINK "IDOINT10G.ORA11G"
   CONNECT TO "GPS_LOGIN" IDENTIFIED BY VALUES '050666F86323DB45FB6D8E17E6B0BA86B30C63708A89F9F93E'
   USING 'idoint.gpsint';
--------------------------------------------------------
--  DDL for Sequence ROLE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ROLE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table CLIENTDETAILS
--------------------------------------------------------

  CREATE TABLE "CLIENTDETAILS"
   (	"APPID" VARCHAR2(255 BYTE),
	"RESOURCEIDS" VARCHAR2(255 BYTE),
	"APPSECRET" VARCHAR2(255 BYTE),
	"SCOPE" VARCHAR2(255 BYTE),
	"GRANTTYPES" VARCHAR2(255 BYTE),
	"REDIRECTURL" VARCHAR2(255 BYTE),
	"AUTHORITIES" VARCHAR2(255 BYTE),
	"ACCESS_TOKEN_VALIDITY" NUMBER,
	"REFRESH_TOKEN_VALIDITY" NUMBER, 
	"ADDITIONALINFORMATION" VARCHAR2(4000 BYTE),
	"AUTOAPPROVESCOPES" VARCHAR2(255 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table OAUTH_ACCESS_TOKEN
--------------------------------------------------------

  CREATE TABLE "OAUTH_ACCESS_TOKEN"
   (	"TOKEN_ID" VARCHAR2(255 BYTE),
	"TOKEN" BLOB,
	"AUTHENTICATION_ID" VARCHAR2(255 BYTE),
	"USER_NAME" VARCHAR2(255 BYTE),
	"CLIENT_ID" VARCHAR2(255 BYTE),
	"AUTHENTICATION" BLOB,
	"REFRESH_TOKEN" VARCHAR2(255 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table OAUTH_APPROVALS
--------------------------------------------------------

  CREATE TABLE "OAUTH_APPROVALS"
   (	"USERID" VARCHAR2(255 BYTE),
	"CLIENTID" VARCHAR2(255 BYTE),
	"SCOPE" VARCHAR2(255 BYTE),
	"STATUS" VARCHAR2(10 BYTE),
	"EXPIRESAT" DATE,
	"LASTMODIFIEDAT" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table OAUTH_CLIENT_DETAILS
--------------------------------------------------------

  CREATE TABLE "OAUTH_CLIENT_DETAILS"
   (	"CLIENT_ID" VARCHAR2(255 BYTE),
	"RESOURCE_IDS" VARCHAR2(255 BYTE),
	"CLIENT_SECRET" VARCHAR2(255 BYTE),
	"SCOPE" VARCHAR2(255 BYTE),
	"AUTHORIZED_GRANT_TYPES" VARCHAR2(255 BYTE),
	"WEB_SERVER_REDIRECT_URI" VARCHAR2(255 BYTE),
	"AUTHORITIES" VARCHAR2(255 BYTE),
	"ACCESS_TOKEN_VALIDITY" NUMBER,
	"REFRESH_TOKEN_VALIDITY" NUMBER,
	"ADDITIONAL_INFORMATION" VARCHAR2(4000 BYTE),
	"AUTOAPPROVE" VARCHAR2(255 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table OAUTH_CLIENT_TOKEN
--------------------------------------------------------

  CREATE TABLE "OAUTH_CLIENT_TOKEN"
   (	"TOKEN_ID" VARCHAR2(255 BYTE),
	"TOKEN" BLOB,
	"AUTHENTICATION_ID" VARCHAR2(255 BYTE),
	"USER_NAME" VARCHAR2(255 BYTE),
	"CLIENT_ID" VARCHAR2(255 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Table OAUTH_CODE
--------------------------------------------------------

  CREATE TABLE "OAUTH_CODE"
   (	"CODE" VARCHAR2(255 BYTE),
	"AUTHENTICATION" BLOB
   ) ;
--------------------------------------------------------
--  DDL for Table OAUTH_REFRESH_TOKEN
--------------------------------------------------------

  CREATE TABLE "OAUTH_REFRESH_TOKEN"
   (	"TOKEN_ID" VARCHAR2(255 BYTE),
	"TOKEN" BLOB,
	"AUTHENTICATION" BLOB
   ) ;
--------------------------------------------------------
--  DDL for Table ROLE
--------------------------------------------------------

  CREATE TABLE "ROLE"
   (	"FUNC_NO" NUMBER(4,0),
	"APP" NVARCHAR2(255),
	"ROLE" NVARCHAR2(255),
	"ID" NUMBER
   ) ;
--------------------------------------------------------
--  DDL for View GPS
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "GPS" ("FUNC_NO", "NOME", "NOMEC", "UNORG", "PASSWD", "EMAIL") AS
  select
    e.func_no,
    e.nome,
    e.nomec,
    e.unorg,
    e.passwd,
    g.email
from gps.efectivo @ IDOINT10G e left join gps.gps_email @ IDOINT10G g
on e.func_no = g.func_no
;
--------------------------------------------------------
--  DDL for View SUBSTITUICAO
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "SUBSTITUICAO" ("FUNC_NO", "INICIO", "FIM", "UNORG", "NO_CHEFE", "ID", "CARGO", "COD_CAT", "NO_CHEFE_SUBST") AS
  select func_no,
    inicio,
    fim,
    unorg,
    no_chefe,
    id,
    cargo,
    cod_cat,
    no_chefe_subst from gps.SUBSTITUICAO @ IDOINT10G
;
--------------------------------------------------------
--  DDL for View SUBSTITUICAO_ROLE
--------------------------------------------------------

  CREATE OR REPLACE FORCE VIEW "SUBSTITUICAO_ROLE" ("FUNC_NO", "INICIO", "FIM", "NO_CHEFE", "APP", "ROLE") AS
  select s.func_no, s.INICIO, s.fim, s.NO_CHEFE, extendrole.app, extendrole.role from
(select r.* from role r
where func_no in (
select no_chefe from substituicao s where trunc(sysdate,'DDD') between s.INICIO and s.FIM
)) extendrole join substituicao s on extendrole.func_no = s.no_chefe
where trunc(sysdate,'DDD') between s.INICIO and s.FIM
;
--------------------------------------------------------
--  DDL for Index ROLE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ROLE_PK" ON "ROLE" ("ID")
  ;
--------------------------------------------------------
--  DDL for Index SYS_C00103956
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C00103956" ON "OAUTH_CLIENT_DETAILS" ("CLIENT_ID")
  ;
--------------------------------------------------------
--  DDL for Index SYS_C00103957
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C00103957" ON "OAUTH_CLIENT_TOKEN" ("AUTHENTICATION_ID")
  ;
--------------------------------------------------------
--  DDL for Index SYS_C00103958
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C00103958" ON "OAUTH_ACCESS_TOKEN" ("AUTHENTICATION_ID")
  ;
--------------------------------------------------------
--  DDL for Index SYS_C00103959
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C00103959" ON "CLIENTDETAILS" ("APPID")
  ;
--------------------------------------------------------
--  DDL for Trigger ROLE_INSERT_T
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "ROLE_INSERT_T"
   before insert on "ROLE"
   for each row
begin
   if inserting then
      if :NEW."ID" is null then
         select ROLE_SEQ.nextval into :NEW."ID" from dual;
      end if;
   end if;
end;


/
ALTER TRIGGER "ROLE_INSERT_T" ENABLE;
--------------------------------------------------------
--  Constraints for Table CLIENTDETAILS
--------------------------------------------------------

  ALTER TABLE "CLIENTDETAILS" ADD PRIMARY KEY ("APPID") ENABLE;
--------------------------------------------------------
--  Constraints for Table OAUTH_ACCESS_TOKEN
--------------------------------------------------------

  ALTER TABLE "OAUTH_ACCESS_TOKEN" ADD PRIMARY KEY ("AUTHENTICATION_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table OAUTH_CLIENT_DETAILS
--------------------------------------------------------

  ALTER TABLE "OAUTH_CLIENT_DETAILS" ADD PRIMARY KEY ("CLIENT_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table OAUTH_CLIENT_TOKEN
--------------------------------------------------------

  ALTER TABLE "OAUTH_CLIENT_TOKEN" ADD PRIMARY KEY ("AUTHENTICATION_ID") ENABLE;
--------------------------------------------------------
--  Constraints for Table ROLE
--------------------------------------------------------

  ALTER TABLE "ROLE" ADD CONSTRAINT "ROLE_PK" PRIMARY KEY ("ID") ENABLE;

  ALTER TABLE "ROLE" MODIFY ("FUNC_NO" NOT NULL ENABLE);

  ALTER TABLE "ROLE" MODIFY ("APP" NOT NULL ENABLE);

  ALTER TABLE "ROLE" MODIFY ("ROLE" NOT NULL ENABLE);

  ALTER TABLE "ROLE" MODIFY ("ID" NOT NULL ENABLE);
