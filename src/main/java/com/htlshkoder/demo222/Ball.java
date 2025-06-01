package com.htlshkoder.demo222;

import javafx.scene.shape.Circle;

/**
 * Represents the ball used in the Pong game.
 * Handles the ball's movement, collision detection, and speed changes.
 */
public class Ball {

    private Circle shape;
    private double startVelocity = 2;
    private double velocityX = startVelocity;
    private double velocityY = startVelocity;
    private boolean movingLeft = false;
    private boolean movingRight = true;

    /**
     * Constructs a Ball object using the given JavaFX Circle shape.
     *
     * @param b the Circle shape representing the ball.
     */
    public Ball(Circle b) {
        this.shape = b;
    }

    /**
     * @return the right boundary (max X) of the ball.
     */
    public double getRightBounds() {
        return shape.getBoundsInParent().getMaxX();
    }

    /**
     * @return the left boundary (min X) of the ball.
     */
    public double getLeftBounds() {
        return shape.getBoundsInParent().getMinX();
    }

    /**
     * @return the top boundary (min Y) of the ball.
     */
    public double getTopBounds() {
        return shape.getBoundsInParent().getMinY();
    }

    /**
     * @return the bottom boundary (max Y) of the ball.
     */
    public double getBottomBounds() {
        return shape.getBoundsInParent().getMaxY();
    }

    /**
     * Moves the ball according to its current velocity.
     */
    public void move() {
        shape.setCenterX(shape.getCenterX() + velocityX);
        shape.setCenterY(shape.getCenterY() + velocityY);
    }

    /**
     * Reverses the ball's horizontal direction and updates movement flags.
     */
    public void reverseX() {
        velocityX = -velocityX;
        movingLeft = velocityX < 0;
        movingRight = velocityX > 0;
    }

    /**
     * Reverses the ball's vertical direction.
     */
    public void reverseY() {
        velocityY = -velocityY;
    }

    /**
     * Resets the ball's position to the center (0,0) and restores initial speed and direction.
     */
    public void reset() {
        shape.setCenterX(0);
        shape.setCenterY(0);

        if (velocityX > 0) {
            velocityX = -startVelocity;
            movingLeft = true;
            movingRight = false;
        } else {
            velocityX = startVelocity;
            movingLeft = false;
            movingRight = true;
        }

        velocityY = (velocityY > 0) ? -startVelocity : startVelocity;
    }

    /**
     * @return true if the ball has moved beyond the top or bottom of the screen.
     */
    public boolean isOutTopBottom() {
        return shape.getCenterY() < -435 || shape.getCenterY() > 435;
    }

    /**
     * @return true if the ball has moved beyond the left edge of the screen.
     */
    public boolean isOutLeft() {
        return shape.getCenterX() < -570;
    }

    /**
     * @return true if the ball has moved beyond the right edge of the screen.
     */
    public boolean isOutRight() {
        return shape.getCenterX() > 570;
    }

    /**
     * Increases the ball's speed in both X and Y directions.
     */
    public void raiseSpeed() {
        velocityX = Math.signum(velocityX) * (Math.abs(velocityX) + 0.2);
        velocityY = Math.signum(velocityY) * (Math.abs(velocityY) + 0.2);
    }

    /**
     * Prints the current velocity of the ball to the console.
     */
    public void printSpeed() {
        System.out.println("X: " + velocityX + " Y: " + velocityY);
    }

    /**
     * @return the JavaFX shape (Circle) representing the ball.
     */
    public Circle getShape() {
        return shape;
    }

    /**
     * Checks whether the ball collides with the given paddle.
     *
     * @param paddle the paddle to check collision against.
     * @return true if the ball intersects with the paddle's bounds.
     */
    public boolean collidesWith(Paddle paddle) {
        return shape.getBoundsInParent().intersects(paddle.getBounds());
    }

    /**
     * @return true if the ball is moving to the left.
     */
    public boolean isMovingLeft() {
        return movingLeft;
    }

    /**
     * @return true if the ball is moving to the right.
     */
    public boolean isMovingRight() {
        return movingRight;
    }

    /**
     * Checks if the ball hit the top or bottom edge of a paddle.
     * This can be used to change the vertical direction upon edge collision.
     *
     * @param paddle the paddle to check against.
     * @return true if the ball hit the top or bottom of the paddle.
     */
    public boolean ballHitsTopOrBottomOfPaddle(Paddle paddle) {
        boolean hitTop = false;
        boolean hitBottom = false;

        if (this.getBottomBounds() >= paddle.getTopBounds() && this.getBottomBounds() < paddle.getTopBounds() + 2) {
            hitTop = true;
        } else if (this.getTopBounds() < paddle.getBottomBounds() && this.getTopBounds() > paddle.getBottomBounds() - 2) {
            hitBottom = true;
        }

        return hitTop || hitBottom;
    }
}