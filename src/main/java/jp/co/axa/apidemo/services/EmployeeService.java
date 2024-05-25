package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * The EmployeeService interface establishes a contract for the EmployeeService implementation class.
 * It provides necessary CRUD operations for Employee entities in the database.
 */
public interface EmployeeService {

    /**
     * Retrieve all employees from the database.
     *
     * @return A list of Employee objects.
     */
    public List<Employee> retrieveEmployees();

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
}