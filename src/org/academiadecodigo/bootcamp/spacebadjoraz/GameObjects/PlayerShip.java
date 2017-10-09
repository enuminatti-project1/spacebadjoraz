package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public class PlayerShip extends Ship implements KeyboardHandler {

    /**
     * This contains valid directions that the player can move to.
     */
    private enum Direction{
        RIGHT,
        LEFT;

        /**
         * When the keyboard is implemented, this will be enabled
         * if you pressed the corresponding key.
         *
         * While it's enabled, the player ship will move in these directions
         * on every frame.
         *
         * When disabled, it'll stop moving in that direction.
         */
        private boolean enabled;

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public boolean isEnabled() {
            return enabled;
        }
    }

    /**
     * This will keep the bullet the player shot until it's retrieved by Game.
     *
     * When Game gets it, it'll handle the moving of the bullet and the player
     * doesn't need it anymore.
     */
    private Bullet bullet;

    /**
     * This contains the ship's graphical representation.
     */
    private Rectangle ship;

    /**
     * Initialize the player's ship and graphical representation.
     *
     * TODO: The Game knows the background's boundaries, so it could just
     * pass the background itself and then the PlayerShip knows where
     * to start too.
     *
     * @param x is the ship's starting position on the horizontal axis
     * @param y is the ship's starting position on the vertical axis
     */
    public PlayerShip(int x, int y) {
        ship = new Rectangle(x, y, 50, 100);
        ship.setColor(Color.RED);
        ship.fill();
    }

    /**
     * Create a new bullet shot by the player.
     * Since it's the player's it'll shoot up.
     */
    public void shoot(){
        this.bullet = new Bullet(true);
    }

    /**
     * This sends to Game the created bullet.
     *
     * @return the bullet that was created
     */
    public Bullet getBullet(){
        Bullet currentBullet = bullet;
        this.bullet = null;
        return currentBullet;
    }

    /**
     * This will move the player to the enabled direction(s).
     *
     * If none are enabled, stand still.
     */
    @Override
    public void move() {
        if (Direction.LEFT.isEnabled()) {
            System.out.println("moving left");
        } else if (Direction.RIGHT.isEnabled()) {
            System.out.println("moving right");
        } else {
            System.out.println("standing still");
        }
    }

    // TODO: add handlers to needed keys
    // TODO: need to listen for keys pressed and released

    /**
     * This listens to pressed keys.
     * If the keys are the right ones, it enables the respective direction.
     *
     * @param keyboardEvent the event that triggered with PRESSED
     */
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
            case KeyboardEvent.KEY_A:
                Direction.LEFT.setEnabled(true);
                break;
            case KeyboardEvent.KEY_RIGHT:
            case KeyboardEvent.KEY_D:
                Direction.RIGHT.setEnabled(true);
                break;
        }
    }

    /**
     * This listens to released keys.
     * If the keys are the right ones, it disables the respective direction.
     *
     * @param keyboardEvent the event that triggered with RELEASED
     */
    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
            case KeyboardEvent.KEY_A:
                Direction.LEFT.setEnabled(false);
                break;
            case KeyboardEvent.KEY_RIGHT:
            case KeyboardEvent.KEY_D:
                Direction.RIGHT.setEnabled(false);
                break;
        }
    }
}
