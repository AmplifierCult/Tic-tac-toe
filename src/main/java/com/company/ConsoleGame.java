package com.company;

import com.company.controller.Controller;
import com.company.table.CellException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class ConsoleGame {
    public static void main(String[] args) throws CellException, IOException {

        //Print rules
        System.out.println("\nTIC-TAC-TOE\n\nRules:\nTo win the game, a player must place three of their marks" +
                " in a horizontal, vertical, or diagonal row. There is no universally-agreed rule as to who plays first,\n" +
                "but in this game realization the convention that X plays first is used.\n");

        //Start game
        System.out.println("Start the game?\nEnter [y] - yes or [n] - no.");
        String answer = enterAnswer();
        if  (answer.equals("n")) {
            System.out.println("Thank you for your attention. See you later.");
            System.exit(0);
        }
        Controller controller = new Controller();

        //Create User
        System.out.println("Enter a nickname.");
        controller.getUser().setName(enterName());
        System.out.println("Choose a character [x] or [0] and write him.");
        controller.getUser().setCharacter(enterCharacter());


        //Create AI
        controller.getAi().setName();
        controller.getAi().setCharacter(controller.getUser().character);
        //Play game
        controller.setCurrentPlayer(controller.getFirstPlayer());
        do {
            System.out.println("Game started.");
            System.out.println(controller.getTable());
            while (!controller.gameOver()) {
                controller.getCurrentPlayer().play(controller.getTable());
                System.out.println("Player" + " \"" + controller.getCurrentPlayer().getName() + " \"" + " move № " + controller.getTable().getNumberOfRecords());
                System.out.println(controller.getTable());
                controller.setCurrentPlayer(controller.getNextPlayer());
            }
            controller.chooseVictory();
            printVictory(controller);
            printStatistics(controller);
        } while (wantToContinue(controller));
    }

    private static void printVictory(Controller controller) {
        if (controller.getVictory().equals("draw")) {
            System.out.println("Game ended in a draw.");
        } else {
            System.out.println("Player" + " \"" + controller.getVictory() + "\"" + " is win.");
        }
    }

    private static void printStatistics(Controller controller) {
        System.out.println("Statistics: Wins - " +
                controller.getStatistics().getNumberOfWins() +
                "; Losses - " +
                controller.getStatistics().getNumberOfLosses() +
                "; Draws - " +
                controller.getStatistics().getNumberOfDraws() +
                "; Number of games - " +
                controller.getStatistics().getNumberOfGames() +
                ".");
    }

    private static boolean wantToContinue(Controller controller) throws IOException {
        System.out.println("Continue?\nEnter [y] - yes or [n] - no.");
        String answer = enterAnswer();
        if  (answer.equals("y")) {
            controller.resetGame();
            return true;
        } else {
            System.out.println("Thanks for playing.");
            return false;
        }
    }

    //TODO universal text input method
    private static String enterText(String helpMessage /*validationMethod*/) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();
        /*while (validationMethod(text)) {
            System.out.println(helpMessage);
            text = reader.readLine();
        }*/
        return text;
    }

    private static String enterAnswer() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String answer = reader.readLine();
        while (validateAnswer(answer)) {
            System.out.println("Illegal answer. Try again.");
            System.out.println("Enter [y] - yes or [n] - no.");
            answer = reader.readLine();
        }
        return answer;
    }

    private static boolean validateAnswer(String answer) {
        return !answer.equals("y") && !answer.equals("n");
    }

    private static String enterName() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        while (validateName(name)) {
            System.out.println("Illegal nickname. Try again.");
            System.out.println("Enter a nickname.");
            name = reader.readLine();
        }
        return name;
    }

    private static boolean validateName(String name) {
        return name == null || Objects.equals(name, "");
    }

    private static String enterCharacter() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String character = reader.readLine();
        while (!validateCharacter(character)) {
            System.out.println("Illegal name of character.");
            System.out.println("Choose a character [x] or [0] and write him.");
            character = reader.readLine();
        }
        return character;
    }
    private static boolean validateCharacter(String characterName) {
        return characterName.equals("x") || characterName.equals("X") || characterName.equals("0");
    }
}
