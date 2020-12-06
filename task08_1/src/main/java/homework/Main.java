package homework;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException,
            IllegalAccessException, InstantiationException {

        Worker worker = new SomeClass();
        System.out.println("Исходный файл:");
        worker.doWork();

        String newCode = MyUtil.getNewCode();
        File newFile = new File("task08_1\\src\\main\\java\\homework\\SomeClass.java");
        MyUtil.createAndCompile(newFile, newCode);

        worker = MyUtil.loadClass("SomeClass");
        System.out.println("Файл после изменения:");
        worker.doWork();
    }
}
