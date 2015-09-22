/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class suskun_nn_QuantizedDnn */

#ifndef _Included_suskun_nn_QuantizedDnn
#define _Included_suskun_nn_QuantizedDnn
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     suskun_nn_QuantizedDnn
 * Method:    initialize
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_suskun_nn_QuantizedDnn_initialize
  (JNIEnv *, jobject, jstring);

/*
 * Class:     suskun_nn_QuantizedDnn
 * Method:    getContext
 * Signature: (III)J
 */
JNIEXPORT jlong JNICALL Java_suskun_nn_QuantizedDnn_getContext
  (JNIEnv *, jobject, jint, jint, jint);

/*
 * Class:     suskun_nn_QuantizedDnn
 * Method:    calculateUntilOutput
 * Signature: (J[F)V
 */
JNIEXPORT void JNICALL Java_suskun_nn_QuantizedDnn_calculateUntilOutput
  (JNIEnv *, jobject, jlong, jfloatArray);

/*
 * Class:     suskun_nn_QuantizedDnn
 * Method:    calculateForOutputs
 * Signature: (JI[I)[F
 */
JNIEXPORT jfloatArray JNICALL Java_suskun_nn_QuantizedDnn_calculateForOutputs
  (JNIEnv *, jobject, jlong, jint, jintArray);

/*
 * Class:     suskun_nn_QuantizedDnn
 * Method:    calculate
 * Signature: ([FIII)[F
 */
JNIEXPORT jfloatArray JNICALL Java_suskun_nn_QuantizedDnn_calculate
  (JNIEnv *, jobject, jfloatArray, jint, jint, jint);

/*
 * Class:     suskun_nn_QuantizedDnn
 * Method:    inputDimension
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_suskun_nn_QuantizedDnn_inputDimension
  (JNIEnv *, jobject);

/*
 * Class:     suskun_nn_QuantizedDnn
 * Method:    outputDimension
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_suskun_nn_QuantizedDnn_outputDimension
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif