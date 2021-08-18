package com.company.statistics;

public class Statistics {
    private int numberOfGames;
    private int numberOfWins;
    private int numberOfLosses;
    private int numberOfDraws;

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames() {
        numberOfGames++;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins() {
        numberOfWins++;
    }

    public int getNumberOfLosses() {
        return numberOfLosses;
    }

    public void setNumberOfLosses() {
        numberOfLosses++;
    }

    public int getNumberOfDraws() {
        return numberOfDraws;
    }

    public void setNumberOfDraws() {
        numberOfDraws++;
    }
}
