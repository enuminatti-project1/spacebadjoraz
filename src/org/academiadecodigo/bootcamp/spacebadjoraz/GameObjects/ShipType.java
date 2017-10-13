package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

import org.academiadecodigo.simplegraphics.graphics.Color;

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public enum ShipType {
    PLAYER(Color.RED),
    ENEMY(Color.BLACK);

    private Color color;

    ShipType(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
