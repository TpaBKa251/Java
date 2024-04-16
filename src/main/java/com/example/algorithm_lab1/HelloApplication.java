package com.example.algorithm_lab1;

import com.example.algorithm_lab1.lab6.utilities.Utility;
import com.example.algorithm_lab1.lab7.Human;
import com.example.algorithm_lab1.lab7.Model;
import com.example.algorithm_lab1.lab7.Robot;
import com.example.algorithm_lab1.lab7.Transporter;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    public static long beforeUsedMem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

    @Override
    public void start(Stage stage) throws IOException {
        beforeUsedMem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("lab6MainMenu.fxml")));
        stage.setScene(new Scene(fxmlLoader));
        stage.setTitle("Олгаритмы брат");
        stage.setOpacity(0);

        stage.show();

        Animation animation = Utility.getAnimationAppear(stage.opacityProperty());
        animation.play();
    }

    public static void main(String[] args) {
        Model model = new Model(2);
        model.addTransporter(new Transporter(3));
        model.addTransporter(new Transporter(2));

        model.getTransporter(0).addWorker(new Human(5));
        model.getTransporter(0).addWorker(new Human(9));
        model.getTransporter(0).addWorker(new Robot(2));

        model.getTransporter(1).addWorker(new Human(12));
        model.getTransporter(1).addWorker(new Human(8));





        launch();
    }
}