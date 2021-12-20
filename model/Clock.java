package model;

import java.util.TimerTask;

public class Clock extends TimerTask {
    World world = World.getInstance();
    @Override
    public void run() {
        world.next();    
        System.out.println(world.toString()); 
		try{
			world.toFile();
		}catch(Exception e){
			System.out.println("It broke");
		}
    }
    
}
