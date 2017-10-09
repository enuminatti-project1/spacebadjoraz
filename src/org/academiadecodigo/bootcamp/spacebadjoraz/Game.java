package org.academiadecodigo.bootcamp.spacebadjoraz;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public class Game {

    private EnemyShip enemy;
    private PlayerShip player;
    private Bullet bullet;
    private Rectangle background;

    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        game.play();
    }

    public Game() {

    }

    public void init() {
        background = new Rectangle(10, 10, 800, 600);
        background.setColor(Color.BLACK);
        background.fill();
        this.enemy = ShipFactory.createEnemy();
        this.player = new PlayerShip((int)(background.getWidth()/2.0), 500);
    }

    public void play() {
        while (true) {
            enemy.move();
            player.move();
            if (bullet != null) {
                bullet.move();
            }
            getBullet();
        }
    }

    public void getBullet() {
        this.bullet = player.getBullet();
    }

}
