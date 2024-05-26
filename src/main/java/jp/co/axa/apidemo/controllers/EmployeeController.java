package jp.co.axa.apidemo.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

/**
 * The EmployeeController class is a RESTful API controller that handles requests related to employees. It provides
 * methods for retrieving a list of all employees, retrieving an employee by ID, saving an employee, deleting an
 * employee, and updating an employee.
 */
@Api(value = "Employees Management System", description = "Operations pertaining to employee in Employees Management System")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Retrieves a page of employees.
     *
     * @param page The number of the page to retrieve. The default value is 0 if not provided.
     * @param size The number of employees to retrieve per page. The default value is 10 if not provided.
     * @return A Page object containing the employees for the specified page.
     */
    @ApiOperation(
            value = "Get Employees",
            notes = "Retrieves a page of employees, with configurable page number and size.")
    @GetMapping("/employees")
    public Page<Employee> getEmployees(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {

        Page<Employee> employees = employeeService.retrieveEmployees(page, size);

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
    @ApiOperation(
            value = "Get Employee",
            notes = "Retrieve a single employee by their employee ID.")
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
    @ApiOperation(
            value = "Save Employee",
            notes = "Saves a new employee to the database.")
    @PostMapping("/employees")
    public ResponseEntity<?> saveEmployee(@Valid @RequestBody Employee employee) {

        return employeeService.saveEmployee(employee);
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
    @ApiOperation(
            value = "Delete Employee",
            notes = "Deletes an employee from the database using their Employee ID.")
    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {

        // Exists check
        checkEmployeeExists(employeeId);

        return employeeService.deleteEmployee(employeeId);
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
    @ApiOperation(
            value = "Update Employee",
            notes = "Updates an existing employee's details using their Employee ID.")
    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody Employee employee,
                                            @PathVariable(name = "employeeId") Long employeeId) {
        // Exists check
        checkEmployeeExists(employeeId);

        employee.setId(employeeId);
        return employeeService.updateEmployee(employee);
    }

    /**
     * Checks if an employee with the given employee ID exists in the database.
     *
     * @param employeeId The ID of the employee to check for existence.
     * @return {@code true} if an employee with the given ID exists, {@code false} otherwise.
     * @throws ResponseStatusException if the employee with the specified ID is not found
     */
    private boolean checkEmployeeExists(Long employeeId) {
        if (!employeeService.existsById(employeeId)) {
            throw new EmployeeNotFoundException("Employee could not be found");
        }
        return true;
    }
}
