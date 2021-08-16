package com.company;

import com.company.table.CellException;
import com.company.table.CellState;
import com.company.table.Table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Player {
    protected String name;
    protected CellState character;
    public abstract void play(Table table) throws IOException, CellException;
    public abstract String getName();

    protected String enterNumber() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String number = reader.readLine();
        while (!validateNumber(number)) {
            number = reader.readLine();
        }
        return number;
    }

    protected boolean validateNumber(String number) {
        if (number.equals("")) {
            System.out.println("Illegal number. Try again.");
            return false;
        }
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            System.out.println("Illegal number. Try again.");
            return false;
        }
        return true;
    }
}
