#!/bin/bash 
source environment
echo "start apache-tomcat-6.0.29"
cd apache-tomcat-6.0.29/bin 
export JAVA_OPTS=-Djava.awt.headless=true
./startup.sh &