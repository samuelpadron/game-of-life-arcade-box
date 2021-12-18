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
        // Controller controller = new Controller("bruh");
        World world = World.getInstance();
		System.out.println("hello world");
		world.setSelectedPattern(0);
		world.placePattern();
		System.out.print(world);
		System.out.println("");
		world.next();
		System.out.println(world);
        // world.setSelectedPattern(0);
        // world.placePattern();
        // System.out.println(world);
        // ScheduledExecutorService executorService;
        // executorService = Executors.newSingleThreadScheduledExecutor();
        // executorService.scheduleAtFixedRate(world, 0, 1, TimeUnit.SECONDS);
        // Thread worldThread = new Thread(world);
        // Thread controlThread = new Thread(controller);
        // worldThread.start();
        // controlThread.start();
    }
}

