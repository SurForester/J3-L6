package com.geekbrains.hw;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SixthExample {

    public static void main(String[] args) {
        // не получились тесты :(
        // вариант 1
        int[] numbers = new int[] {1, 2, 4, 4, 2, 3, 4, 1, 7};
        int[] result = getArrayFromLastFour(numbers);
        System.out.println(Arrays.toString(numbers) + " -> " + Arrays.toString(result));
        // вариант 2
        numbers = new int[] {1, 2, 4, 4, 2, 3, 1, 7};
        result = getArrayFromLastFour(numbers);
        System.out.println(Arrays.toString(numbers) + " -> " + Arrays.toString(result));
        // вариант 2
        numbers = new int[] {1, 2, 4, 4, 2, 3, 5, 1, 7, 4};
        result = getArrayFromLastFour(numbers);
        System.out.println(Arrays.toString(numbers) + " -> " + Arrays.toString(result));

        // массивы чисел 1 и 4
        //checkArrayFor14(List.of(1, 1, 1, 4, 4, 1, 4, 4));
        //checkArrayFor14(List.of(1, 1, 1, 1, 1, 1));
        //checkArrayFor14(List.of(4, 4, 4, 4));
        //checkArrayFor14(List.of(1, 4, 4, 1, 1, 4, 3));
    }

    public static int[] getArrayFromLastFour(int[] numbers) {
        if (numbers == null)
            throw new RuntimeException();
        int indexOfLast4 = 0;
        for (int i = 0; i < numbers.length; i++)
            if (numbers[i] == 4)
                indexOfLast4 = i;
        if (numbers[indexOfLast4] != 4)
            throw new RuntimeException();

        return Arrays.copyOfRange(numbers, indexOfLast4 + 1, numbers.length);
    }

    public static boolean checkArrayFor14(List<Integer> numbers) {
        List<Integer> result = numbers.stream()
                .filter(integer -> integer == 1 || integer == 4)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        boolean resultCheck = (result.size() == 2 && result.get(0).intValue() == 1
                && result.get(1).intValue() == 4);
        System.out.println(resultCheck + " -> " + numbers);
        return resultCheck;
    }
}
