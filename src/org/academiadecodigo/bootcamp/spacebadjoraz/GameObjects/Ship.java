package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

import org.academiadecodigo.bootcamp.spacebadjoraz.Movable;
import org.academiadecodigo.bootcamp.spacebadjoraz.Shootable;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public abstract class Ship implements Movable, Shootable {
    // TODO: add common behavior between PlayerShip and EnemyShips

    private Rectangle ship;
    private Position limits;
    private Picture pic;
    private String picPath;
    private int speed;
    private int health;
    private int maxHealth;
    private String name;
    private Text textName;
    private Text textHealth;

    /**
     *
     * @param ship Rectangle with the temporary aspect of the ship
     */
    protected void setShip(Rectangle ship, int health, String name, String picPath) {
        this.ship = ship;
        this.maxHealth = health;
        this.health = health;
        this.name = name;
        this.textName = new Text(0,0,name);
        this.textHealth = new Text(0,0,String.valueOf(getPercentageHealth()));
        this.picPath = picPath;
    }

    /**
     *
     * @return The Rectangle
     */
    public Rectangle getShip() {
        return ship;
    }

    public String getPicPath() {
        return picPath;
    }

    public Picture getPic() {
        return pic;
    }

    public void setPic(int x ,int y) {
        this.pic = new Picture(x, y, this.picPath);
        pic.draw();
    }

    public String getName(){
        return this.name;
    }

    public int getHealth(){
        return this.health;
    }

    public int getPercentageHealth(){
        return (int)(((double)health / (double)maxHealth) * 100);
    }

    public void setTextName (Text t){
        this.textName = t;
    }

    public Text getTextName(){
        return textName;
    }

    public void setTextHealth (Text t){
        this.textName = t;
    }

    public Text getTextHealth(){
        return textHealth;
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

    public int hit(int bulletPower){
        health -= bulletPower;
        return health;
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
                getPic().translate(dir.getX() * speed, dir.getY() * speed);
            }
        }
    }
}
