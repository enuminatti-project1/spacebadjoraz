package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

import org.academiadecodigo.bootcamp.spacebadjoraz.Movable;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public abstract class Ship implements Movable {
    // TODO: add common behavior between PlayerShip and EnemyShips

    private Rectangle ship;
    private Position limits;
    private int speed;
    private int health;

    /**
     *
     * @param ship Rectangle with the temporary aspect of the ship
     */
    protected void setShip(Rectangle ship, int health) {
        this.ship = ship;
        this.health = health;
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

    public Position getLimits() {
        return limits;
    }

    @Override
    public Position getPosition(){
        if (ship == null)
            return new Position(0,0,0,0);
        return new Position(ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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
                        if(getPosition().getMaxX() + speed > limits.getMaxX()){
                            getShip().translate(limits.getMaxX() - getPosition().getMaxX(), 0);
                            dir.setEnabled(false);
                            continue;
                        }
                        break;
                    case LEFT:
                        if(getPosition().getX() - speed < limits.getX()){
                            getShip().translate(limits.getX() - getPosition().getX(), 0);
                            dir.setEnabled(false);
                            continue;
                        }
                        break;
                    case UP:
                        if(getPosition().getY() - speed < limits.getY()){
                            getShip().translate(0, limits.getY() - getPosition().getY());
                            dir.setEnabled(false);
                            continue;
                        }
                        break;
                    case DOWN:
                        if(getPosition().getMaxY() + speed > limits.getMaxY()){
                            getShip().translate(0, (limits.getMaxY() - getPosition().getMaxY()));
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
