package com.company.player;

import com.company.ConsoleGame;
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
        ConsoleGame.HelpMessageType.ENTER_CELL_COORDINATES.printMessage();
        int numberOfLine;
        int numberOfColumn;
        do {
            ConsoleGame.HelpMessageType.ENTER_LINE.printMessage();
            numberOfLine = Integer.parseInt(enterNumber()) - 1;
            ConsoleGame.HelpMessageType.ENTER_COLUMN.printMessage();
            numberOfColumn = Integer.parseInt(enterNumber()) - 1;
            System.out.println();
        } while (!table.validateCellCoordinates(numberOfLine, numberOfColumn, table));

        table.setCell(numberOfLine, numberOfColumn, getCharacter());
    }
}
