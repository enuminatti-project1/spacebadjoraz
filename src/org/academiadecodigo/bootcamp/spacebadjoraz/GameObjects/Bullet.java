package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

import org.academiadecodigo.bootcamp.spacebadjoraz.Movable;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public class Bullet implements Movable {

    /**
     * Speed the bullet travels at.
     *
     * TODO: different bullets could move at different speeds
     */
    private int speed;
    private Position limits;

    /**
     * Direction the bullet travels to.
     *
     * TODO: bullets can move diagonally
     */
    private boolean shootUp;

    private Ellipse bullet;

    private static final int SIZE = 8;

    /**
     * Constructor for bullet used by PlayerShip and, in the future, EnemyShip
     *
     * @param shootUp True if bullet shoots up, False if it shoots down
     */
    public Bullet(int x, int y, boolean shootUp) {
        this.shootUp = shootUp;
        this.bullet = new Ellipse(x - SIZE / 2, y - SIZE, SIZE, SIZE);
        this.bullet.setColor(Color.YELLOW);
        this.bullet.fill();

        this.speed = 20;
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
    public void setLimits(Position limits) {

        this.limits = limits;

    }

    @Override
    public Position getPosition() {

        return new Position(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());

    }

    @Override
    public void setSpeed(int speed) {

        this.speed = speed;

    }

    public Ellipse getBullet() {

        return bullet;

    }
}
