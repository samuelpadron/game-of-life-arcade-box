import model.Cursor;
import model.World;
import model.patterns.blockPattern;

public class main {

    static {
        System.loadLibrary("rgbmatrix");
    }


    public static void main(String[] args){
	System.out.println(System.getProperty("java.library.path"));
	/*
        World world = World.getInstance();
        // world.start();
        world.setSelectedPattern(0);
        world.placePattern();
        world.next();
	*/
	new main().pixel();
    }

    private native void pixel();
}
