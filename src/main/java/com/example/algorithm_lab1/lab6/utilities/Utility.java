package com.example.algorithm_lab1.lab6.utilities;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.IOException;

public class Utility {
    public static void changeScene(Class aClass, String sceneFileStr, Window prevScene) throws Exception {
        FXMLLoader loader = new FXMLLoader(aClass.getResource(sceneFileStr));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setOpacity(0);

        stage.show();

        Animation animation = Utility.getAnimationAppear(stage.opacityProperty());
        animation.play();

        animation.setOnFinished(event -> {
            prevScene.hide();
        });
    }

    public static Animation getAnimationAppear(DoubleProperty property ) {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame( Duration.ZERO, new KeyValue( property, 0 ) ),
                new KeyFrame( Duration.millis( 200 ), new KeyValue( property, 1 ) )
        );
        return timeline;
    }

    public static Animation getAnimationFade(DoubleProperty property ) {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame( Duration.ZERO, new KeyValue( property, 1 ) ),
                new KeyFrame( Duration.millis( 200 ), new KeyValue( property, 0 ) )
        );
        return timeline;
    }

    public static void shakeDataAnimation(TextField textField){
        textField.setText("Неверный тип данных");
        Shake fieldElement = new Shake(textField);
        fieldElement.playAnim();
    }

    public static void shakeIndexAnimation(TextField textField){
        textField.setText("Индекс за пределами списка");
        Shake fieldElement = new Shake(textField);
        fieldElement.playAnim();
    }

    public static void submitAnimation(TextField textField){
        textField.setText("Добавлено");
        Submit fieldElement = new Submit(textField);
        fieldElement.playAnim();
    }

    public static void scrollAnimation(ScrollPane scrollPane){
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(10), event -> {
                    scrollPane.setVvalue(scrollPane.getVvalue() + 0.01);
                })
        );

        timeline.play();

        timeline.setOnFinished(event -> {
            if (scrollPane.getVvalue() < 1.0){
                scrollAnimation(scrollPane);
            }
        });
    }
}


