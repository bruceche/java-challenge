package jp.co.axa.apidemo.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * This class represents an Employee entity in the database.
 * Employee details are fetched and managed via this entity.
 * <p>
 * Each employee has a unique id, a name, salary and associated department.
 */
@Entity
@Table(name="EMPLOYEE")
@Data
public class Employee {

    // Unique identifier for the employee.
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    // Name of the employee. This field is mandatory.
    @Column(name="EMPLOYEE_NAME")
    @NotBlank(message = "name is mandatory")
    private String name;

    // Salary of the employee.
    @Column(name="EMPLOYEE_SALARY")
    private Integer salary;

    // Department of the employee.
    @Column(name="DEPARTMENT")
    private String department;

}
