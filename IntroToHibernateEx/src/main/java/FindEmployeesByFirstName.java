import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

public class FindEmployeesByFirstName {
    public static void main(String[] args) {

        EntityManager entityManager =
                Persistence.createEntityManagerFactory("PU_Name").createEntityManager();

        entityManager.getTransaction().begin();

        entityManager .createQuery("FROM Employee "
                        + "WHERE firstName LIKE CONCAT(:letters, '%')", Employee.class)
                .setParameter("letters", new Scanner(System.in).nextLine())
                .getResultList()
                .forEach(empl -> System.out.printf("%s %s - %s - ($%s)%n",
                        empl.getFirstName(),
                        empl.getLastName(),
                        empl.getJobTitle(),
                        empl.getSalary()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
