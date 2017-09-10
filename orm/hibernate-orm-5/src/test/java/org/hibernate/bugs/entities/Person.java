package org.hibernate.bugs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;

/**
 * @author Jeroen Stiekema (jeroen@stiekema.eu)
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @Column(name = "PERSON_ID")
    private Long id;

    @Version
    private Integer version;

    private String name;

    public Person() {
    }

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
