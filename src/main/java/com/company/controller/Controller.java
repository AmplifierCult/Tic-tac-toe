package com.company.controller;

import com.company.Player;
import com.company.ai.Ai;
import com.company.table.CellState;
import com.company.table.Table;
import com.company.user.User;

import java.io.IOException;

public class Controller {
    public int numberOfGames;
    public int numberOfWins;
    public int numberOfLosses;
    public int numberOfDraws;
    public final Table table;
    public User user;
    public Ai ai;
    public Player currentPlayer;
    public String victory;
    private CellState equalValue;

    public Controller() throws IOException {
        user = new User();
        ai = new Ai();
        table = new Table();
        equalValue = CellState.UNSET;
    }

    /**
     * Возвращает текущего игрока.
     */
    public Player getCurrentPlayer() {
        Player currentPlayer;
        if (user.getCharacter().equals(CellState.TIC)) {
            currentPlayer = user;
        } else currentPlayer = ai;
        return currentPlayer;
    }

    /**
     * Возвращает следующего игрока.
     */
    public Player getNextPlayer() {
        Player nextPlayer;
        if (currentPlayer instanceof User) {
            nextPlayer = ai;
        } else nextPlayer = user;
        return nextPlayer;
    }

    /**
     * Перезапускает игру.
     */
    public void resetGame() {
        table.clear();
        equalValue = CellState.UNSET;
        victory = null;
        currentPlayer = getCurrentPlayer();
    }

    /**
     * Определяет окончание игры.
     */
    public boolean gameOver() {
        if (table.getNumberOfRecords() < 5) {
            return false;
        } else if (checkEquals()) {
            numberOfGames++;
            return true;
        } else if (table.getNumberOfRecords() == 9) {
            numberOfGames++;
            return true;
        }
        return false;
    }

    /**
     * Проверяет наличие совпадений 3-х символов по столбцам, диагоналям и строкам.
     */
    private boolean checkEquals() {
        // Проверяем первый ряд на совпадения.
        if (table.getCell(0,0).equals(table.getCell(0,1)) && table.getCell(0,0).equals(table.getCell(0,2)) && !table.getCell(0,0).equals(CellState.UNSET)) {
            equalValue = table.getCell(0,0);
            return true;
        }

        // Проверяем второй ряд на совпадения.
        if (table.getCell(1,0).equals(table.getCell(1,1)) && table.getCell(1,0).equals(table.getCell(1,2)) && !table.getCell(1,0).equals(CellState.UNSET)) {
            equalValue = table.getCell(1,0);
            return true;
        }

        // Проверяем третий ряд на совпадения.
        if (table.getCell(2,0).equals(table.getCell(2,1)) && table.getCell(2,0).equals(table.getCell(2,2)) && !table.getCell(2,0).equals(CellState.UNSET)) {
            equalValue = table.getCell(2,0);
            return true;
        }

        // Проверяем первую диагональ на совпадения.
        if (table.getCell(0,0).equals(table.getCell(1,1)) && table.getCell(0,0).equals(table.getCell(2,2)) && !table.getCell(0,0).equals(CellState.UNSET)) {
            equalValue = table.getCell(0,0);
            return true;
        }

        // Проверяем вторую диагональ на совпадения.
        if (table.getCell(2,0).equals(table.getCell(1,1)) && table.getCell(2,0).equals(table.getCell(0,2)) && !table.getCell(2,0).equals(CellState.UNSET)) {
            equalValue = table.getCell(2,0);
            return true;
        }

        // Проверяем первый столбец на совпадения.
        if (table.getCell(0,0).equals(table.getCell(1,0)) && table.getCell(0,0).equals(table.getCell(2,0)) && !table.getCell(0,0).equals(CellState.UNSET)) {
            equalValue = table.getCell(0,0);
            return true;
        }

        // Проверяем второй столбец на совпадения.
        if (table.getCell(0,1).equals(table.getCell(1,1)) && table.getCell(0,1).equals(table.getCell(2,1)) && !table.getCell(0,1).equals(CellState.UNSET)) {
            equalValue = table.getCell(0,1);
            return true;
        }

        // Проверяем третий столбец на совпадения.
        if (table.getCell(0,2).equals(table.getCell(1,2)) && table.getCell(0,2).equals(table.getCell(2,2)) && !table.getCell(0,2).equals(CellState.UNSET)) {
            equalValue = table.getCell(0,2);
            return true;
        }

        return false;
    }

    /**
     * Определяет победителя.
     */
    public void chooseVictory() {
        if (equalValue.equals(CellState.UNSET)) {
            victory = "draw";
            numberOfDraws++;
        } else if (user.getCharacter().equals(equalValue)) {
            victory = user.getName();
            numberOfWins++;
        } else if (ai.getCharacter().equals(equalValue)) {
            victory = ai.getName();
            numberOfLosses++;
        }
    }




}