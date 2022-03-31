package com.geekbrains.hw;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

public class TestExample {

    private SixthExample sixthExample;

    @BeforeEach
    public void init() {
        sixthExample = new SixthExample();
    }

    @Test
    public void TestArr1() {
        int[] numbers = new int[] {1, 2, 4, 4, 2, 3, 4, 1, 7};
        int[] result = sixthExample.getArrayFromLastFour(numbers);
        Assertions.assertEquals(result, numbers);
    }

    @Test
    public void Test1() {
        Assertions.assertTrue(sixthExample.checkArrayFor14(List.of(1, 1, 1, 4, 4, 1, 4, 4)));
    }

    @Test
    public void Test2() {
        Assertions.assertTrue(sixthExample.checkArrayFor14(List.of(1, 1, 1, 1, 1, 1)));
    }

    @Test
    public void Test3() {
        Assertions.assertTrue(sixthExample.checkArrayFor14(List.of(4, 4, 4, 4)));
    }

    @Test
    public void Test4() {
        Assertions.assertTrue(sixthExample.checkArrayFor14(List.of(1, 4, 4, 1, 1, 4, 3)));
    }
}
