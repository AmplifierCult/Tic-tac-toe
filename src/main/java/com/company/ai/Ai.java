package com.company.ai;

import com.company.ListOfPlayers;
import com.company.Player;
import com.company.table.CellException;
import com.company.table.CellState;
import com.company.table.Table;


public class Ai extends Player {
private final ListOfPlayers difficulty;

    public Ai(ListOfPlayers player) {
        difficulty = player;
        setName(difficulty);
    }

    private void setName(ListOfPlayers difficulty) {
        switch (difficulty) {
            case EASY_AI:
                name = "EasyAI";
                break;
            case NORMAL_AI:
                name = "NormalAI";
                break;
            case HARD_AI:
                name = "HardAI";
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public void setCharacter(String character) {
        if (character.equals("0")) {
            this.character = CellState.TAC;
        } else this.character = CellState.TIC;
    }

    //EasyAI
    @Override
    public void play(Table table) throws CellException {
        switch (difficulty) {
            case HARD_AI:
                playHard(table);
                break;
            case NORMAL_AI:
                playNormal(table);
                break;
            case EASY_AI:
                playEasy(table);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private boolean validateCellCoordinates(int numberOfString, int numberOfColumn, Table table) {
        return !table.getCell(numberOfString, numberOfColumn).equals(CellState.UNSET);
    }

    //HardAI
    private void playHard(Table table) throws CellException {
        int numberOfString;
        int numberOfColumn;
        do {
            numberOfString = (int) (Math.random() * 3);
            numberOfColumn = (int) (Math.random() * 3);
        }
        while (validateCellCoordinates(numberOfString, numberOfColumn, table));

        table.setCell(numberOfString, numberOfColumn, getCharacter());
    }

    //NormalAI
    private void playNormal(Table table) throws CellException {
        int numberOfString;
        int numberOfColumn;
        do {
            numberOfString = (int) (Math.random() * 3);
            numberOfColumn = (int) (Math.random() * 3);
        }
        while (validateCellCoordinates(numberOfString, numberOfColumn, table));

        table.setCell(numberOfString, numberOfColumn, getCharacter());
    }

    //EasyAI
    private void playEasy(Table table) throws CellException {
        int numberOfString;
        int numberOfColumn;
        do {
            numberOfString = (int) (Math.random() * 3);
            numberOfColumn = (int) (Math.random() * 3);
        }
        while (validateCellCoordinates(numberOfString, numberOfColumn, table));

        table.setCell(numberOfString, numberOfColumn, getCharacter());
    }
}
