package com.example.algorithm_lab1.lab6.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.algorithm_lab1.HelloApplication;
import com.example.algorithm_lab1.lab6.utilities.Utility;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MemoryTestController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonMainMenu;

    @FXML
    private Button buttonStart;

    @FXML
    private CheckBox checkDouble;

    @FXML
    private CheckBox checkFloat;

    @FXML
    private CheckBox checkInt;

    @FXML
    private CheckBox checkString;

    @FXML
    private Text textError;

    @FXML
    void initialize() {
        checkInt.setOnAction(event -> {
            checkFloat.setSelected(false);
            checkDouble.setSelected(false);
            checkString.setSelected(false);
            StructureTestController.typeData = 'i';
        });
        checkString.setOnAction(event -> {
            checkFloat.setSelected(false);
            checkDouble.setSelected(false);
            checkInt.setSelected(false);
            StructureTestController.typeData = 's';
        });
        checkDouble.setOnAction(event -> {
            checkFloat.setSelected(false);
            checkInt.setSelected(false);
            checkString.setSelected(false);
            StructureTestController.typeData = 'd';
        });
        checkFloat.setOnAction(event -> {
            checkInt.setSelected(false);
            checkDouble.setSelected(false);
            checkString.setSelected(false);
            StructureTestController.typeData = 'f';
        });


        buttonMainMenu.setOnAction(event -> {
            try {
                mainMenu();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonStart.setOnAction(event -> {
            try {
                start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void start() throws Exception {
        if (!checkInt.isSelected() && !checkString.isSelected() && !checkDouble.isSelected() && !checkFloat.isSelected()){
            textError.setText("Выберите тип данных!");
        }
        else {
            Utility.changeScene(HelloApplication.class, "lab6_testMemoryOnStack.fxml", buttonStart.getScene().getWindow());
        }
    }

    private void mainMenu() throws Exception {
        Utility.changeScene(HelloApplication.class, "lab6MainMenu.fxml", buttonMainMenu.getScene().getWindow());
    }
}

