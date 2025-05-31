package com.htlshkoder.demo222;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Bounds;

public class Paddle {
    private Rectangle shape;
    private KeyCode upKey, downKey;
    private boolean movingUp = false, movingDown = false;
    private final double speed = 7;


    public double getRightBounds() {
        return shape.getBoundsInParent().getMaxX();
    }

    public double getLeftBounds() {
        return shape.getBoundsInParent().getMinX();
    }

    public double getTopBounds() {
        return shape.getBoundsInParent().getMinY();
    }

    public double getBottomBounds() {
        return shape.getBoundsInParent().getMaxY();
    }

    public Paddle(Rectangle shape, KeyCode upKey, KeyCode downKey) {
        this.shape = shape;
        this.upKey = upKey;
        this.downKey = downKey;
    }

    public void handleKeyPress(KeyCode code) {
        if (code == upKey) movingUp = true;
        if (code == downKey) movingDown = true;
    }

    public void handleKeyRelease(KeyCode code) {
        if (code == upKey) movingUp = false;
        if (code == downKey) movingDown = false;
    }

    public void update() {
        if (movingUp && shape.getLayoutY() > 0) shape.setLayoutY(shape.getLayoutY() - speed);
        if (movingDown && shape.getLayoutY() < 780) shape.setLayoutY(shape.getLayoutY() + speed);
    }

    public Bounds getBounds() {
        return shape.getBoundsInParent();
    }
}