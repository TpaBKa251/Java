package com.example.algorithm_lab1.lab6.dataStructures;

import java.util.*;

// Какой же это пиздец
// Честно, я в ахуе
public class LinkedList<T> {

    // Поля и конструктор
    private int size = 0;
    private Element<T> first;
    private Element<T> last;



    // пусть пустой будет
    public LinkedList(){
    }



//region<Методы пользователя: add(), get(), set(), remove()>

    // Геты
    public T getFirst(){
        Element<T> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.data;
    }

    public T getLast(){
        Element<T> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return l.data;
    }

    public T get(int index){
        checkIndex(index);
        return element(index).data;
    }

    public int size(){
        return size;
    }



    // Ремувы
    public T removeFirst() {
        Element<T> f = first;
        T element = f.data;

        if (f == null)
            throw new NoSuchElementException();

        Element<T> next = f.nextElement;
        f.data = null;
        f.nextElement = null;
        first = next;

        if (next == null)
            last = null;
        else
            next.previousElement = null;

        size--;

        return element;
    }

    public T removeLast(){
        Element<T> l = last;
        T element = l.data;

        if (l == null)
            throw new NoSuchElementException();

        Element<T> prev = l.previousElement;
        l.data = null;
        l.previousElement = null;
        last = prev;

        if (prev == null)
            first = null;
        else
            prev.nextElement = null;

        size--;

        return element;
    }

    public boolean remove(Object o){
        if (o == null){
            for (Element<T> x = first; x != null; x = x.nextElement){
                if (x.data == null){
                    unlink(x);
                    return true;
                }
            }
        }
        else {
            for (Element<T> x = first; x != null; x = x.nextElement){
                if (o.equals(x.data)){
                    unlink(x);
                    return true;
                }
            }
        }

        return false;
    }

    public T removeByIndex(int index){
        checkIndex(index);
        return unlink(element(index));
    }



    // Эды (и один сет)
    public void addFirst(T t){
        Element<T> f = first;
        Element<T> newElement = new Element<>(t, null, f);
        first = newElement;

        if (f == null){
            last = newElement;
        }
        else {
            f.previousElement = newElement;
        }

        size++;
    }

    public void add(T t){
        linkLast(t);
    }

    public void add(int index, T element){
        if (index >= 0 && index < size) {
            if (index == size)
                linkLast(element);
            else
                linkBefore(element, element(index));
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void set(int index, T element){
        checkIndex(index);
        Element<T> x = element(index);
        x.data = element;
    }


    // Очистить
    public void clear(){
        for (Element<T> x = first; x != null; ) {
            Element<T> next = x.nextElement;
            x.data = null;
            x.nextElement = null;
            x.previousElement = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }
//endregion



//region<Методы, недоступные пользователю>

    // Методы добавления элемента на 0 и любую позиции
    private void linkLast(T t){
        Element<T> l = last;
        Element<T> newElement = new Element<>(t, l, null);
        last = newElement;

        if (l == null)
            first = newElement;
        else
            l.nextElement = newElement;

        size++;
    }

    private void linkBefore(T e, Element<T> succ) {
        Element<T> pred = succ.previousElement;
        Element<T> newElement = new Element<>(e, pred, succ);
        succ.previousElement = newElement;

        if (pred == null)
            first = newElement;
        else
            pred.nextElement = newElement;

        size++;
    }



    // Метод удаления любого элемента
    private T unlink(Element<T> x){
        T element = x.data;
        Element<T> next = x.nextElement;
        Element<T> prev = x.previousElement;

        if (prev == null){
            first = next;
        } else {
            prev.nextElement = next;
            x.previousElement = null;
        }

        if (next == null){
            last = prev;
        } else {
            next.previousElement = prev;
            x.nextElement = null;
        }

        x.data = null;
        size--;
        return element;
    }



    // Метод создающий элемент между первым и последним
    private Element<T> element(int index){
        if (index < (size / 2)) {
            Element<T> x = first;

            for (int i = 0; i < index; i++){
                x = x.nextElement;
            }

            return x;
        }
        else {
            Element<T> x = last;

            for (int i = size - 1; i > index; i--) {
                x = x.previousElement;
            }

            return x;
        }
    }

    // Метод для проверки индекса
    private void checkIndex(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
    }
//endregion



    // Класс элемента (узла/нода)
    private static class Element<T> {
        Element nextElement;
        Element previousElement;
        T data;

        public Element (T data, Element<T> previousElement, Element<T> nextElement){
            this.data = data;
            this.previousElement = previousElement;
            this.nextElement = nextElement;
        }
    }



//region<Оверайды (equals() и hashCode() переписать)>
    @Override
    public String toString() {
        String s = "Список: ";
        Element<T> x = first;
        for (int i = 0; i < size; i++) {
            s += x.data.toString();
            x = x.nextElement;

            if (i != size-1){
                s += ", ";
            }
        }

        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedList<?> that = (LinkedList<?>) o;
        return size == that.size && Objects.equals(first, that.first) && Objects.equals(last, that.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, first, last);
    }

    //endregion
}

