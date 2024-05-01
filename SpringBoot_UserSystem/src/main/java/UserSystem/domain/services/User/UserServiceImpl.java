package UserSystem.domain.services.User;

import UserSystem.Repositories.UserRepository;
import UserSystem.domain.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUserByEmailProvider(String provider) {
        return this.userRepository.findAllByEmailEndingWith(provider);
    }

    @Override
    @Modifying
    public void deactivateInactiveUsers(LocalDate data) {
         this.userRepository.findAllByLastTimeLoggedInBefore(data)
                .forEach(user -> user.setDeleted(true));
    }

    @Override
    public void safe(User user) {
        this.userRepository.save(user);
    }

    @Override
    public long getUsersCount() {
        return this.userRepository.count();
    }

    @Override
    public List<String> getUserNamesAndAgeByAgeRange(int lowBound, int highBound) {
        return this.userRepository.findAllByAgeBetweenOrderByAge(lowBound,highBound)
                .stream()
                .map(User::getFullNameAndAge)
                .collect(Collectors.toList());
    }
}
