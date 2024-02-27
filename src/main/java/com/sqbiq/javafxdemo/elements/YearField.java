package com.sqbiq.javafxdemo.elements;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.time.Year;

public class YearField extends TextField {

    private Year yearValue;

    static private TextFormatter<String> yearTextFormatter = new TextFormatter<>(change -> {
        if (change.isAdded()) {
            String newText = change.getControlNewText();
            try {
                int year = Integer.parseInt(newText);
                if (year < Year.MIN_VALUE || year > Year.MAX_VALUE)
                    return null;
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return change;
    });

    public YearField(Year defaultYear) {
        setTextFormatter(yearTextFormatter);

        yearValue = defaultYear;
        updateText();

        // on mouse click always focus and select year for faster input
        setOnMouseClicked(mouse -> {
            setFocused(true);
            selectAll();
        });

        // on enter set new year and unfocus
        setOnAction(action -> {
            yearValue = Year.of(Integer.parseInt(getText()));
            setFocused(false);
        });

        // when unfocused use last set year
        focusedProperty().addListener((obs, wasFocused, focused) -> {
            if (!focused) {
                updateText();
            }
        });
    }

    private void updateText() {
        setText(yearValue.toString());
    }

    public void previous() {
        yearValue = yearValue.minusYears(1);
        updateText();
    }

    public void next() {
        yearValue = yearValue.plusYears(1);
        updateText();
    }

    @Override
    public void cut() {
        // Disable cutting out year
        copy();
    }
}
