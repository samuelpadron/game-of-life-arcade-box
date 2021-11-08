#include "HelloWorldJNI.h"
#include <iostream>

JNIEXPORT void JNICALL Java_HelloWorldJNI_sayHello
	(JNIEnv* env, jobject thisObject) {
	std::cout << "Hello from C++ :D!" << std::endl;
	}
