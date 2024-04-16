package com.example.algorithm_lab1.lab4;

import java.util.*;

public class GraphAlg {
    public static long numCalc;
    public static int timer = 0;

    //Модернизированный алгоритм обхода в глубину для нахождения точек сочленения 
    static void DFSAdditional(Graph<Integer> graph, int u, boolean[] visited, int[] disc, int[] low, int parent, boolean[] isAP)
    {
        numCalc += 9;
        // количество детей вершины
        int children = 0;

        // отмечаем текущую вершину как посещенную
        visited[u] = true;

        disc[u] = timer++;
        low[u] = disc[u];

        for (Integer v : graph.map.get(u)) {
            numCalc += 7;
            if (!visited[v]) {
                numCalc += 11;
                children++;
                DFSAdditional(graph, v, visited, disc, low, u, isAP);

                low[u] = Math.min(low[u], low[v]);

                if (parent != -1 && low[v] >= disc[u]) {
                    numCalc += 2;
                    isAP[u] = true;
                }
            }

            else if (v != parent) {
                numCalc += 5;
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        if (parent == -1 && children > 1) {
            numCalc += 2;
            isAP[u] = true;
        }
    }

    public static List<Integer> DFS(Graph<Integer> graph, int vertexCnt)
    {
        numCalc = 0;
        numCalc += 7;
        List<Integer> answer = new ArrayList<>();
        boolean[] visited = new boolean[vertexCnt]; // массив посещенных вершин
        int[] disc = new int[vertexCnt]; // массив времени обнаружения вершины
        int[] low = new int[vertexCnt]; // минимальное время обнаружения
        boolean[] isAP = new boolean[vertexCnt]; // массив флагов, обозначающих является ли вершина точкой сочленения
        timer = 0;
        int par = -1;

        // Запускаем для каждой непосещенной вершины алгоритм обхода в глубину
        for (int u = 0; u < vertexCnt; u++) {
            numCalc += 4;
            if (!visited[u]) {
                DFSAdditional(graph, u, visited, disc, low, par, isAP);
            }
        }

        for (int u = 0; u < vertexCnt; u++) {
            numCalc += 4;
            if (isAP[u]) {
                //System.out.print(u + " ");
                answer.add(u);
            }
        }
        //System.out.println();
        return answer;
    }
}
