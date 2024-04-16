package com.example.algorithm_lab1.lab3;

import java.util.Arrays;
import java.util.Random;

public class SortNewIter {
    public static int[] randomSort(int n){
        Random random = new Random();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(-10_000, 10_000);
        }

        return arr;
    }

    public static int[] worstShellSort(int n){
        Random random = new Random();
        int[] arr = new int[n];

        arr[0] = random.nextInt(-10_000, 10_000);

        for (int i = 1; i < n; i++) {
            arr[i] = arr[i-1] - 100;
        }

        return arr;
    }

    public static int[] bestSort(int n){
        Random random = new Random();
        int[] arr = new int[n];

        arr[0] = random.nextInt(-10_000, 10_000);

        for (int i = 1; i < n; i++) {
            arr[i] = arr[i-1] + random.nextInt(1, 100);
        }

        return arr;
    }

    public static int[] worstBlockSort(int n){
        int[] arr = new int[n];
        arr[0] = -10000;
        arr[1] = 10000;

        for (int i = 2; i < n; i++) {
            arr[i] = 10000;
        }

        return arr;
    }

    public static int[] bestBlockSort(int n){
        int[] arr = new int[n];
        Arrays.fill(arr, 10000);

        return arr;
    }

}
