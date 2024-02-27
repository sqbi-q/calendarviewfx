package com.sqbiq.javafxdemo;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

public class Calendar extends VBox {
    private CalendarGrid calendarGrid = new CalendarGrid();
    private Text yearHeader = new Text();
    private Text monthHeader = new Text();
    private YearMonth date = YearMonth.now();

    public Calendar() {
        yearHeader.setText(Integer.toString(date.getYear()));
        getChildren().add(yearHeader);

        monthHeader.setText(date.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()));
        getChildren().add(monthHeader);

        calendarGrid.setDate(date);
        getChildren().add(calendarGrid);
    }
}
