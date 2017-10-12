package org.academiadecodigo.bootcamp.spacebadjoraz.Tests;

import org.academiadecodigo.bootcamp.spacebadjoraz.Game;
import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;

import java.util.List;

/**
 * MIT License
 * (c) 2017 Ricardo Constantino
 */

public class Tester {
    public static void main(String[] args) {
        Game game = new Game();

        assertCondition("Game creation", gameCreation(game));
        assertCondition("objects created", objectsCreation(game));
    }

    private static TestResult gameCreation(Game game) {
        game.init();

        List<Shape> shapes = Canvas.getInstance().getShapes();

        if (shapes.size() != 3) {
            return new TestResult(false, "There should be a background, playership and enemyship");
        }

        Shape gridBorder = shapes.toArray(new Shape[shapes.size()])[0];
        if (!(gridBorder instanceof Rectangle)) {
            return new TestResult(false, "grid shape should be of type rectangle");
        }

        if (!((Rectangle) gridBorder).isFilled()) {
            return new TestResult(false, "grid should be filled");
        }

        if (game.getLeftEdge() != game.PADDING || game.getTopEdge() != game.PADDING) {
            return new TestResult(false, "incorrect grid position not accounting with necessary canvas padding");
        }

        if (game.getRightEdge() != game.WIDTH + game.PADDING ||
                game.getBottomEdge() != game.HEIGHT + game.PADDING) {
            return new TestResult(false, "incorrect grid dimensions");
        }

        return new TestResult(true, null);
    }

    private static TestResult objectsCreation(Game game) {
        List<Shape> shapes = Canvas.getInstance().getShapes();

        Shape enemyShape = shapes.toArray(new Shape[shapes.size()])[1];
        if (!(enemyShape instanceof Rectangle)) {
            return new TestResult(false, "enemy shape should be of type rectangle");
        }

        Shape playerShape = shapes.toArray(new Shape[shapes.size()])[2];
        if (!(playerShape instanceof Rectangle)) {
            return new TestResult(false, "player shape should be of type rectangle");
        }

        return new TestResult(true, null);
    }

    private static void assertCondition(String test, TestResult result) {

        System.out.println(test + ": " + (result.pass ? "OK" : "FAIL"));
        if (!result.pass) {
            System.out.println(" => " + result.message);
        }

    }

    private static class TestResult {

        public boolean pass = true;
        public String message;

        public TestResult(boolean pass, String message) {
            this.pass = pass;
            this.message = message;
        }
    }

}
