
-- failing to load this data. I havenpt solve that for now just inserted these records in the h2DB
-- http://localhost:8080/h2-console [db details are present in the application.properties file]

INSERT INTO USER1(id,active,password,roles,username, secret)
VALUES(1,true,'user','ROLE_USER','user', 'JBSWY3DPEHPK3PXP')

INSERT INTO USER1(id,active,password,roles,username, secret)
VALUES(2,true,'admin','ROLE_ADMIN','admin', 'JBSWY3DPEHPK3PXP')
