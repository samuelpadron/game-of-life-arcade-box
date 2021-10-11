package model;

import model.patterns.*;

public class Cursor {
    //this should become an array that will hold all of the patterns
    private Pattern[] patterns = {new blockPattern()};
    private Pattern selectedPattern;
    private int x;
    private int y;
    private final int SCREEN_MAX_SIZE = 104;
    private final int SCREEN_MIN_SIZE = 20;

    public Cursor(){
        this.x = 52;
        this.y = 52;
    }
    //should return the currently selected pattern
    public Pattern getSelectedPattern() {
        System.out.println("I was used");
        return selectedPattern;
    }

    //should become a counter where that counter will represent the index of the internal pattern array.
    public void setSelectedPattern(int index) {
        System.out.println("I was use222d");
        this.selectedPattern = this.patterns[index];
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }

    public void move(Direction direction){
        switch(direction){
            case UP: 
                if (y - 1 > SCREEN_MIN_SIZE )
                    y--;
                break;
            case DOWN:
                if(y + 1  < SCREEN_MAX_SIZE) 
                    y++;
            break;
            case LEFT:
                if(x - 1 > SCREEN_MIN_SIZE) 
                    x--;
                break;
            case RIGHT: 
                if(x + 1 > SCREEN_MAX_SIZE)
                    x++;
                break;
        }
    }


    
}
