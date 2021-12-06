#include "model_World.h"

#include "led-matrix.h"
#include "threaded-canvas-manipulator.h"
#include "pixel-mapper.h"
#include "graphics.h"

#include <iostream>
#include <string>
#include <assert.h>
#include <getopt.h>
#include <limits.h>
#include <math.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include <algorithm>

using std::max;
using std::min;

#define TERM_ERR "\033[1;31m"
#define TERM_NORM "\033[0m"

using namespace rgb_matrix;

volatile bool interrupt_received = false;
static void InterruptHandler(int signo)
{
	interrupt_received = true;
}

// Simple class that generates a rotating block on the screen.
class RotatingBlockGenerator : public ThreadedCanvasManipulator
{
public:
	RotatingBlockGenerator(Canvas *m, JNIEnv *env, jintArray arr) : ThreadedCanvasManipulator(m)
	{
		this->env = env;
		this->arr = arr;
	}

	uint8_t scale_col(int val, int lo, int hi)
	{
		if (val < lo)
			return 0;
		if (val > hi)
			return 255;
		return 255 * (val - lo) / (hi - lo);
	}

	void Run()
	{
		jsize len = env->GetArrayLength(arr);
		int i, sum = 0;
		jint *body = env->GetIntArrayElements(arr, 0);
		for (i = 0; i < len; i++) {
			if (body[i] == 1) {
				// std::cout << i  << std::endl;
				// std::cout << i / 104  << std::endl;
				// std::cout << i % 104 << std::endl;
				// sum += 1;
				canvas()->SetPixel((i / 104) - 20, (i % 104) - 20, 255, 0, 0);
			}
		}
		env->ReleaseIntArrayElements(arr, body, 0);
		// std::cout << sum << std::endl;

		// jint *body = env->GetIntArrayElements(arr, 0);
		// for (int i = 0; i < canvas()->width() * canvas()->height(); i++)
		// {
		// 	// this works but it doesn't work for body[i] == 1, seems all are set to 0
		// 	if( (int) body[i] == 0 )
		// 		canvas()->SetPixel(i / canvas()->width(), i % canvas()->height(), 255, 0, 0);
		// 	else
		// 		canvas()->SetPixel(i / canvas()->width(), i % canvas()->height(), 0, 255, 0);
		// }
		// env->ReleaseIntArrayElements(arr, body, 0);

		// for (int i = 0; i < canvas()->width(); i++)
		// {
		// 	for (int j = 0; j < canvas()->height(); j++)
		// 	{
		// 		canvas()->SetPixel(i, j, 255, 0, 0);
		// 	}
		// }
		// canvas()->SetPixel(42, 42, 255, 0, 0);
		std::cout << canvas()->width() << std::endl;
		std::cout << canvas()->height() << std::endl;
	}

private:
	JNIEnv *env;
	jintArray arr;
	void Rotate(int x, int y, float angle,
				float *new_x, float *new_y)
	{
		*new_x = x * cosf(angle) - y * sinf(angle);
		*new_y = x * sinf(angle) + y * cosf(angle);
	}
};

JNIEXPORT void JNICALL Java_model_World_toMatrix(JNIEnv *env, jobject obj, jintArray arr)
{
	int runtime_seconds = -1;
	int demo = -1;
	int scroll_ms = 30;

	RGBMatrix::Options matrix_options;
	rgb_matrix::RuntimeOptions runtime_opt;

	// These are the defaults when no command-line flags are given.
	// sudo ./demo -D0 --led-slowdown-gpio=4 --led-no-hardware-pulse --led-chain=2 --led-rows=32 --led-cols=63 --led-pixel-mapper=U-mapper;Rotate:180
	matrix_options.rows = 32;
	matrix_options.cols = 64;
	matrix_options.chain_length = 2;
	runtime_opt.gpio_slowdown = 4;
	matrix_options.hardware_mapping = "adafruit-hat";
	matrix_options.pixel_mapper_config= "U-mapper;Rotate:180";
	matrix_options.disable_hardware_pulsing = true;
	RGBMatrix *matrix = CreateMatrixFromOptions(matrix_options, runtime_opt);
	if (matrix == NULL)
		return;

	printf("Size: %dx%d. Hardware gpio mapping: %s\n",
		   matrix->width(), matrix->height(), matrix_options.hardware_mapping);

	Canvas *canvas = matrix;

	ThreadedCanvasManipulator *image_gen = NULL;

	image_gen = new RotatingBlockGenerator(canvas, env, arr);

	signal(SIGTERM, InterruptHandler);
	signal(SIGINT, InterruptHandler);

	image_gen->Start();

	if (runtime_seconds > 0)
	{
		sleep(runtime_seconds);
	}
	else
	{
		// The
		printf("Press <CTRL-C> to exit and reset LEDs\n");
		while (!interrupt_received)
		{
			sleep(1); // Time doesn't really matter. The syscall will be interrupted.
		}
	}

	delete image_gen;
	delete canvas;

	printf("\%s. Exiting.\n",
		   interrupt_received ? "Received CTRL-C" : "Timeout reached");
}
