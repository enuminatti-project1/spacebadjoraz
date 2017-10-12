package org.academiadecodigo.bootcamp.spacebadjoraz;

import org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects.*;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;

import java.util.LinkedList;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public class Game {

    /**
     * Where the enemy will reside
     */
    private EnemyShip enemy;


    /**
     * Where the player resides
     */
    private PlayerShip player;

    /**
     * This will contain all the bullets shot.
     */
    private LinkedList<Bullet> bullets = new LinkedList<>();

    /**
     * The background for the game
     */
    private Rectangle background;

    private Position gameLimits;

    private Rectangle introBackground;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int PADDING = 10;

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
        background = new Rectangle(PADDING, PADDING, WIDTH, HEIGHT);
        background.setColor(Color.BLACK);
        background.fill();

        gameLimits = new Position(background.getX(), background.getY(),
                background.getWidth(), background.getHeight());

        this.enemy = ShipFactory.createEnemy(background);
        this.player = new PlayerShip(background);

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
        while (enemy != null) {
            enemy.move();
            player.move();

            for (Bullet b : bullets) {
                b.move();
                if (b.getPosition().isInside(enemy.getPosition())) {
                    Position x = enemy.getPosition();
                    enemy.getShip().delete();
                    b.getBullet().delete();
                    Ellipse e = new Ellipse(enemy.getPosition().getX(), enemy.getPosition().getY(), 50, 50);
                    e.setColor(Color.ORANGE);
                    e.fill();
                    System.out.println("bum!");
                    enemy = null;
                    break;

                }
            }
            getBullet();

            Thread.sleep(33);
        }
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
     * This gets the player's bullet and saves it so
     * it can move the bullet.
     */
    public void getBullet() {
        Bullet newBullet = player.getBullet();
        if (newBullet != null) {
            bullets.add(newBullet);
        }
    }

    /**
     * Find out if a certain position is still within the game limits
     *
     * @param pos Position to check
     * @return True if Position is still inside the game limits
     */
    public boolean insideGame(Position pos) {
        return gameLimits.getLeft() <= pos.getLeft() &&
                gameLimits.getRight() >= pos.getRight() &&
                gameLimits.getTop() <= pos.getTop() &&
                gameLimits.getBottom() >= pos.getBottom();
    }

}

// TODO: Create a fortress colidable, with health
