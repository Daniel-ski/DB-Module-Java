import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ChangeCasing {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Town> selectTFromTown = entityManager
                .createQuery("SELECT t FROM Town t ", Town.class).getResultList();

        for (Town town : selectTFromTown) {
            if (town.getName().length() <= 5) {
                String townNameToUpper = town.getName().toUpperCase();
                town.setName(townNameToUpper);

                entityManager.persist(town);
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
