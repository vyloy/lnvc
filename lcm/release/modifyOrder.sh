#!/bin/sh
update-rc.d -f lcm remove
update-rc.d -f postgresql remove
update-rc.d -f mcu4.1 remove
update-rc.d -f lntcsd remove

update-rc.d lcm defaults 50 60
update-rc.d postgresql defaults 20 90
update-rc.d mcu4.1 defaults 40 70
update-rc.d lntcsd defaults 30 80 
