package org.academiadecodigo.bootcamp.spacebadjoraz;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public class Bullet implements Movable{

    private int speed = 1;
    private boolean shootUp;

    public Bullet(boolean shootUp) {
        this.shootUp = shootUp;
    }

    @Override
    public void move () {
        if(shootUp){
            //bullet goes up
        }
        //bullet goes down
    }
}
