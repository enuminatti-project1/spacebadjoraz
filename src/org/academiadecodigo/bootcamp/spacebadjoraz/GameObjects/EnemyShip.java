package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public class EnemyShip extends Ship {

    private static final int SHIPWIDTH = 30;
    private static final int SHIPHEIGHT = 40;

    private EnemyDirection currentDirection;

    private enum EnemyDirection {
        LEFT,
        RIGHT;

        public EnemyDirection getOpposite() {
            EnemyDirection current = null;

            switch (this) {
                case LEFT:
                    current = RIGHT;
                    break;
                case RIGHT:
                    current = LEFT;
                    break;
            }

            return current;
        }
    }

    public EnemyShip(Rectangle canvas) {
        Rectangle r = new Rectangle(
                (canvas.getX() + canvas.getWidth()/2),
                30,
                SHIPWIDTH,
                SHIPHEIGHT);
        r.setColor(Color.BLUE);
        r.fill();
        super.setShip(r);
        super.setLimits(new Position(canvas.getX(), canvas.getY(), canvas.getWidth(), canvas.getHeight()));
        super.setSpeed(5);
        currentDirection = EnemyDirection.RIGHT;
    }

    /**
     * The enemy can't shoot for now.
     * TODO: make the enemy shoot after mvp
     */
    public void shoot() {

        // Bullet bullet = new Bullet(false);

    }

    public void stopShooting() {

    }

    /**
     * The enemy can't move for now.
     * TODO: make the enemy move
     */

    @Override
    public void move() {
        // what to do if we reach the limits
        if (!getLimits().isInside(getPosition())) {
            this.currentDirection = this.currentDirection.getOpposite();
            System.out.println("moving " + this.currentDirection);
        }

        switch (this.currentDirection) {
            case RIGHT:
                getShip().translate(getSpeed(), 0);
                break;
            case LEFT:
                getShip().translate(-getSpeed(), 0);
                break;
        }

    }

}
