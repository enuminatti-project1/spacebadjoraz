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
     * <p>
     * TODO: different bullets could move at different speeds
     */
    private int speed = 20;


    /**
     * Direction the bullet travels to
     * <p>
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
        bullet = new Ellipse(x - SIZE / 2, y - SIZE, SIZE, SIZE);
        bullet.setColor(Color.YELLOW);
        bullet.fill();
    }

    /**
     * Make the bullet move
     */
    @Override
    public void move() {
        if (shootUp) {
            bullet.translate(0, -speed);
            //bullet goes up
        }
        //bullet goes down
    }
}
