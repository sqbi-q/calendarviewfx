package com.sqbiq.calendarviewfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CalendarApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
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