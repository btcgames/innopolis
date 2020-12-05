package homework;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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

        fieldsToCleanUp.forEach(key -> {
            if(map.containsKey(key)) {
                map.remove(key);
            } else {
                throw new IllegalArgumentException("Нет нужных полей");
            }
        });

        fieldsToOutput.stream().map( key -> {
            if (map.containsKey(key)) {
                return map.get(key);
            } else {
                throw new IllegalArgumentException("Нет нужных полей");
            }
        }).forEach(System.out::println);
    }

    private void fieldCleanUp(Object object, Set<String> fieldsToCleanUp, Class<?> clazz) {
        fieldsToCleanUp.forEach((el) -> {
            try {
                Field field = clazz.getDeclaredField(el);
                field.setAccessible(true);
                setValue(object, field);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalArgumentException("Нет нужных полей");
            }
        });
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
        fieldsToOutput.stream().map(el -> {
            try {
                Field field = clazz.getDeclaredField(el);
                if (field.getType().isPrimitive()) {
                    return String.valueOf(field.get(object));
                } else {
                    if(field.get(object) == null) {
                        return field.get(object);
                    } else {
                        return field.get(object).toString();
                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalArgumentException("Нет нужных полей");
            }
        }).forEach(System.out::println);
    }
}