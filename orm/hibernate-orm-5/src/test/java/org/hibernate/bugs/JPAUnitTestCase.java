package org.hibernate.bugs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import org.hibernate.bugs.entities.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 * Entities are auto-discovered, so just add them anywhere on class-path
 * Add your tests, using standard JUnit.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}


	/**
	 * Test case which repreduces the issue described in https://hibernate.atlassian.net/browse/HHH-11979
	 *
	 * @throws Exception
     */
	@Test
	public void HHH11979Test() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// first create an employee entity and commit to the database
		entityManager.getTransaction().begin();
		Employee employee = new Employee(1L, "John Doe", 10000);
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
		entityManager.close();

		// next, load and lock the employee
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Employee lockedEmployee = entityManager.find(Employee.class, 1L);
		entityManager.lock(lockedEmployee, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		entityManager.getTransaction().commit();
	}
}
