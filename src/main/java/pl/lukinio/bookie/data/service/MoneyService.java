package pl.lukinio.bookie.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lukinio.bookie.data.entity.money.Money;
import pl.lukinio.bookie.data.repository.MoneyRepository;

import java.util.List;

@Service
public class MoneyService {

    @Autowired
    private final MoneyRepository moneyRepository;

    public MoneyService(MoneyRepository moneyRepository){
        this.moneyRepository = moneyRepository;
    }
    public List<Money> findAll() {
        return moneyRepository.findAll();
    }
}
