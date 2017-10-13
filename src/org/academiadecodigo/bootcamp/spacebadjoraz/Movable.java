package org.academiadecodigo.bootcamp.spacebadjoraz;

import org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects.Position;

/**
 * Created by Someone who is not me on 09/10/17.
 */

public interface Movable {

    /**
     * Any object that's movable will have this behavior
     */
    void move();

    /**
     * Set the limits where the object can move
     * @param limits
     */
    void setLimits(Position limits);

    Position getPosition();

    /**
     * set the speed of the movement
     * @param speed
     */
    void setSpeed(int speed);

}
