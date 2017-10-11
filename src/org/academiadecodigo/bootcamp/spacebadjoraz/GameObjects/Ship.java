package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

import org.academiadecodigo.bootcamp.spacebadjoraz.Movable;
import org.academiadecodigo.bootcamp.spacebadjoraz.Shootable;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public abstract class Ship implements Movable, Shootable {
    // TODO: add common behavior between PlayerShip and EnemyShips

    protected enum Direction{
        RIGHT(1,0),
        LEFT(-1,0),
        UP(0,-1),
        DOWN(0,1),
        NONE(0,0);

        private int x;
        private int y;
        private boolean enabled;

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
            this.x = x * 8;
            this.y = y * 8;
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

    private Rectangle ship;
    private int x, y, width, height;
    private int speed;

    /**
     *
     * @param ship Rectangle with the temporary aspect of the ship
     */
    protected void setShip(Rectangle ship) {
        this.ship = ship;
    }

    /**
     *
     * @return The Rectangle
     */
    public Rectangle getShip() {
        return ship;
    }

    @Override
    public void setLimits(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Moves the Ship on the direction set by Direction ENUM values
     */
    public void move() {

        if (ship == null) {
            return;
        }
        for (PlayerShip.Direction dir : PlayerShip.Direction.values()){
            if (dir.isEnabled()){
                getShip().translate(dir.getX(), dir.getY());
            }
        }

    }
}
