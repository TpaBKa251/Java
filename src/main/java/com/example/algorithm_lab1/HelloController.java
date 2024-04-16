package com.example.algorithm_lab1;

import com.example.algorithm_lab1.lab2.Search;
import com.example.algorithm_lab1.lab2.SearchNewIter;
import com.example.algorithm_lab1.lab3.Sort;
import com.example.algorithm_lab1.lab3.SortNewIter;
import com.example.algorithm_lab1.lab1.NewIter;
import com.example.algorithm_lab1.lab1.Tasks;
import com.example.algorithm_lab1.lab4.Graph;
import com.example.algorithm_lab1.lab4.GraphAlg;
import com.example.algorithm_lab1.lab4.GraphNewIter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.text.*;

import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Button buttonCheck;

    @FXML
    private TextFlow field;

    @FXML
    private Button graphs;

    @FXML
    private LineChart<?, ?> opTask1RB;

    @FXML
    private LineChart<?, ?> opTask1W;

    @FXML
    private LineChart<?, ?> opTask2RB;

    @FXML
    private LineChart<?, ?> opTask2W;

    @FXML
    private LineChart<?, ?> opTask3RB;

    @FXML
    private LineChart<?, ?> opTask3W;

    @FXML
    private LineChart<?, ?> opTask4RB;

    @FXML
    private LineChart<?, ?> opTask5RB;

    @FXML
    private LineChart<?, ?> timeTask1RB;

    @FXML
    private LineChart<?, ?> timeTask1W;

    @FXML
    private LineChart<?, ?> timeTask2RB;

    @FXML
    private LineChart<?, ?> timeTask2W;

    @FXML
    private LineChart<?, ?> timeTask3RB;

    @FXML
    private LineChart<?, ?> timeTask3W;

    @FXML
    private LineChart<?, ?> timeTask4RB;

    @FXML
    private LineChart<?, ?> timeTask5RB;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //checkLab4();//вызов проверки
        //соответствующая кнопка
        EventHandler<ActionEvent> eventCheck = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                checkLab4();
            }
        };
        buttonCheck.setOnAction(eventCheck);

        //graphsLab4();//рисовка графиков
        //соответствующая кнопка
        EventHandler<ActionEvent> eventGraph = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                graphsLab4();
            }
        };
        graphs.setOnAction(eventGraph);
    }

    //ГРАФИКИ И ПРОВЕРКИ АЛГОРИТМОВ

    // 1 Лаба
    public void graphsLab1(){
        //Заголовки для графиков
        opTask1W.titleProperty().set("1 Алгоритм худ.");
        opTask1RB.titleProperty().set("1 Алгоритм лучш. и ранд.");
        opTask2W.titleProperty().set("2 Алгоритм");
        opTask3W.titleProperty().set("3 Алгоритм");

        //Заголовки лишних графиков удаляем
        opTask2RB.titleProperty().set("");
        opTask3RB.titleProperty().set("");
        opTask4RB.titleProperty().set("");
        opTask5RB.titleProperty().set("");

        //Меняем единицу измерения времени
        timeTask1RB.getYAxis().setLabel("Time, nc");
        timeTask1W.getYAxis().setLabel("Time, mc");
        timeTask2W.getYAxis().setLabel("Time, mc");
        timeTask3W.getYAxis().setLabel("Time, mc");

//-------------------ПЕРВЫЙ АЛГОРИТМ--------------------------------------//

        //Контейнеры точек операций 1 алгоритма
        XYChart.Series graphOpTask1W = new XYChart.Series();
        XYChart.Series graphOpTask1R = new XYChart.Series();
        XYChart.Series graphOpTask1B = new XYChart.Series();

        //Названия графиков + сложность операций 1 алгоритма
        graphOpTask1R.setName("Рандом");
        graphOpTask1B.setName("Лучший");
        graphOpTask1W.setName("Худший O(2n)");

        //Контейнеры точек времени 1 алгоритма
        XYChart.Series graphTimeTask1W = new XYChart.Series();
        XYChart.Series graphTimeTask1R = new XYChart.Series();
        XYChart.Series graphTimeTask1B = new XYChart.Series();

        //Названия графиков времени 1 алгоритма
        graphTimeTask1R.setName("Рандом");
        graphTimeTask1B.setName("Лучший");
        graphTimeTask1W.setName("Худший");

        //1 алгоритм
        for (int n = 1000000; n < 10000000; n += 100000) {
            //Рандом
            int[] arr1 = NewIter.randomForTask1(n);//создание нового массива
            Tasks.task1(arr1);//выполнение алгоритма
            String k = Integer.toString(n);
            graphOpTask1R.getData().add(new XYChart.Data(k, Tasks.numCalc));//добавление данных в контейнеры
            graphTimeTask1R.getData().add(new XYChart.Data(k, Tasks.time));

            //Лучший
            arr1 = NewIter.bestForTask1(n);
            Tasks.task1(arr1);
            k = Integer.toString(n);
            graphOpTask1B.getData().add(new XYChart.Data(k, Tasks.numCalc));
            graphTimeTask1B.getData().add(new XYChart.Data(k, Tasks.time));

            //Худший
            arr1 = NewIter.worstForTask1(n);
            Tasks.task1(arr1);
            k = Integer.toString(n);
            graphOpTask1W.getData().add(new XYChart.Data(k, Tasks.numCalc));
            graphTimeTask1W.getData().add(new XYChart.Data(k, Tasks.time/1_000_000));
        }

        //вывод 1 алгоритма. Худший результат выводится отдельно, так как в операциях худший доходит до десятков млн
        // а лучший всегда 2, рандомный до 13-17 (НЕ млн). На одном графике они сливаются
        opTask1W.getData().add(graphOpTask1W);
        opTask1RB.getData().addAll(graphOpTask1R, graphOpTask1B);
        timeTask1W.getData().add(graphTimeTask1W);
        timeTask1RB.getData().addAll(graphTimeTask1R, graphTimeTask1B);

//-----------------------------ВТОРОЙ АЛГОРИТМ------------------------------------------------//

        //Контейнеры точек операций 2 алгоритма
        XYChart.Series graphOpTask2W = new XYChart.Series();
        XYChart.Series graphOpTask2R = new XYChart.Series();
        XYChart.Series graphOpTask2B = new XYChart.Series();

        //Названия графиков операций 2 алгоритма
        graphOpTask2R.setName("Рандом");
        graphOpTask2B.setName("Лучший");
        graphOpTask2W.setName("Худший O(2n)");

        //Контейнеры точек врмени 2 алгоритма
        XYChart.Series graphTimeTask2W = new XYChart.Series();
        XYChart.Series graphTimeTask2R = new XYChart.Series();
        XYChart.Series graphTimeTask2B = new XYChart.Series();

        //Названия графиков времени 2 алгоритма
        graphTimeTask2R.setName("Рандом");
        graphTimeTask2B.setName("Лучший");
        graphTimeTask2W.setName("Худший");

        //2 алгоритм
        for (int m = 1000; m < 5000; m += 500) {
            int n = m;

            //Рандом
            int[][] arr2 = NewIter.randomForTask2(m, n);//новый массив
            Tasks.task2(arr2);//алгоритм
            String k = Integer.toString(m*n);
            graphOpTask2R.getData().add(new XYChart.Data(k, Tasks.numCalc));//добавлние данных
            graphTimeTask2R.getData().add(new XYChart.Data(k, Tasks.time));

            //Лучший
            arr2 = NewIter.bestForTask2(m, n);
            Tasks.task2(arr2);
            k = Integer.toString(m*n);
            graphOpTask2B.getData().add(new XYChart.Data(k, Tasks.numCalc));
            graphTimeTask2B.getData().add(new XYChart.Data(k, Tasks.time));

            //Худший
            arr2 = NewIter.worstForTask2(m, n);
            Tasks.task2(arr2);
            k = Integer.toString(m*n);
            graphOpTask2W.getData().add(new XYChart.Data(k, Tasks.numCalc));
            graphTimeTask2W.getData().add(new XYChart.Data(k, Tasks.time));
        }

        //вывод 2 алгоритма
        opTask2W.getData().addAll(graphOpTask2W, graphOpTask2R, graphOpTask2B);
        timeTask2W.getData().addAll(graphTimeTask2W, graphTimeTask2R, graphTimeTask2B);

//-----------------------------ТРЕТИЙ АЛГОРИТМ------------------------------------------------//
//Здесь нет лучшего и худшего случая, пирамидка - это пирамидка, а откуда и куда - неважно
        XYChart.Series graphOpTask3 = new XYChart.Series();

        graphOpTask3.setName("Рандом O(2^n)");

        XYChart.Series graphTimeTask3 = new XYChart.Series();

        graphTimeTask3.setName("Рандом");
        
        //3 алгоритм. До этого кол-во операций и подсчет врмени велся в самих алгоритмах (в классе Tasks)
        //Из-за особенности рекурсивной функции вызывать саму себя с ней такой мув не пройдет
        //Кол-во операций до сих пор считается в методе алгоритма, но обнуление происходит здесь, как и подсчет времнени
        for (int n = 1; n < 16; n += 1) {
            Tasks.numCalc = 0;
            Instant start = Instant.now();
            Tasks.task3(n, 'A', 'C', 'B');//неважно откуда и куда мы переносим пирамиду
            Instant finish = Instant.now();                      //док-во в тесте работоспособности
            long time = Duration.between(start, finish).toMillis();
            String k = Integer.toString(n);
            graphOpTask3.getData().add(new XYChart.Data(k, Tasks.numCalc));
            graphTimeTask3.getData().add(new XYChart.Data(k, time));
        }

        //Вывод 3 алгоритма
        opTask3W.getData().add(graphOpTask3);
        timeTask3W.getData().add(graphTimeTask3);
    }
    public void checkLab1(){
        //Проверка работоспосбоности алгоритмов 1 лабы
        field.getChildren().clear();

        Text taskTitle = new Text("Проверка алгоритма 1\n\n");
        taskTitle.setFont(Font.font("Verdana", FontWeight.BLACK, 25));
        String s = "Массив: ";
        int[] arr1 = NewIter.randomForTask1(10);
        for (int i = 0; i < 10; i++) {
            s += arr1[i] + ", ";
        }
        Text text1 = new Text(s);
        Text text2 = new Text("\nОтвет: " + Tasks.task1(arr1) + "\n\n\n");
        text1.setFont(Font.font("Verdana", 15));
        text2.setFont(Font.font("Verdana", FontPosture.ITALIC, 15));

        Text taskTitle2 = new Text("Проверка алгоритма 2\n\n");
        taskTitle2.setFont(Font.font("Verdana", FontWeight.BLACK, 25));

        s = "Матрица: \n\n";
        int[][] arr2 = NewIter.randomForTask2(4, 6);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                s += arr2[i][j] + "  ";
            }
            s += "\n\n";
        }
        s += "\n";
        Text text3 = new Text(s);
        text3.setFont(Font.font("Verdana", 15));

        int[][] arr = Tasks.task2(arr2);
        s = "Ответ: \n\n";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                s += arr[i][j] + "  ";
            }
            s += "\n\n";
        }
        Text text4 = new Text(s);
        text4.setFont(Font.font("Verdana", FontPosture.ITALIC, 15));

        Text taskTitle3 = new Text("\nПроверка алгоритма 3\n\n");
        taskTitle3.setFont(Font.font("Verdana", FontWeight.BLACK, 25));

        Random random = new Random();
        char start = 'A';
        char end = 'C';
        char transit = 'B';
        int n = random.nextInt(2, 5);
        int k = random.nextInt(1, 4);
        switch (k){
            case 1:
                start = 'A';
                end = 'B';
                transit = 'C';
                break;
            case 2:
                start = 'B';
                end = 'A';
                transit = 'C';
                break;
            case 3:
                start = 'C';
                end = 'B';
                transit = 'A';
        }

        s = "Условия: пирамида из " + n + " дисков. Переместить из позици " + start + ", в " + end;

        Text text5 = new Text(s);
        Tasks.task3(n, start, end, transit);
        Text text6 = new Text("\nОтвет: " + Tasks.s + "\n\n\n");
        text5.setFont(Font.font("Verdana", 15));
        text6.setFont(Font.font("Verdana", FontPosture.ITALIC, 15));

        Tasks.s = "";

        Text taskTitle22 = new Text("");
        field.getChildren().addAll(taskTitle, text1, text2, taskTitle2, text3,
                text4, taskTitle3, text5, text6, taskTitle22);
    }

    // 2 Лаба
    public void graphsLab2(){
        //Заголовки для графиков
        opTask1W.titleProperty().set("Линейный и с барьером худ.");
        opTask1RB.titleProperty().set("Линейный и с барьером ранд.");
        opTask4RB.titleProperty().set("Линейный и с барьером лучш.");
        opTask2W.titleProperty().set("Линейный в сорт. худ. и ранд.");
        opTask2RB.titleProperty().set("Линейный в сорт. лучш.");
        opTask3W.titleProperty().set("Бинарный");
        opTask3RB.titleProperty().set("Прыжками худ. и ранд.");
        opTask5RB.titleProperty().set("Прыжками худ. и лучш.");

        //Меняем единицу измерения времени
        timeTask1RB.getYAxis().setLabel("Time, nc");
        timeTask4RB.getYAxis().setLabel("Time, nc");
        timeTask5RB.getYAxis().setLabel("Time, nc");
        timeTask3RB.getYAxis().setLabel("Time, nc");
        timeTask3W.getYAxis().setLabel("Time, nc");

//------------------------ЛИНЕЙНЫЙ--------------------------------//
        //Здесь также выводится рандомный случай для алгоритма с барьером (быстрый линейный)
        //Это нужно, чтобы проверить оба алгоритма в рандомном случае на одних массивах

        //Контейнеры точек операций линейного алгоритма
        XYChart.Series graphOpTask1W = new XYChart.Series();
        XYChart.Series graphOpTask1R = new XYChart.Series();
        XYChart.Series graphOpTask1B = new XYChart.Series();

        //Названия графиков + сложность операций линейного алгоритма
        graphOpTask1R.setName("Рандом линейный");
        graphOpTask1B.setName("Лучший линейный");
        graphOpTask1W.setName("Худший линейный");

        //Контейнеры точек времени линейного алгоритма
        XYChart.Series graphTimeTask1W = new XYChart.Series();
        XYChart.Series graphTimeTask1R = new XYChart.Series();
        XYChart.Series graphTimeTask1B = new XYChart.Series();

        //Названия графиков времени линйеного алгоритма
        graphTimeTask1R.setName("Рандом линейный");
        graphTimeTask1B.setName("Лучший линейный");
        graphTimeTask1W.setName("Худший линейный");

        //Контейнер точек операций рандомного случая алгоритма с барьером
        XYChart.Series graphOpTask2R = new XYChart.Series();

        //Названия графика + сложность операций рандомного случая алгоритма с барьером
        graphOpTask2R.setName("Рандом с барьером");

        //Контейнеры точек времени рандомного случая алгоритма с барьером
        XYChart.Series graphTimeTask2R = new XYChart.Series();

        //Название графика времени рандомного случая алгоритма с барьером
        graphTimeTask2R.setName("Рандом с барьером");

        Random random = new Random();
        //линейный алгоритм + рандомный случай алгоритма с барьером
        for (int n = 1000000; n < 10_000_000; n += 1_000_000) {
            //Рандом
            int[] arr1 = SearchNewIter.randomLineSearchDisord(n);//создание нового массива
            int num = random.nextInt(-100000, 100000);
            Search.lineSearchDisord(arr1, num);//выполнение алгоритма (линейного)
            String k = Integer.toString(n);
            graphOpTask1R.getData().add(new XYChart.Data(k, Search.numCalc));//добавление данных в контейнеры
            graphTimeTask1R.getData().add(new XYChart.Data(k, Search.time));

            Search.fastLineSearchDisord(arr1, num);//выполнение алгоритма (с барьером)
            k = Integer.toString(n);
            graphOpTask2R.getData().add(new XYChart.Data(k, Search.numCalc));//добавление данных в контейнеры
            graphTimeTask2R.getData().add(new XYChart.Data(k, Search.time));

            //Лучший
            arr1 = SearchNewIter.bestLineSearchDisord(n, num);
            Search.lineSearchDisord(arr1, num);
            k = Integer.toString(n);
            graphOpTask1B.getData().add(new XYChart.Data(k, Search.numCalc));
            graphTimeTask1B.getData().add(new XYChart.Data(k, Search.time));

            //Худший
            arr1 = SearchNewIter.worstLineSearchDisord(n, num);
            Search.lineSearchDisord(arr1, num);
            k = Integer.toString(n);
            graphOpTask1W.getData().add(new XYChart.Data(k, Search.numCalc));
            graphTimeTask1W.getData().add(new XYChart.Data(k, Search.time / 1_000_000));
        }

        //вывод линейного алгоритма + рандомный с барьером. Всё выводится отдельно для наглядности
        opTask1W.getData().add(graphOpTask1W);
        opTask1RB.getData().add(graphOpTask1R);
        timeTask1W.getData().add(graphTimeTask1W);
        timeTask1RB.getData().add(graphTimeTask1R);
        opTask4RB.getData().add(graphOpTask1B);
        timeTask4RB.getData().add(graphTimeTask1B);

//--------------------------С БАРЬЕРОМ (БЫСТРЫЙ ЛИНЕЙНЫЙ)--------------------------------------------------//

        //Здесь добиваем оставшиеся случаи алгоритма с барьером

        //Контейнеры точек операций алгоритма
        XYChart.Series graphOpTask2W = new XYChart.Series();
        XYChart.Series graphOpTask2B = new XYChart.Series();

        //Названия графиков + сложность операций алгоритма
        graphOpTask2B.setName("Лучший с барьером");
        graphOpTask2W.setName("Худший с барьером");

        //Контейнеры точек времени алгоритма
        XYChart.Series graphTimeTask2W = new XYChart.Series();
        XYChart.Series graphTimeTask2B = new XYChart.Series();

        //Названия графиков времени алгоритма
        graphTimeTask2B.setName("Лучший с барьером");
        graphTimeTask2W.setName("Худший с барьером");

        //Прогон
        for (int n = 1000000; n < 10_000_000; n += 1_000_000) {
            //Рандом бфл бы
            int[] arr1 = SearchNewIter.randomLineSearchDisord(n);//создание нового массива
            int num = random.nextInt(-100000, 100000);
            String k = Integer.toString(n);
            /*Search.fastLineSearchDisord(arr1, num);//выполнение алгоритма
            String k = Integer.toString(n);
            graphOpTask2R.getData().add(new XYChart.Data(k, Search.numCalc));//добавление данных в контейнеры
            graphTimeTask2R.getData().add(new XYChart.Data(k, Search.time));*/

            //Лучший
            arr1 = SearchNewIter.bestLineSearchDisord(n, num);
            Search.fastLineSearchDisord(arr1, num);
            k = Integer.toString(n);
            graphOpTask2B.getData().add(new XYChart.Data(k, Search.numCalc));
            graphTimeTask2B.getData().add(new XYChart.Data(k, Search.time));

            //Худший
            arr1 = SearchNewIter.worstLineSearchDisord(n, num);
            Search.fastLineSearchDisord(arr1, num);
            k = Integer.toString(n);
            graphOpTask2W.getData().add(new XYChart.Data(k, Search.numCalc));
            graphTimeTask2W.getData().add(new XYChart.Data(k, Search.time / 1_000_000));
        }

        //вывод алгоритма
        opTask1W.getData().add(graphOpTask2W);
        opTask1RB.getData().add(graphOpTask2R);
        timeTask1W.getData().add(graphTimeTask2W);
        timeTask1RB.getData().add(graphTimeTask2R);
        opTask4RB.getData().add(graphOpTask2B);
        timeTask4RB.getData().add(graphTimeTask2B);

//------------------СОРТИРОВАННЫЙ С БАРЬЕРОМ (БЫСТРЫЙ ЛИНЕНЙНЫЙ В ОТСОРТИРОВАННОМ)------------------------------------------//

        //Контейнеры точек операций алгоритма
        XYChart.Series graphOpTask3W = new XYChart.Series();
        XYChart.Series graphOpTask3R = new XYChart.Series();
        XYChart.Series graphOpTask3B = new XYChart.Series();

        //Названия графиков + сложность операций алгоритма
        graphOpTask3R.setName("Рандом сорт");
        graphOpTask3B.setName("Лучший сорт");
        graphOpTask3W.setName("Худший сорт");

        //Контейнеры точек времени алгоритма
        XYChart.Series graphTimeTask3W = new XYChart.Series();
        XYChart.Series graphTimeTask3R = new XYChart.Series();
        XYChart.Series graphTimeTask3B = new XYChart.Series();

        //Названия графиков времени алгоритма
        graphTimeTask3R.setName("Рандом сорт");
        graphTimeTask3B.setName("Лучший сорт");
        graphTimeTask3W.setName("Худший сорт");

        //Прогон
        for (int n = 1_000_000; n < 10_000_000; n += 1_000_000) {
            //Рандом
            int[] arr1 = SearchNewIter.randomSearchOrd(n);//создание нового массива
            int num = random.nextInt(-100000, 100000);
            Search.fastLineSearchOrd(arr1, num);//выполнение алгоритма
            String k = Integer.toString(n);
            graphOpTask3R.getData().add(new XYChart.Data(k, Search.numCalc));//добавление данных в контейнеры
            graphTimeTask3R.getData().add(new XYChart.Data(k, Search.time));

            //Лучший
            arr1 = SearchNewIter.bestLineSearchDisord(n, num);
            Search.fastLineSearchOrd(arr1, num);
            k = Integer.toString(n);
            graphOpTask3B.getData().add(new XYChart.Data(k, Search.numCalc));
            graphTimeTask3B.getData().add(new XYChart.Data(k, Search.time));

            //Худший
            arr1 = SearchNewIter.worstLineBinSearchOrd(n, num);
            Search.fastLineSearchOrd(arr1, num);
            k = Integer.toString(n);
            graphOpTask3W.getData().add(new XYChart.Data(k, Search.numCalc));
            graphTimeTask3W.getData().add(new XYChart.Data(k, Search.time));
        }

        //вывод алгоритма
        opTask2W.getData().addAll(graphOpTask3W, graphOpTask3R);
        timeTask2W.getData().addAll(graphTimeTask3W, graphTimeTask3R);
        opTask2RB.getData().add(graphOpTask3B);
        timeTask2RB.getData().add(graphTimeTask3B);

//--------------------------БИНАРНЫЙ---------------------------------------------//

        //Контейнеры точек операций алгоритма
        XYChart.Series graphOpTask4W = new XYChart.Series();
        XYChart.Series graphOpTask4R = new XYChart.Series();
        XYChart.Series graphOpTask4B = new XYChart.Series();

        //Названия графиков + сложность операций алгоритма
        graphOpTask4R.setName("Рандом бинарный");
        graphOpTask4B.setName("Лучший бинарный");
        graphOpTask4W.setName("Худший бинарный");

        //Контейнеры точек времени алгоритма
        XYChart.Series graphTimeTask4W = new XYChart.Series();
        XYChart.Series graphTimeTask4R = new XYChart.Series();
        XYChart.Series graphTimeTask4B = new XYChart.Series();

        //Названия графиков времени алгоритма
        graphTimeTask4R.setName("Рандом бинарный");
        graphTimeTask4B.setName("Лучший бинарный");
        graphTimeTask4W.setName("Худший бинарный");

        //Прогон
        for (int n = 1_000_000; n < 10_000_000; n += 1_000_000) {
            //Рандом
            int[] arr1 = SearchNewIter.randomSearchOrd(n);//создание нового массива
            int num = random.nextInt(-10000, 10000);
            Search.binSearch(arr1, num);//выполнение алгоритма
            String k = Integer.toString(n);
            graphOpTask4R.getData().add(new XYChart.Data(k, Search.numCalc));//добавление данных в контейнеры
            graphTimeTask4R.getData().add(new XYChart.Data(k, Search.time));

            //Лучший
            arr1 = SearchNewIter.bestBinSearchOrd(n, num);
            Search.binSearch(arr1, num);
            k = Integer.toString(n);
            graphOpTask4B.getData().add(new XYChart.Data(k, Search.numCalc));
            graphTimeTask4B.getData().add(new XYChart.Data(k, Search.time));

            //Худший
            arr1 = SearchNewIter.worstLineBinSearchOrd(n, num);
            Search.binSearch(arr1, num);
            k = Integer.toString(n);
            graphOpTask4W.getData().add(new XYChart.Data(k, Search.numCalc));
            graphTimeTask4W.getData().add(new XYChart.Data(k, Search.time));
        }

        //вывод алгоритма
        opTask3W.getData().addAll(graphOpTask4W, graphOpTask4R, graphOpTask4B);
        timeTask3W.getData().addAll(graphTimeTask4W, graphTimeTask4R, graphTimeTask4B);

//----------------------------ПРЫЖКАМИ (БЛОЧНЫЙ)----------------------------------------//

        //Контейнеры точек операций алгоритма
        XYChart.Series graphOpTask5W = new XYChart.Series();
        XYChart.Series graphOpTask5R = new XYChart.Series();
        XYChart.Series graphOpTask5B = new XYChart.Series();

        //Названия графиков + сложность операций алгоритма
        graphOpTask5R.setName("Рандом прыжками");
        graphOpTask5B.setName("Лучший прыжками");
        graphOpTask5W.setName("Худший прыжками");

        //Контейнеры точек времени алгоритма
        XYChart.Series graphTimeTask5W = new XYChart.Series();
        XYChart.Series graphTimeTask5R = new XYChart.Series();
        XYChart.Series graphTimeTask5B = new XYChart.Series();

        //Названия графиков времени алгоритма
        graphTimeTask5R.setName("Рандом прыжками");
        graphTimeTask5B.setName("Лучший прыжками");
        graphTimeTask5W.setName("Худший прыжками");

        //Прогон
        for (int n = 1_000_000; n < 10_000_000; n += 1_000_000) {
            //Рандом
            int[] arr1 = SearchNewIter.randomSearchOrd(n);//создание нового массива
            int num = random.nextInt(-10000, 10000);
            Search.jumpSearch(arr1, num);//выполнение алгоритма
            String k = Integer.toString(n);
            graphOpTask5R.getData().add(new XYChart.Data(k, Search.numCalc));//добавление данных в контейнеры
            graphTimeTask5R.getData().add(new XYChart.Data(k, Search.time));

            //Лучший
            arr1 = SearchNewIter.bestJumpSearchOrd(n, num);
            Search.jumpSearch(arr1, num);
            k = Integer.toString(n);
            graphOpTask5B.getData().add(new XYChart.Data(k, Search.numCalc));
            graphTimeTask5B.getData().add(new XYChart.Data(k, Search.time));

            //Худший
            arr1 = SearchNewIter.worstJumpSearchOrd(n, num);
            Search.jumpSearch(arr1, num);
            k = Integer.toString(n);
            graphOpTask5W.getData().add(new XYChart.Data(k, Search.numCalc));
            graphTimeTask5W.getData().add(new XYChart.Data(k, Search.time));
        }

        //вывод алгоритма
        opTask3RB.getData().addAll(graphOpTask5W, graphOpTask5R);
        timeTask3RB.getData().addAll(graphTimeTask5W, graphTimeTask5R);
        opTask5RB.getData().add(graphOpTask5B);
        timeTask5RB.getData().add(graphTimeTask5B);
    }
    public void checkLab2(){
        //Проверка работоспосбоности алгоритмов 2 лабы

        field.getChildren().clear();
        Random random = new Random();

        int num = random.nextInt(-10000, 10000);

//-------------------ЛИНЕЙНЫЙ---------------------------------------------------------//

        Text taskTitle = new Text("Проверка линейного поиска\n\n");
        taskTitle.setFont(Font.font("Verdana", FontWeight.BLACK, 25));
        String s = "Массив: ";
        int[] arr1 = SearchNewIter.randomLineSearchDisord(15, num);
        for (int i = 0; i < 15; i++) {
            s += arr1[i] + ", ";
        }
        s += " найти элемент " + num;
        Text text1 = new Text(s);
        Text text2 = new Text("\nОтвет: " + Search.lineSearchDisord(arr1, num) + "\n\n\n");
        text1.setFont(Font.font("Verdana", 15));
        text2.setFont(Font.font("Verdana", FontPosture.ITALIC, 15));

//-------------------ЛИНЕЙНЫЙ С БАРЬЕРОМ---------------------------------------------------------//

        Text taskTitle2 = new Text("Проверка линейного алгоритма с барьером\n\n");
        taskTitle2.setFont(Font.font("Verdana", FontWeight.BLACK, 25));

        num = random.nextInt(-10000, 10000);
        arr1 = SearchNewIter.randomLineSearchDisord(15, num);
        s = "Массив: ";
        for (int i = 0; i < 15; i++) {
            s += arr1[i] + ", ";
        }

        s += " найти элемент " + num;
        Text text3 = new Text(s);
        Text text4 = new Text("\nОтвет: " + Search.fastLineSearchDisord(arr1, num) + "\n\n\n");
        text3.setFont(Font.font("Verdana", 15));
        text4.setFont(Font.font("Verdana", FontPosture.ITALIC, 15));

//-------------------СОРТИРОВАННЫЙ ЛИНЕЙНЫЙ---------------------------------------------------------//

        Text taskTitle3 = new Text("Проверка линейного алгоритма в отсортированном массиве\n\n");
        taskTitle3.setFont(Font.font("Verdana", FontWeight.BLACK, 25));

        num = random.nextInt(-10000, 10000);
        arr1 = SearchNewIter.randomSearchOrd(15, num);
        s = "Массив: ";
        for (int i = 0; i < 15; i++) {
            s += arr1[i] + ", ";
        }

        s += " найти элемент " + num;
        Text text5 = new Text(s);
        Text text6 = new Text("\nОтвет: " + Search.fastLineSearchOrd(arr1, num) + "\n\n\n");
        text5.setFont(Font.font("Verdana", 15));
        text6.setFont(Font.font("Verdana", FontPosture.ITALIC, 15));

//-------------------БИНАРНЫЙ---------------------------------------------------------//

        Text taskTitle4 = new Text("Проверка бинарного алгоритма в отсортированном массиве\n\n");
        taskTitle4.setFont(Font.font("Verdana", FontWeight.BLACK, 25));

        num = random.nextInt(-10000, 10000);
        arr1 = SearchNewIter.randomSearchOrd(15, num);
        s = "Массив: ";
        for (int i = 0; i < 15; i++) {
            s += arr1[i] + ", ";
        }

        s += " найти элемент " + num;
        Text text7 = new Text(s);
        Text text8 = new Text("\nОтвет: " + Search.binSearch(arr1, num) + "\n\n\n");
        text7.setFont(Font.font("Verdana", 15));
        text8.setFont(Font.font("Verdana", FontPosture.ITALIC, 15));

//-------------------ПРЫЖКАМИ---------------------------------------------------------//

        Text taskTitle5 = new Text("Проверка алгоритма прыжками в отсортированном массиве\n\n");
        taskTitle5.setFont(Font.font("Verdana", FontWeight.BLACK, 25));

        num = random.nextInt(-10000, 10000);
        arr1 = SearchNewIter.randomSearchOrd(15, num);
        s = "Массив: ";
        for (int i = 0; i < 15; i++) {
            s += arr1[i] + ", ";
        }

        s += " найти элемент " + num;
        Text text9 = new Text(s);
        Text text10 = new Text("\nОтвет: " + Search.jumpSearch(arr1, num) + "\n\n\n");
        text9.setFont(Font.font("Verdana", 15));
        text10.setFont(Font.font("Verdana", FontPosture.ITALIC, 15));

        Tasks.s = "";

        Text taskTitle22 = new Text("");
        field.getChildren().addAll(taskTitle, text1, text2, taskTitle2, text3,
                text4, taskTitle3, text5, text6,taskTitle4, text7, text8, taskTitle5,
                text9, text10, taskTitle22);
    }

    // 3 Лаба
    public void graphsLab3(){
        //Заголовки для графиков
        opTask1W.titleProperty().set("Шелла худшиий");
        opTask1RB.titleProperty().set("Шелла и быстрый рандомные");
        opTask2W.titleProperty().set("Быстрая худший и лучший");
        opTask3W.titleProperty().set("Блочный весь");

        //Заголовки лишних графиков удаляем
        opTask2RB.titleProperty().set("");
        opTask3RB.titleProperty().set("");
        opTask4RB.titleProperty().set("");
        opTask5RB.titleProperty().set("");

        //Меняем единицу измерения времени
        timeTask1RB.getYAxis().setLabel("Time, nc");
        timeTask1W.getYAxis().setLabel("Time, mc");
        timeTask2W.getYAxis().setLabel("Time, mc");
        timeTask3W.getYAxis().setLabel("Time, mc");
        timeTask2RB.getYAxis().setLabel("Time, nc");

//-------------------МЕТОД ШЕЛЛА--------------------------------------//

        //Контейнеры точек операций 1 алгоритма
        XYChart.Series graphOpTask1W = new XYChart.Series();
        XYChart.Series graphOpTask1R = new XYChart.Series();
        XYChart.Series graphOpTask1B = new XYChart.Series();

        //Названия графиков + сложность операций 1 алгоритма
        graphOpTask1R.setName("Рандом Шелла");
        graphOpTask1B.setName("Лучший Шелла");
        graphOpTask1W.setName("Худший Шелла");

        //Контейнеры точек времени 1 алгоритма
        XYChart.Series graphTimeTask1W = new XYChart.Series();
        XYChart.Series graphTimeTask1R = new XYChart.Series();
        XYChart.Series graphTimeTask1B = new XYChart.Series();

        //Названия графиков времени 1 алгоритма
        graphTimeTask1R.setName("Рандом Шелла");
        graphTimeTask1B.setName("Лучший Шелла");
        graphTimeTask1W.setName("Худший Шелла");

        //Контейнеры точек операций 2 алгоритма
        XYChart.Series graphOpTask2W = new XYChart.Series();
        XYChart.Series graphOpTask2R = new XYChart.Series();
        XYChart.Series graphOpTask2B = new XYChart.Series();

        //Названия графиков операций 2 алгоритма
        graphOpTask2R.setName("Рандом быстрая");
        graphOpTask2B.setName("Лучший быстрая");
        graphOpTask2W.setName("Худший быстрая");

        //Контейнеры точек врмени 2 алгоритма
        XYChart.Series graphTimeTask2W = new XYChart.Series();
        XYChart.Series graphTimeTask2R = new XYChart.Series();
        XYChart.Series graphTimeTask2B = new XYChart.Series();

        //Названия графиков времени 2 алгоритма
        graphTimeTask2R.setName("Рандом быстрая");
        graphTimeTask2B.setName("Лучший быстрая");
        graphTimeTask2W.setName("Худший быстрая");

        XYChart.Series graphOpTask3W = new XYChart.Series();
        XYChart.Series graphOpTask3R = new XYChart.Series();
        XYChart.Series graphOpTask3B = new XYChart.Series();

        //Названия графиков + сложность операций алгоритма
        graphOpTask3R.setName("Рандом блочная");
        graphOpTask3B.setName("Лучший блочная");
        graphOpTask3W.setName("Худший блочная");

        //Контейнеры точек времени алгоритма
        XYChart.Series graphTimeTask3W = new XYChart.Series();
        XYChart.Series graphTimeTask3R = new XYChart.Series();
        XYChart.Series graphTimeTask3B = new XYChart.Series();

        //Названия графиков времени алгоритма
        graphTimeTask3R.setName("Рандом блочная");
        graphTimeTask3B.setName("Лучший блочная");
        graphTimeTask3W.setName("Худший блочная");

        //1 алгоритм
        for (int n = 10000; n < 110000; n += 10000) {
            //Рандом
            int[] arr1 = SortNewIter.randomSort(n);//создание нового массива
            int[] arr2 = arr1;
            int[] arr3 = arr1;
            Sort.numCalc = 0;
            Sort.shellSort(arr1);//выполнение алгоритма
            String k = Integer.toString(n);
            graphOpTask1R.getData().add(new XYChart.Data(k, Sort.numCalc));//добавление данных в контейнеры
            graphTimeTask1R.getData().add(new XYChart.Data(k, Sort.time));
            Sort.numCalc = 0;
            Instant start = Instant.now();
            Sort.quickSort(arr2, 0, n-1);//выполнение алгоритма
            Instant finish = Instant.now();
            long time = Duration.between(start, finish).toNanos();
            k = Integer.toString(n);
            graphOpTask2R.getData().add(new XYChart.Data(k, Sort.numCalc));//добавление данных в контейнеры
            graphTimeTask2R.getData().add(new XYChart.Data(k, time));

            //Лучший
            arr1 = SortNewIter.bestSort(n);
            Sort.numCalc = 0;
            Sort.shellSort(arr1);
            k = Integer.toString(n);
            graphOpTask1B.getData().add(new XYChart.Data(k, Sort.numCalc));
            graphTimeTask1B.getData().add(new XYChart.Data(k, Sort.time));

            //Худший
            Sort.numCalc = 0;
            arr1 = SortNewIter.worstShellSort(n);
            Sort.shellSortWorst(arr1);
            k = Integer.toString(n);
            graphOpTask1W.getData().add(new XYChart.Data(k, Sort.numCalc));
            graphTimeTask1W.getData().add(new XYChart.Data(k, Sort.time));
        }

        //вывод 1 алгоритма. Худший результат выводится отдельно, так как в операциях худший доходит до десятков млн
        // а лучший всегда 2, рандомный до 13-17 (НЕ млн). На одном графике они сливаются
        opTask1W.getData().addAll(graphOpTask1W);
        opTask1RB.getData().addAll(graphOpTask1R, graphOpTask2R);
        timeTask1W.getData().addAll(graphTimeTask1W);
        timeTask1RB.getData().addAll(graphTimeTask1R, graphTimeTask2R);
        opTask4RB.getData().add(graphOpTask1B);
        timeTask4RB.getData().add(graphTimeTask1B);

//--------------------БЫСТРАЯ СОРТИРОВКА------------------------------------------------//



        //2 алгоритм
        for (int n = 10000; n < 110000; n += 10000) {
            //Рандом
            Sort.numCalc = 0;
            int[] arr1 = SortNewIter.randomSort(n);//создание нового массива
            Instant start = Instant.now();
            //Sort.quickSort(arr1, 0, n - 1);//выполнение алгоритма
            Instant finish = Instant.now();
            long time = Duration.between(start, finish).toMillis();
            String k = Integer.toString(n);
            //graphOpTask2R.getData().add(new XYChart.Data(k, Sort.numCalc));//добавление данных в контейнеры
            //graphTimeTask2R.getData().add(new XYChart.Data(k, time));

            //Лучший
            Sort.numCalc = 0;
            arr1 = SortNewIter.bestSort(n);
            start = Instant.now();
            Sort.quickSort(arr1, 0, n - 1);//выполнение алгоритма
            finish = Instant.now();
            time = Duration.between(start, finish).toNanos();
            k = Integer.toString(n);
            graphOpTask2B.getData().add(new XYChart.Data(k, Sort.numCalc));
            graphTimeTask2B.getData().add(new XYChart.Data(k, time));

            //Худший
            Sort.numCalc = 0;
            arr1 = SortNewIter.worstShellSort(n);
            start = Instant.now();
            Sort.quickSortWorst(arr1, 0, n - 1);//выполнение алгоритма
            finish = Instant.now();
            time = Duration.between(start, finish).toMillis();
            k = Integer.toString(n);
            graphOpTask2W.getData().add(new XYChart.Data(k, Sort.numCalc));
            graphTimeTask2W.getData().add(new XYChart.Data(k, time));
        }

        //вывод 2 алгоритма
        opTask2W.getData().addAll(graphOpTask2W);
        timeTask2W.getData().addAll(graphTimeTask2W);
        opTask2RB.getData().add(graphOpTask2B);
        timeTask2RB.getData().add(graphTimeTask2B);

//----------------------БЛОЧНАЯ СОРТИРОВКА------------------------------------------------//


        //Прогон
        for (int n = 10000; n < 110000; n += 10000) {
            //Рандом
            Sort.numCalc = 0;
            int[] arr1 = SortNewIter.randomSort(n);//создание нового массива
            Instant start = Instant.now();
            Sort.bucketOut(arr1);//выполнение алгоритма
            Instant finish = Instant.now();
            long time = Duration.between(start, finish).toMillis();
            String k = Integer.toString(n);
            graphOpTask3R.getData().add(new XYChart.Data(k, Sort.numCalc));//добавление данных в контейнеры
            graphTimeTask3R.getData().add(new XYChart.Data(k, time));

            //Лучший
            Sort.numCalc = 0;
            arr1 = SortNewIter.bestBlockSort(n);
            start = Instant.now();
            Sort.bucketOut(arr1);
            finish = Instant.now();
            time = Duration.between(start, finish).toNanos();
            k = Integer.toString(n);
            graphOpTask3B.getData().add(new XYChart.Data(k, Sort.numCalc));
            graphTimeTask3B.getData().add(new XYChart.Data(k, time));

            //Худший
            Sort.numCalc = 0;
            arr1 = SortNewIter.worstShellSort(n);
            start = Instant.now();
            Sort.bucketOut(arr1);
            finish = Instant.now();
            time = Duration.between(start, finish).toMillis();
            k = Integer.toString(n);
            graphOpTask3W.getData().add(new XYChart.Data(k, Sort.numCalc));
            graphTimeTask3W.getData().add(new XYChart.Data(k, time));
        }

        //вывод алгоритма
        opTask3W.getData().addAll(graphOpTask3W);
        timeTask3W.getData().addAll(graphTimeTask3W);
        opTask3RB.getData().addAll(graphOpTask3R);
        timeTask3RB.getData().addAll( graphTimeTask3R);
        opTask5RB.getData().add(graphOpTask3B);
        timeTask5RB.getData().add(graphTimeTask3B);
    }
    public void checkLab3(){
        //Проверка работоспосбоности алгоритмов 3 лабы

        field.getChildren().clear();

//-------------------МЕТОД ШЕЛЛА---------------------------------------------------------//

        Text taskTitle = new Text("Проверка метода Шелла\n\n");
        taskTitle.setFont(Font.font("Verdana", FontWeight.BLACK, 25));
        String s = "Массив: ";
        int[] arr1 = SortNewIter.randomSort(15);
        for (int i = 0; i < 15; i++) {
            s += arr1[i] + ", ";
        }
        Text text1 = new Text(s);
        s = "\n\nОтвет: ";
        int[] ans = Sort.shellSort(arr1);
        for (int i : ans){
            s += i + ", ";
        }
        Text text2 = new Text(s);
        text1.setFont(Font.font("Verdana", 15));
        text2.setFont(Font.font("Verdana", FontPosture.ITALIC, 15));

//-------------------БЫТСРАЯ СОРТИРОВКА---------------------------------------------------------//

        Text taskTitle2 = new Text("\n\n\nПроверка быстрой сортировки\n\n");
        taskTitle2.setFont(Font.font("Verdana", FontWeight.BLACK, 25));
        s = "Массив: ";
        arr1 = SortNewIter.randomSort(15);
        for (int i = 0; i < 15; i++) {
            s += arr1[i] + ", ";
        }
        Text text3 = new Text(s);
        s = "\n\nОтвет: ";
        Sort.quickSort(arr1, 0, arr1.length-1);
        for (int i : arr1){
            s += i + ", ";
        }
        Text text4 = new Text(s);
        text3.setFont(Font.font("Verdana", 15));
        text4.setFont(Font.font("Verdana", FontPosture.ITALIC, 15));

//-------------------БЛОЧНАЯ СОРТИРОВКА---------------------------------------------------------//

        Text taskTitle3 = new Text("\n\n\nПроверка блочной сортировки\n\n");
        taskTitle3.setFont(Font.font("Verdana", FontWeight.BLACK, 25));
        s = "Массив: ";
        arr1 = SortNewIter.randomSort(15);
        for (int i = 0; i < 15; i++) {
            s += arr1[i] + ", ";
        }
        Text text5 = new Text(s);
        s = "\n\nОтвет: ";
        ans = Sort.bucketOut(arr1);
        for (int i : ans){
            s += i + ", ";
        }
        Text text6 = new Text(s);
        text5.setFont(Font.font("Verdana", 15));
        text6.setFont(Font.font("Verdana", FontPosture.ITALIC, 15));

        Text taskTitle22 = new Text("");
        field.getChildren().addAll(taskTitle, text1, text2, taskTitle2, text3, text4, taskTitle3, text5, text6, taskTitle22);
    }

    // 4 Лаба
    public void graphsLab4(){
        //Заголовки для графиков
        opTask1W.getData().clear();
        timeTask1W.getData().clear();
        opTask1W.titleProperty().set("Поиск точек сочленения");

        //Заголовки лишних графиков удаляем
        opTask2RB.titleProperty().set("");
        opTask3RB.titleProperty().set("");
        opTask4RB.titleProperty().set("");
        opTask5RB.titleProperty().set("");

        //Меняем единицу измерения времени
        timeTask1RB.getYAxis().setLabel("Time, nc");
        timeTask1W.getYAxis().setLabel("Time, mc");
        timeTask2W.getYAxis().setLabel("Time, mc");
        timeTask3W.getYAxis().setLabel("Time, mc");

//-------------------ПЕРВЫЙ АЛГОРИТМ--------------------------------------//

        //Контейнеры точек операций 1 алгоритма
        XYChart.Series graphOpTask1W = new XYChart.Series();
        XYChart.Series graphOpTask1R = new XYChart.Series();
        XYChart.Series graphOpTask1B = new XYChart.Series();

        //Названия графиков + сложность операций 1 алгоритма
        graphOpTask1R.setName("Рандом");
        graphOpTask1B.setName("Лучший");
        graphOpTask1W.setName("Худший");

        //Контейнеры точек времени 1 алгоритма
        XYChart.Series graphTimeTask1W = new XYChart.Series();
        XYChart.Series graphTimeTask1R = new XYChart.Series();
        XYChart.Series graphTimeTask1B = new XYChart.Series();

        //Названия графиков времени 1 алгоритма
        graphTimeTask1R.setName("Рандом");
        graphTimeTask1B.setName("Лучший");
        graphTimeTask1W.setName("Худший");

        //1 алгоритм
        for (int n = 10; n < 110; n += 10) {
            //Рандом
            Graph<Integer> graph = GraphNewIter.randomGraph(n);//создание нового массива
            Instant start = Instant.now();
            GraphAlg.DFS(graph, graph.getVertexCount());//выполнение алгоритма
            Instant finish = Instant.now();
            long time = Duration.between(start, finish).toMillis();
            String k = Integer.toString(n);
            graphOpTask1R.getData().add(new XYChart.Data(k, GraphAlg.numCalc));//добавление данных в контейнеры
            graphTimeTask1R.getData().add(new XYChart.Data(k, time));

            //Лучший
            graph = GraphNewIter.bestGraph(n);
            start = Instant.now();
            GraphAlg.DFS(graph, graph.getVertexCount());
            finish = Instant.now();
            time = Duration.between(start, finish).toMillis();
            k = Integer.toString(n);
            graphOpTask1B.getData().add(new XYChart.Data(k, GraphAlg.numCalc));
            graphTimeTask1B.getData().add(new XYChart.Data(k, time));

            //Худший
            graph = GraphNewIter.worstGraph(n);
            start = Instant.now();
            GraphAlg.DFS(graph, graph.getVertexCount());
            finish = Instant.now();
            time = Duration.between(start, finish).toMillis();
            k = Integer.toString(n);
            graphOpTask1W.getData().add(new XYChart.Data(k, GraphAlg.numCalc));
            graphTimeTask1W.getData().add(new XYChart.Data(k, time));
        }

        //вывод 1 алгоритма. Худший результат выводится отдельно, так как в операциях худший доходит до десятков млн
        // а лучший всегда 2, рандомный до 13-17 (НЕ млн). На одном графике они сливаются
        opTask1W.getData().addAll(graphOpTask1W, graphOpTask1R, graphOpTask1B);
        timeTask1W.getData().addAll(graphTimeTask1W, graphTimeTask1R, graphTimeTask1B);
    }
    public void checkLab4(){
        //Проверка работоспосбоности алгоритмов 4 лабы
        field.getChildren().clear();

        Text taskTitle = new Text("Проверка алгоритма\n\n");
        taskTitle.setFont(Font.font("Verdana", FontWeight.BLACK, 25));
        String s = "Граф: \n";
        Graph<Integer> graph = GraphNewIter.randomGraph(6);

        s += graph.toString();
        Text text1 = new Text(s);
        s = "\nОтвет\n";
        s += GraphAlg.DFS(graph, graph.getVertexCount()).toString();
        Text text2 = new Text(s);
        text1.setFont(Font.font("Verdana", 15));
        text2.setFont(Font.font("Verdana", FontPosture.ITALIC, 15));

        Text taskTitle22 = new Text("");
        field.getChildren().addAll(taskTitle, text1, text2, taskTitle22);
    }
}
