import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class GetEmployeeWithProject {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        int inputId = new Scanner(System.in).nextInt();

        entityManager.createQuery("SELECT e FROM Employee e WHERE e.id = :inputID", Employee.class)
                        .setParameter("inputID",inputId)
                        .getResultList()
                        .forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
