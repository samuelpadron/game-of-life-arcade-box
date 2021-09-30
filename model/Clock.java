package model;

import java.util.TimerTask;

public class Clock extends TimerTask {
    @Override
    public void run() {
        World world = World.getInstance();
        world.next();    
        System.out.println(world.toString()); 
    }
    
}