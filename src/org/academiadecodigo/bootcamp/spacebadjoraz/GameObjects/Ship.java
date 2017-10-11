package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

import org.academiadecodigo.bootcamp.spacebadjoraz.Movable;
import org.academiadecodigo.bootcamp.spacebadjoraz.Shootable;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public abstract class Ship implements Movable, Shootable {
    // TODO: add common behavior between PlayerShip and EnemyShips

    private Rectangle ship;
    private Position limits;
    private int speed;

    /**
     *
     * @param ship Rectangle with the temporary aspect of the ship
     */
    protected void setShip(Rectangle ship) {
        this.ship = ship;
    }

    /**
     *
     * @return The Rectangle
     */
    public Rectangle getShip() {
        return ship;
    }

    @Override
    public void setLimits(Position limits) {
        this.limits = limits;
    }

    @Override
    public Position getPosition(){
        if (ship == null)
            return new Position(0,0,0,0);
        return new Position(ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLeft() {
        return ship.getX();
    }

    public int getTop() {
        return ship.getY();
    }

    public int getRight() {
        return ship.getX() + ship.getWidth();
    }

    public int getBottom() {
        return ship.getY() + ship.getHeight();
    }

    /**
     * Moves the Ship on the direction set by Direction ENUM values
     */
    public void move() {

        if (ship == null) {
            return;
        }

        for (Direction dir : Direction.values()){
            if (dir.isEnabled()){

                switch (dir){

                    case RIGHT:
                        if(getRight() + speed > limits.getRight()){
                            getShip().translate(limits.getRight() - getRight(), 0);
                            dir.setEnabled(false);
                            continue;
                        }
                        break;
                    case LEFT:
                        if(getLeft() - speed < limits.getLeft()){
                            getShip().translate(limits.getLeft() - getLeft(), 0);
                            dir.setEnabled(false);
                            continue;
                        }
                        break;
                    case UP:
                        if(getTop() - speed < limits.getTop()){
                            getShip().translate(0, limits.getTop() - getTop());
                            dir.setEnabled(false);
                            continue;
                        }
                        break;
                    case DOWN:
                        if(getBottom() + speed > limits.getBottom()){
                            getShip().translate(0, (limits.getBottom() - getBottom()));
                            dir.setEnabled(false);
                            continue;
                        }
                        break;
                    case NONE:
                        break;
                }

                getShip().translate(dir.getX() * speed, dir.getY() * speed);
            }
        }
    }
}
