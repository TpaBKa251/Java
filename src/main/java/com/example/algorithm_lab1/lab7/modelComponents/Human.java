package com.example.algorithm_lab1.lab7.modelComponents;

import java.io.Serializable;

public class Human extends Worker implements Serializable {
    private int crookedKoef;

    public Human(int time, int crookedKoef){
        super(time * 3 * crookedKoef);
        this.crookedKoef = crookedKoef;
    }

    public void setCrookedKoef(int crookedKoef){
        this.crookedKoef = crookedKoef;
    }

    public int getCrookedKoef() {
        return crookedKoef;
    }

    @Override
    public void setTime(int time) {
        super.setTime(time * 3 * crookedKoef);
    }

    @Override
    public String toString() {
        return "Человек, " +
                "криворукость = " + crookedKoef +
                ", время = " + getTime() + ", детали = " + getCntComponents();
    }
}
