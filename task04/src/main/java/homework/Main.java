package homework;

import homework.sort.BubbleSort;
import homework.sort.QuickSort;
import homework.sort.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person[] people = PersonUtils.generateArray(10);
        PersonUtils.valid(people);

        List<Sort> sorts = new ArrayList<>();
        sorts.add(new BubbleSort());
        sorts.add(new QuickSort());

        for(int i = 0; i < sorts.size(); i++) {
            long begin = System.currentTimeMillis();
            sorts.get(i).sort(people);
            System.out.println("Время работы(мс): " + (System.currentTimeMillis() - begin));
            Arrays.stream(people).forEach(System.out::println);
        }
    }
}
