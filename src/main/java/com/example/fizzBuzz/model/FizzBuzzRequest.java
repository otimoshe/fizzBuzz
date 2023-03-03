package com.example.fizzBuzz.model;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record FizzBuzzRequest(@NotEmpty(message = "Numbers field cannot be empty.")
                              List<Integer> numbers) {
}