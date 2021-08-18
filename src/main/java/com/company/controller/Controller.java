package com.company.controller;

import com.company.Player;
import com.company.ai.Ai;
import com.company.statistics.Statistics;
import com.company.table.CellState;
import com.company.table.Table;
import com.company.user.User;

public class Controller {
    private final Table table;
    private final User user;
    private final Ai ai;
    private final Statistics statistics;
    private Player currentPlayer;
    private String victory;
    private CellState equalValue;

    public Controller() {
        user = new User();
        ai = new Ai();
        table = new Table();
        statistics = new Statistics();
        equalValue = CellState.UNSET;
    }

    public Table getTable() {
        return table;
    }

    public User getUser() {
        return user;
    }

    public Ai getAi() {
        return ai;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public String getVictory() {
        return victory;
    }

    public void setVictory(String victory) {
        this.victory = victory;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Возвращает игрока который должен ходить первым.
     */
    public Player getFirstPlayer() {
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
        currentPlayer = getFirstPlayer();
    }

    /**
     * Определяет окончание игры.
     */
    public boolean gameOver() {
        if (table.getNumberOfRecords() < 5) {
            return false;
        } else if (checkEquals()) {
            statistics.setNumberOfGames();
            return true;
        } else if (table.getNumberOfRecords() == 9) {
            statistics.setNumberOfGames();
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
            setVictory("draw");
            statistics.setNumberOfDraws();
        } else if (user.getCharacter().equals(equalValue)) {
            setVictory(user.getName());
            statistics.setNumberOfWins();
        } else if (ai.getCharacter().equals(equalValue)) {
            setVictory(ai.getName());
            statistics.setNumberOfLosses();
        }
    }
}