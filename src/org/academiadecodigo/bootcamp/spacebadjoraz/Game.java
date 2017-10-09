package org.academiadecodigo.bootcamp.spacebadjoraz;

import org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects.Bullet;
import org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects.EnemyShip;
import org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects.PlayerShip;
import org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects.ShipFactory;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

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
     * This contains the bullet the player shoots.
     * For now, this can just contain a single bullet.
     */
    private Bullet bullet;

    /**
     * The background for the game
     */
    private Rectangle background;

    /**
     * Entry door to the game :'D
     *
     * @param args
     */
    public static void main(String[] args) {
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
        background = new Rectangle(10, 10, 800, 600);
        background.setColor(Color.BLACK);
        background.fill();

        this.enemy = ShipFactory.createEnemy();
        this.player = new PlayerShip((int)(background.getWidth()/2.0), 500);
    }


    /**
     * The game loop
     * It runs until there's no enemies.
     */
    public void play() {
        while (enemy != null) {
            enemy.move();
            player.move();
            if (bullet != null) {
                bullet.move();
            }
            getBullet();
        }
    }

    /**
     * This gets the player's bullet and saves it so
     * it can move the bullet.
     */
    public void getBullet() {
        this.bullet = player.getBullet();
    }

}
