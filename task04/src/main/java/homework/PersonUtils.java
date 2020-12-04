package homework;

import java.util.Objects;
import java.util.Random;

public class PersonUtils {
    private PersonUtils() {
    }

    public static void valid(Person[] people) {
        for(int i = 0; i < people.length; i++) {
            for(int j = i + 1; j < people.length; j++) {
                if(Objects.equals(people[i].getName(), people[j].getName()) &&
                        people[i].getAge() == people[j].getAge()) {
                    throw new NameAgeException("Имена и возраст людей совпадают");
                }
            }
        }
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

    private static Person.Sex getRandomSex() {
        Random randomSex = new Random();
        return randomSex.nextInt(2) == 0 ? Person.Sex.MAN : Person.Sex.WOMAN;
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
