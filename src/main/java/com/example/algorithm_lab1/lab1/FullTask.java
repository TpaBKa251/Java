package com.example.algorithm_lab1.lab1;

import java.util.Random;


public class FullTask {
    public static int[] arr1;
    public static int[][] arr2;

    public static void task1Random() {
        arr1 = NewIter.randomForTask1(10);
        for (int i = 0; i < 10; i++) {
            System.out.print(arr1[i] + ", ");
        }
        System.out.println(Tasks.task1(arr1));
    }

    //--------------------------------------------------------//

    public static void task2Random(){
        arr2 = NewIter.randomForTask2(4, 6);
        int[][] arr = Tasks.task2(arr2);
    }


    public static void task3Random(){
        Random random = new Random();
        char start = 'A';
        char end = 'C';
        char transit = 'B';

        int n = random.nextInt(1, 5);
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
        Tasks.task3(n, start, end, transit);
    }

}
