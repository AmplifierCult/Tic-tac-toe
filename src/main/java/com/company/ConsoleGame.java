package com.company;

import com.company.controller.Controller;
import com.company.table.CellException;

import java.io.IOException;

public class ConsoleGame {
    public static void main(String[] args) throws CellException, IOException {

        Controller controller = new Controller();
        controller.playGame();
    }
}
