package com.example.algorithm_lab1.lab5;

public class Serializator3000 {
    // СЕРИАЛИЗАЦИЯ
    // Дабле
    public static String serialize(Double num){
        String strBin = Long.toBinaryString(Double.doubleToRawLongBits(num));//Переводим число в бинарную строку
        //Добиваем до 64 бит добавлением нулей слева
        if (strBin.length() < 64){
            String zeros = "";
            for (int i = 0; i < 64 - strBin.length(); i++) {
                zeros += "0";
            }

            strBin = zeros + strBin;
        }

        return strBin;
    }

    // Чар так же, только добиваем до 16 бит
    public static String serialize(char symbol){
        String strBin = Integer.toBinaryString(symbol);

        if (strBin.length() < 16){
            String zeros = "";
            for (int i = 0; i < 16 - strBin.length(); i++) {
                zeros += "0";
            }

            strBin = zeros + strBin;
        }

        return strBin;
    }


    // ДЕСЕРИАЛИЗАЦИЯ
    // Дабле у нас 64 бита, а чар 16 бит, значит длина двоичной строки 64 или 16, так и различаем в какой тип данных переводить
    public static Object deserialize(String strBin) {
        if (strBin.length() == 64) {
            return Double.longBitsToDouble(Long.parseUnsignedLong(strBin, 2));
        }
        else {
            return (char) Integer.parseInt(strBin, 2);
        }
    }


    // РАСЧЕТ МИНИМАЛЬНОГО КОЛИЧЕСТВА ПАМЯТИ В БАЙТАХ (отбрасываем первые нули и считаем длину двоичной строки)
    // Дабле
    public static int countBytes(double num) {
        String strBin = serialize(num);
        int cntBit = strBin.length();
        int i = 0;
        while (strBin.charAt(i) == '0') {
            cntBit -= 1;
            i++;
        }

        int cntByte;

        if (cntBit % 8 == 0){
            cntByte = cntBit / 8;
        }
        else{
            cntByte = cntBit / 8 + 1;
        }

        return cntByte;
    }

    // Чар
    public static int countBytes(char num){
        String strBin = serialize(num);
        int cntBit = strBin.length();
        int i = 0;
        while (strBin.charAt(i) == '0') {
            cntBit -= 1;
            i++;
        }

        int cntByte;

        if (cntBit % 8 == 0){
            cntByte = cntBit / 8;
        }
        else{
            cntByte = cntBit / 8 + 1;
        }

        return cntByte;
    }

    // Из двоичной строки
    public static int countBytes(String s){
        int cntBit = s.length();
        int i = 0;
        while (s.charAt(i) == '0') {
            cntBit -= 1;
            i++;
        }

        int cntByte;

        if (cntBit % 8 == 0){
            cntByte = cntBit / 8;
        }
        else{
            cntByte = cntBit / 8 + 1;
        }

        return cntByte;
    }


    // ИССЛЕДОВАНИЕ ДАБЛЕ

    // Получить параметры из числа
    public static int getSign(double num){
        String strBin = serialize(num);

        if (strBin.charAt(0) == '0'){
            return 0;
        }
        else {
            return 1;
        }
    }

    public static String getMantissa(double num){
        String strBin = serialize(num);

        String mantissa = strBin.substring(12, 64);

        return mantissa;
    }

    public static String getExpo(double num){
        String strBin = serialize(num);

        String expo = strBin.substring(1, 12);

        return expo;
    }

    // Получить параметры из двоичной строки
    public static int getSign(String strBin){
        if (strBin.charAt(0) == '0'){
            return 0;
        }
        else {
            return 1;
        }
    }

    public static String getMantissa(String strBin){
        String mantissa = strBin.substring(12, 64);

        return mantissa;
    }

    public static String getExpo(String strBin){
        String expo = strBin.substring(1, 12);

        return expo;
    }


    // Десериализация через параметры числа
    public static String deserializeByResearch(String strBin){
        String mantissa = getMantissa(strBin);
        double realMantissa = 1;
        int intVal;

        String expo = getExpo(strBin);
        int realExpo = 0;
        
        int sign = getSign(strBin);

        for (int i = 0; i < mantissa.length(); i++) {
            intVal = Character.getNumericValue(mantissa.charAt(i));
            realMantissa += intVal * Math.pow(2, -(i+1));
        }

        for (int i = 0; i < expo.length(); i++) {
            intVal = Character.getNumericValue(expo.charAt(i));
            realExpo += intVal * (int) Math.pow(2, (expo.length()-1) - i);
        }

        realExpo -= 1023;

        double num = realMantissa * Math.pow(2, realExpo);

        String s;

        if (sign == 1){
            num *= -1;
            s = "\nЗнак: " + sign + " - отрицательное" + "\nМантисса: " + mantissa + "\nЭкспонента: " + expo;
        }
        else {
            s = "\nЗнак: " + sign + " - положительное" + "\nМантисса: " + mantissa + "\nЭкспонента: " + expo;
        }

        s += "\n\nРеальная мантисса: " + realMantissa + "\nРеальная экспонента: " + realExpo
                + "\n\nЧисло: " + num + "\nМинимальное число байтов в памяти: " + countBytes(num) + "\n\n\n";

        return s;
    }
}
