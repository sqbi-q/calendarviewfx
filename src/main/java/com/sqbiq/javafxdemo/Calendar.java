package com.sqbiq.javafxdemo;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Calendar extends GridPane {
    static final int ROWS_COUNT = 1 /*header*/ + 4 /*weeks*/;
    static final int COLS_COUNT = 7 /*days of week*/;
    public Calendar() {
        initHeaderRow();
        initGrid();
    }

    static private Button getCell() {
        Button cell = new Button("T");
        cell.setMaxWidth(Double.MAX_VALUE);
        return cell;
    }

    private void initHeaderRow() {
        final String[] days = {"Pn", "Wt", "Śr", "Cz", "Pt", "Sb", "Nd"};
        int i = 0;
        for (String day : days) {
            Button cell = new Button(day);
            add(cell, i, 0);
            i++;
        }
    }

    private void initGrid() {
        for (int y = 1; y < ROWS_COUNT; y++) {
            for (int x = 0; x < COLS_COUNT; x++) {
                add(getCell(), x, y);
            }
        }
    }
}
