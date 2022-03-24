import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("File test/resources/movementListInteger.csv (whole numbers)")
public class TestMovementListInteger {

    private static final double DELTA = 0.01;
    private static final String CSV_FILENAME = "movementListInteger.csv";

    @Test
    @DisplayName("Income")
    void testSumIncome() {
        assertEquals(1500.0, new Movements(getCsvFilenamePath()).getIncomeSum(), DELTA);
    }

    @Test
    @DisplayName("Expense")
    void testSumExpense() {
        assertEquals(300.0, new Movements(getCsvFilenamePath()).getExpenseSum(), DELTA);
    }

    private String getCsvFilenamePath() {
        return new File(this.getClass().getResource(CSV_FILENAME).getPath()).getAbsolutePath();
    }

}