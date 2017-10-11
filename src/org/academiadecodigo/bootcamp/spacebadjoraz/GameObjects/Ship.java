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
    //private int x, y, width, height;
    private Position limits;
    //private Position position;
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
                        if(ship.getX() + ship.getWidth() + speed > limits.getX() + limits.getWidth()){
                            getShip().translate((limits.getWidth()  + limits.getX()) - (ship.getX() + ship.getWidth()), 0);
                            dir.setEnabled(false);
                            continue;
                        }
                        break;
                    case LEFT:
                        if(ship.getX() - speed < limits.getX()){
                            getShip().translate(limits.getX() - ship.getX(), 0);
                            dir.setEnabled(false);
                            continue;
                        }
                        break;
                    case UP:
                        if(ship.getY() - speed < limits.getY()){
                            getShip().translate(0, limits.getY() - ship.getY());
                            dir.setEnabled(false);
                            continue;
                        }
                        break;
                    case DOWN:
                        if(ship.getY() + ship.getHeight() + speed > limits.getY() + limits.getHeight()){
                            getShip().translate(0, ((limits.getHeight() + limits.getY()) - (ship.getY() + ship.getHeight())));
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
