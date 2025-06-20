package com.htlshkoder.demo222;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 900);

        HelloController controller = fxmlLoader.getController();

        scene.setOnKeyPressed(controller::onKeyPressed);
        scene.setOnKeyReleased(controller::onKeyReleased);

        stage.setTitle("Pong Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}