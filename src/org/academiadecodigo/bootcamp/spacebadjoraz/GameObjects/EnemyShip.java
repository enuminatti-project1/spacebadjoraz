package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public class EnemyShip extends Ship {

    private static final int WIDTH = 30;
    private static final int HEIGHT = 40;

    public EnemyShip(Rectangle canvas) {
        Rectangle r = new Rectangle(
                (canvas.getX() + canvas.getWidth()/2),
                30,
                WIDTH,
                HEIGHT);
        r.setColor(Color.BLUE);
        r.fill();
        super.setShip(r);
        super.setLimits(new Position(canvas.getX(), canvas.getY(), canvas.getWidth(), canvas.getHeight()));
        super.setSpeed(5);
        currentDirection = EnemyDirection.RIGHT;
    }

    private EnemyDirection currentDirection;

    public EnemyShip(int x, int y, Rectangle canvas) {
        Rectangle r = new Rectangle(x, y, 30, 40);
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

    private enum EnemyDirection {
        LEFT, RIGHT
    }

    @Override
    public void move() {
        if (getShip().getX() - getSpeed() < getLimits().getX()) {
            currentDirection = EnemyDirection.RIGHT;
            System.out.println("moving right");

        } else if (getShip().getX() > getLimits().getWidth() - 25 ){
            currentDirection = EnemyDirection.LEFT;
            System.out.println("moving left");
        }

        switch (currentDirection) {
            case RIGHT:
                getShip().translate(getSpeed(), 0);
                break;
            case LEFT:
                getShip().translate(-getSpeed(), 0);
                break;
        }

    }

}
