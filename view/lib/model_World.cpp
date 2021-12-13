#include "../include/led-matrix.h"
#include "../include/threaded-canvas-manipulator.h"
#include "../include/pixel-mapper.h"
#include "../include/graphics.h"


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
#include <fstream>
#include <algorithm>
#include <iostream>

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
	RotatingBlockGenerator(Canvas *m) : ThreadedCanvasManipulator(m)
	{
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
		std::ifstream inputFile;
		
		while(true){
			//read from file,
			inputFile.open("../matrix.txt");
			if(inputFile.good())
				std::cout << "file opened";
			//parsefile should give us a vector with the matrix.
			//then we display that matrix;
			//OPTIONAL: run at 24 HERTZ
			
		}	
	}

private:
	void Rotate(int x, int y, float angle,
				float *new_x, float *new_y)
	{
		*new_x = x * cosf(angle) - y * sinf(angle);
		*new_y = x * sinf(angle) + y * cosf(angle);
	}
};

	void Java_model_World_toMatrix()
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

	image_gen = new RotatingBlockGenerator(canvas);

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
