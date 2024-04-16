package com.example.algorithm_lab1.lab4;

import java.util.*;

public class Graph<T> {

    //Сам граф. Ключ это вершина, значение - список смежных вершин
    public Map<T, List<T>> map = new HashMap<>();

    //Метод добавления вершины
    public void addVertex(T vertex){
        map.put(vertex, new LinkedList<T>());
    }

    //Метод добавления ребра, можно указать будет оно однонаправленным или нет
    public void addEdge(T source, T destination, boolean bidirect){
        if (!map.containsKey(source)){
            addVertex(source);
        }
        if (!map.containsKey(destination))
            addVertex(destination);

        map.get(source).add(destination);

        if (bidirect) {
            map.get(destination).add(source);
        }
    }

    //Возвращает число вершин
    public int getVertexCount()
    {
        return map.keySet().size();
    }

    //Возвращает число ребер
    public int getEdgesCount(boolean bidirect)
    {
        int count = 0;
        for (T vertex : map.keySet()) {
            count += map.get(vertex).size();
        }
        if (bidirect) {
            count /= 2;
        }

        return count;
    }

    //Проверяет есть ли вершина в графе
    public boolean hasVertex(T vertex)
    {
        if (map.containsKey(vertex)) {
            return true;
        }
        else {
            return false;
        }
    }

    //Проверяет есть ли ребро в графе
    public boolean hasEdge(T source, T destination)
    {
        if (map.get(source).contains(destination)) {
            return true;
        }
        else {
            return false;
        }
    }

    public Set<T> getVertexes(){
        return map.keySet();
    }

    public Collection<List<T>> getEdges(){
        return map.values();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (T vertex : map.keySet()) {
            builder.append(vertex.toString() + ": ");
            for (T neighbor : map.get(vertex)) {
                builder.append(neighbor.toString() + " ");
            }
            builder.append("\n");
        }

        return (builder.toString());
    }
}
