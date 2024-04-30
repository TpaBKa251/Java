package com.example.algorithm_lab1.lab7.controllers;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.util.Duration;

public class ChartsController {
	@FXML
	private LineChart<?, ?> cntNowComponents;

	@FXML
	private LineChart<?, ?> cntSubComponents;

	@FXML
	void initialize(){
		PauseTransition pause = new PauseTransition(Duration.millis(10));
		pause.setOnFinished(event -> {
			showGraphs();
			pause.play();
		});

		pause.play();
	}

	private void showGraphs(){

	}
}
