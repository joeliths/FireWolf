package com.example.demo.services.validation;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ValidationServiceTest {

    private ValidationService validationService;

    @BeforeAll
    public void init(){
     validationService = new ValidationService();
    }

    @Test
    void checkIfAnyFieldsAreNull() {
        assertTrue(validationService.checkIfAnyFieldsAreNull("null", null));
    }

    @Test
    void checkIfAnyFieldsAreNull_returnFalse(){
        assertFalse(validationService.checkIfAnyFieldsAreNull("null", "yeaaah"));
    }

    @Test
    void checkIfAnyFieldsAreNull_returnFalseWithPrimitives(){
        assertFalse(validationService.checkIfAnyFieldsAreNull("null", 5, true));
    }
}