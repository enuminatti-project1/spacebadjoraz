package org.academiadecodigo.bootcamp.spacebadjoraz;

/**
 * Created by Someone who is not me on 09/10/17.
 */

public interface Movable {

    /**
     * Any object that's movable will have this behavior
     */
    /**
     * Move the Object on the direction x,y defined on Direction ENUM
     */
    void move();

    /**
     * Set the limits where the object can move
     * @param x Starting X point
     * @param y Starting Y point
     * @param width Width to the limit of the Y movement
     * @param height Height to the limit of the Y movement
     */
    void setLimits(int x, int y, int width, int height);

    /**
     * set the speed of the movement
     * @param speed
     */
    void setSpeed(int speed);

}
