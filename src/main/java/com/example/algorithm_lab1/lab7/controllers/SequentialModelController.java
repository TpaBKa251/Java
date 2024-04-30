package com.example.algorithm_lab1.lab7.controllers;

//region<Импорты>
import com.example.algorithm_lab1.HelloApplication;
import com.example.algorithm_lab1.lab6.utilities.Utility;
import com.example.algorithm_lab1.lab7.modelComponents.*;
import com.example.algorithm_lab1.lab7.utility.BinConverter;
import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.*;
//endregion

public class SequentialModelController {
//region<Поля>
    //region<FXML>
    @FXML
    private Rectangle rect;

    @FXML
    private Text information;

    @FXML
    private Text information1;

    @FXML
    private Button chartsButton2;
    
    @FXML
    private LineChart<String, Integer> cntSubComponents;

    @FXML
    private Label titleChoiceModel;

    @FXML
    private Label titleParametrs;

    @FXML
    private Label titleChoicePrinciples;

    @FXML
    private Label titleChoiceTime;

    @FXML
    private TextField cntTransporters;

    @FXML
    private TextField crookedKoef;

    @FXML
    private TextField humanKoefFromUser;

    @FXML
    private TextField maxWorkers;

    @FXML
    private TextField minWorkers;

    @FXML
    private TextField timeComponentFromUser;

    @FXML
    private TextField twistFromUser;

    @FXML
    private ChoiceBox<String> choiceModel;

    @FXML
    private ChoiceBox<String> choicePrincipe;

    @FXML
    private ChoiceBox<String> choiceTime;

    @FXML
    private Button startButton;

    @FXML
    private Button constructorButton;

    @FXML
    private Button settingsBackButton;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button backButton;

    @FXML
    private Button chartsButton;

    @FXML
    private Button menuButton;

    @FXML
    private Button play_or_pauseButton;

    @FXML
    private Button speedDownButton;

    @FXML
    private Tooltip speedDownTooltip;

    @FXML
    private Button speedUpButton;

    @FXML
    private Tooltip speedUpTooltip;

    @FXML
    private BarChart<String, Integer> workersBarChart;
    //endregion




    //region<Поля модели>
    private ModelSequential modelSequential; // модель
    private double speed = 1; // скорость работы модели
    private boolean isPlay = false; // работает ли модель
    private boolean isStart = false; // начинала ли работу модель
    private Timer timer = new Timer(); // таймер
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {

        }
    };
    private int timeComponent; // время изготовление детали
    private final int[] timeToWork = {1000}; // время работы модели в секундах
    private final int[] timeNow = {0}; // текущее время работы модели в секундах
    private int humanKoef = 1; // коэффициент упора работы модели (1 - роботы, 3 - некриворукие люди, и тд)
    private boolean twist = false;// с подкруткой или нет (подстраивание модели)
    private int crookedKoefInModel = 1;
    int max = -1;
    int min = 1_000_000;

    private int cntSubComp = 0;
    private final XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
    private final XYChart.Series<String, Integer> series2 = new XYChart.Series<>();
    //endregion


    //region<Булы для установки подсказок, элементов чойсбокса, анимации>
    private boolean isSetTooltip; // подсказки кнопок сцены с работой модели
    private boolean isAddedToChoiceBox; // элементы чойсбокса
    private boolean isSetTooltipSettings; // подсказки сцены с настройками модели
    private boolean isRandom = true;
    //endregion


    //region<Элементы чойсбоксов>
    private final String[] models = {"Рандом", "Рандом с параметрами", "Роботы", "Люди"};
    private final String[] principles = {"Упор на роботов", "Упор на людей", "Рандом", "Без упора", "Ввести"};
    private final String[] times = {"Рандом", "Ввести"};
    //endregion
//endregion




//-------------------------------------------------------------------------------------------------------------------------------//




//region<Методы создания модели>
    // рандомная модель с заданными параметрами
    private ModelSequential createModelSequentialRandom(int cntTransporters, int minWorkers, int maxWorkers){
        ModelSequential modelSequential = new ModelSequential();
        Random random = new Random();

        for (int i = 0; i < cntTransporters; i++) {
            Transporter transporter = new Transporter();
            int cntWorkers = random.nextInt(minWorkers, maxWorkers+1);
            int worker = random.nextInt(0, 2);

            for (int j = 0; j < cntWorkers; j++) {
                if (worker == 0)
                    transporter.addWorker(new Human(0, random.nextInt(1, 4)));
                else
                    transporter.addWorker(new Robot(0));

                worker = random.nextInt(0, 2);
            }

            modelSequential.addTransporter(transporter);
        }

        return modelSequential;
    }

    // полностью рандомная модель
    private ModelSequential createModelSequentialRandom(){
        ModelSequential modelSequential = new ModelSequential();
        Random random = new Random();

        int cntTransporters = random.nextInt(1, 4);
        int minWorkers = random.nextInt(1, 4);
        int maxWorkers = random.nextInt(3, 5);

        for (int i = 0; i < cntTransporters; i++) {
            Transporter transporter = new Transporter();
            int cntWorkers = random.nextInt(minWorkers, maxWorkers+1);
            int worker = random.nextInt(0, 2);

            for (int j = 0; j < cntWorkers; j++) {
                if (worker == 0)
                    transporter.addWorker(new Human(0, random.nextInt(1, 4)));
                else
                    transporter.addWorker(new Robot(0));

                worker = random.nextInt(0, 2);
            }

            modelSequential.addTransporter(transporter);
        }

        return modelSequential;
    }

    // рандомная модель из роботов с заданными параметрами
    private ModelSequential createModelSequentialRobots(int cntTransporters, int minRobots, int maxRobots){
        ModelSequential modelSequential = new ModelSequential();
        Random random = new Random();

        for (int i = 0; i < cntTransporters; i++) {
            Transporter transporter = new Transporter();
            int cntWorkers = random.nextInt(minRobots, maxRobots+1);

            for (int j = 0; j < cntWorkers; j++) {
                transporter.addWorker(new Robot(0));
            }

            modelSequential.addTransporter(transporter);
        }

        return modelSequential;
    }

    // рандомная модель из людей с заданными параметрами и с одним коэффициентом криворукости
    private ModelSequential createModelSequentialHumans(int cntTransporters, int minHumans, int maxHumans, int crookedKoef){
        ModelSequential modelSequential = new ModelSequential();
        Random random = new Random();

        for (int i = 0; i < cntTransporters; i++) {
            Transporter transporter = new Transporter();
            int cntWorkers = random.nextInt(minHumans, maxHumans+1);

            for (int j = 0; j < cntWorkers; j++) {
                transporter.addWorker(new Human(0, crookedKoef));
            }

            modelSequential.addTransporter(transporter);
        }

        crookedKoefInModel = crookedKoef;

        return modelSequential;
    }

    // рандомная модель из людей с заданными параметрами, коэффициент криворукости у каждого рандомный
    private ModelSequential createModelSequentialHumans(int cntTransporters, int minHumans, int maxHumans){
        ModelSequential modelSequential = new ModelSequential();
        Random random = new Random();

        for (int i = 0; i < cntTransporters; i++) {
            Transporter transporter = new Transporter();
            int cntWorkers = random.nextInt(minHumans, maxHumans+1);

            for (int j = 0; j < cntWorkers; j++) {
                transporter.addWorker(new Human(0, random.nextInt(1, 4)));
            }

            modelSequential.addTransporter(transporter);
        }

        return modelSequential;
    }
