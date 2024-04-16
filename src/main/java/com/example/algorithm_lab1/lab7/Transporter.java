package com.example.algorithm_lab1.lab7;

import com.example.algorithm_lab1.lab6.dataStructures.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Transporter {
    private List<Worker> workers;
    private int cntWorkers;

    private Queue<Worker> workerQueue;

    public Transporter(int cntWorkers){
        this.cntWorkers = cntWorkers;
        workers = new ArrayList<>();
        workerQueue = new Queue<>();
    }

    public int getCntWorkers() {
        return cntWorkers;
    }

    public void setCntWorkers(int cntWorkers) {
        this.cntWorkers = cntWorkers;
    }

    public void addWorker(Worker worker){
        workers.add(worker);
    }

    public void removeWorker(Worker worker){
        workers.remove(worker);
    }

    public void removeWorker(int index){
        workers.remove(index);
    }

    public Worker getWorker(Worker worker){
        int i = workers.indexOf(worker);
        return workers.get(i);
    }

    public Worker getWorker(int i){
        return workers.get(i);
    }
}
