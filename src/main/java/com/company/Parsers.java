package com.company;

public enum Parsers implements Parser  {
    INTEGER {
        @Override
        public Integer parse(String userInput) {
            return Integer.parseInt(userInput);
        }
    },

    BOOLEAN {
        @Override
        public Boolean parse(String userInput) {
            return Boolean.parseBoolean(userInput);
        }
    },

    PLAYER_TYPE {
        @Override
        public Object parse(String userInput) {
            return null;
        }
    }
}
