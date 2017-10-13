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

    /**
     * check if the caller is inside the Position pos
     * @param pos position to check if is inside
     * @return true if is inside and false if not
     */
    public boolean isInside(Position pos){
        return getX() < pos.getMaxX() &&
                getMaxX() > pos.getX() &&
                getY() < pos.getMaxY() &&
                getMaxY() > pos.getY();
    }

    /**
     * Check if the caller is inside the Position position after he
     * translates with the speed
     * @param position position to check if is inside
     * @param speed speed to add to the position
     * @return true or false if the caller is inside the position
     */
    public boolean isInside(Position position, int speed){
        return (getX() - position.getX() < speed ||
                position.getMaxX() - getMaxX() < speed ||
                getY() - position.getY() < speed ||
                position.getMaxY() - getMaxY() < speed);
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

    public int getMidX() {
        return getX() + (int) (getWidth() / 2.0);
    }

    public int getMidY() {
        return getY() + (int) (getHeight() / 2.0);
    }
}
