package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * This class is an implementation of the EmployeeService interface.
 * It provides methods to retrieve, save, update, and delete employees from the employee repository.
 */
@Service
@EnableCaching
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Retrieves a page of employees from the database.
     *
     * @param page The page number to retrieve. Must be greater than or equal to 0.
     * @param size The number of employees per page. Must be greater than 0.
     * @return A Page of Employee objects.
     */
    public Page<Employee> retrieveEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = employeeRepository.findAll(pageable);
        employees.forEach(employee -> cacheEmployee(employee));
        return employees;
    }

    /**
     * Retrieves an Employee based on the provided employeeId.
     *
     * @param employeeId The ID of the Employee to retrieve.
     * @return The Employee with the specified employeeId.
     * @throws EmployeeNotFoundException if no Employee is found with the given employeeId.
     */
    @Cacheable(value = "employee", key = "#employeeId")
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
    @CacheEvict(value = "employee", key = "#employeeId")
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
    @CacheEvict(value = "employee", key = "#employee.id")
    public ResponseEntity<?> updateEmployee(Employee employee) {
        employeeRepository.save(employee);
        return new ResponseEntity<>("Employee has been updated successfully.", HttpStatus.OK);
    }

    /**
     * Checks if an employee with the given ID exists in the database.
     *
     * @param employeeId The ID of the employee to check for existence.
     * @return {@code true} if an employee with the given ID exists, {@code false} otherwise.
     */
    @Override
    public boolean existsById(Long employeeId) {
        return employeeRepository.existsById(employeeId);
    }

    /**
     * Caches an employee object in the cache.
     *
     * @param employee The employee object to be cached.
     * @return The cached employee object.
     */
    @Cacheable(value = "employee", key = "#employee.id")
    private Employee cacheEmployee(Employee employee) {
        return employee;
    }
}