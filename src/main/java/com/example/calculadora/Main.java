package com.example.calculadora;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("calculadora.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 610);
        Image logo = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/media/logoCalculadora.png")));
        stage.getIcons().add(logo);
        stage.setTitle("Main");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}