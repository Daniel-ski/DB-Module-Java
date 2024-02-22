import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Comparator;

public class FindLatest10Projects {
    public static void main(String[] args) {

        EntityManager entityManager =
                Persistence.createEntityManagerFactory("PU_Name").createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery("FROM Project ORDER BY startDate DESC, name", Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(Project::printGeneralInformation);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
