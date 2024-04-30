module com.example.algorithm_lab1 {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.desktop;


	exports com.example.algorithm_lab1.lab1;
    opens com.example.algorithm_lab1.lab1 to javafx.fxml;
    exports com.example.algorithm_lab1.lab4;
    opens com.example.algorithm_lab1.lab4 to javafx.fxml;
    exports com.example.algorithm_lab1.lab2;
    opens com.example.algorithm_lab1.lab2 to javafx.fxml;
    exports com.example.algorithm_lab1.lab3;
    opens com.example.algorithm_lab1.lab3 to javafx.fxml;
    exports com.example.algorithm_lab1.lab5;
    opens com.example.algorithm_lab1.lab5 to javafx.fxml;
    exports com.example.algorithm_lab1;
    opens com.example.algorithm_lab1 to javafx.fxml;
    exports com.example.algorithm_lab1.lab6.controllers;
    opens com.example.algorithm_lab1.lab6.controllers to javafx.fxml;
    exports com.example.algorithm_lab1.lab6.utilities;
    opens com.example.algorithm_lab1.lab6.utilities to javafx.fxml;
    exports com.example.algorithm_lab1.lab7.controllers;
    opens com.example.algorithm_lab1.lab7.controllers to javafx.fxml;
}