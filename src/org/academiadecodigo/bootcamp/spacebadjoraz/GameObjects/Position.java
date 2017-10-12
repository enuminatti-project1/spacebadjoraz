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
        return getX() < pos.getMaxX() &&
                getMaxX() > pos.getX() &&
                getY() < pos.getMaxY() &&
                getMaxY() > pos.getY();
    }

    public boolean isUnsafe(Position pos, int speed) {
        return getX() - speed < pos.getX() ||
                getMaxX() + speed > pos.getMaxX() ||
                getY() - speed < pos.getY() ||
                getMaxY() + speed > pos.getMaxY();
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

    public int getMaxX() {
        return x + width;
    }

    public int getMaxY() {
        return y + height;
    }
}
