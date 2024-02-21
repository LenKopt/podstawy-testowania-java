package pl.akademiaspecjalistowit.podstawytestowaniajava.ex2.money;

public class Money {

    public final Currency currency;
    public final Double amount;

    public Money(Currency currency, Double amount) {
        if (amount < 0) {
            throw new MoneyException("Money cannot hold negative amount");
        }
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "[" + amount + ", " + currency + "]";
    }
}
