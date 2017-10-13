package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public class ShipFactory {
    /**
     * This generates a new enemy.
     * TODO: generate different enemies
     *
     * @return created enemy
     */
    public static EnemyShip createEnemy(Position gameArea){

        return new EnemyShip(gameArea, 20, "Teachers", "resources/img/java.png");

    }
}
