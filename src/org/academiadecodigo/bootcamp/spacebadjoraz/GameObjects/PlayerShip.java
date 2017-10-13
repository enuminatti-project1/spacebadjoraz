package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

import org.academiadecodigo.bootcamp.spacebadjoraz.Exceptions.NoBullet;
import org.academiadecodigo.bootcamp.spacebadjoraz.Shootable;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Movable;
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
                canvas.getWidth()/2.0, 500,
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
                Direction.LEFT.setEnabled(true);
                break;
            case KeyboardEvent.KEY_RIGHT:
            case KeyboardEvent.KEY_D:
                Direction.RIGHT.setEnabled(true);
                break;
            case KeyboardEvent.KEY_UP:
            case KeyboardEvent.KEY_W:
                Direction.UP.setEnabled(true);
                break;
            case KeyboardEvent.KEY_DOWN:
            case KeyboardEvent.KEY_S:
                Direction.DOWN.setEnabled(true);
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
                Direction.LEFT.setEnabled(false);
                break;
            case KeyboardEvent.KEY_RIGHT:
            case KeyboardEvent.KEY_D:
                Direction.RIGHT.setEnabled(false);
                break;
            case KeyboardEvent.KEY_UP:
            case KeyboardEvent.KEY_W:
                Direction.UP.setEnabled(false);
                break;
            case KeyboardEvent.KEY_DOWN:
            case KeyboardEvent.KEY_S:
                Direction.DOWN.setEnabled(false);
                break;
            case KeyboardEvent.KEY_SPACE:
                stopShooting();
                break;


        }
    }
}
