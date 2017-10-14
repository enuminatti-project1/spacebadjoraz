package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class StartScreen implements KeyboardHandler {

    private boolean keypressed = false;

    public void start() throws InterruptedException {
        Keyboard key = new Keyboard(this);
        KeyboardEvent k = new KeyboardEvent();
        k.setKey(KeyboardEvent.KEY_SPACE);
        k.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        key.addEventListener(k);

        Picture startScreen = new Picture(10,10,"resources/img/spaceStart.png");
        Picture noStartScreen = new Picture(10,10,"resources/img/spaceNoStart.png");


        while (!keypressed){
            startScreen.draw();
            noStartScreen.delete();
            Thread.sleep(1000);
            startScreen.delete();
            noStartScreen.draw();
            Thread.sleep(1000);
        }

        startScreen.delete();
        noStartScreen.delete();

        Picture p = new Picture(10, 10, "resources/img/gameintro.jpg");
        p.draw();

        Thread.sleep(10000);

        p.delete();
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        keypressed = true;

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
