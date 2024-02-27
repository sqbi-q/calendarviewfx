package com.sqbiq.javafxdemo;

import com.sqbiq.javafxdemo.elements.MonthItem;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.Month;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Calendar extends VBox {
    private CalendarGrid calendarGrid = new CalendarGrid();
    private Text yearHeader = new Text();
    private ComboBox<MonthItem> monthSelect = new ComboBox<MonthItem>();
    private YearMonth date = YearMonth.now();

    public Calendar() {
        yearHeader.setText(Integer.toString(date.getYear()));
        getChildren().add(yearHeader);
        
        List<MonthItem> months = Arrays.stream(Month.values())
                .map(month -> new MonthItem(month, Locale.getDefault()))
                .toList();

        monthSelect.getItems().addAll(months);
        getChildren().add(monthSelect);

        calendarGrid.setDate(date);
        getChildren().add(calendarGrid);
    }
}
