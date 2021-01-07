package pl.lukinio.bookie.data.entity.money;

import pl.lukinio.bookie.data.entity.users.Currency;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class MoneyBase implements Serializable, Comparable<MoneyBase> {
    private BigDecimal amount;
    private Currency currency;

    public MoneyBase(BigDecimal amount, Currency currency) {
        this.amount = amount.setScale(currency.getScale(), currency.getRoundingMode());
        this.currency = currency;
    }
    public MoneyBase(){}

    public MoneyBase add(MoneyBase other) {
        assertSameCurrency(other);
        return new MoneyBase(amount.add(other.amount), currency);
    }

    public MoneyBase subtract(MoneyBase other) {
        assertSameCurrency(other);
        return new MoneyBase(amount.subtract(other.amount), currency);
    }

    public MoneyBase multiply(MoneyBase other){
        assertSameCurrency(other);
        return new MoneyBase(amount.multiply(other.amount), currency);
    }

    private void assertSameCurrency(MoneyBase other) {
        if (!other.currency.equals(this.currency)) {
            throw new IllegalArgumentException("Money objects must have the same currency");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MoneyBase moneyBase = (MoneyBase) o;
        return Objects.equals(amount, moneyBase.amount) && Objects.equals(currency, moneyBase.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), amount, currency);
    }

    @Override
    public int compareTo(MoneyBase other) {
        return amount.subtract(other.amount).signum();
    }

}