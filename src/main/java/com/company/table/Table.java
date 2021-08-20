package com.company.table;

import com.company.ConsoleGame;

public class Table {
    private final CellState [][] table;
    private int count;

    public Table() {
        table = new CellState[3][3];
        clear();
    }

    /**
     * Возвращает значение ячейки.
     */
    public CellState getCell(int index1, int index2) {
        return table[index1][index2];
    }

    /**
     * Записывает значение в ячейку.
     */
    public void setCell(int index1, int index2, CellState value) throws CellException {
        validateValue(value);
        CellState cell = table[index1][index2];
        detectUNSET(cell);
        table[index1][index2] = value;
        count++;
    }

    /**
     * Проверка ячейки на пустоту.
     */
    public boolean isEmptyCell(int index1, int index2) {
        return table[index1][index2].equals(CellState.UNSET);
    }

    /**
     * Возвращает количество заполненных ячеек.
     */
    public int getNumberOfRecords() {
        return count;
    }

    /**
     * Очищает таблицу.
     */
    public void clear() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = CellState.UNSET;
            }
        }
        count = 0;
    }

    private void validateValue(CellState value) throws CellException {
        if (value == null) {
            throw new CellException(ConsoleGame.HelpMessageType.VALUE_IS_NULL.getMessage());
        }
    }

    private void detectUNSET(CellState cell) throws CellException {
        if (cell != CellState.UNSET) {
            throw new CellException(ConsoleGame.HelpMessageType.CELL_NOT_EMPTY.getMessage());
        }
    }

    public boolean validateCellCoordinates(int numberOfString, int numberOfColumn, Table table) {
        if (numberOfColumn >= 0 && numberOfColumn <= 2 && numberOfString >= 0 && numberOfString <= 2) {
            if (!table.getCell(numberOfString, numberOfColumn).equals(CellState.UNSET)) {
                ConsoleGame.HelpMessageType.CELL_IS_BUSY.printMessage();
                return false;
            } else return true;
        } else {
            ConsoleGame.HelpMessageType.CELL_COORDINATES_NOT_VALID.printMessage();
            return false;
        }
    }
}
