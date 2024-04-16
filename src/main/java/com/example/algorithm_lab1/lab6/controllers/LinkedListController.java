package com.example.algorithm_lab1.lab6.controllers;

import java.util.Random;

import com.example.algorithm_lab1.HelloApplication;
import com.example.algorithm_lab1.lab6.dataStructures.LinkedList;
import com.example.algorithm_lab1.lab6.utilities.Utility;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class LinkedListController {

    @FXML
    private MenuItem buttonAddLast;

    @FXML
    private ScrollPane scrollPlane;

    @FXML
    private MenuItem buttonAddByIndex;

    @FXML
    private MenuItem buttonAddFirst;

    @FXML
    private Button buttonAddManyRandom;

    @FXML
    private Button buttonAddRandom;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonClear;

    @FXML
    private MenuItem buttonGetByIndex;

    @FXML
    private MenuItem buttonGetFirst;

    @FXML
    private MenuItem buttonGetLast;

    @FXML
    private Button buttonMainMenu;

    @FXML
    private Button buttonPrint;

    @FXML
    private MenuItem buttonRemoveByIndex;

    @FXML
    private MenuItem buttonRemoveByValue;

    @FXML
    private MenuItem buttonRemoveFirst;

    @FXML
    private MenuItem buttonRemoveLast;

    @FXML
    private Button buttonSet;

    @FXML
    private TextFlow textData;

    @FXML
    private Text textError;

    @FXML
    private TextField textFieldElement;

    @FXML
    private TextField textFieldIndex;

    private final LinkedList<Object> list = new LinkedList<>();

    @FXML
    void initialize() {
        buttonBack.setOnAction(event -> {
            try {
                back();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonMainMenu.setOnAction(event -> {
            try {
                mainMenu();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ;
        });

        buttonAddFirst.setOnAction(event -> addFirst());

        buttonAddLast.setOnAction(event -> add());

        buttonAddByIndex.setOnAction(event -> {
            addByIndex();
        });

        buttonSet.setOnAction(event -> {
            set();
        });

        buttonAddRandom.setOnAction(event -> {
            randomElement();
        });

        buttonAddManyRandom.setOnAction(event -> {
            addManyRandom();
        });

        buttonRemoveFirst.setOnAction(event -> {
            removeFirst();
        });

        buttonRemoveLast.setOnAction(event -> {
            removeLast();
        });

        buttonRemoveByValue.setOnAction(event -> {
            remove();
        });

        buttonRemoveByIndex.setOnAction(event -> {
            removeByIndex();
        });

        buttonPrint.setOnAction(event -> {
            printList();
        });

        buttonClear.setOnAction(event -> {
            textData.getChildren().clear();
            list.clear();
            textFieldElement.setText("");
            textError.setText("");
            textFieldIndex.setText("");
        });

        buttonGetFirst.setOnAction(event -> {
            getFirst();
        });

        buttonGetLast.setOnAction(event -> {
            getLast();
        });

        buttonGetByIndex.setOnAction(event -> {
            get();
        });

        textFieldElement.setOnMouseClicked(mouseEvent -> {
            textFieldElement.setText("");
        });

        textFieldIndex.setOnMouseClicked(mouseEvent -> {
            textFieldIndex.setText("");
        });
    }

    //region<Эды и сет>
    private void addFirst(){
        switch (StructureTestController.typeData){
            case 'i':
                int i;
                try {
                    i = Integer.parseInt(textFieldElement.getText());
                    list.addFirst(i);
                    Utility.submitAnimation(textFieldElement);
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }

                break;
            case 's':
                list.addFirst(textFieldElement.getText());
                Utility.submitAnimation(textFieldElement);
                break;
            case 'd':
                try {
                    double d = Double.parseDouble(textFieldElement.getText());
                    list.addFirst(d);
                    Utility.submitAnimation(textFieldElement);
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);

                }

                break;
            case 'f':
                try {
                    float f = Float.parseFloat(textFieldElement.getText());
                    list.addFirst(f);
                    Utility.submitAnimation(textFieldElement);
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }

                break;
        }
    }

    private void add(){
        switch (StructureTestController.typeData){
            case 'i':
                int i;
                try {
                    i = Integer.parseInt(textFieldElement.getText());
                    list.add(i);
                    Utility.submitAnimation(textFieldElement);
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }

                break;
            case 's':
                list.add(textFieldElement.getText());
                Utility.submitAnimation(textFieldElement);
                break;
            case 'd':
                try {
                    double d = Double.parseDouble(textFieldElement.getText());
                    list.add(d);
                    Utility.submitAnimation(textFieldElement);
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }

                break;
            case 'f':
                try {
                    float f = Float.parseFloat(textFieldElement.getText());
                    list.add(f);
                    Utility.submitAnimation(textFieldElement);
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }

                break;
        }
    }

    private void addByIndex(){
        switch (StructureTestController.typeData){
            case 'i':
                int i;
                try {
                    i = Integer.parseInt(textFieldElement.getText());
                    list.add(Integer.parseInt(textFieldIndex.getText()), i);
                    Utility.submitAnimation(textFieldElement);
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }
                catch (IndexOutOfBoundsException e){
                    Utility.shakeIndexAnimation(textFieldIndex);
                }

                break;

            case 's':
                try {
                    list.add(Integer.parseInt(textFieldIndex.getText()), textFieldElement.getText());
                    Utility.submitAnimation(textFieldElement);
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }
                catch (IndexOutOfBoundsException e){
                    Utility.shakeIndexAnimation(textFieldIndex);
                }

                break;

            case 'd':
                try {
                    double d = Double.parseDouble(textFieldElement.getText());
                    list.add(Integer.parseInt(textFieldIndex.getText()), d);
                    Utility.submitAnimation(textFieldElement);
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }
                catch (IndexOutOfBoundsException e){
                    Utility.shakeIndexAnimation(textFieldIndex);
                }

                break;

            case 'f':
                try {
                    float f = Float.parseFloat(textFieldElement.getText());
                    list.add(Integer.parseInt(textFieldIndex.getText()), f);
                    Utility.submitAnimation(textFieldElement);
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }
                catch (IndexOutOfBoundsException e){
                    Utility.shakeIndexAnimation(textFieldIndex);
                }

                break;
        }
    }

    private void set(){
        Text setText = new Text();

        switch (StructureTestController.typeData){
            case 'i':
                int i;
                try {
                    i = Integer.parseInt(textFieldElement.getText());
                    int i2 = (int) list.get(Integer.parseInt(textFieldIndex.getText()));
                    list.set(Integer.parseInt(textFieldIndex.getText()), i);

                    setText = new Text("Элемент " + i2 + " заменен на " + i + "\n\n");
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }
                catch (IndexOutOfBoundsException e){
                    Utility.shakeIndexAnimation(textFieldIndex);
                }

                break;
            case 's':
                try {
                    String s2 = (String) list.get(Integer.parseInt(textFieldIndex.getText()));
                    list.set(Integer.parseInt(textFieldIndex.getText()), textFieldElement.getText());

                    setText = new Text("Элемент " + s2 + " заменен на " + textFieldElement.getText() + "\n\n");
                }
                catch (IndexOutOfBoundsException e){
                    Utility.shakeIndexAnimation(textFieldIndex);
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }

                break;
            case 'd':
                try {
                    double d = Double.parseDouble(textFieldElement.getText());
                    double d2 = (double) list.get(Integer.parseInt(textFieldIndex.getText()));
                    list.set(Integer.parseInt(textFieldIndex.getText()), d);

                    setText = new Text("Элемент " + d2 + " заменен на " + d + "\n\n");
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }
                catch (IndexOutOfBoundsException e){
                    Utility.shakeIndexAnimation(textFieldIndex);
                }

                break;
            case 'f':
                try {
                    float f = Float.parseFloat(textFieldElement.getText());
                    float f2 = (float) list.get(Integer.parseInt(textFieldIndex.getText()));
                    list.set(Integer.parseInt(textFieldIndex.getText()), f);

                    setText = new Text("Элемент " + f2 + " заменен на " + f + "\n\n");
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }
                catch (IndexOutOfBoundsException e){
                    Utility.shakeIndexAnimation(textFieldIndex);
                }

                break;
        }

        println(setText);
    }

    private void randomElement(){
        Random random = new Random();

        switch (StructureTestController.typeData){
            case 'i':
                int i = random.nextInt(-100_000, 100_000);
                textFieldElement.setText("" + i);

                break;

            case 's':
                String s = "";
                int l = random.nextInt(1, 16);

                for (int j = 0; j < l; j++) {
                    char c = (char) random.nextInt(0, 150);
                    s += c;
                }

                textFieldElement.setText(s);

                break;

            case 'd':
                double d = random.nextDouble(-100_000, 100_000);
                textFieldElement.setText("" + d);

                break;

            case 'f':
                float f = random.nextFloat(-100_000, 100_000);
                textFieldElement.setText("" + f);

                break;
        }
    }

    private void addManyRandom(){
        Random random = new Random();

        switch (StructureTestController.typeData){
            case 'i':
                for (int i = 0; i < 1_000_000; i++) {
                    int a = random.nextInt(-100_000, 100_000);
                    list.add(a);
                }

                break;

            case 's':
                for (int i = 0; i < 1_000_000; i++) {
                    StringBuilder s = new StringBuilder();
                    int l = random.nextInt(1, 16);

                    for (int j = 0; j < l; j++) {
                        char c = (char) random.nextInt(0, 150);
                        s.append(c);
                    }

                    list.add(s.toString());
                }

                break;

            case 'd':
                for (int i = 0; i < 1_000_000; i++) {
                    double a = random.nextDouble(-100_000, 100_000);
                    list.add(a);
                }

                break;

            case 'f':
                for (int i = 0; i < 1_000_000; i++) {
                    float a = random.nextFloat(-100_000, 100_000);
                    list.add(a);
                }

                break;
        }
    }
    //endregion




    //region<Ремувы>
    private void removeFirst(){
        String s = list.removeFirst().toString();
        Text popText = new Text("Удален первый элемент: " + s + "\n\n");

        println(popText);
    }

    private void removeLast(){
        String s = list.removeLast().toString();
        Text popText = new Text("Удален последний элемент: " + s + "\n\n");

        println(popText);
    }

    private void remove(){
        Text popText = new Text();

        switch (StructureTestController.typeData){
            case 'i':
                try {
                    int i = Integer.parseInt(textFieldElement.getText());

                    if (list.remove(i)){
                        popText = new Text("Элемент " + textFieldElement.getText() + " удален\n\n");
                    }
                    else {
                        popText = new Text("Элемент " + textFieldElement.getText() + " не найден\n\n");
                    }
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }

                break;

            case 's':
                if (list.remove(textFieldElement.getText())){
                    popText = new Text("Элемент " + textFieldElement.getText() + "удален\n\n");
                }
                else {
                    popText = new Text("Элемент " + textFieldElement.getText() + "не найден\n\n");
                }

                break;

            case 'd':
                try {
                    double d = Double.parseDouble(textFieldElement.getText());
                    if (list.remove(d)){
                        popText = new Text("Элемент " + textFieldElement.getText() + "удален\n\n");
                    }
                    else {
                        popText = new Text("Элемент " + textFieldElement.getText() + "не найден\n\n");
                    }
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }

                break;

            case 'f':
                try {
                    float f = Float.parseFloat(textFieldElement.getText());
                    if (list.remove(f)){
                        popText = new Text("Элемент " + textFieldElement.getText() + "удален\n\n");
                    }
                    else {
                        popText = new Text("Элемент " + textFieldElement.getText() + "не найден\n\n");
                    }
                }
                catch (NumberFormatException e){
                    Utility.shakeDataAnimation(textFieldElement);
                }

                break;
        }

        println(popText);
    }

    private void removeByIndex(){
        try {
            String s = list.removeByIndex(Integer.parseInt(textFieldIndex.getText())).toString();
            Text popText = new Text("Удален элемент: " + s + "\n\n");

            println(popText);
        }
        catch (IndexOutOfBoundsException e){
            Utility.shakeIndexAnimation(textFieldIndex);
        }
        catch (NumberFormatException e){
            Utility.shakeDataAnimation(textFieldElement);
        }

    }
    //endregion




    //region<Геты>
    private void getFirst(){
        Text topText = new Text("Первый элемент: " + list.getFirst() + "\n\n");

        println(topText);
    }

    private void getLast(){
        Text topText = new Text("Последний элемент: " + list.getLast() + "\n\n");

        println(topText);
    }

    private void get(){
        try {
            Text topText = new Text(textFieldIndex.getText() + "-й по индексу элемент: " + list.get(Integer.parseInt(textFieldIndex.getText())) + "\n\n");
            println(topText);
        }
        catch (IndexOutOfBoundsException e){
            Utility.shakeIndexAnimation(textFieldIndex);
        }
        catch (NumberFormatException e){
            Utility.shakeDataAnimation(textFieldElement);
        }
    }
    //endregion


    private void printList(){
        if (list.size() <= 10 && list.size() > 0){
            textError.setText("");

            Text stackPrint = new Text(list + "\n\n");

            println(stackPrint);
        }
        else {
            textError.setText("Лист пуст, или его длина больше 10");
        }
    }

    private void println(Text text){
        text.setFont(Font.font("Avenir Next Demi Bold", 25));

        textData.getChildren().add(text);

        /*if (text.getBoundsInParent().getHeight() >= textData.getHeight()){
            //textData.setPrefHeight(textData.getHeight() + 50);
            //textData.setPrefWidth(738);
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
