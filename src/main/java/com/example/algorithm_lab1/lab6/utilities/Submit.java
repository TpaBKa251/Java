package com.example.algorithm_lab1.lab6.utilities;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Submit {
    private TranslateTransition tt;


    public Submit(Node node){
        tt = new TranslateTransition(Duration.millis(120), node);
        tt.setFromY(0f);
        tt.setByY(-15f);
        tt.setCycleCount(2);
        tt.setAutoReverse(true);
    }

    public void playAnim(){
        tt.playFromStart();
    }
}
