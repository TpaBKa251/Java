package com.example.algorithm_lab1.lab1;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Tasks {
    //Поля для фикасации кол-ва оперций и времени
    public static int numCalc;//Кол-во операций
    public static long time;//Время
    public static String s = "";//Строка вывода для 3 алгоритма

    //ПЕВЫЙ АЛГОРИТМ: первый положительный - последний отрицательный
    public static int task1(int[] arr) {
        numCalc = 0;//обнуляем счетчик оперций
        Instant start = Instant.now();//фиксируем текущее время
        int n = arr.length;

        numCalc+=3;
        int pos = 0;
        int neg = 0;
        //int pos2 = 0;
        //int neg2 = 0;

        /*for (int j : arr) {//решение в лоб
            numCalc+=3;
            if (j > 0 && pos == 0) {
                numCalc++;
                pos = j;
            } else if (j < 0) {
                numCalc++;
                neg = j;
            }
        }*/

        for (int i = 0; i < n; i++) {
            numCalc+=4;
            if (arr[i] > 0){
                numCalc++;
                pos = arr[i];
                break;
            }
        }
        for (int i = n-1; i >= 0; i--) {
            numCalc+=4;
            if (arr[i] < 0){
                numCalc++;
                neg = arr[i];
                break;
            }
        }


        Instant finish = Instant.now();//Фиксируем время по завершении алгоритма
        long duration = Duration.between(start, finish).toNanos();//Считаем время выполнения в нс
        time = duration;

        return pos - neg;
    }

    //ВТОРОЙ АЛГОРИТМ: замена отрицательных элементов матрицы максимальным
    public static int[][] task2(int[][] arr){
        numCalc = 0;
        Instant start = Instant.now();

        numCalc+=6;
        int m = arr.length;
        int n = arr[0].length;
        int max = -100000;
        //int min = 100000;
        boolean flag = false;
        int[] arrNeg = new int[m*n*2];
        int k = 0;

        /*for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                numCalc++;

                max = Math.max(Math.max(max, arr[m-1-i][n-1-j]), arr[i][j]);//Просто ищем максимальный элемент матрицы, ничего интересного

                if (i > m-1-i){
                    break;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                numCalc++;

                if (arr[i][j] < 0){
                    arr[i][j] = max;
                }
                if (arr[m-i-1][n-j-1] < 0){
                    arr[m-i-1][n-j-1] = max;
                }

                if (i > m-1-i){
                    break;
                }
            }
        }*/

        /*for (int i = 0; i < m; i++) {
            numCalc+=2;
            for (int j = 0; j < n; j++) {
                numCalc+=6;

                max = Math.max(max, arr[i][j]);//Просто ищем максимальный элемент матрицы, ничего интересного
                min = Math.min(min, arr[i][j]);
            }
        }

        numCalc+=2;
        if (min < 0){
            for (int i = 0; i < m; i++) {
                numCalc+=2;
                for (int j = 0; j < n; j++) {
                    numCalc+=4;

                    if (arr[i][j] < 0){
                        numCalc++;
                        arr[i][j] = max;
                    }
                }
            }
        }*/

        for (int i = 0; i < m; i++) {
            numCalc+=2;
            for (int j = 0; j < n; j++) {
                numCalc+=6;
                max = Math.max(max, arr[i][j]);//Просто ищем максимальный элемент матрицы, ничего интересного

                if (arr[i][j] < 0){
                    flag = true;
                    numCalc+=4;
                    arrNeg[k] = i;
                    arrNeg[k+1] = j;
                    k += 2;
                }
            }
        }

        numCalc+=2;
        if (flag) {
            for (int i = 0; i < k-1; i += 2) {
                numCalc+=3;
                arr[arrNeg[i]][arrNeg[i+1]] = max;
            }
        }

        Instant finish = Instant.now();
        long duration = Duration.between(start, finish).toMillis();
        time = duration;
        return arr;
    }

    //ТРЕТИЙ АЛГОРИТМ: ханойские башни
    public static void task3(int n, char from_rod, char to_rod, char aux_rod){
        numCalc+=2;

        if (n == 1)
        {
            numCalc++;
            s += "\nПереставить диск 1 с позиции " +  from_rod + " на позицию " + to_rod;
            return;
        }

        numCalc+=3;
        task3(n-1, from_rod, aux_rod, to_rod);
        s += "\nПереставить диск " + n + " с позиции " +  from_rod + " на позицию " + to_rod;
        task3(n-1, aux_rod, to_rod, from_rod);
    }
}
