package com.sqbiq.javafxdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
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