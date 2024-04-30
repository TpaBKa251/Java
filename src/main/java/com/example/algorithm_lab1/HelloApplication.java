package com.example.algorithm_lab1;

import com.example.algorithm_lab1.lab6.utilities.Utility;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class HelloApplication extends Application {

    public static long beforeUsedMem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
    //public static ModelSequential modelSequential;

    @Override
    public void start(Stage stage) throws IOException {
        //beforeUsedMem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("lab7_mainMenu.fxml")));
        stage.setScene(new Scene(fxmlLoader));
        stage.setTitle("Олгаритмы брат");
        stage.setOpacity(0);

        stage.show();

        Animation animation = Utility.getAnimationAppear(stage.opacityProperty());
        animation.play();
        stage.setOnCloseRequest(windowEvent -> Platform.exit());
    }

    public static void main(String[] args) {
        final JFrame jFrame = new JFrame();

        //loading an image from a file
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        final URL imageResource = HelloApplication.class.getClassLoader().getResource("com/example/algorithm_lab1/icon.jpg");
        final Image image = defaultToolkit.getImage(imageResource);

        //this is new since JDK 9
        final Taskbar taskbar = Taskbar.getTaskbar();

        try {
            //set icon for macOS (and other systems which do support this method)
            taskbar.setIconImage(image);
        } catch (final UnsupportedOperationException e) {
            System.out.println("The os does not support: 'taskbar.setIconImage'");
        } catch (final SecurityException e) {
            System.out.println("There was a security exception for: 'taskbar.setIconImage'");
        }

        /*//set icon for Windows os (and other systems which do support this method)
        jFrame.setIconImage(image);

        //adding something to the window so it does show up

        //some default JFrame things
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);*/
        launch();
    }
}