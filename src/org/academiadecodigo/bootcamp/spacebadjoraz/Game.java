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
        background.setColor(Color.BLUE);
        background.fill();

        playerInfo = new Rectangle(PADDING, PADDING, PL_WIDTH, PL_HEIGHT);
        playerInfo.setColor(Color.BLACK);
        playerInfo.fill();

        enemyinfo = new Rectangle((canvas.getX()+canvas.getWidth()) - EN_WIDTH, PADDING, EN_WIDTH, EN_HEIGHT);
        enemyinfo.setColor(Color.BLACK);
        enemyinfo.fill();


        gameLimits = new Position(background.getX(), background.getY(),
                background.getWidth(), background.getHeight());

        this.enemy = ShipFactory.createEnemy(background);
        this.player = new PlayerShip(background);
    }


    /**
     * The game loop
     * It runs until there's no enemies.
     */
    public void play() throws InterruptedException {
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
        return gameLimits.getX() <= pos.getX() &&
                gameLimits.getMaxX() >= pos.getMaxX() &&
                gameLimits.getY() <= pos.getY() &&
                gameLimits.getMaxY() >= pos.getMaxY();
    }

}

// TODO: Create a fortress colidable, with health
