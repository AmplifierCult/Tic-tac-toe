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

public class ConsoleGame {
    public static final Parser<Boolean> BOOLEAN_PARSER = userInput -> userInput.equals(InputTextType.YES.getInputText());

    public static final Parser<String> STRING_PARSER = userInput -> userInput;

    public static final Parser<CellState> TIC_TAC_PARSER = userInput -> {
        if (userInput.equals(InputTextType.CROSS.getInputText())) {
            return CellState.TIC;
        } else return CellState.TAC;
    };

    public static final Parser<PlayerType> PLAYER_TYPE_PARSER = userInput -> {
        if (userInput.equals(InputTextType.NUMBER_1.getInputText())) {
            return PlayerType.USER;
        } else if (userInput.equals(InputTextType.NUMBER_2.getInputText())) {
            return PlayerType.EASY_AI;
        } else if (userInput.equals(InputTextType.NUMBER_3.getInputText())) {
            return PlayerType.NORMAL_AI;
        } else if (userInput.equals(InputTextType.NUMBER_4.getInputText())) {
            return PlayerType.HARD_AI;
        } else {
            throw new IllegalArgumentException();
        }
    };

    public static void main(String[] args) throws CellException, IOException {

        //Print game title
        HelpMessageType.GAME_TITLE.printMessage();

        //Print rules
        HelpMessageType.RULES.printMessage();

        //Start game
        Boolean answer = askUser(HelpMessageType.START_GAME.getMessage(), Validators.YES_NO, BOOLEAN_PARSER);
        if  (!answer) {
            HelpMessageType.THANKS_FOR_ATTENTION.printMessage();
            System.exit(0);
        }
        Controller controller = new Controller();

        //Create first player
        HelpMessageType.SELECT_FIRST_PLAYER.printMessage();
        Player firstPlayer = initializePlayer(null);
        controller.setFirstPlayer(firstPlayer);

        //Create second player
        HelpMessageType.SELECT_SECOND_PLAYER.printMessage();
        Player secondPlayer = initializePlayer(firstPlayer);
        controller.setSecondPlayer(secondPlayer);

        //Play game
        controller.setCurrentPlayer(controller.getPlayerGoFirst());
        do {
            HelpMessageType.GAME_STARTED.printMessage();
            TableConsolePrinter.printTable(controller.getTable());
            while (!controller.gameOver()) {
                controller.getCurrentPlayer().play(controller.getTable());
                System.out.println("Player" + " \"" + controller.getCurrentPlayer().getName() + " \"" + " move ??? " + controller.getTable().getNumberOfRecords());
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
            HelpMessageType.GAME_ENDED_DRAW.printMessage();
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
        Boolean answer = askUser(HelpMessageType.CONTINUE_THE_GAME.getMessage(), Validators.YES_NO, BOOLEAN_PARSER);
        if  (answer) {
            controller.resetGame();
            return true;
        } else {
            HelpMessageType.THANKS_FOR_PLAYING.printMessage();
            return false;
        }
    }

    private static <K> K askUser(String helpMessage, Validator validator, Parser<K> parser) throws IOException {
        System.out.println(helpMessage);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();
        while (!validator.validate(text)) {
            String message = validator.getErrorMessage(text);
            System.out.println(message);
            text = reader.readLine();
        }
        return parser.parse(text);
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
                return askUser(HelpMessageType.ENTER_NAME.getMessage(), Validators.NAMES, STRING_PARSER);
            case EASY_AI:
                HelpMessageType.SELECT_AI.printMessage();
                return "EasyAI";
            case NORMAL_AI:
                HelpMessageType.SELECT_AI.printMessage();
                return "NormalAI";
            case HARD_AI:
                HelpMessageType.SELECT_AI.printMessage();
                return "HardAI";
            default:
                throw new IllegalArgumentException(HelpMessageType.UNKNOWN_PLAYER.getMessage());
        }
    }

    private static CellState inputPlayerCharacter() throws IOException {
        return askUser(HelpMessageType.CHOOSE_CHARACTER.getMessage(), Validators.CROSS_ZERO, TIC_TAC_PARSER);
    }

    private static PlayerType selectPlayerType() throws IOException {
        HelpMessageType.LIST_OF_PLAYER_TYPE.printMessage();
        return askUser(HelpMessageType.ENTER_NUMBER.getMessage(), Validators.NUMBERS, PLAYER_TYPE_PARSER);
    }

    public enum HelpMessageType {
        GAME_TITLE("\nTIC-TAC-TOE\n\n"),
        RULES("Rules:\nTo win the game, a player must place three of their marks" +
                " in a horizontal, vertical, or diagonal row. There is no universally-agreed rule as to who plays first,\n" +
                "but in this game realization the convention that X plays first is used.\n"),
        START_GAME("Start the game?\nEnter [y] - yes or [n] - no."),
        GAME_STARTED("Game started."),
        CONTINUE_THE_GAME("Continue?\nEnter [y] - yes or [n] - no."),
        ILLEGAL_ANSWER_FOR_CONTINUE_THE_GAME("Illegal answer. Try again."),
        SELECT_FIRST_PLAYER("Select first player:"),
        SELECT_SECOND_PLAYER("Select second player:"),
        SELECT_AI("You select AI for gaming. The name will be their difficulty."),
        LIST_OF_PLAYER_TYPE("[1] User\n[2] EasyAI\n[3] NormalAI\n[4] HardAI"),
        ENTER_NUMBER("Enter the number "),
        ILLEGAL_NUMBER("Illegal number. Try again."),
        ENTER_NAME("Enter a nickname: "),
        ILLEGAL_NAME("Illegal nickname. Try again."),
        UNKNOWN_PLAYER("Unknown player type"),
        CHOOSE_CHARACTER("Choose a character [x] or [0] and write him."),
        ILLEGAL_CHARACTER("Illegal name of character."),
        GAME_ENDED_DRAW("Game ended in a draw."),
        THANKS_FOR_ATTENTION("Thank you for your attention. See you later."),
        THANKS_FOR_PLAYING("Thanks for playing."),
        UNKNOWN_CELL_STATE("Unknown cell state"),
        VALUE_IS_NULL("Value is NULL"),
        ENTER_CELL_COORDINATES("Enter cell coordinates:"),
        ENTER_LINE("Enter number of line, ??? = "),
        ENTER_COLUMN("Enter number of column, ??? = "),
        CELL_NOT_EMPTY("Cell is not empty."),
        CELL_IS_BUSY("Cell is busy. Choose another."),
        CELL_COORDINATES_NOT_VALID("Cell coordinates is not a valid. Try again."),
        ILLEGAL_STATE("Illegal cell state.");

        private final String message;

        HelpMessageType(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void printMessage() {
            System.out.println(message);
        }
    }

    enum InputTextType {
        YES("y"),
        NO("n"),
        CROSS("x"),
        ZERO("0"),
        NUMBER_1("1"),
        NUMBER_2("2"),
        NUMBER_3("3"),
        NUMBER_4("4");

        private final String inputText;

        InputTextType(String inputText) {
            this.inputText = inputText;
        }

        public String getInputText() {
            return inputText;
        }
    }
}

