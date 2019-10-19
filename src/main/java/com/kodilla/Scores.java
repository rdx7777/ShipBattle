package com.kodilla;

public class Scores {
    private int playerScore;
    private int computerScore;

    public void playerWon() {
        playerScore++;
    }

    public void computerWon() {
        computerScore++;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getComputerScore() {
        return computerScore;
    }
}
