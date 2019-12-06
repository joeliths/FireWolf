package com.example.demo.services.validation;

import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Stream;

@Service
public class ValidationService {

    public boolean checkIfAnyFieldsAreNull(Object... objects){
        return Stream.of(objects).anyMatch(Objects::isNull);
    }

}
