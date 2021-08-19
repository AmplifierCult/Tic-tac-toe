package com.company.table;

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
                throw new IllegalStateException("Unknown cell state");
        }
    }
}
