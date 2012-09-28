@echo off
set JAVA_HOME=%cd%/jre

set PATH=%JAVA_HOME%/bin;%PATH% 
set CLASSPATH=.
start javaw -Xms512m -Xmx512m -client -jar lvmc4.jar