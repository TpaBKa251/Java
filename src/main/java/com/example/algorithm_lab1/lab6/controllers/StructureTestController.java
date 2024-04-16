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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StructureTestController {

    @FXML
    private Button buttonLinkedList;

    @FXML
    private Button buttonMainMenu;

    @FXML
    private Button buttonQueue;

    @FXML
    private Button buttonStack;

    @FXML
    private CheckBox checkDouble;

    @FXML
    private CheckBox checkFloat;

    @FXML
    private CheckBox checkInt;

    @FXML
    private CheckBox checkString;

    public static char typeData;

    private char buttonName;

    @FXML
    private Text textError;

    @FXML
    void initialize() {
        checkInt.setOnAction(event -> {
            checkFloat.setSelected(false);
            checkDouble.setSelected(false);
            checkString.setSelected(false);
            typeData = 'i';
        });
        checkString.setOnAction(event -> {
            checkFloat.setSelected(false);
            checkDouble.setSelected(false);
            checkInt.setSelected(false);
            typeData = 's';
        });
        checkDouble.setOnAction(event -> {
            checkFloat.setSelected(false);
            checkInt.setSelected(false);
            checkString.setSelected(false);
            typeData = 'd';
        });
        checkFloat.setOnAction(event -> {
            checkInt.setSelected(false);
            checkDouble.setSelected(false);
            checkString.setSelected(false);
            typeData = 'f';
        });

        buttonStack.setOnAction(event -> {
            try {
                testScene('s');
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        buttonQueue.setOnAction(event -> {
            try {
                testScene('q');
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        buttonLinkedList.setOnAction(event -> {
            try {
                testScene('l');
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        buttonMainMenu.setOnAction(event -> {
            try {
                mainMenu();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    private void testScene(char c) throws Exception {
        if (!checkInt.isSelected() && !checkString.isSelected() && !checkDouble.isSelected() && !checkFloat.isSelected()){
            textError.setText("Выберите тип данных!");
        }
        else {
            switch (c){
                case 's':
                    Utility.changeScene(HelloApplication.class, "lab6_stack.fxml", buttonLinkedList.getScene().getWindow());
                    break;

                case 'q':
                    Utility.changeScene(HelloApplication.class, "lab6_queue.fxml", buttonLinkedList.getScene().getWindow());
                    break;

                case 'l':
                    Utility.changeScene(HelloApplication.class, "lab6_linkedList.fxml", buttonLinkedList.getScene().getWindow());
                    break;
            }
        }
    }


    private void mainMenu() throws Exception {
        Utility.changeScene(HelloApplication.class, "lab6MainMenu.fxml", buttonMainMenu.getScene().getWindow());
    }
}

