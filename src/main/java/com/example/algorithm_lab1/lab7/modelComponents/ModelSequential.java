package com.example.algorithm_lab1.lab7.modelComponents;

import com.example.algorithm_lab1.lab6.dataStructures.Queue;

import java.io.Serializable;
import java.util.TimerTask;

public class ModelSequential implements Serializable {
    private Queue<Transporter> queueTransporters;
    private int cntTransporters;


    private double speed;
    private int humanKoef;
    private boolean twist;
    private int timeComponent;
    private int crookedKoef;



    public ModelSequential(){
        queueTransporters = new Queue<>();
    }

    public void addTransporter(Transporter transporter){
        cntTransporters++;
        queueTransporters.enqueue(transporter);
    }

    public Transporter removeTransporter(Transporter transporter){
        cntTransporters--;
        return queueTransporters.dequeue();
    }

    public int getCntTransporters() {
        return cntTransporters;
    }

    public int getCntWorkers(){
        int cnt = 0;

        for (int i = 0; i < cntTransporters; i++) {
            cnt += getTransporter(i).getCntWorkers();
        }

        return cnt;
    }

    public int getCntComponents(){
        int cnt = 0;

        for (int i = 0; i < cntTransporters; i++) {
            Transporter transporter = getTransporter(i);

            for (int j = 0; j < transporter.getCntWorkers(); j++) {
                cnt += transporter.get(j).getCntComponents();
            }
        }

        return cnt;
    }

    public long getAllTime(){
        long allTime = 0;

        for (int i = 0; i < cntTransporters; i++) {
            Transporter transporter = getTransporter(i);

            for (int j = 0; j < transporter.getCntWorkers(); j++) {
                if (transporter.get(j) instanceof Human h)
                    allTime += (long) timeComponent * 3 * h.getCrookedKoef();
                else
                    allTime += timeComponent;
            }
        }

        return allTime;
    }

    public Transporter getTransporter(int index){
        return queueTransporters.get(index);
    }





    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public double getSpeed() {
        return speed;
    }


    public void setHumanKoef(int humanKoef) {
        this.humanKoef = humanKoef;
    }
    public int getHumanKoef() {
        return humanKoef;
    }


    public void setTimeComponent(int timeComponent) {
        this.timeComponent = timeComponent;
    }
    public int getTimeComponent() {
        return timeComponent;
    }


    public void setTwist(boolean twist) {
        this.twist = twist;
    }
    public boolean getTwist(){
        return twist;
    }


    public void setCrookedKoef(int crookedKoef) {
        this.crookedKoef = crookedKoef;
    }
    public int getCrookedKoef() {
        return crookedKoef;
    }




    @Override
    public String toString() {
        return "Последовательный конвейер, " +
                "кол-во транспортеров = " + cntTransporters +
                ", скорость работы = " + speed + ", человеческий коэффициент = " + humanKoef +
                ", подкрутка = " + twist + ", время детали = " + timeComponent +
                ", коэффициент криворукости = " + crookedKoef + ", " + queueTransporters;
    }

    public String toStringForUser(){
        if (twist) {
            return "Время детали = " + timeComponent + ", подстраивание модели: да, " +
                    "период подачи детали = " + (1.0 / (double) (humanKoef * crookedKoef * timeComponent)) + " дет/сек";
        }
        else {
            return "Время детали = " + timeComponent + ", подстраивание модели: нет, " +
                    "период подачи детали = 1 дет/сек";
        }
    }
}
