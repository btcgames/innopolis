package homework.sort;

import homework.NameAgeException;
import homework.Person;
import homework.comparators.ByAgeComparator;
import homework.comparators.ByAlphabetComparator;
import homework.comparators.BySexComparator;

import java.util.Comparator;
import java.util.Objects;

public class BubbleSort implements Sort {

    public void sort(Person[] person) {
        Comparator<Person> comparator = new BySexComparator().thenComparing(new ByAgeComparator()).thenComparing(new ByAlphabetComparator());

        long begin = System.currentTimeMillis();
        for(int i = 0; i < person.length; i++) {
            for(int j = 0; j < person.length; j++) {
                if(comparator.compare(person[i], person[j]) < 0) {

                    if(Objects.equals(person[i].getName(), person[j].getName()) &&
                    person[i].getAge() == person[j].getAge()) {
                        try {
                            throw new NameAgeException("Имена людей и возраст совпадают");
                        } catch (NameAgeException e) {
                            e.printStackTrace();
                        }
                    }

                    Person temp = person[i];
                    person[i] = person[j];
                    person[j] = temp;
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Время работы(мс): " + (end - begin));
    }
}
