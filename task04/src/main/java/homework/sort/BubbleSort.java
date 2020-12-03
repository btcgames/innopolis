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

        for(int i = 0; i < person.length; i++) {
            for(int j = 0; j < person.length; j++) {
                if(comparator.compare(person[i], person[j]) < 0) {

                    Person temp = person[i];
                    person[i] = person[j];
                    person[j] = temp;
                }
            }
        }
    }
}
