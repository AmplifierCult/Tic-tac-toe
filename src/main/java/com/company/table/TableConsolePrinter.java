package com.company.table;

import com.company.ConsoleGame;

public class TableConsolePrinter {
    public static void printTable(Table table) {
        StringBuilder tablePrinter = new StringBuilder();
        tablePrinter.append("     C  o  l  u  m  n  s\n");
        tablePrinter.append("        1     2     3   \n");
        tablePrinter.append("L    +-----+-----+-----+\n");
        tablePrinter.append("   1 |  ");
        try {
            tablePrinter.append(getChar(table.getCell(0, 0)));
            tablePrinter.append("  |  ");
            tablePrinter.append(getChar(table.getCell(0, 1)));
            tablePrinter.append("  |  ");
            tablePrinter.append(getChar(table.getCell(0, 2)));
            tablePrinter.append("  |\n");
            tablePrinter.append("i    +-----+-----+-----+\n");
            tablePrinter.append("   2 |  ");
            tablePrinter.append(getChar(table.getCell(1, 0)));
            tablePrinter.append("  |  ");
            tablePrinter.append(getChar(table.getCell(1, 1)));
            tablePrinter.append("  |  ");
            tablePrinter.append(getChar(table.getCell(1, 2)));
            tablePrinter.append("  |\n");
            tablePrinter.append("n    +-----+-----+-----+\n");
            tablePrinter.append("   3 |  ");
            tablePrinter.append(getChar(table.getCell(2, 0)));
            tablePrinter.append("  |  ");
            tablePrinter.append(getChar(table.getCell(2, 1)));
            tablePrinter.append("  |  ");
            tablePrinter.append(getChar(table.getCell(2, 2)));
        } catch (CellException e) {
            e.printStackTrace();
        }
        tablePrinter.append("  |\n");
        tablePrinter.append("e    +-----+-----+-----+\n");
        System.out.println(tablePrinter);
    }

    private static String getChar(CellState cellValue) throws CellException {
        if (cellValue == null) {
            throw new CellException(ConsoleGame.HelpMessageType.VALUE_IS_NULL.getMessage());
        }

        switch (cellValue) {
            case UNSET: return " ";
            case TIC: return "x";
            case TAC: return "0";
            default: throw new CellException(ConsoleGame.HelpMessageType.ILLEGAL_STATE.getMessage());
        }
    }
}
