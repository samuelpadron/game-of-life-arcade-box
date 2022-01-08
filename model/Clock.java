package model;

import java.util.TimerTask;

public class Clock extends TimerTask {
  World world = World.getInstance();

  @Override
  public void run() {
    System.out.print("\033[H\033[2J");
    System.out.println(world.toString());
    world.next();

    try {
      world.toFile();
    } catch (Exception e) {
      System.out.println("It broke");
    }
  }

}
