import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesFromDepartment {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        String departmentName = "Research and Development";

        List<Employee> employeeList =
                entityManager.createQuery("SELECT e FROM Employee e WHERE e.department.name = :department_name" +
                " ORDER BY e.salary ASC ,e.id ASC ", Employee.class)
                        .setParameter("department_name",departmentName)
                        .getResultList();

        String printFormat = "%s %s from %s - $%.2f%n";

        for (Employee employee : employeeList) {
            System.out.printf(String.format(printFormat
                    ,employee.getFirstName()
                    ,employee.getLastName()
                    ,employee.getDepartment().getName()
                    ,employee.getSalary()));
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
