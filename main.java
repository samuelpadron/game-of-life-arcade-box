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
	System.out.println(new main().pixel());
    }

    private native int pixel();
}
