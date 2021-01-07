package pl.lukinio.bookie.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lukinio.bookie.data.entity.events.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team getByName(String name);
}
