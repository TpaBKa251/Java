package com.example.algorithm_lab1.lab4;

import java.util.ArrayList;

public class Graph2 {
    public ArrayList<ArrayList<Integer>> graph;

    public void addEdge(ArrayList<ArrayList<Integer> > adj, int u, int v)
    {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
}
