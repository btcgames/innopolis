package homework;

import java.lang.reflect.Field;
import java.util.*;

public class CleanAndOut {

    void cleanup(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) {
        Class<?> clazz = object.getClass();

        if (isMap(clazz)) {
            mapCleanOutput(object, fieldsToCleanup, fieldsToOutput);
        } else {
            fieldCleanUp(object, fieldsToCleanup, clazz);
            fieldOutput(object, fieldsToOutput, clazz);
        }
    }

    boolean isMap(Class<?> clazz) {
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            if (Objects.equals(anInterface.getName(), "Map")) {
                return true;
            }
        }
        return false;
    }

    private void mapCleanOutput(Object object, Set<String> fieldsToCleanUp, Set<String> fieldsToOutput) {
        Map<?, ?> map = (Map<?, ?>) object;

        Iterator<String> cleanIter = fieldsToCleanUp.iterator();
        Iterator<String> outIter = fieldsToOutput.iterator();

        while (cleanIter.hasNext()) {
            String key = cleanIter.next();
            if(map.containsKey(key)) {
                map.remove(key);
            } else {
                throw new IllegalArgumentException("Нет нужных полей");
            }
        }

        while (outIter.hasNext()) {
            String key = outIter.next();
            if(map.containsKey(key)) {
                System.out.println(map.get(key));
            } else {
                throw new IllegalArgumentException("Нет нужных полей");
            }
        }
    }

    private void fieldCleanUp(Object object, Set<String> fieldsToCleanUp, Class<?> clazz) {
        Iterator<String> iterator = fieldsToCleanUp.iterator();

        while (iterator.hasNext()) {
            try {
                Field field = clazz.getDeclaredField(iterator.next());
                if(!field.isAccessible()) {
                    field.setAccessible(true);
                }
                setValue(object, field);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalArgumentException("Нет нужных полей");
            }
        }
    }

    private void setValue(Object object, Field field) throws IllegalAccessException {
        if (field.getType().isPrimitive()) {
            if (field.getType().getName().equals("boolean")) {
                field.setBoolean(object, false);
            } else if (field.getType().getName().equals("char")) {
                field.setChar(object, '\u0000');
            } else {
                field.set(object, 0);
            }
        } else {
            field.set(object, null);
        }
    }

    private void fieldOutput(Object object, Set<String> fieldsToOutput, Class<?> clazz) {
        Iterator<String> iterator = fieldsToOutput.iterator();

        while (iterator.hasNext()) {
            try {
                Field field = clazz.getDeclaredField(iterator.next());
                if (field.getType().isPrimitive()) {
                    System.out.println(String.valueOf(field.get(object)));
                } else {
                    System.out.println(field.get(object).toString());
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalArgumentException("Нет нужных полей");
            }
        }
    }
}
