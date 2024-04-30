package com.example.algorithm_lab1.lab7.controllers;

import com.example.algorithm_lab1.HelloApplication;
import com.example.algorithm_lab1.lab6.utilities.Utility;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public class MainMenuController {

    @FXML
    private Button buttonParallelModel;

    @FXML
    private Button buttonSequentialModel;

    @FXML
    void initialize() {
        Tooltip t = new Tooltip("СКОРО...");
        t.setShowDelay(Duration.millis(0));
        Tooltip.install(buttonParallelModel, t);
    }

    @FXML
    private void parallelModelSettings() {

    }

    @FXML
    private void sequentialModelSettings() throws Exception {
        Utility.changeScene(HelloApplication.class, "lab7_modelSequential_settings.fxml", buttonSequentialModel.getScene().getWindow());
    }
}
