package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public class EnemyShip extends Ship {

    private static final int SHIPWIDTH = 30;
    private static final int SHIPHEIGHT = 40;
    private static final int RANDPATH = 50;

    private EnemyDirection currentDirection;
    private int remainPath;

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
                (canvas.getX() + canvas.getWidth() / 2),
                30,
                SHIPWIDTH,
                SHIPHEIGHT);
        r.setColor(Color.BLACK);
        r.fill();
        super.setShip(r);
        super.setLimits(new Position(canvas.getX(), canvas.getY(), canvas.getWidth(), canvas.getHeight()));
        super.setSpeed(5);
        currentDirection = EnemyDirection.RIGHT;
        remainPath = RANDPATH;
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

    public Bullet getBullet() {
        if (remainPath < 3) {
            int x = (getShip().getWidth() / 2) + getShip().getX();
            int y = getShip().getY() + getShip().getHeight() + 2;
            return new Bullet(x, y, false);
        }
        return null;
    }


    @Override
    public void move() {
        remainPath--;

        // what to do if we reach the limits
        if (getPosition().isInside(getLimits(), getSpeed())) {
            remainPath = RANDPATH;
            this.currentDirection = this.currentDirection.getOpposite();
            System.out.println("Hit limit: switching to " + currentDirection);
        }

        if (remainPath == 0) {
            remainPath = (int) (Math.random() * RANDPATH);
            this.currentDirection = this.currentDirection.getOpposite();
            System.out.println("Juke: Switching to " + currentDirection);
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
