package com.example.algorithm_lab1.lab7;

public abstract class Worker {
    private int time;
    public Worker(int time){
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
