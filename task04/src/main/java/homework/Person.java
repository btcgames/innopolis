package homework;

import java.util.Objects;
import java.util.Random;

public class Person {
    private Sex sex;
    private int age;
    private String name;

    public Person(Sex sex, int age, String name) {
        this.sex = sex;
        this.age = age;
        this.name = name;
    }

    enum Sex {
        MAN, WOMAN
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0 && age < 100) {
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

    public static Person[] generateArray(int size)  {
        Person[] persons = new Person[size];
        Random randomAge = new Random();
        for (int i = 0; i < persons.length; i++) {
            persons[i] = new Person(getRandomSex(), randomAge.nextInt(101),
                    getRandomName());
        }
        return persons;
    }
    private static Sex getRandomSex()
    {
        Random randomSex = new Random();
        return randomSex.nextInt(2) == 0 ? Sex.MAN : Sex.WOMAN;
    }
    private static String getRandomName(){
        String alphabet = "абвгдежзиклмнопрстуфхцчшщъыьэюя";
        Random randomNameLength = new Random();
        randomNameLength.setSeed(2);
        int nameLength = randomNameLength.nextInt(10);
        Random randomChar = new Random();
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < nameLength; i++) {
            name.append(alphabet.charAt(randomChar.nextInt(31)));
        }
        return name.toString();
    }
}
