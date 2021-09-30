package model;

import java.util.*;

public class World {
    private String hello;
    private Cell[][] grid = new Cell[64][64];
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

    //temporary for testing
    public void setCellAlive(int x, int y){
        this.grid[x][y].setValue(1);
    }
    
    public int countLiveNeighbours(Cell cell){
        int counter = 0;

        int startPosX = cell.getCol() - 1 < 0 ? cell.getCol() : cell.getCol() - 1;
        int startPosY = cell.getRow() - 1 < 0 ? cell.getRow(): cell.getRow() - 1 ;
        int endPosX =   cell.getCol() + 1 > 63 ? cell.getCol() : cell.getCol() + 1;
        int endPosY =   cell.getRow() + 1 > 63 ? cell.getRow() : cell.getRow() + 1;

        for (int row=startPosY; row<=endPosY; row++) {
            for (int col=startPosX; col<=endPosX; col++) {
                if(grid[row][col].getValue() == 1)
                    counter++;
            }
        }  
        
        if( counter > 0) {
            System.out.println("I'm at " + cell.getRow() + ", " + cell.getCol() + " and have " + counter);
        }
        return counter - 1;
    }
    /*
        Any live cell with two or three live neighbours survives.
        Any dead cell with three live neighbours becomes a live cell.
        All other live cells die in the next generation. Similarly, all other dead cells stay dead.
    */

    private void setCellState(Cell cell) {
        int amountOfLiveNeightbours = countLiveNeighbours(cell);
        if(cell.getValue() == 1 && ( amountOfLiveNeightbours == 2 || amountOfLiveNeightbours == 3 )){
            System.out.println("here?");
            cell.setValue(1);
        }else if(cell.getValue() == 0 && amountOfLiveNeightbours == 3){
            cell.setValue(1);
        } else {
            cell.setValue(0);
        }
    }
    public void next(){
        for (int row = 0; row < 64; row++) {
            for (int col = 0; col < 64; col++) {
                setCellState(grid[row][col]);
            }
        }
    }
    
    public void start() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new Clock(), 0, 1000);
    }

    public static World getInstance(){
        return new World();
    }

   
}