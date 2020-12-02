package homework;

import homework.sort.BubbleSort;
import homework.sort.QuickSort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Person[] people = Person.generateArray(10000);
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(people);
//        QuickSort quickSort = new QuickSort();
//        quickSort.sort(people);
        Arrays.stream(people).forEach(System.out::println);
    }
}
