package com.htlshkoder.demo222;

import javafx.scene.control.Label;

public class Scoreboard {
    private int score1 = 0;
    private int score2 = 0;
    private Label scoreText;

    public Scoreboard(Label scoreText) {
        this.scoreText = scoreText;
        updateScore(0, 0);
    }

    public void updateScore(int p1, int p2) {
        score1 += p1;
        score2 += p2;
        scoreText.setText(score1 + " : " + score2);
    }
    public int getScore(int pos) {
        if(pos == 0){
            return score1;
        }
        else if(pos == 1){
            return score2;
        }
        return 0;
    }
}
