package pl.lukinio.bookie.data.service;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lukinio.bookie.data.entity.User;
import pl.lukinio.bookie.data.entity.UserBase;
import pl.lukinio.bookie.data.repository.UserRepository;
import pl.lukinio.bookie.views.login.LoginView;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.vaadin.flow.component.notification.Notification.Position.TOP_CENTER;

@Service
public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserBase> findAll() {
        return userRepository.findAll();
    }

    public long count() {
        return userRepository.count();
    }

    public void delete(UserBase user) {
        userRepository.delete(user);
    }

    public void register(String username, String password, String email){
        if(userRepository.existsByUsername(username)){
            Notification.show("Username exists", 2500, TOP_CENTER);
            LOGGER.log(Level.INFO, () -> String.format("Given username exists: %s", username));
            return;
        }
        if(userRepository.existsByEmail(email)){
            Notification.show("Email exists", 2500, TOP_CENTER);
            LOGGER.log(Level.INFO, () -> String.format("Given email exist: %s", email));
            return;
        }
        User newUser = new User(username, password, email);
        userRepository.save(newUser);
        Notification.show("Registration succeeded.", 2500, TOP_CENTER);
        LOGGER.log(Level.INFO, () -> String.format("Registration succeeded: %s", newUser));
        UI.getCurrent().navigate(LoginView.class);
    }

    public void authenticate(String username, String password) {
        if(!userRepository.existsByUsername(username)){
            Notification.show("Username not exists", 2500, TOP_CENTER);
            LOGGER.log(Level.INFO, () -> String.format("Given username not exists: %s", username));
            return;
        }
        UserBase user = userRepository.getByUsername(username);
        if (user.checkPassword(password)) {
            VaadinSession.getCurrent().setAttribute(UserBase.class, user);
            user.setAvailableRoutes();
            UI.getCurrent().getPage().reload();
            UI.getCurrent().getPage().setLocation("about");
        } else {
            Notification.show("Wrong credentials", 2500, TOP_CENTER);
            LOGGER.log(Level.INFO, "Wrong credentials");
        }
    }
}