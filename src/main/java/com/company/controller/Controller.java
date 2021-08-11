package com.company.controller;

import com.company.ai.Ai;
import com.company.table.CellException;
import com.company.table.CellState;
import com.company.table.Table;
import com.company.user.User;

public class Controller {
    Table table;

    public Controller() {
        table = new Table();
    }

    // TODO реализовать методы.

    /**
     * Устанавливает новое значение.
     */
    public void setValue(int index1, int index2, CellState value) throws CellException {
        table.setCell(index1, index2, value);
    }

    /**
     * Запускает игру.
     */
    public void startTheGame() {
        User user = new User();
        Ai ai = new Ai();
        Table table = new Table();
        while(!gameOver()) {
            // совершает действие текущий игрок
        }
        choseVictory();
    }

    /**
     * Определяет окончание игры.
     */
    public boolean gameOver() {
        return  false;
    }

    /**
     * Определяет победителя.
     */
    public CellState choseVictory() {
        CellState victory = null;
        return victory;
    }
}
