package com.example.algorithm_lab1.lab6.controllers;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import com.example.algorithm_lab1.HelloApplication;
import com.example.algorithm_lab1.lab6.dataStructures.Stack;
import com.example.algorithm_lab1.lab6.utilities.Utility;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class StackController implements Initializable {

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonClear;

    @FXML
    private ScrollPane scrollPlane;

    @FXML
    private Button buttonGetTop;

    @FXML
    private Button buttonMainMenu;

    @FXML
    private Button buttonPop;

    @FXML
    private Button buttonPrint;

    @FXML
    private Button buttonPush;

    @FXML
    private Button buttonPushManyRandom;

    @FXML
    private Button buttonPushRandom;

    @FXML
    private TextFlow textData;

    @FXML
    private Text textError;

    @FXML
    private TextField textFieldElement;

    private Stack stack = new Stack<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonPush.setOnAction(event -> {
            push();
        });

        buttonPrint.setOnAction(event -> {
            printStack();
        });

        buttonMainMenu.setOnAction(event -> {
            try {
                mainMenu();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonBack.setOnAction(event -> {
            try {
                back();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonPop.setOnAction(event -> {
            pop();
        });

        buttonGetTop.setOnAction(event -> {
            getTop();
        });

        buttonClear.setOnAction(event -> {
            textData.getChildren().clear();
            stack.clear();
            textFieldElement.setText("");
            textError.setText("");
        });

        buttonPushRandom.setOnAction(event -> {
            pushRandom();
        });

        buttonPushManyRandom.setOnAction(event -> {
            pushManyRandom();
        });

        textFieldElement.setOnMouseClicked(mouseEvent -> {
            textFieldElement.setText("");
        });
    }

    private void push(){
        switch (StructureTestController.typeData){
            case 'i':
                int i;
                try {
                    i = Integer.parseInt(textFieldElement.getText());
                    stack.push(i);
                    Utility.submitAnimation(textFieldElement);
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }

                break;
            case 's':
                stack.push(textFieldElement.getText());
                Utility.submitAnimation(textFieldElement);
                break;
            case 'd':
                try {
                    double d = Double.parseDouble(textFieldElement.getText());
                    stack.push(d);
                    Utility.submitAnimation(textFieldElement);
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }

                break;
            case 'f':
                try {
                    float f = Float.parseFloat(textFieldElement.getText());
                    stack.push(f);
                    Utility.submitAnimation(textFieldElement);
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }

                break;
        }
    }

    private void pushRandom(){
        Random random = new Random();

        switch (StructureTestController.typeData){
            case 'i':
                int i = random.nextInt(-100_000, 100_000);
                stack.push(i);

                Text pushText = new Text("Добавлено: " + i + "\n\n");
                println(pushText);

                break;

            case 's':
                String s = "";
                int l = random.nextInt(1, 16);

                for (int j = 0; j < l; j++) {
                    char c = (char) random.nextInt(0, 150);
                    s += c;
                }

                stack.push(s);
                pushText = new Text("Добавлено: " + s + "\n\n");
                println(pushText);

                break;

            case 'd':
                double d = random.nextDouble(-100_000, 100_000);
                stack.push(d);

                pushText = new Text("Добавлено: " + d + "\n\n");
                println(pushText);

                break;

            case 'f':
                float f = random.nextFloat(-100_000, 100_000);
                stack.push(f);

                pushText = new Text("Добавлено: " + f + "\n\n");
                println(pushText);

                break;
        }
    }

    private void pushManyRandom(){
        Random random = new Random();

        switch (StructureTestController.typeData){
            case 'i':
                for (int i = 0; i < 1_000_000; i++) {
                    int a = random.nextInt(-100_000, 100_000);
                    stack.push(a);
                }

                break;

            case 's':
                for (int i = 0; i < 1_000_000; i++) {
                    String s = "";
                    int l = random.nextInt(1, 16);

                    for (int j = 0; j < l; j++) {
                        char c = (char) random.nextInt(0, 150);
                        s += c;
                    }

                    stack.push(s);
                }

                break;

            case 'd':
                for (int i = 0; i < 1_000_000; i++) {
                    double a = random.nextDouble(-100_000, 100_000);
                    stack.push(a);
                }

                break;

            case 'f':
                for (int i = 0; i < 1_000_000; i++) {
                    float a = random.nextFloat(-100_000, 100_000);
                    stack.push(a);
                }

                break;
        }
    }

    private void pop(){
        String s = stack.pop().toString();
        Text popText = new Text("Извлечено: " + s + "\n\n");

        println(popText);
    }

    private void getTop(){
        Text topText = new Text("Пик: " + stack.getTop() + "\n\n");

        println(topText);
    }

    private void printStack(){
        if (stack.length() <= 10 && stack.length() > 0){
            textError.setText("");

            Text stackPrint = new Text(stack.toString() + "\n\n");

            println(stackPrint);
        }
        else {
            textError.setText("Стек пуст, или его длина больше 10");
        }
    }

    private void println(Text text){
        text.setFont(Font.font("Avenir Next Demi Bold", 25));

        textData.getChildren().add(text);

        /*if (text.getBoundsInParent().getHeight() >= textData.getHeight()){
            textData.setPrefHeight(textData.getHeight() + 50);
            textData.setPrefWidth(738);
        }*/

        //scrollPlane.setVvalue(1.0);

        Utility.scrollAnimation(scrollPlane);
    }

    private void mainMenu() throws Exception {
        Utility.changeScene(HelloApplication.class, "lab6MainMenu.fxml", buttonMainMenu.getScene().getWindow());
    }

    private void back() throws Exception {
        Utility.changeScene(HelloApplication.class, "lab6_structuresTest.fxml", buttonMainMenu.getScene().getWindow());
    }
}



