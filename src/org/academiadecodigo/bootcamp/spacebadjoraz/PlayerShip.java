package org.academiadecodigo.bootcamp.spacebadjoraz;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public class PlayerShip extends Ship implements KeyboardHandler {

    private enum Direction{
        RIGHT,
        LEFT;

        private boolean enabled;

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public boolean isEnabled() {
            return enabled;
        }
    }
    private Bullet bullet;
    private Rectangle ship;

    public PlayerShip(int x, int y) {
        ship = new Rectangle(x, y, 50, 100);
        ship.setColor(Color.RED);
        ship.fill();
    }

    public void shoot(){
        this.bullet = new Bullet(true);
    }

    public Bullet getBullet(){
        Bullet currentBullet = bullet;
        this.bullet = null;
        return currentBullet;
    }

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
