package com.example.billink.Handler;

import com.example.billink.Exceptions.NoSuchElementException;
import com.example.billink.Models.Budget;
import graphql.GraphQLError;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationHandler {

    @GraphQlExceptionHandler(NoSuchElementException.class)
    public GraphQLError handleValidationExceptions(NoSuchElementException e) {

        return GraphQLError.newError()
                .errorType(ErrorType.INTERNAL_ERROR)
                .message(e.getMessage())
                .build();
    }
}