//endregion




//-------------------------------------------------------------------------------------------------------------------------------//




//region<Принципы работы модели>
    // работа модели с упором на роботов
    private TimerTask startModelSequentialPerToRobots(ModelSequential modelSequential, int timeComponent){
        humanKoef = 1;
        twist = true;
        // создаем новую модель
        this.modelSequential = modelSequential;
        this.timeComponent = timeComponent;
        // установка времени для 1 рабочего на 1 транспортере
        modelSequential.getTransporter(0).get(0).setTime(timeComponent);

	    return createTimerTask(modelSequential, timeComponent, humanKoef, twist);
    }

    // работа модели с упором на не криворуких людей
    private TimerTask startModelSequentialPerToHumans(ModelSequential modelSequential, int timeComponent){
        humanKoef = 3 * crookedKoefInModel;
        twist = true;
        // создаем новую модель
        this.modelSequential = modelSequential;
        this.timeComponent = timeComponent;
        // установка времени для 1 рабочего на 1 транспортере
        modelSequential.getTransporter(0).get(0).setTime(timeComponent);

	    return createTimerTask(modelSequential, timeComponent, humanKoef, twist);
    }

    // полностью рандомная модель
    private TimerTask startModelSequentialRandom(){
        Random random = new Random();
        humanKoef = random.nextInt(1, 4);
        twist = random.nextBoolean();
        // создаем новую модель
        modelSequential = createModelSequentialRandom();
        timeComponent = random.nextInt(1, 4);
        // установка времени для 1 рабочего на 1 транспортере
        modelSequential.getTransporter(0).get(0).setTime(timeComponent);

	    return createTimerTask(modelSequential, timeComponent, humanKoef, twist);
    }

    private TimerTask startModelSequentialRandom(int cntTransporters, int minWorkers, int maxWorkers){
        Random random = new Random();
        humanKoef = random.nextInt(1, 4);
        twist = random.nextBoolean();
        // создаем новую модель
        modelSequential = createModelSequentialRandom(cntTransporters, minWorkers, maxWorkers);
        timeComponent = random.nextInt(1, 4);
        // установка времени для 1 рабочего на 1 транспортере
        modelSequential.getTransporter(0).get(0).setTime(timeComponent);

        return createTimerTask(modelSequential, timeComponent, humanKoef, twist);
    }

    // своя работа модели
    private TimerTask startModelSequential(ModelSequential modelSequential, int timeComponent, int humanKoef, boolean twist){
        this.humanKoef = humanKoef;
        this.twist = twist;
        // создаем новую модель
        this.modelSequential = modelSequential;
        this.timeComponent = timeComponent;
        // установка времени для 1 рабочего на 1 транспортере
        modelSequential.getTransporter(0).get(0).setTime(timeComponent);

	    return createTimerTask(modelSequential, timeComponent, humanKoef, twist);
    }
//endregion




//-------------------------------------------------------------------------------------------------------------------------------//




