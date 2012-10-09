#!/bin/bash
if [ ! $(whoami) = 'root' ]
then 
	echo "you are not root"
	exit 1
fi

echo "Stop tomcat................"
./tomcat_stop.sh >/dev/null

echo "Stop database.............."
/etc/init.d/postgres-8.4-openscg stop

echo "Remove database............."
dpkg -r postgres-8.4 >/dev/null

echo "Delete user postgres................"
userdel -r postgres >/dev/null

echo "Remove start on boot................"
update-rc.d -f lcm remove
update-rc.d -f postgres-8.4-openscg remove
rm -f /etc/init.d/lcm
rm -f /etc/init.d/postgres-8.4-openscg

echo "Remove data.............."
rm -r /opt/postgres >/dev/null

if [ -e /opt/lcp/lcm ]
then rm -r /opt/lcp/lcm
fi

echo "uninstall lcm and postgres success"

