package com.example.algorithm_lab1.lab7.modelComponents;

import com.example.algorithm_lab1.lab6.dataStructures.Queue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ModelParallel implements Serializable {
    private int cntTransporters;
    private List<Queue<Transporter>> queueListTransporters;

    public ModelParallel(){
        queueListTransporters = new ArrayList<>();
    }

    public void addQueueTransporters(Transporter transporter){
        Queue<Transporter> queue = new Queue<>();
        queue.enqueue(transporter);
        cntTransporters++;
        queueListTransporters.add(queue);
    }

    public void addQueueTransporters(Queue<Transporter> queue){
        if (queue.length() != 0){
            cntTransporters++;
            queueListTransporters.add(queue);
        }
    }

    public void addTransporter(Transporter transporter, int indexQueue){
        cntTransporters++;
        queueListTransporters.get(indexQueue).enqueue(transporter);
    }

    public void addTransporter(Transporter transporter){
        cntTransporters++;
        Queue<Transporter> queue = new Queue<>();
        queue.enqueue(transporter);
        queueListTransporters.add(queue);
    }

    public Transporter removeTransporter(int indexQueue){
        cntTransporters--;
        return queueListTransporters.get(indexQueue).dequeue();
    }

    public int getCntTransporters() {
        return cntTransporters;
    }

    public int getCntQueues(){
        return queueListTransporters.size();
    }

    public int getCntTransportersQueue(int index){
        return queueListTransporters.get(index).length();
    }

    public Transporter getTransporter(int indexQueue, int indexTransporter){
        return queueListTransporters.get(indexQueue).get(indexTransporter);
    }
}
