package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The EmployeeRepository interface defines the contract for interacting with the employee repository.
 * It extends the JpaRepository interface, providing basic CRUD operations for the Employee entity.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
