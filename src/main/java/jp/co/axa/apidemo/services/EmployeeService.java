package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * The EmployeeService interface establishes a contract for the EmployeeService implementation class.
 * It provides necessary CRUD operations for Employee entities in the database.
 */
public interface EmployeeService {

    /**
     * Retrieves a page of employees.
     *
     * @param page The number of the page to retrieve.
     * @param size The number of employees to retrieve per page.
     * @return A Page object containing the employees for the specified page.
     */
    public Page<Employee> retrieveEmployees(int page, int size);

    /**
     * Retrieve a single employee using its ID.
     *
     * @param employeeId The ID of the employee to retrieve.
     * @return An Employee object that matches the provided ID.
     */
    public Employee getEmployee(Long employeeId);

    /**
     * Save a new employee to the database.
     *
     * @param employee The employee entity to be saved.
     * @return A ResponseEntity indicating the status of the operation.
     */
    public ResponseEntity<?> saveEmployee(Employee employee);

    /**
     * Delete an employee from the database.
     *
     * @param employeeId The ID of the employee to be deleted.
     * @return A ResponseEntity indicating the status of the operation.
     */
    public ResponseEntity<?> deleteEmployee(Long employeeId);

    /**
     * Update an existing employee's information in the database.
     *
     * @param employee The updated employee entity.
     * @return A ResponseEntity indicating the status of the operation.
     */
    public ResponseEntity<?> updateEmployee(Employee employee);

    /**
     * Checks if an employee with the given ID exists in the database.
     *
     * @param employeeId The ID of the employee to check for existence.
     * @return {@code true} if an employee with the given ID exists, {@code false} otherwise.
     */
    public boolean existsById(Long employeeId);
}