package com.example.algorithm_lab1.lab7.modelComponents;

import java.io.Serializable;

public class Component implements Serializable {
    private int time;

    public Component(int time){
        this.time = time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }
}
