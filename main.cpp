#include "main.h"

#include "led-matrix.h"
#include "threaded-canvas-manipulator.h"
#include "pixel-mapper.h"
#include "graphics.h"

#include <iostream>
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

using std::min;
using std::max;

#define TERM_ERR  "\033[1;31m"
#define TERM_NORM "\033[0m"

using namespace rgb_matrix;

volatile bool interrupt_received = false;
static void InterruptHandler(int signo) {
  interrupt_received = true;
}

// Simple class that generates a rotating block on the screen.
class RotatingBlockGenerator : public ThreadedCanvasManipulator {
public:
  RotatingBlockGenerator(Canvas *m) : ThreadedCanvasManipulator(m) {}

  uint8_t scale_col(int val, int lo, int hi) {
    if (val < lo) return 0;
    if (val > hi) return 255;
    return 255 * (val - lo) / (hi - lo);
  }

  void Run() {

    for (int i = 0; i < canvas()->width(); i++) {
        for(int j = 0; j < canvas()->height(); j++) {
	        canvas()->SetPixel(i, j, 255,0,0);  
        }
    }
  }

private:
  void Rotate(int x, int y, float angle,
              float *new_x, float *new_y) {
    *new_x = x * cosf(angle) - y * sinf(angle);
    *new_y = x * sinf(angle) + y * cosf(angle);
  }
};

JNIEXPORT jint JNICALL Java_main_pixel
	(JNIEnv *env, jobject thisObject) {
		int runtime_seconds = -1;
  		int demo = -1;
  		int scroll_ms = 30;

		RGBMatrix::Options matrix_options;
		rgb_matrix::RuntimeOptions runtime_opt;

		// These are the defaults when no command-line flags are given.
		matrix_options.rows = 64;
		matrix_options.cols = 32;
		matrix_options.chain_length = 2;
		matrix_options.parallel = 1;
		runtime_opt.gpio_slowdown = 2;
		matrix_options.hardware_mapping = "adafruit-hat";


		RGBMatrix *matrix = CreateMatrixFromOptions(matrix_options, runtime_opt);
		if (matrix == NULL)
			return 1;

		printf("Size: %dx%d. Hardware gpio mapping: %s\n",
				matrix->width(), matrix->height(), matrix_options.hardware_mapping);

		Canvas *canvas = matrix;

		ThreadedCanvasManipulator *image_gen = NULL;
		
			image_gen = new RotatingBlockGenerator(canvas);

		signal(SIGTERM, InterruptHandler);
		signal(SIGINT, InterruptHandler);

		image_gen->Start();

		if (runtime_seconds > 0) {
			sleep(runtime_seconds);
		} else {
			// The
			printf("Press <CTRL-C> to exit and reset LEDs\n");
			while (!interrupt_received) {
			sleep(1); // Time doesn't really matter. The syscall will be interrupted.
			}
		}

		delete image_gen;
		delete canvas;

		printf("\%s. Exiting.\n",
				interrupt_received ? "Received CTRL-C" : "Timeout reached");
		return 0;
	}

