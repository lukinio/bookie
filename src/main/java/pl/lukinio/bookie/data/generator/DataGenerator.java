package pl.lukinio.bookie.data.generator;

import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import pl.lukinio.bookie.data.entity.Admin;
import pl.lukinio.bookie.data.entity.User;
import pl.lukinio.bookie.data.repository.UserRepository;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository) {
        return args -> {
            Admin admin = new Admin("admin", "admin", "admin@bookie.pl");
            if(!userRepository.exists(Example.of(admin))){
                userRepository.save(admin);
            }
            User user = new User("user", "user", "user@bookie.pl");
            if(!userRepository.exists(Example.of(user))){
                userRepository.save(user);
            }
        };
    }
}