package com.example.fizzBuzz.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FizzBuzzServiceTest {
    @Autowired
    private FizzBuzzService fizzBuzzService;

    @Test
    public void testFizzBuzz() {
        Assertions.assertEquals("fizz", fizzBuzzService.fizzBuzz(3));
        Assertions.assertEquals("buzz", fizzBuzzService.fizzBuzz(5));
        Assertions.assertEquals("fizzbuzz", fizzBuzzService.fizzBuzz(15));
        Assertions.assertEquals("7", fizzBuzzService.fizzBuzz(7));
    }

    @Test
    public void testPlayFizzBuzz() {
        Assertions.assertEquals(List.of("1", "2", "fizz", "4", "buzz", "fizzbuzz"), fizzBuzzService.playFizzBuzz(List.of(1, 2, 3, 4, 5, 15)));
        Assertions.assertEquals(List.of("fizzbuzz", "fizzbuzz"), fizzBuzzService.playFizzBuzz(List.of(15, 15)));
        Assertions.assertEquals(List.of("fizz", "fizz"), fizzBuzzService.playFizzBuzz(List.of(3, 3)));
        Assertions.assertEquals(List.of("buzz", "buzz"), fizzBuzzService.playFizzBuzz(List.of(5, 5)));
    }
}
