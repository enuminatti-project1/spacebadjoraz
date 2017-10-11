package org.academiadecodigo.bootcamp.spacebadjoraz;

import org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects.Position;

/**
 * Created by Someone who is not me on 09/10/17.
 */

public interface Movable {

    /**
     * Any object that's movable will have this behavior
     */

    enum Direction{
        RIGHT(1,0),
        LEFT(-1,0),
        UP(0,-1),
        DOWN(0,1),
        NONE(0,0);

        private int x;
        private int y;
        private boolean enabled = false;

        /**
         * When the keyboard is implemented, this will be enabled
         * if you pressed the corresponding key.
         *
         * While it's enabled, the player ship will move in these directions
         * on every frame.
         *
         * When disabled, it'll stop moving in that direction.
         */

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        /**
         * Enable or disable the direction of the ship
         * When ther's no direction defined enable 'NONE' direction
         * @param enabled
         */
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
            if (this.enabled == false) {
                NONE.enabled = true;
            }
        }

        /**
         *
         * @return status of the Direction
         */
        public boolean isEnabled() {
            return enabled;
        }
    }

    /**
     * Move the Object on the direction x,y defined on Direction ENUM
     */
    void move();

    /**
     * Set the limits where the object can move
     * @param limits
     */
    void setLimits(Position limits);

    //void setPosition(Position position);

    Position getPosition();

    /**
     * set the speed of the movement
     * @param speed
     */
    void setSpeed(int speed);

}
