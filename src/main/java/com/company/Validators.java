package com.company;

import java.util.Objects;

public enum Validators implements Validator {
    YES_NO {
        @Override
        public boolean validate(String input) {
            return input.equals(ConsoleGame.InputTextType.YES.getInputText()) || input.equals(ConsoleGame.InputTextType.NO.getInputText());
        }

        @Override
        public String getErrorMessage(String input) {
            return ConsoleGame.HelpMessageType.ILLEGAL_ANSWER_FOR_CONTINUE_THE_GAME.getMessage();
        }
    },

    CROSS_ZERO {
        @Override
        public boolean validate(String input) {
            return input.equals(ConsoleGame.InputTextType.CROSS.getInputText()) || input.equals(ConsoleGame.InputTextType.ZERO.getInputText());
        }

        @Override
        public String getErrorMessage(String input) {
            return ConsoleGame.HelpMessageType.ILLEGAL_CHARACTER.getMessage();
        }
    },

    NUMBERS {
        @Override
        public boolean validate(String input) {
            return input.equals(ConsoleGame.InputTextType.NUMBER_1.getInputText()) || input.equals(ConsoleGame.InputTextType.NUMBER_2.getInputText()) || input.equals(ConsoleGame.InputTextType.NUMBER_3.getInputText()) || input.equals(ConsoleGame.InputTextType.NUMBER_4.getInputText()) || input.equals(ConsoleGame.InputTextType.NUMBER_5.getInputText());
        }

        @Override
        public String getErrorMessage(String input) {
            return ConsoleGame.HelpMessageType.ILLEGAL_NUMBER.getMessage();
        }
    },

    NAMES {
        @Override
        public boolean validate(String input) {
            return input == null || !Objects.equals(input, "");
        }

        @Override
        public String getErrorMessage(String input) {
            return ConsoleGame.HelpMessageType.ILLEGAL_NAME.getMessage();
        }
    }
}
