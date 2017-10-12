package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

/**
 * Created by Samuel Laço on 11/10/17.
 */
public class Position {
    private int x, y, width, height;

    public Position(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isInside(Position pos){
        return getLeft() < pos.getLeft() &&
                getRight() > pos.getRight() &&
                getTop() < pos.getTop() &&
                getBottom() > pos.getBottom();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLeft() {
        return x;
    }

    public int getTop() {
        return y;
    }

    public int getRight() {
        return x + width;
    }

    public int getBottom() {
        return y + height;
    }
}
