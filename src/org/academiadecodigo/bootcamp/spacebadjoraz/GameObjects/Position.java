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
        return x < pos.x + pos.width && x + width > pos.x && y < pos.y + pos.height && y + height > pos.y;
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
}
