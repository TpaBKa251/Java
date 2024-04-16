package com.example.algorithm_lab1.lab5;

import java.util.Random;

public class SerNewData {
    public static double randomDouble(){
        Random random = new Random();

        return random.nextDouble(-100.0, 100.0);
    }

    public static char randomChar(){
        Random random = new Random();

        int i = random.nextInt(33, 126);

        return (char) i;
    }

    public static Double[] random5Double(){
        Double[] doubles = new Double[5];

        for (int i = 0; i < 5; i++) {
            doubles[i] = randomDouble();
        }

        return doubles;
    }

    public static char[] random5Char(){
        char[] chars = new char[5];

        for (int i = 0; i < 5; i++) {
            chars[i] = randomChar();
        }

        return chars;
    }
}
