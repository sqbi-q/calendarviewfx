module com.sqbiq.javafxdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.sqbiq.javafxdemo to javafx.fxml;
    exports com.sqbiq.javafxdemo;
}