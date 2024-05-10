module com.example.calculadora {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.calculadora to javafx.fxml;
    exports com.example.calculadora;
    exports com.example.calculadora.controller;
    opens com.example.calculadora.controller to javafx.fxml;
}