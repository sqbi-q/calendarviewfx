package com.sqbiq.javafxdemo;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class CalendarGrid extends GridPane {
    static final int ROWS_COUNT = 1 /*header*/ + 5 /*weeks*/;
    static final int COLS_COUNT = 7 /*days of week*/;
    private YearMonth date = YearMonth.now();

    private Locale displayLocale = Locale.getDefault();
    private Locale daysOfWeekLocale = Locale.getDefault();

    final WeekFields daysOfWeek = WeekFields.of(daysOfWeekLocale);

    public CalendarGrid() {
        drawCells();
    }

    public void setDate(YearMonth yearMonth) {
        date = yearMonth;
        drawCells();
    }

    public void setDisplayLocale(Locale locale) { displayLocale = locale; }
    public void setDaysOfWeekLocale(Locale locale) { daysOfWeekLocale = locale; }

    static private Button getCell(String text) {
        Button cell = new Button(text);
        cell.setMaxWidth(Double.MAX_VALUE);
        return cell;
    }

    private void drawCells() {
        getChildren().clear();
        initDaysOfWeekRow();
        initGrid();
    }

    private void initDaysOfWeekRow() {
        DayOfWeek day = daysOfWeek.getFirstDayOfWeek();
        // for every week day
        for (int i = 0; i < 7; i++) {
            Button cell = new Button(day.getDisplayName(TextStyle.SHORT, displayLocale));
            add(cell, i, 0);
            day = day.plus(1);
        }
    }

    private void initGrid() {
        LocalDate monthly = LocalDate.of(date.getYear(), date.getMonth(), 1);
        final DayOfWeek firstDayOfWeekInMonth = monthly.getDayOfWeek();
        final DayOfWeek firstDayOfWeek = daysOfWeek.getFirstDayOfWeek();
        final int daysInMonth = monthly.lengthOfMonth();

        // fill cells gap
        final int gap = Math.floorMod((firstDayOfWeekInMonth.getValue() - firstDayOfWeek.getValue()), 7);
        int cell_i = 0;

        for (; cell_i < gap; cell_i++) {
            add(getCell(""), cell_i, 1);
        }

        // fill cells date
        for (int cellCount = 0; cellCount < daysInMonth; cell_i++, cellCount++) {
            int cellCol = cell_i % 7;
            int cellRow = cell_i / 7;
            String day = Integer.toString(monthly.getDayOfMonth());
            add(getCell(day), cellCol, cellRow+1);
            monthly = monthly.plusDays(1);
        }
    }
}
