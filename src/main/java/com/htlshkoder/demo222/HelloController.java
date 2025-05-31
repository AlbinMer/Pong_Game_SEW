package com.htlshkoder.demo222;

import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Label;


public class HelloController {

    @FXML
    private Rectangle leftPaddle;
    @FXML
    private Rectangle rightPaddle;
    @FXML
    private Circle ball;
    @FXML
    private Label welcomeText;
    @FXML
    private Label gameOverLabel;

    private Ball gameBall;
    private Paddle player1;
    private Paddle player2;
    private Scoreboard scoreBoard;

    private AnimationTimer gameLoop;

    @FXML
    public void initialize() {

        gameBall = new Ball(ball);
        player1 = new Paddle(leftPaddle, KeyCode.W, KeyCode.S);
        player2 = new Paddle(rightPaddle, KeyCode.UP, KeyCode.DOWN);
        scoreBoard = new Scoreboard(welcomeText);
        gameOverLabel.setVisible(false);
        gameOverLabel.setManaged(false);

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        gameLoop.start();

    }
    private void update() {
        player1.update();
        player2.update();
        gameBall.move();

        // Ball collision with LEFT Paddle
        if(gameBall.isMovingRight() && gameBall.getRightBounds() >= player2.getLeftBounds()-20){
            if (gameBall.collidesWith(player2)) {
                if (!gameBall.ballHitsTopOrBottomOfPaddle(player2)) {
                    gameBall.reverseX();
                    gameBall.raiseSpeed();
                    gameBall.printSpeed();
                } else {
                    gameBall.reverseY();
                    gameBall.reverseX();
                    gameBall.raiseSpeed();
                    gameBall.printSpeed();
                }
            }
        }
        // Ball collision with RIGHT Paddle
        if(gameBall.isMovingLeft() && gameBall.getLeftBounds() <= player1.getRightBounds()+20){
            if (gameBall.collidesWith(player1)) {
                if (!gameBall.ballHitsTopOrBottomOfPaddle(player1)) {
                    gameBall.reverseX();
                    gameBall.raiseSpeed();

                } else {
                    gameBall.reverseY();
                    gameBall.reverseX();
                    gameBall.raiseSpeed();

                }
            }
        }

        // Ball collision with top and bottom walls
        if (gameBall.isOutTopBottom()) {
            gameBall.reverseY();
        }

        // Scoring and Reset
        if (gameBall.isOutLeft()) {
            scoreBoard.updateScore(0, 1);
            gameBall.reset();
        }

        if (gameBall.isOutRight()) {
            scoreBoard.updateScore(1, 0);
            gameBall.reset();
        }

        if (scoreBoard.getScore(0) >= 7 || scoreBoard.getScore(1) >= 7) {
            gameLoop.stop(); // Stop the game loop
            String winner = (scoreBoard.getScore(0) >= 7) ? "Left Player" : "Right Player";
            showGameOver(winner + " wins!");
        }
    }

    @FXML
    public void onKeyPressed(KeyEvent event) {
        player1.handleKeyPress(event.getCode());
        player2.handleKeyPress(event.getCode());
    }

    @FXML
    public void onKeyReleased(KeyEvent event) {
        player1.handleKeyRelease(event.getCode());
        player2.handleKeyRelease(event.getCode());
    }

    public void showGameOver(String message) {
        gameOverLabel.setText(message);
        gameOverLabel.setVisible(true);
        gameOverLabel.setManaged(true);
        gameBall.getShape().setVisible(false);
    }
}