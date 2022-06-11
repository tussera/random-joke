package com.ggcorreia.randomjoke.exception;

import org.springframework.http.HttpStatus;

public class JokeCriteriaNotMatchedException extends ApiException{
    public JokeCriteriaNotMatchedException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
