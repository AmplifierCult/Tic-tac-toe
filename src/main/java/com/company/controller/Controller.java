package com.company.controller;

import com.company.Player;
import com.company.ai.Ai;
import com.company.table.CellException;
import com.company.table.CellState;
import com.company.table.Table;
import com.company.user.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    public int numberOfGames;
    public int numberOfWins;
    public int numberOfLosses;
    private final Table table;
    private final User user;
    private final Ai ai;
    public String victory;
    private CellState equalValue;

    public Controller() throws IOException {
        printRules();
        startGame();
        user = new User();
        ai = new Ai(user.getCharacter());
        table = new Table();
        equalValue = CellState.UNSET;
    }

    private void printRules() {
        System.out.println("\nTIC-TAC-TOE\n\nRules:\nTo win the game, a player must place three of their marks" +
                " in a horizontal, vertical, or diagonal row. There is no universally-agreed rule as to who plays first,\n" +
                "but in this game realization the convention that X plays first is used.\n");
    }

    private void startGame() throws IOException {
        System.out.println("Start the game?\nEnter [y] - yes or [n] - no.");
        String answer = enterAnswer();
        if  (answer.equals("n")) {
            System.out.println("Thank you for your attention. See you later.");
            System.exit(0);
        }
    }
    /**
     * Проводит игру.
     */
    public void playGame() throws CellException, IOException {
        do {
            Player currentPlayer = getCurrentPlayer();
            System.out.println("Game started.");
            System.out.println(table);
            while(!gameOver()) {
                currentPlayer.play(table);
                System.out.println("Player" + " \"" + currentPlayer.getName() + " \"" + " move № " + table.getNumberOfRecords());
                System.out.println(table);
                currentPlayer = getNextPlayer(currentPlayer);
            }
            chooseVictory();
            numberOfGames++;
            printVictory();
            printStatistics();
        } while (wantToContinue());
    }

    private Player getCurrentPlayer() {
        Player currentPlayer;
        if (user.getCharacter().equals(CellState.TIC)) {
            currentPlayer = user;
        } else currentPlayer = ai;
        return currentPlayer;
    }

    private Player getNextPlayer(Player currentPlayer) {
        Player nextPlayer;
        if (currentPlayer instanceof User) {
            nextPlayer = ai;
        } else nextPlayer = user;
        return nextPlayer;
    }

    /**
     * Перезапускает игру.
     */
    public void resetGame() throws CellException, IOException {
        table.clear();
        equalValue = CellState.UNSET;
        victory = null;
    }

    /**
     * Определяет окончание игры.
     */
    private boolean gameOver() {
        if (table.getNumberOfRecords() < 5) {
            return false;
        }

        if (checkEquals()) {
            return true;
        }

        return table.getNumberOfRecords() == 9;
    }

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
        if (table.getCell(0,1).equals(table.getCell(1,1)) && table.getCell(0,1).equals(table.getCell(2,1)) && !table.getCell(0,0).equals(CellState.UNSET)) {
            equalValue = table.getCell(0,1);
            return true;
        }

        // Проверяем третий столбец на совпадения.
        if (table.getCell(0,2).equals(table.getCell(1,2)) && table.getCell(0,2).equals(table.getCell(2,2)) && !table.getCell(0,0).equals(CellState.UNSET)) {
            equalValue = table.getCell(0,2);
            return true;
        }

        return false;
    }

    /**
     * Определяет победителя.
     */
    private void chooseVictory() {
        if (equalValue.equals(CellState.UNSET)) {
            victory = "Game ended in a draw.";
        } else if (user.getCharacter().equals(equalValue)) {
            victory = "Player" + " \"" + user.getName() + "\"" + " is win.";
            numberOfWins++;
        } else if (ai.getCharacter().equals(equalValue)) {
            victory = "Player" + " \"" + ai.getName() + "\"" + " is win.";
            numberOfLosses++;
        }
    }

    private void printVictory() {
        System.out.println(victory);
    }

    private void printStatistics() {
        System.out.println("Statistics: Wins - " +
                numberOfWins +
                "; Losses - " +
                numberOfLosses +
                "; Number of games - " +
                numberOfGames +
                ".");
    }

    private boolean wantToContinue() throws IOException, CellException {
        System.out.println("Continue?\nEnter [y] - yes or [n] - no.");
        String answer = enterAnswer();
        if  (answer.equals("y")) {
            resetGame();
            return true;
        } else {
            System.out.println("Thanks for playing.");
            return false;
        }
    }

    private String enterAnswer() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String answer = reader.readLine();
        while (!validateAnswer(answer)) {
            System.out.println("Illegal answer. Try again.");
            System.out.println("Enter [y] - yes or [n] - no.");
            answer = reader.readLine();
        }
        return answer;
    }

    private boolean validateAnswer(String answer) {
        return answer.equals("y") || answer.equals("n");
    }
}