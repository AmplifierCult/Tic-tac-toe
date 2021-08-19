package com.company.player;

import com.company.table.CellException;
import com.company.table.CellState;
import com.company.table.Table;

import java.io.IOException;

public class User extends Player {

    public User(String name, CellState character) {
        this.name = name;
        this.character = character;
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
