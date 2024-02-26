package com.sqbiq.javafxdemo;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class CalendarGrid extends GridPane {
    static final int ROWS_COUNT = 1 /*header*/ + 4 /*weeks*/;
    static final int COLS_COUNT = 7 /*days of week*/;

    public CalendarGrid() {
        initDaysOfWeekRow();
        initGrid();
    }

    static private Button getCell() {
        Button cell = new Button("T");
        cell.setMaxWidth(Double.MAX_VALUE);
        return cell;
    }

    private void initDaysOfWeekRow() {
        final WeekFields daysOfWeek = WeekFields.of(Locale.FRANCE);
        DayOfWeek day = daysOfWeek.getFirstDayOfWeek();
        // for every week day
        for (int i = 0; i < 7; i++) {
            Button cell = new Button(day.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
            add(cell, i, 0);
            day = day.plus(1);
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
