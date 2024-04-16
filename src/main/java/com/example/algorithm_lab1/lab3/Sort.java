package com.example.algorithm_lab1.lab3;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Sort {
    public static long numCalc;
    public static long time;

    public static int[] ansForBucket;
    public static int l = 0;

//-------------МЕТОД ШЕЛЛА----------------------------//
    public static int[] shellSort(int[] arr) {
        Instant start = Instant.now();

        numCalc++;
        int n = arr.length;

        for (int h = n/2; h > 0; h /= 2) {
            numCalc +=2;
            for (int i = h; i < n; i++) {
                numCalc +=4;
                int temp = arr[i];

                int j;
                for (j = i; j >= h && arr[j - h] > temp; j -= h) {
                    numCalc +=6;
                    arr[j] = arr[j - h];
                }

                numCalc++;
                arr[j] = temp;
            }
        }

        Instant finish = Instant.now();
        time = Duration.between(start, finish).toNanos();

        return arr;
    }

    public static int[] shellSortWorst(int[] arr) {
        Instant start = Instant.now();

        numCalc++;
        int n = arr.length;

        for (int h = 1; h > 0; h /= 2) {
            numCalc +=2;
            for (int i = h; i < n; i++) {
                numCalc +=4;
                int temp = arr[i];

                int j;
                for (j = i; j >= h && arr[j - h] > temp; j -= h) {
                    numCalc +=6;
                    arr[j] = arr[j - h];
                }

                numCalc++;
                arr[j] = temp;
            }
        }

        Instant finish = Instant.now();
        time = Duration.between(start, finish).toMillis();

        return arr;
    }

//-------------БЫСТРАЯ СОРТИРОВКА----------------------------//
    public static void quickSort(int[] arr, int low, int high){
        numCalc++;

        if (low < high) {
            int p = separation(arr, low, high);

            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    public static void quickSortWorst(int[] arr, int low, int high){
        numCalc++;

        if (low < high) {
            int p = separationWorst(arr, low, high);

            quickSortWorst(arr, low, p - 1);
            quickSortWorst(arr, p + 1, high);
        }
    }

    static int separation(int[] arr, int low, int high){
        numCalc += 7;

        int midle = (high + low) / 2;
        int stay = arr[midle];

        int i = low - 1;
        swap(arr, midle, high);

        for (int j = low; j < high; j++) {
            numCalc += 5;

            if (arr[j] < stay){
                i++;
                swap(arr, j, i);
            }
        }

        swap(arr, high, i+1);

        return i+1;
    }

    static int separationWorst(int[] arr, int low, int high){
        numCalc += 7;

        int stay = arr[low];

        int i = low - 1;
        for (int j = low; j < high; j++) {
            numCalc += 5;

            if (arr[j] < stay){
                i++;
                swap(arr, j, i);
            }
        }

        swap(arr, high, i+1);

        return i+1;
    }

    static void swap(int[] arr, int i, int j)
    {
        numCalc += 7;

        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


//-------------БЛОЧНАЯ СОРТИРОВКА----------------------------//
    //ВОТ ЭТО ШЛЯПА, ПРОПУСКАЕМ. СЛЕДУЮЩИЙ НОРМ
    public static void bucketSortOne(int[] arr){
        Instant start = Instant.now();
        int n = arr.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i : arr){
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        //System.out.println(max + ", " + min);

        int bucketLength = (int) Math.sqrt(n) + 1;
        int bucketCnt = bucketLength;

        HashMap<Integer, List<Integer>> bucket = new HashMap<>();

        int k = ((max - min) / bucketCnt);

        for (int i = 0; i < n; i++) {
            int k2 = k;
            while (arr[i] > min + k2){
                k2 += k;
            }
            int l = k2/k;

            if (!bucket.containsKey(l)){
                bucket.put(l, new ArrayList<>());
                bucket.get(l).add(arr[i]);
            }
            else {
                bucket.get(l).add(arr[i]);
            }
        }
        //System.out.println(bucket);

        int j = 0;
        for (List<Integer> list : bucket.values()){
            list.sort(Comparator.naturalOrder());
            for (int i = 0; i < list.size(); i++) {
                arr[j] = list.get(i);
                j++;
            }
        }

        /*for (int i : arr){
            System.out.print(i + ", ");
        }*/

        Instant finish = Instant.now();
        time = Duration.between(start, finish).toMillis();

    }

//-------------РАБОЧИЙ ВАРИАНТ (НЕ ПРАВДА)----------------------------//
    public static int[] bucketSortTwo(int[] arr) {
        numCalc = 0;
        numCalc += 13;

        int n = arr.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i : arr) {
            numCalc += 4;

            min = Math.min(min, i);
            max = Math.max(max, i);
        }

        int bucketCnt = (int) Math.sqrt(n) + 1;
        int k = ((max - min) / bucketCnt) + 1;

        int[][] bucket = new int[bucketCnt][0];
        for (int i = 0; i < n; i++) {
            numCalc += 20;

            int k2 = k;
            while (arr[i] > min + k2) {
                numCalc += 4;

                k2 += k;
            }

            int pos = k2 / k - 1;
            int bucketLength = bucket[pos].length;

            int[] temp = new int[bucketLength + 1];
            if (bucketLength + 1 > 1) {
                for (int j = 0; j < bucketLength; j++) {
                    numCalc += 6;

                    temp[j] = bucket[pos][j];
                }
            }
            temp[temp.length - 1] = arr[i];

            bucket[pos] = temp;
        }

        int[] arrSorted = new int[n];
        int s = 0;
        for (int i = 0; i < bucketCnt; i++) {
            numCalc += 6;
            int bucketLength = bucket[i].length - 1;
            shellSort(bucket[i]);
            for (int j = 0; j <= bucketLength; j++) {
                numCalc += 7;
                arrSorted[s] = bucket[i][j];
                //System.out.print(arrSorted[s] + ", ");
                s++;
            }
        }

        return arrSorted;
    }

    //ТОЖЕ МИМО, ПРЕДЫДУЩИЙ НОРМ
    public static void bucketSortThree(int[] arr) {
        Instant start = Instant.now();

        int n = arr.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        ArrayList<Integer> arrSorted = new ArrayList<>();
        //int s = 0;

        for (int i : arr) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }

        int bucketCnt = 1500;
        int k = ((max - min) / bucketCnt) + 1;

        ArrayList<Integer>[] bucket = new ArrayList[bucketCnt];

        for (int i = 0; i < bucketCnt; i++) {
            bucket[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int k2 = k;

            while (arr[i] > min + k2) {
                k2 += k;
            }

            int bucketNum = k2/k - 1;

            //ArrayList<Integer> list = bucket[bucketNum];

            /*f (list.size() > 1){
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j) > arr[i]){
                        list.add(j, arr[i]);
                        break;
                    }

                    if (j == list.size() - 1){
                        list.add(arr[i]);
                    }
                }


            }
            else {
                list.add(arr[i]);
            }*/

            /*if (list.size() > 1){
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j) < arr[i]) {
                        continue;
                    }

                    list.add(j, arr[i]);
                    break;
                }
                if (list.size() == bucket[bucketNum].size()){
                    list.add(arr[i]);
                }
            }
            else {
                list.add(arr[i]);
            }*/

            insert(arr[i], bucket[bucketNum]);
        }

        for (int i = 0; i < bucketCnt; i++) {
            arrSorted.addAll(bucket[i]);
        }

        //System.out.println(arrSorted);


        Instant finish = Instant.now();
        time = Duration.between(start, finish).toMillis();
    }

    public static void insert(int x, ArrayList<Integer> list){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < x){
                continue;
            }
            list.add(i, x);
            return;
        }
        list.add(x);
    }

//-----------ВОТ ОНО--------------------//

    public static void bucketSortFour(int[] arr) {
            //numCalc += 13;

            int n = arr.length;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            boolean isSorted = true;

            if (arr.length > 0){
                min = Math.min(min, arr[0]);
                max = Math.max(max, arr[0]);
            }


        for (int i = 1; i < n; i++) {
            numCalc++;
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);

            if (arr[i] < arr[i-1]){
                isSorted = false;
            }
        }
            int bucketCnt = (int) Math.sqrt(n) + 1;
            int k = ((max - min) / bucketCnt) + 1;
            int[][] bucket = new int[bucketCnt][0];

        if (k > 2 && !isSorted){
            for (int i = 0; i < n; i++) {
                numCalc++;
                //numCalc += 20;

                int k2 = k;
                while (arr[i] > min + k2) {
                    numCalc++;
                    //numCalc += 4;

                    k2 += k;
                }

                int pos = k2 / k - 1;
                int bucketLength = bucket[pos].length;

                int[] temp = new int[bucketLength + 1];
                if (bucketLength + 1 > 1) {
                    for (int j = 0; j < bucketLength; j++) {
                        numCalc++;
                        //numCalc += 6;

                        temp[j] = bucket[pos][j];
                    }
                }
                temp[temp.length - 1] = arr[i];

                bucket[pos] = temp;
            }

            for (int i = 0; i < bucketCnt; i++) {
                //numCalc += 3;
                numCalc++;
                bucketSortFour(bucket[i]);
            }
        }
        else{
            for (int i = 0; i < arr.length; i++) {
                //numCalc += 7;
                numCalc++;
                ansForBucket[l] = arr[i];
                l++;
            }
        }
    }

    public static int[] bucketOut(int[] arr){
        l = 0;
        ansForBucket = new int[arr.length];
        bucketSortFour(arr);

        return ansForBucket;
    }
}
