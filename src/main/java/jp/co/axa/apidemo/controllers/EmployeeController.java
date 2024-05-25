package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.Valid;
import java.util.List;

/**
 * The EmployeeController class is a RESTful API controller that handles requests related to employees. It provides
 * methods for retrieving a list of all employees, retrieving an employee by ID, saving an employee, deleting an
 * employee, and updating an employee.
 */
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Value("${error.message.notFound}")
    private String notFoundError;

    @Value("${error.message.database}")
    private String databaseError;

    @Value("${error.message.general}")
    private String generalError;

    /**
     * Retrieves a list of all employees.
     *
     * @return a List of Employee objects representing the employees
     */
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        return employees;
    }

    /**
     * Retrieves an employee by their employee ID.
     *
     * @param employeeId the ID of the employee to retrieve
     * @return a ResponseEntity representing the response containing the employee
     * @throws EmployeeNotFoundException if the employee with the specified ID is not found
     * @throws DataAccessException       if there is a database error
     * @throws Exception                 if an unexpected error occurs
     */
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<?> getEmployee(@PathVariable(name = "employeeId") Long employeeId) {

        // Retrieves an employee by their employee ID.
        Employee emp = employeeService.getEmployee(employeeId);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    /**
     * Save an employee.
     *
     * @param employee the employee to be saved
     * @return a ResponseEntity representing the response containing the saved employee
     */
    @PostMapping("/employees")
    public ResponseEntity<?> saveEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
        //System.out.println("Employee Saved Successfully");
    }

    /**
     * Deletes an employee by their employee ID.
     *
     * @param employeeId the ID of the employee to delete
     * @return a ResponseEntity representing the response indicating the success or failure of the deletion
     * @throws EmployeeNotFoundException if the employee with the specified ID is not found
     * @throws DataAccessException if there is a database error
     * @throws Exception if an unexpected error occurs
     */
    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        employeeService.getEmployee(employeeId);
        return employeeService.deleteEmployee(employeeId);
        //System.out.println("Employee Deleted Successfully");
    }

    /**
     * Updates an employee with the given employee data.
     *
     * @param employee     the updated employee object
     * @param employeeId   the ID of the employee to be updated
     * @return a ResponseEntity representing the response indicating the success or failure of the update
     * @throws EmployeeNotFoundException  if the employee with the specified ID is not found
     * @throws DataAccessException        if there is a database error
     * @throws Exception                  if an unexpected error occurs
     */
    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody Employee employee,
                                            @PathVariable(name = "employeeId") Long employeeId) {
        employeeService.getEmployee(employeeId);
        employee.setId(employeeId);
        return employeeService.updateEmployee(employee);
    }

    /**
     * This method handles the MethodArgumentNotValidException,
     * which is thrown when the validation of method arguments fails.
     * It returns the default error message associated with the first invalid field.
     *
     * @param ex the MethodArgumentNotValidException to be handled
     * @return the default error message of the first invalid field
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldError().getDefaultMessage();
    }

    /**
     * This method handles the MethodArgumentTypeMismatchException, which is thrown when
     * a method argument type does not match the expected type.
     *
     * @param ex the MethodArgumentTypeMismatchException to be handled
     * @return the error message associated with the exception
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return ex.getMessage();
    }

    /**
     * This method handles the EmployeeNotFoundException, which is thrown when an employee is not found.
     *
     * @param ex the EmployeeNotFoundException to be handled
     * @return the error message indicating that the employee was not found
     */
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public String handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return notFoundError;
    }

    /**
     * This method handles a DataAccessException and returns a string representing a database error.
     *
     * @param ex the DataAccessException to be handled
     * @return a string representing the database error
     */
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handleDataAccessException(DataAccessException ex) {
        return databaseError;
    }

    /**
     * This method handles the generic exception thrown within the EmployeeController class.
     * It returns a string representation of the error message associated with the exception.
     *
     * @param ex the exception to be handled
     * @return a string representation of the error message
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String handleException(Exception ex) {
        return generalError + ex.getMessage();
    }
}
