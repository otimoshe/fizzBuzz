package com.example.fizzBuzz.controler;


import com.example.fizzBuzz.model.FizzBuzzRequest;
import com.example.fizzBuzz.model.FizzBuzzResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class FizzBuzzControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void fizzBuzzForValidInput() throws Exception {
        FizzBuzzResponse expected = new FizzBuzzResponse(List.of("1", "2", "fizz", "4", "buzz"));
        FizzBuzzRequest request = new FizzBuzzRequest(List.of(1, 2, 3, 4, 5));

        mockMvc.perform(post("/fizzBuzz")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expected)));
    }

    @Test
    void badRequestForEmptyInput() throws Exception {
        String expected = "Numbers field cannot be empty.";
        FizzBuzzRequest request = new FizzBuzzRequest(Collections.emptyList());

        mockMvc.perform(post("/fizzBuzz")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(expected));
    }

}