import controller.Controller;
import model.Cursor;
import model.World;
import model.patterns.blockPattern;

public class main {

<<<<<<< HEAD
    public static void main(String[] args){
        World world = World.getInstance();
        //world.start();
        world.setSelectedPattern(0);
        world.placePattern();
        world.next();
        System.out.println(world);
    }
=======
    // static {
    //     System.loadLibrary("rgbmatrix");
    // }


    public static void main(String[] args){
        Controller controller = new Controller("test");
        controller.run();
        
        // World world = World.getInstance();
        // // world.start();
        // world.setSelectedPattern(0);
        // world.placePattern();
        // world.next();
        // System.out.println(world);
        // world.next();
        // System.out.println(world);
        // // int[] line = world.cellToInt();s
	    // //new main().pixel(line);
    }

    // private native void pixel(int[] intGrid);
>>>>>>> 1-add-support-for-controllers
}


