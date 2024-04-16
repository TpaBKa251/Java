package com.example.algorithm_lab1.lab6.dataStructures;

import java.util.Arrays;
import java.util.Objects;

public class Stack<T> {
    private T[] stack;
    private int topIndex;

    public Stack (){
        topIndex = -1;
        stack = (T[]) new Object[10_000_000];
    }

    public T getTop() {
        if (topIndex == -1){
            return null;
        }
        return stack[topIndex];
    }
    public int length(){
        return topIndex+1;
    }

    public void push(T element){
        if (topIndex < stack.length -1){
            topIndex++;
            stack[topIndex] = element;
        }
        else {
            throw new RuntimeException("Стек заполнен");
        }
    }

    public T pop(){
        if (topIndex == -1){
            return null;
        }
        else {
            topIndex--;
            T e = stack[topIndex+1];
            stack[topIndex+1] = null;

            return e;
        }
    }

    public void clear(){
        topIndex = -1;
        Arrays.fill(stack, null);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < topIndex+1; i++) {
            s += stack[i].toString();
            if (i != topIndex){
                s += ", ";
            }
        }

        return "Стек: " + s +
                ", пик = " + stack[topIndex];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stack<?> stack1 = (Stack<?>) o;
        return topIndex == stack1.topIndex && Arrays.equals(stack, stack1.stack);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(topIndex);
        result = 31 * result + Arrays.hashCode(stack);
        return result;
    }
}
