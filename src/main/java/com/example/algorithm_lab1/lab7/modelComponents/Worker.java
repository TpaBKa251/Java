package com.example.algorithm_lab1.lab7.modelComponents;

import com.example.algorithm_lab1.lab6.dataStructures.Queue;

import java.io.Serializable;

public abstract class Worker implements Serializable{
    private int time;
    private int max = -1;
    private int min = 1_000_000;

    private Queue<Component> queue = new Queue<>();

    public Worker(int time){
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void decreaseTime(){
        this.time--;
    }

    public void enqueue(Component component){
        queue.enqueue(component);
    }

    public Component dequeue(){
        return queue.dequeue();
    }

    public Component getHead(){
        return queue.getHead();
    }

    public int getCntComponents(){
        return queue.length();
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    @Override
    public String toString() {
        return "Работник, " +
                "время = " + time;
    }
}
