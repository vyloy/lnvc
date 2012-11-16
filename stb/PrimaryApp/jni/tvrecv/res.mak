# GNU Make 3.81
# Copyright (C) 2006  Free Software Foundation, Inc.
# This is free software; see the source for copying conditions.
# There is NO warranty; not even for MERCHANTABILITY or FITNESS FOR A
# PARTICULAR PURPOSE.

# This program built for i686-pc-linux-gnu
============================================
PLATFORM_VERSION_CODENAME=REL
PLATFORM_VERSION=2.3.1
TARGET_PRODUCT=full_smdkv210
TARGET_BUILD_VARIANT=eng
TARGET_SIMULATOR=false
TARGET_BUILD_TYPE=release
TARGET_BUILD_APPS=
TARGET_ARCH=arm
HOST_ARCH=x86
HOST_OS=linux
HOST_BUILD_TYPE=release
BUILD_ID=GINGERBREAD
============================================
# make: Entering directory `/home/lnt/stb'
target thumb C: tvrecv <= external/tvplay/tvrecv/main.c
target Executable: tvrecv (out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/LINKED/tvrecv)
target Non-prelinked: tvrecv (out/target/product/smdkv210/symbols/system/bin/tvrecv)
target Strip: tvrecv (out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/tvrecv)
Install: out/target/product/smdkv210/system/bin/tvrecv

# Make data base, printed on Thu Mar  1 19:20:58 2012

# Variables

# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.seen := true
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 57)
BOARD_USES_DUAL_CAMERA := false
# makefile (from `build/core/product_config.mk', line 194)
_node_import_context := 
# automatic
?F = $(notdir $?)
# makefile (from `build/core/product_config.mk', line 194)
PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic_x86.mk.PRODUCT_SDK_ADDON_NAME := 
# environment
DESKTOP_SESSION = gnome
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 73)
BOARD_KERNEL_BASE := 0x30000000
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/core.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/Makefile', line 266)
all_event_log_tags_file := out/target/common/obj/all-event-log-tags.txt
# makefile (from `frameworks/base/CleanSpec.mk', line 70)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@@@@@@@@@@@@@@@@@ := rm -rf out/target/common/obj/JAVA_LIBRARIES/framework_intermediates/src/core/java/android/os/storage/*
# makefile (from `build/core/base_rules.mk', line 126)
use_data := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.seen := true
# makefile (from `build/target/product/generic_x86.mk', line 27)
PRODUCT_POLICY := android.policy_phone
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.PRODUCT_DEVICE := generic
# makefile (from `build/core/binary.mk', line 89)
LOCAL_CPP_EXTENSION := .cpp
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic_x86.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/binary.mk', line 132)
ALL_GENERATED_SOURCES := $(subst ,, )
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/binary.mk', line 347)
LOCAL_C_INCLUDES :=  external/tvplay/tvrecv out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates  dalvik/libnativehelper/include/nativehelper
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_TAGS := 
# makefile (from `build/core/combo/select.mk', line 54)
HOST_SHLIB_SUFFIX := .so
# makefile (from `build/core/product.mk', line 91)
dump-products = $(foreach p,$(PRODUCTS),$(call dump-product,$(p)))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/tasks/cts.mk', line 44)
DEFAULT_TEST_PLAN := /resource/plans
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/notice_files.mk', line 5)
notice_file := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 211)
TARGET_CRTBEGIN_DYNAMIC_O := out/target/product/smdkv210/obj/lib/crtbegin_dynamic.o
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 93)
arch_include_dir := system/core/include/arch/linux-arm/
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/config.mk', line 309)
HOST_GLOBAL_CFLAGS := -fno-exceptions -Wno-multichar -m32 -fPIC -include system/core/include/arch/linux-x86/AndroidConfig.h -D_FORTIFY_SOURCE=0 -DANDROID -fmessage-length=0 -W -Wall -Wno-unused -Winit-self -Wpointer-arith -O2 -g -fno-strict-aliasing -DNDEBUG -UDEBUG
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# automatic
@F = $(notdir $@)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# default
RM = rm -f
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_CONTRIBUTORS_FILE := 
# default
CO = co
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/node_fns.mk', line 243)
import-nodes = $(if $(foreach _in,$(2), $(eval _node_import_context := _nic.$(1).[[$(_in)]]) $(if $(_include_stack),$(eval $(error ASSERTION FAILED: _include_stack should be empty here: $(_include_stack))),) $(eval _include_stack := ) $(call _import-nodes-inner,$(_node_import_context),$(_in),$(3)) $(call move-var-list,$(_node_import_context).$(_in),$(1).$(_in),$(3)) $(eval _node_import_context :=) $(eval $(1) := $($(1)) $(_in)) $(if $(_include_stack),$(eval $(error ASSERTION FAILED: _include_stack should be empty here: $(_include_stack))),) ) ,)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `device/samsung/smdkc110/CleanSpec.mk', line 60)
device_samsung_smdkc110_CleanSpec-mk_acs := 6@@@@@@@@@@@
# makefile (from `frameworks/base/CleanSpec.mk', line 71)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@@@@@@@@@@@@@@@@@@ := rm -rf out/target/common/obj/JAVA_LIBRARIES/framework_intermediates/src/core/java/com/trustedlogic
# makefile (from `build/core/definitions.mk', line 1624)
get-instrumentation-proguard-flags = $(if $(PRIVATE_INSTRUMENTATION_FOR),$(if $(ALL_MODULES.$(PRIVATE_INSTRUMENTATION_FOR).PROGUARD_ENABLED),-applymapping $(call intermediates-dir-for,APPS,$(PRIVATE_INSTRUMENTATION_FOR),,COMMON)/proguard_dictionary))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# environment
_ = /usr/bin/make
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
h := ||| @inherit:device/samsung/smdkv210/device.mk |||
# makefile (from `build/core/definitions.mk', line 1574)
define transform-prebuilt-to-target-strip-comments
@echo "$(if $(PRIVATE_IS_HOST_MODULE),host,target) Prebuilt: $(PRIVATE_MODULE) ($@)"
$(copy-file-to-target-strip-comments)
endef
# makefile (from `build/core/node_fns.mk', line 29)
clear-var-list = $(foreach v,$(1),$(eval $(v):=))
# makefile (from `build/core/envsetup.mk', line 231)
TARGET_OUT_EXECUTABLES := out/target/product/smdkv210/system/bin
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.PRODUCT_PACKAGES := 
# environment
XDG_CONFIG_DIRS = /etc/xdg/xdg-gnome:/etc/xdg
# makefile (from `build/core/product_config.mk', line 203)
p := device/samsung/product/full_smdkv210.mk
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/main.mk', line 547)
r := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.inherited := 
# makefile (from `build/core/config.mk', line 212)
MKTARBALL := build/tools/mktarball.sh
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/Makefile', line 208)
all_built_packages := 
# makefile (from `build/core/product_config.mk', line 259)
TARGET_AAPT_CHARACTERISTICS := nosdcard
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/binary.mk', line 276)
gen_c_sources := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/base_rules.mk', line 536)
ALL_MODULES.tvrecv.REQUIRED := $(subst ,, )
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/combo/arch/arm/armv7-a-neon.mk', line 6)
ARCH_ARM_HAVE_64BIT_DATA := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/envsetup.mk', line 205)
HOST_COMMON_OUT_ROOT := out/host/common
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.inherited := 
# makefile (from `build/core/base_rules.mk', line 550)
ALL_MODULE_TAGS := debug
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/config.mk', line 239)
YACC_HEADER_SUFFIX := .hpp
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/dex_preopt.mk', line 66)
_dbj_jar := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `vendor/samsung/smdkv210/BoardConfigVendor.mk', line 17)
BOARD_GPS_LIBRARIES := libgps
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/main.mk', line 43)
TOP := .
# makefile (from `build/core/definitions.mk', line 1767)
keep-or-override = $(eval $(1) := $(if $(2),$(2),$($(1))))
# makefile (from `build/core/Makefile', line 486)
tools_notice_file_txt := out/host/linux-x86/obj/NOTICE.txt
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/htc/passion/full_passion.mk.PRODUCT_DEVICE := passion
# environment
TARGET_SIMULATOR = false
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 54)
BOARD_CAMERA_LIBRARIES := libcamera
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.PRODUCT_NAME := core
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_TAGS := 
# environment
DBUS_SESSION_BUS_ADDRESS = unix:abstract=/tmp/dbus-7ztaXorxT0,guid=19b7b33a07602548d60f982d00000044
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/base_rules.mk', line 309)
LOCAL_JAVA_RESOURCE_FILES := 
# makefile (from `build/core/definitions.mk', line 741)
define transform-logtags-to-java
@mkdir -p $(dir $@)
@echo "logtags: $@ <= $<"
$(hide) $(JAVATAGS) -o $@ $^
endef
# makefile (from `build/core/combo/arch/arm/armv7-a-neon.mk', line 11)
ARCH_ARM_HAVE_VFP := true
# makefile (from `build/core/product_config.mk', line 194)
PRODUCT_MAKEFILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/clear_vars.mk', line 24)
LOCAL_ACP_UNAVAILABLE := 
# makefile (from `build/core/envsetup.mk', line 204)
TARGET_COMMON_OUT_ROOT := out/target/common
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/config.mk', line 61)
BUILD_RAW_EXECUTABLE := build/core/raw_executable.mk
# makefile (from `build/core/binary.mk', line 460)
built_static_libraries := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/combo/select.mk', line 41)
HOST_HAVE_PTHREAD_RWLOCK := 1
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/combo/arch/arm/armv7-a-neon.mk', line 9)
ARCH_ARM_HAVE_FFS := true
# makefile (from `frameworks/base/CleanSpec.mk', line 74)
frameworks_base_CleanSpec-mk_acs := 6@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
# makefile (from `frameworks/base/CleanSpec.mk', line 73)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@@@@@@@@@@@@@@@@@@@@ := rm -rf out/target/common/obj/JAVA_LIBRARIES/framework_intermediates/src/core/java/com/trustedlogic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `device/htc/passion/passion_us.mk', line 26)
PRODUCTS.device/htc/passion/passion_us.mk.INHERITS_FROM := device/common/gps/gps_us_supl.mk device/htc/passion/passion.mk
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.PRODUCT_LOCALES := 
# default
CPP = $(CC) -E
# makefile (from `build/core/config.mk', line 143)
board_config_mk := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/Makefile', line 1108)
BUILT_TESTS_ZIP_PACKAGE := out/target/product/smdkv210/obj/PACKAGING/tests_zip_intermediates/full_smdkv210-tests-eng.lnt.zip
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/binary.mk', line 449)
LOCAL_SHARED_LIBRARIES :=  libc libstdc++ libm
# makefile (from `build/core/product_config.mk', line 196)
w := 
# makefile (from `build/core/definitions.mk', line 911)
define transform-host-c-to-o
$(transform-host-c-to-o-no-deps)
$(transform-d-to-p)
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_MODEL := 
# makefile (from `build/core/base_rules.mk', line 41)
my_host := 
# makefile (from `build/core/base_rules.mk', line 181)
OVERRIDE_BUILT_MODULE_PATH := 
# makefile (from `build/core/config.mk', line 58)
BUILD_RAW_STATIC_LIBRARY := build/core/raw_static_library.mk
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/htc/passion/full_passion.mk.PRODUCT_TAGS := dalvik.gc.type-precise
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.PRODUCT_NAME := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/dynamic_binary.mk', line 131)
strip_input := out/target/product/smdkv210/symbols/system/bin/tvrecv
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/base_rules.mk', line 528)
ALL_MODULES.tvrecv.TAGS :=  debug
# makefile (from `build/CleanSpec.mk', line 47)
INTERNAL_CLEAN_STEP.build_CleanSpec-mk_acs6@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libmediaplayerservice_intermediates
# makefile (from `build/core/combo/select.mk', line 41)
TARGET_HAVE_PTHREAD_RWLOCK := 1
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/Makefile', line 170)
INSTALLED_SDK_BUILD_PROP_TARGET := out/target/product/smdkv210/sdk/sdk-build.prop
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/node_fns.mk', line 47)
copy-var-list = $(foreach v,$(2),$(eval $(strip $(1)).$(v):=$($(v))))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_MANUFACTURER := 
# default
TEXI2DVI = texi2dvi
# makefile (from `build/core/config.mk', line 39)
SRC_TARGET_DIR := build/target
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/full.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.inherited := 
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 69)
TARGET_thumb_CFLAGS := -mthumb -Os -fomit-frame-pointer -fno-strict-aliasing -finline-limit=64
# makefile (from `frameworks/base/CleanSpec.mk', line 74)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ := rm -rf out/target/common/obj/JAVA_LIBRARIES/android_stubs_current_intermediates/src/com/trustedlogic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.seen := true
# environment
SESSION_MANAGER = local/lnt-HP-Pavilion-g4-Notebook-PC:@/tmp/.ICE-unix/1370,unix/lnt-HP-Pavilion-g4-Notebook-PC:/tmp/.ICE-unix/1370
# makefile (from `build/core/definitions.mk', line 1722)
add-radio-file =   $(eval $(call add-radio-file-internal,$(1),$(notdir $(1))))
# makefile (from `build/core/envsetup.mk', line 247)
TARGET_OUT_DATA_KEYLAYOUT := out/target/product/smdkv210/system/usr/keylayout
# makefile (from `build/core/definitions.mk', line 517)
space := $(subst ,, )
# default
COMPILE.mod = $(M2C) $(M2FLAGS) $(MODFLAGS) $(TARGET_ARCH)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.PRODUCT_BRAND := generic
# makefile (from `build/core/base_rules.mk', line 32)
LOCAL_IS_HOST_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/samsung/product/full_smdkv210.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/definitions.mk', line 813)
define transform-s-to-o-no-deps
@echo "target asm: $(PRIVATE_MODULE) <= $<"
$(call transform-c-or-s-to-o-no-deps, $(PRIVATE_ASFLAGS))
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/base_rules.mk', line 279)
java_resource_file_groups := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/sample/products/sample_addon.mk.PRODUCT_BRAND := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/copy_headers.mk', line 23)
_chTo := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_BRAND := 
# makefile (from `build/core/binary.mk', line 372)
LOCAL_SYSTEM_SHARED_LIBRARIES := libc libstdc++ libm
# makefile (from `build/core/envsetup.mk', line 218)
HOST_OUT_INTERMEDIATES := out/host/linux-x86/obj
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/CleanSpec.mk', line 48)
INTERNAL_CLEAN_STEP.build_CleanSpec-mk_acs6@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libmedia_jni_intermediates
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sdk.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 239)
comma := ,
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/sample/products/sample_addon.mk.PRODUCT_DEVICE := generic
# makefile (from `build/core/product_config.mk', line 194)
PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/target/product/generic_x86.mk', line 21)
PRODUCTS.build/target/product/generic_x86.mk.INHERITS_FROM := build/target/product/core.mk
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.PRODUCT_BRAND := 
# makefile (from `build/core/main.mk', line 331)
should-install-to-system = $(if $(filter tests,$(1)),,true)
# makefile (from `build/core/binary.mk', line 62)
my_target_global_cflags := -fno-exceptions -Wno-multichar -msoft-float -fpic -ffunction-sections -funwind-tables -fstack-protector -Wa,--noexecstack -Werror=format-security -fno-short-enums -march=armv7-a -mfloat-abi=softfp -mfpu=neon -include system/core/include/arch/linux-arm/AndroidConfig.h -I system/core/include/arch/linux-arm/ -Wno-psabi -mthumb-interwork -DANDROID -fmessage-length=0 -W -Wall -Wno-unused -Winit-self -Wpointer-arith -Werror=return-type -Werror=non-virtual-dtor -Werror=address -Werror=sequence-point -DNDEBUG -g -Wstrict-aliasing=2 -finline-functions -fno-inline-functions-called-once -fgcse-after-reload -frerun-cse-after-loop -frename-registers -DNDEBUG -UDEBUG
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_MANUFACTURER := 
# default
YACC.y = $(YACC) $(YFLAGS)
# makefile (from `build/core/binary.mk', line 82)
LOCAL_CXX := prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/arm-eabi-g++
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/definitions.mk', line 375)
intermediates-dir-for = $(strip $(eval _idfClass := $(strip $(1))) $(if $(_idfClass),, $(error $(LOCAL_PATH): Class not defined in call to intermediates-dir-for)) $(eval _idfName := $(strip $(2))) $(if $(_idfName),, $(error $(LOCAL_PATH): Name not defined in call to intermediates-dir-for)) $(eval _idfPrefix := $(if $(strip $(3)),HOST,TARGET)) $(if $(filter $(_idfPrefix)-$(_idfClass),$(COMMON_MODULE_CLASSES))$(4), $(eval _idfIntBase := $($(_idfPrefix)_OUT_COMMON_INTERMEDIATES)) , $(eval _idfIntBase := $($(_idfPrefix)_OUT_INTERMEDIATES)) ) $(_idfIntBase)/$(_idfClass)/$(_idfName)_intermediates )
# default
AR = ar
# makefile (from `device/htc/passion/passion.mk', line 53)
PRODUCTS.device/htc/passion/passion.mk.INHERITS_FROM := device/htc/passion-common/passion.mk
# makefile (from `build/core/Makefile', line 1350)
INTERNAL_FINDBUGS_HTML_TARGET := out/target/product/smdkv210/findbugs.html
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 262)
PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/htc/passion/full_passion.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/base_rules.mk', line 262)
event_log_tags := 
# makefile (from `build/core/combo/select.mk', line 56)
HOST_STATIC_LIB_SUFFIX := .a
# makefile (from `build/core/config.mk', line 288)
TARGET_RELEASE_CFLAGS := -DNDEBUG -g -Wstrict-aliasing=2 -finline-functions -fno-inline-functions-called-once -fgcse-after-reload -frerun-cse-after-loop -frename-registers -DNDEBUG -UDEBUG
# makefile (from `build/core/combo/HOST_linux-x86.mk', line 33)
HOST_GLOBAL_LDFLAGS :=  -m32
# makefile (from `external/clearsilver/CleanSpec.mk', line 52)
INTERNAL_CLEAN_STEP.external_clearsilver_CleanSpec-mk_acs6@@@ := rm -rf out/host/linux-x86/obj/SHARED_LIBRARIES/libneo_util_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# default
.FEATURES := target-specific order-only second-expansion else-if archives jobserver check-symlink
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.PRODUCT_PACKAGES := 
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 90)
BOARD_NONBLOCK_MODE_PROCESS := true
# default
TANGLE = tangle
# makefile (from `build/core/config.mk', line 285)
HOST_RELEASE_CPPFLAGS = $(COMMON_RELEASE_CPPFLAGS)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/combo/select.mk', line 36)
TARGET_HAVE_WINDOWS_FILE_PATH := 0
# makefile (from `build/core/Makefile', line 731)
INSTALLED_SYSTEMIMAGE := out/target/product/smdkv210/system.img
# makefile (from `build/core/definitions.mk', line 1507)
define copy-one-file
$(2): $(1) | $(ACP)
	@echo "Copy: $$@"
	$$(copy-file-to-target)
endef
# makefile (from `build/core/config.mk', line 315)
PREBUILT_IS_PRESENT := true
# makefile (from `build/CleanSpec.mk', line 49)
INTERNAL_CLEAN_STEP.build_CleanSpec-mk_acs6@@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libstagefright_omx_intermediates
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 58)
TARGET_arm_CFLAGS := -O2 -fomit-frame-pointer -fstrict-aliasing -funswitch-loops -finline-limit=300
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/main.mk', line 482)
FULL_BUILD := 
# makefile (from `build/core/clear_vars.mk', line 19)
LOCAL_PACKAGE_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic_x86.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/envsetup.mk', line 215)
HOST_OUT_JAVA_LIBRARIES := out/host/linux-x86/framework
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 56)
SED_EXTENDED := sed -E
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_TAGS := 
# makefile (from `build/core/Makefile', line 699)
systemimage_intermediates := out/target/product/smdkv210/obj/PACKAGING/systemimage_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.seen := true
# makefile (from `cts/CtsHostLibraryList.mk', line 15)
CTS_HOST_LIBRARY_JARS := out/host/linux-x86/framework/CtsTestAnnotationsHostLib.jar
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/htc/passion/full_passion.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/core.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/definitions.mk', line 265)
find-subdir-files = $(patsubst ./%,%,$(shell cd $(LOCAL_PATH) ; find $(1)))
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/samsung/product/full_smdkv210.mk.PRODUCT_MODEL := Full Android on SMDKV210
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `frameworks/base/CleanSpec.mk', line 67)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@@@@@@@@@@@@@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libreverb_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/definitions.mk', line 1470)
define obfuscate-jar
@echo "Obfuscate jar: $(notdir $@) ($@)"
@mkdir -p $(dir $@)
@rm -f $@
@mkdir -p $(PRIVATE_INTERMEDIATES_DIR)
$(hide) sed -e 's/^/-keep class /' < $(PRIVATE_KEEP_FILE) > $(PRIVATE_INTERMEDIATES_DIR)/keep.pro
$(hide) java -Xmx512M -jar $(HOST_OUT_JAVA_LIBRARIES)/proguard-4.0.1.jar -injars $< -outjars $@ -target 1.5 -dontnote -dontwarn -printmapping $(PRIVATE_INTERMEDIATES_DIR)/out.map -forceprocessing -renamesourcefileattribute SourceFile -keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod -repackageclasses -keepclassmembers "class * { public protected *; }" @$(PRIVATE_INTERMEDIATES_DIR)/keep.pro
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/definitions.mk', line 848)
define transform-host-cpp-to-o
@mkdir -p $(dir $@)
@echo "host C++: $(PRIVATE_MODULE) <= $<"
$(hide) $(PRIVATE_CXX) $(foreach incdir, $(PRIVATE_C_INCLUDES) $(if $(PRIVATE_NO_DEFAULT_COMPILER_FLAGS),, $(HOST_PROJECT_INCLUDES) $(HOST_C_INCLUDES) ) , -I $(incdir) ) -c $(if $(PRIVATE_NO_DEFAULT_COMPILER_FLAGS),, $(HOST_GLOBAL_CFLAGS) $(HOST_GLOBAL_CPPFLAGS) ) $(PRIVATE_CFLAGS) $(PRIVATE_CPPFLAGS) $(PRIVATE_DEBUG_CFLAGS) -MD -o $@ $<
$(transform-d-to-p)
endef
# makefile (from `vendor/samsung/smdkv210/device-vendor.mk', line 32)
inherit_var := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/definitions.mk', line 534)
word-colon = $(word $(1),$(subst :,$(space),$(2)))
# makefile (from `vendor/samsung/smdkv210/BoardConfigVendor.mk', line 21)
BOARD_USES_GENERIC_AUDIO := false
# makefile (from `device/samsung/smdkv210/device.mk', line 247)
PRODUCTS.device/samsung/smdkv210/device.mk.INHERITS_FROM := vendor/samsung/smdkv210/device-vendor.mk
# makefile (from `build/core/definitions.mk', line 1273)
unzip-jar-files =   $(hide) for f in $(1); do if [ ! -f $$f ]; then echo Missing file $$f; exit 1; fi; unzip -qo $$f -d $(2); (cd $(2) && rm -rf META-INF); done
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/samsung/product/full_smdkv210.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/clear_vars.mk', line 63)
LOCAL_DROIDDOC_ASSET_DIR := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.PRODUCT_BRAND := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.PRODUCT_MANUFACTURER := 
# makefile (from `frameworks/base/CleanSpec.mk', line 54)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@ := rm -rf out/target/common/obj/JAVA_LIBRARIES/android.policy*
# makefile (from `external/webkit/CleanSpec.mk', line 48)
INTERNAL_CLEAN_STEP.external_webkit_CleanSpec-mk_acs6@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libwebcore_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 33)
_ici_alphaunderscore := a b c d e f g h i j k l m n o p q r s t u v w x y z A B C D E F G H I J K L M N O P Q R S T U V W X Y Z _
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/combo/select.mk', line 32)
HOST_BINDER_MINI := 0
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/cleanbuild.mk', line 155)
current_build_config := 
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 63)
BOARD_HDMI_STD := STD_720P             
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/version_defaults.mk', line 62)
PLATFORM_VERSION_CODENAME := REL
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 219)
TARGET_DEFAULT_SYSTEM_SHARED_LIBRARIES := libc libstdc++ libm
# makefile (from `build/core/product.mk', line 36)
get-product-makefiles = $(sort $(foreach f,$(1), $(eval PRODUCT_MAKEFILES :=) $(eval LOCAL_DIR := $(patsubst %/,%,$(dir $(f)))) $(eval include $(f)) $(PRODUCT_MAKEFILES) ) $(eval PRODUCT_MAKEFILES :=) $(eval LOCAL_DIR :=) )
# makefile (from `build/CleanSpec.mk', line 51)
INTERNAL_CLEAN_STEP.build_CleanSpec-mk_acs6@@@@@ := rm -rf out/target/product/smdkv210/system/vendor
# makefile (from `frameworks/base/CleanSpec.mk', line 60)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@@@@@@@ := rm -f out/target/product/smdkv210/obj/lib/libreverbtest.so
# makefile (from `build/core/Makefile', line 1349)
INTERNAL_FINDBUGS_XML_TARGET := out/target/product/smdkv210/findbugs.xml
# makefile (from `build/core/binary.mk', line 238)
gen_s_sources := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.PRODUCT_NAME := 
# makefile (from `build/core/base_rules.mk', line 27)
LOCAL_MODULE := tvrecv
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# environment
LOGNAME = lnt
# environment
TERM = xterm
# makefile (from `build/core/envsetup.mk', line 189)
HOST_OUT_debug := out/debug/host/linux-x86
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.inherited := 
# makefile (from `build/target/product/generic.mk', line 46)
PRODUCTS.build/target/product/generic.mk.INHERITS_FROM := build/target/product/core.mk
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/cleanbuild.mk', line 176)
installclean_files := ./out/host/linux-x86/obj/NOTICE_FILES ./out/host/linux-x86/sdk ./out/target/product/smdkv210/*.img ./out/target/product/smdkv210/*.txt ./out/target/product/smdkv210/*.xlb ./out/target/product/smdkv210/*.zip ./out/target/product/smdkv210/data ./out/target/product/smdkv210/obj/APPS ./out/target/product/smdkv210/obj/NOTICE_FILES ./out/target/product/smdkv210/obj/PACKAGING ./out/target/product/smdkv210/recovery ./out/target/product/smdkv210/root ./out/target/product/smdkv210/system ./out/target/product/smdkv210/dex_bootjars ./out/target/product/smdkv210/obj/JAVA_LIBRARIES
# makefile (from `build/core/Makefile', line 721)
define build-systemimage-target
    @echo "Target system fs image: $(1)"
    @mkdir -p $(dir $(1))
    $(hide) $(MKYAFFS2) -f $(mkyaffs2_extra_flags) $(TARGET_OUT) $(1)
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sdk.mk.PRODUCT_PROPERTY_OVERRIDES := ro.config.notification_sound=OnTheHunt.ogg ro.config.alarm_alert=Alarm_Classic.ogg
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/sample/products/sample_addon.mk.PRODUCT_PACKAGES := PlatformLibraryClient com.example.android.platform_library libplatform_library_jni SystemUI AccountAndSyncSettings Camera Calculator DeskClock Development DrmProvider Email Fallback Gallery GPSEnable Launcher2 Protips Music Mms Settings SdkSetup CustomLocale gpstest sqlite3 LatinIME PinyinIME OpenWnn libWnnEngDic libWnnJpnDic libwnndict CertInstaller LiveWallpapersPicker ApiDemos GestureBuilder SoftKeyboard CubeLiveWallpapers QuickSearchBox adb dmtracedump etc1tool hprof-conv mksdcard emulator ddms hierarchyviewer draw9patch layoutopt traceview android dexdump androidprefs sdkstats archquery ddms ddmlib ddmuilib hierarchyviewer draw9patch layoutopt uix traceview anttasks sdklib sdkuilib sdkmanager swing-worker-1.1 groovy-all-1.7.0 commons-compress-1.0 emmalib org-netbeans-api-visual org-openide-util jcommon-1.0.12 jfreechart-1.0.9 jfreechart-1.0.9-swt org.eclipse.core.commands_3.4.0.I20080509-2000 org.eclipse.equinox.common_3.4.0.v20080421-2006 org.eclipse.jface_3.4.2.M20090107-0800 osgi layoutlib bouncycastle com.android.location.provider com.android.location.provider.xml core core-junit create_test_dmtrace dalvikvm dexdeps dexdump dexlist dexopt dmtracedump dvz dx ext framework-res hprof-conv icu.dat jasmin jasmin.jar libcrypto libdex libdvm libexpat libicui18n libicuuc libjavacore libnativehelper libnfc_ndef libsqlite_jni libssl libz sqlite-jdbc Browser Contacts Home HTMLViewer Phone ApplicationsProvider ContactsProvider DownloadProvider DownloadProviderUi MediaProvider PicoTts SettingsProvider TelephonyProvider TtsService VpnServices UserDictionaryProvider PackageInstaller DefaultContainerService Bugreport
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/binary.mk', line 324)
asm_objects := $(subst ,, )
# makefile (from `build/core/definitions.mk', line 1078)
define transform-o-to-package
@mkdir -p $(dir $@)
@echo "target Package: $(PRIVATE_MODULE) ($@)"
$(hide) $(transform-o-to-shared-lib-inner)
endef
# makefile (from `build/core/main.mk', line 546)
m := 
# makefile (from `build/core/product_config.mk', line 196)
pb := generic
# environment
GNOME_DESKTOP_SESSION_ID = this-is-deprecated
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.PRODUCT_MODEL := 
# makefile (from `build/core/definitions.mk', line 546)
collapse-pairs = $(eval _cpSEP := $(strip $(if $(2),$(2),=))) $(subst $(space)$(_cpSEP)$(space),$(_cpSEP),$(strip $(subst $(_cpSEP), $(_cpSEP) ,$(1))))
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic_x86.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/clear_vars.mk', line 98)
LOCAL_EMMA_COVERAGE_FILTER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/definitions.mk', line 1542)
define copy-file-to-target-strip-comments
@mkdir -p $(dir $@)
$(hide) sed -e 's/#.*$$//' -e 's/[ \t]*$$//' -e '/^$$/d' < $< > $@
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.seen := true
# makefile (from `build/core/main.mk', line 627)
modules_to_install := out/target/product/smdkv210/kernel out/target/product/smdkv210/root/default.prop out/target/product/smdkv210/root/init.rc out/target/product/smdkv210/root/init.smdkv210.rc out/target/product/smdkv210/root/ueventd.smdkv210.rc out/target/product/smdkv210/system/app/FileManagers.apk out/target/product/smdkv210/system/app/Game.apk out/target/product/smdkv210/system/app/HomePage.apk out/target/product/smdkv210/system/app/PrimaryApp.apk out/target/product/smdkv210/system/bin/bash out/target/product/smdkv210/system/bin/busybox out/target/product/smdkv210/system/bin/dvb_load out/target/product/smdkv210/system/bin/dvb_server out/target/product/smdkv210/system/bin/playts out/target/product/smdkv210/system/bin/remount_ctl out/target/product/smdkv210/system/bin/tvrecv out/target/product/smdkv210/system/build.prop out/target/product/smdkv210/system/etc/apns-conf.xml out/target/product/smdkv210/system/etc/dhd.ko out/target/product/smdkv210/system/etc/event-log-tags out/target/product/smdkv210/system/etc/fw_bcm4329.bin out/target/product/smdkv210/system/etc/media_profiles.xml out/target/product/smdkv210/system/etc/nvram.txt out/target/product/smdkv210/system/etc/permissions/android.software.live_wallpaper.xml out/target/product/smdkv210/system/etc/permissions/handheld_core_hardware.xml out/target/product/smdkv210/system/etc/secomxregistry out/target/product/smdkv210/system/etc/security/otacerts.zip out/target/product/smdkv210/system/etc/vold.conf out/target/product/smdkv210/system/etc/vold.fstab out/target/product/smdkv210/system/etc/wifi/wpa_supplicant.conf out/target/product/smdkv210/system/lib/egl/egl.cfg out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Encoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M2V.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Encoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.WMV.Decoder.so out/target/product/smdkv210/system/lib/libSEC_OMX_Core.so out/target/product/smdkv210/system/lib/libSEC_Resourcemanager.so out/target/product/smdkv210/system/lib/liba52.so out/target/product/smdkv210/system/lib/libdvbbinderclient.so out/target/product/smdkv210/system/lib/libdvbcomm.so out/target/product/smdkv210/system/lib/libjnidvb.so out/target/product/smdkv210/system/lib/libmoddvb.so out/target/product/smdkv210/system/lib/libstagefright.so out/target/product/smdkv210/system/lib/libstagefright_amrnb_common.so out/target/product/smdkv210/system/lib/libstagefright_avc_common.so out/target/product/smdkv210/system/lib/libstagefright_color_conversion.so out/target/product/smdkv210/system/lib/libstagefright_enc_common.so out/target/product/smdkv210/system/lib/libstagefright_foundation.so out/target/product/smdkv210/system/lib/libstagefright_omx.so out/target/product/smdkv210/system/lib/libtv.so out/target/product/smdkv210/system/lib/modules/utiusb.ko out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_01.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_02.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_03.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Buzzer.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Classic.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Rooster_02.ogg out/target/product/smdkv210/system/media/audio/notifications/Beat_Box_Android.ogg out/target/product/smdkv210/system/media/audio/notifications/CaffeineSnake.ogg out/target/product/smdkv210/system/media/audio/notifications/DearDeer.ogg out/target/product/smdkv210/system/media/audio/notifications/DontPanic.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_MissedCall.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_New_MMS.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_New_SMS.ogg out/target/product/smdkv210/system/media/audio/notifications/Heaven.ogg out/target/product/smdkv210/system/media/audio/notifications/Highwire.ogg out/target/product/smdkv210/system/media/audio/notifications/KzurbSonar.ogg out/target/product/smdkv210/system/media/audio/notifications/OnTheHunt.ogg out/target/product/smdkv210/system/media/audio/notifications/TaDa.ogg out/target/product/smdkv210/system/media/audio/notifications/Tinkerbell.ogg out/target/product/smdkv210/system/media/audio/notifications/Voila.ogg out/target/product/smdkv210/system/media/audio/ringtones/BeatPlucker.ogg out/target/product/smdkv210/system/media/audio/ringtones/BentleyDubs.ogg out/target/product/smdkv210/system/media/audio/ringtones/BirdLoop.ogg out/target/product/smdkv210/system/media/audio/ringtones/CaribbeanIce.ogg out/target/product/smdkv210/system/media/audio/ringtones/CrazyDream.ogg out/target/product/smdkv210/system/media/audio/ringtones/CurveBall.ogg out/target/product/smdkv210/system/media/audio/ringtones/DreamTheme.ogg out/target/product/smdkv210/system/media/audio/ringtones/EtherShake.ogg out/target/product/smdkv210/system/media/audio/ringtones/FriendlyGhost.ogg out/target/product/smdkv210/system/media/audio/ringtones/GameOverGuitar.ogg out/target/product/smdkv210/system/media/audio/ringtones/Growl.ogg out/target/product/smdkv210/system/media/audio/ringtones/InsertCoin.ogg out/target/product/smdkv210/system/media/audio/ringtones/LoopyLounge.ogg out/target/product/smdkv210/system/media/audio/ringtones/LoveFlute.ogg out/target/product/smdkv210/system/media/audio/ringtones/MidEvilJaunt.ogg out/target/product/smdkv210/system/media/audio/ringtones/MildlyAlarming.ogg out/target/product/smdkv210/system/media/audio/ringtones/NewPlayer.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises1.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises2.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises3.ogg out/target/product/smdkv210/system/media/audio/ringtones/OrganDub.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Classic_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Digital_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_04.ogg out/target/product/smdkv210/system/media/audio/ringtones/RomancingTheTone.ogg out/target/product/smdkv210/system/media/audio/ringtones/SitarVsSitar.ogg out/target/product/smdkv210/system/media/audio/ringtones/SpringyJalopy.ogg out/target/product/smdkv210/system/media/audio/ringtones/Terminated.ogg out/target/product/smdkv210/system/media/audio/ringtones/TwirlAway.ogg out/target/product/smdkv210/system/media/audio/ringtones/VeryAlarmed.ogg out/target/product/smdkv210/system/media/audio/ringtones/World.ogg out/target/product/smdkv210/system/media/audio/ui/Effect_Tick.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressDelete.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressReturn.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressSpacebar.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressStandard.ogg out/target/product/smdkv210/system/media/audio/ui/VideoRecord.ogg out/target/product/smdkv210/system/media/audio/ui/camera_click.ogg out/target/product/smdkv210/system/media/bootanimation.zip out/target/product/smdkv210/system/tts/lang_pico/de-DE_gl0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/de-DE_ta.bin out/target/product/smdkv210/system/tts/lang_pico/en-GB_kh0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/en-GB_ta.bin out/target/product/smdkv210/system/tts/lang_pico/en-US_lh0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/en-US_ta.bin out/target/product/smdkv210/system/tts/lang_pico/es-ES_ta.bin out/target/product/smdkv210/system/tts/lang_pico/es-ES_zl0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/fr-FR_nk0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/fr-FR_ta.bin out/target/product/smdkv210/system/tts/lang_pico/it-IT_cm0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/it-IT_ta.bin out/target/product/smdkv210/system/usr/keylayout/s3c-keypad.kl out/target/product/smdkv210/system/vendor/bin/pvrsrvinit out/target/product/smdkv210/system/vendor/lib/egl/libEGL_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/egl/libGLESv1_CM_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/egl/libGLESv2_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/hw/gralloc.s5pc110.so out/target/product/smdkv210/system/vendor/lib/libIMGegl.so out/target/product/smdkv210/system/vendor/lib/libPVRScopeServices.so out/target/product/smdkv210/system/vendor/lib/libglslcompiler.so out/target/product/smdkv210/system/vendor/lib/libpvr2d.so out/target/product/smdkv210/system/vendor/lib/libpvrANDROID_WSEGL.so out/target/product/smdkv210/system/vendor/lib/libsrv_init.so out/target/product/smdkv210/system/vendor/lib/libsrv_um.so out/target/product/smdkv210/system/vendor/lib/libusc.so
# makefile (from `build/core/combo/select.mk', line 55)
HOST_JNILIB_SUFFIX := .so
# environment
COLORTERM = gnome-terminal
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.PRODUCT_BRAND := 
# default
MAKE = $(MAKE_COMMAND)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/version_defaults.mk', line 56)
PLATFORM_SDK_VERSION := 9
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_NAME := 
# makefile (from `build/core/definitions.mk', line 86)
print-vars = $(foreach var,$(1), $(info $(var):) $(foreach word,$($(var)), $(info $(space)$(space)$(word)) ) )
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/definitions.mk', line 1098)
define transform-to-prelinked
@mkdir -p $(dir $@)
@echo "target Prelink: $(PRIVATE_MODULE) ($@)"
$(hide) $(APRIORI) --prelinkmap $(TARGET_PRELINKER_MAP) --locals-only --quiet $< --output $@
endef
# makefile (from `build/core/binary.mk', line 239)
gen_s_objects := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 283)
PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/binary.mk', line 162)
lex_sources := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/base_rules.mk', line 190)
LOCAL_BUILT_MODULE := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/tvrecv
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 32)
_ici_digits := 0 1 2 3 4 5 6 7 8 9
# makefile (from `build/core/Makefile', line 1176)
APPS_ZIP := out/target/product/smdkv210/full_smdkv210-apps-eng.lnt.zip
# makefile (from `build/core/cleanbuild.mk', line 98)
INTERNAL_CLEAN_BUILD_VERSION := 
# makefile (from `build/core/combo/javac.mk', line 33)
HOST_JAVAC = $(COMMON_JAVAC)
# makefile (from `build/core/product_config.mk', line 194)
LOCAL_DIR := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/main.mk', line 310)
apns_to_use := 
# makefile (from `build/core/cleanbuild.mk', line 145)
PREVIOUS_BUILD_CONFIG := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sim.mk.PRODUCT_DEVICE := sim
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_MANUFACTURER := 
# default
FC = f77
# makefile (from `build/core/definitions.mk', line 342)
define include-prebuilt
    include $$(CLEAR_VARS)
    LOCAL_SRC_FILES := $(1)
    LOCAL_BUILT_MODULE_STEM := $(1)
    LOCAL_MODULE_SUFFIX := $$(suffix $(1))
    LOCAL_MODULE := $$(basename $(1))
    LOCAL_MODULE_CLASS := $(2)
    include $$(BUILD_PREBUILT)
endef
# makefile (from `build/core/binary.mk', line 104)
LOCAL_ARM_MODE := 
# makefile (from `build/core/dex_preopt.mk', line 11)
DEXPREOPT_PRODUCT_DIR := target/product/smdkv210/dex_bootjars
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/sample/products/sample_addon.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/CleanSpec.mk', line 53)
INTERNAL_CLEAN_STEP.build_CleanSpec-mk_acs6@@@@@@@ := find out/target/product/smdkv210 -name "*.apk" | xargs rm
# makefile (from `build/core/definitions.mk', line 1238)
emit-line =    $(if $(1),echo -n '$(strip $(1)) ' >> $(2))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/envsetup.mk', line 248)
TARGET_OUT_DATA_KEYCHARS := out/target/product/smdkv210/system/usr/keychars
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/envsetup.mk', line 184)
HOST_OUT_ROOT_release := out/host
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 117)
TARGET_GLOBAL_LDFLAGS :=  -Wl,-z,noexecstack -Wl,--fix-cortex-a8
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/samsung/product/full_smdkv210.mk.PRODUCT_PROPERTY_OVERRIDES := ro.opengles.version=131072 dalvik.vm.heapsize=32m keyguard.no_require_sim=true ro.com.android.dateformat=MM-dd-yyyy ro.com.android.dataroaming=true ro.ril.hsxpa=1 ro.ril.gprsclass=10 ro.config.notification_sound=OnTheHunt.ogg ro.config.alarm_alert=Alarm_Classic.ogg
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/CleanSpec.mk', line 50)
INTERNAL_CLEAN_STEP.build_CleanSpec-mk_acs6@@@@ := rm -rf out/target/product/smdkv210/system/build.prop
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_NAME := 
# makefile (from `build/core/config.mk', line 217)
JAVATAGS := build/tools/java-event-log-tags.py
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/Makefile', line 1280)
deps := out/target/product/smdkv210/obj/NOTICE.txt out/host/linux-x86/obj/NOTICE.txt out/target/common/docs/offline-sdk-timestamp out/target/product/smdkv210/full_smdkv210-symbols-eng.lnt.zip out/target/product/smdkv210/system.img out/target/product/smdkv210/userdata.img out/target/product/smdkv210/ramdisk.img out/target/product/smdkv210/sdk/sdk-build.prop out/target/product/smdkv210/system/build.prop     out/target/product/smdkv210/system/bin/tvrecv out/target/product/smdkv210/root/init.rc out/target/product/smdkv210/system/etc/vold.fstab out/target/product/smdkv210/system/lib/egl/egl.cfg out/target/product/smdkv210/root/init.smdkv210.rc out/target/product/smdkv210/root/ueventd.smdkv210.rc out/target/product/smdkv210/system/usr/keylayout/s3c-keypad.kl out/target/product/smdkv210/system/etc/media_profiles.xml out/target/product/smdkv210/system/etc/secomxregistry out/target/product/smdkv210/system/lib/libSEC_OMX_Core.so out/target/product/smdkv210/system/lib/libSEC_Resourcemanager.so out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M2V.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.WMV.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Encoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Encoder.so out/target/product/smdkv210/system/lib/libstagefright_amrnb_common.so out/target/product/smdkv210/system/lib/libstagefright_enc_common.so out/target/product/smdkv210/system/lib/libstagefright_avc_common.so out/target/product/smdkv210/system/lib/libstagefright_foundation.so out/target/product/smdkv210/system/lib/libstagefright_color_conversion.so out/target/product/smdkv210/system/lib/libstagefright.so out/target/product/smdkv210/system/lib/libstagefright_omx.so out/target/product/smdkv210/system/lib/liba52.so out/target/product/smdkv210/system/bin/playts out/target/product/smdkv210/system/lib/libtv.so out/target/product/smdkv210/system/bin/remount_ctl out/target/product/smdkv210/system/media/bootanimation.zip out/target/product/smdkv210/system/app/PrimaryApp.apk out/target/product/smdkv210/system/app/HomePage.apk out/target/product/smdkv210/system/app/FileManagers.apk out/target/product/smdkv210/system/app/Game.apk out/target/product/smdkv210/system/lib/libmoddvb.so out/target/product/smdkv210/system/lib/libjnidvb.so out/target/product/smdkv210/system/lib/libdvbbinderclient.so out/target/product/smdkv210/system/lib/libdvbcomm.so out/target/product/smdkv210/system/lib/modules/utiusb.ko out/target/product/smdkv210/system/bin/dvb_load out/target/product/smdkv210/system/bin/busybox out/target/product/smdkv210/system/bin/bash out/target/product/smdkv210/system/bin/dvb_server out/target/product/smdkv210/system/etc/wifi/wpa_supplicant.conf out/target/product/smdkv210/system/etc/dhd.ko out/target/product/smdkv210/system/etc/fw_bcm4329.bin out/target/product/smdkv210/system/etc/nvram.txt out/target/product/smdkv210/system/etc/permissions/handheld_core_hardware.xml out/target/product/smdkv210/system/etc/permissions/android.software.live_wallpaper.xml out/target/product/smdkv210/kernel out/target/product/smdkv210/system/etc/permissions/android.software.live_wallpaper.xml out/target/product/smdkv210/system/vendor/bin/pvrsrvinit out/target/product/smdkv210/system/vendor/lib/egl/libEGL_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/egl/libGLESv1_CM_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/egl/libGLESv2_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/libglslcompiler.so out/target/product/smdkv210/system/vendor/lib/libIMGegl.so out/target/product/smdkv210/system/vendor/lib/libpvr2d.so out/target/product/smdkv210/system/vendor/lib/libpvrANDROID_WSEGL.so out/target/product/smdkv210/system/vendor/lib/libPVRScopeServices.so out/target/product/smdkv210/system/vendor/lib/libsrv_init.so out/target/product/smdkv210/system/vendor/lib/libsrv_um.so out/target/product/smdkv210/system/vendor/lib/libusc.so out/target/product/smdkv210/system/vendor/lib/hw/gralloc.s5pc110.so out/target/product/smdkv210/system/etc/apns-conf.xml out/target/product/smdkv210/system/etc/vold.conf out/target/product/smdkv210/system/media/audio/notifications/F1_MissedCall.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_New_MMS.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_New_SMS.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Buzzer.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_01.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_02.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Classic.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_03.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Rooster_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Classic_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Digital_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_04.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/BeatPlucker.ogg out/target/product/smdkv210/system/media/audio/ringtones/BentleyDubs.ogg out/target/product/smdkv210/system/media/audio/ringtones/BirdLoop.ogg out/target/product/smdkv210/system/media/audio/ringtones/CaribbeanIce.ogg out/target/product/smdkv210/system/media/audio/ringtones/CurveBall.ogg out/target/product/smdkv210/system/media/audio/ringtones/EtherShake.ogg out/target/product/smdkv210/system/media/audio/ringtones/FriendlyGhost.ogg out/target/product/smdkv210/system/media/audio/ringtones/GameOverGuitar.ogg out/target/product/smdkv210/system/media/audio/ringtones/Growl.ogg out/target/product/smdkv210/system/media/audio/ringtones/InsertCoin.ogg out/target/product/smdkv210/system/media/audio/ringtones/LoopyLounge.ogg out/target/product/smdkv210/system/media/audio/ringtones/LoveFlute.ogg out/target/product/smdkv210/system/media/audio/ringtones/MidEvilJaunt.ogg out/target/product/smdkv210/system/media/audio/ringtones/MildlyAlarming.ogg out/target/product/smdkv210/system/media/audio/ringtones/NewPlayer.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises1.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises2.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises3.ogg out/target/product/smdkv210/system/media/audio/ringtones/OrganDub.ogg out/target/product/smdkv210/system/media/audio/ringtones/RomancingTheTone.ogg out/target/product/smdkv210/system/media/audio/ringtones/SitarVsSitar.ogg out/target/product/smdkv210/system/media/audio/ringtones/SpringyJalopy.ogg out/target/product/smdkv210/system/media/audio/ringtones/Terminated.ogg out/target/product/smdkv210/system/media/audio/ringtones/TwirlAway.ogg out/target/product/smdkv210/system/media/audio/ringtones/VeryAlarmed.ogg out/target/product/smdkv210/system/media/audio/ringtones/World.ogg out/target/product/smdkv210/system/media/audio/notifications/CaffeineSnake.ogg out/target/product/smdkv210/system/media/audio/notifications/DearDeer.ogg out/target/product/smdkv210/system/media/audio/notifications/DontPanic.ogg out/target/product/smdkv210/system/media/audio/notifications/Highwire.ogg out/target/product/smdkv210/system/media/audio/notifications/KzurbSonar.ogg out/target/product/smdkv210/system/media/audio/notifications/OnTheHunt.ogg out/target/product/smdkv210/system/media/audio/notifications/Voila.ogg out/target/product/smdkv210/system/media/audio/notifications/Beat_Box_Android.ogg out/target/product/smdkv210/system/media/audio/notifications/Heaven.ogg out/target/product/smdkv210/system/media/audio/notifications/TaDa.ogg out/target/product/smdkv210/system/media/audio/notifications/Tinkerbell.ogg out/target/product/smdkv210/system/media/audio/ui/Effect_Tick.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressStandard.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressSpacebar.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressDelete.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressReturn.ogg out/target/product/smdkv210/system/media/audio/ui/VideoRecord.ogg out/target/product/smdkv210/system/media/audio/ui/camera_click.ogg out/target/product/smdkv210/system/media/audio/ringtones/CrazyDream.ogg out/target/product/smdkv210/system/media/audio/ringtones/DreamTheme.ogg out/target/product/smdkv210/system/tts/lang_pico/de-DE_gl0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/de-DE_ta.bin out/target/product/smdkv210/system/tts/lang_pico/en-GB_kh0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/en-GB_ta.bin out/target/product/smdkv210/system/tts/lang_pico/en-US_lh0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/en-US_ta.bin out/target/product/smdkv210/system/tts/lang_pico/es-ES_zl0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/es-ES_ta.bin out/target/product/smdkv210/system/tts/lang_pico/fr-FR_nk0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/fr-FR_ta.bin out/target/product/smdkv210/system/tts/lang_pico/it-IT_cm0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/it-IT_ta.bin out/target/product/smdkv210/root/default.prop out/target/product/smdkv210/system/build.prop out/target/product/smdkv210/system/etc/event-log-tags out/target/product/smdkv210/system/etc/security/otacerts.zip out/target/product/smdkv210/ramdisk.img  out/target/common/docs/index.html  development/build/sdk.atree sdk/build/tools.atree out/host/linux-x86/bin/atree out/host/linux-x86/bin/line_endings
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_TAGS := 
# makefile (from `build/core/Makefile', line 19)
_fulldest := out/target/product/smdkv210/system/tts/lang_pico/it-IT_ta.bin
# makefile (from `build/core/clear_vars.mk', line 21)
LOCAL_EXPORT_PACKAGE_RESOURCES := 
# makefile (from `build/core/clear_vars.mk', line 61)
LOCAL_DROIDDOC_TEMPLATE_DIR := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.PRODUCT_PROPERTY_OVERRIDES := 
# automatic
%D = $(patsubst %/,%,$(dir $%))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/base_rules.mk', line 561)
ALL_MODULE_NAME_TAGS.debug = tvrecv
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.PRODUCT_TAGS := 
# makefile (from `build/core/config.mk', line 222)
ACP := out/host/linux-x86/bin/acp
# makefile (from `build/core/definitions.mk', line 1590)
transform-host-ranlib-copy-hack = true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `device/htc/passion-common/CleanSpec.mk', line 47)
INTERNAL_CLEAN_STEP.device_htc_passion-common_CleanSpec-mk_acs6@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libgps-rpc_intermediates
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 46)
TARGET_TOOLS_PREFIX := prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/arm-eabi-
# makefile (from `build/core/config.mk', line 226)
KCM := out/host/linux-x86/bin/kcm
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/Makefile', line 312)
BUILT_RAMDISK_TARGET := out/target/product/smdkv210/ramdisk.img
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_COPY_FILES := 
# makefile (from `device/samsung/smdkc110/CleanSpec.mk', line 46)
INTERNAL_CLEAN_STEP.device_samsung_smdkc110_CleanSpec-mk_acs6@ := find out/target/product/smdkv210 -name "*.apk" | xargs rm
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 274)
PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.PRODUCT_COPY_FILES := 
# environment
OLDPWD = /home/lnt/stb/external/tvplay/snddrv
# makefile (from `build/core/combo/arch/arm/armv7-a-neon.mk', line 23)
arch_variant_ldflags := -Wl,--fix-cortex-a8
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/main.mk', line 46)
BUILD_SYSTEM := build/core
# makefile (from `device/htc/passion-common/passion.mk', line 73)
PRODUCTS.device/htc/passion-common/passion.mk.INHERITS_FROM := device/htc/common/common.mk device/htc/passion-common/media_a1026.mk
# makefile (from `build/core/definitions.mk', line 925)
define transform-host-m-to-o-no-deps
@echo "host ObjC: $(PRIVATE_MODULE) <= $<"
$(call transform-host-c-or-s-to-o-no-deps)
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/combo/select.mk', line 58)
HOST_PRELINKER_MAP := build/core/prelink-linux-x86.map
# makefile (from `build/core/envsetup.mk', line 214)
HOST_OUT_SHARED_LIBRARIES := out/host/linux-x86/lib
# makefile (from `build/core/clear_vars.mk', line 57)
LOCAL_NO_STANDARD_LIBRARIES := 
# makefile (from `build/core/tasks/apicheck.mk', line 32)
define check-api
$(TARGET_OUT_COMMON_INTERMEDIATES)/PACKAGING/$(strip $(1))-timestamp: $(2) $(3) $(APICHECK)
	@echo "Checking API:" $(1)
	$(hide) ( $(APICHECK) $(4) $(2) $(3) || ( $(5) ; exit 38 ) )
	$(hide) mkdir -p $$(dir $$@)
	$(hide) touch $$@
checkapi: $(TARGET_OUT_COMMON_INTERMEDIATES)/PACKAGING/$(strip $(1))-timestamp
endef
# makefile (from `build/core/combo/select.mk', line 51)
HOST_GLOBAL_ARFLAGS := crsP
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/definitions.mk', line 939)
extract-and-include-target-whole-static-libs = $(foreach lib,$(PRIVATE_ALL_WHOLE_STATIC_LIBRARIES), $(hide) echo "preparing StaticLib: $(PRIVATE_MODULE) [including $(lib)]"; ldir=$(PRIVATE_INTERMEDIATES_DIR)/WHOLE/$(basename $(notdir $(lib)))_objs; rm -rf $$ldir; mkdir -p $$ldir; filelist=; for f in `$(TARGET_AR) t $(lib)`; do $(TARGET_AR) p $(lib) $$f > $$ldir/$$f; filelist="$$filelist $$ldir/$$f"; done ; $(TARGET_AR) $(TARGET_GLOBAL_ARFLAGS) $(PRIVATE_ARFLAGS) $@ $$filelist; )
# makefile (from `build/core/envsetup.mk', line 265)
TARGET_RECOVERY_OUT := out/target/product/smdkv210/recovery
# default
COMPILE.def = $(M2C) $(M2FLAGS) $(DEFFLAGS) $(TARGET_ARCH)
# makefile (from `build/core/clear_vars.mk', line 22)
LOCAL_MANIFEST_PACKAGE_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `vendor/samsung/smdkv210/BoardConfigVendor.mk', line 23)
BOARD_USES_LIBSECRIL_STUB := false
# makefile (from `build/core/Makefile', line 169)
sdk_build_prop_remove := ro.build.user= ro.build.host= ro.product.brand= ro.product.manufacturer= ro.product.device= ro.build.product=
# makefile (from `build/core/distdir.mk', line 23)
dist_goal := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/envsetup.mk', line 236)
TARGET_OUT_KEYLAYOUT := out/target/product/smdkv210/system/usr/keylayout
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# automatic
*D = $(patsubst %/,%,$(dir $*))
# makefile (from `build/core/binary.mk', line 165)
lex_objects := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `external/clearsilver/CleanSpec.mk', line 55)
INTERNAL_CLEAN_STEP.external_clearsilver_CleanSpec-mk_acs6@@@@@@ := rm -rf out/host/linux-x86/obj/SHARED_LIBRARIES/libneo_cgi_intermediates
# makefile (from `build/core/definitions.mk', line 752)
define transform-cpp-to-o
@mkdir -p $(dir $@)
@echo "target $(PRIVATE_ARM_MODE) C++: $(PRIVATE_MODULE) <= $<"
$(hide) $(PRIVATE_CXX) $(foreach incdir, $(PRIVATE_C_INCLUDES) $(if $(PRIVATE_NO_DEFAULT_COMPILER_FLAGS),, $(PRIVATE_TARGET_PROJECT_INCLUDES) $(PRIVATE_TARGET_C_INCLUDES) ) , -I $(incdir) ) -c $(if $(PRIVATE_NO_DEFAULT_COMPILER_FLAGS),, $(PRIVATE_TARGET_GLOBAL_CFLAGS) $(PRIVATE_TARGET_GLOBAL_CPPFLAGS) $(PRIVATE_ARM_CFLAGS) ) -fno-rtti $(PRIVATE_CFLAGS) $(PRIVATE_CPPFLAGS) $(PRIVATE_DEBUG_CFLAGS) -MD -o $@ $<
$(hide) $(transform-d-to-p)
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/Makefile', line 732)
SYSTEMIMAGE_SOURCE_DIR := out/target/product/smdkv210/system
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic_x86.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/samsung/product/full_smdkv210.mk.PRODUCT_LOCALES := en_US en_GB fr_FR it_IT es_ES es_US de_DE nl_NL cs_CZ pl_PL zh_TW zh_CN ru_RU ko_KR nb_NO pt_PT pt_BR da_DK el_GR sv_SE tr_TR ja_JP hdpi en_US en_GB fr_FR it_IT de_DE es_ES
# automatic
+D = $(patsubst %/,%,$(dir $+))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/CleanSpec.mk', line 55)
INTERNAL_CLEAN_STEP.build_CleanSpec-mk_acs6@@@@@@@@@ := rm -rf out/target/product/smdkv210/system/build.prop
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/core.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/dex_preopt.mk', line 66)
_dbj_jar_no_dex := out/target/product/smdkv210/dex_bootjars/system/framework/core-junit_nodex.jar
# makefile (from `vendor/samsung/smdkv210/device-vendor.mk', line 32)
PRODUCTS.vendor/samsung/smdkv210/device-vendor.mk.INHERITS_FROM := vendor/samsung/smdkv210/device-vendor-blobs.mk
# makefile (from `build/core/combo/HOST_linux-x86.mk', line 21)
get-file-size = stat --format "%s" "$(1)" | tr -d '\n'
# makefile (from `build/core/definitions.mk', line 651)
define dump-module-variables
@echo all_dependencies=$^
@echo PRIVATE_YACCFLAGS=$(PRIVATE_YACCFLAGS);
@echo PRIVATE_CFLAGS=$(PRIVATE_CFLAGS);
@echo PRIVATE_CPPFLAGS=$(PRIVATE_CPPFLAGS);
@echo PRIVATE_DEBUG_CFLAGS=$(PRIVATE_DEBUG_CFLAGS);
@echo PRIVATE_C_INCLUDES=$(PRIVATE_C_INCLUDES);
@echo PRIVATE_LDFLAGS=$(PRIVATE_LDFLAGS);
@echo PRIVATE_LDLIBS=$(PRIVATE_LDLIBS);
@echo PRIVATE_ARFLAGS=$(PRIVATE_ARFLAGS);
@echo PRIVATE_AAPT_FLAGS=$(PRIVATE_AAPT_FLAGS);
@echo PRIVATE_DX_FLAGS=$(PRIVATE_DX_FLAGS);
@echo PRIVATE_JAVACFLAGS=$(PRIVATE_JAVACFLAGS);
@echo PRIVATE_JAVA_LIBRARIES=$(PRIVATE_JAVA_LIBRARIES);
@echo PRIVATE_ALL_SHARED_LIBRARIES=$(PRIVATE_ALL_SHARED_LIBRARIES);
@echo PRIVATE_ALL_STATIC_LIBRARIES=$(PRIVATE_ALL_STATIC_LIBRARIES);
@echo PRIVATE_ALL_WHOLE_STATIC_LIBRARIES=$(PRIVATE_ALL_WHOLE_STATIC_LIBRARIES);
@echo PRIVATE_ALL_OBJECTS=$(PRIVATE_ALL_OBJECTS);
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_NAME := 
# makefile (from `build/core/combo/select.mk', line 51)
TARGET_GLOBAL_ARFLAGS := crsP
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/pathmap.mk', line 68)
JNI_H_INCLUDE := dalvik/libnativehelper/include/nativehelper
# default
M2C = m2c
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/htc/passion/full_passion.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/config.mk', line 258)
OLD_FLEX := prebuilt/linux-x86/flex/flex-2.5.4a
# makefile (from `build/core/envsetup.mk', line 219)
HOST_OUT_HEADERS := out/host/linux-x86/obj/include
# makefile (from `build/core/definitions.mk', line 1154)
define transform-o-to-static-executable
@mkdir -p $(dir $@)
@echo "target StaticExecutable: $(PRIVATE_MODULE) ($@)"
$(hide) $(transform-o-to-static-executable-inner)
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/clear_vars.mk', line 28)
LOCAL_STATIC_JAVA_LIBRARIES := 
# makefile (from `build/core/config.mk', line 207)
FS_GET_STATS := out/host/linux-x86/bin/fs_get_stats
# makefile (from `build/core/binary.mk', line 502)
all_libraries := out/target/product/smdkv210/obj/lib/libc.so out/target/product/smdkv210/obj/lib/libstdc++.so out/target/product/smdkv210/obj/lib/libm.so  
# makefile (from `build/core/envsetup.mk', line 228)
TARGET_OUT_COMMON_INTERMEDIATES := out/target/common/obj
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.PRODUCT_MODEL := 
# makefile (from `build/core/base_rules.mk', line 505)
LOCAL_CHECKED_MODULE := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/tvrecv
# environment
DEFAULTS_PATH = /usr/share/gconf/gnome.default.path
# makefile (from `device/samsung/smdkc110/CleanSpec.mk', line 52)
INTERNAL_CLEAN_STEP.device_samsung_smdkc110_CleanSpec-mk_acs6@@@ := rm -rf out/target/product/smdkc110/obj/SHARED_LIBRARIES/libaudio_intermediates
# makefile (from `build/core/product.mk', line 25)
_find-android-products-files = $(shell test -d device && find device -maxdepth 6 -name AndroidProducts.mk) $(shell test -d vendor && find vendor -maxdepth 6 -name AndroidProducts.mk) $(SRC_TARGET_DIR)/product/AndroidProducts.mk
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/htc/passion/full_passion.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.PRODUCT_MODEL := 
# makefile (from `device/samsung/smdkc110/CleanSpec.mk', line 51)
INTERNAL_CLEAN_STEP.device_samsung_smdkc110_CleanSpec-mk_acs6@@ := rm -rf out/target/product/smdkv210/system/build.prop
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 91)
product_goals := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/dynamic_binary.mk', line 132)
strip_output := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/tvrecv
# makefile (from `build/core/envsetup.mk', line 260)
TARGET_ROOT_OUT_BIN := out/target/product/smdkv210/root/bin
# makefile (from `build/core/binary.mk', line 293)
objc_sources := 
# makefile (from `build/core/product_config.mk', line 245)
PRODUCT_MODEL := Full Android on SMDKV210
# makefile (from `build/core/base_rules.mk', line 144)
LOCAL_MODULE_PATH := out/target/product/smdkv210/system/bin
# makefile (from `build/core/binary.mk', line 277)
gen_c_objects := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 240)
PRODUCT_AAPT_CONFIG := en_US,en_GB,fr_FR,it_IT,es_ES,es_US,de_DE,nl_NL,cs_CZ,pl_PL,zh_TW,zh_CN,ru_RU,ko_KR,nb_NO,pt_PT,pt_BR,da_DK,el_GR,sv_SE,tr_TR,ja_JP,hdpi,en_US,en_GB,fr_FR,it_IT,de_DE,es_ES,nodpi
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sim.mk.PRODUCT_PACKAGES := AccountAndSyncSettings DeskClock AlarmProvider Bluetooth Calculator Calendar Camera CertInstaller DrmProvider Email Gallery3D LatinIME Launcher2 Mms Music Provision Protips QuickSearchBox Settings Sync SystemUI Updater CalendarProvider SyncProvider bouncycastle com.android.location.provider com.android.location.provider.xml core core-junit create_test_dmtrace dalvikvm dexdeps dexdump dexlist dexopt dmtracedump dvz dx ext framework-res hprof-conv icu.dat jasmin jasmin.jar libcrypto libdex libdvm libexpat libicui18n libicuuc libjavacore libnativehelper libnfc_ndef libsqlite_jni libssl libz sqlite-jdbc Browser Contacts Home HTMLViewer Phone ApplicationsProvider ContactsProvider DownloadProvider DownloadProviderUi MediaProvider PicoTts SettingsProvider TelephonyProvider TtsService VpnServices UserDictionaryProvider PackageInstaller DefaultContainerService Bugreport
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic_x86.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/binary.mk', line 465)
built_whole_libraries := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.PRODUCT_LOCALES := 
# default
.LIBPATTERNS = lib%.so lib%.a
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/config.mk', line 71)
BUILD_COPY_HEADERS := build/core/copy_headers.mk
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `external/webkit/CleanSpec.mk', line 51)
INTERNAL_CLEAN_STEP.external_webkit_CleanSpec-mk_acs6@@@@@ := rm -rf out/target/product/smdkv210/obj/STATIC_LIBRARIES/libwebcore_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/executable.mk', line 12)
LOCAL_MODULE_SUFFIX := 
# makefile (from `build/core/product_config.mk', line 253)
PRODUCT_MANUFACTURER := unknown
# makefile (from `build/core/Makefile', line 306)
INTERNAL_RAMDISK_FILES := out/target/product/smdkv210/root/init.rc out/target/product/smdkv210/root/init.smdkv210.rc out/target/product/smdkv210/root/ueventd.smdkv210.rc out/target/product/smdkv210/root/default.prop
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/binary.mk', line 46)
LOCAL_CPPFLAGS := $(subst ,, )
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/dynamic_binary.mk', line 97)
prelink_output := out/target/product/smdkv210/symbols/system/bin/tvrecv
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.seen := true
# makefile (from `build/core/Makefile', line 701)
BUILT_SYSTEMIMAGE := out/target/product/smdkv210/obj/PACKAGING/systemimage_intermediates/system.img
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.PRODUCT_DEVICE := generic
# makefile (from `external/webkit/CleanSpec.mk', line 49)
INTERNAL_CLEAN_STEP.external_webkit_CleanSpec-mk_acs6@@@ := rm -rf out/target/product/smdkv210/obj/STATIC_LIBRARIES/libwebcore_intermediates
# makefile (from `build/core/definitions.mk', line 640)
hide := @
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/full.mk.PRODUCT_DEVICE := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.PRODUCT_NAME := 
# makefile (from `build/core/definitions.mk', line 231)
all-logtags-files-under = $(patsubst ./%,%, $(shell cd $(LOCAL_PATH) ; find $(1) -name "*.logtags" -and -not -name ".*") )
# makefile (from `build/core/Makefile', line 489)
kernel_notice_file := out/target/product/smdkv210/obj/NOTICE_FILES/src/kernel.txt
# makefile (from `build/core/product_config.mk', line 289)
PRODUCT_TAGS := dalvik.gc.type-precise
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_eiv_tv := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/core.mk.PRODUCT_MODEL := 
# makefile (from `build/core/config.mk', line 43)
SRC_DROIDDOC_DIR := build/tools/droiddoc
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# environment
TARGET_BUILD_VARIANT = eng
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.PRODUCT_DEVICE := generic
# makefile (from `build/core/definitions.mk', line 200)
all-subdir-c-files = $(call all-c-files-under,.)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/config.mk', line 36)
SRC_HOST_HEADERS := tools/include
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/notice_files.mk', line 69)
notice_target := NOTICE-TARGET-EXECUTABLES-tvrecv
# default
SUFFIXES := .out .a .ln .o .c .cc .C .cpp .p .f .F .r .y .l .s .S .mod .sym .def .h .info .dvi .tex .texinfo .texi .txinfo .w .ch .web .sh .elc .el
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sdk.mk.PRODUCT_DEVICE := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.inherited := 
# makefile (from `build/core/Makefile', line 56)
_cpSEP := =
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/core.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/envsetup.mk', line 275)
TARGET_INSTALLER_SYSTEM_OUT := out/target/product/smdkv210/installer/root/system
# makefile (from `build/core/dex_preopt.mk', line 77)
_build-dexpreopt-boot-jar-dependency = $(call _build-dexpreopt-boot-jar-dependency-pair,$(DEXPREOPT_BOOT_JARS_MODULES))
# environment
WINDOWPATH = 7
# makefile (from `build/core/Makefile', line 250)
DEFAULT_KEY_CERT_PAIR := build/target/product/security/testkey
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/envsetup.mk', line 201)
TARGET_PRODUCT_OUT_ROOT := out/target/product
# makefile (from `external/webkit/CleanSpec.mk', line 50)
INTERNAL_CLEAN_STEP.external_webkit_CleanSpec-mk_acs6@@@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libwebcore_intermediates
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/core.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/definitions.mk', line 578)
get-tagged-modules = $(filter-out $(call modules-for-tag-list,$(2)), $(call modules-for-tag-list,$(1)))
# default
CHECKOUT,v = +$(if $(wildcard $@),,$(CO) $(COFLAGS) $< $@)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/tasks/cts.mk', line 147)
APP_SECURITY_LIB := out/host/linux-x86/cts/android-cts/repository/testcases/CtsAppSecurityTests.jar
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product.mk', line 117)
inherit-product-if-exists =   $(if $(wildcard $(1)),$(call inherit-product,$(1)),)
# default
LINK.F = $(FC) $(FFLAGS) $(CPPFLAGS) $(LDFLAGS) $(TARGET_ARCH)
# makefile (from `build/core/envsetup.mk', line 244)
TARGET_OUT_DATA_SHARED_LIBRARIES := out/target/product/smdkv210/system/lib
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/clear_vars.mk', line 37)
LOCAL_NO_FDO_SUPPORT := 
# makefile (from `build/core/tasks/cts.mk', line 128)
CORE_INTERMEDIATES := out/target/common/obj/JAVA_LIBRARIES/core_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/CleanSpec.mk', line 55)
build_CleanSpec-mk_acs := 6@@@@@@@@@
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/combo/select.mk', line 56)
TARGET_STATIC_LIB_SUFFIX := .a
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/config.mk', line 233)
APRIORI := out/host/linux-x86/bin/apriori
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.PRODUCT_DEVICE := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `external/webkit/CleanSpec.mk', line 52)
INTERNAL_CLEAN_STEP.external_webkit_CleanSpec-mk_acs6@@@@@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libwebcore_intermediates
# makefile (from `build/core/Makefile', line 30)
gen := out/target/common/docs/index.html
# makefile (from `build/core/clear_vars.mk', line 66)
LOCAL_DROIDDOC_HTML_DIR := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.inherited := 
# makefile (from `build/core/definitions.mk', line 277)
find-subdir-subdir-files = $(filter-out $(patsubst %,$(1)/%,$(3)),$(patsubst ./%,%,$(shell cd $(LOCAL_PATH) ; find $(1) -maxdepth 1 -name $(2))))
# makefile (from `build/CleanSpec.mk', line 55)
_acs_id := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# default
LINK.f = $(FC) $(FFLAGS) $(LDFLAGS) $(TARGET_ARCH)
# makefile (from `device/samsung/smdkc110/CleanSpec.mk', line 55)
INTERNAL_CLEAN_STEP.device_samsung_smdkc110_CleanSpec-mk_acs6@@@@@@ := rm -rf out/target/product/smdkc110/obj/EXECUTABLES/alsa_*
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/cleanbuild.mk', line 42)
add-clean-step = $(eval # for build/core/cleanspec.mk, dont use makefile path as part of step id) $(if $(filter %/cleanspec.mk,$(lastword $(MAKEFILE_LIST))), $(eval $(call _add-clean-step,$(1),true)), $(eval $(call _add-clean-step,$(1))))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/config.mk', line 56)
BUILD_HOST_SHARED_LIBRARY := build/core/host_shared_library.mk
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/samsung/product/full_smdkv210.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/definitions.mk', line 986)
define transform-host-o-to-static-lib
@mkdir -p $(dir $@)
@rm -f $@
$(extract-and-include-host-whole-static-libs)
@echo "host StaticLib: $(PRIVATE_MODULE) ($@)"
echo $(filter %.o, $^) | xargs $(HOST_AR) $(HOST_GLOBAL_ARFLAGS) $(PRIVATE_ARFLAGS) $@
endef
# makefile (from `build/core/cleanbuild.mk', line 97)
INTERNAL_CLEAN_STEPS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/cleanbuild.mk', line 84)
CURRENT_CLEAN_STEPS := 
# makefile (from `build/core/definitions.mk', line 287)
find-subdir-assets = $(if $(1),$(patsubst ./%,%, $(shell if [ -d $(1) ] ; then cd $(1) ; find ./ -type f -and -not -type l ; fi)), $(warning Empty argument supplied to find-subdir-assets) )
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.PRODUCT_PROPERTY_OVERRIDES := 
# automatic
<D = $(patsubst %/,%,$(dir $<))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `device/samsung/smdkv210/device.mk', line 87)
BOARD_USES_LNT_OMX := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_TAGS := 
# makefile (from `build/core/dynamic_binary.mk', line 18)
LOCAL_UNSTRIPPED_PATH := out/target/product/smdkv210/symbols/system/bin
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 94)
BOARD_USES_BROADCOM_WIFI := true
# makefile (from `build/core/product_config.mk', line 279)
PRODUCT_PROPERTY_OVERRIDES := ro.opengles.version=131072 dalvik.vm.heapsize=32m keyguard.no_require_sim=true ro.com.android.dateformat=MM-dd-yyyy ro.com.android.dataroaming=true ro.ril.hsxpa=1 ro.ril.gprsclass=10 ro.config.notification_sound=OnTheHunt.ogg ro.config.alarm_alert=Alarm_Classic.ogg
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/config.mk', line 310)
HOST_GLOBAL_CPPFLAGS = $(COMMON_GLOBAL_CPPFLAGS) $(HOST_RELEASE_CPPFLAGS)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 36)
is-c-identifier = $(strip $(if $(1), $(if $(filter $(addsuffix %,$(_ici_digits)),$(1)), , $(eval w := $(1)) $(foreach c,$(_ici_digits) $(_ici_alphaunderscore), $(eval w := $(subst $(c),,$(w))) ) $(if $(w),,TRUE) $(eval w :=) ) ) )
# makefile (from `build/core/definitions.mk', line 930)
define tranform-host-m-to-o
$(transform-host-m-to-o-no-deps)
$(transform-d-to-p)
endef
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sim.mk.PRODUCT_BRAND := generic
# makefile (from `build/core/distdir.mk', line 72)
dist-for-goals = 
# makefile (from `build/core/config.mk', line 205)
MKYAFFS2 := out/host/linux-x86/bin/mkyaffs2image
# makefile (from `build/core/definitions.mk', line 1674)
assert-max-file-size = $(if $(2), size=$$(for i in $(1); do $(call get-file-size,$$i); echo +; done; echo 0); total=$$(( $$( echo "$$size" ) )); printname=$$(echo -n "$(1)" | tr " " +); echo "$$printname total size is $$total"; img_blocksize=$(call image-size-from-data-size,$(BOARD_FLASH_BLOCK_SIZE)); if [ "$(3)" == "yaffs" ]; then reservedblocks=8; else reservedblocks=0; fi; twoblocks=$$((img_blocksize * 2)); onepct=$$((((($(2) / 100) - 1) / img_blocksize + 1) * img_blocksize)); reserve=$$(((twoblocks > onepct ? twoblocks : onepct) + reservedblocks * img_blocksize)); maxsize=$$(($(2) - reserve)); if [ "$$total" -gt "$$maxsize" ]; then echo "error: $$printname too large ($$total > [$(2) - $$reserve])"; false; elif [ "$$total" -gt $$((maxsize - 32768)) ]; then echo "WARNING: $$printname approaching size limit ($$total now; limit $$maxsize)"; fi , true )
# makefile (from `build/core/base_rules.mk', line 201)
LOCAL_INSTALLED_MODULE := out/target/product/smdkv210/system/bin/tvrecv
# makefile (from `build/core/config.mk', line 214)
E2FSCK := e2fsck
# makefile (from `device/samsung/smdkc110/CleanSpec.mk', line 56)
INTERNAL_CLEAN_STEP.device_samsung_smdkc110_CleanSpec-mk_acs6@@@@@@@ := rm -rf out/target/product/smdkc110/system/bin/alsa_*
# makefile (from `build/core/definitions.mk', line 901)
define transform-host-c-to-o-no-deps
@echo "host C: $(PRIVATE_MODULE) <= $<"
$(call transform-host-c-or-s-to-o-no-deps, )
endef
# makefile (from `device/samsung/smdkv210/CleanSpec.mk', line 51)
INTERNAL_CLEAN_STEP.device_samsung_smdkv210_CleanSpec-mk_acs6@@ := rm -rf out/target/product/smdkv210/system/build.prop
# environment
ANDROID_JAVA_TOOLCHAIN = /usr/lib/jvm/java-6-sun/bin
# makefile (from `cts/CtsTestCaseList.mk', line 18)
CTS_SECURITY_APPS_LIST := CtsAppAccessData CtsAppWithData CtsInstrumentationAppDiffCert CtsPermissionDeclareApp CtsSharedUidInstall CtsSharedUidInstallDiffCert CtsSimpleAppInstall CtsSimpleAppInstallDiffCert CtsTargetInstrumentationApp CtsUsePermissionDiffCert
# makefile (from `external/v8/CleanSpec.mk', line 47)
INTERNAL_CLEAN_STEP.external_v8_CleanSpec-mk_acs6@ := rm -rf out/target/product/smdkv210/obj/STATIC_LIBRARIES/libv8_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/base_rules.mk', line 129)
LOCAL_MODULE_CLASS := EXECUTABLES
# automatic
?D = $(patsubst %/,%,$(dir $?))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/Makefile', line 1188)
EMMA_META_ZIP := out/target/product/smdkv210/emma_meta.zip
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/base_rules.mk', line 422)
cleantarget := clean-tvrecv
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/binary.mk', line 230)
gen_S_objects := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/config.mk', line 40)
SRC_API_DIR := frameworks/base/api
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.seen := true
# makefile (from `build/core/definitions.mk', line 338)
add-prebuilt-file =     $(eval $(include-prebuilt))
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/sample/products/sample_addon.mk.PRODUCT_TAGS := 
# environment
XAUTHORITY = /var/run/gdm/auth-for-lnt-UTq6C4/database
# makefile (from `build/core/definitions.mk', line 60)
ALL_PREBUILT := 
# makefile (from `build/core/cleanbuild.mk', line 125)
building_sdk := 
# makefile (from `build/core/combo/arch/arm/armv7-a-neon.mk', line 18)
arch_variant_cflags := -march=armv7-a -mfloat-abi=softfp -mfpu=neon
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sdk.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/clear_vars.mk', line 92)
LOCAL_NDK_VERSION := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/full.mk.PRODUCT_PACKAGES := OpenWnn PinyinIME VoiceDialer libWnnEngDic libWnnJpnDic libwnndict AccountAndSyncSettings DeskClock AlarmProvider Bluetooth Calculator Calendar Camera CertInstaller DrmProvider Email Gallery3D LatinIME Launcher2 Mms Music Provision Protips QuickSearchBox Settings Sync SystemUI Updater CalendarProvider SyncProvider bouncycastle com.android.location.provider com.android.location.provider.xml core core-junit create_test_dmtrace dalvikvm dexdeps dexdump dexlist dexopt dmtracedump dvz dx ext framework-res hprof-conv icu.dat jasmin jasmin.jar libcrypto libdex libdvm libexpat libicui18n libicuuc libjavacore libnativehelper libnfc_ndef libsqlite_jni libssl libz sqlite-jdbc Browser Contacts Home HTMLViewer Phone ApplicationsProvider ContactsProvider DownloadProvider DownloadProviderUi MediaProvider PicoTts SettingsProvider TelephonyProvider TtsService VpnServices UserDictionaryProvider PackageInstaller DefaultContainerService Bugreport
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.PRODUCT_MANUFACTURER := 
# makefile (from `device/samsung/smdkv210/device.mk', line 56)
source_vold_fstab_file := device/samsung/smdkv210/vold.fstab
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/core.mk.PRODUCT_PROPERTY_OVERRIDES := ro.config.notification_sound=OnTheHunt.ogg ro.config.alarm_alert=Alarm_Classic.ogg
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 59)
BOARD_USES_OVERLAY := true
# makefile (from `build/core/envsetup.mk', line 273)
TARGET_INSTALLER_DATA_OUT := out/target/product/smdkv210/installer/data
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic_x86.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `external/webkit/CleanSpec.mk', line 53)
INTERNAL_CLEAN_STEP.external_webkit_CleanSpec-mk_acs6@@@@@@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libwebcore_intermediates
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 38)
TARGET_NO_KERNEL := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/core.mk.PRODUCT_NAME := core
# makefile (from `build/core/Makefile', line 484)
target_notice_file_html := out/target/product/smdkv210/obj/NOTICE.html
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.PRODUCT_TAGS := 
# makefile (from `device/samsung/smdkc110/CleanSpec.mk', line 57)
INTERNAL_CLEAN_STEP.device_samsung_smdkc110_CleanSpec-mk_acs6@@@@@@@@ := rm -rf out/target/product/smdkc110/system/etc/asound.conf
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/config.mk', line 69)
BUILD_HOST_JAVA_LIBRARY := build/core/host_java_library.mk
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.PRODUCT_NAME := 
# makefile (from `build/core/device.mk', line 17)
_device_var_list := DEVICE_NAME DEVICE_BOARD DEVICE_REGION
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_LOCALES := 
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 33)
BOARD_HAVE_BLUETOOTH := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sdk.mk.PRODUCT_PACKAGES := SystemUI AccountAndSyncSettings Camera Calculator DeskClock Development DrmProvider Email Fallback Gallery GPSEnable Launcher2 Protips Music Mms Settings SdkSetup CustomLocale gpstest sqlite3 LatinIME PinyinIME OpenWnn libWnnEngDic libWnnJpnDic libwnndict CertInstaller LiveWallpapersPicker ApiDemos GestureBuilder SoftKeyboard CubeLiveWallpapers QuickSearchBox adb dmtracedump etc1tool hprof-conv mksdcard emulator ddms hierarchyviewer draw9patch layoutopt traceview android dexdump androidprefs sdkstats archquery ddms ddmlib ddmuilib hierarchyviewer draw9patch layoutopt uix traceview anttasks sdklib sdkuilib sdkmanager swing-worker-1.1 groovy-all-1.7.0 commons-compress-1.0 emmalib org-netbeans-api-visual org-openide-util jcommon-1.0.12 jfreechart-1.0.9 jfreechart-1.0.9-swt org.eclipse.core.commands_3.4.0.I20080509-2000 org.eclipse.equinox.common_3.4.0.v20080421-2006 org.eclipse.jface_3.4.2.M20090107-0800 osgi layoutlib bouncycastle com.android.location.provider com.android.location.provider.xml core core-junit create_test_dmtrace dalvikvm dexdeps dexdump dexlist dexopt dmtracedump dvz dx ext framework-res hprof-conv icu.dat jasmin jasmin.jar libcrypto libdex libdvm libexpat libicui18n libicuuc libjavacore libnativehelper libnfc_ndef libsqlite_jni libssl libz sqlite-jdbc Browser Contacts Home HTMLViewer Phone ApplicationsProvider ContactsProvider DownloadProvider DownloadProviderUi MediaProvider PicoTts SettingsProvider TelephonyProvider TtsService VpnServices UserDictionaryProvider PackageInstaller DefaultContainerService Bugreport
# environment
TARGET_BUILD_APPS = 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/htc/passion/full_passion.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/CleanSpec.mk', line 54)
INTERNAL_CLEAN_STEP.build_CleanSpec-mk_acs6@@@@@@@@ := rm -rf out/target/product/smdkv210/obj/APPS/*
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 210)
TARGET_CRTBEGIN_STATIC_O := out/target/product/smdkv210/obj/lib/crtbegin_static.o
# makefile (from `build/core/definitions.mk', line 137)
first-makefiles-under = $(shell build/tools/findleaves.py --prune=out --prune=.repo --prune=.git --mindepth=2 $(1) Android.mk)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_BRAND := 
# makefile (from `build/core/definitions.mk', line 1641)
define transform-generated-source
@echo "target Generated: $(PRIVATE_MODULE) <= $<"
@mkdir -p $(dir $@)
$(hide) $(PRIVATE_CUSTOM_TOOL)
endef
# environment
JAVA_HOME = /usr/lib/jvm/java-6-sun
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 197)
KERNEL_HEADERS_ARCH := bionic/libc/kernel/arch-arm
# makefile (from `build/core/product_config.mk', line 216)
extra_locales := 
# makefile (from `build/core/Makefile', line 818)
INSTALLED_USERDATAIMAGE_TARGET := out/target/product/smdkv210/userdata.img
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.inherited := 
# makefile (from `build/core/tasks/cts.mk', line 131)
_idfIntBase := out/target/common/obj
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.PRODUCT_PACKAGES := 
# makefile (from `build/CleanSpec.mk', line 55)
_acs_makefile_prefix := 
# makefile (from `build/core/config.mk', line 203)
MINIGZIP := out/host/linux-x86/bin/minigzip
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sdk.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/envsetup.mk', line 234)
TARGET_OUT_JAVA_LIBRARIES := out/target/product/smdkv210/system/framework
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/clear_vars.mk', line 67)
LOCAL_ASSET_FILES := 
# makefile (from `build/core/device.mk', line 22)
dump-device = $(info ==== $(1) ====) $(foreach v,$(_device_var_list), $(info DEVICES.$(1).$(v) := $(DEVICES.$(1).$(v)))) $(info --------)
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/base_rules.mk', line 172)
LOCAL_INSTALLED_MODULE_STEM := tvrecv
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# default
COFLAGS = 
# environment
XDG_DATA_DIRS = /usr/share/gnome:/usr/local/share/:/usr/share/
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/config.mk', line 197)
DOXYGEN := doxygen
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/sample/products/sample_addon.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `external/v8/CleanSpec.mk', line 49)
INTERNAL_CLEAN_STEP.external_v8_CleanSpec-mk_acs6@@@ := rm -rf out/host/linux-x86/obj/EXECUTABLES/mksnapshot_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.inherited := 
# makefile (from `external/webkit/CleanSpec.mk', line 54)
INTERNAL_CLEAN_STEP.external_webkit_CleanSpec-mk_acs6@@@@@@@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libwebcore_intermediates
# default
CC = cc
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/device.mk', line 29)
dump-devices = $(foreach p,$(DEVICES),$(call dump-device,$(p)))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.PRODUCT_PACKAGES := 
# makefile (from `external/clearsilver/CleanSpec.mk', line 57)
external_clearsilver_CleanSpec-mk_acs := 6@@@@@@@@
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/core.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/base_rules.mk', line 235)
aidl_java_sources := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/clear_vars.mk', line 97)
LOCAL_PROGUARD_FLAG_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/samsung/product/full_smdkv210.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/envsetup.mk', line 250)
TARGET_OUT_DATA_STATIC_LIBRARIES := out/target/product/smdkv210/obj/lib
# makefile (from `build/core/base_rules.mk', line 524)
ALL_MODULES.tvrecv.CLASS :=  EXECUTABLES
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_NAME := 
# makefile (from `build/core/tasks/cts.mk', line 20)
CTS_EXECUTABLE := startcts
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `frameworks/base/CleanSpec.mk', line 58)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@@@@@ := rm -f out/target/product/smdkv210/obj/lib/libequalizertest.so
# makefile (from `build/core/combo/select.mk', line 43)
TARGET_HAVE_STRERROR_R_STRRET := 1
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/Makefile', line 775)
system_tar := out/target/product/smdkv210/system.tar
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/clear_vars.mk', line 46)
LOCAL_AAPT_FLAGS := 
# makefile (from `build/core/definitions.mk', line 1344)
define create-empty-package
@mkdir -p $(dir $@)
$(hide) touch $(dir $@)/dummy
$(hide) (cd $(dir $@) && jar cf $(notdir $@) dummy)
$(hide) zip -qd $@ dummy
$(hide) rm $(dir $@)/dummy
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/sample/products/sample_addon.mk.PRODUCT_PACKAGE_OVERLAYS := development/sdk_overlay
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `device/samsung/smdkc110/CleanSpec.mk', line 59)
INTERNAL_CLEAN_STEP.device_samsung_smdkc110_CleanSpec-mk_acs6@@@@@@@@@@ := rm -rf out/target/product/smdkc110/obj/PACKAGING/systemimage_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/clear_vars.mk', line 80)
LOCAL_POST_PROCESS_COMMAND := true
# makefile (from `build/core/tasks/cts.mk', line 74)
define generate-core-test-description
@echo "Generate core-test description ("$(notdir $(1))")"
$(hide) java $(PRIVATE_JAVAOPTS) -classpath $(PRIVATE_CLASSPATH) $(PRIVATE_PARAMS) CollectAllTests $(1) $(2) $(3) $(4) $(5)
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/config.mk', line 225)
DX := out/host/linux-x86/bin/dx
# makefile (from `build/core/tasks/cts.mk', line 32)
CTS_CORE_CASE_LIST := android.core.tests.dom android.core.tests.luni.io android.core.tests.luni.lang android.core.tests.luni.net android.core.tests.luni.util android.core.tests.xml android.core.tests.runner
# makefile (from `build/core/combo/select.mk', line 44)
HOST_HAVE_STRLCPY := 0
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.seen := true
# makefile (from `build/core/definitions.mk', line 637)
pretty = @echo $1
# makefile (from `build/core/definitions.mk', line 446)
module-installed-files = $(foreach module,$(1),$(ALL_MODULES.$(module).INSTALLED))
# makefile (from `build/core/config.mk', line 55)
BUILD_HOST_STATIC_LIBRARY := build/core/host_static_library.mk
# makefile (from `build/core/definitions.mk', line 79)
ALL_FINDBUGS_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `external/clearsilver/CleanSpec.mk', line 50)
INTERNAL_CLEAN_STEP.external_clearsilver_CleanSpec-mk_acs6@ := rm -rf out/host/linux-x86/obj/SHARED_LIBRARIES/libneo_cs_intermediates
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 74)
BOARD_KERNEL_PAGESIZE := 4096
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.PRODUCT_PROPERTY_OVERRIDES := 
# default
LD = ld
# makefile (from `build/core/combo/select.mk', line 39)
HOST_HAVE_64BIT_IO := 1
# makefile (from `build/core/combo/select.mk', line 40)
TARGET_HAVE_CLOCK_TIMERS := 1
# makefile (from `build/core/envsetup.mk', line 207)
PRODUCT_OUT := out/target/product/smdkv210
# makefile (from `build/core/binary.mk', line 247)
gen_asm_objects := $(subst ,, )
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/htc/passion/full_passion.mk.PRODUCT_MODEL := Full Android on Passion
# makefile (from `build/core/binary.mk', line 213)
gen_cpp_objects := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/config.mk', line 196)
YACC := bison -d
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.PRODUCT_TAGS := 
# makefile (from `build/core/config.mk', line 215)
JARJAR := out/host/linux-x86/framework/jarjar.jar
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/envsetup.mk', line 226)
TARGET_OUT_HEADERS := out/target/product/smdkv210/obj/include
# makefile (from `build/core/version_defaults.mk', line 34)
INTERNAL_BUILD_ID_MAKEFILE := build/core/build_id.mk
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 228)
transform-o-to-shared-lib-inner = $(TARGET_CXX) -nostdlib -Wl,-soname,$(notdir $@) -Wl,-T,$(BUILD_SYSTEM)/armelf.xsc -Wl,--gc-sections -Wl,-shared,-Bsymbolic $(PRIVATE_TARGET_GLOBAL_LD_DIRS) $(PRIVATE_ALL_OBJECTS) $(PRIVATE_TARGET_CRTBEGIN_SO_O) -Wl,--whole-archive $(call normalize-host-libraries,$(PRIVATE_ALL_WHOLE_STATIC_LIBRARIES)) -Wl,--no-whole-archive $(call normalize-target-libraries,$(PRIVATE_ALL_STATIC_LIBRARIES)) $(call normalize-target-libraries,$(PRIVATE_ALL_SHARED_LIBRARIES)) -o $@ $(PRIVATE_TARGET_GLOBAL_LDFLAGS) $(PRIVATE_LDFLAGS) $(PRIVATE_TARGET_FDO_LIB) $(PRIVATE_TARGET_LIBGCC) $(PRIVATE_TARGET_CRTEND_SO_O)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 58)
BOARD_USES_HGL := true
# makefile (from `build/core/tasks/cts.mk', line 26)
CTS_HOST_JAR := out/host/linux-x86/framework/cts.jar
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.PRODUCT_BRAND := generic
# makefile (from `build/core/envsetup.mk', line 19)
CORRECT_BUILD_ENV_SEQUENCE_NUMBER := 10
# makefile (from `build/core/binary.mk', line 333)
all_objects :=         out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/debug.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostAppInfoClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostCAClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDateTimeServerAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDeviceAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostEPGDBClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostPlatForm.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostRgnClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostSCClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostTunerClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostXDCServerAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/main.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketRecv.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketSend.o    
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/full.mk.PRODUCT_BRAND := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.PRODUCT_LOCALES := 
# makefile (from `device/samsung/smdkc110/CleanSpec.mk', line 60)
INTERNAL_CLEAN_STEP.device_samsung_smdkc110_CleanSpec-mk_acs6@@@@@@@@@@@ := rm -rf out/target/product/smdkc110/obj/SHARED_LIBRARIES/libaudio_intermediates
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic_x86.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `device/samsung/smdkv210/CleanSpec.mk', line 53)
INTERNAL_CLEAN_STEP.device_samsung_smdkv210_CleanSpec-mk_acs6@@@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libaudio_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/htc/passion/full_passion.mk.PRODUCT_MANUFACTURER := HTC
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.PRODUCT_MODEL := 
# makefile (from `build/core/binary.mk', line 198)
cpp_objects := $(subst ,, )
# makefile (from `build/core/combo/select.mk', line 39)
TARGET_HAVE_64BIT_IO := 1
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/htc/passion/full_passion.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/binary.mk', line 383)
insert-liblog =   $(if $(filter liblog,$(1)),$(1), $(if $(filter libcutils,$(1)), $(patsubst libcutils,liblog libcutils,$(1)) , $(patsubst libutils,liblog libutils,$(1)) ) )
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.PRODUCT_BRAND := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.PRODUCT_DEVICE := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sim.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/definitions.mk', line 808)
define transform-c-to-o-no-deps
@echo "target $(PRIVATE_ARM_MODE) C: $(PRIVATE_MODULE) <= $<"
$(call transform-c-or-s-to-o-no-deps, )
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_NAME := 
# makefile (from `build/core/dex_preopt.mk', line 13)
DEXPREOPT_DEXOPT := host/linux-x86/bin/dexopt
# makefile (from `build/core/config.mk', line 230)
EMMA_JAR := external/emma/lib/emma.jar
# makefile (from `dalvik/CleanSpec.mk', line 51)
dalvik_CleanSpec-mk_acs := 6@@@@@
# makefile (from `build/core/envsetup.mk', line 105)
HOST_BUILD_TYPE := release
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sdk.mk.PRODUCT_BRAND := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.PRODUCT_TAGS := 
# makefile (from `build/core/envsetup.mk', line 249)
TARGET_OUT_DATA_ETC := out/target/product/smdkv210/system/etc
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/sample/products/sample_addon.mk.PRODUCT_NAME := sample_addon
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/tasks/cts.mk', line 124)
CORE_VM_TEST_DESC := out/host/linux-x86/cts/android-cts/repository/testcases/android.core.vm-tests
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/clear_vars.mk', line 30)
LOCAL_WHOLE_STATIC_LIBRARIES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/target/product/sdk.mk', line 112)
PRODUCTS.build/target/product/sdk.mk.INHERITS_FROM := build/target/product/core.mk
# makefile (from `build/core/binary.mk', line 63)
my_target_global_cppflags := -fvisibility-inlines-hidden -DANDROID -fmessage-length=0 -W -Wall -Wno-unused -Winit-self -Wpointer-arith -Wsign-promo -Werror=return-type -Werror=non-virtual-dtor -Werror=address -Werror=sequence-point -DNDEBUG -UDEBUG
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `frameworks/base/CleanSpec.mk', line 65)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@@@@@@@@@@@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libequalizer_intermediates
# makefile (from `build/core/envsetup.mk', line 238)
TARGET_OUT_ETC := out/target/product/smdkv210/system/etc
# makefile (from `build/core/base_rules.mk', line 174)
LOCAL_BUILT_MODULE_STEM := tvrecv
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/combo/select.mk', line 54)
TARGET_SHLIB_SUFFIX := .so
# default
MAKEINFO = makeinfo
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/combo/select.mk', line 36)
HOST_HAVE_WINDOWS_FILE_PATH := 0
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_MODEL := 
# makefile (from `build/core/Makefile', line 792)
INTERNAL_USERDATAIMAGE_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.PRODUCT_NAME := sdk
# makefile (from `build/core/tasks/product-graph.mk', line 18)
products_graph := out/products.dot
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sim.mk.PRODUCT_TAGS := 
# makefile (from `build/core/envsetup.mk', line 255)
TARGET_ROOT_OUT_UNSTRIPPED := out/target/product/smdkv210/symbols
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/base_rules.mk', line 195)
LOCAL_UNINSTALLABLE_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/config.mk', line 200)
ICUDATA := out/host/linux-x86/bin/icudata
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/envsetup.mk', line 246)
TARGET_OUT_DATA_APPS := out/target/product/smdkv210/data/app
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/config.mk', line 38)
SRC_SERVERS := servers
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.PRODUCT_PROPERTY_OVERRIDES := 
# environment
SSH_AUTH_SOCK = /tmp/keyring-xdTzEP/ssh
# makefile (from `build/core/definitions.mk', line 607)
define _get-package-overrides
 $(eval ### Discard any words containing slashes, unless they end in .apk, ### in which case trim off the directory component and the suffix. ### If there are no slashes, keep the entire word.)
 $(eval _gpo_names := $(subst /,@@@ @@@,$(1)))
 $(eval _gpo_names := $(filter %.apk,$(_gpo_names)) $(filter-out %@@@ @@@%,$(_gpo_names)))
 $(eval _gpo_names := $(patsubst %.apk,%,$(_gpo_names)))
 $(eval _gpo_names := $(patsubst @@@%,%,$(_gpo_names)))

 $(eval ### Remove any remaining words that contain dots.)
 $(eval _gpo_names := $(subst .,@@@ @@@,$(_gpo_names)))
 $(eval _gpo_names := $(filter-out %@@@ @@@%,$(_gpo_names)))

 $(eval ### Now we have a list of any words that could possibly refer to ### packages, although there may be words that do not.  Only ### real packages will be present under PACKAGES.*, though.)
 $(foreach _gpo_name,$(_gpo_names),$(PACKAGES.$(_gpo_name).OVERRIDES))
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.PRODUCT_BRAND := 
# default
PC = pc
# makefile (from `build/core/Makefile', line 269)
all_event_log_tags_src := 
# makefile (from `build/core/config.mk', line 52)
BUILD_COMBOS := build/core/combo
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.PRODUCT_TAGS := 
# makefile (from `build/core/dex_preopt.mk', line 7)
DEXPREOPT_BOOT_JARS := core:bouncycastle:ext:framework:android.policy:services:core-junit
# makefile (from `build/core/binary.mk', line 124)
debug_cflags := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.PRODUCT_NAME := 
# makefile (from `build/core/binary.mk', line 139)
yacc_sources := 
# default
COMPILE.F = $(FC) $(FFLAGS) $(CPPFLAGS) $(TARGET_ARCH) -c
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.seen := true
# makefile (from `frameworks/base/CleanSpec.mk', line 59)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@@@@@@ := rm -f out/target/product/smdkv210/obj/lib/libreverb.so
# makefile (from `build/core/combo/HOST_linux-x86.mk', line 43)
HOST_NO_UNDEFINED_LDFLAGS := -Wl,--no-undefined
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 40)
TARGET_NO_RADIOIMAGE := true
# default
CTANGLE = ctangle
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/config.mk', line 202)
MKBOOTFS := out/host/linux-x86/bin/mkbootfs
# makefile (from `build/core/config.mk', line 62)
BUILD_HOST_EXECUTABLE := build/core/host_executable.mk
# makefile (from `build/core/definitions.mk', line 1030)
define transform-host-o-to-package
@mkdir -p $(dir $@)
@echo "host Package: $(PRIVATE_MODULE) ($@)"
$(hide) $(transform-host-o-to-shared-lib-inner)
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 91)
BOARD_ENABLE_LASTFRAME_VIEW := false
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/copy_headers.mk', line 7)
my_prefix := TARGET_
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `device/samsung/smdkv210/CleanSpec.mk', line 54)
INTERNAL_CLEAN_STEP.device_samsung_smdkv210_CleanSpec-mk_acs6@@@@@ := rm -rf out/target/product/smdkv210/obj/STATIC_LIBRARIES/libasound_intermediates
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 154)
TARGET_LIBGCC := prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/libgcc.a
# makefile (from `build/core/clear_vars.mk', line 62)
LOCAL_DROIDDOC_CUSTOM_TEMPLATE_DIR := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/combo/select.mk', line 42)
TARGET_HAVE_STRNLEN := 1
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.inherited := 
# makefile (from `external/clearsilver/CleanSpec.mk', line 54)
INTERNAL_CLEAN_STEP.external_clearsilver_CleanSpec-mk_acs6@@@@@ := rm -rf out/host/linux-x86/obj/SHARED_LIBRARIES/libneo_cs_intermediates
# makefile (from `build/core/combo/select.mk', line 45)
TARGET_HAVE_STRLCAT := 0
# makefile (from `build/core/tasks/cts.mk', line 175)
define copy-testcase-apk

$(hide) $(ACP) -fp $(call intermediates-dir-for,APPS,$(1))/package.apk $(PRIVATE_DIR)/repository/testcases/$(1).apk

endef
# makefile (from `build/core/clear_vars.mk', line 60)
LOCAL_DROIDDOC_SOURCE_PATH := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/base_rules.mk', line 334)
java_resource_sources := 
# makefile (from `build/core/envsetup.mk', line 232)
TARGET_OUT_OPTIONAL_EXECUTABLES := out/target/product/smdkv210/system/xbin
# makefile (from `device/samsung/smdkc110/CleanSpec.mk', line 54)
INTERNAL_CLEAN_STEP.device_samsung_smdkc110_CleanSpec-mk_acs6@@@@@ := rm -rf out/target/product/smdkc110/obj/STATIC_LIBRARIES/libasound_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_MODEL := 
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 201)
TARGET_C_INCLUDES := bionic/libc/arch-arm/include bionic/libc/include bionic/libstdc++/include bionic/libc/kernel/common bionic/libc/kernel/arch-arm bionic/libm/include bionic/libm/include/arch/arm bionic/libthread_db/include
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.PRODUCT_COPY_FILES := 
# makefile (from `external/webkit/CleanSpec.mk', line 55)
INTERNAL_CLEAN_STEP.external_webkit_CleanSpec-mk_acs6@@@@@@@@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libwebcore_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_TAGS := 
# makefile (from `device/samsung/smdkc110/CleanSpec.mk', line 53)
INTERNAL_CLEAN_STEP.device_samsung_smdkc110_CleanSpec-mk_acs6@@@@ := rm -rf out/target/product/smdkc110/obj/SHARED_LIBRARIES/libaudio_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/binary.mk', line 475)
installed_static_library_notice_file_targets := 
# makefile (from `build/core/envsetup.mk', line 220)
HOST_OUT_INTERMEDIATE_LIBRARIES := out/host/linux-x86/obj/lib
# makefile (from `build/core/config.mk', line 101)
TARGET_ERROR_FLAGS := -Werror=return-type -Werror=non-virtual-dtor -Werror=address -Werror=sequence-point
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 56)
TARGET_NO_UNDEFINED_LDFLAGS := -Wl,--no-undefined
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/definitions.mk', line 1166)
transform-host-o-to-executable-inner = $(HOST_CXX) -Wl,-rpath-link=$(TARGET_OUT_INTERMEDIATE_LIBRARIES) -Wl,-rpath,\$$ORIGIN/../lib $(HOST_GLOBAL_LD_DIRS) $(PRIVATE_LDFLAGS) $(if $(PRIVATE_NO_DEFAULT_COMPILER_FLAGS),, $(HOST_GLOBAL_LDFLAGS) ) $(PRIVATE_ALL_OBJECTS) -Wl,--whole-archive $(call normalize-host-libraries,$(PRIVATE_ALL_WHOLE_STATIC_LIBRARIES)) -Wl,--no-whole-archive $(call normalize-host-libraries,$(PRIVATE_ALL_STATIC_LIBRARIES)) $(call normalize-host-libraries,$(PRIVATE_ALL_SHARED_LIBRARIES)) -o $@ $(PRIVATE_LDLIBS)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.inherited := 
# makefile (from `build/core/base_rules.mk', line 534)
ALL_MODULES.tvrecv.INSTALLED :=  out/target/product/smdkv210/system/bin/tvrecv
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/htc/passion/full_passion.mk.PRODUCT_PROPERTY_OVERRIDES := ro.sf.lcd_density=240 rild.libpath=/system/lib/libhtc_ril.so wifi.interface=eth0 wifi.supplicant_scan_interval=15 ro.telephony.default_network=0 ro.opengles.version=131072 dalvik.vm.heapsize=32m media.a1026.nsForVoiceRec = 0 media.a1026.enableA1026 = 1 keyguard.no_require_sim=true ro.com.android.dateformat=MM-dd-yyyy ro.com.android.dataroaming=true ro.ril.hsxpa=1 ro.ril.gprsclass=10 ro.config.notification_sound=OnTheHunt.ogg ro.config.alarm_alert=Alarm_Classic.ogg
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sim.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/cleanbuild.mk', line 154)
previous_build_config_file := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/main.mk', line 585)
tests_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_TAGS := 
# makefile (from `dalvik/CleanSpec.mk', line 47)
INTERNAL_CLEAN_STEP.dalvik_CleanSpec-mk_acs6@ := rm -rf /home/lnt/stb/out/target/product/smdkv210/obj/SHARED_LIBRARIES/libdvm*
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.inherited := 
# makefile (from `build/core/clear_vars.mk', line 100)
LOCAL_BUILD_HOST_DEX := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/combo/javac.mk', line 34)
TARGET_JAVAC = $(COMMON_JAVAC)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic.mk.PRODUCT_DEVICE := generic
# makefile (from `build/core/tasks/cts.mk', line 15)
cts_dir := out/host/linux-x86/cts
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.PRODUCT_COPY_FILES := 
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 89)
BOARD_USE_SAMSUNG_COLORFORMAT := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/envsetup.mk', line 227)
TARGET_OUT_INTERMEDIATE_LIBRARIES := out/target/product/smdkv210/obj/lib
# makefile (from `build/core/definitions.mk', line 628)
get-package-overrides = $(strip $(sort $(call _get-package-overrides,$(1))))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/envsetup.mk', line 209)
OUT_DOCS := out/target/common/docs
# makefile (from `build/core/envsetup.mk', line 261)
TARGET_ROOT_OUT_SBIN := out/target/product/smdkv210/root/sbin
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.PRODUCT_DEVICE := generic
# makefile (from `build/core/binary.mk', line 229)
gen_S_sources := 
# makefile (from `build/core/definitions.mk', line 210)
all-Iaidl-files-under = $(patsubst ./%,%, $(shell cd $(LOCAL_PATH) ; find $(1) -name "I*.aidl" -and -not -name ".*") )
# makefile (from `build/core/Makefile', line 1253)
sdk_name := android-sdk_eng.lnt_linux-x86
# default
TEX = tex
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/config.mk', line 198)
AAPT := out/host/linux-x86/bin/aapt
# makefile (from `build/CleanSpec.mk', line 52)
INTERNAL_CLEAN_STEP.build_CleanSpec-mk_acs6@@@@@@ := rm -rf out/target/product/smdkv210/android-info.txt
# makefile (from `build/core/definitions.mk', line 906)
define transform-host-s-to-o-no-deps
@echo "host asm: $(PRIVATE_MODULE) <= $<"
$(call transform-host-c-or-s-to-o-no-deps, $(PRIVATE_ASFLAGS))
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/samsung/product/full_smdkv210.mk.PRODUCT_CHARACTERISTICS := nosdcard
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.PRODUCT_CONTRIBUTORS_FILE := 
# automatic
^D = $(patsubst %/,%,$(dir $^))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `dalvik/CleanSpec.mk', line 48)
INTERNAL_CLEAN_STEP.dalvik_CleanSpec-mk_acs6@@ := rm -rf /home/lnt/stb/out/target/product/smdkv210/obj/SHARED_LIBRARIES/libdvm*
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 51)
TARGET_CXX := prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/arm-eabi-g++
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/envsetup.mk', line 151)
TARGET_ARCH := arm
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/samsung/product/full_smdkv210.mk.PRODUCT_NAME := full_smdkv210
# makefile (from `build/core/combo/arch/arm/armv7-a-neon.mk', line 10)
ARCH_ARM_HAVE_ARMV7A := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/combo/arch/arm/armv7-a-neon.mk', line 8)
ARCH_ARM_HAVE_CLZ := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/main.mk', line 521)
known_custom_modules := 
# makefile (from `build/core/definitions.mk', line 1325)
define transform-classes.jar-to-dex
@echo "target Dex: $(PRIVATE_MODULE)"
@mkdir -p $(dir $@)
$(hide) $(DX) $(if $(findstring windows,$(HOST_OS)),,-JXms16M -JXmx1536M) --dex --output=$@ $(if $(NO_OPTIMIZE_DX), --no-optimize) $(if $(GENERATE_DEX_DEBUG), --debug --verbose --dump-to=$(@:.dex=.lst) --dump-width=1000) $(PRIVATE_DX_FLAGS) $<
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_DEVICE := 
# environment
SHLVL = 1
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/htc/passion/full_passion.mk.PRODUCT_LOCALES := en_US en_GB fr_FR it_IT es_ES es_US de_DE nl_NL cs_CZ pl_PL zh_TW zh_CN ru_RU ko_KR nb_NO pt_PT pt_BR da_DK el_GR sv_SE tr_TR ja_JP hdpi en_US en_GB fr_FR it_IT de_DE es_ES
# makefile (from `build/core/clear_vars.mk', line 51)
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# environment
ANDROID_QTOOLS = /home/lnt/stb/development/emulator/qtools
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/Makefile', line 1262)
ATREE_FILES :=     out/target/product/smdkv210/system/bin/tvrecv out/target/product/smdkv210/root/init.rc out/target/product/smdkv210/system/etc/vold.fstab out/target/product/smdkv210/system/lib/egl/egl.cfg out/target/product/smdkv210/root/init.smdkv210.rc out/target/product/smdkv210/root/ueventd.smdkv210.rc out/target/product/smdkv210/system/usr/keylayout/s3c-keypad.kl out/target/product/smdkv210/system/etc/media_profiles.xml out/target/product/smdkv210/system/etc/secomxregistry out/target/product/smdkv210/system/lib/libSEC_OMX_Core.so out/target/product/smdkv210/system/lib/libSEC_Resourcemanager.so out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M2V.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.WMV.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Encoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Encoder.so out/target/product/smdkv210/system/lib/libstagefright_amrnb_common.so out/target/product/smdkv210/system/lib/libstagefright_enc_common.so out/target/product/smdkv210/system/lib/libstagefright_avc_common.so out/target/product/smdkv210/system/lib/libstagefright_foundation.so out/target/product/smdkv210/system/lib/libstagefright_color_conversion.so out/target/product/smdkv210/system/lib/libstagefright.so out/target/product/smdkv210/system/lib/libstagefright_omx.so out/target/product/smdkv210/system/lib/liba52.so out/target/product/smdkv210/system/bin/playts out/target/product/smdkv210/system/lib/libtv.so out/target/product/smdkv210/system/bin/remount_ctl out/target/product/smdkv210/system/media/bootanimation.zip out/target/product/smdkv210/system/app/PrimaryApp.apk out/target/product/smdkv210/system/app/HomePage.apk out/target/product/smdkv210/system/app/FileManagers.apk out/target/product/smdkv210/system/app/Game.apk out/target/product/smdkv210/system/lib/libmoddvb.so out/target/product/smdkv210/system/lib/libjnidvb.so out/target/product/smdkv210/system/lib/libdvbbinderclient.so out/target/product/smdkv210/system/lib/libdvbcomm.so out/target/product/smdkv210/system/lib/modules/utiusb.ko out/target/product/smdkv210/system/bin/dvb_load out/target/product/smdkv210/system/bin/busybox out/target/product/smdkv210/system/bin/bash out/target/product/smdkv210/system/bin/dvb_server out/target/product/smdkv210/system/etc/wifi/wpa_supplicant.conf out/target/product/smdkv210/system/etc/dhd.ko out/target/product/smdkv210/system/etc/fw_bcm4329.bin out/target/product/smdkv210/system/etc/nvram.txt out/target/product/smdkv210/system/etc/permissions/handheld_core_hardware.xml out/target/product/smdkv210/system/etc/permissions/android.software.live_wallpaper.xml out/target/product/smdkv210/kernel out/target/product/smdkv210/system/etc/permissions/android.software.live_wallpaper.xml out/target/product/smdkv210/system/vendor/bin/pvrsrvinit out/target/product/smdkv210/system/vendor/lib/egl/libEGL_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/egl/libGLESv1_CM_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/egl/libGLESv2_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/libglslcompiler.so out/target/product/smdkv210/system/vendor/lib/libIMGegl.so out/target/product/smdkv210/system/vendor/lib/libpvr2d.so out/target/product/smdkv210/system/vendor/lib/libpvrANDROID_WSEGL.so out/target/product/smdkv210/system/vendor/lib/libPVRScopeServices.so out/target/product/smdkv210/system/vendor/lib/libsrv_init.so out/target/product/smdkv210/system/vendor/lib/libsrv_um.so out/target/product/smdkv210/system/vendor/lib/libusc.so out/target/product/smdkv210/system/vendor/lib/hw/gralloc.s5pc110.so out/target/product/smdkv210/system/etc/apns-conf.xml out/target/product/smdkv210/system/etc/vold.conf out/target/product/smdkv210/system/media/audio/notifications/F1_MissedCall.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_New_MMS.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_New_SMS.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Buzzer.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_01.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_02.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Classic.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_03.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Rooster_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Classic_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Digital_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_04.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/BeatPlucker.ogg out/target/product/smdkv210/system/media/audio/ringtones/BentleyDubs.ogg out/target/product/smdkv210/system/media/audio/ringtones/BirdLoop.ogg out/target/product/smdkv210/system/media/audio/ringtones/CaribbeanIce.ogg out/target/product/smdkv210/system/media/audio/ringtones/CurveBall.ogg out/target/product/smdkv210/system/media/audio/ringtones/EtherShake.ogg out/target/product/smdkv210/system/media/audio/ringtones/FriendlyGhost.ogg out/target/product/smdkv210/system/media/audio/ringtones/GameOverGuitar.ogg out/target/product/smdkv210/system/media/audio/ringtones/Growl.ogg out/target/product/smdkv210/system/media/audio/ringtones/InsertCoin.ogg out/target/product/smdkv210/system/media/audio/ringtones/LoopyLounge.ogg out/target/product/smdkv210/system/media/audio/ringtones/LoveFlute.ogg out/target/product/smdkv210/system/media/audio/ringtones/MidEvilJaunt.ogg out/target/product/smdkv210/system/media/audio/ringtones/MildlyAlarming.ogg out/target/product/smdkv210/system/media/audio/ringtones/NewPlayer.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises1.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises2.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises3.ogg out/target/product/smdkv210/system/media/audio/ringtones/OrganDub.ogg out/target/product/smdkv210/system/media/audio/ringtones/RomancingTheTone.ogg out/target/product/smdkv210/system/media/audio/ringtones/SitarVsSitar.ogg out/target/product/smdkv210/system/media/audio/ringtones/SpringyJalopy.ogg out/target/product/smdkv210/system/media/audio/ringtones/Terminated.ogg out/target/product/smdkv210/system/media/audio/ringtones/TwirlAway.ogg out/target/product/smdkv210/system/media/audio/ringtones/VeryAlarmed.ogg out/target/product/smdkv210/system/media/audio/ringtones/World.ogg out/target/product/smdkv210/system/media/audio/notifications/CaffeineSnake.ogg out/target/product/smdkv210/system/media/audio/notifications/DearDeer.ogg out/target/product/smdkv210/system/media/audio/notifications/DontPanic.ogg out/target/product/smdkv210/system/media/audio/notifications/Highwire.ogg out/target/product/smdkv210/system/media/audio/notifications/KzurbSonar.ogg out/target/product/smdkv210/system/media/audio/notifications/OnTheHunt.ogg out/target/product/smdkv210/system/media/audio/notifications/Voila.ogg out/target/product/smdkv210/system/media/audio/notifications/Beat_Box_Android.ogg out/target/product/smdkv210/system/media/audio/notifications/Heaven.ogg out/target/product/smdkv210/system/media/audio/notifications/TaDa.ogg out/target/product/smdkv210/system/media/audio/notifications/Tinkerbell.ogg out/target/product/smdkv210/system/media/audio/ui/Effect_Tick.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressStandard.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressSpacebar.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressDelete.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressReturn.ogg out/target/product/smdkv210/system/media/audio/ui/VideoRecord.ogg out/target/product/smdkv210/system/media/audio/ui/camera_click.ogg out/target/product/smdkv210/system/media/audio/ringtones/CrazyDream.ogg out/target/product/smdkv210/system/media/audio/ringtones/DreamTheme.ogg out/target/product/smdkv210/system/tts/lang_pico/de-DE_gl0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/de-DE_ta.bin out/target/product/smdkv210/system/tts/lang_pico/en-GB_kh0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/en-GB_ta.bin out/target/product/smdkv210/system/tts/lang_pico/en-US_lh0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/en-US_ta.bin out/target/product/smdkv210/system/tts/lang_pico/es-ES_zl0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/es-ES_ta.bin out/target/product/smdkv210/system/tts/lang_pico/fr-FR_nk0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/fr-FR_ta.bin out/target/product/smdkv210/system/tts/lang_pico/it-IT_cm0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/it-IT_ta.bin out/target/product/smdkv210/root/default.prop out/target/product/smdkv210/system/build.prop out/target/product/smdkv210/system/etc/event-log-tags out/target/product/smdkv210/system/etc/security/otacerts.zip out/target/product/smdkv210/ramdisk.img  out/target/common/docs/index.html 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/sample/products/sample_addon.mk.PRODUCT_SDK_ADDON_NAME := platform_library
# makefile (from `build/core/definitions.mk', line 188)
all-c-files-under = $(patsubst ./%,%, $(shell cd $(LOCAL_PATH) ; find $(1) -name "*.c" -and -not -name ".*") )
# makefile (from `build/core/product_config.mk', line 194)
PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/main.mk', line 555)
Default_MODULES := out/target/product/smdkv210/system/bin/tvrecv
# makefile (from `build/core/combo/select.mk', line 37)
HOST_HAVE_RTTI := 1
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/notice_files.mk', line 63)
installed_notice_file := 
# makefile (from `build/core/dynamic_binary.mk', line 135)
LOCAL_STRIP_MODULE := true
# makefile (from `build/core/tasks/apicheck.mk', line 44)
last_released_sdk_version := 9
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/definitions.mk', line 559)
modules-for-tag-list = $(sort $(foreach tag,$(1),$(ALL_MODULE_TAGS.$(tag))))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.PRODUCT_NAME := core
# makefile (from `build/core/clear_vars.mk', line 69)
LOCAL_RESOURCE_DIR := 
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 143)
libc_root := bionic/libc
# makefile (from `build/core/Makefile', line 38)
INSTALLED_DEFAULT_PROP_TARGET := out/target/product/smdkv210/root/default.prop
# makefile (from `build/core/clear_vars.mk', line 68)
LOCAL_ASSET_DIR := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/sample/products/sample_addon.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.PRODUCT_TAGS := 
# makefile (from `build/core/Makefile', line 76)
BUILD_FINGERPRINT := generic/full_smdkv210/smdkv210:2.3.1/GINGERBREAD/eng.lnt.20120301.192057:eng/test-keys
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/full.mk.PRODUCT_TAGS := 
# makefile (from `build/core/base_rules.mk', line 281)
LOCAL_JAVA_RESOURCE_DIRS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/clear_vars.mk', line 29)
LOCAL_STATIC_LIBRARIES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product.mk', line 187)
resolve-short-product-name = $(strip $(call _resolve-short-product-name,$(1)))
# makefile (from `build/core/envsetup.mk', line 192)
BUILD_OUT := out/host/linux-x86
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/dex_preopt.mk', line 10)
DEXPREOPT_BUILD_DIR := out
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `packages/inputmethods/LatinIME/CleanSpec.mk', line 50)
INTERNAL_CLEAN_STEP.packages_inputmethods_LatinIME_CleanSpec-mk_acs6@@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libjni_latinime_intermediates
# makefile (from `build/core/combo/select.mk', line 37)
TARGET_HAVE_RTTI := 1
# makefile (from `build/core/definitions.mk', line 397)
local-intermediates-dir = $(strip $(if $(strip $(LOCAL_MODULE_CLASS)),, $(error $(LOCAL_PATH): LOCAL_MODULE_CLASS not defined before call to local-intermediates-dir)) $(if $(strip $(LOCAL_MODULE)),, $(error $(LOCAL_PATH): LOCAL_MODULE not defined before call to local-intermediates-dir)) $(call intermediates-dir-for,$(LOCAL_MODULE_CLASS),$(LOCAL_MODULE),$(LOCAL_IS_HOST_MODULE),$(1)) )
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.PRODUCT_DEVICE := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.seen := true
# environment
GNOME_KEYRING_PID = 1351
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/envsetup.mk', line 280)
DIST_DIR := out/dist
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/binary.mk', line 455)
installed_shared_libraries := out/target/product/smdkv210/system/lib/libc.so out/target/product/smdkv210/system/lib/libstdc++.so out/target/product/smdkv210/system/lib/libm.so
# makefile (from `build/core/tasks/cts.mk', line 86)
DOM_INTERMEDIATES := out/target/common/obj/JAVA_LIBRARIES/core-tests-dom_intermediates
# makefile (from `build/core/config.mk', line 211)
MKEXT2BOOTIMG := external/genext2fs/mkbootimg_ext2.sh
# makefile (from `build/core/clear_vars.mk', line 75)
LOCAL_FORCE_STATIC_EXECUTABLE := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/core.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_NAME := 
# makefile (from `build/core/combo/select.mk', line 58)
TARGET_PRELINKER_MAP := build/core/prelink-linux-arm.map
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `device/samsung/product/full_smdkv210.mk', line 27)
PRODUCTS.device/samsung/product/full_smdkv210.mk.INHERITS_FROM := build/target/product/full.mk build/target/product/languages_full.mk device/samsung/smdkv210/device.mk
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.inherited := 
# makefile (from `build/core/cleanbuild.mk', line 126)
locale_list := 
# environment
ANDROID_TOOLCHAIN = /home/lnt/stb/prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin
# default
WEAVE = weave
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.PRODUCT_MODEL := 
# makefile (from `build/core/config.mk', line 164)
combo_target := TARGET_
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 46)
TARGET_SEC_INTERNAL_STORAGE := false
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/tasks/cts.mk', line 129)
JUNIT_INTERMEDIATES := out/target/common/obj/JAVA_LIBRARIES/core-junit_intermediates
# makefile (from `device/samsung/smdkv210/CleanSpec.mk', line 52)
INTERNAL_CLEAN_STEP.device_samsung_smdkv210_CleanSpec-mk_acs6@@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libaudio_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/combo/select.mk', line 35)
TARGET_HAVE_UNIX_FILE_PATH := 1
# makefile (from `build/core/config.mk', line 19)
PYTHONDONTWRITEBYTECODE := 1
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_NAME := 
# makefile (from `cts/CtsTestCaseList.mk', line 68)
CTS_TEST_CASE_LIST := TestDeviceSetup CtsDelegatingAccessibilityService SignatureTest ApiDemos ApiDemosReferenceTest CtsVerifier CtsAccessibilityServiceTestCases CtsAccountManagerTestCases CtsAppTestCases CtsBluetoothTestCases CtsContentTestCases CtsDatabaseTestCases CtsDpiTestCases CtsDpiTestCases2 CtsExampleTestCases CtsGestureTestCases CtsGraphicsTestCases CtsHardwareTestCases CtsJniTestCases CtsLocationTestCases CtsMediaTestCases CtsOsTestCases CtsPermissionTestCases CtsPermission2TestCases CtsPreferenceTestCases CtsProviderTestCases CtsSaxTestCases CtsSpeechTestCases CtsTelephonyTestCases CtsTestStubs CtsTextTestCases CtsUtilTestCases CtsViewTestCases CtsWebkitTestCases CtsWidgetTestCases CtsNetTestCases CtsPerformanceTestCases CtsPerformance2TestCases CtsPerformance3TestCases CtsPerformance4TestCases CtsPerformance5TestCases CtsAppAccessData CtsAppWithData CtsInstrumentationAppDiffCert CtsPermissionDeclareApp CtsSharedUidInstall CtsSharedUidInstallDiffCert CtsSimpleAppInstall CtsSimpleAppInstallDiffCert CtsTargetInstrumentationApp CtsUsePermissionDiffCert
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/core.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product.mk', line 103)
inherit-product =   $(foreach v,$(_product_var_list), $(eval $(v) := $($(v)) $(INHERIT_TAG)$(strip $(1)))) $(eval inherit_var := PRODUCTS.$(strip $(word 1,$(_include_stack))).INHERITS_FROM) $(eval $(inherit_var) := $(sort $($(inherit_var)) $(strip $(1)))) $(eval inherit_var:=) $(eval ALL_PRODUCTS := $(sort $(ALL_PRODUCTS) $(word 1,$(_include_stack))))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 70)
DEFAULT_FB_NUM := 0  
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/htc/passion/full_passion.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/combo/javac.mk', line 15)
COMMON_JAVAC := javac -J-Xmx512M -target 1.5 -Xmaxerrs 9999999
# makefile (from `build/core/Makefile', line 281)
event_log_tags_src := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/binary.mk', line 212)
gen_cpp_sources := 
# environment
MFLAGS = -wp
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `device/samsung/smdkv210/device.mk', line 50)
source_init_rc_file := device/samsung/smdkv210/init.rc
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/full.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `dalvik/CleanSpec.mk', line 51)
INTERNAL_CLEAN_STEP.dalvik_CleanSpec-mk_acs6@@@@@ := rm -rf /home/lnt/stb/out/target/product/smdkv210/obj/SHARED_LIBRARIES/libdvm*
# makefile (from `build/core/clear_vars.mk', line 55)
LOCAL_JAVACFLAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/Makefile', line 183)
PACKAGES_TO_STAT := out/target/product/smdkv210/system/app/FileManagers.apk out/target/product/smdkv210/system/app/Game.apk out/target/product/smdkv210/system/app/HomePage.apk out/target/product/smdkv210/system/app/PrimaryApp.apk
# makefile (from `build/core/combo/select.mk', line 34)
TARGET_HAVE_EXCEPTIONS := 0
# makefile (from `build/core/binary.mk', line 294)
objc_objects := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/binary.mk', line 257)
c_normal_objects := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/debug.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostAppInfoClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostCAClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDateTimeServerAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDeviceAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostEPGDBClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostPlatForm.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostRgnClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostSCClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostTunerClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostXDCServerAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/main.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketRecv.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketSend.o
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/full.mk.PRODUCT_COPY_FILES := development/data/etc/apns-conf.xml:system/etc/apns-conf.xml development/data/etc/vold.conf:system/etc/vold.conf frameworks/base/data/sounds/F1_MissedCall.ogg:system/media/audio/notifications/F1_MissedCall.ogg frameworks/base/data/sounds/F1_New_MMS.ogg:system/media/audio/notifications/F1_New_MMS.ogg frameworks/base/data/sounds/F1_New_SMS.ogg:system/media/audio/notifications/F1_New_SMS.ogg frameworks/base/data/sounds/Alarm_Buzzer.ogg:system/media/audio/alarms/Alarm_Buzzer.ogg frameworks/base/data/sounds/Alarm_Beep_01.ogg:system/media/audio/alarms/Alarm_Beep_01.ogg frameworks/base/data/sounds/Alarm_Beep_02.ogg:system/media/audio/alarms/Alarm_Beep_02.ogg frameworks/base/data/sounds/Alarm_Classic.ogg:system/media/audio/alarms/Alarm_Classic.ogg frameworks/base/data/sounds/Alarm_Beep_03.ogg:system/media/audio/alarms/Alarm_Beep_03.ogg frameworks/base/data/sounds/Alarm_Rooster_02.ogg:system/media/audio/alarms/Alarm_Rooster_02.ogg frameworks/base/data/sounds/Ring_Classic_02.ogg:system/media/audio/ringtones/Ring_Classic_02.ogg frameworks/base/data/sounds/Ring_Digital_02.ogg:system/media/audio/ringtones/Ring_Digital_02.ogg frameworks/base/data/sounds/Ring_Synth_04.ogg:system/media/audio/ringtones/Ring_Synth_04.ogg frameworks/base/data/sounds/Ring_Synth_02.ogg:system/media/audio/ringtones/Ring_Synth_02.ogg frameworks/base/data/sounds/newwavelabs/BeatPlucker.ogg:system/media/audio/ringtones/BeatPlucker.ogg frameworks/base/data/sounds/newwavelabs/BentleyDubs.ogg:system/media/audio/ringtones/BentleyDubs.ogg frameworks/base/data/sounds/newwavelabs/BirdLoop.ogg:system/media/audio/ringtones/BirdLoop.ogg frameworks/base/data/sounds/newwavelabs/CaribbeanIce.ogg:system/media/audio/ringtones/CaribbeanIce.ogg frameworks/base/data/sounds/newwavelabs/CurveBall.ogg:system/media/audio/ringtones/CurveBall.ogg frameworks/base/data/sounds/newwavelabs/EtherShake.ogg:system/media/audio/ringtones/EtherShake.ogg frameworks/base/data/sounds/newwavelabs/FriendlyGhost.ogg:system/media/audio/ringtones/FriendlyGhost.ogg frameworks/base/data/sounds/newwavelabs/GameOverGuitar.ogg:system/media/audio/ringtones/GameOverGuitar.ogg frameworks/base/data/sounds/newwavelabs/Growl.ogg:system/media/audio/ringtones/Growl.ogg frameworks/base/data/sounds/newwavelabs/InsertCoin.ogg:system/media/audio/ringtones/InsertCoin.ogg frameworks/base/data/sounds/newwavelabs/LoopyLounge.ogg:system/media/audio/ringtones/LoopyLounge.ogg frameworks/base/data/sounds/newwavelabs/LoveFlute.ogg:system/media/audio/ringtones/LoveFlute.ogg frameworks/base/data/sounds/newwavelabs/MidEvilJaunt.ogg:system/media/audio/ringtones/MidEvilJaunt.ogg frameworks/base/data/sounds/newwavelabs/MildlyAlarming.ogg:system/media/audio/ringtones/MildlyAlarming.ogg frameworks/base/data/sounds/newwavelabs/NewPlayer.ogg:system/media/audio/ringtones/NewPlayer.ogg frameworks/base/data/sounds/newwavelabs/Noises1.ogg:system/media/audio/ringtones/Noises1.ogg frameworks/base/data/sounds/newwavelabs/Noises2.ogg:system/media/audio/ringtones/Noises2.ogg frameworks/base/data/sounds/newwavelabs/Noises3.ogg:system/media/audio/ringtones/Noises3.ogg frameworks/base/data/sounds/newwavelabs/OrganDub.ogg:system/media/audio/ringtones/OrganDub.ogg frameworks/base/data/sounds/newwavelabs/RomancingTheTone.ogg:system/media/audio/ringtones/RomancingTheTone.ogg frameworks/base/data/sounds/newwavelabs/SitarVsSitar.ogg:system/media/audio/ringtones/SitarVsSitar.ogg frameworks/base/data/sounds/newwavelabs/SpringyJalopy.ogg:system/media/audio/ringtones/SpringyJalopy.ogg frameworks/base/data/sounds/newwavelabs/Terminated.ogg:system/media/audio/ringtones/Terminated.ogg frameworks/base/data/sounds/newwavelabs/TwirlAway.ogg:system/media/audio/ringtones/TwirlAway.ogg frameworks/base/data/sounds/newwavelabs/VeryAlarmed.ogg:system/media/audio/ringtones/VeryAlarmed.ogg frameworks/base/data/sounds/newwavelabs/World.ogg:system/media/audio/ringtones/World.ogg frameworks/base/data/sounds/newwavelabs/CaffeineSnake.ogg:system/media/audio/notifications/CaffeineSnake.ogg frameworks/base/data/sounds/newwavelabs/DearDeer.ogg:system/media/audio/notifications/DearDeer.ogg frameworks/base/data/sounds/newwavelabs/DontPanic.ogg:system/media/audio/notifications/DontPanic.ogg frameworks/base/data/sounds/newwavelabs/Highwire.ogg:system/media/audio/notifications/Highwire.ogg frameworks/base/data/sounds/newwavelabs/KzurbSonar.ogg:system/media/audio/notifications/KzurbSonar.ogg frameworks/base/data/sounds/newwavelabs/OnTheHunt.ogg:system/media/audio/notifications/OnTheHunt.ogg frameworks/base/data/sounds/newwavelabs/Voila.ogg:system/media/audio/notifications/Voila.ogg frameworks/base/data/sounds/notifications/Beat_Box_Android.ogg:system/media/audio/notifications/Beat_Box_Android.ogg frameworks/base/data/sounds/notifications/Heaven.ogg:system/media/audio/notifications/Heaven.ogg frameworks/base/data/sounds/notifications/TaDa.ogg:system/media/audio/notifications/TaDa.ogg frameworks/base/data/sounds/notifications/Tinkerbell.ogg:system/media/audio/notifications/Tinkerbell.ogg frameworks/base/data/sounds/effects/Effect_Tick.ogg:system/media/audio/ui/Effect_Tick.ogg frameworks/base/data/sounds/effects/KeypressStandard.ogg:system/media/audio/ui/KeypressStandard.ogg frameworks/base/data/sounds/effects/KeypressSpacebar.ogg:system/media/audio/ui/KeypressSpacebar.ogg frameworks/base/data/sounds/effects/KeypressDelete.ogg:system/media/audio/ui/KeypressDelete.ogg frameworks/base/data/sounds/effects/KeypressReturn.ogg:system/media/audio/ui/KeypressReturn.ogg frameworks/base/data/sounds/effects/VideoRecord.ogg:system/media/audio/ui/VideoRecord.ogg frameworks/base/data/sounds/effects/camera_click.ogg:system/media/audio/ui/camera_click.ogg frameworks/base/data/sounds/newwavelabs/CrazyDream.ogg:system/media/audio/ringtones/CrazyDream.ogg frameworks/base/data/sounds/newwavelabs/DreamTheme.ogg:system/media/audio/ringtones/DreamTheme.ogg external/svox/pico/lang/de-DE_gl0_sg.bin:system/tts/lang_pico/de-DE_gl0_sg.bin external/svox/pico/lang/de-DE_ta.bin:system/tts/lang_pico/de-DE_ta.bin external/svox/pico/lang/en-GB_kh0_sg.bin:system/tts/lang_pico/en-GB_kh0_sg.bin external/svox/pico/lang/en-GB_ta.bin:system/tts/lang_pico/en-GB_ta.bin external/svox/pico/lang/en-US_lh0_sg.bin:system/tts/lang_pico/en-US_lh0_sg.bin external/svox/pico/lang/en-US_ta.bin:system/tts/lang_pico/en-US_ta.bin external/svox/pico/lang/es-ES_zl0_sg.bin:system/tts/lang_pico/es-ES_zl0_sg.bin external/svox/pico/lang/es-ES_ta.bin:system/tts/lang_pico/es-ES_ta.bin external/svox/pico/lang/fr-FR_nk0_sg.bin:system/tts/lang_pico/fr-FR_nk0_sg.bin external/svox/pico/lang/fr-FR_ta.bin:system/tts/lang_pico/fr-FR_ta.bin external/svox/pico/lang/it-IT_cm0_sg.bin:system/tts/lang_pico/it-IT_cm0_sg.bin external/svox/pico/lang/it-IT_ta.bin:system/tts/lang_pico/it-IT_ta.bin
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/base_rules.mk', line 543)
INSTALLABLE_FILES.out/target/product/smdkv210/system/bin/tvrecv.MODULE := tvrecv
# makefile (from `build/core/definitions.mk', line 1618)
define proguard-enabled-commands
@echo Proguard: $@
$(hide) $(PROGUARD) -injars $< -outjars $@ $(PRIVATE_PROGUARD_FLAGS) $(1)
endef
# makefile (from `build/core/main.mk', line 464)
subdirs := .
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.PRODUCT_NAME := 
# makefile (from `build/core/tasks/cts.mk', line 24)
CTS_EXECUTABLE_PATH := cts/tools/utils/startcts
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sdk.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.inherited := 
# default
AS = as
# makefile (from `build/core/Makefile', line 837)
userdata_tar := out/target/product/smdkv210/userdata.tar
# makefile (from `build/core/clear_vars.mk', line 59)
LOCAL_DROIDDOC_USE_STANDARD_DOCLET := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/Makefile', line 7)
FILE_NAME_TAG := eng.lnt
# makefile (from `build/core/definitions.mk', line 1600)
transform-ranlib-copy-hack = true
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/full.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/Makefile', line 1272)
atree_dir := development/build
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_NAME := 
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 145)
libstdc++_root := bionic/libstdc++
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 37)
TARGET_ARCH_SPECIFIC_MAKEFILE := build/core/combo/arch/arm/armv7-a-neon.mk
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/htc/passion/full_passion.mk.PRODUCT_NAME := full_passion
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/envsetup.mk', line 190)
HOST_OUT := out/host/linux-x86
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.PRODUCT_DEVICE := generic
# makefile (from `build/core/main.mk', line 155)
INTERNAL_MODIFIER_TARGETS := showcommands checkbuild
# makefile (from `build/core/config.mk', line 154)
select-android-config-h = system/core/include/arch/$(1)/AndroidConfig.h
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/definitions.mk', line 879)
define transform-host-c-or-s-to-o-no-deps
@mkdir -p $(dir $@)
$(hide) $(PRIVATE_CC) $(foreach incdir, $(PRIVATE_C_INCLUDES) $(if $(PRIVATE_NO_DEFAULT_COMPILER_FLAGS),, $(HOST_PROJECT_INCLUDES) $(HOST_C_INCLUDES) ) , -I $(incdir) ) -c $(if $(PRIVATE_NO_DEFAULT_COMPILER_FLAGS),, $(HOST_GLOBAL_CFLAGS) ) $(PRIVATE_CFLAGS) $(1) $(PRIVATE_DEBUG_CFLAGS) -MD -o $@ $<
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/config.mk', line 228)
FINDBUGS := prebuilt/common/findbugs/bin/findbugs
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/binary.mk', line 450)
built_shared_libraries := out/target/product/smdkv210/obj/lib/libc.so out/target/product/smdkv210/obj/lib/libstdc++.so out/target/product/smdkv210/obj/lib/libm.so
# makefile (from `build/core/definitions.mk', line 412)
normalize-libraries = $(foreach so,$(filter %.so,$(1)),-l$(patsubst lib%.so,%,$(notdir $(so)))) $(filter-out %.so,$(1))
# makefile (from `build/core/base_rules.mk', line 205)
LOCAL_INTERMEDIATE_TARGETS := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/LINKED/tvrecv out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/tvrecv
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sdk.mk.PRODUCT_PACKAGE_OVERLAYS := development/sdk_overlay
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic_x86.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 203)
INTERNAL_PRODUCT := device/samsung/product/full_smdkv210.mk
# makefile (from `build/core/definitions.mk', line 508)
java-lib-deps = $(foreach lib,$(1),$(call _java-lib-full-dep,$(lib),$(2)))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/definitions.mk', line 329)
add-dependency = $(1): $(2)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.PRODUCT_PACKAGE_OVERLAYS := 
# automatic
^F = $(notdir $^)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.PRODUCT_NAME := core
# makefile (from `build/core/Makefile', line 1296)
INTERNAL_SDK_TARGET := out/host/linux-x86/sdk/android-sdk_eng.lnt_linux-x86.zip
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/full.mk.PRODUCT_NAME := full
# makefile (from `build/core/Makefile', line 487)
tools_notice_file_html := out/host/linux-x86/obj/NOTICE.html
# makefile (from `build/core/main.mk', line 595)
_gpo_names := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/clear_vars.mk', line 95)
LOCAL_PROGUARD_ENABLED := 
# makefile (from `build/core/envsetup.mk', line 230)
TARGET_OUT := out/target/product/smdkv210/system
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/config.mk', line 293)
HOST_GLOBAL_LD_DIRS = -L$(HOST_OUT_INTERMEDIATE_LIBRARIES)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/binary.mk', line 61)
my_target_c_inclues := bionic/libc/arch-arm/include bionic/libc/include bionic/libstdc++/include bionic/libc/kernel/common bionic/libc/kernel/arch-arm bionic/libm/include bionic/libm/include/arch/arm bionic/libthread_db/include
# makefile (from `build/core/clear_vars.mk', line 72)
LOCAL_GENERATED_SOURCES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic.mk.PRODUCT_BRAND := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.PRODUCT_NAME := core
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/config.mk', line 235)
SOSLIM := out/host/linux-x86/bin/soslim
# makefile (from `build/core/combo/select.mk', line 42)
HOST_HAVE_STRNLEN := 1
# makefile (from `build/core/binary.mk', line 188)
cpp_arm_objects := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sdk.mk.PRODUCT_NAME := sdk
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/Makefile', line 182)
PACKAGE_STATS_FILE := out/target/product/smdkv210/package-stats.txt
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/dynamic_binary.mk', line 39)
guessed_intermediates := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates
# makefile (from `build/core/cleanbuild.mk', line 195)
dataclean_files := ./out/target/product/smdkv210/data/* ./out/target/product/smdkv210/data-qemu/* ./out/target/product/smdkv210/userdata-qemu.img
# environment
ANDROID_BUILD_TOP = /home/lnt/stb
# makefile (from `build/core/tasks/cts.mk', line 42)
CTS_CASE_LIST := android.core.tests.dom android.core.tests.luni.io android.core.tests.luni.lang android.core.tests.luni.net android.core.tests.luni.util android.core.tests.xml android.core.tests.runner TestDeviceSetup CtsDelegatingAccessibilityService SignatureTest ApiDemos ApiDemosReferenceTest CtsVerifier CtsAccessibilityServiceTestCases CtsAccountManagerTestCases CtsAppTestCases CtsBluetoothTestCases CtsContentTestCases CtsDatabaseTestCases CtsDpiTestCases CtsDpiTestCases2 CtsExampleTestCases CtsGestureTestCases CtsGraphicsTestCases CtsHardwareTestCases CtsJniTestCases CtsLocationTestCases CtsMediaTestCases CtsOsTestCases CtsPermissionTestCases CtsPermission2TestCases CtsPreferenceTestCases CtsProviderTestCases CtsSaxTestCases CtsSpeechTestCases CtsTelephonyTestCases CtsTestStubs CtsTextTestCases CtsUtilTestCases CtsViewTestCases CtsWebkitTestCases CtsWidgetTestCases CtsNetTestCases CtsPerformanceTestCases CtsPerformance2TestCases CtsPerformance3TestCases CtsPerformance4TestCases CtsPerformance5TestCases CtsAppAccessData CtsAppWithData CtsInstrumentationAppDiffCert CtsPermissionDeclareApp CtsSharedUidInstall CtsSharedUidInstallDiffCert CtsSimpleAppInstall CtsSimpleAppInstallDiffCert CtsTargetInstrumentationApp CtsUsePermissionDiffCert
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/definitions.mk', line 785)
define transform-c-or-s-to-o-no-deps
@mkdir -p $(dir $@)
$(hide) $(PRIVATE_CC) $(foreach incdir, $(PRIVATE_C_INCLUDES) $(if $(PRIVATE_NO_DEFAULT_COMPILER_FLAGS),, $(PRIVATE_TARGET_PROJECT_INCLUDES) $(PRIVATE_TARGET_C_INCLUDES) ) , -I $(incdir) ) -c $(if $(PRIVATE_NO_DEFAULT_COMPILER_FLAGS),, $(PRIVATE_TARGET_GLOBAL_CFLAGS) $(PRIVATE_ARM_CFLAGS) ) $(PRIVATE_CFLAGS) $(1) $(PRIVATE_DEBUG_CFLAGS) -MD -o $@ $<
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/main.mk', line 50)
DEFAULT_GOAL := droid
# makefile (from `build/core/clear_vars.mk', line 91)
LOCAL_SDK_VERSION := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.PRODUCT_NAME := core
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/Makefile', line 1274)
sdk_atree_files := development/build/sdk.exclude.atree development/build/sdk.atree development/build/sdk-linux-x86.atree sdk/build/tools.atree
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic_x86.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/clear_vars.mk', line 84)
LOCAL_MANIFEST_INSTRUMENTATION_FOR := 
# makefile (from `build/core/node_fns.mk', line 223)
_import-nodes-inner =   $(foreach _in,$(2), $(if $(wildcard $(_in)), $(if $($(1).$(_in).seen), $(eval ### "skipping already-imported $(_in)") , $(eval $(1).$(_in).seen := true) $(call _import-node,$(1),$(strip $(_in)),$(3)) ) , $(error $(1): "$(_in)" does not exist) ) )
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.PRODUCT_NAME := core
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `device/samsung/smdkv210/CleanSpec.mk', line 58)
INTERNAL_CLEAN_STEP.device_samsung_smdkv210_CleanSpec-mk_acs6@@@@@@@@@ := rm -rf out/target/product/smdkv210/system/usr/share/alsa
# makefile (from `build/core/config.mk', line 98)
COMMON_ANDROID_PACKAGE_SUFFIX := .apk
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/sample/products/sample_addon.mk.PRODUCT_PROPERTY_OVERRIDES := ro.config.notification_sound=OnTheHunt.ogg ro.config.alarm_alert=Alarm_Classic.ogg
# makefile (from `build/core/combo/select.mk', line 43)
HOST_HAVE_STRERROR_R_STRRET := 1
# default
.INCLUDE_DIRS = /usr/include /usr/local/include /usr/include
# makefile (from `build/core/combo/select.mk', line 24)
combo_os_arch := linux-arm
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.PRODUCT_NAME := full
# makefile (from `build/core/definitions.mk', line 178)
all-subdir-java-files = $(call all-java-files-under,.)
# makefile (from `build/core/config.mk', line 67)
BUILD_JAVA_LIBRARY := build/core/java_library.mk
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.seen := true
# default
COMPILE.r = $(FC) $(FFLAGS) $(RFLAGS) $(TARGET_ARCH) -c
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.PRODUCT_BRAND := 
# environment
MAKELEVEL := 0
# makefile (from `build/core/definitions.mk', line 1437)
define transform-host-java-to-package
@echo "host Java: $(PRIVATE_MODULE) ($(PRIVATE_CLASS_INTERMEDIATES_DIR))"
@rm -f $@
@rm -rf $(PRIVATE_CLASS_INTERMEDIATES_DIR)
@mkdir -p $(dir $@)
@mkdir -p $(PRIVATE_CLASS_INTERMEDIATES_DIR)
$(call unzip-jar-files,$(PRIVATE_STATIC_JAVA_LIBRARIES), $(PRIVATE_CLASS_INTERMEDIATES_DIR))
$(call dump-words-to-file,$(sort $(PRIVATE_JAVA_SOURCES)), $(PRIVATE_INTERMEDIATES_DIR)/java-source-list-uniq)
$(hide) $(HOST_JAVAC) -encoding ascii -g $(PRIVATE_JAVACFLAGS) $(xlint_unchecked) $(addprefix -classpath ,$(strip $(call normalize-path-list,$(PRIVATE_ALL_JAVA_LIBRARIES)))) -extdirs "" -d $(PRIVATE_CLASS_INTERMEDIATES_DIR) \@$(PRIVATE_INTERMEDIATES_DIR)/java-source-list-uniq || ( rm -rf $(PRIVATE_CLASS_INTERMEDIATES_DIR) ; exit 41 )
$(hide) rm -f $(PRIVATE_INTERMEDIATES_DIR)/java-source-list
$(hide) rm -f $(PRIVATE_INTERMEDIATES_DIR)/java-source-list-uniq
$(hide) jar $(if $(strip $(PRIVATE_JAR_MANIFEST)),-cfm,-cf) $@ $(PRIVATE_JAR_MANIFEST) $(PRIVATE_EXTRA_JAR_ARGS) -C $(PRIVATE_CLASS_INTERMEDIATES_DIR) .
endef
# makefile (from `build/core/definitions.mk', line 502)
_java-lib-full-dep = $(call _java-lib-dir,$(1),$(2))/javalib$(COMMON_JAVA_PACKAGE_SUFFIX)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_BRAND := 
# makefile (from `build/core/definitions.mk', line 418)
normalize-host-libraries = $(call normalize-libraries,$(1))
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/sample/products/sample_addon.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/binary.mk', line 191)
cpp_normal_objects := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/config.mk', line 234)
LSD := out/host/linux-x86/bin/lsd
# makefile (from `build/core/binary.mk', line 429)
a_suffix := .a
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sim.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/combo/select.mk', line 46)
HOST_HAVE_KERNEL_MODULES := 0
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 215)
TARGET_CRTEND_SO_O := out/target/product/smdkv210/obj/lib/crtend_so.o
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_DEVICE := 
# environment
LANG = en_HK.utf8
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/definitions.mk', line 73)
ALL_SDK_FILES := 
# default
LINK.o = $(CC) $(LDFLAGS) $(TARGET_ARCH)
# makefile (from `build/core/Makefile', line 95)
BUILD_DISPLAY_ID := full_smdkv210-eng 2.3.1 GINGERBREAD eng.lnt.20120301.192057 test-keys
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sim.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/Makefile', line 31)
ALL_DOCS :=  out/target/common/docs/index.html
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/clear_vars.mk', line 101)
LOCAL_DEX_PREOPT := 
# makefile (from `build/core/combo/select.mk', line 53)
TARGET_EXECUTABLE_SUFFIX := 
# makefile (from `build/core/definitions.mk', line 76)
INTERNAL_DALVIK_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/core.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/config.mk', line 218)
DEXOPT := out/host/linux-x86/bin/dexopt
# makefile (from `build/core/combo/select.mk', line 28)
HOST_CC := cc
# makefile (from `build/core/config.mk', line 208)
MKEXT2IMG := out/host/linux-x86/bin/genext2fs
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/combo/select.mk', line 53)
HOST_EXECUTABLE_SUFFIX := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.PRODUCT_BRAND := generic
# makefile (from `build/core/dynamic_binary.mk', line 47)
ALL_ORIGINAL_DYNAMIC_BINARIES :=  out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/LINKED/tvrecv
# makefile (from `build/core/node_fns.mk', line 111)
INHERIT_TAG := @inherit:
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 34)
BOARD_HAVE_BLUETOOTH_BCM := false
# makefile (from `build/core/combo/select.mk', line 46)
TARGET_HAVE_KERNEL_MODULES := 0
# makefile (from `build/core/definitions.mk', line 592)
append-path = $(subst //,/,$(1)/$(2))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_NAME := 
# makefile (from `build/core/definitions.mk', line 1562)
define transform-prebuilt-to-target
@echo "$(if $(PRIVATE_IS_HOST_MODULE),host,target) Prebuilt: $(PRIVATE_MODULE) ($@)"
$(copy-file-to-target)
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/Makefile', line 838)
INSTALLED_USERDATATARBALL_TARGET := out/target/product/smdkv210/userdata.tar.bz2
# makefile (from `build/core/main.mk', line 207)
enable_target_debugging := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# environment
ANDROID_EABI_TOOLCHAIN = /home/lnt/stb/prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.inherited := 
# makefile (from `build/core/envsetup.mk', line 274)
TARGET_INSTALLER_ROOT_OUT := out/target/product/smdkv210/installer/root
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic_x86.mk.PRODUCT_PROPERTY_OVERRIDES := ro.config.notification_sound=OnTheHunt.ogg ro.config.alarm_alert=Alarm_Classic.ogg
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/htc/passion/full_passion.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `dalvik/CleanSpec.mk', line 49)
INTERNAL_CLEAN_STEP.dalvik_CleanSpec-mk_acs6@@@ := rm -rf /home/lnt/stb/out/target/product/smdkv210/obj/SHARED_LIBRARIES/libdvm*
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_MODEL := 
# default
CWEAVE = cweave
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/samsung/product/full_smdkv210.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 173)
TARGET_FDO_PROFILE_PATH := fdo/profiles/arm/armv7-a-neon
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/definitions.mk', line 1135)
define transform-o-to-executable
@mkdir -p $(dir $@)
@echo "target Executable: $(PRIVATE_MODULE) ($@)"
$(hide) $(transform-o-to-executable-inner)
endef
# makefile (from `build/core/main.mk', line 192)
is_sdk_build := 
# makefile (from `build/core/envsetup.mk', line 272)
TARGET_INSTALLER_OUT := out/target/product/smdkv210/installer
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/main.mk', line 761)
ALL_SDK_TARGETS := out/host/linux-x86/sdk/android-sdk_eng.lnt_linux-x86.zip
# makefile (from `build/core/definitions.mk', line 302)
find-other-html-files = 	$(call find-subdir-files,$(1) -name "*.html" -and -not -name ".*")
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/tasks/cts.mk', line 131)
_idfClass := JAVA_LIBRARIES
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.PRODUCT_DEVICE := 
# makefile
CURDIR := /home/lnt/stb
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sim.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 196)
_cap_names :=  core full generic generic_x86 sdk sim full_passion sample_addon full_smdkv210
# makefile (from `build/core/main.mk', line 595)
overridden_packages := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_NAME := 
# makefile (from `build/core/Makefile', line 829)
define build-userdatatarball-target
    $(call pretty,"Target userdata fs tarball: " "$(INSTALLED_USERDATATARBALL_TARGET)")
    $(MKTARBALL) $(FS_GET_STATS) $(PRODUCT_OUT) data $(PRIVATE_USERDATA_TAR) $(INSTALLED_USERDATATARBALL_TARGET)
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/binary.mk', line 428)
so_suffix := .so
# makefile (from `build/core/Makefile', line 40)
ADDITIONAL_DEFAULT_PROPERTIES :=  ro.secure=0 ro.allow.mock.location=1 ro.debuggable=1 persist.service.adb.enable=1
# makefile (from `build/core/dex_preopt.mk', line 69)
_build-dexpreopt-boot-jar-dependency-pair = $(if $(filter 1,$(words $(1)))$(filter 0,$(words $(1))),, $(eval _bdbjdp_target := $(DEXPREOPT_BOOT_JAR_DIR_FULL_PATH)/$(word 2,$(1)).odex) $(eval _bdbjdp_dep := $(DEXPREOPT_BOOT_JAR_DIR_FULL_PATH)/$(word 1,$(1)).odex) $(eval $(call add-dependency,$(_bdbjdp_target),$(_bdbjdp_dep))) $(eval $(call _build-dexpreopt-boot-jar-dependency-pair,$(wordlist 2,999,$(1)))))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.PRODUCT_TAGS := 
# makefile (from `build/core/base_rules.mk', line 555)
ALL_MODULE_TAGS.debug := out/target/product/smdkv210/system/bin/tvrecv
# makefile (from `build/core/envsetup.mk', line 216)
HOST_OUT_SDK_ADDON := out/host/linux-x86/sdk_addon
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/sample/products/sample_addon.mk.PRODUCT_MODEL := 
# makefile (from `build/target/product/sim.mk', line 1)
PRODUCTS.build/target/product/sim.mk.INHERITS_FROM := build/target/product/generic.mk
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/combo/arch/arm/armv7-a-neon.mk', line 5)
ARCH_ARM_HAVE_FAST_INTERWORKING := true
# default
PREPROCESS.F = $(FC) $(FFLAGS) $(CPPFLAGS) $(TARGET_ARCH) -F
# makefile (from `external/openssl/CleanSpec.mk', line 52)
external_openssl_CleanSpec-mk_acs := 6@@@@@@@
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.seen := true
# makefile (from `build/core/dex_preopt.mk', line 66)
_dbj_odex := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.DEVICE_PACKAGE_OVERLAYS := 
# environment
BUILD_ENV_SEQUENCE_NUMBER = 10
# makefile (from `build/core/envsetup.mk', line 185)
HOST_OUT_ROOT_debug := out/debug/host
# makefile (from `build/core/dex_preopt.mk', line 81)
_bdbjdp_dep := out/target/product/smdkv210/dex_bootjars/system/framework/services.odex
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/Makefile', line 892)
package_files-copy-root =   if [ -d "$(strip $(1))" -a "$$(ls -A $(1))" ]; then mkdir -p $(2) && $(ACP) -rd $(strip $(1))/* $(2); fi
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/sample/products/sample_addon.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/envsetup.mk', line 242)
TARGET_OUT_DATA := out/target/product/smdkv210/data
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS := build/target/product/core.mk build/target/product/full.mk build/target/product/generic.mk build/target/product/generic_x86.mk build/target/product/sdk.mk build/target/product/sim.mk device/htc/passion/full_passion.mk device/sample/products/sample_addon.mk device/samsung/product/full_smdkv210.mk
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.PRODUCT_COPY_FILES := 
# environment
ANDROID_PRE_BUILD_PATHS = /usr/lib/jvm/java-6-sun/bin
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.PRODUCT_MODEL := 
# makefile (from `build/core/tasks/sdk-addon.mk', line 1)
MAKEFILE_LIST := build/core/clear_vars.mk build/core/executable.mk build/core/dynamic_binary.mk build/core/binary.mk build/core/base_rules.mk build/core/notice_files.mk out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/debug.P out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostAppInfoClientAPI.P out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostCAClientAPI.P out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDateTimeServerAPI.P out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDeviceAPI.P out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostEPGDBClientAPI.P out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostPlatForm.P out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostRgnClientAPI.P out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostSCClientAPI.P out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostTunerClientAPI.P out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostXDCServerAPI.P out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/main.P out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketRecv.P out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketSend.P build/core/copy_headers.mk build/core/Makefile build/core/tasks/apicheck.mk build/core/tasks/cts.mk cts/CtsTestCaseList.mk cts/CtsHostLibraryList.mk build/core/tasks/ide.mk build/core/tasks/product-graph.mk build/core/tasks/sdk-addon.mk
# makefile (from `build/core/pathmap.mk', line 60)
include-path-for = $(foreach n,$(1),$(patsubst $(n):%,%,$(filter $(n):%,$(pathmap_INCL))))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/sample/products/sample_addon.mk.PRODUCT_SDK_ADDON_COPY_MODULES := com.example.android.platform_library:libs/platform_library.jar
# makefile (from `build/core/product_config.mk', line 270)
PRODUCT_COPY_FILES := device/samsung/smdkv210/init.rc:root/init.rc device/samsung/smdkv210/vold.fstab:system/etc/vold.fstab device/samsung/smdkv210/egl.cfg:system/lib/egl/egl.cfg device/samsung/smdkv210/init.smdkv210.rc:root/init.smdkv210.rc device/samsung/smdkv210/ueventd.smdkv210.rc:root/ueventd.smdkv210.rc device/samsung/smdkv210/s3c-keypad.kl:system/usr/keylayout/s3c-keypad.kl device/samsung/smdkv210/media_profiles.xml:system/etc/media_profiles.xml LMC1800/sources/media/omx/secomxregistry:system/etc/secomxregistry LMC1800/sources/media/omx/libSEC_OMX_Core.so:system/lib/libSEC_OMX_Core.so LMC1800/sources/media/omx/libSEC_Resourcemanager.so:system/lib/libSEC_Resourcemanager.so LMC1800/sources/media/omx/libOMX.SEC.AVC.Decoder.so:system/lib/libOMX.SEC.AVC.Decoder.so LMC1800/sources/media/omx/libOMX.SEC.M4V.Decoder.so:system/lib/libOMX.SEC.M4V.Decoder.so LMC1800/sources/media/omx/libOMX.SEC.M2V.Decoder.so:system/lib/libOMX.SEC.M2V.Decoder.so LMC1800/sources/media/omx/libOMX.SEC.WMV.Decoder.so:system/lib/libOMX.SEC.WMV.Decoder.so LMC1800/sources/media/omx/libOMX.SEC.AVC.Encoder.so:system/lib/libOMX.SEC.AVC.Encoder.so LMC1800/sources/media/omx/libOMX.SEC.M4V.Encoder.so:system/lib/libOMX.SEC.M4V.Encoder.so LMC1800/sources/media/sf/libstagefright_amrnb_common.so:system/lib/libstagefright_amrnb_common.so LMC1800/sources/media/sf/libstagefright_enc_common.so:system/lib/libstagefright_enc_common.so LMC1800/sources/media/sf/libstagefright_avc_common.so:system/lib/libstagefright_avc_common.so LMC1800/sources/media/sf/libstagefright_foundation.so:system/lib/libstagefright_foundation.so LMC1800/sources/media/sf/libstagefright_color_conversion.so:system/lib/libstagefright_color_conversion.so LMC1800/sources/media/sf/libstagefright.so:system/lib/libstagefright.so LMC1800/sources/media/sf/libstagefright_omx.so:system/lib/libstagefright_omx.so LMC1800/sources/media/sf/liba52.so:system/lib/liba52.so LMC1800/sources/media/sf/playts:system/bin/playts LMC1800/sources/media/sf/libtv.so:system/lib/libtv.so LMC1800/sources/remount_ctl:system/bin/remount_ctl LMC1800/sources/ui/bootanimation.zip:system/media/bootanimation.zip LMC1800/sources/ui/PrimaryApp.apk:system/app/PrimaryApp.apk LMC1800/sources/ui/HomePage.apk:system/app/HomePage.apk LMC1800/sources/ui/FileManagers.apk:system/app/FileManagers.apk LMC1800/sources/ui/Game.apk:system/app/Game.apk LMC1800/sources/dvb/libmoddvb.so:system/lib/libmoddvb.so LMC1800/sources/dvb/libjnidvb.so:system/lib/libjnidvb.so LMC1800/sources/dvb/libdvbbinderclient.so:system/lib/libdvbbinderclient.so LMC1800/sources/dvb/libdvbcomm.so:system/lib/libdvbcomm.so LMC1800/sources/utiusb.ko:system/lib/modules/utiusb.ko LMC1800/sources/dvb_load:system/bin/dvb_load LMC1800/sources/busybox:system/bin/busybox LMC1800/sources/bash:system/bin/bash LMC1800/sources/dvb/dvb_server:system/bin/dvb_server external/wpa_supplicant/wpa_supplicant.conf:/system/etc/wifi/wpa_supplicant.conf LMC1800/sources/broadcom/dhd.ko:/system/etc/dhd.ko LMC1800/sources/broadcom/fw_bcm4329.bin:/system/etc/fw_bcm4329.bin LMC1800/sources/broadcom/nvram.txt:/system/etc/nvram.txt frameworks/base/data/etc/handheld_core_hardware.xml:system/etc/permissions/handheld_core_hardware.xml packages/wallpapers/LivePicker/android.software.live_wallpaper.xml:system/etc/permissions/android.software.live_wallpaper.xml device/samsung/smdkv210/kernel:kernel packages/wallpapers/LivePicker/android.software.live_wallpaper.xml:/system/etc/permissions/android.software.live_wallpaper.xml vendor/samsung/smdkv210/proprietary/pvrsrvinit:system/vendor/bin/pvrsrvinit vendor/samsung/smdkv210/proprietary/libEGL_POWERVR_SGX540_120.so:system/vendor/lib/egl/libEGL_POWERVR_SGX540_120.so vendor/samsung/smdkv210/proprietary/libGLESv1_CM_POWERVR_SGX540_120.so:system/vendor/lib/egl/libGLESv1_CM_POWERVR_SGX540_120.so vendor/samsung/smdkv210/proprietary/libGLESv2_POWERVR_SGX540_120.so:system/vendor/lib/egl/libGLESv2_POWERVR_SGX540_120.so vendor/samsung/smdkv210/proprietary/libglslcompiler.so:system/vendor/lib/libglslcompiler.so vendor/samsung/smdkv210/proprietary/libIMGegl.so:system/vendor/lib/libIMGegl.so vendor/samsung/smdkv210/proprietary/libpvr2d.so:system/vendor/lib/libpvr2d.so vendor/samsung/smdkv210/proprietary/libpvrANDROID_WSEGL.so:system/vendor/lib/libpvrANDROID_WSEGL.so vendor/samsung/smdkv210/proprietary/libPVRScopeServices.so:system/vendor/lib/libPVRScopeServices.so vendor/samsung/smdkv210/proprietary/libsrv_init.so:system/vendor/lib/libsrv_init.so vendor/samsung/smdkv210/proprietary/libsrv_um.so:system/vendor/lib/libsrv_um.so vendor/samsung/smdkv210/proprietary/libusc.so:system/vendor/lib/libusc.so vendor/samsung/smdkv210/proprietary/gralloc.s5pc110.so:system/vendor/lib/hw/gralloc.s5pc110.so development/data/etc/apns-conf.xml:system/etc/apns-conf.xml development/data/etc/vold.conf:system/etc/vold.conf frameworks/base/data/sounds/F1_MissedCall.ogg:system/media/audio/notifications/F1_MissedCall.ogg frameworks/base/data/sounds/F1_New_MMS.ogg:system/media/audio/notifications/F1_New_MMS.ogg frameworks/base/data/sounds/F1_New_SMS.ogg:system/media/audio/notifications/F1_New_SMS.ogg frameworks/base/data/sounds/Alarm_Buzzer.ogg:system/media/audio/alarms/Alarm_Buzzer.ogg frameworks/base/data/sounds/Alarm_Beep_01.ogg:system/media/audio/alarms/Alarm_Beep_01.ogg frameworks/base/data/sounds/Alarm_Beep_02.ogg:system/media/audio/alarms/Alarm_Beep_02.ogg frameworks/base/data/sounds/Alarm_Classic.ogg:system/media/audio/alarms/Alarm_Classic.ogg frameworks/base/data/sounds/Alarm_Beep_03.ogg:system/media/audio/alarms/Alarm_Beep_03.ogg frameworks/base/data/sounds/Alarm_Rooster_02.ogg:system/media/audio/alarms/Alarm_Rooster_02.ogg frameworks/base/data/sounds/Ring_Classic_02.ogg:system/media/audio/ringtones/Ring_Classic_02.ogg frameworks/base/data/sounds/Ring_Digital_02.ogg:system/media/audio/ringtones/Ring_Digital_02.ogg frameworks/base/data/sounds/Ring_Synth_04.ogg:system/media/audio/ringtones/Ring_Synth_04.ogg frameworks/base/data/sounds/Ring_Synth_02.ogg:system/media/audio/ringtones/Ring_Synth_02.ogg frameworks/base/data/sounds/newwavelabs/BeatPlucker.ogg:system/media/audio/ringtones/BeatPlucker.ogg frameworks/base/data/sounds/newwavelabs/BentleyDubs.ogg:system/media/audio/ringtones/BentleyDubs.ogg frameworks/base/data/sounds/newwavelabs/BirdLoop.ogg:system/media/audio/ringtones/BirdLoop.ogg frameworks/base/data/sounds/newwavelabs/CaribbeanIce.ogg:system/media/audio/ringtones/CaribbeanIce.ogg frameworks/base/data/sounds/newwavelabs/CurveBall.ogg:system/media/audio/ringtones/CurveBall.ogg frameworks/base/data/sounds/newwavelabs/EtherShake.ogg:system/media/audio/ringtones/EtherShake.ogg frameworks/base/data/sounds/newwavelabs/FriendlyGhost.ogg:system/media/audio/ringtones/FriendlyGhost.ogg frameworks/base/data/sounds/newwavelabs/GameOverGuitar.ogg:system/media/audio/ringtones/GameOverGuitar.ogg frameworks/base/data/sounds/newwavelabs/Growl.ogg:system/media/audio/ringtones/Growl.ogg frameworks/base/data/sounds/newwavelabs/InsertCoin.ogg:system/media/audio/ringtones/InsertCoin.ogg frameworks/base/data/sounds/newwavelabs/LoopyLounge.ogg:system/media/audio/ringtones/LoopyLounge.ogg frameworks/base/data/sounds/newwavelabs/LoveFlute.ogg:system/media/audio/ringtones/LoveFlute.ogg frameworks/base/data/sounds/newwavelabs/MidEvilJaunt.ogg:system/media/audio/ringtones/MidEvilJaunt.ogg frameworks/base/data/sounds/newwavelabs/MildlyAlarming.ogg:system/media/audio/ringtones/MildlyAlarming.ogg frameworks/base/data/sounds/newwavelabs/NewPlayer.ogg:system/media/audio/ringtones/NewPlayer.ogg frameworks/base/data/sounds/newwavelabs/Noises1.ogg:system/media/audio/ringtones/Noises1.ogg frameworks/base/data/sounds/newwavelabs/Noises2.ogg:system/media/audio/ringtones/Noises2.ogg frameworks/base/data/sounds/newwavelabs/Noises3.ogg:system/media/audio/ringtones/Noises3.ogg frameworks/base/data/sounds/newwavelabs/OrganDub.ogg:system/media/audio/ringtones/OrganDub.ogg frameworks/base/data/sounds/newwavelabs/RomancingTheTone.ogg:system/media/audio/ringtones/RomancingTheTone.ogg frameworks/base/data/sounds/newwavelabs/SitarVsSitar.ogg:system/media/audio/ringtones/SitarVsSitar.ogg frameworks/base/data/sounds/newwavelabs/SpringyJalopy.ogg:system/media/audio/ringtones/SpringyJalopy.ogg frameworks/base/data/sounds/newwavelabs/Terminated.ogg:system/media/audio/ringtones/Terminated.ogg frameworks/base/data/sounds/newwavelabs/TwirlAway.ogg:system/media/audio/ringtones/TwirlAway.ogg frameworks/base/data/sounds/newwavelabs/VeryAlarmed.ogg:system/media/audio/ringtones/VeryAlarmed.ogg frameworks/base/data/sounds/newwavelabs/World.ogg:system/media/audio/ringtones/World.ogg frameworks/base/data/sounds/newwavelabs/CaffeineSnake.ogg:system/media/audio/notifications/CaffeineSnake.ogg frameworks/base/data/sounds/newwavelabs/DearDeer.ogg:system/media/audio/notifications/DearDeer.ogg frameworks/base/data/sounds/newwavelabs/DontPanic.ogg:system/media/audio/notifications/DontPanic.ogg frameworks/base/data/sounds/newwavelabs/Highwire.ogg:system/media/audio/notifications/Highwire.ogg frameworks/base/data/sounds/newwavelabs/KzurbSonar.ogg:system/media/audio/notifications/KzurbSonar.ogg frameworks/base/data/sounds/newwavelabs/OnTheHunt.ogg:system/media/audio/notifications/OnTheHunt.ogg frameworks/base/data/sounds/newwavelabs/Voila.ogg:system/media/audio/notifications/Voila.ogg frameworks/base/data/sounds/notifications/Beat_Box_Android.ogg:system/media/audio/notifications/Beat_Box_Android.ogg frameworks/base/data/sounds/notifications/Heaven.ogg:system/media/audio/notifications/Heaven.ogg frameworks/base/data/sounds/notifications/TaDa.ogg:system/media/audio/notifications/TaDa.ogg frameworks/base/data/sounds/notifications/Tinkerbell.ogg:system/media/audio/notifications/Tinkerbell.ogg frameworks/base/data/sounds/effects/Effect_Tick.ogg:system/media/audio/ui/Effect_Tick.ogg frameworks/base/data/sounds/effects/KeypressStandard.ogg:system/media/audio/ui/KeypressStandard.ogg frameworks/base/data/sounds/effects/KeypressSpacebar.ogg:system/media/audio/ui/KeypressSpacebar.ogg frameworks/base/data/sounds/effects/KeypressDelete.ogg:system/media/audio/ui/KeypressDelete.ogg frameworks/base/data/sounds/effects/KeypressReturn.ogg:system/media/audio/ui/KeypressReturn.ogg frameworks/base/data/sounds/effects/VideoRecord.ogg:system/media/audio/ui/VideoRecord.ogg frameworks/base/data/sounds/effects/camera_click.ogg:system/media/audio/ui/camera_click.ogg frameworks/base/data/sounds/newwavelabs/CrazyDream.ogg:system/media/audio/ringtones/CrazyDream.ogg frameworks/base/data/sounds/newwavelabs/DreamTheme.ogg:system/media/audio/ringtones/DreamTheme.ogg external/svox/pico/lang/de-DE_gl0_sg.bin:system/tts/lang_pico/de-DE_gl0_sg.bin external/svox/pico/lang/de-DE_ta.bin:system/tts/lang_pico/de-DE_ta.bin external/svox/pico/lang/en-GB_kh0_sg.bin:system/tts/lang_pico/en-GB_kh0_sg.bin external/svox/pico/lang/en-GB_ta.bin:system/tts/lang_pico/en-GB_ta.bin external/svox/pico/lang/en-US_lh0_sg.bin:system/tts/lang_pico/en-US_lh0_sg.bin external/svox/pico/lang/en-US_ta.bin:system/tts/lang_pico/en-US_ta.bin external/svox/pico/lang/es-ES_zl0_sg.bin:system/tts/lang_pico/es-ES_zl0_sg.bin external/svox/pico/lang/es-ES_ta.bin:system/tts/lang_pico/es-ES_ta.bin external/svox/pico/lang/fr-FR_nk0_sg.bin:system/tts/lang_pico/fr-FR_nk0_sg.bin external/svox/pico/lang/fr-FR_ta.bin:system/tts/lang_pico/fr-FR_ta.bin external/svox/pico/lang/it-IT_cm0_sg.bin:system/tts/lang_pico/it-IT_cm0_sg.bin external/svox/pico/lang/it-IT_ta.bin:system/tts/lang_pico/it-IT_ta.bin
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic_x86.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/full.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/definitions.mk', line 1412)
define align-package
$(hide) mv $@ $@.unaligned
$(hide) $(ZIPALIGN) -f 4 $@.unaligned $@.aligned
$(hide) mv $@.aligned $@
endef
# makefile (from `build/core/main.mk', line 643)
modules_to_check := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/tvrecv
# makefile (from `build/core/base_rules.mk', line 540)
ALL_MODULES.tvrecv.INTERMEDIATE_SOURCE_DIR := $(subst ,, )
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sim.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/envsetup.mk', line 233)
TARGET_OUT_SHARED_LIBRARIES := out/target/product/smdkv210/system/lib
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 77)
TARGET_RECOVERY_UI_LIB := librecovery_ui_smdkv210
# makefile (from `build/core/config.mk', line 89)
COMMON_GLOBAL_CFLAGS := -DANDROID -fmessage-length=0 -W -Wall -Wno-unused -Winit-self -Wpointer-arith
# environment
USERNAME = lnt
# default
LINK.p = $(PC) $(PFLAGS) $(CPPFLAGS) $(LDFLAGS) $(TARGET_ARCH)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/envsetup.mk', line 181)
TARGET_OUT_ROOT_debug := out/debug/target
# makefile (from `build/core/definitions.mk', line 470)
doc-timestamp-for = $(OUT_DOCS)/$(strip $(1))-timestamp
# environment
MANDATORY_PATH = /usr/share/gconf/gnome.mandatory.path
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `device/samsung/smdkv210/CleanSpec.mk', line 60)
INTERNAL_CLEAN_STEP.device_samsung_smdkv210_CleanSpec-mk_acs6@@@@@@@@@@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libaudio_intermediates
# makefile (from `packages/inputmethods/LatinIME/CleanSpec.mk', line 47)
INTERNAL_CLEAN_STEP.packages_inputmethods_LatinIME_CleanSpec-mk_acs6@ := rm -rf out/target/common/obj/APPS/LatinIME*
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/full.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/tasks/cts.mk', line 18)
cts_name := android-cts
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/clear_vars.mk', line 83)
LOCAL_INSTRUMENTATION_FOR := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sdk.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/definitions.mk', line 566)
module-names-for-tag-list = $(sort $(foreach tag,$(1),$(ALL_MODULE_NAME_TAGS.$(tag))))
# makefile (from `build/core/definitions.mk', line 1708)
assert-max-image-size = $(if $(2), $(call assert-max-file-size,$(1),$(call image-size-from-data-size,$(2))), true)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.seen := true
# makefile (from `build/core/Makefile', line 114)
BUILDINFO_SH := build/tools/buildinfo.sh
# makefile (from `vendor/samsung/smdkv210/BoardConfigVendor.mk', line 25)
BOARD_NO_PAGE_FLIPPING := false
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_MODEL := 
# makefile (from `build/core/dex_preopt.mk', line 12)
DEXPREOPT_BOOT_JAR_DIR := system/framework
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/core.mk.PRODUCT_COPY_FILES := 
# default
COMPILE.C = $(COMPILE.cc)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sdk.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_BRAND := 
# makefile (from `vendor/samsung/smdkv210/device-vendor.mk', line 32)
ALL_PRODUCTS := build/target/product/full.mk build/target/product/generic.mk build/target/product/generic_x86.mk build/target/product/sdk.mk build/target/product/sim.mk device/htc/passion-common/passion.mk device/htc/passion/full_passion.mk device/htc/passion/passion.mk device/htc/passion/passion_us.mk device/sample/products/sample_addon.mk device/samsung/product/full_smdkv210.mk device/samsung/smdkv210/device.mk external/svox/pico/lang/all_pico_languages.mk vendor/samsung/smdkv210/device-vendor.mk
# makefile (from `build/core/Makefile', line 683)
INSTALLED_RECOVERYIMAGE_TARGET := 
# makefile (from `build/core/definitions.mk', line 111)
my-dir = $(strip $(eval md_file_ := $$(lastword $$(MAKEFILE_LIST))) $(if $(filter $(CLEAR_VARS),$(md_file_)), $(error LOCAL_PATH must be set before including $$(CLEAR_VARS)) , $(patsubst %/,%,$(dir $(md_file_))) ) )
# environment
LESSOPEN = | /usr/bin/lesspipe %s
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/tasks/ide.mk', line 21)
filter-ide-modules = $(strip $(subst -,$(space),$(patsubst $(1)-%,%,$(2))))
# makefile (from `build/core/envsetup.mk', line 158)
TARGET_BUILD_TYPE := release
# makefile (from `build/core/clear_vars.mk', line 93)
LOCAL_NO_EMMA_INSTRUMENT := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/Makefile', line 768)
define build-systemtarball-target
    $(call pretty,"Target system fs tarball: $(INSTALLED_SYSTEMTARBALL_TARGET)")
    $(MKTARBALL) $(FS_GET_STATS) $(PRODUCT_OUT) system $(PRIVATE_SYSTEM_TAR) $(INSTALLED_SYSTEMTARBALL_TARGET)
endef
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sim.mk.PRODUCT_NAME := sim
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.PRODUCT_BRAND := 
# environment
PATH = /usr/lib/jvm/java-6-sun/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/home/lnt/stb/out/host/linux-x86/bin:/home/lnt/stb/prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin:/home/lnt/stb/development/emulator/qtools:/home/lnt/stb/prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin:/home/lnt/stb/prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sim.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/envsetup.mk', line 266)
TARGET_RECOVERY_ROOT_OUT := out/target/product/smdkv210/recovery/root
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.PRODUCT_TAGS := 
# makefile (from `build/core/definitions.mk', line 1661)
image-size-from-data-size = $(shell echo $$(($(1) / 2048 * (2048+64))))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/Makefile', line 1107)
intermediates := out/target/product/smdkv210/obj/PACKAGING/tests_zip_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.PRODUCT_TAGS := 
# makefile (from `build/core/clear_vars.mk', line 56)
LOCAL_JAVA_LIBRARIES := 
# makefile (from `build/core/binary.mk', line 77)
LOCAL_CC := prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/arm-eabi-gcc
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `device/samsung/smdkv210/CleanSpec.mk', line 60)
device_samsung_smdkv210_CleanSpec-mk_acs := 6@@@@@@@@@@@
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/base_rules.mk', line 109)
_fpf := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.inherited := 
# makefile (from `build/core/definitions.mk', line 839)
define transform-m-to-o
$(transform-m-to-o-no-deps)
$(hide) $(transform-d-to-p)
endef
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 42)
TARGET_PROVIDES_INIT_TARGET_RC := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# default
ARFLAGS = rv
# default
LINK.r = $(FC) $(FFLAGS) $(RFLAGS) $(LDFLAGS) $(TARGET_ARCH)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.PRODUCT_COPY_FILES := 
# default
COMPILE.s = $(AS) $(ASFLAGS) $(TARGET_MACH)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/definitions.mk', line 1316)
transform-classes.jar-to-emma = $(hide) java -classpath $(EMMA_JAR) emma instr -outmode fullcopy -outfile $(PRIVATE_EMMA_COVERAGE_FILE) -ip $< -d $(PRIVATE_EMMA_INTERMEDIATES_DIR) $(addprefix -ix , $(PRIVATE_EMMA_COVERAGE_FILTER))
# makefile (from `build/core/dex_preopt.mk', line 25)
DEXPREOPT_UNIPROCESSOR := --uniprocessor
# makefile (from `build/core/envsetup.mk', line 262)
TARGET_ROOT_OUT_ETC := out/target/product/smdkv210/root/etc
# makefile (from `build/core/product_config.mk', line 194)
_include_stack := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/definitions.mk', line 146)
all-subdir-makefiles = $(call all-makefiles-under,$(call my-dir))
# makefile (from `build/core/config.mk', line 201)
SIGNAPK_JAR := out/host/linux-x86/framework/signapk.jar
# makefile (from `build/core/config.mk', line 334)
numerically_sort = $(shell function sgrax() { while [ -n "$$1" ] ; do echo $$1 ; shift ; done } ; ( sgrax $(1) | sort -g ) )
# makefile (from `build/core/clear_vars.mk', line 49)
LOCAL_PREBUILT_EXECUTABLES := 
# makefile (from `build/core/binary.mk', line 187)
cpp_arm_sources := 
# makefile (from `build/core/binary.mk', line 105)
arm_objects_mode := arm
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/clear_vars.mk', line 36)
LOCAL_NO_DEFAULT_COMPILER_FLAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.PRODUCT_TAGS := 
# makefile (from `packages/apps/Camera/CleanSpec.mk', line 46)
INTERNAL_CLEAN_STEP.packages_apps_Camera_CleanSpec-mk_acs6@ := rm -rf out/target/product/smdkv210/obj/APPS/Camera*
# makefile (from `build/core/tasks/product-graph.mk', line 17)
products_pdf := out/products.pdf
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/binary.mk', line 253)
c_arm_sources := 
# makefile (from `build/core/dynamic_binary.mk', line 86)
compress_output := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/LINKED/tvrecv
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 196)
KERNEL_HEADERS_COMMON := bionic/libc/kernel/common
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/definitions.mk', line 126)
all-makefiles-under = $(wildcard $(1)/*/Android.mk)
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/tasks/cts.mk', line 162)
INTERNAL_CTS_TARGET := out/host/linux-x86/cts/android-cts.zip
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/core.mk.PRODUCT_TAGS := 
# makefile (from `build/core/base_rules.mk', line 520)
ALL_MODULES :=  tvrecv
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/envsetup.mk', line 221)
HOST_OUT_STATIC_LIBRARIES := out/host/linux-x86/obj/lib
# makefile (from `build/core/clear_vars.mk', line 23)
LOCAL_REQUIRED_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_included := 
# makefile (from `build/core/Makefile', line 884)
BUILT_TARGET_FILES_PACKAGE := out/target/product/smdkv210/obj/PACKAGING/target_files_intermediates/full_smdkv210-target_files-eng.lnt.zip
# environment
LS_COLORS = rs=0:di=01;34:ln=01;36:mh=00:pi=40;33:so=01;35:do=01;35:bd=40;33;01:cd=40;33;01:or=40;31;01:su=37;41:sg=30;43:ca=30;41:tw=30;42:ow=34;42:st=37;44:ex=01;32:*.tar=01;31:*.tgz=01;31:*.arj=01;31:*.taz=01;31:*.lzh=01;31:*.lzma=01;31:*.tlz=01;31:*.txz=01;31:*.zip=01;31:*.z=01;31:*.Z=01;31:*.dz=01;31:*.gz=01;31:*.lz=01;31:*.xz=01;31:*.bz2=01;31:*.bz=01;31:*.tbz=01;31:*.tbz2=01;31:*.tz=01;31:*.deb=01;31:*.rpm=01;31:*.jar=01;31:*.rar=01;31:*.ace=01;31:*.zoo=01;31:*.cpio=01;31:*.7z=01;31:*.rz=01;31:*.jpg=01;35:*.jpeg=01;35:*.gif=01;35:*.bmp=01;35:*.pbm=01;35:*.pgm=01;35:*.ppm=01;35:*.tga=01;35:*.xbm=01;35:*.xpm=01;35:*.tif=01;35:*.tiff=01;35:*.png=01;35:*.svg=01;35:*.svgz=01;35:*.mng=01;35:*.pcx=01;35:*.mov=01;35:*.mpg=01;35:*.mpeg=01;35:*.m2v=01;35:*.mkv=01;35:*.ogm=01;35:*.mp4=01;35:*.m4v=01;35:*.mp4v=01;35:*.vob=01;35:*.qt=01;35:*.nuv=01;35:*.wmv=01;35:*.asf=01;35:*.rm=01;35:*.rmvb=01;35:*.flc=01;35:*.avi=01;35:*.fli=01;35:*.flv=01;35:*.gl=01;35:*.dl=01;35:*.xcf=01;35:*.xwd=01;35:*.yuv=01;35:*.cgm=01;35:*.emf=01;35:*.axv=01;35:*.anx=01;35:*.ogv=01;35:*.ogx=01;35:*.aac=00;36:*.au=00;36:*.flac=00;36:*.mid=00;36:*.midi=00;36:*.mka=00;36:*.mp3=00;36:*.mpc=00;36:*.ogg=00;36:*.ra=00;36:*.wav=00;36:*.axa=00;36:*.oga=00;36:*.spx=00;36:*.xspf=00;36:
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.PRODUCT_MODEL := 
# makefile (from `build/core/combo/arch/arm/armv7-a-neon.mk', line 12)
ARCH_ARM_HAVE_VFP_D32 := true
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/htc/passion/full_passion.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/Makefile', line 584)
INTERNAL_USERIMAGES_DEPS := out/host/linux-x86/bin/mkyaffs2image
# makefile (from `build/core/Makefile', line 107)
default-locale-language = $(word 2, 2, $(call default-locale, $(1)))
# automatic
%F = $(notdir $%)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/config.mk', line 90)
COMMON_RELEASE_CFLAGS := -DNDEBUG -UDEBUG
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/clear_vars.mk', line 99)
LOCAL_MANIFEST_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sim.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/definitions.mk', line 1243)
define dump-words-to-file
        @rm -f $(2)
        @$(call emit-line,$(wordlist 1,200,$(1)),$(2))
        @$(call emit-line,$(wordlist 201,400,$(1)),$(2))
        @$(call emit-line,$(wordlist 401,600,$(1)),$(2))
        @$(call emit-line,$(wordlist 601,800,$(1)),$(2))
        @$(call emit-line,$(wordlist 801,1000,$(1)),$(2))
        @$(call emit-line,$(wordlist 1001,1200,$(1)),$(2))
        @$(call emit-line,$(wordlist 1201,1400,$(1)),$(2))
        @$(call emit-line,$(wordlist 1401,1600,$(1)),$(2))
        @$(call emit-line,$(wordlist 1601,1800,$(1)),$(2))
        @$(call emit-line,$(wordlist 1801,2000,$(1)),$(2))
        @$(call emit-line,$(wordlist 2001,2200,$(1)),$(2))
        @$(call emit-line,$(wordlist 2201,2400,$(1)),$(2))
        @$(call emit-line,$(wordlist 2401,2600,$(1)),$(2))
        @$(call emit-line,$(wordlist 2601,2800,$(1)),$(2))
        @$(call emit-line,$(wordlist 2801,3000,$(1)),$(2))
        @$(call emit-line,$(wordlist 3001,3200,$(1)),$(2))
        @$(call emit-line,$(wordlist 3201,3400,$(1)),$(2))
        @$(call emit-line,$(wordlist 3401,3600,$(1)),$(2))
        @$(call emit-line,$(wordlist 3601,3800,$(1)),$(2))
        @$(call emit-line,$(wordlist 3801,4000,$(1)),$(2))
        @$(if $(wordlist 4001,4002,$(1)),$(error Too many words ($(words $(1)))))
endef
# environment
OPROFILE_EVENTS_DIR = /home/lnt/stb/prebuilt/linux-x86/oprofile
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/tasks/cts.mk', line 87)
XML_INTERMEDIATES := out/target/common/obj/JAVA_LIBRARIES/core-tests-xml_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/sample/products/sample_addon.mk.PRODUCT_LOCALES := ldpi hdpi mdpi ar_EG ar_IL bg_BG ca_ES cs_CZ da_DK de_AT de_CH de_DE de_LI el_GR en_AU en_CA en_GB en_IE en_IN en_NZ en_SG en_US en_ZA es_ES es_US fi_FI fr_BE fr_CA fr_CH fr_FR he_IL hi_IN hr_HR hu_HU id_ID it_CH it_IT ja_JP ko_KR lt_LT lv_LV nb_NO nl_BE nl_NL pl_PL pt_BR pt_PT ro_RO ru_RU sk_SK sl_SI sr_RS sv_SE th_TH tl_PH tr_TR uk_UA vi_VN zh_CN zh_TW
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/binary.mk', line 190)
cpp_normal_sources := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/envsetup.mk', line 239)
TARGET_OUT_STATIC_LIBRARIES := out/target/product/smdkv210/obj/lib
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/config.mk', line 274)
MD5SUM := md5sum
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sim.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.inherited := 
# makefile (from `build/core/combo/select.mk', line 35)
HOST_HAVE_UNIX_FILE_PATH := 1
# makefile (from `build/core/base_rules.mk', line 155)
module_id := MODULE.TARGET.EXECUTABLES.tvrecv
# makefile (from `external/openssl/CleanSpec.mk', line 50)
INTERNAL_CLEAN_STEP.external_openssl_CleanSpec-mk_acs6@@@@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libcrypto_intermediates out/target/product/smdkv210/obj/SHARED_LIBRARIES/libssl_intermediates out/target/product/smdkv210/obj/EXECUTABLES/openssl_intermediates out/target/product/smdkv210/obj/EXECUTABLES/ssltest_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.PRODUCT_PACKAGE_OVERLAYS := 
# default
LINK.C = $(LINK.cc)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/main.mk', line 249)
INCLUDE_TEST_OTA_KEYS := true
# makefile (from `build/core/config.mk', line 323)
HISTORICAL_NDK_VERSIONS_ROOT := prebuilt/ndk
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sim.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/combo/select.mk', line 30)
HOST_AR := ar
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/definitions.mk', line 675)
define transform-variables
@mkdir -p $(dir $@)
@echo "Sed: $(if $(PRIVATE_MODULE),$(PRIVATE_MODULE),$@) <= $<"
$(hide) sed $(foreach var,$(REPLACE_VARS),-e "s/{{$(var)}}/$(subst /,\/,$(PWD)/$($(var)))/g") $< >$@
$(hide) if [ "$(suffix $@)" = ".sh" ]; then chmod a+rx $@; fi
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.PRODUCT_MANUFACTURER := 
# default
LINK.S = $(CC) $(ASFLAGS) $(CPPFLAGS) $(LDFLAGS) $(TARGET_MACH)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# environment
SSH_AGENT_PID = 1400
# makefile (from `build/core/config.mk', line 294)
TARGET_GLOBAL_LD_DIRS = -L$(TARGET_OUT_INTERMEDIATE_LIBRARIES)
# makefile (from `build/core/envsetup.mk', line 73)
BUILD_OS := linux
# makefile (from `build/core/Makefile', line 1206)
INTERNAL_EMULATOR_PACKAGE_FILES = $(HOST_OUT_EXECUTABLES)/emulator$(HOST_EXECUTABLE_SUFFIX) prebuilt/android-arm/kernel/kernel-qemu $(INSTALLED_RAMDISK_TARGET) $(INSTALLED_SYSTEMIMAGE) $(INSTALLED_USERDATAIMAGE_TARGET)
# makefile (from `build/core/config.mk', line 248)
COLUMN := column
# makefile (from `build/core/cleanspec.mk', line 69)
subdir_cleanspecs := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# default
LINK.c = $(CC) $(CFLAGS) $(CPPFLAGS) $(LDFLAGS) $(TARGET_ARCH)
# makefile (from `build/core/build_id.mk', line 26)
BUILD_ID := GINGERBREAD
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic.mk.PRODUCT_NAME := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.inherited := 
# makefile (from `build/core/envsetup.mk', line 284)
PRINT_BUILD_CONFIG := true
# makefile (from `build/core/definitions.mk', line 1522)
define copy-file-to-target
@mkdir -p $(dir $@)
$(hide) $(ACP) -fpt $< $@
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_MODEL := 
# default
LINK.s = $(CC) $(ASFLAGS) $(LDFLAGS) $(TARGET_MACH)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.PRODUCT_NAME := 
# environment
HOME = /home/lnt
# makefile (from `build/core/envsetup.mk', line 254)
TARGET_OUT_SHARED_LIBRARIES_UNSTRIPPED := out/target/product/smdkv210/symbols/system/lib
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/clear_vars.mk', line 64)
LOCAL_DROIDDOC_CUSTOM_ASSET_DIR := 
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 50)
TARGET_CC := prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/arm-eabi-gcc
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.PRODUCT_NAME := core
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 52)
TARGET_AR := prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/arm-eabi-ar
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/main.mk', line 206)
user_variant := 
# makefile (from `build/core/config.mk', line 206)
APICHECK := out/host/linux-x86/bin/apicheck
# makefile (from `build/core/main.mk', line 548)
add-required-deps := 
# environment
XDG_SESSION_COOKIE = 04df9bafe2ad738341363a1500000005-1330563640.598225-1157296102
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/core.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/full.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `device/htc/passion/full_passion.mk', line 27)
PRODUCTS.device/htc/passion/full_passion.mk.INHERITS_FROM := build/target/product/full.mk build/target/product/languages_full.mk device/htc/passion/passion_us.mk
# makefile (from `build/core/cleanbuild.mk', line 81)
steps := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/config.mk', line 291)
TARGET_RELEASE_CPPFLAGS = $(COMMON_RELEASE_CPPFLAGS)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/definitions.mk', line 1763)
set-inherited-package-variables = $(strip $(call set-inherited-package-variables-internal))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.PRODUCT_DEVICE := generic
# environment
GDM_KEYBOARD_LAYOUT = us
# makefile (from `build/core/config.mk', line 251)
dir := Linux
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/config.mk', line 66)
BUILD_MULTI_PREBUILT := build/core/multi_prebuilt.mk
# makefile (from `build/core/definitions.mk', line 698)
define transform-l-to-cpp
@mkdir -p $(dir $@)
@echo "Lex: $(PRIVATE_MODULE) <= $<"
$(hide) $(LEX) -o$@ $<
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/definitions.mk', line 298)
find-other-java-files = 	$(call find-subdir-files,$(1) -name "*.java" -and -not -name ".*")
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/cleanbuild.mk', line 25)
define _add-clean-step
  $(if $(strip $(INTERNAL_CLEAN_BUILD_VERSION)),, $(error INTERNAL_CLEAN_BUILD_VERSION not set))
  $(eval _acs_makefile_prefix := $(lastword $(MAKEFILE_LIST)))
  $(eval _acs_makefile_prefix := $(subst /,_,$(_acs_makefile_prefix)))
  $(eval _acs_makefile_prefix := $(subst .,-,$(_acs_makefile_prefix)))
  $(eval _acs_makefile_prefix := $(_acs_makefile_prefix)_acs)
  $(if $($(_acs_makefile_prefix)),, $(eval $(_acs_makefile_prefix) := $(INTERNAL_CLEAN_BUILD_VERSION)))
  $(eval $(_acs_makefile_prefix) := $($(_acs_makefile_prefix))@)
  $(if $(strip $(2)),$(eval _acs_id := $($(_acs_makefile_prefix))), $(eval _acs_id := $(_acs_makefile_prefix)$($(_acs_makefile_prefix))))
  $(eval INTERNAL_CLEAN_STEPS += $(_acs_id))
  $(eval INTERNAL_CLEAN_STEP.$(_acs_id) := $(1))
  $(eval _acs_id :=)
  $(eval _acs_makefile_prefix :=)
endef
# makefile (from `build/core/Makefile', line 19)
_src := external/svox/pico/lang/it-IT_ta.bin
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/Makefile', line 1159)
SYMBOLS_ZIP := out/target/product/smdkv210/full_smdkv210-symbols-eng.lnt.zip
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sdk.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/base_rules.mk', line 335)
extra_jar_args := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `external/tvplay/tvrecv/Android.mk', line 1)
md_file_ := external/tvplay/tvrecv/Android.mk
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.PRODUCT_BRAND := 
# makefile (from `build/core/config.mk', line 199)
AIDL := out/host/linux-x86/bin/aidl
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.PRODUCT_NAME := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.PRODUCT_BRAND := 
# makefile (from `build/core/binary.mk', line 163)
lex_cpps := 
# makefile (from `build/core/binary.mk', line 112)
normal_objects_cflags := -mthumb -Os -fomit-frame-pointer -fno-strict-aliasing -finline-limit=64
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/build_id.mk', line 32)
DISPLAY_BUILD_NUMBER := true
# makefile (from `build/core/definitions.mk', line 1753)
define inherit-package-internal
  LOCAL_PACKAGE_OVERRIDES := $(strip $(1))||$(strip $(2))||$(strip $(3))||$(strip $(4))||&&$(strip $(5))||&&$(strip $(6))||&&$(strip $(7)) $(LOCAL_PACKAGE_OVERRIDES)
  include $(1)
  LOCAL_PACKAGE_OVERRIDES := $(wordlist 1,$(words $(LOCAL_PACKAGE_OVERRIDES)), $(LOCAL_PACKAGE_OVERRIDES))
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.PRODUCT_TAGS := 
# makefile (from `build/core/base_rules.mk', line 261)
logtags_java_sources := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.inherited := 
# makefile (from `build/core/binary.mk', line 254)
c_arm_objects := 
# makefile (from `build/core/dynamic_binary.mk', line 71)
LOCAL_COMPRESS_MODULE_SYMBOLS := false
# makefile (from `build/core/version_defaults.mk', line 44)
PLATFORM_VERSION := 2.3.1
# makefile (from `build/core/definitions.mk', line 1186)
define transform-host-o-to-executable
@mkdir -p $(dir $@)
@echo "host Executable: $(PRIVATE_MODULE) ($@)"
$(hide) $(transform-host-o-to-executable-inner)
endef
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/htc/passion/full_passion.mk.PRODUCT_COPY_FILES := device/common/gps/gps.conf_US_SUPL:system/etc/gps.conf device/htc/passion/init.mahimahi.rc:root/init.mahimahi.rc device/htc/passion/ueventd.mahimahi.rc:root/ueventd.mahimahi.rc frameworks/base/data/etc/handheld_core_hardware.xml:system/etc/permissions/handheld_core_hardware.xml frameworks/base/data/etc/android.hardware.camera.flash-autofocus.xml:system/etc/permissions/android.hardware.camera.flash-autofocus.xml frameworks/base/data/etc/android.hardware.telephony.gsm.xml:system/etc/permissions/android.hardware.telephony.gsm.xml frameworks/base/data/etc/android.hardware.location.gps.xml:system/etc/permissions/android.hardware.location.gps.xml frameworks/base/data/etc/android.hardware.wifi.xml:system/etc/permissions/android.hardware.wifi.xml frameworks/base/data/etc/android.hardware.sensor.proximity.xml:system/etc/permissions/android.hardware.sensor.proximity.xml frameworks/base/data/etc/android.hardware.sensor.light.xml:system/etc/permissions/android.hardware.sensor.light.xml frameworks/base/data/etc/android.software.sip.voip.xml:system/etc/permissions/android.software.sip.voip.xml frameworks/base/data/etc/android.hardware.touchscreen.multitouch.xml:system/etc/permissions/android.hardware.touchscreen.multitouch.xml device/htc/passion-common/media_profiles.xml:system/etc/media_profiles.xml device/htc/passion-common/mahimahi-keypad.kl:system/usr/keylayout/mahimahi-keypad.kl device/htc/passion-common/h2w_headset.kl:system/usr/keylayout/h2w_headset.kl device/htc/passion-common/synaptics-rmi-touchscreen.idc:system/usr/idc/synaptics-rmi-touchscreen.idc device/htc/passion-common/vold.fstab:system/etc/vold.fstab device/htc/passion-common/bcm4329.ko:system/lib/modules/bcm4329.ko device/htc/passion-common/kernel:kernel device/htc/common/ecclist_for_mcc.conf:system/etc/ecclist_for_mcc.conf development/data/etc/apns-conf.xml:system/etc/apns-conf.xml development/data/etc/vold.conf:system/etc/vold.conf frameworks/base/data/sounds/F1_MissedCall.ogg:system/media/audio/notifications/F1_MissedCall.ogg frameworks/base/data/sounds/F1_New_MMS.ogg:system/media/audio/notifications/F1_New_MMS.ogg frameworks/base/data/sounds/F1_New_SMS.ogg:system/media/audio/notifications/F1_New_SMS.ogg frameworks/base/data/sounds/Alarm_Buzzer.ogg:system/media/audio/alarms/Alarm_Buzzer.ogg frameworks/base/data/sounds/Alarm_Beep_01.ogg:system/media/audio/alarms/Alarm_Beep_01.ogg frameworks/base/data/sounds/Alarm_Beep_02.ogg:system/media/audio/alarms/Alarm_Beep_02.ogg frameworks/base/data/sounds/Alarm_Classic.ogg:system/media/audio/alarms/Alarm_Classic.ogg frameworks/base/data/sounds/Alarm_Beep_03.ogg:system/media/audio/alarms/Alarm_Beep_03.ogg frameworks/base/data/sounds/Alarm_Rooster_02.ogg:system/media/audio/alarms/Alarm_Rooster_02.ogg frameworks/base/data/sounds/Ring_Classic_02.ogg:system/media/audio/ringtones/Ring_Classic_02.ogg frameworks/base/data/sounds/Ring_Digital_02.ogg:system/media/audio/ringtones/Ring_Digital_02.ogg frameworks/base/data/sounds/Ring_Synth_04.ogg:system/media/audio/ringtones/Ring_Synth_04.ogg frameworks/base/data/sounds/Ring_Synth_02.ogg:system/media/audio/ringtones/Ring_Synth_02.ogg frameworks/base/data/sounds/newwavelabs/BeatPlucker.ogg:system/media/audio/ringtones/BeatPlucker.ogg frameworks/base/data/sounds/newwavelabs/BentleyDubs.ogg:system/media/audio/ringtones/BentleyDubs.ogg frameworks/base/data/sounds/newwavelabs/BirdLoop.ogg:system/media/audio/ringtones/BirdLoop.ogg frameworks/base/data/sounds/newwavelabs/CaribbeanIce.ogg:system/media/audio/ringtones/CaribbeanIce.ogg frameworks/base/data/sounds/newwavelabs/CurveBall.ogg:system/media/audio/ringtones/CurveBall.ogg frameworks/base/data/sounds/newwavelabs/EtherShake.ogg:system/media/audio/ringtones/EtherShake.ogg frameworks/base/data/sounds/newwavelabs/FriendlyGhost.ogg:system/media/audio/ringtones/FriendlyGhost.ogg frameworks/base/data/sounds/newwavelabs/GameOverGuitar.ogg:system/media/audio/ringtones/GameOverGuitar.ogg frameworks/base/data/sounds/newwavelabs/Growl.ogg:system/media/audio/ringtones/Growl.ogg frameworks/base/data/sounds/newwavelabs/InsertCoin.ogg:system/media/audio/ringtones/InsertCoin.ogg frameworks/base/data/sounds/newwavelabs/LoopyLounge.ogg:system/media/audio/ringtones/LoopyLounge.ogg frameworks/base/data/sounds/newwavelabs/LoveFlute.ogg:system/media/audio/ringtones/LoveFlute.ogg frameworks/base/data/sounds/newwavelabs/MidEvilJaunt.ogg:system/media/audio/ringtones/MidEvilJaunt.ogg frameworks/base/data/sounds/newwavelabs/MildlyAlarming.ogg:system/media/audio/ringtones/MildlyAlarming.ogg frameworks/base/data/sounds/newwavelabs/NewPlayer.ogg:system/media/audio/ringtones/NewPlayer.ogg frameworks/base/data/sounds/newwavelabs/Noises1.ogg:system/media/audio/ringtones/Noises1.ogg frameworks/base/data/sounds/newwavelabs/Noises2.ogg:system/media/audio/ringtones/Noises2.ogg frameworks/base/data/sounds/newwavelabs/Noises3.ogg:system/media/audio/ringtones/Noises3.ogg frameworks/base/data/sounds/newwavelabs/OrganDub.ogg:system/media/audio/ringtones/OrganDub.ogg frameworks/base/data/sounds/newwavelabs/RomancingTheTone.ogg:system/media/audio/ringtones/RomancingTheTone.ogg frameworks/base/data/sounds/newwavelabs/SitarVsSitar.ogg:system/media/audio/ringtones/SitarVsSitar.ogg frameworks/base/data/sounds/newwavelabs/SpringyJalopy.ogg:system/media/audio/ringtones/SpringyJalopy.ogg frameworks/base/data/sounds/newwavelabs/Terminated.ogg:system/media/audio/ringtones/Terminated.ogg frameworks/base/data/sounds/newwavelabs/TwirlAway.ogg:system/media/audio/ringtones/TwirlAway.ogg frameworks/base/data/sounds/newwavelabs/VeryAlarmed.ogg:system/media/audio/ringtones/VeryAlarmed.ogg frameworks/base/data/sounds/newwavelabs/World.ogg:system/media/audio/ringtones/World.ogg frameworks/base/data/sounds/newwavelabs/CaffeineSnake.ogg:system/media/audio/notifications/CaffeineSnake.ogg frameworks/base/data/sounds/newwavelabs/DearDeer.ogg:system/media/audio/notifications/DearDeer.ogg frameworks/base/data/sounds/newwavelabs/DontPanic.ogg:system/media/audio/notifications/DontPanic.ogg frameworks/base/data/sounds/newwavelabs/Highwire.ogg:system/media/audio/notifications/Highwire.ogg frameworks/base/data/sounds/newwavelabs/KzurbSonar.ogg:system/media/audio/notifications/KzurbSonar.ogg frameworks/base/data/sounds/newwavelabs/OnTheHunt.ogg:system/media/audio/notifications/OnTheHunt.ogg frameworks/base/data/sounds/newwavelabs/Voila.ogg:system/media/audio/notifications/Voila.ogg frameworks/base/data/sounds/notifications/Beat_Box_Android.ogg:system/media/audio/notifications/Beat_Box_Android.ogg frameworks/base/data/sounds/notifications/Heaven.ogg:system/media/audio/notifications/Heaven.ogg frameworks/base/data/sounds/notifications/TaDa.ogg:system/media/audio/notifications/TaDa.ogg frameworks/base/data/sounds/notifications/Tinkerbell.ogg:system/media/audio/notifications/Tinkerbell.ogg frameworks/base/data/sounds/effects/Effect_Tick.ogg:system/media/audio/ui/Effect_Tick.ogg frameworks/base/data/sounds/effects/KeypressStandard.ogg:system/media/audio/ui/KeypressStandard.ogg frameworks/base/data/sounds/effects/KeypressSpacebar.ogg:system/media/audio/ui/KeypressSpacebar.ogg frameworks/base/data/sounds/effects/KeypressDelete.ogg:system/media/audio/ui/KeypressDelete.ogg frameworks/base/data/sounds/effects/KeypressReturn.ogg:system/media/audio/ui/KeypressReturn.ogg frameworks/base/data/sounds/effects/VideoRecord.ogg:system/media/audio/ui/VideoRecord.ogg frameworks/base/data/sounds/effects/camera_click.ogg:system/media/audio/ui/camera_click.ogg frameworks/base/data/sounds/newwavelabs/CrazyDream.ogg:system/media/audio/ringtones/CrazyDream.ogg frameworks/base/data/sounds/newwavelabs/DreamTheme.ogg:system/media/audio/ringtones/DreamTheme.ogg external/svox/pico/lang/de-DE_gl0_sg.bin:system/tts/lang_pico/de-DE_gl0_sg.bin external/svox/pico/lang/de-DE_ta.bin:system/tts/lang_pico/de-DE_ta.bin external/svox/pico/lang/en-GB_kh0_sg.bin:system/tts/lang_pico/en-GB_kh0_sg.bin external/svox/pico/lang/en-GB_ta.bin:system/tts/lang_pico/en-GB_ta.bin external/svox/pico/lang/en-US_lh0_sg.bin:system/tts/lang_pico/en-US_lh0_sg.bin external/svox/pico/lang/en-US_ta.bin:system/tts/lang_pico/en-US_ta.bin external/svox/pico/lang/es-ES_zl0_sg.bin:system/tts/lang_pico/es-ES_zl0_sg.bin external/svox/pico/lang/es-ES_ta.bin:system/tts/lang_pico/es-ES_ta.bin external/svox/pico/lang/fr-FR_nk0_sg.bin:system/tts/lang_pico/fr-FR_nk0_sg.bin external/svox/pico/lang/fr-FR_ta.bin:system/tts/lang_pico/fr-FR_ta.bin external/svox/pico/lang/it-IT_cm0_sg.bin:system/tts/lang_pico/it-IT_cm0_sg.bin external/svox/pico/lang/it-IT_ta.bin:system/tts/lang_pico/it-IT_ta.bin
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `frameworks/base/CleanSpec.mk', line 47)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@ := rm -rf out/target/common/obj/JAVA_LIBRARIES/framework_intermediates/src/core/java/android/backup
# makefile (from `build/core/binary.mk', line 45)
LOCAL_CFLAGS := $(subst ,, )
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sim.mk.PRODUCT_PROPERTY_OVERRIDES := ro.config.notification_sound=OnTheHunt.ogg ro.config.alarm_alert=Alarm_Classic.ogg
# makefile (from `build/core/tasks/cts.mk', line 133)
GEN_CLASSPATH := out/target/common/obj/JAVA_LIBRARIES/core_intermediates/classes.jar:out/target/common/obj/JAVA_LIBRARIES/core-junit_intermediates/classes.jar:out/target/common/obj/JAVA_LIBRARIES/core-junitrunner_intermediates/classes.jar:out/target/common/obj/JAVA_LIBRARIES/core-tests_intermediates/classes.jar:out/host/linux-x86/obj/EXECUTABLES/vm-tests_intermediates/android.core.vm-tests.jar:out/host/linux-x86/framework/descGen.jar:out/host/linux-x86/framework/hosttestlib.jar:out/host/linux-x86/framework/ddmlib-prebuilt.jar:/usr/lib/jvm/java-6-openjdk/lib/tools.jar
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.PRODUCT_BRAND := 
# makefile (from `build/core/binary.mk', line 106)
normal_objects_mode := thumb
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/Makefile', line 1251)
INTERNAL_SDK_HOST_OS_NAME := linux-x86
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/binary.mk', line 316)
asm_objects_s := 
# makefile (from `build/core/envsetup.mk', line 166)
TARGET_PREBUILT_TAG := android-arm
# default
COMPILE.p = $(PC) $(PFLAGS) $(CPPFLAGS) $(TARGET_ARCH) -c
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.PRODUCT_PACKAGES := 
# makefile (from `external/tvplay/tvrecv/Android.mk', line 10)
LOCAL_SRC_FILES := debug.c HostAppInfoClientAPI.c HostCAClientAPI.c HostDateTimeServerAPI.c HostDeviceAPI.c HostEPGDBClientAPI.c HostPlatForm.c HostRgnClientAPI.c HostSCClientAPI.c HostTunerClientAPI.c HostXDCServerAPI.c main.c SocketRecv.c SocketSend.c
# makefile (from `build/core/envsetup.mk', line 60)
HOST_OS := linux
# makefile (from `build/core/tasks/sdk-addon.mk', line 18)
addon_name := 
# makefile
.DEFAULT_GOAL := droid
# makefile (from `build/core/Makefile', line 110)
default-locale-region = $(word 3, 3, $(call default-locale, $(1)))
# makefile (from `build/core/Makefile', line 19)
_dest := system/tts/lang_pico/it-IT_ta.bin
# makefile (from `build/core/config.mk', line 312)
TARGET_GLOBAL_CFLAGS := -fno-exceptions -Wno-multichar -msoft-float -fpic -ffunction-sections -funwind-tables -fstack-protector -Wa,--noexecstack -Werror=format-security -fno-short-enums -march=armv7-a -mfloat-abi=softfp -mfpu=neon -include system/core/include/arch/linux-arm/AndroidConfig.h -I system/core/include/arch/linux-arm/ -Wno-psabi -mthumb-interwork -DANDROID -fmessage-length=0 -W -Wall -Wno-unused -Winit-self -Wpointer-arith -Werror=return-type -Werror=non-virtual-dtor -Werror=address -Werror=sequence-point -DNDEBUG -g -Wstrict-aliasing=2 -finline-functions -fno-inline-functions-called-once -fgcse-after-reload -frerun-cse-after-loop -frename-registers -DNDEBUG -UDEBUG
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/envsetup.mk', line 56)
UNAME := Linux i686
# makefile (from `build/core/config.mk', line 229)
LOCALIZE := out/host/linux-x86/bin/localize
# makefile (from `build/core/base_rules.mk', line 530)
ALL_MODULES.tvrecv.CHECKED :=  out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/tvrecv
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `device/samsung/smdkv210/CleanSpec.mk', line 57)
INTERNAL_CLEAN_STEP.device_samsung_smdkv210_CleanSpec-mk_acs6@@@@@@@@ := rm -rf out/target/product/smdkv210/system/etc/asound.conf
# makefile (from `build/core/definitions.mk', line 1402)
define sign-package
$(hide) mv $@ $@.unsigned
$(hide) java -jar $(SIGNAPK_JAR) $(PRIVATE_CERTIFICATE) $(PRIVATE_PRIVATE_KEY) $@.unsigned $@.signed
$(hide) mv $@.signed $@
endef
# makefile (from `build/core/definitions.mk', line 1024)
define transform-host-o-to-shared-lib
@mkdir -p $(dir $@)
@echo "host SharedLib: $(PRIVATE_MODULE) ($@)"
$(hide) $(transform-host-o-to-shared-lib-inner)
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/samsung/product/full_smdkv210.mk.PRODUCT_DEVICE := smdkv210
# makefile (from `build/core/dex_preopt.mk', line 17)
DEXPREOPT_BOOT_ODEXS :=  out/target/product/smdkv210/dex_bootjars/system/framework/core.odex  out/target/product/smdkv210/dex_bootjars/system/framework/bouncycastle.odex  out/target/product/smdkv210/dex_bootjars/system/framework/ext.odex  out/target/product/smdkv210/dex_bootjars/system/framework/framework.odex  out/target/product/smdkv210/dex_bootjars/system/framework/android.policy.odex  out/target/product/smdkv210/dex_bootjars/system/framework/services.odex  out/target/product/smdkv210/dex_bootjars/system/framework/core-junit.odex
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `frameworks/base/CleanSpec.mk', line 48)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@ := rm -rf out/target/common/obj/JAVA_LIBRARIES/framework_intermediates/src/core/java/com/android/internal/backup
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/Makefile', line 807)
define build-userdataimage-target
    $(call pretty,"Target userdata fs image: $(INSTALLED_USERDATAIMAGE_TARGET)")
    @mkdir -p $(TARGET_OUT_DATA)
    $(hide) $(MKYAFFS2) -f $(mkyaffs2_extra_flags) $(TARGET_OUT_DATA) $(INSTALLED_USERDATAIMAGE_TARGET)
    $(hide) $(call assert-max-image-size,$(INSTALLED_USERDATAIMAGE_TARGET),$(BOARD_USERDATAIMAGE_PARTITION_SIZE),yaffs)
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/definitions.mk', line 1288)
define transform-java-to-classes.jar
@echo "target Java: $(PRIVATE_MODULE) ($(PRIVATE_CLASS_INTERMEDIATES_DIR))"
$(hide) rm -f $@
$(hide) rm -rf $(PRIVATE_CLASS_INTERMEDIATES_DIR)
$(hide) mkdir -p $(PRIVATE_CLASS_INTERMEDIATES_DIR)
$(call unzip-jar-files,$(PRIVATE_STATIC_JAVA_LIBRARIES), $(PRIVATE_CLASS_INTERMEDIATES_DIR))
$(call dump-words-to-file,$(PRIVATE_JAVA_SOURCES),$(dir $(PRIVATE_CLASS_INTERMEDIATES_DIR))/java-source-list)
$(hide) if [ -d "$(PRIVATE_SOURCE_INTERMEDIATES_DIR)" ]; then find $(PRIVATE_SOURCE_INTERMEDIATES_DIR) -name '*.java' >> $(dir $(PRIVATE_CLASS_INTERMEDIATES_DIR))/java-source-list; fi
$(hide) tr ' ' '\n' < $(dir $(PRIVATE_CLASS_INTERMEDIATES_DIR))/java-source-list | sort -u > $(dir $(PRIVATE_CLASS_INTERMEDIATES_DIR))/java-source-list-uniq
$(hide) $(TARGET_JAVAC) -encoding ascii $(PRIVATE_BOOTCLASSPATH) $(addprefix -classpath ,$(strip $(call normalize-path-list,$(PRIVATE_ALL_JAVA_LIBRARIES)))) $(PRIVATE_JAVACFLAGS) $(strip $(PRIVATE_JAVAC_DEBUG_FLAGS)) $(xlint_unchecked) -extdirs "" -d $(PRIVATE_CLASS_INTERMEDIATES_DIR) \@$(dir $(PRIVATE_CLASS_INTERMEDIATES_DIR))/java-source-list-uniq || ( rm -rf $(PRIVATE_CLASS_INTERMEDIATES_DIR) ; exit 41 )
$(hide) rm -f $(dir $(PRIVATE_CLASS_INTERMEDIATES_DIR))/java-source-list
$(hide) rm -f $(dir $(PRIVATE_CLASS_INTERMEDIATES_DIR))/java-source-list-uniq
$(hide) mkdir -p $(dir $@)
$(hide) jar $(if $(strip $(PRIVATE_JAR_MANIFEST)),-cfm,-cf) $@ $(PRIVATE_JAR_MANIFEST) -C $(PRIVATE_CLASS_INTERMEDIATES_DIR) .
$(hide) rm -rf $(PRIVATE_CLASS_INTERMEDIATES_DIR)
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.PRODUCT_DEVICE := 
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 64)
BOARD_USES_HDMI_SUBTITLES := true
# makefile (from `build/core/product_config.mk', line 285)
DEVICE_PACKAGE_OVERLAYS := device/samsung/smdkv210/overlay vendor/samsung/smdkv210/overlay
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/definitions.mk', line 357)
add-prebuilt-files =     $(foreach f,$(2),$(call add-prebuilt-file,$f,$(1)))
# default
MAKE_COMMAND := make
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/full.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# default
LINK.cpp = $(LINK.cc)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/definitions.mk', line 1529)
define copy-file-to-target-with-cp
@mkdir -p $(dir $@)
$(hide) cp -fp $< $@
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.seen := true
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 199)
KERNEL_HEADERS := bionic/libc/kernel/common bionic/libc/kernel/arch-arm
# makefile (from `build/core/definitions.mk', line 156)
all-named-subdir-makefiles = $(wildcard $(addsuffix /Android.mk, $(addprefix $(my-dir)/,$(1))))
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sdk.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/full.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/device.mk', line 45)
import-devices = $(call import-nodes,DEVICES,$(1),$(_device_var_list))
# automatic
*F = $(notdir $*)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sim.mk.PRODUCT_LOCALES := en_US
# makefile (from `build/core/base_rules.mk', line 270)
java_sources := $(subst ,,   )
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/definitions.mk', line 56)
ALL_MODULE_NAME_TAGS := 
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 50)
ARCH_ARM_HAVE_TLS_REGISTER := true
# makefile (from `device/htc/passion-common/passion.mk', line 18)
ro.media.dec.jpeg.memcap = 20000000
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.PRODUCT_MODEL := 
# makefile (from `build/core/config.mk', line 349)
INTERNAL_PLATFORM_API_FILE := out/target/common/obj/PACKAGING/public_api.xml
# environment
GNOME_KEYRING_CONTROL = /tmp/keyring-xdTzEP
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sdk.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/main.mk', line 628)
ALL_DEFAULT_INSTALLED_MODULES := 
# makefile (from `build/core/definitions.mk', line 687)
transform-d-to-p = @cp $(@:%.o=%.d) $(@:%.o=%.P); sed -e 's/#.*//' -e 's/^[^:]*: *//' -e 's/ *\\$$//' -e '/^$$/ d' -e 's/$$/ :/' < $(@:%.o=%.d) >> $(@:%.o=%.P); rm -f $(@:%.o=%.d)
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 146)
libthread_db_root := bionic/libthread_db
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/binary.mk', line 142)
yacc_headers := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sdk.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/envsetup.mk', line 176)
DEBUG_OUT_DIR := out/debug
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.seen := true
# makefile (from `build/core/envsetup.mk', line 257)
TARGET_ROOT_OUT_BIN_UNSTRIPPED := out/target/product/smdkv210/symbols/bin
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic_x86.mk.PRODUCT_DEVICE := generic_x86
# makefile (from `build/core/node_fns.mk', line 149)
_expand-inherited-values =   $(foreach v,$(3), $(eval ### "Shorthand for the name of the target variable") $(eval _eiv_tv := $(1).$(2).$(v)) $(eval ### "Get the list of nodes that this variable inherits") $(eval _eiv_i := $(sort $(patsubst $(INHERIT_TAG)%,%, $(filter $(INHERIT_TAG)%, $($(_eiv_tv)) )))) $(foreach i,$(_eiv_i), $(eval ### "Make sure that this inherit appears only once") $(eval $(_eiv_tv) := $(call uniq-word,$($(_eiv_tv)),$(INHERIT_TAG)$(i))) $(eval ### "Expand the inherit tag") $(eval $(_eiv_tv) := $(strip $(patsubst $(INHERIT_TAG)$(i),$($(1).$(i).$(v)), $($(_eiv_tv))))) $(eval ### "Clear the child so DAGs don't create duplicate entries" ) $(eval $(1).$(i).$(v) :=) $(eval ### "If we just inherited ourselves, it's a cycle.") $(if $(filter $(INHERIT_TAG)$(2),$($(_eiv_tv))), $(warning Cycle detected between "$(2)" and "$(i)" for context "$(1)") $(error import of "$(2)" failed) ) ) ) $(eval _eiv_tv :=) $(eval _eiv_i :=)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.PRODUCT_MODEL := Full Android
# makefile (from `build/core/definitions.mk', line 1556)
define copy-file-to-new-target-with-cp
@mkdir -p $(dir $@)
$(hide) cp -f $< $@
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/Makefile', line 1255)
sdk_dep_file := out/host/linux-x86/sdk/sdk_deps.mk
# makefile (from `frameworks/base/CleanSpec.mk', line 46)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@ := rm -rf out/target/common/obj/JAVA_LIBRARIES/framework_intermediates/src/core/java/com/android/internal/os/IDropBoxService.java
# makefile (from `packages/inputmethods/LatinIME/CleanSpec.mk', line 48)
INTERNAL_CLEAN_STEP.packages_inputmethods_LatinIME_CleanSpec-mk_acs6@@ := rm -rf out/target/product/smdkv210/system/app/LatinIME.apk
# makefile (from `build/core/envsetup.mk', line 128)
WITH_HOST_DALVIK := true
# makefile (from `build/core/executable.mk', line 18)
LOCAL_PRELINK_MODULE := false
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `external/clearsilver/CleanSpec.mk', line 56)
INTERNAL_CLEAN_STEP.external_clearsilver_CleanSpec-mk_acs6@@@@@@@ := rm -rf out/host/linux-x86/obj/SHARED_LIBRARIES/libneo_util_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 233)
PRODUCT_LOCALES := en_US en_GB fr_FR it_IT es_ES es_US de_DE nl_NL cs_CZ pl_PL zh_TW zh_CN ru_RU ko_KR nb_NO pt_PT pt_BR da_DK el_GR sv_SE tr_TR ja_JP hdpi en_US en_GB fr_FR it_IT de_DE es_ES nodpi
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_COPY_FILES := 
# automatic
+F = $(notdir $+)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.PRODUCT_DEVICE := generic
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/clear_vars.mk', line 85)
LOCAL_AIDL_INCLUDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/definitions.mk', line 460)
module-stubs-files = $(foreach module,$(1),$(ALL_MODULES.$(module).STUBS))
# makefile (from `build/core/main.mk', line 576)
user_PACKAGES := 
# makefile (from `build/core/dynamic_binary.mk', line 93)
prelink_input := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/LINKED/tvrecv
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.PRODUCT_NAME := full
# makefile (from `build/core/config.mk', line 72)
BUILD_KEY_CHAR_MAP := build/core/key_char_map.mk
# makefile (from `frameworks/base/CleanSpec.mk', line 50)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@ := rm -rf out/target/common/obj/JAVA_LIBRARIES/framework_intermediates/src/core/java/com/android/internal/backup
# makefile (from `build/core/product.mk', line 62)
_product_var_list := PRODUCT_NAME PRODUCT_MODEL PRODUCT_LOCALES PRODUCT_PACKAGES PRODUCT_DEVICE PRODUCT_MANUFACTURER PRODUCT_BRAND PRODUCT_PROPERTY_OVERRIDES PRODUCT_CHARACTERISTICS PRODUCT_COPY_FILES PRODUCT_OTA_PUBLIC_KEYS PRODUCT_PACKAGE_OVERLAYS DEVICE_PACKAGE_OVERLAYS PRODUCT_CONTRIBUTORS_FILE PRODUCT_TAGS PRODUCT_SDK_ADDON_NAME PRODUCT_SDK_ADDON_COPY_FILES PRODUCT_SDK_ADDON_COPY_MODULES PRODUCT_SDK_ADDON_DOC_MODULE PRODUCT_DEFAULT_WIFI_CHANNELS
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/envsetup.mk', line 268)
TARGET_SYSLOADER_OUT := out/target/product/smdkv210/sysloader
# makefile (from `build/core/node_fns.mk', line 70)
move-var-list = $(foreach v,$(3), $(eval $(2).$(v) := $($(1).$(v))) $(eval $(1).$(v) :=) )
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/node_fns.mk', line 187)
define _import-node
  $(eval _include_stack := $(2) $$(_include_stack))
  $(call clear-var-list, $(3))
  $(eval LOCAL_PATH := $(patsubst %/,%,$(dir $(2))))
  $(eval MAKEFILE_LIST :=)
  $(eval include $(2))
  $(eval _included := $(filter-out $(2),$(MAKEFILE_LIST)))
  $(eval MAKEFILE_LIST :=)
  $(eval LOCAL_PATH :=)
  $(call copy-var-list, $(1).$(2), $(3))
  $(call clear-var-list, $(3))

  $(eval $(1).$(2).inherited := $(call get-inherited-nodes,$(1).$(2),$(3)))
  $(call _import-nodes-inner,$(1),$($(1).$(2).inherited),$(3))

  $(call _expand-inherited-values,$(1),$(2),$(3))

  $(eval $(1).$(2).inherited :=)
  $(eval _include_stack := $(wordlist 2,9999,$$(_include_stack)))
endef
# makefile (from `external/v8/CleanSpec.mk', line 49)
external_v8_CleanSpec-mk_acs := 6@@@
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_PACKAGES := 
# makefile (from `device/samsung/smdkv210/CleanSpec.mk', line 46)
INTERNAL_CLEAN_STEP.device_samsung_smdkv210_CleanSpec-mk_acs6@ := find out/target/product/smdkv210 -name "*.apk" | xargs rm
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/core.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/envsetup.mk', line 235)
TARGET_OUT_APPS := out/target/product/smdkv210/system/app
# makefile (from `build/core/combo/select.mk', line 40)
HOST_HAVE_CLOCK_TIMERS := 1
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/clear_vars.mk', line 94)
LOCAL_NO_EMMA_COMPILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.seen := true
# default
MAKEFILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/clear_vars.mk', line 20)
LOCAL_OVERRIDES_PACKAGES := 
# makefile (from `build/core/combo/select.mk', line 38)
HOST_HAVE_CALL_STACKS := 1
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/definitions.mk', line 519)
normalize-path-list = $(subst $(space),:,$(strip $(1)))
# makefile (from `build/core/config.mk', line 65)
BUILD_PREBUILT := build/core/prebuilt.mk
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/envsetup.mk', line 43)
build_variant := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.PRODUCT_TAGS := 
# makefile (from `build/core/tasks/cts.mk', line 28)
DDMLIB_JAR := out/host/linux-x86/framework/ddmlib-prebuilt.jar
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.PRODUCT_COPY_FILES := 
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 41)
TARGET_PROVIDES_INIT_RC := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# default
CXX = g++
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 52)
USE_CAMERA_STUB := false
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.PRODUCT_BRAND := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 92)
android_config_h := system/core/include/arch/linux-arm/AndroidConfig.h
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/full.mk.PRODUCT_PROPERTY_OVERRIDES := keyguard.no_require_sim=true ro.com.android.dateformat=MM-dd-yyyy ro.com.android.dataroaming=true ro.ril.hsxpa=1 ro.ril.gprsclass=10 ro.config.notification_sound=OnTheHunt.ogg ro.config.alarm_alert=Alarm_Classic.ogg
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/definitions.mk', line 1092)
define transform-to-stripped
@mkdir -p $(dir $@)
@echo "target Strip: $(PRIVATE_MODULE) ($@)"
$(hide) $(SOSLIM) --strip --shady --quiet $< --outfile $@
endef
# makefile (from `packages/apps/Camera/CleanSpec.mk', line 47)
packages_apps_Camera_CleanSpec-mk_acs := 6@@
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/samsung/product/full_smdkv210.mk.PRODUCT_PACKAGES := s3c-keypad.kcm make_ext4fs setup_fs libSEC_OMX_Core libSEC_Resourcemanager libOMX.SEC.AVC.Decoder libOMX.SEC.M2V.Decoder libOMX.SEC.M4V.Decoder libOMX.SEC.WMV.Decoder libOMX.SEC.M4V.Encoder libOMX.SEC.AVC.Encoder lights.s5pc110 overlay.s5pc110 copybit.s5pc110 libcamera libstagefrighthw VoiceDialer SpeechRecorder LiveWallpapersPicker LiveWallpapers MagicSmokeWallpapers VisualizationWallpapers librs_jni LiveWallpapers LiveWallpapersPicker MagicSmokeWallpapers VisualizationWallpapers librs_jni OpenWnn PinyinIME VoiceDialer libWnnEngDic libWnnJpnDic libwnndict AccountAndSyncSettings DeskClock AlarmProvider Bluetooth Calculator Calendar Camera CertInstaller DrmProvider Email Gallery3D LatinIME Launcher2 Mms Music Provision Protips QuickSearchBox Settings Sync SystemUI Updater CalendarProvider SyncProvider bouncycastle com.android.location.provider com.android.location.provider.xml core core-junit create_test_dmtrace dalvikvm dexdeps dexdump dexlist dexopt dmtracedump dvz dx ext framework-res hprof-conv icu.dat jasmin jasmin.jar libcrypto libdex libdvm libexpat libicui18n libicuuc libjavacore libnativehelper libnfc_ndef libsqlite_jni libssl libz sqlite-jdbc Browser Contacts Home HTMLViewer Phone ApplicationsProvider ContactsProvider DownloadProvider DownloadProviderUi MediaProvider PicoTts SettingsProvider TelephonyProvider TtsService VpnServices UserDictionaryProvider PackageInstaller DefaultContainerService Bugreport
# makefile (from `build/core/binary.mk', line 351)
ALL_C_CPP_ETC_OBJECTS :=          out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/debug.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostAppInfoClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostCAClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDateTimeServerAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDeviceAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostEPGDBClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostPlatForm.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostRgnClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostSCClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostTunerClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostXDCServerAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/main.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketRecv.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketSend.o    
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/sample/products/sample_addon.mk.PRODUCT_SDK_ADDON_DOC_MODULE := platform_library
# makefile (from `build/core/config.mk', line 313)
TARGET_GLOBAL_CPPFLAGS = -fvisibility-inlines-hidden $(COMMON_GLOBAL_CPPFLAGS) $(TARGET_ERROR_FLAGS) $(TARGET_RELEASE_CPPFLAGS)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/binary.mk', line 143)
yacc_objects := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/envsetup.mk', line 245)
TARGET_OUT_DATA_JAVA_LIBRARIES := out/target/product/smdkv210/system/framework
# makefile (from `build/core/combo/select.mk', line 55)
TARGET_JNILIB_SUFFIX := .so
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/device.mk', line 74)
resolve-short-device-name = $(strip $(call _resolve-short-device-name,$(1)))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/config.mk', line 96)
COMMON_PACKAGE_SUFFIX := .zip
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# default
COMPILE.S = $(CC) $(ASFLAGS) $(CPPFLAGS) $(TARGET_MACH) -c
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.PRODUCT_TAGS := 
# makefile (from `build/core/config.mk', line 282)
HOST_RELEASE_CFLAGS := -O2 -g -fno-strict-aliasing -DNDEBUG -UDEBUG
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/clear_vars.mk', line 39)
LOCAL_YACCFLAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.inherited := 
# makefile (from `build/core/version_defaults.mk', line 97)
BUILD_NUMBER := eng.lnt.20120301.192057
# makefile (from `build/core/envsetup.mk', line 243)
TARGET_OUT_DATA_EXECUTABLES := out/target/product/smdkv210/system/bin
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.DEVICE_PACKAGE_OVERLAYS := 
# default
COMPILE.c = $(CC) $(CFLAGS) $(CPPFLAGS) $(TARGET_ARCH) -c
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/dex_preopt.mk', line 44)
define _dexpreopt-boot-jar
$(eval _dbj_jar := $(DEXPREOPT_BOOT_JAR_DIR_FULL_PATH)/$(1).jar)
$(eval _dbj_odex := $(DEXPREOPT_BOOT_JAR_DIR_FULL_PATH)/$(1).odex)
$(eval _dbj_jar_no_dex := $(DEXPREOPT_BOOT_JAR_DIR_FULL_PATH)/$(1)_nodex.jar)
$(eval _dbj_src_jar := $(call intermediates-dir-for,JAVA_LIBRARIES,$(1),,COMMON)/javalib.jar)
$(eval $(_dbj_odex): PRIVATE_DBJ_JAR := $(_dbj_jar))
$(_dbj_odex) : $(_dbj_src_jar) | $(ACP) $(DEXPREOPT) $(DEXOPT)
	@echo "Dexpreopt Boot Jar: $$@"
	$(hide) rm -f $$@
	$(hide) mkdir -p $$(dir $$@)
	$(hide) $(ACP) -fpt $$< $$(PRIVATE_DBJ_JAR)
	$$(call dexpreopt-one-file,$$(PRIVATE_DBJ_JAR),$$@)

$(_dbj_jar_no_dex) : $(_dbj_src_jar) | $(ACP) $(AAPT)
	$$(call copy-file-to-target)
	$$(call dexpreopt-remove-classes.dex,$$@)

$(eval _dbj_jar :=)
$(eval _dbj_odex :=)
$(eval _dbj_src_jar :=)
endef
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 162)
target_libgcov := prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/libgcov.a
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_MANUFACTURER := 
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 43)
TARGET_BOARD_PLATFORM := s5pc110
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/config.mk', line 64)
BUILD_HOST_PREBUILT := build/core/host_prebuilt.mk
# makefile (from `build/core/clear_vars.mk', line 52)
LOCAL_PREBUILT_STRIP_COMMENTS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.PRODUCT_TAGS := 
# makefile (from `build/core/Makefile', line 56)
ADDITIONAL_BUILD_PROPERTIES :=  ro.opengles.version=131072 dalvik.vm.heapsize=32m keyguard.no_require_sim=true ro.com.android.dateformat=MM-dd-yyyy ro.com.android.dataroaming=true ro.ril.hsxpa=1 ro.ril.gprsclass=10 ro.config.notification_sound=OnTheHunt.ogg ro.config.alarm_alert=Alarm_Classic.ogg ro.kernel.android.checkjni=1 ro.setupwizard.mode=OPTIONAL dalvik.vm.dexopt-flags=m=y net.bt.name=Android dalvik.vm.stack-trace-file=/data/anr/traces.txt
# makefile (from `build/core/main.mk', line 522)
unknown_custom_modules := out/target/product/smdkv210/system/bin/tvrecv
# makefile (from `build/core/combo/arch/arm/armv7-a-neon.mk', line 4)
ARCH_ARM_HAVE_THUMB_SUPPORT := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/config.mk', line 219)
DEXPREOPT := dalvik/tools/dex-preopt
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_NAME := 
# makefile (from `build/core/device.mk', line 36)
inherit-device =   $(foreach v,$(_device_var_list), $(eval $(v) := $($(v)) $(INHERIT_TAG)$(strip $(1))))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.seen := true
# makefile (from `build/core/Makefile', line 553)
INTERNAL_USERIMAGES_EXT_VARIANT := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.PRODUCT_PACKAGES := 
# makefile (from `cts/CtsTestCaseList.mk', line 31)
CTS_COVERAGE_TEST_CASE_LIST := CtsAccessibilityServiceTestCases CtsAccountManagerTestCases CtsAppTestCases CtsBluetoothTestCases CtsContentTestCases CtsDatabaseTestCases CtsDpiTestCases CtsDpiTestCases2 CtsExampleTestCases CtsGestureTestCases CtsGraphicsTestCases CtsHardwareTestCases CtsJniTestCases CtsLocationTestCases CtsMediaTestCases CtsOsTestCases CtsPermissionTestCases CtsPermission2TestCases CtsPreferenceTestCases CtsProviderTestCases CtsSaxTestCases CtsSpeechTestCases CtsTelephonyTestCases CtsTestStubs CtsTextTestCases CtsUtilTestCases CtsViewTestCases CtsWebkitTestCases CtsWidgetTestCases CtsNetTestCases CtsPerformanceTestCases CtsPerformance2TestCases CtsPerformance3TestCases CtsPerformance4TestCases CtsPerformance5TestCases
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic_x86.mk.PRODUCT_PACKAGES := AlarmClock AlarmProvider Calendar Camera DrmProvider LatinIME Mms Music Settings Sync Updater CalendarProvider SubscribedFeedsProvider SyncProvider bouncycastle com.android.location.provider com.android.location.provider.xml core core-junit create_test_dmtrace dalvikvm dexdeps dexdump dexlist dexopt dmtracedump dvz dx ext framework-res hprof-conv icu.dat jasmin jasmin.jar libcrypto libdex libdvm libexpat libicui18n libicuuc libjavacore libnativehelper libnfc_ndef libsqlite_jni libssl libz sqlite-jdbc Browser Contacts Home HTMLViewer Phone ApplicationsProvider ContactsProvider DownloadProvider DownloadProviderUi MediaProvider PicoTts SettingsProvider TelephonyProvider TtsService VpnServices UserDictionaryProvider PackageInstaller DefaultContainerService Bugreport
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/samsung/product/full_smdkv210.mk.DEVICE_PACKAGE_OVERLAYS := device/samsung/smdkv210/overlay vendor/samsung/smdkv210/overlay
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/full.mk.PRODUCT_LOCALES := en_US en_GB fr_FR it_IT de_DE es_ES
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/config.mk', line 68)
BUILD_STATIC_JAVA_LIBRARY := build/core/static_java_library.mk
# makefile (from `frameworks/base/CleanSpec.mk', line 53)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@ := rm -rf out/target/common/obj/APPS/FrameworkTest_intermediates/
# makefile (from `build/core/definitions.mk', line 1771)
define set-inherited-package-variables-internal
  $(eval _o := $(subst ||, ,$(lastword $(LOCAL_PACKAGE_OVERRIDES))))
  $(eval _n := $(subst ||, ,$(firstword $(LOCAL_PACKAGE_OVERRIDES))))
  $(if $(filter $(word 2,$(_n)),$(LOCAL_PACKAGE_NAME)), $(eval LOCAL_PACKAGE_NAME := $(word 3,$(_o))) $(eval LOCAL_MANIFEST_PACKAGE_NAME := $(word 4,$(_o))) $(call keep-or-override,LOCAL_CERTIFICATE,$(patsubst &&%,%,$(word 5,$(_o)))) $(call keep-or-override,LOCAL_INSTRUMENTATION_FOR,$(patsubst &&%,%,$(word 6,$(_o)))) $(call keep-or-override,LOCAL_MANIFEST_INSTRUMENTATION_FOR,$(patsubst &&%,%,$(word 7,$(_o)))) $(eval LOCAL_OVERRIDES_PACKAGES := $(sort $(LOCAL_OVERRIDES_PACKAGES) $(word 2,$(_o)))) true ,)
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product.mk', line 170)
define _resolve-short-product-name
  $(eval pn := $(strip $(1)))
  $(eval p := $(foreach p,$(PRODUCTS), $(if $(filter $(pn),$(PRODUCTS.$(p).PRODUCT_NAME)), $(p) )) )
  $(eval p := $(sort $(p)))
  $(if $(filter 1,$(words $(p))), $(p), $(if $(filter 0,$(words $(p))), $(error No matches for product "$(pn)"), $(error Product "$(pn)" ambiguous: matches $(p)) ) )
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 85)
INTERNAL_VALID_VARIANTS := user userdebug eng tests
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.PRODUCT_PACKAGES := 
# makefile (from `external/openssl/CleanSpec.mk', line 46)
INTERNAL_CLEAN_STEP.external_openssl_CleanSpec-mk_acs6@ := rm -rf out/target/product/smdkv210/obj/EXECUTABLES/openssl_intermediates
# makefile (from `build/core/combo/arch/arm/armv7-a-neon.mk', line 7)
ARCH_ARM_HAVE_HALFWORD_MULTIPLY := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/config.mk', line 97)
COMMON_JAVA_PACKAGE_SUFFIX := .jar
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/base_rules.mk', line 168)
LOCAL_MODULE_STEM := tvrecv
# makefile (from `build/core/config.mk', line 54)
CLEAR_VARS := build/core/clear_vars.mk
# makefile (from `build/core/definitions.mk', line 1535)
define copy-file-to-target-with-zipalign
@mkdir -p $(dir $@)
$(hide) $(ZIPALIGN) -f 4 $< $@
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sdk.mk.PRODUCT_LOCALES := ldpi hdpi mdpi ar_EG ar_IL bg_BG ca_ES cs_CZ da_DK de_AT de_CH de_DE de_LI el_GR en_AU en_CA en_GB en_IE en_IN en_NZ en_SG en_US en_ZA es_ES es_US fi_FI fr_BE fr_CA fr_CH fr_FR he_IL hi_IN hr_HR hu_HU id_ID it_CH it_IT ja_JP ko_KR lt_LT lv_LV nb_NO nl_BE nl_NL pl_PL pt_BR pt_PT ro_RO ru_RU sk_SK sl_SI sr_RS sv_SE th_TH tl_PH tr_TR uk_UA vi_VN zh_CN zh_TW
# makefile (from `build/core/envsetup.mk', line 277)
COMMON_MODULE_CLASSES := TARGET-NOTICE_FILES HOST-NOTICE_FILES HOST-JAVA_LIBRARIES
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/definitions.mk', line 166)
all-java-files-under = $(patsubst ./%,%, $(shell cd $(LOCAL_PATH) ; find $(1) -name "*.java" -and -not -name ".*") )
# makefile (from `build/core/definitions.mk', line 496)
java-lib-files = $(foreach lib,$(1),$(call _java-lib-full-classes.jar,$(lib),$(2)))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.PRODUCT_TAGS := 
# makefile (from `build/core/tasks/cts.mk', line 85)
SUPPORT_INTERMEDIATES := out/target/common/obj/JAVA_LIBRARIES/core-tests-support_intermediates
# makefile (from `build/core/cleanbuild.mk', line 83)
CURRENT_CLEAN_BUILD_VERSION := 
# makefile (from `packages/inputmethods/LatinIME/CleanSpec.mk', line 50)
packages_inputmethods_LatinIME_CleanSpec-mk_acs := 6@@@
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.PRODUCT_DEVICE := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sim.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/config.mk', line 216)
PROGUARD := external/proguard/bin/proguard.sh
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_CHARACTERISTICS := 
# makefile
MAKEFLAGS = wp
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCT_DEVICE := 
# makefile (from `build/core/main.mk', line 286)
BUILD_WITHOUT_PV := true
# makefile (from `external/openssl/CleanSpec.mk', line 47)
INTERNAL_CLEAN_STEP.external_openssl_CleanSpec-mk_acs6@@ := rm -rf out/target/product/smdkv210/obj/EXECUTABLES/openssl_intermediates
# makefile (from `build/core/definitions.mk', line 1611)
define proguard-disabled-commands
@echo Copying: $@
$(hide) $(ACP) $< $@
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/config.mk', line 151)
TARGET_CPU_ABI2 := armeabi
# makefile (from `build/core/tasks/cts.mk', line 131)
_idfName := core-tests
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.seen := true
# makefile (from `build/core/base_rules.mk', line 272)
all_java_sources := $(subst ,,    )
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 160)
TARGET_FDO_LIB := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/binary.mk', line 256)
c_normal_sources := debug.c HostAppInfoClientAPI.c HostCAClientAPI.c HostDateTimeServerAPI.c HostDeviceAPI.c HostEPGDBClientAPI.c HostPlatForm.c HostRgnClientAPI.c HostSCClientAPI.c HostTunerClientAPI.c HostXDCServerAPI.c main.c SocketRecv.c SocketSend.c
# makefile (from `build/core/config.mk', line 63)
BUILD_PACKAGE := build/core/package.mk
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/samsung/product/full_smdkv210.mk.PRODUCT_BRAND := generic
# environment
GDMSESSION = gnome
# makefile (from `build/core/user_tags.mk', line 28)
GRANDFATHERED_USER_MODULES :=  20-dns.conf 95-configured aapt acp adb AdbWinApi AdbWinUsbApi adbd aidl am android android-common android.policy androidprefs android.test.runner ant antlr-2.7.7 anttasks apicheck apkcheck applypatch app_process apriori archquery asm-3.1 atree audio bb2sym bb_dump bbprof bison bluetoothd bmgr bootanimation brcm_patchram_plus bugreport cfassembler check_stack check_trace clearsilver cmu2nuance com.android.inputmethod.pinyin.lib com.android.phone.common commons-compress-1.0 copybit.qsd8k coverage cpufeatures cts CtsAppSecurityTests cts-dalvik-buildutil dasm dbus-daemon ddmlib ddms ddmuilib debuggerd descGen dexpreopt dex-tools dhcpcd dhcpcd.conf dhcpcd-run-hooks dictTest dnsmasq draw9patch droiddoc dumpeventlog dumpkey dump_regions dumpstate dumpsys dx-tests easymock edify emmalib emulator emulator-arm emulator-core emulator-elff emulator-hw emulator-memcheck emulator-tcg emulator-ui etc1tool eventanalyzer exc_dump fastboot framework FrameworkCoreHostTests frameworks-core-util-lib fsck_msdos fs_get_stats fw_bcm4329_apsta.bin fw_bcm4329.bin genext2fs gps.mahimahi gralloc.default gralloc.qsd8k groovy-all-1.7.0 grxmlcompile guava guavalib gzip hciattach hierarchyviewer hist_trace hosttestlib icudata idegen ime init input installd iptables ip-up-vpn iself isprelinked jarjar javax.obex jcommon-1.0.12 jdiff jdwpspy jfreechart-1.0.9 jfreechart-1.0.9-swt jsr305 jsr305lib junit jython kcm keystore kxml2-2.3.0 launch-wrapper layoutlib layoutlib_api layoutlib_create layoutlib_utils layoutopt liba2dp libabi libacc libandroid libandroid_runtime libandroid_servers libarity libastl libastl_host libaudio libaudioeffect_jni libaudioflinger libaudiointerface libaudiopolicy libaudiopolicybase libbinder libbluedroid libbluetooth libbluetoothd libbuiltinplugin libbundlewrapper libbz libc libcamera_client libcameraservice libcamerastub libc_common libc_nomalloc libctest libcutils libdb libdbus libdiskconfig libdl libdrm1 libdrm1_jni libebl libebl_arm libebl_sh libedify libeffects libEGL libelf libelfcopy libESR_Portable libESR_Shared libETC1 libexif libext libfdlibm libfdlibm-host libFFTEm libfst libft2 libgdbus_static libgif libGLES_android libGLESv1_CM libGLESv2 libglib_static libgui libhardware libhardware_legacy libhost libiprouteutil libiptc libjnigraphics libjni_latinime libjni_pinyinime libjpeg libjs liblinenoise libloc_api-rpc liblog libm libmedia libmedia_jni libmediaplayerservice libmincrypt libminui libminzip libmtdutils libmusicbundle libneo_cgi libneo_cs libneo_util libnetlink libnetutils libop libOpenSLES libopensles_helper libOpenSLESUT libpcap libpixelflinger libpixelflinger_armv6 libpixelflinger_static libpng libpopt libpower librecovery_ui_htc libreference-cdma-sms libreference-ril libreverb libreverbwrapper libril librpc librtp_jni libsafe_iop libSDL libSDLmain libsensorservice libskia libskiagl libsonivox libsoundpool libspeex libsqlite libsqlite3_android libSR_AcousticModels libSR_AcousticState libSR_AudioIn libSR_Core libsrec_jni libSR_EventLog libSR_G2P libSR_Grammar libSR_Nametag libSR_Recognizer libSR_Semproc libSR_Session libSR_Vocabulary libstagefright libstagefright_aacdec libstagefright_aacenc libstagefright_amrnb_common libstagefright_amrnbdec libstagefright_amrnbenc libstagefright_amrwbdec libstagefright_amrwbenc libstagefright_avc_common libstagefright_avcdec libstagefright_avcenc libstagefright_color_conversion libstagefright_enc_common libstagefright_foundation libstagefright_g711dec libstagefright_httplive libstagefrighthw libstagefright_id3 libstagefright_m4vh263dec libstagefright_m4vh263enc libstagefright_matroska libstagefright_mp3dec libstagefright_mpeg2ts libstagefright_omx libstagefright_rtsp libstagefright_vorbisdec libstagefright_vpxdec libstdc++ libstlport libstlport_static libstorage libsurfaceflinger libsurfaceflinger_client libsvoxpico libsystem_server libsysutils libthread_db libtinyxml libtomcrypt libtommath libttspico libttssynthproxy libui libunz libutil libutils libv8 libvisualizer libvorbisidec libvpx libwebcore libwpa_client libwrapsim libxml2 libzipfile lights.kraken lights.qsd8k line_endings linker localize logcat logwrapper lsd mahimahi-keypad.kcm make_cfst makedict make_ext4fs make_g2g makekeycodes make_ve_grammar mediaserver minigzip mkbootfs mkbootimg mksdcard mksnapshot mkstubs mkuserimg.sh mkyaffs2image monkey monkeyrunner mtpd ndc netcfg netd ninepatch oauth obbtool omx_tests org.eclipse.core.commands_3.4.0.I20080509-2000 org.eclipse.equinox.common_3.4.0.v20080421-2006 org.eclipse.jface_3.4.2.M20090107-0800 org-netbeans-api-visual org-openide-util osgi pand parseStringTest ping platform.xml pm post_trace pppd preload profile_pid profile_trace q2dm q2g qwerty2.kcm qwerty.kcm racoon read_addr read_method read_pid read_trace rgb2565 rild rsg-generator run-as runtime schedtest screenshot2 sdcard sdklauncher sdklib sdkmanager sdkstats sdkuilib sdk_v4 sdk_v5 sdk_v6 sdk_v7 sdk_v8 sdptool service servicemanager services sh sig sig-check sig-create signapk signature-tools simg2img simulator soslim spec-progress sqlite3 stack_dump stringtemplate surfaceflinger svc swing-worker-1.1 swt system_server tc temp_layoutlib test_g2g test-progress test-progress-new test_swiarb test_zipfile toolbox traceview tuttle2.kcm uix usbtest vdc vm-tests vold wdsclient wpa_supplicant wpa_supplicant.conf xmlwriter yuv420sp2rgb zipalign
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.PRODUCT_BRAND := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/config.mk', line 14)
SHELL := /bin/bash
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.PRODUCT_NAME := 
# makefile (from `build/core/config.mk', line 345)
TARGET_AVAILABLE_NDK_VERSIONS := 4
# environment
GDM_LANG = en_HK.utf8
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.inherited := 
# makefile (from `build/core/config.mk', line 70)
BUILD_DROIDDOC := build/core/droiddoc.mk
# makefile (from `build/core/config.mk', line 210)
MKEXT2USERIMG := out/host/linux-x86/bin/mkuserimg.sh
# makefile (from `external/tvplay/tvrecv/Android.mk', line 1)
LOCAL_PATH := external/tvplay/tvrecv
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.PRODUCT_BRAND := 
# makefile (from `frameworks/base/CleanSpec.mk', line 55)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@@ := rm -rf out/target/product/smdkv210/system/framework/android.policy.jar
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/Makefile', line 857)
OTATOOLS := out/host/linux-x86/bin/minigzip out/host/linux-x86/bin/mkbootfs out/host/linux-x86/bin/mkbootimg out/host/linux-x86/bin/fs_config out/host/linux-x86/bin/mkyaffs2image out/host/linux-x86/bin/zipalign out/host/linux-x86/bin/aapt out/host/linux-x86/bin/bsdiff out/host/linux-x86/bin/imgdiff out/host/linux-x86/framework/dumpkey.jar out/host/linux-x86/framework/signapk.jar
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/binary.mk', line 97)
LOCAL_LDFLAGS :=  external/tvplay/tvrecv/libutihost.android.a    -Wl,--no-undefined
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.PRODUCT_TAGS := 
# makefile (from `device/samsung/smdkv210/CleanSpec.mk', line 55)
INTERNAL_CLEAN_STEP.device_samsung_smdkv210_CleanSpec-mk_acs6@@@@@@ := rm -rf out/target/product/smdkv210/obj/EXECUTABLES/alsa_*
# makefile (from `build/core/clear_vars.mk', line 89)
LOCAL_DX_FLAGS := 
# makefile (from `external/openssl/CleanSpec.mk', line 48)
INTERNAL_CLEAN_STEP.external_openssl_CleanSpec-mk_acs6@@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libcrypto_intermediates out/target/product/smdkv210/obj/SHARED_LIBRARIES/libssl_intermediates out/target/product/smdkv210/obj/EXECUTABLES/openssl_intermediates out/target/product/smdkv210/obj/EXECUTABLES/ssltest_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/core.mk.PRODUCT_DEVICE := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/clear_vars.mk', line 90)
LOCAL_CERTIFICATE := 
# makefile (from `build/core/config.mk', line 92)
COMMON_GLOBAL_CPPFLAGS := -DANDROID -fmessage-length=0 -W -Wall -Wno-unused -Winit-self -Wpointer-arith -Wsign-promo
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.DEVICE_PACKAGE_OVERLAYS := 
# default
OUTPUT_OPTION = -o $@
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 268)
transform-o-to-static-executable-inner = $(TARGET_CXX) -nostdlib -Bstatic -Wl,-T,$(BUILD_SYSTEM)/armelf.x -Wl,--gc-sections -o $@ $(TARGET_GLOBAL_LD_DIRS) $(TARGET_CRTBEGIN_STATIC_O) $(TARGET_GLOBAL_LDFLAGS) $(PRIVATE_LDFLAGS) $(PRIVATE_ALL_OBJECTS) $(call normalize-target-libraries,$(PRIVATE_ALL_STATIC_LIBRARIES)) $(TARGET_FDO_LIB) $(TARGET_LIBGCC) $(TARGET_CRTEND_O)
# makefile (from `build/core/combo/select.mk', line 32)
TARGET_BINDER_MINI := 0
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# environment
ORBIT_SOCKETDIR = /tmp/orbit-lnt
# makefile (from `build/core/cleanbuild.mk', line 96)
clean_steps_file := 
# makefile (from `build/core/tasks/cts.mk', line 30)
HOSTTESTLIB_JAR := out/host/linux-x86/framework/hosttestlib.jar
# default
COMPILE.cpp = $(COMPILE.cc)
# makefile (from `build/core/binary.mk', line 60)
my_target_project_includes := system/core/include hardware/libhardware/include hardware/libhardware_legacy/include hardware/ril/include dalvik/libnativehelper/include frameworks/base/include frameworks/base/opengl/include frameworks/base/native/include external/skia/include out/target/product/smdkv210/obj/include
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/tasks/cts.mk', line 126)
VMTESTS_INTERMEDIATES := out/host/linux-x86/obj/EXECUTABLES/vm-tests_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/clear_vars.mk', line 81)
LOCAL_JNI_SHARED_LIBRARIES := 
# makefile (from `build/core/definitions.mk', line 483)
_java-lib-dir = $(call intermediates-dir-for, JAVA_LIBRARIES,$(1),$(2),COMMON)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/Makefile', line 692)
mkyaffs2_extra_flags := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product.mk', line 125)
import-products = $(call import-nodes,PRODUCTS,$(1),$(_product_var_list))
# makefile (from `build/core/node_fns.mk', line 99)
uniq-word = $(strip $(if $(filter $(2),$(1)), $(eval h := |||$(subst $(space),|||,$(strip $(1)))|||) $(eval h := $(subst |||$(strip $(2))|||,|||$(space)|||,$(h))) $(eval h := $(word 1,$(h)) $(2) $(wordlist 2,9999,$(h))) $(subst |||,$(space),$(h)) , $(1) ))
# makefile (from `build/core/binary.mk', line 315)
asm_sources_s := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.inherited := 
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 217)
TARGET_STRIP_MODULE := true
# makefile (from `build/core/product.mk', line 84)
dump-product = $(info ==== $(1) ====) $(foreach v,$(_product_var_list), $(info PRODUCTS.$(1).$(v) := $(PRODUCTS.$(1).$(v)))) $(info --------)
# makefile (from `frameworks/base/CleanSpec.mk', line 56)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@@@ := rm -rf out/target/common/obj/JAVA_LIBRARIES/framework_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/dynamic_binary.mk', line 68)
compress_input := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/LINKED/tvrecv
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# environment
ANDROID_BUILD_PATHS = :/home/lnt/stb/out/host/linux-x86/bin:/home/lnt/stb/prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin:/home/lnt/stb/development/emulator/qtools:/home/lnt/stb/prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin:/home/lnt/stb/prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin
# makefile (from `build/core/Makefile', line 152)
build_desc := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic_x86.mk.PRODUCT_BRAND := generic_x86
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.seen := true
# makefile (from `device/samsung/smdkv210/CleanSpec.mk', line 56)
INTERNAL_CLEAN_STEP.device_samsung_smdkv210_CleanSpec-mk_acs6@@@@@@@ := rm -rf out/target/product/smdkv210/system/bin/alsa_*
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `external/openssl/CleanSpec.mk', line 49)
INTERNAL_CLEAN_STEP.external_openssl_CleanSpec-mk_acs6@@@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libcrypto_intermediates out/target/product/smdkv210/obj/SHARED_LIBRARIES/libssl_intermediates out/target/product/smdkv210/obj/EXECUTABLES/openssl_intermediates out/target/product/smdkv210/obj/EXECUTABLES/ssltest_intermediates
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 221)
TARGET_CUSTOM_LD_COMMAND := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/envsetup.mk', line 237)
TARGET_OUT_KEYCHARS := out/target/product/smdkv210/system/usr/keychars
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `external/v8/CleanSpec.mk', line 48)
INTERNAL_CLEAN_STEP.external_v8_CleanSpec-mk_acs6@@ := rm -rf out/host/linux-x86/bin/mksnapshot
# makefile (from `build/core/definitions.mk', line 712)
define transform-y-to-cpp
@mkdir -p $(dir $@)
@echo "Yacc: $(PRIVATE_MODULE) <= $<"
$(YACC) $(PRIVATE_YACCFLAGS) -o $@ $<
touch $(@:$1=$(YACC_HEADER_SUFFIX))
echo '#ifndef '$(@F:$1=_h) > $(@:$1=.h)
echo '#define '$(@F:$1=_h) >> $(@:$1=.h)
cat $(@:$1=$(YACC_HEADER_SUFFIX)) >> $(@:$1=.h)
echo '#endif' >> $(@:$1=.h)
rm -f $(@:$1=$(YACC_HEADER_SUFFIX))
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.PRODUCT_TAGS := 
# makefile (from `build/core/Makefile', line 278)
event_log_tags_file := out/target/product/smdkv210/system/etc/event-log-tags
# makefile (from `dalvik/CleanSpec.mk', line 50)
INTERNAL_CLEAN_STEP.dalvik_CleanSpec-mk_acs6@@@@ := rm -rf /home/lnt/stb/out/target/product/smdkv210/obj/SHARED_LIBRARIES/libdvm*
# makefile (from `build/core/definitions.mk', line 1396)
add-java-resources-to-package = $(hide) jar uf $@ $(PRIVATE_EXTRA_JAR_ARGS)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.PRODUCT_BRAND := generic
# makefile (from `frameworks/base/CleanSpec.mk', line 62)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@@@@@@@@@ := rm -f out/target/product/smdkv210/symbols/system/lib/libequalizertest.so
# makefile (from `build/core/main.mk', line 523)
CUSTOM_MODULES :=  out/target/product/smdkv210/system/bin/tvrecv
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/definitions.mk', line 1072)
define transform-o-to-shared-lib
@mkdir -p $(dir $@)
@echo "target SharedLib: $(PRIVATE_MODULE) ($@)"
$(hide) $(transform-o-to-shared-lib-inner)
endef
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 75)
BOARD_KERNEL_CMDLINE := console=ttyFIQ0 no_console_suspend
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/Makefile', line 68)
BUILD_VERSION_TAGS := test-keys
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/sample/products/sample_addon.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 53)
TARGET_OBJCOPY := prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/arm-eabi-objcopy
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/binary.mk', line 307)
asm_objects_S := 
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 78)
TARGET_RELEASETOOLS_EXTENSIONS := device/samsung/smdkv210
# automatic
@D = $(patsubst %/,%,$(dir $@))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/envsetup.mk', line 270)
TARGET_SYSLOADER_SYSTEM_OUT := out/target/product/smdkv210/sysloader/root/system
# makefile (from `device/samsung/smdkc110/CleanSpec.mk', line 58)
INTERNAL_CLEAN_STEP.device_samsung_smdkc110_CleanSpec-mk_acs6@@@@@@@@@ := rm -rf out/target/product/smdkc110/system/usr/share/alsa
# makefile (from `build/core/definitions.mk', line 490)
_java-lib-full-classes.jar = $(call _java-lib-dir,$(1),$(2))/classes$(COMMON_JAVA_PACKAGE_SUFFIX)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_TAGS := 
# makefile (from `frameworks/base/CleanSpec.mk', line 57)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@@@@ := rm -f out/target/product/smdkv210/obj/lib/libequalizer.so
# makefile (from `build/core/product_config.mk', line 159)
unbundled_goals := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 98)
BOARD_USES_LNT_UI := true
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 36)
TARGET_NO_BOOTLOADER := true
# makefile (from `build/core/product_config.mk', line 203)
pn := full_smdkv210
# makefile (from `build/core/config.mk', line 81)
SHOW_COMMANDS := 
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 54)
TARGET_LD := prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/arm-eabi-ld
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/config.mk', line 204)
MKBOOTIMG := out/host/linux-x86/bin/mkbootimg
# makefile (from `build/core/definitions.mk', line 1568)
define transform-prebuilt-to-target-with-zipalign
@echo "$(if $(PRIVATE_IS_HOST_MODULE),host,target) Prebuilt APK: $(PRIVATE_MODULE) ($@)"
$(copy-file-to-target-with-zipalign)
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_BRAND := 
# makefile (from `build/core/pathmap.mk', line 29)
pathmap_INCL := bluedroid:system/bluetooth/bluedroid/include bluez:external/bluetooth/bluez glib:external/bluetooth/glib bootloader:bootable/bootloader/legacy/include corecg:external/skia/include/core dbus:external/dbus frameworks-base:frameworks/base/include graphics:external/skia/include/core libc:bionic/libc/include libdrm1:frameworks/base/media/libdrm/mobile1/include libhardware:hardware/libhardware/include libhardware_legacy:hardware/libhardware_legacy/include libhost:build/libs/host/include libm:bionic/libm/include libnativehelper:dalvik/libnativehelper/include libpagemap:system/extras/libpagemap/include libril:hardware/ril/include libstdc++:bionic/libstdc++/include libthread_db:bionic/libthread_db/include mkbootimg:system/core/mkbootimg recovery:bootable/recovery system-core:system/core/include
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.PRODUCT_PACKAGES := 
# default
LINK.cc = $(CXX) $(CXXFLAGS) $(CPPFLAGS) $(LDFLAGS) $(TARGET_ARCH)
# makefile (from `build/core/config.mk', line 26)
SRC_HEADERS := system/core/include hardware/libhardware/include hardware/libhardware_legacy/include hardware/ril/include dalvik/libnativehelper/include frameworks/base/include frameworks/base/opengl/include frameworks/base/native/include external/skia/include
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 49)
TARGET_ARCH_VARIANT := armv7-a-neon
# makefile (from `build/core/definitions.mk', line 834)
define transform-m-to-o-no-deps
@echo "target ObjC: $(PRIVATE_MODULE) <= $<"
$(call transform-c-or-s-to-o-no-deps)
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/combo/select.mk', line 45)
HOST_HAVE_STRLCAT := 0
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.PRODUCT_BRAND := generic
# makefile (from `build/core/config.mk', line 322)
HISTORICAL_SDK_VERSIONS_ROOT := prebuilt/sdk
# makefile (from `build/core/copy_headers.mk', line 22)
_chFrom := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/device.mk', line 53)
define _resolve-short-device-name
  $(eval dn := $(strip $(1)))
  $(eval d := $(foreach d,$(DEVICES), $(if $(filter $(dn),$(DEVICES.$(d).DEVICE_NAME)), $(d) )) )
  $(eval d := $(sort $(d)))
  $(if $(filter 1,$(words $(d))), $(d), $(if $(filter 0,$(words $(d))), $(error No matches for device "$(dn)"), $(error Device "$(dn)" ambiguous: matches $(d)) ) )
endef
# makefile (from `build/core/dex_preopt.mk', line 8)
DEXPREOPT_BOOT_JARS_MODULES := core bouncycastle ext framework android.policy services core-junit
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/dynamic_binary.mk', line 45)
linked_module := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/LINKED/tvrecv
# makefile (from `build/core/main.mk', line 581)
user_MODULES := $(subst ,, )
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 225)
WITH_JIT := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/envsetup.mk', line 269)
TARGET_SYSLOADER_ROOT_OUT := out/target/product/smdkv210/sysloader/root
# makefile (from `frameworks/base/CleanSpec.mk', line 69)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@@@@@@@@@@@@@@@@ := rm -rf out/target/product/smdkv210/system/lib/soundfx/
# automatic
<F = $(notdir $<)
# makefile (from `build/core/config.mk', line 209)
MAKE_EXT4FS := out/host/linux-x86/bin/make_ext4fs
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_MODEL := 
# makefile (from `build/core/tasks/cts.mk', line 29)
junit_host_jar := out/host/linux-x86/framework/junit.jar
# makefile (from `external/openssl/CleanSpec.mk', line 51)
INTERNAL_CLEAN_STEP.external_openssl_CleanSpec-mk_acs6@@@@@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libcrypto_intermediates out/target/product/smdkv210/obj/SHARED_LIBRARIES/libssl_intermediates out/target/product/smdkv210/obj/EXECUTABLES/openssl_intermediates out/target/product/smdkv210/obj/EXECUTABLES/ssltest_intermediates
# makefile (from `build/core/definitions.mk', line 256)
all-subdir-html-files = $(call all-html-files-under,.)
# makefile (from `build/core/binary.mk', line 11)
my_ndk_version_root := 
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 67)
BOARD_USES_FIMGAPI := true
# makefile (from `build/core/base_rules.mk', line 163)
intermediates.COMMON := out/target/common/obj/EXECUTABLES/tvrecv_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/dex_preopt.mk', line 81)
_bdbjdp_target := out/target/product/smdkv210/dex_bootjars/system/framework/core-junit.odex
# makefile (from `build/core/base_rules.mk', line 191)
built_module_path := 
# makefile (from `build/core/Makefile', line 402)
define combine-notice-files
$(1) $(2): PRIVATE_MESSAGE := $(3)
$(1) $(2) $(4)/hash-timestamp: PRIVATE_DIR := $(4)
$(4)/hash-timestamp: $(5) $(BUILD_SYSTEM)/Makefile
	@echo Finding NOTICE files: $$@
	$$(hide) rm -rf $$@ $$(PRIVATE_DIR)/hash
	$$(hide) mkdir -p $$(PRIVATE_DIR)/hash
	$$(hide) for file in $$$$(find $$(PRIVATE_DIR)/src -type f); do hash=$$$$($(MD5SUM) $$$$file | sed -e "s/ .*//"); hashfile=$$(PRIVATE_DIR)/hash/$$$$hash; echo $$$$file >> $$$$hashfile; done
	$$(hide) touch $$@
$(1): $(4)/hash-timestamp
	@echo Combining NOTICE files: $$@
	$$(hide) mkdir -p $$(dir $$@)
	$$(hide) echo $$(PRIVATE_MESSAGE) > $$@
	$$(hide) find $$(PRIVATE_DIR)/hash -type f | xargs cat | sort | sed -e "s:$$(PRIVATE_DIR)/src\(.*\)\.txt:  \1:" >> $$@
	$$(hide) echo >> $$@
	$$(hide) echo >> $$@
	$$(hide) echo >> $$@
	$$(hide) for hashfile in $$$$(find $$(PRIVATE_DIR)/hash -type f); do echo "============================================================" >> $$@; echo "Notices for file(s):" >> $$@; cat $$$$hashfile | sort | sed -e "s:$$(PRIVATE_DIR)/src\(.*\)\.txt:  \1:" >> $$@; echo "------------------------------------------------------------" >> $$@; echo >> $$@; orig=$$$$(head -n 1 $$$$hashfile); cat $$$$orig >> $$@; echo >> $$@; echo >> $$@; echo >> $$@; done
$(2): $(4)/hash-timestamp
	@echo Combining NOTICE files: $$@
	$$(hide) mkdir -p $$(dir $$@)
	$$(hide) echo "<html><head>" > $$@
	$$(hide) echo "<style type=\"text/css\">" >> $$@
	$$(hide) echo "body { padding: 0; font-family: sans-serif; }" >> $$@
	$$(hide) echo ".same-license { background-color: #eeeeee; border-top: 20px solid white; padding: 10px; }" >> $$@
	$$(hide) echo ".label { font-weight: bold; }" >> $$@
	$$(hide) echo ".file-list { margin-left: 1em; font-color: blue; }" >> $$@
	$$(hide) echo "</style>" >> $$@
	$$(hide) echo "</head><body topmargin=\"0\" leftmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\">" >> $$@
	$$(hide) echo "<table cellpading=\"0\" cellspacing=\"0\" border=\"0\">" >> $$@
	$$(hide) for hashfile in $$$$(find $$(PRIVATE_DIR)/hash -type f); do cat $$$$hashfile | sort | sed -e "s:$$(PRIVATE_DIR)/src\(.*\)\.txt:  <a name=\"\1\"></a>:" >> $$@; echo "<tr><td class=\"same-license\">" >> $$@; echo "<div class=\"label\">Notices for file(s):</div>" >> $$@; echo "<div class=\"file-list\">" >> $$@; cat $$$$hashfile | sort | sed -e "s:$$(PRIVATE_DIR)/src\(.*\)\.txt:  \1<br/>:" >> $$@; echo "</div><!-- file-list -->" >> $$@; echo >> $$@; orig=$$$$(head -n 1 $$$$hashfile); echo "<pre class=\"license-text\">" >> $$@; cat $$$$orig | sed -e "s/\&/\&amp;/g" | sed -e "s/</\&lt;/g" | sed -e "s/>/\&gt;/g" >> $$@; echo "</pre><!-- license-text -->" >> $$@; echo "</td></tr><!-- same-license -->" >> $$@; echo >> $$@; echo >> $$@; echo >> $$@; done
	$$(hide) echo "</table>" >> $$@
	$$(hide) echo "</body></html>" >> $$@
notice_files: $(1) $(2)
endef
# environment
WINDOWID = 69306425
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.PRODUCT_MODEL := 
# default
COMPILE.f = $(FC) $(FFLAGS) $(TARGET_ARCH) -c
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.PRODUCT_NAME := 
# makefile (from `build/core/definitions.mk', line 1211)
define create-resource-java-files
@mkdir -p $(PRIVATE_SOURCE_INTERMEDIATES_DIR)
@mkdir -p $(dir $(PRIVATE_RESOURCE_PUBLICS_OUTPUT))
$(hide) $(AAPT) package $(PRIVATE_AAPT_FLAGS) -m $(eval # PRODUCT_AAPT_CONFIG is intentionally missing-- see comment.) $(addprefix -J , $(PRIVATE_SOURCE_INTERMEDIATES_DIR)) $(addprefix -M , $(PRIVATE_ANDROID_MANIFEST)) $(addprefix -P , $(PRIVATE_RESOURCE_PUBLICS_OUTPUT)) $(addprefix -S , $(PRIVATE_RESOURCE_DIR)) $(addprefix -A , $(PRIVATE_ASSET_DIR)) $(addprefix -I , $(PRIVATE_AAPT_INCLUDES)) $(addprefix -G , $(PRIVATE_PROGUARD_OPTIONS_FILE)) $(addprefix --min-sdk-version , $(DEFAULT_APP_TARGET_SDK)) $(addprefix --target-sdk-version , $(DEFAULT_APP_TARGET_SDK)) $(if $(filter --version-code,$(PRIVATE_AAPT_FLAGS)),,$(addprefix --version-code , $(PLATFORM_SDK_VERSION))) $(if $(filter --version-name,$(PRIVATE_AAPT_FLAGS)),,$(addprefix --version-name , $(PLATFORM_VERSION))) $(addprefix --rename-manifest-package , $(PRIVATE_MANIFEST_PACKAGE_NAME)) $(addprefix --rename-instrumentation-target-package , $(PRIVATE_MANIFEST_INSTRUMENTATION_FOR))
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/Makefile', line 371)
INSTALLED_BOOTIMAGE_TARGET := out/target/product/smdkv210/ramdisk.img
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/core.mk.PRODUCT_PACKAGES := bouncycastle com.android.location.provider com.android.location.provider.xml core core-junit create_test_dmtrace dalvikvm dexdeps dexdump dexlist dexopt dmtracedump dvz dx ext framework-res hprof-conv icu.dat jasmin jasmin.jar libcrypto libdex libdvm libexpat libicui18n libicuuc libjavacore libnativehelper libnfc_ndef libsqlite_jni libssl libz sqlite-jdbc Browser Contacts Home HTMLViewer Phone ApplicationsProvider ContactsProvider DownloadProvider DownloadProviderUi MediaProvider PicoTts SettingsProvider TelephonyProvider TtsService VpnServices UserDictionaryProvider PackageInstaller DefaultContainerService Bugreport
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/config.mk', line 59)
BUILD_SHARED_LIBRARY := build/core/shared_library.mk
# makefile (from `build/core/definitions.mk', line 244)
all-html-files-under = $(patsubst ./%,%, $(shell cd $(LOCAL_PATH) ; find $(1) -name "*.html" -and -not -name ".*") )
# makefile (from `build/core/envsetup.mk', line 180)
TARGET_OUT_ROOT_release := out/target
# makefile (from `build/core/cleanbuild.mk', line 218)
force_installclean := 
# default
LINT = lint
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `device/sample/products/sample_addon.mk', line 37)
PRODUCTS.device/sample/products/sample_addon.mk.INHERITS_FROM := build/target/product/sdk.mk
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/config.mk', line 93)
COMMON_RELEASE_CPPFLAGS := -DNDEBUG -UDEBUG
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.PRODUCT_DEVICE := generic
# makefile (from `device/samsung/smdkv210/CleanSpec.mk', line 59)
INTERNAL_CLEAN_STEP.device_samsung_smdkv210_CleanSpec-mk_acs6@@@@@@@@@@ := rm -rf out/target/product/smdkv210/obj/PACKAGING/systemimage_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `external/openssl/CleanSpec.mk', line 52)
INTERNAL_CLEAN_STEP.external_openssl_CleanSpec-mk_acs6@@@@@@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libcrypto_intermediates out/target/product/smdkv210/obj/SHARED_LIBRARIES/libssl_intermediates out/target/product/smdkv210/obj/EXECUTABLES/openssl_intermediates out/target/product/smdkv210/obj/EXECUTABLES/ssltest_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.PRODUCT_TAGS := 
# makefile (from `build/core/Makefile', line 1236)
sdk_dir := out/host/linux-x86/sdk
# makefile (from `build/core/Makefile', line 315)
INSTALLED_RAMDISK_TARGET := out/target/product/smdkv210/ramdisk.img
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/Makefile', line 1215)
INTERNAL_EMULATOR_PACKAGE_TARGET := out/target/product/smdkv210/full_smdkv210-emulator-eng.lnt.zip
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `packages/apps/Camera/CleanSpec.mk', line 47)
INTERNAL_CLEAN_STEP.packages_apps_Camera_CleanSpec-mk_acs6@@ := rm -rf out/target/common/obj/APPS/Camera*
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.PRODUCT_SDK_ADDON_NAME := 
# default
GET = get
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.inherited := 
# makefile (from `build/core/Makefile', line 101)
default-locale = $(subst _, , $(firstword $(1)))
# makefile (from `build/core/node_fns.mk', line 130)
get-inherited-nodes = $(sort $(subst $(INHERIT_TAG),, $(filter $(INHERIT_TAG)%, $(foreach v,$(2),$($(1).$(v))) )))
# makefile (from `build/core/tasks/ide.mk', line 17)
filter-ide-goals = $(strip $(filter $(1)-%,$(MAKECMDGOALS)))
# environment
DISPLAY = :0.0
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/definitions.mk', line 1498)
define copy-one-header
$(2): $(1)
	@echo "Header: $$@"
	$$(copy-file-to-new-target-with-cp)
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/tasks/cts.mk', line 131)
_idfPrefix := TARGET
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.PRODUCT_NAME := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/envsetup.mk', line 186)
HOST_OUT_ROOT := out/host
# makefile (from `build/core/dex_preopt.mk', line 29)
dexpreopt-remove-classes.dex = $(hide) $(AAPT) remove $(1) classes.dex
# makefile (from `build/core/config.mk', line 142)
TARGET_DEVICE_DIR := device/samsung/smdkv210
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/samsung/product/full_smdkv210.mk.PRODUCT_TAGS := dalvik.gc.type-precise
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/envsetup.mk', line 263)
TARGET_ROOT_OUT_USR := out/target/product/smdkv210/root/usr
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/core.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.PRODUCT_PACKAGES := 
# environment
ONE_SHOT_MAKEFILE = external/tvplay/tvrecv/Android.mk
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/full.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/config.mk', line 341)
TARGET_AVAILABLE_SDK_VERSIONS := current 4 5 6 7 8
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.inherited := 
# makefile (from `build/core/envsetup.mk', line 225)
TARGET_OUT_INTERMEDIATES := out/target/product/smdkv210/obj
# environment
PWD = /home/lnt/stb/external/tvplay/tvrecv
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_NAME := 
# makefile (from `build/core/tasks/cts.mk', line 131)
TESTS_INTERMEDIATES := out/target/common/obj/JAVA_LIBRARIES/core-tests_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.seen := true
# makefile (from `build/core/envsetup.mk', line 256)
TARGET_ROOT_OUT_SBIN_UNSTRIPPED := out/target/product/smdkv210/symbols/sbin
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/base_rules.mk', line 538)
ALL_MODULES.tvrecv.EVENT_LOG_TAGS := $(subst ,, )
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 80)
BOARD_SDMMC_BSP := false
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.PRODUCT_BRAND := 
# default
PREPROCESS.r = $(FC) $(FFLAGS) $(RFLAGS) $(TARGET_ARCH) -F
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_BRAND := 
# makefile (from `build/core/combo/arch/arm/armv7-a-neon.mk', line 13)
ARCH_ARM_HAVE_NEON := true
# makefile (from `build/core/envsetup.mk', line 182)
TARGET_OUT_ROOT := out/target
# makefile (from `build/core/clear_vars.mk', line 96)
LOCAL_PROGUARD_FLAGS := 
# makefile (from `build/core/definitions.mk', line 1003)
transform-host-o-to-shared-lib-inner = $(HOST_CXX) -Wl,-rpath-link=$(TARGET_OUT_INTERMEDIATE_LIBRARIES) -Wl,-rpath,\$$ORIGIN/../lib -shared -Wl,-soname,$(notdir $@) $(PRIVATE_LDFLAGS) $(HOST_GLOBAL_LD_DIRS) $(if $(PRIVATE_NO_DEFAULT_COMPILER_FLAGS),, $(HOST_GLOBAL_LDFLAGS) ) $(PRIVATE_ALL_OBJECTS) -Wl,--whole-archive $(call normalize-host-libraries,$(PRIVATE_ALL_WHOLE_STATIC_LIBRARIES)) -Wl,--no-whole-archive $(call normalize-host-libraries,$(PRIVATE_ALL_STATIC_LIBRARIES)) $(call normalize-host-libraries,$(PRIVATE_ALL_SHARED_LIBRARIES)) -o $@ $(PRIVATE_LDLIBS)
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sdk.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/main.mk', line 258)
tags_to_install := user debug eng
# makefile (from `frameworks/base/CleanSpec.mk', line 61)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@@@@@@@@ := rm -f out/target/product/smdkv210/symbols/system/lib/libequalizer.so
# makefile (from `build/core/dex_preopt.mk', line 15)
DEXPREOPT_BOOT_JAR_DIR_FULL_PATH := out/target/product/smdkv210/dex_bootjars/system/framework
# environment
GTK_MODULES = canberra-gtk-module
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic.mk.PRODUCT_PROPERTY_OVERRIDES := ro.config.notification_sound=OnTheHunt.ogg ro.config.alarm_alert=Alarm_Classic.ogg
# makefile (from `build/core/tasks/cts.mk', line 16)
cts_tools_src_dir := cts/tools
# makefile (from `build/core/config.mk', line 213)
TUNE2FS := tune2fs
# makefile (from `build/core/definitions.mk', line 823)
define transform-s-to-o
$(transform-s-to-o-no-deps)
$(hide) $(transform-d-to-p)
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/binary.mk', line 111)
arm_objects_cflags := -O2 -fomit-frame-pointer -fstrict-aliasing -funswitch-loops -finline-limit=300
# environment
ANDROID_PRODUCT_OUT = /home/lnt/stb/out/target/product/smdkv210
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/definitions.mk', line 1361)
add-assets-to-package = $(hide) $(AAPT) package -u $(PRIVATE_AAPT_FLAGS) $(addprefix -c , $(PRODUCT_AAPT_CONFIG)) $(addprefix -M , $(PRIVATE_ANDROID_MANIFEST)) $(addprefix -S , $(PRIVATE_RESOURCE_DIR)) $(addprefix -A , $(PRIVATE_ASSET_DIR)) $(addprefix -I , $(PRIVATE_AAPT_INCLUDES)) $(addprefix --min-sdk-version , $(DEFAULT_APP_TARGET_SDK)) $(addprefix --target-sdk-version , $(DEFAULT_APP_TARGET_SDK)) $(addprefix --product , $(TARGET_AAPT_CHARACTERISTICS)) $(if $(filter --version-code,$(PRIVATE_AAPT_FLAGS)),,$(addprefix --version-code , $(PLATFORM_SDK_VERSION))) $(if $(filter --version-name,$(PRIVATE_AAPT_FLAGS)),,$(addprefix --version-name , $(PLATFORM_VERSION))) $(addprefix --rename-manifest-package , $(PRIVATE_MANIFEST_PACKAGE_NAME)) $(addprefix --rename-instrumentation-target-package , $(PRIVATE_MANIFEST_INSTRUMENTATION_FOR)) -F $@
# environment
LESSCLOSE = /usr/bin/lesspipe %s %s
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic_x86.mk.PRODUCT_TAGS := 
# makefile (from `frameworks/base/CleanSpec.mk', line 52)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@ := rm -rf out/target/common/obj/JAVA_LIBRARIES/framework_intermediates/src/core/java/android/content
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.PRODUCT_TAGS := 
# makefile (from `build/core/clear_vars.mk', line 53)
LOCAL_INTERMEDIATE_SOURCES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/smdkv210/device.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/samsung/product/full_smdkv210.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/definitions.mk', line 1628)
define transform-jar-to-proguard
$(eval _instrumentation_proguard_flags:=$(call get-instrumentation-proguard-flags))
$(eval _enable_proguard:=$(PRIVATE_PROGUARD_ENABLED)$(_instrumentation_proguard_flags))
$(if $(_enable_proguard),$(call proguard-enabled-commands,$(_instrumentation_proguard_flags)),$(call proguard-disabled-commands))
$(eval _instrumentation_proguard_flags:=)
$(eval _enable_proguard:=)
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.PRODUCT_DEVICE := generic
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/full.mk.PRODUCT_MODEL := Full Android
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `external/tvplay/tvrecv/Android.mk', line 26)
LOCAL_LDLIBS = $(LOCAL_PATH)/libutihost.android.a 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/clear_vars.mk', line 73)
LOCAL_COPY_HEADERS_TO := 
# makefile (from `build/core/definitions.mk', line 314)
find-parent-file = $(strip $(eval _fpf := $(wildcard $(strip $(1))/$(strip $(2)))) $(if $(_fpf),$(_fpf), $(if $(filter-out ./ .,$(1)), $(call find-parent-file,$(patsubst %/,%,$(dir $(1))),$(2)) ) ) )
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/sample/products/sample_addon.mk.PRODUCT_COPY_FILES := device/sample/frameworks/PlatformLibrary/com.example.android.platform_library.xml:system/etc/permissions/com.example.android.platform_library.xml system/core/rootdir/etc/vold.fstab:system/etc/vold.fstab frameworks/base/data/sounds/effects/camera_click.ogg:system/media/audio/ui/camera_click.ogg frameworks/base/data/sounds/effects/VideoRecord.ogg:system/media/audio/ui/VideoRecord.ogg frameworks/base/data/etc/android.hardware.camera.autofocus.xml:system/etc/permissions/android.hardware.camera.autofocus.xml external/svox/pico/lang/de-DE_gl0_sg.bin:system/tts/lang_pico/de-DE_gl0_sg.bin external/svox/pico/lang/de-DE_ta.bin:system/tts/lang_pico/de-DE_ta.bin external/svox/pico/lang/en-GB_kh0_sg.bin:system/tts/lang_pico/en-GB_kh0_sg.bin external/svox/pico/lang/en-GB_ta.bin:system/tts/lang_pico/en-GB_ta.bin external/svox/pico/lang/en-US_lh0_sg.bin:system/tts/lang_pico/en-US_lh0_sg.bin external/svox/pico/lang/en-US_ta.bin:system/tts/lang_pico/en-US_ta.bin external/svox/pico/lang/es-ES_zl0_sg.bin:system/tts/lang_pico/es-ES_zl0_sg.bin external/svox/pico/lang/es-ES_ta.bin:system/tts/lang_pico/es-ES_ta.bin external/svox/pico/lang/fr-FR_nk0_sg.bin:system/tts/lang_pico/fr-FR_nk0_sg.bin external/svox/pico/lang/fr-FR_ta.bin:system/tts/lang_pico/fr-FR_ta.bin external/svox/pico/lang/it-IT_cm0_sg.bin:system/tts/lang_pico/it-IT_cm0_sg.bin external/svox/pico/lang/it-IT_ta.bin:system/tts/lang_pico/it-IT_ta.bin
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/core.mk.PRODUCT_BRAND := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/base_rules.mk', line 242)
logtags_sources := 
# makefile (from `build/core/base_rules.mk', line 526)
ALL_MODULES.tvrecv.PATH :=  external/tvplay/tvrecv
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 212)
TARGET_CRTEND_O := out/target/product/smdkv210/obj/lib/crtend_android.o
# makefile (from `build/core/definitions.mk', line 1387)
add-dex-to-package = $(if $(filter classes.dex,$(notdir $(PRIVATE_DEX_FILE))), $(hide) $(AAPT) add -k $@ $(PRIVATE_DEX_FILE), $(eval _adtp_classes.dex := $(dir $(PRIVATE_DEX_FILE))/classes.dex) $(hide) cp $(PRIVATE_DEX_FILE) $(_adtp_classes.dex) && $(AAPT) add -k $@ $(_adtp_classes.dex) && rm -f $(_adtp_classes.dex))
# makefile (from `build/core/clear_vars.mk', line 8)
LOCAL_DONT_CHECK_MODULE := 
# makefile (from `build/core/clear_vars.mk', line 40)
LOCAL_ASFLAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/config.mk', line 60)
BUILD_EXECUTABLE := build/core/executable.mk
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/common/common.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/envsetup.mk', line 222)
HOST_OUT_NOTICE_FILES := out/host/linux-x86/obj/NOTICE_FILES
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/Makefile', line 204)
APKCERTS_FILE := out/target/product/smdkv210/obj/PACKAGING/apkcerts_intermediates/full_smdkv210-apkcerts-eng.lnt.txt
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/samsung/product/full_smdkv210.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/main.mk', line 584)
debug_MODULES := out/target/product/smdkv210/system/bin/tvrecv
# makefile (from `build/core/combo/select.mk', line 34)
HOST_HAVE_EXCEPTIONS := 0
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/distdir.mk', line 24)
MAKECMDGOALS := all_modules
# makefile (from `cts/CtsTestCaseList.mk', line 15)
CTS_APPS_LIST := CtsVerifier
# makefile (from `build/core/clear_vars.mk', line 88)
LOCAL_ALLOW_UNDEFINED_SYMBOLS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.PRODUCT_DEVICE := generic
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 96)
BOARD_WPA_SUPPLICANT_DRIVER := WEXT
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.PRODUCT_MODEL := Full Android
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 214)
TARGET_CRTBEGIN_SO_O := out/target/product/smdkv210/obj/lib/crtbegin_so.o
# makefile (from `frameworks/base/CleanSpec.mk', line 63)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@@@@@@@@@@ := rm -f out/target/product/smdkv210/symbols/system/lib/libreverb.so
# makefile (from `external/clearsilver/CleanSpec.mk', line 51)
INTERNAL_CLEAN_STEP.external_clearsilver_CleanSpec-mk_acs6@@ := rm -rf out/host/linux-x86/obj/SHARED_LIBRARIES/libneo_cgi_intermediates
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 144)
libm_root := bionic/libm
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_full.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/envsetup.mk', line 240)
TARGET_OUT_NOTICE_FILES := out/target/product/smdkv210/obj/NOTICE_FILES
# default
PREPROCESS.S = $(CC) -E $(CPPFLAGS)
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic_x86.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/core.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.PRODUCT_BRAND := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_CONTRIBUTORS_FILE := 
# default
MAKE_VERSION := 3.81
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.PRODUCT_MANUFACTURER := 
# environment
USER = lnt
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/envsetup.mk', line 213)
HOST_OUT_EXECUTABLES := out/host/linux-x86/bin
# makefile (from `build/core/product_config.mk', line 76)
find-copy-subdir-files = $(shell find $(2) -name "$(1)" | $(SED_EXTENDED) "s:($(2)/?(.*)):\\1\\:$(3)/\\2:" | sed "s://:/:g")
# makefile (from `build/core/envsetup.mk', line 253)
TARGET_OUT_EXECUTABLES_UNSTRIPPED := out/target/product/smdkv210/symbols/system/bin
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `external/svox/pico/lang/all_pico_languages.mk', line 22)
PRODUCTS.external/svox/pico/lang/all_pico_languages.mk.INHERITS_FROM := external/svox/pico/lang/PicoLangDeDeInSystem.mk external/svox/pico/lang/PicoLangEnGBInSystem.mk external/svox/pico/lang/PicoLangEnUsInSystem.mk external/svox/pico/lang/PicoLangEsEsInSystem.mk external/svox/pico/lang/PicoLangFrFrInSystem.mk external/svox/pico/lang/PicoLangItItInSystem.mk
# makefile (from `build/core/product_config.mk', line 207)
TARGET_DEVICE := smdkv210
# makefile (from `build/core/pathmap.mk', line 102)
FRAMEWORKS_BASE_JAVA_SRC_DIRS := frameworks/base/core/java frameworks/base/graphics/java frameworks/base/location/java frameworks/base/media/java frameworks/base/opengl/java frameworks/base/sax/java frameworks/base/telephony/java frameworks/base/ethernet/java frameworks/base/wifi/java frameworks/base/vpn/java frameworks/base/keystore/java frameworks/base/voip/java frameworks/base/slsi/java
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/config.mk', line 267)
HOST_JDK_TOOLS_JAR := /usr/lib/jvm/java-6-openjdk/lib/tools.jar
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic_x86.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_eiv_i := 
# makefile (from `build/core/config.mk', line 227)
ZIPALIGN := out/host/linux-x86/bin/zipalign
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 159)
TARGET_FDO_CFLAGS := 
# makefile (from `build/core/definitions.mk', line 969)
extract-and-include-host-whole-static-libs = $(foreach lib,$(PRIVATE_ALL_WHOLE_STATIC_LIBRARIES), @echo "preparing StaticLib: $(PRIVATE_MODULE) [including $(lib)]"; ldir=$(PRIVATE_INTERMEDIATES_DIR)/WHOLE/$(basename $(notdir $(lib)))_objs; rm -rf $$ldir; mkdir -p $$ldir; filelist=; for f in `$(HOST_AR) t $(lib) | grep '\.o$$'`; do $(HOST_AR) p $(lib) $$f > $$ldir/$$f; filelist="$$filelist $$ldir/$$f"; done ; $(HOST_AR) $(HOST_GLOBAL_ARFLAGS) $(PRIVATE_ARFLAGS) $@ $$filelist; )
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/clear_vars.mk', line 58)
LOCAL_CLASSPATH := 
# makefile (from `build/core/envsetup.mk', line 173)
OUT_DIR := out
# makefile (from `frameworks/base/CleanSpec.mk', line 64)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@@@@@@@@@@@ := rm -f out/target/product/smdkv210/symbols/system/lib/libreverbtest.so
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic_x86.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/envsetup.mk', line 97)
BUILD_ARCH := x86
# makefile (from `build/core/Makefile', line 899)
built_ota_tools := out/target/product/smdkv210/obj/EXECUTABLES/applypatch_intermediates/applypatch out/target/product/smdkv210/obj/EXECUTABLES/applypatch_static_intermediates/applypatch_static out/target/product/smdkv210/obj/EXECUTABLES/check_prereq_intermediates/check_prereq out/target/product/smdkv210/obj/EXECUTABLES/updater_intermediates/updater
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/Makefile', line 54)
INSTALLED_BUILD_PROP_TARGET := out/target/product/smdkv210/system/build.prop
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/config.mk', line 107)
TARGET_PRELINK_MODULE := true
# makefile (from `build/core/pathmap.mk', line 79)
FRAMEWORKS_BASE_SUBDIRS := core/java graphics/java location/java media/java opengl/java sax/java telephony/java ethernet/java wifi/java vpn/java keystore/java voip/java slsi/java
# makefile (from `build/core/product_config.mk', line 54)
trysed := b
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/generic.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/config.mk', line 22)
SRC_DOCS := docs
# makefile (from `device/samsung/smdkv210/device.mk', line 88)
BOARD_USES_LNT_SF := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.PRODUCT_PACKAGE_OVERLAYS := 
# default
LINT.c = $(LINT) $(LINTFLAGS) $(CPPFLAGS) $(TARGET_ARCH)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/definitions.mk', line 1378)
define add-jni-shared-libs-to-package
$(hide) rm -rf $(dir $@)lib
$(hide) mkdir -p $(dir $@)lib/$(TARGET_CPU_ABI)
$(hide) cp $(PRIVATE_JNI_SHARED_LIBRARIES) $(dir $@)lib/$(TARGET_CPU_ABI)
$(hide) (cd $(dir $@) && zip -r $(notdir $@) lib)
$(hide) rm -rf $(dir $@)lib
endef
# default
F77 = $(FC)
# default
COMPILE.cc = $(CXX) $(CXXFLAGS) $(CPPFLAGS) $(TARGET_ARCH) -c
# makefile (from `build/core/version_defaults.mk', line 72)
DEFAULT_APP_TARGET_SDK := 9
# makefile (from `build/core/definitions.mk', line 729)
define transform-aidl-to-java
@mkdir -p $(dir $@)
@echo "Aidl: $(PRIVATE_MODULE) <= $<"
$(hide) $(AIDL) -d$(patsubst %.java,%.P,$@) $(PRIVATE_AIDL_FLAGS) $< $@
endef
# makefile (from `build/core/definitions.mk', line 1418)
define install-dex-debug
$(hide) if [ -f "$(PRIVATE_INTERMEDIATES_DIR)/classes.dex" ]; then mkdir -p $(TOP)/dalvik/DEBUG-FILES; $(ACP) $(PRIVATE_INTERMEDIATES_DIR)/classes.dex $(TOP)/dalvik/DEBUG-FILES/$(PRIVATE_MODULE).dex; fi
$(hide) if [ -f "$(PRIVATE_INTERMEDIATES_DIR)/classes.lst" ]; then mkdir -p $(TOP)/dalvik/DEBUG-FILES; $(ACP) $(PRIVATE_INTERMEDIATES_DIR)/classes.lst $(TOP)/dalvik/DEBUG-FILES/$(PRIVATE_MODULE).lst; fi
endef
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# default
.VARIABLES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sdk.mk.PRODUCT_COPY_FILES := system/core/rootdir/etc/vold.fstab:system/etc/vold.fstab frameworks/base/data/sounds/effects/camera_click.ogg:system/media/audio/ui/camera_click.ogg frameworks/base/data/sounds/effects/VideoRecord.ogg:system/media/audio/ui/VideoRecord.ogg frameworks/base/data/etc/android.hardware.camera.autofocus.xml:system/etc/permissions/android.hardware.camera.autofocus.xml external/svox/pico/lang/de-DE_gl0_sg.bin:system/tts/lang_pico/de-DE_gl0_sg.bin external/svox/pico/lang/de-DE_ta.bin:system/tts/lang_pico/de-DE_ta.bin external/svox/pico/lang/en-GB_kh0_sg.bin:system/tts/lang_pico/en-GB_kh0_sg.bin external/svox/pico/lang/en-GB_ta.bin:system/tts/lang_pico/en-GB_ta.bin external/svox/pico/lang/en-US_lh0_sg.bin:system/tts/lang_pico/en-US_lh0_sg.bin external/svox/pico/lang/en-US_ta.bin:system/tts/lang_pico/en-US_ta.bin external/svox/pico/lang/es-ES_zl0_sg.bin:system/tts/lang_pico/es-ES_zl0_sg.bin external/svox/pico/lang/es-ES_ta.bin:system/tts/lang_pico/es-ES_ta.bin external/svox/pico/lang/fr-FR_nk0_sg.bin:system/tts/lang_pico/fr-FR_nk0_sg.bin external/svox/pico/lang/fr-FR_ta.bin:system/tts/lang_pico/fr-FR_ta.bin external/svox/pico/lang/it-IT_cm0_sg.bin:system/tts/lang_pico/it-IT_cm0_sg.bin external/svox/pico/lang/it-IT_ta.bin:system/tts/lang_pico/it-IT_ta.bin
# makefile (from `build/core/main.mk', line 44)
TOPDIR := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.inherited := 
# makefile (from `build/core/base_rules.mk', line 212)
aidl_sources := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/generic.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product.mk', line 134)
define check-all-products
$(if ,, $(eval _cap_names :=) $(foreach p,$(PRODUCTS), $(eval pn := $(strip $(PRODUCTS.$(p).PRODUCT_NAME))) $(if $(pn),,$(error $(p): PRODUCT_NAME must be defined.)) $(if $(filter $(pn),$(_cap_names)), $(error $(p): PRODUCT_NAME must be unique; "$(pn)" already used by $(strip $(foreach pp,$(PRODUCTS),
              $(if $(filter $(pn),$(PRODUCTS.$(pp).PRODUCT_NAME)), $(pp) ))) ) ) $(eval _cap_names += $(pn)) $(if $(call is-c-identifier,$(pn)),, $(error $(p): PRODUCT_NAME must be a valid C identifier, not "$(pn)") ) $(eval pb := $(strip $(PRODUCTS.$(p).PRODUCT_BRAND))) $(if $(pb),,$(error $(p): PRODUCT_BRAND must be defined.)) $(foreach cf,$(strip $(PRODUCTS.$(p).PRODUCT_COPY_FILES)), $(if $(filter 2,$(words $(subst :,$(space),$(cf)))),, $(error $(p): malformed COPY_FILE "$(cf)") ) ) ) )
endef
# makefile (from `build/core/definitions.mk', line 916)
define transform-host-s-to-o
$(transform-host-s-to-o-no-deps)
$(transform-d-to-p)
endef
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/htc/passion/full_passion.mk.PRODUCT_PACKAGES := sensors.mahimahi lights.mahimahi librs_jni OpenWnn PinyinIME VoiceDialer libWnnEngDic libWnnJpnDic libwnndict AccountAndSyncSettings DeskClock AlarmProvider Bluetooth Calculator Calendar Camera CertInstaller DrmProvider Email Gallery3D LatinIME Launcher2 Mms Music Provision Protips QuickSearchBox Settings Sync SystemUI Updater CalendarProvider SyncProvider bouncycastle com.android.location.provider com.android.location.provider.xml core core-junit create_test_dmtrace dalvikvm dexdeps dexdump dexlist dexopt dmtracedump dvz dx ext framework-res hprof-conv icu.dat jasmin jasmin.jar libcrypto libdex libdvm libexpat libicui18n libicuuc libjavacore libnativehelper libnfc_ndef libsqlite_jni libssl libz sqlite-jdbc Browser Contacts Home HTMLViewer Phone ApplicationsProvider ContactsProvider DownloadProvider DownloadProviderUi MediaProvider PicoTts SettingsProvider TelephonyProvider TtsService VpnServices UserDictionaryProvider PackageInstaller DefaultContainerService Bugreport
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/generic_x86.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/envsetup.mk', line 153)
TARGET_OS := linux
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.PRODUCT_NAME := 
# makefile (from `build/core/binary.mk', line 140)
yacc_cpps := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/Makefile', line 1084)
INSTALLED_FILES_FILE := out/target/product/smdkv210/installed-files.txt
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sim.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic_x86.mk.PRODUCT_NAME := generic_x86
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/config.mk', line 195)
LEX := flex
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/sample/products/sample_addon.mk.PRODUCT_SDK_ADDON_COPY_FILES := device/sample/sdk_addon/manifest.ini:manifest.ini device/sample/sdk_addon/hardware.ini:hardware.ini device/sample/skins/WVGAMedDpi:skins/WVGAMedDpi/ device/sample/skins/WVGAMedDpi/select.png:skins/WVGAMedDpi/select.png device/sample/skins/WVGAMedDpi/layout:skins/WVGAMedDpi/layout device/sample/skins/WVGAMedDpi/arrow_down.png:skins/WVGAMedDpi/arrow_down.png device/sample/skins/WVGAMedDpi/keyboard.png:skins/WVGAMedDpi/keyboard.png device/sample/skins/WVGAMedDpi/background_land.png:skins/WVGAMedDpi/background_land.png device/sample/skins/WVGAMedDpi/hardware.ini:skins/WVGAMedDpi/hardware.ini device/sample/skins/WVGAMedDpi/arrow_right.png:skins/WVGAMedDpi/arrow_right.png device/sample/skins/WVGAMedDpi/controls.png:skins/WVGAMedDpi/controls.png device/sample/skins/WVGAMedDpi/arrow_left.png:skins/WVGAMedDpi/arrow_left.png device/sample/skins/WVGAMedDpi/button.png:skins/WVGAMedDpi/button.png device/sample/skins/WVGAMedDpi/spacebar.png:skins/WVGAMedDpi/spacebar.png device/sample/skins/WVGAMedDpi/background_port.png:skins/WVGAMedDpi/background_port.png device/sample/skins/WVGAMedDpi/arrow_up.png:skins/WVGAMedDpi/arrow_up.png device/sample/skins/WVGAMedDpi/key.png:skins/WVGAMedDpi/key.png
# makefile (from `external/clearsilver/CleanSpec.mk', line 53)
INTERNAL_CLEAN_STEP.external_clearsilver_CleanSpec-mk_acs6@@@@ := rm -rf out/host/linux-x86/obj/SHARED_LIBRARIES/libclearsilver-jni_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/combo/select.mk', line 44)
TARGET_HAVE_STRLCPY := 0
# makefile (from `build/core/Makefile', line 1213)
name := full_smdkv210-emulator-eng.lnt
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.inherited := 
# makefile (from `build/core/dex_preopt.mk', line 66)
_dbj_src_jar := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/core.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/clear_vars.mk', line 87)
LOCAL_ADDITIONAL_JAVA_DIR := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_SDK_ADDON_NAME := 
# default
LEX.l = $(LEX) $(LFLAGS) -t
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic_x86.mk]].build/target/product/core.mk.PRODUCT_NAME := core
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.PRODUCT_BRAND := 
# makefile (from `build/core/main.mk', line 583)
eng_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/full.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/base_rules.mk', line 532)
ALL_MODULES.tvrecv.BUILT :=  out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/tvrecv
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/clear_vars.mk', line 54)
LOCAL_INTERMEDIATE_SOURCE_DIR := 
# makefile (from `build/core/Makefile', line 776)
INSTALLED_SYSTEMTARBALL_TARGET := out/target/product/smdkv210/system.tar.bz2
# makefile (from `build/core/envsetup.mk', line 252)
TARGET_OUT_UNSTRIPPED := out/target/product/smdkv210/symbols
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/tasks/ide.mk', line 26)
eclipse_project_goals := 
# makefile (from `build/core/clear_vars.mk', line 48)
LOCAL_PREBUILT_LIBS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/binary.mk', line 264)
c_objects :=  out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/debug.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostAppInfoClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostCAClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDateTimeServerAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDeviceAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostEPGDBClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostPlatForm.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostRgnClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostSCClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostTunerClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostXDCServerAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/main.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketRecv.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketSend.o
# makefile (from `build/core/clear_vars.mk', line 65)
LOCAL_DROIDDOC_OPTIONS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion_us.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_MANUFACTURER := 
# makefile (from `frameworks/base/CleanSpec.mk', line 66)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@@@@@@@@@@@@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libequalizertest_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/config.mk', line 296)
HOST_PROJECT_INCLUDES := system/core/include hardware/libhardware/include hardware/libhardware_legacy/include hardware/ril/include dalvik/libnativehelper/include frameworks/base/include frameworks/base/opengl/include frameworks/base/native/include external/skia/include tools/include out/host/linux-x86/obj/include
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_NAME := 
# makefile (from `build/core/Makefile', line 815)
BUILT_USERDATAIMAGE_TARGET := out/target/product/smdkv210/userdata.img
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].device/samsung/product/full_smdkv210.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/clear_vars.mk', line 76)
LOCAL_ADDITIONAL_DEPENDENCIES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sdk.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/Makefile', line 483)
target_notice_file_txt := out/target/product/smdkv210/obj/NOTICE.txt
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/htc/passion/full_passion.mk.DEVICE_PACKAGE_OVERLAYS := device/htc/passion-common/overlay device/htc/common/overlay
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.seen := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/sdk.mk.PRODUCT_MODEL := 
# environment
TARGET_PRODUCT = full_smdkv210
# makefile (from `build/core/config.mk', line 104)
TARGET_COMPRESS_MODULE_SYMBOLS := false
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/base_rules.mk', line 58)
LOCAL_MODULE_TAGS := debug
# makefile (from `build/core/base_rules.mk', line 160)
MODULE.TARGET.EXECUTABLES.tvrecv := external/tvplay/tvrecv
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/config.mk', line 57)
BUILD_STATIC_LIBRARY := build/core/static_library.mk
# makefile (from `build/core/dex_preopt.mk', line 35)
dexpreopt-one-file = $(hide) $(DEXPREOPT) --dexopt=$(DEXPREOPT_DEXOPT) --build-dir=$(DEXPREOPT_BUILD_DIR) --product-dir=$(DEXPREOPT_PRODUCT_DIR) --boot-dir=$(DEXPREOPT_BOOT_JAR_DIR) --boot-jars=$(DEXPREOPT_BOOT_JARS) $(DEXPREOPT_UNIPROCESSOR) $(patsubst $(DEXPREOPT_BUILD_DIR)/%,%,$(1)) $(patsubst $(DEXPREOPT_BUILD_DIR)/%,%,$(2))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/envsetup.mk', line 90)
HOST_ARCH := x86
# environment
PROMPT_COMMAND = echo -ne "\033]0;[full_smdkv210-eng] lnt@lnt-HP-Pavilion-g4-Notebook-PC: /home/lnt/stb\007"
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/combo/TARGET_linux-arm.mk', line 249)
transform-o-to-executable-inner = $(TARGET_CXX) -nostdlib -Bdynamic -Wl,-T,$(BUILD_SYSTEM)/armelf.x -Wl,-dynamic-linker,/system/bin/linker -Wl,--gc-sections -Wl,-z,nocopyreloc -o $@ $(TARGET_GLOBAL_LD_DIRS) -Wl,-rpath-link=$(TARGET_OUT_INTERMEDIATE_LIBRARIES) $(call normalize-target-libraries,$(PRIVATE_ALL_SHARED_LIBRARIES)) $(TARGET_CRTBEGIN_DYNAMIC_O) $(PRIVATE_ALL_OBJECTS) $(call normalize-target-libraries,$(PRIVATE_ALL_STATIC_LIBRARIES)) $(TARGET_GLOBAL_LDFLAGS) $(PRIVATE_LDFLAGS) $(TARGET_FDO_LIB) $(TARGET_LIBGCC) $(TARGET_CRTEND_O)
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/full_passion.mk.PRODUCT_SDK_ADDON_COPY_FILES := 
# makefile (from `build/core/config.mk', line 146)
TARGET_BOOTLOADER_BOARD_NAME := smdkv210
# makefile (from `build/core/definitions.mk', line 221)
all-subdir-Iaidl-files = $(call all-Iaidl-files-under,.)
# makefile (from `build/core/config.mk', line 37)
SRC_LIBRARIES := libs
# makefile (from `device/htc/passion-common/CleanSpec.mk', line 47)
device_htc_passion-common_CleanSpec-mk_acs := 6@@
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/all_pico_languages.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product.mk', line 54)
get-all-product-makefiles = $(call get-product-makefiles,$(_find-android-products-files))
# makefile (from `build/core/clear_vars.mk', line 74)
LOCAL_COPY_HEADERS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_BRAND := 
# makefile (from `frameworks/base/CleanSpec.mk', line 72)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@@@@@@@@@@@@@@@@@@@ := rm -rf out/target/common/obj/JAVA_LIBRARIES/android_stubs_current_intermediates/src/com/trustedlogic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/core.mk.PRODUCT_BRAND := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.PRODUCT_CHARACTERISTICS := 
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 60)
BOARD_USES_COPYBIT := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].external/svox/pico/lang/PicoLangFrFrInSystem.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/envsetup.mk', line 119)
HOST_PREBUILT_TAG := linux-x86
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangItItInSystem.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_BRAND := 
# makefile (from `build/core/definitions.mk', line 956)
define transform-o-to-static-lib
@mkdir -p $(dir $@)
@rm -f $@
$(extract-and-include-target-whole-static-libs)
@echo "target StaticLib: $(PRIVATE_MODULE) ($@)"
$(hide) echo $(filter %.o, $^) | xargs $(TARGET_AR) $(TARGET_GLOBAL_ARFLAGS) $(PRIVATE_ARFLAGS) $@
endef
# makefile (from `build/core/Makefile', line 512)
installed_notice_html_gz := out/target/product/smdkv210/system/etc/NOTICE.html.gz
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_SDK_ADDON_DOC_MODULE := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/samsung/product/full_smdkv210.mk.PRODUCT_COPY_FILES := device/samsung/smdkv210/init.rc:root/init.rc device/samsung/smdkv210/vold.fstab:system/etc/vold.fstab device/samsung/smdkv210/egl.cfg:system/lib/egl/egl.cfg device/samsung/smdkv210/init.smdkv210.rc:root/init.smdkv210.rc device/samsung/smdkv210/ueventd.smdkv210.rc:root/ueventd.smdkv210.rc device/samsung/smdkv210/s3c-keypad.kl:system/usr/keylayout/s3c-keypad.kl device/samsung/smdkv210/media_profiles.xml:system/etc/media_profiles.xml LMC1800/sources/media/omx/secomxregistry:system/etc/secomxregistry LMC1800/sources/media/omx/libSEC_OMX_Core.so:system/lib/libSEC_OMX_Core.so LMC1800/sources/media/omx/libSEC_Resourcemanager.so:system/lib/libSEC_Resourcemanager.so LMC1800/sources/media/omx/libOMX.SEC.AVC.Decoder.so:system/lib/libOMX.SEC.AVC.Decoder.so LMC1800/sources/media/omx/libOMX.SEC.M4V.Decoder.so:system/lib/libOMX.SEC.M4V.Decoder.so LMC1800/sources/media/omx/libOMX.SEC.M2V.Decoder.so:system/lib/libOMX.SEC.M2V.Decoder.so LMC1800/sources/media/omx/libOMX.SEC.WMV.Decoder.so:system/lib/libOMX.SEC.WMV.Decoder.so LMC1800/sources/media/omx/libOMX.SEC.AVC.Encoder.so:system/lib/libOMX.SEC.AVC.Encoder.so LMC1800/sources/media/omx/libOMX.SEC.M4V.Encoder.so:system/lib/libOMX.SEC.M4V.Encoder.so LMC1800/sources/media/sf/libstagefright_amrnb_common.so:system/lib/libstagefright_amrnb_common.so LMC1800/sources/media/sf/libstagefright_enc_common.so:system/lib/libstagefright_enc_common.so LMC1800/sources/media/sf/libstagefright_avc_common.so:system/lib/libstagefright_avc_common.so LMC1800/sources/media/sf/libstagefright_foundation.so:system/lib/libstagefright_foundation.so LMC1800/sources/media/sf/libstagefright_color_conversion.so:system/lib/libstagefright_color_conversion.so LMC1800/sources/media/sf/libstagefright.so:system/lib/libstagefright.so LMC1800/sources/media/sf/libstagefright_omx.so:system/lib/libstagefright_omx.so LMC1800/sources/media/sf/liba52.so:system/lib/liba52.so LMC1800/sources/media/sf/playts:system/bin/playts LMC1800/sources/media/sf/libtv.so:system/lib/libtv.so LMC1800/sources/remount_ctl:system/bin/remount_ctl LMC1800/sources/ui/bootanimation.zip:system/media/bootanimation.zip LMC1800/sources/ui/PrimaryApp.apk:system/app/PrimaryApp.apk LMC1800/sources/ui/HomePage.apk:system/app/HomePage.apk LMC1800/sources/ui/FileManagers.apk:system/app/FileManagers.apk LMC1800/sources/ui/Game.apk:system/app/Game.apk LMC1800/sources/dvb/libmoddvb.so:system/lib/libmoddvb.so LMC1800/sources/dvb/libjnidvb.so:system/lib/libjnidvb.so LMC1800/sources/dvb/libdvbbinderclient.so:system/lib/libdvbbinderclient.so LMC1800/sources/dvb/libdvbcomm.so:system/lib/libdvbcomm.so LMC1800/sources/utiusb.ko:system/lib/modules/utiusb.ko LMC1800/sources/dvb_load:system/bin/dvb_load LMC1800/sources/busybox:system/bin/busybox LMC1800/sources/bash:system/bin/bash LMC1800/sources/dvb/dvb_server:system/bin/dvb_server external/wpa_supplicant/wpa_supplicant.conf:/system/etc/wifi/wpa_supplicant.conf LMC1800/sources/broadcom/dhd.ko:/system/etc/dhd.ko LMC1800/sources/broadcom/fw_bcm4329.bin:/system/etc/fw_bcm4329.bin LMC1800/sources/broadcom/nvram.txt:/system/etc/nvram.txt frameworks/base/data/etc/handheld_core_hardware.xml:system/etc/permissions/handheld_core_hardware.xml packages/wallpapers/LivePicker/android.software.live_wallpaper.xml:system/etc/permissions/android.software.live_wallpaper.xml device/samsung/smdkv210/kernel:kernel packages/wallpapers/LivePicker/android.software.live_wallpaper.xml:/system/etc/permissions/android.software.live_wallpaper.xml vendor/samsung/smdkv210/proprietary/pvrsrvinit:system/vendor/bin/pvrsrvinit vendor/samsung/smdkv210/proprietary/libEGL_POWERVR_SGX540_120.so:system/vendor/lib/egl/libEGL_POWERVR_SGX540_120.so vendor/samsung/smdkv210/proprietary/libGLESv1_CM_POWERVR_SGX540_120.so:system/vendor/lib/egl/libGLESv1_CM_POWERVR_SGX540_120.so vendor/samsung/smdkv210/proprietary/libGLESv2_POWERVR_SGX540_120.so:system/vendor/lib/egl/libGLESv2_POWERVR_SGX540_120.so vendor/samsung/smdkv210/proprietary/libglslcompiler.so:system/vendor/lib/libglslcompiler.so vendor/samsung/smdkv210/proprietary/libIMGegl.so:system/vendor/lib/libIMGegl.so vendor/samsung/smdkv210/proprietary/libpvr2d.so:system/vendor/lib/libpvr2d.so vendor/samsung/smdkv210/proprietary/libpvrANDROID_WSEGL.so:system/vendor/lib/libpvrANDROID_WSEGL.so vendor/samsung/smdkv210/proprietary/libPVRScopeServices.so:system/vendor/lib/libPVRScopeServices.so vendor/samsung/smdkv210/proprietary/libsrv_init.so:system/vendor/lib/libsrv_init.so vendor/samsung/smdkv210/proprietary/libsrv_um.so:system/vendor/lib/libsrv_um.so vendor/samsung/smdkv210/proprietary/libusc.so:system/vendor/lib/libusc.so vendor/samsung/smdkv210/proprietary/gralloc.s5pc110.so:system/vendor/lib/hw/gralloc.s5pc110.so development/data/etc/apns-conf.xml:system/etc/apns-conf.xml development/data/etc/vold.conf:system/etc/vold.conf frameworks/base/data/sounds/F1_MissedCall.ogg:system/media/audio/notifications/F1_MissedCall.ogg frameworks/base/data/sounds/F1_New_MMS.ogg:system/media/audio/notifications/F1_New_MMS.ogg frameworks/base/data/sounds/F1_New_SMS.ogg:system/media/audio/notifications/F1_New_SMS.ogg frameworks/base/data/sounds/Alarm_Buzzer.ogg:system/media/audio/alarms/Alarm_Buzzer.ogg frameworks/base/data/sounds/Alarm_Beep_01.ogg:system/media/audio/alarms/Alarm_Beep_01.ogg frameworks/base/data/sounds/Alarm_Beep_02.ogg:system/media/audio/alarms/Alarm_Beep_02.ogg frameworks/base/data/sounds/Alarm_Classic.ogg:system/media/audio/alarms/Alarm_Classic.ogg frameworks/base/data/sounds/Alarm_Beep_03.ogg:system/media/audio/alarms/Alarm_Beep_03.ogg frameworks/base/data/sounds/Alarm_Rooster_02.ogg:system/media/audio/alarms/Alarm_Rooster_02.ogg frameworks/base/data/sounds/Ring_Classic_02.ogg:system/media/audio/ringtones/Ring_Classic_02.ogg frameworks/base/data/sounds/Ring_Digital_02.ogg:system/media/audio/ringtones/Ring_Digital_02.ogg frameworks/base/data/sounds/Ring_Synth_04.ogg:system/media/audio/ringtones/Ring_Synth_04.ogg frameworks/base/data/sounds/Ring_Synth_02.ogg:system/media/audio/ringtones/Ring_Synth_02.ogg frameworks/base/data/sounds/newwavelabs/BeatPlucker.ogg:system/media/audio/ringtones/BeatPlucker.ogg frameworks/base/data/sounds/newwavelabs/BentleyDubs.ogg:system/media/audio/ringtones/BentleyDubs.ogg frameworks/base/data/sounds/newwavelabs/BirdLoop.ogg:system/media/audio/ringtones/BirdLoop.ogg frameworks/base/data/sounds/newwavelabs/CaribbeanIce.ogg:system/media/audio/ringtones/CaribbeanIce.ogg frameworks/base/data/sounds/newwavelabs/CurveBall.ogg:system/media/audio/ringtones/CurveBall.ogg frameworks/base/data/sounds/newwavelabs/EtherShake.ogg:system/media/audio/ringtones/EtherShake.ogg frameworks/base/data/sounds/newwavelabs/FriendlyGhost.ogg:system/media/audio/ringtones/FriendlyGhost.ogg frameworks/base/data/sounds/newwavelabs/GameOverGuitar.ogg:system/media/audio/ringtones/GameOverGuitar.ogg frameworks/base/data/sounds/newwavelabs/Growl.ogg:system/media/audio/ringtones/Growl.ogg frameworks/base/data/sounds/newwavelabs/InsertCoin.ogg:system/media/audio/ringtones/InsertCoin.ogg frameworks/base/data/sounds/newwavelabs/LoopyLounge.ogg:system/media/audio/ringtones/LoopyLounge.ogg frameworks/base/data/sounds/newwavelabs/LoveFlute.ogg:system/media/audio/ringtones/LoveFlute.ogg frameworks/base/data/sounds/newwavelabs/MidEvilJaunt.ogg:system/media/audio/ringtones/MidEvilJaunt.ogg frameworks/base/data/sounds/newwavelabs/MildlyAlarming.ogg:system/media/audio/ringtones/MildlyAlarming.ogg frameworks/base/data/sounds/newwavelabs/NewPlayer.ogg:system/media/audio/ringtones/NewPlayer.ogg frameworks/base/data/sounds/newwavelabs/Noises1.ogg:system/media/audio/ringtones/Noises1.ogg frameworks/base/data/sounds/newwavelabs/Noises2.ogg:system/media/audio/ringtones/Noises2.ogg frameworks/base/data/sounds/newwavelabs/Noises3.ogg:system/media/audio/ringtones/Noises3.ogg frameworks/base/data/sounds/newwavelabs/OrganDub.ogg:system/media/audio/ringtones/OrganDub.ogg frameworks/base/data/sounds/newwavelabs/RomancingTheTone.ogg:system/media/audio/ringtones/RomancingTheTone.ogg frameworks/base/data/sounds/newwavelabs/SitarVsSitar.ogg:system/media/audio/ringtones/SitarVsSitar.ogg frameworks/base/data/sounds/newwavelabs/SpringyJalopy.ogg:system/media/audio/ringtones/SpringyJalopy.ogg frameworks/base/data/sounds/newwavelabs/Terminated.ogg:system/media/audio/ringtones/Terminated.ogg frameworks/base/data/sounds/newwavelabs/TwirlAway.ogg:system/media/audio/ringtones/TwirlAway.ogg frameworks/base/data/sounds/newwavelabs/VeryAlarmed.ogg:system/media/audio/ringtones/VeryAlarmed.ogg frameworks/base/data/sounds/newwavelabs/World.ogg:system/media/audio/ringtones/World.ogg frameworks/base/data/sounds/newwavelabs/CaffeineSnake.ogg:system/media/audio/notifications/CaffeineSnake.ogg frameworks/base/data/sounds/newwavelabs/DearDeer.ogg:system/media/audio/notifications/DearDeer.ogg frameworks/base/data/sounds/newwavelabs/DontPanic.ogg:system/media/audio/notifications/DontPanic.ogg frameworks/base/data/sounds/newwavelabs/Highwire.ogg:system/media/audio/notifications/Highwire.ogg frameworks/base/data/sounds/newwavelabs/KzurbSonar.ogg:system/media/audio/notifications/KzurbSonar.ogg frameworks/base/data/sounds/newwavelabs/OnTheHunt.ogg:system/media/audio/notifications/OnTheHunt.ogg frameworks/base/data/sounds/newwavelabs/Voila.ogg:system/media/audio/notifications/Voila.ogg frameworks/base/data/sounds/notifications/Beat_Box_Android.ogg:system/media/audio/notifications/Beat_Box_Android.ogg frameworks/base/data/sounds/notifications/Heaven.ogg:system/media/audio/notifications/Heaven.ogg frameworks/base/data/sounds/notifications/TaDa.ogg:system/media/audio/notifications/TaDa.ogg frameworks/base/data/sounds/notifications/Tinkerbell.ogg:system/media/audio/notifications/Tinkerbell.ogg frameworks/base/data/sounds/effects/Effect_Tick.ogg:system/media/audio/ui/Effect_Tick.ogg frameworks/base/data/sounds/effects/KeypressStandard.ogg:system/media/audio/ui/KeypressStandard.ogg frameworks/base/data/sounds/effects/KeypressSpacebar.ogg:system/media/audio/ui/KeypressSpacebar.ogg frameworks/base/data/sounds/effects/KeypressDelete.ogg:system/media/audio/ui/KeypressDelete.ogg frameworks/base/data/sounds/effects/KeypressReturn.ogg:system/media/audio/ui/KeypressReturn.ogg frameworks/base/data/sounds/effects/VideoRecord.ogg:system/media/audio/ui/VideoRecord.ogg frameworks/base/data/sounds/effects/camera_click.ogg:system/media/audio/ui/camera_click.ogg frameworks/base/data/sounds/newwavelabs/CrazyDream.ogg:system/media/audio/ringtones/CrazyDream.ogg frameworks/base/data/sounds/newwavelabs/DreamTheme.ogg:system/media/audio/ringtones/DreamTheme.ogg external/svox/pico/lang/de-DE_gl0_sg.bin:system/tts/lang_pico/de-DE_gl0_sg.bin external/svox/pico/lang/de-DE_ta.bin:system/tts/lang_pico/de-DE_ta.bin external/svox/pico/lang/en-GB_kh0_sg.bin:system/tts/lang_pico/en-GB_kh0_sg.bin external/svox/pico/lang/en-GB_ta.bin:system/tts/lang_pico/en-GB_ta.bin external/svox/pico/lang/en-US_lh0_sg.bin:system/tts/lang_pico/en-US_lh0_sg.bin external/svox/pico/lang/en-US_ta.bin:system/tts/lang_pico/en-US_ta.bin external/svox/pico/lang/es-ES_zl0_sg.bin:system/tts/lang_pico/es-ES_zl0_sg.bin external/svox/pico/lang/es-ES_ta.bin:system/tts/lang_pico/es-ES_ta.bin external/svox/pico/lang/fr-FR_nk0_sg.bin:system/tts/lang_pico/fr-FR_nk0_sg.bin external/svox/pico/lang/fr-FR_ta.bin:system/tts/lang_pico/fr-FR_ta.bin external/svox/pico/lang/it-IT_cm0_sg.bin:system/tts/lang_pico/it-IT_cm0_sg.bin external/svox/pico/lang/it-IT_ta.bin:system/tts/lang_pico/it-IT_ta.bin
# makefile (from `external/webkit/CleanSpec.mk', line 55)
external_webkit_CleanSpec-mk_acs := 6@@@@@@@@@
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/full.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/Makefile', line 703)
INTERNAL_SYSTEMIMAGE_FILES := out/target/product/smdkv210/system/bin/tvrecv out/target/product/smdkv210/system/etc/vold.fstab out/target/product/smdkv210/system/lib/egl/egl.cfg out/target/product/smdkv210/system/usr/keylayout/s3c-keypad.kl out/target/product/smdkv210/system/etc/media_profiles.xml out/target/product/smdkv210/system/etc/secomxregistry out/target/product/smdkv210/system/lib/libSEC_OMX_Core.so out/target/product/smdkv210/system/lib/libSEC_Resourcemanager.so out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M2V.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.WMV.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Encoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Encoder.so out/target/product/smdkv210/system/lib/libstagefright_amrnb_common.so out/target/product/smdkv210/system/lib/libstagefright_enc_common.so out/target/product/smdkv210/system/lib/libstagefright_avc_common.so out/target/product/smdkv210/system/lib/libstagefright_foundation.so out/target/product/smdkv210/system/lib/libstagefright_color_conversion.so out/target/product/smdkv210/system/lib/libstagefright.so out/target/product/smdkv210/system/lib/libstagefright_omx.so out/target/product/smdkv210/system/lib/liba52.so out/target/product/smdkv210/system/bin/playts out/target/product/smdkv210/system/lib/libtv.so out/target/product/smdkv210/system/bin/remount_ctl out/target/product/smdkv210/system/media/bootanimation.zip out/target/product/smdkv210/system/app/PrimaryApp.apk out/target/product/smdkv210/system/app/HomePage.apk out/target/product/smdkv210/system/app/FileManagers.apk out/target/product/smdkv210/system/app/Game.apk out/target/product/smdkv210/system/lib/libmoddvb.so out/target/product/smdkv210/system/lib/libjnidvb.so out/target/product/smdkv210/system/lib/libdvbbinderclient.so out/target/product/smdkv210/system/lib/libdvbcomm.so out/target/product/smdkv210/system/lib/modules/utiusb.ko out/target/product/smdkv210/system/bin/dvb_load out/target/product/smdkv210/system/bin/busybox out/target/product/smdkv210/system/bin/bash out/target/product/smdkv210/system/bin/dvb_server out/target/product/smdkv210/system/etc/wifi/wpa_supplicant.conf out/target/product/smdkv210/system/etc/dhd.ko out/target/product/smdkv210/system/etc/fw_bcm4329.bin out/target/product/smdkv210/system/etc/nvram.txt out/target/product/smdkv210/system/etc/permissions/handheld_core_hardware.xml out/target/product/smdkv210/system/etc/permissions/android.software.live_wallpaper.xml out/target/product/smdkv210/system/etc/permissions/android.software.live_wallpaper.xml out/target/product/smdkv210/system/vendor/bin/pvrsrvinit out/target/product/smdkv210/system/vendor/lib/egl/libEGL_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/egl/libGLESv1_CM_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/egl/libGLESv2_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/libglslcompiler.so out/target/product/smdkv210/system/vendor/lib/libIMGegl.so out/target/product/smdkv210/system/vendor/lib/libpvr2d.so out/target/product/smdkv210/system/vendor/lib/libpvrANDROID_WSEGL.so out/target/product/smdkv210/system/vendor/lib/libPVRScopeServices.so out/target/product/smdkv210/system/vendor/lib/libsrv_init.so out/target/product/smdkv210/system/vendor/lib/libsrv_um.so out/target/product/smdkv210/system/vendor/lib/libusc.so out/target/product/smdkv210/system/vendor/lib/hw/gralloc.s5pc110.so out/target/product/smdkv210/system/etc/apns-conf.xml out/target/product/smdkv210/system/etc/vold.conf out/target/product/smdkv210/system/media/audio/notifications/F1_MissedCall.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_New_MMS.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_New_SMS.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Buzzer.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_01.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_02.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Classic.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_03.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Rooster_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Classic_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Digital_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_04.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/BeatPlucker.ogg out/target/product/smdkv210/system/media/audio/ringtones/BentleyDubs.ogg out/target/product/smdkv210/system/media/audio/ringtones/BirdLoop.ogg out/target/product/smdkv210/system/media/audio/ringtones/CaribbeanIce.ogg out/target/product/smdkv210/system/media/audio/ringtones/CurveBall.ogg out/target/product/smdkv210/system/media/audio/ringtones/EtherShake.ogg out/target/product/smdkv210/system/media/audio/ringtones/FriendlyGhost.ogg out/target/product/smdkv210/system/media/audio/ringtones/GameOverGuitar.ogg out/target/product/smdkv210/system/media/audio/ringtones/Growl.ogg out/target/product/smdkv210/system/media/audio/ringtones/InsertCoin.ogg out/target/product/smdkv210/system/media/audio/ringtones/LoopyLounge.ogg out/target/product/smdkv210/system/media/audio/ringtones/LoveFlute.ogg out/target/product/smdkv210/system/media/audio/ringtones/MidEvilJaunt.ogg out/target/product/smdkv210/system/media/audio/ringtones/MildlyAlarming.ogg out/target/product/smdkv210/system/media/audio/ringtones/NewPlayer.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises1.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises2.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises3.ogg out/target/product/smdkv210/system/media/audio/ringtones/OrganDub.ogg out/target/product/smdkv210/system/media/audio/ringtones/RomancingTheTone.ogg out/target/product/smdkv210/system/media/audio/ringtones/SitarVsSitar.ogg out/target/product/smdkv210/system/media/audio/ringtones/SpringyJalopy.ogg out/target/product/smdkv210/system/media/audio/ringtones/Terminated.ogg out/target/product/smdkv210/system/media/audio/ringtones/TwirlAway.ogg out/target/product/smdkv210/system/media/audio/ringtones/VeryAlarmed.ogg out/target/product/smdkv210/system/media/audio/ringtones/World.ogg out/target/product/smdkv210/system/media/audio/notifications/CaffeineSnake.ogg out/target/product/smdkv210/system/media/audio/notifications/DearDeer.ogg out/target/product/smdkv210/system/media/audio/notifications/DontPanic.ogg out/target/product/smdkv210/system/media/audio/notifications/Highwire.ogg out/target/product/smdkv210/system/media/audio/notifications/KzurbSonar.ogg out/target/product/smdkv210/system/media/audio/notifications/OnTheHunt.ogg out/target/product/smdkv210/system/media/audio/notifications/Voila.ogg out/target/product/smdkv210/system/media/audio/notifications/Beat_Box_Android.ogg out/target/product/smdkv210/system/media/audio/notifications/Heaven.ogg out/target/product/smdkv210/system/media/audio/notifications/TaDa.ogg out/target/product/smdkv210/system/media/audio/notifications/Tinkerbell.ogg out/target/product/smdkv210/system/media/audio/ui/Effect_Tick.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressStandard.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressSpacebar.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressDelete.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressReturn.ogg out/target/product/smdkv210/system/media/audio/ui/VideoRecord.ogg out/target/product/smdkv210/system/media/audio/ui/camera_click.ogg out/target/product/smdkv210/system/media/audio/ringtones/CrazyDream.ogg out/target/product/smdkv210/system/media/audio/ringtones/DreamTheme.ogg out/target/product/smdkv210/system/tts/lang_pico/de-DE_gl0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/de-DE_ta.bin out/target/product/smdkv210/system/tts/lang_pico/en-GB_kh0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/en-GB_ta.bin out/target/product/smdkv210/system/tts/lang_pico/en-US_lh0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/en-US_ta.bin out/target/product/smdkv210/system/tts/lang_pico/es-ES_zl0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/es-ES_ta.bin out/target/product/smdkv210/system/tts/lang_pico/fr-FR_nk0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/fr-FR_ta.bin out/target/product/smdkv210/system/tts/lang_pico/it-IT_cm0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/it-IT_ta.bin out/target/product/smdkv210/system/build.prop out/target/product/smdkv210/system/etc/event-log-tags out/target/product/smdkv210/system/etc/security/otacerts.zip
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.inherited := 
# makefile (from `build/core/config.mk', line 297)
TARGET_PROJECT_INCLUDES := system/core/include hardware/libhardware/include hardware/libhardware_legacy/include hardware/ril/include dalvik/libnativehelper/include frameworks/base/include frameworks/base/opengl/include frameworks/base/native/include external/skia/include out/target/product/smdkv210/obj/include
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_PACKAGES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/languages_small.mk.PRODUCT_MODEL := 
# makefile (from `device/samsung/smdkv210/device.mk', line 235)
LOCAL_KERNEL := device/samsung/smdkv210/kernel
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.DEVICE_PACKAGE_OVERLAYS := 
# environment
OUT = /home/lnt/stb/out/target/product/smdkv210
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEnGBInSystem.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/sim.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/combo/select.mk', line 29)
HOST_CXX := g++
# makefile (from `build/target/product/full.mk', line 53)
PRODUCTS.build/target/product/full.mk.INHERITS_FROM := build/target/product/generic.mk build/target/product/languages_small.mk external/svox/pico/lang/all_pico_languages.mk frameworks/base/data/sounds/OriginalAudio.mk
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion/passion.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/full.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/envsetup.mk', line 211)
BUILD_OUT_EXECUTABLES := out/host/linux-x86/bin
# makefile (from `build/core/main.mk', line 67)
VERSION_CHECK_SEQUENCE_NUMBER := 2
# makefile (from `out/versions_checked.mk', line 1)
VERSIONS_CHECKED := 2
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].device/sample/products/sample_addon.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/generic.mk]].build/target/product/generic.mk.PRODUCT_DEVICE := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/core.mk.inherited := 
# makefile (from `build/core/definitions.mk', line 818)
define transform-c-to-o
$(transform-c-to-o-no-deps)
$(hide) $(transform-d-to-p)
endef
# makefile (from `build/core/product_config.mk', line 300)
PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/config.mk', line 147)
TARGET_CPU_ABI := armeabi-v7a
# makefile (from `frameworks/base/CleanSpec.mk', line 68)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@@@@@@@@@@@@@@@@@@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/libreverbtest_intermediates
# makefile (from `build/core/envsetup.mk', line 259)
TARGET_ROOT_OUT := out/target/product/smdkv210/root
# makefile (from `frameworks/base/CleanSpec.mk', line 51)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@@@ := rm -rf out/target/common/obj/JAVA_LIBRARIES/framework_intermediates/src/core/java/android/app
# makefile (from `device/samsung/smdkv210/BoardConfig.mk', line 62)
BOARD_USES_HDMI := true
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/passion.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/htc/passion/full_passion.mk.PRODUCT_BRAND := generic
# makefile (from `build/core/envsetup.mk', line 188)
HOST_OUT_release := out/host/linux-x86
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor.mk.PRODUCT_LOCALES := 
# makefile (from `build/core/definitions.mk', line 422)
normalize-target-libraries = $(call normalize-libraries,$(1))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/definitions.mk', line 101)
true-or-empty = $(filter true, $(1))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.PRODUCT_MODEL := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/samsung/product/full_smdkv210.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/tasks/cts.mk', line 130)
RUNNER_INTERMEDIATES := out/target/common/obj/JAVA_LIBRARIES/core-junitrunner_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/htc/passion-common/media_a1026.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic.mk.PRODUCT_PACKAGES := AccountAndSyncSettings DeskClock AlarmProvider Bluetooth Calculator Calendar Camera CertInstaller DrmProvider Email Gallery3D LatinIME Launcher2 Mms Music Provision Protips QuickSearchBox Settings Sync SystemUI Updater CalendarProvider SyncProvider bouncycastle com.android.location.provider com.android.location.provider.xml core core-junit create_test_dmtrace dalvikvm dexdeps dexdump dexlist dexopt dmtracedump dvz dx ext framework-res hprof-conv icu.dat jasmin jasmin.jar libcrypto libdex libdvm libexpat libicui18n libicuuc libjavacore libnativehelper libnfc_ndef libsqlite_jni libssl libz sqlite-jdbc Browser Contacts Home HTMLViewer Phone ApplicationsProvider ContactsProvider DownloadProvider DownloadProviderUi MediaProvider PicoTts SettingsProvider TelephonyProvider TtsService VpnServices UserDictionaryProvider PackageInstaller DefaultContainerService Bugreport
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/generic.mk.PRODUCT_MODEL := 
# makefile (from `build/core/definitions.mk', line 1549)
define copy-file-to-new-target
@mkdir -p $(dir $@)
$(hide) $(ACP) -fp $< $@
endef
# makefile (from `frameworks/base/CleanSpec.mk', line 49)
INTERNAL_CLEAN_STEP.frameworks_base_CleanSpec-mk_acs6@@@@ := rm -rf out/target/common/obj/JAVA_LIBRARIES/framework_intermediates/src/core/java/android/backup
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sdk.mk]].build/target/product/core.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_PACKAGE_OVERLAYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.PRODUCT_SDK_ADDON_NAME := 
# makefile (from `external/webkit/CleanSpec.mk', line 47)
INTERNAL_CLEAN_STEP.external_webkit_CleanSpec-mk_acs6@ := rm -rf out/target/product/smdkv210/obj/STATIC_LIBRARIES/libwebcore_intermediates
# makefile (from `build/core/definitions.mk', line 1725)
define add-radio-file-internal
INSTALLED_RADIOIMAGE_TARGET += $$(PRODUCT_OUT)/$(2)
ALL_PREBUILT += $$(PRODUCT_OUT)/$(2)
$$(PRODUCT_OUT)/$(2) : $$(LOCAL_PATH)/$(1) | $$(ACP)
	$$(transform-prebuilt-to-target)
endef
# makefile (from `build/core/definitions.mk', line 434)
module-built-files = $(foreach module,$(1),$(ALL_MODULES.$(module).BUILT))
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].frameworks/base/data/sounds/OriginalAudio.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].device/common/gps/gps_us_supl.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].vendor/samsung/smdkv210/device-vendor-blobs.mk.PRODUCT_TAGS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.PRODUCT_MODEL := 
# makefile (from `build/core/definitions.mk', line 1749)
inherit-package =   $(eval $(call inherit-package-internal,$(1),$(2),$(3),$(4),$(5),$(6),$(7)))
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.device/samsung/product/full_smdkv210.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.PRODUCT_TAGS := 
# makefile (from `build/core/envsetup.mk', line 223)
HOST_OUT_COMMON_INTERMEDIATES := out/host/common/obj
# makefile (from `build/core/product_config.mk', line 194)
PRODUCTS.build/target/product/generic.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/full.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/sim.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/combo/select.mk', line 38)
TARGET_HAVE_CALL_STACKS := 1
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/core.mk.PRODUCT_COPY_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].frameworks/base/data/sounds/OriginalAudio.mk.DEVICE_PACKAGE_OVERLAYS := 
# makefile (from `external/clearsilver/CleanSpec.mk', line 57)
INTERNAL_CLEAN_STEP.external_clearsilver_CleanSpec-mk_acs6@@@@@@@@ := rm -rf out/host/linux-x86/obj/SHARED_LIBRARIES/libclearsilver-jni_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.inherited := 
# makefile (from `build/core/clear_vars.mk', line 82)
LOCAL_JAR_MANIFEST := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.inherited := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].external/svox/pico/lang/PicoLangEnUsInSystem.mk.PRODUCT_NAME := 
# makefile (from `build/core/clear_vars.mk', line 50)
LOCAL_PREBUILT_JAVA_LIBRARIES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/sample/products/sample_addon.mk]].build/target/product/sdk.mk.PRODUCT_DEFAULT_WIFI_CHANNELS := 
# default
F77FLAGS = $(FFLAGS)
# makefile (from `build/core/definitions.mk', line 516)
empty := 
# makefile (from `build/core/clear_vars.mk', line 27)
LOCAL_PREBUILT_OBJ_FILES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.PRODUCT_MANUFACTURER := 
# makefile (from `build/core/binary.mk', line 306)
asm_sources_S := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/full.mk.PRODUCT_PROPERTY_OVERRIDES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_full.mk.PRODUCT_CONTRIBUTORS_FILE := 
# makefile (from `build/core/Makefile', line 485)
target_notice_file_html_gz := out/target/product/smdkv210/obj/NOTICE.html.gz
# makefile (from `device/htc/passion-common/CleanSpec.mk', line 46)
INTERNAL_CLEAN_STEP.device_htc_passion-common_CleanSpec-mk_acs6@ := rm -rf out/target/product/smdkv210/obj/SHARED_LIBRARIES/gps.mahimahi_intermediates
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/sim.mk]].build/target/product/generic.mk.PRODUCT_NAME := generic
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/core.mk]].build/target/product/core.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/clear_vars.mk', line 86)
LOCAL_JARJAR_RULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangDeDeInSystem.mk.PRODUCT_BRAND := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/htc/passion/full_passion.mk]].build/target/product/languages_small.mk.PRODUCT_SDK_ADDON_COPY_MODULES := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].build/target/product/core.mk.PRODUCT_OTA_PUBLIC_KEYS := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[build/target/product/full.mk]].build/target/product/languages_small.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 194)
_nic.PRODUCTS.[[device/samsung/product/full_smdkv210.mk]].external/svox/pico/lang/PicoLangEsEsInSystem.mk.PRODUCT_NAME := 
# makefile (from `build/core/product_config.mk', line 243)
PRODUCT_BRAND := generic
# variable set hash-table stats:
# Load=2793/4096=68%, Rehash=2, Collisions=59280/64422=92%

# Pattern-specific Variable Values

# No pattern-specific variable values.

# Directories

# vendor/htc/passion-common: could not be stat'd.
# packages/wallpapers/Basic/RCS: could not be stat'd.
# hardware/ti/omap3 (device 2051, inode 1073719): 9 files, no impossibilities.
# external/wpa_supplicant/SCCS: could not be stat'd.
# packages/providers/ContactsProvider/SCCS: could not be stat'd.
# system/extras (device 2051, inode 1219281): 16 files, no impossibilities.
# external/clearsilver (device 2051, inode 532952): 40 files, no impossibilities.
# external/esd (device 2051, inode 532466): 4 files, no impossibilities.
# vendor/google/etc: could not be stat'd.
# prebuilt/RCS: could not be stat'd.
# external/jpeg/RCS: could not be stat'd.
# external/giflib/RCS: could not be stat'd.
# external/mtpd (device 2051, inode 271606): 11 files, no impossibilities.
# bionic/libc/kernel/arch-arm/asm/RCS: could not be stat'd.
# sdk/SCCS: could not be stat'd.
# vendor/samsung/smdkv210 (device 2051, inode 539046): 9 files, no impossibilities.
# packages/apps/Provision/SCCS: could not be stat'd.
# external/nist-sip (device 2051, inode 2246057): 9 files, no impossibilities.
# build/core/RCS: could not be stat'd.
# external/expat (device 2051, inode 1585186): 26 files, no impossibilities.
# external/libpng/RCS: could not be stat'd.
# . (device 2051, inode 262232): 25 files, no impossibilities.
# system/media/RCS: could not be stat'd.
# dalvik (device 2051, inode 530779): 20 files, no impossibilities.
# external/oauth (device 2051, inode 2243260): 5 files, no impossibilities.
# external/jdiff (device 2051, inode 534465): 10 files, no impossibilities.
# packages/apps/SpeechRecorder/RCS: could not be stat'd.
# device/samsung/product (device 2051, inode 1995012): 5 files, no impossibilities.
# external/v8/RCS: could not be stat'd.
# vendor/google: could not be stat'd.
# packages/providers/DownloadProvider/RCS: could not be stat'd.
# external/guava (device 2051, inode 794635): 13 files, no impossibilities.
# external/embunit (device 2051, inode 532485): 7 files, no impossibilities.
# device/samsung/smdkc110/RCS: could not be stat'd.
# external/expat/SCCS: could not be stat'd.
# external/nist-sip/SCCS: could not be stat'd.
# packages/apps/Calculator/SCCS: could not be stat'd.
# out/target/product/smdkv210/obj/lib/RCS: could not be stat'd.
# frameworks/base/RCS: could not be stat'd.
# external/svox/pico/lang/RCS: could not be stat'd.
# packages/apps/Gallery3D (device 2051, inode 2355121): 10 files, no impossibilities.
# hardware/libhardware (device 2051, inode 1595085): 10 files, no impossibilities.
# packages/apps/Calendar/RCS: could not be stat'd.
# external/grub/RCS: could not be stat'd.
# device/samsung/product/SCCS: could not be stat'd.
# dalvik/SCCS: could not be stat'd.
# external/chromium/RCS: could not be stat'd.
# build/core/combo/arch/arm (device 2051, inode 1336175): 7 files, no impossibilities.
# external/jdiff/SCCS: could not be stat'd.
# external/mtpd/SCCS: could not be stat'd.
# packages/apps/Email/RCS: could not be stat'd.
# external/embunit/SCCS: could not be stat'd.
# external/netcat (device 2051, inode 1718541): 16 files, no impossibilities.
# external/icu4c (device 2051, inode 1583344): 40 files, no impossibilities.
# packages/apps/Camera (device 2051, inode 2361070): 11 files, no impossibilities.
# external/bluetooth/bluez/SCCS: could not be stat'd.
# device/htc/common (device 2051, inode 2125112): 11 files, no impossibilities.
# vendor/google/SCCS: could not be stat'd.
# packages/providers/ApplicationsProvider/SCCS: could not be stat'd.
# external/astl (device 2051, inode 2241047): 9 files, no impossibilities.
# bionic/libc/include/RCS: could not be stat'd.
# hardware/qcom/gps (device 2051, inode 2257383): 4 files, no impossibilities.
# bionic/libc/include/arpa/SCCS: could not be stat'd.
# system/wlan/ti/RCS: could not be stat'd.
# packages/wallpapers/Basic (device 2051, inode 1715930): 10 files, no impossibilities.
# packages/apps/Bluetooth (device 2051, inode 1843378): 8 files, no impossibilities.
# external/libxml2/RCS: could not be stat'd.
# system/netd/RCS: could not be stat'd.
# packages/apps/Contacts (device 2051, inode 2361249): 11 files, no impossibilities.
# external/elfcopy (device 2051, inode 940340): 21 files, no impossibilities.
# hardware/libhardware/SCCS: could not be stat'd.
# external/jsr305 (device 2051, inode 271035): 14 files, no impossibilities.
# device (device 2051, inode 286905): 6 files, no impossibilities.
# external/bzip2 (device 2051, inode 532810): 64 files, no impossibilities.
# external/netcat/SCCS: could not be stat'd.
# packages/inputmethods/PinyinIME/RCS: could not be stat'd.
# external/emma (device 2051, inode 1342623): 19 files, no impossibilities.
# external/oauth/SCCS: could not be stat'd.
# external/libpcap (device 2051, inode 2362334): 89 files, no impossibilities.
# packages/wallpapers/MagicSmoke (device 2051, inode 1840629): 10 files, no impossibilities.
# build/core/combo/arch/arm/SCCS: could not be stat'd.
# external/bsdiff (device 2051, inode 1980723): 11 files, no impossibilities.
# device/htc/common/SCCS: could not be stat'd.
# external/iproute2/RCS: could not be stat'd.
# frameworks/base/data/sounds (device 2051, inode 1593364): 40 files, no impossibilities.
# ndk/RCS: could not be stat'd.
# out/host/linux-x86/sdk/SCCS: could not be stat'd.
# external/strace (device 2051, inode 2248235): 65 files, no impossibilities.
# system/media (device 2051, inode 1219217): 4 files, no impossibilities.
# packages/apps/Browser/RCS: could not be stat'd.
# external/openssl/RCS: could not be stat'd.
# external/bluetooth/hcidump/RCS: could not be stat'd.
# packages/apps/Contacts/SCCS: could not be stat'd.
# packages/apps/Gallery3D/SCCS: could not be stat'd.
# packages/apps/Bluetooth/SCCS: could not be stat'd.
# device/htc/passion (device 2051, inode 2126984): 21 files, no impossibilities.
# external/xmlwriter/RCS: could not be stat'd.
# packages/wallpapers/Basic/SCCS: could not be stat'd.
# packages/apps/SoundRecorder/SCCS: could not be stat'd.
# device/common/gps/RCS: could not be stat'd.
# external/gtest (device 2051, inode 1582996): 16 files, no impossibilities.
# external/blktrace/RCS: could not be stat'd.
# external/libpcap/SCCS: could not be stat'd.
# out/target/product/smdkv210/obj/lib/SCCS: could not be stat'd.
# external/speex/RCS: could not be stat'd.
# packages/apps/PackageInstaller/RCS: could not be stat'd.
# external/ppp/RCS: could not be stat'd.
# build/core/combo/RCS: could not be stat'd.
# build/SCCS: could not be stat'd.
# external/sonivox/SCCS: could not be stat'd.
# external/emma/SCCS: could not be stat'd.
# external/bsdiff/SCCS: could not be stat'd.
# external/tcpdump (device 2051, inode 2363198): 230 files, no impossibilities.
# packages/inputmethods/OpenWnn (device 2051, inode 1841730): 11 files, no impossibilities.
# packages/apps/Launcher2/SCCS: could not be stat'd.
# external/strace/SCCS: could not be stat'd.
# external/ipsec-tools/RCS: could not be stat'd.
# packages/apps/Calculator (device 2051, inode 1060667): 11 files, no impossibilities.
# device/htc/passion/SCCS: could not be stat'd.
# external/zlib/RCS: could not be stat'd.
# system/media/SCCS: could not be stat'd.
# external/bluetooth/hcidump/SCCS: could not be stat'd.
# packages/apps/CertInstaller/RCS: could not be stat'd.
# external/yaffs2/RCS: could not be stat'd.
# external/jhead/SCCS: could not be stat'd.
# packages/apps/Provision (device 2051, inode 790172): 9 files, no impossibilities.
# external/tremolo (device 2051, inode 2245638): 8 files, no impossibilities.
# external/e2fsprogs/RCS: could not be stat'd.
# external/jsr305/SCCS: could not be stat'd.
# external/tcpdump/SCCS: could not be stat'd.
# frameworks/base/data/sounds/RCS: could not be stat'd.
# packages/inputmethods/OpenWnn/SCCS: could not be stat'd.
# sdk/RCS: could not be stat'd.
# packages/apps/Music (device 2051, inode 790640): 11 files, no impossibilities.
# external/wpa_supplicant_6/RCS: could not be stat'd.
# external/webkit/SCCS: could not be stat'd.
# bootable/diskinstaller/SCCS: could not be stat'd.
# external/safe-iop/RCS: could not be stat'd.
# external/clearsilver/SCCS: could not be stat'd.
# packages/apps/HTMLViewer (device 2051, inode 1843347): 9 files, no impossibilities.
# device/sample/products (device 2051, inode 286914): 5 files, no impossibilities.
# bionic/RCS: could not be stat'd.
# external/tagsoup (device 2051, inode 795493): 4 files, no impossibilities.
# vendor/*/smdkv210: could not be stat'd.
# external/libgsm/RCS: could not be stat'd.
# system/core/SCCS: could not be stat'd.
# external/easymock (device 2051, inode 795030): 6 files, no impossibilities.
# external/ping (device 2051, inode 941685): 8 files, no impossibilities.
# out/host/linux-x86/bin (device 2051, inode 544818): 111 files, no impossibilities.
# packages/apps/Stk (device 2051, inode 1338916): 9 files, no impossibilities.
# external/dnsmasq (device 2051, inode 1854699): 22 files, no impossibilities.
# external/icu4c/SCCS: could not be stat'd.
# external/tagsoup/RCS: could not be stat'd.
# packages/providers/TelephonyProvider (device 2051, inode 1578432): 9 files, no impossibilities.
# bionic/libc/kernel/common/asm-generic/SCCS: could not be stat'd.
# packages/providers/ContactsProvider/RCS: could not be stat'd.
# packages/apps/PackageInstaller (device 2051, inode 269185): 9 files, no impossibilities.
# external/bluetooth/glib (device 2051, inode 937071): 69 files, no impossibilities.
# external/tvplay/tvrecv (device 2051, inode 1055463): 35 files, 14 impossibilities.
# libcore (device 2051, inode 1463004): 22 files, no impossibilities.
# external/stlport/RCS: could not be stat'd.
# packages/apps/Music/SCCS: could not be stat'd.
# prebuilt/sdk/6 (device 2051, inode 1070371): 4 files, no impossibilities.
# packages/apps/HTMLViewer/SCCS: could not be stat'd.
# device/sample/products/SCCS: could not be stat'd.
# hardware/broadcom/wlan/RCS: could not be stat'd.
# out/target/product/smdkv210/RCS: could not be stat'd.
# external/bluetooth/glib/RCS: could not be stat'd.
# packages/wallpapers/MusicVisualization/RCS: could not be stat'd.
# bionic/libc/kernel/common/linux/byteorder (device 2051, inode 1194618): 7 files, no impossibilities.
# hardware/ti/wlan/RCS: could not be stat'd.
# hardware/ril/RCS: could not be stat'd.
# external/tvplay (device 2051, inode 936533): 5 files, no impossibilities.
# external/wpa_supplicant/RCS: could not be stat'd.
# hardware/qcom/gps/RCS: could not be stat'd.
# external/dnsmasq/SCCS: could not be stat'd.
# packages/providers/UserDictionaryProvider (device 2051, inode 1578326): 10 files, no impossibilities.
# external/dhcpcd/RCS: could not be stat'd.
# packages/providers/TelephonyProvider/SCCS: could not be stat'd.
# hardware/qcom/media (device 2051, inode 2254606): 6 files, no impossibilities.
# packages/apps/Stk/SCCS: could not be stat'd.
# packages/apps/PackageInstaller/SCCS: could not be stat'd.
# external/ping/SCCS: could not be stat'd.
# external/iptables (device 2051, inode 405373): 32 files, no impossibilities.
# external/kernel-headers/SCCS: could not be stat'd.
# bionic/libc/kernel/common/linux/RCS: could not be stat'd.
# external/v8 (device 2051, inode 793751): 19 files, no impossibilities.
# external/netperf/SCCS: could not be stat'd.
# hardware/broadcom/wlan (device 2051, inode 1985390): 4 files, no impossibilities.
# build/core/tasks/SCCS: could not be stat'd.
# external/bzip2/SCCS: could not be stat'd.
# external/bluetooth/glib/SCCS: could not be stat'd.
# external/oprofile (device 2051, inode 2245736): 16 files, no impossibilities.
# system/extras/RCS: could not be stat'd.
# packages/providers/UserDictionaryProvider/SCCS: could not be stat'd.
# hardware/ril (device 2051, inode 804040): 9 files, no impossibilities.
# system/core/include/arch/linux-arm/SCCS: could not be stat'd.
# build/core/tasks (device 2051, inode 1059893): 7 files, no impossibilities.
# system/core (device 2051, inode 1218975): 33 files, no impossibilities.
# external/svox/SCCS: could not be stat'd.
# external/fdlibm/SCCS: could not be stat'd.
# packages/experimental (device 2051, inode 1338768): 9 files, no impossibilities.
# frameworks/ex/RCS: could not be stat'd.
# bionic/libc/kernel/common/linux (device 2051, inode 1194389): 333 files, no impossibilities.
# hardware/qcom/media/SCCS: could not be stat'd.
# hardware/broadcom/wlan/SCCS: could not be stat'd.
# build (device 2051, inode 266734): 9 files, no impossibilities.
# external/freetype/SCCS: could not be stat'd.
# frameworks/opt/emoji (device 2051, inode 286892): 6 files, no impossibilities.
# packages/apps/QuickSearchBox (device 2051, inode 2239303): 12 files, no impossibilities.
# external/mtpd/RCS: could not be stat'd.
# external/expat/RCS: could not be stat'd.
# external/nist-sip/RCS: could not be stat'd.
# external/sqlite/SCCS: could not be stat'd.
# external/oprofile/SCCS: could not be stat'd.
# hardware/ril/SCCS: could not be stat'd.
# external/zlib/SCCS: could not be stat'd.
# frameworks/base/data/sounds/SCCS: could not be stat'd.
# bionic/libc/kernel/common/linux/SCCS: could not be stat'd.
# device/samsung/product/RCS: could not be stat'd.
# external/libffi (device 2051, inode 1980961): 47 files, no impossibilities.
# external (device 2051, inode 531687): 89 files, no impossibilities.
# dalvik/RCS: could not be stat'd.
# external/bluetooth/bluez (device 2051, inode 792719): 36 files, no impossibilities.
# packages/apps/QuickSearchBox/SCCS: could not be stat'd.
# frameworks/base/SCCS: could not be stat'd.
# external/jdiff/RCS: could not be stat'd.
# hardware/ti/omap3/RCS: could not be stat'd.
# external/embunit/RCS: could not be stat'd.
# external/tremolo/SCCS: could not be stat'd.
# bootable/recovery (device 2051, inode 287247): 32 files, no impossibilities.
# packages/apps/Gallery (device 2051, inode 790439): 10 files, no impossibilities.
# frameworks/opt/emoji/SCCS: could not be stat'd.
# vendor/google/RCS: could not be stat'd.
# packages/providers/ApplicationsProvider/RCS: could not be stat'd.
# packages/apps/Phone/SCCS: could not be stat'd.
# external/dbus/RCS: could not be stat'd.
# bootable/recovery/RCS: could not be stat'd.
# packages/apps/Mms/RCS: could not be stat'd.
# external/guava/RCS: could not be stat'd.
# bionic/libc/arch-arm/include/SCCS: could not be stat'd.
# external/qemu/RCS: could not be stat'd.
# external/fdlibm (device 2051, inode 1980752): 96 files, no impossibilities.
# out (device 2051, inode 287362): 8 files, no impossibilities.
# external/libffi/SCCS: could not be stat'd.
# bionic/libc/arch-arm/include (device 2051, inode 1196256): 4 files, no impossibilities.
# packages/apps/Gallery/SCCS: could not be stat'd.
# packages/apps/Launcher2 (device 2051, inode 1061226): 11 files, no impossibilities.
# packages/providers/DrmProvider (device 2051, inode 1578830): 9 files, no impossibilities.
# external/netcat/RCS: could not be stat'd.
# vendor/samsung/smdkv210/RCS: could not be stat'd.
# external/protobuf/RCS: could not be stat'd.
# external/proguard (device 2051, inode 940412): 10 files, no impossibilities.
# external/junit (device 2051, inode 795238): 10 files, no impossibilities.
# out/target/product/smdkv210/system/lib/RCS: could not be stat'd.
# ndk (device 2051, inode 526805): 14 files, no impossibilities.
# device/samsung/smdkc110 (device 2051, inode 1995139): 26 files, no impossibilities.
# prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/RCS: could not be stat'd.
# bionic/libc/include/sys (device 2051, inode 1192492): 59 files, no impossibilities.
# external/apache-http (device 2051, inode 408919): 7 files, no impossibilities.
# bionic/libc/include/netinet/SCCS: could not be stat'd.
# external/astl/RCS: could not be stat'd.
# external/icu4c/RCS: could not be stat'd.
# external/freetype/RCS: could not be stat'd.
# packages/apps/Camera/RCS: could not be stat'd.
# out/SCCS: could not be stat'd.
# external/neven/RCS: could not be stat'd.
# packages/apps/Gallery3D/RCS: could not be stat'd.
# external/webkit/RCS: could not be stat'd.
# packages/apps/Bluetooth/RCS: could not be stat'd.
# prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include (device 2051, inode 412543): 13 files, no impossibilities.
# external/tinyxml (device 2051, inode 1852329): 25 files, no impossibilities.
# packages/providers/DrmProvider/SCCS: could not be stat'd.
# packages/apps/Mms (device 2051, inode 2360776): 11 files, no impossibilities.
# external/junit/SCCS: could not be stat'd.
# external/libpcap/RCS: could not be stat'd.
# bootable/bootloader/legacy (device 2051, inode 287170): 13 files, no impossibilities.
# packages/apps/AccountsAndSyncSettings (device 2051, inode 2361406): 9 files, no impossibilities.
# device/htc/common/RCS: could not be stat'd.
# external/apache-http/SCCS: could not be stat'd.
# prebuilt/ndk (device 2051, inode 537667): 5 files, no impossibilities.
# external/proguard/SCCS: could not be stat'd.
# external/emma/RCS: could not be stat'd.
# external/bison (device 2051, inode 409718): 38 files, no impossibilities.
# development (device 2051, inode 262234): 20 files, no impossibilities.
# external/svox/RCS: could not be stat'd.
# external/stlport (device 2051, inode 1851989): 19 files, no impossibilities.
# out/host/linux-x86/bin/SCCS: could not be stat'd.
# prebuilt/sdk (device 2051, inode 537970): 9 files, no impossibilities.
# external/strace/RCS: could not be stat'd.
# hardware/qcom/gps/SCCS: could not be stat'd.
# device/htc/passion/RCS: could not be stat'd.
# external/gtest/RCS: could not be stat'd.
# packages/apps/Mms/SCCS: could not be stat'd.
# external/protobuf (device 2051, inode 269262): 36 files, no impossibilities.
# external/e2fsprogs (device 2051, inode 406140): 43 files, no impossibilities.
# external/tinyxml/SCCS: could not be stat'd.
# build/core/combo/SCCS: could not be stat'd.
# RCS: could not be stat'd.
# prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/SCCS: could not be stat'd.
# packages/providers/GoogleContactsProvider (device 2051, inode 1447366): 5 files, no impossibilities.
# system/bluetooth (device 2051, inode 1219182): 10 files, no impossibilities.
# out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates (device 2051, inode 1335957): 32 files, no impossibilities.
# development/SCCS: could not be stat'd.
# external/qemu (device 2051, inode 793860): 238 files, no impossibilities.
# packages/apps/VoiceDialer (device 2051, inode 2356255): 10 files, no impossibilities.
# external/jsr305/RCS: could not be stat'd.
# external/bison/SCCS: could not be stat'd.
# packages/apps/Settings (device 2051, inode 1197854): 11 files, no impossibilities.
# packages/inputmethods/OpenWnn/RCS: could not be stat'd.
# external/bzip2/RCS: could not be stat'd.
# device/htc/passion-common (device 2051, inode 2126813): 22 files, no impossibilities.
# packages/apps/Calculator/RCS: could not be stat'd.
# build/core/combo (device 2051, inode 1196701): 11 files, no impossibilities.
# external/bluetooth/hcidump (device 2051, inode 793635): 17 files, no impossibilities.
# external/e2fsprogs/SCCS: could not be stat'd.
# bionic/libc/arch-arm/include/machine (device 2051, inode 1196258): 12 files, no impossibilities.
# external/dhcpcd (device 2051, inode 1066874): 43 files, no impossibilities.
# device/sample (device 2051, inode 286913): 12 files, no impossibilities.
# device/samsung/smdkc110/SCCS: could not be stat'd.
# packages/providers/GoogleContactsProvider/SCCS: could not be stat'd.
# external/protobuf/SCCS: could not be stat'd.
# external/bluetooth/bluez/RCS: could not be stat'd.
# bootable/bootloader/legacy/SCCS: could not be stat'd.
# out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SCCS: could not be stat'd.
# bootable/diskinstaller (device 2051, inode 287228): 12 files, no impossibilities.
# external/genext2fs/SCCS: could not be stat'd.
# frameworks/ex (device 2051, inode 286808): 4 files, no impossibilities.
# packages/apps/VoiceDialer/SCCS: could not be stat'd.
# system/bluetooth/SCCS: could not be stat'd.
# packages/apps/Settings/SCCS: could not be stat'd.
# cts/SCCS: could not be stat'd.
# external/ipsec-tools (device 2051, inode 2362622): 13 files, no impossibilities.
# packages/experimental/SCCS: could not be stat'd.
# device/samsung/smdkv210 (device 2051, inode 951204): 26 files, no impossibilities.
# device/htc/passion-common/SCCS: could not be stat'd.
# external/elfutils (device 2051, inode 537383): 48 files, no impossibilities.
# external/netperf/RCS: could not be stat'd.
# SCCS: could not be stat'd.
# build/target/product (device 2051, inode 266953): 12 files, no impossibilities.
# external/esd/SCCS: could not be stat'd.
# packages/apps/Music/RCS: could not be stat'd.
# external/sonivox/RCS: could not be stat'd.
# hardware/libhardware_legacy (device 2051, inode 1595189): 14 files, no impossibilities.
# packages/apps/HTMLViewer/RCS: could not be stat'd.
# device/sample/products/RCS: could not be stat'd.
# cts (device 2051, inode 1351967): 12 files, no impossibilities.
# packages/inputmethods/LatinIME/RCS: could not be stat'd.
# device/sample/SCCS: could not be stat'd.
# out/target/product/smdkv210/system/lib/SCCS: could not be stat'd.
# external/dbus (device 2051, inode 665522): 36 files, no impossibilities.
# external/skia/SCCS: could not be stat'd.
# external/dnsmasq/RCS: could not be stat'd.
# external/easymock/RCS: could not be stat'd.
# packages/providers/TelephonyProvider/RCS: could not be stat'd.
# external/elfutils/SCCS: could not be stat'd.
# packages/apps/Stk/RCS: could not be stat'd.
# external/tvplay/tvrecv/RCS: could not be stat'd.
# external/ping/RCS: could not be stat'd.
# external/guava/SCCS: could not be stat'd.
# external/kernel-headers/RCS: could not be stat'd.
# packages/providers/CalendarProvider (device 2051, inode 1446747): 11 files, no impossibilities.
# system/vold (device 2051, inode 1219275): 36 files, no impossibilities.
# external/svox/pico/lang (device 2051, inode 2112874): 22 files, no impossibilities.
# prebuilt/sdk/4 (device 2051, inode 800581): 4 files, no impossibilities.
# packages/providers/MediaProvider (device 2051, inode 1579194): 9 files, no impossibilities.
# build/core/tasks/RCS: could not be stat'd.
# external/sqlite (device 2051, inode 1066793): 6 files, no impossibilities.
# libcore/RCS: could not be stat'd.
# external/webkit (device 2051, inode 1067034): 18 files, no impossibilities.
# external/dbus/SCCS: could not be stat'd.
# external/netperf (device 2051, inode 1582911): 39 files, no impossibilities.
# build/target/board/smdkv210: could not be stat'd.
# packages/apps/Provision/RCS: could not be stat'd.
# packages/providers/UserDictionaryProvider/RCS: could not be stat'd.
# system/core/include/arch/linux-arm/RCS: could not be stat'd.
# prebuilt/sdk/8 (device 2051, inode 672638): 4 files, no impossibilities.
# hardware/libhardware/RCS: could not be stat'd.
# hardware/msm7k (device 2051, inode 1594846): 17 files, no impossibilities.
# external/fsck_msdos (device 2051, inode 271779): 14 files, no impossibilities.
# packages/providers/CalendarProvider/SCCS: could not be stat'd.
# external/srec (device 2051, inode 1852107): 18 files, no impossibilities.
# external/iptables/SCCS: could not be stat'd.
# external/elfcopy/SCCS: could not be stat'd.
# packages/providers/MediaProvider/SCCS: could not be stat'd.
# hardware/qcom/media/RCS: could not be stat'd.
# packages/apps/SoundRecorder (device 2051, inode 2356752): 9 files, no impossibilities.
# external/svox/pico/lang/SCCS: could not be stat'd.
# system/vold/SCCS: could not be stat'd.
# external/openssl (device 2051, inode 1460660): 20 files, no impossibilities.
# prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin (device 2051, inode 2370102): 28 files, no impossibilities.
# bionic/libc/include/sys/SCCS: could not be stat'd.
# external/sqlite/RCS: could not be stat'd.
# device/samsung/smdkv210/SCCS: could not be stat'd.
# packages/wallpapers/LivePicker (device 2051, inode 1715503): 11 files, no impossibilities.
# vendor/htc/passion: could not be stat'd.
# hardware/ti/wlan/SCCS: could not be stat'd.
# external/fsck_msdos/SCCS: could not be stat'd.
# external/quake (device 2051, inode 1068518): 15 files, no impossibilities.
# packages/apps/AccountsAndSyncSettings/SCCS: could not be stat'd.
# external/libffi/RCS: could not be stat'd.
# hardware/msm7k/SCCS: could not be stat'd.
# system/core/RCS: could not be stat'd.
# external/tcpdump/RCS: could not be stat'd.
# external/svox (device 2051, inode 1974874): 8 files, no impossibilities.
# packages/apps/QuickSearchBox/RCS: could not be stat'd.
# external/srec/SCCS: could not be stat'd.
# packages/apps/Protips (device 2051, inode 2110524): 7 files, no impossibilities.
# packages/wallpapers/MagicSmoke/SCCS: could not be stat'd.
# frameworks/opt/emoji/RCS: could not be stat'd.
# build/RCS: could not be stat'd.
# packages/wallpapers/LivePicker/SCCS: could not be stat'd.
# device/common/gps (device 2051, inode 286907): 14 files, no impossibilities.
# prebuilt/sdk/5 (device 2051, inode 672654): 4 files, no impossibilities.
# external/dropbear/RCS: could not be stat'd.
# hardware/ti/omap3/SCCS: could not be stat'd.
# external/jpeg (device 2051, inode 2119090): 156 files, no impossibilities.
# bionic/libc/arch-arm/include/RCS: could not be stat'd.
# external/bsdiff/RCS: could not be stat'd.
# out/host/linux-x86/sdk: could not be stat'd.
# external/quake/SCCS: could not be stat'd.
# packages/providers/ApplicationsProvider (device 2051, inode 1447383): 9 files, no impossibilities.
# packages/apps/Gallery/RCS: could not be stat'd.
# packages/apps/Protips/SCCS: could not be stat'd.
# bionic/libc/kernel/arch-arm/asm (device 2051, inode 1195963): 83 files, no impossibilities.
# external/freetype (device 2051, inode 1343759): 10 files, no impossibilities.
# build/core (device 2051, inode 1059838): 61 files, no impossibilities.
# device/common/RCS: could not be stat'd.
# external/libpng (device 2051, inode 792093): 64 files, no impossibilities.
# bionic/libc/include/arpa (device 2051, inode 1192626): 5 files, no impossibilities.
# out/host/linux-x86/sdk/RCS: could not be stat'd.
# external/dhcpcd/SCCS: could not be stat'd.
# bionic/libc/include/netinet/RCS: could not be stat'd.
# out/RCS: could not be stat'd.
# device/common/gps/SCCS: could not be stat'd.
# external/fdlibm/RCS: could not be stat'd.
# external/jpeg/SCCS: could not be stat'd.
# external/ppp (device 2051, inode 1585264): 5 files, no impossibilities.
# packages/providers/DrmProvider/RCS: could not be stat'd.
# packages/apps/SpeechRecorder (device 2051, inode 2361060): 7 files, no impossibilities.
# prebuilt/sdk/7 (device 2051, inode 672670): 4 files, no impossibilities.
# bionic/libc/arch-arm/include/machine/SCCS: could not be stat'd.
# external/libxml2 (device 2051, inode 2241203): 57 files, no impossibilities.
# external/junit/RCS: could not be stat'd.
# bionic/libc/kernel/arch-arm/asm/SCCS: could not be stat'd.
# frameworks/base (device 2051, inode 277986): 36 files, no impossibilities.
# packages/apps/Contacts/RCS: could not be stat'd.
# system/core/include/arch/linux-arm (device 2051, inode 1218981): 3 files, no impossibilities.
# packages/providers/DownloadProvider (device 2051, inode 1337684): 12 files, no impossibilities.
# external/grub (device 2051, inode 1461803): 41 files, no impossibilities.
# external/proguard/RCS: could not be stat'd.
# fdo/profiles/arm: could not be stat'd.
# build/core/SCCS: could not be stat'd.
# prebuilt (device 2051, inode 537612): 16 files, no impossibilities.
# external/libpng/SCCS: could not be stat'd.
# out/host/linux-x86/bin/RCS: could not be stat'd.
# external/iproute2 (device 2051, inode 791920): 28 files, no impossibilities.
# packages/apps/Calendar (device 2051, inode 2109710): 10 files, no impossibilities.
# external/ppp/SCCS: could not be stat'd.
# packages/apps/SpeechRecorder/SCCS: could not be stat'd.
# external/kernel-headers (device 2051, inode 2241488): 4 files, no impossibilities.
# external/chromium (device 2051, inode 535788): 13 files, no impossibilities.
# packages/wallpapers/MagicSmoke/RCS: could not be stat'd.
# bootable/bootloader/legacy/RCS: could not be stat'd.
# packages/inputmethods/PinyinIME (device 2051, inode 1338866): 11 files, no impossibilities.
# external/skia (device 2051, inode 2243468): 15 files, no impossibilities.
# packages/apps/Email (device 2051, inode 2361133): 17 files, no impossibilities.
# external/jhead/RCS: could not be stat'd.
# external/dropbear (device 2051, inode 2247771): 128 files, no impossibilities.
# packages/apps/AccountsAndSyncSettings/RCS: could not be stat'd.
# bionic/libc/include (device 2051, inode 1192436): 62 files, no impossibilities.
# packages/providers/DownloadProvider/SCCS: could not be stat'd.
# frameworks/ex/SCCS: could not be stat'd.
# development/RCS: could not be stat'd.
# prebuilt/SCCS: could not be stat'd.
# system/wlan/ti (device 2051, inode 1218367): 7 files, no impossibilities.
# out/target/product/smdkv210/obj/lib (device 2051, inode 1358001): 162 files, no impossibilities.
# external/bison/RCS: could not be stat'd.
# system/netd (device 2051, inode 2256276): 30 files, no impossibilities.
# packages/apps/Launcher2/RCS: could not be stat'd.
# packages/apps/Calendar/SCCS: could not be stat'd.
# external/grub/SCCS: could not be stat'd.
# bootable/diskinstaller/RCS: could not be stat'd.
# external/giflib (device 2051, inode 1066760): 13 files, no impossibilities.
# bionic/libc/kernel/common/linux/byteorder/SCCS: could not be stat'd.
# system/extras/SCCS: could not be stat'd.
# external/chromium/SCCS: could not be stat'd.
# external/genext2fs (device 2051, inode 271635): 31 files, no impossibilities.
# external/clearsilver/RCS: could not be stat'd.
# packages/providers/GoogleContactsProvider/RCS: could not be stat'd.
# external/dropbear/SCCS: could not be stat'd.
# packages/apps/Email/SCCS: could not be stat'd.
# out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/RCS: could not be stat'd.
# external/genext2fs/RCS: could not be stat'd.
# packages/apps/DeskClock/RCS: could not be stat'd.
# packages/apps/VoiceDialer/RCS: could not be stat'd.
# system/bluetooth/RCS: could not be stat'd.
# packages/apps/Settings/RCS: could not be stat'd.
# device/common (device 2051, inode 286906): 5 files, no impossibilities.
# bionic/libc/include/SCCS: could not be stat'd.
# external/yaffs2 (device 2051, inode 271724): 7 files, no impossibilities.
# packages/experimental/RCS: could not be stat'd.
# bootable/recovery/SCCS: could not be stat'd.
# system/wlan/ti/SCCS: could not be stat'd.
# device/htc/passion-common/RCS: could not be stat'd.
# packages/apps/Browser (device 2051, inode 1843214): 12 files, no impossibilities.
# external/qemu/SCCS: could not be stat'd.
# hardware/libhardware_legacy/RCS: could not be stat'd.
# system/netd/SCCS: could not be stat'd.
# external/blktrace (device 2051, inode 1068570): 31 files, no impossibilities.
# libcore/SCCS: could not be stat'd.
# external/esd/RCS: could not be stat'd.
# external/xmlwriter (device 2051, inode 1582081): 4 files, no impossibilities.
# bionic/libc/arch-arm/include/machine/RCS: could not be stat'd.
# packages/inputmethods/PinyinIME/SCCS: could not be stat'd.
# external/giflib/SCCS: could not be stat'd.
# external/wpa_supplicant_6 (device 2051, inode 1845229): 8 files, no impossibilities.
# device/sample/RCS: could not be stat'd.
# external/easymock/SCCS: could not be stat'd.
# build/target/product/RCS: could not be stat'd.
# external/iproute2/SCCS: could not be stat'd.
# external/skia/RCS: could not be stat'd.
# device/common/SCCS: could not be stat'd.
# external/oauth/RCS: could not be stat'd.
# external/zlib (device 2051, inode 1722937): 67 files, no impossibilities.
# external/elfutils/RCS: could not be stat'd.
# packages/apps/Browser/SCCS: could not be stat'd.
# external/bouncycastle/RCS: could not be stat'd.
# external/openssl/SCCS: could not be stat'd.
# external/neven/SCCS: could not be stat'd.
# external/oprofile/RCS: could not be stat'd.
# bionic/libc/include/netinet (device 2051, inode 1192643): 11 files, no impossibilities.
# bionic/libc/kernel/common/asm-generic/RCS: could not be stat'd.
# external/xmlwriter/SCCS: could not be stat'd.
# vendor/htc/common: could not be stat'd.
# ndk/SCCS: could not be stat'd.
# cts/RCS: could not be stat'd.
# external/blktrace/SCCS: could not be stat'd.
# external/gtest/SCCS: could not be stat'd.
# external/speex/SCCS: could not be stat'd.
# packages/apps/CertInstaller (device 2051, inode 2357203): 9 files, no impossibilities.
# packages/inputmethods/LatinIME (device 2051, inode 1968051): 8 files, no impossibilities.
# out/target/product/smdkv210 (device 2051, inode 287365): 19 files, no impossibilities.
# external/tvplay/tvrecv/SCCS: could not be stat'd.
# sdk (device 2051, inode 530167): 28 files, no impossibilities.
# packages/apps/Phone (device 2051, inode 2357599): 10 files, no impossibilities.
# external/neven (device 2051, inode 2247744): 10 files, no impossibilities.
# packages/providers/CalendarProvider/RCS: could not be stat'd.
# external/iptables/RCS: could not be stat'd.
# external/elfcopy/RCS: could not be stat'd.
# packages/providers/MediaProvider/RCS: could not be stat'd.
# bionic/libc/kernel/common/linux/byteorder/RCS: could not be stat'd.
# external/bouncycastle/SCCS: could not be stat'd.
# bionic (device 2051, inode 266620): 12 files, no impossibilities.
# external/safe-iop (device 2051, inode 2247217): 10 files, no impossibilities.
# external/jhead (device 2051, inode 2241450): 15 files, no impossibilities.
# system/vold/RCS: could not be stat'd.
# packages/apps/CertInstaller/SCCS: could not be stat'd.
# external/yaffs2/SCCS: could not be stat'd.
# external/bouncycastle (device 2051, inode 667330): 13 files, no impossibilities.
# vendor/samsung/smdkv210/SCCS: could not be stat'd.
# out/target/product/smdkv210/system/lib (device 2051, inode 287417): 134 files, no impossibilities.
# external/libgsm (device 2051, inode 1068406): 12 files, no impossibilities.
# device/samsung/smdkv210/RCS: could not be stat'd.
# external/wpa_supplicant_6/SCCS: could not be stat'd.
# external/fsck_msdos/RCS: could not be stat'd.
# frameworks/base/api (device 2051, inode 286791): 12 files, no impossibilities.
# external/sonivox (device 2051, inode 1974991): 12 files, no impossibilities.
# external/v8/SCCS: could not be stat'd.
# hardware/msm7k/RCS: could not be stat'd.
# packages/apps/DeskClock (device 2051, inode 2359803): 11 files, no impossibilities.
# external/tinyxml/RCS: could not be stat'd.
# packages/providers/ContactsProvider (device 2051, inode 1714697): 10 files, no impossibilities.
# hardware/libhardware_legacy/SCCS: could not be stat'd.
# packages/apps/SoundRecorder/RCS: could not be stat'd.
# vendor (device 2051, inode 539044): 3 files, no impossibilities.
# external/safe-iop/SCCS: could not be stat'd.
# bionic/libc/include/sys/RCS: could not be stat'd.
# external/srec/RCS: could not be stat'd.
# external/tremolo/RCS: could not be stat'd.
# external/astl/SCCS: could not be stat'd.
# packages/wallpapers/MusicVisualization (device 2051, inode 1338562): 10 files, no impossibilities.
# external/speex (device 2051, inode 534211): 11 files, no impossibilities.
# bionic/SCCS: could not be stat'd.
# hardware/ti/wlan (device 2051, inode 1595290): 5 files, no impossibilities.
# packages/wallpapers/LivePicker/RCS: could not be stat'd.
# packages/apps/Phone/RCS: could not be stat'd.
# external/libgsm/SCCS: could not be stat'd.
# bionic/libc/include/arpa/RCS: could not be stat'd.
# external/libxml2/SCCS: could not be stat'd.
# external/wpa_supplicant (device 2051, inode 532489): 199 files, no impossibilities.
# external/tagsoup/SCCS: could not be stat'd.
# external/ipsec-tools/SCCS: could not be stat'd.
# external/apache-http/RCS: could not be stat'd.
# bionic/libc/kernel/common/asm-generic (device 2051, inode 1195455): 30 files, no impossibilities.
# external/quake/RCS: could not be stat'd.
# packages/apps/DeskClock/SCCS: could not be stat'd.
# external/stlport/SCCS: could not be stat'd.
# packages/apps/Protips/RCS: could not be stat'd.
# packages/apps/Camera/SCCS: could not be stat'd.
# build/target/product/SCCS: could not be stat'd.
# out/target/product/smdkv210/SCCS: could not be stat'd.
# packages/inputmethods/LatinIME/SCCS: could not be stat'd.
# build/core/combo/arch/arm/RCS: could not be stat'd.
# packages/wallpapers/MusicVisualization/SCCS: could not be stat'd.

# 5306 files, 14 impossibilities in 605 directories.

# Implicit Rules

NOTICE-HOST-%:
#  commands to execute (from `build/core/main.mk', line 485):
	

NOTICE-TARGET-%:
#  commands to execute (from `build/core/main.mk', line 486):
	

(%): %
#  commands to execute (built-in):
	$(AR) $(ARFLAGS) $@ $<

%.out: %
#  commands to execute (built-in):
	@rm -f $@ 
	cp $< $@

%.c: %.w %.ch
#  commands to execute (built-in):
	$(CTANGLE) $^ $@

%.tex: %.w %.ch
#  commands to execute (built-in):
	$(CWEAVE) $^ $@

%:: %,v
#  commands to execute (built-in):
	$(CHECKOUT,v)

%:: RCS/%,v
#  commands to execute (built-in):
	$(CHECKOUT,v)

%:: RCS/%
#  commands to execute (built-in):
	$(CHECKOUT,v)

%:: s.%
#  commands to execute (built-in):
	$(GET) $(GFLAGS) $(SCCS_OUTPUT_OPTION) $<

%:: SCCS/s.%
#  commands to execute (built-in):
	$(GET) $(GFLAGS) $(SCCS_OUTPUT_OPTION) $<

# 11 implicit rules, 5 (45.5%) terminal.

# Files

out/target/product/smdkv210/system.tar.bz2: out/host/linux-x86/bin/fs_get_stats out/target/product/smdkv210/system/bin/tvrecv out/target/product/smdkv210/system/etc/vold.fstab out/target/product/smdkv210/system/lib/egl/egl.cfg out/target/product/smdkv210/system/usr/keylayout/s3c-keypad.kl out/target/product/smdkv210/system/etc/media_profiles.xml out/target/product/smdkv210/system/etc/secomxregistry out/target/product/smdkv210/system/lib/libSEC_OMX_Core.so out/target/product/smdkv210/system/lib/libSEC_Resourcemanager.so out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M2V.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.WMV.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Encoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Encoder.so out/target/product/smdkv210/system/lib/libstagefright_amrnb_common.so out/target/product/smdkv210/system/lib/libstagefright_enc_common.so out/target/product/smdkv210/system/lib/libstagefright_avc_common.so out/target/product/smdkv210/system/lib/libstagefright_foundation.so out/target/product/smdkv210/system/lib/libstagefright_color_conversion.so out/target/product/smdkv210/system/lib/libstagefright.so out/target/product/smdkv210/system/lib/libstagefright_omx.so out/target/product/smdkv210/system/lib/liba52.so out/target/product/smdkv210/system/bin/playts out/target/product/smdkv210/system/lib/libtv.so out/target/product/smdkv210/system/bin/remount_ctl out/target/product/smdkv210/system/media/bootanimation.zip out/target/product/smdkv210/system/app/PrimaryApp.apk out/target/product/smdkv210/system/app/HomePage.apk out/target/product/smdkv210/system/app/FileManagers.apk out/target/product/smdkv210/system/app/Game.apk out/target/product/smdkv210/system/lib/libmoddvb.so out/target/product/smdkv210/system/lib/libjnidvb.so out/target/product/smdkv210/system/lib/libdvbbinderclient.so out/target/product/smdkv210/system/lib/libdvbcomm.so out/target/product/smdkv210/system/lib/modules/utiusb.ko out/target/product/smdkv210/system/bin/dvb_load out/target/product/smdkv210/system/bin/busybox out/target/product/smdkv210/system/bin/bash out/target/product/smdkv210/system/bin/dvb_server out/target/product/smdkv210/system/etc/wifi/wpa_supplicant.conf out/target/product/smdkv210/system/etc/dhd.ko out/target/product/smdkv210/system/etc/fw_bcm4329.bin out/target/product/smdkv210/system/etc/nvram.txt out/target/product/smdkv210/system/etc/permissions/handheld_core_hardware.xml out/target/product/smdkv210/system/etc/permissions/android.software.live_wallpaper.xml out/target/product/smdkv210/system/etc/permissions/android.software.live_wallpaper.xml out/target/product/smdkv210/system/vendor/bin/pvrsrvinit out/target/product/smdkv210/system/vendor/lib/egl/libEGL_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/egl/libGLESv1_CM_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/egl/libGLESv2_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/libglslcompiler.so out/target/product/smdkv210/system/vendor/lib/libIMGegl.so out/target/product/smdkv210/system/vendor/lib/libpvr2d.so out/target/product/smdkv210/system/vendor/lib/libpvrANDROID_WSEGL.so out/target/product/smdkv210/system/vendor/lib/libPVRScopeServices.so out/target/product/smdkv210/system/vendor/lib/libsrv_init.so out/target/product/smdkv210/system/vendor/lib/libsrv_um.so out/target/product/smdkv210/system/vendor/lib/libusc.so out/target/product/smdkv210/system/vendor/lib/hw/gralloc.s5pc110.so out/target/product/smdkv210/system/etc/apns-conf.xml out/target/product/smdkv210/system/etc/vold.conf out/target/product/smdkv210/system/media/audio/notifications/F1_MissedCall.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_New_MMS.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_New_SMS.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Buzzer.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_01.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_02.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Classic.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_03.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Rooster_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Classic_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Digital_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_04.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/BeatPlucker.ogg out/target/product/smdkv210/system/media/audio/ringtones/BentleyDubs.ogg out/target/product/smdkv210/system/media/audio/ringtones/BirdLoop.ogg out/target/product/smdkv210/system/media/audio/ringtones/CaribbeanIce.ogg out/target/product/smdkv210/system/media/audio/ringtones/CurveBall.ogg out/target/product/smdkv210/system/media/audio/ringtones/EtherShake.ogg out/target/product/smdkv210/system/media/audio/ringtones/FriendlyGhost.ogg out/target/product/smdkv210/system/media/audio/ringtones/GameOverGuitar.ogg out/target/product/smdkv210/system/media/audio/ringtones/Growl.ogg out/target/product/smdkv210/system/media/audio/ringtones/InsertCoin.ogg out/target/product/smdkv210/system/media/audio/ringtones/LoopyLounge.ogg out/target/product/smdkv210/system/media/audio/ringtones/LoveFlute.ogg out/target/product/smdkv210/system/media/audio/ringtones/MidEvilJaunt.ogg out/target/product/smdkv210/system/media/audio/ringtones/MildlyAlarming.ogg out/target/product/smdkv210/system/media/audio/ringtones/NewPlayer.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises1.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises2.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises3.ogg out/target/product/smdkv210/system/media/audio/ringtones/OrganDub.ogg out/target/product/smdkv210/system/media/audio/ringtones/RomancingTheTone.ogg out/target/product/smdkv210/system/media/audio/ringtones/SitarVsSitar.ogg out/target/product/smdkv210/system/media/audio/ringtones/SpringyJalopy.ogg out/target/product/smdkv210/system/media/audio/ringtones/Terminated.ogg out/target/product/smdkv210/system/media/audio/ringtones/TwirlAway.ogg out/target/product/smdkv210/system/media/audio/ringtones/VeryAlarmed.ogg out/target/product/smdkv210/system/media/audio/ringtones/World.ogg out/target/product/smdkv210/system/media/audio/notifications/CaffeineSnake.ogg out/target/product/smdkv210/system/media/audio/notifications/DearDeer.ogg out/target/product/smdkv210/system/media/audio/notifications/DontPanic.ogg out/target/product/smdkv210/system/media/audio/notifications/Highwire.ogg out/target/product/smdkv210/system/media/audio/notifications/KzurbSonar.ogg out/target/product/smdkv210/system/media/audio/notifications/OnTheHunt.ogg out/target/product/smdkv210/system/media/audio/notifications/Voila.ogg out/target/product/smdkv210/system/media/audio/notifications/Beat_Box_Android.ogg out/target/product/smdkv210/system/media/audio/notifications/Heaven.ogg out/target/product/smdkv210/system/media/audio/notifications/TaDa.ogg out/target/product/smdkv210/system/media/audio/notifications/Tinkerbell.ogg out/target/product/smdkv210/system/media/audio/ui/Effect_Tick.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressStandard.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressSpacebar.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressDelete.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressReturn.ogg out/target/product/smdkv210/system/media/audio/ui/VideoRecord.ogg out/target/product/smdkv210/system/media/audio/ui/camera_click.ogg out/target/product/smdkv210/system/media/audio/ringtones/CrazyDream.ogg out/target/product/smdkv210/system/media/audio/ringtones/DreamTheme.ogg out/target/product/smdkv210/system/tts/lang_pico/de-DE_gl0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/de-DE_ta.bin out/target/product/smdkv210/system/tts/lang_pico/en-GB_kh0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/en-GB_ta.bin out/target/product/smdkv210/system/tts/lang_pico/en-US_lh0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/en-US_ta.bin out/target/product/smdkv210/system/tts/lang_pico/es-ES_zl0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/es-ES_ta.bin out/target/product/smdkv210/system/tts/lang_pico/fr-FR_nk0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/fr-FR_ta.bin out/target/product/smdkv210/system/tts/lang_pico/it-IT_cm0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/it-IT_ta.bin out/target/product/smdkv210/system/build.prop out/target/product/smdkv210/system/etc/event-log-tags out/target/product/smdkv210/system/etc/security/otacerts.zip
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/Makefile', line 777)
# PRIVATE_SYSTEM_TAR := out/target/product/smdkv210/system.tar
# variable set hash-table stats:
# Load=1/32=3%, Rehash=0, Collisions=0/2=0%
#  commands to execute (from `build/core/Makefile', line 779):
	$(build-systemtarball-target)
	

out/target/product/smdkv210/system/tts/lang_pico/it-IT_cm0_sg.bin: external/svox/pico/lang/it-IT_cm0_sg.bin | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketRecv.P:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  Last modified 2012-02-27 22:04:06.250410089
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/newwavelabs/CaribbeanIce.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/host/linux-x86/bin/atree:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/include/stdint.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

docs: out/target/common/docs/index.html
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

out/target/product/smdkv210/system/bin/busybox: LMC1800/sources/busybox | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
external/xmlwriter/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:12
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/host/linux-x86/bin/bsdiff:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
device/htc/common/common.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:59
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
adb:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/include/sys/ioctl.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

otacerts: out/target/product/smdkv210/system/etc/security/otacerts.zip
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

# Not a target:
TestDeviceSetup:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/blktrace/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/newwavelabs/World.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
.web.p:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(TANGLE) $<

# Not a target:
external/speex/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:49:41
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
cts/CtsTestCaseList.mk:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:41
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/lib/libstagefright_color_conversion.so: LMC1800/sources/media/sf/libstagefright_color_conversion.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

external/tvplay/tvrecv/HostRgnClientAPI.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/newwavelabs/Noises2.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/svox/pico/lang/PicoLangDeDeInSystem.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:34
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
device/samsung/product/AndroidProducts.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:54
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/vendor/lib/libusc.so: vendor/samsung/smdkv210/proprietary/libusc.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

bionic/libc/include/sys/types.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
LMC1800/sources/media/sf/libstagefright_avc_common.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/kernel/common/linux/byteorder/generic.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/app/PrimaryApp.apk: LMC1800/sources/ui/PrimaryApp.apk | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
frameworks/base/data/sounds/notifications/Tinkerbell.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
CtsAccountManagerTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/target/product/smdkv210/clean_steps.mk:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  Last modified 2012-03-01 19:20:58.063885608
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/media/audio/ringtones/Terminated.ogg: frameworks/base/data/sounds/newwavelabs/Terminated.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/system/media/audio/ringtones/FriendlyGhost.ogg: frameworks/base/data/sounds/newwavelabs/FriendlyGhost.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
frameworks/base/data/sounds/newwavelabs/CurveBall.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/ringtones/Ring_Digital_02.ogg: frameworks/base/data/sounds/Ring_Digital_02.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
out/host/linux-x86/bin/fs_get_stats:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/ipsec-tools/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:38
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
build/target/product/languages_small.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/kernel/common/linux/in6.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/zlib/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:50:33
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/dex_bootjars/system/framework/services.odex: out/target/common/obj/JAVA_LIBRARIES/services_intermediates/javalib.jar out/target/product/smdkv210/dex_bootjars/system/framework/android.policy.odex | out/host/linux-x86/bin/acp dalvik/tools/dex-preopt out/host/linux-x86/bin/dexopt
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/dex_preopt.mk', line 66)
# PRIVATE_DBJ_JAR := out/target/product/smdkv210/dex_bootjars/system/framework/services.jar
# variable set hash-table stats:
# Load=1/32=3%, Rehash=0, Collisions=0/1=0%
#  commands to execute (from `build/core/dex_preopt.mk', line 66):
	@echo "Dexpreopt Boot Jar: $@"
	@ rm -f $@
	@ mkdir -p $(dir $@)
	@ out/host/linux-x86/bin/acp -fpt $< $(PRIVATE_DBJ_JAR)
	$(call dexpreopt-one-file,$(PRIVATE_DBJ_JAR),$@)
	

# Not a target:
CtsAccessibilityServiceTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/host/linux-x86/obj/NOTICE.html: out/host/linux-x86/obj/NOTICE_FILES/hash-timestamp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/Makefile', line 498)
# PRIVATE_DIR := out/host/linux-x86/obj/NOTICE_FILES
# makefile (from `build/core/Makefile', line 498)
# PRIVATE_MESSAGE := "Notices for files contained in the tools directory:"
# variable set hash-table stats:
# Load=2/32=6%, Rehash=0, Collisions=0/2=0%
#  commands to execute (from `build/core/Makefile', line 498):
	@echo Combining NOTICE files: $@
	$(hide) mkdir -p $(dir $@)
	$(hide) echo "<html><head>" > $@
	$(hide) echo "<style type=\"text/css\">" >> $@
	$(hide) echo "body { padding: 0; font-family: sans-serif; }" >> $@
	$(hide) echo ".same-license { background-color: #eeeeee; border-top: 20px solid white; padding: 10px; }" >> $@
	$(hide) echo ".label { font-weight: bold; }" >> $@
	$(hide) echo ".file-list { margin-left: 1em; font-color: blue; }" >> $@
	$(hide) echo "</style>" >> $@
	$(hide) echo "</head><body topmargin=\"0\" leftmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\">" >> $@
	$(hide) echo "<table cellpading=\"0\" cellspacing=\"0\" border=\"0\">" >> $@
	$(hide) for hashfile in $$(find $(PRIVATE_DIR)/hash -type f); do cat $$hashfile | sort | sed -e "s:$(PRIVATE_DIR)/src\(.*\)\.txt:  <a name=\"\1\"></a>:" >> $@; echo "<tr><td class=\"same-license\">" >> $@; echo "<div class=\"label\">Notices for file(s):</div>" >> $@; echo "<div class=\"file-list\">" >> $@; cat $$hashfile | sort | sed -e "s:$(PRIVATE_DIR)/src\(.*\)\.txt:  \1<br/>:" >> $@; echo "</div><!-- file-list -->" >> $@; echo >> $@; orig=$$(head -n 1 $$hashfile); echo "<pre class=\"license-text\">" >> $@; cat $$orig | sed -e "s/\&/\&amp;/g" | sed -e "s/</\&lt;/g" | sed -e "s/>/\&gt;/g" >> $@; echo "</pre><!-- license-text -->" >> $@; echo "</td></tr><!-- same-license -->" >> $@; echo >> $@; echo >> $@; echo >> $@; done
	$(hide) echo "</table>" >> $@
	$(hide) echo "</body></html>" >> $@
	

# Not a target:
android.core.tests.xml:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/sdk/sdk-build.prop: out/target/product/smdkv210/system/build.prop
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 172):
	@echo SDK buildinfo: $@
	@mkdir -p $(dir $@)
	$(hide) grep -v "$(subst $(space),\|,$(strip \
	$(sdk_build_prop_remove)))" $< > $@.tmp
	$(hide) for x in $(sdk_build_prop_remove); do \
	echo "$$x"generic >> $@.tmp; done
	$(hide) mv $@.tmp $@
	

out/target/product/smdkv210/dex_bootjars/system/framework/framework.odex: out/target/common/obj/JAVA_LIBRARIES/framework_intermediates/javalib.jar out/target/product/smdkv210/dex_bootjars/system/framework/ext.odex | out/host/linux-x86/bin/acp dalvik/tools/dex-preopt out/host/linux-x86/bin/dexopt
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/dex_preopt.mk', line 66)
# PRIVATE_DBJ_JAR := out/target/product/smdkv210/dex_bootjars/system/framework/framework.jar
# variable set hash-table stats:
# Load=1/32=3%, Rehash=0, Collisions=0/1=0%
#  commands to execute (from `build/core/dex_preopt.mk', line 66):
	@echo "Dexpreopt Boot Jar: $@"
	@ rm -f $@
	@ mkdir -p $(dir $@)
	@ out/host/linux-x86/bin/acp -fpt $< $(PRIVATE_DBJ_JAR)
	$(call dexpreopt-one-file,$(PRIVATE_DBJ_JAR),$@)
	

# Not a target:
.l.r:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(LEX.l) $< > $@ 
	mv -f lex.yy.r $@

# Not a target:
external/yaffs2/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:50:31
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
LMC1800/sources/ui/HomePage.apk:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
CtsSpeechTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

apkcerts-list: out/target/product/smdkv210/obj/PACKAGING/apkcerts_intermediates/full_smdkv210-apkcerts-eng.lnt.txt
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

# Not a target:
out/target/product/smdkv210/obj/lib/crtend_android.o:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 11:18:54.802990158
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/media/audio/ringtones/TwirlAway.ogg: frameworks/base/data/sounds/newwavelabs/TwirlAway.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
out/target/product/smdkv210/previous_build_config.mk:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  Last modified 2012-03-01 19:20:58.067885605
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
CtsSimpleAppInstall:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/bin/dvb_server: LMC1800/sources/dvb/dvb_server | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
frameworks/base/data/sounds/Alarm_Beep_01.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/ui/KeypressSpacebar.ogg: frameworks/base/data/sounds/effects/KeypressSpacebar.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
vendor/samsung/smdkv210/proprietary/libIMGegl.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_02.ogg: frameworks/base/data/sounds/Ring_Synth_02.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

bionic/libc/arch-arm/include/machine/limits.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
sdk/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:47:51
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/kernel/common/linux/compiler.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/wpa_supplicant_6/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:48
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/etc/event-log-tags: out/target/common/obj/all-event-log-tags.txt
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/Makefile', line 288)
# PRIVATE_MERGED_FILE := out/target/common/obj/all-event-log-tags.txt
# makefile (from `build/core/Makefile', line 287)
# PRIVATE_SRC_FILES := 
# variable set hash-table stats:
# Load=2/32=6%, Rehash=0, Collisions=0/4=0%
#  commands to execute (from `build/core/Makefile', line 290):
	$(hide) mkdir -p $(dir $@)
	$(hide) build/tools/merge-event-log-tags.py -o $@ -m $(PRIVATE_MERGED_FILE) $(PRIVATE_SRC_FILES)
	

# Not a target:
build/core/tasks/sdk-addon.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/vendor/lib/hw/gralloc.s5pc110.so: vendor/samsung/smdkv210/proprietary/gralloc.s5pc110.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
packages/wallpapers/LivePicker/android.software.live_wallpaper.xml:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/root/init.rc: device/samsung/smdkv210/init.rc | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/common/obj/PACKAGING/checkapi-last-timestamp: frameworks/base/api/9.xml out/target/common/obj/PACKAGING/public_api.xml out/host/linux-x86/bin/apicheck
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/tasks/apicheck.mk', line 53):
	@echo "Checking API:"  checkapi-last
	@ ( out/host/linux-x86/bin/apicheck  -hide 2 -hide 3 -hide 4 -hide 5 -hide 6 -hide 24 -hide 25 -error 7 -error 8 -error 9 -error 10 -error 11 -error 12 -error 13 -error 14 -error 15 -error 16 -error 17 -error 18   frameworks/base/api/9.xml  out/target/common/obj/PACKAGING/public_api.xml || (  cat build/core/apicheck_msg_last.txt  ; exit 38 ) )
	@ mkdir -p $(dir $@)
	@ touch $@
	

# Not a target:
CtsContentTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
vendor/google/user_tags.mk:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  File does not exist.
#  File has been updated.
#  Failed to be updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/target/product/smdkv210/obj/lib/crtbegin_dynamic.o:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 11:18:54.798990158
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/safe-iop/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:13
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
CtsLocationTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
build/core/config.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/vendor/lib/libPVRScopeServices.so: vendor/samsung/smdkv210/proprietary/libPVRScopeServices.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/system/etc/security/otacerts.zip: build/target/product/security/testkey.x509.pem
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/Makefile', line 539)
# KEY_CERT_PAIR := build/target/product/security/testkey
# variable set hash-table stats:
# Load=1/32=3%, Rehash=0, Collisions=0/2=0%
#  commands to execute (from `build/core/Makefile', line 541):
	$(hide) rm -f $@
	$(hide) mkdir -p $(dir $@)
	$(hide) zip -qj $@ $<
	

out/target/product/smdkv210/system/media/audio/ringtones/CaribbeanIce.ogg: frameworks/base/data/sounds/newwavelabs/CaribbeanIce.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
LMC1800/sources/media/sf/libstagefright_foundation.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
LMC1800/sources/dvb/dvb_server:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/app/Game.apk: LMC1800/sources/ui/Game.apk | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
.F.o:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(COMPILE.F) $(OUTPUT_OPTION) $<

out/target/product/smdkv210/system/media/audio/ringtones/LoopyLounge.ogg: frameworks/base/data/sounds/newwavelabs/LoopyLounge.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
bionic/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:05
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/bluetooth/hcidump/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:50:43
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/jhead/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:46
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/inputmethods/LatinIME/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:48
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/tts/lang_pico/de-DE_ta.bin: external/svox/pico/lang/de-DE_ta.bin | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
frameworks/base/data/sounds/newwavelabs/DearDeer.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/ringtones/CurveBall.ogg: frameworks/base/data/sounds/newwavelabs/CurveBall.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/system/etc/dhd.ko: LMC1800/sources/broadcom/dhd.ko | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
external/bison/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:50:54
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/host/linux-x86/bin/imgdiff:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/target/common/obj/JAVA_LIBRARIES/core-tests-support_intermediates/javalib.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/tagsoup/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:01
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
.y.ln:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(YACC.y) $< 
	$(LINT.c) -C$* y.tab.c 
	$(RM) y.tab.c

# Not a target:
.o:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(LINK.o) $^ $(LOADLIBES) $(LDLIBS) -o $@

# Not a target:
packages/providers/ContactsProvider/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:02:02
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

FORCE:
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

# Not a target:
CtsOsTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/tts/lang_pico/it-IT_ta.bin: external/svox/pico/lang/it-IT_ta.bin | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
LMC1800/sources/ui/FileManagers.apk:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
packages/apps/DeskClock/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:37
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/kernel/arch-arm/asm/ioctl.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
LMC1800/sources/media/sf/libstagefright.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/stlport/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:45
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/Alarm_Classic.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
development/data/etc/apns-conf.xml:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Encoder.so: LMC1800/sources/media/omx/libOMX.SEC.M4V.Encoder.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

external/tvplay/tvrecv/HostPlatForm.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
.def.sym:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(COMPILE.def) -o $@ $<

# Not a target:
out/versions_checked.mk:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  Last modified 2012-02-17 11:18:14.938990848
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/include/memory.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/include/malloc.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
android.core.tests.runner:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
packages/wallpapers/MusicVisualization/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:59
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/include/sched.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/fsck_msdos/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:12
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
hardware/ti/wlan/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:52
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

.PHONY: droid FORCE dataclean installclean dist tvrecv NOTICE-TARGET-EXECUTABLES-tvrecv package-stats apkcerts-list systemimage event-log-tags notice_files otacerts recoveryimage systemimage-nodeps snod systemtarball-nodeps stnod userdataimage-nodeps userdatatarball-nodeps otatools installed-file-list tests-zip-package tests-build-target dalvikfiles checkapi update-api cts prebuilt all_copied_headers files checkbuild ramdisk systemtarball userdataimage userdatatarball bootimage droidcore dist_libraries droid tests all_modules docs sdk findbugs clean clobber modules showcommands
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/kernel: device/samsung/smdkv210/kernel | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
frameworks/base/data/sounds/effects/camera_click.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/wpa_supplicant/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:51
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

all_modules: tvrecv
#  Phony target (prerequisite of .PHONY).
#  Command-line target.
#  Implicit rule search has not been done.
#  File does not exist.
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/69=0%

# Not a target:
device/htc/passion/AndroidProducts.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:59
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/media/audio/notifications/F1_MissedCall.ogg: frameworks/base/data/sounds/F1_MissedCall.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

bionic/libc/arch-arm/include/machine/_types.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/svox/pico/lang/all_pico_languages.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:34
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/common/obj/all-event-log-tags.txt:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/Makefile', line 272)
# PRIVATE_SRC_FILES := 
# variable set hash-table stats:
# Load=1/32=3%, Rehash=0, Collisions=0/2=0%
#  commands to execute (from `build/core/Makefile', line 274):
	$(hide) mkdir -p $(dir $@)
	$(hide) build/tools/merge-event-log-tags.py -o $@ $(PRIVATE_SRC_FILES)
	

# Not a target:
vm-tests:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/include/sys/limits.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/newwavelabs/OrganDub.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/effects/KeypressSpacebar.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

tests: droidcore
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

external/tvplay/tvrecv/HostDateTimeServerAPI.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
LMC1800/sources/busybox:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

external/tvplay/tvrecv/HostTunerClientAPI.c:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/host/linux-x86/framework/signapk.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
.p.o:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(COMPILE.p) $(OUTPUT_OPTION) $<

# Not a target:
.p:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(LINK.p) $^ $(LOADLIBES) $(LDLIBS) -o $@

out/target/product/smdkv210/system/lib/libtv.so: LMC1800/sources/media/sf/libtv.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

droidcore: checkapi files systemimage out/target/product/smdkv210/ramdisk.img out/target/product/smdkv210/userdata.img out/target/product/smdkv210/installed-files.txt
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/Alarm_Beep_02.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
.txinfo.dvi:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(TEXI2DVI) $(TEXI2DVI_FLAGS) $<

# Not a target:
CtsMediaTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostSCClientAPI.P:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  Last modified 2012-02-27 22:04:05.790410102
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/include/sys/sysmacros.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
LMC1800/sources/dvb/libjnidvb.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/newwavelabs/RomancingTheTone.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/host/linux-x86/sdk/sdk_deps.mk:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  File does not exist.
#  File has been updated.
#  Failed to be updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/target/product/smdkv210/system/lib/libm.so:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 11:47:06
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
LMC1800/sources/dvb/libdvbbinderclient.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_01.ogg: frameworks/base/data/sounds/Alarm_Beep_01.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
system/extras/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:53:34
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/host/linux-x86/bin/minigzip:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
CtsPerformance5TestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/notifications/CaffeineSnake.ogg: frameworks/base/data/sounds/newwavelabs/CaffeineSnake.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/system/lib/libstagefright_enc_common.so: LMC1800/sources/media/sf/libstagefright_enc_common.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
CtsPreferenceTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/clearsilver/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:59
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/host/linux-x86/cts/android-cts/repository/testcases/android.core.vm-tests: vm-tests out/host/linux-x86/framework/descGen.jar out/target/common/obj/JAVA_LIBRARIES/core_intermediates/javalib.jar out/target/common/obj/JAVA_LIBRARIES/core-junit_intermediates/javalib.jar out/target/common/obj/JAVA_LIBRARIES/core-junitrunner_intermediates/javalib.jar out/host/linux-x86/obj/EXECUTABLES/vm-tests_intermediates/android.core.vm-tests.jar out/target/common/obj/JAVA_LIBRARIES/core-tests_intermediates/javalib.jar out/host/linux-x86/framework/hosttestlib.jar out/host/linux-x86/framework/ddmlib-prebuilt.jar out/host/linux-x86/cts/all_cts_files_stamp | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/tasks/cts.mk', line 135)
# PRIVATE_CLASSPATH := out/target/common/obj/JAVA_LIBRARIES/core_intermediates/classes.jar:out/target/common/obj/JAVA_LIBRARIES/core-junit_intermediates/classes.jar:out/target/common/obj/JAVA_LIBRARIES/core-junitrunner_intermediates/classes.jar:out/target/common/obj/JAVA_LIBRARIES/core-tests_intermediates/classes.jar:out/host/linux-x86/obj/EXECUTABLES/vm-tests_intermediates/android.core.vm-tests.jar:out/host/linux-x86/framework/descGen.jar:out/host/linux-x86/framework/hosttestlib.jar:out/host/linux-x86/framework/ddmlib-prebuilt.jar:/usr/lib/jvm/java-6-openjdk/lib/tools.jar
# makefile (from `build/core/tasks/cts.mk', line 138)
# PRIVATE_JAVAOPTS := -Xmx256M
# makefile (from `build/core/tasks/cts.mk', line 137)
# PRIVATE_PARAMS := -Dcts.useSuppliedTestResult=true -Dcts.useEnhancedJunit=true
# variable set hash-table stats:
# Load=3/32=9%, Rehash=0, Collisions=1/6=17%
#  commands to execute (from `build/core/tasks/cts.mk', line 141):
	$(call generate-core-test-description,$(CORE_VM_TEST_DESC),\
	cts/tests/vm-tests/AndroidManifest.xml,\
	dot.junit.AllJunitHostTests, libcore/expectations, cts/tools/vm-tests/Android.mk)
	$(ACP) -fv $(VMTESTS_INTERMEDIATES)/android.core.vm-tests.jar $(PRIVATE_DIR)/repository/testcases/android.core.vm-tests.jar
	

# Not a target:
frameworks/base/data/sounds/newwavelabs/KzurbSonar.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/svox/pico/lang/de-DE_ta.bin:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/obj/NOTICE.html.gz: out/target/product/smdkv210/obj/NOTICE.html | out/host/linux-x86/bin/minigzip
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 511):
	$(hide) $(MINIGZIP) -9 < $< > $@
	

# Not a target:
frameworks/base/data/sounds/newwavelabs/CrazyDream.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/Ring_Classic_02.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/effects/KeypressReturn.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/target/common/obj/JAVA_LIBRARIES/core_intermediates/javalib.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
build/core/combo/select.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/include/unistd.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/fdlibm/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:12
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
vendor/samsung/smdkv210/proprietary/libPVRScopeServices.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/host/linux-x86/obj/EXECUTABLES/vm-tests_intermediates/android.core.vm-tests.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/host/linux-x86/obj/NOTICE.txt: out/host/linux-x86/obj/NOTICE_FILES/hash-timestamp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/Makefile', line 498)
# PRIVATE_DIR := out/host/linux-x86/obj/NOTICE_FILES
# makefile (from `build/core/Makefile', line 498)
# PRIVATE_MESSAGE := "Notices for files contained in the tools directory:"
# variable set hash-table stats:
# Load=2/32=6%, Rehash=0, Collisions=0/2=0%
#  commands to execute (from `build/core/Makefile', line 498):
	@echo Combining NOTICE files: $@
	$(hide) mkdir -p $(dir $@)
	$(hide) echo $(PRIVATE_MESSAGE) > $@
	$(hide) find $(PRIVATE_DIR)/hash -type f | xargs cat | sort | sed -e "s:$(PRIVATE_DIR)/src\(.*\)\.txt:  \1:" >> $@
	$(hide) echo >> $@
	$(hide) echo >> $@
	$(hide) echo >> $@
	$(hide) for hashfile in $$(find $(PRIVATE_DIR)/hash -type f); do echo "============================================================" >> $@; echo "Notices for file(s):" >> $@; cat $$hashfile | sort | sed -e "s:$(PRIVATE_DIR)/src\(.*\)\.txt:  \1:" >> $@; echo "------------------------------------------------------------" >> $@; echo >> $@; orig=$$(head -n 1 $$hashfile); cat $$orig >> $@; echo >> $@; echo >> $@; echo >> $@; done
	

# Not a target:
frameworks/base/data/sounds/newwavelabs/Noises3.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/ringtones/SpringyJalopy.ogg: frameworks/base/data/sounds/newwavelabs/SpringyJalopy.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/findbugs.html: out/target/product/smdkv210/findbugs.xml
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 1356):
	@echo ConvertXmlToText: $@
	$(hide) prebuilt/common/findbugs/bin/convertXmlToText -html:fancy.xsl \
	$(INTERNAL_FINDBUGS_XML_TARGET)	> $@
	

out/target/product/smdkv210/system/media/audio/ui/KeypressStandard.ogg: frameworks/base/data/sounds/effects/KeypressStandard.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/system/media/audio/ui/camera_click.ogg: frameworks/base/data/sounds/effects/camera_click.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/system/vendor/lib/libpvrANDROID_WSEGL.so: vendor/samsung/smdkv210/proprietary/libpvrANDROID_WSEGL.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

dist_libraries:
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/ui/KeypressDelete.ogg: frameworks/base/data/sounds/effects/KeypressDelete.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
external/svox/pico/lang/PicoLangFrFrInSystem.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:34
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/arch-arm/include/machine/kernel.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
.l.ln:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	@$(RM) $*.c
	$(LEX.l) $< > $*.c
	$(LINT.c) -i $*.c -o $@
	$(RM) $*.c

# Not a target:
LMC1800/sources/utiusb.ko:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/dex_bootjars/system/framework/ext_nodex.jar: out/target/common/obj/JAVA_LIBRARIES/ext_intermediates/javalib.jar | out/host/linux-x86/bin/acp out/host/linux-x86/bin/aapt
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/dex_preopt.mk', line 66):
	$(call copy-file-to-target)
	$(call dexpreopt-remove-classes.dex,$@)
	

# Not a target:
LMC1800/sources/media/sf/liba52.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/expat/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/nist-sip/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:44
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
.w.c:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(CTANGLE) $< - $@

# Not a target:
.texi.dvi:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(TEXI2DVI) $(TEXI2DVI_FLAGS) $<

external/tvplay/tvrecv/HostAppInfoClientAPI.c:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
vendor/samsung/smdkv210/device-vendor-blobs.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:44
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/main.o: external/tvplay/tvrecv/main.c system/core/include/arch/linux-arm/AndroidConfig.h external/tvplay/tvrecv/HostIncludeApi.h bionic/libc/include/fcntl.h bionic/libc/include/sys/cdefs.h bionic/libc/include/sys/cdefs_elf.h bionic/libc/include/sys/types.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stddef.h bionic/libc/include/stdint.h bionic/libc/include/sys/_types.h bionic/libc/arch-arm/include/machine/_types.h bionic/libc/kernel/common/linux/posix_types.h bionic/libc/kernel/common/linux/stddef.h bionic/libc/kernel/common/linux/compiler.h bionic/libc/kernel/arch-arm/asm/posix_types.h bionic/libc/kernel/arch-arm/asm/types.h bionic/libc/kernel/common/linux/types.h bionic/libc/arch-arm/include/machine/kernel.h bionic/libc/include/sys/sysmacros.h bionic/libc/kernel/common/linux/fcntl.h bionic/libc/kernel/arch-arm/asm/fcntl.h bionic/libc/kernel/common/asm-generic/fcntl.h bionic/libc/include/unistd.h bionic/libc/include/sys/select.h bionic/libc/include/sys/time.h bionic/libc/kernel/common/linux/time.h bionic/libc/include/signal.h bionic/libc/include/limits.h bionic/libc/include/sys/limits.h bionic/libc/kernel/common/linux/limits.h bionic/libc/arch-arm/include/machine/internal_types.h bionic/libc/arch-arm/include/machine/limits.h bionic/libc/include/sys/syslimits.h bionic/libc/include/string.h bionic/libc/include/malloc.h bionic/libc/kernel/arch-arm/asm/signal.h bionic/libc/kernel/common/asm-generic/signal.h bionic/libc/kernel/arch-arm/asm/siginfo.h bionic/libc/kernel/common/asm-generic/siginfo.h bionic/libc/include/sys/sysconf.h bionic/libc/kernel/common/linux/capability.h bionic/libc/include/pathconf.h bionic/libc/include/stdlib.h bionic/libc/include/alloca.h bionic/libc/include/strings.h bionic/libc/include/memory.h bionic/libc/include/stdio.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stdarg.h bionic/libc/include/pthread.h bionic/libc/include/time.h bionic/libc/include/sched.h bionic/libc/include/sys/ioctl.h bionic/libc/kernel/common/linux/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctl.h bionic/libc/kernel/common/asm-generic/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctls.h bionic/libc/kernel/arch-arm/asm/termbits.h bionic/libc/include/sys/ioctl_compat.h external/tvplay/tvrecv/HostUTIAPI.h external/tvplay/tvrecv/HostDeviceAPI.h external/tvplay/tvrecv/HostPlatForm.h external/tvplay/tvrecv/debug.h external/tvplay/tvrecv/HostCAClientAPI.h external/tvplay/tvrecv/HostAppInfoClientAPI.h external/tvplay/tvrecv/HostEPGDBClientAPI.h external/tvplay/tvrecv/HostTunerClientAPI.h external/tvplay/tvrecv/HostRgnClientAPI.h external/tvplay/tvrecv/HostSCClientAPI.h external/tvplay/tvrecv/HostDateTimeServerAPI.h external/tvplay/tvrecv/SocketHeader.h bionic/libc/include/sys/socket.h bionic/libc/kernel/common/linux/socket.h bionic/libc/kernel/arch-arm/asm/socket.h bionic/libc/kernel/arch-arm/asm/sockios.h bionic/libc/kernel/common/linux/sockios.h bionic/libc/kernel/common/linux/uio.h bionic/libc/include/netinet/in.h bionic/libc/arch-arm/include/endian.h bionic/libc/include/sys/endian.h bionic/libc/kernel/common/linux/in.h bionic/libc/kernel/arch-arm/asm/byteorder.h bionic/libc/kernel/common/linux/byteorder/little_endian.h bionic/libc/kernel/common/linux/byteorder/swab.h bionic/libc/kernel/common/linux/byteorder/generic.h bionic/libc/kernel/common/linux/in6.h bionic/libc/include/netinet/in6.h bionic/libc/include/arpa/inet.h | all_copied_headers
#  Implicit rule search has not been done.
#  Implicit/static pattern stem: `main'
#  Last modified 2012-03-01 19:20:58.251885596
#  File has been updated.
#  Successfully updated.
# automatic
# @ := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/main.o
# automatic
# % := 
# automatic
# * := main
# automatic
# + := external/tvplay/tvrecv/main.c external/tvplay/tvrecv/main.c system/core/include/arch/linux-arm/AndroidConfig.h external/tvplay/tvrecv/HostIncludeApi.h bionic/libc/include/fcntl.h bionic/libc/include/sys/cdefs.h bionic/libc/include/sys/cdefs_elf.h bionic/libc/include/sys/types.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stddef.h bionic/libc/include/stdint.h bionic/libc/include/sys/_types.h bionic/libc/arch-arm/include/machine/_types.h bionic/libc/kernel/common/linux/posix_types.h bionic/libc/kernel/common/linux/stddef.h bionic/libc/kernel/common/linux/compiler.h bionic/libc/kernel/arch-arm/asm/posix_types.h bionic/libc/kernel/arch-arm/asm/types.h bionic/libc/kernel/common/linux/types.h bionic/libc/arch-arm/include/machine/kernel.h bionic/libc/include/sys/sysmacros.h bionic/libc/kernel/common/linux/fcntl.h bionic/libc/kernel/arch-arm/asm/fcntl.h bionic/libc/kernel/common/asm-generic/fcntl.h bionic/libc/include/unistd.h bionic/libc/include/sys/select.h bionic/libc/include/sys/time.h bionic/libc/kernel/common/linux/time.h bionic/libc/include/signal.h bionic/libc/include/limits.h bionic/libc/include/sys/limits.h bionic/libc/kernel/common/linux/limits.h bionic/libc/arch-arm/include/machine/internal_types.h bionic/libc/arch-arm/include/machine/limits.h bionic/libc/include/sys/syslimits.h bionic/libc/include/string.h bionic/libc/include/malloc.h bionic/libc/kernel/arch-arm/asm/signal.h bionic/libc/kernel/common/asm-generic/signal.h bionic/libc/kernel/arch-arm/asm/siginfo.h bionic/libc/kernel/common/asm-generic/siginfo.h bionic/libc/include/sys/sysconf.h bionic/libc/kernel/common/linux/capability.h bionic/libc/include/pathconf.h bionic/libc/include/stdlib.h bionic/libc/include/alloca.h bionic/libc/include/strings.h bionic/libc/include/memory.h bionic/libc/include/stdio.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stdarg.h bionic/libc/include/pthread.h bionic/libc/include/time.h bionic/libc/include/sched.h bionic/libc/include/sys/ioctl.h bionic/libc/kernel/common/linux/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctl.h bionic/libc/kernel/common/asm-generic/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctls.h bionic/libc/kernel/arch-arm/asm/termbits.h bionic/libc/include/sys/ioctl_compat.h external/tvplay/tvrecv/HostUTIAPI.h external/tvplay/tvrecv/HostDeviceAPI.h external/tvplay/tvrecv/HostPlatForm.h external/tvplay/tvrecv/debug.h external/tvplay/tvrecv/HostCAClientAPI.h external/tvplay/tvrecv/HostAppInfoClientAPI.h external/tvplay/tvrecv/HostEPGDBClientAPI.h external/tvplay/tvrecv/HostTunerClientAPI.h external/tvplay/tvrecv/HostRgnClientAPI.h external/tvplay/tvrecv/HostSCClientAPI.h external/tvplay/tvrecv/HostDateTimeServerAPI.h external/tvplay/tvrecv/SocketHeader.h bionic/libc/include/sys/socket.h bionic/libc/kernel/common/linux/socket.h bionic/libc/kernel/arch-arm/asm/socket.h bionic/libc/kernel/arch-arm/asm/sockios.h bionic/libc/kernel/common/linux/sockios.h bionic/libc/kernel/common/linux/uio.h bionic/libc/include/netinet/in.h bionic/libc/arch-arm/include/endian.h bionic/libc/include/sys/endian.h bionic/libc/kernel/common/linux/in.h bionic/libc/kernel/arch-arm/asm/byteorder.h bionic/libc/kernel/common/linux/byteorder/little_endian.h bionic/libc/kernel/common/linux/byteorder/swab.h bionic/libc/kernel/common/linux/byteorder/generic.h bionic/libc/kernel/common/linux/in6.h bionic/libc/include/netinet/in6.h bionic/libc/include/arpa/inet.h
# makefile (from `build/core/binary.mk', line 261)
# PRIVATE_ARM_MODE := thumb
# automatic
# | := all_copied_headers
# makefile (from `build/core/binary.mk', line 262)
# PRIVATE_ARM_CFLAGS := -mthumb -Os -fomit-frame-pointer -fno-strict-aliasing -finline-limit=64
# automatic
# < := external/tvplay/tvrecv/main.c
# automatic
# ^ := external/tvplay/tvrecv/main.c system/core/include/arch/linux-arm/AndroidConfig.h external/tvplay/tvrecv/HostIncludeApi.h bionic/libc/include/fcntl.h bionic/libc/include/sys/cdefs.h bionic/libc/include/sys/cdefs_elf.h bionic/libc/include/sys/types.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stddef.h bionic/libc/include/stdint.h bionic/libc/include/sys/_types.h bionic/libc/arch-arm/include/machine/_types.h bionic/libc/kernel/common/linux/posix_types.h bionic/libc/kernel/common/linux/stddef.h bionic/libc/kernel/common/linux/compiler.h bionic/libc/kernel/arch-arm/asm/posix_types.h bionic/libc/kernel/arch-arm/asm/types.h bionic/libc/kernel/common/linux/types.h bionic/libc/arch-arm/include/machine/kernel.h bionic/libc/include/sys/sysmacros.h bionic/libc/kernel/common/linux/fcntl.h bionic/libc/kernel/arch-arm/asm/fcntl.h bionic/libc/kernel/common/asm-generic/fcntl.h bionic/libc/include/unistd.h bionic/libc/include/sys/select.h bionic/libc/include/sys/time.h bionic/libc/kernel/common/linux/time.h bionic/libc/include/signal.h bionic/libc/include/limits.h bionic/libc/include/sys/limits.h bionic/libc/kernel/common/linux/limits.h bionic/libc/arch-arm/include/machine/internal_types.h bionic/libc/arch-arm/include/machine/limits.h bionic/libc/include/sys/syslimits.h bionic/libc/include/string.h bionic/libc/include/malloc.h bionic/libc/kernel/arch-arm/asm/signal.h bionic/libc/kernel/common/asm-generic/signal.h bionic/libc/kernel/arch-arm/asm/siginfo.h bionic/libc/kernel/common/asm-generic/siginfo.h bionic/libc/include/sys/sysconf.h bionic/libc/kernel/common/linux/capability.h bionic/libc/include/pathconf.h bionic/libc/include/stdlib.h bionic/libc/include/alloca.h bionic/libc/include/strings.h bionic/libc/include/memory.h bionic/libc/include/stdio.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stdarg.h bionic/libc/include/pthread.h bionic/libc/include/time.h bionic/libc/include/sched.h bionic/libc/include/sys/ioctl.h bionic/libc/kernel/common/linux/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctl.h bionic/libc/kernel/common/asm-generic/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctls.h bionic/libc/kernel/arch-arm/asm/termbits.h bionic/libc/include/sys/ioctl_compat.h external/tvplay/tvrecv/HostUTIAPI.h external/tvplay/tvrecv/HostDeviceAPI.h external/tvplay/tvrecv/HostPlatForm.h external/tvplay/tvrecv/debug.h external/tvplay/tvrecv/HostCAClientAPI.h external/tvplay/tvrecv/HostAppInfoClientAPI.h external/tvplay/tvrecv/HostEPGDBClientAPI.h external/tvplay/tvrecv/HostTunerClientAPI.h external/tvplay/tvrecv/HostRgnClientAPI.h external/tvplay/tvrecv/HostSCClientAPI.h external/tvplay/tvrecv/HostDateTimeServerAPI.h external/tvplay/tvrecv/SocketHeader.h bionic/libc/include/sys/socket.h bionic/libc/kernel/common/linux/socket.h bionic/libc/kernel/arch-arm/asm/socket.h bionic/libc/kernel/arch-arm/asm/sockios.h bionic/libc/kernel/common/linux/sockios.h bionic/libc/kernel/common/linux/uio.h bionic/libc/include/netinet/in.h bionic/libc/arch-arm/include/endian.h bionic/libc/include/sys/endian.h bionic/libc/kernel/common/linux/in.h bionic/libc/kernel/arch-arm/asm/byteorder.h bionic/libc/kernel/common/linux/byteorder/little_endian.h bionic/libc/kernel/common/linux/byteorder/swab.h bionic/libc/kernel/common/linux/byteorder/generic.h bionic/libc/kernel/common/linux/in6.h bionic/libc/include/netinet/in6.h bionic/libc/include/arpa/inet.h
# automatic
# ? := external/tvplay/tvrecv/main.c
# variable set hash-table stats:
# Load=10/32=31%, Rehash=0, Collisions=3/49=6%
#  commands to execute (from `build/core/binary.mk', line 268):
	$(transform-$(PRIVATE_HOST)c-to-o)
	

# Not a target:
.sh:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	cat $< >$@ 
	chmod a+x $@

# Not a target:
LMC1800/sources/media/sf/libstagefright_omx.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

tests-build-target: out/target/product/smdkv210/obj/PACKAGING/tests_zip_intermediates/full_smdkv210-tests-eng.lnt.zip out/target/product/smdkv210/userdata.img
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

# Not a target:
LMC1800/sources/media/omx/libOMX.SEC.M4V.Encoder.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/host/linux-x86/obj/NOTICE_FILES/hash-timestamp: out/target/product/smdkv210/system/bin/tvrecv out/target/product/smdkv210/root/init.rc out/target/product/smdkv210/system/etc/vold.fstab out/target/product/smdkv210/system/lib/egl/egl.cfg out/target/product/smdkv210/root/init.smdkv210.rc out/target/product/smdkv210/root/ueventd.smdkv210.rc out/target/product/smdkv210/system/usr/keylayout/s3c-keypad.kl out/target/product/smdkv210/system/etc/media_profiles.xml out/target/product/smdkv210/system/etc/secomxregistry out/target/product/smdkv210/system/lib/libSEC_OMX_Core.so out/target/product/smdkv210/system/lib/libSEC_Resourcemanager.so out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M2V.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.WMV.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Encoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Encoder.so out/target/product/smdkv210/system/lib/libstagefright_amrnb_common.so out/target/product/smdkv210/system/lib/libstagefright_enc_common.so out/target/product/smdkv210/system/lib/libstagefright_avc_common.so out/target/product/smdkv210/system/lib/libstagefright_foundation.so out/target/product/smdkv210/system/lib/libstagefright_color_conversion.so out/target/product/smdkv210/system/lib/libstagefright.so out/target/product/smdkv210/system/lib/libstagefright_omx.so out/target/product/smdkv210/system/lib/liba52.so out/target/product/smdkv210/system/bin/playts out/target/product/smdkv210/system/lib/libtv.so out/target/product/smdkv210/system/bin/remount_ctl out/target/product/smdkv210/system/media/bootanimation.zip out/target/product/smdkv210/system/app/PrimaryApp.apk out/target/product/smdkv210/system/app/HomePage.apk out/target/product/smdkv210/system/app/FileManagers.apk out/target/product/smdkv210/system/app/Game.apk out/target/product/smdkv210/system/lib/libmoddvb.so out/target/product/smdkv210/system/lib/libjnidvb.so out/target/product/smdkv210/system/lib/libdvbbinderclient.so out/target/product/smdkv210/system/lib/libdvbcomm.so out/target/product/smdkv210/system/lib/modules/utiusb.ko out/target/product/smdkv210/system/bin/dvb_load out/target/product/smdkv210/system/bin/busybox out/target/product/smdkv210/system/bin/bash out/target/product/smdkv210/system/bin/dvb_server out/target/product/smdkv210/system/etc/wifi/wpa_supplicant.conf out/target/product/smdkv210/system/etc/dhd.ko out/target/product/smdkv210/system/etc/fw_bcm4329.bin out/target/product/smdkv210/system/etc/nvram.txt out/target/product/smdkv210/system/etc/permissions/handheld_core_hardware.xml out/target/product/smdkv210/system/etc/permissions/android.software.live_wallpaper.xml out/target/product/smdkv210/kernel out/target/product/smdkv210/system/etc/permissions/android.software.live_wallpaper.xml out/target/product/smdkv210/system/vendor/bin/pvrsrvinit out/target/product/smdkv210/system/vendor/lib/egl/libEGL_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/egl/libGLESv1_CM_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/egl/libGLESv2_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/libglslcompiler.so out/target/product/smdkv210/system/vendor/lib/libIMGegl.so out/target/product/smdkv210/system/vendor/lib/libpvr2d.so out/target/product/smdkv210/system/vendor/lib/libpvrANDROID_WSEGL.so out/target/product/smdkv210/system/vendor/lib/libPVRScopeServices.so out/target/product/smdkv210/system/vendor/lib/libsrv_init.so out/target/product/smdkv210/system/vendor/lib/libsrv_um.so out/target/product/smdkv210/system/vendor/lib/libusc.so out/target/product/smdkv210/system/vendor/lib/hw/gralloc.s5pc110.so out/target/product/smdkv210/system/etc/apns-conf.xml out/target/product/smdkv210/system/etc/vold.conf out/target/product/smdkv210/system/media/audio/notifications/F1_MissedCall.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_New_MMS.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_New_SMS.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Buzzer.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_01.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_02.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Classic.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_03.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Rooster_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Classic_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Digital_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_04.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/BeatPlucker.ogg out/target/product/smdkv210/system/media/audio/ringtones/BentleyDubs.ogg out/target/product/smdkv210/system/media/audio/ringtones/BirdLoop.ogg out/target/product/smdkv210/system/media/audio/ringtones/CaribbeanIce.ogg out/target/product/smdkv210/system/media/audio/ringtones/CurveBall.ogg out/target/product/smdkv210/system/media/audio/ringtones/EtherShake.ogg out/target/product/smdkv210/system/media/audio/ringtones/FriendlyGhost.ogg out/target/product/smdkv210/system/media/audio/ringtones/GameOverGuitar.ogg out/target/product/smdkv210/system/media/audio/ringtones/Growl.ogg out/target/product/smdkv210/system/media/audio/ringtones/InsertCoin.ogg out/target/product/smdkv210/system/media/audio/ringtones/LoopyLounge.ogg out/target/product/smdkv210/system/media/audio/ringtones/LoveFlute.ogg out/target/product/smdkv210/system/media/audio/ringtones/MidEvilJaunt.ogg out/target/product/smdkv210/system/media/audio/ringtones/MildlyAlarming.ogg out/target/product/smdkv210/system/media/audio/ringtones/NewPlayer.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises1.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises2.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises3.ogg out/target/product/smdkv210/system/media/audio/ringtones/OrganDub.ogg out/target/product/smdkv210/system/media/audio/ringtones/RomancingTheTone.ogg out/target/product/smdkv210/system/media/audio/ringtones/SitarVsSitar.ogg out/target/product/smdkv210/system/media/audio/ringtones/SpringyJalopy.ogg out/target/product/smdkv210/system/media/audio/ringtones/Terminated.ogg out/target/product/smdkv210/system/media/audio/ringtones/TwirlAway.ogg out/target/product/smdkv210/system/media/audio/ringtones/VeryAlarmed.ogg out/target/product/smdkv210/system/media/audio/ringtones/World.ogg out/target/product/smdkv210/system/media/audio/notifications/CaffeineSnake.ogg out/target/product/smdkv210/system/media/audio/notifications/DearDeer.ogg out/target/product/smdkv210/system/media/audio/notifications/DontPanic.ogg out/target/product/smdkv210/system/media/audio/notifications/Highwire.ogg out/target/product/smdkv210/system/media/audio/notifications/KzurbSonar.ogg out/target/product/smdkv210/system/media/audio/notifications/OnTheHunt.ogg out/target/product/smdkv210/system/media/audio/notifications/Voila.ogg out/target/product/smdkv210/system/media/audio/notifications/Beat_Box_Android.ogg out/target/product/smdkv210/system/media/audio/notifications/Heaven.ogg out/target/product/smdkv210/system/media/audio/notifications/TaDa.ogg out/target/product/smdkv210/system/media/audio/notifications/Tinkerbell.ogg out/target/product/smdkv210/system/media/audio/ui/Effect_Tick.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressStandard.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressSpacebar.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressDelete.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressReturn.ogg out/target/product/smdkv210/system/media/audio/ui/VideoRecord.ogg out/target/product/smdkv210/system/media/audio/ui/camera_click.ogg out/target/product/smdkv210/system/media/audio/ringtones/CrazyDream.ogg out/target/product/smdkv210/system/media/audio/ringtones/DreamTheme.ogg out/target/product/smdkv210/system/tts/lang_pico/de-DE_gl0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/de-DE_ta.bin out/target/product/smdkv210/system/tts/lang_pico/en-GB_kh0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/en-GB_ta.bin out/target/product/smdkv210/system/tts/lang_pico/en-US_lh0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/en-US_ta.bin out/target/product/smdkv210/system/tts/lang_pico/es-ES_zl0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/es-ES_ta.bin out/target/product/smdkv210/system/tts/lang_pico/fr-FR_nk0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/fr-FR_ta.bin out/target/product/smdkv210/system/tts/lang_pico/it-IT_cm0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/it-IT_ta.bin out/target/product/smdkv210/root/default.prop out/target/product/smdkv210/system/build.prop out/target/product/smdkv210/system/etc/event-log-tags build/core/Makefile
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/Makefile', line 498)
# PRIVATE_DIR := out/host/linux-x86/obj/NOTICE_FILES
# variable set hash-table stats:
# Load=1/32=3%, Rehash=0, Collisions=0/1=0%
#  commands to execute (from `build/core/Makefile', line 498):
	@echo Finding NOTICE files: $@
	$(hide) rm -rf $@ $(PRIVATE_DIR)/hash
	$(hide) mkdir -p $(PRIVATE_DIR)/hash
	$(hide) for file in $$(find $(PRIVATE_DIR)/src -type f); do hash=$$(md5sum $$file | sed -e "s/ .*//"); hashfile=$(PRIVATE_DIR)/hash/$$hash; echo $$file >> $$hashfile; done
	$(hide) touch $@
	

out/target/product/smdkv210/package-stats.txt: out/target/product/smdkv210/system/app/FileManagers.apk out/target/product/smdkv210/system/app/Game.apk out/target/product/smdkv210/system/app/HomePage.apk out/target/product/smdkv210/system/app/PrimaryApp.apk
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 187):
	@echo Package stats: $@
	@mkdir -p $(dir $@)
	$(hide) rm -f $@
	$(hide) build/tools/dump-package-stats $^ > $@
	

# Not a target:
device/sample/products/AndroidProducts.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:54
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
dalvik/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:14
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/build.prop: build/tools/buildinfo.sh build/core/build_id.mk
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/Makefile', line 72)
# PRIVATE_BUILD_DESC := full_smdkv210-eng 2.3.1 GINGERBREAD eng.lnt.20120301.192057 test-keys
# variable set hash-table stats:
# Load=1/32=3%, Rehash=0, Collisions=0/2=0%
#  commands to execute (from `build/core/Makefile', line 116):
	@echo Target buildinfo: $@
	@mkdir -p $(dir $@)
	$(hide) TARGET_BUILD_TYPE="$(TARGET_BUILD_VARIANT)" \
	TARGET_DEVICE="$(TARGET_DEVICE)" \
	PRODUCT_NAME="$(TARGET_PRODUCT)" \
	PRODUCT_BRAND="$(PRODUCT_BRAND)" \
	PRODUCT_DEFAULT_LANGUAGE="$(call default-locale-language,$(PRODUCT_LOCALES))" \
	PRODUCT_DEFAULT_REGION="$(call default-locale-region,$(PRODUCT_LOCALES))" \
	PRODUCT_DEFAULT_WIFI_CHANNELS="$(PRODUCT_DEFAULT_WIFI_CHANNELS)" \
	PRODUCT_MODEL="$(PRODUCT_MODEL)" \
	PRODUCT_MANUFACTURER="$(PRODUCT_MANUFACTURER)" \
	PRIVATE_BUILD_DESC="$(PRIVATE_BUILD_DESC)" \
	BUILD_ID="$(BUILD_ID)" \
	BUILD_DISPLAY_ID="$(BUILD_DISPLAY_ID)" \
	BUILD_NUMBER="$(BUILD_NUMBER)" \
	PLATFORM_VERSION="$(PLATFORM_VERSION)" \
	PLATFORM_SDK_VERSION="$(PLATFORM_SDK_VERSION)" \
	PLATFORM_VERSION_CODENAME="$(PLATFORM_VERSION_CODENAME)" \
	BUILD_VERSION_TAGS="$(BUILD_VERSION_TAGS)" \
	TARGET_BOOTLOADER_BOARD_NAME="$(TARGET_BOOTLOADER_BOARD_NAME)" \
	BUILD_FINGERPRINT="$(BUILD_FINGERPRINT)" \
	TARGET_BOARD_PLATFORM="$(TARGET_BOARD_PLATFORM)" \
	TARGET_CPU_ABI="$(TARGET_CPU_ABI)" \
	TARGET_CPU_ABI2="$(TARGET_CPU_ABI2)" \
	bash $(BUILDINFO_SH) > $@
	$(hide) if [ -f $(TARGET_DEVICE_DIR)/system.prop ]; then \
	cat $(TARGET_DEVICE_DIR)/system.prop >> $@; \
	fi
	$(if $(ADDITIONAL_BUILD_PROPERTIES), \
	$(hide) echo >> $@; \
	echo "#" >> $@; \
	echo "# ADDITIONAL_BUILD_PROPERTIES" >> $@; \
	echo "#" >> $@; )
	$(hide) $(foreach line,$(ADDITIONAL_BUILD_PROPERTIES), \
	echo "$(line)" >> $@;)
	

# Not a target:
LMC1800/sources/media/omx/libOMX.SEC.M2V.Decoder.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/alarms/Alarm_Classic.ogg: frameworks/base/data/sounds/Alarm_Classic.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
build/core/distdir.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/main.P:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  Last modified 2012-03-01 19:19:20.135890564
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/media/audio/ringtones/EtherShake.ogg: frameworks/base/data/sounds/newwavelabs/EtherShake.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
external/jdiff/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:48:27
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
hardware/ti/omap3/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:51
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
.cc:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(LINK.cc) $^ $(LOADLIBES) $(LDLIBS) -o $@

# Not a target:
external/embunit/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:38
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Encoder.so: LMC1800/sources/media/omx/libOMX.SEC.AVC.Encoder.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
out/host/linux-x86/bin/apicheck:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
LMC1800/sources/media/sf/libstagefright_amrnb_common.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/usr/keylayout/s3c-keypad.kl: device/samsung/smdkv210/s3c-keypad.kl | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
CtsProviderTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/ringtones/GameOverGuitar.ogg: frameworks/base/data/sounds/newwavelabs/GameOverGuitar.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

bionic/libc/include/sys/time.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/providers/ApplicationsProvider/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:02:00
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/products.pdf: out/products.dot
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/tasks/product-graph.mk', line 44):
	@echo Product graph PDF: $@
	dot -Tpdf -Nshape=box -o $@ $<
	

# Not a target:
.cc.o:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(COMPILE.cc) $(OUTPUT_OPTION) $<

# Not a target:
frameworks/base/data/sounds/effects/KeypressDelete.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/notifications/F1_New_SMS.ogg: frameworks/base/data/sounds/F1_New_SMS.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
dalvik/tools/dex-preopt:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/F1_New_MMS.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
packages/apps/Phone/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:41
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
CtsInstrumentationAppDiffCert:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/guava/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:53:10
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/common/docs/index.html: frameworks/base/docs/docs-redirect-index.html
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 33):
	@mkdir -p $(dir $@)
	@cp -f $< $@
	

# Not a target:
vendor/samsung/smdkv210/proprietary/libusc.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDateTimeServerAPI.o: external/tvplay/tvrecv/HostDateTimeServerAPI.c external/tvplay/tvrecv/HostDateTimeServerAPI.c system/core/include/arch/linux-arm/AndroidConfig.h external/tvplay/tvrecv/HostIncludeApi.h bionic/libc/include/fcntl.h bionic/libc/include/sys/cdefs.h bionic/libc/include/sys/cdefs_elf.h bionic/libc/include/sys/types.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stddef.h bionic/libc/include/stdint.h bionic/libc/include/sys/_types.h bionic/libc/arch-arm/include/machine/_types.h bionic/libc/kernel/common/linux/posix_types.h bionic/libc/kernel/common/linux/stddef.h bionic/libc/kernel/common/linux/compiler.h bionic/libc/kernel/arch-arm/asm/posix_types.h bionic/libc/kernel/arch-arm/asm/types.h bionic/libc/kernel/common/linux/types.h bionic/libc/arch-arm/include/machine/kernel.h bionic/libc/include/sys/sysmacros.h bionic/libc/kernel/common/linux/fcntl.h bionic/libc/kernel/arch-arm/asm/fcntl.h bionic/libc/kernel/common/asm-generic/fcntl.h bionic/libc/include/unistd.h bionic/libc/include/sys/select.h bionic/libc/include/sys/time.h bionic/libc/kernel/common/linux/time.h bionic/libc/include/signal.h bionic/libc/include/limits.h bionic/libc/include/sys/limits.h bionic/libc/kernel/common/linux/limits.h bionic/libc/arch-arm/include/machine/internal_types.h bionic/libc/arch-arm/include/machine/limits.h bionic/libc/include/sys/syslimits.h bionic/libc/include/string.h bionic/libc/include/malloc.h bionic/libc/kernel/arch-arm/asm/signal.h bionic/libc/kernel/common/asm-generic/signal.h bionic/libc/kernel/arch-arm/asm/siginfo.h bionic/libc/kernel/common/asm-generic/siginfo.h bionic/libc/include/sys/sysconf.h bionic/libc/kernel/common/linux/capability.h bionic/libc/include/pathconf.h bionic/libc/include/stdlib.h bionic/libc/include/alloca.h bionic/libc/include/strings.h bionic/libc/include/memory.h bionic/libc/include/stdio.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stdarg.h bionic/libc/include/pthread.h bionic/libc/include/time.h bionic/libc/include/sched.h bionic/libc/include/sys/ioctl.h bionic/libc/kernel/common/linux/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctl.h bionic/libc/kernel/common/asm-generic/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctls.h bionic/libc/kernel/arch-arm/asm/termbits.h bionic/libc/include/sys/ioctl_compat.h external/tvplay/tvrecv/HostUTIAPI.h external/tvplay/tvrecv/HostDeviceAPI.h external/tvplay/tvrecv/HostPlatForm.h external/tvplay/tvrecv/debug.h external/tvplay/tvrecv/HostCAClientAPI.h external/tvplay/tvrecv/HostAppInfoClientAPI.h external/tvplay/tvrecv/HostEPGDBClientAPI.h external/tvplay/tvrecv/HostTunerClientAPI.h external/tvplay/tvrecv/HostRgnClientAPI.h external/tvplay/tvrecv/HostSCClientAPI.h external/tvplay/tvrecv/HostDateTimeServerAPI.h | all_copied_headers
#  Implicit rule search has not been done.
#  Implicit/static pattern stem: `HostDateTimeServerAPI'
#  Last modified 2012-02-27 22:04:05.262410117
#  File has been updated.
#  Successfully updated.
# makefile (from `build/core/binary.mk', line 261)
# PRIVATE_ARM_MODE := thumb
# makefile (from `build/core/binary.mk', line 262)
# PRIVATE_ARM_CFLAGS := -mthumb -Os -fomit-frame-pointer -fno-strict-aliasing -finline-limit=64
# variable set hash-table stats:
# Load=2/32=6%, Rehash=0, Collisions=0/4=0%
#  commands to execute (from `build/core/binary.mk', line 268):
	$(transform-$(PRIVATE_HOST)c-to-o)
	

# Not a target:
prebuilt/android-arm/kernel/kernel-qemu:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/include/sys/socket.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/target/common/obj/JAVA_LIBRARIES/ext_intermediates/javalib.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/arch-arm/include/endian.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
hardware/libhardware/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:45
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

stnod: systemtarball-nodeps
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/ringtones/LoveFlute.ogg: frameworks/base/data/sounds/newwavelabs/LoveFlute.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

.SUFFIXES:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/include/stdio.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
device/samsung/smdkv210/media_profiles.xml:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
build/core/product_config.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/vendor/lib/libIMGegl.so: vendor/samsung/smdkv210/proprietary/libIMGegl.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
LMC1800/sources/media/sf/libtv.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDateTimeServerAPI.P:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  Last modified 2012-02-27 22:04:05.274410117
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
build/target/product/languages_full.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/include/arpa/inet.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

userdataimage: out/target/product/smdkv210/userdata.img
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

out/target/product/smdkv210/system/vendor/lib/egl/libEGL_POWERVR_SGX540_120.so: vendor/samsung/smdkv210/proprietary/libEGL_POWERVR_SGX540_120.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
device/htc/common/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:59
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/target/product/smdkv210/obj/EXECUTABLES/applypatch_static_intermediates/applypatch_static:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/notifications/Heaven.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

files: prebuilt out/target/product/smdkv210/kernel out/target/product/smdkv210/root/default.prop out/target/product/smdkv210/root/init.rc out/target/product/smdkv210/root/init.smdkv210.rc out/target/product/smdkv210/root/ueventd.smdkv210.rc out/target/product/smdkv210/system/app/FileManagers.apk out/target/product/smdkv210/system/app/Game.apk out/target/product/smdkv210/system/app/HomePage.apk out/target/product/smdkv210/system/app/PrimaryApp.apk out/target/product/smdkv210/system/bin/bash out/target/product/smdkv210/system/bin/busybox out/target/product/smdkv210/system/bin/dvb_load out/target/product/smdkv210/system/bin/dvb_server out/target/product/smdkv210/system/bin/playts out/target/product/smdkv210/system/bin/remount_ctl out/target/product/smdkv210/system/bin/tvrecv out/target/product/smdkv210/system/build.prop out/target/product/smdkv210/system/etc/apns-conf.xml out/target/product/smdkv210/system/etc/dhd.ko out/target/product/smdkv210/system/etc/event-log-tags out/target/product/smdkv210/system/etc/fw_bcm4329.bin out/target/product/smdkv210/system/etc/media_profiles.xml out/target/product/smdkv210/system/etc/nvram.txt out/target/product/smdkv210/system/etc/permissions/android.software.live_wallpaper.xml out/target/product/smdkv210/system/etc/permissions/handheld_core_hardware.xml out/target/product/smdkv210/system/etc/secomxregistry out/target/product/smdkv210/system/etc/security/otacerts.zip out/target/product/smdkv210/system/etc/vold.conf out/target/product/smdkv210/system/etc/vold.fstab out/target/product/smdkv210/system/etc/wifi/wpa_supplicant.conf out/target/product/smdkv210/system/lib/egl/egl.cfg out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Encoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M2V.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Encoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.WMV.Decoder.so out/target/product/smdkv210/system/lib/libSEC_OMX_Core.so out/target/product/smdkv210/system/lib/libSEC_Resourcemanager.so out/target/product/smdkv210/system/lib/liba52.so out/target/product/smdkv210/system/lib/libdvbbinderclient.so out/target/product/smdkv210/system/lib/libdvbcomm.so out/target/product/smdkv210/system/lib/libjnidvb.so out/target/product/smdkv210/system/lib/libmoddvb.so out/target/product/smdkv210/system/lib/libstagefright.so out/target/product/smdkv210/system/lib/libstagefright_amrnb_common.so out/target/product/smdkv210/system/lib/libstagefright_avc_common.so out/target/product/smdkv210/system/lib/libstagefright_color_conversion.so out/target/product/smdkv210/system/lib/libstagefright_enc_common.so out/target/product/smdkv210/system/lib/libstagefright_foundation.so out/target/product/smdkv210/system/lib/libstagefright_omx.so out/target/product/smdkv210/system/lib/libtv.so out/target/product/smdkv210/system/lib/modules/utiusb.ko out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_01.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_02.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_03.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Buzzer.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Classic.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Rooster_02.ogg out/target/product/smdkv210/system/media/audio/notifications/Beat_Box_Android.ogg out/target/product/smdkv210/system/media/audio/notifications/CaffeineSnake.ogg out/target/product/smdkv210/system/media/audio/notifications/DearDeer.ogg out/target/product/smdkv210/system/media/audio/notifications/DontPanic.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_MissedCall.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_New_MMS.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_New_SMS.ogg out/target/product/smdkv210/system/media/audio/notifications/Heaven.ogg out/target/product/smdkv210/system/media/audio/notifications/Highwire.ogg out/target/product/smdkv210/system/media/audio/notifications/KzurbSonar.ogg out/target/product/smdkv210/system/media/audio/notifications/OnTheHunt.ogg out/target/product/smdkv210/system/media/audio/notifications/TaDa.ogg out/target/product/smdkv210/system/media/audio/notifications/Tinkerbell.ogg out/target/product/smdkv210/system/media/audio/notifications/Voila.ogg out/target/product/smdkv210/system/media/audio/ringtones/BeatPlucker.ogg out/target/product/smdkv210/system/media/audio/ringtones/BentleyDubs.ogg out/target/product/smdkv210/system/media/audio/ringtones/BirdLoop.ogg out/target/product/smdkv210/system/media/audio/ringtones/CaribbeanIce.ogg out/target/product/smdkv210/system/media/audio/ringtones/CrazyDream.ogg out/target/product/smdkv210/system/media/audio/ringtones/CurveBall.ogg out/target/product/smdkv210/system/media/audio/ringtones/DreamTheme.ogg out/target/product/smdkv210/system/media/audio/ringtones/EtherShake.ogg out/target/product/smdkv210/system/media/audio/ringtones/FriendlyGhost.ogg out/target/product/smdkv210/system/media/audio/ringtones/GameOverGuitar.ogg out/target/product/smdkv210/system/media/audio/ringtones/Growl.ogg out/target/product/smdkv210/system/media/audio/ringtones/InsertCoin.ogg out/target/product/smdkv210/system/media/audio/ringtones/LoopyLounge.ogg out/target/product/smdkv210/system/media/audio/ringtones/LoveFlute.ogg out/target/product/smdkv210/system/media/audio/ringtones/MidEvilJaunt.ogg out/target/product/smdkv210/system/media/audio/ringtones/MildlyAlarming.ogg out/target/product/smdkv210/system/media/audio/ringtones/NewPlayer.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises1.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises2.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises3.ogg out/target/product/smdkv210/system/media/audio/ringtones/OrganDub.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Classic_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Digital_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_04.ogg out/target/product/smdkv210/system/media/audio/ringtones/RomancingTheTone.ogg out/target/product/smdkv210/system/media/audio/ringtones/SitarVsSitar.ogg out/target/product/smdkv210/system/media/audio/ringtones/SpringyJalopy.ogg out/target/product/smdkv210/system/media/audio/ringtones/Terminated.ogg out/target/product/smdkv210/system/media/audio/ringtones/TwirlAway.ogg out/target/product/smdkv210/system/media/audio/ringtones/VeryAlarmed.ogg out/target/product/smdkv210/system/media/audio/ringtones/World.ogg out/target/product/smdkv210/system/media/audio/ui/Effect_Tick.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressDelete.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressReturn.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressSpacebar.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressStandard.ogg out/target/product/smdkv210/system/media/audio/ui/VideoRecord.ogg out/target/product/smdkv210/system/media/audio/ui/camera_click.ogg out/target/product/smdkv210/system/media/bootanimation.zip out/target/product/smdkv210/system/tts/lang_pico/de-DE_gl0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/de-DE_ta.bin out/target/product/smdkv210/system/tts/lang_pico/en-GB_kh0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/en-GB_ta.bin out/target/product/smdkv210/system/tts/lang_pico/en-US_lh0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/en-US_ta.bin out/target/product/smdkv210/system/tts/lang_pico/es-ES_ta.bin out/target/product/smdkv210/system/tts/lang_pico/es-ES_zl0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/fr-FR_nk0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/fr-FR_ta.bin out/target/product/smdkv210/system/tts/lang_pico/it-IT_cm0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/it-IT_ta.bin out/target/product/smdkv210/system/usr/keylayout/s3c-keypad.kl out/target/product/smdkv210/system/vendor/bin/pvrsrvinit out/target/product/smdkv210/system/vendor/lib/egl/libEGL_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/egl/libGLESv1_CM_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/egl/libGLESv2_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/hw/gralloc.s5pc110.so out/target/product/smdkv210/system/vendor/lib/libIMGegl.so out/target/product/smdkv210/system/vendor/lib/libPVRScopeServices.so out/target/product/smdkv210/system/vendor/lib/libglslcompiler.so out/target/product/smdkv210/system/vendor/lib/libpvr2d.so out/target/product/smdkv210/system/vendor/lib/libpvrANDROID_WSEGL.so out/target/product/smdkv210/system/vendor/lib/libsrv_init.so out/target/product/smdkv210/system/vendor/lib/libsrv_um.so out/target/product/smdkv210/system/vendor/lib/libusc.so out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/tvrecv
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostPlatForm.o: external/tvplay/tvrecv/HostPlatForm.c external/tvplay/tvrecv/HostPlatForm.c system/core/include/arch/linux-arm/AndroidConfig.h external/tvplay/tvrecv/HostIncludeApi.h bionic/libc/include/fcntl.h bionic/libc/include/sys/cdefs.h bionic/libc/include/sys/cdefs_elf.h bionic/libc/include/sys/types.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stddef.h bionic/libc/include/stdint.h bionic/libc/include/sys/_types.h bionic/libc/arch-arm/include/machine/_types.h bionic/libc/kernel/common/linux/posix_types.h bionic/libc/kernel/common/linux/stddef.h bionic/libc/kernel/common/linux/compiler.h bionic/libc/kernel/arch-arm/asm/posix_types.h bionic/libc/kernel/arch-arm/asm/types.h bionic/libc/kernel/common/linux/types.h bionic/libc/arch-arm/include/machine/kernel.h bionic/libc/include/sys/sysmacros.h bionic/libc/kernel/common/linux/fcntl.h bionic/libc/kernel/arch-arm/asm/fcntl.h bionic/libc/kernel/common/asm-generic/fcntl.h bionic/libc/include/unistd.h bionic/libc/include/sys/select.h bionic/libc/include/sys/time.h bionic/libc/kernel/common/linux/time.h bionic/libc/include/signal.h bionic/libc/include/limits.h bionic/libc/include/sys/limits.h bionic/libc/kernel/common/linux/limits.h bionic/libc/arch-arm/include/machine/internal_types.h bionic/libc/arch-arm/include/machine/limits.h bionic/libc/include/sys/syslimits.h bionic/libc/include/string.h bionic/libc/include/malloc.h bionic/libc/kernel/arch-arm/asm/signal.h bionic/libc/kernel/common/asm-generic/signal.h bionic/libc/kernel/arch-arm/asm/siginfo.h bionic/libc/kernel/common/asm-generic/siginfo.h bionic/libc/include/sys/sysconf.h bionic/libc/kernel/common/linux/capability.h bionic/libc/include/pathconf.h bionic/libc/include/stdlib.h bionic/libc/include/alloca.h bionic/libc/include/strings.h bionic/libc/include/memory.h bionic/libc/include/stdio.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stdarg.h bionic/libc/include/pthread.h bionic/libc/include/time.h bionic/libc/include/sched.h bionic/libc/include/sys/ioctl.h bionic/libc/kernel/common/linux/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctl.h bionic/libc/kernel/common/asm-generic/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctls.h bionic/libc/kernel/arch-arm/asm/termbits.h bionic/libc/include/sys/ioctl_compat.h external/tvplay/tvrecv/HostUTIAPI.h external/tvplay/tvrecv/HostDeviceAPI.h external/tvplay/tvrecv/HostPlatForm.h external/tvplay/tvrecv/debug.h external/tvplay/tvrecv/HostCAClientAPI.h external/tvplay/tvrecv/HostAppInfoClientAPI.h external/tvplay/tvrecv/HostEPGDBClientAPI.h external/tvplay/tvrecv/HostTunerClientAPI.h external/tvplay/tvrecv/HostRgnClientAPI.h external/tvplay/tvrecv/HostSCClientAPI.h external/tvplay/tvrecv/HostDateTimeServerAPI.h | all_copied_headers
#  Implicit rule search has not been done.
#  Implicit/static pattern stem: `HostPlatForm'
#  Last modified 2012-02-27 22:04:05.614410106
#  File has been updated.
#  Successfully updated.
# makefile (from `build/core/binary.mk', line 261)
# PRIVATE_ARM_MODE := thumb
# makefile (from `build/core/binary.mk', line 262)
# PRIVATE_ARM_CFLAGS := -mthumb -Os -fomit-frame-pointer -fno-strict-aliasing -finline-limit=64
# variable set hash-table stats:
# Load=2/32=6%, Rehash=0, Collisions=0/4=0%
#  commands to execute (from `build/core/binary.mk', line 268):
	$(transform-$(PRIVATE_HOST)c-to-o)
	

# Not a target:
.c.o:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(COMPILE.c) $(OUTPUT_OPTION) $<

# Not a target:
external/astl/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:38
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
Makefile:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:02:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/icu4c/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:57
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
CtsGestureTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

external/tvplay/tvrecv/HostDeviceAPI.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/apps/Camera/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:29
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/apps/Contacts/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:46
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/media/audio/ringtones/RomancingTheTone.ogg: frameworks/base/data/sounds/newwavelabs/RomancingTheTone.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
.r.o:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(COMPILE.r) $(OUTPUT_OPTION) $<

# Not a target:
out/host/linux-x86/bin/fs_config:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
packages/apps/Bluetooth/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:37
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
.r:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(LINK.r) $^ $(LOADLIBES) $(LDLIBS) -o $@

# Not a target:
build/core/build_id.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/wallpapers/Basic/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:58
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/Alarm_Rooster_02.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
CtsTargetInstrumentationApp:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/etc/permissions/handheld_core_hardware.xml: frameworks/base/data/etc/handheld_core_hardware.xml | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
android.core.tests.luni.util:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
vendor/samsung/smdkv210/proprietary/pvrsrvinit:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

target-files-package: out/target/product/smdkv210/obj/PACKAGING/target_files_intermediates/full_smdkv210-target_files-eng.lnt.zip
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/libpcap/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:46
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
cts/CtsHostLibraryList.mk:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:41
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/etc/nvram.txt: LMC1800/sources/broadcom/nvram.txt | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/system/media/audio/notifications/DontPanic.ogg: frameworks/base/data/sounds/newwavelabs/DontPanic.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
out/target/common/obj/JAVA_LIBRARIES/android.policy_intermediates/javalib.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/notifications/TaDa.ogg: frameworks/base/data/sounds/notifications/TaDa.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
CtsPerformance2TestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/sonivox/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:53:20
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

modules:
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.
#  commands to execute (from `build/core/main.mk', line 784):
	@echo "Available sub-modules:"
	@echo "$(call module-names-for-tag-list,$(ALL_MODULE_TAGS))" | \
	tr -s ' ' '\n' | sort -u | $(COLUMN)
	

# Not a target:
external/emma/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:49
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/bsdiff/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:12
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/newwavelabs/InsertCoin.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/libffi/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:52
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/media/audio/ringtones/MidEvilJaunt.ogg: frameworks/base/data/sounds/newwavelabs/MidEvilJaunt.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/obj/NOTICE_FILES/src/kernel.txt: prebuilt/android-arm/kernel/LINUX_KERNEL_COPYING | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 527):
	@echo Copying: $@
	$(hide) mkdir -p $(dir $@)
	$(hide) $(ACP) $< $@
	

out/target/product/smdkv210/ramdisk.img: out/host/linux-x86/bin/mkbootfs out/target/product/smdkv210/root/init.rc out/target/product/smdkv210/root/init.smdkv210.rc out/target/product/smdkv210/root/ueventd.smdkv210.rc out/target/product/smdkv210/root/default.prop | out/host/linux-x86/bin/minigzip
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 317):
	$(call pretty,"Target ram disk: $@")
	$(hide) $(MKBOOTFS) $(TARGET_ROOT_OUT) | $(MINIGZIP) > $@
	

external/tvplay/tvrecv/main.c:
#  Implicit rule search has been done.
#  Last modified 2012-03-01 19:20:56.2198857
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/strace/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:01
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/newwavelabs/DontPanic.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
hardware/qcom/gps/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:45
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
device/htc/passion/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:59
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/gtest/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

external/tvplay/tvrecv/HostUTIAPI.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
system/media/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:53:29
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/v8/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:53:07
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/include/limits.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/svox/pico/lang/PicoLangEnGBInSystem.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:34
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

NOTICE-TARGET-EXECUTABLES-tvrecv:
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/newwavelabs/Terminated.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDeviceAPI.o: external/tvplay/tvrecv/HostDeviceAPI.c external/tvplay/tvrecv/HostDeviceAPI.c system/core/include/arch/linux-arm/AndroidConfig.h external/tvplay/tvrecv/HostIncludeApi.h bionic/libc/include/fcntl.h bionic/libc/include/sys/cdefs.h bionic/libc/include/sys/cdefs_elf.h bionic/libc/include/sys/types.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stddef.h bionic/libc/include/stdint.h bionic/libc/include/sys/_types.h bionic/libc/arch-arm/include/machine/_types.h bionic/libc/kernel/common/linux/posix_types.h bionic/libc/kernel/common/linux/stddef.h bionic/libc/kernel/common/linux/compiler.h bionic/libc/kernel/arch-arm/asm/posix_types.h bionic/libc/kernel/arch-arm/asm/types.h bionic/libc/kernel/common/linux/types.h bionic/libc/arch-arm/include/machine/kernel.h bionic/libc/include/sys/sysmacros.h bionic/libc/kernel/common/linux/fcntl.h bionic/libc/kernel/arch-arm/asm/fcntl.h bionic/libc/kernel/common/asm-generic/fcntl.h bionic/libc/include/unistd.h bionic/libc/include/sys/select.h bionic/libc/include/sys/time.h bionic/libc/kernel/common/linux/time.h bionic/libc/include/signal.h bionic/libc/include/limits.h bionic/libc/include/sys/limits.h bionic/libc/kernel/common/linux/limits.h bionic/libc/arch-arm/include/machine/internal_types.h bionic/libc/arch-arm/include/machine/limits.h bionic/libc/include/sys/syslimits.h bionic/libc/include/string.h bionic/libc/include/malloc.h bionic/libc/kernel/arch-arm/asm/signal.h bionic/libc/kernel/common/asm-generic/signal.h bionic/libc/kernel/arch-arm/asm/siginfo.h bionic/libc/kernel/common/asm-generic/siginfo.h bionic/libc/include/sys/sysconf.h bionic/libc/kernel/common/linux/capability.h bionic/libc/include/pathconf.h bionic/libc/include/stdlib.h bionic/libc/include/alloca.h bionic/libc/include/strings.h bionic/libc/include/memory.h bionic/libc/include/stdio.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stdarg.h bionic/libc/include/pthread.h bionic/libc/include/time.h bionic/libc/include/sched.h bionic/libc/include/sys/ioctl.h bionic/libc/kernel/common/linux/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctl.h bionic/libc/kernel/common/asm-generic/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctls.h bionic/libc/kernel/arch-arm/asm/termbits.h bionic/libc/include/sys/ioctl_compat.h external/tvplay/tvrecv/HostUTIAPI.h external/tvplay/tvrecv/HostDeviceAPI.h external/tvplay/tvrecv/HostPlatForm.h external/tvplay/tvrecv/debug.h external/tvplay/tvrecv/HostCAClientAPI.h external/tvplay/tvrecv/HostAppInfoClientAPI.h external/tvplay/tvrecv/HostEPGDBClientAPI.h external/tvplay/tvrecv/HostTunerClientAPI.h external/tvplay/tvrecv/HostRgnClientAPI.h external/tvplay/tvrecv/HostSCClientAPI.h external/tvplay/tvrecv/HostDateTimeServerAPI.h | all_copied_headers
#  Implicit rule search has not been done.
#  Implicit/static pattern stem: `HostDeviceAPI'
#  Last modified 2012-02-27 22:04:05.390410113
#  File has been updated.
#  Successfully updated.
# makefile (from `build/core/binary.mk', line 261)
# PRIVATE_ARM_MODE := thumb
# makefile (from `build/core/binary.mk', line 262)
# PRIVATE_ARM_CFLAGS := -mthumb -Os -fomit-frame-pointer -fno-strict-aliasing -finline-limit=64
# variable set hash-table stats:
# Load=2/32=6%, Rehash=0, Collisions=0/4=0%
#  commands to execute (from `build/core/binary.mk', line 268):
	$(transform-$(PRIVATE_HOST)c-to-o)
	

# Not a target:
build/core/cleanbuild.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/host/linux-x86/cts/all_cts_files_stamp: android.core.tests.dom android.core.tests.luni.io android.core.tests.luni.lang android.core.tests.luni.net android.core.tests.luni.util android.core.tests.xml android.core.tests.runner TestDeviceSetup CtsDelegatingAccessibilityService SignatureTest ApiDemos ApiDemosReferenceTest CtsVerifier CtsAccessibilityServiceTestCases CtsAccountManagerTestCases CtsAppTestCases CtsBluetoothTestCases CtsContentTestCases CtsDatabaseTestCases CtsDpiTestCases CtsDpiTestCases2 CtsExampleTestCases CtsGestureTestCases CtsGraphicsTestCases CtsHardwareTestCases CtsJniTestCases CtsLocationTestCases CtsMediaTestCases CtsOsTestCases CtsPermissionTestCases CtsPermission2TestCases CtsPreferenceTestCases CtsProviderTestCases CtsSaxTestCases CtsSpeechTestCases CtsTelephonyTestCases CtsTestStubs CtsTextTestCases CtsUtilTestCases CtsViewTestCases CtsWebkitTestCases CtsWidgetTestCases CtsNetTestCases CtsPerformanceTestCases CtsPerformance2TestCases CtsPerformance3TestCases CtsPerformance4TestCases CtsPerformance5TestCases CtsAppAccessData CtsAppWithData CtsInstrumentationAppDiffCert CtsPermissionDeclareApp CtsSharedUidInstall CtsSharedUidInstallDiffCert CtsSimpleAppInstall CtsSimpleAppInstallDiffCert CtsTargetInstrumentationApp CtsUsePermissionDiffCert out/host/linux-x86/framework/junit.jar out/host/linux-x86/framework/hosttestlib.jar out/host/linux-x86/framework/CtsTestAnnotationsHostLib.jar out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/tasks/cts.mk', line 46)
# PRIVATE_JUNIT_HOST_JAR := out/host/linux-x86/framework/junit.jar
# variable set hash-table stats:
# Load=1/32=3%, Rehash=0, Collisions=0/2=0%
#  commands to execute (from `build/core/tasks/cts.mk', line 51):
	@rm -rf $(PRIVATE_CTS_DIR)
	@mkdir -p $(TMP_DIR)
	@mkdir -p $(PRIVATE_DIR)/docs
	@mkdir -p $(PRIVATE_DIR)/tools
	@mkdir -p $(PRIVATE_DIR)/repository/testcases
	@mkdir -p $(PRIVATE_DIR)/repository/plans
	$(hide) $(ACP) -fp $(CTS_HOST_JAR) $(CTS_EXECUTABLE_PATH) $(DDMLIB_JAR) $(PRIVATE_JUNIT_HOST_JAR) $(HOSTTESTLIB_JAR) $(CTS_HOST_LIBRARY_JARS) $(PRIVATE_DIR)/tools
	$(hide) chmod ug+rwX $(PRIVATE_DIR)/tools/$(notdir $(CTS_EXECUTABLE_PATH))
	$(foreach apk,$(CTS_CASE_LIST), \
	$(call copy-testcase-apk,$(apk)))
	$(hide) $(ACP) -fp $(cts_tools_src_dir)/utils/host_config.xml $(PRIVATE_DIR)/repository/
	$(hide) touch $@
	

# Not a target:
LMC1800/sources/dvb_load:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
bootable/bootloader/legacy/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:07
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/dex_bootjars/system/framework/core-junit.odex: out/target/common/obj/JAVA_LIBRARIES/core-junit_intermediates/javalib.jar out/target/product/smdkv210/dex_bootjars/system/framework/services.odex | out/host/linux-x86/bin/acp dalvik/tools/dex-preopt out/host/linux-x86/bin/dexopt
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/dex_preopt.mk', line 66)
# PRIVATE_DBJ_JAR := out/target/product/smdkv210/dex_bootjars/system/framework/core-junit.jar
# variable set hash-table stats:
# Load=1/32=3%, Rehash=0, Collisions=0/1=0%
#  commands to execute (from `build/core/dex_preopt.mk', line 66):
	@echo "Dexpreopt Boot Jar: $@"
	@ rm -f $@
	@ mkdir -p $(dir $@)
	@ out/host/linux-x86/bin/acp -fpt $< $(PRIVATE_DBJ_JAR)
	$(call dexpreopt-one-file,$(PRIVATE_DBJ_JAR),$@)
	

# Not a target:
frameworks/base/data/sounds/Alarm_Beep_03.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

update-api: out/target/common/obj/PACKAGING/public_api.xml | out/host/linux-x86/bin/acp
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.
#  commands to execute (from `build/core/tasks/apicheck.mk', line 78):
	@echo Copying current.xml
	$(hide) $(ACP) $(INTERNAL_PLATFORM_API_FILE) $(SRC_API_DIR)/current.xml
	

# Not a target:
out/target/common/obj/JAVA_LIBRARIES/core-junitrunner_intermediates/javalib.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
build/tools/buildinfo.sh:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/host/linux-x86/framework/ddmlib-prebuilt.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/jsr305/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:08
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/tcpdump/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:00
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/host/linux-x86/sdk/android-sdk_eng.lnt_linux-x86.zip: out/target/product/smdkv210/obj/NOTICE.txt out/host/linux-x86/obj/NOTICE.txt out/target/common/docs/offline-sdk-timestamp out/target/product/smdkv210/full_smdkv210-symbols-eng.lnt.zip out/target/product/smdkv210/system.img out/target/product/smdkv210/userdata.img out/target/product/smdkv210/ramdisk.img out/target/product/smdkv210/sdk/sdk-build.prop out/target/product/smdkv210/system/build.prop out/target/product/smdkv210/system/bin/tvrecv out/target/product/smdkv210/root/init.rc out/target/product/smdkv210/system/etc/vold.fstab out/target/product/smdkv210/system/lib/egl/egl.cfg out/target/product/smdkv210/root/init.smdkv210.rc out/target/product/smdkv210/root/ueventd.smdkv210.rc out/target/product/smdkv210/system/usr/keylayout/s3c-keypad.kl out/target/product/smdkv210/system/etc/media_profiles.xml out/target/product/smdkv210/system/etc/secomxregistry out/target/product/smdkv210/system/lib/libSEC_OMX_Core.so out/target/product/smdkv210/system/lib/libSEC_Resourcemanager.so out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M2V.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.WMV.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Encoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Encoder.so out/target/product/smdkv210/system/lib/libstagefright_amrnb_common.so out/target/product/smdkv210/system/lib/libstagefright_enc_common.so out/target/product/smdkv210/system/lib/libstagefright_avc_common.so out/target/product/smdkv210/system/lib/libstagefright_foundation.so out/target/product/smdkv210/system/lib/libstagefright_color_conversion.so out/target/product/smdkv210/system/lib/libstagefright.so out/target/product/smdkv210/system/lib/libstagefright_omx.so out/target/product/smdkv210/system/lib/liba52.so out/target/product/smdkv210/system/bin/playts out/target/product/smdkv210/system/lib/libtv.so out/target/product/smdkv210/system/bin/remount_ctl out/target/product/smdkv210/system/media/bootanimation.zip out/target/product/smdkv210/system/app/PrimaryApp.apk out/target/product/smdkv210/system/app/HomePage.apk out/target/product/smdkv210/system/app/FileManagers.apk out/target/product/smdkv210/system/app/Game.apk out/target/product/smdkv210/system/lib/libmoddvb.so out/target/product/smdkv210/system/lib/libjnidvb.so out/target/product/smdkv210/system/lib/libdvbbinderclient.so out/target/product/smdkv210/system/lib/libdvbcomm.so out/target/product/smdkv210/system/lib/modules/utiusb.ko out/target/product/smdkv210/system/bin/dvb_load out/target/product/smdkv210/system/bin/busybox out/target/product/smdkv210/system/bin/bash out/target/product/smdkv210/system/bin/dvb_server out/target/product/smdkv210/system/etc/wifi/wpa_supplicant.conf out/target/product/smdkv210/system/etc/dhd.ko out/target/product/smdkv210/system/etc/fw_bcm4329.bin out/target/product/smdkv210/system/etc/nvram.txt out/target/product/smdkv210/system/etc/permissions/handheld_core_hardware.xml out/target/product/smdkv210/system/etc/permissions/android.software.live_wallpaper.xml out/target/product/smdkv210/kernel out/target/product/smdkv210/system/etc/permissions/android.software.live_wallpaper.xml out/target/product/smdkv210/system/vendor/bin/pvrsrvinit out/target/product/smdkv210/system/vendor/lib/egl/libEGL_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/egl/libGLESv1_CM_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/egl/libGLESv2_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/libglslcompiler.so out/target/product/smdkv210/system/vendor/lib/libIMGegl.so out/target/product/smdkv210/system/vendor/lib/libpvr2d.so out/target/product/smdkv210/system/vendor/lib/libpvrANDROID_WSEGL.so out/target/product/smdkv210/system/vendor/lib/libPVRScopeServices.so out/target/product/smdkv210/system/vendor/lib/libsrv_init.so out/target/product/smdkv210/system/vendor/lib/libsrv_um.so out/target/product/smdkv210/system/vendor/lib/libusc.so out/target/product/smdkv210/system/vendor/lib/hw/gralloc.s5pc110.so out/target/product/smdkv210/system/etc/apns-conf.xml out/target/product/smdkv210/system/etc/vold.conf out/target/product/smdkv210/system/media/audio/notifications/F1_MissedCall.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_New_MMS.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_New_SMS.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Buzzer.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_01.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_02.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Classic.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_03.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Rooster_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Classic_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Digital_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_04.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/BeatPlucker.ogg out/target/product/smdkv210/system/media/audio/ringtones/BentleyDubs.ogg out/target/product/smdkv210/system/media/audio/ringtones/BirdLoop.ogg out/target/product/smdkv210/system/media/audio/ringtones/CaribbeanIce.ogg out/target/product/smdkv210/system/media/audio/ringtones/CurveBall.ogg out/target/product/smdkv210/system/media/audio/ringtones/EtherShake.ogg out/target/product/smdkv210/system/media/audio/ringtones/FriendlyGhost.ogg out/target/product/smdkv210/system/media/audio/ringtones/GameOverGuitar.ogg out/target/product/smdkv210/system/media/audio/ringtones/Growl.ogg out/target/product/smdkv210/system/media/audio/ringtones/InsertCoin.ogg out/target/product/smdkv210/system/media/audio/ringtones/LoopyLounge.ogg out/target/product/smdkv210/system/media/audio/ringtones/LoveFlute.ogg out/target/product/smdkv210/system/media/audio/ringtones/MidEvilJaunt.ogg out/target/product/smdkv210/system/media/audio/ringtones/MildlyAlarming.ogg out/target/product/smdkv210/system/media/audio/ringtones/NewPlayer.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises1.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises2.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises3.ogg out/target/product/smdkv210/system/media/audio/ringtones/OrganDub.ogg out/target/product/smdkv210/system/media/audio/ringtones/RomancingTheTone.ogg out/target/product/smdkv210/system/media/audio/ringtones/SitarVsSitar.ogg out/target/product/smdkv210/system/media/audio/ringtones/SpringyJalopy.ogg out/target/product/smdkv210/system/media/audio/ringtones/Terminated.ogg out/target/product/smdkv210/system/media/audio/ringtones/TwirlAway.ogg out/target/product/smdkv210/system/media/audio/ringtones/VeryAlarmed.ogg out/target/product/smdkv210/system/media/audio/ringtones/World.ogg out/target/product/smdkv210/system/media/audio/notifications/CaffeineSnake.ogg out/target/product/smdkv210/system/media/audio/notifications/DearDeer.ogg out/target/product/smdkv210/system/media/audio/notifications/DontPanic.ogg out/target/product/smdkv210/system/media/audio/notifications/Highwire.ogg out/target/product/smdkv210/system/media/audio/notifications/KzurbSonar.ogg out/target/product/smdkv210/system/media/audio/notifications/OnTheHunt.ogg out/target/product/smdkv210/system/media/audio/notifications/Voila.ogg out/target/product/smdkv210/system/media/audio/notifications/Beat_Box_Android.ogg out/target/product/smdkv210/system/media/audio/notifications/Heaven.ogg out/target/product/smdkv210/system/media/audio/notifications/TaDa.ogg out/target/product/smdkv210/system/media/audio/notifications/Tinkerbell.ogg out/target/product/smdkv210/system/media/audio/ui/Effect_Tick.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressStandard.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressSpacebar.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressDelete.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressReturn.ogg out/target/product/smdkv210/system/media/audio/ui/VideoRecord.ogg out/target/product/smdkv210/system/media/audio/ui/camera_click.ogg out/target/product/smdkv210/system/media/audio/ringtones/CrazyDream.ogg out/target/product/smdkv210/system/media/audio/ringtones/DreamTheme.ogg out/target/product/smdkv210/system/tts/lang_pico/de-DE_gl0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/de-DE_ta.bin out/target/product/smdkv210/system/tts/lang_pico/en-GB_kh0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/en-GB_ta.bin out/target/product/smdkv210/system/tts/lang_pico/en-US_lh0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/en-US_ta.bin out/target/product/smdkv210/system/tts/lang_pico/es-ES_zl0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/es-ES_ta.bin out/target/product/smdkv210/system/tts/lang_pico/fr-FR_nk0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/fr-FR_ta.bin out/target/product/smdkv210/system/tts/lang_pico/it-IT_cm0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/it-IT_ta.bin out/target/product/smdkv210/root/default.prop out/target/product/smdkv210/system/build.prop out/target/product/smdkv210/system/etc/event-log-tags out/target/product/smdkv210/system/etc/security/otacerts.zip out/target/product/smdkv210/ramdisk.img out/target/common/docs/index.html development/build/sdk.atree sdk/build/tools.atree out/host/linux-x86/bin/atree out/host/linux-x86/bin/line_endings
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/Makefile', line 1298)
# PRIVATE_DIR := out/host/linux-x86/sdk/android-sdk_eng.lnt_linux-x86
# makefile (from `build/core/Makefile', line 1300)
# PRIVATE_INPUT_FILES := development/build/sdk.exclude.atree development/build/sdk.atree development/build/sdk-linux-x86.atree sdk/build/tools.atree
# makefile (from `build/core/Makefile', line 1299)
# PRIVATE_DEP_FILE := out/host/linux-x86/sdk/sdk_deps.mk
# makefile (from `build/core/Makefile', line 1297)
# PRIVATE_NAME := android-sdk_eng.lnt_linux-x86
# variable set hash-table stats:
# Load=4/32=12%, Rehash=0, Collisions=0/9=0%
#  commands to execute (from `build/core/Makefile', line 1307):
	@echo "Package SDK: $@"
	$(hide) rm -rf $(PRIVATE_DIR) $@
	$(hide) for f in $(target_gnu_MODULES); do \
	if [ -f $$f ]; then \
	echo SDK: $(if $(SDK_GNU_ERROR),ERROR:,warning:) \
	including GNU target $$f >&2; \
	FAIL=$(SDK_GNU_ERROR); \
	fi; \
	done; \
	if [ $$FAIL ]; then exit 1; fi
	$(hide) ( \
	$(HOST_OUT_EXECUTABLES)/atree \
	$(addprefix -f ,$(PRIVATE_INPUT_FILES)) \
	-m $(PRIVATE_DEP_FILE) \
	-I . \
	-I $(PRODUCT_OUT) \
	-I $(HOST_OUT) \
	-I $(TARGET_COMMON_OUT_ROOT) \
	-v "PLATFORM_NAME=android-$(PLATFORM_VERSION)" \
	-o $(PRIVATE_DIR) && \
	cp -f $(target_notice_file_txt) \
	$(PRIVATE_DIR)/platforms/android-$(PLATFORM_VERSION)/images/NOTICE.txt && \
	cp -f $(tools_notice_file_txt) $(PRIVATE_DIR)/tools/NOTICE.txt && \
	HOST_OUT_EXECUTABLES=$(HOST_OUT_EXECUTABLES) HOST_OS=$(HOST_OS) \
	development/build/tools/sdk_clean.sh $(PRIVATE_DIR) && \
	chmod -R ug+rwX $(PRIVATE_DIR) && \
	cd $(dir $@) && zip -rq $(notdir $@) $(PRIVATE_NAME) \
	) || ( rm -rf $(PRIVATE_DIR) $@ && exit 44 )
	

# Not a target:
ApiDemosReferenceTest:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
packages/inputmethods/OpenWnn/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:58
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/newwavelabs/BentleyDubs.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/bzip2/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:50
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostAppInfoClientAPI.P:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  Last modified 2012-02-27 22:04:05.086410122
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/apps/Calculator/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:29
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

dataclean:
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.
# makefile (from `build/core/cleanbuild.mk', line 202)
# FILES := ./out/target/product/smdkv210/data/* ./out/target/product/smdkv210/data-qemu/* ./out/target/product/smdkv210/userdata-qemu.img
# variable set hash-table stats:
# Load=1/32=3%, Rehash=0, Collisions=0/2=0%
#  commands to execute (from `build/core/cleanbuild.mk', line 204):
	$(hide) rm -rf $(FILES)
	@echo "Deleted emulator userdata images."
	

out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostTunerClientAPI.o: external/tvplay/tvrecv/HostTunerClientAPI.c external/tvplay/tvrecv/HostTunerClientAPI.c system/core/include/arch/linux-arm/AndroidConfig.h external/tvplay/tvrecv/HostIncludeApi.h bionic/libc/include/fcntl.h bionic/libc/include/sys/cdefs.h bionic/libc/include/sys/cdefs_elf.h bionic/libc/include/sys/types.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stddef.h bionic/libc/include/stdint.h bionic/libc/include/sys/_types.h bionic/libc/arch-arm/include/machine/_types.h bionic/libc/kernel/common/linux/posix_types.h bionic/libc/kernel/common/linux/stddef.h bionic/libc/kernel/common/linux/compiler.h bionic/libc/kernel/arch-arm/asm/posix_types.h bionic/libc/kernel/arch-arm/asm/types.h bionic/libc/kernel/common/linux/types.h bionic/libc/arch-arm/include/machine/kernel.h bionic/libc/include/sys/sysmacros.h bionic/libc/kernel/common/linux/fcntl.h bionic/libc/kernel/arch-arm/asm/fcntl.h bionic/libc/kernel/common/asm-generic/fcntl.h bionic/libc/include/unistd.h bionic/libc/include/sys/select.h bionic/libc/include/sys/time.h bionic/libc/kernel/common/linux/time.h bionic/libc/include/signal.h bionic/libc/include/limits.h bionic/libc/include/sys/limits.h bionic/libc/kernel/common/linux/limits.h bionic/libc/arch-arm/include/machine/internal_types.h bionic/libc/arch-arm/include/machine/limits.h bionic/libc/include/sys/syslimits.h bionic/libc/include/string.h bionic/libc/include/malloc.h bionic/libc/kernel/arch-arm/asm/signal.h bionic/libc/kernel/common/asm-generic/signal.h bionic/libc/kernel/arch-arm/asm/siginfo.h bionic/libc/kernel/common/asm-generic/siginfo.h bionic/libc/include/sys/sysconf.h bionic/libc/kernel/common/linux/capability.h bionic/libc/include/pathconf.h bionic/libc/include/stdlib.h bionic/libc/include/alloca.h bionic/libc/include/strings.h bionic/libc/include/memory.h bionic/libc/include/stdio.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stdarg.h bionic/libc/include/pthread.h bionic/libc/include/time.h bionic/libc/include/sched.h bionic/libc/include/sys/ioctl.h bionic/libc/kernel/common/linux/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctl.h bionic/libc/kernel/common/asm-generic/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctls.h bionic/libc/kernel/arch-arm/asm/termbits.h bionic/libc/include/sys/ioctl_compat.h external/tvplay/tvrecv/HostUTIAPI.h external/tvplay/tvrecv/HostDeviceAPI.h external/tvplay/tvrecv/HostPlatForm.h external/tvplay/tvrecv/debug.h external/tvplay/tvrecv/HostCAClientAPI.h external/tvplay/tvrecv/HostAppInfoClientAPI.h external/tvplay/tvrecv/HostEPGDBClientAPI.h external/tvplay/tvrecv/HostTunerClientAPI.h external/tvplay/tvrecv/HostRgnClientAPI.h external/tvplay/tvrecv/HostSCClientAPI.h external/tvplay/tvrecv/HostDateTimeServerAPI.h | all_copied_headers
#  Implicit rule search has not been done.
#  Implicit/static pattern stem: `HostTunerClientAPI'
#  Last modified 2012-02-27 22:04:05.8504101
#  File has been updated.
#  Successfully updated.
# makefile (from `build/core/binary.mk', line 261)
# PRIVATE_ARM_MODE := thumb
# makefile (from `build/core/binary.mk', line 262)
# PRIVATE_ARM_CFLAGS := -mthumb -Os -fomit-frame-pointer -fno-strict-aliasing -finline-limit=64
# variable set hash-table stats:
# Load=2/32=6%, Rehash=0, Collisions=0/4=0%
#  commands to execute (from `build/core/binary.mk', line 268):
	$(transform-$(PRIVATE_HOST)c-to-o)
	

out/target/common/obj/PACKAGING/checkapi-current-timestamp: frameworks/base/api/current.xml out/target/common/obj/PACKAGING/public_api.xml out/host/linux-x86/bin/apicheck
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/tasks/apicheck.mk', line 65):
	@echo "Checking API:"  checkapi-current
	@ ( out/host/linux-x86/bin/apicheck  -error 2 -error 3 -error 4 -error 5 -error 6 -error 7 -error 8 -error 9 -error 10 -error 11 -error 12 -error 13 -error 14 -error 15 -error 16 -error 17 -error 18 -error 19 -error 20 -error 21 -error 23 -error 24 -error 25   frameworks/base/api/current.xml  out/target/common/obj/PACKAGING/public_api.xml || (  cat build/core/apicheck_msg_current.txt  ; exit 38 ) )
	@ mkdir -p $(dir $@)
	@ touch $@
	

# Not a target:
out/target/product/smdkv210/obj/lib/libc.so:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 11:46:59.530961008
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
build/core/combo/TARGET_linux-arm.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

tvrecv: out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/tvrecv out/target/product/smdkv210/system/bin/tvrecv
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/69=0%

bionic/libc/arch-arm/include/machine/internal_types.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/media/audio/ui/KeypressReturn.ogg: frameworks/base/data/sounds/effects/KeypressReturn.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/system/bin/tvrecv: out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/tvrecv | out/host/linux-x86/bin/acp out/target/product/smdkv210/system/lib/libc.so out/target/product/smdkv210/system/lib/libstdc++.so out/target/product/smdkv210/system/lib/libm.so
#  Implicit rule search has not been done.
#  Implicit/static pattern stem: `'
#  Last modified 2012-03-01 19:20:58
#  File has been updated.
#  Successfully updated.
# automatic
# @ := out/target/product/smdkv210/system/bin/tvrecv
# automatic
# % := 
# automatic
# * := 
# automatic
# + := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/tvrecv
# automatic
# | := out/host/linux-x86/bin/acp out/target/product/smdkv210/system/lib/libc.so out/target/product/smdkv210/system/lib/libstdc++.so out/target/product/smdkv210/system/lib/libm.so
# automatic
# < := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/tvrecv
# automatic
# ^ := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/tvrecv
# automatic
# ? := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/tvrecv
# variable set hash-table stats:
# Load=8/32=25%, Rehash=0, Collisions=2/21=10%
#  commands to execute (from `build/core/base_rules.mk', line 475):
	@echo "Install: $@"
	$(copy-file-to-target)
	

# Not a target:
out/target/product/smdkv210/obj/EXECUTABLES/updater_intermediates/updater:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
vendor/samsung/smdkv210/proprietary/libpvrANDROID_WSEGL.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
CtsTestStubs:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
vendor/samsung/smdkv210/proprietary/libGLESv1_CM_POWERVR_SGX540_120.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
build/core/node_fns.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/bluetooth/bluez/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:50:43
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostTunerClientAPI.P:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  Last modified 2012-02-27 22:04:05.8664101
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
LMC1800/sources/media/omx/libOMX.SEC.WMV.Decoder.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
packages/apps/Provision/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:28
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

showcommands:
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.
#  commands to execute (from `build/core/main.mk', line 790):
	@echo >/dev/null
	

# Not a target:
.l.c:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	@$(RM) $@ 
	$(LEX.l) $< > $@

out/target/product/smdkv210/system/lib/libmoddvb.so: LMC1800/sources/dvb/libmoddvb.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
external/tremolo/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:49
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/etc/secomxregistry: LMC1800/sources/media/omx/secomxregistry | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

clobber: clean
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

# Not a target:
out/target/common/docs/offline-sdk-timestamp:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/target/common/obj/JAVA_LIBRARIES/framework_intermediates/javalib.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/host/linux-x86/bin/acp:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 11:35:08.958973303
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/effects/Effect_Tick.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
build/core/definitions.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/dex_bootjars/system/framework/core.odex: out/target/common/obj/JAVA_LIBRARIES/core_intermediates/javalib.jar | out/host/linux-x86/bin/acp dalvik/tools/dex-preopt out/host/linux-x86/bin/dexopt
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/dex_preopt.mk', line 66)
# PRIVATE_DBJ_JAR := out/target/product/smdkv210/dex_bootjars/system/framework/core.jar
# variable set hash-table stats:
# Load=1/32=3%, Rehash=0, Collisions=0/1=0%
#  commands to execute (from `build/core/dex_preopt.mk', line 66):
	@echo "Dexpreopt Boot Jar: $@"
	@ rm -f $@
	@ mkdir -p $(dir $@)
	@ out/host/linux-x86/bin/acp -fpt $< $(PRIVATE_DBJ_JAR)
	$(call dexpreopt-one-file,$(PRIVATE_DBJ_JAR),$@)
	

# Not a target:
device/samsung/smdkv210/kernel:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

external/tvplay/tvrecv/HostSCClientAPI.c:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/include/sys/cdefs_elf.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

external/tvplay/tvrecv/SocketHeader.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-27 18:52:34
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/kernel/arch-arm/asm/socket.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/vendor/lib/egl/libGLESv2_POWERVR_SGX540_120.so: vendor/samsung/smdkv210/proprietary/libGLESv2_POWERVR_SGX540_120.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
CtsJniTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
CtsAppAccessData:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/kernel/common/asm-generic/ioctl.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/media/audio/ringtones/SitarVsSitar.ogg: frameworks/base/data/sounds/newwavelabs/SitarVsSitar.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

bionic/libc/include/sys/_types.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/apps/Music/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:22
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/media/audio/ui/Effect_Tick.ogg: frameworks/base/data/sounds/effects/Effect_Tick.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
device/samsung/smdkv210/egl.cfg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

installclean: dataclean
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.
# makefile (from `build/core/cleanbuild.mk', line 208)
# FILES := ./out/host/linux-x86/obj/NOTICE_FILES ./out/host/linux-x86/sdk ./out/target/product/smdkv210/*.img ./out/target/product/smdkv210/*.txt ./out/target/product/smdkv210/*.xlb ./out/target/product/smdkv210/*.zip ./out/target/product/smdkv210/data ./out/target/product/smdkv210/obj/APPS ./out/target/product/smdkv210/obj/NOTICE_FILES ./out/target/product/smdkv210/obj/PACKAGING ./out/target/product/smdkv210/recovery ./out/target/product/smdkv210/root ./out/target/product/smdkv210/system ./out/target/product/smdkv210/dex_bootjars ./out/target/product/smdkv210/obj/JAVA_LIBRARIES
# variable set hash-table stats:
# Load=1/32=3%, Rehash=0, Collisions=0/2=0%
#  commands to execute (from `build/core/cleanbuild.mk', line 210):
	$(hide) rm -rf $(FILES)
	@echo "Deleted images and staging directories."
	

# Not a target:
packages/apps/HTMLViewer/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:26
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/kernel/arch-arm/asm/signal.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/etc/vold.fstab: device/samsung/smdkv210/vold.fstab | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/system/media/audio/ringtones/InsertCoin.ogg: frameworks/base/data/sounds/newwavelabs/InsertCoin.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/system/media/audio/notifications/Voila.ogg: frameworks/base/data/sounds/newwavelabs/Voila.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
build/target/product/sim.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/host/linux-x86/bin/dexopt:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/obj/NOTICE.html: out/target/product/smdkv210/obj/NOTICE_FILES/hash-timestamp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/Makefile', line 491)
# PRIVATE_DIR := out/target/product/smdkv210/obj/NOTICE_FILES
# makefile (from `build/core/Makefile', line 491)
# PRIVATE_MESSAGE := "Notices for files contained in the filesystem images in this directory:"
# variable set hash-table stats:
# Load=2/32=6%, Rehash=0, Collisions=0/2=0%
#  commands to execute (from `build/core/Makefile', line 491):
	@echo Combining NOTICE files: $@
	$(hide) mkdir -p $(dir $@)
	$(hide) echo "<html><head>" > $@
	$(hide) echo "<style type=\"text/css\">" >> $@
	$(hide) echo "body { padding: 0; font-family: sans-serif; }" >> $@
	$(hide) echo ".same-license { background-color: #eeeeee; border-top: 20px solid white; padding: 10px; }" >> $@
	$(hide) echo ".label { font-weight: bold; }" >> $@
	$(hide) echo ".file-list { margin-left: 1em; font-color: blue; }" >> $@
	$(hide) echo "</style>" >> $@
	$(hide) echo "</head><body topmargin=\"0\" leftmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\">" >> $@
	$(hide) echo "<table cellpading=\"0\" cellspacing=\"0\" border=\"0\">" >> $@
	$(hide) for hashfile in $$(find $(PRIVATE_DIR)/hash -type f); do cat $$hashfile | sort | sed -e "s:$(PRIVATE_DIR)/src\(.*\)\.txt:  <a name=\"\1\"></a>:" >> $@; echo "<tr><td class=\"same-license\">" >> $@; echo "<div class=\"label\">Notices for file(s):</div>" >> $@; echo "<div class=\"file-list\">" >> $@; cat $$hashfile | sort | sed -e "s:$(PRIVATE_DIR)/src\(.*\)\.txt:  \1<br/>:" >> $@; echo "</div><!-- file-list -->" >> $@; echo >> $@; orig=$$(head -n 1 $$hashfile); echo "<pre class=\"license-text\">" >> $@; cat $$orig | sed -e "s/\&/\&amp;/g" | sed -e "s/</\&lt;/g" | sed -e "s/>/\&gt;/g" >> $@; echo "</pre><!-- license-text -->" >> $@; echo "</td></tr><!-- same-license -->" >> $@; echo >> $@; echo >> $@; echo >> $@; done
	$(hide) echo "</table>" >> $@
	$(hide) echo "</body></html>" >> $@
	

event-log-tags: out/target/product/smdkv210/system/etc/event-log-tags
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

# Not a target:
out/target/common/obj/PACKAGING/public_api.xml:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/newwavelabs/DreamTheme.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/svox/pico/lang/it-IT_ta.bin:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
build/core/tasks/product-graph.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/dnsmasq/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:14
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/kernel/arch-arm/asm/types.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/easymock/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:46
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
.C:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(LINK.C) $^ $(LOADLIBES) $(LDLIBS) -o $@

bionic/libc/include/time.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/apps/Stk/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:36
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/apps/PackageInstaller/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:38
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/ping/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:12
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/kernel/common/linux/sockios.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
build/core/main.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/kernel-headers/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:06
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

otatools: out/host/linux-x86/bin/minigzip out/host/linux-x86/bin/mkbootfs out/host/linux-x86/bin/mkbootimg out/host/linux-x86/bin/fs_config out/host/linux-x86/bin/mkyaffs2image out/host/linux-x86/bin/zipalign out/host/linux-x86/bin/aapt out/host/linux-x86/bin/bsdiff out/host/linux-x86/bin/imgdiff out/host/linux-x86/framework/dumpkey.jar out/host/linux-x86/framework/signapk.jar
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

out/target/product/smdkv210/root/init.smdkv210.rc: device/samsung/smdkv210/init.smdkv210.rc | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
.r.f:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(PREPROCESS.r) $(OUTPUT_OPTION) $<

out/target/product/smdkv210/full_smdkv210-apps-eng.lnt.zip: out/target/product/smdkv210/system.img
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 1178):
	@echo "Package apps: $@"
	$(hide) rm -rf $@
	$(hide) mkdir -p $(dir $@)
	$(hide) zip -qj $@ $(TARGET_OUT_APPS)/*
	

# Not a target:
frameworks/base/data/sounds/newwavelabs/GameOverGuitar.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/include/alloca.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/debug.o: external/tvplay/tvrecv/debug.c external/tvplay/tvrecv/debug.c system/core/include/arch/linux-arm/AndroidConfig.h external/tvplay/tvrecv/HostIncludeApi.h bionic/libc/include/fcntl.h bionic/libc/include/sys/cdefs.h bionic/libc/include/sys/cdefs_elf.h bionic/libc/include/sys/types.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stddef.h bionic/libc/include/stdint.h bionic/libc/include/sys/_types.h bionic/libc/arch-arm/include/machine/_types.h bionic/libc/kernel/common/linux/posix_types.h bionic/libc/kernel/common/linux/stddef.h bionic/libc/kernel/common/linux/compiler.h bionic/libc/kernel/arch-arm/asm/posix_types.h bionic/libc/kernel/arch-arm/asm/types.h bionic/libc/kernel/common/linux/types.h bionic/libc/arch-arm/include/machine/kernel.h bionic/libc/include/sys/sysmacros.h bionic/libc/kernel/common/linux/fcntl.h bionic/libc/kernel/arch-arm/asm/fcntl.h bionic/libc/kernel/common/asm-generic/fcntl.h bionic/libc/include/unistd.h bionic/libc/include/sys/select.h bionic/libc/include/sys/time.h bionic/libc/kernel/common/linux/time.h bionic/libc/include/signal.h bionic/libc/include/limits.h bionic/libc/include/sys/limits.h bionic/libc/kernel/common/linux/limits.h bionic/libc/arch-arm/include/machine/internal_types.h bionic/libc/arch-arm/include/machine/limits.h bionic/libc/include/sys/syslimits.h bionic/libc/include/string.h bionic/libc/include/malloc.h bionic/libc/kernel/arch-arm/asm/signal.h bionic/libc/kernel/common/asm-generic/signal.h bionic/libc/kernel/arch-arm/asm/siginfo.h bionic/libc/kernel/common/asm-generic/siginfo.h bionic/libc/include/sys/sysconf.h bionic/libc/kernel/common/linux/capability.h bionic/libc/include/pathconf.h bionic/libc/include/stdlib.h bionic/libc/include/alloca.h bionic/libc/include/strings.h bionic/libc/include/memory.h bionic/libc/include/stdio.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stdarg.h bionic/libc/include/pthread.h bionic/libc/include/time.h bionic/libc/include/sched.h bionic/libc/include/sys/ioctl.h bionic/libc/kernel/common/linux/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctl.h bionic/libc/kernel/common/asm-generic/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctls.h bionic/libc/kernel/arch-arm/asm/termbits.h bionic/libc/include/sys/ioctl_compat.h external/tvplay/tvrecv/HostUTIAPI.h external/tvplay/tvrecv/HostDeviceAPI.h external/tvplay/tvrecv/HostPlatForm.h external/tvplay/tvrecv/debug.h external/tvplay/tvrecv/HostCAClientAPI.h external/tvplay/tvrecv/HostAppInfoClientAPI.h external/tvplay/tvrecv/HostEPGDBClientAPI.h external/tvplay/tvrecv/HostTunerClientAPI.h external/tvplay/tvrecv/HostRgnClientAPI.h external/tvplay/tvrecv/HostSCClientAPI.h external/tvplay/tvrecv/HostDateTimeServerAPI.h | all_copied_headers
#  Implicit rule search has not been done.
#  Implicit/static pattern stem: `debug'
#  Last modified 2012-02-27 22:04:05.046410123
#  File has been updated.
#  Successfully updated.
# makefile (from `build/core/binary.mk', line 261)
# PRIVATE_ARM_MODE := thumb
# makefile (from `build/core/binary.mk', line 262)
# PRIVATE_ARM_CFLAGS := -mthumb -Os -fomit-frame-pointer -fno-strict-aliasing -finline-limit=64
# variable set hash-table stats:
# Load=2/32=6%, Rehash=0, Collisions=0/4=0%
#  commands to execute (from `build/core/binary.mk', line 268):
	$(transform-$(PRIVATE_HOST)c-to-o)
	

systemtarball-nodeps: out/host/linux-x86/bin/fs_get_stats all_modules
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 784):
	$(build-systemtarball-target)
	

# Not a target:
.S:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(LINK.S) $^ $(LOADLIBES) $(LDLIBS) -o $@

out/target/product/smdkv210/system/tts/lang_pico/fr-FR_ta.bin: external/svox/pico/lang/fr-FR_ta.bin | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

external/tvplay/tvrecv/HostCAClientAPI.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/svox/pico/lang/en-US_ta.bin:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/bluetooth/glib/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:50:47
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/bin/playts: LMC1800/sources/media/sf/playts | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

bionic/libc/include/sys/ioctl_compat.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_03.ogg: frameworks/base/data/sounds/Alarm_Beep_03.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

external/tvplay/tvrecv/HostDeviceAPI.c:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
device/samsung/smdkv210/init.rc:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/ringtones/Noises3.ogg: frameworks/base/data/sounds/newwavelabs/Noises3.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
.texinfo.info:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(MAKEINFO) $(MAKEINFO_FLAGS) $< -o $@

# Not a target:
out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/debug.P:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  Last modified 2012-02-27 22:04:05.050410123
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/apps/CertInstaller/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:41
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
.c:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(LINK.c) $^ $(LOADLIBES) $(LDLIBS) -o $@

bionic/libc/include/strings.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/tts/lang_pico/en-US_lh0_sg.bin: external/svox/pico/lang/en-US_lh0_sg.bin | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/system/etc/fw_bcm4329.bin: LMC1800/sources/broadcom/fw_bcm4329.bin | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/system/vendor/lib/libsrv_um.so: vendor/samsung/smdkv210/proprietary/libsrv_um.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
.w.tex:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(CWEAVE) $< - $@

# Not a target:
device/htc/passion/passion_us.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:59
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/newwavelabs/EtherShake.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/host/linux-x86/framework/junit.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
build/target/product/security/testkey.x509.pem:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/etc/media_profiles.xml: device/samsung/smdkv210/media_profiles.xml | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
.c.ln:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(LINT.c) -C$* $<

# Not a target:
.s.o:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(COMPILE.s) -o $@ $<

# Not a target:
frameworks/base/data/sounds/newwavelabs/BeatPlucker.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

external/tvplay/tvrecv/HostEPGDBClientAPI.c:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
CtsDpiTestCases2:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
.s:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(LINK.s) $^ $(LOADLIBES) $(LDLIBS) -o $@

out/target/product/smdkv210/system/media/audio/ringtones/Noises2.ogg: frameworks/base/data/sounds/newwavelabs/Noises2.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
build/core/clear_vars.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
hardware/qcom/media/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:44
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
hardware/broadcom/wlan/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:45
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/svox/pico/lang/PicoLangEsEsInSystem.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:34
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/app/HomePage.apk: LMC1800/sources/ui/HomePage.apk | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

bootimage: out/target/product/smdkv210/ramdisk.img
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

# Not a target:
LMC1800/sources/dvb/libmoddvb.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_02.ogg: frameworks/base/data/sounds/Alarm_Beep_02.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
CtsTelephonyTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

userdatatarball: out/target/product/smdkv210/userdata.tar.bz2
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

out/target/product/smdkv210/system/tts/lang_pico/de-DE_gl0_sg.bin: external/svox/pico/lang/de-DE_gl0_sg.bin | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
CtsSharedUidInstallDiffCert:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
build/core/copy_headers.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/tvrecv: out/target/product/smdkv210/symbols/system/bin/tvrecv | out/host/linux-x86/bin/soslim
#  Implicit rule search has not been done.
#  Implicit/static pattern stem: `'
#  Last modified 2012-03-01 19:20:58.559885583
#  File has been updated.
#  Successfully updated.
# automatic
# @ := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/tvrecv
# makefile (from `build/core/binary.mk', line 496)
# PRIVATE_ALL_OBJECTS :=         out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/debug.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostAppInfoClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostCAClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDateTimeServerAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDeviceAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostEPGDBClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostPlatForm.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostRgnClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostSCClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostTunerClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostXDCServerAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/main.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketRecv.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketSend.o    
# makefile (from `build/core/binary.mk', line 489)
# PRIVATE_LDLIBS := external/tvplay/tvrecv/libutihost.android.a 
# makefile (from `build/core/binary.mk', line 493)
# PRIVATE_ALL_SHARED_LIBRARIES := out/target/product/smdkv210/obj/lib/libc.so out/target/product/smdkv210/obj/lib/libstdc++.so out/target/product/smdkv210/obj/lib/libm.so
# makefile (from `build/core/binary.mk', line 66)
# PRIVATE_TARGET_C_INCLUDES := bionic/libc/arch-arm/include bionic/libc/include bionic/libstdc++/include bionic/libc/kernel/common bionic/libc/kernel/arch-arm bionic/libm/include bionic/libm/include/arch/arm bionic/libthread_db/include
# automatic
# % := 
# makefile (from `build/core/binary.mk', line 84)
# PRIVATE_CXX := prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/arm-eabi-g++
# makefile (from `build/core/binary.mk', line 483)
# PRIVATE_ASFLAGS := 
# makefile (from `build/core/binary.mk', line 79)
# PRIVATE_CC := prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/arm-eabi-gcc
# makefile (from `build/core/base_rules.mk', line 449)
# PRIVATE_INTERMEDIATES_DIR := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates
# makefile (from `build/core/binary.mk', line 495)
# PRIVATE_ALL_WHOLE_STATIC_LIBRARIES := 
# makefile (from `build/core/base_rules.mk', line 438)
# PRIVATE_PATH := external/tvplay/tvrecv
# makefile (from `build/core/binary.mk', line 73)
# PRIVATE_NO_DEFAULT_COMPILER_FLAGS := 
# automatic
# * := 
# automatic
# + := out/target/product/smdkv210/symbols/system/bin/tvrecv
# makefile (from `build/core/binary.mk', line 484)
# PRIVATE_CFLAGS := $(subst ,, )
# makefile (from `build/core/binary.mk', line 485)
# PRIVATE_CPPFLAGS := $(subst ,, )
# makefile (from `build/core/binary.mk', line 486)
# PRIVATE_DEBUG_CFLAGS := 
# automatic
# ^ := out/target/product/smdkv210/symbols/system/bin/tvrecv
# makefile (from `build/core/binary.mk', line 488)
# PRIVATE_LDFLAGS :=  external/tvplay/tvrecv/libutihost.android.a    -Wl,--no-undefined
# makefile (from `build/core/base_rules.mk', line 440)
# PRIVATE_AAPT_FLAGS := 
# makefile (from `build/core/base_rules.mk', line 446)
# PRIVATE_IS_HOST_MODULE := 
# makefile (from `build/core/binary.mk', line 67)
# PRIVATE_TARGET_GLOBAL_CFLAGS := -fno-exceptions -Wno-multichar -msoft-float -fpic -ffunction-sections -funwind-tables -fstack-protector -Wa,--noexecstack -Werror=format-security -fno-short-enums -march=armv7-a -mfloat-abi=softfp -mfpu=neon -include system/core/include/arch/linux-arm/AndroidConfig.h -I system/core/include/arch/linux-arm/ -Wno-psabi -mthumb-interwork -DANDROID -fmessage-length=0 -W -Wall -Wno-unused -Winit-self -Wpointer-arith -Werror=return-type -Werror=non-virtual-dtor -Werror=address -Werror=sequence-point -DNDEBUG -g -Wstrict-aliasing=2 -finline-functions -fno-inline-functions-called-once -fgcse-after-reload -frerun-cse-after-loop -frename-registers -DNDEBUG -UDEBUG
# makefile (from `build/core/base_rules.mk', line 443)
# PRIVATE_MANIFEST_INSTRUMENTATION_FOR := 
# makefile (from `build/core/binary.mk', line 91)
# PRIVATE_CPP_EXTENSION := .cpp
# makefile (from `build/core/base_rules.mk', line 445)
# PRIVATE_ALL_JAVA_LIBRARIES := 
# makefile (from `build/core/binary.mk', line 494)
# PRIVATE_ALL_STATIC_LIBRARIES := 
# makefile (from `build/core/base_rules.mk', line 441)
# PRIVATE_JAVA_LIBRARIES := 
# makefile (from `build/core/base_rules.mk', line 442)
# PRIVATE_MANIFEST_PACKAGE_NAME := 
# makefile (from `build/core/binary.mk', line 487)
# PRIVATE_C_INCLUDES :=  external/tvplay/tvrecv out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates  dalvik/libnativehelper/include/nativehelper
# makefile (from `build/core/base_rules.mk', line 439)
# PRIVATE_POST_PROCESS_COMMAND := true
# makefile (from `build/core/binary.mk', line 482)
# PRIVATE_YACCFLAGS := 
# makefile (from `build/core/binary.mk', line 68)
# PRIVATE_TARGET_GLOBAL_CPPFLAGS := -fvisibility-inlines-hidden -DANDROID -fmessage-length=0 -W -Wall -Wno-unused -Winit-self -Wpointer-arith -Wsign-promo -Werror=return-type -Werror=non-virtual-dtor -Werror=address -Werror=sequence-point -DNDEBUG -UDEBUG
# makefile (from `build/core/base_rules.mk', line 447)
# PRIVATE_HOST := 
# automatic
# | := out/host/linux-x86/bin/soslim
# makefile (from `build/core/binary.mk', line 65)
# PRIVATE_TARGET_PROJECT_INCLUDES := system/core/include hardware/libhardware/include hardware/libhardware_legacy/include hardware/ril/include dalvik/libnativehelper/include frameworks/base/include frameworks/base/opengl/include frameworks/base/native/include external/skia/include out/target/product/smdkv210/obj/include
# makefile (from `build/core/base_rules.mk', line 452)
# PRIVATE_MODULE := tvrecv
# automatic
# < := out/target/product/smdkv210/symbols/system/bin/tvrecv
# automatic
# ? := out/target/product/smdkv210/symbols/system/bin/tvrecv
# variable set hash-table stats:
# Load=39/64=61%, Rehash=1, Collisions=237/169=140%
#  commands to execute (from `build/core/dynamic_binary.mk', line 141):
	$(transform-to-stripped)
	

out/host/linux-x86/cts/android-cts/repository/testcases/CtsAppSecurityTests.jar: out/host/linux-x86/framework/CtsAppSecurityTests.jar out/host/linux-x86/cts/all_cts_files_stamp out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/tasks/cts.mk', line 150):
	$(ACP) -fv $(HOST_OUT_JAVA_LIBRARIES)/CtsAppSecurityTests.jar $(APP_SECURITY_LIB)
	

# Not a target:
external/sqlite/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:13
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/oprofile/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:00
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/include/signal.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/dex_bootjars/system/framework/android.policy_nodex.jar: out/target/common/obj/JAVA_LIBRARIES/android.policy_intermediates/javalib.jar | out/host/linux-x86/bin/acp out/host/linux-x86/bin/aapt
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/dex_preopt.mk', line 66):
	$(call copy-file-to-target)
	$(call dexpreopt-remove-classes.dex,$@)
	

# Not a target:
hardware/ril/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:46
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
build/core/binary.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
LMC1800/sources/broadcom/nvram.txt:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
device/samsung/smdkv210/s3c-keypad.kl:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/bootanimation.zip: LMC1800/sources/ui/bootanimation.zip | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
android.core.tests.luni.lang:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/lib/libstagefright_amrnb_common.so: LMC1800/sources/media/sf/libstagefright_amrnb_common.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
out/host/linux-x86/bin/line_endings:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
build/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
system/core/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:53:35
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/symbols/system/bin/tvrecv: out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/LINKED/tvrecv | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Implicit/static pattern stem: `'
#  Last modified 2012-03-01 19:20:58
#  File has been updated.
#  Successfully updated.
# automatic
# @ := out/target/product/smdkv210/symbols/system/bin/tvrecv
# automatic
# % := 
# automatic
# * := 
# automatic
# + := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/LINKED/tvrecv
# automatic
# | := out/host/linux-x86/bin/acp
# automatic
# < := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/LINKED/tvrecv
# automatic
# ^ := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/LINKED/tvrecv
# automatic
# ? := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/LINKED/tvrecv
# variable set hash-table stats:
# Load=8/32=25%, Rehash=0, Collisions=2/64=3%
#  commands to execute (from `build/core/dynamic_binary.mk', line 118):
	@echo "target Non-prelinked: $(PRIVATE_MODULE) ($@)"
	$(copy-file-to-target)
	

out/target/product/smdkv210/system/bin/bash: LMC1800/sources/bash | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
packages/apps/QuickSearchBox/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:42
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/target/common/obj/JAVA_LIBRARIES/core-junit_intermediates/javalib.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/chromium/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:49:41
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
CtsDatabaseTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
frameworks/base/data/etc/handheld_core_hardware.xml:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
android.core.tests.luni.net:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/F1_MissedCall.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/tts/lang_pico/en-GB_kh0_sg.bin: external/svox/pico/lang/en-GB_kh0_sg.bin | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
build/core/tasks/cts.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

findbugs: out/target/product/smdkv210/findbugs.html out/target/product/smdkv210/findbugs.xml
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/newwavelabs/LoopyLounge.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

external/tvplay/tvrecv/HostEPGDBClientAPI.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
build/core/product.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/opt/emoji/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:42:15
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
build/target/product/AndroidProducts.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
LMC1800/sources/media/sf/libstagefright_color_conversion.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/svox/pico/lang/it-IT_cm0_sg.bin:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/kernel/common/linux/fcntl.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
CtsPerformance4TestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
build/core/tasks/ide.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/newwavelabs/MildlyAlarming.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/newwavelabs/FriendlyGhost.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/target/common/obj/JAVA_LIBRARIES/core-tests-xml_intermediates/javalib.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
vendor/samsung/smdkv210/proprietary/libpvr2d.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/svox/pico/lang/en-US_lh0_sg.bin:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
buildspec.mk:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  File does not exist.
#  File has been updated.
#  Failed to be updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

external/tvplay/tvrecv/HostSCClientAPI.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostAppInfoClientAPI.o: external/tvplay/tvrecv/HostAppInfoClientAPI.c external/tvplay/tvrecv/HostAppInfoClientAPI.c system/core/include/arch/linux-arm/AndroidConfig.h external/tvplay/tvrecv/HostIncludeApi.h bionic/libc/include/fcntl.h bionic/libc/include/sys/cdefs.h bionic/libc/include/sys/cdefs_elf.h bionic/libc/include/sys/types.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stddef.h bionic/libc/include/stdint.h bionic/libc/include/sys/_types.h bionic/libc/arch-arm/include/machine/_types.h bionic/libc/kernel/common/linux/posix_types.h bionic/libc/kernel/common/linux/stddef.h bionic/libc/kernel/common/linux/compiler.h bionic/libc/kernel/arch-arm/asm/posix_types.h bionic/libc/kernel/arch-arm/asm/types.h bionic/libc/kernel/common/linux/types.h bionic/libc/arch-arm/include/machine/kernel.h bionic/libc/include/sys/sysmacros.h bionic/libc/kernel/common/linux/fcntl.h bionic/libc/kernel/arch-arm/asm/fcntl.h bionic/libc/kernel/common/asm-generic/fcntl.h bionic/libc/include/unistd.h bionic/libc/include/sys/select.h bionic/libc/include/sys/time.h bionic/libc/kernel/common/linux/time.h bionic/libc/include/signal.h bionic/libc/include/limits.h bionic/libc/include/sys/limits.h bionic/libc/kernel/common/linux/limits.h bionic/libc/arch-arm/include/machine/internal_types.h bionic/libc/arch-arm/include/machine/limits.h bionic/libc/include/sys/syslimits.h bionic/libc/include/string.h bionic/libc/include/malloc.h bionic/libc/kernel/arch-arm/asm/signal.h bionic/libc/kernel/common/asm-generic/signal.h bionic/libc/kernel/arch-arm/asm/siginfo.h bionic/libc/kernel/common/asm-generic/siginfo.h bionic/libc/include/sys/sysconf.h bionic/libc/kernel/common/linux/capability.h bionic/libc/include/pathconf.h bionic/libc/include/stdlib.h bionic/libc/include/alloca.h bionic/libc/include/strings.h bionic/libc/include/memory.h bionic/libc/include/stdio.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stdarg.h bionic/libc/include/pthread.h bionic/libc/include/time.h bionic/libc/include/sched.h bionic/libc/include/sys/ioctl.h bionic/libc/kernel/common/linux/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctl.h bionic/libc/kernel/common/asm-generic/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctls.h bionic/libc/kernel/arch-arm/asm/termbits.h bionic/libc/include/sys/ioctl_compat.h external/tvplay/tvrecv/HostUTIAPI.h external/tvplay/tvrecv/HostDeviceAPI.h external/tvplay/tvrecv/HostPlatForm.h external/tvplay/tvrecv/debug.h external/tvplay/tvrecv/HostCAClientAPI.h external/tvplay/tvrecv/HostAppInfoClientAPI.h external/tvplay/tvrecv/HostEPGDBClientAPI.h external/tvplay/tvrecv/HostTunerClientAPI.h external/tvplay/tvrecv/HostRgnClientAPI.h external/tvplay/tvrecv/HostSCClientAPI.h external/tvplay/tvrecv/HostDateTimeServerAPI.h | all_copied_headers
#  Implicit rule search has not been done.
#  Implicit/static pattern stem: `HostAppInfoClientAPI'
#  Last modified 2012-02-27 22:04:05.082410122
#  File has been updated.
#  Successfully updated.
# makefile (from `build/core/binary.mk', line 261)
# PRIVATE_ARM_MODE := thumb
# makefile (from `build/core/binary.mk', line 262)
# PRIVATE_ARM_CFLAGS := -mthumb -Os -fomit-frame-pointer -fno-strict-aliasing -finline-limit=64
# variable set hash-table stats:
# Load=2/32=6%, Rehash=0, Collisions=0/4=0%
#  commands to execute (from `build/core/binary.mk', line 268):
	$(transform-$(PRIVATE_HOST)c-to-o)
	

# Not a target:
.texinfo.dvi:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(TEXI2DVI) $(TEXI2DVI_FLAGS) $<

# Not a target:
build/target/product/generic_x86.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
build/core/base_rules.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
bootable/recovery/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:08
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
LMC1800/sources/broadcom/dhd.ko:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/bin/dvb_load: LMC1800/sources/dvb_load | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
packages/apps/Gallery/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:31
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

external/tvplay/tvrecv/HostPlatForm.c:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/lib/libOMX.SEC.M2V.Decoder.so: LMC1800/sources/media/omx/libOMX.SEC.M2V.Decoder.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
LMC1800/sources/media/omx/libOMX.SEC.M4V.Decoder.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/target/product/smdkv210/obj/lib/libstdc++.so:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 11:47:12.67096078
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

external/tvplay/tvrecv/debug.c:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/lib/libstagefright_foundation.so: LMC1800/sources/media/sf/libstagefright_foundation.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/system/media/audio/ringtones/World.ogg: frameworks/base/data/sounds/newwavelabs/World.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
LMC1800/sources/bash:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostPlatForm.P:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  Last modified 2012-02-27 22:04:05.622410106
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/newwavelabs/Voila.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/root/default.prop:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 44):
	@echo Target buildinfo: $@
	@mkdir -p $(dir $@)
	$(hide) echo "#" > $@; \
	echo "# ADDITIONAL_DEFAULT_PROPERTIES" >> $@; \
	echo "#" >> $@;
	$(hide) $(foreach line,$(ADDITIONAL_DEFAULT_PROPERTIES), \
	echo "$(line)" >> $@;)
	

# Not a target:
LMC1800/sources/media/sf/playts:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/include/sys/endian.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
CtsVerifier:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/svox/pico/lang/de-DE_gl0_sg.bin:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
.y.c:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(YACC.y) $< 
	mv -f y.tab.c $@

out/target/product/smdkv210/system/lib/libOMX.SEC.WMV.Decoder.so: LMC1800/sources/media/omx/libOMX.SEC.WMV.Decoder.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostRgnClientAPI.P:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  Last modified 2012-02-27 22:04:05.738410103
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/kernel/common/asm-generic/signal.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/kernel/arch-arm/asm/siginfo.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/lib/modules/utiusb.ko: LMC1800/sources/utiusb.ko | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
external/dhcpcd/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:49
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/host/linux-x86/bin/aapt:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/dex_bootjars/system/framework/android.policy.odex: out/target/common/obj/JAVA_LIBRARIES/android.policy_intermediates/javalib.jar out/target/product/smdkv210/dex_bootjars/system/framework/framework.odex | out/host/linux-x86/bin/acp dalvik/tools/dex-preopt out/host/linux-x86/bin/dexopt
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/dex_preopt.mk', line 66)
# PRIVATE_DBJ_JAR := out/target/product/smdkv210/dex_bootjars/system/framework/android.policy.jar
# variable set hash-table stats:
# Load=1/32=3%, Rehash=0, Collisions=0/1=0%
#  commands to execute (from `build/core/dex_preopt.mk', line 66):
	@echo "Dexpreopt Boot Jar: $@"
	@ rm -f $@
	@ mkdir -p $(dir $@)
	@ out/host/linux-x86/bin/acp -fpt $< $(PRIVATE_DBJ_JAR)
	$(call dexpreopt-one-file,$(PRIVATE_DBJ_JAR),$@)
	

out/products.dot:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/tasks/product-graph.mk', line 21):
	@echo Product graph DOT: $@
	$(hide) ( \
	echo 'digraph {'; \
	echo 'graph [ ratio=.5 ];'; \
	$(foreach p,$(ALL_PRODUCTS), \
	$(foreach d,$(PRODUCTS.$(strip $(p)).INHERITS_FROM), \
	echo \"$(d)\" -\> \"$(p)\";)) \
	$(foreach prod, \
	$(sort $(foreach p,$(ALL_PRODUCTS), \
	$(foreach d,$(PRODUCTS.$(strip $(p)).INHERITS_FROM), \
	$(d))) \
	$(foreach p,$(ALL_PRODUCTS),$(p))), \
	echo \"$(prod)\" [ label=\"$(dir $(prod))\\n$(notdir $(prod))\"];) \
	echo '}' \
	) > $@
	

# Not a target:
.web.tex:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(WEAVE) $<

# Not a target:
frameworks/base/data/sounds/newwavelabs/OnTheHunt.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
device/sample/products/sample_addon.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:54
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
.texi.info:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(MAKEINFO) $(MAKEINFO_FLAGS) $< -o $@

out/target/product/smdkv210/system/media/audio/notifications/DearDeer.ogg: frameworks/base/data/sounds/newwavelabs/DearDeer.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
device/htc/passion/passion.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:59
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/providers/DrmProvider/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:02:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

product-graph: out/products.pdf
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
development/data/etc/vold.conf:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_04.ogg: frameworks/base/data/sounds/Ring_Synth_04.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/system/lib/egl/egl.cfg: device/samsung/smdkv210/egl.cfg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
external/junit/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:12
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/root/ueventd.smdkv210.rc: device/samsung/smdkv210/ueventd.smdkv210.rc | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
frameworks/base/api/current.xml:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
LMC1800/sources/ui/Game.apk:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
CtsExampleTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
CtsSimpleAppInstallDiffCert:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
.DEFAULT:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
packages/providers/TelephonyProvider/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:02:01
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
LMC1800/sources/dvb/libdvbcomm.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/apache-http/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:09
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/bin/remount_ctl: LMC1800/sources/remount_ctl | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
external/proguard/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:02
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/obj/PACKAGING/tests_zip_intermediates/full_smdkv210-tests-eng.lnt.zip: out/target/product/smdkv210/obj/PACKAGING/systemimage_intermediates/system.img out/target/product/smdkv210/userdata.img | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/Makefile', line 1109)
# intermediates := out/target/product/smdkv210/obj/PACKAGING/tests_zip_intermediates
# makefile (from `build/core/Makefile', line 1110)
# zip_root := out/target/product/smdkv210/obj/PACKAGING/tests_zip_intermediates/full_smdkv210-tests-eng.lnt
# variable set hash-table stats:
# Load=2/32=6%, Rehash=0, Collisions=1/5=20%
#  commands to execute (from `build/core/Makefile', line 1118):
	@echo "Package test files: $@"
	$(hide) rm -rf $@ $(zip_root)
	$(hide) mkdir -p $(dir $@) $(zip_root)
	@# Some parts of the system image
	$(hide) $(call package_files-copy-root, \
	$(SYSTEMIMAGE_SOURCE_DIR)/xbin,$(zip_root)/SYSTEM/xbin)
	$(hide) $(call package_files-copy-root, \
	$(SYSTEMIMAGE_SOURCE_DIR)/lib,$(zip_root)/SYSTEM/lib)
	$(hide) $(call package_files-copy-root, \
	$(SYSTEMIMAGE_SOURCE_DIR)/framework, \
	$(zip_root)/SYSTEM/framework)
	$(hide) $(ACP) $(SYSTEMIMAGE_SOURCE_DIR)/build.prop $(zip_root)/SYSTEM
	@# Contents of the data image
	$(hide) $(call package_files-copy-root, \
	$(TARGET_OUT_DATA),$(zip_root)/DATA)
	$(hide) (cd $(zip_root) && zip -qry ../$(notdir $@) .)
	

# Not a target:
external/tvplay/tvrecv/Android.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-27 22:11:03.450398209
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

external/tvplay/tvrecv/HostAppInfoClientAPI.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

snod: all_modules | out/host/linux-x86/bin/mkyaffs2image
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 762):
	@echo "make $@: ignoring dependencies"
	$(call build-systemimage-target,$(INSTALLED_SYSTEMIMAGE))
	$(hide) $(call assert-max-image-size,$(INSTALLED_SYSTEMIMAGE),$(BOARD_SYSTEMIMAGE_PARTITION_SIZE),yaffs)
	

out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Decoder.so: LMC1800/sources/media/omx/libOMX.SEC.M4V.Decoder.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
android.core.tests.dom:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/host/linux-x86/bin/mkyaffs2image:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/host/linux-x86/framework/dumpkey.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
device/samsung/smdkv210/init.smdkv210.rc:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
packages/apps/Gallery3D/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:26
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
build/core/combo/HOST_linux-x86.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/target/product/smdkv210/system/lib/libc.so:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 11:46:59
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/apps/Mms/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:31
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/kernel/common/linux/byteorder/swab.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/wallpapers/MagicSmoke/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:58
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/tinyxml/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:13
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

external/tvplay/tvrecv/HostIncludeApi.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

external/tvplay/tvrecv/HostTunerClientAPI.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/dex_bootjars/system/framework/ext.odex: out/target/common/obj/JAVA_LIBRARIES/ext_intermediates/javalib.jar out/target/product/smdkv210/dex_bootjars/system/framework/bouncycastle.odex | out/host/linux-x86/bin/acp dalvik/tools/dex-preopt out/host/linux-x86/bin/dexopt
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/dex_preopt.mk', line 66)
# PRIVATE_DBJ_JAR := out/target/product/smdkv210/dex_bootjars/system/framework/ext.jar
# variable set hash-table stats:
# Load=1/32=3%, Rehash=0, Collisions=0/1=0%
#  commands to execute (from `build/core/dex_preopt.mk', line 66):
	@echo "Dexpreopt Boot Jar: $@"
	@ rm -f $@
	@ mkdir -p $(dir $@)
	@ out/host/linux-x86/bin/acp -fpt $< $(PRIVATE_DBJ_JAR)
	$(call dexpreopt-one-file,$(PRIVATE_DBJ_JAR),$@)
	

userdataimage-nodeps: out/host/linux-x86/bin/mkyaffs2image
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 825):
	$(build-userdataimage-target)
	

external/tvplay/tvrecv/HostDateTimeServerAPI.c:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/apps/AccountsAndSyncSettings/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:18
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/media/audio/alarms/Alarm_Rooster_02.ogg: frameworks/base/data/sounds/Alarm_Rooster_02.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

droid: droidcore dist_libraries
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

out/target/product/smdkv210/system/lib/libstagefright_avc_common.so: LMC1800/sources/media/sf/libstagefright_avc_common.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/dex_bootjars/system/framework/framework_nodex.jar: out/target/common/obj/JAVA_LIBRARIES/framework_intermediates/javalib.jar | out/host/linux-x86/bin/acp out/host/linux-x86/bin/aapt
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/dex_preopt.mk', line 66):
	$(call copy-file-to-target)
	$(call dexpreopt-remove-classes.dex,$@)
	

# Not a target:
development/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:48:27
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
vendor/samsung/smdkv210/proprietary/libglslcompiler.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/obj/NOTICE_FILES/hash-timestamp: out/target/product/smdkv210/system/bin/tvrecv out/target/product/smdkv210/root/init.rc out/target/product/smdkv210/system/etc/vold.fstab out/target/product/smdkv210/system/lib/egl/egl.cfg out/target/product/smdkv210/root/init.smdkv210.rc out/target/product/smdkv210/root/ueventd.smdkv210.rc out/target/product/smdkv210/system/usr/keylayout/s3c-keypad.kl out/target/product/smdkv210/system/etc/media_profiles.xml out/target/product/smdkv210/system/etc/secomxregistry out/target/product/smdkv210/system/lib/libSEC_OMX_Core.so out/target/product/smdkv210/system/lib/libSEC_Resourcemanager.so out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M2V.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.WMV.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Encoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Encoder.so out/target/product/smdkv210/system/lib/libstagefright_amrnb_common.so out/target/product/smdkv210/system/lib/libstagefright_enc_common.so out/target/product/smdkv210/system/lib/libstagefright_avc_common.so out/target/product/smdkv210/system/lib/libstagefright_foundation.so out/target/product/smdkv210/system/lib/libstagefright_color_conversion.so out/target/product/smdkv210/system/lib/libstagefright.so out/target/product/smdkv210/system/lib/libstagefright_omx.so out/target/product/smdkv210/system/lib/liba52.so out/target/product/smdkv210/system/bin/playts out/target/product/smdkv210/system/lib/libtv.so out/target/product/smdkv210/system/bin/remount_ctl out/target/product/smdkv210/system/media/bootanimation.zip out/target/product/smdkv210/system/app/PrimaryApp.apk out/target/product/smdkv210/system/app/HomePage.apk out/target/product/smdkv210/system/app/FileManagers.apk out/target/product/smdkv210/system/app/Game.apk out/target/product/smdkv210/system/lib/libmoddvb.so out/target/product/smdkv210/system/lib/libjnidvb.so out/target/product/smdkv210/system/lib/libdvbbinderclient.so out/target/product/smdkv210/system/lib/libdvbcomm.so out/target/product/smdkv210/system/lib/modules/utiusb.ko out/target/product/smdkv210/system/bin/dvb_load out/target/product/smdkv210/system/bin/busybox out/target/product/smdkv210/system/bin/bash out/target/product/smdkv210/system/bin/dvb_server out/target/product/smdkv210/system/etc/wifi/wpa_supplicant.conf out/target/product/smdkv210/system/etc/dhd.ko out/target/product/smdkv210/system/etc/fw_bcm4329.bin out/target/product/smdkv210/system/etc/nvram.txt out/target/product/smdkv210/system/etc/permissions/handheld_core_hardware.xml out/target/product/smdkv210/system/etc/permissions/android.software.live_wallpaper.xml out/target/product/smdkv210/kernel out/target/product/smdkv210/system/etc/permissions/android.software.live_wallpaper.xml out/target/product/smdkv210/system/vendor/bin/pvrsrvinit out/target/product/smdkv210/system/vendor/lib/egl/libEGL_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/egl/libGLESv1_CM_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/egl/libGLESv2_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/libglslcompiler.so out/target/product/smdkv210/system/vendor/lib/libIMGegl.so out/target/product/smdkv210/system/vendor/lib/libpvr2d.so out/target/product/smdkv210/system/vendor/lib/libpvrANDROID_WSEGL.so out/target/product/smdkv210/system/vendor/lib/libPVRScopeServices.so out/target/product/smdkv210/system/vendor/lib/libsrv_init.so out/target/product/smdkv210/system/vendor/lib/libsrv_um.so out/target/product/smdkv210/system/vendor/lib/libusc.so out/target/product/smdkv210/system/vendor/lib/hw/gralloc.s5pc110.so out/target/product/smdkv210/system/etc/apns-conf.xml out/target/product/smdkv210/system/etc/vold.conf out/target/product/smdkv210/system/media/audio/notifications/F1_MissedCall.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_New_MMS.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_New_SMS.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Buzzer.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_01.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_02.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Classic.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_03.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Rooster_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Classic_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Digital_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_04.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/BeatPlucker.ogg out/target/product/smdkv210/system/media/audio/ringtones/BentleyDubs.ogg out/target/product/smdkv210/system/media/audio/ringtones/BirdLoop.ogg out/target/product/smdkv210/system/media/audio/ringtones/CaribbeanIce.ogg out/target/product/smdkv210/system/media/audio/ringtones/CurveBall.ogg out/target/product/smdkv210/system/media/audio/ringtones/EtherShake.ogg out/target/product/smdkv210/system/media/audio/ringtones/FriendlyGhost.ogg out/target/product/smdkv210/system/media/audio/ringtones/GameOverGuitar.ogg out/target/product/smdkv210/system/media/audio/ringtones/Growl.ogg out/target/product/smdkv210/system/media/audio/ringtones/InsertCoin.ogg out/target/product/smdkv210/system/media/audio/ringtones/LoopyLounge.ogg out/target/product/smdkv210/system/media/audio/ringtones/LoveFlute.ogg out/target/product/smdkv210/system/media/audio/ringtones/MidEvilJaunt.ogg out/target/product/smdkv210/system/media/audio/ringtones/MildlyAlarming.ogg out/target/product/smdkv210/system/media/audio/ringtones/NewPlayer.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises1.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises2.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises3.ogg out/target/product/smdkv210/system/media/audio/ringtones/OrganDub.ogg out/target/product/smdkv210/system/media/audio/ringtones/RomancingTheTone.ogg out/target/product/smdkv210/system/media/audio/ringtones/SitarVsSitar.ogg out/target/product/smdkv210/system/media/audio/ringtones/SpringyJalopy.ogg out/target/product/smdkv210/system/media/audio/ringtones/Terminated.ogg out/target/product/smdkv210/system/media/audio/ringtones/TwirlAway.ogg out/target/product/smdkv210/system/media/audio/ringtones/VeryAlarmed.ogg out/target/product/smdkv210/system/media/audio/ringtones/World.ogg out/target/product/smdkv210/system/media/audio/notifications/CaffeineSnake.ogg out/target/product/smdkv210/system/media/audio/notifications/DearDeer.ogg out/target/product/smdkv210/system/media/audio/notifications/DontPanic.ogg out/target/product/smdkv210/system/media/audio/notifications/Highwire.ogg out/target/product/smdkv210/system/media/audio/notifications/KzurbSonar.ogg out/target/product/smdkv210/system/media/audio/notifications/OnTheHunt.ogg out/target/product/smdkv210/system/media/audio/notifications/Voila.ogg out/target/product/smdkv210/system/media/audio/notifications/Beat_Box_Android.ogg out/target/product/smdkv210/system/media/audio/notifications/Heaven.ogg out/target/product/smdkv210/system/media/audio/notifications/TaDa.ogg out/target/product/smdkv210/system/media/audio/notifications/Tinkerbell.ogg out/target/product/smdkv210/system/media/audio/ui/Effect_Tick.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressStandard.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressSpacebar.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressDelete.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressReturn.ogg out/target/product/smdkv210/system/media/audio/ui/VideoRecord.ogg out/target/product/smdkv210/system/media/audio/ui/camera_click.ogg out/target/product/smdkv210/system/media/audio/ringtones/CrazyDream.ogg out/target/product/smdkv210/system/media/audio/ringtones/DreamTheme.ogg out/target/product/smdkv210/system/tts/lang_pico/de-DE_gl0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/de-DE_ta.bin out/target/product/smdkv210/system/tts/lang_pico/en-GB_kh0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/en-GB_ta.bin out/target/product/smdkv210/system/tts/lang_pico/en-US_lh0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/en-US_ta.bin out/target/product/smdkv210/system/tts/lang_pico/es-ES_zl0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/es-ES_ta.bin out/target/product/smdkv210/system/tts/lang_pico/fr-FR_nk0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/fr-FR_ta.bin out/target/product/smdkv210/system/tts/lang_pico/it-IT_cm0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/it-IT_ta.bin out/target/product/smdkv210/root/default.prop out/target/product/smdkv210/system/build.prop out/target/product/smdkv210/system/etc/event-log-tags out/target/product/smdkv210/obj/NOTICE_FILES/src/kernel.txt build/core/Makefile
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/Makefile', line 491)
# PRIVATE_DIR := out/target/product/smdkv210/obj/NOTICE_FILES
# variable set hash-table stats:
# Load=1/32=3%, Rehash=0, Collisions=0/1=0%
#  commands to execute (from `build/core/Makefile', line 491):
	@echo Finding NOTICE files: $@
	$(hide) rm -rf $@ $(PRIVATE_DIR)/hash
	$(hide) mkdir -p $(PRIVATE_DIR)/hash
	$(hide) for file in $$(find $(PRIVATE_DIR)/src -type f); do hash=$$(md5sum $$file | sed -e "s/ .*//"); hashfile=$(PRIVATE_DIR)/hash/$$hash; echo $$file >> $$hashfile; done
	$(hide) touch $@
	

bionic/libc/kernel/arch-arm/asm/byteorder.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/tts/lang_pico/fr-FR_nk0_sg.bin: external/svox/pico/lang/fr-FR_nk0_sg.bin | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
device/htc/passion-common/media_a1026.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:59
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/userdata.tar.bz2: out/host/linux-x86/bin/fs_get_stats
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/Makefile', line 839)
# PRIVATE_USERDATA_TAR := out/target/product/smdkv210/userdata.tar
# variable set hash-table stats:
# Load=1/32=3%, Rehash=0, Collisions=0/2=0%
#  commands to execute (from `build/core/Makefile', line 841):
	$(build-userdatatarball-target)
	

# Not a target:
LMC1800/sources/media/omx/libSEC_Resourcemanager.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
CtsSaxTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

clean-tvrecv::
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/base_rules.mk', line 423)
# PRIVATE_MODULE := tvrecv
# makefile (from `build/core/dynamic_binary.mk', line 160)
# PRIVATE_CLEAN_FILES :=  out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/tvrecv out/target/product/smdkv210/system/bin/tvrecv out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/LINKED/tvrecv out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/LINKED/tvrecv out/target/product/smdkv210/symbols/system/bin/tvrecv
# variable set hash-table stats:
# Load=2/32=6%, Rehash=0, Collisions=0/12=0%
#  commands to execute (from `build/core/base_rules.mk', line 430):
	@echo "Clean: $(PRIVATE_MODULE)"
	$(hide) rm -rf $(PRIVATE_CLEAN_FILES)
	

# Not a target:
packages/apps/Launcher2/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:25
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

checkbuild: out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/tvrecv
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

# Not a target:
out/host/linux-x86/bin/mkbootfs:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/app/FileManagers.apk: LMC1800/sources/ui/FileManagers.apk | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketRecv.o: external/tvplay/tvrecv/SocketRecv.c external/tvplay/tvrecv/SocketRecv.c system/core/include/arch/linux-arm/AndroidConfig.h external/tvplay/tvrecv/SocketHeader.h bionic/libc/include/sys/types.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stddef.h bionic/libc/include/stdint.h bionic/libc/include/sys/_types.h bionic/libc/arch-arm/include/machine/_types.h bionic/libc/include/sys/cdefs.h bionic/libc/include/sys/cdefs_elf.h bionic/libc/kernel/common/linux/posix_types.h bionic/libc/kernel/common/linux/stddef.h bionic/libc/kernel/common/linux/compiler.h bionic/libc/kernel/arch-arm/asm/posix_types.h bionic/libc/kernel/arch-arm/asm/types.h bionic/libc/kernel/common/linux/types.h bionic/libc/arch-arm/include/machine/kernel.h bionic/libc/include/sys/sysmacros.h bionic/libc/include/sys/socket.h bionic/libc/kernel/common/linux/socket.h bionic/libc/kernel/arch-arm/asm/socket.h bionic/libc/kernel/arch-arm/asm/sockios.h bionic/libc/kernel/common/linux/sockios.h bionic/libc/kernel/common/linux/uio.h bionic/libc/include/netinet/in.h bionic/libc/arch-arm/include/endian.h bionic/libc/include/sys/endian.h bionic/libc/kernel/common/linux/in.h bionic/libc/kernel/arch-arm/asm/byteorder.h bionic/libc/kernel/common/linux/byteorder/little_endian.h bionic/libc/kernel/common/linux/byteorder/swab.h bionic/libc/kernel/common/linux/byteorder/generic.h bionic/libc/kernel/common/linux/in6.h bionic/libc/include/netinet/in6.h bionic/libc/include/arpa/inet.h bionic/libc/include/unistd.h bionic/libc/include/sys/select.h bionic/libc/include/sys/time.h bionic/libc/kernel/common/linux/time.h bionic/libc/include/signal.h bionic/libc/include/limits.h bionic/libc/include/sys/limits.h bionic/libc/kernel/common/linux/limits.h bionic/libc/arch-arm/include/machine/internal_types.h bionic/libc/arch-arm/include/machine/limits.h bionic/libc/include/sys/syslimits.h bionic/libc/include/string.h bionic/libc/include/malloc.h bionic/libc/kernel/arch-arm/asm/signal.h bionic/libc/kernel/common/asm-generic/signal.h bionic/libc/kernel/arch-arm/asm/siginfo.h bionic/libc/kernel/common/asm-generic/siginfo.h bionic/libc/include/sys/sysconf.h bionic/libc/kernel/common/linux/capability.h bionic/libc/include/pathconf.h bionic/libc/include/fcntl.h bionic/libc/kernel/common/linux/fcntl.h bionic/libc/kernel/arch-arm/asm/fcntl.h bionic/libc/kernel/common/asm-generic/fcntl.h bionic/libc/include/stdlib.h bionic/libc/include/alloca.h bionic/libc/include/strings.h bionic/libc/include/memory.h bionic/libc/include/stdio.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stdarg.h | all_copied_headers
#  Implicit rule search has not been done.
#  Implicit/static pattern stem: `SocketRecv'
#  Last modified 2012-02-27 22:04:06.238410089
#  File has been updated.
#  Successfully updated.
# makefile (from `build/core/binary.mk', line 261)
# PRIVATE_ARM_MODE := thumb
# makefile (from `build/core/binary.mk', line 262)
# PRIVATE_ARM_CFLAGS := -mthumb -Os -fomit-frame-pointer -fno-strict-aliasing -finline-limit=64
# variable set hash-table stats:
# Load=2/32=6%, Rehash=0, Collisions=0/4=0%
#  commands to execute (from `build/core/binary.mk', line 268):
	$(transform-$(PRIVATE_HOST)c-to-o)
	

out/host/linux-x86/cts/all_cts_core_files_stamp: android.core.tests.dom android.core.tests.luni.io android.core.tests.luni.lang android.core.tests.luni.net android.core.tests.luni.util android.core.tests.xml android.core.tests.runner out/host/linux-x86/framework/descGen.jar out/target/common/obj/JAVA_LIBRARIES/core_intermediates/javalib.jar out/target/common/obj/JAVA_LIBRARIES/core-junit_intermediates/javalib.jar out/target/common/obj/JAVA_LIBRARIES/core-junitrunner_intermediates/javalib.jar out/target/common/obj/JAVA_LIBRARIES/core-tests-support_intermediates/javalib.jar out/target/common/obj/JAVA_LIBRARIES/core-tests-dom_intermediates/javalib.jar out/target/common/obj/JAVA_LIBRARIES/core-tests-xml_intermediates/javalib.jar out/target/common/obj/JAVA_LIBRARIES/core-tests_intermediates/javalib.jar out/host/linux-x86/cts/all_cts_files_stamp | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/tasks/cts.mk', line 94)
# PRIVATE_PARAMS := -Dcts.useSuppliedTestResult=true -Dcts.useEnhancedJunit=true
# makefile (from `build/core/tasks/cts.mk', line 91)
# PRIVATE_CLASSPATH := out/target/common/obj/JAVA_LIBRARIES/core_intermediates/classes.jar:out/target/common/obj/JAVA_LIBRARIES/core-junit_intermediates/classes.jar:out/target/common/obj/JAVA_LIBRARIES/core-junitrunner_intermediates/classes.jar:out/target/common/obj/JAVA_LIBRARIES/core-tests-support_intermediates/classes.jar:out/target/common/obj/JAVA_LIBRARIES/core-tests-dom_intermediates/classes.jar:out/target/common/obj/JAVA_LIBRARIES/core-tests-xml_intermediates/classes.jar:out/target/common/obj/JAVA_LIBRARIES/core-tests_intermediates/classes.jar:out/target/common/obj/JAVA_LIBRARIES/core_intermediates/javalib.jar:out/target/common/obj/JAVA_LIBRARIES/core-junit_intermediates/javalib.jar:out/target/common/obj/JAVA_LIBRARIES/core-junitrunner_intermediates/javalib.jar:out/target/common/obj/JAVA_LIBRARIES/core-tests-support_intermediates/javalib.jar:out/target/common/obj/JAVA_LIBRARIES/core-tests-dom_intermediates/javalib.jar:out/target/common/obj/JAVA_LIBRARIES/core-tests-xml_intermediates/javalib.jar:out/target/common/obj/JAVA_LIBRARIES/core-tests_intermediates/javalib.jar:out/host/linux-x86/framework/descGen.jar:/usr/lib/jvm/java-6-openjdk/lib/tools.jar
# makefile (from `build/core/tasks/cts.mk', line 92)
# PRIVATE_JAVAOPTS := -Xmx256M
# variable set hash-table stats:
# Load=3/32=9%, Rehash=0, Collisions=3/6=50%
#  commands to execute (from `build/core/tasks/cts.mk', line 101):
	$(call generate-core-test-description,$(cts_dir)/$(cts_name)/repository/testcases/android.core.tests.dom,\
	cts/tests/core/dom/AndroidManifest.xml,\
	tests.dom.AllTests, libcore/expectations)
	$(call generate-core-test-description,$(cts_dir)/$(cts_name)/repository/testcases/android.core.tests.luni.io,\
	cts/tests/core/luni-io/AndroidManifest.xml,\
	tests.luni.AllTestsIo, libcore/expectations)
	$(call generate-core-test-description,$(cts_dir)/$(cts_name)/repository/testcases/android.core.tests.luni.lang,\
	cts/tests/core/luni-lang/AndroidManifest.xml,\
	tests.luni.AllTestsLang, libcore/expectations)
	$(call generate-core-test-description,$(cts_dir)/$(cts_name)/repository/testcases/android.core.tests.luni.net,\
	cts/tests/core/luni-net/AndroidManifest.xml,\
	tests.luni.AllTestsNet, libcore/expectations)
	$(call generate-core-test-description,$(cts_dir)/$(cts_name)/repository/testcases/android.core.tests.luni.util,\
	cts/tests/core/luni-util/AndroidManifest.xml,\
	tests.luni.AllTestsUtil, libcore/expectations)
	$(call generate-core-test-description,$(cts_dir)/$(cts_name)/repository/testcases/android.core.tests.xml,\
	cts/tests/core/xml/AndroidManifest.xml,\
	tests.xml.AllTests, libcore/expectations)
	$(hide) touch $@
	

# Not a target:
external/e2fsprogs/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:00
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

/resource/plans: out/host/linux-x86/cts/all_cts_files_stamp out/host/linux-x86/cts/all_cts_core_files_stamp cts/tools/utils/buildCts.py out/host/linux-x86/cts/android-cts/repository/testcases/android.core.vm-tests out/host/linux-x86/cts/android-cts/repository/testcases/CtsAppSecurityTests.jar out/host/linux-x86/framework/descGen.jar
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/tasks/cts.mk', line 155):
	$(hide) $(cts_tools_src_dir)/utils/buildCts.py cts/tests/tests/ $(PRIVATE_DIR) $(TMP_DIR) \
	$(TOP) $(HOST_OUT_JAVA_LIBRARIES)/descGen.jar
	

# Not a target:
packages/inputmethods/PinyinIME/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:47
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/providers/CalendarProvider/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:02:02
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
build/core/combo/arch/arm/armv7-a-neon.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/include/string.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
LMC1800/sources/media/omx/libOMX.SEC.AVC.Decoder.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/kernel/arch-arm/asm/fcntl.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
device/samsung/smdkc110/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:56
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/providers/GoogleContactsProvider/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:02:02
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/vendor/lib/egl/libGLESv1_CM_POWERVR_SGX540_120.so: vendor/samsung/smdkv210/proprietary/libGLESv1_CM_POWERVR_SGX540_120.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
external/protobuf/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:50:39
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
build/core/notice_files.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/qemu/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:53:26
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
LMC1800/sources/ui/bootanimation.zip:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/genext2fs/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:42
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
.tex.dvi:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(TEX) $<

# Not a target:
prebuilt/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:07
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/apps/VoiceDialer/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:25
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
system/bluetooth/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:53:33
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/apps/Settings/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:36
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
build/target/product/full.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/target/product/smdkv210/obj/EXECUTABLES/check_prereq_intermediates/check_prereq:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/include/stdlib.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/host/linux-x86/bin/soslim:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 11:35:09
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
.cpp.o:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(COMPILE.cpp) $(OUTPUT_OPTION) $<

# Not a target:
.cpp:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(LINK.cpp) $^ $(LOADLIBES) $(LDLIBS) -o $@

out/target/product/smdkv210/system/media/audio/ringtones/VeryAlarmed.ogg: frameworks/base/data/sounds/newwavelabs/VeryAlarmed.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
device/htc/passion-common/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:59
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

clean:
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.
#  commands to execute (from `build/core/main.mk', line 773):
	@rm -rf $(OUT_DIR)
	@echo "Entire build directory removed."
	

# Not a target:
out/host/linux-x86/framework/hosttestlib.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/Ring_Synth_02.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
device/samsung/smdkv210/ueventd.smdkv210.rc:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
.C.o:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(COMPILE.C) $(OUTPUT_OPTION) $<

# Not a target:
LMC1800/sources/media/omx/libOMX.SEC.AVC.Encoder.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/mtpd/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:50:40
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
LMC1800/sources/media/sf/libstagefright_enc_common.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/esd/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:46
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
libcore/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:42:15
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/svox/pico/lang/fr-FR_ta.bin:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/ringtones/BirdLoop.ogg: frameworks/base/data/sounds/newwavelabs/BirdLoop.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

bionic/libc/include/netinet/in6.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/obj/NOTICE.txt: out/target/product/smdkv210/obj/NOTICE_FILES/hash-timestamp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/Makefile', line 491)
# PRIVATE_DIR := out/target/product/smdkv210/obj/NOTICE_FILES
# makefile (from `build/core/Makefile', line 491)
# PRIVATE_MESSAGE := "Notices for files contained in the filesystem images in this directory:"
# variable set hash-table stats:
# Load=2/32=6%, Rehash=0, Collisions=0/2=0%
#  commands to execute (from `build/core/Makefile', line 491):
	@echo Combining NOTICE files: $@
	$(hide) mkdir -p $(dir $@)
	$(hide) echo $(PRIVATE_MESSAGE) > $@
	$(hide) find $(PRIVATE_DIR)/hash -type f | xargs cat | sort | sed -e "s:$(PRIVATE_DIR)/src\(.*\)\.txt:  \1:" >> $@
	$(hide) echo >> $@
	$(hide) echo >> $@
	$(hide) echo >> $@
	$(hide) for hashfile in $$(find $(PRIVATE_DIR)/hash -type f); do echo "============================================================" >> $@; echo "Notices for file(s):" >> $@; cat $$hashfile | sort | sed -e "s:$(PRIVATE_DIR)/src\(.*\)\.txt:  \1:" >> $@; echo "------------------------------------------------------------" >> $@; echo >> $@; orig=$$(head -n 1 $$hashfile); cat $$orig >> $@; echo >> $@; echo >> $@; echo >> $@; done
	

# Not a target:
device/samsung/smdkv210/device.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:58
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/lib/libSEC_Resourcemanager.so: LMC1800/sources/media/omx/libSEC_Resourcemanager.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
bootable/diskinstaller/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:07
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

cts: out/host/linux-x86/cts/android-cts.zip adb
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

external/tvplay/tvrecv/debug.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
device/sample/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:54
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
LMC1800/sources/broadcom/fw_bcm4329.bin:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
build/core/tasks/apicheck.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/newwavelabs/NewPlayer.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/Ring_Digital_02.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/svox/pico/lang/es-ES_ta.bin:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/host/linux-x86/bin/mkbootimg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/skia/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:12
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/tts/lang_pico/es-ES_ta.bin: external/svox/pico/lang/es-ES_ta.bin | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/system/media/audio/notifications/Beat_Box_Android.ogg: frameworks/base/data/sounds/notifications/Beat_Box_Android.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
CtsPermissionTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
build/target/product/sdk.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/media/audio/notifications/Heaven.ogg: frameworks/base/data/sounds/notifications/Heaven.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
external/elfutils/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
CtsPermissionDeclareApp:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/bouncycastle/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:50:32
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
build/target/product/generic.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
vendor/samsung/smdkv210/BoardConfigVendor.mk:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:44
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/neven/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:01
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
hardware/libhardware_legacy/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:46
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/lib/libstagefright.so: LMC1800/sources/media/sf/libstagefright.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
sdk/build/tools.atree:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/ringtones/BentleyDubs.ogg: frameworks/base/data/sounds/newwavelabs/BentleyDubs.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
vendor/samsung/smdkv210/proprietary/libGLESv2_POWERVR_SGX540_120.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/kernel/common/linux/capability.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/newwavelabs/SpringyJalopy.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

systemimage-nodeps: all_modules | out/host/linux-x86/bin/mkyaffs2image
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 762):
	@echo "make $@: ignoring dependencies"
	$(call build-systemimage-target,$(INSTALLED_SYSTEMIMAGE))
	$(hide) $(call assert-max-image-size,$(INSTALLED_SYSTEMIMAGE),$(BOARD_SYSTEMIMAGE_PARTITION_SIZE),yaffs)
	

out/target/product/smdkv210/system/lib/libjnidvb.so: LMC1800/sources/dvb/libjnidvb.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
cts/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:41
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/newwavelabs/Noises1.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/ringtones/CrazyDream.ogg: frameworks/base/data/sounds/newwavelabs/CrazyDream.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
build/core/user_tags.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

dist:
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.
#  commands to execute (from `build/core/distdir.mk', line 21):
	

# Not a target:
.txinfo.info:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(MAKEINFO) $(MAKEINFO_FLAGS) $< -o $@

out/target/product/smdkv210/obj/PACKAGING/systemimage_intermediates/system.img: out/target/product/smdkv210/system/bin/tvrecv out/target/product/smdkv210/system/etc/vold.fstab out/target/product/smdkv210/system/lib/egl/egl.cfg out/target/product/smdkv210/system/usr/keylayout/s3c-keypad.kl out/target/product/smdkv210/system/etc/media_profiles.xml out/target/product/smdkv210/system/etc/secomxregistry out/target/product/smdkv210/system/lib/libSEC_OMX_Core.so out/target/product/smdkv210/system/lib/libSEC_Resourcemanager.so out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M2V.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.WMV.Decoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Encoder.so out/target/product/smdkv210/system/lib/libOMX.SEC.M4V.Encoder.so out/target/product/smdkv210/system/lib/libstagefright_amrnb_common.so out/target/product/smdkv210/system/lib/libstagefright_enc_common.so out/target/product/smdkv210/system/lib/libstagefright_avc_common.so out/target/product/smdkv210/system/lib/libstagefright_foundation.so out/target/product/smdkv210/system/lib/libstagefright_color_conversion.so out/target/product/smdkv210/system/lib/libstagefright.so out/target/product/smdkv210/system/lib/libstagefright_omx.so out/target/product/smdkv210/system/lib/liba52.so out/target/product/smdkv210/system/bin/playts out/target/product/smdkv210/system/lib/libtv.so out/target/product/smdkv210/system/bin/remount_ctl out/target/product/smdkv210/system/media/bootanimation.zip out/target/product/smdkv210/system/app/PrimaryApp.apk out/target/product/smdkv210/system/app/HomePage.apk out/target/product/smdkv210/system/app/FileManagers.apk out/target/product/smdkv210/system/app/Game.apk out/target/product/smdkv210/system/lib/libmoddvb.so out/target/product/smdkv210/system/lib/libjnidvb.so out/target/product/smdkv210/system/lib/libdvbbinderclient.so out/target/product/smdkv210/system/lib/libdvbcomm.so out/target/product/smdkv210/system/lib/modules/utiusb.ko out/target/product/smdkv210/system/bin/dvb_load out/target/product/smdkv210/system/bin/busybox out/target/product/smdkv210/system/bin/bash out/target/product/smdkv210/system/bin/dvb_server out/target/product/smdkv210/system/etc/wifi/wpa_supplicant.conf out/target/product/smdkv210/system/etc/dhd.ko out/target/product/smdkv210/system/etc/fw_bcm4329.bin out/target/product/smdkv210/system/etc/nvram.txt out/target/product/smdkv210/system/etc/permissions/handheld_core_hardware.xml out/target/product/smdkv210/system/etc/permissions/android.software.live_wallpaper.xml out/target/product/smdkv210/system/etc/permissions/android.software.live_wallpaper.xml out/target/product/smdkv210/system/vendor/bin/pvrsrvinit out/target/product/smdkv210/system/vendor/lib/egl/libEGL_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/egl/libGLESv1_CM_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/egl/libGLESv2_POWERVR_SGX540_120.so out/target/product/smdkv210/system/vendor/lib/libglslcompiler.so out/target/product/smdkv210/system/vendor/lib/libIMGegl.so out/target/product/smdkv210/system/vendor/lib/libpvr2d.so out/target/product/smdkv210/system/vendor/lib/libpvrANDROID_WSEGL.so out/target/product/smdkv210/system/vendor/lib/libPVRScopeServices.so out/target/product/smdkv210/system/vendor/lib/libsrv_init.so out/target/product/smdkv210/system/vendor/lib/libsrv_um.so out/target/product/smdkv210/system/vendor/lib/libusc.so out/target/product/smdkv210/system/vendor/lib/hw/gralloc.s5pc110.so out/target/product/smdkv210/system/etc/apns-conf.xml out/target/product/smdkv210/system/etc/vold.conf out/target/product/smdkv210/system/media/audio/notifications/F1_MissedCall.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_New_MMS.ogg out/target/product/smdkv210/system/media/audio/notifications/F1_New_SMS.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Buzzer.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_01.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_02.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Classic.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Beep_03.ogg out/target/product/smdkv210/system/media/audio/alarms/Alarm_Rooster_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Classic_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Digital_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_04.ogg out/target/product/smdkv210/system/media/audio/ringtones/Ring_Synth_02.ogg out/target/product/smdkv210/system/media/audio/ringtones/BeatPlucker.ogg out/target/product/smdkv210/system/media/audio/ringtones/BentleyDubs.ogg out/target/product/smdkv210/system/media/audio/ringtones/BirdLoop.ogg out/target/product/smdkv210/system/media/audio/ringtones/CaribbeanIce.ogg out/target/product/smdkv210/system/media/audio/ringtones/CurveBall.ogg out/target/product/smdkv210/system/media/audio/ringtones/EtherShake.ogg out/target/product/smdkv210/system/media/audio/ringtones/FriendlyGhost.ogg out/target/product/smdkv210/system/media/audio/ringtones/GameOverGuitar.ogg out/target/product/smdkv210/system/media/audio/ringtones/Growl.ogg out/target/product/smdkv210/system/media/audio/ringtones/InsertCoin.ogg out/target/product/smdkv210/system/media/audio/ringtones/LoopyLounge.ogg out/target/product/smdkv210/system/media/audio/ringtones/LoveFlute.ogg out/target/product/smdkv210/system/media/audio/ringtones/MidEvilJaunt.ogg out/target/product/smdkv210/system/media/audio/ringtones/MildlyAlarming.ogg out/target/product/smdkv210/system/media/audio/ringtones/NewPlayer.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises1.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises2.ogg out/target/product/smdkv210/system/media/audio/ringtones/Noises3.ogg out/target/product/smdkv210/system/media/audio/ringtones/OrganDub.ogg out/target/product/smdkv210/system/media/audio/ringtones/RomancingTheTone.ogg out/target/product/smdkv210/system/media/audio/ringtones/SitarVsSitar.ogg out/target/product/smdkv210/system/media/audio/ringtones/SpringyJalopy.ogg out/target/product/smdkv210/system/media/audio/ringtones/Terminated.ogg out/target/product/smdkv210/system/media/audio/ringtones/TwirlAway.ogg out/target/product/smdkv210/system/media/audio/ringtones/VeryAlarmed.ogg out/target/product/smdkv210/system/media/audio/ringtones/World.ogg out/target/product/smdkv210/system/media/audio/notifications/CaffeineSnake.ogg out/target/product/smdkv210/system/media/audio/notifications/DearDeer.ogg out/target/product/smdkv210/system/media/audio/notifications/DontPanic.ogg out/target/product/smdkv210/system/media/audio/notifications/Highwire.ogg out/target/product/smdkv210/system/media/audio/notifications/KzurbSonar.ogg out/target/product/smdkv210/system/media/audio/notifications/OnTheHunt.ogg out/target/product/smdkv210/system/media/audio/notifications/Voila.ogg out/target/product/smdkv210/system/media/audio/notifications/Beat_Box_Android.ogg out/target/product/smdkv210/system/media/audio/notifications/Heaven.ogg out/target/product/smdkv210/system/media/audio/notifications/TaDa.ogg out/target/product/smdkv210/system/media/audio/notifications/Tinkerbell.ogg out/target/product/smdkv210/system/media/audio/ui/Effect_Tick.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressStandard.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressSpacebar.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressDelete.ogg out/target/product/smdkv210/system/media/audio/ui/KeypressReturn.ogg out/target/product/smdkv210/system/media/audio/ui/VideoRecord.ogg out/target/product/smdkv210/system/media/audio/ui/camera_click.ogg out/target/product/smdkv210/system/media/audio/ringtones/CrazyDream.ogg out/target/product/smdkv210/system/media/audio/ringtones/DreamTheme.ogg out/target/product/smdkv210/system/tts/lang_pico/de-DE_gl0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/de-DE_ta.bin out/target/product/smdkv210/system/tts/lang_pico/en-GB_kh0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/en-GB_ta.bin out/target/product/smdkv210/system/tts/lang_pico/en-US_lh0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/en-US_ta.bin out/target/product/smdkv210/system/tts/lang_pico/es-ES_zl0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/es-ES_ta.bin out/target/product/smdkv210/system/tts/lang_pico/fr-FR_nk0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/fr-FR_ta.bin out/target/product/smdkv210/system/tts/lang_pico/it-IT_cm0_sg.bin out/target/product/smdkv210/system/tts/lang_pico/it-IT_ta.bin out/target/product/smdkv210/system/build.prop out/target/product/smdkv210/system/etc/event-log-tags out/target/product/smdkv210/system/etc/security/otacerts.zip out/host/linux-x86/bin/mkyaffs2image
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 729):
	$(call build-systemimage-target,$@)
	

# Not a target:
external/dbus/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:08
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
CtsUsePermissionDiffCert:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
vendor/samsung/smdkv210/proprietary/libsrv_um.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/include/fcntl.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/notifications/TaDa.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/etc/permissions/android.software.live_wallpaper.xml: packages/wallpapers/LivePicker/android.software.live_wallpaper.xml packages/wallpapers/LivePicker/android.software.live_wallpaper.xml | out/host/linux-x86/bin/acp out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

bionic/libc/kernel/common/linux/ioctl.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/media/audio/notifications/Tinkerbell.ogg: frameworks/base/data/sounds/notifications/Tinkerbell.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
android.core.tests.luni.io:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostCAClientAPI.o: external/tvplay/tvrecv/HostCAClientAPI.c external/tvplay/tvrecv/HostCAClientAPI.c system/core/include/arch/linux-arm/AndroidConfig.h external/tvplay/tvrecv/HostIncludeApi.h bionic/libc/include/fcntl.h bionic/libc/include/sys/cdefs.h bionic/libc/include/sys/cdefs_elf.h bionic/libc/include/sys/types.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stddef.h bionic/libc/include/stdint.h bionic/libc/include/sys/_types.h bionic/libc/arch-arm/include/machine/_types.h bionic/libc/kernel/common/linux/posix_types.h bionic/libc/kernel/common/linux/stddef.h bionic/libc/kernel/common/linux/compiler.h bionic/libc/kernel/arch-arm/asm/posix_types.h bionic/libc/kernel/arch-arm/asm/types.h bionic/libc/kernel/common/linux/types.h bionic/libc/arch-arm/include/machine/kernel.h bionic/libc/include/sys/sysmacros.h bionic/libc/kernel/common/linux/fcntl.h bionic/libc/kernel/arch-arm/asm/fcntl.h bionic/libc/kernel/common/asm-generic/fcntl.h bionic/libc/include/unistd.h bionic/libc/include/sys/select.h bionic/libc/include/sys/time.h bionic/libc/kernel/common/linux/time.h bionic/libc/include/signal.h bionic/libc/include/limits.h bionic/libc/include/sys/limits.h bionic/libc/kernel/common/linux/limits.h bionic/libc/arch-arm/include/machine/internal_types.h bionic/libc/arch-arm/include/machine/limits.h bionic/libc/include/sys/syslimits.h bionic/libc/include/string.h bionic/libc/include/malloc.h bionic/libc/kernel/arch-arm/asm/signal.h bionic/libc/kernel/common/asm-generic/signal.h bionic/libc/kernel/arch-arm/asm/siginfo.h bionic/libc/kernel/common/asm-generic/siginfo.h bionic/libc/include/sys/sysconf.h bionic/libc/kernel/common/linux/capability.h bionic/libc/include/pathconf.h bionic/libc/include/stdlib.h bionic/libc/include/alloca.h bionic/libc/include/strings.h bionic/libc/include/memory.h bionic/libc/include/stdio.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stdarg.h bionic/libc/include/pthread.h bionic/libc/include/time.h bionic/libc/include/sched.h bionic/libc/include/sys/ioctl.h bionic/libc/kernel/common/linux/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctl.h bionic/libc/kernel/common/asm-generic/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctls.h bionic/libc/kernel/arch-arm/asm/termbits.h bionic/libc/include/sys/ioctl_compat.h external/tvplay/tvrecv/HostUTIAPI.h external/tvplay/tvrecv/HostDeviceAPI.h external/tvplay/tvrecv/HostPlatForm.h external/tvplay/tvrecv/debug.h external/tvplay/tvrecv/HostCAClientAPI.h external/tvplay/tvrecv/HostAppInfoClientAPI.h external/tvplay/tvrecv/HostEPGDBClientAPI.h external/tvplay/tvrecv/HostTunerClientAPI.h external/tvplay/tvrecv/HostRgnClientAPI.h external/tvplay/tvrecv/HostSCClientAPI.h external/tvplay/tvrecv/HostDateTimeServerAPI.h | all_copied_headers
#  Implicit rule search has not been done.
#  Implicit/static pattern stem: `HostCAClientAPI'
#  Last modified 2012-02-27 22:04:05.182410119
#  File has been updated.
#  Successfully updated.
# makefile (from `build/core/binary.mk', line 261)
# PRIVATE_ARM_MODE := thumb
# makefile (from `build/core/binary.mk', line 262)
# PRIVATE_ARM_CFLAGS := -mthumb -Os -fomit-frame-pointer -fno-strict-aliasing -finline-limit=64
# variable set hash-table stats:
# Load=2/32=6%, Rehash=0, Collisions=0/4=0%
#  commands to execute (from `build/core/binary.mk', line 268):
	$(transform-$(PRIVATE_HOST)c-to-o)
	

# Not a target:
LMC1800/sources/remount_ctl:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/newwavelabs/SitarVsSitar.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/ui/VideoRecord.ogg: frameworks/base/data/sounds/effects/VideoRecord.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stdarg.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:53:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
device/htc/passion/full_passion.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:59
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
CtsNetTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/include/pathconf.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

installed-file-list: out/target/product/smdkv210/installed-files.txt
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

# Not a target:
external/iptables/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:50:40
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/kernel/arch-arm/asm/posix_types.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/elfcopy/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:50:39
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/providers/MediaProvider/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:02:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

external/tvplay/tvrecv/SocketRecv.c:
#  Implicit rule search has been done.
#  Last modified 2012-02-27 18:55:44
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostCAClientAPI.P:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  Last modified 2012-02-27 22:04:05.194410119
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/webkit/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:50:30
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/experimental/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:02:00
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostRgnClientAPI.o: external/tvplay/tvrecv/HostRgnClientAPI.c external/tvplay/tvrecv/HostRgnClientAPI.c system/core/include/arch/linux-arm/AndroidConfig.h external/tvplay/tvrecv/HostIncludeApi.h bionic/libc/include/fcntl.h bionic/libc/include/sys/cdefs.h bionic/libc/include/sys/cdefs_elf.h bionic/libc/include/sys/types.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stddef.h bionic/libc/include/stdint.h bionic/libc/include/sys/_types.h bionic/libc/arch-arm/include/machine/_types.h bionic/libc/kernel/common/linux/posix_types.h bionic/libc/kernel/common/linux/stddef.h bionic/libc/kernel/common/linux/compiler.h bionic/libc/kernel/arch-arm/asm/posix_types.h bionic/libc/kernel/arch-arm/asm/types.h bionic/libc/kernel/common/linux/types.h bionic/libc/arch-arm/include/machine/kernel.h bionic/libc/include/sys/sysmacros.h bionic/libc/kernel/common/linux/fcntl.h bionic/libc/kernel/arch-arm/asm/fcntl.h bionic/libc/kernel/common/asm-generic/fcntl.h bionic/libc/include/unistd.h bionic/libc/include/sys/select.h bionic/libc/include/sys/time.h bionic/libc/kernel/common/linux/time.h bionic/libc/include/signal.h bionic/libc/include/limits.h bionic/libc/include/sys/limits.h bionic/libc/kernel/common/linux/limits.h bionic/libc/arch-arm/include/machine/internal_types.h bionic/libc/arch-arm/include/machine/limits.h bionic/libc/include/sys/syslimits.h bionic/libc/include/string.h bionic/libc/include/malloc.h bionic/libc/kernel/arch-arm/asm/signal.h bionic/libc/kernel/common/asm-generic/signal.h bionic/libc/kernel/arch-arm/asm/siginfo.h bionic/libc/kernel/common/asm-generic/siginfo.h bionic/libc/include/sys/sysconf.h bionic/libc/kernel/common/linux/capability.h bionic/libc/include/pathconf.h bionic/libc/include/stdlib.h bionic/libc/include/alloca.h bionic/libc/include/strings.h bionic/libc/include/memory.h bionic/libc/include/stdio.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stdarg.h bionic/libc/include/pthread.h bionic/libc/include/time.h bionic/libc/include/sched.h bionic/libc/include/sys/ioctl.h bionic/libc/kernel/common/linux/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctl.h bionic/libc/kernel/common/asm-generic/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctls.h bionic/libc/kernel/arch-arm/asm/termbits.h bionic/libc/include/sys/ioctl_compat.h external/tvplay/tvrecv/HostUTIAPI.h external/tvplay/tvrecv/HostDeviceAPI.h external/tvplay/tvrecv/HostPlatForm.h external/tvplay/tvrecv/debug.h external/tvplay/tvrecv/HostCAClientAPI.h external/tvplay/tvrecv/HostAppInfoClientAPI.h external/tvplay/tvrecv/HostEPGDBClientAPI.h external/tvplay/tvrecv/HostTunerClientAPI.h external/tvplay/tvrecv/HostRgnClientAPI.h external/tvplay/tvrecv/HostSCClientAPI.h external/tvplay/tvrecv/HostDateTimeServerAPI.h | all_copied_headers
#  Implicit rule search has not been done.
#  Implicit/static pattern stem: `HostRgnClientAPI'
#  Last modified 2012-02-27 22:04:05.730410104
#  File has been updated.
#  Successfully updated.
# makefile (from `build/core/binary.mk', line 261)
# PRIVATE_ARM_MODE := thumb
# makefile (from `build/core/binary.mk', line 262)
# PRIVATE_ARM_CFLAGS := -mthumb -Os -fomit-frame-pointer -fno-strict-aliasing -finline-limit=64
# variable set hash-table stats:
# Load=2/32=6%, Rehash=0, Collisions=0/4=0%
#  commands to execute (from `build/core/binary.mk', line 268):
	$(transform-$(PRIVATE_HOST)c-to-o)
	

# Not a target:
frameworks/base/data/sounds/newwavelabs/VeryAlarmed.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
system/vold/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:53:33
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/target/product/smdkv210/obj/lib/libm.so:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 11:47:06.962960878
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
LMC1800/sources/ui/PrimaryApp.apk:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
build/core/combo/javac.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/include/sys/select.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/obj/PACKAGING/target_files_intermediates/full_smdkv210-target_files-eng.lnt.zip: out/target/product/smdkv210/ramdisk.img out/target/product/smdkv210/system.img out/target/product/smdkv210/userdata.img out/target/product/smdkv210/obj/EXECUTABLES/applypatch_intermediates/applypatch out/target/product/smdkv210/obj/EXECUTABLES/applypatch_static_intermediates/applypatch_static out/target/product/smdkv210/obj/EXECUTABLES/check_prereq_intermediates/check_prereq out/target/product/smdkv210/obj/EXECUTABLES/updater_intermediates/updater out/target/product/smdkv210/obj/PACKAGING/apkcerts_intermediates/full_smdkv210-apkcerts-eng.lnt.txt out/host/linux-x86/bin/fs_config | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/Makefile', line 885)
# intermediates := out/target/product/smdkv210/obj/PACKAGING/target_files_intermediates
# makefile (from `build/core/Makefile', line 906)
# PRIVATE_RECOVERY_API_VERSION := 
# makefile (from `build/core/Makefile', line 886)
# zip_root := out/target/product/smdkv210/obj/PACKAGING/target_files_intermediates/full_smdkv210-target_files-eng.lnt
# makefile (from `build/core/Makefile', line 912)
# tool_extensions := device/samsung/smdkv210
# makefile (from `build/core/Makefile', line 904)
# PRIVATE_OTA_TOOLS := out/target/product/smdkv210/obj/EXECUTABLES/applypatch_intermediates/applypatch out/target/product/smdkv210/obj/EXECUTABLES/applypatch_static_intermediates/applypatch_static out/target/product/smdkv210/obj/EXECUTABLES/check_prereq_intermediates/check_prereq out/target/product/smdkv210/obj/EXECUTABLES/updater_intermediates/updater
# variable set hash-table stats:
# Load=5/32=16%, Rehash=0, Collisions=1/11=9%
#  commands to execute (from `build/core/Makefile', line 928):
	@echo "Package target files: $@"
	$(hide) rm -rf $@ $(zip_root)
	$(hide) mkdir -p $(dir $@) $(zip_root)
	@# Components of the recovery image
	$(hide) mkdir -p $(zip_root)/RECOVERY
	$(hide) $(call package_files-copy-root, \
	$(TARGET_RECOVERY_ROOT_OUT),$(zip_root)/RECOVERY/RAMDISK)
	$(hide) echo "$(BOARD_KERNEL_CMDLINE)" > $(zip_root)/RECOVERY/cmdline
	$(hide) echo "$(BOARD_KERNEL_BASE)" > $(zip_root)/RECOVERY/base
	$(hide) echo "$(BOARD_KERNEL_PAGESIZE)" > $(zip_root)/RECOVERY/pagesize
	@# Components of the boot image
	$(hide) mkdir -p $(zip_root)/BOOT
	$(hide) $(call package_files-copy-root, \
	$(TARGET_ROOT_OUT),$(zip_root)/BOOT/RAMDISK)
	$(hide) echo "$(BOARD_KERNEL_CMDLINE)" > $(zip_root)/BOOT/cmdline
	$(hide) echo "$(BOARD_KERNEL_BASE)" > $(zip_root)/BOOT/base
	$(hide) echo "$(BOARD_KERNEL_PAGESIZE)" > $(zip_root)/BOOT/pagesize
	$(hide) $(foreach t,$(INSTALLED_RADIOIMAGE_TARGET),\
	mkdir -p $(zip_root)/RADIO; \
	$(ACP) $(t) $(zip_root)/RADIO/$(notdir $(t));)
	@# Contents of the system image
	$(hide) $(call package_files-copy-root, \
	$(SYSTEMIMAGE_SOURCE_DIR),$(zip_root)/SYSTEM)
	@# Contents of the data image
	$(hide) $(call package_files-copy-root, \
	$(TARGET_OUT_DATA),$(zip_root)/DATA)
	@# Extra contents of the OTA package
	$(hide) mkdir -p $(zip_root)/OTA/bin
	$(hide) $(ACP) $(INSTALLED_ANDROID_INFO_TXT_TARGET) $(zip_root)/OTA/
	$(hide) $(ACP) $(PRIVATE_OTA_TOOLS) $(zip_root)/OTA/bin/
	@# Files that do not end up in any images, but are necessary to
	@# build them.
	$(hide) mkdir -p $(zip_root)/META
	$(hide) $(ACP) $(APKCERTS_FILE) $(zip_root)/META/apkcerts.txt
	$(hide)	echo "$(PRODUCT_OTA_PUBLIC_KEYS)" > $(zip_root)/META/otakeys.txt
	$(hide) echo "recovery_api_version=$(PRIVATE_RECOVERY_API_VERSION)" > $(zip_root)/META/misc_info.txt
	$(hide) echo "tool_extensions=$(tool_extensions)" >> $(zip_root)/META/misc_info.txt
	@# Zip everything up, preserving symlinks
	$(hide) (cd $(zip_root) && zip -qry ../$(notdir $@) .)
	@# Run fs_config on all the system files in the zip, and save the output
	$(hide) zipinfo -1 $@ | awk -F/ 'BEGIN { OFS="/" } /^SYSTEM\// {$$1 = "system"; print}' | $(HOST_OUT_EXECUTABLES)/fs_config > $(zip_root)/META/filesystem_config.txt
	$(hide) (cd $(zip_root) && zip -q ../$(notdir $@) META/filesystem_config.txt)
	

# Not a target:
CtsPerformance3TestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/vendor/bin/pvrsrvinit: vendor/samsung/smdkv210/proprietary/pvrsrvinit | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/system/media/audio/ringtones/Noises1.ogg: frameworks/base/data/sounds/newwavelabs/Noises1.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/full_smdkv210-emulator-eng.lnt.zip: out/host/linux-x86/bin/emulator prebuilt/android-arm/kernel/kernel-qemu out/target/product/smdkv210/ramdisk.img out/target/product/smdkv210/system.img out/target/product/smdkv210/userdata.img
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 1218):
	@echo "Package: $@"
	$(hide) zip -qj $@ $(INTERNAL_EMULATOR_PACKAGE_FILES)
	

# Not a target:
device/samsung/smdkv210/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:58
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/host/linux-x86/framework/descGen.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
build/core/dex_preopt.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
device/samsung/smdkv210/vold.fstab:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/Alarm_Buzzer.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/netperf/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:50:40
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/tts/lang_pico/en-GB_ta.bin: external/svox/pico/lang/en-GB_ta.bin | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/system/vendor/lib/libglslcompiler.so: vendor/samsung/smdkv210/proprietary/libglslcompiler.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostEPGDBClientAPI.o: external/tvplay/tvrecv/HostEPGDBClientAPI.c external/tvplay/tvrecv/HostEPGDBClientAPI.c system/core/include/arch/linux-arm/AndroidConfig.h external/tvplay/tvrecv/HostIncludeApi.h bionic/libc/include/fcntl.h bionic/libc/include/sys/cdefs.h bionic/libc/include/sys/cdefs_elf.h bionic/libc/include/sys/types.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stddef.h bionic/libc/include/stdint.h bionic/libc/include/sys/_types.h bionic/libc/arch-arm/include/machine/_types.h bionic/libc/kernel/common/linux/posix_types.h bionic/libc/kernel/common/linux/stddef.h bionic/libc/kernel/common/linux/compiler.h bionic/libc/kernel/arch-arm/asm/posix_types.h bionic/libc/kernel/arch-arm/asm/types.h bionic/libc/kernel/common/linux/types.h bionic/libc/arch-arm/include/machine/kernel.h bionic/libc/include/sys/sysmacros.h bionic/libc/kernel/common/linux/fcntl.h bionic/libc/kernel/arch-arm/asm/fcntl.h bionic/libc/kernel/common/asm-generic/fcntl.h bionic/libc/include/unistd.h bionic/libc/include/sys/select.h bionic/libc/include/sys/time.h bionic/libc/kernel/common/linux/time.h bionic/libc/include/signal.h bionic/libc/include/limits.h bionic/libc/include/sys/limits.h bionic/libc/kernel/common/linux/limits.h bionic/libc/arch-arm/include/machine/internal_types.h bionic/libc/arch-arm/include/machine/limits.h bionic/libc/include/sys/syslimits.h bionic/libc/include/string.h bionic/libc/include/malloc.h bionic/libc/kernel/arch-arm/asm/signal.h bionic/libc/kernel/common/asm-generic/signal.h bionic/libc/kernel/arch-arm/asm/siginfo.h bionic/libc/kernel/common/asm-generic/siginfo.h bionic/libc/include/sys/sysconf.h bionic/libc/kernel/common/linux/capability.h bionic/libc/include/pathconf.h bionic/libc/include/stdlib.h bionic/libc/include/alloca.h bionic/libc/include/strings.h bionic/libc/include/memory.h bionic/libc/include/stdio.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stdarg.h bionic/libc/include/pthread.h bionic/libc/include/time.h bionic/libc/include/sched.h bionic/libc/include/sys/ioctl.h bionic/libc/kernel/common/linux/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctl.h bionic/libc/kernel/common/asm-generic/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctls.h bionic/libc/kernel/arch-arm/asm/termbits.h bionic/libc/include/sys/ioctl_compat.h external/tvplay/tvrecv/HostUTIAPI.h external/tvplay/tvrecv/HostDeviceAPI.h external/tvplay/tvrecv/HostPlatForm.h external/tvplay/tvrecv/debug.h external/tvplay/tvrecv/HostCAClientAPI.h external/tvplay/tvrecv/HostAppInfoClientAPI.h external/tvplay/tvrecv/HostEPGDBClientAPI.h external/tvplay/tvrecv/HostTunerClientAPI.h external/tvplay/tvrecv/HostRgnClientAPI.h external/tvplay/tvrecv/HostSCClientAPI.h external/tvplay/tvrecv/HostDateTimeServerAPI.h | all_copied_headers
#  Implicit rule search has not been done.
#  Implicit/static pattern stem: `HostEPGDBClientAPI'
#  Last modified 2012-02-27 22:04:05.51441011
#  File has been updated.
#  Successfully updated.
# makefile (from `build/core/binary.mk', line 261)
# PRIVATE_ARM_MODE := thumb
# makefile (from `build/core/binary.mk', line 262)
# PRIVATE_ARM_CFLAGS := -mthumb -Os -fomit-frame-pointer -fno-strict-aliasing -finline-limit=64
# variable set hash-table stats:
# Load=2/32=6%, Rehash=0, Collisions=0/4=0%
#  commands to execute (from `build/core/binary.mk', line 268):
	$(transform-$(PRIVATE_HOST)c-to-o)
	

# Not a target:
out/target/common/obj/JAVA_LIBRARIES/services_intermediates/javalib.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/host/linux-x86/framework/CtsTestAnnotationsHostLib.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
hardware/msm7k/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:52
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/media/audio/notifications/F1_New_MMS.ogg: frameworks/base/data/sounds/F1_New_MMS.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
external/wpa_supplicant/wpa_supplicant.conf:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

userdatatarball-nodeps: out/host/linux-x86/bin/fs_get_stats
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 845):
	$(build-userdatatarball-target)
	

out/target/product/smdkv210/dex_bootjars/system/framework/services_nodex.jar: out/target/common/obj/JAVA_LIBRARIES/services_intermediates/javalib.jar | out/host/linux-x86/bin/acp out/host/linux-x86/bin/aapt
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/dex_preopt.mk', line 66):
	$(call copy-file-to-target)
	$(call dexpreopt-remove-classes.dex,$@)
	

# Not a target:
out/target/common/obj/JAVA_LIBRARIES/bouncycastle_intermediates/javalib.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
packages/apps/SoundRecorder/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:46
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
CtsSharedUidInstall:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/effects/VideoRecord.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/kernel/common/linux/uio.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

checkapi: out/target/common/obj/PACKAGING/checkapi-last-timestamp out/target/common/obj/PACKAGING/checkapi-current-timestamp
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

# Not a target:
out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostEPGDBClientAPI.P:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  Last modified 2012-02-27 22:04:05.522410112
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/dex_bootjars/system/framework/bouncycastle.odex: out/target/common/obj/JAVA_LIBRARIES/bouncycastle_intermediates/javalib.jar out/target/product/smdkv210/dex_bootjars/system/framework/core.odex | out/host/linux-x86/bin/acp dalvik/tools/dex-preopt out/host/linux-x86/bin/dexopt
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/dex_preopt.mk', line 66)
# PRIVATE_DBJ_JAR := out/target/product/smdkv210/dex_bootjars/system/framework/bouncycastle.jar
# variable set hash-table stats:
# Load=1/32=3%, Rehash=0, Collisions=0/1=0%
#  commands to execute (from `build/core/dex_preopt.mk', line 66):
	@echo "Dexpreopt Boot Jar: $@"
	@ rm -f $@
	@ mkdir -p $(dir $@)
	@ out/host/linux-x86/bin/acp -fpt $< $(PRIVATE_DBJ_JAR)
	$(call dexpreopt-one-file,$(PRIVATE_DBJ_JAR),$@)
	

# Not a target:
external/srec/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:53:02
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/newwavelabs/LoveFlute.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/kernel/arch-arm/asm/sockios.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
build/core/device.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/kernel/common/linux/stddef.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
build/core/pathmap.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

package-stats: out/target/product/smdkv210/package-stats.txt
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

# Not a target:
external/svox/pico/lang/fr-FR_nk0_sg.bin:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
ApiDemos:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

recoveryimage:
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

# Not a target:
packages/wallpapers/LivePicker/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:59
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system.img: out/target/product/smdkv210/obj/PACKAGING/systemimage_intermediates/system.img | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 753):
	@echo "Install system fs image: $@"
	$(copy-file-to-target)
	$(hide) $(call assert-max-image-size,$@ $(RECOVERY_FROM_BOOT_PATCH),$(BOARD_SYSTEMIMAGE_PARTITION_SIZE),yaffs)
	

out/target/product/smdkv210/system/etc/wifi/wpa_supplicant.conf: external/wpa_supplicant/wpa_supplicant.conf | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
CtsTextTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
CtsGraphicsTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/include/sys/cdefs.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/netcat/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:01
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
vendor/samsung/smdkv210/proprietary/gralloc.s5pc110.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/newwavelabs/MidEvilJaunt.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
CtsAppTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
CtsWidgetTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
LMC1800/sources/media/omx/secomxregistry:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostXDCServerAPI.o: external/tvplay/tvrecv/HostXDCServerAPI.c external/tvplay/tvrecv/HostXDCServerAPI.c system/core/include/arch/linux-arm/AndroidConfig.h external/tvplay/tvrecv/HostIncludeApi.h bionic/libc/include/fcntl.h bionic/libc/include/sys/cdefs.h bionic/libc/include/sys/cdefs_elf.h bionic/libc/include/sys/types.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stddef.h bionic/libc/include/stdint.h bionic/libc/include/sys/_types.h bionic/libc/arch-arm/include/machine/_types.h bionic/libc/kernel/common/linux/posix_types.h bionic/libc/kernel/common/linux/stddef.h bionic/libc/kernel/common/linux/compiler.h bionic/libc/kernel/arch-arm/asm/posix_types.h bionic/libc/kernel/arch-arm/asm/types.h bionic/libc/kernel/common/linux/types.h bionic/libc/arch-arm/include/machine/kernel.h bionic/libc/include/sys/sysmacros.h bionic/libc/kernel/common/linux/fcntl.h bionic/libc/kernel/arch-arm/asm/fcntl.h bionic/libc/kernel/common/asm-generic/fcntl.h bionic/libc/include/unistd.h bionic/libc/include/sys/select.h bionic/libc/include/sys/time.h bionic/libc/kernel/common/linux/time.h bionic/libc/include/signal.h bionic/libc/include/limits.h bionic/libc/include/sys/limits.h bionic/libc/kernel/common/linux/limits.h bionic/libc/arch-arm/include/machine/internal_types.h bionic/libc/arch-arm/include/machine/limits.h bionic/libc/include/sys/syslimits.h bionic/libc/include/string.h bionic/libc/include/malloc.h bionic/libc/kernel/arch-arm/asm/signal.h bionic/libc/kernel/common/asm-generic/signal.h bionic/libc/kernel/arch-arm/asm/siginfo.h bionic/libc/kernel/common/asm-generic/siginfo.h bionic/libc/include/sys/sysconf.h bionic/libc/kernel/common/linux/capability.h bionic/libc/include/pathconf.h bionic/libc/include/stdlib.h bionic/libc/include/alloca.h bionic/libc/include/strings.h bionic/libc/include/memory.h bionic/libc/include/stdio.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stdarg.h bionic/libc/include/pthread.h bionic/libc/include/time.h bionic/libc/include/sched.h bionic/libc/include/sys/ioctl.h bionic/libc/kernel/common/linux/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctl.h bionic/libc/kernel/common/asm-generic/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctls.h bionic/libc/kernel/arch-arm/asm/termbits.h bionic/libc/include/sys/ioctl_compat.h external/tvplay/tvrecv/HostUTIAPI.h external/tvplay/tvrecv/HostDeviceAPI.h external/tvplay/tvrecv/HostPlatForm.h external/tvplay/tvrecv/debug.h external/tvplay/tvrecv/HostCAClientAPI.h external/tvplay/tvrecv/HostAppInfoClientAPI.h external/tvplay/tvrecv/HostEPGDBClientAPI.h external/tvplay/tvrecv/HostTunerClientAPI.h external/tvplay/tvrecv/HostRgnClientAPI.h external/tvplay/tvrecv/HostSCClientAPI.h external/tvplay/tvrecv/HostDateTimeServerAPI.h | all_copied_headers
#  Implicit rule search has not been done.
#  Implicit/static pattern stem: `HostXDCServerAPI'
#  Last modified 2012-02-27 22:04:05.954410097
#  File has been updated.
#  Successfully updated.
# makefile (from `build/core/binary.mk', line 261)
# PRIVATE_ARM_MODE := thumb
# makefile (from `build/core/binary.mk', line 262)
# PRIVATE_ARM_CFLAGS := -mthumb -Os -fomit-frame-pointer -fno-strict-aliasing -finline-limit=64
# variable set hash-table stats:
# Load=2/32=6%, Rehash=0, Collisions=0/4=0%
#  commands to execute (from `build/core/binary.mk', line 268):
	$(transform-$(PRIVATE_HOST)c-to-o)
	

out/target/product/smdkv210/obj/PACKAGING/apkcerts_intermediates/full_smdkv210-apkcerts-eng.lnt.txt:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 215):
	@echo APK certs list: $@
	@mkdir -p $(dir $@)
	@rm -f $@
	$(hide) $(foreach p,$(PACKAGES),\
	$(if $(PACKAGES.$(p).EXTERNAL_KEY),\
	echo 'name="$(p).apk" certificate="EXTERNAL" \
	private_key=""' >> $@;,\
	echo 'name="$(p).apk" certificate="$(PACKAGES.$(p).CERTIFICATE)" \
	private_key="$(PACKAGES.$(p).PRIVATE_KEY)"' >> $@;))
	

out/target/product/smdkv210/system/lib/libdvbbinderclient.so: LMC1800/sources/dvb/libdvbbinderclient.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
CtsAppWithData:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/lib/libOMX.SEC.AVC.Decoder.so: LMC1800/sources/media/omx/libOMX.SEC.AVC.Decoder.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
external/quake/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:50:51
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

system/core/include/arch/linux-arm/AndroidConfig.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:53:34
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
CtsBluetoothTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
.S.s:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(PREPROCESS.S) $< > $@

# Not a target:
.mod:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(COMPILE.mod) -o $@ -e $@ $^

# Not a target:
packages/apps/Protips/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:36
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
vendor/samsung/smdkv210/device-vendor.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:44
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/full_smdkv210-symbols-eng.lnt.zip: out/target/product/smdkv210/system.img out/target/product/smdkv210/ramdisk.img
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 1161):
	@echo "Package symbols: $@"
	$(hide) rm -rf $@
	$(hide) mkdir -p $(dir $@)
	$(hide) zip -qr $@ $(TARGET_OUT_UNSTRIPPED)
	

out/target/product/smdkv210/system/tts/lang_pico/es-ES_zl0_sg.bin: external/svox/pico/lang/es-ES_zl0_sg.bin | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostXDCServerAPI.P:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  Last modified 2012-02-27 22:04:05.970410097
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
CtsHardwareTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/target/common/obj/JAVA_LIBRARIES/core-tests-dom_intermediates/javalib.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/ringtones/OrganDub.ogg: frameworks/base/data/sounds/newwavelabs/OrganDub.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
prebuilt/android-arm/kernel/LINUX_KERNEL_COPYING:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
.mod.o:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(COMPILE.mod) -o $@ $<

# Not a target:
external/svox/pico/lang/es-ES_zl0_sg.bin:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
CtsPermission2TestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/host/linux-x86/bin/emulator:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/vendor/lib/libsrv_init.so: vendor/samsung/smdkv210/proprietary/libsrv_init.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
frameworks/base/docs/docs-redirect-index.html:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
.F.f:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(PREPROCESS.F) $(OUTPUT_OPTION) $<

ramdisk: out/target/product/smdkv210/ramdisk.img
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

# Not a target:
out/host/linux-x86/framework/CtsAppSecurityTests.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/kernel/common/asm-generic/siginfo.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
vendor/samsung/smdkv210/proprietary/libEGL_POWERVR_SGX540_120.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/newwavelabs/CaffeineSnake.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
device/common/gps/gps_us_supl.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:54
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
CtsViewTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

external/tvplay/tvrecv/HostCAClientAPI.c:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/media/audio/notifications/Highwire.ogg: frameworks/base/data/sounds/newwavelabs/Highwire.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
external/jpeg/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:50:31
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

notice_files: out/target/product/smdkv210/obj/NOTICE.txt out/target/product/smdkv210/obj/NOTICE.html out/host/linux-x86/obj/NOTICE.txt out/host/linux-x86/obj/NOTICE.html
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

# Not a target:
external/svox/pico/lang/en-GB_kh0_sg.bin:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
CtsDelegatingAccessibilityService:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/newwavelabs/Growl.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/svox/pico/lang/PicoLangItItInSystem.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:34
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

.DELETE_ON_ERROR:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/installed-files.txt: out/target/product/smdkv210/system.img
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 1086):
	@echo Installed file list: $@
	@mkdir -p $(dir $@)
	@rm -f $@
	$(hide) build/tools/fileslist.py $(TARGET_OUT) $(TARGET_OUT_DATA) > $@
	

out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketSend.o: external/tvplay/tvrecv/SocketSend.c external/tvplay/tvrecv/SocketSend.c system/core/include/arch/linux-arm/AndroidConfig.h external/tvplay/tvrecv/SocketHeader.h bionic/libc/include/sys/types.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stddef.h bionic/libc/include/stdint.h bionic/libc/include/sys/_types.h bionic/libc/arch-arm/include/machine/_types.h bionic/libc/include/sys/cdefs.h bionic/libc/include/sys/cdefs_elf.h bionic/libc/kernel/common/linux/posix_types.h bionic/libc/kernel/common/linux/stddef.h bionic/libc/kernel/common/linux/compiler.h bionic/libc/kernel/arch-arm/asm/posix_types.h bionic/libc/kernel/arch-arm/asm/types.h bionic/libc/kernel/common/linux/types.h bionic/libc/arch-arm/include/machine/kernel.h bionic/libc/include/sys/sysmacros.h bionic/libc/include/sys/socket.h bionic/libc/kernel/common/linux/socket.h bionic/libc/kernel/arch-arm/asm/socket.h bionic/libc/kernel/arch-arm/asm/sockios.h bionic/libc/kernel/common/linux/sockios.h bionic/libc/kernel/common/linux/uio.h bionic/libc/include/netinet/in.h bionic/libc/arch-arm/include/endian.h bionic/libc/include/sys/endian.h bionic/libc/kernel/common/linux/in.h bionic/libc/kernel/arch-arm/asm/byteorder.h bionic/libc/kernel/common/linux/byteorder/little_endian.h bionic/libc/kernel/common/linux/byteorder/swab.h bionic/libc/kernel/common/linux/byteorder/generic.h bionic/libc/kernel/common/linux/in6.h bionic/libc/include/netinet/in6.h bionic/libc/include/arpa/inet.h bionic/libc/include/unistd.h bionic/libc/include/sys/select.h bionic/libc/include/sys/time.h bionic/libc/kernel/common/linux/time.h bionic/libc/include/signal.h bionic/libc/include/limits.h bionic/libc/include/sys/limits.h bionic/libc/kernel/common/linux/limits.h bionic/libc/arch-arm/include/machine/internal_types.h bionic/libc/arch-arm/include/machine/limits.h bionic/libc/include/sys/syslimits.h bionic/libc/include/string.h bionic/libc/include/malloc.h bionic/libc/kernel/arch-arm/asm/signal.h bionic/libc/kernel/common/asm-generic/signal.h bionic/libc/kernel/arch-arm/asm/siginfo.h bionic/libc/kernel/common/asm-generic/siginfo.h bionic/libc/include/sys/sysconf.h bionic/libc/kernel/common/linux/capability.h bionic/libc/include/pathconf.h bionic/libc/include/fcntl.h bionic/libc/kernel/common/linux/fcntl.h bionic/libc/kernel/arch-arm/asm/fcntl.h bionic/libc/kernel/common/asm-generic/fcntl.h bionic/libc/include/stdlib.h bionic/libc/include/alloca.h bionic/libc/include/strings.h bionic/libc/include/memory.h bionic/libc/include/stdio.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stdarg.h | all_copied_headers
#  Implicit rule search has not been done.
#  Implicit/static pattern stem: `SocketSend'
#  Last modified 2012-02-27 22:34:44.470357734
#  File has been updated.
#  Successfully updated.
# makefile (from `build/core/binary.mk', line 261)
# PRIVATE_ARM_MODE := thumb
# makefile (from `build/core/binary.mk', line 262)
# PRIVATE_ARM_CFLAGS := -mthumb -Os -fomit-frame-pointer -fno-strict-aliasing -finline-limit=64
# variable set hash-table stats:
# Load=2/32=6%, Rehash=0, Collisions=0/4=0%
#  commands to execute (from `build/core/binary.mk', line 268):
	$(transform-$(PRIVATE_HOST)c-to-o)
	

# Not a target:
.S.o:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(COMPILE.S) -o $@ $<

# Not a target:
build/core/version_defaults.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/kernel/common/linux/limits.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/providers/UserDictionaryProvider/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:02:00
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

external/tvplay/tvrecv/HostRgnClientAPI.c:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
build/core/executable.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/lib/liba52.so: LMC1800/sources/media/sf/liba52.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
frameworks/base/data/sounds/newwavelabs/Highwire.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostSCClientAPI.o: external/tvplay/tvrecv/HostSCClientAPI.c external/tvplay/tvrecv/HostSCClientAPI.c system/core/include/arch/linux-arm/AndroidConfig.h external/tvplay/tvrecv/HostIncludeApi.h bionic/libc/include/fcntl.h bionic/libc/include/sys/cdefs.h bionic/libc/include/sys/cdefs_elf.h bionic/libc/include/sys/types.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stddef.h bionic/libc/include/stdint.h bionic/libc/include/sys/_types.h bionic/libc/arch-arm/include/machine/_types.h bionic/libc/kernel/common/linux/posix_types.h bionic/libc/kernel/common/linux/stddef.h bionic/libc/kernel/common/linux/compiler.h bionic/libc/kernel/arch-arm/asm/posix_types.h bionic/libc/kernel/arch-arm/asm/types.h bionic/libc/kernel/common/linux/types.h bionic/libc/arch-arm/include/machine/kernel.h bionic/libc/include/sys/sysmacros.h bionic/libc/kernel/common/linux/fcntl.h bionic/libc/kernel/arch-arm/asm/fcntl.h bionic/libc/kernel/common/asm-generic/fcntl.h bionic/libc/include/unistd.h bionic/libc/include/sys/select.h bionic/libc/include/sys/time.h bionic/libc/kernel/common/linux/time.h bionic/libc/include/signal.h bionic/libc/include/limits.h bionic/libc/include/sys/limits.h bionic/libc/kernel/common/linux/limits.h bionic/libc/arch-arm/include/machine/internal_types.h bionic/libc/arch-arm/include/machine/limits.h bionic/libc/include/sys/syslimits.h bionic/libc/include/string.h bionic/libc/include/malloc.h bionic/libc/kernel/arch-arm/asm/signal.h bionic/libc/kernel/common/asm-generic/signal.h bionic/libc/kernel/arch-arm/asm/siginfo.h bionic/libc/kernel/common/asm-generic/siginfo.h bionic/libc/include/sys/sysconf.h bionic/libc/kernel/common/linux/capability.h bionic/libc/include/pathconf.h bionic/libc/include/stdlib.h bionic/libc/include/alloca.h bionic/libc/include/strings.h bionic/libc/include/memory.h bionic/libc/include/stdio.h prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stdarg.h bionic/libc/include/pthread.h bionic/libc/include/time.h bionic/libc/include/sched.h bionic/libc/include/sys/ioctl.h bionic/libc/kernel/common/linux/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctl.h bionic/libc/kernel/common/asm-generic/ioctl.h bionic/libc/kernel/arch-arm/asm/ioctls.h bionic/libc/kernel/arch-arm/asm/termbits.h bionic/libc/include/sys/ioctl_compat.h external/tvplay/tvrecv/HostUTIAPI.h external/tvplay/tvrecv/HostDeviceAPI.h external/tvplay/tvrecv/HostPlatForm.h external/tvplay/tvrecv/debug.h external/tvplay/tvrecv/HostCAClientAPI.h external/tvplay/tvrecv/HostAppInfoClientAPI.h external/tvplay/tvrecv/HostEPGDBClientAPI.h external/tvplay/tvrecv/HostTunerClientAPI.h external/tvplay/tvrecv/HostRgnClientAPI.h external/tvplay/tvrecv/HostSCClientAPI.h external/tvplay/tvrecv/HostDateTimeServerAPI.h | all_copied_headers
#  Implicit rule search has not been done.
#  Implicit/static pattern stem: `HostSCClientAPI'
#  Last modified 2012-02-27 22:04:05.782410102
#  File has been updated.
#  Successfully updated.
# makefile (from `build/core/binary.mk', line 261)
# PRIVATE_ARM_MODE := thumb
# makefile (from `build/core/binary.mk', line 262)
# PRIVATE_ARM_CFLAGS := -mthumb -Os -fomit-frame-pointer -fno-strict-aliasing -finline-limit=64
# variable set hash-table stats:
# Load=2/32=6%, Rehash=0, Collisions=0/4=0%
#  commands to execute (from `build/core/binary.mk', line 268):
	$(transform-$(PRIVATE_HOST)c-to-o)
	

# Not a target:
cts/tools/utils/buildCts.py:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/freetype/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:48:29
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

prebuilt:
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

# Not a target:
out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketSend.P:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  Last modified 2012-02-27 22:34:44.474357734
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/newwavelabs/BirdLoop.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/libpng/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:53:27
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/dex_bootjars/system/framework/bouncycastle_nodex.jar: out/target/common/obj/JAVA_LIBRARIES/bouncycastle_intermediates/javalib.jar | out/host/linux-x86/bin/acp out/host/linux-x86/bin/aapt
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/dex_preopt.mk', line 66):
	$(call copy-file-to-target)
	$(call dexpreopt-remove-classes.dex,$@)
	

bionic/libc/include/pthread.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

tests-zip-package: out/target/product/smdkv210/obj/PACKAGING/tests_zip_intermediates/full_smdkv210-tests-eng.lnt.zip
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

out/target/product/smdkv210/userdata.img: out/host/linux-x86/bin/mkyaffs2image
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 821):
	$(build-userdataimage-target)
	

# Not a target:
build/core/Makefile:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
build/core/envsetup.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
CtsWebkitTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/ppp/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:37
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/apps/SpeechRecorder/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:31
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/kernel/common/linux/in.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/lib/libSEC_OMX_Core.so: LMC1800/sources/media/omx/libSEC_OMX_Core.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

sdk: out/host/linux-x86/sdk/android-sdk_eng.lnt_linux-x86.zip
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

out/target/product/smdkv210/system/vendor/lib/libpvr2d.so: vendor/samsung/smdkv210/proprietary/libpvr2d.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
CtsPerformanceTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/Ring_Synth_04.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/media/audio/ringtones/MildlyAlarming.ogg: frameworks/base/data/sounds/newwavelabs/MildlyAlarming.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
external/svox/pico/lang/en-GB_ta.bin:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/svox/pico/lang/PicoLangEnUsInSystem.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:34
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
build/target/product/core.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

dalvikfiles:
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/LINKED/tvrecv: out/target/product/smdkv210/obj/lib/crtbegin_dynamic.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/debug.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostAppInfoClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostCAClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDateTimeServerAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDeviceAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostEPGDBClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostPlatForm.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostRgnClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostSCClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostTunerClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostXDCServerAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/main.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketRecv.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketSend.o out/target/product/smdkv210/obj/lib/libc.so out/target/product/smdkv210/obj/lib/libstdc++.so out/target/product/smdkv210/obj/lib/libm.so out/target/product/smdkv210/obj/lib/crtend_android.o
#  Implicit rule search has not been done.
#  Implicit/static pattern stem: `'
#  Last modified 2012-03-01 19:20:58.295885594
#  File has been updated.
#  Successfully updated.
# automatic
# @ := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/LINKED/tvrecv
# makefile (from `build/core/binary.mk', line 496)
# PRIVATE_ALL_OBJECTS :=         out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/debug.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostAppInfoClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostCAClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDateTimeServerAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDeviceAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostEPGDBClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostPlatForm.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostRgnClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostSCClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostTunerClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostXDCServerAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/main.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketRecv.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketSend.o    
# makefile (from `build/core/binary.mk', line 489)
# PRIVATE_LDLIBS := external/tvplay/tvrecv/libutihost.android.a 
# makefile (from `build/core/binary.mk', line 493)
# PRIVATE_ALL_SHARED_LIBRARIES := out/target/product/smdkv210/obj/lib/libc.so out/target/product/smdkv210/obj/lib/libstdc++.so out/target/product/smdkv210/obj/lib/libm.so
# makefile (from `build/core/binary.mk', line 66)
# PRIVATE_TARGET_C_INCLUDES := bionic/libc/arch-arm/include bionic/libc/include bionic/libstdc++/include bionic/libc/kernel/common bionic/libc/kernel/arch-arm bionic/libm/include bionic/libm/include/arch/arm bionic/libthread_db/include
# automatic
# % := 
# makefile (from `build/core/binary.mk', line 84)
# PRIVATE_CXX := prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/arm-eabi-g++
# makefile (from `build/core/binary.mk', line 483)
# PRIVATE_ASFLAGS := 
# makefile (from `build/core/binary.mk', line 79)
# PRIVATE_CC := prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/arm-eabi-gcc
# makefile (from `build/core/base_rules.mk', line 449)
# PRIVATE_INTERMEDIATES_DIR := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates
# makefile (from `build/core/binary.mk', line 495)
# PRIVATE_ALL_WHOLE_STATIC_LIBRARIES := 
# makefile (from `build/core/base_rules.mk', line 438)
# PRIVATE_PATH := external/tvplay/tvrecv
# makefile (from `build/core/binary.mk', line 73)
# PRIVATE_NO_DEFAULT_COMPILER_FLAGS := 
# automatic
# * := 
# automatic
# + := out/target/product/smdkv210/obj/lib/crtbegin_dynamic.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/debug.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostAppInfoClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostCAClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDateTimeServerAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDeviceAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostEPGDBClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostPlatForm.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostRgnClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostSCClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostTunerClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostXDCServerAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/main.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketRecv.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketSend.o out/target/product/smdkv210/obj/lib/libc.so out/target/product/smdkv210/obj/lib/libstdc++.so out/target/product/smdkv210/obj/lib/libm.so out/target/product/smdkv210/obj/lib/crtend_android.o
# makefile (from `build/core/binary.mk', line 484)
# PRIVATE_CFLAGS := $(subst ,, )
# makefile (from `build/core/binary.mk', line 485)
# PRIVATE_CPPFLAGS := $(subst ,, )
# makefile (from `build/core/binary.mk', line 486)
# PRIVATE_DEBUG_CFLAGS := 
# automatic
# ^ := out/target/product/smdkv210/obj/lib/crtbegin_dynamic.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/debug.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostAppInfoClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostCAClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDateTimeServerAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDeviceAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostEPGDBClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostPlatForm.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostRgnClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostSCClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostTunerClientAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostXDCServerAPI.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/main.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketRecv.o out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/SocketSend.o out/target/product/smdkv210/obj/lib/libc.so out/target/product/smdkv210/obj/lib/libstdc++.so out/target/product/smdkv210/obj/lib/libm.so out/target/product/smdkv210/obj/lib/crtend_android.o
# makefile (from `build/core/binary.mk', line 488)
# PRIVATE_LDFLAGS :=  external/tvplay/tvrecv/libutihost.android.a    -Wl,--no-undefined
# makefile (from `build/core/base_rules.mk', line 440)
# PRIVATE_AAPT_FLAGS := 
# makefile (from `build/core/base_rules.mk', line 446)
# PRIVATE_IS_HOST_MODULE := 
# makefile (from `build/core/binary.mk', line 67)
# PRIVATE_TARGET_GLOBAL_CFLAGS := -fno-exceptions -Wno-multichar -msoft-float -fpic -ffunction-sections -funwind-tables -fstack-protector -Wa,--noexecstack -Werror=format-security -fno-short-enums -march=armv7-a -mfloat-abi=softfp -mfpu=neon -include system/core/include/arch/linux-arm/AndroidConfig.h -I system/core/include/arch/linux-arm/ -Wno-psabi -mthumb-interwork -DANDROID -fmessage-length=0 -W -Wall -Wno-unused -Winit-self -Wpointer-arith -Werror=return-type -Werror=non-virtual-dtor -Werror=address -Werror=sequence-point -DNDEBUG -g -Wstrict-aliasing=2 -finline-functions -fno-inline-functions-called-once -fgcse-after-reload -frerun-cse-after-loop -frename-registers -DNDEBUG -UDEBUG
# makefile (from `build/core/base_rules.mk', line 443)
# PRIVATE_MANIFEST_INSTRUMENTATION_FOR := 
# makefile (from `build/core/binary.mk', line 91)
# PRIVATE_CPP_EXTENSION := .cpp
# makefile (from `build/core/base_rules.mk', line 445)
# PRIVATE_ALL_JAVA_LIBRARIES := 
# makefile (from `build/core/binary.mk', line 494)
# PRIVATE_ALL_STATIC_LIBRARIES := 
# makefile (from `build/core/base_rules.mk', line 441)
# PRIVATE_JAVA_LIBRARIES := 
# makefile (from `build/core/base_rules.mk', line 442)
# PRIVATE_MANIFEST_PACKAGE_NAME := 
# makefile (from `build/core/binary.mk', line 487)
# PRIVATE_C_INCLUDES :=  external/tvplay/tvrecv out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates  dalvik/libnativehelper/include/nativehelper
# makefile (from `build/core/base_rules.mk', line 439)
# PRIVATE_POST_PROCESS_COMMAND := true
# makefile (from `build/core/binary.mk', line 482)
# PRIVATE_YACCFLAGS := 
# makefile (from `build/core/binary.mk', line 68)
# PRIVATE_TARGET_GLOBAL_CPPFLAGS := -fvisibility-inlines-hidden -DANDROID -fmessage-length=0 -W -Wall -Wno-unused -Winit-self -Wpointer-arith -Wsign-promo -Werror=return-type -Werror=non-virtual-dtor -Werror=address -Werror=sequence-point -DNDEBUG -UDEBUG
# makefile (from `build/core/base_rules.mk', line 447)
# PRIVATE_HOST := 
# automatic
# | := 
# makefile (from `build/core/binary.mk', line 65)
# PRIVATE_TARGET_PROJECT_INCLUDES := system/core/include hardware/libhardware/include hardware/libhardware_legacy/include hardware/ril/include dalvik/libnativehelper/include frameworks/base/include frameworks/base/opengl/include frameworks/base/native/include external/skia/include out/target/product/smdkv210/obj/include
# makefile (from `build/core/base_rules.mk', line 452)
# PRIVATE_MODULE := tvrecv
# automatic
# < := out/target/product/smdkv210/obj/lib/crtbegin_dynamic.o
# automatic
# ? := out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/main.o
# variable set hash-table stats:
# Load=39/64=61%, Rehash=1, Collisions=202/164=123%
#  commands to execute (from `build/core/executable.mk', line 27):
	$(transform-o-to-executable)
	

# Not a target:
device/samsung/product/full_smdkv210.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:54
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/media/audio/ringtones/NewPlayer.ogg: frameworks/base/data/sounds/newwavelabs/NewPlayer.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
packages/providers/DownloadProvider/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:02:01
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/ex/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:47:29
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/include/sys/sysconf.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/etc/apns-conf.xml: development/data/etc/apns-conf.xml | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
build/core/cleanspec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

systemimage: out/target/product/smdkv210/system.img
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

systemtarball: out/target/product/smdkv210/system.tar.bz2
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has not been updated.

# Not a target:
frameworks/base/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:47:28
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
SignatureTest:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/dex_bootjars/system/framework/core-junit_nodex.jar: out/target/common/obj/JAVA_LIBRARIES/core-junit_intermediates/javalib.jar | out/host/linux-x86/bin/acp out/host/linux-x86/bin/aapt
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/dex_preopt.mk', line 66):
	$(call copy-file-to-target)
	$(call dexpreopt-remove-classes.dex,$@)
	

bionic/libc/include/netinet/in.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

prebuilt/linux-x86/toolchain/arm-eabi-4.4.3/bin/../lib/gcc/arm-eabi/4.4.3/include/stddef.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:53:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/kernel/arch-arm/asm/termbits.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
packages/apps/Calendar/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:38
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/grub/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:53:07
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
device/htc/passion-common/passion.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:59
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/etc/NOTICE.html.gz: out/target/product/smdkv210/obj/NOTICE.html.gz | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 514):
	$(copy-file-to-target)
	

out/target/product/smdkv210/system/media/audio/alarms/Alarm_Buzzer.ogg: frameworks/base/data/sounds/Alarm_Buzzer.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
out/target/product/smdkv210/obj/EXECUTABLES/tvrecv_intermediates/HostDeviceAPI.P:
#  A default, MAKEFILES, or -include/sinclude makefile.
#  Implicit rule search has been done.
#  Last modified 2012-02-27 22:04:05.398410113
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/libgsm/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:46
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

external/tvplay/tvrecv/HostXDCServerAPI.c:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 15:41:55
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/svox/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:36
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/kernel/common/asm-generic/fcntl.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/api/9.xml:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
out/host/linux-x86/bin/zipalign:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/dropbear/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:14
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
.F:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(LINK.F) $^ $(LOADLIBES) $(LDLIBS) -o $@

# Not a target:
packages/apps/Email/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:28
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

all_copied_headers:
#  Phony target (prerequisite of .PHONY).
#  Implicit rule search has not been done.
#  File does not exist.
#  File has been updated.
#  Successfully updated.
#  commands to execute (from `build/core/main.mk', line 657):
	

out/target/product/smdkv210/system/media/audio/ringtones/BeatPlucker.ogg: frameworks/base/data/sounds/newwavelabs/BeatPlucker.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
vendor/samsung/smdkv210/proprietary/libsrv_init.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
device/samsung/smdkv210/BoardConfig.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:58
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/media/audio/notifications/OnTheHunt.ogg: frameworks/base/data/sounds/newwavelabs/OnTheHunt.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
out/target/product/smdkv210/system/lib/libstdc++.so:
#  Implicit rule search has been done.
#  Last modified 2012-02-17 11:47:12
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
build/core/dynamic_binary.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:53
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/host/linux-x86/cts/android-cts.zip: out/host/linux-x86/cts/all_cts_files_stamp /resource/plans out/host/linux-x86/cts/android-cts/repository/testcases/android.core.vm-tests
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
# makefile (from `build/core/tasks/cts.mk', line 165)
# PRIVATE_DIR := out/host/linux-x86/cts/android-cts
# makefile (from `build/core/tasks/cts.mk', line 164)
# PRIVATE_CTS_DIR := out/host/linux-x86/cts
# makefile (from `build/core/tasks/cts.mk', line 166)
# TMP_DIR := out/host/linux-x86/cts/temp
# makefile (from `build/core/tasks/cts.mk', line 163)
# PRIVATE_NAME := android-cts
# variable set hash-table stats:
# Load=4/32=12%, Rehash=0, Collisions=0/9=0%
#  commands to execute (from `build/core/tasks/cts.mk', line 168):
	@echo "Package CTS: $@"
	$(hide) cd $(dir $@) && zip -rq $(notdir $@) $(PRIVATE_NAME)
	

# Not a target:
out/target/product/smdkv210/obj/EXECUTABLES/applypatch_intermediates/applypatch:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
development/build/sdk.atree:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/emma_meta.zip: out/target/product/smdkv210/system.img
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 1190):
	@echo "Collecting Emma coverage meta files."
	$(hide) find $(TARGET_COMMON_OUT_ROOT) -name "coverage.em" | \
	zip -@ -q $@
	

bionic/libc/include/sys/syslimits.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:04
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
CtsDpiTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

bionic/libc/kernel/arch-arm/asm/ioctls.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
out/target/common/obj/JAVA_LIBRARIES/core-tests_intermediates/javalib.jar:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
system/wlan/ti/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:53:33
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/media/audio/ringtones/Growl.ogg: frameworks/base/data/sounds/newwavelabs/Growl.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
frameworks/base/data/sounds/effects/KeypressStandard.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
external/libxml2/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:50:52
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/media/audio/ringtones/DreamTheme.ogg: frameworks/base/data/sounds/newwavelabs/DreamTheme.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
frameworks/base/data/sounds/notifications/Beat_Box_Android.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/lib/libstagefright_omx.so: LMC1800/sources/media/sf/libstagefright_omx.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
system/netd/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:53:35
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/kernel/common/linux/types.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/dex_bootjars/system/framework/core_nodex.jar: out/target/common/obj/JAVA_LIBRARIES/core_intermediates/javalib.jar | out/host/linux-x86/bin/acp out/host/linux-x86/bin/aapt
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/dex_preopt.mk', line 66):
	$(call copy-file-to-target)
	$(call dexpreopt-remove-classes.dex,$@)
	

bionic/libc/kernel/common/linux/posix_types.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/lib/libdvbcomm.so: LMC1800/sources/dvb/libdvbcomm.so | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
.f:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(LINK.f) $^ $(LOADLIBES) $(LDLIBS) -o $@

bionic/libc/kernel/common/linux/time.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/OriginalAudio.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:47:13
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/giflib/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:12
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
external/oauth/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:00
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

bionic/libc/kernel/common/linux/byteorder/little_endian.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
.f.o:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (built-in):
	$(COMPILE.f) $(OUTPUT_OPTION) $<

bionic/libc/kernel/common/linux/socket.h:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:03
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

external/tvplay/tvrecv/SocketSend.c:
#  Implicit rule search has been done.
#  Last modified 2012-02-27 22:34:35.038358003
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
device/common/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:00:54
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
frameworks/base/data/sounds/F1_New_SMS.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/system/tts/lang_pico/en-US_ta.bin: external/svox/pico/lang/en-US_ta.bin | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
external/iproute2/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:52:46
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/media/audio/ringtones/Ring_Classic_02.ogg: frameworks/base/data/sounds/Ring_Classic_02.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

out/target/product/smdkv210/system/media/audio/notifications/KzurbSonar.ogg: frameworks/base/data/sounds/newwavelabs/KzurbSonar.ogg | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
ndk/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:17
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# Not a target:
LMC1800/sources/media/omx/libSEC_OMX_Core.so:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
CtsUtilTestCases:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

# Not a target:
frameworks/base/data/sounds/newwavelabs/TwirlAway.ogg:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.

out/target/product/smdkv210/findbugs.xml:
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 1352):
	@echo UnionBugs: $@
	$(hide) prebuilt/common/findbugs/bin/unionBugs $(ALL_FINDBUGS_FILES) \
	> $@
	

# Not a target:
packages/apps/Browser/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 10:01:23
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

out/target/product/smdkv210/system/etc/vold.conf: development/data/etc/vold.conf | out/host/linux-x86/bin/acp
#  Implicit rule search has not been done.
#  Modification time never checked.
#  File has not been updated.
#  commands to execute (from `build/core/Makefile', line 19):
	@echo "Copy: $@"
	$(copy-file-to-target)
	

# Not a target:
external/openssl/CleanSpec.mk:
#  Implicit rule search has been done.
#  Last modified 2012-02-15 09:51:07
#  File has been updated.
#  Successfully updated.
# variable set hash-table stats:
# Load=0/32=0%, Rehash=0, Collisions=0/0=0%

# files hash-table stats:
# Load=921/1024=90%, Rehash=0, Collisions=20941/6808=308%
# VPATH Search Paths

# No `vpath' search paths.

# No general (`VPATH' variable) search path.

# # of strings in strcache: 254
# # of strcache buffers: 3
# strcache size: total = 12288 / max = 4096 / min = 4096 / avg = 4096
# strcache free: total = 3370 / max = 3363 / min = 1 / avg = 1123

# Finished Make data base on Thu Mar  1 19:20:58 2012

# make: Leaving directory `/home/lnt/stb'
