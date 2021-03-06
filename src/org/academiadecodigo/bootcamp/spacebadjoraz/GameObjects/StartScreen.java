package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;

import org.academiadecodigo.simplegraphics.keyboard.*;
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
        keypressed = false;

        startScreen.delete();
        noStartScreen.delete();

        Picture p = new Picture(10, 10, "resources/img/gameintro.jpg");
        Picture p1 = new Picture(10, 10, "resources/img/gameintrotext.jpg");

        while (!keypressed){
            p1.draw();
            p.delete();
            Thread.sleep(1800);
            p1.delete();
            p.draw();
            Thread.sleep(200);
        }
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        keypressed = true;
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
