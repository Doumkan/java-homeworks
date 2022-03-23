import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("BankAccount test")
public class BankAccountTest {

    public static final double DELTA = 0.01;

    private static final String notExpectedSumMessage = "The amount on the account does not match the expected";
    private BankAccount bankAccount;

    @BeforeEach
    public void setUp() {
        bankAccount = new BankAccount();
    }

    @Test
    @DisplayName("Method 'put'")
    void put() {
        bankAccount.put(1.0);
        assertEquals(1.0, bankAccount.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Attempt to call method 'put' with negative amount (balance should not change)")
    void putNegativeAmount() {
        bankAccount.put(-1.0);
        assertEquals(0.0, bankAccount.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Method take")
    void take() {
        bankAccount.put(2.0);
        bankAccount.take(1.0);
        assertEquals(1.0, bankAccount.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Attempt to withdraw more money from the account than there is on the account")
    void takeTooMuchMoney() {
        bankAccount.put(2.0);
        bankAccount.take(3.0);
        assertEquals(2.0, bankAccount.getAmount(), DELTA, notExpectedSumMessage);
    }
}