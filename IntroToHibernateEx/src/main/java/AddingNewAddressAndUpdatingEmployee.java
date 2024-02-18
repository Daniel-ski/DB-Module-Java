import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AddingNewAddressAndUpdatingEmployee {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        Address address = new Address();
        String newAddress = "Vitoshka 15";
        address.setText(newAddress);

        entityManager.persist(address);

        String lastName = new Scanner(System.in).nextLine().trim();

        int affectedCount = entityManager.createQuery("UPDATE Employee e SET e.address = :newAddress WHERE e.lastName = :last_name")
                .setParameter("newAddress", address)
                .setParameter("last_name", lastName)
                .executeUpdate();

        if (affectedCount <= 0){
            entityManager.getTransaction().rollback();
        }else {
            entityManager.getTransaction().commit();
        }

        entityManager.close();

        System.out.printf("%d Employees affected",affectedCount);
    }
}
