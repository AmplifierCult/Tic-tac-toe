package com.company.table;

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
            throw new CellException("Value is NULL");
        }
    }

    private void detectUNSET(CellState cell) throws CellException {
        if (cell != CellState.UNSET) {
            throw new CellException("Cell is not empty.");
        }
    }

    @Override
    public String toString() {
        StringBuilder table = new StringBuilder();
        table.append("+-----+-----+-----+\n");
        table.append("|  ");
        try {
            table.append(getChar(this.table[0][0]));
            table.append("  |  ");
            table.append(getChar(this.table[0][1]));
            table.append("  |  ");
            table.append(getChar(this.table[0][2]));
            table.append("  |\n");
            table.append("+-----+-----+-----+\n");
            table.append("|  ");
            table.append(getChar(this.table[1][0]));
            table.append("  |  ");
            table.append(getChar(this.table[1][1]));
            table.append("  |  ");
            table.append(getChar(this.table[1][2]));
            table.append("  |\n");
            table.append("+-----+-----+-----+\n");
            table.append("|  ");
            table.append(getChar(this.table[2][0]));
            table.append("  |  ");
            table.append(getChar(this.table[2][1]));
            table.append("  |  ");
            table.append(getChar(this.table[2][2]));
        } catch (CellException e) {
            e.printStackTrace();
        }
        table.append("  |\n");
        table.append("+-----+-----+-----+\n");
        return table.toString();
    }

    public String getChar(CellState cellValue) throws CellException {
        if (cellValue == null) {
            throw new CellException("Cell value is null.");
        }
        if (cellValue.equals(CellState.TIC)) {
            return "x";
        } else if (cellValue.equals(CellState.TAC)) {
            return "0";
        } else if (cellValue.equals(CellState.UNSET)) {
            return " ";
        } else throw new CellException("Illegal cell state.");
    }
}
