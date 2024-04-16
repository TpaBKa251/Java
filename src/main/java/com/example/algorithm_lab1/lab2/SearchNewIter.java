package com.example.algorithm_lab1.lab2;

import java.util.Arrays;
import java.util.Random;

public class SearchNewIter {
//---------------------ЛИНЕЙНЫЙ И С БАРЬЕРОМ------------------------------//

    //Лучший
    //Ставим нужный элемент в начало
    public static int[] bestLineSearchDisord(int n, int num){
        int[] arr = new int[n];
        arr[0] = num;

        return arr;
    }

    //Худший
    //Нужного элемента нет
    public static int[] worstLineSearchDisord(int n, int num){
        int[] arr = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = num + random.nextInt(1, 1000000);
        }

        return arr;
    }

    //Рандом (2 варианта, с указанием нужного элемента и без)
    public static int[] randomLineSearchDisord(int n){
        int[] arr = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(-1000000, 1000000);
        }

        return arr;
    }

    public static int[] randomLineSearchDisord(int n, int num){
        int[] arr = new int[n];
        Random random = new Random();
        int pos = random.nextInt(0, n);

        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(-1000000, 1000000);
        }

        arr[pos] = num;

        return arr;
    }

//-----------СОЛЯНКА ДЛЯ ОТСОРТИРОВАННОГО--------------------------------------//

    //Лучший бинарный
    //Ставим нужный элемент в середину
    public static int[] bestBinSearchOrd(int n, int num){
        int[] arr = randomLineSearchDisord(n);
        Arrays.sort(arr);
        int firstIndex = 0;
        int lastIndex = arr.length - 1;
        arr[(firstIndex + lastIndex) / 2] = num;
        return arr;
    }

    //Рандом (2 варианта)
    public static int[] randomSearchOrd(int n){
        int[] arr = randomLineSearchDisord(n);
        Arrays.sort(arr);
        return arr;
    }
    public static int[] randomSearchOrd(int n, int num){
        int[] arr = randomLineSearchDisord(n, num);
        Arrays.sort(arr);
        return arr;
    }

    //Лучший прыжками
    //Ставим нужный элемент первым
    public static int[] bestJumpSearchOrd(int n, int num){
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = num + i;
        }

        return arr;
    }

    //Худший для линейного и бинарного
    //Ставим элемент в конец
    public static int[] worstLineBinSearchOrd(int n, int num){
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = num - i - 1;
        }

        Arrays.sort(arr);

        return arr;
    }

    //Худший прыжками
    //Ставим элемент в конец последнего блока (в конец матрицы не прокатит, изучи алгоритм)
    public static int[] worstJumpSearchOrd(int n, int num){
        int[] arr = new int[n];
        int s = (int) Math.sqrt(n) * (int) Math.sqrt(n) - 1;
        arr[s] = num;
        int k = 1;
        for (int i = s-1; i >= 0; i--) {
            arr[i] = num - k;
            k++;
        }
        for (int i = s+1; i < n; i++) {
            arr[i] = num + i;
        }

        return arr;
    }
}
