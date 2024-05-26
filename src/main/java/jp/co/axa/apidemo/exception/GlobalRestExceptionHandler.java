package jp.co.axa.apidemo.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The GlobalRestExceptionHandler class is a controller advice that handles exceptions thrown within RESTful API endpoints.
 * It provides a method to handle the EmployeeNotFoundException and return a ResponseEntity with an EmployeeErrorResponse object.
 */
@ControllerAdvice
public class GlobalRestExceptionHandler {

    @Value("${error.message.notFound}")
    private String notFoundError;

    @Value("${error.message.general}")
    private String generalError;

    /**
     * This method handles the EmployeeNotFoundException and returns a ResponseEntity containing an EmployeeErrorResponse object.
     *
     * @param ex The EmployeeNotFoundException that was thrown.
     * @return A ResponseEntity containing an EmployeeErrorResponse object with details about the exception.
     */
    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundException ex) {

        // create a EmployeeErrorResponse
        EmployeeErrorResponse error = new EmployeeErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles generic exceptions and returns a ResponseEntity containing an EmployeeErrorResponse object.
     *
     * @param ex The exception that was thrown.
     * @return A ResponseEntity containing an EmployeeErrorResponse object with details about the exception.
     */
    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(Exception ex) {

        // create a EmployeeErrorResponse
        EmployeeErrorResponse error = new EmployeeErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
