package com.company.table;

import java.util.Arrays;

public class Table {
    private final CellState [][] table;
    private int count;

    public Table() {
        table = new CellState[3][3];
        clear();
    }

    public CellState getCell(int index1, int index2) {
        return table[index1][index2];
    }

    public void setCell(int index1, int index2, CellState value) throws CellException {
        validateValue(value);
        CellState cell = table[index1][index2];
        detectUNSET(cell);
        table[index1][index2] = value;
        count++;
    }

    public int getNumberOfRecords() {
        return count;
    }

    private void clear() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = CellState.UNSET;
            }
        }
    }

    private void validateValue(CellState value) throws CellException {
        if (value == null) {
            throw new CellException("Value is NULL");
        }
    }

    private void detectUNSET(CellState cell) throws CellException {
        if (cell != CellState.UNSET) {
            throw new CellException("Cell is not empty.");
        }
    }
}
