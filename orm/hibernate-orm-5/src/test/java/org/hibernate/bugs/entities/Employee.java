package org.hibernate.bugs.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * @author Jeroen Stiekema (jeroen@stiekema.eu)
 */
@Entity
@PrimaryKeyJoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "PERSON_ID")
public class Employee extends Person {

    private Integer salary;

    public Employee() {
    }

    public Employee(Long id, String name, Integer salary) {
        super(id, name);
        this.salary = salary;
    }
}
