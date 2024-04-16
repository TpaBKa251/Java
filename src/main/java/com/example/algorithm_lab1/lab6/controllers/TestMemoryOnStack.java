package com.example.algorithm_lab1.lab6.controllers;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import com.example.algorithm_lab1.HelloApplication;
import com.example.algorithm_lab1.lab6.dataStructures.Stack;
import com.example.algorithm_lab1.lab6.utilities.Utility;
import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

public class TestMemoryOnStack implements Initializable {

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonClear;

    @FXML
    private ScrollPane scrollPlane;

    @FXML
    private Button buttonGetMemory;

    @FXML
    private Button buttonPopMany;

    @FXML
    private Button buttonMainMenu;

    @FXML
    private Button buttonPop;

    @FXML
    private Button buttonPushManyRandom;

    @FXML
    private Button buttonPushRandom;

    @FXML
    private LineChart<String, Integer> graphMemory;

    @FXML
    private TextFlow textData;

    private Stack stack = new Stack<>();

    private XYChart.Series<String, Integer> graphMemoryContainer = new XYChart.Series<>();

    private int time = 0;

    private int memoryStr = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        buttonClear.setOnAction(event -> {
            stack.clear();
            println(new Text("Стек очищен\n\n"));
            memoryStr = 0;
            System.gc();
        });

        buttonPushRandom.setOnAction(event -> {
            pushRandom();
        });

        buttonPushManyRandom.setOnAction(event -> {
            pushManyRandom();
        });

        buttonGetMemory.setOnAction(event -> {
            memory();
        });

        buttonPopMany.setOnAction(event -> {
            popMany();
        });
    }

    private void pushRandom(){
        Random random = new Random();

        switch (StructureTestController.typeData){
            case 'i':
                int i = random.nextInt(-100_000, 100_000);
                stack.push(i);

                Text pushText = new Text("Добавлено рандомное значение\n\n");
                println(pushText);

                break;

            case 's':
                String s = "";
                int l = random.nextInt(1, 16);

                memoryStr += 8 * (int) ((l * 2 + 45 ) / 8);

                for (int j = 0; j < l; j++) {
                    char c = (char) random.nextInt(0, 150);
                    s += c;
                }

                stack.push(s);
                pushText = new Text("Добавлено рандомное значение\n\n");
                println(pushText);

                break;

            case 'd':
                double d = random.nextDouble(-100_000, 100_000);
                stack.push(d);

                pushText = new Text("Добавлено рандомное значение\n\n");
                println(pushText);

                break;

            case 'f':
                float f = random.nextFloat(-100_000, 100_000);
                stack.push(f);

                pushText = new Text("Добавлено рандомное значение\n\n");
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

                Text pushText = new Text("Добавлен 1млн рандомных значений\n\n");
                println(pushText);

                break;

            case 's':
                for (int i = 0; i < 1_000_000; i++) {
                    String s = "";
                    int l = random.nextInt(1, 16);

                    memoryStr += 8 * (int) ((l * 2 + 45 ) / 8);

                    for (int j = 0; j < l; j++) {
                        char c = (char) random.nextInt(0, 150);
                        s += c;
                    }

                    stack.push(s);
                }

                pushText = new Text("Добавлен 1млн рандомных значений\n\n");
                println(pushText);

                break;

            case 'd':
                for (int i = 0; i < 1_000_000; i++) {
                    double a = random.nextDouble(-100_000, 100_000);
                    stack.push(a);
                }

                pushText = new Text("Добавлен 1млн рандомных значений\n\n");
                println(pushText);

                break;

            case 'f':
                for (int i = 0; i < 1_000_000; i++) {
                    float a = random.nextFloat(-100_000, 100_000);
                    stack.push(a);
                }

                pushText = new Text("Добавлен 1млн рандомных значений\n\n");
                println(pushText);

                break;
        }
    }

    private void pop(){
        Object o = stack.pop();
        if (o != null){
            Text popText = new Text("Извлечено\n\n");
            println(popText);
        }
        System.gc();
    }

    private void popMany(){
        for (int i = 0; i < 1_000_000; i++) {
            Object o = stack.pop();
            if (o == null){
                Text popText = new Text("Извлечено " + i + " элементов\n\n");
                println(popText);
                System.gc();
                return;
            }
        }

        Text popText = new Text("Извлечено 1млн элементов\n\n");
        println(popText);
        System.gc();
    }

    private void memory(){
        time += 1;
        long afterUsedBytes = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long usedBytes = afterUsedBytes - HelloApplication.beforeUsedMem;
        float stackMemory = 0;
        switch (StructureTestController.typeData){
            case 'i':
                stackMemory = (float) ((8 * (int)(stack.length() * 4 + 16 + 4 + 4 + 12) / 8) / 1024.0);
                break;
            case 's':
                stackMemory = (float) ((8 * (int)(memoryStr + 16 + 4 + 4 + 12) / 8) / 1024.0);
                break;
            case 'd':
                stackMemory = (float) ((8 * (int)(stack.length() * 8 + 16 + 4 + 4 + 12) / 8) / 1024.0);
                break;
            case 'f':
                stackMemory = (float) ((8 * (int)(stack.length() * 4 + 16 + 4 + 4 + 12) / 8) / 1024.0);
                break;
        }


        println(new Text("Память: " + usedBytes/1024 + " кб\n\n"));
        println(new Text("Память стека: " + stackMemory + " кб\n\n"));
        println(new Text("Количество элементов стека: " + stack.length() + "\n\n"));

        XYChart.Data<String, Integer> k = new XYChart.Data<>(Integer.toString(time), (int) (usedBytes/1024));


        graphMemoryContainer.getData().add(k);
        Tooltip t = new Tooltip(stack.length() + " элементов\nПамять: " + usedBytes/1024 + " кб\nСтек: " +
                stackMemory + " кб");
        t.setShowDuration(new Duration(100000));
        t.setShowDelay(new Duration(0));
        t.setHideDelay(new Duration(200));


        //Tooltip.install(graphMemoryContainer.getData().get(time-1).getNode(), t);

        graphMemory.getData().clear();

        graphMemory.getData().add(graphMemoryContainer);
        Node node = graphMemory.getData().get(0).getData().get(time-1).getNode();

        node.setOnMouseEntered(mouseEvent -> {
            t.setOpacity(0);
            Animation animation = Utility.getAnimationAppear(t.opacityProperty());
            animation.play();
        });

        node.setOnMouseExited(mouseEvent -> {
            t.setOpacity(1);
            Animation animation = Utility.getAnimationFade(t.opacityProperty());
            animation.play();
        });

        Tooltip.install(node, t);
    }

    private void println(Text text){
        text.setFont(Font.font("Avenir Next Demi Bold", 25));

        textData.getChildren().add(text);

        /*if (text.getBoundsInParent().getHeight() >= textData.getHeight()){
            textData.setPrefHeight(textData.getHeight() + 50);
        }*/

        Utility.scrollAnimation(scrollPlane);
    }

    private void mainMenu() throws Exception {
        Utility.changeScene(HelloApplication.class, "lab6MainMenu.fxml", buttonMainMenu.getScene().getWindow());
    }

    private void back() throws Exception {
        Utility.changeScene(HelloApplication.class, "lab6_memoryTest.fxml", buttonMainMenu.getScene().getWindow());
    }
}

