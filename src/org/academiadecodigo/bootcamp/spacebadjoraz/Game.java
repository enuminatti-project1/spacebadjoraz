package org.academiadecodigo.bootcamp.spacebadjoraz;

import org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects.*;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

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

            for (Bullet b : bullets ) {
                b.move();
                if(b.getPosition().isInside(enemy.getPosition())){
                    Position x = enemy.getPosition();
                    enemy.getShip().delete();
                    b.getBullet().delete();
                    Ellipse e = new Ellipse(enemy.getPosition().getX(), enemy.getPosition().getY(), 50,50);
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
        System.out.println("we win");
    }

    /**
     * This gets the player's bullet and saves it so
     * it can move the bullet.
     */
    public void getBullet() {
        Bullet newBullet = player.getBullet();
        if(newBullet != null) {
            bullets.add(newBullet);
        }
    }

    public int getRightEdge() {
        return background.getX() + background.getWidth();
    }

    public int getBottomEdge() {
        return background.getY() + background.getHeight();
    }

    // TODO: Create a fortress colidable, with health

}
