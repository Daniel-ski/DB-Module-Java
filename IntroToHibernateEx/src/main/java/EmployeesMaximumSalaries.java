import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EmployeesMaximumSalaries {
    public static void main(String[] args) {

        EntityManager entityManager =
                Persistence.createEntityManagerFactory("PU_Name").createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery("SELECT department.name, max(salary)" +
                        " FROM Employee " +
                        " GROUP BY department.name" +
                        " HAVING max(salary) NOT BETWEEN 30000 AND 70000", Object[].class)
                .getResultList()
                .forEach(objects -> System.out.println(objects[0] + " " + objects[1]));

        System.out.println(System.lineSeparator());

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
