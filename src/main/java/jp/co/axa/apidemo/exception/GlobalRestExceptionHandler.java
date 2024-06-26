package jp.co.axa.apidemo.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The GlobalRestExceptionHandler class provides global exception handling for REST endpoints.
 */
@ControllerAdvice
public class GlobalRestExceptionHandler {

    @Value("${error.message.general}")
    private String generalError;

    /**
     * This method handles the EmployeeNotFoundException and returns a ResponseEntity containing an EmployeeErrorResponse object.
     *
     * @param ex The EmployeeNotFoundException that was thrown.
     * @return A ResponseEntity containing an EmployeeErrorResponse object with details about the exception.
     */
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundException ex) {

        // Print stack trace to console
        ex.printStackTrace();

        // create a EmployeeErrorResponse
        EmployeeErrorResponse error = new EmployeeErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles the EmployeeDuplicateException and returns a ResponseEntity containing an EmployeeErrorResponse object.
     *
     * @param ex The EmployeeDuplicateException that was thrown.
     * @return A ResponseEntity containing an EmployeeErrorResponse object with details about the exception.
     */
    @ExceptionHandler(EmployeeDuplicateException.class)
    public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeDuplicateException ex) {

        // Print stack trace to console
        ex.printStackTrace();

        // create a EmployeeErrorResponse
        EmployeeErrorResponse error = new EmployeeErrorResponse();

        error.setStatus(HttpStatus.CONFLICT.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    /**
     * This method handles generic exceptions and returns a ResponseEntity containing an EmployeeErrorResponse object.
     *
     * @param ex The exception that was thrown.
     * @return A ResponseEntity containing an EmployeeErrorResponse object with details about the exception.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<EmployeeErrorResponse> handleException(Exception ex) {

        // Print stack trace to console
        ex.printStackTrace();

        // create a EmployeeErrorResponse
        EmployeeErrorResponse error = new EmployeeErrorResponse();

        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(generalError);
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
