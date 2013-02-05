@echo off
setlocal EnableDelayedExpansion
set rpcontent1=STB
set rpcontent2=PHONE
set rpcontent3=CLOUDTV
set file=.\src\com\lorent\video\config\lorent_video.properties
set tmpfile=.\src\com\lorent\video\config\lorent_video_tmp.properties
set devicetypes=PHONE STB CLOUDTV
for %%a in (%devicetypes%) do (
del %file%
echo DeviceType=%%a>>%file%
mvn install --define typename=%%a
)