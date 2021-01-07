package pl.lukinio.bookie.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lukinio.bookie.data.entity.events.Games;


@Repository
public interface GameRepository extends JpaRepository<Games, Long> {

}
