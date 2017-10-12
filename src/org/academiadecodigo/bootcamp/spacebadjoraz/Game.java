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
     * Contains the shapes/images of the Explosions
     */
    private LinkedList<Ellipse> explosions;

    /**
     * The background for the game
     */
    private Rectangle canvas;
    private Rectangle background;
    private Rectangle playerInfo;
    private Rectangle enemyinfo;
    private Position gameLimits;

    private Text plNameText;
    private Text plHealthText;
    private Text enNameText;
    private Text enHealthText;
    private Rectangle introBackground;

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

        for (ShipFactory.Level enemy : ShipFactory.Level.values()) {
            enemies.add(ShipFactory.createEnemy(gameLimits, enemy));
        }

        this.enemy = enemies.removeFirst();

        ships.add(this.player);
        ships.add(this.enemy);

        shootables.addAll(ships);
        explosions = new LinkedList<>();

        enemy.getPic().draw();
        player.getPic().draw();

        setShipInfo();
        updateShipInfo(enemy);
        updateShipInfo(player);

        this.introBackground = new Rectangle(PADDING, PADDING, WIDTH, HEIGHT);
        introBackground.setColor(Color.BLACK);
        introBackground.fill();
    }

    public void gameIntro() {

        Text t = new Text(300, 350, "In the beginning of times there was only God.");
        Text t1 = new Text(300, 400, "Then wild aliens appeared,");
        Text t2 = new Text(300, 450, "but luckily God also created a brave warrior,");
        Text t3 = new Text(300, 500, "to defend the universe.");
        t2.setColor(Color.GREEN);
        t2.draw();
        t2.grow(20, 10);
        t.setColor(Color.GREEN);
        t.draw();
        t.grow(20, 10);
        t1.setColor(Color.GREEN);
        t1.draw();
        t1.grow(20, 10);
        t3.setColor(Color.GREEN);
        t3.draw();
        t3.grow(20, 10);

        while (t3.getY() > -30) {
            t.translate(0, -1);
            t1.translate(0, -1);
            t2.translate(0, -1);
            t3.translate(0, -1);

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        introBackground.delete();
        t.delete();
        t1.delete();
        t2.delete();
        t3.delete();
    }


    /**
     * The game loop
     * It runs until there's no enemies.
     */
    public void play() throws InterruptedException {
        gameIntro();
        while (enemy != null && player != null) {
            enemy.move();
            player.move();

            getBullets();

            for (Ellipse explosion : explosions) {
                explosion.delete();
            }

            Iterator<Bullet> bulletIterator = bullets.listIterator();

            while (bulletIterator.hasNext() && enemy != null && player != null) {
                Bullet b = bulletIterator.next();
                b.move();

                for (Ship s : ships) {
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

            if (enemy == null) {
                if (!enemies.isEmpty()) {
                    Thread.sleep(1000);
                    enemy = enemies.removeFirst();
                    enemy.getPic().draw();
                    ships.add(enemy);
                    shootables.add(enemy);
                    updateShipInfo(enemy);
                }
            }
        }


        //Player lose
        if (player == null) {
            Text t = new Text(400, 300, "YOU LOSE!");
            t.grow(50, 50);
            t.setColor(Color.RED);
            t.draw();
            while (true) {
                if (t.getWidth() > background.getWidth() ||
                        t.getHeight() > background.getHeight()) {
                    break;
                }
                t.grow(10, 10);
                Thread.sleep(33);
            }
            Thread.sleep(500);
            System.exit(0);
        }

        //Player win
        Text t = new Text(400, 300, "YOU WIN!");
        t.grow(50, 50);
        t.setColor(Color.GREEN);
        t.draw();
        while (true) {
            if (t.getWidth() > background.getWidth() ||
                    t.getHeight() > background.getHeight()) {
                break;
            }
            t.grow(10, 10);
            Thread.sleep(33);
        }
        Thread.sleep(500);
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
            } catch (NoBullet ignored) {
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
     *
     * @param shootable
     * @param b
     */
    public void hit(Ship shootable, Bullet b) {
        int health = shootable.hit(b.getBulletPower());
        b.getBullet().delete();

        Position x = shootable.getPosition();
        explosion(x);

        // If the heath goes to zero kills the ship
        if (health <= 0) {
            if (shootable instanceof PlayerShip) {
                player = null;
                return;
            }
            enemy = null;
            shootable.getShip().delete();
            shootables.remove(shootable);
            ships.remove(shootable);
            shootable.getPic().delete();
        }
    }

    /**
     * creates the explosion
     *
     * @param pos
     */
    public void explosion(Position pos) {

        Ellipse explosion = new Ellipse(pos.getX(), pos.getY(), 50, 50);
        explosion.setColor(Color.ORANGE);
        explosion.fill();
        explosions.add(explosion);
    }

    /**
     * Update the ships info on the sides of the screen
     *
     * @param ship The ship to update the information
     */
    public void updateShipInfo(Ship ship) {

        if (ship instanceof PlayerShip) {
            plNameText.setText(ship.getName());
            plNameText.draw();
            plHealthText.setText(ship.getPercentageHealth());
            plHealthText.draw();
        } else {
            enHealthText.setText(ship.getPercentageHealth());
            enHealthText.draw();
            enNameText.setText(ship.getName());
            enNameText.draw();
        }

    }

    private void setShipInfo() {

        plNameText = player.getTextName();
        plNameText.translate(playerInfo.getX() + 10, playerInfo.getHeight() + playerInfo.getY() - 100);
        plNameText.grow(0, 6);
        plNameText.setColor(Color.RED);

        plHealthText = player.getTextHealth();
        plHealthText.translate(playerInfo.getX() + 10, playerInfo.getHeight() + playerInfo.getY() - 60);
        plHealthText.grow(0, 5);
        plHealthText.setColor(Color.WHITE);

        enNameText = enemy.getTextName();
        enNameText.translate(enemyinfo.getX() + 10, enemyinfo.getHeight() + enemyinfo.getY() - 100);
        enNameText.grow(0, 6);
        enNameText.setColor(Color.RED);

        enHealthText = enemy.getTextHealth();
        enHealthText.translate(enemyinfo.getX() + 10, enemyinfo.getHeight() + enemyinfo.getY() - 60);
        enHealthText.grow(0, 5);
        enHealthText.setColor(Color.WHITE);

    }
}

// TODO: Create a fortress colidable, with health
