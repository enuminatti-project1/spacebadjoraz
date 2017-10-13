package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

import org.academiadecodigo.bootcamp.spacebadjoraz.Exceptions.NoBullet;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public class PlayerShip extends Ship implements KeyboardHandler {

    private Keyboard key;
    //private Picture pic;    //Picture of the Ship


    enum PlayerDirection {
        RIGHT(1, 0),
        LEFT(-1, 0),
        UP(0, -1),
        DOWN(0, 1),
        NONE(0, 0);

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

        PlayerDirection(int x, int y) {
            this.x = x;
            this.y = y;
            this.enabled = false;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        /**
         * Enable or disable the direction of the ship
         * When there's no direction defined enable 'NONE' direction
         *
         * @param enabled
         */
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
            if (!this.enabled) {
                NONE.enabled = true;
            }
        }

        /**
         * @return status of the PlayerDirection
         */
        public boolean isEnabled() {
            return enabled;
        }
    }


    /**
     * This will keep the shooting the player shot until it's retrieved by Game.
     *
     * When Game gets it, it'll handle the moving of the shooting and the player
     * doesn't need it anymore.
     */
    private boolean shooting;

    /**
     * Bullets until weapon needs to cooldown
     */
    private static final int BULLETS = 5;


    private static final int HEALTH = 100;

    /**
     * Bullets we can still shoot until the weapon stops shooting
     */
    private int bulletsLeftToShoot;

    /**
     * Initialize the player's ship and graphical representation.
     *
     * @param canvas is the Game's Rectangle so the ship knows the game limits and placement
     */
    public PlayerShip(Rectangle canvas) {
        Rectangle r = new Rectangle(
                canvas.getWidth() / 2.0, 500,
                50, 60);
        //r.setColor(Color.RED);
        //r.fill();
        super.setShip(r, HEALTH, "Enuminatti", "img/plLogo.png");
        super.setPic(r.getX(), r.getY());
        super.setLimits(new Position(canvas.getX(), canvas.getY(), canvas.getWidth(), canvas.getHeight()));
        super.setSpeed(8);
        configKeyboard();

        this.bulletsLeftToShoot = BULLETS;
    }

    /**
     * Set the Keyboard keys to handle
     */
    public void configKeyboard() {

        this.key = new Keyboard(this);

        int[] kArray = {
                KeyboardEvent.KEY_W,
                KeyboardEvent.KEY_A,
                KeyboardEvent.KEY_S,
                KeyboardEvent.KEY_D,

                KeyboardEvent.KEY_UP,
                KeyboardEvent.KEY_LEFT,
                KeyboardEvent.KEY_DOWN,
                KeyboardEvent.KEY_RIGHT,

                KeyboardEvent.KEY_SPACE
        };

        for (int i : kArray) {

            KeyboardEvent k = new KeyboardEvent();
            k.setKey(i);
            k.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            key.addEventListener(k);

            k = new KeyboardEvent();
            k.setKey(i);
            k.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
            key.addEventListener(k);
        }

    }

    /**
     * Moves the Ship on the direction set by PlayerDirection ENUM values
     */
    @Override
    public void move() {

        if (getShip() == null) {
            return;
        }

        for (PlayerDirection dir : PlayerDirection.values()) {
            if (dir.isEnabled()) {

                switch (dir) {

                    case RIGHT:
                        if (getPosition().getMaxX() + getSpeed() > getLimits().getMaxX()) {
                            getShip().translate(getLimits().getMaxX() - getPosition().getMaxX(), 0);
                            dir.setEnabled(false);
                            continue;
                        }
                        break;
                    case LEFT:
                        if (getPosition().getX() - getSpeed() < getLimits().getX()) {
                            getShip().translate(getLimits().getX() - getPosition().getX(), 0);
                            dir.setEnabled(false);
                            continue;
                        }
                        break;
                    case UP:
                        if (getPosition().getY() - getSpeed() < getLimits().getY()) {
                            getShip().translate(0, getLimits().getY() - getPosition().getY());
                            dir.setEnabled(false);
                            continue;
                        }
                        break;
                    case DOWN:
                        if (getPosition().getMaxY() + getSpeed() > getLimits().getMaxY()) {
                            getShip().translate(0, (getLimits().getMaxY() - getPosition().getMaxY()));
                            dir.setEnabled(false);
                            continue;
                        }
                        break;
                    case NONE:
                        break;
                }

                getShip().translate(dir.getX() * getSpeed(), dir.getY() * getSpeed());
            }
        }
    }

    /**
     * Start shooting.
     */
    @Override
    public void shoot() {
        this.shooting = true;
    }

    /**
     * Stop shooting.
     */
    @Override
    public void stopShooting() {
        this.shooting = false;
        this.bulletsLeftToShoot = BULLETS;
    }

    /**
     * This sends to Game the created bullet.
     *
     * @return the Bullet that was created
     */
    @Override
    public Bullet getBullet() throws NoBullet {
        if (this.shooting && this.bulletsLeftToShoot > 0) {
            int x = (getShip().getWidth() / 2) + getShip().getX();
            int y = getShip().getY();
            this.bulletsLeftToShoot--;
            return new Bullet(x, y, true);
        }
        throw new NoBullet();
    }


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
                PlayerDirection.LEFT.setEnabled(true);
                break;
            case KeyboardEvent.KEY_RIGHT:
            case KeyboardEvent.KEY_D:
                PlayerDirection.RIGHT.setEnabled(true);
                break;
            case KeyboardEvent.KEY_UP:
            case KeyboardEvent.KEY_W:
                PlayerDirection.UP.setEnabled(true);
                break;
            case KeyboardEvent.KEY_DOWN:
            case KeyboardEvent.KEY_S:
                PlayerDirection.DOWN.setEnabled(true);
                break;
            case KeyboardEvent.KEY_SPACE:
                shoot();
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
                PlayerDirection.LEFT.setEnabled(false);
                break;
            case KeyboardEvent.KEY_RIGHT:
            case KeyboardEvent.KEY_D:
                PlayerDirection.RIGHT.setEnabled(false);
                break;
            case KeyboardEvent.KEY_UP:
            case KeyboardEvent.KEY_W:
                PlayerDirection.UP.setEnabled(false);
                break;
            case KeyboardEvent.KEY_DOWN:
            case KeyboardEvent.KEY_S:
                PlayerDirection.DOWN.setEnabled(false);
                break;
            case KeyboardEvent.KEY_SPACE:
                stopShooting();
                break;


        }
    }
}
