package com.montfi.employeeregistrationapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class EmployeeDatabaseException extends RuntimeException {
        public EmployeeDatabaseException(String message, Throwable cause) {
            super(message, cause);

        }
}
