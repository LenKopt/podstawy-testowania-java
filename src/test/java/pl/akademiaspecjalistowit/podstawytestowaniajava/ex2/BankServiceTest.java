package pl.akademiaspecjalistowit.podstawytestowaniajava.ex2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.akademiaspecjalistowit.podstawytestowaniajava.ex2.money.Currency;
import pl.akademiaspecjalistowit.podstawytestowaniajava.ex2.money.Money;
import pl.akademiaspecjalistowit.podstawytestowaniajava.ex2.money.MoneyException;

import java.util.List;

class BankServiceTest {

    private BankService bankServiceSuT;


    @BeforeEach
    void setUp() {
        bankServiceSuT = new BankServicePln();
    }

    @Test
    public void should_deposit_money_successfully() {
        //given
        Money money = new Money(Currency.PLN, 14.5);

        //when
        bankServiceSuT.depositMoney(money);

        //then
        assertThat(bankServiceSuT.checkBalance()).isNotEmpty();
        assertThat(bankServiceSuT.checkBalance().get(0)).isEqualTo(money);
    }

    @Test
    public void should_withdraw_money_successfully() {
        //given
        bankServiceSuT.depositMoney(new Money(Currency.PLN, 100.0));
        Money money = new Money(Currency.PLN, 10.0);
        Double resultOperation = getSummaMoney(bankServiceSuT.checkBalance()) - money.amount;

        //when
        bankServiceSuT.withdrawMoney(money);

        //then
        assertThat(getSummaMoney(bankServiceSuT.checkBalance())).isEqualTo(resultOperation);
    }

    private Double getSummaMoney(List<Money> listMoney) {
        Double sum = 0.0;
        for (Money nextItem : listMoney) {
            sum += nextItem.amount;
        }
        return sum;
    }

    @Test
    public void should_not_allow_to_withdraw_money_on_debit() {
        //rozumiem ten test tak, że nie możno pobrać więcej niż saldo
        //given
        bankServiceSuT.depositMoney(new Money(Currency.PLN, 100.0));
        Money money = new Money(Currency.PLN, 110.0);
        Double resultOperation = getSummaMoney(bankServiceSuT.checkBalance()) - money.amount;
        //when
        Money moneyEnd = bankServiceSuT.withdrawMoney(money);
        //then
        try {
            //nie wiem co moge tutaj napisac
        } catch (MoneyException thrown) {
            assertThat(thrown.getMessage()).isNotEqualTo("");
        }
    }

    @Test
    public void balance_should_not_change_when_withdraw_failed() {
        //given
        Double currentBalance = getSummaMoney(bankServiceSuT.checkBalance());
        Money money = new Money(Currency.PLN, currentBalance * 10 / 100);
        //when
        bankServiceSuT.withdrawMoney(money);
        //then
        assertThat(bankServiceSuT.checkBalance()).isEqualTo(currentBalance);
    }

    @Test
    public void should_not_allow_to_deposit_over_5000_at_once() {
        //given
        Money money = new Money(Currency.PLN, 5001.0);
        Double currentBalance = getSummaMoney(bankServiceSuT.checkBalance());
        //when
        bankServiceSuT.depositMoney(money);

        //then
        assertThat(getSummaMoney(bankServiceSuT.checkBalance())).isEqualTo(currentBalance);
    }

    @Test
    public void should_not_allow_to_withdraw_money_with_different_account_currency() {
        //given
        bankServiceSuT.depositMoney(new Money(Currency.PLN, 100.0));
        Money money = new Money(Currency.EUR, 10.0);

        //when
        bankServiceSuT.withdrawMoney(money);
        //then
        try {
            //nie wiem co moge tutaj napisac
        } catch (MoneyException thrown) {
            assertThat(thrown.getMessage()).isNotEqualTo("");
        }
    }

    @Test
    public void should_not_allow_to_deposit_money_with_different_account_currency() {
        //given
        Money money = new Money(Currency.EUR, 14.5);
        Double currentBalance = getSummaMoney(bankServiceSuT.checkBalance());
        //when
        bankServiceSuT.depositMoney(money);

        //then
        assertThat(getSummaMoney(bankServiceSuT.checkBalance())).isEqualTo(currentBalance);
    }
}