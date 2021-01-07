package pl.lukinio.bookie.data.entity.money;

import pl.lukinio.bookie.data.entity.users.Currency;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Money extends MoneyBase{

    @Id
    @Column(name = "id")
    private Long userId;

    public Money(Long userId, BigDecimal amount, Currency currency) {
        super(amount, currency);
        this.userId = userId;
    }
    public Money(){}

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
