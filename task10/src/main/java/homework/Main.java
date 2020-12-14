package homework;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        String in = args[0];
        String out = args[1];
        int steps = Integer.parseInt(args[2]);

        Generator generator = new Generator();
        char[][] matrix = MyUtil.readFile("task10\\src\\main\\java\\homework\\" + in);
        Field field = new Field(matrix);
        generator.setField(field);

        long start = System.currentTimeMillis();
        generator.generate(steps);
        long timeOne = System.currentTimeMillis() - start;

        long start2 = System.currentTimeMillis();
        generator.generateWithThreads(steps);
        long timeMany = System.currentTimeMillis() - start2;

        System.out.println("One thread: " + timeOne + "ms");
        System.out.println("Many threads: " + timeMany + "ms");

        MyUtil.writeFile("task10\\src\\main\\java\\homework\\" + out, generator.getField().getMatrix());
    }
}
