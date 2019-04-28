package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex {
    private boolean visited;

    private final int valueOfVertex;
    private final Coordinate coordinate;
    private List<Vertex> neighbour;

    public Vertex(int value, Coordinate coordinate) {
        this.coordinate = coordinate;
        this.visited = false;
        valueOfVertex = value;
        neighbour = new ArrayList<>();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getValueOfVertex() {
        return valueOfVertex;
    }

    public void addNeighbour(Vertex vertex) {
        neighbour.add(vertex);
    }


    public List<Vertex> getNeighbour() {
        List<Vertex> copyOfNeighbours = new ArrayList<>();
        copyOfNeighbours.addAll(neighbour);
        return copyOfNeighbours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex vertex = (Vertex) o;
        return isVisited() == vertex.isVisited() &&
                getValueOfVertex() == vertex.getValueOfVertex() &&
                Objects.equals(getCoordinate(), vertex.getCoordinate()) &&
                Objects.equals(getNeighbour(), vertex.getNeighbour());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isVisited(), getValueOfVertex(), getCoordinate(), getNeighbour());
    }
}
