package com.company.table;

import com.company.ConsoleGame;

public enum CellState {
    UNSET,
    TIC,
    TAC;

    public CellState inverse() {
        switch (this) {
            case UNSET:
                return UNSET;
            case TIC:
                return TAC;
            case TAC:
                return TIC;
            default:
                throw new IllegalStateException(ConsoleGame.HelpMessageType.UNKNOWN_CELL_STATE.getMessage());
        }
    }
}
