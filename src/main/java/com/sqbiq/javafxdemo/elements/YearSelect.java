package com.sqbiq.javafxdemo.elements;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.time.Year;

public class YearSelect extends HBox {
    final private Button previous = new Button();
    final private Button next = new Button();
    private Year yearValue;
    final private TextField yearField = new TextField();

    public YearSelect(Year defaultYear) {
        previous.setText("<");
        getChildren().add(previous);

        yearValue = defaultYear;

        yearField.setText(yearValue.toString());
        getChildren().add(yearField);

        next.setText(">");
        getChildren().add(next);
    }

    public void setYear(Year year) {
        yearValue = year;
        yearField.setText(yearValue.toString());
    }
}
