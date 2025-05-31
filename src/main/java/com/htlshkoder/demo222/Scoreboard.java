package com.htlshkoder.demo222;

import javafx.scene.control.Label;

/**
 * Manages and displays the scores for two players using a JavaFX Label.
 */
public class Scoreboard {
    private int score1 = 0;
    private int score2 = 0;
    private Label scoreText;

    /**
     * Constructs a Scoreboard and initializes the score display.
     *
     * @param scoreText the JavaFX Label used to display the score
     */
    public Scoreboard(Label scoreText) {
        this.scoreText = scoreText;
        updateScore(0, 0);
    }

    /**
     * Updates the scores by adding the given points to each player
     * and refreshes the display.
     *
     * @param p1 points to add to player 1's score
     * @param p2 points to add to player 2's score
     */
    public void updateScore(int p1, int p2) {
        score1 += p1;
        score2 += p2;
        scoreText.setText(score1 + " : " + score2);
    }

    /**
     * Returns the score of the specified player.
     *
     * @param pos the player's index (0 for player 1, 1 for player 2)
     * @return the player's score, or 0 if an invalid index is provided
     */
    public int getScore(int pos) {
        if (pos == 0) {
            return score1;
        } else if (pos == 1) {
            return score2;
        }
        return 0;
    }
}