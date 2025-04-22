package example.tests.entities;

import example.entities.Account;
import example.factory.AccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class AccountTests {

    @Test
    public void depositShouldIncreaseBalanceWhenPositiveAmount () {
        double amount = 200.0;
        double expectedValue = 196.0;

        Account acc = AccountFactory.createEmptyAccount();

        acc.deposit(amount);

        Assertions.assertEquals(expectedValue, acc.getBalance());
    }

    @Test
    public void depositShouldNothingWhenNegativeAmount () {
        double expectedValue = 100.0;
        Account acc = AccountFactory.createAccount(expectedValue);
        double amount = -200.0;

        acc.deposit(amount);

        Assertions.assertEquals(expectedValue, acc.getBalance());
    }

    @Test
    public void fullWithdrawShouldClearBalance () {
        double expectedValue = 0.0;
        double initialBalance = 800.0;
        Account acc = AccountFactory.createAccount(initialBalance);
        double result = acc.fullwithdraw();
        Assertions.assertTrue(expectedValue == acc.getBalance());
        Assertions.assertTrue(result == initialBalance);
    }

    @Test
    public void withdrawShouldDecreaseBalanceWhenSufficientBalance () {
        double initialBalance = 800.0;
        Account acc = AccountFactory.createAccount(initialBalance);
        acc.withdraw(500.0);
        Assertions.assertEquals(300.0, acc.getBalance());
    }

    @Test
    public void withDrawShouldThrowExceptionWhenInsufficientBalance () {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            double initialBalance = 800.0;
            Account acc = AccountFactory.createAccount(initialBalance);
            acc.withdraw(801.0);
        });
    }

}
