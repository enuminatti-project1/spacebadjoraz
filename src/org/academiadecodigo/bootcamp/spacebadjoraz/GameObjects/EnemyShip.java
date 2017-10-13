package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

import org.academiadecodigo.bootcamp.spacebadjoraz.Exceptions.NoBullet;
import org.academiadecodigo.bootcamp.spacebadjoraz.Shootable;
import org.academiadecodigo.bootcamp.spacebadjoraz.Utils.Calculations;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public class EnemyShip extends Ship {

    private static final int SHIPWIDTH = 50;
    private static final int SHIPHEIGHT = 68;
    private static final int RANDPATH = 50;
    private static final int SPEED = 5;

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

    //public EnemyShip(Position gameLimits, int health, String name, String picPath) {
    public EnemyShip(Position gameLimits, String name, String picPath, int health, int bulletPower){
        super(new Rectangle(Calculations.calcMid(gameLimits.getMidX(), SHIPWIDTH),
                30, SHIPWIDTH, SHIPHEIGHT), gameLimits, SPEED, health, ShipType.ENEMY,
                name, picPath, bulletPower);
        currentDirection = EnemyDirection.RIGHT;
        remainPath = RANDPATH;
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

    @Override
    public Bullet getBullet() throws NoBullet {
        if ((remainPath > 2 && remainPath < 7) || remainPath > 15 && remainPath < 20 ) {
            int x = (getShip().getWidth() / 2) + getShip().getX();
            int y = getShip().getY() + getShip().getHeight() + 2;
            return new Bullet(x, y, false, super.getBulletPower());
        }
        throw new NoBullet();
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
                getPic().translate(getSpeed(), 0);
                break;
            case LEFT:
                getShip().translate(-getSpeed(), 0);
                getPic().translate(-getSpeed(), 0);
                break;
        }
    }
}
