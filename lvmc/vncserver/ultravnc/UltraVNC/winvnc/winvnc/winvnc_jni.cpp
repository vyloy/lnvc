#include "winvnc.h"
#include "winvnc_jni.h"

static JavaVM* gs_jvm=NULL;
char *jitoa(int val, char * dst_buf) {
	sprintf(dst_buf, "%d", val);
	return dst_buf;
}

extern "C" JNIEXPORT jint JNICALL Java_com_lorent_util_CSUtil_init(JNIEnv* env,jobject thiz,jobject obj){

	printf("init\n");
    env->GetJavaVM(&gs_jvm);

    return 0;
}

extern "C" JNIEXPORT jobjectArray JNICALL Java_com_lorent_util_CSUtil_getConnectList(JNIEnv *env, jobject thiz){

}

extern "C" JNIEXPORT jobjectArray JNICALL Java_com_lorent_util_CSUtil_getInCallList(JNIEnv *env, jobject thiz){

}