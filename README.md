# TODO
- Find how to make the JNI method stop running without it having to kill everything as well

# C++
Compile the JNI method implementation:
```
g++ -c -fPIC -I/usr/lib/jvm/default-java/include -I/usr/lib/jvm/default-java/include/linux -I/home/pi/A3/view/include model_World.cpp -o model_World.o
```
Link it with library (I don't know if this needs to be run everytime a change is made but idk):
```
g++ -shared -fPIC -o librgbmatrix.so *.o -lc
```
