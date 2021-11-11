#include "./main.h"
#include "./view/src/pixel.h"

#include <iostream>

JNIEXPORT jobject JNICALL Java_main_pixel
	(JNIEnv *env, jobject thisObject) {
		main();
	}

