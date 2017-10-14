package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public class ShipFactory {


    public enum Level{
        ONE("Filipe", "resources/img/filipe.jpg", 20, 1),
        TWO("Pedro", "resources/img/pedro.jpg", 40,2),
        TREE("Jorge", "resources/img/jorge.jpg", 60,3),
        FOUR("Catarina", "resources/img/catarina.jpg",100,4),
        FIVE("Ferrão","resources/img/ferrao.jpg",250,20);

        private String name;
        private String picPath;
        private int health;
        private int bulletPower;

        Level(String name, String picPath, int health, int bulletPower) {
            this.name = name;
            this.picPath = picPath;
            this.health = health;
            this.bulletPower = bulletPower;
        }

        public String getName() {
            return name;
        }

        public String getPicPath() {
            return picPath;
        }

        public int getHealth() {
            return health;
        }

        public int getBulletPower() {
            return bulletPower;
        }
    }
    /**
     * This generates a new enemy.
     * TODO: generate different enemies
     *
     * @return created enemy
     */
    public static EnemyShip createEnemy(Position gameArea, Level level){

        return new EnemyShip(gameArea, level.getName(), level.getPicPath(), level.getHealth(), level.getBulletPower());

    }
}
