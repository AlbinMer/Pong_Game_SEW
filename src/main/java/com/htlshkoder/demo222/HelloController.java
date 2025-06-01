package com.htlshkoder.demo222;

import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Label;

/**
 * Controller class for the Pong-like game using JavaFX.
 * Manages game logic, user inputs, collisions, and game state updates.
 */
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

    /**
     * Initializes the game objects, sets up the paddles, ball, scoreboard,
     * and starts the game loop.
     */
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

    /**
     * Main game update method called on every frame by the AnimationTimer.
     * Handles paddle movement, ball movement, collisions, scoring, and game end conditions.
     */
    private void update() {
        player1.update();
        player2.update();
        gameBall.move();

        // Ball collision with right paddle (player2)
        if (gameBall.isMovingRight() && gameBall.getRightBounds() >= player2.getLeftBounds() - 20) {
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

        // Ball collision with left paddle (player1)
        if (gameBall.isMovingLeft() && gameBall.getLeftBounds() <= player1.getRightBounds() + 20) {
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

        // Ball collision with top or bottom window boundaries
        if (gameBall.isOutTopBottom()) {
            gameBall.reverseY();
        }

        // Score updates and ball reset when ball leaves left or right side
        if (gameBall.isOutLeft()) {
            scoreBoard.updateScore(0, 1);
            gameBall.reset();
        }

        if (gameBall.isOutRight()) {
            scoreBoard.updateScore(1, 0);
            gameBall.reset();
        }

        // Game over logic: first player to reach 7 points wins
        if (scoreBoard.getScore(0) >= 7 || scoreBoard.getScore(1) >= 7) {
            gameLoop.stop();
            String winner = (scoreBoard.getScore(0) >= 7) ? "Left Player" : "Right Player";
            showGameOver(winner + " wins!");
        }
    }

    /**
     * Handles key press events and delegates to each player's input handler.
     *
     * @param event the key event triggered by the player
     */
    @FXML
    public void onKeyPressed(KeyEvent event) {
        player1.handleKeyPress(event.getCode());
        player2.handleKeyPress(event.getCode());
    }

    /**
     * Handles key release events and delegates to each player's input handler.
     *
     * @param event the key event triggered by the player
     */
    @FXML
    public void onKeyReleased(KeyEvent event) {
        player1.handleKeyRelease(event.getCode());
        player2.handleKeyRelease(event.getCode());
    }

    /**
     * Displays the game over label with the winning message and hides the ball.
     *
     * @param message the game over message to be displayed
     */
    public void showGameOver(String message) {
        gameOverLabel.setText(message);
        gameOverLabel.setVisible(true);
        gameOverLabel.setManaged(true);
        gameBall.getShape().setVisible(false);
    }
}
