package com.company.controller;

import com.company.table.CellException;
import com.company.table.CellState;
import com.company.table.Table;

public class Controller {
    Table table;

    public Controller() {
        table = new Table();
    }

    public void setValue(int index1, int index2, CellState value) throws CellException {
        table.setCell(index1, index2, value);
    }

    public void startTheGame() {

    }

    public boolean gameOver() {
        return  false;
    }

    public CellState choseVictory() {
        CellState victory = null;
        return victory;
    }
}
