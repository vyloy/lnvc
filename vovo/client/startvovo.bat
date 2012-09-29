@echo off
set JAVA_HOME=%cd%/jre

set PATH=%JAVA_HOME%/bin;%PATH% 
set CLASSPATH=.
start javaw -jar vovo.jar