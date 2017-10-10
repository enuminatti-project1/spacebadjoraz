package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public class EnemyShip extends Ship {

    public EnemyShip(int x, int y) {
        Rectangle r = new Rectangle(x, y, 30, 40);
        r.setColor(Color.BLUE);
        r.fill();
    }

    /**
     * The enemy can't shoot for now.
     * TODO: make the enemy shoot after mvp
     */
    @Override
    public void shoot() {

        // Bullet bullet = new Bullet(false);

    }

    /**
     * The enemy can't move for now.
     * TODO: make the enemy move
     */
    @Override
    public void move() {

    }
}
