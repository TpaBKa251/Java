package com.example.algorithm_lab1.lab6.dataStructures;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Queue<T> implements Serializable {
    // Поля
    private T[] queue;
    private int headIndex;
    private int tailIndex;
    private int length;

    public Queue (){
        headIndex = 0;
        length = 0;
        tailIndex = -1;
        queue = (T[]) new Object[1_000_000];
    }

    public T getHead() {
        if (tailIndex == -1){
            return null;
        }
        else {
            return queue[headIndex];
        }
    }
    public int length(){
        return length;
    }

    public void enqueue(T element){
        if (tailIndex < queue.length-1){

            tailIndex++;
            queue[tailIndex] = element;

            length++;
        }
        else if (tailIndex == queue.length-1 && headIndex != 0) {

            tailIndex = (tailIndex+1) % queue.length;
            queue[tailIndex] = element;

            length++;
        }
        else {
            throw new RuntimeException("Очередь заполнена");
        }
    }

    public T dequeue(){
        if (length == 0){
            return null;
        }
        else if (headIndex < queue.length - 1) {
            headIndex++;
            length--;

            return queue[headIndex-1];
        }
        else {
            int i = headIndex;
            length--;

            headIndex = (headIndex+1) % queue.length;

            return queue[i];
        }
    }

    public void clear(){
        headIndex = 0;
        length = 0;
        tailIndex = -1;
        queue = (T[]) new Object[10_000_000];
    }

    public T get(int index){
        if (headIndex <= tailIndex && index < queue.length) {
            return queue[index + headIndex];
        }
        else {
            if (index >= headIndex && index < queue.length){
                return queue[index + headIndex];
            }
            else {
                return queue[index];
            }
            /*for (int i = headIndex; i < queue.length; i++) {
                s += queue[i].toString() + ", ";
            }

            for (int i = 0; i < tailIndex + 1; i++) {
                s += queue[i].toString();

                if (i != tailIndex) {
                    s += ", ";
                }
            }*/
        }
    }

    @Override
    public String toString() {
        String s = "";

        if (headIndex <= tailIndex) {

            for (int i = headIndex; i < tailIndex + 1; i++) {
                s += queue[i].toString();

                if (i != tailIndex) {
                    s += ", ";
                }
            }
        }
        else {

            for (int i = headIndex; i < queue.length; i++) {
                s += queue[i].toString() + ", ";
            }

            for (int i = 0; i < tailIndex + 1; i++) {
                s += queue[i].toString();

                if (i != tailIndex) {
                    s += ", ";
                }
            }
        }

        return "Очередь: " + s;
                /*", голова = " + queue[headIndex];*/
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Queue<?> queue1 = (Queue<?>) o;
        return headIndex == queue1.headIndex && tailIndex == queue1.tailIndex && Arrays.equals(queue, queue1.queue);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(headIndex, tailIndex);
        result = 31 * result + Arrays.hashCode(queue);
        return result;
    }
}
