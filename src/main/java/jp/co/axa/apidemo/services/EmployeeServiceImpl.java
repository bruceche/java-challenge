package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is an implementation of the EmployeeService interface.
 * It provides methods to retrieve, save, update, and delete employees from the employee repository.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Retrieve all Employee entities from the database.
     *
     * @return A List of Employee entities. If no employees exist in the database, it returns an empty list.
     */
    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    /**
     * Retrieves an Employee based on the provided employeeId.
     *
     * @param employeeId The ID of the Employee to retrieve.
     * @return The Employee with the specified employeeId.
     * @throws EmployeeNotFoundException if no Employee is found with the given employeeId.
     */
    public Employee getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee could not be found"));
    }

    /**
     * Saves the given employee.
     *
     * @param employee The employee to be saved.
     * @return A ResponseEntity object with a success message and HTTP status code 201 (CREATED).
     */
    public ResponseEntity<?> saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return new ResponseEntity<>("Employee has been saved successfully.", HttpStatus.CREATED);
    }

    /**
     * Deletes an employee with the specified employeeId.
     *
     * @param employeeId The ID of the employee to delete.
     * @return A ResponseEntity object with a success message and HTTP status code 200 (OK).
     */
    public ResponseEntity<?> deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
        return new ResponseEntity<>("Employee has been deleted successfully.", HttpStatus.OK);
    }

    /**
     * Updates an employee in the employee repository.
     *
     * @param employee The employee to be updated.
     * @return A ResponseEntity object with a success message and HTTP status code 200 (OK).
     */
    public ResponseEntity<?> updateEmployee(Employee employee) {
        employeeRepository.save(employee);
        return new ResponseEntity<>("Employee has been updated successfully.", HttpStatus.OK);
    }
}