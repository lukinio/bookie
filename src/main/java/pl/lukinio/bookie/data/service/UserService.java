package pl.lukinio.bookie.data.service;


import org.springframework.stereotype.Service;
import pl.lukinio.bookie.data.entity.User;
import pl.lukinio.bookie.data.repository.UserRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public long count() {
        return userRepository.count();
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void save(User user) {
        if (user == null) {
            LOGGER.log(Level.SEVERE,
                    "Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        userRepository.save(user);
    }
}