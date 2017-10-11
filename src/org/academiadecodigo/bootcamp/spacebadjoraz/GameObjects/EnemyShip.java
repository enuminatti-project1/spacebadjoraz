package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public class EnemyShip extends Ship {

    //private Rectangle limits;

    public EnemyShip(int x, int y, Rectangle canvas) {
        Rectangle r = new Rectangle(x, y, 30, 40);
        r.setColor(Color.BLUE);
        r.fill();
        super.setShip(r);
        super.setLimits(new Position(canvas.getX(), canvas.getY(), canvas.getWidth(), canvas.getHeight()));
        super.setSpeed(5);
    }

    /**
     * The enemy can't shoot for now.
     * TODO: make the enemy shoot after mvp
     */
    @Override
    public void shoot() {

        // Bullet bullet = new Bullet(false);

    }

    @Override
    public void stopShooting() {

    }

    /**
     * The enemy can't move for now.
     * TODO: make the enemy move
     */

    @Override
    public void move(){/*
        int i = 0;
        while(i < 100) {
            System.out.println("mooving");
            getShip().translate(-1, 0);
            i++;
        }
        */
    }

}
