package com.company;

import com.company.controller.Controller;
import com.company.player.Player;
import com.company.player.PlayerType;
import com.company.table.CellException;
import com.company.table.CellState;
import com.company.table.TableConsolePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.function.Predicate;

public class ConsoleGame {
    public static void main(String[] args) throws CellException, IOException {

        //Print rules
        System.out.println("\nTIC-TAC-TOE\n\nRules:\nTo win the game, a player must place three of their marks" +
                " in a horizontal, vertical, or diagonal row. There is no universally-agreed rule as to who plays first,\n" +
                "but in this game realization the convention that X plays first is used.\n");

        //Start game
        System.out.println("Start the game?\nEnter [y] - yes or [n] - no.");
        String answer = enterText(HelpMessageType.CONTINUE_THE_GAME);
        if  (answer.equals("n")) {
            System.out.println("Thank you for your attention. See you later.");
            System.exit(0);
        }
        Controller controller = new Controller();

        //Create first player
        System.out.println("Select first player:");
        Player firstPlayer = initializePlayer(null);
        controller.setFirstPlayer(firstPlayer);

        //Create second player
        System.out.println("Select second player:");
        Player secondPlayer = initializePlayer(firstPlayer);
        controller.setSecondPlayer(secondPlayer);

        //Play game
        controller.setCurrentPlayer(controller.getPlayerGoFirst());
        do {
            System.out.println("Game started.");
            TableConsolePrinter.printTable(controller.getTable());
            while (!controller.gameOver()) {
                controller.getCurrentPlayer().play(controller.getTable());
                System.out.println("Player" + " \"" + controller.getCurrentPlayer().getName() + " \"" + " move № " + controller.getTable().getNumberOfRecords());
                TableConsolePrinter.printTable(controller.getTable());
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
        String answer = enterText(HelpMessageType.CONTINUE_THE_GAME);
        if  (answer.equals("y")) {
            controller.resetGame();
            return true;
        } else {
            System.out.println("Thanks for playing.");
            return false;
        }
    }

    private static String enterText(HelpMessageType helpMessage) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();
        Predicate<String> validation = getValidation(helpMessage);
        while (!validation.test(text)) {
            System.out.println(helpMessage);
            text = reader.readLine();
        }
        return text;
    }

    private static Predicate<String> getValidation(HelpMessageType helpMessage) {
        switch (helpMessage) {
            case ENTER_NAME:
                return name -> name == null || !Objects.equals(name, "");
            case ENTER_NUMBER:
                return number -> number.equals("1") || number.equals("2") || number.equals("3") || number.equals("4") || number.equals("5");
            case ENTER_CHARACTER:
                return character -> character.equals("x") || character.equals("X") || character.equals("0");
            case CONTINUE_THE_GAME:
                return answer -> answer.equals("y") || answer.equals("n");
            default:
                throw new IllegalArgumentException("Illegal help message.");
        }
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

    private static String enterName() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String name = reader.readLine();
            while (validateName(name)) {
                System.out.println("Illegal nickname. Try again.");
                System.out.println("Enter a nickname.");
                name = reader.readLine();
            }
            return name;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private static boolean validateName(String name) {
        return name == null || Objects.equals(name, "");
    }

    private static boolean validateCharacter(String characterName) {
        return characterName.equals("x") || characterName.equals("X") || characterName.equals("0");
    }

    private static Player initializePlayer(Player previousPlayer) throws IOException {
        PlayerType playerType = selectPlayerType();

        String playerName = inputPlayerName(playerType);

        CellState playerCharacter;
        if (previousPlayer != null) {
            playerCharacter = previousPlayer.getCharacter().inverse();
        } else {
            playerCharacter = inputPlayerCharacter();
        }

        return Player.createPlayer(playerType, playerName, playerCharacter);
    }

    private static String inputPlayerName(PlayerType playerType) throws IOException {
        switch (playerType) {
            case USER:
                System.out.println("Enter a nickname: ");
                return enterText(HelpMessageType.ENTER_NAME);
            case EASY_AI:
                System.out.println("You select AI for gaming. The name will be their difficulty.");
                return "EasyAI";
            case NORMAL_AI:
                System.out.println("You select AI for gaming. The name will be their difficulty.");
                return "NormalAI";
            case HARD_AI:
                System.out.println("You select AI for gaming. The name will be their difficulty.");
                return "HardAI";
            default:
                throw new IllegalArgumentException("Unknown player type");
        }
    }

    private static CellState inputPlayerCharacter() throws IOException {
        System.out.println("Choose a character [x] or [0] and write him.");
        String character = enterText(HelpMessageType.ENTER_CHARACTER);
        if (character.equals("0")) {
            return CellState.TAC;
        } else {
            return CellState.TIC;
        }
    }

    private static String enterCharacter() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String character = reader.readLine();
            while (!validateCharacter(character)) {
                System.out.println("Illegal name of character.");
                System.out.println("Choose a character [x] or [0] and write him.");
                character = reader.readLine();
            }
            return character;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private static PlayerType selectPlayerType() throws IOException {
        System.out.println("[1] User");
        System.out.println("[2] EasyAI");
        System.out.println("[3] NormalAI");
        System.out.println("[4] HardAI");
        System.out.print("Enter the number ");
        switch (enterText(HelpMessageType.ENTER_NUMBER)) {
            case "1":
                return PlayerType.USER;
            case "2":
                return PlayerType.EASY_AI;
            case "3":
                return PlayerType.NORMAL_AI;
            case "4":
                return PlayerType.HARD_AI;
            default:
                System.out.println("Illegal number. Try again.");
                return selectPlayerType();
        }
    }

    private static String enterNumber() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String number = reader.readLine();
            while (!validateNumber(number)) {
                number = reader.readLine();
            }
            return number;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private static boolean validateNumber(String number) {
        if (number.equals("")) {
            System.out.println("Illegal number. Try again.");
            return false;
        }
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            System.out.println("Illegal number. Try again.");
            return false;
        }
        return true;
    }
}
