# tinder

sudo systemctl start tomcat
sudo systemctl status tomcat


tail -f /opt/tomcat/logs/catalina.out


mvn cargo:deploy

sudo systemctl daemon-reload
sudo systemctl start tomcat
sudo systemctl status tomcat



psql -h database-1.cl2n834aix67.us-east-1.rds.amazonaws.com -U postgres
LhlyOD1JvL2XnLHO2xoE

psql -h aa12ctchocsiwsm.cl2n834aix67.us-east-1.rds.amazonaws.com -U postgres
LhlyOD1JvL2XnLHO2xoE

PostgreSQL show tables using psql
\dt

List databases
\l

Connect to database
\c tinder

Expanded view:
\x
select * from users;

pg_dump -h database-1.cl2n834aix67.us-east-1.rds.amazonaws.com -U postgres tinder > dump.sql

psql aa12ctchocsiwsm.cl2n834aix67.us-east-1.rds.amazonaws.com -U postgres tinder < dump.sql

