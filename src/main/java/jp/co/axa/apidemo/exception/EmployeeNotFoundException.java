package jp.co.axa.apidemo.exception;

/**
 * This class represents an exception that is thrown when an employee is not found.
 */
public class EmployeeNotFoundException extends RuntimeException {

    /**
     * This constructor takes a string message that describes the exception.
     *
     * @param message A string that contains the specific details of the exception. This message is constructed and passed by the caller.
     */
    public EmployeeNotFoundException(String message) {
        super(message);
    }

    /**
     * This constructor takes a string message and a Throwable cause that caused the exception.
     *
     * @param message A string that contains the specific details of the exception. This message is constructed and passed by the caller.
     * @param cause   An instance of Throwable that represents the underlying cause of this exception. This is useful when the
     *                EmployeeNotFoundException is a result of different exception thrown in system.
     */
    public EmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
