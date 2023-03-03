package com.example.fizzBuzz.controller;

import com.example.fizzBuzz.exception.FizzBuzzException;
import com.example.fizzBuzz.model.FizzBuzzRequest;
import com.example.fizzBuzz.model.FizzBuzzResponse;
import com.example.fizzBuzz.service.FizzBuzzService;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class FizzBuzzController {
    private FizzBuzzService fizzBuzzService;
    private final MeterRegistry meterRegistry;
    private final Logger logger = LoggerFactory.getLogger(FizzBuzzController.class);

    @PostMapping(path = "/fizzBuzz", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FizzBuzzResponse> fizzBuzz(@RequestBody @Valid FizzBuzzRequest req,
                                                     BindingResult bindingResult) {
        logger.info("Received request: {}", req);
        if (bindingResult.hasErrors()) {
            meterRegistry.counter("fizzBuzz_invalid_input").increment();
            String message = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(" "));
            logger.error("Invalid input: {}", message);
            throw new FizzBuzzException(HttpStatus.BAD_REQUEST, message);
        }
        List<String> result;
        try {
            result = fizzBuzzService.playFizzBuzz(req.numbers());
            logger.info("fizzBuzz result: ", result);
            meterRegistry.counter("fizzBuzz_success").increment();
        } catch (Exception e) {
            meterRegistry.counter("fizzBuzz_errors").increment();
            logger.error("Error occurred while processing request: {}", req, e);
            throw new FizzBuzzException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return ResponseEntity.ok(new FizzBuzzResponse(result));
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(FizzBuzzException e) {
        return new ResponseEntity<>(e.getMessage(), e.getStatus());
    }

}