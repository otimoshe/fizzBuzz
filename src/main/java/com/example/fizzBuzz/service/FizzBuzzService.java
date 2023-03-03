package com.example.fizzBuzz.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FizzBuzzService {
    public String fizzBuzz(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return "fizzbuzz";
        } else if (number % 3 == 0) {
            return "fizz";
        } else if (number % 5 == 0) {
            return "buzz";
        } else {
            return String.valueOf(number);
        }
    }

    public List<String> playFizzBuzz(List<Integer> input) {
        return input.stream().map(this::fizzBuzz).toList();
    }
}