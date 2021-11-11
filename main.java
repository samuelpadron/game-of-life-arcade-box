import model.Cursor;
import model.World;
import model.patterns.blockPattern;

public class main {

    static {
        System.loadLibrary("native");
    }


    public static void main(String[] args){
        World world = World.getInstance();
        // world.start();
        world.setSelectedPattern(0);
        world.placePattern();
        world.next();
        System.out.println(world);
    
    }

    private native void pixel();

}
