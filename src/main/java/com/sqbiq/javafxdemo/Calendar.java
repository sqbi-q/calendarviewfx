package com.sqbiq.javafxdemo;

import javafx.scene.layout.VBox;

import java.time.Month;

public class Calendar extends VBox {
    private CalendarGrid calendarGrid = new CalendarGrid();

    public Calendar() {
        getChildren().add(calendarGrid);
    }
}
