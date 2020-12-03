package homework;

import java.util.Objects;
import java.util.Random;

public class Person {
    private Sex sex;
    private int age;
    private String name;

    public Person(Sex sex, int age, String name) {
        setSex(sex);
        setAge(age);
        this.name = name;
    }

    enum Sex {
        MAN, WOMAN
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 100) {
            throw new RuntimeException("Возраст должен быть от 0 до 100");
        }
        this.age = age;
    }

    public String getSex() {
        return sex.name();
    }

    public void setSex(Sex sex) {
        if(Objects.equals(sex, Sex.MAN) || Objects.equals(sex, Sex.WOMAN)) {
            this.sex = sex;
        } else {
            throw new RuntimeException("Пол должен быть MAN или WOMAN");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "sex=" + sex +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
