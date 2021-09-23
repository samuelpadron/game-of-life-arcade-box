package model;


public class World{
    private String hello;
    private int[][] grid;
    private World(){
        grid = new int[64][64];
        hello = new String("hello world !!!!");
    }

    public String getString(){
        return hello; 
    }

    public static World getInstance(){
        return new World();
    }
}