# JNI
## Tutorial
<https://www.baeldung.com/jni>
## Commands
### Create Header File
`javac -h . FILENAME.java`
### Compiling and Linking
Make sure to include a line with `FILE.o: FILE.cpp FILE.h` in the Makefile in view/lib first.
`g++ -c -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux -I/home/pi/a3/view/include FILENAME.cpp -o FILENAME.o`\
${JAVA\_HOME} = /usr/lib/jvm/default-java <br> 
`g++ -shared -fPIC -o libLIBRARYNAME.so *.o -lc`
### Running
`java -cp . -Djava.library.path=/home/pi/A3/view/lib FILE.java`
