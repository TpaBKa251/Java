package com.example.algorithm_lab1.lab1;

import java.util.Random;

public class NewIter {
//-------СОЗДАНИЕ ВХОДНЫХ ДАННЫХ 1 АЛГОРИТМА-----------------//

    //Создает рандомный массив
    public static int[] randomForTask1(int n){
        int[] arr = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(-10000, 10000);
        }

        return arr;
    }

    //создает массив для лучшего варианта
    public static int[] bestForTask1(int n){
        int[] arr = new int[n];
        Random random = new Random();
        arr[0] = 1;//первый элемент сразу же пололжительный
        arr[n-1] = -1;//а последний отрицательный

        for (int i = 1; i < n-1; i++) {
            arr[i] = random.nextInt(-10000, 10000);//а тут неважно
        }

        return arr;
    }

    //худший
    public static int[] worstForTask1(int n){
        int[] arr = new int[n];
        arr[n-1] = 1;//здесь наоборот первый элемент отрицательный, а послдений положительный
        arr[0] = -1;

        for (int i = 1; i < n-1; i++) {
            arr[i] = 0;//а остальные равны нулю, так как "+" или "-" нам больше не нужен
        }

        return arr;
    }

    //------ВХОДНЫЕ ДАННЫЕ ДЛЯ 2 АЛГОРИТМА--------------------------------//

    //Созадет рандомную матрицу
    public static int[][] randomForTask2(int m, int n){
        int[][] arr = new int[m][n];
        Random random = new Random();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = random.nextInt(-1000, 1000);
            }
        }

        return arr;
    }

    //Лучший
    public static int[][] bestForTask2(int m, int n){
        int[][] arr = new int[m][n];
        Random random = new Random();

        //Заполняем матрицу рандомными положительными числами (и нулями по преколу)
        //В таком случае вторая часть алгоритма с заменой отрицательных чисел не запуститься,
        //так как их просто нет
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = 1;
            }
        }

        return arr;
    }

    //Худший
    public static int[][] worstForTask2(int m, int n){
        int[][] arr = new int[m][n];
        Random random = new Random();

        //Заполняем матрицу только отрицательными числами (нулей по преколу нет)
        //В этом случае вторая часть алгоритма отработатет полностью по всей матрице
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = -1;
            }
        }

        return arr;
    }

    //------ДО СВИДАНИЯ-----------------------------------------------//

    //А тут нет худших и лучших случаев, всегда один
    public static void randomForTask3(){

    }
}
