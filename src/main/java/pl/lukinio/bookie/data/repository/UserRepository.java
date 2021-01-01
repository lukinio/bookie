package pl.lukinio.bookie.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lukinio.bookie.data.entity.UserBase;

@Repository
public interface UserRepository extends JpaRepository<UserBase, Long> {
    UserBase getByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
