package com.company;

import java.util.*;

public class Graph {

    private Map<Coordinate, Vertex> vertexMap;
    private List<Vertex> objects;
    private int lengthOfRows;
    private int lengthOfColumns;

    public Graph() {
        vertexMap = new HashMap<>();
        objects = new ArrayList<>();
    }

    public void start(int[][] matrix) {
        buildGraph(matrix);
        List<Vertex> path = searchPath();

        output(path);
    }

    private void addVertex(Coordinate coordinate, Vertex vertex) {
        vertexMap.put(coordinate, vertex);
    }


    private void buildGraph(int[][] matrix) {
        lengthOfRows = matrix.length;
        lengthOfColumns = matrix[0].length;
        for (int i = 0; i < lengthOfRows; i++) {
            for (int j = 0; j < lengthOfColumns; j++) {
                int value = matrix[i][j];
                Coordinate coordinate = new Coordinate(i,j);
                Vertex vertex = new Vertex(value, coordinate);
                addVertex(coordinate, vertex);
            }
        }

        makeEdges(matrix);
    }

    private void makeEdges(int[][] matrix) {
        for (int i = 0; i < lengthOfRows; i++) {
            for (int j = 0; j < lengthOfColumns; j++) {
                int value = matrix[i][j];
                Vertex vertex = vertexMap.get(new Coordinate(i, j));

                boolean north = false;
                boolean east = false;
                boolean south = false;
                boolean west = false;

                if (value >= 16) {
                    objects.add(vertex);
                    value -= 16;
                }
                if (value >= 8) {
                    west = true;
                    value -= 8;
                }
                if (value >= 4) {
                    south = true;
                    value -= 4;
                }
                if (value >= 2) {
                    east = true;
                    value -= 2;
                }
                if (value >= 1) {
                    north = true;
                }

                if (!west && j >= 1) {
                    vertex.addNeighbour(vertexMap.get(new Coordinate(i, j - 1)));
                }
                if (!north && i >= 1) {
                    vertex.addNeighbour(vertexMap.get(new Coordinate(i - 1, j)));
                }
                if (!south && i < lengthOfRows - 1) {
                    vertex.addNeighbour(vertexMap.get(new Coordinate(i + 1, j)));
                }
                if (!east && j < lengthOfColumns - 1) {
                    vertex.addNeighbour(vertexMap.get(new Coordinate(i, j + 1)));
                }
            }
        }
    }

    private List<Vertex> searchPath() {
        Stack<Vertex> path = new Stack<>();
        Vertex actualPosition = vertexMap.get(new Coordinate(0, 0));
        List<Vertex> visited = new ArrayList<>();

        for (int i = 0; i < objects.size(); i++) {
            while (actualPosition != objects.get(i)) {
                //actualPosition.setVisited(true);
                visited.add(actualPosition);
                List<Vertex> neighbours = actualPosition.getNeighbour();
                neighbours.removeAll(visited);

                if (!neighbours.isEmpty()) {
                    Vertex child = neighbours.get(0);
                    path.push(actualPosition);
                    actualPosition = child;
                } else {
                    actualPosition = path.pop();
                }
            }
            visited.clear();
        }
        visited.clear();

        Vertex endPosition = vertexMap.get(new Coordinate(lengthOfColumns - 1, lengthOfRows - 1));

        while (actualPosition != endPosition) {
            //actualPosition.setVisited(true);
            visited.add(actualPosition);
            List<Vertex> neighbours = actualPosition.getNeighbour();
            neighbours.removeAll(visited);

            if (!neighbours.isEmpty()) {
                Vertex child = neighbours.get(0);
                path.push(actualPosition);
                actualPosition = child;
            } else {
                actualPosition = path.pop();
            }
        }
        path.push(actualPosition);

        List<Vertex> resultPath = new ArrayList<>();
        resultPath.addAll(path);
        resultPath.remove(0);

        return resultPath;
    }

    private void output(List<Vertex> path) {
        for(int i = 0; i < path.size(); i++) {
            System.out.println(path.get(i).getCoordinate().getX() + " " + path.get(i).getCoordinate().getY());
            if(objects.contains(path.get(i))) {
                System.out.println("felvesz");
                objects.remove(path.get(i));
            }
        }
    }

}
