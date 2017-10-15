package org.academiadecodigo.bootcamp.spacebadjoraz;

import org.academiadecodigo.bootcamp.Sound;
import org.academiadecodigo.bootcamp.spacebadjoraz.Exceptions.NoBullet;
import org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects.*;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;
import java.util.HashMap;
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
    private HashMap<String, ArrayList<Picture>> explosionsPic;

    private HashMap<GameSound, Sound> soundsMap;

    /**
     * The background for the game
     */
    private Rectangle canvas;
    private Rectangle background;
    private Rectangle playerInfo;
    private Rectangle enemyInfo;
    private Position gameLimits;

    private Text plNameText;
    private Text plHealthText;
    private Text enNameText;
    private Text enHealthText;

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
    public void init() throws InterruptedException {

        StartScreen s = new StartScreen();
        s.start();

        canvas = new Rectangle(PADDING, PADDING, WIDTH, HEIGHT);
        canvas.draw();

        background = new Rectangle(PADDING + PL_WIDTH, PADDING, WIDTH - PL_WIDTH - EN_WIDTH, HEIGHT);

        Picture back = new Picture(PADDING + PL_WIDTH, PADDING, "resources/img/background 500x600.png");
        back.draw();

        playerInfo = new Rectangle(PADDING, PADDING, PL_WIDTH, PL_HEIGHT);
        playerInfo.setColor(Color.BLACK);
        playerInfo.fill();

        enemyInfo = new Rectangle((canvas.getX() + canvas.getWidth()) - EN_WIDTH, PADDING, EN_WIDTH, EN_HEIGHT);
        enemyInfo.setColor(Color.BLACK);
        enemyInfo.fill();

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

        explosionsPic = new HashMap<>();

        Picture playerShipHit = new Picture(0, 0, "resources/img/hit.png");
        Picture playerShipExplosion = new Picture(0, 0, "resources/img/explosion.png");
        Picture enemyShipHit = new Picture(0, 0, "resources/img/hit.png");
        Picture enemyShipExplosion = new Picture(0, 0, "resources/img/explosion.png");

        ArrayList<Picture> playerExplosions = new ArrayList<>();
        playerExplosions.add(playerShipHit);
        playerExplosions.add(playerShipExplosion);

        ArrayList<Picture> enemyExplosions = new ArrayList<>();
        enemyExplosions.add(enemyShipHit);
        enemyExplosions.add(enemyShipExplosion);

        explosionsPic.put("enemy", enemyExplosions);
        explosionsPic.put("player", playerExplosions);

        enemy.getPic().draw();
        player.getPic().draw();

        setShipInfo();
        updateShipInfo(enemy);
        updateShipInfo(player);

        createGameSounds();
    }

    /**
     * The game loop
     * It runs until there's no enemies.
     */
    public void play() throws InterruptedException {
        soundsMap.get(GameSound.FILIPEINTRO).play(true);
        soundsMap.get(GameSound.BGM).loopIndef();
        while (enemy != null && player != null) {
            enemy.move();
            player.move();

            getBullets();

            for (ArrayList<Picture> a : explosionsPic.values()) {
                for (Picture p : a) {
                    p.delete();
                }
            }

            Iterator<Bullet> bulletIterator = bullets.listIterator();

            ArrayList<Bullet> bulletsToDestroy = new ArrayList<>();

            while (bulletIterator.hasNext() && enemy != null && player != null) {
                Bullet b = bulletIterator.next();
                b.move();
                for (Ship s : ships) {
                    if (b.getPosition().isInside(s.getPosition())) {
                        hit(s, b);
                        bulletsToDestroy.add(b);
                        updateShipInfo(s);
                    }
                }
                if (b.getPosition().isInside(gameLimits, b.getSpeed())) {
                    bulletsToDestroy.add(b);
                }
            }

            for (Bullet b : bulletsToDestroy) {
                b.getBullet().delete();
                bullets.remove(b);
            }

            Thread.sleep(33);

            if (enemy == null) {
                if (!enemies.isEmpty()) {
                    enemy = enemies.removeFirst();

                    // death sounds
                    switch (enemy.getName()) {
                        case "Pedro":
                            soundsMap.get(GameSound.FILIPEDEATH).play(true);
                            break;
                        case "Jorge":
                            soundsMap.get(GameSound.BRIGHENTIDEATH).play(true);
                            break;
                        case "Catarina":
                            soundsMap.get(GameSound.JORGEDEATH).play(true);
                            break;
                        case "Ferrão":
                            soundsMap.get(GameSound.CATARINADEATH).play(true);
                            break;
                        default:
                    }
                    Thread.sleep(1000);


                    enemy.getPic().draw();

                    // intro sounds and bgms start
                    switch (enemy.getName()) {
                        case "Pedro":
                            soundsMap.get(GameSound.BRIGHENTIINTRO).play(true);
                            Thread.sleep(2000);
                            break;
                        case "Jorge":
                            // jorge intro
                            soundsMap.get(GameSound.BGM).stop();
                            soundsMap.get(GameSound.JORGEINTRO).play(true);
                            Thread.sleep(800);
                            break;
                        case "Catarina":
                            // catarina intro
                            soundsMap.get(GameSound.CATARINAINTRO).play(true);
                            Thread.sleep(700);
                            break;
                        case "Ferrão":
                            // ferrao intro
                            soundsMap.get(GameSound.BGMJORGE).stop();
                            soundsMap.get(GameSound.FERRAOINTRO).play(true);
                            Thread.sleep(1500);
                            break;
                        default:
                    }

                    ships.add(enemy);
                    shootables.add(enemy);
                    updateShipInfo(enemy);

                    // bgm
                    switch (enemy.getName()) {
                        case "Jorge":
                            soundsMap.get(GameSound.BGMJORGE).loopIndef();
                            break;
                        case "Ferrão":
                            soundsMap.get(GameSound.FERRAOBGM).loopIndef();
                            break;
                        default:
                    }
                }
            }
        }

        soundsMap.get(GameSound.BGM).stop();
        soundsMap.get(GameSound.FERRAOBGM).stop();

        //Player lose
        if (player == null) {
            Text t = new Text(400, 300, "YOU LOSE!");
            t.grow(50, 50);
            t.setColor(Color.RED);
            t.draw();
            soundsMap.get(GameSound.LOSS).play(true);
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
        soundsMap.get(GameSound.WIN).play(true);
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
                Sound newSound = soundsMap.get(GameSound.ENEMYBULLET);
                if (shootable instanceof EnemyShip &&
                        ((EnemyShip) shootable).getName().equals("Catarina")) {
                    Sound[] randomSounds = new Sound[]{
                            soundsMap.get(GameSound.CATARINAINTRO),
                            soundsMap.get(GameSound.BALELE),
                            soundsMap.get(GameSound.UNDERNIGHT)
                    };
                    newSound = randomSounds[(int) (Math.random() * randomSounds.length)];
                } else if (shootable instanceof PlayerShip) {
                    newSound = soundsMap.get(GameSound.PEW);
                }
                playIfNotPlaying(newSound);
            } catch (NoBullet ignored) {
            }
        }

    }

    public void playIfNotPlaying(Sound clip) {
        if (!clip.isPlaying()) {
            clip.play(true);
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

        explosion(shootable);

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
     * @param ship
     */
    public void explosion(Ship ship) {
        Position shipPos = ship.getPosition();
        Picture explosion;
        ArrayList<Picture> pics;

        switch (ship.getType()) {
            case ENEMY:
                pics = explosionsPic.get("enemy");
                break;
            default:
                pics = explosionsPic.get("player");
        }

        if (ship.getHealth() > 0) {
            explosion = pics.get(0);
        } else {
            explosion = pics.get(1);
        }

        explosion.translate(shipPos.getX() - explosion.getX(),
                shipPos.getY() - explosion.getY());
        explosion.draw();
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
        enNameText.translate(enemyInfo.getX() + 10, enemyInfo.getHeight() + enemyInfo.getY() - 100);
        enNameText.grow(0, 6);
        enNameText.setColor(Color.RED);

        enHealthText = enemy.getTextHealth();
        enHealthText.translate(enemyInfo.getX() + 10, enemyInfo.getHeight() + enemyInfo.getY() - 60);
        enHealthText.grow(0, 5);
        enHealthText.setColor(Color.WHITE);

    }

    private enum GameSound {
        BGM("tank"),
        BGMJORGE("imperial"),
        FERRAOBGM("sephirothalterado"),
        PEW("piu"),
        LOSS("burro"),
        FILIPEINTRO("ehpah"),
        FILIPEDEATH("filipedeath"),
        BRIGHENTIINTRO("brighentiintro"),
        BRIGHENTIDEATH("brighentideath"),
        JORGEINTRO("jorgeintro"),
        JORGEDEATH("jorgedeath"),
        CATARINAINTRO("catarinaintro"),
        CATARINADEATH("catarinadeath"),
        FERRAOINTRO("ferraointro"),
        ENEMYBULLET("pah"),
        WIN("ohnaosei"),
        BALELE("balele"),
        UNDERNIGHT("undernight");

        private String name;

        GameSound(String name) {
            this.name = name;
        }

        public String getPath() {
            return "/resources/sound/" + this.name + ".wav";
        }
    }

    private void createGameSounds() {
        soundsMap = new HashMap<>();
        for (GameSound sound : GameSound.values()) {
            soundsMap.put(sound, new Sound(sound.getPath()));
        }
    }


}

// TODO: Create a fortress colidable, with health
