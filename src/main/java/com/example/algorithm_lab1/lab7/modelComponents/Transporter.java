package com.example.algorithm_lab1.lab7.modelComponents;

import com.example.algorithm_lab1.lab6.dataStructures.Queue;

import java.io.Serializable;

public class Transporter implements Serializable {
    private int cntWorkers;

    private Queue<Worker> workerQueue;

    public Transporter(){
        workerQueue = new Queue<>();
    }

    public int getCntWorkers() {
        return cntWorkers;
    }

    public void addWorker(Worker worker){
        workerQueue.enqueue(worker);
        cntWorkers++;
    }

    public Worker removeWorker(){
        cntWorkers--;
        return workerQueue.dequeue();
    }

    public Worker get (int index){
        return workerQueue.get(index);
    }

    @Override
    public String toString() {
        return "\nТранспортер, " +
                "кол-во работников = " + cntWorkers +
                ", " + workerQueue;
    }
}
