package com.example.algorithm_lab1.lab7.modelComponents;

import java.io.Serializable;

public class Robot extends Worker implements Serializable {

    public Robot(int time){
        super(time);
    }

    @Override
    public String toString() {
        return "Робот, " +
                "время = " + getTime() + ", детали = " + getCntComponents();
    }
}
