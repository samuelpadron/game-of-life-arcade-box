#include "ExampleObjectsJNI.h"
#include <iostream>

JNIEXPORT jobject JNICALL Java_ExampleObjectsJNI_createUser
	(JNIEnv *env, jobject thisObject, jstring name, jdouble balance) {
		jclass userDataClass = env->FindClass("UserData");
		jobject newUserData = env->AllocObject(userDataClass);

		//GetFieldID(class, attr-name, attr-type)
		jfieldID nameField = env->GetFieldID(userDataClass, "name", "Ljava/lang/String;");
		jfieldID balanceField = env->GetFieldID(userDataClass, "balance", "D");

		//set vals of objects, arrow operator (->) is the same as (.) but when u have ptr to it
		env->SetObjectField(newUserData, nameField, name);
		env->SetDoubleField(newUserData, balanceField, balance);

		//return created object
		return newUserData;
	}

JNIEXPORT jstring JNICALL Java_ExampleObjectsJNI_printUserData 
	(JNIEnv *env, jobject thisObject, jobject userData) {
	
		//find class method id
		jclass userDataClass = env->GetObjectClass(userData);
		jmethodID methodId = env->GetMethodID(userDataClass, "getUserInfo", "()Ljava/lang/String;");

		//call obj method and get result
		jstring result = (jstring)env->CallObjectMethod(userData, methodId);

		std::cout << "C++: User data is: " << env->GetStringUTFChars(result, NULL) << std::endl;

		return result;
	}
