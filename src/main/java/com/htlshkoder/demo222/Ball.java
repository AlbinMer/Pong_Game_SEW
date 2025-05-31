package com.htlshkoder.demo222;
import javafx.scene.shape.Circle;
public class Ball {

    private Circle shape;
    private double startVelocity = 2;
    private double velocityX = startVelocity;
    private double velocityY = startVelocity;
    private boolean movingLeft = false;
    private boolean movingRight = true;

    public Ball(Circle b) {
        this.shape = b;
    }

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

    public void move() {
        shape.setCenterX(shape.getCenterX() + velocityX);
        shape.setCenterY(shape.getCenterY() + velocityY);

    }

    public void reverseX() {
        velocityX = -velocityX;
        movingLeft = velocityX < 0;
        movingRight = velocityX > 0;
    }

    public void reverseY() {
        velocityY = -velocityY;
    }

    public void reset() {
        shape.setCenterX(0);
        shape.setCenterY(0);

        if(velocityX > 0) {
            velocityX = -startVelocity;
            movingLeft = true;
            movingRight = false;
        }
        else {
            velocityX = startVelocity;
            movingLeft = false;
            movingRight = true;
        }

        if(velocityY > 0) {
            velocityY = -startVelocity;
        }
        else velocityY = startVelocity;

    }

    public boolean isOutTopBottom() {
        return shape.getCenterY() < -435 || shape.getCenterY() > 435;
    }

    public boolean isOutLeft() {
        return shape.getCenterX() < -570;
    }

    public boolean isOutRight() {
        return shape.getCenterX() > 570;
    }

    public void raiseSpeed(){
        velocityX = Math.signum(velocityX) * (Math.abs(velocityX) + 0.2);
        velocityY = Math.signum(velocityY) * (Math.abs(velocityY) + 0.2);
    }

    public void printSpeed() {
        System.out.println("X: "+ velocityX + " Y: "+ velocityY);
    }

    public Circle getShape() {
        return shape;
    }

    public boolean collidesWith(Paddle paddle) {
        return shape.getBoundsInParent().intersects(paddle.getBounds());
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public boolean ballHitsTopOrBottomOfPaddle(Paddle paddle) {
        // Get ball center coordinates and radius
        boolean hitTop = false;
        boolean hitBottom = false;

        if(this.getBottomBounds() >= paddle.getTopBounds() && this.getBottomBounds() < paddle.getTopBounds() + 2){
            hitTop = true;
        }

        else if(this.getTopBounds() < paddle.getBottomBounds() && this.getTopBounds() > paddle.getBottomBounds() - 2){
            hitBottom = true;
        }

        return hitTop || hitBottom;
    }
}