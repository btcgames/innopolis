package homework;

import java.io.*;

public class MyUtil {

    public static char[][] readFile(String name) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(name)));
        String str = bufferedReader.readLine();
        int num = Integer.parseInt(str.trim());
        char[][] matrix = new char[num][num];
        for (int i = 0; i < matrix.length; i++) {
            String[] els = bufferedReader.readLine().trim().split(" ");
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = els[j].charAt(0);
            }
        }
        bufferedReader.close();
        return matrix;
    }

    public static void writeFile(String name, char[][] matrix) throws IOException {
        FileWriter fileWriter = new FileWriter(new File(name));
        fileWriter.write(matrix.length + "\n");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                fileWriter.write(matrix[i][j] + " ");
            }
            fileWriter.write("\n");
        }
        fileWriter.close();
    }
}
