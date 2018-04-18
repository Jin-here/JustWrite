## JNI官方文档
详细文档：http://docs.oracle.com/javase/7/docs/technotes/guides/jni/spec/jniTOC.html

## 类型
#### 映射
|Java Language Type|        Native Type|        Description|
|-|-|-|
|boolean|                   jboolean|           unsigned 8 bits|
|byte|                      jbyte|              signed 8 bits|
|char|                      jchar|              unsigned 16 bits|
|short|                     jshort|             signed 16 bits|
|int|                       jint|               singed 32 bits|
|long|                      jlong|              signed 64 bits|
|float|                     jfloat|             32 bits|
|double|                    jdouble|            64 bits|
|Class|                     jclass|
|Object|                    jobject|
|String|                    jstring|
|Object[]|                  jobjectArray|
|boolean[]|                 jbooleanArray|
|byte[]|                    jbyteArray|
|char[]|                    jcharArray|
|short[]|                   jshortArray|
|int[]|                     jintArray|
|long[]|                    jlongArray|
|float[]|                   jfloatArray|
|double[]|                  jdoubleArray|
|Throwable|                 jthrowable|

#### JNI基本类型定义
```c
typedef unsigned char  jboolean;
typedef unsigned short jchar;
typedef short          jshort;
typedef float          jfloat;
typedef double         jdouble;
typedef int            jint;
#ifdef _LP64 /* 64-bit Solaris */
typedef long           jlong;
#else
typedef long long      jlong;
#endif

typedef signed char    jbyte;
```

## 字符串处理
#### Get/ReleaseStringUTFChars
```java
JNIEXPORT jstring JNICALL_com_study_sample_fun1
(JNIEnv *env, jclass cls, jstring j_str) {
    char buffer[128] = {0};
    // 返回JNI_TRUE表示返回原字符串的拷贝，返回JNI_FALSE表示返回原字符串的指针
    // 开发过程中，并不关心这个值是多少，通常情况下填NULL即可
    jboolean isCopy;
    // Java默认使用Unicode编码，而C/C++默认使用UTF编码，所以本地代码操作字符串时，
    // 必须使用合适的JNI函数进行转换
    const char* c_str = env->GetStringUTFChars(j_str, &isCopy);
    printf("isCopy:%d\n", isCopy);
    // 因为可能会进行内存分配工作，当内存空间不够时，会导致调用失败，返回NULL，
    // 并抛出OutOfMemoryError异常。JNI异常和Java异常处理流程不一样，Java遇到异常如果
    // 没有捕获，程序会立即停止，而JNI则不会改变程序的运行流程，继续往下走
    if (c_str == NULL) {
        return NULL;
    }
    printf("c_str: %s", c_str);
    sprintf(buff, "hello %s", c_str);
    // 释放已分配的内存空间
    env->ReleaseStringUTFChars(j_str, c_str);
    return env->NewStringUTF(buff);
}
```

#### Get/ReleaseStringChars
用于获取和释放Unicode格式编码的字符串，而Get/ReleaseStringUTFChar用于获取和释放以UTF-8编码的字符串

#### GetStringLength
- 签名：jsize GetStringLength(JNIEnv *env, jstring string);
- 由于UTF-8编码的字符串以'\0'结尾，而Unicode字符串不是，如果要获取一个指向Unicode编码的jstring字符串的长度，
可通过该函数获取

#### GetStringUTFLength
- 签名：jsize GetStringUTFLength(JNIEnv *env, jstring string);
- 获取UTF-8编码字符串的长度，也可以通过标准C函数strlen获取

#### Get/ReleaseStringCritical
提高JVM返回原字符串直接指针的可能性，需要注意到是，在Get和Release之间的代码 must not issue arbitrary JNI calls,
or cause the current thread to block

#### GetStringRegion/getStringUTFRegion
- 签名：void GetStringRegion(JNIEnv *env, jstring str, jsize start, jsize len, jchar *buf);
- 异常：StringIndexOutOfBoundsException
Copies "len" number of Unicode/UTF-8 characters beginning at offset "start" to the given buffer "buf"。
GetStringUTFRegion会做越界检查，抛出StringIndexOutOfBoundsException，这个方法与GetStringUTFChars相似，不同的是，
GetStringUTFRegion内部不分配内存，不会抛出内存溢出异常，所以没有提供Release函数

## 数组
#### Get/Set<PrimitiveType>ArrayRegion
- 签名：void Get<PrimitiveType>ArrayRegion(JNIEnv *env, ArrayType array,
jsize start, jsize len, NativeType *buf);
- A family of functions that copies a region of a primitive array into a buffer
- 签名：void Set<PrimitiveType>ArrayRegion(JNIEnv *env, ArrayType array,
jsize start, jsize len, const NativeType *buf);
- A family of functions that copies back a region of a primitive array from a buffer

#### Get/Release<PrimitiveType>ArrayElements
- 签名：NativeType *Get<PrimitiveType>ArrayElements(JNIEnv *env,
ArrayType array, jboolean *isCopy);
- A family of functions that returns the body of the primitive array. The result is valid until the corresponding 
Release<PrimitiveTuype>ArrayElements() function is called. Since the returned array may be a coyp of the Java array,
changes made to the returned array will not necessarily be reflected in the original array until 
Release<PrimitiveType>ArrayElements() is called.
- 签名：void Release<PrimitiveType>ArrayElements(JNIEnv *env,
ArrayType array, NativeType *elems, jint mode);

#### Get/ReleasePrimitiveArrayCritical
- 签名：
  - void * GetPrimitiveArrayCritical(JNIEnv *env, jarray array, jboolean *isCopy);
  - void ReleasePrimitiveArrayCritical(JNIEnv *env, jarray array, void *carray, jint mode);
- The semantics of these two functions are very similar to the existing Get/Release<PrimitiveType>ArrayElements functions.
If possible, the VM returns a pointer to the primitive array; otherwise, a copy is made.
Multiple pairs of GetPrimtiveArrayCritical and ReleasePrimitiveArrayCritical may be nested.
