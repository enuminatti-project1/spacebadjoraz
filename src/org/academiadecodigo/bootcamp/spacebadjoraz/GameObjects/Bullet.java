package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

import org.academiadecodigo.bootcamp.spacebadjoraz.Movable;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public class Bullet implements Movable {

    private final int bulletPower;
    /**
     * Speed the bullet travels at.
     */
    private int speed;

    /**
     * Direction the bullet travels to.
     */
    private boolean shootUp;

    private Ellipse bullet;

    private static final int SIZE = 8;

    /**
     * Constructor for bullet used by PlayerShip and, in the future, EnemyShip
     *
     * @param shootUp True if bullet shoots up, False if it shoots down
     */
    Bullet(int x, int y, boolean shootUp, int bulletPower) {
        this.shootUp = shootUp;
        this.bullet = new Ellipse(x - SIZE / 2, y - SIZE, SIZE, SIZE);
        this.bullet.setColor(Color.YELLOW);
        this.bullet.fill();
        this.bulletPower = bulletPower;

        this.speed = 15;
    }

    /**
     * Make the bullet move
     */
    @Override
    public void move() {
        if (shootUp) {
            //bullet goes up
            bullet.translate(0, -speed);
        } else {
            //bullet goes down
            bullet.translate(0, speed);
        }
    }

    @Override
    public Position getPosition() {

        return new Position(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());

    }

    public int getBulletPower() {
        return bulletPower;
    }

    public Ellipse getBullet() {

        return bullet;

    }

    public int getSpeed() {
        return speed;
    }
}
