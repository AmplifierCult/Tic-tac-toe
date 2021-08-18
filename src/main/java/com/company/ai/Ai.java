package com.company.ai;

import com.company.Player;
import com.company.table.CellException;
import com.company.table.CellState;
import com.company.table.Table;

import java.io.IOException;


public class Ai extends Player {

    public void setName() throws IOException {
        System.out.println("Choose the difficulty of artificial intelligence (AI):\n[1] EasyAI\n[2] NormalAI\n[3] HardAI");
        System.out.print("Enter the number of AI ");
        int number;
        do {
            number = Integer.parseInt(enterNumber());
        } while (!validateNumberOfDifficulty(number));

        if (number == 1) {
            name = "EasyAI";
        } else if (number == 2) {
            name = "NormalAI";
        } else name = "HardAI";
    }

    private boolean validateNumberOfDifficulty(int number) {
        if (number < 0 || number > 3) {
            System.out.println("Illegal number. Try again.");
            return false;
        } else return true;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setCharacter(CellState character) {
        if (character.equals(CellState.TIC)) {
            this.character = CellState.TAC;
        } else this.character = CellState.TIC;
    }

    public CellState getCharacter() {
        return character;
    }

    //EasyAI
    @Override
    public void play(Table table) throws IOException, CellException {
        int numberOfString;
        int numberOfColumn;
        do {
            numberOfString = (int) (Math.random() * 3);
            numberOfColumn = (int) (Math.random() * 3);
        }
        while (validateCellCoordinates(numberOfString, numberOfColumn, table));

        table.setCell(numberOfString, numberOfColumn, getCharacter());
    }

    private boolean validateCellCoordinates(int numberOfString, int numberOfColumn, Table table) {
        return !table.getCell(numberOfString, numberOfColumn).equals(CellState.UNSET);
    }

    //HardAI
    private void playHard(Table table) throws CellException {
        int numberOfString;
        int numberOfColumn;
        do {
            numberOfString = (int) (Math.random() * 3);
            numberOfColumn = (int) (Math.random() * 3);
        }
        while (validateCellCoordinates(numberOfString, numberOfColumn, table));

        table.setCell(numberOfString, numberOfColumn, getCharacter());
    }
}
