package org.academiadecodigo.bootcamp.spacebadjoraz;

import org.academiadecodigo.bootcamp.spacebadjoraz.Exceptions.NoBullet;
import org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects.*;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public class Game {

    /**
     * Where the enemy will reside
     */
    private Ship enemy;


    /**
     * Where the player resides
     */
    private Ship player;

    private LinkedList<Shootable> shootables = new LinkedList<>();

    private LinkedList<Ship> ships = new LinkedList<>();

    private LinkedList<Ship> enemies = new LinkedList<>();

    /**
     * This will contain all the bullets shot.
     */
    private LinkedList<Bullet> bullets = new LinkedList<>();

    /**
     * Contain the shape/ image of the Explosions
     */

    private Ellipse explosion;

    /**
     * The background for the game
     */
    private Rectangle canvas;
    private Rectangle background;
    private Rectangle playerInfo;
    private Rectangle enemyinfo;
    private Position gameLimits;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int PADDING = 10;

    public static final int PL_WIDTH = 150;
    public static final int PL_HEIGHT = 600;

    public static final int EN_WIDTH = 150;
    public static final int EN_HEIGHT = 600;


    /**
     * Entry door to the game :'D
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        game.init();
        game.play();
    }

    public Game() {

    }

    /**
     * Initialize game objects and background
     */
    public void init() {

        canvas = new Rectangle(PADDING, PADDING, WIDTH, HEIGHT);
        canvas.draw();

        background = new Rectangle(PADDING + PL_WIDTH, PADDING, WIDTH - PL_WIDTH - EN_WIDTH, HEIGHT);

        Picture back = new Picture(PADDING + PL_WIDTH, PADDING, "resources/img/background 500x600.png");
        back.draw();

        playerInfo = new Rectangle(PADDING, PADDING, PL_WIDTH, PL_HEIGHT);
        playerInfo.setColor(Color.BLACK);
        playerInfo.fill();

        enemyinfo = new Rectangle((canvas.getX() + canvas.getWidth()) - EN_WIDTH, PADDING, EN_WIDTH, EN_HEIGHT);
        enemyinfo.setColor(Color.BLACK);
        enemyinfo.fill();

        gameLimits = new Position(background.getX(), background.getY(),
                background.getWidth(), background.getHeight());

        this.player = new PlayerShip(gameLimits);

        for(ShipFactory.Level enemy : ShipFactory.Level.values()){
            enemies.add(ShipFactory.createEnemy(gameLimits, enemy));
        }

        this.enemy = enemies.removeFirst();

        ships.add(this.player);
        ships.add(this.enemy);

        updateShipInfo(ships.get(0));
        updateShipInfo(ships.get(1));

        shootables.addAll(ships);

        enemy.getPic().draw();
        player.getPic().draw();

    }


    /**
     * The game loop
     * It runs until there's no enemies.
     */
    public void play() throws InterruptedException {
        while (enemy != null && player !=null) {
            enemy.move();
            player.move();

            getBullets();

            if (explosion != null) {
                explosion.delete();
            }

            Iterator<Bullet> bulletIterator = bullets.listIterator();

            while (bulletIterator.hasNext() && enemy != null && player != null) {
                Bullet b = bulletIterator.next();
                b.move();

                for(Ship s : ships  ){
                    if (b.getPosition().isInside(s.getPosition())) {
                        hit(s, b);
                        bulletIterator.remove();
                        updateShipInfo(s);
                    }
                }
                if (b.getPosition().isInside(gameLimits, b.getSpeed())) {
                    b.getBullet().delete();
                    bulletIterator.remove();
                }
            }

            Thread.sleep(33);

            if (enemy == null){
                if (!enemies.isEmpty()) {
                    Thread.sleep(1000);
                    enemy = enemies.removeFirst();
                    enemy.getPic().draw();
                    ships.add(enemy);
                    updateShipInfo(enemy);
                }
            }
        }



        //Player loose
        if (player == null){
            Text t = new Text(400, 300, "YOU LOOSE!");
            t.grow(50, 50);
            t.setColor(Color.RED);
            t.draw();
            System.out.println("we win");
            while (true) {
                if (t.getWidth() > background.getWidth() ||
                        t.getHeight() > background.getHeight()) {
                    break;
                }
                t.grow(10, 10);
                Thread.sleep(33);
            }
            System.exit(0);
        }

        //Player win
        Text t = new Text(400, 300, "YOU WIN!");
        t.grow(50, 50);
        t.setColor(Color.GREEN);
        t.draw();
        System.out.println("we win");
        while (true) {
            if (t.getWidth() > background.getWidth() ||
                    t.getHeight() > background.getHeight()) {
                break;
            }
            t.grow(10, 10);
            Thread.sleep(33);
        }
        System.exit(0);
    }

    /**
     * This gets all shootables' bullets and saves them so
     * it can move them.
     */
    public void getBullets() {

        for (Shootable shootable : shootables) {
            if (shootable == null) {
                continue;
            }

            try {
                Bullet newBullet = shootable.getBullet();
                newBullet.setLimits(gameLimits);
                bullets.add(newBullet);
            }
            catch (NoBullet ignored) {
            }
        }

    }

    /**
     * Find out if a certain position is still within the game limits
     *
     * @param pos Position to check
     * @return True if Position is still inside the game limits
     */
    public boolean insideGame(Position pos) {
        return gameLimits.getX() <= pos.getX() &&
                gameLimits.getMaxX() >= pos.getMaxX() &&
                gameLimits.getY() <= pos.getY() &&
                gameLimits.getMaxY() >= pos.getMaxY();
    }

    /**
     * Deletes the bullet on impact and call the method to make the explosion
     * @param shootable
     * @param b
     */
    public void hit(Ship shootable, Bullet b) {
        int health = shootable.hit(b.getBulletPower());
        b.getBullet().delete();

        Position x = shootable.getPosition();
        explosion(x);

        // If the heath goes to zero kills the ship
        if(health <= 0) {
            if (shootable instanceof EnemyShip){
                enemy = null;
            }
            shootable.getShip().delete();
            ships.remove(shootable);
            shootable.getPic().delete();
            //shootable = null;
            System.out.println("bum!");
        }
    }

    /**
     * creates the explosion
     * @param pos
     */
    public void explosion(Position pos) {

        explosion = new Ellipse(pos.getX(), pos.getY(), 50, 50);
        explosion.setColor(Color.ORANGE);
        explosion.fill();
    }

    /**
     * Update the ships info on the sides of the screen
     * @param ship The ship to update the information
     */
    public void updateShipInfo(Ship ship){

        if (ship.getTextHealth() != null && ship.getTextName() != null){
            ship.getTextName().delete();
            ship.getTextHealth().delete();
        }

        Rectangle r;
        if (ship instanceof EnemyShip){
            r = enemyinfo;
        } else {
            r = playerInfo;
        }

        Text temptext = new Text(r.getX() + 10, r.getHeight() + r.getY() - 100, ship.getName());
        temptext.grow(0, 6);
        temptext.setColor(Color.RED);
        temptext.draw();
        ship.setTextName(temptext);


        Text temptext2 = new Text(r.getX() + 10, r.getHeight() + r.getY() - 60, String.valueOf(ship.getPercentageHealth()) + " %");
        temptext2.grow(0, 5);
        temptext2.setColor(Color.WHITE);
        temptext2.draw();
        ship.setTextHealth(temptext2);
    }
}

// TODO: Create a fortress colidable, with health
