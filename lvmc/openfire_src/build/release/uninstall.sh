#!/bin/sh
defaultDir=/opt/lcp/imserver
curDir=$(pwd)
waitStr='..........'

if [ ! $(whoami) = 'root' ]
then 
	echo "you are not root"
	exit 1
fi

echo "stop IMServer $waitStr"
/etc/init.d/imserverd stop

echo "remove auto start $waitStr"
update-rc.d -f imserverd remove

rm /etc/init.d/imserverd

echo "remove data $waitStr"
rm -r $defaultDir

echo "Uninstall IMServer success"



