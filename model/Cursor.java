package model;

import model.patterns.*;

public class Cursor {
    // this should become an array that will hold all of the patterns
    private Pattern[] patterns = { new blockPattern(), new blinkerPattern(), new gliderPattern(), new dieHardPattern(),
            new pulsarPattern() };
    private int patternPointer = 0;
    private Pattern selectedPattern;
    private int x;
    private int y;
    private World world = World.getInstance();
    private final int SCREEN_MAX_SIZE = 84; // was 104 before
    private final int SCREEN_MIN_SIZE = 20;

    public Cursor() {
        this.x = 52;
        this.y = 52;
        setSelectedPattern(patternPointer);
    }

    // should return the currently selected pattern
    public Pattern getSelectedPattern() {
        return selectedPattern;
    }

    // should become a counter where that counter will represent the index of the
    // internal pattern array.
    public void setSelectedPattern(int index) {
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

    public void move(Direction direction) {
        switch (direction) {
            case UP:
                if (y - 1 > SCREEN_MIN_SIZE)
                    y--;
                break;
            case DOWN:
                if (y + 1 < SCREEN_MAX_SIZE)
                    y++;
                break;
            case LEFT:
                if (x - 1 > SCREEN_MIN_SIZE)
                    x--;
                break;
            case RIGHT:
                if (x + 1 > SCREEN_MAX_SIZE)
                    x++;
                break;
        }
        try {
            world.updateCursorLocation(x, y);
        } catch (Exception e) {
            System.out.println("why");
        }
    }

    public void incrementPattern() {
        if (patternPointer + 1 >= patterns.length) {
            patternPointer = 0;
        } else {
            patternPointer++;
        }

        setSelectedPattern(patternPointer);
    }

    public void decrementPattern() {
        if (patternPointer - 1 < 0) {
            patternPointer = patterns.length;
        } else {
            patternPointer--;
        }

        setSelectedPattern(patternPointer);
    }

}
