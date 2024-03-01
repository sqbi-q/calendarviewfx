package com.sqbiq.calendarviewfx.elements;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.time.Year;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class YearField extends TextField {

    private Year yearValue;
    private final List<ValueChangeListener> valueChangeListeners = new ArrayList<>();

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

    public interface ValueChangeListener extends EventListener {
        void changed();
    }

    public YearField(Year defaultYear) {
        setTextFormatter(yearTextFormatter);

        yearValue = defaultYear;
        updateYearValue();

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
                updateYearValue();
            }
        });
    }

    private void updateYearValue() {
        setText(yearValue.toString());

        // notify on year change to update calendar
        valueChangeListeners.forEach(listener -> listener.changed());
    }

    public void addValueChangeListener(ValueChangeListener onValueChange) {
        valueChangeListeners.add(onValueChange);
    }

    public void removeValueChangeListener(ValueChangeListener onValueChange) {
        valueChangeListeners.remove(onValueChange);
    }

    public Year getValue() {
        return yearValue;
    }

    public void previous() {
        yearValue = yearValue.minusYears(1);
        updateYearValue();
    }

    public void next() {
        yearValue = yearValue.plusYears(1);
        updateYearValue();
    }

    @Override
    public void cut() {
        // Disable cutting out year
        copy();
    }
}
