package homework.sort;

import homework.NameAgeException;
import homework.Person;
import homework.comparators.ByAgeComparator;
import homework.comparators.ByAlphabetComparator;
import homework.comparators.BySexComparator;

import java.util.Comparator;
import java.util.Objects;

public class QuickSort implements Sort {

    public void sort(Person[] person) {
        long begin = System.currentTimeMillis();
        Comparator<Person> comparator = new BySexComparator().thenComparing(new ByAgeComparator()).thenComparing(new ByAlphabetComparator());
        quickSort(person, 0, person.length - 1, comparator);
        long end = System.currentTimeMillis();
        System.out.println("Время работы(мс): " + (end - begin));
    }

    public static void quickSort(Person[] array, int left, int right, Comparator<Person> comparator) {
        if (left < right) {
            int pivotIndex = partition(array, left, right, comparator);
            quickSort(array, left, pivotIndex - 1, comparator);
            quickSort(array, pivotIndex + 1, right, comparator);
        }
    }

    private static int partition(Person[] array, int left, int right, Comparator<Person> comparator) {
        Person pivot = array[right];
        int partitionIndex = left;

        for (int i = left; i < right; i++) {

            if(Objects.equals(array[i].getName(), pivot.getName()) &&
                    array[i].getAge() == pivot.getAge()) {
                try {
                    throw new NameAgeException("Имена людей и возраст совпадают");
                } catch (NameAgeException e) {
                    e.printStackTrace();
                }
            }

            if (comparator.compare(array[i], pivot) < 0) {
                swap(array, i, partitionIndex);
                partitionIndex++;
            }
        }

        swap(array, partitionIndex, right);

        return partitionIndex;
    }

    private static void swap(Person[] array, int i, int j) {
        Person temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
