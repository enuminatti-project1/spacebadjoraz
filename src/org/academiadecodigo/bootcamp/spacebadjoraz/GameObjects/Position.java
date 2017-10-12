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

        return (this.x < pos.getX() + pos.getWidth() &&
                this.x + this.width > pos.getX() &&
                this.y < pos.getY() + pos.getHeight() &&
                this.height + this.y > pos.getY());

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
