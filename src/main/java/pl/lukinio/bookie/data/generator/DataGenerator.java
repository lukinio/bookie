package pl.lukinio.bookie.data.generator;

import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import pl.lukinio.bookie.data.entity.money.Money;
import pl.lukinio.bookie.data.entity.users.Admin;
import pl.lukinio.bookie.data.entity.users.Currency;
import pl.lukinio.bookie.data.entity.users.User;
import pl.lukinio.bookie.data.repository.MoneyRepository;
import pl.lukinio.bookie.data.repository.UserRepository;

import java.math.BigDecimal;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, MoneyRepository moneyRepository) {
        return args -> {
            Admin admin = new Admin("admin", "admin", "admin@bookie.pl");
            if(!userRepository.exists(Example.of(admin))){
                userRepository.save(admin);
            }
            User user = new User("user", "user", "user@bookie.pl");
            if(!userRepository.exists(Example.of(user))){
                userRepository.save(user);
            }

            Money userMoney = new Money(2L, new BigDecimal("100"), Currency.PLN);
            if(!moneyRepository.exists(Example.of(userMoney))){
                moneyRepository.save(userMoney);
            }

        };
    }
}