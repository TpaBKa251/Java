package com.example.algorithm_lab1.lab6.controllers;

import com.example.algorithm_lab1.HelloApplication;
import com.example.algorithm_lab1.lab6.utilities.Utility;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    private Button buttonTestClasses;

    @FXML
    private Button buttonTestMemory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonTestClasses.setOnAction(event -> {
            try {
                setTestClassesScene();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonTestMemory.setOnAction(event -> {
            try {
                setTestMemoryScene();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setTestClassesScene() throws Exception {
        Utility.changeScene(HelloApplication.class, "lab6_structuresTest.fxml", buttonTestClasses.getScene().getWindow());
    }

    private void setTestMemoryScene() throws Exception {
        Utility.changeScene(HelloApplication.class, "lab6_memoryTest.fxml", buttonTestMemory.getScene().getWindow());
    }

}
