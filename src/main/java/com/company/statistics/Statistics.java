package com.company.statistics;

public class Statistics {
    private int numberOfGames;
    private int numberOfWins;
    private int numberOfLosses;
    private int numberOfDraws;

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void incrementNumberOfGames() {
        numberOfGames++;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void incrementNumberOfWins() {
        numberOfWins++;
    }

    public int getNumberOfLosses() {
        return numberOfLosses;
    }

    public void incrementNumberOfLosses() {
        numberOfLosses++;
    }

    public int getNumberOfDraws() {
        return numberOfDraws;
    }

    public void incrementNumberOfDraws() {
        numberOfDraws++;
    }
}
