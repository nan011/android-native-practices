#include <jni.h>
#include <cstring>

void swap(char *elem1, char *elem2) {
    char *temp = elem1;
    elem1 = elem2;
    elem2 = temp;
}

char* reverseConstString(char const* str)
{
    // find length of string
    int n = strlen(str);

    // create a dynamic pointer char array
    char *rev = new char[n+1];

    // copy of string to ptr array
    strcpy(rev, str);

    // Swap character starting from two
    // corners
    for (int i=0, j=n-1; i<j; i++,j--)
        swap(&rev[i], &rev[j]);

    // return pointer of the reversed string
    return rev;
}

extern "C" JNIEXPORT jstring JNICALL
Java_id_ui_ac_cs_mobileprogramming_nandhikaprayoga_activities_Lab2Activity_00024Companion_reverse(
        JNIEnv *env,
        jobject instance,
        jstring content) {
    const char *cont = env->GetStringUTFChars( content, nullptr );
    return env->NewStringUTF(reverseConstString(cont));
}