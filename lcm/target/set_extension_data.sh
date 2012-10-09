#!/bin/bash
DATABASE_PATH=/opt/postgres/8.4/bin
CUR_DIR=$PWD

if [ ! $(whoami) = 'root' ]
then 
	echo "you are not root"
	exit 1
fi

cd $DATABASE_PATH
su postgres -c "./psql -d lcmDB_Dev < $CUR_DIR/db/extensions_conf.sql"
