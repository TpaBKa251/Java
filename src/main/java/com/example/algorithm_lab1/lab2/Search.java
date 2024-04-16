package com.example.algorithm_lab1.lab2;

import java.time.Duration;
import java.time.Instant;

public class Search {
    public static int numCalc;//счетчик операций
    public static long time;//время

//-----------------------ЛИНЕЙНЫЙ------------------------------------------//
    public static int lineSearchDisord(int[] arr, int num){
        numCalc = 0;
        time = 0;
        Instant start = Instant.now();

        for (int i = 0; i < arr.length; i++) {//ну тут нечего объяснять - всё понятно
            numCalc+=5;
            if (arr[i] == num){
                Instant finish = Instant.now();
                time = Duration.between(start, finish).toNanos();
                return i;
            }
        }

        Instant finish = Instant.now();
        time = Duration.between(start, finish).toNanos();
        return -1;
    }

    //А тут подумать надо
//-----------------ЛИНЕЙНЫЙ С БАРЬЕРОМ (БЫСТРЫЙ)------------------------------------------//
    public static int fastLineSearchDisord(int[] arr, int num){
        numCalc = 0;
        time = 0;
        Instant start = Instant.now();

        numCalc += 12;
        int n = arr.length;
        int last = arr[n - 1];//Сохраним прежний элемент массива
        arr[n - 1] = num;//Гарантируем, что num есть в массиве
        //Есть гарантия того, что элемент есть в массиве, значит индекс можно не проверять
        int i = 0;
        while (arr[i] != num){
            i++;
        }

        numCalc += i*3;

        if (i != n-1 || num == last) {//Последний элемент был искомым§
            Instant finish = Instant.now();
            time = Duration.between(start, finish).toNanos();
            return i;
        }

        Instant finish = Instant.now();
        time = Duration.between(start, finish).toNanos();
        return -1;
    }

    //Ну тут по аналогии
//---------------ЛИНЕЙНЫЙ С БАРЬЕРОМ В ОТСОРТИРОВАННОМ------------------------------------------//
    public static int fastLineSearchOrd(int[] arr, int num){
        numCalc = 0;
        time = 0;
        Instant start = Instant.now();

        numCalc += 9;
        int n = arr.length;
        int last = arr[n - 1];//Сохраним прежний элемент массива
        arr[n - 1] = num;//Гарантируем, что num есть в массиве
        //Есть гарантия того, что элемент есть в массиве, значит индекс можно не проверять
        int i = 0;

        for (i = 0; arr[i] < num; i++) {//Одно условие в цикле
            numCalc+=3;
        }

        numCalc += 3;
        if (i != (n - 1) || num == last) {//Не уткнулись в барьер или последний элемент был искомым
            Instant finish = Instant.now();
            time = Duration.between(start, finish).toMillis();
            return i;
        }

        Instant finish = Instant.now();
        time = Duration.between(start, finish).toMillis();
        return -1;
    }

    //-----------------------БИНАРНЫЙ------------------------------------------//
    public static int binSearch(int[] arr, int num){
        numCalc = 0;
        time = 0;
        Instant start = Instant.now();
        int firstIndex = 0;
        int lastIndex = arr.length - 1;

        // условие прекращения (элемент не представлен)
        while(firstIndex <= lastIndex) {
            numCalc+=6;
            int middleIndex = (firstIndex + lastIndex) / 2;
            // если средний элемент - целевой элемент, вернуть его индекс
            if (arr[middleIndex] == num) {
                Instant finish = Instant.now();
                time = Duration.between(start, finish).toNanos();

                return middleIndex;
            }

            // если средний элемент меньше
            // направляем наш индекс в middle+1, убирая первую часть из рассмотрения
            else if (arr[middleIndex] < num) {
                numCalc++;
                firstIndex = middleIndex + 1;
            }
                // если средний элемент больше
                // направляем наш индекс в middle-1, убирая вторую часть из рассмотрения
            else if (arr[middleIndex] > num) {
                numCalc++;
                lastIndex = middleIndex - 1;
            }
        }

        Instant finish = Instant.now();
        time = Duration.between(start, finish).toNanos();
        return -1;
    }

    //---------------ПРЫЖКАМИ (БЛОЧНЫЙ)------------------------------------------//
    public static int jumpSearch(int[] arr, int num){
        numCalc = 0;
        time = 0;
        Instant start = Instant.now();

        //numCalc+=5;
        int n = arr.length;
        int jumpStep = (int) Math.sqrt(n);//длина каждого блока квадратный корень из длины массива
        //это лучшая длина блока, так как размер и количество блоков ±равны
        int previousStep = 0;//последний элемент предыдущего блока (точнее индекс)

        //Прыгаем по блокам, пока не найдем блок, последний элемент которого (jumpStep)
        //или последний элемент массива (чему то меньшему из них) меньше нашего числа
        while (arr[Math.min(jumpStep, n) - 1] < num) {
            //numCalc+=7;
            numCalc++;
            previousStep = jumpStep;                   //а этот <-|
            jumpStep += (int) Math.sqrt(n);      //прыгаем дальше |
            if (previousStep >= n) {//если наш текущий прыжок ^ больше либо равен длине массива - до свидания
                Instant finish = Instant.now();//             | НЕ этот
                time = Duration.between(start, finish).toNanos();
                return -1;
            }
        }

        //Далее перебираем нужный блок, если переменная равна прыжку или длине массива (чему то меньшему из них)
        //То элемента в блоке, а соответственно во всём массиве нет
        while (arr[previousStep] < num) {
            //numCalc+=5;
            numCalc++;
            previousStep++;
            if (previousStep == Math.min(jumpStep, n)) {
                Instant finish = Instant.now();
                time = Duration.between(start, finish).toNanos();
                return -1;
            }
        }

        //Если мы вышли из предыдущего цикла, то проверяем равен ли найденный элемент искомому
        numCalc+=2;
        if (arr[previousStep] == num) {
            Instant finish = Instant.now();
            time = Duration.between(start, finish).toNanos();
            return previousStep;
        }

        Instant finish = Instant.now();
        time = Duration.between(start, finish).toNanos();
        return -1;
    }
}
