import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class IncreaseSalaries {
    private static final List<String> DEPARTMENTS_NAME = List.of(
            "Engineering",
            "Tool Design",
            "Marketing",
            "Information Services");

    public static void main(String[] args) {

        EntityManager entityManager =
                Persistence.createEntityManagerFactory("PU_Name").createEntityManager();

        entityManager.getTransaction().begin();

        List<Employee> employeeList =
                entityManager.createQuery("FROM Employee e WHERE e.department.name IN (:departments)", Employee.class)
                        .setParameter("departments",DEPARTMENTS_NAME)
                        .getResultList();

        employeeList.forEach(e -> e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12))));

        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
