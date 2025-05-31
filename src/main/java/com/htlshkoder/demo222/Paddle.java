package com.htlshkoder.demo222;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Bounds;

/**
 * Represents a paddle in a game, which can be moved vertically
 * using assigned keyboard keys.
 */
public class Paddle {
    private Rectangle shape;
    private KeyCode upKey, downKey;
    private boolean movingUp = false, movingDown = false;
    private final double speed = 7;

    /**
     * Returns the X-coordinate of the right boundary of the paddle.
     * @return the right X-coordinate
     */
    public double getRightBounds() {
        return shape.getBoundsInParent().getMaxX();
    }

    /**
     * Returns the X-coordinate of the left boundary of the paddle.
     * @return the left X-coordinate
     */
    public double getLeftBounds() {
        return shape.getBoundsInParent().getMinX();
    }

    /**
     * Returns the Y-coordinate of the top boundary of the paddle.
     * @return the top Y-coordinate
     */
    public double getTopBounds() {
        return shape.getBoundsInParent().getMinY();
    }

    /**
     * Returns the Y-coordinate of the bottom boundary of the paddle.
     * @return the bottom Y-coordinate
     */
    public double getBottomBounds() {
        return shape.getBoundsInParent().getMaxY();
    }

    /**
     * Constructs a Paddle with the given shape and key controls.
     *
     * @param shape the Rectangle representing the paddle's shape
     * @param upKey the key used to move the paddle up
     * @param downKey the key used to move the paddle down
     */
    public Paddle(Rectangle shape, KeyCode upKey, KeyCode downKey) {
        this.shape = shape;
        this.upKey = upKey;
        this.downKey = downKey;
    }

    /**
     * Handles a key press event to start moving the paddle.
     *
     * @param code the pressed key code
     */
    public void handleKeyPress(KeyCode code) {
        if (code == upKey) movingUp = true;
        if (code == downKey) movingDown = true;
    }

    /**
     * Handles a key release event to stop moving the paddle.
     *
     * @param code the released key code
     */
    public void handleKeyRelease(KeyCode code) {
        if (code == upKey) movingUp = false;
        if (code == downKey) movingDown = false;
    }

    /**
     * Updates the paddle's position based on current movement state.
     */
    public void update() {
        if (movingUp && shape.getLayoutY() > 0)
            shape.setLayoutY(shape.getLayoutY() - speed);
        if (movingDown && shape.getLayoutY() < 780)
            shape.setLayoutY(shape.getLayoutY() + speed);
    }

    /**
     * Returns the current bounds of the paddle.
     *
     * @return the paddle's bounds
     */
    public Bounds getBounds() {
        return shape.getBoundsInParent();
    }
}