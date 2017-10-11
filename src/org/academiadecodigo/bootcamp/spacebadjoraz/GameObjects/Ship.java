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
    private int x, y, width, height;
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
    public void setLimits(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

        for (PlayerShip.Direction dir : PlayerShip.Direction.values()){
            if (dir.isEnabled()){

                switch (dir){

                    case RIGHT:
                        if(ship.getX() + ship.getWidth() + speed > this.x + this.width){
                            getShip().translate((this.width  + this.x) - (ship.getX() + ship.getWidth()), 0);
                            dir.setEnabled(false);
                            continue;
                        }
                        break;
                    case LEFT:
                        if(ship.getX() - speed < this.x){
                            getShip().translate(this.x - ship.getX(), 0);
                            dir.setEnabled(false);
                            continue;
                        }
                        break;
                    case UP:
                        if(ship.getY() - speed < this.y){
                            getShip().translate(0, this.y - ship.getY());
                            dir.setEnabled(false);
                            continue;
                        }
                        break;
                    case DOWN:
                        if(ship.getY() + ship.getHeight() + speed > this.y + this.height){
                            getShip().translate(0, ((this.height + this.y) - (ship.getY() + ship.getHeight())));
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
