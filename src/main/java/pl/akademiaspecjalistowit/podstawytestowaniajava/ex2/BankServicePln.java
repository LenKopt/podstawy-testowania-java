package pl.akademiaspecjalistowit.podstawytestowaniajava.ex2;

import pl.akademiaspecjalistowit.podstawytestowaniajava.ex2.money.Currency;
import pl.akademiaspecjalistowit.podstawytestowaniajava.ex2.money.Money;
import pl.akademiaspecjalistowit.podstawytestowaniajava.ex2.money.MoneyException;

import java.util.ArrayList;
import java.util.List;

public class BankServicePln implements BankService {
    private List<Money> listOfMoney;
    private Currency currency;

    public BankServicePln(Currency currency) {
        this.listOfMoney = new ArrayList<>();
        this.currency = currency;
    }

    @Override
    public Money withdrawMoney(Money amount) {
        if (!amount.currency.equals(currency)) {
            throw new MoneyException("Waluta różni się od aktualnej - " + amount.currency + ". Aktualna waluta: " + currency);
        }
        Double currentAmount = amount.amount;
        Double realAmount = getSummaMoney(listOfMoney);
        if (realAmount < currentAmount) {
            throw new MoneyException("Maksymalna kwota, która jest możliwa - " + realAmount);
        }
        Money moneyToWithdrow = new Money(currency, currentAmount);
        changeListBalance(currentAmount);
        return moneyToWithdrow;
    }

    private void changeListBalance(Double realAmount) {
        Double startAmount = realAmount;
        int i = 0;
        while (startAmount > 0 && i < listOfMoney.size()) {
            Double nextItemOfList = listOfMoney.get(i).amount;
            if (nextItemOfList <= startAmount) {
                startAmount -= nextItemOfList;
                listOfMoney.remove(listOfMoney.get(i));
            } else {
                listOfMoney.remove(listOfMoney.get(i));
                listOfMoney.add(i, new Money(currency, nextItemOfList - startAmount));
                startAmount = 0.0;
            }
        }
    }

    @Override
    public void depositMoney(Money amount) {

        if (!amount.currency.equals(currency)) {
            throw new MoneyException("Aktualna waluta " + amount.currency);
        }
        if (amount.amount > 5000) {
            throw new MoneyException("Kwota jednorazowa nie może być większa za 5000 " + amount.currency);
        }

        listOfMoney.add(amount);
    }

    @Override
    public List<Money> checkBalance() {
        return listOfMoney;
    }

    public static Double getSummaMoney(List<Money> listMoney) {
        Double sum = 0.0;
        for (Money nextItem : listMoney) {
            sum += nextItem.amount;
        }
        return sum;
    }

}
