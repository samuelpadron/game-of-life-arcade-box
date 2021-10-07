package model;

import java.util.*;

public class World {
    private final int WORLD_MAX_SIZE = 104;
    private final int BORDER = 20;
    private final int SCREEN_MAX_SIZE = WORLD_MAX_SIZE - BORDER;
    private Cell[][] grid = new Cell[WORLD_MAX_SIZE][WORLD_MAX_SIZE];
    private Cursor cursor = new Cursor();

    private World(){
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                grid[row][col] = new Cell(row, col);
            }
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int row = BORDER; row < SCREEN_MAX_SIZE; row++) {
            for (int col = BORDER; col < SCREEN_MAX_SIZE; col++) {
                sb.append(grid[row][col].getValue());
                sb.append("|");
            }
            sb.append("\n");
            for (int col = BORDER; col < SCREEN_MAX_SIZE; col++) {
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
        int startPosY = cell.getRow() - 1 < 0 ? cell.getRow() : cell.getRow() - 1 ;
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
        for (int row = 0; row < WORLD_MAX_SIZE; row++) {
            for (int col = 0; col < WORLD_MAX_SIZE; col++) {
                setCellState(grid[row][col]);
            }
        }
    }

    public void placePattern(Pattern pattern){
        
        for (int row = cursor.getY(); row < pattern.getLength(); row++) {
            for (int col = cursor.getX(); col < pattern.getWidth(); col++) {
                if(grid[cursor.getY() + row][cursor.getX() + col].getValue() == 0)
                    grid[cursor.getY() + row][cursor.getX() + col] = pattern.getPattern()[row][col];
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