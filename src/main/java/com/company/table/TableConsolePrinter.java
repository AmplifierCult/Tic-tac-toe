package com.company.table;

public class TableConsolePrinter {
    public static void printTable(Table table) {
        StringBuilder tablePrinter = new StringBuilder();
        tablePrinter.append("     C  o  l  u  m  n  s\n");
        tablePrinter.append("        1     2     3   \n");
        tablePrinter.append("S    +-----+-----+-----+\n");
        tablePrinter.append("t  1 |  ");
        try {
            tablePrinter.append(getChar(table.getCell(0, 0)));
            tablePrinter.append("  |  ");
            tablePrinter.append(getChar(table.getCell(0, 1)));
            tablePrinter.append("  |  ");
            tablePrinter.append(getChar(table.getCell(0, 2)));
            tablePrinter.append("  |\n");
            tablePrinter.append("r    +-----+-----+-----+\n");
            tablePrinter.append("i  2 |  ");
            tablePrinter.append(getChar(table.getCell(1, 0)));
            tablePrinter.append("  |  ");
            tablePrinter.append(getChar(table.getCell(1, 1)));
            tablePrinter.append("  |  ");
            tablePrinter.append(getChar(table.getCell(1, 2)));
            tablePrinter.append("  |\n");
            tablePrinter.append("n    +-----+-----+-----+\n");
            tablePrinter.append("g  3 |  ");
            tablePrinter.append(getChar(table.getCell(2, 0)));
            tablePrinter.append("  |  ");
            tablePrinter.append(getChar(table.getCell(2, 1)));
            tablePrinter.append("  |  ");
            tablePrinter.append(getChar(table.getCell(2, 2)));
        } catch (CellException e) {
            e.printStackTrace();
        }
        tablePrinter.append("  |\n");
        tablePrinter.append("s    +-----+-----+-----+\n");
        System.out.println(tablePrinter);
    }

    private static String getChar(CellState cellValue) throws CellException {
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
