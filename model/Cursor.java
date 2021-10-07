package model;

public class Cursor {
    private Pattern pattern;
    private int x;
    private int y;
    private final int SCREEN_MAX_SIZE = 84;
    private final int SCREEN_MIN_SIZE = 20;

    public Cursor(){
        this.x = 42;
        this.y = 42;
    }
    
    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        //bounds check to see if the current pattern will fit aorund the cursor.
        this.pattern = pattern;
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
