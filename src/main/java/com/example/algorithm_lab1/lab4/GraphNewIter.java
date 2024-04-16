package com.example.algorithm_lab1.lab4;

import java.util.Random;

public class GraphNewIter {
    // Лучшим случаем будет "прямой" граф (в виде паровозика).
    // То есть все вершины соединены одним ребром со следующей и/или предыдущей
    // Сложность будет O(2n - 1)
    public static Graph<Integer> bestGraph(int n){
        Graph<Integer> graph = new Graph<>();
        for (int i = 0; i < n-1; i++) {
            graph.addEdge(i, i+1, true);
        }

        return graph;
    }

    // В рандомном случае мы создаем наш паровозик и добавляем ещё рандомных ребер
    // в количестве от 0 до максимально возможного, вычтя уже созданные (n*(n-1)/2 - (n-1))
    public static Graph<Integer> randomGraph(int n){
        Graph<Integer> graph = new Graph<>();
        int source;
        int destination;
        Random random = new Random();

        for (int i = 0; i < n-1; i++) {
            graph.addEdge(i, i+1, true);
        }

        int m = random.nextInt(0, n*(n-1)/2 - (n-1));

        for (int i = 0; i < m; i++) {
            do{
                source = random.nextInt(0, n);
                destination = random.nextInt(0, n);
            }
            while (graph.hasEdge(source, destination) || source == destination);

            graph.addEdge(source, destination, true);
        }

        return graph;
    }

    // в худшем случае создаем полносвязный граф
    // сложность будет O(n*(n+m)), где n - число вершин, m - ребер
    public static Graph<Integer> worstGraph(int n){
        Graph<Integer> graph = new Graph<>();
        int source;
        int destination;
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            graph.addVertex(i);
        }

        for (int i = 0; i < n; i++) {
            if (i == n-2){
                break;
            }
            for (int j = 0; j < n; j++) {
                if (!graph.hasEdge(i, j) && i != j) {
                    graph.addEdge(i, j, true);
                }
            }
        }

        return graph;
    }
}
