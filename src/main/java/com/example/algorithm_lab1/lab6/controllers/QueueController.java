package com.example.algorithm_lab1.lab6.controllers;

import java.util.Random;

import com.example.algorithm_lab1.HelloApplication;
import com.example.algorithm_lab1.lab6.dataStructures.Queue;
import com.example.algorithm_lab1.lab6.utilities.Utility;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class QueueController {

    @FXML
    private Button buttonBack;

    @FXML
    private ScrollPane scrollPlane;

    @FXML
    private Button buttonClear;

    @FXML
    private Button buttonDequeue;

    @FXML
    private Button buttonEnqueue;

    @FXML
    private Button buttonEnqueueManyRandom;

    @FXML
    private Button buttonEnqueueRandom;

    @FXML
    private Button buttonGetHead;

    @FXML
    private Button buttonMainMenu;

    @FXML
    private Button buttonPrint;

    @FXML
    private TextFlow textData;

    @FXML
    private Text textError;

    @FXML
    private TextField textFieldElement;

    private Queue queue = new Queue<>();


    @FXML
    void initialize() {
        buttonEnqueue.setOnAction(event -> {
            enqueue();
        });

        buttonPrint.setOnAction(event -> {
            printQueue();
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

        buttonDequeue.setOnAction(event -> {
            dequeue();
        });

        buttonGetHead.setOnAction(event -> {
            getHead();
        });

        buttonClear.setOnAction(event -> {
            textData.getChildren().clear();
            queue.clear();
            textFieldElement.setText("");
            textError.setText("");
        });

        buttonEnqueueRandom.setOnAction(event -> {
            enqueueRandom();
        });

        buttonEnqueueManyRandom.setOnAction(event -> {
            enqueueManyRandom();
        });

        textFieldElement.setOnMouseClicked(mouseEvent -> {
            textFieldElement.setText("");
        });
    }

    private void enqueue(){
        switch (StructureTestController.typeData){
            case 'i':
                int i;
                try {
                    i = Integer.parseInt(textFieldElement.getText());
                    queue.enqueue(i);
                    Utility.submitAnimation(textFieldElement);
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }

                break;
            case 's':
                queue.enqueue(textFieldElement.getText());
                Utility.submitAnimation(textFieldElement);
                break;
            case 'd':
                try {
                    double d = Double.parseDouble(textFieldElement.getText());
                    queue.enqueue(d);
                    Utility.submitAnimation(textFieldElement);
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }

                break;
            case 'f':
                try {
                    float f = Float.parseFloat(textFieldElement.getText());
                    queue.enqueue(f);
                    Utility.submitAnimation(textFieldElement);
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }

                break;
        }
    }

    private void enqueueRandom(){
        Random random = new Random();

        switch (StructureTestController.typeData){
            case 'i':
                int i = random.nextInt(-100_000, 100_000);
                queue.enqueue(i);

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

                queue.enqueue(s);
                pushText = new Text("Добавлено: " + s + "\n\n");
                println(pushText);

                break;

            case 'd':
                double d = random.nextDouble(-100_000, 100_000);
                queue.enqueue(d);

                pushText = new Text("Добавлено: " + d + "\n\n");
                println(pushText);

                break;

            case 'f':
                float f = random.nextFloat(-100_000, 100_000);
                queue.enqueue(f);

                pushText = new Text("Добавлено: " + f + "\n\n");
                println(pushText);

                break;
        }
    }

    private void enqueueManyRandom(){
        Random random = new Random();

        switch (StructureTestController.typeData){
            case 'i':
                for (int i = 0; i < 1_000_000; i++) {
                    int a = random.nextInt(-100_000, 100_000);
                    queue.enqueue(a);
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

                    queue.enqueue(s);
                }

                break;

            case 'd':
                for (int i = 0; i < 1_000_000; i++) {
                    double a = random.nextDouble(-100_000, 100_000);
                    queue.enqueue(a);
                }

                break;

            case 'f':
                for (int i = 0; i < 1_000_000; i++) {
                    float a = random.nextFloat(-100_000, 100_000);
                    queue.enqueue(a);
                }

                break;
        }
    }

    private void dequeue(){
        String s = queue.dequeue().toString();
        Text popText = new Text("Извлечено: " + s + "\n\n");

        println(popText);
    }

    private void getHead(){
        Text topText = new Text("Голова: " + queue.getHead() + "\n\n");

        println(topText);
    }

    private void printQueue(){
        if (queue.length() <= 10 && queue.length() > 0){
            textError.setText("");

            Text stackPrint = new Text(queue.toString() + "\n\n");

            println(stackPrint);
        }
        else {
            textError.setText("Очередь пуста, или её длина больше 10");
        }
    }

    private void println(Text text){
        text.setFont(Font.font("Avenir Next Demi Bold", 25));

        textData.getChildren().add(text);

        /*if (text.getBoundsInParent().getHeight() >= textData.getHeight()){
            textData.setPrefHeight(textData.getHeight() + 50);
            textData.setPrefWidth(738);
        }*/

        Utility.scrollAnimation(scrollPlane);
    }

    private void mainMenu() throws Exception {
        Utility.changeScene(HelloApplication.class, "lab6MainMenu.fxml", buttonMainMenu.getScene().getWindow());
    }

    private void back() throws Exception {
        Utility.changeScene(HelloApplication.class, "lab6_structuresTest.fxml", buttonMainMenu.getScene().getWindow());
    }
}



