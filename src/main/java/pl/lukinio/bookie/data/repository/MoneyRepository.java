package pl.lukinio.bookie.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lukinio.bookie.data.entity.money.Money;

@Repository
public interface MoneyRepository extends JpaRepository<Money, Long> {

}
