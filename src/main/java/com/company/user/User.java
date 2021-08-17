package com.company.user;

import com.company.Player;
import com.company.table.CellException;
import com.company.table.CellState;
import com.company.table.Table;

import java.io.IOException;

public class User extends Player {

    public CellState getCharacter() {
        return character;
    }

    public void setCharacter(String character) throws IOException {
        if (character.equals("0")) {
            this.character = CellState.TAC;
        } else this.character = CellState.TIC;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) throws IOException {
        this.name = name;
    }

    @Override
    public void play(Table table) throws IOException, CellException {
        System.out.println("Enter cell coordinates:");
        int numberOfString;
        int numberOfColumn;
        do {
            System.out.print("Enter number of string, № = ");
            numberOfString = Integer.parseInt(enterNumber()) - 1;
            System.out.print("Enter number of column, № = ");
            numberOfColumn = Integer.parseInt(enterNumber()) - 1;
            System.out.println();
        } while (!validateCellCoordinates(numberOfString, numberOfColumn, table));

        table.setCell(numberOfString, numberOfColumn, getCharacter());
    }

    //method of table
    private boolean validateCellCoordinates(int numberOfString, int numberOfColumn, Table table) {
        if (numberOfColumn >= 0 && numberOfColumn <= 2 && numberOfString >= 0 && numberOfString <= 2) {
            if (!table.getCell(numberOfString, numberOfColumn).equals(CellState.UNSET)) {
                System.out.println("Cell is busy. Choose another.");
                return false;
            } else return true;
        } else {
            System.out.println("Cell coordinates is not a valid. Try again.");
            return false;
        }
    }
}
