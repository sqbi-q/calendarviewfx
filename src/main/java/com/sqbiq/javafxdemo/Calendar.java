package com.sqbiq.javafxdemo;

import com.sqbiq.javafxdemo.elements.MonthItem;
import com.sqbiq.javafxdemo.elements.YearSelect;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Calendar extends VBox {
    private YearMonth date = YearMonth.now();
    private CalendarGrid calendarGrid = new CalendarGrid();
    private YearSelect yearHeader = new YearSelect(Year.from(date));
    private ComboBox<MonthItem> monthSelect = new ComboBox<MonthItem>();

    public Calendar() {
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
