package com.company.user;

import com.company.Player;
import com.company.table.CellException;
import com.company.table.CellState;
import com.company.table.Table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class User extends Player {

    public User() throws IOException {
        setName();
        setCharacter();
    }

    public CellState getCharacter() {
        return character;
    }

    public void setCharacter() throws IOException {
        System.out.println("Choose a character [x] or [0] and write him.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String characterName = reader.readLine();
        while (!validateCharacterName(characterName)) {
            System.out.println("Illegal name of character.");
            System.out.println("Choose a character [x] or [0] and write him.");
            characterName = reader.readLine();
        }
        if (characterName.equals("0")) {
            character = CellState.TAC;
        } else character = CellState.TIC;
    }

    private boolean validateCharacterName(String characterName) {
        return characterName.equals("x") || characterName.equals("X") || characterName.equals("х") || characterName.equals("Х") || characterName.equals("0");
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName() throws IOException {
        while (name == null || Objects.equals(name, "")) {
            System.out.println("Enter a nickname.");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            name = reader.readLine();
        }
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
