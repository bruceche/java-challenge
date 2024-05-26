package jp.co.axa.apidemo.exception;

import lombok.Data;

/**
 * The EmployeeErrorResponse class represents an error response for employee-related operations.
 * It contains the status, message, and timestamp of the error.
 */
@Data
public class EmployeeErrorResponse {

    private int status;

    private String message;

    private long timeStamp;
}
