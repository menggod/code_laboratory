#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_cn_mf_codelaboratory_MainActivity_getTingYunKey(
        JNIEnv *env,
        jobject /* this */) {
    std::string tingyunKey = "1f8cc58c8d12b12d";
    return env->NewStringUTF(tingyunKey.c_str());
}
