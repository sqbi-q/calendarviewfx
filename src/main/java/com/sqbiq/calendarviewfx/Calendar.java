package com.sqbiq.calendarviewfx;

import com.sqbiq.calendarviewfx.elements.MonthItem;
import com.sqbiq.calendarviewfx.elements.YearSelect;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.*;

public class Calendar extends VBox {
    private YearMonth date = YearMonth.now();
    private CalendarGrid calendarGrid = new CalendarGrid();
    private YearSelect yearSelect = new YearSelect(Year.from(date));
    private ComboBox<MonthItem> monthSelect = new ComboBox<MonthItem>();

    private final List<DateChangeListener> dateChangeListeners = new ArrayList<>();

    public interface DateChangeListener extends EventListener {
        void changed();
    }

    public Calendar() {
        setAlignment(Pos.CENTER);

        yearSelect.getStyleClass().add("year-select");
        yearSelect.setAlignment(Pos.CENTER);
        getChildren().add(yearSelect);

        List<MonthItem> months = Arrays.stream(Month.values())
                .map(month -> new MonthItem(month, calendarGrid.getDisplayLocale()))
                .toList();

        monthSelect.getStyleClass().add("month-select");
        monthSelect.getItems().addAll(months);
        getChildren().add(monthSelect);

        // select default month
        monthSelect.getSelectionModel().select(new MonthItem(date.getMonth(), calendarGrid.getDisplayLocale()));

        calendarGrid.getStyleClass().add("calendar-grid");
        calendarGrid.setAlignment(Pos.CENTER);
        calendarGrid.setDate(date);
        getChildren().add(calendarGrid);

        // on year or month change update date displayed on grid
        yearSelect.addValueChangedListener(this::updateDate);
        monthSelect.valueProperty().addListener(
                (obs, oldVal, newVal) -> updateDate());

        // load stylesheet resource
        String stylesheetRes = getClass().getResource("CalendarStylesheet.css").toExternalForm();
        getStylesheets().add(stylesheetRes);
    }

    public void setDisplayLocale(Locale locale) {
        calendarGrid.setDisplayLocale(locale);
    }

    public void setDaysOfWeekLocale(Locale locale) {
        calendarGrid.setDaysOfWeekLocale(locale);
    }

    public YearMonth getDate() {
        return date;
    }

    public CalendarGrid getCalendarGrid() {
        return calendarGrid;
    }

    public void addChangeDateListener(DateChangeListener onDateChange) {
        dateChangeListeners.add(onDateChange);
    }

    public void removeChangeDateListener(DateChangeListener onDateChange) {
        dateChangeListeners.remove(onDateChange);
    }

    private void updateDate() {
        Month selectedMonth = monthSelect.getValue().getValue();
        Year selectedYear = yearSelect.getValue();
        date = YearMonth.of(selectedYear.getValue(), selectedMonth);
        calendarGrid.setDate(date);
        dateChangeListeners.forEach(listener -> listener.changed());
    }
}