//region<Методы-утилиты>
    // задание для таймера
    private TimerTask createTimerTask(ModelSequential modelSequential, int timeComponent, int humanKoef, boolean twist){

       // с подкруткой (модель подстраивается под заданный темп работы)
       if (twist) {
           return new TimerTask() {
               @Override
               public void run() {
                   // Идеальными условиями для модели будут роботы или люди с одинаковым коэффициентом криворукости на всем конвейере.
                   // Модель сделана так, что новые детали на конвейер поступают раз в промежуток времени,
                   // необходимый для изготовления детали роботом или человеком
                   if (timeNow[0] % (timeComponent * humanKoef) == 0) {
                       // Добавление детали в очередь первого работника
                       Worker worker1 = modelSequential.getTransporter(0).get(0);
                       worker1.enqueue(new Component(timeComponent));
                       if (worker1.getCntComponents() == 1){
                           worker1.setTime(worker1.getHead().getTime());
                       }

                       cntSubComp++;
                   }

                   for (int i = 0; i < modelSequential.getCntTransporters(); i++) {
                       Transporter transporter = modelSequential.getTransporter(i);

                       for (int j = 0; j < transporter.getCntWorkers(); j++) {
                           Worker worker = transporter.get(j);

                           // если время на изготовление детали рабочим равно нулю, и у него есть детали,
                           // то деталь идет дальше по конвейеру
                           if (worker.getTime() == 0 && worker.getCntComponents() > 0) {
                               // проверка последний ли это рабочий на транспортере
                               if (j + 1 == transporter.getCntWorkers()) {
                                   // проверка последний ли это транспортер
                                   if (i + 1 == modelSequential.getCntTransporters()) {
                                       // если это последний рабочий на всем конвейере, то удаляем деталь из очереди
                                       // если в очереди есть еще детали, то ставим следующую на изготовление
                                       worker.dequeue();

                                       if (worker.getCntComponents() > 0) {
                                           worker.setTime(worker.getHead().getTime());
                                       }

                                   } else {
                                       // если это последний рабочий на не последнем транспортере,
                                       // то передаем деталь первому рабочему следующего транспортера
                                       Worker nextWorker = modelSequential.getTransporter(i + 1).get(0);
                                       nextWorker.enqueue(worker.dequeue());

                                       // при это если у текущего рабочего остались детали в очереди, ставим на изготовление следующую
                                       if (worker.getCntComponents() > 0) {
                                           worker.setTime(worker.getHead().getTime());
                                       }

                                       // если у следующего рабочего только одна деталь (та, которую только передал текущий рабочий),
                                       // то ставим ее на изготовление
                                       if (nextWorker.getTime() == 0 && nextWorker.getCntComponents() == 1) {
                                           nextWorker.setTime(nextWorker.getHead().getTime());
                                       }
                                   }

                               } else {
                                   // если это не последний рабочий любого транспортера,
                                   // то передаем деталь следующему рабочему на транспортере
                                   Worker nextWorker = transporter.get(j + 1);
                                   nextWorker.enqueue(worker.dequeue());

                                   // при это если у текущего рабочего остались детали в очереди, ставим на изготовление следующую
                                   if (worker.getCntComponents() > 0) {
                                       worker.setTime(worker.getHead().getTime());
                                   }

                                   // если у следующего рабочего только одна деталь (та, которую только передал текущий рабочий),
                                   // то ставим ее на изготовление
                                   if (nextWorker.getTime() == 0 && nextWorker.getCntComponents() == 1) {
                                       nextWorker.setTime(nextWorker.getHead().getTime());
                                   }
                               }
                           }

                           // просто вывод в консоль
                           System.out.print("Время = " + timeNow[0] + ", ");
                           System.out.println(worker);
                       }
                   }
                   System.out.println();

                   // если текущее время превысило время на работу модели, то завершаем работу
                   if (timeNow[0] > timeToWork[0]) {
                       timer.cancel();
                       System.out.println("Всё");
                   }

                   // уменьшение времени изготовления детали рабочим
                   for (int i = 0; i < modelSequential.getCntTransporters(); i++) {
                       Transporter transporter = modelSequential.getTransporter(i);

                       for (int j = 0; j < transporter.getCntWorkers(); j++) {
                           Worker worker = transporter.get(j);
                           // происходит только если время рабочего больше нуля
                           if (worker.getTime() > 0) {
                               worker.decreaseTime();
                           }
                       }
                   }

                   // прибавляем текущее время
                   timeNow[0]++;
               }
           };
       }
       // без подкрутки
       else {
           return new TimerTask() {
               @Override
               public void run() {
                   // Добавление детали в очередь первого работника
                   Worker worker1 = modelSequential.getTransporter(0).get(0);
                   worker1.enqueue(new Component(timeComponent));

                   cntSubComp++;

                   for (int i = 0; i < modelSequential.getCntTransporters(); i++) {
                       Transporter transporter = modelSequential.getTransporter(i);

                       for (int j = 0; j < transporter.getCntWorkers(); j++) {
                           Worker worker = transporter.get(j);

                           // если время на изготовление детали рабочим равно нулю, и у него есть детали,
                           // то деталь идет дальше по конвейеру
                           if (worker.getTime() == 0 && worker.getCntComponents() > 0) {
                               // проверка последний ли это рабочий на транспортере
                               if (j + 1 == transporter.getCntWorkers()) {
                                   // проверка последний ли это транспортер
                                   if (i + 1 == modelSequential.getCntTransporters()) {
                                       // если это последний рабочий на всем конвейере, то удаляем деталь из очереди
                                       // если в очереди есть еще детали, то ставим следующую на изготовление
                                       worker.dequeue();

                                       if (worker.getCntComponents() > 0) {
                                           worker.setTime(worker.getHead().getTime());
                                       }

                                   } else {
                                       // если это последний рабочий на не последнем транспортере,
                                       // то передаем деталь первому рабочему следующего транспортера
                                       Worker nextWorker = modelSequential.getTransporter(i + 1).get(0);
                                       nextWorker.enqueue(worker.dequeue());

                                       // при это если у текущего рабочего остались детали в очереди, ставим на изготовление следующую
                                       if (worker.getCntComponents() > 0) {
                                           worker.setTime(worker.getHead().getTime());
                                       }

                                       // если у следующего рабочего только одна деталь (та, которую только передал текущий рабочий),
                                       // то ставим ее на изготовление
                                       if (nextWorker.getTime() == 0 && nextWorker.getCntComponents() == 1) {
                                           nextWorker.setTime(nextWorker.getHead().getTime());
                                       }
                                   }

                               } else {
                                   // если это не последний рабочий любого транспортера,
                                   // то передаем деталь следующему рабочему на транспортере
                                   Worker nextWorker = transporter.get(j + 1);
                                   nextWorker.enqueue(worker.dequeue());

                                   // при это если у текущего рабочего остались детали в очереди, ставим на изготовление следующую
                                   if (worker.getCntComponents() > 0) {
                                       worker.setTime(worker.getHead().getTime());
                                   }

                                   // если у следующего рабочего только одна деталь (та, которую только передал текущий рабочий),
                                   // то ставим ее на изготовление
                                   if (nextWorker.getTime() == 0 && nextWorker.getCntComponents() == 1) {
                                       nextWorker.setTime(nextWorker.getHead().getTime());
                                   }
                               }
                           }

                           // просто вывод в консоль
                           System.out.print("Время = " + timeNow[0] + ", ");
                           System.out.println(worker);
                       }
                   }
                   System.out.println();

                   // если текущее время превысило время на работу модели, то завершаем работу
                   if (timeNow[0] > timeToWork[0]) {
                       timer.cancel();
                       System.out.println("Всё");
                   }

                   // уменьшение времени изготовления детали рабочим
                   for (int i = 0; i < modelSequential.getCntTransporters(); i++) {
                       Transporter transporter = modelSequential.getTransporter(i);

                       for (int j = 0; j < transporter.getCntWorkers(); j++) {
                           Worker worker = transporter.get(j);
                           // происходит только если время рабочего больше нуля
                           if (worker.getTime() > 0) {
                               worker.decreaseTime();
                           }
                       }
                   }

                   // прибавляем текущее время
                   timeNow[0]++;
               }
           };
       }
    }

    // обновление графиков
    private void showGraphs(){
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        series1.getData().add(new XYChart.Data<>("" + timeNow[0], cntSubComp));
        series2.getData().add(new XYChart.Data<>("" + timeNow[0], modelSequential.getCntComponents()));

        cntSubComponents.getData().clear();
        cntSubComponents.getData().addAll(series1, series2);

        max = Math.max(max, modelSequential.getCntComponents());
        min = Math.min(min, modelSequential.getCntComponents());

        information1.setText("Сейчас деталей: " + modelSequential.getCntComponents() + ", подано деталей: " + cntSubComp +
                ", максимум: " + max + ", минимум: " + min + ", прошло: " + (cntSubComp - modelSequential.getCntComponents()));

        for (int i = 0; i < modelSequential.getCntTransporters(); i++) {
            Transporter transporter = modelSequential.getTransporter(i);

            for (int j = 0; j < transporter.getCntWorkers(); j++) {
                Worker worker = transporter.get(j);
                XYChart.Data<String, Integer> k = new XYChart.Data<>(i + "-" + j + worker.toString().charAt(0), worker.getCntComponents());
                series.getData().add(k);
            }
        }

        workersBarChart.getData().clear();
        workersBarChart.getData().add(series);
    }

    // установка подсказок на кнопки сцены с работой модели
    private void setTooltip(){
        speedUpTooltip.setShowDuration(new Duration(100000));
        speedUpTooltip.setShowDelay(new Duration(100));

        speedDownTooltip.setShowDuration(new Duration(100000));
        speedDownTooltip.setShowDelay(new Duration(100));

        for (int i = 0; i < pane.getChildren().size(); i++) {
            if (pane.getChildren().get(i) instanceof Button b){
                Tooltip t = b.getTooltip();

                setAnimationOnTooltip(b, t);
            }
        }
    }

    // рисование конвейера
    private void paintModel(){
        int cntTransporters = modelSequential.getCntTransporters();
        double widthTransporter;
        double heightTransporter;
        double widthWorker;
        double heightWorker;

        if (cntTransporters > 5){
            widthTransporter = 1500.0 / 5.0;
            heightTransporter = 500.0 / 5.0;
        }
        else {
            widthTransporter = 1500.0 / cntTransporters;
            heightTransporter = 500.0 / cntTransporters;
        }

        if (cntTransporters <= 5) {
            for (int i = 0; i < cntTransporters; i++) {
                Transporter transporter = modelSequential.getTransporter(i);
                int cntWorkers = transporter.getCntWorkers();

	            widthWorker = (widthTransporter - 5 * (cntWorkers + 1)) / cntWorkers;
                heightWorker = heightTransporter - 10;

                Rectangle rectangle = new Rectangle(widthTransporter, heightTransporter);

                rectangle.setX((1680 - 75 - widthTransporter * cntTransporters - 10 * cntTransporters) / 2 + 75 + i * widthTransporter + 10 * i);
                rectangle.setY((930 - heightTransporter) / 2);

                rectangle.getStyleClass().add("rectangle");

                rectangle.setOpacity(0);

                mainPane.getChildren().add(rectangle);
                Animation animation = Utility.getAnimationAppear(mainPane.getChildren().getLast().opacityProperty());
                animation.play();

                for (int j = 0; j < cntWorkers; j++) {
                    Rectangle rectangle1 = new Rectangle(widthWorker, heightWorker);
                    Worker worker = transporter.get(j);

                    rectangle1.setX(rectangle.getX() + j * widthWorker + 5 * (j+1));
                    rectangle1.setY(rectangle.getY() + 5);

                    rectangle1.getStyleClass().add("rectangle2");

                    rectangle1.setOpacity(0);

                    Tooltip t = new Tooltip();
                    t.setShowDuration(Duration.seconds(1000));
                    t.setShowDelay(Duration.millis(100));
                    t.setFont(new Font("Avenir Next Demi Bold", 20));
                    t.setOpacity(0.85);

                    addTooltip(rectangle1, t);
                    PauseTransition pause = new PauseTransition(Duration.millis(100));


                    pause.setOnFinished(event -> {
                        int max = Math.max(worker.getMax(), worker.getCntComponents());
                        int min = Math.min(worker.getMin(), worker.getCntComponents());

                        worker.setMin(min);
                        worker.setMax(max);

                        String workerName;
                        if (worker instanceof Human h){
                            workerName = "Человек, криворукость: " + h.getCrookedKoef();
                        }
                        else {
                            workerName = "Робот";
                        }

	                    t.setText(workerName + "\nКол-во деталей: " + worker.getCntComponents() +
                                "\nМаксимум: " + max + "\nМинимум: " + min);
                        pause.play();
                    });

                    pause.play();

                    mainPane.getChildren().add(rectangle1);
                    Animation animation1 = Utility.getAnimationAppear(mainPane.getChildren().getLast().opacityProperty());
                    animation1.play();
                }
            }
        }
        else {
            int k = 0;
            int a;
            double q = 0;

            if (cntTransporters % 5 == 0){
                a = cntTransporters / 5;
            }
            else {
                a = cntTransporters / 5 + 1;
            }

            for (int i = 0; i < cntTransporters; i++) {
                Rectangle rectangle = new Rectangle(widthTransporter, heightTransporter);

                Transporter transporter = modelSequential.getTransporter(i);
                int cntWorkers = transporter.getCntWorkers();

                widthWorker = (widthTransporter - 5 * (cntWorkers + 1)) / cntWorkers;
                heightWorker = heightTransporter - 10;

                if (i % 5 == 0 && i != 0) {
                    q += 30 + heightTransporter;
                    k += 5;
                }

                rectangle.setX((1680 - 75 - widthTransporter * 5 - 10 * 5) / 2 + 75 + (i - k) * widthTransporter + 10 * (i-k));
                rectangle.setY((930 - heightTransporter * a - 20 * a) / 2 + q);

                rectangle.getStyleClass().add("rectangle");
                rectangle.setOpacity(0);

                mainPane.getChildren().add(rectangle);
                Animation animation = Utility.getAnimationAppear(mainPane.getChildren().getLast().opacityProperty());
                animation.play();

                for (int j = 0; j < cntWorkers; j++) {
                    Rectangle rectangle1 = new Rectangle(widthWorker, heightWorker);
                    Worker worker = transporter.get(j);

                    rectangle1.setX(rectangle.getX() + j * widthWorker + 5 * (j+1));
                    rectangle1.setY(rectangle.getY() + 5);

                    rectangle1.getStyleClass().add("rectangle2");

                    rectangle1.setOpacity(0);

                    Tooltip t = new Tooltip();
                    t.setShowDuration(Duration.seconds(1000));
                    t.setShowDelay(Duration.millis(100));
                    t.setFont(new Font("Avenir Next Demi Bold", 20));
                    t.setOpacity(0.85);

                    addTooltip(rectangle1, t);
                    PauseTransition pause = new PauseTransition(Duration.millis(100));


                    pause.setOnFinished(event -> {
                        int max = Math.max(worker.getMax(), worker.getCntComponents());
                        int min = Math.min(worker.getMin(), worker.getCntComponents());

                        worker.setMin(min);
                        worker.setMax(max);

                        String workerName;
                        if (worker instanceof Human h){
                            workerName = "Человек, криворукость: " + h.getCrookedKoef();
                        }
                        else {
                            workerName = "Робот";
                        }

                        t.setText(workerName + "\nКол-во деталей: " + worker.getCntComponents() +
                                "\nМаксимум: " + max + "\nМинимум: " + min);
                        pause.play();
                    });

                    pause.play();

                    mainPane.getChildren().add(rectangle1);
                    Animation animation1 = Utility.getAnimationAppear(mainPane.getChildren().getLast().opacityProperty());
                    animation1.play();
                }
            }
        }
    }

    // спрятать конвейер
    private void hideModel(){
        Animation animation1 = Utility.getAnimationFade(information.opacityProperty());
        Animation animation2  = Utility.getAnimationFade(information1.opacityProperty());
        animation1.play();
        animation2.play();

        for (int i = 0; i < mainPane.getChildren().size(); i++) {
            if (mainPane.getChildren().get(i) instanceof Rectangle r){
                Animation animation = Utility.getAnimationFade(r.opacityProperty());
                animation.play();
                animation.setOnFinished(event -> r.disableProperty().setValue(true));
            }
        }
    }

    // показать конвейер
    private void showModel(){
        Animation animation1 = Utility.getAnimationAppear(information.opacityProperty());
        Animation animation2  = Utility.getAnimationAppear(information1.opacityProperty());
        animation1.play();
        animation2.play();

        for (int i = 0; i < mainPane.getChildren().size(); i++) {
            if (mainPane.getChildren().get(i) instanceof Rectangle r){
                Animation animation = Utility.getAnimationAppear(r.opacityProperty());
                animation.play();
                animation.setOnFinished(event -> r.disableProperty().setValue(false));
            }
        }
    }

    // установка анимации на подсказку
    private void setAnimationOnTooltip(Node node, Tooltip t){
        node.setOnMouseEntered(mouseEvent -> {
            t.setOpacity(0);
            Animation animation = Utility.getAnimationAppear(t.opacityProperty());
            t.setOnShown(windowEvent -> animation.play());
        });

        node.setOnMouseExited(mouseEvent -> {
            t.setOpacity(1);
            Animation animation = Utility.getAnimationFade(t.opacityProperty());
            animation.play();
        });
    }

    // добавление подсказки элементу (с анимацией)
    private void addTooltip(Node n, String s){
        Tooltip t = new Tooltip();
        t.setText(s);
        setAnimationOnTooltip(n, t);
        Tooltip.install(n, t);
    }

    private void addTooltip(Node n, Tooltip t){
        setAnimationOnTooltip(n, t);
        Tooltip.install(n, t);
    }

    // сериализация модели
    private void convert() throws Exception {
        BinConverter converter = new BinConverter();

        modelSequential.setSpeed(speed);
        modelSequential.setHumanKoef(humanKoef);
        modelSequential.setTwist(twist);
        modelSequential.setTimeComponent(timeComponent);
        modelSequential.setCrookedKoef(crookedKoefInModel);

        converter.convertModel(modelSequential);
    }

    // настройка модели, если выбрана не рандомная
    private void setSettings() throws Exception {
        Random random = new Random();

        switch (choiceTime.getValue()){

            case "Рандом":

                switch (choicePrincipe.getValue()){

                    case "Упор на роботов":
                        task = startModelSequentialPerToRobots(modelSequential, random.nextInt(1, 5));
                        break;

                    case "Упор на людей":
                        task = startModelSequentialPerToHumans(modelSequential, random.nextInt(1, 5));
                        break;

                    case "Рандом":
                        task = startModelSequential(modelSequential, random.nextInt(1, 5), random.nextInt(1, 4), true);
                        break;

                    case "Без упора":
                        task = startModelSequential(modelSequential, random.nextInt(1, 5), 1, false);
                        break;

                    case "Ввести":
                        task = startModelSequential(modelSequential, random.nextInt(1, 5),
                                Integer.parseInt(humanKoefFromUser.getText()), true);
                        break;
                }

                convert();
                break;

            case "Ввести":
                switch (choicePrincipe.getValue()){

                    case "Упор на роботов":
                        task = startModelSequentialPerToRobots(modelSequential, Integer.parseInt(timeComponentFromUser.getText()));
                        break;

                    case "Упор на людей":
                        task = startModelSequentialPerToHumans(modelSequential, Integer.parseInt(timeComponentFromUser.getText()));
                        break;

                    case "Рандом":
                        task = startModelSequential(modelSequential, Integer.parseInt(timeComponentFromUser.getText()),
                                random.nextInt(1, 4), true);
                        break;

                    case "Без упора":
                        task = startModelSequential(modelSequential, Integer.parseInt(timeComponentFromUser.getText()), 1, false);
                        break;

                    case "Ввести":
                        task = startModelSequential(modelSequential, Integer.parseInt(timeComponentFromUser.getText()),
                                Integer.parseInt(humanKoefFromUser.getText()), true);
                        break;
                }

                convert();
                break;
        }
    }
    //endregion




