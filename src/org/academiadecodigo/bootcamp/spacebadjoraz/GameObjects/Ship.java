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
    private int speed;
    private int health;
    private int maxHealth;
    private int bulletPower;
    private String name;
    private Text textName;
    private Text textHealth;
    private Text textBulletPower;
    private ShipType type;

    public Ship(Rectangle ship, Position gameLimits,
                int speed, int health, ShipType type,
                String name, String picPath, int bulletPower) {
        this.ship = ship;
        this.limits = gameLimits;
        this.speed = speed;
        this.maxHealth = health;
        this.health = health;
        this.name = name;
        this.textName = new Text(0,0,name);
        this.textHealth = new Text(0,0, String.valueOf(getPercentageHealth()));
        this.bulletPower = bulletPower;
        setPic(ship.getX(), ship.getY(), picPath);
        pic.delete();
        this.textName = new Text(0,0,"Name: " + this.name);
        this.textHealth =  new Text(0,0,"Health: " + String.valueOf(getPercentageHealth()) + " %");
        this.textBulletPower = new Text(0,0,"Bullet Power: " + String.valueOf(bulletPower));
        this.type = type;
    }

    /**
     *
     * @return The Rectangle
     */
    public Rectangle getShip() {
        return ship;
    }

    public Picture getPic() {
        return pic;
    }

    public ShipType getType() {
        return type;
    }

    public void setPic(int x , int y, String picPath) {
        this.pic = new Picture(x, y, picPath);
        pic.draw();
    }

    public String getName(){
        return this.name;
    }

    public int getHealth(){
        return this.health;
    }

    public String getPercentageHealth(){
        int percentage = (int)(((double)health / (double)maxHealth) * 100);
        return String.valueOf(percentage < 0 ? 0 : percentage) + "%";
    }

    public Text getTextName(){
        return textName;
    }

    public Text getTextHealth(){
        return textHealth;
    }

    public Text getTextBulletPower() {
        return textBulletPower;
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

    public int getBulletPower() {
        return bulletPower;
    }

    /**
     * Moves the Ship on the direction set by Direction ENUM values
     */

    public abstract void move();
}
