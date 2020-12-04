package homework.comparators;

import homework.Person;

import java.util.Comparator;

public class BySexComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getSex().compareTo(o2.getSex());
    }
}
