import controller.Controller;
import model.Cursor;
import model.World;
import model.patterns.blockPattern;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import model.patterns.*;
public class main {
    
    public static void main(String[] args){
        Controller controller = new Controller("Controller");
        World world = World.getInstance();
		world.run();
        Thread worldThread = new Thread(world);
        Thread controlThread = new Thread(controller);
        worldThread.start();
        controlThread.start();
    }
}

