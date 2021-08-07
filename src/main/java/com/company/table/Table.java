package com.company.table;

import java.util.Arrays;

public class Table {
    private final Object [][] table;
    private int count;

    public Table() {
        table = new Object[3][3];
    }

    public Object getCell(int index1, int index2) {
        return table[index1][index2];
    }

    public Object setCell(int index1, int index2, Boolean value) {
        Object cell = table[index1][index2];
        if (cell == null) {
            table[index1][index2] = value;
            count++;
            return value;
        } else {
            return null;
        }
    }

    public int getNumberOfRecords() {
        return count;
    }
}
