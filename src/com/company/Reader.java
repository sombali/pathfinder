package com.company;

import java.util.*;

public class Reader {
    private Scanner scanner;

    private List<String> rowsOfMatrix = new ArrayList<>();

    private int[][] matrix;

    private int rows;
    private int lengthOfColumns;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getLengthOfColumns() {
        return lengthOfColumns;
    }

    public void setLengthOfColumns(int lengthOfColumns) {
        this.lengthOfColumns = lengthOfColumns;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    private int numberOfObjects = 0;

    private static final int ROW_OF_OBJECT = 1;

    public Reader() {
        scanner = new Scanner(System.in);
    }

    public void read() {
        String nextLine;
        while (scanner.hasNextLine()) {
            nextLine = scanner.nextLine();
            if(nextLine.trim().length() == 1) break;
            rowsOfMatrix.add(nextLine);
            //if (nextLine.isEmpty()) {
            //    initMatrix();
            //    return;
            //}
        }
        scanner.close();
        initMatrix();
    }

    private void initMatrix() {

        int lengthOfColumns = rowsOfMatrix.get(0).split(" ").length;
        int lengthOfRows = rowsOfMatrix.size();
        matrix = new int[lengthOfRows][lengthOfColumns];

        addElementsToMatrix(lengthOfRows);
    }

    private void addElementsToMatrix(int lengthOfRows) {
        for (int i = 0; i < lengthOfRows; i++) {
            String[] splittedString = rowsOfMatrix.get(i).split(" ");
            if (splittedString.length != 1) {
                for (int j = 0; j < splittedString.length; j++) {
                    matrix[i][j] = Integer.parseInt(splittedString[j]);
                }
            } else {
                numberOfObjects = Integer.parseInt(splittedString[0]);
            }
        }
    }
}
