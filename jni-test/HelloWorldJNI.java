public class HelloWorldJNI {

	static {
		System.loadLibrary("native");
	}

	public static void main(String[] args) {
		new HelloWorldJNI().sayHello();
	}

	//Declare a native method sayHello() that receives no args and returns void
	private native void sayHello();
}
