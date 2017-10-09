package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

import org.academiadecodigo.bootcamp.spacebadjoraz.Movable;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public class Bullet implements Movable {

    /**
     * Speed the bullet travels at.
     *
     * TODO: different bullets could move at different speeds
     */
    private int speed = 1;


    /**
     * Direction the bullet travels to
     *
     * TODO: bullets can move diagonally
     */
    private boolean shootUp;

    /**
     * Constructor for bullet used by PlayerShip and, in the future, EnemyShip
     *
     * @param shootUp True if bullet shoots up, False if it shoots down
     */
    public Bullet(boolean shootUp) {
        this.shootUp = shootUp;
    }

    /**
     * Make the bullet move
     */
    @Override
    public void move () {
        if(shootUp){
            //bullet goes up
        }
        //bullet goes down
    }
}
