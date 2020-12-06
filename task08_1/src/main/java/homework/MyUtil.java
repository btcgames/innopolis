package homework;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class MyUtil {
    static String readFromConsole() {
        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line = br.readLine();
            while (!line.isEmpty()) {
                result.append(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    static String readFromClass() {
        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new FileReader("task08_1\\src\\main\\java\\homework\\SomeClass.java"))) {
            String line = br.readLine();
            while (br.ready()) {
                result.append(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    static String getNewCode() {
        String someClassCode = MyUtil.readFromClass();
        String methodCode = MyUtil.readFromConsole();
        return someClassCode.substring(0, (someClassCode.indexOf("doWork()") + 10))
                + methodCode + "}\n}\n";
    }

    static void createAndCompile(File file, String code) throws IOException {
        file.createNewFile();
        Files.write(file.toPath(), code.getBytes(StandardCharsets.UTF_8));

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null,null, file.getPath());
    }

    static Worker loadClass(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myClassLoader = new MyClassLoader();
        Class<?> loadCLass = myClassLoader.loadClass(name);
        return (Worker) loadCLass.newInstance();
    }
}
