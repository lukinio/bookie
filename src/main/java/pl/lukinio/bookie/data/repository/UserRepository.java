package pl.lukinio.bookie.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lukinio.bookie.data.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getByUsername(String username);
}
