package com.sqbiq.javafxdemo.elements;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.time.Year;

public class YearSelect extends HBox {
    final private Button previous = new Button();
    final private Button next = new Button();
    final private YearField yearField;

    public YearSelect(Year defaultYear) {
        previous.setText("<");
        getChildren().add(previous);

        yearField = new YearField(defaultYear);
        getChildren().add(yearField);

        next.setText(">");
        getChildren().add(next);

        // on click go back one year
        previous.setOnAction(action -> yearField.previous());

        // on click go forward one year
        next.setOnAction(action -> yearField.next());
    }
}
