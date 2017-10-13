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

    public Ship(Rectangle ship, Position gameLimits,
                int speed, int health, ShipType type) {
        this.ship = ship;
        this.limits = gameLimits;
        this.speed = speed;
        this.maxHealth = health;
        this.health = health;
        this.ship.setColor(type.getColor());
        this.ship.fill();
        setPic(ship.getX(), ship.getY());
    }

    protected void setShip(String name, String picPath) {
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

    @Override
    public int hit(int bulletPower){
        health -= bulletPower;
        return health;
    }

    /**
     * Moves the Ship on the direction set by Direction ENUM values
     */

    public abstract void move();
}