//-------------------------------------------------------------------------------------------------------------------------------//




//region<Методы кнопок (FXML-методы)>
    // возврат к настройкам
    @FXML
    private void settings() throws Exception {
        timer.cancel();
       Utility.changeScene(HelloApplication.class, "lab7_modelSequential_settings.fxml", backButton.getScene().getWindow());
    }

    // возврат в главное меню
    @FXML
    private void mainMenu() throws Exception {
        timer.cancel();
        Utility.changeScene(HelloApplication.class, "lab7_mainMenu.fxml", menuButton.getScene().getWindow());
    }

    // продолжить/приостановить работу модели
    @FXML
    private void playPause(){
        PauseTransition pauseCharts = new PauseTransition(Duration.millis(100));
        pauseCharts.setOnFinished(event -> {
            showGraphs();
            pauseCharts.play();
        });

        if (!isStart){
            information.setText(modelSequential.toStringForUser());
            information1.setText("Сейчас деталей: " + modelSequential.getCntComponents() + ", подано деталей: " + cntSubComp +
                    ", максимум: " + 0 + ", минимум: " + 0 + ", прошло: " + (cntSubComp - modelSequential.getCntComponents()));

            Animation a = Utility.getAnimationAppear(rect.opacityProperty());
            Animation a1 = Utility.getAnimationAppear(information.opacityProperty());
            Animation a2  =Utility.getAnimationAppear(information1.opacityProperty());
            a.play();
            a2.play();
            a1.play();

            System.out.println(modelSequential);
            paintModel();

            if (workersBarChart.getOpacity() == 1 || cntSubComponents.getOpacity() == 1) {
                Animation animation = null;
                
                if (workersBarChart.getOpacity() == 1) {
                    animation = Utility.getAnimationFade(workersBarChart.opacityProperty());
                    animation.play();
                }
                
                if (cntSubComponents.getOpacity() == 1){
                    animation = Utility.getAnimationFade(cntSubComponents.opacityProperty());
                    animation.play();
                }
                
                animation.setOnFinished(event1 -> {
                    pauseCharts.play();
                });
            }
            else {
                pauseCharts.play();
            }

            timer.scheduleAtFixedRate(task, 0, (int) (1000 / speed));

            isPlay = true;
            isStart = true;
        }
        else if (isPlay){
            timer.cancel();
            isPlay = false;
        }
        else if (!isPlay){
            timer = new Timer();
            timer.scheduleAtFixedRate(createTimerTask(modelSequential, timeComponent, humanKoef, twist), 0, (int) (1000 / speed));
            isPlay = true;
        }
    }

    // увеличить скорость работы модели
    @FXML
    private void speedUp(){
        if (speed < 4 && isPlay){
            speed *= 2;
            speedUpTooltip.setText("Увеличить скорость\nСкорость: " + speed);
            speedDownTooltip.setText("Уменьшить скорость\nСкорость: " + speed);
            timer.cancel();
            timer = new Timer();
            timer.scheduleAtFixedRate(createTimerTask(modelSequential, timeComponent, humanKoef, twist), 0, (int) (1000 / speed));
        }
        else if (speed < 4 && !isPlay){
            speed *= 2;
            speedUpTooltip.setText("Увеличить скорость\nСкорость: " + speed);
            speedDownTooltip.setText("Уменьшить скорость\nСкорость: " + speed);
        }
    }

    // уменьшить скорость работы модели
    @FXML
    private void speedDown(){
        if (speed > 0.25 && isPlay){
            speed /= 2;
            speedUpTooltip.setText("Увеличить скорость\nСкорость: " + speed);
            speedDownTooltip.setText("Уменьшить скорость\nСкорость: " + speed);
            timer.cancel();
            timer = new Timer();
            timer.scheduleAtFixedRate(createTimerTask(modelSequential, timeComponent, humanKoef, twist), 0, (int) (1000 / speed));
        }
        else if (speed > 0.25 && !isPlay){
            speed /= 2;
            speedUpTooltip.setText("Увеличить скорость\nСкорость: " + speed);
            speedDownTooltip.setText("Уменьшить скорость\nСкорость: " + speed);
        }
    }

    // показать график рабочих
    @FXML
    private void charts(){
        if (workersBarChart.getOpacity() == 0) {
            if (cntSubComponents.getOpacity() == 1) {
                Animation animation1 = Utility.getAnimationFade(cntSubComponents.opacityProperty());

                animation1.play();
            }
            else
                hideModel();

            Animation animation3 = Utility.getAnimationAppear(workersBarChart.opacityProperty());
            animation3.play();
        }
        else if (workersBarChart.getOpacity() == 1){
            Animation animation = Utility.getAnimationFade(workersBarChart.opacityProperty());
            animation.play();

            showModel();
        }
    }

    // показать графики динамики деталей
    @FXML
    void charts2() {
        if (cntSubComponents.getOpacity() == 0) {

            Animation animation1 = Utility.getAnimationAppear(cntSubComponents.opacityProperty());
            if (workersBarChart.getOpacity() == 1) {
                Animation animation2 = Utility.getAnimationFade(workersBarChart.opacityProperty());
                animation2.play();
            }
            else
                hideModel();

            animation1.play();
        }
        else if (cntSubComponents.getOpacity() == 1){
            Animation animation1 = Utility.getAnimationFade(cntSubComponents.opacityProperty());

            animation1.play();

            showModel();
        }
    }

    // переход из настроек в главное меню
    @FXML
    private void settingsToMainMenu() throws Exception {
        Utility.changeScene(HelloApplication.class, "lab7_mainMenu.fxml", settingsBackButton.getScene().getWindow());
    }

    // переход от настроек к сцене с работой модели
    @FXML
    private void startModel() throws Exception {
        Random random = new Random();

        switch (choiceModel.getValue()){

            case "Рандом":
                task = startModelSequentialRandom();
                convert();
                break;

            case "Рандом с параметрами":
                task  = startModelSequentialRandom(Integer.parseInt(cntTransporters.getText()),
                        Integer.parseInt(minWorkers.getText()), Integer.parseInt(maxWorkers.getText()));
                convert();
                break;

            case "Роботы":
                modelSequential = createModelSequentialRobots(Integer.parseInt(cntTransporters.getText()),
                        Integer.parseInt(minWorkers.getText()), Integer.parseInt(maxWorkers.getText()));

                setSettings();
                break;

            case "Люди":
                if (choicePrincipe.getValue().equals("Упор на людей")) {
                    if (crookedKoef.getText().isEmpty())
                        modelSequential = createModelSequentialHumans(Integer.parseInt(cntTransporters.getText()),
                                Integer.parseInt(minWorkers.getText()), Integer.parseInt(maxWorkers.getText()), random.nextInt(1, 4));
                    else {
                        modelSequential = createModelSequentialHumans(Integer.parseInt(cntTransporters.getText()),
                                Integer.parseInt(minWorkers.getText()), Integer.parseInt(maxWorkers.getText()), Integer.parseInt(crookedKoef.getText()));

                    }
                }
                else {
                    if (crookedKoef.getText().isEmpty())
                        modelSequential = createModelSequentialHumans(Integer.parseInt(cntTransporters.getText()),
                                Integer.parseInt(minWorkers.getText()), Integer.parseInt(maxWorkers.getText()));
                    else
                        modelSequential = createModelSequentialHumans(Integer.parseInt(cntTransporters.getText()),
                                Integer.parseInt(minWorkers.getText()), Integer.parseInt(maxWorkers.getText()), Integer.parseInt(crookedKoef.getText()));
                }

                setSettings();
                break;
        }

        Label label = new Label("Обрабатываем данные...");
        label.setFont(new Font("Avenir Next Demi Bold", 35));
        label.setLayoutX((double) 1662 / 2 - 225);
        label.setLayoutY(540);

        pane.getChildren().add(label);

        PauseTransition pauseTransition = new PauseTransition(Duration.millis(50));
        pauseTransition.setOnFinished(event -> {
	        try {
		        Utility.changeScene(HelloApplication.class, "lab7_modelSequential.fxml", startButton.getScene().getWindow());
	        } catch (Exception e) {
		        throw new RuntimeException(e);
	        }
        });

        pauseTransition.play();

    }

    // установка подсказок кнопок сцены с работой модели
    @FXML
    private void showTooltip() throws Exception {
       if (!isSetTooltip) {
           series1.setName("Поданные детали");
           series2.setName("Сейчас деталей");

           setTooltip();
           BinConverter converter = new BinConverter();
           String path = new File(HelloApplication.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
           path = path.substring(0, path.length() - 19) + "/saved.txt";
           modelSequential = converter.unConvertFile(path);

           backButton.getScene().getWindow().setOnCloseRequest(windowEvent -> Platform.exit());

           timeComponent = modelSequential.getTimeComponent();
           humanKoef = modelSequential.getHumanKoef();
           speed = modelSequential.getSpeed();
           twist = modelSequential.getTwist();
           crookedKoefInModel = modelSequential.getCrookedKoef();
           task = createTimerTask(modelSequential, timeComponent, humanKoef, twist);

           isSetTooltip = true;
       }
    }

    // добавление всякого на сцену с настройками
    @FXML
    private void addStaffOnSettings() {
        if (!isAddedToChoiceBox && !isSetTooltipSettings){
            choiceModel.getItems().addAll(models);
            choicePrincipe.getItems().addAll(principles);
            choiceTime.getItems().addAll(times);
            isAddedToChoiceBox = true;
            choiceTime.getScene().getWindow().setOnCloseRequest(windowEvent -> Platform.exit());

            choiceModel.setOnAction(windowEvent -> {
                if ((!choiceModel.getValue().equals("Рандом") && !choiceModel.getValue().equals("Рандом с параметрами"))
                        && !choiceModel.getValue().isEmpty() && isRandom){
                    isRandom = false;
                    TranslateTransition tt = Utility.getShift(choiceModel, "l", 581, 0, 500);
                    TranslateTransition tt2 = Utility.getShift(titleChoiceModel, "l", 581, 0, 500);
                    tt.playFromStart();
                    tt2.playFromStart();

                    Animation animation = Utility.getAnimationAppear(choicePrincipe.opacityProperty());
                    Animation animation1 = Utility.getAnimationAppear(titleChoicePrinciples.opacityProperty());
                    Animation animation2 = Utility.getAnimationAppear(choiceTime.opacityProperty());
                    Animation animation3 = Utility.getAnimationAppear(titleChoiceTime.opacityProperty());

                    Animation animation4 = Utility.getAnimationAppear(titleParametrs.opacityProperty());
                    Animation animation5 = Utility.getAnimationAppear(cntTransporters.opacityProperty());
                    Animation animation6 = Utility.getAnimationAppear(minWorkers.opacityProperty());
                    Animation animation7 = Utility.getAnimationAppear(maxWorkers.opacityProperty());

                    Animation animation8 = Utility.getAnimationAppear(humanKoefFromUser.opacityProperty());
                    Animation animation9 = Utility.getAnimationAppear(timeComponentFromUser.opacityProperty());
                    Animation animation10 = Utility.getAnimationAppear(crookedKoef.opacityProperty());



                    tt.setOnFinished(event -> {
                        choicePrincipe.disableProperty().setValue(false);
                        choiceTime.disableProperty().setValue(false);
                        cntTransporters.disableProperty().setValue(false);
                        minWorkers.disableProperty().setValue(false);
                        maxWorkers.disableProperty().setValue(false);

                        if (titleParametrs.getOpacity() == 0){
                            animation4.play();
                            animation5.play();
                            animation6.play();
                            animation7.play();
                            animation8.play();
                            animation9.play();
                            animation10.play();
                        }

                        if (choicePrincipe.getOpacity() == 0){
                            animation.play();
                            animation1.play();
                            animation2.play();
                            animation3.play();
                        }
                    });
                }
                else if ((choiceModel.getValue().equals("Рандом") || choiceModel.getValue().equals("Рандом с параметрами")) && !isRandom){
                    isRandom = true;
                    TranslateTransition tt = Utility.getShift(choiceModel, "r", 581, -581, 500);
                    TranslateTransition tt2 = Utility.getShift(titleChoiceModel, "r", 581, -581,500);

                    Animation animation = Utility.getAnimationFade(choicePrincipe.opacityProperty());
                    Animation animation1 = Utility.getAnimationFade(titleChoicePrinciples.opacityProperty());
                    Animation animation2 = Utility.getAnimationFade(choiceTime.opacityProperty());
                    Animation animation3 = Utility.getAnimationFade(titleChoiceTime.opacityProperty());
                    animation.play();
                    animation1.play();
                    animation2.play();
                    animation3.play();

                    if (choiceModel.getValue().equals("Рандом")){
                        if (titleParametrs.getOpacity() == 1) {
                            Animation animation4 = Utility.getAnimationFade(titleParametrs.opacityProperty());
                            Animation animation5 = Utility.getAnimationFade(cntTransporters.opacityProperty());
                            Animation animation6 = Utility.getAnimationFade(minWorkers.opacityProperty());
                            Animation animation7 = Utility.getAnimationFade(maxWorkers.opacityProperty());
                            Animation animation8 = Utility.getAnimationFade(humanKoefFromUser.opacityProperty());
                            Animation animation9 = Utility.getAnimationFade(timeComponentFromUser.opacityProperty());
                            Animation animation10 = Utility.getAnimationFade(crookedKoef.opacityProperty());
                            animation4.play();
                            animation5.play();
                            animation6.play();
                            animation7.play();
                            animation8.play();
                            animation9.play();
                            animation10.play();
                        }
                    }

                    animation1.setOnFinished(event -> {
                        if (choiceModel.getValue().equals("Рандом с параметрами")){
                            choicePrincipe.disableProperty().setValue(true);
                            choiceTime.disableProperty().setValue(true);
                            humanKoefFromUser.disableProperty().setValue(true);
                            timeComponentFromUser.disableProperty().setValue(true);
                            crookedKoef.disableProperty().setValue(true);
                        }
                        else {
                            choicePrincipe.disableProperty().setValue(true);
                            choiceTime.disableProperty().setValue(true);
                            cntTransporters.disableProperty().setValue(true);
                            minWorkers.disableProperty().setValue(true);
                            maxWorkers.disableProperty().setValue(true);
                            humanKoefFromUser.disableProperty().setValue(true);
                            timeComponentFromUser.disableProperty().setValue(true);
                            crookedKoef.disableProperty().setValue(true);
                        }

                        tt.playFromStart();
                        tt2.playFromStart();
                    });
                }
                else if ((choiceModel.getValue().equals("Рандом") && isRandom)){
                    Animation animation = Utility.getAnimationFade(titleParametrs.opacityProperty());
                    Animation animation1 = Utility.getAnimationFade(cntTransporters.opacityProperty());
                    Animation animation2 = Utility.getAnimationFade(minWorkers.opacityProperty());
                    Animation animation3 = Utility.getAnimationFade(maxWorkers.opacityProperty());
                    Animation animation8 = Utility.getAnimationFade(humanKoefFromUser.opacityProperty());
                    Animation animation9 = Utility.getAnimationFade(timeComponentFromUser.opacityProperty());
                    Animation animation10 = Utility.getAnimationFade(crookedKoef.opacityProperty());

                    if (titleParametrs.getOpacity() == 1){
                        animation.play();
                        animation1.play();
                        animation2.play();
                        animation3.play();
                        animation8.play();
                        animation9.play();
                        animation10.play();

                        animation1.setOnFinished(event -> {
                            cntTransporters.disableProperty().setValue(true);
                            minWorkers.disableProperty().setValue(true);
                            maxWorkers.disableProperty().setValue(true);
                            humanKoefFromUser.disableProperty().setValue(true);
                            timeComponentFromUser.disableProperty().setValue(true);
                            crookedKoef.disableProperty().setValue(true);
                        });
                    }

                }
                else if (choiceModel.getValue().equals("Рандом с параметрами") && isRandom) {
                    Animation animation = Utility.getAnimationAppear(titleParametrs.opacityProperty());
                    Animation animation1 = Utility.getAnimationAppear(cntTransporters.opacityProperty());
                    Animation animation2 = Utility.getAnimationAppear(minWorkers.opacityProperty());
                    Animation animation3 = Utility.getAnimationAppear(maxWorkers.opacityProperty());
                    Animation animation8 = Utility.getAnimationAppear(humanKoefFromUser.opacityProperty());
                    Animation animation9 = Utility.getAnimationAppear(timeComponentFromUser.opacityProperty());
                    Animation animation10 = Utility.getAnimationAppear(crookedKoef.opacityProperty());

                    cntTransporters.disableProperty().setValue(false);
                    minWorkers.disableProperty().setValue(false);
                    maxWorkers.disableProperty().setValue(false);
                    humanKoefFromUser.disableProperty().setValue(true);
                    timeComponentFromUser.disableProperty().setValue(true);
                    crookedKoef.disableProperty().setValue(true);

                    if (titleParametrs.getOpacity() == 0) {
                        animation8.play();
                        animation9.play();
                        animation10.play();
                        animation.play();
                        animation1.play();
                        animation2.play();
                        animation3.play();
                    }
                }

                if (choiceModel.getValue().equals("Люди")){
                    crookedKoef.disableProperty().setValue(false);
                }
                else {
                    crookedKoef.disableProperty().setValue(true);
                }
            });

            choicePrincipe.setOnAction(event -> {
                if (choicePrincipe.getValue().equals("Ввести"))
                    humanKoefFromUser.disableProperty().setValue(false);
                else
                    humanKoefFromUser.disableProperty().setValue(true);
            });

            choiceTime.setOnAction(event -> {
                if (choiceTime.getValue().equals("Ввести")){
                    timeComponentFromUser.disableProperty().setValue(false);
                }
                else {
                    timeComponentFromUser.disableProperty().setValue(true);
                }
            });

            addTooltip(constructorButton, "Создание своей модели");
            addTooltip(startButton, "Выберите все параметры\nдля создания модели");
            isSetTooltipSettings = true;
        }
    }

    // переход к конструктору модели 🚬🤏🫠
    @FXML
    void construct() {

    }
//endregion
}
