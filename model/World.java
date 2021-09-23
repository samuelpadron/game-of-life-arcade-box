package model;


public class World{
    private String hello;
    private Cell[][] grid = new Cell[64][64];;
    private World(){
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                grid[row][col] = new Cell(row, col);
            }
        }
        hello = new String("hello world !!!!");
    }

    public String getString(){
        return hello; 
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                sb.append(grid[row][col].getValue());
                sb.append("|");
            }
            sb.append("\n");
            for (int col = 0; col < grid.length; col++) {
                sb.append("--");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void next(){
        //
    }
    public static World getInstance(){
        return new World();
    }
}