/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
#include <sstream>
#include <iostream>
#include <fstream>
#include <opencv2/opencv.hpp>
#include "opencv2/imgproc/imgproc_c.h"
#include "opencv2/objdetect/objdetect.hpp"
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/core/core.hpp"
#include "opencv2/contrib/contrib.hpp"
using namespace cv;
using namespace std;
#ifndef _Included_com_example_app_yarabisahl_OpencvClass
#define _Included_com_example_app_yarabisahl_OpencvClass



#ifdef __cplusplus
extern "C"
{
#endif
int a1=-1,a2=-1,a3=-1,a4=-1,a5=-1,a6=-1,a7=-1;
String NameX = "Inconnu";
char * path = "/storage/emulated/0/Files/Trainer.txt";
int imgi=1;
int numberface=0;
int *T;
int k=0;
String Name = "Inconnu";
int label=-1;
String chemin;
String loadFromFile();
void AddSomeOne();
String tostring(int);
void detect(Mat& frame);
void FileWrite(char *,String);
void fisherFaceTrainer();
void  FaceRecognition(Mat&);
String DetectName(int);
int* LesIds();
int getLab1();
int getLab2();
int getLab3();
int getLab4();
int getLab5();
int getLab6();
int getLab7();
static void dbread(const string& , vector<Mat>& , vector<int>& ,vector<String>&, char);
JNIEXPORT void JNICALL Java_com_example_app_yarabisahl_OpencvClass_faceDetection
(JNIEnv *, jclass, jlong);
JNIEXPORT void JNICALL Java_com_example_app_yarabisahl_OpencvClass_fisher
(JNIEnv *, jclass, jlong );
JNIEXPORT void JNICALL
Java_com_example_app_yarabisahl_OpencvClass_AddNewPerson(JNIEnv *env, jclass type);

JNIEXPORT void JNICALL
Java_com_example_app_yarabisahl_OpencvClass_RecogniseFace(JNIEnv *env, jclass type,jlong);

JNIEXPORT jint JNICALL
Java_com_example_app_yarabisahl_OpencvClass_lab1(JNIEnv *env, jclass type);

JNIEXPORT jint JNICALL
        Java_com_example_app_yarabisahl_OpencvClass_lab2(JNIEnv *env, jclass type);
JNIEXPORT jint JNICALL
        Java_com_example_app_yarabisahl_OpencvClass_lab3(JNIEnv *env, jclass type);
JNIEXPORT jint JNICALL
        Java_com_example_app_yarabisahl_OpencvClass_lab4(JNIEnv *env, jclass type);
JNIEXPORT jint JNICALL
        Java_com_example_app_yarabisahl_OpencvClass_lab5(JNIEnv *env, jclass type);
JNIEXPORT jint JNICALL
        Java_com_example_app_yarabisahl_OpencvClass_lab6(JNIEnv *env, jclass type);
JNIEXPORT jint JNICALL
        Java_com_example_app_yarabisahl_OpencvClass_lab7(JNIEnv *env, jclass type);
#ifdef __cplusplus
}
#endif
#endif