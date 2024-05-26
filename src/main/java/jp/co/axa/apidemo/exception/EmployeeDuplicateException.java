package jp.co.axa.apidemo.exception;

/**
 * The EmployeeDuplicateException class is a custom exception that is thrown when a duplicate employee is encountered.
 */
public class EmployeeDuplicateException extends RuntimeException {

    /**
     * Creates a new instance of EmployeeDuplicateException with the specified message.
     *
     * @param message the detailed message of the exception
     */
    public EmployeeDuplicateException(String message) {
        super(message);
    }

    /**
     * This class represents an exception that is thrown when a duplicate employee is encountered.
     *
     * @param message The detailed error message.
     * @param cause   The cause of the exception.
     */
    public EmployeeDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
