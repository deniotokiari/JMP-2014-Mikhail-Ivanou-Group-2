#include <jni.h>
#include <string.h>
#include <map.h>

map<jstring, jobject> rep;
jobject Java_com_epam_jmp_memorymanagement_SampleActivity_getFromCacheJNI( JNIEnv* env, jobject obj, jstring key)
{
    return rep.get(key);
}

void Java_com_epam_jmp_memorymanagement_SampleActivity_putToCacheJNI( JNIEnv* env, jobject obj, jstring key, jstring  str)
{   
    rep.insert(pair<jstring, jobject>(key, jniBmp));
}
