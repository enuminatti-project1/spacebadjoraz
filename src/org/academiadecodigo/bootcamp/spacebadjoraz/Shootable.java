package org.academiadecodigo.bootcamp.spacebadjoraz;

import org.academiadecodigo.bootcamp.spacebadjoraz.Exceptions.NoBullet;
import org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects.Bullet;

/**
 * Created by Someone who is not me on 09/10/17.
 */
public interface Shootable {

    /**
     * Any object that can shoot will implement this.
     *
     * This can remain unusable for now until the
     * enemies learn how to shoot too.
     */
    void shoot();

    /**
     * Stop shooting.
     */
    void stopShooting();

    /**
     * If something is shooting this retrieves the bullet shot.
     *
     * @return the shot Bullet.
     */
    Bullet getBullet() throws NoBullet;

    int hit(int bulletPower);
}
