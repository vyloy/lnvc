#!/bin/bash
curdir=$PWD
echo 'install postgres'
dpkg -i db/postgres-8.4.8-1.i386.openscg.deb
echo 'add user'
useradd postgres
cd /opt/postgres/8.4
mkdir -p data
chown postgres:postgres data

cd bin
echo 'init database'
su postgres -c './initdb -E UTF8 ../data'

echo 'configure database'
sed -i -e 's:127.0.0.1/32:0.0.0.0/0:' ../data/pg_hba.conf
cp -f $curdir/db/postgresql.conf ../data/postgresql.conf
chmod +x $curdir/db/postgres-8.4-openscg
cp -f $curdir/db/postgres-8.4-openscg /etc/init.d/postgres-8.4-openscg

echo 'start database'
su postgres -c './pg_ctl -D ../data -m f stop'
sleep 2
su postgres -c './pg_ctl -D ../data -w start'
sleep 5
echo 'create database and user'
su postgres -c "./psql < $curdir/db/createdb.sql"
echo 'create table'
su postgres -c "./psql -d lcmDB_Dev < $curdir/db/lcmDB_Demo.sql"
echo 'init data'
su postgres -c "./psql -d lcmDB_Dev < $curdir/db/initdata.sql"
 
/etc/init.d/postgres-8.4-openscg restart