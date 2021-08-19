package com.company.player;

import com.company.table.CellException;
import com.company.table.CellState;
import com.company.table.Table;


public class Ai extends Player {

    private final PlayerType difficulty;

    public Ai(PlayerType playerType, String name, CellState character) {
        this.name = name;
        this.character = character;
        this.difficulty = playerType;
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

    //HardAI
    private void playHard(Table table) throws CellException {
        playEasy(table); // TODO Реализовать алгоритм для hard игры.
    }

    //NormalAI
    private void playNormal(Table table) throws CellException {
        playEasy(table); // TODO Реализовать алгоритм для normal игры.
    }

    //EasyAI
    private void playEasy(Table table) throws CellException {
        int numberOfString;
        int numberOfColumn;
        do {
            numberOfString = (int) (Math.random() * 3);
            numberOfColumn = (int) (Math.random() * 3);
        }
        while (! table.isEmptyCell(numberOfString, numberOfColumn));

        table.setCell(numberOfString, numberOfColumn, getCharacter());
    }
}
