package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

import org.academiadecodigo.bootcamp.spacebadjoraz.Utils.Calculations;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public class EnemyShip extends Ship {

    private Rectangle limits;

    private static final int WIDTH = 30;
    private static final int HEIGHT = 40;

    public EnemyShip(Rectangle canvas) {
        Rectangle r = new Rectangle(
                Calculations.calcMid(canvas.getX(), WIDTH),
                30,
                WIDTH,
                HEIGHT);
        r.setColor(Color.BLUE);
        r.fill();
    }

    public EnemyShip(int x, int y, Rectangle canvas) {
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

    @Override
    public void stopShooting() {

    }

    /**
     * The enemy can't move for now.
     * TODO: make the enemy move
     */

}
