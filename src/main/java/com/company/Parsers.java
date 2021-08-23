package com.company;

import com.company.player.PlayerType;
import com.company.table.CellState;

public enum Parsers implements Parser  {

    STRING {
        @Override
        public String parse(String userInput) {
            return userInput;
        }
    },

    BOOLEAN {
        @Override
        public Boolean parse(String userInput) {
            return userInput.equals(ConsoleGame.InputTextType.YES.getInputText());
        }
    },

    TIC_TAC {
        @Override
        public CellState parse(String userInput) {
            if (userInput.equals(ConsoleGame.InputTextType.CROSS.getInputText())) {
                return CellState.TIC;
            } else return CellState.TAC;
        }
    },

    PLAYER_TYPE {
        @Override
        public PlayerType parse(String userInput) {
            if (userInput.equals(ConsoleGame.InputTextType.NUMBER_1.getInputText())) {
                return PlayerType.USER;
            } else if (userInput.equals(ConsoleGame.InputTextType.NUMBER_2.getInputText())) {
                return PlayerType.EASY_AI;
            } else if (userInput.equals(ConsoleGame.InputTextType.NUMBER_3.getInputText())) {
                return PlayerType.NORMAL_AI;
            } else if (userInput.equals(ConsoleGame.InputTextType.NUMBER_4.getInputText())) {
                return PlayerType.HARD_AI;
            } else {
                throw new IllegalArgumentException();
            }
        }
    }
}
