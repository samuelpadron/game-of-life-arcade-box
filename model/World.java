package model;

import java.util.*;

public class World {

    static {
        System.loadLibrary("rgbmatrix");
    }

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
            cell.setValue(1);
        }else if(cell.getValue() == 0 && amountOfLiveNeightbours == 3){
            cell.setValue(1);
        } else {
            cell.setValue(0);
        }
    }

    public int[] cellToInt() {

    //Make a list to append values to
    List<Integer> intList = new ArrayList<Integer>();
    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid.length; j++) { 
            intList.add(grid[i][j].getValue()); 
        }
    }

    //Make the 1d array to send to C++
    int[] intVersion = new int[intList.size()];
    for (int i = 0; i < intVersion.length; i++) {
        intVersion[i] = intList.get(i);
    }

    return intVersion;
    }

    public void next(){
        for (int row = 0; row < WORLD_MAX_SIZE; row++) {
            for (int col = 0; col < WORLD_MAX_SIZE; col++) {
                setCellState(grid[row][col]);
            }
        }
        toMatrix(this.cellToInt());
    }

    public void placePattern(){
        int counterY = 0;
        int counterX = 0;
        //this is not a good solution -> hovering is not supported
        

        System.out.println(cursor.getSelectedPattern().getLength());
        for (int row = cursor.getY(); row < cursor.getY() + cursor.getSelectedPattern().getLength(); row++) {
            for (int col = cursor.getX(); col <  cursor.getX() + cursor.getSelectedPattern().getWidth(); col++) {
                if(grid[cursor.getY() + counterY][cursor.getX() + counterX].getValue() == 0){
                    grid[cursor.getY() + counterY][cursor.getX() + counterX].setValue(cursor.getSelectedPattern().getPattern()[counterY][counterX].getValue());
                }
                counterX++;
            }
            counterX = 0;
            counterY++;
        }
    }

    public void move(Direction direction){
        cursor.move(direction);
    }
    public void setSelectedPattern(int index){
        cursor.setSelectedPattern(index);
    }
    
    public void start() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new Clock(), 0, 1000);
    }

    public static World getInstance(){
        return new World();
    }

    private native void toMatrix(int[] intGrid);
    
   
}