up: main.java ./view/lib/main.o
	sudo java -cp . -Djava.library.path=/home/pi/A3/view/lib main.java
	cd view/lib/
	g++ -c -fPIC -I/usr/lib/jvm/default-java/include -I/usr/lib/jvm/default-java/include/linux -I/home/pi/A3/view/include main.cpp -o main.o
	g++ -shared -fPIC -o librgbmatrix.so *.o -lc