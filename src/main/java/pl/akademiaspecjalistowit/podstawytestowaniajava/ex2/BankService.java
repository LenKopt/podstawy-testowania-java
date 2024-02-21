package pl.akademiaspecjalistowit.podstawytestowaniajava.ex2;

import java.util.List;
import pl.akademiaspecjalistowit.podstawytestowaniajava.ex2.money.Money;

public interface BankService {

    Money withdrawMoney(Money amount);

    void depositMoney(Money amount);

    List<Money> checkBalance();

}
