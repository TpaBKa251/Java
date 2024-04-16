package com.example.algorithm_lab1.lab7;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<Transporter> transporters;

    private int cntTransporters;

    public Model(int cntTransporters){
        this.cntTransporters = cntTransporters;
        transporters = new ArrayList<>();
    }

    public int getCntTransporters() {
        return cntTransporters;
    }

    public void setCntTransporters(int cntTransporters) {
        this.cntTransporters = cntTransporters;
    }

    public void addTransporter(Transporter transporter){
        transporters.add(transporter);
    }

    public void removeTransporter(Transporter transporter){
        transporters.remove(transporter);
    }

    public void removeTransporter(int index){
        transporters.remove(index);
    }

    public Transporter getTransporter(Transporter transporter){
        int i = transporters.indexOf(transporter);
        return transporters.get(i);
    }

    public Transporter getTransporter(int i){
        return transporters.get(i);
    }
}
