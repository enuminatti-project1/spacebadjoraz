package org.academiadecodigo.bootcamp.spacebadjoraz;

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public class Hitbox {
    private int x;
    private int y;
    private int width;
    private int height;

    public Hitbox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getLeft() {
        return x;
    }

    public int getTop() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRight() {
        return x + width;
    }

    public int getBottom() {
        return y + height;
    }
}
