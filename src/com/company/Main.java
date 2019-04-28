package com.company;

public class Main {

    public static void main(String[] args) {
        Reader reader = new Reader();
        reader.read();

        Graph graph = new Graph();
        graph.start(reader.getMatrix());

    }
}
