package pl.lukinio.bookie.data.service;


import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import pl.lukinio.bookie.data.entity.User;
import pl.lukinio.bookie.data.repository.UserRepository;

import java.util.List;
import java.util.Optional;
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

    public boolean save(User user) {
        if (user == null) {
            LOGGER.log(Level.SEVERE, "User is null");
            return false;
        } else if(userRepository.exists(Example.of(user))) {
            LOGGER.log(Level.SEVERE, "User exists");
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public Optional<User> find(User user){
        return userRepository.findOne(Example.of(user));
    }

}