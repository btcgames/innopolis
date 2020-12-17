package homework;

import java.util.Random;

public class Field {

    public static final char EMPTY = 'O';
    public static final char ELEM = '1';

    private char[][] matrix;

    public Field(int size) {
        matrix = new char[size][size];
        initEmpty();
    }

    public Field(char[][] matrix) {
        this.matrix = matrix;
    }

    public void initRandom() {
        Random random = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = random.nextBoolean() ? ELEM : EMPTY;
            }
        }
    }

    public void initEmpty() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = EMPTY;
            }
        }
    }

    public void print() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getSize() {
        return matrix.length;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }
}
