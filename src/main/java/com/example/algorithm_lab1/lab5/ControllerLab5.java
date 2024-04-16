package com.example.algorithm_lab1.lab5;

import com.example.algorithm_lab1.lab5.SerNewData;
import com.example.algorithm_lab1.lab5.Serializator3000;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLab5 implements Initializable {

    @FXML
    private Button button5RandomChar;

    @FXML
    private Button button5RandomDouble;

    @FXML
    private Button buttonCharRandom;

    @FXML
    private Button buttonClear;

    @FXML
    private Button buttonDoubleRandom;

    @FXML
    private Button enter;

    @FXML
    private AnchorPane scrollPlane;

    @FXML
    private TextFlow textBox;

    @FXML
    private TextField textField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EventHandler<ActionEvent> eventRandomDouble = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                setRandomDouble();
            }
        };
        buttonDoubleRandom.setOnAction(eventRandomDouble);

        EventHandler<ActionEvent> eventRandomChar = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                setRandomChar();
            }
        };
        buttonCharRandom.setOnAction(eventRandomChar);

        EventHandler<ActionEvent> eventClear = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                clear();
            }
        };
        buttonClear.setOnAction(eventClear);

        EventHandler<ActionEvent> event5RandomDouble = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                set5RandomDouble();
            }
        };
        button5RandomDouble.setOnAction(event5RandomDouble);

        EventHandler<ActionEvent> event5RandomChar = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                set5RandomChar();
            }
        };
        button5RandomChar.setOnAction(event5RandomChar);

        EventHandler<ActionEvent> eventEnter = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                enterText();
            }
        };
        enter.setOnAction(eventEnter);
    }

    private void setRandomDouble(){
        double d = SerNewData.randomDouble();
        writeRandomDouble(d);
    }
    private void set5RandomDouble(){
        Double[] doubles = SerNewData.random5Double();
        for (double d : doubles){
            writeRandomDouble(d);
        }
    }
    private void writeRandomDouble(double d){
        String s = Serializator3000.serialize(d);

        Text randomDouble = new Text(" " + d);
        Text answer = new Text("\n\nВ двоичном виде: " + s + Serializator3000.deserializeByResearch(s));

        randomDouble.setFont(Font.font("Verdana", FontWeight.BLACK, 15));
        answer.setFont(Font.font("Verdana", 15));

        Text empty = new Text();

        textBox.getChildren().addAll(randomDouble, answer, empty);
    }

    private void setRandomChar(){
        char c = SerNewData.randomChar();
        writeRandomChar(c);
    }
    private void set5RandomChar(){
        char[] chars = SerNewData.random5Char();
        for (char c : chars){
            writeRandomChar(c);
        }
    }
    private void writeRandomChar(char c){
        String s = Serializator3000.serialize(c);

        Text randomChar = new Text(" " + c);
        Text answer = new Text("\n\nВ двоичном виде: " + s + "\nСимвол: " + Serializator3000.deserialize(s)
                + "\nМинимальное число байтов в памяти: " + Serializator3000.countBytes(c) + "\n\n\n");

        randomChar.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        answer.setFont(Font.font("Verdana", 15));

        Text empty = new Text();

        textBox.getChildren().addAll(randomChar, answer, empty);
    }

    private void clear(){
        textBox.getChildren().clear();
    }
    private void enterText(){
        String s = textField.getText();

        if (s != null){
            if (s.matches("[\\d.]+[\\d]") || s.matches("[\\d]+")){
                double d = Double.parseDouble(s);
                writeRandomDouble(d);
            }
            else if (s.matches("[\\D]") && s.length() == 1){
                char c = s.charAt(0);
                writeRandomChar(c);
            }
            else{
                textField.replaceText(0, textField.getText().length(), "Читать не умеете?");
            }
        }
    }
}



