package com.sqbiq.javafxdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class CalendarApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        final WeekFields daysOfWeek = WeekFields.of(Locale.FRANCE);
//        final WeekFields daysOfWeek = WeekFields.of(Locale.getDefault());
        DayOfWeek day = daysOfWeek.getFirstDayOfWeek();

        for (int i = 0; i < 7 /*hopefully every locale has seven days in week*/; i++) {
            System.out.printf("%s ", day.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
            day = day.plus(1);
        }
        System.out.println();

        int selectedYear = 2024;
        Month selectedMonth = Month.FEBRUARY;
        LocalDate monthly = LocalDate.of(selectedYear, selectedMonth, 1);
        final DayOfWeek firstDayOfWeekInMonth = monthly.getDayOfWeek();
        final DayOfWeek firstDayOfWeek = daysOfWeek.getFirstDayOfWeek();
        final DayOfWeek lastDayOfWeek = daysOfWeek.getFirstDayOfWeek().minus(1);
        final int daysInMonth = monthly.lengthOfMonth();

        // fill cells gap
        final int gap = Math.floorMod((firstDayOfWeekInMonth.getValue() - firstDayOfWeek.getValue()), 7);
        System.out.print("  X ".repeat(gap));

        // fill cells with date
        for (int i = 0; i < daysInMonth; i++) {
            System.out.printf("%3d ", monthly.getDayOfMonth());
            if (monthly.getDayOfWeek() == lastDayOfWeek)
                System.out.println();
            monthly = monthly.plusDays(1);
        }


        Calendar root = new Calendar();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Calendar demo");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}