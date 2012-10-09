#!/bin/bash 
source environment
echo "stop apache-tomcat-6.0.29"
cd apache-tomcat-6.0.29/bin 
./shutdown.sh
