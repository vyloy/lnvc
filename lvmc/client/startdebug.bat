@echo off
set JAVA_HOME=%cd%/jre

set PATH=%JAVA_HOME%/bin;%PATH% 
set CLASSPATH=.
java -client -jar lvmc4.jar