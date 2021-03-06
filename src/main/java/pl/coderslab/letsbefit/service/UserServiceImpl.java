package pl.coderslab.letsbefit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbefit.entity.Plan;
import pl.coderslab.letsbefit.entity.User;
import pl.coderslab.letsbefit.entity.Weight;
import pl.coderslab.letsbefit.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean checkPasswordForRegister(String pass1, String pass2) {
        return (
                pass1.equals(pass2) &&
                        pass1.matches(".{8,}") &&
                        pass1.matches("^.*\\d+.*$") &&
                        pass1.matches("^.*[a-zA-Z]+.*$")
        );
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.getByLogin(login);
    }

    @Override
    public LocalDate forecastRealDate(Plan plan, Weight weight) {
        double kgPerDay = 0.5 / 7;
        LocalDate today = LocalDate.now();
        int numOfDaysToBeFit = (int) Math.round(Math.abs((plan.getTargetWeight() - weight.getCurrentWeight())/(kgPerDay)));
        return today.plusDays(numOfDaysToBeFit);
    }

}
