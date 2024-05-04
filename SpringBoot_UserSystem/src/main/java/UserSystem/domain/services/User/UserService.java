package UserSystem.domain.services.User;

import UserSystem.domain.entities.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    List<User> getAllUserByEmailProvider(String provider);

    void deactivateInactiveUsers(LocalDate data);

    void safe(User user);

    long getUsersCount();

    List<String> getUserNamesAndAgeByAgeRange(int lowBound, int highBound);
}
