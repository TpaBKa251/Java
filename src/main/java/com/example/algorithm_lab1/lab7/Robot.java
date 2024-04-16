package com.example.algorithm_lab1.lab7;

public class Robot extends Worker {
    private int time;

    public Robot(int time){
        super(time);
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public void setTime(int time) {
        this.time = time;
    }
}
