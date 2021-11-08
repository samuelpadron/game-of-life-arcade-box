public class ExampleObjectsJNI {
	
	static { 
		System.loadLibrary("native");
	}

	public static void main(String[] args) {
		ExampleObjectsJNI instance = new ExampleObjectsJNI();
		UserData newUser = instance.createUser("John Doe", 12.23);
		instance.printUserData(newUser);
	}

	public native UserData createUser(String name, double balance);

	public native String printUserData(UserData user);
}
