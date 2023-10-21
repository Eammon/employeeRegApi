package com.montfi.employeeregistrationapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)//Return Internal Server Error as the response status
public class ResourceNotSavedException extends RuntimeException{
    public ResourceNotSavedException(String message) {
        super(message);
    }
}
