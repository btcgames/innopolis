package homework;

public class Generator {

    private Field field;

    public Generator() {
        field = new Field(30);
        field.init();
    }

    public void generateWithThreads(int steps) throws InterruptedException {
        System.out.println("Many threads");
        getField().print();
        for (int i = 0; i < steps; i++) {
            setAndPrintMatrix();
        }
    }

    private void setAndPrintMatrix() throws InterruptedException {
        char[][] newMatrix = new Field(field.getSIZE()).getMatrix();
        for (int row = 0; row < field.getSIZE(); row++) {
            newMatrix[row] = getRow(row);
        }
        field.setMatrix(newMatrix);
        field.print();
    }

    private char[] getRow(int row) throws InterruptedException {
        char[] array = new char[field.getSIZE()];
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (new Object()) {
                    for (int col = 0; col < field.getSIZE(); col++) {
                        if (isAlive(field.getMatrix(), row, col)) {
                            array[col] = Field.ELEM;
                        } else {
                            array[col] = Field.EMPTY;
                        }
                    }
                }
            }
        });
        thread.start();
        thread.join();
        return array;
    }

    public void generate(int steps) {
        System.out.println("One thread");
        getField().print();
        for (int i = 0; i < steps; i++) {
            generate();
            getField().print();
        }
    }

    private void generate() {
        char[][] newMatrix = new Field(field.getSIZE()).getMatrix();
        for (int i = 0; i < field.getSIZE(); i++) {
            for (int j = 0; j < field.getSIZE(); j++) {
                if (isAlive(field.getMatrix(), i, j)) {
                    newMatrix[i][j] = Field.ELEM;
                }
            }
        }
        field.setMatrix(newMatrix);
    }

    private boolean isAlive(char[][] matrix, int i, int j) {
        int count = 0;
        int size = matrix.length;

        if (matrix[i][j - 1 < 0 ? size - 1 : j - 1] == Field.ELEM) {
            count++;
        }

        if (matrix[i - 1 < 0 ? size - 1 : i - 1][j - 1 < 0 ? size - 1 : j - 1] == Field.ELEM) {
            count++;
        }

        if (matrix[i - 1 < 0 ? size - 1 : i - 1][j] == Field.ELEM) {
            count++;
        }

        if (matrix[i][j + 1 == size ? 0 : j + 1] == Field.ELEM) {
            count++;
        }

        if (matrix[i + 1 == size ? 0 : i + 1][j + 1 == size ? 0 : j + 1] == Field.ELEM) {
            count++;
        }

        if (matrix[i + 1 == size ? 0 : i + 1][j] == Field.ELEM) {
            count++;
        }

        return matrix[i][j] == Field.ELEM && (count == 2 || count == 3) ||
                matrix[i][j] == Field.EMPTY && count == 3;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
