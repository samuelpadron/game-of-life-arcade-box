package model;

public class Cell {
    private int row;
    private int col;
    private int value;
    private World world = World.getInstance();

    public Cell(int row, int col){
        this.row = row;
        this.col = col;
        this.value = 0;
    }

    public int getValue() {
        return value;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
