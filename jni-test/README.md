# JNI
## Tutorial
<https://www.baeldung.com/jni>
## Commands
### Create Header File
`javac -h . FILENAME.java`
### Compiling and Linking
`g++ -c -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux FILENAME.cpp -o FILENAME.o`\
${JAVA\_HOME} = /usr/lib/jvm/JAVAVERSION <br> 
`g++ -shared -fPIC -o libLIBRARYNAME.so FILENAME.o -lc`
### Running
java -cp . -Djava.library.path=/LIBRARYDIR
