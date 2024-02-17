import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class ContainsEmployee {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        String[] inputName = scanner.nextLine().split("\\s+");
        String firstName = inputName[0];
        String secondName = inputName[1];

        Long singleResult = entityManager.createQuery("SELECT count(e) FROM Employee e " +
                " WHERE e.firstName = :first_name AND e.lastName = :last_name", Long.class)
                .setParameter("first_name",firstName).setParameter("last_name",secondName) .getSingleResult();

        if (singleResult > 0){
            System.out.println("Yes");
        }else {
            System.out.println("No");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
