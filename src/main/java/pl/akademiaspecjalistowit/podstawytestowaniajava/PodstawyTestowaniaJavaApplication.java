package pl.akademiaspecjalistowit.podstawytestowaniajava;

import pl.akademiaspecjalistowit.podstawytestowaniajava.ex2.BankServicePln;
import pl.akademiaspecjalistowit.podstawytestowaniajava.ex2.money.Currency;
import pl.akademiaspecjalistowit.podstawytestowaniajava.ex2.money.Money;

import static pl.akademiaspecjalistowit.podstawytestowaniajava.ex2.BankServicePln.getSummaMoney;

public class PodstawyTestowaniaJavaApplication {

    public static void main(String[] args) {
        BankServicePln bankServicePln = new BankServicePln(Currency.PLN);
        bankServicePln.depositMoney(new Money(Currency.PLN, 100.0));
        //bankServicePln.depositMoney(new Money(Currency.PLN, 5001.0));
        bankServicePln.depositMoney(new Money(Currency.PLN, 200.0));
        System.out.println(getSummaMoney(bankServicePln.checkBalance()));
        System.out.println(bankServicePln.checkBalance());
        bankServicePln.withdrawMoney(new Money(Currency.PLN, 50.0));
        System.out.println(bankServicePln.checkBalance());
        bankServicePln.withdrawMoney(new Money(Currency.PLN, 200.0));
        System.out.println(bankServicePln.checkBalance());
        bankServicePln.withdrawMoney(new Money(Currency.EUR, 1.0));
        System.out.println(bankServicePln.checkBalance());
    }

}
