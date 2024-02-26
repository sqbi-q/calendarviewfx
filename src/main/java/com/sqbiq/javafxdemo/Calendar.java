package com.sqbiq.javafxdemo;

import javafx.scene.layout.HBox;

import java.time.Month;

public class Calendar extends HBox {
    private CalendarGrid calendarGrid = new CalendarGrid();

    public Calendar() {
        getChildren().add(calendarGrid);
    }
}
