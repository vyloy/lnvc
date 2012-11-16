LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := tvcore

LOCAL_CFLAGS := -DANDROID_NDK \
                -DDISABLE_IMPORTGL

#LOCAL_C_INCLUDES += /home/lijiawen/android-ndk/sources/cxx-stl/stlport/stlport
LOCAL_C_INCLUDES += /home/lnt/android-ndk/sources/cxx-stl/stlport/stlport
APP_STL := gnustl_static

LOCAL_SRC_FILES := com_qd_jni_JniPlayer.cpp	\
	../../tvdaemon/ipc/ipc_ctl.cpp	\
	../../tvdaemon/ipc/manage_pid.c


LOCAL_LDLIBS := -lGLESv1_CM -ldl -llog


LOCAL_LDLIBS +=  ../libstlport_static.a

include $(BUILD_SHARED_LIBRARY)

###############################
#LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

#LOCAL_C_INCLUDES:= \
#	$(call include-path-for,bluez)/lib/ \

#LOCAL_CFLAGS:= \
#	-DVERSION=\"1.42\"

LOCAL_SRC_FILES:= \
	./tvrecv/debug.c \
	./tvrecv/HostPlatForm.c	\
	./tvrecv/HostDeviceAPI.c \
	./tvrecv/HostTunerClientAPI.c	\
	./tvrecv/HostXDCServerAPI.c \
	./tvrecv/HostCAClientAPI.c \
	./tvrecv/HostRgnClientAPI.c \
	./tvrecv/HostSCClientAPI.c \
	./tvrecv/HostDateTimeServerAPI.c	\
	./tvrecv/HostAppInfoClientAPI.c	\
	./tvrecv/HostEPGDBClientAPI.c	\
	./tvrecv/main.c \
	./tvrecv/SocketRecv.c \
	./tvrecv/SocketSend.c	\
	../../tvdaemon/ipc/child_body.c	\
	../../tvdaemon/ipc/manage_pid.c

LOCAL_LDLIBS = \
	./tvrecv/libutihost.android.a 

LOCAL_LDLIBS += -lGLESv1_CM -ldl -llog

LOCAL_LDFLAGS += \
	$(LOCAL_LDLIBS) 

#LOCAL_MODULE_PATH := $(TARGET_OUT_OPTIONAL_EXECUTABLES)
LOCAL_MODULE_TAGS := debug
LOCAL_MODULE:=tvrecv

include $(BUILD_EXECUTABLE)


###############################
#LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)
LOCAL_SRC_FILES:= \
	./tvplay.c	\
	../../tvdaemon/ipc/child_body.c	\
	../../tvdaemon/ipc/manage_pid.c

LOCAL_LDLIBS += -lGLESv1_CM -ldl -llog

LOCAL_LDFLAGS += \
	$(LOCAL_LDLIBS) 

#LOCAL_MODULE_PATH := $(TARGET_OUT_OPTIONAL_EXECUTABLES)
LOCAL_MODULE_TAGS := debug
LOCAL_MODULE:=tvplay

include $(BUILD_EXECUTABLE)


################################


#LOCAL_PATH := $(call my-dir)

#include $(CLEAR_VARS)

#LOCAL_MODULE := tvplay
	
#LOCAL_SRC_FILES := ./tvplay.c ./libdaemon/child_main.c ./libdaemon/child_body.c

#LOCAL_LDLIBS := -lGLESv1_CM -ldl -llog

#include $(BUILD_EXECUTABLE)

#############################

